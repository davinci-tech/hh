package com.huawei.codevalueplatform.coderule.bean.response;

import com.huawei.codevalueplatform.coderule.bean.basebean.GlobalInfo;
import com.huawei.codevalueplatform.coderule.bean.basebean.Rule;
import java.util.List;

/* loaded from: classes3.dex */
public class CodeRulesResponse {
    private long cachePeriod;
    private int cacheVersion;
    private GlobalInfo globalInfo;
    private Integer nextIdx;
    private String resultCode;
    private String resultMessage;
    private List<Rule> ruleList;
    private String transactionID;

    public int getCacheVersion() {
        return this.cacheVersion;
    }

    public void setCacheVersion(int i) {
        this.cacheVersion = i;
    }

    public long getCachePeriod() {
        return this.cachePeriod;
    }

    public void setCachePeriod(long j) {
        this.cachePeriod = j;
    }

    public GlobalInfo getGlobalInfo() {
        return this.globalInfo;
    }

    public void setGlobalInfo(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
    }

    public List<Rule> getRuleList() {
        return this.ruleList;
    }

    public void setRuleList(List<Rule> list) {
        this.ruleList = list;
    }

    public Integer getNextIdx() {
        return this.nextIdx;
    }

    public void setNextIdx(Integer num) {
        this.nextIdx = num;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(String str) {
        this.transactionID = str;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setResultMessage(String str) {
        this.resultMessage = str;
    }
}
