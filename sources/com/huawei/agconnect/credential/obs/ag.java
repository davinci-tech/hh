package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import com.huawei.agconnect.datastore.annotation.SharedPreference;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ag {

    /* renamed from: a, reason: collision with root package name */
    private Boolean f1744a;
    private String b;

    @SharedPreference(fileName = "agc_site", isDynamic = true, key = "backup")
    String backUrl;

    @SharedPreference(fileName = "agc_site", isDynamic = true, key = "main")
    String mainUrl;

    @SharedPreference(fileName = "agc_site", isDynamic = true, key = HwPayConstant.KEY_VALIDTIME)
    long validTime;

    public int hashCode() {
        return a(this.mainUrl, this.backUrl);
    }

    public boolean g() {
        return (TextUtils.isEmpty(this.mainUrl) && TextUtils.isEmpty(this.backUrl)) ? false : true;
    }

    public long f() {
        return this.validTime;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ag agVar = (ag) obj;
        return a(this.mainUrl, agVar.mainUrl) && a(this.backUrl, agVar.backUrl);
    }

    public String e() {
        return this.b;
    }

    public Boolean d() {
        return this.f1744a;
    }

    public String c() {
        return this.f1744a.booleanValue() ? this.backUrl : this.mainUrl;
    }

    public String b() {
        return this.backUrl;
    }

    public void a(String str, boolean z) {
        this.b = str;
        this.f1744a = Boolean.valueOf(z);
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(this.mainUrl)) {
            ah.a().a(this, str);
        }
        if (TextUtils.isEmpty(this.backUrl)) {
            return;
        }
        ah.a().b(this, str);
    }

    public void a(Boolean bool) {
        this.f1744a = bool;
    }

    public String a() {
        return this.mainUrl;
    }

    public static void c(String str) {
        ah.a().a(str);
        ah.a().b(str);
    }

    public static ag b(String str) {
        ag agVar = new ag();
        ah.a().d(agVar, str);
        ah.a().e(agVar, str);
        ah.a().f(agVar, str);
        return agVar;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void a(String str, long j) {
        ag agVar = new ag();
        agVar.validTime = j;
        ah.a().c(agVar, str);
    }

    private int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public ag(String str, String str2) {
        this.validTime = 0L;
        this.f1744a = false;
        this.b = null;
        this.mainUrl = str;
        this.backUrl = str2;
        if (TextUtils.isEmpty(str)) {
            this.f1744a = true;
        }
    }

    private ag() {
        this.validTime = 0L;
        this.f1744a = false;
        this.b = null;
    }
}
