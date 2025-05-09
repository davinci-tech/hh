package com.huawei.health.marketing.datatype;

/* loaded from: classes3.dex */
public class MarketingEventInfo {
    private final String huId;
    private final int positionId;
    private final String reservedField;
    private final String resourceId;
    private final long triggerTime;
    private final int typeId;
    private final String value;

    public MarketingEventInfo(Builder builder) {
        this.huId = builder.huId;
        this.positionId = builder.positionId;
        this.typeId = builder.typeId;
        this.resourceId = builder.resourceId;
        this.triggerTime = builder.triggerTime;
        this.value = builder.value;
        this.reservedField = builder.reservedField;
    }

    public static class Builder {
        private String huId;
        private int positionId;
        private String reservedField;
        private String resourceId;
        private long triggerTime;
        private int typeId;
        private String value;

        public Builder setHuId(String str) {
            this.huId = str;
            return this;
        }

        public Builder setPositionId(int i) {
            this.positionId = i;
            return this;
        }

        public Builder setTypeId(int i) {
            this.typeId = i;
            return this;
        }

        public Builder setResourceId(String str) {
            this.resourceId = str;
            return this;
        }

        public Builder setTriggerTime(long j) {
            this.triggerTime = j;
            return this;
        }

        public Builder setValue(String str) {
            this.value = str;
            return this;
        }

        public Builder setReservedField(String str) {
            this.reservedField = str;
            return this;
        }

        public MarketingEventInfo build() {
            return new MarketingEventInfo(this);
        }
    }

    public String getHuId() {
        return this.huId;
    }

    public int getPositionId() {
        return this.positionId;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public long getTriggerTime() {
        return this.triggerTime;
    }

    public String getValue() {
        return this.value;
    }

    public String getReservedField() {
        return this.reservedField;
    }
}
