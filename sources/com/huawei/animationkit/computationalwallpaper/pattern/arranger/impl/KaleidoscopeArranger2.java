package com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.AbstractArranger;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.KaleidoscopeArranger2;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType;
import defpackage.abo;
import defpackage.abv;
import defpackage.adl;
import defpackage.aeb;
import java.io.File;
import java.util.Optional;
import java.util.Properties;

/* loaded from: classes8.dex */
public class KaleidoscopeArranger2 extends AbstractArranger {
    private static final long ANIMATION_DURATION = 5000;
    private static final float ANIMATION_VALUE_INCREMENT = 0.05f;
    private static final String INIT_FRACTION = "init_fraction";
    private static final int PATH_OFFSET = 1;
    private static final String RESOURCE = "resource";
    private static final int SAMPLE_DEGREE = 45;
    private static final String TAG = "KaleidoscopeArranger";
    private float mAnimationValue;
    private Bitmap mBitmap;
    private final Bitmap mClipBitmap;
    private final int mClipLength;
    private float mInitFraction;
    private final Interpolator mInterpolator;
    private final Bitmap mMirroredBitmap;
    private final Paint mPaint;
    private final Path mPath;
    private final int mRadius;
    private final Point mSamplePoint;
    private UpdateListener mUpdateListener;
    private final ValueAnimator mValueAnimator;
    private String mVariable;
    private static final float SAMPLE_RADIUS = (float) Math.toRadians(45.0d);
    public static final Parcelable.Creator<KaleidoscopeArranger2> CREATOR = new Parcelable.Creator<KaleidoscopeArranger2>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.KaleidoscopeArranger2.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: gc_, reason: merged with bridge method [inline-methods] */
        public KaleidoscopeArranger2 createFromParcel(Parcel parcel) {
            return new KaleidoscopeArranger2(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public KaleidoscopeArranger2[] newArray(int i) {
            return new KaleidoscopeArranger2[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public KaleidoscopeArranger2() {
        this(aeb.a().nextFloat(VariableType.Random.RANDOM.getKey(), 0.0f, 1.0f));
    }

    private KaleidoscopeArranger2(float f) {
        int sqrt = (int) (Math.sqrt(Math.pow(abo.j(), 2.0d) + Math.pow(abo.i(), 2.0d)) / 2.0d);
        this.mRadius = sqrt;
        double d = SAMPLE_RADIUS;
        int cos = ((int) (sqrt * Math.cos(d))) + 1;
        this.mClipLength = cos;
        this.mVariable = VariableType.Random.RANDOM.getKey();
        this.mInterpolator = new PathInterpolator(0.2f, 0.0f, 0.8f, 1.0f);
        this.mValueAnimator = new ValueAnimator();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = getClipPath();
        this.mMirroredBitmap = Bitmap.createBitmap(sqrt, sqrt * 2, Bitmap.Config.ARGB_8888);
        this.mClipBitmap = Bitmap.createBitmap(cos, (int) (cos * Math.tan(d)), Bitmap.Config.ARGB_8888);
        this.mInitFraction = f;
        this.mSamplePoint = new Point(600, 600);
        this.mAnimationValue = this.mInitFraction;
        initAnimation();
    }

    private KaleidoscopeArranger2(Parcel parcel) {
        this(parcel.readFloat());
        setColor((ColorResult) parcel.readParcelable(ColorResult.class.getClassLoader()));
        setResource(parcel.readString());
    }

    private void initAnimation() {
        this.mValueAnimator.setDuration(5000L);
        this.mValueAnimator.setInterpolator(this.mInterpolator);
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: adt
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                KaleidoscopeArranger2.this.m125x716555df(valueAnimator);
            }
        });
    }

    /* renamed from: lambda$initAnimation$0$com-huawei-animationkit-computationalwallpaper-pattern-arranger-impl-KaleidoscopeArranger2, reason: not valid java name */
    public /* synthetic */ void m125x716555df(ValueAnimator valueAnimator) {
        this.mAnimationValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        Optional.ofNullable(this.mUpdateListener).ifPresent(new adl());
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void arrange(Canvas canvas) {
        try {
            initBitmap();
            draw(canvas);
        } catch (abv unused) {
            Log.e(TAG, "init bitmap failed");
        }
    }

    private void draw(Canvas canvas) {
        canvas.save();
        int centerX = getBound().centerX();
        int centerY = getBound().centerY();
        Bitmap mirrorBitmap = mirrorBitmap(clipBitmap());
        float sqrt = (float) ((Math.sqrt(Math.pow(getBound().width(), 2.0d) + Math.pow(getBound().height(), 2.0d)) / 2.0d) / this.mRadius);
        float f = centerX;
        float f2 = centerY;
        canvas.scale(sqrt, sqrt, f, f2);
        for (int i = 0; i < 4; i++) {
            canvas.drawBitmap(mirrorBitmap, f, centerY - this.mRadius, this.mPaint);
            canvas.rotate(90.0f, f, f2);
        }
        canvas.restore();
    }

    private void initBitmap() throws abv {
        if (this.mBitmap != null) {
            return;
        }
        this.mBitmap = getResourceBitmap(null);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void releaseBitmap() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mBitmap = null;
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void startAnimation() {
        float f = this.mAnimationValue % 1.0f;
        this.mAnimationValue = f;
        this.mValueAnimator.setFloatValues(f, 0.05f + f);
        this.mValueAnimator.start();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void cancelAnimation() {
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setUpdateListener(UpdateListener updateListener) {
        this.mUpdateListener = updateListener;
    }

    private Bitmap mirrorBitmap(Bitmap bitmap) {
        this.mMirroredBitmap.eraseColor(0);
        Canvas canvas = new Canvas(this.mMirroredBitmap);
        float f = 0;
        float f2 = this.mRadius;
        canvas.drawBitmap(bitmap, f, f2, this.mPaint);
        canvas.scale(1.0f, -1.0f, f, f2);
        canvas.drawBitmap(bitmap, f, f2, this.mPaint);
        canvas.scale(1.0f, -1.0f, f, f2);
        canvas.scale(-1.0f, 1.0f, bitmap.getWidth(), f2);
        canvas.drawBitmap(bitmap, f, f2, this.mPaint);
        canvas.scale(1.0f, -1.0f, bitmap.getWidth(), f2);
        canvas.drawBitmap(bitmap, f, f2, this.mPaint);
        return this.mMirroredBitmap;
    }

    private Bitmap clipBitmap() {
        float f = this.mAnimationValue * 360.0f;
        Log.i(TAG, "update position, x=" + this.mSamplePoint.x + " y=" + this.mSamplePoint.y + " degree=" + f);
        this.mClipBitmap.eraseColor(0);
        Canvas canvas = new Canvas(this.mClipBitmap);
        canvas.clipPath(this.mPath);
        canvas.translate(((float) this.mClipLength) / 3.0f, 0.0f);
        canvas.rotate(-f, 0.0f, 0.0f);
        int i = this.mRadius;
        int i2 = -i;
        canvas.drawBitmap(this.mBitmap, new Rect(this.mSamplePoint.x - this.mRadius, this.mSamplePoint.y - this.mRadius, this.mSamplePoint.x + this.mRadius, this.mSamplePoint.y + this.mRadius), new Rect(i2, i2, i, i), new Paint());
        return this.mClipBitmap;
    }

    private Path getClipPath() {
        int tan = (int) (this.mClipLength * Math.tan(SAMPLE_RADIUS));
        Path path = new Path();
        path.moveTo(-1.0f, 0.0f);
        path.lineTo(this.mClipLength, 0.0f);
        path.lineTo(this.mClipLength, tan + 2);
        path.close();
        return path;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void save(File file, Properties properties) throws abv {
        properties.setProperty(INIT_FRACTION, String.valueOf(this.mInitFraction));
        properties.setProperty(RESOURCE, getResource());
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void load(File file, Properties properties, ColorResult colorResult) throws abv {
        float parseFloat = parseFloat(properties.getProperty(INIT_FRACTION, "0"));
        this.mInitFraction = parseFloat;
        this.mAnimationValue = parseFloat;
        setResource(properties.getProperty(RESOURCE));
        setColor(colorResult);
        initAnimation();
    }

    private float parseFloat(String str) throws abv {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            Log.e(TAG, "load init fraction failed", e);
            throw new abv(e);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mInitFraction);
        parcel.writeParcelable(getColor(), i);
        parcel.writeString(getResource());
    }
}
