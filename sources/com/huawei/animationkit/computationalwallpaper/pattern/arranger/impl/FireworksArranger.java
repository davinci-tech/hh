package com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.AbstractArranger;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.FireworksArranger;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType;
import defpackage.abv;
import defpackage.adl;
import defpackage.aee;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/* loaded from: classes8.dex */
public class FireworksArranger extends AbstractArranger {
    private static final long ANIMATION_DURATION = 3000;
    public static final Parcelable.Creator<FireworksArranger> CREATOR = new Parcelable.Creator<FireworksArranger>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.FireworksArranger.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: fY_, reason: merged with bridge method [inline-methods] */
        public FireworksArranger createFromParcel(Parcel parcel) {
            return new FireworksArranger(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public FireworksArranger[] newArray(int i) {
            return new FireworksArranger[i];
        }
    };
    private static final int DEGREE = 30;
    private static final float SIZE = 500.0f;
    private static final String TAG = "FireworksArranger";
    private final List<b> mAlphaFractionPoint;
    private final Interpolator mAppearInterpolator;
    private List<Integer> mColors;
    private final Interpolator mDisappearInterpolator;
    private final FloatEvaluator mEvaluator;
    private float mFraction;
    private List<Integer> mGradients;
    private final Paint mPaint;
    private final List<b> mSizeFractionPoint;
    private UpdateListener mUpdateListener;
    private final ValueAnimator mValueAnimator;
    private String mVariable;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void releaseBitmap() {
    }

    public FireworksArranger() {
        this.mEvaluator = new FloatEvaluator();
        this.mVariable = VariableType.Random.RANDOM.getKey();
        ArrayList arrayList = new ArrayList();
        this.mSizeFractionPoint = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.mAlphaFractionPoint = arrayList2;
        this.mAppearInterpolator = new PathInterpolator(0.2f, 0.0f, 0.1f, 1.0f);
        this.mDisappearInterpolator = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
        this.mValueAnimator = new ValueAnimator();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        arrayList.add(new b(0.0f, 0.67f, 0.78f, 0.94f));
        arrayList.add(new b(0.0f, 0.75f, 0.805f, 0.97f));
        arrayList.add(new b(0.0f, 0.83f, 0.83f, 1.0f));
        arrayList2.add(new b(0.0f, 0.33f, 0.78f, 0.94f));
        arrayList2.add(new b(0.083f, 0.417f, 0.805f, 0.97f));
        arrayList2.add(new b(0.17f, 0.5f, 0.83f, 1.0f));
    }

    private FireworksArranger(Parcel parcel) {
        this();
        ColorResult colorResult = (ColorResult) parcel.readParcelable(ColorResult.class.getClassLoader());
        super.setColor(colorResult);
        initColors(colorResult);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void arrange(Canvas canvas) {
        float centerX = getBound().centerX();
        float centerY = getBound().centerY();
        float fraction = getFraction();
        canvas.save();
        float max = Math.max(getBound().width(), getBound().height()) / SIZE;
        canvas.scale(max, max, centerX, centerY);
        drawFirstLayer(canvas, fraction);
        canvas.rotate(15.0f, centerX, centerY);
        drawSecondLayer(canvas, fraction);
        canvas.rotate(-15.0f, centerX, centerY);
        drawThirdLayer(canvas, fraction);
        canvas.restore();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void startAnimation() {
        this.mValueAnimator.setDuration(ANIMATION_DURATION);
        this.mValueAnimator.setFloatValues(0.0f, 1.0f);
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: adq
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                FireworksArranger.this.m123x9b766cc(valueAnimator);
            }
        });
        this.mValueAnimator.start();
    }

