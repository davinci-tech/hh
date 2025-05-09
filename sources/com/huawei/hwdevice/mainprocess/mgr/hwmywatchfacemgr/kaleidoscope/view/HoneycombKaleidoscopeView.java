package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import com.huawei.haf.common.utils.CollectionUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class HoneycombKaleidoscopeView extends BasePatternKaleidoscopeView {
    private static final int HONEYCOMB_DENSITY_THREE = 3;
    private static final String TAG = "HoneycombKaleidoscopeView";
    private static final String TAG_RELEASE = "R_HoneycombKaleidoscopeView";
    private static final int TRANSLATE_PRECISION = 2;
    private List<Point> mCenterPointList;
    private Bitmap mCutBitmap;
    private Canvas mCutBitmapCanvas;
    private int mCutBitmapSizeCos30;
    private Bitmap mElementBitmap;
    private Canvas mElementBitmapCanvas;
    private int mElementBitmapSize;

    public HoneycombKaleidoscopeView(Bitmap bitmap) {
        super(bitmap);
        this.mCenterPointList = new ArrayList();
        this.mWatchFaceRadius /= 3.0f;
        this.mCutPathRadius = (int) Math.ceil(this.mWatchFaceRadius);
        this.mElementBitmapSize = (int) (this.mWatchFaceRadius * 2.0f);
        this.mCutBitmapSizeCos30 = (int) Math.ceil(this.mCutPathRadius * COS_30);
        this.mInterceptRect = new RectF(0.0f, 0.0f, this.mCutPathRadius, this.mCutPathRadius);
        this.mCutBitmap = Bitmap.createBitmap(this.mCutBitmapSizeCos30, this.mCutPathRadius, Bitmap.Config.ARGB_8888);
        int i = this.mElementBitmapSize;
        this.mElementBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        this.mCutBitmapCanvas = new Canvas(this.mCutBitmap);
        this.mElementBitmapCanvas = new Canvas(this.mElementBitmap);
        initCenterPointList();
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view.BasePatternKaleidoscopeView
    public void updateDstBitmapByTrajectoryPoint(Point point) {
        if (point == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "trajectoryPoint is null.");
            return;
        }
        super.updateDstBitmapByTrajectoryPoint(point);
        if (CollectionUtils.d(this.mCenterPointList)) {
            initCenterPointList();
        }
        Bitmap updateInterceptedAndSpliceBitmapByPath = updateInterceptedAndSpliceBitmapByPath(this.mPath, this.mCutBitmapCanvas, this.mCutBitmap, this.mElementBitmapCanvas, this.mElementBitmap);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mDstBitmapCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        this.mDstBitmapCanvas.setBitmap(this.mDstBitmap);
        float f = this.mElementBitmapSize / 2.0f;
        for (Point point2 : this.mCenterPointList) {
            this.mDstBitmapCanvas.drawBitmap(updateInterceptedAndSpliceBitmapByPath, point2.x - f, point2.y - f, this.mPaint);
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view.BasePatternKaleidoscopeView
    public void releaseResource() {
        super.releaseResource();
        this.mCenterPointList.clear();
        Bitmap bitmap = this.mCutBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mCutBitmap = null;
        }
        Bitmap bitmap2 = this.mElementBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mElementBitmap = null;
        }
    }

    private void initCenterPointList() {
        int i = (int) (this.mWatchFaceRadius + (this.mWatchFaceRadius * SIN_30));
        int i2 = ((int) ((this.mWatchFaceRadius * 2.0f) * COS_30)) - 2;
        int i3 = i2 / 2;
        int i4 = 0;
        int i5 = 0;
        while (i4 - this.mWatchFaceRadius < 233.0f) {
            for (int i6 = i5 % 2 != 0 ? i3 : 0; i6 - i3 < 233; i6 += i2) {
                if (i6 <= 233 || i4 <= i3) {
                    int i7 = i6 + 233;
                    int i8 = i4 + 233;
                    this.mCenterPointList.add(new Point(i7, i8));
                    if (i6 != 0) {
                        this.mCenterPointList.add(new Point(233 - i6, i8));
                    }
                    if (i4 != 0) {
                        this.mCenterPointList.add(new Point(i7, 233 - i4));
                    }
                    if (i6 != 0 && i4 != 0) {
                        this.mCenterPointList.add(new Point(233 - i6, 233 - i4));
                    }
                }
            }
            i4 += i - 2;
            i5++;
        }
    }
}
