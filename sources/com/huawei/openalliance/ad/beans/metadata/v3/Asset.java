package com.huawei.openalliance.ad.beans.metadata.v3;

import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Data;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Image;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Title;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Video;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class Asset implements Serializable {
    private static final long serialVersionUID = 1094499159973642680L;
    private String alias;
    private String context;
    private Data data;
    private String filePath;
    private int id;
    private Image img;
    private Title title;
    private Video video;

    public String h() {
        return this.context;
    }

    public String g() {
        return this.filePath;
    }

    public Title f() {
        return this.title;
    }

    public Video e() {
        return this.video;
    }

    public Image d() {
        return this.img;
    }

    public void c(String str) {
        this.context = str;
    }

    public Data c() {
        return this.data;
    }

    public void b(String str) {
        this.filePath = str;
    }

    public String b() {
        return this.alias;
    }

    public void a(String str) {
        this.alias = str;
    }

    public void a(Video video) {
        this.video = video;
    }

    public void a(Title title) {
        this.title = title;
    }

    public void a(Image image) {
        this.img = image;
    }

    public void a(Data data) {
        this.data = data;
    }

    public void a(int i) {
        this.id = i;
    }

    public int a() {
        return this.id;
    }
}
