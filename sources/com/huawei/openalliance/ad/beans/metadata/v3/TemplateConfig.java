package com.huawei.openalliance.ad.beans.metadata.v3;

/* loaded from: classes5.dex */
public class TemplateConfig {
    private String style;
    private int styleVer;
    private String tag;
    private String templateId;

    public String d() {
        return this.tag;
    }

    public String c() {
        return this.style;
    }

    public int b() {
        return this.styleVer;
    }

    public String a() {
        return this.templateId;
    }

    public TemplateConfig(String str, int i) {
        this.templateId = str;
        this.styleVer = i;
    }

    public TemplateConfig() {
    }
}
