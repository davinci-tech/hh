package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dn;
import java.io.File;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class VideoInfo implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f7052a;
    private String b;
    private int c;
    private int d;
    private String e;
    private int f;
    private String g;
    private int h;
    private String i;
    private int j;
    private String k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private Float s;
    private boolean t;
    private boolean u;
    private float v;
    private int w;
    private String x;

    public int a() {
        return 209715200;
    }

    public void j(int i) {
        this.w = i;
    }

    public boolean isCheckSha256() {
        return this.m;
    }

    public boolean isBackFromFullScreen() {
        return this.o;
    }

    public void i(int i) {
        this.f = i;
    }

    public String i() {
        return this.x;
    }

    public void h(int i) {
        if (i == 1) {
            this.r = 1;
        } else {
            this.r = 0;
        }
    }

    public int h() {
        return this.w;
    }

    public Float getVideoRatio() {
        return this.s;
    }

    public int getVideoPlayMode() {
        return this.l;
    }

    public int getVideoFileSize() {
        return this.d;
    }

    public int getVideoDuration() {
        return this.c;
    }

    public String getVideoDownloadUrl() {
        return this.f7052a;
    }

    public String getVideoAutoPlayWithSound() {
        return this.g;
    }

    public String getVideoAutoPlay() {
        return this.e;
    }

    public int getTimeBeforeVideoAutoPlay() {
        ho.b("VideoInfo", "getTimeBeforeVideoAutoPlay, custom exposure type: %s", Integer.valueOf(this.w));
        int i = this.w;
        if (i == 2 || i == 1) {
            return 0;
        }
        return this.h;
    }

    public String getSoundSwitch() {
        return this.k;
    }

    public String getSha256() {
        return this.i;
    }

    public int getDownloadNetwork() {
        return this.r;
    }

    public int getAutoStopPlayAreaRatio() {
        return this.q;
    }

    public int getAutoPlayNetwork() {
        return this.f;
    }

    public int getAutoPlayAreaRatio() {
        return this.p;
    }

    public void g(String str) {
        this.x = str;
    }

    public void g(int i) {
        this.q = i;
    }

    public String g() {
        return this.b;
    }

    public boolean f() {
        return this.u;
    }

    public void f(String str) {
        this.b = str;
    }

    public void f(int i) {
        this.p = i;
    }

    public boolean e() {
        return this.t;
    }

    public void e(boolean z) {
        this.u = z;
    }

    public void e(String str) {
        this.k = str;
    }

    public void e(int i) {
        this.j = i;
    }

    public void d(boolean z) {
        this.t = z;
    }

    public void d(String str) {
        this.i = str;
    }

    public void d(int i) {
        this.l = i;
    }

    public float d() {
        return this.v;
    }

    public boolean c() {
        return this.n;
    }

    public void c(boolean z) {
        this.o = z;
    }

    public void c(String str) {
        this.g = str;
    }

    public void c(int i) {
        this.h = i;
    }

    public boolean b(Context context) {
        String str;
        dk a2;
        String str2;
        int i = this.l;
        if (2 == i || 3 == i || this.u) {
            return true;
        }
        if (TextUtils.isEmpty(this.f7052a)) {
            return false;
        }
        String str3 = this.f7052a;
        if (str3 != null && str3.startsWith(Scheme.CONTENT.toString())) {
            return true;
        }
        if (this.f7052a.startsWith("http")) {
            dk a3 = dh.a(context, "normal");
            str = a3.c(a3.e(this.f7052a));
            if (cz.b(str)) {
                a2 = dh.a(context, Constants.TPLATE_CACHE);
                str2 = a2.e(this.f7052a);
                str = a2.c(str2);
            }
            return 1 != this.l && dn.a(context, str, (long) a()) && (!this.m || dn.a(context, str, this.i));
        }
        if (dk.i(this.f7052a)) {
            str = dh.a(context, "normal").c(this.f7052a);
            if (!ae.d(new File(str))) {
                a2 = dh.a(context, Constants.TPLATE_CACHE);
                str2 = this.f7052a;
                str = a2.c(str2);
            }
        } else {
            str = this.f7052a;
        }
        if (1 != this.l) {
        }
    }

    public void b(boolean z) {
        this.n = z;
    }

    public void b(String str) {
        this.e = str;
    }

    public void b(int i) {
        this.d = i;
    }

    public int b() {
        return this.j;
    }

    public boolean a(Context context) {
        int i = this.l;
        if (2 == i || this.u) {
            return true;
        }
        return 1 == i && dn.a(context, this.f7052a, (long) a());
    }

    public void a(boolean z) {
        this.m = z;
    }

    public void a(String str) {
        this.f7052a = str;
    }

    public void a(Float f) {
        if (f == null) {
            f = null;
        } else if (f.floatValue() <= 0.0f) {
            f = Float.valueOf(1.7777778f);
        }
        this.s = f;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(float f) {
        if (f <= 0.0f) {
            f = 3.5f;
        }
        this.v = f;
    }

    public VideoInfo(PlacementMediaFile placementMediaFile) {
        this.e = "y";
        this.g = "n";
        this.h = 200;
        this.j = 0;
        this.k = "n";
        this.l = 1;
        this.n = true;
        this.o = false;
        this.p = 100;
        this.q = 90;
        this.r = 0;
        this.t = true;
        this.u = false;
        this.w = 0;
        this.x = Boolean.FALSE.toString();
        if (placementMediaFile != null) {
            this.b = placementMediaFile.getUrl();
            this.m = placementMediaFile.getCheckSha256() == 0;
            this.i = placementMediaFile.getSha256();
        }
    }

    public VideoInfo(com.huawei.openalliance.ad.beans.metadata.VideoInfo videoInfo) {
        this.e = "y";
        this.g = "n";
        this.h = 200;
        this.j = 0;
        this.k = "n";
        this.l = 1;
        this.n = true;
        this.o = false;
        this.p = 100;
        this.q = 90;
        this.r = 0;
        this.t = true;
        this.u = false;
        this.w = 0;
        this.x = Boolean.FALSE.toString();
        if (videoInfo != null) {
            this.f7052a = videoInfo.a();
            this.b = videoInfo.a();
            this.c = videoInfo.b();
            this.d = videoInfo.c();
            if (TextUtils.equals(videoInfo.d(), "y") || TextUtils.equals(videoInfo.d(), VideoPlayFlag.PLAY_IN_ALL)) {
                this.e = "y";
            } else {
                this.e = "n";
            }
            this.g = videoInfo.e();
            this.h = videoInfo.f();
            this.i = videoInfo.g();
            this.l = videoInfo.h();
            this.k = this.g;
            this.m = videoInfo.i() == 0;
            if (videoInfo.j() != null) {
                this.p = videoInfo.j().intValue();
            }
            if (videoInfo.k() != null) {
                this.q = videoInfo.k().intValue();
            }
            h(videoInfo.l());
            if (TextUtils.equals(videoInfo.d(), VideoPlayFlag.PLAY_IN_ALL)) {
                this.f = 1;
            } else {
                this.f = 0;
            }
            a(videoInfo.m());
            this.t = "y".equalsIgnoreCase(videoInfo.n());
            a(videoInfo.o());
        }
    }

    public VideoInfo() {
        this.e = "y";
        this.g = "n";
        this.h = 200;
        this.j = 0;
        this.k = "n";
        this.l = 1;
        this.n = true;
        this.o = false;
        this.p = 100;
        this.q = 90;
        this.r = 0;
        this.t = true;
        this.u = false;
        this.w = 0;
        this.x = Boolean.FALSE.toString();
    }
}
