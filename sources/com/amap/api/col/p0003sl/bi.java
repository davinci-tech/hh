package com.amap.api.col.p0003sl;

@ja(a = "update_item_file")
/* loaded from: classes2.dex */
class bi {

    /* renamed from: a, reason: collision with root package name */
    @jb(a = "mAdcode", b = 6)
    private String f920a;

    @jb(a = "file", b = 6)
    private String b;

    public bi() {
        this.f920a = "";
        this.b = "";
    }

    public bi(String str, String str2) {
        this.f920a = str;
        this.b = str2;
    }

    public final String a() {
        return this.b;
    }

    public static String a(String str) {
        return "mAdcode='" + str + "'";
    }
}
