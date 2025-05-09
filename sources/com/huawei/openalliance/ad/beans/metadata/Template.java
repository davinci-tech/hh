package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public class Template {
    private String templateContent;
    private String templateId;

    public String c() {
        return this.templateContent;
    }

    public String b() {
        return this.templateId;
    }

    public boolean a() {
        return (TextUtils.isEmpty(this.templateId) || TextUtils.isEmpty(this.templateContent)) ? false : true;
    }
}
