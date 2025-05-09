package com.huawei.openalliance.ad.beans.metadata.v3;

import java.util.List;

/* loaded from: classes5.dex */
public class SlotTemplateRsp {
    private List<String> deletedTemplates;
    private String slotId;
    private List<TemplateConfig> updatedTemplates;

    public List<String> c() {
        return this.deletedTemplates;
    }

    public List<TemplateConfig> b() {
        return this.updatedTemplates;
    }

    public String a() {
        return this.slotId;
    }
}
