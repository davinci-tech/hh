package com.huawei.openalliance.ad.beans.metadata.v3.openrtb;

import com.huawei.openalliance.ad.annotations.d;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class Image implements Serializable {
    private static final long serialVersionUID = -8414679420743760437L;

    @d(a = "Ext")
    private ImageExt ext;

    @d(a = "H")
    private int height;
    private String localPath;
    private String url;

    @d(a = "W")
    private int width;

    public String e() {
        return this.localPath;
    }

    public ImageExt d() {
        return this.ext;
    }

    public int c() {
        return this.height;
    }

    public void b(String str) {
        this.localPath = str;
    }

    public void b(int i) {
        this.height = i;
    }

    public int b() {
        return this.width;
    }

    public void a(String str) {
        this.url = str;
    }

    public void a(ImageExt imageExt) {
        this.ext = imageExt;
    }

    public void a(int i) {
        this.width = i;
    }

    public String a() {
        return this.url;
    }
}
