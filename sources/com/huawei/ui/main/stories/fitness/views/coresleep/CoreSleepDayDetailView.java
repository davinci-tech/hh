package com.huawei.ui.main.stories.fitness.views.coresleep;

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
import com.huawei.ui.main.R$string;
import defpackage.pvz;
import defpackage.pyf;
import defpackage.scn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class CoreSleepDayDetailView extends BaseSleepDayDetailView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9969a;
    private OnClickViewDefaultListener b;
    private boolean c;
    private boolean d;
    private long e;
    private boolean f;
    private String g;
    private PointF h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private int n;
    private float o;

    public interface OnClickViewDefaultListener {
        void onClickViewDefaultListener(String str, String str2, int i, boolean z);
    }

    public CoreSleepDayDetailView(Context context) {
        this(context, null);
        setBaseContext(context);
    }

    public CoreSleepDayDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
        this.h = new PointF(-1.0f, -1.0f);
        this.e = 0L;
        setBaseContext(context);
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepDayDetailView.5
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                CoreSleepDayDetailView.this.c = true;
                return false;
            }
        });
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setViewWidth(getDefaultSize(getSuggestedMinimumWidth(), i));
        setViewHeight(getDefaultSize(getSuggestedMinimumHeight(), i2));
        setDiagramWidth(getViewWidth() - scn.b(80.0f));
        setDiagramHeight(scn.b(158.0f));
        setTransparentHeight(getViewHeight() - scn.b(24.0f));
        setCursorHeight(getTransparentHeight());
        setCursorCenter(getViewWidth() / 2.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        getTimeLabel();
        dwy_(canvas);
        drawSleepRect(canvas);
        c();
        if (getIsDefault()) {
            a();
        }
        dwz_(canvas);
        drawMaxSleepVerticalLine(canvas);
        dwx_(canvas);
        drawCursorText(canvas);
        dwA_(canvas);
    }

    private void dwy_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1.0f);
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297799_res_0x7f090607));
        canvas.drawRect(0.0f, getTransparentHeight(), getViewWidth(), getViewHeight(), paint);
    }

    private void c() {
        OnClickViewDefaultListener onClickViewDefaultListener;
        ArrayList<pyf> cursorList = getCursorList();
        int size = cursorList != null ? cursorList.size() : 0;
        if (cursorList == null || size == 0) {
            if (getIsMove() || (onClickViewDefaultListener = this.b) == null) {
                return;
            }
            onClickViewDefaultListener.onClickViewDefaultListener("", "", 0, getIsOnlyNoonSleepType());
            return;
        }
        pyf pyfVar = cursorList.get(size - 1);
        String str = pyfVar.j;
        this.g = str;
        OnClickViewDefaultListener onClickViewDefaultListener2 = this.b;
        if (onClickViewDefaultListener2 != null) {
            onClickViewDefaultListener2.onClickViewDefaultListener(str, d(pyfVar.d), (int) pyfVar.f16342a, getIsOnlyNoonSleepType());
        }
    }

    private void a() {
        float c;
        float chartBorderWidth;
        if (getLastData() != null) {
            if (getIsOnlyNoonSleepType()) {
                c = (getLastData().c() / 1440.0f) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            } else if (getIsScienceSleep()) {
                c = ((getLastData().c() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            } else {
                c = (getLastData().c() / 1440.0f) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            }
            float f = c + chartBorderWidth;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                f = getViewWidth() - f;
            }
            if (!getIsMove()) {
                setCursorCenter(f + 1.0f);
            }
            OnClickViewDefaultListener onClickViewDefaultListener = this.b;
            if (onClickViewDefaultListener != null) {
                onClickViewDefaultListener.onClickViewDefaultListener("", "", -1, getIsOnlyNoonSleepType());
                return;
            }
            return;
        }
        setCursorCenter(getViewWidth() / 2.0f);
    }

    private void dwz_(Canvas canvas) {
        float b;
        if (getFitnessDataList() == null || getFitnessDataList().size() == 0) {
            return;
        }
        this.h.set(getCursorCenter(), getCursorHeight());
        e(this.h.x);
        if (this.f9969a && !this.d) {
            Paint paint = new Paint();
            paint.setStrokeWidth(1.0f);
            paint.setColor(this.n);
            RectF rectF = new RectF(this.l, this.o, this.k, getTransparentHeight());
            if (Math.abs(this.k - this.l) < scn.b(6.0f)) {
                b = Math.abs(this.k - this.l) / 2.0f;
            } else {
                b = scn.b(6.0f);
            }
            float[] fArr = {b, b, b, b, 0.0f, 0.0f, 0.0f, 0.0f};
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            canvas.drawPath(path, paint);
        }
    }

    private void dwx_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297799_res_0x7f090607));
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        float b = scn.b(36.0f);
        float b2 = scn.b(13.5f);
        path.moveTo(getCursorCenter() - b, getCursorHeight());
        float cursorCenter = getCursorCenter();
        float f = (2.0f * b) / 6.0f;
        float cursorHeight = getCursorHeight();
        float cursorCenter2 = getCursorCenter();
        path.cubicTo(cursorCenter - f, cursorHeight, cursorCenter2 - f, getCursorHeight() - b2, getCursorCenter(), getCursorHeight() - b2);
        float cursorCenter3 = getCursorCenter();
        float cursorHeight2 = getCursorHeight();
        float cursorCenter4 = getCursorCenter();
        float f2 = cursorCenter3 + f;
        float f3 = cursorHeight2 - b2;
        float f4 = ((b * 3.0f) / 6.0f) + cursorCenter4;
        path.cubicTo(f2, f3, f4, getCursorHeight(), getCursorCenter() + b, getCursorHeight());
        canvas.drawPath(path, paint);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(scn.b(7.0f), 0.0f, scn.b(3.0f), getBaseContext().getResources().getColor(R.color._2131297802_res_0x7f09060a));
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131296657_res_0x7f090191));
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + scn.b(1.0f), scn.b(11.0f), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setAlpha(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + scn.b(1.0f), scn.b(11.0f), paint);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297801_res_0x7f090609));
        paint.setAntiAlias(true);
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + scn.b(1.0f), scn.b(11.0f), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(scn.b(0.5f));
        paint.setColor(getBaseContext().getResources().getColor(R.color._2131297802_res_0x7f09060a));
        paint.setAntiAlias(true);
        canvas.drawCircle(getCursorCenter(), getCursorHeight() + scn.b(1.0f), scn.b(11.25f), paint);
    }

    private void dwA_(Canvas canvas) {
        if (getFitnessDataList() == null || getFitnessDataList().size() == 0) {
            return;
        }
        Paint paint = new Paint();
        int[] iArr = {getBaseContext().getResources().getColor(R.color._2131296808_res_0x7f090228), getBaseContext().getResources().getColor(R.color._2131296807_res_0x7f090227), getBaseContext().getResources().getColor(R.color._2131296808_res_0x7f090228)};
        if (getColorType() == 1) {
            paint.setAlpha(10);
        }
        paint.setShader(new LinearGradient(getCursorCenter(), 0.0f, getCursorCenter(), getCursorHeight() - 60.0f, iArr, (float[]) null, Shader.TileMode.MIRROR));
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        canvas.drawLine(getCursorCenter(), scn.b(16.0f), getCursorCenter(), getCursorHeight() - scn.b(16.0f), paint);
    }

    private void e(float f) {
        if (getFitnessDataList() == null || getFitnessDataList().size() == 0) {
            getCursorList().clear();
            LogUtil.b("R_Sleep_CoreSleepDayDetailView", "addAnchor getFitnessDataList() == null or getFitnessDataList().size() == 0");
            return;
        }
        for (pvz pvzVar : getFitnessDataList()) {
            int e = pvzVar.e();
            int c = pvzVar.c();
            if (getIsOnlyNoonSleepType()) {
                c(pvzVar, e, c);
            } else if (getIsScienceSleep()) {
                if (pvzVar.b() == 69) {
                    this.d = true;
                } else {
                    c(pvzVar);
                }
            } else if (pvzVar.b() == 69) {
                this.d = true;
            } else {
                b(pvzVar, e, c);
            }
            this.m = getOnlyNoonRectSetting(pvzVar.b(), getDiagramHeight()).d();
            if (f >= this.i && f <= this.j) {
                e(pvzVar, e, c);
                return;
            } else {
                this.f9969a = false;
                getCursorList().clear();
            }
        }
    }

    private void e(pvz pvzVar, float f, float f2) {
        this.f9969a = true;
        pyf pyfVar = new pyf();
        pyfVar.c = 1;
        pyfVar.b = new PointF(f, this.m);
        int c = pvzVar.c();
        int e = pvzVar.e();
        pyfVar.j = e(pvzVar.e()) + " - " + e(pvzVar.c());
        this.g = e(pvzVar.e()) + " - " + e(pvzVar.c());
        pyfVar.f16342a = (double) (c - e);
        pyfVar.e = new RectF(f, f2, 100.0f + f, getViewHeight());
        pyfVar.d = pvzVar.b();
        getCursorList().clear();
        getCursorList().add(pyfVar);
    }

    private void c(pvz pvzVar, int i, int i2) {
        this.d = false;
        this.i = ((i / 1440.0f) * getDiagramWidth()) + getChartBorderWidth();
        this.j = ((i2 / 1440.0f) * getDiagramWidth()) + getChartBorderWidth();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f = this.i;
            this.i = getViewWidth() - this.j;
            this.j = getViewWidth() - f;
        }
        this.l = this.i;
        this.k = this.j;
        a(pvzVar.b());
    }

    private void c(pvz pvzVar) {
        this.d = false;
        this.i = (((pvzVar.e() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth()) + getChartBorderWidth();
        this.j = (((pvzVar.c() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth()) + getChartBorderWidth();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f = this.i;
            this.i = getViewWidth() - this.j;
            this.j = getViewWidth() - f;
        }
        this.l = this.i;
        this.k = this.j;
        b(pvzVar.b());
    }

    private void b(pvz pvzVar, int i, int i2) {
        this.d = false;
        this.i = ((i / 1440.0f) * getDiagramWidth()) + getChartBorderWidth();
        this.j = ((i2 / 1440.0f) * getDiagramWidth()) + getChartBorderWidth();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f = this.i;
            this.i = getViewWidth() - this.j;
            this.j = getViewWidth() - f;
        }
        this.l = this.i;
        this.k = this.j;
        c(pvzVar.b());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        setIsDefault(false);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.e = System.currentTimeMillis();
        } else if (action == 1) {
            dwD_(motionEvent);
        } else if (action == 2) {
            dwB_(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    private void dwB_(MotionEvent motionEvent) {
        if (getCursorHeight() - scn.b(18.0f) < motionEvent.getY() && motionEvent.getY() < getViewHeight()) {
            dwE_(motionEvent);
        } else if (this.c) {
            dwE_(motionEvent);
        } else {
            this.f = true;
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }

    private void dwE_(MotionEvent motionEvent) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            if (motionEvent.getX() < scn.b(22.0f) || motionEvent.getX() > getViewWidth() - getChartBorderWidth()) {
                return;
            }
            dwC_(motionEvent);
            return;
        }
        if (getChartBorderWidth() > motionEvent.getX() || motionEvent.getX() > getViewWidth() - scn.b(22.0f)) {
            return;
        }
        dwC_(motionEvent);
    }

    private void dwD_(MotionEvent motionEvent) {
        this.c = false;
        if (System.currentTimeMillis() - this.e < 850) {
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                if (scn.b(22.0f) <= motionEvent.getX() && motionEvent.getX() <= getViewWidth() - getChartBorderWidth()) {
                    e(motionEvent.getX());
                    dwC_(motionEvent);
                }
            } else if (getChartBorderWidth() <= motionEvent.getX() && motionEvent.getX() <= getViewWidth() - scn.b(22.0f)) {
                e(motionEvent.getX());
                dwC_(motionEvent);
            }
        }
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    private void dwC_(MotionEvent motionEvent) {
        float c;
        float chartBorderWidth;
        this.f = false;
        this.h.set(motionEvent.getX(), motionEvent.getY());
        e(this.h.x);
        if (getLastData() != null) {
            if (getIsOnlyNoonSleepType()) {
                c = (getLastData().c() / 1440.0f) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            } else if (getIsScienceSleep()) {
                c = ((getLastData().c() - getNightStartPoint()) / (getNightEndPoint() - getNightStartPoint())) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            } else {
                c = (getLastData().c() / 1440.0f) * getDiagramWidth();
                chartBorderWidth = getChartBorderWidth();
            }
            float f = c + chartBorderWidth;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                if (motionEvent.getX() >= getViewWidth() - f && motionEvent.getX() <= getViewWidth() - getChartBorderWidth()) {
                    setIsDefault(false);
                    setIsMove(false);
                } else {
                    setIsDefault(true);
                    setIsMove(true);
                }
            } else if (motionEvent.getX() >= f && motionEvent.getX() <= getViewWidth() - scn.b(22.0f)) {
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

    private String d(int i) {
        switch (i) {
            case 65:
                return getBaseContext().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep);
            case 66:
                return getBaseContext().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep);
            case 67:
                return getBaseContext().getString(R$string.IDS_details_sleep_sleep_latency);
            case 68:
                return getBaseContext().getString(R$string.IDS_fitness_core_sleep_rem_sleep);
            case 69:
                return getBaseContext().getString(R$string.IDS_fitness_core_sleep_noontime_sleep);
            default:
                return getBaseContext().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep);
        }
    }

    private String e(int i) {
        int i2 = i / 60;
        int i3 = i2 + 20;
        if (i3 >= 24) {
            i3 = i2 - 4;
        }
        return formatTime(getTimeStr(i3) + ":" + getTimeStr(i % 60));
    }

    public void setonClickViewDefaultListener(OnClickViewDefaultListener onClickViewDefaultListener) {
        this.b = onClickViewDefaultListener;
    }

    private void b(int i) {
        setIsOtherType(false);
        switch (i) {
            case 65:
                this.o = scn.b(6.8f) + (getDiagramHeight() * 0.75f);
                if (getColorType() == 1) {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296713_res_0x7f0901c9);
                    break;
                } else {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296763_res_0x7f0901fb);
                    break;
                }
            case 66:
                this.o = scn.b(6.8f) + (getDiagramHeight() * 0.5f);
                if (getColorType() == 1) {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296719_res_0x7f0901cf);
                    break;
                } else {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296765_res_0x7f0901fd);
                    break;
                }
            case 67:
                this.o = scn.b(6.8f);
                if (getColorType() == 1) {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296732_res_0x7f0901dc);
                    break;
                } else {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296767_res_0x7f0901ff);
                    break;
                }
            case 68:
                this.o = scn.b(6.8f) + (getDiagramHeight() * 0.25f);
                if (getColorType() == 1) {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296727_res_0x7f0901d7);
                    break;
                } else {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296766_res_0x7f0901fe);
                    break;
                }
            default:
                setIsOtherType(true);
                break;
        }
    }

    private void c(int i) {
        setIsOtherType(false);
        switch (i) {
            case 65:
                this.o = scn.b(20.0f) + (getDiagramHeight() * 0.66f);
                if (getColorType() == 1) {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296713_res_0x7f0901c9);
                    break;
                } else {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296763_res_0x7f0901fb);
                    break;
                }
            case 66:
                this.o = scn.b(20.0f) + (getDiagramHeight() * 0.33f);
                if (getColorType() == 1) {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296719_res_0x7f0901cf);
                    break;
                } else {
                    this.n = getBaseContext().getResources().getColor(R.color._2131296765_res_0x7f0901fd);
                    break;
                }
            case 67:
                e();
                break;
            default:
                setIsOtherType(true);
                break;
        }
    }

    private void e() {
        this.o = scn.b(20.0f);
        if (getColorType() == 1) {
            this.n = getBaseContext().getResources().getColor(R.color._2131296732_res_0x7f0901dc);
        } else {
            this.n = getBaseContext().getResources().getColor(R.color._2131296767_res_0x7f0901ff);
        }
    }

    private void a(int i) {
        setIsOtherType(false);
        if (i == 67) {
            e();
            return;
        }
        if (i == 69) {
            this.o = scn.b(20.0f) + ((getDiagramHeight() - scn.b(20.0f)) * 0.38f);
            if (getColorType() == 1) {
                this.n = getBaseContext().getResources().getColor(R.color._2131296711_res_0x7f0901c7);
                return;
            } else {
                this.n = getBaseContext().getResources().getColor(R.color._2131296760_res_0x7f0901f8);
                return;
            }
        }
        setIsOtherType(true);
    }
}
