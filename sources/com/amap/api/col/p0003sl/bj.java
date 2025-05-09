package com.amap.api.col.p0003sl;

import com.huawei.openalliance.ad.constant.GlobalUtilKeys;
import com.huawei.openalliance.ad.db.bean.ContentResource;

@ja(a = "update_item")
/* loaded from: classes2.dex */
public class bj {

    @jb(a = GlobalUtilKeys.LOCAL_PATH, b = 6)
    protected String h;

    @jb(a = "mCompleteCode", b = 2)
    protected int j;

    @jb(a = "mState", b = 2)
    public int l;

    /* renamed from: a, reason: collision with root package name */
    @jb(a = "title", b = 6)
    protected String f921a = null;

    @jb(a = "url", b = 6)
    protected String b = null;

    @jb(a = "mAdcode", b = 6)
    protected String c = null;

    @jb(a = ContentResource.FILE_NAME, b = 6)
    protected String d = null;

    @jb(a = "version", b = 6)
    protected String e = "";

    @jb(a = "lLocalLength", b = 5)
    protected long f = 0;

    @jb(a = "lRemoteLength", b = 5)
    protected long g = 0;

    @jb(a = "isProvince", b = 2)
    protected int i = 0;

    @jb(a = "mCityCode", b = 6)
    protected String k = "";

    @jb(a = "mPinyin", b = 6)
    public String m = "";

    public final String c() {
        return this.f921a;
    }

    public final String d() {
        return this.e;
    }

    public final String e() {
        return this.c;
    }

    public final void c(String str) {
        this.c = str;
    }

    public final String f() {
        return this.b;
    }

    public final int g() {
        return this.j;
    }

    public final void d(String str) {
        this.k = str;
    }

    public final String h() {
        return this.m;
    }

    public static String e(String str) {
        return "mAdcode='" + str + "'";
    }

    public static String f(String str) {
        return "mPinyin='" + str + "'";
    }
}
