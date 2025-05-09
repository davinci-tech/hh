package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.magazine.inter.MagLockAdContent;

/* loaded from: classes9.dex */
public class hw implements MagLockAdContent {

    /* renamed from: a, reason: collision with root package name */
    private String f6929a;
    private long b;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private int u;
    private int v = 0;

    public String toString() {
        return "MagLockAdContentImpl [contentId=" + this.f6929a + ", endTime=" + this.b + ", url=" + com.huawei.openalliance.ad.utils.cz.f(this.e) + ", sha256=" + com.huawei.openalliance.ad.utils.cz.g(this.f) + ", previewSha256=" + com.huawei.openalliance.ad.utils.cz.g(this.i) + ", fileSize=" + this.g + ", previewUrl=" + com.huawei.openalliance.ad.utils.cz.g(this.h) + ", appType=" + this.v + ", interactionType=" + this.u + "]";
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setUrl(String str) {
        this.e = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setTitle(String str) {
        this.l = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setSha256(String str) {
        this.f = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setPreviewUrl(String str) {
        this.h = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setPreviewSha256(String str) {
        this.i = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setParamFromServer(String str) {
        this.c = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setMonitor(String str) {
        this.j = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setMetaData(String str) {
        this.d = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setFileSize(long j) {
        this.g = j;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setEndTime(long j) {
        this.b = j;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setDescription(String str) {
        this.m = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public void setContentId(String str) {
        this.f6929a = str;
    }

    public void h(String str) {
        this.t = str;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getUrl() {
        return this.e;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getTitle() {
        return this.l;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getTargetPkgName() {
        return this.q;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getTargetAppName() {
        return this.r;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getSha256() {
        return this.f;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getPreviewUrl() {
        return this.h;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getPreviewSha256() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getParamFromServer() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getMonitor() {
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getMetaData() {
        return this.d;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getLabel() {
        return this.n;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public int getInteractionType() {
        return this.u;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getImagePath() {
        return this.k;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public long getFileSize() {
        return this.g;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public long getEndTime() {
        return this.b;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getDescription() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getDeeplinkUrl() {
        return this.p;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getContentUrl() {
        return this.o;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getContentId() {
        return this.f6929a;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public int getAppType() {
        return this.v;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getAdSign() {
        return this.s;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdContent
    public String getAdLabelStr() {
        return this.t;
    }

    public void g(String str) {
        this.s = str;
    }

    public void f(String str) {
        this.r = str;
    }

    public void e(String str) {
        this.q = str;
    }

    public void d(String str) {
        this.p = str;
    }

    public void c(String str) {
        this.o = str;
    }

    public void b(String str) {
        this.n = str;
    }

    public void b(int i) {
        this.u = i;
    }

    public void a(String str) {
        this.k = str;
    }

    public void a(int i) {
        this.v = i;
    }
}
