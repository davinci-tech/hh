package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.KaleidoscopeTrajectoryPointUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Objects;

/* loaded from: classes9.dex */
public class BasePatternKaleidoscopeView {
    private static final int CIRCLE_DEGREE = 360;
    protected static final int HALF_SCREEN_SIZE = 233;
    private static final float HALF_VALUE = 0.5f;
    protected static final float INTERCEPT_DEGREES = 30.1f;
    private static final int KALEIDOSCOPE_ROTATE_DEGREE = 60;
    private static final int PIXEL_OFFSET_X = 1;
    protected static final int SCREEN_SIZE = 466;
    private static final String TAG = "BasePatternKaleidoscopeView";
    private static final String TAG_RELEASE = "R_BasePatternKaleidoscopeView";
    protected static final int TWO_TIMES = 2;
    private BitmapShader mBitmapShader;
    protected int mCutPathRadius;
    private Bitmap mSrcBitmap;
    protected int mSrcBitmapSize;
    protected static final double SIN_30 = Math.sin(Math.toRadians(30.100000381469727d));
    protected static final double COS_30 = Math.cos(Math.toRadians(30.100000381469727d));
    private static final float[] MATRIX_X_VALUES = {1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] MATRIX_Y_VALUES = {-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    protected Path mPath = new Path();
    protected float mWatchFaceRadius = 233.0f;
    protected Paint mPaint = new Paint(1);
    protected RectF mInterceptRect = new RectF();
    protected float mRotateTangle = 0.0f;
    private RectF mCutRectF = new RectF();
    private Point mTrajectoryPoint = new Point(HALF_SCREEN_SIZE, HALF_SCREEN_SIZE);
    private boolean mIsSrcBitmapChanged = true;
    private Matrix mMatrix = new Matrix();
    private float mRotatePointValue = 0.0f;
    private float mRotateLeftOffset = 0.0f;
    private float mRotateTopOffset = 0.0f;
    protected Bitmap mDstBitmap = Bitmap.createBitmap(466, 466, Bitmap.Config.ARGB_8888);
    protected Canvas mDstBitmapCanvas = new Canvas(this.mDstBitmap);

    public BasePatternKaleidoscopeView(Bitmap bitmap) {
        this.mSrcBitmap = (Bitmap) Objects.requireNonNull(bitmap);
        this.mBitmapShader = new BitmapShader(this.mSrcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.mSrcBitmapSize = Math.min(bitmap.getHeight(), bitmap.getWidth());
        RectF rectF = new RectF(0.0f, 0.0f, 466.0f, 466.0f);
        this.mPath.reset();
        this.mPath.addArc(rectF, 0.0f, 360.0f);
        this.mPath.lineTo(233.0f, 233.0f);
        this.mDstBitmapCanvas.clipPath(this.mPath);
        KaleidoscopeTrajectoryPointUtil.getInstance().initWatchFaceIdPoint(this.mSrcBitmapSize);
    }

    public void updateDestBitmap() {
        Point geTrajectoryPoint = KaleidoscopeTrajectoryPointUtil.getInstance().geTrajectoryPoint();
        this.mRotateTangle = KaleidoscopeTrajectoryPointUtil.getInstance().getRotateTangle();
        if (!this.mIsSrcBitmapChanged && this.mTrajectoryPoint.equals(geTrajectoryPoint)) {
            LogUtil.c(TAG, "don't need to update.");
            return;
        }
        if (this.mSrcBitmap == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "updateDestBitmap:mSrcBitmap is null.");
            return;
        }
        this.mIsSrcBitmapChanged = false;
        this.mTrajectoryPoint.x = geTrajectoryPoint.x;
        this.mTrajectoryPoint.y = geTrajectoryPoint.y;
        updateDstBitmapByTrajectoryPoint(this.mTrajectoryPoint);
    }

    public void updateDstBitmapByTrajectoryPoint(Point point) {
        this.mInterceptRect.top = point.y - this.mCutPathRadius;
        this.mInterceptRect.left = point.x - this.mCutPathRadius;
        this.mInterceptRect.bottom = point.y + this.mCutPathRadius;
        this.mInterceptRect.right = point.x + this.mCutPathRadius;
        this.mPath.reset();
        this.mPath.addArc(this.mInterceptRect, this.mRotateTangle, INTERCEPT_DEGREES);
        this.mPath.lineTo(point.x, point.y);
    }

    protected void matrixSymmetricX(Bitmap bitmap, Canvas canvas, Paint paint, int i) {
        if (bitmap == null || canvas == null || paint == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "matrixSymmetricX params is null");
            return;
        }
        this.mMatrix.reset();
        this.mMatrix.setValues(MATRIX_X_VALUES);
        float f = i;
        this.mMatrix.postTranslate(0.0f, f);
        canvas.save();
        canvas.clipRect(0.0f, 0.0f, f, f / 2.0f);
        canvas.drawBitmap(bitmap, this.mMatrix, paint);
        canvas.restore();
    }

