package com.huawei.haf.mediaprocess;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import androidx.core.location.LocationRequestCompat;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class VideoEncodeThread extends Thread implements IVideoEncodeThread {

    /* renamed from: a, reason: collision with root package name */
    private MediaCodec f2136a;
    private VideoProcessException b;
    private MediaMuxer c;
    private AtomicBoolean d;
    private volatile CountDownLatch e;
    private CountDownLatch h;
    private VideoProgress i;
    private volatile Surface j;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        String str = "release encode fail";
        super.run();
        try {
            try {
                a();
                try {
                    MediaCodec mediaCodec = this.f2136a;
                    str = str;
                    if (mediaCodec != null) {
                        mediaCodec.stop();
                        this.f2136a.release();
                        str = str;
                    }
                } catch (IllegalStateException e) {
                    VideoProcessException videoProcessException = this.b;
                    if (videoProcessException == null) {
                        videoProcessException = new VideoProcessException(e);
                    }
                    this.b = videoProcessException;
                    Object[] objArr = {"release encode fail"};
                    LogUtil.e("VideoEncodeThread", objArr);
                    str = objArr;
                }
            } catch (IOException e2) {
                LogUtil.e("VideoEncodeThread", "video encode error");
                this.b = new VideoProcessException(e2);
                try {
                    str = str;
                    if (this.f2136a != null) {
                        this.f2136a.stop();
                        this.f2136a.release();
                        str = str;
                    }
                } catch (IllegalStateException e3) {
                    VideoProcessException videoProcessException2 = this.b;
                    if (videoProcessException2 == null) {
                        videoProcessException2 = new VideoProcessException(e3);
                    }
                    this.b = videoProcessException2;
                    Object[] objArr2 = {"release encode fail"};
                    LogUtil.e("VideoEncodeThread", objArr2);
                    str = objArr2;
                }
            }
        } catch (Throwable th) {
            try {
                MediaCodec mediaCodec2 = this.f2136a;
                if (mediaCodec2 != null) {
                    mediaCodec2.stop();
                    this.f2136a.release();
                }
            } catch (IllegalStateException e4) {
                VideoProcessException videoProcessException3 = this.b;
                if (videoProcessException3 == null) {
                    videoProcessException3 = new VideoProcessException(e4);
                }
                this.b = videoProcessException3;
                LogUtil.e("VideoEncodeThread", str);
            }
            throw th;
        }
    }

    private void a() throws IOException {
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        boolean z = false;
        int i = 0;
        int i2 = -5;
        while (true) {
            if (this.d.get() && !z) {
                this.f2136a.signalEndOfInputStream();
                z = true;
            }
            int dequeueOutputBuffer = this.f2136a.dequeueOutputBuffer(bufferInfo, 2500L);
            LogUtil.c("VideoEncodeThread", "encode outputBufferIndex = ", Integer.valueOf(dequeueOutputBuffer));
            if (z && dequeueOutputBuffer == -1) {
                i++;
                if (i > 10) {
                    LogUtil.e("VideoEncodeThread", "INFO_TRY_AGAIN_LATER 10 times,force End!");
                    break;
                }
            } else {
                i = 0;
            }
            if (dequeueOutputBuffer != -1) {
                if (dequeueOutputBuffer == -2) {
                    MediaFormat outputFormat = this.f2136a.getOutputFormat();
                    if (i2 == -5) {
                        i2 = this.c.addTrack(outputFormat);
                        c();
                    }
                    LogUtil.c("VideoEncodeThread", "encode newFormat = ", outputFormat);
                } else if (dequeueOutputBuffer < 0) {
                    LogUtil.e("VideoEncodeThread", "unexpected result from decoder.dequeueOutputBuffer: ", Integer.valueOf(dequeueOutputBuffer));
                } else {
                    if (bufferInfo.flags == 4 && bufferInfo.presentationTimeUs < 0) {
                        bufferInfo.presentationTimeUs = 0L;
                    }
                    LogUtil.c("VideoEncodeThread", "writeSampleData,size:", Integer.valueOf(bufferInfo.size), " time:", Long.valueOf(bufferInfo.presentationTimeUs / 1000));
                    this.c.writeSampleData(i2, this.f2136a.getOutputBuffer(dequeueOutputBuffer), bufferInfo);
                    zf_(bufferInfo);
                    this.f2136a.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if (bufferInfo.flags == 4) {
                        LogUtil.c("VideoEncodeThread", "encoderDone");
                        break;
                    }
                }
            }
        }
        LogUtil.c("VideoEncodeThread", "Video Encode Done!");
    }

    private void c() {
        this.c.start();
        this.h.countDown();
    }

    private void zf_(MediaCodec.BufferInfo bufferInfo) {
        VideoProgress videoProgress = this.i;
        if (videoProgress == null) {
            return;
        }
        videoProgress.a((bufferInfo.flags & 4) != 0 ? LocationRequestCompat.PASSIVE_INTERVAL : bufferInfo.presentationTimeUs);
    }

    @Override // com.huawei.haf.mediaprocess.IVideoEncodeThread
    public Surface getSurface() {
        return this.j;
    }

    @Override // com.huawei.haf.mediaprocess.IVideoEncodeThread
    public CountDownLatch getEglContextLatch() {
        return this.e;
    }
}
