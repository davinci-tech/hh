package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class hjg implements Serializable {
    private static final long serialVersionUID = 334431821164339725L;

    /* renamed from: a, reason: collision with root package name */
    private int f13185a;
    private float b;
    private hjd c;
    private int d;
    private float e;
    private int h;
    private int i;

    public hjg(hjd hjdVar, int i, int i2) {
        this.f13185a = -1;
        this.i = -1;
        this.e = -1.0f;
        this.b = -1.0f;
        this.c = hjdVar;
        this.d = i;
        this.h = i2;
    }

    public hjg(hjd hjdVar, int i, int i2, int i3, int i4) {
        this.e = -1.0f;
        this.b = -1.0f;
        this.c = hjdVar;
        this.f13185a = i;
        this.i = i2;
        this.d = i3;
        this.h = i4;
    }

    public Drawable bgH_() {
        Context e = BaseApplication.e();
        int i = this.d;
        if (i == -1) {
            ReleaseLogUtil.c("Track_HiHealthMarker", "mMarkerIconId is invalid");
            return null;
        }
        int i2 = this.i;
        if (i2 == -1) {
            return bgG_(ContextCompat.getDrawable(e, i), this.h);
        }
        Drawable bgG_ = bgG_(ContextCompat.getDrawable(e, i2), this.h);
        Drawable drawable = ContextCompat.getDrawable(e, this.d);
        int i3 = this.f13185a;
        return new LayerDrawable(i3 == -1 ? new Drawable[]{bgG_, drawable} : new Drawable[]{ContextCompat.getDrawable(e, i3), bgG_, drawable});
    }

    private Drawable bgG_(Drawable drawable, int i) {
        return (drawable == null || i == -1) ? drawable : nrf.cJH_(drawable, i);
    }

    public hjd b() {
        return this.c;
    }

    public float a() {
        return this.e;
    }

    public void a(float f) {
        this.e = f;
    }

    public float c() {
        return this.b;
    }

    public void e(float f) {
        this.b = f;
    }
}
