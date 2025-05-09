package com.huawei.codevalueplatform.coderule.bean.basebean;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class Rule {
    private int cacheVersion;
    private ArrayList<IntentInfo> intentInfo;
    private int matchType;
    private String port;
    private String ruleId;
    private String schema = "";
    private String host = "";
    private String path = "";
    private String matchedStr = "";

    public String getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(String str) {
        this.ruleId = str;
    }

    public int getMatchType() {
        return this.matchType;
    }

    public void setMatchType(int i) {
        this.matchType = i;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String str) {
        this.schema = str;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String str) {
        this.port = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getMatchedStr() {
        return this.matchedStr;
    }

    public void setMatchedStr(String str) {
        this.matchedStr = str;
    }

    public ArrayList<IntentInfo> getIntentInfo() {
        return this.intentInfo;
    }

    public void setIntentInfo(ArrayList<IntentInfo> arrayList) {
        this.intentInfo = arrayList;
    }

    public int getCacheVersion() {
        return this.cacheVersion;
    }

    public void setCacheVersion(int i) {
        this.cacheVersion = i;
    }
}
