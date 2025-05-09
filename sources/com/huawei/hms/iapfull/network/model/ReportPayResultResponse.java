package com.huawei.hms.iapfull.network.model;

/* loaded from: classes9.dex */
public class ReportPayResultResponse extends BaseResponse {
    private String newSign;
    private String returnDevSign;
    private String signatureAlgorithm;

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setReturnDevSign(String str) {
        this.returnDevSign = str;
    }

    public void setNewSign(String str) {
        this.newSign = str;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public String getReturnDevSign() {
        return this.returnDevSign;
    }

    public String getNewSign() {
        return this.newSign;
    }

    public ReportPayResultResponse(String str, String str2) {
        super(str, str2);
    }

    public ReportPayResultResponse(String str) {
        super(str);
    }

    public ReportPayResultResponse() {
    }
}
