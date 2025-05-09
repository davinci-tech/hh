package com.huawei.haf.mediaprocess;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class VideoDecodeThread extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private DecodeOutputSurface f2135a;
    private int b;
    private MediaCodec c;
    private MediaCodec.BufferInfo d;
    private Integer e;
    private Integer f;
    private VideoProcessException g;
    private MediaExtractor h;
    private FrameDropHelper i;
    private EncodeInputSurface j;
    private AtomicBoolean k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private Integer p;
    private long q;
    private IVideoEncodeThread r;
    private Integer s;
    private int t;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        String str = "decode stop error";
        super.run();
        try {
            try {
                c();
            } finally {
                EncodeInputSurface encodeInputSurface = this.j;
                if (encodeInputSurface != null) {
                    encodeInputSurface.c();
                }
                DecodeOutputSurface decodeOutputSurface = this.f2135a;
                if (decodeOutputSurface != null) {
                    decodeOutputSurface.c();
                }
                try {
                    MediaCodec mediaCodec = this.c;
                    if (mediaCodec != null) {
                        mediaCodec.stop();
                        this.c.release();
                    }
                } catch (IllegalStateException e) {
                    VideoProcessException videoProcessException = this.g;
                    if (videoProcessException == null) {
                        videoProcessException = new VideoProcessException(e);
                    }
                    this.g = videoProcessException;
                    LogUtil.e("VideoDecodeThread", str);
                }
            }
        } catch (IOException e2) {
            this.g = new VideoProcessException(e2);
            LogUtil.e("VideoDecodeThread", "video decode error");
            if (this.j != null) {
                this.j.c();
            }
            DecodeOutputSurface decodeOutputSurface2 = this.f2135a;
            if (decodeOutputSurface2 != null) {
                decodeOutputSurface2.c();
            }
            try {
                MediaCodec mediaCodec2 = this.c;
                str = str;
                if (mediaCodec2 != null) {
                    mediaCodec2.stop();
                    this.c.release();
                    str = str;
                }
            } catch (IllegalStateException e3) {
                VideoProcessException videoProcessException2 = this.g;
                if (videoProcessException2 == null) {
                    videoProcessException2 = new VideoProcessException(e3);
                }
                this.g = videoProcessException2;
                Object[] objArr = {"decode stop error"};
                LogUtil.e("VideoDecodeThread", objArr);
                str = objArr;
            }
        }
    }

    private void c() throws IOException {
        if (f()) {
            a();
            b();
            boolean z = false;
            while (!this.m) {
                if (!z) {
                    int sampleTrackIndex = this.h.getSampleTrackIndex();
                    if (sampleTrackIndex == this.t) {
                        i();
                    } else if (sampleTrackIndex == -1) {
                        LogUtil.c("VideoDecodeThread", "inputDone");
                        int dequeueInputBuffer = this.c.dequeueInputBuffer(2500L);
                        if (dequeueInputBuffer >= 0) {
                            this.c.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
                            z = true;
                        }
                    }
                }
                if (this.m) {
                    LogUtil.c("VideoDecodeThread", "decoderOutputAvailable:false");
                } else {
                    e();
                }
            }
            LogUtil.c("VideoDecodeThread", "Video Decode Done!");
            this.k.set(true);
        }
    }

    private void e() throws IOException {
        while (true) {
            int dequeueOutputBuffer = this.c.dequeueOutputBuffer(this.d, 2500L);
            LogUtil.c("VideoDecodeThread", "outputBufferIndex = ", Integer.valueOf(dequeueOutputBuffer));
            boolean z = false;
            if (this.n && dequeueOutputBuffer == -1) {
                int i = this.b + 1;
                this.b = i;
                if (i > 10) {
                    LogUtil.e("VideoDecodeThread", "INFO_TRY_AGAIN_LATER 10 times,force End!");
                    this.m = true;
                    return;
                }
            } else {
                this.b = 0;
            }
            if (dequeueOutputBuffer == -1) {
                return;
            }
            if (dequeueOutputBuffer == -2) {
                LogUtil.c("VideoDecodeThread", "decode newFormat = ", this.c.getOutputFormat());
            } else if (dequeueOutputBuffer < 0) {
                LogUtil.e("VideoDecodeThread", "unexpected result from decoder.dequeueOutputBuffer: ", Integer.valueOf(dequeueOutputBuffer));
            } else {
                boolean d = d();
                if (this.d.flags == 4) {
                    this.m = true;
                    this.c.releaseOutputBuffer(dequeueOutputBuffer, false);
                    LogUtil.c("VideoDecodeThread", "decoderDone");
                    return;
                }
                FrameDropHelper frameDropHelper = this.i;
                if (frameDropHelper == null || !frameDropHelper.d(this.l)) {
                    z = d;
                } else {
                    LogUtil.a("VideoDecodeThread", "drop frame at:", Integer.valueOf(this.l));
                }
                this.l++;
                this.c.releaseOutputBuffer(dequeueOutputBuffer, z);
                if (z && h()) {
                    return;
                }
            }
        }
    }

    private boolean d() {
        boolean z = true;
        if (this.f != null && this.d.presentationTimeUs >= this.f.intValue() * 1000) {
            this.n = true;
            this.m = true;
            this.d.flags |= 4;
            z = false;
        }
        if (this.p == null || this.d.presentationTimeUs >= this.p.intValue() * 1000) {
            return z;
        }
        LogUtil.e("VideoDecodeThread", "drop frame startTime = ", this.p, " present time = ", Long.valueOf(this.d.presentationTimeUs / 1000));
        return false;
    }

    private boolean h() throws IOException {
        boolean z;
        try {
            this.f2135a.e();
            z = false;
        } catch (IOException e) {
            LogUtil.e("VideoDecodeThread", ExceptionUtils.d(e));
            z = true;
        }
        if (!z) {
            if (this.q == -1) {
                long j = this.d.presentationTimeUs;
                this.q = j;
                LogUtil.c("VideoDecodeThread", "videoStartTime:", Long.valueOf(j / 1000));
            }
            this.f2135a.b();
            long j2 = (this.d.presentationTimeUs - this.q) * 1000;
            LogUtil.c("VideoDecodeThread", "drawImage,setPresentationTimeMs:", Long.valueOf((j2 / 1000) / 1000));
            this.j.c(j2);
            this.j.e();
        }
        return !z;
    }

    private void i() {
        this.m = false;
        int dequeueInputBuffer = this.c.dequeueInputBuffer(2500L);
        if (dequeueInputBuffer >= 0) {
            int readSampleData = this.h.readSampleData(this.c.getInputBuffer(dequeueInputBuffer), 0);
            if (readSampleData < 0) {
                this.c.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
                this.m = true;
            } else {
                this.c.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, this.h.getSampleTime(), 0);
                this.h.advance();
            }
        }
    }

    private boolean f() {
        try {
            if (this.r.getEglContextLatch().await(5L, TimeUnit.SECONDS)) {
                return true;
            }
            this.g = new VideoProcessException("wait eglContext timeout!");
            return false;
        } catch (InterruptedException e) {
            LogUtil.e("VideoDecodeThread", "decode is interrupt");
            this.g = new VideoProcessException(e);
            return false;
        }
    }

    private void a() throws IOException {
        EncodeInputSurface encodeInputSurface = new EncodeInputSurface(this.r.getSurface());
        this.j = encodeInputSurface;
        encodeInputSurface.d();
        MediaFormat trackFormat = this.h.getTrackFormat(this.t);
        this.c = MediaCodec.createDecoderByType(trackFormat.getString("mime"));
        DecodeOutputSurface decodeOutputSurface = new DecodeOutputSurface();
        this.f2135a = decodeOutputSurface;
        this.c.configure(trackFormat, decodeOutputSurface.zc_(), (MediaCrypto) null, 0);
        this.c.start();
    }

    private void b() {
        Integer num;
        if (!this.o || (num = this.s) == null || this.e == null || num.intValue() <= this.e.intValue()) {
            return;
        }
        this.i = new FrameDropHelper(this.s.intValue(), this.e.intValue());
        LogUtil.a("VideoDecodeThread", "need to drop frame:", this.s + "->", this.e);
    }
}
