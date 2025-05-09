package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.utils.dn;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class PlacementMediaFile implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f7048a;
    private String b;
    private long c;
    private int d;
    private int e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    private long k;
    private String l;
    private int m;

    public int a() {
        return 209715200;
    }

    public boolean isVideo() {
        return "video/mp4".equals(this.f7048a);
    }

    public boolean isValid(Context context) {
        return dn.a(context, this.b, (long) a());
    }

    public int getWidth() {
        return this.d;
    }

    public String getUrl() {
        return this.b;
    }

    public String getSoundSwitch() {
        return this.g;
    }

    public String getSha256() {
        return this.f;
    }

    public int getPlayMode() {
        return this.j;
    }

    public String getMime() {
        return this.f7048a;
    }

    public int getHeight() {
        return this.e;
    }

    public long getFileSize() {
        return this.c;
    }

    public long getDuration() {
        return this.k;
    }

    public int getDownloadNetwork() {
        return this.i;
    }

    public int getCheckSha256() {
        return this.h;
    }

    public Float d() {
        int i;
        int i2 = this.d;
        if (i2 <= 0 || (i = this.e) <= 0) {
            return null;
        }
        return Float.valueOf(i2 / i);
    }

    public int c() {
        return this.m;
    }

    public void b(String str) {
        this.l = str;
    }

    public String b() {
        return this.l;
    }

    public boolean a(Context context) {
        if (dn.a(context, this.l, a())) {
            return 1 == this.h || dn.a(context, this.l, this.f);
        }
        return false;
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(int i) {
        this.m = i;
    }

    public PlacementMediaFile(MediaFile mediaFile, long j) {
        this.d = 0;
        this.e = 0;
        this.g = "y";
        this.i = 0;
        this.m = 0;
        this.f7048a = mediaFile.a();
        this.b = mediaFile.e();
        this.c = mediaFile.d();
        this.h = mediaFile.g();
        this.i = mediaFile.h();
        this.d = mediaFile.b();
        this.e = mediaFile.c();
        this.f = mediaFile.f();
        this.j = mediaFile.i();
        this.k = j;
    }

    public PlacementMediaFile() {
        this.d = 0;
        this.e = 0;
        this.g = "y";
        this.i = 0;
        this.m = 0;
    }
}
