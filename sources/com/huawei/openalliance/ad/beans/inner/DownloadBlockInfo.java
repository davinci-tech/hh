package com.huawei.openalliance.ad.beans.inner;

/* loaded from: classes5.dex */
public class DownloadBlockInfo {
    private volatile long endTime;
    private volatile boolean fullFile;
    private volatile long size;
    private long startTime;

    public String toString() {
        return "DownloadBlockInfo{size=" + this.size + ", fullFile=" + this.fullFile + ", duration=" + (this.endTime - this.startTime) + '}';
    }

    public boolean c() {
        return this.fullFile;
    }

    public void c(long j) {
        this.size = j;
    }

    public void b(long j) {
        this.endTime = j;
    }

    public long b() {
        return this.endTime - this.startTime;
    }

    public void a(boolean z) {
        this.fullFile = z;
    }

    public void a(long j) {
        this.startTime = j;
    }

    public long a() {
        return this.size;
    }
}
