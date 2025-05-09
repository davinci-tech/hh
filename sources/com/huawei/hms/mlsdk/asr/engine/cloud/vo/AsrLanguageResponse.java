package com.huawei.hms.mlsdk.asr.engine.cloud.vo;

/* loaded from: classes9.dex */
public class AsrLanguageResponse {
    private LanguageListInfo result;
    private String retCode;
    private String retMsg;

    public LanguageListInfo getResult() {
        return this.result;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public String getRetMsg() {
        return this.retMsg;
    }

    public void setResult(LanguageListInfo languageListInfo) {
        this.result = languageListInfo;
    }

    public void setRetCode(String str) {
        this.retCode = str;
    }

    public void setRetMsg(String str) {
        this.retMsg = str;
    }
}
