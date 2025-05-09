package com.huawei.wisesecurity.kfs.ha.message;

import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public abstract class BaseReportMsgBuilder implements ReportMsgBuilder {
    private long nanoCallTime;
    private long segmentationStartTime;
    private int segCount = 6;
    public LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

    @Override // com.huawei.wisesecurity.kfs.ha.message.ReportMsgBuilder
    public LinkedHashMap<String, String> build() {
        return this.linkedHashMap;
    }

    public BaseReportMsgBuilder setTransId(String str) {
        this.linkedHashMap.put("transId", str);
        return this;
    }

    public BaseReportMsgBuilder setPackageName(String str) {
        this.linkedHashMap.put("packageName", str);
        return this;
    }

    public BaseReportMsgBuilder setAppId(String str) {
        this.linkedHashMap.put("appId", str);
        return this;
    }

    public BaseReportMsgBuilder setVersion(String str) {
        this.linkedHashMap.put("version", str);
        return this;
    }

    public BaseReportMsgBuilder setApiName(String str) {
        this.linkedHashMap.put("apiName", str);
        return this;
    }

    public BaseReportMsgBuilder setStatusCode(int i) {
        this.linkedHashMap.put("statusCode", String.valueOf(i));
        return this;
    }

    public BaseReportMsgBuilder setErrorCode(int i) {
        this.linkedHashMap.put("errorCode", String.valueOf(i));
        return this;
    }

    public BaseReportMsgBuilder setInnerErrorCode(int i) {
        this.linkedHashMap.put("innerErrorCode", String.valueOf(i));
        return this;
    }

    public BaseReportMsgBuilder setErrorMsg(String str) {
        this.linkedHashMap.put("errorMsg", str);
        return this;
    }

    public BaseReportMsgBuilder setInnerErrorMsg(String str) {
        this.linkedHashMap.put("innerErrorMsg", str);
        return this;
    }

    public BaseReportMsgBuilder setCallTime() {
        long nanoTime = System.nanoTime();
        this.nanoCallTime = nanoTime;
        this.segmentationStartTime = nanoTime;
        this.linkedHashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        return this;
    }

    public BaseReportMsgBuilder setCostTime() {
        this.linkedHashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf((System.nanoTime() - this.nanoCallTime) / 1000000));
        return this;
    }

    public BaseReportMsgBuilder setSegT1CostTime() {
        this.linkedHashMap.put("t1", String.valueOf((System.nanoTime() - this.segmentationStartTime) / 1000000));
        this.segmentationStartTime = System.nanoTime();
        return this;
    }

    public BaseReportMsgBuilder setSegT2CostTime() {
        this.linkedHashMap.put("t2", String.valueOf((System.nanoTime() - this.segmentationStartTime) / 1000000));
        this.segmentationStartTime = System.nanoTime();
        return this;
    }

    public BaseReportMsgBuilder setSegT3CostTime() {
        this.linkedHashMap.put("t3", String.valueOf((System.nanoTime() - this.segmentationStartTime) / 1000000));
        this.segmentationStartTime = System.nanoTime();
        return this;
    }

    public BaseReportMsgBuilder setSegT4CostTime() {
        this.linkedHashMap.put("t4", String.valueOf((System.nanoTime() - this.segmentationStartTime) / 1000000));
        this.segmentationStartTime = System.nanoTime();
        return this;
    }

    public BaseReportMsgBuilder setSegT5CostTime() {
        this.linkedHashMap.put("t5", String.valueOf((System.nanoTime() - this.segmentationStartTime) / 1000000));
        this.segmentationStartTime = System.nanoTime();
        return this;
    }

    public BaseReportMsgBuilder setSegTnCostTime() {
        LinkedHashMap<String, String> linkedHashMap = this.linkedHashMap;
        StringBuilder sb = new StringBuilder(FitRunPlayAudio.PLAY_TYPE_T);
        int i = this.segCount;
        this.segCount = i + 1;
        sb.append(i);
        linkedHashMap.put(sb.toString(), String.valueOf((System.nanoTime() - this.segmentationStartTime) / 1000000));
        this.segmentationStartTime = System.nanoTime();
        return this;
    }
}
