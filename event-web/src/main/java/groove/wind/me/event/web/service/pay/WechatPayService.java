package groove.wind.me.event.web.service.pay;

import groove.wind.me.event.web.entity.WechatPayResultMap;

public interface WechatPayService {

    /**
     * @Description: 微信支付统一下单
     * @param orderNo: 订单编号
     * @param amount: 实际支付金额
     * @param body: 订单描述
     * @Author:
     * @return
     */
    WechatPayResultMap unifiedOrder(String orderNo, double amount, String body, String ip) ;

    /**
     * @Description: 订单支付异步通知
     * @param notifyStr: 微信异步通知消息字符串
     * @Author:
     * @return
     */
    String notify(String notifyStr) throws Exception;

    /**
     * @Description: 退款
     * @param orderNo: 订单编号
     * @param amount: 实际支付金额
     * @param refundReason: 退款原因
     * @Author:
     * @return
     */
    WechatPayResultMap refund(String orderNo, double amount, String refundReason) throws Exception;

}
