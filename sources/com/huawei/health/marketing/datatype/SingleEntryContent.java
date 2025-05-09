package com.huawei.health.marketing.datatype;

/* loaded from: classes3.dex */
public class SingleEntryContent {
    private final String extend;
    private final long itemEffectiveTime;
    private final long itemExpirationTime;
    private final String linkValue;
    private final String name;
    private final boolean nameVisibility;
    private final String picture;
    private final int priority;
    private final long redEndTime;
    private final String redName;
    private final long redStartTime;

    public SingleEntryContent(Builder builder) {
        this.priority = builder.priority;
        this.name = builder.name;
        this.nameVisibility = builder.nameVisibility;
        this.picture = builder.picture;
        this.linkValue = builder.linkValue;
        this.redName = builder.redName;
        this.redStartTime = builder.redStartTime;
        this.redEndTime = builder.redEndTime;
        this.itemEffectiveTime = builder.itemEffectiveTime;
        this.itemExpirationTime = builder.itemExpirationTime;
        this.extend = builder.extend;
    }

    public static class Builder {
        private String extend;
        private long itemEffectiveTime;
        private long itemExpirationTime;
        private String linkValue;
        private String name;
        private boolean nameVisibility;
        private String picture;
        private int priority;
        private long redEndTime;
        private String redName;
        private long redStartTime;

        public Builder setPriority(int i) {
            this.priority = i;
            return this;
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setNameVisibility(boolean z) {
            this.nameVisibility = z;
            return this;
        }

        public Builder setPicture(String str) {
            this.picture = str;
            return this;
        }

        public Builder setLinkValue(String str) {
            this.linkValue = str;
            return this;
        }

        public Builder setRedName(String str) {
            this.redName = str;
            return this;
        }

        public Builder setRedStartTime(long j) {
            this.redStartTime = j;
            return this;
        }

        public Builder setRedEndTime(long j) {
            this.redEndTime = j;
            return this;
        }

        public Builder setItemEffectiveTime(long j) {
            this.itemEffectiveTime = j;
            return this;
        }

        public Builder setItemExpirationTime(long j) {
            this.itemExpirationTime = j;
            return this;
        }

        public Builder setExtend(String str) {
            this.extend = str;
            return this;
        }

        public SingleEntryContent build() {
            return new SingleEntryContent(this);
        }
    }

    public int getPriority() {
        return this.priority;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNameVisibility() {
        return this.nameVisibility;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public String getRedName() {
        return this.redName;
    }

    public long getRedStartTime() {
        return this.redStartTime;
    }

    public long getRedEndTime() {
        return this.redEndTime;
    }

    public long getItemEffectiveTime() {
        return this.itemEffectiveTime;
    }

    public long getItemExpirationTime() {
        return this.itemExpirationTime;
    }

    public String getExtend() {
        return this.extend;
    }
}
