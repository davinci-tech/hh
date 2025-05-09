package com.huawei.hms.iapfull.network.model;

import android.text.TextUtils;
import com.huawei.hms.iapfull.y0;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes4.dex */
public class ReportEntity extends PayResponse {
    private static final String YEAR_TO_DATE = "yyyy-MM-dd";
    private static final String YEAR_TO_SECOND_24 = "yyyy-MM-dd HH:mm:ss";
    private static final long serialVersionUID = 8662926143095592044L;
    private boolean isSuccessToPayResultActivity = true;
    private String payType;
    private String relatedOrderId;
    private String reportEntityProductName;
    private String serverReturnCode;
    private String tempFpid;
    private String tempStr;
    private String userName;
    private String withHoldId;
    private String yeeOrAliPaySign;
    private String yeeOrAliPaySignContent;

    public void updateInfo(PayResponse payResponse) {
        super.udpate(payResponse);
    }

    public void setYeeOrAliPaySignContent(String str) {
        this.yeeOrAliPaySignContent = str;
    }

    public void setYeeOrAliPaySign(String str) {
        this.yeeOrAliPaySign = str;
    }

    public void setWithHoldId(String str) {
        this.withHoldId = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public void setTradeTime() {
        if (TextUtils.isEmpty(this.tradeTime)) {
            this.tradeTime = getFormatTime(YEAR_TO_SECOND_24);
        }
    }

    public void setTempStr(String str) {
        this.tempStr = str;
    }

    public void setTempFpid(String str) {
        this.tempFpid = str;
    }

    public void setSuccessToPayResultActivity(boolean z) {
        this.isSuccessToPayResultActivity = z;
    }

    public void setServerReturnCode(String str) {
        this.serverReturnCode = str;
    }

    @Override // com.huawei.hms.iapfull.network.model.BaseResponse
    public void setReturnCode(String str) {
        this.returnCode = str;
    }

    public void setReportInfo(String str, String str2, String str3, String str4) {
        this.requestId = str;
        this.reportEntityProductName = str2;
        this.userName = str3;
        this.payType = str4;
    }

    public void setReportEntityProductName(String str) {
        this.reportEntityProductName = str;
    }

    public void setRelatedOrderid(String str) {
        this.relatedOrderId = str;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    @Override // com.huawei.hms.iapfull.network.model.PayResponse
    public void setAmount(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.amount = str;
    }

    public boolean isSuccessToPayResultActivity() {
        return this.isSuccessToPayResultActivity;
    }

    public boolean isPayResultSuccess() {
        return "0".equals(this.returnCode);
    }

    public String getYeeOrAliPaySignContent() {
        return this.yeeOrAliPaySignContent;
    }

    public String getYeeOrAliPaySign() {
        return this.yeeOrAliPaySign;
    }

    public String getWithHoldId() {
        return this.withHoldId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getTempStr() {
        return this.tempStr;
    }

    public String getTempFpid() {
        return this.tempFpid;
    }

    public String getServerReturnCode() {
        return this.serverReturnCode;
    }

    @Override // com.huawei.hms.iapfull.network.model.BaseResponse
    public String getReturnCode() {
        if (TextUtils.isEmpty(this.returnCode)) {
            return null;
        }
        return this.returnCode;
    }

    public String getReportEntityProductName() {
        return this.reportEntityProductName;
    }

    public String getRelatedOrderid() {
        return this.relatedOrderId;
    }

    public Integer getPayTypeInt() {
        return Integer.valueOf(Integer.parseInt(getPayType()));
    }

    public String getPayType() {
        return this.payType;
    }

    public PayResponse getPayResultResponse() {
        try {
            return super.mo607clone();
        } catch (CloneNotSupportedException unused) {
            y0.a("ReportEntity", "getPayResultResponse CloneNotSupportedException");
            return new PayResponse("-1");
        }
    }

    public String getPayOrderId() {
        return TextUtils.isEmpty(this.orderNo) ? this.requestId : this.orderNo;
    }

    public String getAmount() {
        return this.amount;
    }

    @Override // com.huawei.hms.iapfull.network.model.PayResponse
    /* renamed from: clone */
    public ReportEntity mo607clone() throws CloneNotSupportedException {
        return (ReportEntity) super.mo607clone();
    }

    public void checkAndSetTmpStr(boolean z, String str) {
        if (z) {
            this.tempStr = str;
        }
    }

    private String getFormatTime(String str) {
        return (TextUtils.isEmpty(str) ? new SimpleDateFormat(YEAR_TO_DATE, Locale.US) : new SimpleDateFormat(str, Locale.US)).format(new Date());
    }

    public ReportEntity(String str) {
        this.returnCode = str;
    }

    public ReportEntity() {
    }
}
