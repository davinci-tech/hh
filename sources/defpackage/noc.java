package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.touch.GlobalActionMonitor;
import com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener;
import com.huawei.ui.commonui.linechart.common.touch.OperationSequence;
import com.huawei.ui.commonui.linechart.common.touch.TouchModeDelegateMgr;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class noc implements OperationSequence {

    /* renamed from: a, reason: collision with root package name */
    private Context f15407a;
    private long b;
    private GlobalActionMonitor c;
    private int d;
    private HwHealthBaseBarLineChart e;
    private HwHealthBarLineChartTouchListener h;
    private int k;
    private Matrix l;
    private int n;
    private float o;
    private ViewPortHandler p;
    private VelocityTracker t;
    private boolean m = false;
    private MPPointF s = MPPointF.getInstance(0.0f, 0.0f);
    private boolean j = false;
    private Matrix r = new Matrix();
    private AtomicBoolean g = new AtomicBoolean(false);
    private boolean i = false;
    private boolean f = false;

    public noc(Context context, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler, HwHealthBarLineChartTouchListener hwHealthBarLineChartTouchListener) {
        this.e = null;
        this.h = null;
        this.p = null;
        this.l = null;
        this.c = null;
        this.f15407a = context;
        this.e = hwHealthBaseBarLineChart;
        this.p = viewPortHandler;
        this.h = hwHealthBarLineChartTouchListener;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.k = 6500;
        this.n = viewConfiguration.getScaledMaximumFlingVelocity();
        this.l = viewPortHandler.getMatrixTouch();
        this.c = new GlobalActionMonitor(this.e);
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public boolean canSeqStart(MotionEvent motionEvent) {
        return motionEvent != null && (motionEvent.getAction() & 255) == 0;
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public boolean isSeqInterrupted() {
        return this.m;
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public void prepare() {
        this.m = false;
        this.j = false;
        if (this.t == null) {
            this.t = VelocityTracker.obtain();
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public void release() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.t = null;
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public void onTouchEvent(MotionEvent motionEvent) {
        if (this.g.get() || motionEvent == null) {
            return;
        }
        this.t.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.j = false;
            this.i = false;
            this.f = false;
            if (this.c.cCg_(motionEvent)) {
                return;
            }
            cBY_(motionEvent);
            return;
        }
        if (action != 1) {
            if (action == 2) {
                LogUtil.c("HealthChart_SinglePointer", "perform Move :", Float.valueOf(Math.abs(motionEvent.getX() - this.s.getX())));
                if (!this.c.cCh_(motionEvent)) {
                    cCa_(motionEvent);
                }
                if (this.j || this.f) {
                    this.c.b();
                    return;
                }
                return;
            }
            if (action != 3) {
                c();
                return;
            }
        }
        if (this.c.cCf_(motionEvent)) {
            return;
        }
        cBX_(motionEvent);
    }

    private void c() {
        this.m = true;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
    }

    private void cBX_(MotionEvent motionEvent) {
        if (this.h.f8882a != HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE) {
            c();
            return;
        }
        if (cBW_(motionEvent, this.t)) {
            float x = motionEvent.getX() - this.s.x;
            boolean z = (x < 0.0f && !nng.d(this.f15407a)) || (x > 0.0f && nng.d(this.f15407a));
            float abs = Math.abs(this.t.getXVelocity());
            float f = this.k;
            if (abs < f) {
                abs = f;
            }
            if (cBZ_(motionEvent, abs, z)) {
                c();
                return;
            }
        }
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.e;
        if (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) {
            HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = (HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart;
            if (hwHealthBaseScrollBarLineChart.acquireChartShowMode() == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE) {
                Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
                hwHealthBaseScrollBarLineChart.correctViewPosition(new HwHealthBaseScrollBarLineChart.e(hwHealthBaseScrollBarLineChart, hwHealthBaseScrollBarLineChart) { // from class: noc.1
                    final /* synthetic */ HwHealthBaseScrollBarLineChart e;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                        this.e = hwHealthBaseScrollBarLineChart;
                        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
                    }

                    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
                    public void d() {
                        super.d();
                        this.e.animateBorderYAuto(2);
                        ((HwHealthBaseScrollBarLineChart) noc.this.e).adsorbMarkerViewToSelectedDataByDataArea();
                    }
                });
            }
        }
        c();
    }

    private boolean cBW_(MotionEvent motionEvent, VelocityTracker velocityTracker) {
        this.t.computeCurrentVelocity(1000, this.n);
        float xVelocity = this.t.getXVelocity();
        if (Math.abs(this.p.contentWidth()) < 1.0E-6d) {
            LogUtil.h("HealthChart_SinglePointer", "Math.abs(mViewPortHandler.contentWidth()) < 1e-6");
            return false;
        }
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.e;
        return (!(hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) || ((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart).querySupportTouchScroll()) ? Math.abs(xVelocity) > ((float) this.k) && Math.abs(motionEvent.getX() - this.s.getX()) / this.p.contentWidth() > 0.16666667f && Math.abs(this.b - SystemClock.elapsedRealtime()) < 600 : Math.abs(motionEvent.getX() - this.s.getX()) / this.p.contentWidth() > 0.16666667f;
    }

    private void cBY_(MotionEvent motionEvent) {
        cCc_(motionEvent);
        this.d = this.e.getMonthDaysOfMinTime();
    }

    private void cCa_(MotionEvent motionEvent) {
        if (!this.j) {
            float abs = Math.abs(motionEvent.getX() - this.s.getX());
            float abs2 = Math.abs(motionEvent.getY() - this.s.getY());
            if (abs > TouchModeDelegateMgr.f8883a && abs2 > TouchModeDelegateMgr.f8883a) {
                if (abs < abs2 * 2.0f) {
                    this.j = true;
                    d();
                    c();
                    return;
                }
                this.j = true;
            }
        }
        boolean z = this.h.f8882a == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE && ((HwHealthBaseScrollBarLineChart) this.e).querySupportTouchScroll();
        HwHealthBaseScrollBarLineChart.ChartShowMode chartShowMode = this.h.f8882a;
        if (chartShowMode == HwHealthBaseScrollBarLineChart.ChartShowMode.NORMAL || chartShowMode == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_SCALE_MODE || z) {
            cCb_(motionEvent);
        }
    }

    private void d() {
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.e;
        if (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) {
            HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = (HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart;
            if (hwHealthBaseScrollBarLineChart.acquireChartShowMode() == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE) {
                Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
                hwHealthBaseScrollBarLineChart.correctViewPosition(new HwHealthBaseScrollBarLineChart.e(hwHealthBaseScrollBarLineChart, hwHealthBaseScrollBarLineChart) { // from class: noc.2
                    final /* synthetic */ HwHealthBaseScrollBarLineChart d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                        this.d = hwHealthBaseScrollBarLineChart;
                        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
                    }

                    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
                    public void d() {
                        super.d();
                        this.d.animateBorderYAuto(1);
                        ((HwHealthBaseScrollBarLineChart) noc.this.e).adsorbMarkerViewToSelectedDataByDataArea();
                    }
                });
            }
        }
    }

    private boolean cBZ_(MotionEvent motionEvent, float f, boolean z) {
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.e;
        if (!(hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart)) {
            return false;
        }
        HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = (HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart;
        if (!hwHealthBaseScrollBarLineChart.isSupportFling()) {
            return false;
        }
        if (z) {
            if (!hwHealthBaseScrollBarLineChart.canScrollNewerPager()) {
                return false;
            }
            this.g.set(true);
            Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
            HwHealthBaseScrollBarLineChart.e eVar = new HwHealthBaseScrollBarLineChart.e(hwHealthBaseScrollBarLineChart) { // from class: noc.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                    Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
                }

                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
                public void d() {
                    super.d();
                    noc.this.g.set(false);
                    ((HwHealthBaseScrollBarLineChart) noc.this.e).adsorbMarkerViewToSelectedDataByDataArea();
                }
            };
            HwHealthBaseScrollBarLineChart.AnimateValueTransfer animateValueTransfer = new HwHealthBaseScrollBarLineChart.AnimateValueTransfer() { // from class: noc.5
                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateValueTransfer
                public float transferValue(float f2) {
                    return (2.0f * f2) - (f2 * f2);
                }
            };
            Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
            hwHealthBaseScrollBarLineChart.scrollOnePageNewer(eVar, animateValueTransfer, new HwHealthBaseScrollBarLineChart.AbstractAnimateTimeComputer(hwHealthBaseScrollBarLineChart, f) { // from class: noc.4
                final /* synthetic */ float e;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                    this.e = f;
                    Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
                }

                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AbstractAnimateTimeComputer
                public int computeTimeByPixel(float f2) {
                    return (int) (((f2 * 2.0f) / this.e) * 1000.0f);
                }
            });
        } else {
            if (!hwHealthBaseScrollBarLineChart.canScrollOlderPager()) {
                return false;
            }
            d(hwHealthBaseScrollBarLineChart, f);
        }
        return true;
    }

    private void cCb_(MotionEvent motionEvent) {
        float abs = Math.abs(motionEvent.getX() - this.s.x);
        float abs2 = Math.abs(motionEvent.getY() - this.s.y);
        if (this.e.isDragXEnabled() && abs >= abs2) {
            this.i = true;
            if (abs > TouchModeDelegateMgr.f8883a || abs2 > TouchModeDelegateMgr.f8883a) {
                this.f = true;
            }
        }
        if (this.i) {
            float x = motionEvent.getX();
            float f = this.o;
            this.o = motionEvent.getX();
            b(x - f, 0.0f);
            this.l = this.e.getViewPortHandler().refresh(this.l, this.e, true);
        }
    }

    private void cCc_(MotionEvent motionEvent) {
        this.r.set(this.p.getMatrixTouch());
        this.s.x = motionEvent.getX();
        this.s.y = motionEvent.getY();
        this.b = SystemClock.elapsedRealtime();
        this.o = motionEvent.getX();
    }

    private void b(float f, float f2) {
        int i;
        int monthDaysOfMinTime = this.e.getMonthDaysOfMinTime();
        float f3 = (monthDaysOfMinTime == 0 || (i = this.d) == 0) ? 1.0f : i / monthDaysOfMinTime;
        this.d = monthDaysOfMinTime;
        if (Float.compare(f3, 1.0f) != 0) {
            this.l.postScale(f3, 1.0f, 0.0f, 0.0f);
        }
        this.l.postTranslate(f, f2);
    }

    private void d(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f) {
        this.g.set(true);
        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
        HwHealthBaseScrollBarLineChart.e eVar = new HwHealthBaseScrollBarLineChart.e(hwHealthBaseScrollBarLineChart) { // from class: noc.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
            public void d() {
                super.d();
                noc.this.g.set(false);
                ((HwHealthBaseScrollBarLineChart) noc.this.e).adsorbMarkerViewToSelectedDataByDataArea();
            }
        };
        HwHealthBaseScrollBarLineChart.AnimateValueTransfer animateValueTransfer = new HwHealthBaseScrollBarLineChart.AnimateValueTransfer() { // from class: noc.6
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateValueTransfer
            public float transferValue(float f2) {
                return (2.0f * f2) - (f2 * f2);
            }
        };
        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
        hwHealthBaseScrollBarLineChart.scrollOnePageOlder(eVar, animateValueTransfer, new HwHealthBaseScrollBarLineChart.AbstractAnimateTimeComputer(hwHealthBaseScrollBarLineChart, f) { // from class: noc.9
            final /* synthetic */ float e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.e = f;
                Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AbstractAnimateTimeComputer
            public int computeTimeByPixel(float f2) {
                return (int) (((f2 * 2.0f) / this.e) * 1000.0f);
            }
        });
    }
}
