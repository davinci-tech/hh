package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ecc;
import defpackage.ecu;
import defpackage.een;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class CoreSleepDayDetailView extends BaseSleepDayDetailView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2601a;
    private long b;
    private boolean c;
    private boolean d;
    private OnClickViewDefaultListener e;
    private String f;
    private boolean g;
    private float h;
    private float i;
    private PointF j;
    private float k;
    private float l;
    private float m;
    private float n;
    private int o;

    public interface OnClickViewDefaultListener {
        void onClickViewDefaultListener(String str, String str2, int i, boolean z, int i2, ArrayList<ecu> arrayList, int i3);
    }

    public CoreSleepDayDetailView(Context context) {
        this(context, null);
        setBaseContext(context);
    }

    public CoreSleepDayDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2601a = false;
        this.j = new PointF(-1.0f, -1.0f);
        this.b = 0L;
        setBaseContext(context);
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.huawei.health.knit.section.chart.CoreSleepDayDetailView.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                CoreSleepDayDetailView.this.f2601a = true;
                return false;
            }
        });
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setViewWidth(getDefaultSize(getSuggestedMinimumWidth(), i));
        setViewHeight(getDefaultSize(getSuggestedMinimumHeight(), i2));
        setDiagramWidth(getViewWidth() - een.e(48.0f));
        setDiagramHeight(een.e(158.0f));
        setTransparentHeight(getViewHeight() - een.e(24.0f));
        setCursorHeight(getTransparentHeight());
        setCursorCenter(getViewWidth() / 2.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        getTimeLabel();
        afw_(canvas);
        drawSleepRect(canvas);
        c();
        if (getIsDefault()) {
            b();
        }
        afy_(canvas);
        drawMaxSleepVerticalLine(canvas);
        afv_(canvas);
        drawCursorText(canvas);
        afz_(canvas);
    }

    private void afw_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1.0f);
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297799_res_0x7f090607));
        canvas.drawRect(0.0f, getTransparentHeight(), getViewWidth(), getViewHeight(), paint);
    }

    private void c() {
        ArrayList<ecc> cursorList = getCursorList();
        int size = cursorList != null ? cursorList.size() : 0;
        if (this.e == null) {
            return;
        }
        if (cursorList == null || size == 0) {
            if (getIsMove()) {
                return;
            }
            LogUtil.b("CoreSleepDayDetailView", "setTimeSegmentListener !getIsMove()");
            this.e.onClickViewDefaultListener("", "", 0, getIsOnlyNoonSleepType(), this.mCursorInBedState, getManualCursorList(), 0);
            return;
        }
        ecc eccVar = cursorList.get(size - 1);
        this.f = eccVar.j;
        LogUtil.b("CoreSleepDayDetailView", "setTimeSegmentListener mTimePeriod is " + this.f);
        this.e.onClickViewDefaultListener(this.f, getSleepTypeString(eccVar.f11950a), (int) eccVar.c, getIsOnlyNoonSleepType(), this.mCursorInBedState, getManualCursorList(), eccVar.f11950a);
    }

    private void b() {
        float a2;
        float chartBorderWidth;
        if (getLastData() != null) {
            if (getIsOnlyNoonSleepType()) {
                a2 = (getLastData().a() / 1440.0f) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            } else {
                a2 = ((getLastData().a() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            }
            float f = a2 + chartBorderWidth;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                f = getViewWidth() - f;
            }
            if (!getIsMove()) {
                setCursorCenter(f + 1.0f);
            }
            LogUtil.b("debug2", "setDefaultCursorPoint");
            OnClickViewDefaultListener onClickViewDefaultListener = this.e;
            if (onClickViewDefaultListener == null) {
                return;
            }
            onClickViewDefaultListener.onClickViewDefaultListener("", "", -1, getIsOnlyNoonSleepType(), this.mCursorInBedState, getManualCursorList(), 0);
            return;
        }
        setCursorCenter(getViewWidth() / 2.0f);
    }

    private void afy_(Canvas canvas) {
        float e;
        if (getFitnessDataList() == null || getFitnessDataList().size() == 0) {
            return;
        }
        this.j.set(getCursorCenter(), getCursorHeight());
        c(this.j.x);
        if (this.d && !this.c) {
            Paint paint = new Paint();
            paint.setStrokeWidth(1.0f);
            if (this.mIsManual) {
                paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getTransparentHeight(), this.o, getBaseContext().getResources().getColor(R.color._2131298755_res_0x7f0909c3), Shader.TileMode.CLAMP));
            } else {
                paint.setColor(this.o);
            }
            RectF rectF = new RectF(this.k, this.l, this.n, getTransparentHeight());
            if (Math.abs(this.n - this.k) < een.e(6.0f)) {
                e = Math.abs(this.n - this.k) / 2.0f;
            } else {
                e = een.e(6.0f);
            }
            float[] fArr = {e, e, e, e, 0.0f, 0.0f, 0.0f, 0.0f};
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            canvas.drawPath(path, paint);
        }
    }

    private void afv_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297799_res_0x7f090607));
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        float e = een.e(36.0f);
        float e2 = een.e(13.5f);
        path.moveTo(getCursorCenter() - e, getCursorHeight());
        float cursorCenter = getCursorCenter();
        float f = (2.0f * e) / 6.0f;
        float cursorHeight = getCursorHeight();
        float cursorCenter2 = getCursorCenter();
        path.cubicTo(cursorCenter - f, cursorHeight, cursorCenter2 - f, getCursorHeight() - e2, getCursorCenter(), getCursorHeight() - e2);
        float cursorCenter3 = getCursorCenter();
        float cursorHeight2 = getCursorHeight();
        float cursorCenter4 = getCursorCenter();
        float f2 = cursorCenter3 + f;
        float f3 = cursorHeight2 - e2;
        float f4 = ((e * 3.0f) / 6.0f) + cursorCenter4;
        path.cubicTo(f2, f3, f4, getCursorHeight(), getCursorCenter() + e, getCursorHeight());
        canvas.drawPath(path, paint);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(een.e(7.0f), 0.0f, een.e(3.0f), getBaseContext().getResources().getColor(R.color._2131297802_res_0x7f09060a));
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131296657_res_0x7f090191));
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + een.e(1.0f), een.e(11.0f), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setAlpha(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + een.e(1.0f), een.e(11.0f), paint);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297801_res_0x7f090609));
        paint.setAntiAlias(true);
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + een.e(1.0f), een.e(11.0f), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(een.e(0.5f));
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297802_res_0x7f09060a));
        paint.setAntiAlias(true);
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + een.e(1.0f), een.e(11.25f), paint);
    }

    private void afz_(Canvas canvas) {
        if (getFitnessDataList() == null || getFitnessDataList().size() == 0) {
            return;
        }
        try {
            Paint paint = new Paint();
            int[] iArr = {getBaseContext().getResources().getColor(R.color._2131296808_res_0x7f090228), getBaseContext().getResources().getColor(R.color._2131296807_res_0x7f090227), getBaseContext().getResources().getColor(R.color._2131296808_res_0x7f090228)};
            if (getColorType() == 1) {
                paint.setAlpha(10);
            }
            paint.setShader(new LinearGradient(getCursorCenter(), 0.0f, getCursorCenter(), getCursorHeight() - 60.0f, iArr, (float[]) null, Shader.TileMode.MIRROR));
            paint.setStrokeWidth(1.0f);
            paint.setAntiAlias(true);
            canvas.drawLine(getCursorCenter(), een.e(16.0f), getCursorCenter(), getCursorHeight() - een.e(16.0f), paint);
        } catch (IllegalArgumentException unused) {
            LogUtil.a("CoreSleepDayDetailView", "API version too high do not use linearGradient!");
            afx_(canvas);
        }
    }

    private void afx_(Canvas canvas) {
        Paint paint = new Paint();
        if (getColorType() == 1) {
            paint.setAlpha(10);
        }
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131296807_res_0x7f090227));
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        canvas.drawLine(getCursorCenter(), een.e(16.0f), getCursorCenter(), getCursorHeight() - een.e(16.0f), paint);
    }

    private void c(float f) {
        if (getFitnessDataList() == null || getFitnessDataList().size() == 0) {
            getCursorList().clear();
            LogUtil.b("R_Sleep_CoreSleepDayDetailView", "addAnchor getFitnessDataList() == null or getFitnessDataList().size() == 0");
            return;
        }
        for (ecu ecuVar : getFitnessDataList()) {
            int c = ecuVar.c();
            int a2 = ecuVar.a();
            if (getIsOnlyNoonSleepType()) {
                c(ecuVar, c, a2);
            } else if (getIsScienceSleep()) {
                if (ecuVar.d() == 69) {
                    this.c = true;
                } else {
                    c(ecuVar);
                }
            } else if (ecuVar.d() == 69) {
                this.c = true;
            } else {
                a(ecuVar);
            }
            this.m = getOnlyNoonRectSetting(ecuVar.d(), getDiagramHeight()).a();
            if (f >= this.h && f <= this.i) {
                c(ecuVar, c, a2);
                return;
            } else {
                this.d = false;
                getCursorList().clear();
            }
        }
    }

    private void c(ecu ecuVar, float f, float f2) {
        this.d = true;
        ecc eccVar = new ecc();
        eccVar.e = 1;
        eccVar.d = new PointF(f, this.m);
        int a2 = ecuVar.a();
        int c = ecuVar.c();
        eccVar.j = getDigitTimeStr(ecuVar.c()) + " - " + getDigitTimeStr(ecuVar.a());
        this.f = getDigitTimeStr(ecuVar.c()) + " - " + getDigitTimeStr(ecuVar.a());
        eccVar.c = (double) (a2 - c);
        eccVar.b = new RectF(f, f2, 100.0f + f, getViewHeight());
        eccVar.f11950a = ecuVar.d();
        getCursorList().clear();
        getCursorList().add(eccVar);
    }

    private void c(ecu ecuVar, int i, int i2) {
        d(i, i2);
        b(ecuVar.d());
    }

    private void c(ecu ecuVar) {
        d(ecuVar);
        b(ecuVar.d(), false);
    }

    private void d(ecu ecuVar) {
        this.c = false;
        int c = ecuVar.c();
        int a2 = ecuVar.a();
        float nightStartPoint = (((c - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth()) + getChartBorderWidth();
        float nightStartPoint2 = (((a2 - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth()) + getChartBorderWidth();
        this.h = (((ecuVar.c() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth()) + getChartBorderWidth();
        this.i = (((ecuVar.a() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth()) + getChartBorderWidth();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float viewWidth = getViewWidth();
            float viewWidth2 = getViewWidth() - nightStartPoint;
            float f = this.h;
            this.h = getViewWidth() - this.i;
            this.i = getViewWidth() - f;
            nightStartPoint = viewWidth - nightStartPoint2;
            nightStartPoint2 = viewWidth2;
        }
        this.k = nightStartPoint;
        this.n = nightStartPoint2;
    }

    private void a(ecu ecuVar) {
        d(ecuVar);
        c(ecuVar.d());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        setIsDefault(false);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.b = System.currentTimeMillis();
        } else if (action == 1) {
            afC_(motionEvent);
        } else if (action == 2) {
            afA_(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    private void afA_(MotionEvent motionEvent) {
        if (getCursorHeight() - een.e(18.0f) < motionEvent.getY() && motionEvent.getY() < getViewHeight()) {
            afD_(motionEvent);
        } else if (this.f2601a) {
            afD_(motionEvent);
        } else {
            this.g = true;
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }

    private void afD_(MotionEvent motionEvent) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            if (motionEvent.getX() < een.e(22.0f) || motionEvent.getX() > getViewWidth() - getChartBorderWidth()) {
                return;
            }
            afB_(motionEvent);
            return;
        }
        if (getChartBorderWidth() > motionEvent.getX() || motionEvent.getX() > getViewWidth() - een.e(22.0f)) {
            return;
        }
        afB_(motionEvent);
    }

    private void afC_(MotionEvent motionEvent) {
        this.f2601a = false;
        if (System.currentTimeMillis() - this.b < 850) {
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                if (een.e(22.0f) <= motionEvent.getX() && motionEvent.getX() <= getViewWidth() - getChartBorderWidth()) {
                    c(motionEvent.getX());
                    afB_(motionEvent);
                }
            } else if (getChartBorderWidth() <= motionEvent.getX() && motionEvent.getX() <= getViewWidth() - een.e(22.0f)) {
                c(motionEvent.getX());
                afB_(motionEvent);
            }
        }
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    private void afB_(MotionEvent motionEvent) {
        float a2;
        float chartBorderWidth;
        this.g = false;
        this.j.set(motionEvent.getX(), motionEvent.getY());
        c(this.j.x);
        if (getLastData() != null) {
            if (getIsOnlyNoonSleepType()) {
                a2 = (getLastData().a() / 1440.0f) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            } else {
                a2 = ((getLastData().a() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            }
            float f = a2 + chartBorderWidth;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                if (motionEvent.getX() >= getViewWidth() - f && motionEvent.getX() <= getViewWidth() - getChartBorderWidth()) {
                    setIsDefault(false);
                    setIsMove(false);
                } else {
                    setIsDefault(true);
                    setIsMove(true);
                }
            } else if (motionEvent.getX() >= f && motionEvent.getX() <= getViewWidth() - een.e(22.0f)) {
                setIsDefault(true);
                setIsMove(true);
            } else {
                setIsDefault(false);
                setIsMove(false);
            }
        }
        setCursorCenter(motionEvent.getX());
        invalidate();
    }

    public void setonClickViewDefaultListener(OnClickViewDefaultListener onClickViewDefaultListener) {
        this.e = onClickViewDefaultListener;
    }

    private void b(int i, boolean z) {
        setIsOtherType(false);
        if (this.mIsPhone) {
            a(i);
        } else if (this.mIsManual) {
            e(i, z);
        } else {
            d(i);
        }
    }

    private void e(int i, boolean z) {
        int color;
        if (i == 66) {
            this.l = een.e(24.0f) + ((getDiagramHeight() - een.e(24.0f)) * 0.33f);
            if (getColorType() == 1) {
                this.o = getBaseContext().getResources().getColor(R.color._2131298755_res_0x7f0909c3);
                return;
            } else {
                this.o = getBaseContext().getResources().getColor(R.color._2131298758_res_0x7f0909c6);
                return;
            }
        }
        if (i == 67) {
            if (this.mIsManualAndOnlyBed) {
                this.l = een.e(24.0f) + ((getDiagramHeight() - een.e(24.0f)) * 0.33f);
                if (getColorType() == 1) {
                    this.o = getBaseContext().getResources().getColor(R.color._2131298748_res_0x7f0909bc);
                    return;
                } else {
                    this.o = getBaseContext().getResources().getColor(R.color._2131298753_res_0x7f0909c1);
                    return;
                }
            }
            this.l = een.e(24.0f);
            if (getColorType() == 1) {
                this.o = getBaseContext().getResources().getColor(R.color._2131298748_res_0x7f0909bc);
                return;
            }
            if (z) {
                color = getBaseContext().getResources().getColor(R.color._2131298759_res_0x7f0909c7);
            } else {
                color = getBaseContext().getResources().getColor(R.color._2131298751_res_0x7f0909bf);
            }
            this.o = color;
            return;
        }
        setIsOtherType(true);
    }

    private void c(int i) {
        setIsOtherType(false);
        switch (i) {
            case 65:
                this.l = een.e(24.0f) + (getDiagramHeight() * 0.66f);
                a();
                break;
            case 66:
                this.l = een.e(24.0f) + (getDiagramHeight() * 0.33f);
                if (getColorType() == 1) {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296719_res_0x7f0901cf);
                    break;
                } else {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296765_res_0x7f0901fd);
                    break;
                }
            case 67:
                d();
                break;
            default:
                setIsOtherType(true);
                break;
        }
    }

    private void d() {
        this.l = een.e(24.0f);
        if (getColorType() == 1) {
            this.o = getBaseContext().getResources().getColor(R.color._2131298748_res_0x7f0909bc);
        } else {
            this.o = getBaseContext().getResources().getColor(R.color._2131298751_res_0x7f0909bf);
        }
    }

    private void b(int i) {
        setIsOtherType(false);
        if (i == 67) {
            d();
            return;
        }
        if (i == 69) {
            this.l = een.e(24.0f) + ((getDiagramHeight() - een.e(24.0f)) * 0.38f);
            if (getColorType() == 1) {
                this.o = getBaseContext().getResources().getColor(R.color._2131296711_res_0x7f0901c7);
                return;
            } else {
                this.o = getBaseContext().getResources().getColor(R.color._2131296760_res_0x7f0901f8);
                return;
            }
        }
        setIsOtherType(true);
    }

    public boolean e() {
        return this.g;
    }

    private void d(int i, int i2) {
        this.c = false;
        this.h = ((i / 1440.0f) * getDiagramWidth()) + getChartBorderWidth();
        this.i = ((i2 / 1440.0f) * getDiagramWidth()) + getChartBorderWidth();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f = this.h;
            this.h = getViewWidth() - this.i;
            this.i = getViewWidth() - f;
        }
        this.k = this.h;
        this.n = this.i;
    }

    private void a() {
        if (getColorType() == 1) {
            this.o = getBaseContext().getResources().getColor(R.color._2131296713_res_0x7f0901c9);
        } else {
            this.o = getBaseContext().getResources().getColor(R.color._2131296763_res_0x7f0901fb);
        }
    }

    private void a(int i) {
        switch (i) {
            case 65:
                this.l = een.e(24.0f) + (getDiagramHeight() * 0.66f);
                a();
                break;
            case 66:
                this.l = een.e(24.0f) + (getDiagramHeight() * 0.33f);
                if (getColorType() == 1) {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296719_res_0x7f0901cf);
                    break;
                } else {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296765_res_0x7f0901fd);
                    break;
                }
            case 67:
                d();
                break;
            default:
                setIsOtherType(true);
                break;
        }
    }

    private void d(int i) {
        switch (i) {
            case 65:
                this.l = een.e(24.0f) + (getDiagramHeight() * 0.75f);
                a();
                break;
            case 66:
                this.l = een.e(24.0f) + (getDiagramHeight() * 0.5f);
                if (getColorType() == 1) {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296719_res_0x7f0901cf);
                    break;
                } else {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296765_res_0x7f0901fd);
                    break;
                }
            case 67:
                this.l = een.e(24.0f);
                if (getColorType() == 1) {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296732_res_0x7f0901dc);
                    break;
                } else {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296767_res_0x7f0901ff);
                    break;
                }
            case 68:
                this.l = een.e(24.0f) + (getDiagramHeight() * 0.25f);
                if (getColorType() == 1) {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296727_res_0x7f0901d7);
                    break;
                } else {
                    this.o = getBaseContext().getResources().getColor(R.color._2131296766_res_0x7f0901fe);
                    break;
                }
            default:
                setIsOtherType(true);
                break;
        }
    }
}
