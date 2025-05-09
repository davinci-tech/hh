package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view.BasePatternKaleidoscopeView;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view.HoneycombKaleidoscopeView;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view.RadialKaleidoscopeView;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nrf;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class KaleidoscopeDraw extends View {
    private static final String TAG = "KaleidoscopeDraw";
    private static final String TAG_RELEASE = "R_KaleidoscopeDraw";
    private BasePatternKaleidoscopeView mBasePatternKaleidoscopeView;
    private Bitmap mDstBitmap;
    private String mKaleidoscopePattern;
    private Bitmap mResNameBitmap;
    private Paint paint;

    public enum EnumPattern {
        HONEYCOMB,
        RADIAL
    }

    public KaleidoscopeDraw(Context context, String str, String str2) {
        super(context);
        this.paint = new Paint();
        this.mKaleidoscopePattern = str2;
        initResNameBitmap(str);
        initPatternKaleidoscopeView();
    }

    public Bitmap preDraw() {
        BasePatternKaleidoscopeView basePatternKaleidoscopeView = this.mBasePatternKaleidoscopeView;
        if (basePatternKaleidoscopeView != null) {
            basePatternKaleidoscopeView.updateDestBitmap();
            this.mDstBitmap = this.mBasePatternKaleidoscopeView.getDstBitmap();
        }
        return this.mDstBitmap;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        super.onDraw(canvas);
        LogUtil.a(TAG, "onDraw");
        if (canvas == null || (bitmap = this.mDstBitmap) == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "onDraw: parameter error.");
        } else if (bitmap.isRecycled()) {
            ReleaseLogUtil.d(TAG_RELEASE, "drawBitmap: bitmap has recycled.");
        } else {
            canvas.drawBitmap(this.mDstBitmap, 0.0f, 0.0f, this.paint);
        }
    }

    protected void onDestroy() {
        Bitmap bitmap = this.mDstBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mDstBitmap = null;
        }
        Bitmap bitmap2 = this.mResNameBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mResNameBitmap = null;
        }
        this.mResNameBitmap = null;
        this.mDstBitmap = null;
        BasePatternKaleidoscopeView basePatternKaleidoscopeView = this.mBasePatternKaleidoscopeView;
        if (basePatternKaleidoscopeView != null) {
            basePatternKaleidoscopeView.releaseResource();
            this.mBasePatternKaleidoscopeView = null;
        }
    }

    private void initResNameBitmap(String str) {
        if (this.mResNameBitmap == null) {
            this.mResNameBitmap = nrf.cHA_(str);
        }
    }

    private void initPatternKaleidoscopeView() {
        if (this.mResNameBitmap == null) {
            return;
        }
        if (EnumPattern.HONEYCOMB.toString().equals(this.mKaleidoscopePattern)) {
            this.mBasePatternKaleidoscopeView = new HoneycombKaleidoscopeView(this.mResNameBitmap);
        } else {
            this.mBasePatternKaleidoscopeView = new RadialKaleidoscopeView(this.mResNameBitmap);
        }
    }
}
