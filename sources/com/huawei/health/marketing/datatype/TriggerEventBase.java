package com.huawei.health.marketing.datatype;

/* loaded from: classes3.dex */
public class TriggerEventBase {
    private final int delay;
    private final int eventSubtype;
    private final int eventType;
    private final String eventValue;

    public TriggerEventBase(Builder builder) {
        this.eventType = builder.eventType;
        this.eventSubtype = builder.eventSubtype;
        this.eventValue = builder.eventValue;
        this.delay = builder.delay;
    }

    public String toString() {
        return "[eventType:" + this.eventType + ", eventValue:" + this.eventValue + "]";
    }

    public static class Builder {
        private int delay;
        private int eventSubtype;
        private int eventType;
        private String eventValue;

        public Builder setEventType(int i) {
            this.eventType = i;
            return this;
        }

        public Builder setEventSubType(int i) {
            this.eventSubtype = i;
            return this;
        }

        public Builder setEventValue(String str) {
            this.eventValue = str;
            return this;
        }

        public Builder setDelay(int i) {
            this.delay = i;
            return this;
        }

        public TriggerEventBase build() {
            return new TriggerEventBase(this);
        }
    }

    public int getEventType() {
        return this.eventType;
    }

    public int getEventSubType() {
        return this.eventSubtype;
    }

    public String getEventValue() {
        return this.eventValue;
    }

    public int getDelay() {
        return this.delay;
    }
}
