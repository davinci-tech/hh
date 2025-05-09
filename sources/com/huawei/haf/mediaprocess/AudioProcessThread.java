package com.huawei.haf.mediaprocess;

import android.media.MediaExtractor;
import android.media.MediaMuxer;
import com.huawei.haf.mediaprocess.HealthVideoProcessor;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class AudioProcessThread extends Thread implements VideoProgressListener {

    /* renamed from: a, reason: collision with root package name */
    private MediaMuxer f2128a;
    private VideoProcessException b;
    private MediaExtractor c;
    private HealthVideoProcessor.MediaSource d;
    private int e;
    private VideoProgress f;
    private CountDownLatch j;

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        try {
            try {
                e();
            } finally {
                this.c.release();
            }
        } catch (IOException | InterruptedException | TimeoutException e) {
            this.b = new VideoProcessException(e);
        }
    }

    private void e() throws IOException, TimeoutException, InterruptedException {
        this.d.zd_(this.c);
        if (VideoUtil.zh_(this.c, true) >= 0) {
            if (!this.j.await(3L, TimeUnit.SECONDS)) {
                throw new TimeoutException("wait muxerStartLatch timeout!");
            }
            AudioUtil.zb_(this.c, this.f2128a, this.e, this);
        }
        VideoProgress videoProgress = this.f;
        if (videoProgress != null) {
            videoProgress.e(1.0f);
        }
        LogUtil.c("AudioProcessThread", "Audio Process Done!");
    }

    @Override // com.huawei.haf.mediaprocess.VideoProgressListener
    public void onProgress(float f) {
        VideoProgress videoProgress = this.f;
        if (videoProgress != null) {
            videoProgress.e(f);
        }
    }
}
