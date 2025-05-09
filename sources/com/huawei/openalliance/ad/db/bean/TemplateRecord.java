package com.huawei.openalliance.ad.db.bean;

import com.huawei.openalliance.ad.annotations.e;

/* loaded from: classes5.dex */
public class TemplateRecord extends a {
    public static final String TEMPLATE_ID = "templateId";

    @e
    private String _id;
    private String templateContent;
    private String templateId;

    public void b(String str) {
        this.templateContent = str;
    }

    public void a(String str) {
        this.templateId = str;
    }

    public String a() {
        return this.templateId;
    }
}
