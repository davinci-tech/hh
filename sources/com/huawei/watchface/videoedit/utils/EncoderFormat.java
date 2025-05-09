package com.huawei.watchface.videoedit.utils;

import com.huawei.watchface.utils.HwLog;

/* loaded from: classes9.dex */
public class EncoderFormat {
    private static final int BITRATE = 2000000;
    private static final int FRAMERATE = 30;
    private static final int HEIRHT = 720;
    private static final int WIDTH = 1080;
    private int mBitRate = BITRATE;
    private int mFrameRate = 30;
    private int mFrameInterval = 1;
    private int mWidth = 1080;
    private int mHeight = 720;

    public static int getVideoEncBitrate(int i, int i2) {
        int min = Math.min(i, i2);
        if (min <= 270) {
            return 1000000;
        }
        if (min <= 540) {
            return 1200000;
        }
        return BITRATE;
    }

    public static long getMaxDurationForSize(int i) {
        long j = i <= 270 ? 120L : i <= 540 ? 90L : i <= 900 ? 45L : i <= 1200 ? 30L : i <= 1500 ? 15L : i <= 1800 ? 12L : 10L;
        HwLog.d(HwLog.TAG, "shortSize = " + i + " minutes = " + j);
        return j;
    }

    public int getmBitRate() {
        return this.mBitRate;
    }

    public void setmBitRate(int i) {
        this.mBitRate = i;
    }

    public int getmFrameRate() {
        return this.mFrameRate;
    }

    public void setmFrameRate(int i) {
        this.mFrameRate = i;
    }

    public int getmFrameInterval() {
        return this.mFrameInterval;
    }

    public void setmFrameInterval(int i) {
        this.mFrameInterval = i;
    }

    public int getmWidth() {
        return this.mWidth;
    }

    public void setmWidth(int i) {
        this.mWidth = i;
    }

    public int getmHeight() {
        return this.mHeight;
    }

    public void setmHeight(int i) {
        this.mHeight = i;
    }
}
