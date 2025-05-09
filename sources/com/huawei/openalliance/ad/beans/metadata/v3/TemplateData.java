package com.huawei.openalliance.ad.beans.metadata.v3;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class TemplateData implements Serializable {
    private static final long serialVersionUID = 881899438177174869L;
    private String componentContext;
    private List<MotionData> motionData;
    private String motions;
    private String templateContext;

    public String d() {
        return this.componentContext;
    }

    public void c(String str) {
        this.componentContext = str;
    }

    public List<MotionData> c() {
        return this.motionData;
    }

    public void b(String str) {
        this.motions = str;
    }

    public String b() {
        return this.motions;
    }

    public void a(List<MotionData> list) {
        this.motionData = list;
    }

    public void a(String str) {
        this.templateContext = str;
    }

    public String a() {
        return this.templateContext;
    }

    public TemplateData(String str, String str2, List<MotionData> list) {
        this.templateContext = str;
        this.motions = str2;
        this.motionData = list;
    }

    public TemplateData() {
    }
}
