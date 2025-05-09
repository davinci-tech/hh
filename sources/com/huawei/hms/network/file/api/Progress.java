package com.huawei.hms.network.file.api;

/* loaded from: classes4.dex */
public class Progress {
    long finishedSize;
    int progress;
    Request request;
    long speed;
    long totalSize;
    String url;

    public String toString() {
        return "Progress{progress=" + this.progress + ", totalSize=" + this.totalSize + ", finishedSize=" + this.finishedSize + ", speed=" + this.speed + "(byte/s)}";
    }

    public String getUrl() {
        return this.url;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public long getSpeed() {
        return this.speed;
    }

    public Request getRequest() {
        return this.request;
    }

    public int getProgress() {
        return this.progress;
    }

    public long getFinishedSize() {
        return this.finishedSize;
    }

    public Progress(Request request, String str, int i, long j, long j2, long j3) {
        this.request = request;
        this.url = str;
        this.progress = i;
        this.totalSize = j;
        this.finishedSize = j2;
        this.speed = j3;
    }
}
