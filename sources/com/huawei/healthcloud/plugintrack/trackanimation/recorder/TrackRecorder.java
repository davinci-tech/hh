package com.huawei.healthcloud.plugintrack.trackanimation.recorder;

import android.content.res.AssetFileDescriptor;
import android.hardware.display.VirtualDisplay;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaMuxer;
import android.media.projection.MediaProjection;
import android.net.Uri;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.view.Surface;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.auxiliary.FrameControlCallBack;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hag;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public abstract class TrackRecorder extends Thread {
    protected static final int DEFAULT_VIDEO_BITRATE = 24000000;
    protected static final int DEFAULT_VIDEO_DPI = 1;
    private static final int DEFAULT_VIDEO_HEIGHT = 1280;
    private static final int DEFAULT_VIDEO_WIDTH = 720;
    private static final int ERROR_KEY = -1;
    protected static final int FRAME_RATE = 60;
    private static final int HD_VIDEO_WIDTH = 1080;
    protected static final int IFRAME_INTERVAL = 1;
    protected static final String MIME_TYPE = "video/avc";
    public static final int MSG_RECORD_FINISHED = 15790320;
    private static final String TAG = "Track_TrackRecorder";
    private static final int TIMEOUT_US = 10000;
    private static final int WAIT_TIME_MS = 10;
    private MediaCodec.BufferInfo mBufferInfo;
    protected MediaCodec mEncoder;
    protected e mErrorManager;
    protected int mHeight;
    private boolean mIsMuxerStarted;
    private MediaProjection mMediaProjection;
    private a mMessageManager;
    private b mMixAudio;
    private Handler mMsgHandler;
    private MediaMuxer mMuxer;
    private c mMuxerFileManager;
    private int mVideoTrackIndex;
    private VirtualDisplay mVirtualDisplay;
    protected Surface mVirtualSurface;
    protected int mWidth;

    protected abstract void prepareEncoder();

    protected abstract void startEncode();

    protected abstract void stopEncode();

    public TrackRecorder(MediaProjection mediaProjection, String str) {
        super(TAG);
        this.mWidth = 720;
        this.mHeight = 1280;
        this.mVirtualSurface = null;
        this.mEncoder = null;
        this.mErrorManager = new e();
        this.mMuxer = null;
        this.mBufferInfo = new MediaCodec.BufferInfo();
        this.mVirtualDisplay = null;
        this.mIsMuxerStarted = false;
        this.mVideoTrackIndex = -1;
        this.mMixAudio = new b();
        this.mMuxerFileManager = new c();
        this.mMessageManager = new a();
        this.mMsgHandler = null;
        this.mMediaProjection = mediaProjection;
        this.mMuxerFileManager.d(str);
    }

    public TrackRecorder(MediaProjection mediaProjection, Uri uri) {
        super(TAG);
        this.mWidth = 720;
        this.mHeight = 1280;
        this.mVirtualSurface = null;
        this.mEncoder = null;
        this.mErrorManager = new e();
        this.mMuxer = null;
        this.mBufferInfo = new MediaCodec.BufferInfo();
        this.mVirtualDisplay = null;
        this.mIsMuxerStarted = false;
        this.mVideoTrackIndex = -1;
        this.mMixAudio = new b();
        this.mMuxerFileManager = new c();
        this.mMessageManager = new a();
        this.mMsgHandler = null;
        this.mMediaProjection = mediaProjection;
        this.mMuxerFileManager.aYC_(uri);
    }

    public TrackRecorder reviseVideoResolution(boolean z, boolean z2) {
        if (z2) {
            setDisplayVideo(16.0f, 16, 1080);
        } else {
            setDisplayVideo(16.0f, 16, 720);
        }
        if (z) {
            this.mHeight *= 2;
            this.mWidth *= 2;
        }
        return this;
    }

    private void setDisplayVideo(float f, int i, int i2) {
        int d = (hag.d() * i2) / hag.a();
        this.mHeight = d;
        this.mHeight = Math.round(d / f) * i;
        this.mWidth = i2;
    }

    public long getVideoDuration() {
        return this.mMixAudio.c();
    }

    public TrackRecorder setAudioFileDescriptor(AssetFileDescriptor assetFileDescriptor) {
        this.mMixAudio.aYB_(assetFileDescriptor);
        return this;
    }

    public TrackRecorder setAudioFilePath(String str) {
        this.mMixAudio.b(str);
        return this;
    }

    public TrackRecorder setHandler(Handler handler) {
        this.mMsgHandler = handler;
        return this;
    }

    public boolean isRunSuccess() {
        return this.mErrorManager.e();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.mMessageManager.b();
        prepareEncoder();
        this.mMuxerFileManager.a();
        if (!this.mErrorManager.e()) {
            release();
            this.mMessageManager.d();
            return;
        }
        if (this.mVirtualSurface == null) {
            release();
            this.mMessageManager.d();
            return;
        }
        try {
            this.mMediaProjection.registerCallback(new MediaProjection.Callback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder.4
                @Override // android.media.projection.MediaProjection.Callback
                public void onStop() {
                    super.onStop();
                    ReleaseLogUtil.d(TrackRecorder.TAG, "mMediaProjection onStop");
                }
            }, this.mMsgHandler);
            this.mVirtualDisplay = this.mMediaProjection.createVirtualDisplay("Track_TrackRecorder-display", this.mWidth, this.mHeight, 1, 1, this.mVirtualSurface, null, null);
        } catch (SecurityException e2) {
            ReleaseLogUtil.d(TAG, "mediaProjection create error : ", LogAnonymous.b((Throwable) e2));
        }
        if (this.mVirtualDisplay == null) {
            release();
            this.mMessageManager.d();
            return;
        }
        try {
            startEncode();
            this.mMixAudio.d();
        } finally {
            release();
            this.mMessageManager.d();
            this.mMessageManager.c();
        }
    }

    public void stopScreen() {
        this.mMessageManager.c();
        stopEncode();
    }

    public void stopScreenOnActive() {
        this.mErrorManager.d("stop success");
        this.mMessageManager.c();
        stopEncode();
    }

    protected void encodeCurrentFrame(FrameControlCallBack frameControlCallBack) {
        int i;
        try {
            i = this.mEncoder.dequeueOutputBuffer(this.mBufferInfo, PreConnectManager.CONNECT_INTERNAL);
        } catch (IllegalStateException e2) {
            LogUtil.h(TAG, "encodeCurrentFrame:c ", LogAnonymous.b((Throwable) e2));
            i = -3;
        }
        ReleaseLogUtil.e(TAG, "encodeCurrentFrame:c index = ", Integer.valueOf(i));
        if (i == -2) {
            resetOutputFormat();
            return;
        }
        if (i == -1) {
            LogUtil.h(TAG, "retrieving buffers time out! c");
            try {
                Thread.sleep(10L);
                return;
            } catch (InterruptedException unused) {
                LogUtil.h(TAG, "thread sleep is error c");
                return;
            }
        }
        if (i >= 0) {
            if (!this.mIsMuxerStarted) {
                this.mErrorManager.d("media start mixur error");
                stopEncode();
                return;
            } else {
                addVideoTrack(i, frameControlCallBack);
                this.mEncoder.releaseOutputBuffer(i, false);
                return;
            }
        }
        LogUtil.b(TAG, "error in encodeCurrentFrame c");
    }

    public void encodeCurrentFrame() {
        int i;
        try {
            i = this.mEncoder.dequeueOutputBuffer(this.mBufferInfo, PreConnectManager.CONNECT_INTERNAL);
        } catch (IllegalStateException e2) {
            LogUtil.h(TAG, "encodeCurrentFrame: ", LogAnonymous.b((Throwable) e2));
            i = -3;
        }
        if (i == -2) {
            ReleaseLogUtil.e(TAG, "encodeCurrentFrame: index = ", -2);
            resetOutputFormat();
            return;
        }
        if (i == -1) {
            ReleaseLogUtil.e(TAG, "encodeCurrentFrame: index = ", -1);
            LogUtil.h(TAG, "retrieving buffers time out!");
            try {
                Thread.sleep(10L);
                return;
            } catch (InterruptedException unused) {
                LogUtil.h(TAG, "thread sleep is error");
                return;
            }
        }
        if (i >= 0) {
            if (!this.mIsMuxerStarted) {
                this.mErrorManager.d("media start mixur error");
                stopEncode();
                return;
            } else {
                addVideoTrack(i);
                this.mEncoder.releaseOutputBuffer(i, false);
                return;
            }
        }
        LogUtil.b(TAG, "error in encodeCurrentFrame");
    }

    private void release() {
        MediaProjection mediaProjection;
        try {
            try {
                MediaCodec mediaCodec = this.mEncoder;
                if (mediaCodec != null) {
                    mediaCodec.stop();
                    this.mEncoder.release();
                    this.mEncoder = null;
                }
                VirtualDisplay virtualDisplay = this.mVirtualDisplay;
                if (virtualDisplay != null) {
                    virtualDisplay.release();
                    this.mVirtualDisplay = null;
                }
                MediaMuxer mediaMuxer = this.mMuxer;
                if (mediaMuxer != null) {
                    mediaMuxer.stop();
                    this.mMuxer.release();
                    this.mMuxer = null;
                }
                this.mMixAudio.b();
                MediaProjection mediaProjection2 = this.mMediaProjection;
                if (mediaProjection2 != null) {
                    mediaProjection2.stop();
                    this.mMediaProjection = null;
                }
                mediaProjection = this.mMediaProjection;
                if (mediaProjection == null) {
                    return;
                }
            } catch (IllegalStateException e2) {
                LogUtil.b(TAG, e2.getMessage());
                mediaProjection = this.mMediaProjection;
                if (mediaProjection == null) {
                    return;
                }
            }
            mediaProjection.stop();
            this.mMediaProjection = null;
        } catch (Throwable th) {
            MediaProjection mediaProjection3 = this.mMediaProjection;
            if (mediaProjection3 != null) {
                mediaProjection3.stop();
                this.mMediaProjection = null;
            }
            throw th;
        }
    }

    private void addVideoTrack(int i, FrameControlCallBack frameControlCallBack) {
        ByteBuffer outputBuffer = this.mEncoder.getOutputBuffer(i);
        if ((this.mBufferInfo.flags & 2) != 0) {
            LogUtil.a(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG");
            this.mBufferInfo.size = 0;
        }
        if (this.mBufferInfo.size == 0) {
            outputBuffer = null;
        }
        if (outputBuffer != null) {
            LogUtil.a(TAG, "before presentationTimeUs: " + this.mBufferInfo.presentationTimeUs);
            if (frameControlCallBack.isSaveCurrentFrame(this.mBufferInfo.presentationTimeUs)) {
                outputBuffer.position(this.mBufferInfo.offset);
                outputBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                this.mBufferInfo.presentationTimeUs = frameControlCallBack.getFrameTimeStamp();
                LogUtil.a(TAG, "after presentationTimeUs: " + this.mBufferInfo.presentationTimeUs);
                this.mMixAudio.a(this.mBufferInfo.presentationTimeUs);
                this.mMuxer.writeSampleData(this.mVideoTrackIndex, outputBuffer, this.mBufferInfo);
            }
        }
    }

    private void addVideoTrack(int i) {
        ByteBuffer outputBuffer = this.mEncoder.getOutputBuffer(i);
        if ((this.mBufferInfo.flags & 2) != 0) {
            LogUtil.a(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG");
            this.mBufferInfo.size = 0;
        }
        if (this.mBufferInfo.size == 0) {
            outputBuffer = null;
        }
        if (outputBuffer != null) {
            outputBuffer.position(this.mBufferInfo.offset);
            outputBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
            this.mMixAudio.a(this.mBufferInfo.presentationTimeUs);
            try {
                this.mMuxer.writeSampleData(this.mVideoTrackIndex, outputBuffer, this.mBufferInfo);
            } catch (IllegalArgumentException e2) {
                ReleaseLogUtil.d(TAG, "addVideoTrack: IllegalArgumentException ", LogAnonymous.b((Throwable) e2));
            } catch (IllegalStateException e3) {
                ReleaseLogUtil.d(TAG, "addVideoTrack: IllegalStateException ", LogAnonymous.b((Throwable) e3));
            }
        }
    }

    private void resetOutputFormat() {
        if (this.mIsMuxerStarted) {
            stopEncode();
            this.mErrorManager.d("media add video error");
            return;
        }
        this.mVideoTrackIndex = this.mMuxer.addTrack(this.mEncoder.getOutputFormat());
        this.mMixAudio.e();
        ReleaseLogUtil.e(TAG, "resetOutputFormat: video index is ", Integer.valueOf(this.mVideoTrackIndex), " audio index is ", Integer.valueOf(this.mMixAudio.h));
        if (this.mVideoTrackIndex == -1) {
            stopEncode();
            this.mErrorManager.d("media add video error");
            LogUtil.a(TAG, "resetOutputFormat: ");
            return;
        }
        try {
            this.mMuxer.start();
            this.mIsMuxerStarted = true;
        } catch (IllegalStateException e2) {
            ReleaseLogUtil.d(TAG, "resetOutputFormat: ", LogAnonymous.b((Throwable) e2));
            stopEncode();
            this.mErrorManager.d("Muxer start error");
        }
    }

    class a {
        private boolean e;

        private a() {
            this.e = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.e = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            this.e = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            if (TrackRecorder.this.mMsgHandler == null || !this.e) {
                return;
            }
            TrackRecorder.this.mMsgHandler.sendEmptyMessage(TrackRecorder.MSG_RECORD_FINISHED);
            this.e = false;
        }
    }

    class c {

        /* renamed from: a, reason: collision with root package name */
        private Uri f3591a;
        private boolean b;
        private String c;
        private FileDescriptor e;

        private c() {
            this.b = false;
        }

        public void d(String str) {
            this.c = str;
            this.e = null;
            this.b = false;
            this.f3591a = null;
        }

        public void aYC_(Uri uri) {
            this.f3591a = uri;
            this.e = null;
            this.c = null;
            this.b = true;
        }

        public void a() {
            try {
                if (!this.b) {
                    if (this.c != null) {
                        TrackRecorder.this.mMuxer = new MediaMuxer(this.c, 0);
                        return;
                    } else {
                        TrackRecorder.this.mErrorManager.d("out file is null");
                        LogUtil.b(TrackRecorder.TAG, "dst path is null");
                        return;
                    }
                }
                if (this.f3591a == null) {
                    TrackRecorder.this.mErrorManager.d("out file is null");
                    LogUtil.b(TrackRecorder.TAG, "dst file Uri is null");
                    return;
                }
                FileDescriptor b = b();
                this.e = b;
                if (b != null) {
                    TrackRecorder.this.mMuxer = new MediaMuxer(this.e, 0);
                } else {
                    TrackRecorder.this.mErrorManager.d("out file is null");
                    LogUtil.b(TrackRecorder.TAG, "dst file descriptor is null");
                }
            } catch (IOException e) {
                LogUtil.b(TrackRecorder.TAG, "mp4 file write error", " exception = ", LogAnonymous.b((Throwable) e));
                TrackRecorder.this.mErrorManager.d("mp4 file write error");
            } catch (IllegalArgumentException e2) {
                LogUtil.b(TrackRecorder.TAG, "mp4 file read error", " exception = ", LogAnonymous.b((Throwable) e2));
                TrackRecorder.this.mErrorManager.d("mp4 file read error");
            }
        }

        private FileDescriptor b() {
            ParcelFileDescriptor parcelFileDescriptor;
            if (this.f3591a == null) {
                LogUtil.h(TrackRecorder.TAG, "acquireFileDescriptor: mVideoUri is null");
                return null;
            }
            try {
                parcelFileDescriptor = BaseApplication.getContext().getContentResolver().openFileDescriptor(this.f3591a, "rw");
            } catch (FileNotFoundException e) {
                ReleaseLogUtil.d(TrackRecorder.TAG, "acquireFileDescriptor : Failed to open file", LogAnonymous.b((Throwable) e));
                parcelFileDescriptor = null;
            }
            if (parcelFileDescriptor == null) {
                LogUtil.h(TrackRecorder.TAG, "pdf is null");
                return null;
            }
            if (!parcelFileDescriptor.getFileDescriptor().valid()) {
                ReleaseLogUtil.d(TrackRecorder.TAG, "acquireFileDescriptor: fileDescriptor is invalid");
                return null;
            }
            return parcelFileDescriptor.getFileDescriptor();
        }
    }

    class b {

        /* renamed from: a, reason: collision with root package name */
        private long f3590a;
        private MediaCodec.BufferInfo b;
        private long c;
        private ByteBuffer d;
        private AssetFileDescriptor f;
        private MediaExtractor g;
        private int h;
        private long i;
        private boolean j;
        private long k;
        private long m;

        private b() {
            this.h = -1;
            this.g = null;
            this.b = new MediaCodec.BufferInfo();
            this.d = ByteBuffer.allocate(2097152);
            this.j = false;
            this.m = -1L;
            this.k = -1L;
            this.i = -1L;
            this.f3590a = -1L;
            this.c = -1L;
            this.f = null;
        }

        public void b(String str) {
            if (TextUtils.isEmpty(str)) {
                this.j = false;
                return;
            }
            this.j = true;
            try {
                try {
                    MediaExtractor mediaExtractor = new MediaExtractor();
                    this.g = mediaExtractor;
                    mediaExtractor.setDataSource(str);
                    this.g.selectTrack(0);
                    if (TrackRecorder.this.mErrorManager.e()) {
                        return;
                    }
                } catch (IOException unused) {
                    LogUtil.b(TrackRecorder.TAG, "audio file error");
                    TrackRecorder.this.mErrorManager.d("audio file error");
                    if (TrackRecorder.this.mErrorManager.e()) {
                        return;
                    }
                }
                this.j = false;
            } catch (Throwable th) {
                if (!TrackRecorder.this.mErrorManager.e()) {
                    this.j = false;
                }
                throw th;
            }
        }

        public void aYB_(AssetFileDescriptor assetFileDescriptor) {
            this.f = assetFileDescriptor;
            if (assetFileDescriptor == null) {
                this.j = false;
                return;
            }
            this.j = true;
            try {
                try {
                    MediaExtractor mediaExtractor = new MediaExtractor();
                    this.g = mediaExtractor;
                    mediaExtractor.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    this.g.selectTrack(0);
                    if (TrackRecorder.this.mErrorManager.e()) {
                        return;
                    }
                } catch (IOException unused) {
                    LogUtil.b(TrackRecorder.TAG, "audio file error");
                    TrackRecorder.this.mErrorManager.d("audio file error");
                    if (TrackRecorder.this.mErrorManager.e()) {
                        return;
                    }
                }
                this.j = false;
            } catch (Throwable th) {
                if (!TrackRecorder.this.mErrorManager.e()) {
                    this.j = false;
                }
                throw th;
            }
        }

        public long c() {
            return (this.k - this.m) / 1000;
        }

        public void e() {
            if (this.j) {
                this.h = TrackRecorder.this.mMuxer.addTrack(this.g.getTrackFormat(0));
            }
        }

        private void a() {
            this.i = this.g.getSampleTime();
            this.f3590a = -1L;
            long j = 0;
            while (this.g.advance()) {
                if (this.g.getSampleTime() > this.f3590a) {
                    this.f3590a = this.g.getSampleTime();
                    j++;
                }
            }
            long j2 = this.f3590a;
            long j3 = this.i;
            this.c = (j2 - j3) / (j - 1);
            this.g.seekTo(j3, 0);
        }

        public void d() {
            if (this.j && TrackRecorder.this.mErrorManager.e()) {
                a();
                long j = this.m;
                long j2 = 0;
                while (j <= this.k) {
                    int readSampleData = this.g.readSampleData(this.d, 0);
                    this.b.offset = 0;
                    this.b.size = readSampleData;
                    this.b.flags = this.g.getSampleFlags();
                    this.b.presentationTimeUs = j;
                    try {
                        TrackRecorder.this.mMuxer.writeSampleData(this.h, this.d, this.b);
                    } catch (IllegalArgumentException e) {
                        TrackRecorder.this.mErrorManager.d("audio stream error");
                        LogUtil.b(TrackRecorder.TAG, e.getMessage());
                    } catch (IllegalStateException e2) {
                        TrackRecorder.this.mErrorManager.d("audio stream error");
                        LogUtil.b(TrackRecorder.TAG, e2.getMessage());
                    }
                    if (!this.g.advance() || this.g.getSampleTime() < 0) {
                        this.g.seekTo(this.i, 2);
                        j2 += (this.f3590a - this.i) + this.c;
                    }
                    j = (this.g.getSampleTime() - this.i) + j2 + this.m;
                }
            }
        }

        public void a(long j) {
            if (this.m == -1) {
                this.m = j;
            } else {
                this.k = j;
            }
        }

        public void b() {
            MediaExtractor mediaExtractor = this.g;
            if (mediaExtractor != null) {
                mediaExtractor.release();
                this.g = null;
            }
            AssetFileDescriptor assetFileDescriptor = this.f;
            if (assetFileDescriptor != null) {
                try {
                    try {
                        assetFileDescriptor.getParcelFileDescriptor().close();
                    } catch (IOException unused) {
                        LogUtil.b(TrackRecorder.TAG, "mAudioFileDescriptor close error");
                    }
                } finally {
                    this.f = null;
                }
            }
        }
    }

    public static class e {
        private String c = "record success";

        public void d(String str) {
            this.c = str;
        }

        public boolean e() {
            return "record success".equals(this.c);
        }
    }
}
