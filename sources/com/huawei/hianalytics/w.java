package com.huawei.hianalytics;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hms.common.PackageConstants;
import com.huawei.secure.android.common.util.SafeBase64;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    public static volatile w f3958a;
    public static final List<String> e = Arrays.asList(PackageConstants.GENERAL_SERVICES_ACTION, "com.huawei.hwid.core");
    public static final List<String> f = Arrays.asList("Analytics_Kit_Tag");

    /* renamed from: a, reason: collision with other field name */
    public boolean f94a = true;

    /* renamed from: a, reason: collision with other field name */
    public int f90a = 1;

    /* renamed from: a, reason: collision with other field name */
    public long f91a = 86400000;
    public int b = 30;
    public int c = 30;
    public int d = 30;

    /* renamed from: e, reason: collision with other field name */
    public int f99e = com.huawei.hms.network.embedded.y.c;

    /* renamed from: f, reason: collision with other field name */
    public int f100f = 50;
    public int g = 512;
    public int h = 300;

    /* renamed from: a, reason: collision with other field name */
    public String f92a = "";

    /* renamed from: b, reason: collision with other field name */
    public boolean f96b = true;

    /* renamed from: a, reason: collision with other field name */
    public final List<String> f93a = new ArrayList();

    /* renamed from: b, reason: collision with other field name */
    public final List<String> f95b = new ArrayList();

    /* renamed from: c, reason: collision with other field name */
    public final List<String> f97c = new ArrayList();

    /* renamed from: d, reason: collision with other field name */
    public final List<String> f98d = new ArrayList();

    /* renamed from: a, reason: collision with other method in class */
    public final void m595a() {
        String a2;
        synchronized (this) {
            HiLog.d("MCC", "init");
            try {
                a2 = j.a("kit_ha_metric_config", "mc_config", "");
            } catch (Throwable th) {
                HiLog.w("MCC", "init fail " + th.getMessage());
                m596b();
            }
            if (TextUtils.isEmpty(a2)) {
                m596b();
                return;
            }
            JSONObject jSONObject = new JSONObject(a2);
            boolean z = this.f94a;
            boolean optBoolean = jSONObject.optBoolean("collectEnable", false);
            this.f94a = optBoolean;
            if (optBoolean != z) {
                t.a(optBoolean);
            }
            this.f90a = jSONObject.optInt("idFlag", 1);
            this.f91a = jSONObject.optLong("reportInterval", 86400000L);
            this.b = jSONObject.optInt("randomBound", 30);
            this.f99e = jSONObject.optInt("minValidTime", com.huawei.hms.network.embedded.y.c);
            this.c = jSONObject.optInt("totalCount", 30);
            this.d = jSONObject.optInt("batchSize", 30);
            this.f100f = jSONObject.optInt("titleLength", 50);
            this.g = jSONObject.optInt("artistLength", 512);
            this.h = jSONObject.optInt("albumLength", 300);
            this.f92a = jSONObject.optString("mepUrl", "");
            this.f96b = jSONObject.optBoolean("checkCollectUrl", true);
            List<String> a3 = a(jSONObject, "whiteProcess");
            if (!((ArrayList) a3).isEmpty()) {
                a(a3, this.f95b, false);
            }
            List<String> a4 = a(jSONObject, "whiteTag");
            if (!((ArrayList) a4).isEmpty()) {
                a(a4, this.f97c, false);
            }
            List<String> a5 = a(jSONObject, "whiteList");
            if (!((ArrayList) a5).isEmpty()) {
                a(a5, this.f93a, true);
            }
            List<String> a6 = a(jSONObject, "whiteTimeList");
            if (!((ArrayList) a6).isEmpty()) {
                a(a6, this.f98d, true);
            }
        }
    }

    public final void a(List<String> list, List<String> list2, boolean z) {
        list2.clear();
        for (String str : list) {
            if (z) {
                try {
                    str = new String(SafeBase64.decode(j.b(str), 0), StandardCharsets.UTF_8);
                } catch (Throwable th) {
                    HiLog.w("MCC", "initList fail: " + th.getMessage());
                }
            }
            list2.add(str);
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    public final void m596b() {
        HiLog.d("MCC", "reset");
        this.f94a = true;
        this.f90a = 1;
        this.f91a = 86400000L;
        this.b = 30;
        this.f99e = com.huawei.hms.network.embedded.y.c;
        this.c = 30;
        this.d = 30;
        this.f100f = 50;
        this.g = 512;
        this.h = 300;
        this.f92a = "";
        this.f96b = true;
        try {
            a(a(e), this.f95b, false);
            a(a(f), this.f97c, false);
            this.f93a.clear();
            this.f98d.clear();
        } catch (Throwable th) {
            HiLog.w("MCC", "reset fail: " + th.getMessage());
        }
    }

    public long b() {
        return this.f91a;
    }

    public final List<String> a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            String optString = optJSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    public final List<String> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    /* renamed from: a, reason: collision with other method in class */
    public long m594a() {
        return new Random().nextInt(this.b) * 60000;
    }

    public static w a() {
        if (f3958a == null) {
            synchronized (w.class) {
                if (f3958a == null) {
                    f3958a = new w();
                }
            }
        }
        return f3958a;
    }

    public w() {
        m595a();
    }
}
