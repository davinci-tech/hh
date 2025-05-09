package com.huawei.trade.datatype;

/* loaded from: classes9.dex */
public class FunctionBriefInfo {
    private String deepLink;
    private String functionValue;

    public String getDeepLink() {
        return this.deepLink;
    }

    public String getFunctionValue() {
        return this.functionValue;
    }

    public void setDeepLink(String str) {
        this.deepLink = str;
    }

    public void setFunctionValue(String str) {
        this.functionValue = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("deepLink=");
        sb.append(this.deepLink);
        sb.append(" functionValue=");
        sb.append(this.functionValue);
        return sb.toString();
    }
}
