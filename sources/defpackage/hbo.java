package defpackage;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.projection.MediaProjection;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.glec.RecorderEglRender;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.io.IOException;

/* loaded from: classes4.dex */
public class hbo extends TrackRecorder {

    /* renamed from: a, reason: collision with root package name */
    private long f13066a;
    private RecorderEglRender b;
    private Surface c;
    private a d;
    private boolean e;

    public hbo(MediaProjection mediaProjection, String str, long j) {
        super(mediaProjection, str);
        this.d = new a();
        this.b = null;
        this.e = true;
        this.f13066a = j;
    }

    public hbo(MediaProjection mediaProjection, Uri uri, long j) {
        super(mediaProjection, uri);
        this.d = new a();
        this.b = null;
        this.e = true;
        this.f13066a = j;
    }

    public hbo d(boolean z) {
        this.e = z;
        return this;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder
    public void startEncode() {
        RecorderEglRender recorderEglRender = this.b;
        if (recorderEglRender != null) {
            try {
                recorderEglRender.a();
            } catch (hbm unused) {
                this.mErrorManager.d("EGL runtime error");
                LogUtil.b("Track_SpecialVideoRecorder", "egl render error");
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder
    public void prepareEncoder() {
        try {
            MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
            createVideoFormat.setInteger("color-format", 2130708361);
            createVideoFormat.setInteger("bitrate", 24000000);
            createVideoFormat.setInteger("frame-rate", 60);
            createVideoFormat.setInteger("i-frame-interval", 1);
            this.mEncoder = MediaCodec.createEncoderByType("video/avc");
            this.mEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            this.c = this.mEncoder.createInputSurface();
            RecorderEglRender recorderEglRender = new RecorderEglRender(this.mWidth, this.mHeight, 60, this.c, this.d.b());
            this.b = recorderEglRender;
            recorderEglRender.c(new RecorderEglRender.OnFrameCallBack() { // from class: hbo.4
                @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.glec.RecorderEglRender.OnFrameCallBack
                public void onUpdate() {
                    hbo.this.encodeCurrentFrame();
                }
            });
            this.mVirtualSurface = this.b.aYH_();
            this.mEncoder.start();
        } catch (hbm unused) {
            this.mErrorManager.d("EGL create error");
            LogUtil.b("Track_SpecialVideoRecorder", "prepareEncoder:egl render cannot be created mWidth =", Integer.valueOf(this.mWidth), " mHeight = ", Integer.valueOf(this.mHeight));
        } catch (IOException unused2) {
            this.mErrorManager.d("media codec create error");
            LogUtil.b("Track_SpecialVideoRecorder", "media codec cannot be created");
        } catch (IllegalArgumentException e) {
            e = e;
            this.mErrorManager.d("configure_create_error");
            LogUtil.b("Track_SpecialVideoRecorder", "prepareEncoder:  mWidth =", Integer.valueOf(this.mWidth), " mHeight = ", Integer.valueOf(this.mHeight), LogAnonymous.b(e));
            a();
        } catch (IllegalStateException e2) {
            e = e2;
            this.mErrorManager.d("configure_create_error");
            LogUtil.b("Track_SpecialVideoRecorder", "prepareEncoder:  mWidth =", Integer.valueOf(this.mWidth), " mHeight = ", Integer.valueOf(this.mHeight), LogAnonymous.b(e));
            a();
        }
    }

    private void a() {
        MediaCodecList mediaCodecList = new MediaCodecList(1);
        StringBuilder sb = new StringBuilder();
        for (MediaCodecInfo mediaCodecInfo : mediaCodecList.getCodecInfos()) {
            if (mediaCodecInfo.isEncoder()) {
                aYx_(sb, mediaCodecInfo);
            }
        }
        LogUtil.b("Track_SpecialVideoRecorder", "getSupportTypes: ", sb.toString());
    }

    private void aYx_(StringBuilder sb, MediaCodecInfo mediaCodecInfo) {
        for (String str : mediaCodecInfo.getSupportedTypes()) {
            if (TextUtils.equals("video/avc", str)) {
                sb.append(" -media Name : ");
                sb.append(mediaCodecInfo.getName());
                sb.append(" type ");
                sb.append(str);
                sb.append(" ");
                for (int i : mediaCodecInfo.getCapabilitiesForType("video/avc").colorFormats) {
                    sb.append("colorFormat : ");
                    sb.append(i);
                    sb.append(System.lineSeparator());
                    sb.append(" |");
                }
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder
    public void stopEncode() {
        RecorderEglRender recorderEglRender = this.b;
        if (recorderEglRender != null) {
            recorderEglRender.b();
        }
    }

    class a {
        private a() {
        }

        public long b() {
            if (!hbo.this.e || hbo.this.f13066a < 15000) {
                return 17L;
            }
            return (hbo.this.f13066a / 900) + 1;
        }
    }
}
