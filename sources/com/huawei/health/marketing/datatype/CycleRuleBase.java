package com.huawei.health.marketing.datatype;

/* loaded from: classes3.dex */
public class CycleRuleBase {
    private final int cycleSubtype;
    private final int cycleType;
    private final String cycleValue;

    private CycleRuleBase(Builder builder) {
        this.cycleType = builder.cycleType;
        this.cycleSubtype = builder.cycleSubtype;
        this.cycleValue = builder.cycleValue;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private int cycleSubtype;
        private int cycleType;
        private String cycleValue;

        public Builder setCycleType(int i) {
            this.cycleType = i;
            return this;
        }

        public Builder setCycleSubType(int i) {
            this.cycleSubtype = i;
            return this;
        }

        public Builder setCycleValue(String str) {
            this.cycleValue = str;
            return this;
        }

        public CycleRuleBase build() {
            return new CycleRuleBase(this);
        }
    }

    public int getCycleType() {
        return this.cycleType;
    }

    public int getCycleSubType() {
        return this.cycleSubtype;
    }

    public String getCycleValue() {
        return this.cycleValue;
    }
}
