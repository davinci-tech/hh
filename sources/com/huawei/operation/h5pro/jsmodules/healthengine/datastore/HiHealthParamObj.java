package com.huawei.operation.h5pro.jsmodules.healthengine.datastore;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealthkit.HiHealthDataQueryOption;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
class HiHealthParamObj {

    @SerializedName("deviceInfo")
    private HiHealthDeviceObj deviceInfo;

    @SerializedName("hiHealthUnit")
    private int hiHealthUnit;

    @SerializedName("metadata")
    private String metadata;

    @SerializedName("queryOption")
    private HiHealthDataQueryOption queryOption;

    @SerializedName("sequenceContent")
    private String sequenceContent;

    @SerializedName("simpleData")
    private String simpleData;

    @SerializedName("value")
    private double value;

    @SerializedName("valueHolder")
    private Map<String, Object> valueHolder;

    @SerializedName("type")
    private int type = 0;

    @SerializedName("startTime")
    private long startTime = 0;

    @SerializedName("endTime")
    private long endTime = 0;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long modifyTime = 0;

    @SerializedName("map")
    private List<HiHealthMapItemObj> map = null;

    HiHealthParamObj() {
    }

    public int getType() {
        return this.type;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public List<HiHealthMapItemObj> getMap() {
        return this.map;
    }

    public HiHealthDataQueryOption getQueryOption() {
        HiHealthDataQueryOption hiHealthDataQueryOption = this.queryOption;
        return hiHealthDataQueryOption == null ? new HiHealthDataQueryOption() : hiHealthDataQueryOption;
    }

    public HiHealthDeviceObj getDeviceInfo() {
        return this.deviceInfo;
    }

    public double getValue() {
        return this.value;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public String getSimpleData() {
        return this.simpleData;
    }

    public void setSimpleData(String str) {
        this.simpleData = str;
    }

    public String getSequenceContent() {
        return this.sequenceContent;
    }

    public void setSequenceContent(String str) {
        this.sequenceContent = str;
    }

    public int getHiHealthUnit() {
        return this.hiHealthUnit;
    }

    public Map<String, Object> getValueHolder() {
        return this.valueHolder;
    }
}
