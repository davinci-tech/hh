package com.huawei.operation.h5pro.jsmodules.healthengine.service;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes9.dex */
class QueryDataParamObj {

    @SerializedName("bundle")
    private Map<String, Object> bundle;

    @SerializedName("filterKeys")
    private String filterKeys;

    @SerializedName("filterValues")
    private String filterValues;

    @SerializedName("queryOption")
    private QueryDataOption queryOption;

    @SerializedName("subTypes")
    private ArrayList<Integer> subTypes;

    @SerializedName("type")
    private int type = 0;

    @SerializedName("startTime")
    private long startTime = 0;

    @SerializedName("endTime")
    private long endTime = 0;

    @SerializedName("valueFilterType")
    private int valueFilterType = 0;

    @SerializedName("aggregateType")
    private int aggregateType = 0;

    @SerializedName("groupUnitType")
    private int groupUnitType = 0;

    QueryDataParamObj() {
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

    public int getValueFilterType() {
        return this.valueFilterType;
    }

    public QueryDataOption getQueryOption() {
        return this.queryOption;
    }

    public Map<String, Object> getBundle() {
        return this.bundle;
    }

    public int getAggregateType() {
        return this.aggregateType;
    }

    public int getGroupUnitType() {
        return this.groupUnitType;
    }

    public ArrayList<Integer> getSubTypes() {
        return this.subTypes;
    }

    public String getFilterKeys() {
        return this.filterKeys;
    }

    public String getFilterValues() {
        return this.filterValues;
    }

    public static class QueryDataOption {

        @SerializedName(BleConstants.LIMIT)
        private int limit;

        @SerializedName(TypedValues.CycleType.S_WAVE_OFFSET)
        private int offset;

        @SerializedName("order")
        private int order;

        public int getLimit() {
            return this.limit;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getOrder() {
            return this.order;
        }
    }
}
