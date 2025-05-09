package com.huawei.openalliance.ad.db.bean;

import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class ContentTemplateRecord extends a implements Serializable {
    public static final String ASSETS = "assets";
    public static final String CONTENT_ID = "contentId";
    public static final String MOTIONS = "motions";
    public static final String MOTION_DATA = "motionData";
    public static final String TEMPLATE_CONTEXT = "templateContext";
    public static final String TEMPLATE_ID = "templateId";
    private static final long serialVersionUID = 1999006876326113126L;
    private List<Asset> assets;
    private String contentId;
    private List<MotionData> motionData;
    private String motions;
    private String templateContext;
    private String templateId;
    private String templateUrl;

    public List<MotionData> f() {
        return this.motionData;
    }

    public String e() {
        return this.motions;
    }

    public String d() {
        return this.templateContext;
    }

    public List<Asset> c() {
        return this.assets;
    }

    public String b() {
        return this.templateId;
    }

    public void a(String str) {
        this.templateUrl = str;
    }

    public String a() {
        return this.contentId;
    }

    public ContentTemplateRecord(String str, String str2, List<Asset> list, TemplateData templateData) {
        this.contentId = str;
        this.templateId = str2;
        this.assets = list;
        if (templateData != null) {
            this.templateContext = templateData.a();
            this.motions = templateData.b();
            this.motionData = templateData.c();
        }
    }

    public ContentTemplateRecord() {
    }
}
