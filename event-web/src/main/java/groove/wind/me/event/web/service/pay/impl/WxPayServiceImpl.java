package groove.wind.me.event.web.service.pay.impl;

import groove.wind.me.event.web.config.WechatPayConfig;
import groove.wind.me.event.web.entity.WechatPayResultMap;
import groove.wind.me.event.web.sdk.WXPay;
import groove.wind.me.event.web.sdk.WXPayUtil;
import groove.wind.me.event.web.service.pay.WechatPayService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class WxPayServiceImpl implements WechatPayService {

    @Autowired
    private WechatPayConfig wechatPayConfig;

    @Override
    public WechatPayResultMap unifiedOrder(String orderNo, double amount, String body, String ip) {
        Map<String, String> returnMap = new HashMap<>();
        Map<String, String> responseMap = new HashMap<>();
        Map<String, String> requestMap = new HashMap<>();
        try {
            WXPay wxpay = new WXPay(wechatPayConfig);
            requestMap.put("body", body);                                     // 商品描述
            requestMap.put("out_trade_no", orderNo);                          // 商户订单号
            requestMap.put("total_fee", String.valueOf((int)(amount*100)));   // 总金额
            requestMap.put("trade_type", "JSAPI");                              // App支付类型
            requestMap.put("spbill_create_ip", ip);
            requestMap.put("notify_url", wechatPayConfig.getPayNotifyUrl());   // 接收微信支付异步通知回调地址
            Map<String, String> resultMap = wxpay.unifiedOrder(requestMap);
            for (String resultKey : resultMap.keySet()) {
                log.info("订单key:{}", resultMap.get(resultKey));
            }

            //获取返回码
            String returnCode = resultMap.get("return_code");
            String returnMsg = resultMap.get("return_msg");
            //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = resultMap.get("result_code");
                String errCodeDes = resultMap.get("err_code_des");
                if ("SUCCESS".equals(resultCode)) {
                    responseMap = resultMap;
                }
            }
            if (responseMap == null || responseMap.isEmpty()) {
                return WechatPayResultMap.error("获取预支付交易会话标识失败");
            }
            // 3、签名生成算法
            Long time = System.currentTimeMillis() / 1000;
            String timestamp = time.toString();
            returnMap.put("appid", wechatPayConfig.getAppID());
            returnMap.put("partnerid", wechatPayConfig.getMchID());
            returnMap.put("prepayid", responseMap.get("prepay_id"));
            returnMap.put("noncestr", responseMap.get("nonce_str"));
            returnMap.put("timestamp", timestamp);
            returnMap.put("package", "Sign=WXPay");
            returnMap.put("sign", WXPayUtil.generateSignature(returnMap, wechatPayConfig.getKey()));//微信支付签名
            return WechatPayResultMap.ok().put("data", returnMap);
        } catch (Exception e) {
            log.warn("订单号：{}，错误信息：{}", orderNo, e.getMessage());
            return WechatPayResultMap.error("微信支付统一下单失败");
        }
    }

    @Override
    public String notify(String notifyStr) throws Exception {
        String xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
        try {
            // 转换成map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(notifyStr);
            WXPay wxpayApp = new WXPay(wechatPayConfig);
            if (wxpayApp.isPayResultNotifySignatureValid(resultMap)) {
                String returnCode = resultMap.get("return_code");  //状态
                String outTradeNo = resultMap.get("out_trade_no");//商户订单号
                String transactionId = resultMap.get("transaction_id");
                if (returnCode.equals("SUCCESS")) {
                    if (StringUtils.isNotBlank(outTradeNo)) {
                        /**
                         * 注意！！！
                         * 请根据业务流程，修改数据库订单支付状态，和其他数据的相应状态
                         *
                         */
                        log.info("微信手机支付回调成功,订单号:{}", outTradeNo);
                        xmlBack = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return xmlBack;
    }

    @Override
    public WechatPayResultMap refund(String orderNo, double amount, String refundReason) throws Exception {
        if(StringUtils.isEmpty(orderNo)){
            return WechatPayResultMap.error("订单编号不能为空");
        }
        if(amount <= 0){
            return WechatPayResultMap.error("退款金额必须大于0");
        }

        Map<String, String> responseMap = new HashMap<>();
        Map<String, String> requestMap = new HashMap<>();
        WXPay wxpay = new WXPay(wechatPayConfig);

        requestMap.put("out_trade_no", orderNo);
        requestMap.put("out_refund_no", "xxxx自行手动生成");//商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
        requestMap.put("total_fee", "订单总金额");
        requestMap.put("refund_fee", String.valueOf((int)(amount*100)));//所需退款金额
        requestMap.put("refund_desc", refundReason);
        try {
            responseMap = wxpay.refund(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String responseKey : responseMap.keySet()) {
            log.info("订单key:{}", responseMap.get(responseKey));
        }
        String return_code = responseMap.get("return_code");   //返回状态码
        String return_msg = responseMap.get("return_msg");     //返回信息
        if ("SUCCESS".equals(return_code)) {
            String result_code = responseMap.get("result_code");       //业务结果
            String err_code_des = responseMap.get("err_code_des");     //错误代码描述
            if ("SUCCESS".equals(result_code)) {
                //表示退款申请接受成功，结果通过退款查询接口查询
                //修改用户订单状态为退款申请中或已退款。退款异步通知根据需求，可选
                return WechatPayResultMap.ok("退款申请成功");
            } else {
                log.info("订单号:{}错误信息:{}", orderNo, err_code_des);
                return WechatPayResultMap.error(err_code_des);
            }
        } else {
            log.info("订单号:{}错误信息:{}", orderNo, return_msg);
            return WechatPayResultMap.error(return_msg);
        }
    }


}
