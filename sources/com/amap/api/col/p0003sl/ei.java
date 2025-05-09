package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.google.android.gms.common.util.GmsVersion;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hms.framework.common.ExceptionCode;

/* loaded from: classes2.dex */
public final class ei extends View {

    /* renamed from: a, reason: collision with root package name */
    private String f1006a;
    private int b;
    private IAMapDelegate c;
    private Paint d;
    private Paint e;
    private Rect f;
    private IPoint g;
    private float h;
    private final int[] i;

    public final void a() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.f1006a = null;
        this.g = null;
    }

    public ei(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f1006a = "";
        this.b = 0;
        this.h = 0.0f;
        this.i = new int[]{ExceptionCode.CRASH_EXCEPTION, GmsVersion.VERSION_LONGHORN, 2000000, 1000000, 500000, AwarenessConstants.REGISTER_SUCCESS_CODE, 100000, 50000, 30000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5};
        this.c = iAMapDelegate;
        this.d = new Paint();
        this.f = new Rect();
        this.d.setAntiAlias(true);
        this.d.setColor(-16777216);
        this.d.setStrokeWidth(v.f1366a * 2.0f);
        this.d.setStyle(Paint.Style.STROKE);
        Paint paint = new Paint();
        this.e = paint;
        paint.setAntiAlias(true);
        this.e.setColor(-16777216);
        this.e.setTextSize(v.f1366a * 20.0f);
        this.h = dp.b(context);
        this.g = new IPoint();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        Point waterMarkerPositon;
        String str = this.f1006a;
        if (str == null || "".equals(str) || this.b == 0 || (waterMarkerPositon = this.c.getWaterMarkerPositon()) == null) {
            return;
        }
        Paint paint = this.e;
        String str2 = this.f1006a;
        paint.getTextBounds(str2, 0, str2.length(), this.f);
        int i = waterMarkerPositon.x;
        int height = (waterMarkerPositon.y - this.f.height()) + 5;
        canvas.drawText(this.f1006a, ((this.b - this.f.width()) / 2) + i, height, this.e);
        float f = i;
        float height2 = height + (this.f.height() - 5);
        canvas.drawLine(f, height2 - (this.h * 2.0f), f, height2 + v.f1366a, this.d);
        canvas.drawLine(f, height2, this.b + i, height2, this.d);
        float f2 = i + this.b;
        canvas.drawLine(f2, height2 - (this.h * 2.0f), f2, height2 + v.f1366a, this.d);
    }

    private void a(String str) {
        this.f1006a = str;
    }

    private void a(int i) {
        this.b = i;
    }

    public final void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
        } else {
            a("");
            a(0);
            setVisibility(8);
        }
    }

    public final void b() {
        IAMapDelegate iAMapDelegate = this.c;
        if (iAMapDelegate == null) {
            return;
        }
        try {
            int engineIDWithType = iAMapDelegate.getGLMapEngine().getEngineIDWithType(1);
            float preciseLevel = this.c.getPreciseLevel(engineIDWithType);
            this.c.getGeoCenter(engineIDWithType, this.g);
            if (this.g == null) {
                return;
            }
            DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(r0.x, this.g.y, 20);
            float mapZoomScale = this.c.getMapZoomScale();
            double cos = (float) ((((Math.cos((pixelsToLatLong.y * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, preciseLevel) * 256.0d));
            int i = this.i[(int) preciseLevel];
            int i2 = (int) (i / (cos * mapZoomScale));
            String a2 = dv.a(i);
            a(i2);
            a(a2);
            pixelsToLatLong.recycle();
            invalidate();
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImpGLSurfaceView", "changeScaleState");
            th.printStackTrace();
        }
    }
}
