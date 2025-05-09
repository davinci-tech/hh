package com.huawei.openalliance.ad.db.bean;

/* loaded from: classes5.dex */
public class ContentResource extends a {
    public static final String CACHE_TYPE = "cacheType";
    public static final String CONTENT_ID = "contentId";
    public static final String FILE_NAME = "fileName";
    public static final String PRIORITY = "priority";
    public static final String SLOT_ID = "slotId";
    public static final String UPDATE_TIME = "updateTime";
    private int adType;
    private String cacheType;
    private String contentId;
    private int downloadSource;
    private String fileName;
    private int isPreload;
    private int priority;
    private String resType;
    private String slotId;
    private int unzipStatus;
    private long updateTime;
    private int useCount;

    public String j() {
        return this.cacheType;
    }

    public String i() {
        return this.resType;
    }

    public int h() {
        return this.useCount;
    }

    public int g() {
        return this.downloadSource;
    }

    public void f(int i) {
        this.unzipStatus = i;
    }

    public int f() {
        return this.isPreload;
    }

    public void e(String str) {
        this.cacheType = str;
    }

    public void e(int i) {
        this.useCount = i;
    }

    public int e() {
        return this.priority;
    }

    public void d(String str) {
        this.resType = str;
    }

    public void d(int i) {
        this.downloadSource = i;
    }

    public int d() {
        return this.adType;
    }

    public void c(String str) {
        this.contentId = str;
    }

    public void c(int i) {
        this.isPreload = i;
    }

    public String c() {
        return this.contentId;
    }

    public void b(String str) {
        this.slotId = str;
    }

    public void b(int i) {
        this.priority = i;
    }

    public String b() {
        return this.slotId;
    }

    public void a(String str) {
        this.fileName = str;
    }

    public void a(long j) {
        this.updateTime = j;
    }

    public void a(int i) {
        this.adType = i;
    }

    public String a() {
        return this.fileName;
    }

    public ContentResource(ContentRecord contentRecord) {
        this.adType = -1;
        this.downloadSource = 2;
        this.contentId = contentRecord.m();
        this.slotId = contentRecord.l();
        this.adType = contentRecord.e();
    }

    public ContentResource() {
        this.adType = -1;
        this.downloadSource = 2;
    }
}
