package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.openalliance.ad.utils.dn;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class ImageInfo implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f7044a;
    private String b;
    private int c;
    private int d;
    private int e;
    private String f;
    private boolean g;
    private String h;

    public int a() {
        return 52428800;
    }

    public boolean isCheckSha256() {
        return this.g;
    }

    public int getWidth() {
        return this.c;
    }

    public String getUrl() {
        return this.f7044a;
    }

    public String getSha256() {
        return this.f;
    }

    public String getOriginalUrl() {
        return this.b;
    }

    public String getImageType() {
        return this.h;
    }

    public int getHeight() {
        return this.d;
    }

    public int getFileSize() {
        return this.e;
    }

    public void d(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.f7044a = str;
    }

    public void c(int i) {
        this.e = i;
    }

    public boolean b(Context context) {
        return dn.a(context, this.f7044a, (long) a()) && (!this.g || dn.a(context, this.f7044a, this.f));
    }

    public void b(String str) {
        this.f = str;
    }

    public void b(int i) {
        this.c = i;
    }

    public boolean a(Context context) {
        return dn.a(context, this.f7044a, a());
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(String str) {
        this.h = str;
    }

    public void a(int i) {
        this.d = i;
    }

    public ImageInfo(com.huawei.openalliance.ad.beans.metadata.ImageInfo imageInfo) {
        this.c = 0;
        this.d = 0;
        if (imageInfo != null) {
            this.f7044a = imageInfo.c();
            this.b = imageInfo.c();
            this.c = imageInfo.d();
            this.d = imageInfo.e();
            this.f = imageInfo.a();
            this.h = imageInfo.b();
            this.e = imageInfo.f();
            this.g = imageInfo.g() == 0;
        }
    }

    public ImageInfo() {
        this.c = 0;
        this.d = 0;
    }
}
