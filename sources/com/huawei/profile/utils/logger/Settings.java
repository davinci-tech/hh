package com.huawei.profile.utils.logger;

/* loaded from: classes6.dex */
public final class Settings {
    private static final int DEFAULT_METHOD_COUNT = 2;
    private LogAdapter mLogAdapter;
    private LogAdapter secondLogAdapter;
    private int mMethodCount = 2;
    private boolean mIsShowThreadInfo = true;
    private boolean mIsShowMethodInfo = true;
    private boolean mIsShowLineNumber = true;
    private int mMethodOffset = 0;

    public Settings hideThreadInfo() {
        this.mIsShowThreadInfo = false;
        return this;
    }

    public Settings hideMethodInfo() {
        this.mIsShowMethodInfo = false;
        return this;
    }

    public Settings hideLineNumber() {
        this.mIsShowLineNumber = false;
        return this;
    }

    public Settings methodCount(int i) {
        this.mMethodCount = i;
        return this;
    }

    public Settings methodOffset(int i) {
        this.mMethodOffset = i;
        return this;
    }

    public Settings logAdapter(LogAdapter logAdapter) {
        this.mLogAdapter = logAdapter;
        return this;
    }

    public int getMethodCount() {
        return this.mMethodCount;
    }

    public boolean isShowThreadInfo() {
        return this.mIsShowThreadInfo;
    }

    public boolean isShowMethodInfo() {
        return this.mIsShowMethodInfo;
    }

    public boolean isShowLineNumber() {
        return this.mIsShowLineNumber;
    }

    public int getMethodOffset() {
        return this.mMethodOffset;
    }

    public LogAdapter getLogAdapter() {
        return this.mLogAdapter;
    }

    public LogAdapter getSecondLogAdapter() {
        return this.secondLogAdapter;
    }

    public void setSecondLogAdapter(LogAdapter logAdapter) {
        this.secondLogAdapter = logAdapter;
    }

    public void reset() {
        this.mMethodCount = 2;
        this.mMethodOffset = 0;
        this.mIsShowThreadInfo = true;
        this.mIsShowMethodInfo = true;
        this.mIsShowLineNumber = true;
    }
}
