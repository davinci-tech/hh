package com.huawei.openalliance.ad.db.bean;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateConfig;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bl;
import java.util.Map;

/* loaded from: classes5.dex */
public class TemplateStyleRecord extends a {
    private static final String H5_TEMPLATE_TAG = "js";
    private static final int H5_TEMPLATE_TAG_VALUE = 1;
    public static final String SLOT_ID = "slotId";
    public static final String STYLE = "style";
    public static final String STYLE_VER = "styleVer";
    public static final String TAG = "tag";
    public static final String TEMPLATE_ID = "templateId";
    public static final String TMP_TYPE = "tmpType";
    private String slotId;
    private String style;
    private int styleVer;
    private String tag;
    private String templateId;
    private int tmpType;

    public String e() {
        return this.tag;
    }

    public int d() {
        return this.styleVer;
    }

    public String c() {
        return this.style;
    }

    public String b() {
        return this.templateId;
    }

    public String a() {
        return this.slotId;
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ho.a("tag", "tag: %s", str);
        Map map = (Map) be.b(str, Map.class, Integer.class);
        if (bl.a(map)) {
            ho.a("tag", "tag is null");
        } else if (map.containsKey(H5_TEMPLATE_TAG) && ((Integer) map.get(H5_TEMPLATE_TAG)).intValue() == 1) {
            this.tmpType = 1;
        }
    }

    public TemplateStyleRecord(String str, TemplateConfig templateConfig, int i) {
        this.slotId = str;
        this.templateId = templateConfig.a();
        this.style = templateConfig.c();
        this.styleVer = templateConfig.b();
        this.tag = templateConfig.d();
        this.tmpType = i;
    }

    public TemplateStyleRecord(String str, TemplateConfig templateConfig) {
        this.slotId = str;
        this.templateId = templateConfig.a();
        this.style = templateConfig.c();
        this.styleVer = templateConfig.b();
        this.tag = templateConfig.d();
        a(templateConfig.d());
    }

    public TemplateStyleRecord() {
    }
}
