package com.huawei.haf.mediaprocess;

import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class VideoProgress {

    /* renamed from: a, reason: collision with root package name */
    private VideoProgressListener f2137a;
    private int b;
    private Float c;
    private float d;
    private float e;
    private int g;

    public void a(long j) {
        if (this.f2137a == null) {
            return;
        }
        Float f = this.c;
        if (f != null) {
            j = (long) (j * f.floatValue());
        }
        int i = this.b - this.g;
        if (i != 0) {
            float f2 = (j / 1000.0f) / i;
            this.d = f2;
            float max = Math.max(f2, 0.0f);
            this.d = max;
            float min = Math.min(max, 1.0f);
            this.d = min;
            this.f2137a.onProgress((min + this.e) / 2.0f);
            LogUtil.c("VideoProgress", "mEncodeProgress:", Float.valueOf(this.d));
        }
    }

    public void e(float f) {
        this.e = f;
        VideoProgressListener videoProgressListener = this.f2137a;
        if (videoProgressListener != null) {
            videoProgressListener.onProgress((this.d + f) / 2.0f);
        }
        LogUtil.c("VideoProgress", "mAudioProgress:", Float.valueOf(this.e));
    }
}
