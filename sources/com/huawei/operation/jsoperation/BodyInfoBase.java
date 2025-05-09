package com.huawei.operation.jsoperation;

/* loaded from: classes5.dex */
public class BodyInfoBase {
    protected long mEndTime;
    protected long mStartTime;
    private String mTimezone;
    protected int mType;

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public String getTimezone() {
        return this.mTimezone;
    }

    public void setTimezone(String str) {
        this.mTimezone = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public static class SamplePointsBeanBase {
        private long mEndTime;
        private String mKey;
        private long mStartTime;
        private String mUnit;

        public String getUnit() {
            return this.mUnit;
        }

        public void setUnit(String str) {
            this.mUnit = str;
        }

        public String getKey() {
            return this.mKey;
        }

        public void setKey(String str) {
            this.mKey = str;
        }

        public long getEndTime() {
            return this.mEndTime;
        }

        public void setEndTime(long j) {
            this.mEndTime = j;
        }

        public long getStartTime() {
            return this.mStartTime;
        }

        public void setStartTime(long j) {
            this.mStartTime = j;
        }
    }
}
