package defpackage;

import android.media.projection.MediaProjection;
import android.net.Uri;
import com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecorder;

/* loaded from: classes4.dex */
public class hbn {
    private boolean d = false;
    private long e = 0;
    private boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f13065a = true;

    public hbn c(long j) {
        this.e = j;
        return this;
    }

    public hbn a(boolean z) {
        this.d = z;
        return this;
    }

    public TrackRecorder aYw_(MediaProjection mediaProjection, String str, boolean z, boolean z2) {
        TrackRecorder d;
        if (this.b) {
            d = new hbg(mediaProjection, str).a(this.e).d(this.d);
        } else {
            d = new hbo(mediaProjection, str, this.e).d(this.d);
        }
        return this.f13065a ? d.reviseVideoResolution(z, z2) : d;
    }

    public TrackRecorder aYv_(MediaProjection mediaProjection, Uri uri, boolean z, boolean z2) {
        TrackRecorder d;
        if (this.b) {
            d = new hbg(mediaProjection, uri).a(this.e).d(this.d);
        } else {
            d = new hbo(mediaProjection, uri, this.e).d(this.d);
        }
        return this.f13065a ? d.reviseVideoResolution(z, z2) : d;
    }
}
