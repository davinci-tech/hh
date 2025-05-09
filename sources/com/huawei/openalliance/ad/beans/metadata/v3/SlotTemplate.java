package com.huawei.openalliance.ad.beans.metadata.v3;

import java.util.List;

/* loaded from: classes5.dex */
public class SlotTemplate {
    private String slotId;
    private List<TemplateConfig> templates;

    public SlotTemplate(String str, List<TemplateConfig> list) {
        this.slotId = str;
        this.templates = list;
    }
}
