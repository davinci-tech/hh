package com.huawei.openalliance.ad;

import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class gz {
    private ImageInfo b;
    private VideoInfo f;
    private AppInfo g;
    private String h;
    private VideoConfiguration l;

    /* renamed from: a, reason: collision with root package name */
    private String f6893a = null;
    private int c = 0;
    private String d = "n";
    private boolean e = false;
    private boolean i = false;
    private int j = 0;
    private String k = Boolean.FALSE.toString();

    public VideoConfiguration i() {
        return this.l;
    }

    public String h() {
        return this.k;
    }

    public String g() {
        return this.f6893a;
    }

    public String f() {
        return this.h;
    }

    public boolean e() {
        return this.e;
    }

    public void d(String str) {
        this.k = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.f6893a = str;
    }

    public int c() {
        return this.c;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void b(String str) {
        this.h = str;
    }

    public ImageInfo b() {
        return this.b;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(AppInfo appInfo) {
        this.g = appInfo;
    }

    public void a(VideoInfo videoInfo) {
        this.f = videoInfo;
    }

    public void a(ImageInfo imageInfo) {
        this.b = imageInfo;
    }

    public void a(VideoConfiguration videoConfiguration) {
        this.l = videoConfiguration;
    }

    public void a(int i) {
        this.c = i;
    }

    public VideoInfo a() {
        return this.f;
    }
}