    /* renamed from: lambda$startAnimation$0$com-huawei-animationkit-computationalwallpaper-pattern-arranger-impl-FireworksArranger, reason: not valid java name */
    public /* synthetic */ void m123x9b766cc(ValueAnimator valueAnimator) {
        this.mFraction = valueAnimator.getAnimatedFraction();
        Optional.ofNullable(this.mUpdateListener).ifPresent(new adl());
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void cancelAnimation() {
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void goFirstFrame() {
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
        }
        this.mFraction = 0.0f;
        Optional.ofNullable(this.mUpdateListener).ifPresent(new adl());
        Log.i(TAG, "goFirstFrame");
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setUpdateListener(UpdateListener updateListener) {
        this.mUpdateListener = updateListener;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void load(File file, Properties properties, ColorResult colorResult) throws abv {
        super.load(file, properties, colorResult);
        initColors(colorResult);
    }

    private float getFraction() {
        if (this.mValueAnimator.isRunning()) {
            return (this.mFraction + 0.78f) % 1.0f;
        }
        return 0.78f;
    }

    private void drawFirstLayer(Canvas canvas, float f) {
        float sizeFraction = getSizeFraction(f, this.mSizeFractionPoint.get(0));
        float floatValue = this.mEvaluator.evaluate(sizeFraction, (Number) 2, (Number) 9).floatValue();
        float floatValue2 = this.mEvaluator.evaluate(sizeFraction, (Number) 3, (Number) 12).floatValue();
        float floatValue3 = this.mEvaluator.evaluate(sizeFraction, (Number) 50, (Number) 106).floatValue();
        float floatValue4 = this.mEvaluator.evaluate(sizeFraction, (Number) 40, (Number) 100).floatValue();
        this.mPaint.setAlpha(getAlpha(f, this.mAlphaFractionPoint.get(0)));
        a aVar = new a(floatValue, floatValue2, floatValue3, floatValue4);
        aVar.a(this.mColors.get(0).intValue(), this.mGradients.get(0).intValue());
        drawFlower(canvas, aVar);
    }

    private void drawSecondLayer(Canvas canvas, float f) {
        float sizeFraction = getSizeFraction(f, this.mSizeFractionPoint.get(1));
        float floatValue = this.mEvaluator.evaluate(sizeFraction, (Number) 2, (Number) 9).floatValue();
        float floatValue2 = this.mEvaluator.evaluate(sizeFraction, (Number) 3, (Number) 12).floatValue();
        float floatValue3 = this.mEvaluator.evaluate(sizeFraction, (Number) 50, (Number) 74).floatValue();
        float floatValue4 = this.mEvaluator.evaluate(sizeFraction, (Number) 40, (Number) 100).floatValue();
        this.mPaint.setAlpha(getAlpha(f, this.mAlphaFractionPoint.get(1)));
        a aVar = new a(floatValue, floatValue2, floatValue3, floatValue4);
        aVar.a(this.mColors.get(1).intValue(), this.mGradients.get(1).intValue());
        drawFlower(canvas, aVar);
    }

    private void drawThirdLayer(Canvas canvas, float f) {
        float sizeFraction = getSizeFraction(f, this.mSizeFractionPoint.get(2));
        float floatValue = this.mEvaluator.evaluate(sizeFraction, (Number) 2, (Number) 9).floatValue();
        float floatValue2 = this.mEvaluator.evaluate(sizeFraction, (Number) 3, (Number) 12).floatValue();
        float floatValue3 = this.mEvaluator.evaluate(sizeFraction, (Number) 50, (Number) 30).floatValue();
        float floatValue4 = this.mEvaluator.evaluate(sizeFraction, (Number) 40, (Number) 100).floatValue();
        this.mPaint.setAlpha(getAlpha(f, this.mAlphaFractionPoint.get(2)));
        a aVar = new a(floatValue, floatValue2, floatValue3, floatValue4);
        aVar.a(this.mColors.get(2).intValue(), this.mGradients.get(2).intValue());
        drawFlower(canvas, aVar);
    }

    private void drawFlower(Canvas canvas, a aVar) {
        int i = aVar.c;
        int c = aee.c(aVar.b, 0.0f);
        float centerX = getBound().centerX();
        float f = aVar.e;
        float f2 = aVar.j;
        float centerX2 = getBound().centerX();
        float f3 = aVar.e;
        this.mPaint.setShader(new LinearGradient((centerX + f) - f2, getBound().centerY(), centerX2 + f3 + aVar.d + aVar.f1853a, getBound().centerY(), new int[]{c, i}, (float[]) null, Shader.TileMode.CLAMP));
        Path createPetalPath = createPetalPath(aVar);
        canvas.save();
        for (int i2 = 0; i2 < 360; i2 += 30) {
            canvas.rotate(30, getBound().centerX(), getBound().centerY());
            canvas.drawPath(createPetalPath, this.mPaint);
        }
        canvas.restore();
    }

    private Path createPetalPath(a aVar) {
        float f = aVar.j;
        float f2 = aVar.f1853a;
        float f3 = aVar.e;
        float f4 = aVar.d;
        PointF pointF = new PointF(getBound().centerX() + f3, getBound().centerY());
        PointF pointF2 = new PointF(pointF.x + f4, pointF.y);
        double acos = Math.acos(((f2 - f) * 1.0d) / f4);
        PointF pointF3 = new PointF();
        PointF pointF4 = new PointF();
        PointF pointF5 = new PointF();
        PointF pointF6 = new PointF();
        double d = f;
        pointF3.x = (float) (pointF.x - (Math.cos(acos) * d));
        pointF4.x = pointF3.x;
        pointF3.y = (float) (pointF.y - (Math.sin(acos) * d));
        pointF4.y = (float) (pointF.y + (d * Math.sin(acos)));
        double d2 = f2;
        pointF5.x = (float) (pointF2.x - (Math.cos(acos) * d2));
        pointF6.x = pointF5.x;
        pointF5.y = (float) (pointF2.y - (Math.sin(acos) * d2));
        pointF6.y = (float) (pointF2.y + (d2 * Math.sin(acos)));
        Path path = new Path();
        path.addCircle(pointF.x, pointF.y, f, Path.Direction.CW);
        path.addCircle(pointF2.x, pointF2.y, f2, Path.Direction.CW);
        path.op(createPolygonPath(Arrays.asList(pointF, pointF3, pointF5, pointF2, pointF6, pointF4, pointF)), Path.Op.UNION);
        return path;
    }

    private Path createPolygonPath(List<PointF> list) {
        if (list.size() < 2) {
            throw new RuntimeException("The number of points must be greater than 2");
        }
        Path path = new Path();
        path.moveTo(list.get(0).x, list.get(0).y);
        for (int i = 1; i < list.size(); i++) {
            path.lineTo(list.get(i).x, list.get(i).y);
        }
        path.close();
        return path;
    }

    private float getSizeFraction(float f, b bVar) {
        if (f < bVar.e) {
            return 0.0f;
        }
        if (f < bVar.c) {
            return this.mAppearInterpolator.getInterpolation((f - bVar.e) / bVar.d()) * 0.96f;
        }
        if (f < bVar.b) {
            return 0.96f;
        }
        return (this.mDisappearInterpolator.getInterpolation((f - bVar.b) / bVar.b()) * 0.04f) + 0.96f;
    }

    private int getAlpha(float f, b bVar) {
        if (f < bVar.e) {
            return 0;
        }
        if (f < bVar.c) {
            return (int) (this.mAppearInterpolator.getInterpolation((f - bVar.e) / bVar.d()) * 255.0f);
        }
        if (f < bVar.b) {
            return 255;
        }
        if (f >= bVar.d) {
            return 0;
        }
        return (int) ((1.0f - this.mDisappearInterpolator.getInterpolation((f - bVar.b) / bVar.b())) * 255.0f);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getColor(), i);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.AbstractArranger, com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setColor(ColorResult colorResult) {
        super.setColor(colorResult);
        initColors(colorResult);
    }

    private void initColors(ColorResult colorResult) {
        this.mColors = colorResult.getAllColors();
        this.mGradients = colorResult.getAllGradients();
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private final float f1853a;
        private int b;
        private int c;
        private final float d;
        private final float e;
        private final float j;

        a(float f, float f2, float f3, float f4) {
            this.j = f;
            this.f1853a = f2;
            this.e = f3;
            this.d = f4;
        }

        public void a(int i, int i2) {
            this.c = i;
            this.b = i2;
        }
    }

    static class b {
        private final float b;
        private final float c;
        private final float d;
        private final float e;

        private b(float f, float f2, float f3, float f4) {
            this.e = f;
            this.c = f2;
            this.b = f3;
            this.d = f4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float d() {
            return this.c - this.e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float b() {
            return this.d - this.b;
        }
    }
}
