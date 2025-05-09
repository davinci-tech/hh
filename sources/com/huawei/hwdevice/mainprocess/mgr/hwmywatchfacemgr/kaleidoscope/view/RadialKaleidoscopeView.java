package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class RadialKaleidoscopeView extends BasePatternKaleidoscopeView {
    private static final int CUT_PIXEL_PRECISION = 1;
    private static final String TAG = "RadialKaleidoscopeView";
    private static final String TAG_RELEASE = "R_RadialKaleidoscopeView";
    private Bitmap mCutBitmap;
    private Canvas mCutBitmapCanvas;

    public RadialKaleidoscopeView(Bitmap bitmap) {
        super(bitmap);
        this.mCutPathRadius = FitnessSleepType.HW_FITNESS_WAKE;
        this.mCutBitmap = Bitmap.createBitmap(this.mCutPathRadius, this.mCutPathRadius, Bitmap.Config.ARGB_8888);
        this.mCutBitmapCanvas = new Canvas(this.mCutBitmap);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view.BasePatternKaleidoscopeView
    public void updateDstBitmapByTrajectoryPoint(Point point) {
        if (point == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "trajectoryPoint is null.");
        } else {
            super.updateDstBitmapByTrajectoryPoint(point);
            updateInterceptedAndSpliceBitmapByPath(this.mPath, this.mCutBitmapCanvas, this.mCutBitmap, this.mDstBitmapCanvas, this.mDstBitmap);
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view.BasePatternKaleidoscopeView
    public void releaseResource() {
        super.releaseResource();
        Bitmap bitmap = this.mCutBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mCutBitmap = null;
        }
    }
}
