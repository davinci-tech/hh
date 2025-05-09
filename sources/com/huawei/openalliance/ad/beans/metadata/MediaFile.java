package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.constant.MimeType;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class MediaFile implements Serializable {
    private static final long serialVersionUID = 30422300;
    private long fileSize;
    private int height;
    private String sha256;

    @a
    private String url;
    private int width;
    private String mime = "video/mp4";
    private int checkSha256Flag = 0;
    private int downloadNetwork = 0;
    private int playMode = 1;

    public int l() {
        if (MimeType.JPEG.equals(this.mime) || MimeType.JPG.equals(this.mime) || "image/png".equals(this.mime)) {
            return 2;
        }
        return MimeType.GIF.equals(this.mime) ? 4 : 100;
    }

    public boolean k() {
        if (TextUtils.isEmpty(this.mime)) {
            return false;
        }
        return "video/mp4".equals(this.mime);
    }

    public boolean j() {
        return !TextUtils.isEmpty(this.mime) && (MimeType.JPEG.equals(this.mime) || MimeType.JPG.equals(this.mime) || "image/png".equals(this.mime) || MimeType.GIF.equals(this.mime));
    }

    public int i() {
        return this.playMode;
    }

    public int h() {
        return this.downloadNetwork;
    }

    public int g() {
        return this.checkSha256Flag;
    }

    public String f() {
        return this.sha256;
    }

    public void e(int i) {
        this.playMode = i;
    }

    public String e() {
        return this.url;
    }

    public void d(int i) {
        this.downloadNetwork = i;
    }

    public long d() {
        return this.fileSize;
    }

    public void c(String str) {
        this.sha256 = str;
    }

    public void c(int i) {
        this.checkSha256Flag = i;
    }

    public int c() {
        return this.height;
    }

    public void b(String str) {
        this.url = str;
    }

    public void b(int i) {
        this.height = i;
    }

    public int b() {
        return this.width;
    }

    public void a(String str) {
        this.mime = str;
    }

    public void a(long j) {
        this.fileSize = j;
    }

    public void a(int i) {
        this.width = i;
    }

    public String a() {
        return this.mime;
    }
}
