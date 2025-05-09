package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.e;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ImageInfo implements Serializable {
    private static final long serialVersionUID = 30414300;
    private int checkSha256Flag;

    @e
    private String fileKey;
    private String sha256;

    @a
    private String url;
    private int width = 0;
    private int height = 0;
    private String imageType = "img";
    private int fileSize = 0;

    @e
    private List<String> attachments = new ArrayList();

    public int g() {
        return this.checkSha256Flag;
    }

    public int f() {
        return this.fileSize;
    }

    public int e() {
        return this.height;
    }

    public void d(int i) {
        this.checkSha256Flag = i;
    }

    public int d() {
        return this.width;
    }

    public void c(String str) {
        this.url = str;
    }

    public void c(int i) {
        this.fileSize = i;
    }

    public String c() {
        return this.url;
    }

    public void b(String str) {
        this.imageType = str;
    }

    public void b(int i) {
        this.height = i;
    }

    public String b() {
        return this.imageType;
    }

    public void a(String str) {
        this.sha256 = str;
    }

    public void a(int i) {
        this.width = i;
    }

    public String a() {
        return this.sha256;
    }
}
