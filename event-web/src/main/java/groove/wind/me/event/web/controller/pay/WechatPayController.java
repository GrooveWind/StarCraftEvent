package groove.wind.me.event.web.controller.pay;

import groove.wind.me.event.web.entity.WechatPayResultMap;
import groove.wind.me.event.web.service.event.EventOrderService;
import groove.wind.me.event.web.service.pay.WechatPayService;
import groove.wind.me.event.web.utils.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@RestController
@Api(value = "微信支付api", tags = "微信支付api")
@RequestMapping("/biz/pay/wechat")
@Log4j2
public class WechatPayController {

    @Autowired
    WechatPayService wechatPayService;

    @Autowired
    EventOrderService eventOrderService;

    /**
     * 统一下单接口
     */
    @PostMapping("/unifiedOrder")
    @ApiOperation(value = "统一下单", notes = "统一下单")
    public WechatPayResultMap unifiedOrder(@ApiParam(value = "订单金额-单位 元") @RequestParam double amount,
                                           @ApiParam(value = "商品名称") @RequestParam String body,
                                           HttpServletRequest request) {
        try {
            // 创建订单
            String orderNum = eventOrderService.buildOrderNum();
            eventOrderService.buildOrder(orderNum);

            // 向微信服务器发送请求
            WechatPayResultMap resultMap = wechatPayService.unifiedOrder(orderNum, amount, body, IpUtils.getIpAddr(request));

            return resultMap;
        } catch (Exception e) {
            log.error(e);
            return WechatPayResultMap.error(500, e.getMessage());
        }
    }

    /**
     * 微信支付异步通知
     */
    @PostMapping(value = "/notify")
    @ApiOperation(value = "支付异步通知", notes = "支付异步通知")
    public String payNotify(HttpServletRequest request) {
        String xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
        try {
            xmlBack = wechatPayService.notify(IOUtils.toString(request.getInputStream(), Charset.forName("utf8")));
        } catch (Exception e) {
            log.error("微信手机支付回调通知失败：", e);
        }
        return xmlBack;
    }

    @PostMapping("/refund")
    @ApiOperation(value = "退款", notes = "退款")
    public WechatPayResultMap refund(@ApiParam(value = "订单号") @RequestParam String orderNo,
                            @ApiParam(value = "退款金额") @RequestParam double amount,
                            @ApiParam(value = "退款原因") @RequestParam(required = false) String refundReason) throws Exception {

        return wechatPayService.refund(orderNo, amount, refundReason);
    }

}
