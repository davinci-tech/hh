package com.huawei.ui.commonui.linechart.common.touch;

import android.content.Context;
import android.view.MotionEvent;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import defpackage.nng;
import defpackage.noc;
import defpackage.nof;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class TouchModeDelegateMgr {

    /* renamed from: a, reason: collision with root package name */
    public static final int f8883a = (int) Utils.convertDpToPixel(3.0f);
    private Context b;
    private TouchModeDelegate c;
    private HwHealthBaseBarLineChart d;
    private TouchModeDelegate e;
    private ViewPortHandler f;
    private HwHealthBarLineChartTouchListener g;
    private TouchModeDelegate j;

    enum Area {
        CONTENT,
        MARK_VIEW,
        OTHER
    }

    public interface TouchModeDelegate {
        TouchModeDelegate monitor(MotionEvent motionEvent);
    }

    public TouchModeDelegateMgr(HwHealthBarLineChartTouchListener hwHealthBarLineChartTouchListener, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler) {
        this.e = null;
        this.c = null;
        this.j = null;
        this.d = null;
        this.g = null;
        this.f = null;
        this.b = hwHealthBaseBarLineChart.getContext();
        this.g = hwHealthBarLineChartTouchListener;
        this.d = hwHealthBaseBarLineChart;
        this.f = viewPortHandler;
        this.e = new e();
        this.c = new d();
        this.j = new b();
    }

    public TouchModeDelegate e() {
        return this.e;
    }

    class e implements TouchModeDelegate {
        private e() {
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.TouchModeDelegateMgr.TouchModeDelegate
        public TouchModeDelegate monitor(MotionEvent motionEvent) {
            if (motionEvent == null) {
                return null;
            }
            return (motionEvent.getAction() & 255) != 0 ? this : cCn_(motionEvent);
        }

        private TouchModeDelegate cCn_(MotionEvent motionEvent) {
            Area cCm_ = cCm_(motionEvent);
            if (cCm_ == Area.CONTENT) {
                return TouchModeDelegateMgr.this.c.monitor(motionEvent);
            }
            return cCm_ == Area.MARK_VIEW ? TouchModeDelegateMgr.this.j.monitor(motionEvent) : TouchModeDelegateMgr.this.e;
        }

        private Area cCm_(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (!TouchModeDelegateMgr.this.b(x) || !TouchModeDelegateMgr.this.d(y)) {
                if (TouchModeDelegateMgr.this.d.getViewPortHandler().isInBounds(x, TouchModeDelegateMgr.this.a(y))) {
                    return Area.CONTENT;
                }
                return Area.OTHER;
            }
            return Area.MARK_VIEW;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float a(float f) {
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.d;
        return ((hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) && ((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart).acquireChartShowMode() == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_SCALE_MODE && f > this.d.getViewPortHandler().contentBottom()) ? f - Utils.convertDpToPixel(15.0f) : f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(float f) {
        return f >= ((float) this.d.getLeft()) && f <= ((float) this.d.getRight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(float f) {
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.d;
        return ((hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) && ((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart).acquireChartShowMode() == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_SCALE_MODE) ? f >= this.d.getViewPortHandler().contentBottom() + Utils.convertDpToPixel(15.0f) && f <= ((float) this.d.getBottom()) + Utils.convertDpToPixel(45.0f) : f >= this.d.getViewPortHandler().contentBottom() - Utils.convertDpToPixel(15.0f) && f <= ((float) this.d.getBottom()) + Utils.convertDpToPixel(45.0f);
    }

    class d implements TouchModeDelegate {
        private OperationSequence d = null;
        private List<OperationSequence> e;

        d() {
            ArrayList arrayList = new ArrayList(2);
            this.e = arrayList;
            arrayList.add(new noc(TouchModeDelegateMgr.this.b, TouchModeDelegateMgr.this.d, TouchModeDelegateMgr.this.f, TouchModeDelegateMgr.this.g));
            this.e.add(new nof(TouchModeDelegateMgr.this.b, TouchModeDelegateMgr.this.d, TouchModeDelegateMgr.this.f, TouchModeDelegateMgr.this.g));
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.TouchModeDelegateMgr.TouchModeDelegate
        public TouchModeDelegate monitor(MotionEvent motionEvent) {
            if (motionEvent == null) {
                return null;
            }
            int action = motionEvent.getAction() & 255;
            if (action == 0 || action == 5) {
                Iterator<OperationSequence> it = this.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    OperationSequence next = it.next();
                    if (next.canSeqStart(motionEvent)) {
                        OperationSequence operationSequence = this.d;
                        if (operationSequence != null) {
                            operationSequence.release();
                        }
                        next.prepare();
                        this.d = next;
                    }
                }
            }
            OperationSequence operationSequence2 = this.d;
            if (operationSequence2 == null) {
                throw new RuntimeException("no op seq defined,logic error!!!");
            }
            operationSequence2.onTouchEvent(motionEvent);
            return this.d.isSeqInterrupted() ? TouchModeDelegateMgr.this.e : this;
        }
    }

    class b implements TouchModeDelegate {

        /* renamed from: a, reason: collision with root package name */
        private GlobalActionMonitor f8884a;
        private boolean e = false;
        private MPPointF c = MPPointF.getInstance(0.0f, 0.0f);
        private boolean b = false;

        b() {
            this.f8884a = null;
            this.f8884a = new GlobalActionMonitor(TouchModeDelegateMgr.this.d);
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.TouchModeDelegateMgr.TouchModeDelegate
        public TouchModeDelegate monitor(MotionEvent motionEvent) {
            TouchModeDelegate touchModeDelegate = null;
            if (motionEvent == null) {
                return null;
            }
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                this.b = false;
                if (!this.f8884a.cCg_(motionEvent)) {
                    touchModeDelegate = cCq_(motionEvent);
                }
            } else {
                if (action != 1) {
                    if (action == 2) {
                        touchModeDelegate = !this.f8884a.cCh_(motionEvent) ? cCr_(motionEvent) : this;
                        if (this.b) {
                            this.f8884a.b();
                        }
                    } else if (action != 3) {
                        touchModeDelegate = TouchModeDelegateMgr.this.e;
                    }
                }
                touchModeDelegate = !this.f8884a.cCf_(motionEvent) ? cCp_(motionEvent) : TouchModeDelegateMgr.this.e;
            }
            if (touchModeDelegate == TouchModeDelegateMgr.this.e) {
                this.e = false;
                if (TouchModeDelegateMgr.this.d instanceof HwHealthBaseScrollBarLineChart) {
                    ((HwHealthBaseScrollBarLineChart) TouchModeDelegateMgr.this.d).adsorbMarkerViewToSelectedDataByDataArea();
                }
            }
            return touchModeDelegate;
        }

        private TouchModeDelegate cCr_(MotionEvent motionEvent) {
            if (!this.b) {
                float abs = Math.abs(motionEvent.getX() - this.c.getX());
                float abs2 = Math.abs(motionEvent.getY() - this.c.getY());
                if (abs > TouchModeDelegateMgr.f8883a && abs2 > TouchModeDelegateMgr.f8883a) {
                    this.b = true;
                    if (abs < abs2 * 2.0f) {
                        return TouchModeDelegateMgr.this.e;
                    }
                }
            }
            if (!this.e) {
                return this;
            }
            cCt_(motionEvent);
            return this;
        }

        private TouchModeDelegate cCq_(MotionEvent motionEvent) {
            this.e = cCs_(motionEvent);
            cCu_(motionEvent);
            return this;
        }

        private void cCu_(MotionEvent motionEvent) {
            this.c.x = motionEvent.getX();
            this.c.y = motionEvent.getY();
        }

        private TouchModeDelegate cCp_(MotionEvent motionEvent) {
            return TouchModeDelegateMgr.this.e;
        }

        private void cCt_(MotionEvent motionEvent) {
            TouchModeDelegateMgr.this.d.highlightValuePx(cCo_(motionEvent), false);
            TouchModeDelegateMgr.this.d.invalidateForce();
        }

        private boolean cCs_(MotionEvent motionEvent) {
            return Math.abs(motionEvent.getX() - TouchModeDelegateMgr.this.d.fetchMarkViewXValPx()) < Utils.convertDpToPixel(40.0f);
        }

        private float cCo_(MotionEvent motionEvent) {
            if (TouchModeDelegateMgr.this.d.getGlobalPointFlag()) {
                if (!nng.d(TouchModeDelegateMgr.this.b)) {
                    float x = motionEvent.getX();
                    float j = TouchModeDelegateMgr.this.d.acquireChartAnchor().j();
                    HwHealthBaseBarLineChart unused = TouchModeDelegateMgr.this.d;
                    if (x > j - Utils.convertDpToPixel(26.0f)) {
                        float j2 = TouchModeDelegateMgr.this.d.acquireChartAnchor().j();
                        HwHealthBaseBarLineChart unused2 = TouchModeDelegateMgr.this.d;
                        return j2 - Utils.convertDpToPixel(26.0f);
                    }
                }
                if (nng.d(TouchModeDelegateMgr.this.b)) {
                    float x2 = motionEvent.getX();
                    float f = TouchModeDelegateMgr.this.d.acquireChartAnchor().f();
                    HwHealthBaseBarLineChart unused3 = TouchModeDelegateMgr.this.d;
                    if (x2 < f + Utils.convertDpToPixel(26.0f)) {
                        float f2 = TouchModeDelegateMgr.this.d.acquireChartAnchor().f();
                        HwHealthBaseBarLineChart unused4 = TouchModeDelegateMgr.this.d;
                        return f2 + Utils.convertDpToPixel(26.0f);
                    }
                }
            }
            if (motionEvent.getX() > TouchModeDelegateMgr.this.d.getViewPortHandler().contentRight()) {
                return TouchModeDelegateMgr.this.d.getViewPortHandler().contentRight();
            }
            if (motionEvent.getX() < TouchModeDelegateMgr.this.d.getViewPortHandler().contentLeft()) {
                return TouchModeDelegateMgr.this.d.getViewPortHandler().contentLeft();
            }
            return motionEvent.getX();
        }
    }
}
