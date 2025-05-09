package com.huawei.basefitnessadvice.model;

/* loaded from: classes3.dex */
public class Media {
    private long mDownloadLength;
    private boolean mIsFinished;
    private long mLength;
    private String mPath;
    private int mType;
    private String mUrl;

    /* loaded from: classes8.dex */
    public interface FileType {
        public static final int AUDIO = 2;
        public static final int BASE_AUDIO = 3;
        public static final int VIDEO = 1;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getPath() {
        return this.mPath;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public long getLength() {
        return this.mLength;
    }

    public void setLength(long j) {
        this.mLength = j;
    }

    public boolean isFinished() {
        return this.mIsFinished;
    }

    public void setFinished(boolean z) {
        this.mIsFinished = z;
    }

    public long getDownloadLength() {
        return this.mDownloadLength;
    }

    public void setDownloadLength(long j) {
        this.mDownloadLength = j;
    }
}
