package com.huawei.hianalytics;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.config.RomAttributeCollector;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class f implements RomAttributeCollector {

    /* renamed from: a, reason: collision with root package name */
    public int f3851a;

    /* renamed from: a, reason: collision with other field name */
    public String f34a;
    public int b;

    /* renamed from: b, reason: collision with other field name */
    public String f35b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;

    @Override // com.huawei.hianalytics.framework.config.RomAttributeCollector
    public JSONObject doCollector() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_rom_ver", this.f34a);
            jSONObject.put("_emui_ver", this.f35b);
            jSONObject.put("_model", this.c);
            jSONObject.put("_package_name", this.d);
            jSONObject.put("_app_ver", this.e);
            jSONObject.put("_lib_ver", this.f);
            jSONObject.put("_lib_name", this.h);
            jSONObject.put("_channel", this.g);
            jSONObject.put("_manufacturer", this.i);
            jSONObject.put("_oaid_tracking_flag", this.l);
            jSONObject.put("_app_brand", this.j);
            jSONObject.put("_brand", this.k);
            jSONObject.put("_mcc", this.q);
            jSONObject.put("_mnc", this.r);
            jSONObject.put("_os", this.m);
            jSONObject.put("_os_ver", this.n);
            jSONObject.put("_screen_height", this.f3851a);
            jSONObject.put("_screen_width", this.b);
            jSONObject.put("_language", this.p);
            jSONObject.put("_hm_version", this.o);
        } catch (Exception unused) {
            HiLog.e("OpennessRomCollector", "json exception");
        }
        return jSONObject;
    }
}
