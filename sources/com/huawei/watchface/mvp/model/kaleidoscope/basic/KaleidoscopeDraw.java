package com.huawei.watchface.mvp.model.kaleidoscope.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.huawei.watchface.aw;
import com.huawei.watchface.ax;
import com.huawei.watchface.ay;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceBitmapUtil;

/* loaded from: classes7.dex */
public class KaleidoscopeDraw extends View {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f11083a;
    private Bitmap b;
    private String c;
    private aw d;
    private Paint e;

    public enum a {
        HONEYCOMB,
        RADIAL
    }

    public KaleidoscopeDraw(Context context, String str, String str2) {
        super(context);
        this.e = new Paint();
        this.c = str2;
        a(str);
        b();
    }

    public Bitmap a() {
        aw awVar = this.d;
        if (awVar != null) {
            awVar.a();
            this.b = this.d.b();
        }
        return this.b;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        super.onDraw(canvas);
        HwLog.i("KaleidoscopeDraw", "onDraw");
        if (canvas == null || (bitmap = this.b) == null) {
            HwLog.w("KaleidoscopeDraw", "onDraw: parameter error.");
        } else if (bitmap.isRecycled()) {
            HwLog.w("KaleidoscopeDraw", "drawBitmap: bitmap has recycled.");
        } else {
            canvas.drawBitmap(this.b, 0.0f, 0.0f, this.e);
        }
    }

    private void a(String str) {
        if (this.f11083a == null) {
            this.f11083a = WatchFaceBitmapUtil.getSafeDecodeFile(str);
        }
    }

    private void b() {
        if (this.f11083a == null) {
            return;
        }
        if (a.HONEYCOMB.toString().equals(this.c)) {
            this.d = new ax(this.f11083a);
        } else {
            this.d = new ay(this.f11083a);
        }
    }
}
