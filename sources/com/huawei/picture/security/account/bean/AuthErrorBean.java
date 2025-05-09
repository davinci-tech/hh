package com.huawei.picture.security.account.bean;

/* loaded from: classes6.dex */
public class AuthErrorBean {
    private String codeKey;
    private String codeValue;
    private String msgKey;
    private String msgValue;
    private String path;

    public AuthErrorBean() {
    }

    public AuthErrorBean(String str, String str2, String str3) {
        this.path = str;
        this.codeKey = str2;
        this.codeValue = str3;
    }

    public String getCodeKey() {
        return this.codeKey;
    }

    public void setCodeKey(String str) {
        this.codeKey = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getCodeValue() {
        return this.codeValue;
    }

    public void setCodeValue(String str) {
        this.codeValue = str;
    }

    public String getMsgKey() {
        return this.msgKey;
    }

    public void setMsgKey(String str) {
        this.msgKey = str;
    }

    public String getMsgValue() {
        return this.msgValue;
    }

    public void setMsgValue(String str) {
        this.msgValue = str;
    }
}
