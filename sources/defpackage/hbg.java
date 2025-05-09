package defpackage;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.projection.MediaProjection;
import android.net.Uri;
import android.view.Surface;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.auxiliary.FrameControlCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class hbg extends TrackRecorder implements FrameControlCallBack {

    /* renamed from: a, reason: collision with root package name */
    private d f13063a;
    private int b;
    private long c;
    private boolean d;
    private int e;
    private int i;
    private AtomicBoolean j;

    public hbg(MediaProjection mediaProjection, String str) {
        super(mediaProjection, str);
        this.j = new AtomicBoolean(false);
        this.f13063a = new d();
        this.d = false;
        this.c = 0L;
        this.e = 0;
        this.i = -1;
        this.b = 0;
    }

    public hbg(MediaProjection mediaProjection, Uri uri) {
        super(mediaProjection, uri);
        this.j = new AtomicBoolean(false);
        this.f13063a = new d();
        this.d = false;
        this.c = 0L;
        this.e = 0;
        this.i = -1;
        this.b = 0;
    }

    public hbg d(boolean z) {
        this.d = z;
        return this;
    }

    public hbg a(long j) {
        this.c = j;
        return this;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder
    public void startEncode() {
        while (!this.j.get()) {
            encodeCurrentFrame(this);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.auxiliary.FrameControlCallBack
    public boolean isSaveCurrentFrame(long j) {
        int i = this.i + 1;
        this.i = i;
        if (i == 0) {
            this.b = 0;
            return true;
        }
        int i2 = this.e;
        if (i2 == 0) {
            this.b++;
            return true;
        }
        if (i % i2 == 0) {
            return false;
        }
        this.b++;
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.auxiliary.FrameControlCallBack
    public long getFrameTimeStamp() {
        return (this.b * 1000000) / 60;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder
    public void prepareEncoder() {
        try {
            this.e = this.f13063a.a();
            MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
            createVideoFormat.setInteger("color-format", 2130708361);
            createVideoFormat.setInteger("bitrate", 24000000);
            createVideoFormat.setInteger("frame-rate", 60);
            createVideoFormat.setInteger("i-frame-interval", 0);
            this.mEncoder = MediaCodec.createEncoderByType("video/avc");
            this.mEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            this.mVirtualSurface = this.mEncoder.createInputSurface();
            this.mEncoder.start();
        } catch (IOException unused) {
            this.mErrorManager.d("media codec create error");
            LogUtil.b("Track_CommonVideoRecorder", "media codec cannot be created");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder
    public void stopEncode() {
        this.j.set(true);
    }

    class d {
        private d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a() {
            if (!hbg.this.d || hbg.this.c <= 15000) {
                return 0;
            }
            return (int) (hbg.this.c / (hbg.this.c - 15000));
        }
    }
}
