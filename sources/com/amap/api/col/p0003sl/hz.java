package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;

@ja(a = VideoPlayFlag.PLAY_IN_ALL)
/* loaded from: classes2.dex */
public final class hz {

    /* renamed from: a, reason: collision with root package name */
    @jb(a = "a1", b = 6)
    private String f1163a;

    @jb(a = "a2", b = 6)
    private String b;

    @jb(a = "a6", b = 2)
    private int c;

    @jb(a = "a3", b = 6)
    private String d;

    @jb(a = "a4", b = 6)
    private String e;

    @jb(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    /* synthetic */ hz(a aVar, byte b) {
        this(aVar);
    }

    private hz() {
        this.c = 1;
        this.l = null;
    }

    private hz(a aVar) {
        this.c = 1;
        this.l = null;
        this.g = aVar.f1164a;
        this.h = aVar.b;
        this.j = aVar.c;
        this.i = aVar.d;
        this.c = aVar.e ? 1 : 0;
        this.k = aVar.f;
        this.l = aVar.g;
        this.b = ia.b(this.h);
        this.f1163a = ia.b(this.j);
        this.d = ia.b(this.i);
        this.e = ia.b(a(this.l));
        this.f = ia.b(this.k);
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f1164a;
        private String b;
        private String c;
        private String d;
        private boolean e = true;
        private String f = MediaManager.ROPE_MEDIA_STANDARD;
        private String[] g = null;

        public a(String str, String str2, String str3) {
            this.f1164a = str2;
            this.b = str2;
            this.d = str3;
            this.c = str;
        }

        public final a a(boolean z) {
            this.e = z;
            return this;
        }

        public final a a(String[] strArr) {
            if (strArr != null) {
                this.g = (String[]) strArr.clone();
            }
            return this;
        }

        public final a a(String str) {
            this.b = str;
            return this;
        }

        public final hz a() throws hm {
            if (this.g == null) {
                throw new hm("sdk packages is null");
            }
            return new hz(this, (byte) 0);
        }
    }

    public final void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public final String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.f1163a)) {
            this.j = ia.c(this.f1163a);
        }
        return this.j;
    }

    public final String b() {
        return this.g;
    }

    public final String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = ia.c(this.b);
        }
        return this.h;
    }

    public final String d() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = ia.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = MediaManager.ROPE_MEDIA_STANDARD;
        }
        return this.k;
    }

    public final boolean e() {
        return this.c == 1;
    }

    public final String[] f() {
        String[] strArr = this.l;
        if ((strArr == null || strArr.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = a(ia.c(this.e));
        }
        return (String[]) this.l.clone();
    }

    private static String[] a(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
                sb.append(";");
            }
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        try {
            if (this.j.equals(((hz) obj).j) && this.g.equals(((hz) obj).g)) {
                if (this.h.equals(((hz) obj).h)) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }
}