    protected void matrixSymmetricY(Bitmap bitmap, Canvas canvas, Paint paint, int i) {
        if (bitmap == null || canvas == null || paint == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "matrixSymmetricY params is null");
            return;
        }
        this.mMatrix.reset();
        this.mMatrix.setValues(MATRIX_Y_VALUES);
        float f = i;
        this.mMatrix.postTranslate(f, 0.0f);
        canvas.save();
        canvas.clipRect(0.0f, 0.0f, f / 2.0f, f);
        canvas.drawBitmap(bitmap, this.mMatrix, paint);
        canvas.restore();
    }

    private void matrixRotateBitmap(Bitmap bitmap, Canvas canvas, Paint paint, int i, int i2) {
        if (bitmap == null || canvas == null || paint == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "matrixRotateBitmap params is null");
            return;
        }
        float f = i;
        float f2 = f / 2.0f;
        this.mRotatePointValue = f2;
        this.mRotateLeftOffset = f2 - 1.0f;
        this.mRotateTopOffset = (i - i2) / 2.0f;
        this.mCutRectF.set(-2.0f, -1.0f, f, i + 1);
        for (int i3 = -60; i3 <= 60; i3 += 60) {
            canvas.save();
            float f3 = this.mRotatePointValue;
            canvas.rotate(i3, f3, f3);
            this.mPath.reset();
            this.mPath.addArc(this.mCutRectF, -30.1f, 60.2f);
            this.mPath.lineTo(this.mRotateLeftOffset, this.mRotatePointValue);
            canvas.clipPath(this.mPath);
            canvas.drawBitmap(bitmap, this.mRotateLeftOffset, this.mRotateTopOffset, paint);
            canvas.restore();
        }
    }

    protected Bitmap updateInterceptedAndSpliceBitmapByPath(Path path, Canvas canvas, Bitmap bitmap, Canvas canvas2, Bitmap bitmap2) {
        if (WatchFaceUtil.checkObjectIsNull(TAG, path, canvas, bitmap, bitmap2, canvas2)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getInterceptedAndSpliceBitmapByPath params is null");
            return this.mSrcBitmap;
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        updateCutBitmap(path, canvas, bitmap, bitmap.getHeight());
        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas2.setBitmap(bitmap2);
        matrixRotateBitmap(bitmap, canvas2, this.mPaint, bitmap2.getHeight(), bitmap.getHeight());
        matrixSymmetricY(bitmap2, canvas2, this.mPaint, bitmap2.getHeight());
        return bitmap2;
    }

    private void updateCutBitmap(Path path, Canvas canvas, Bitmap bitmap, int i) {
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setShader(this.mBitmapShader);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.setBitmap(bitmap);
        canvas.save();
        float f = i / 2.0f;
        canvas.rotate(-this.mRotateTangle, 0.0f, f);
        canvas.translate(-this.mTrajectoryPoint.x, f - this.mTrajectoryPoint.y);
        canvas.drawPath(path, this.mPaint);
        canvas.restore();
        matrixSymmetricX(bitmap, canvas, this.mPaint, i);
    }

    public Bitmap getDstBitmap() {
        return this.mDstBitmap;
    }

    public void releaseResource() {
        Bitmap bitmap = this.mSrcBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mSrcBitmap = null;
        }
        Bitmap bitmap2 = this.mDstBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mDstBitmap = null;
        }
        this.mBitmapShader = null;
    }
}
