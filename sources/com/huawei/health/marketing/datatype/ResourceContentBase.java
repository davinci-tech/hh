package com.huawei.health.marketing.datatype;

/* loaded from: classes3.dex */
public class ResourceContentBase {
    private String content;
    private final String lang;

    public ResourceContentBase(Builder builder) {
        this.lang = builder.lang;
        this.content = builder.content;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private String content;
        private String lang;

        public Builder setLang(String str) {
            this.lang = str;
            return this;
        }

        public Builder setContent(String str) {
            this.content = str;
            return this;
        }

        public ResourceContentBase build() {
            return new ResourceContentBase(this);
        }
    }

    public String getLang() {
        return this.lang;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String toString() {
        return "ResourceContentBase{lang='" + this.lang + "', content='" + this.content + "'}";
    }
}
