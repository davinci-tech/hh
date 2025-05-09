package com.huawei.watchface.mvp.model.pay;

import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.watchface.be;

/* loaded from: classes9.dex */
public class JsPayResult extends be {
    private String amount;
    private String errMsg;
    private String orderID;
    private String requestId;
    private int resultCode;
    private int returnCode;
    private String sign;
    private String time;
    private String userName;
    private String withholdID;

    public void setPayResultInfo(PayResultInfo payResultInfo) {
        if (payResultInfo == null) {
            return;
        }
        setResultCode(payResultInfo.getReturnCode());
        setErrMsg(payResultInfo.getErrMsg());
        setOrderID(payResultInfo.getOrderID());
        setAmount(payResultInfo.getAmount());
        setCurrency(payResultInfo.getCurrency());
        setCountry(payResultInfo.getCountry());
        setTime(payResultInfo.getTime());
        setWithholdID(payResultInfo.getWithholdID());
        setRequestId(payResultInfo.getRequestId());
        setUserName(payResultInfo.getUserName());
        setSign(payResultInfo.getSign());
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String str) {
        this.orderID = str;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getWithholdID() {
        return this.withholdID;
    }

    public void setWithholdID(String str) {
        this.withholdID = str;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }
}
