package com.amap.api.col.p0003sl;

@ja(a = "update_item_download_info")
/* loaded from: classes2.dex */
class bh {

    /* renamed from: a, reason: collision with root package name */
    @jb(a = "mAdcode", b = 6)
    private String f919a;

    @jb(a = "fileLength", b = 5)
    private long b;

    @jb(a = "splitter", b = 2)
    private int c;

    @jb(a = "startPos", b = 5)
    private long d;

    @jb(a = "endPos", b = 5)
    private long e;

    public bh() {
        this.f919a = "";
        this.b = 0L;
        this.c = 0;
        this.d = 0L;
        this.e = 0L;
    }

    public bh(String str, long j, int i, long j2, long j3) {
        this.f919a = str;
        this.b = j;
        this.c = i;
        this.d = j2;
        this.e = j3;
    }

    public static String a(String str) {
        return "mAdcode='" + str + "'";
    }
}
