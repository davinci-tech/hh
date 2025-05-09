package com.huawei.ui.commonui.linechart.common.touch;

import android.os.SystemClock;
import android.view.MotionEvent;
import com.github.mikephil.charting.utils.MPPointF;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class GlobalActionMonitor {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8881a;
    private HwHealthBaseBarLineChart c;
    private GlobalAction d;
    private List<GlobalAction> e;

    public GlobalActionMonitor(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        this.d = null;
        this.f8881a = false;
        this.c = hwHealthBaseBarLineChart;
        arrayList.add(new b());
        this.e.add(new e());
    }

    public boolean cCg_(MotionEvent motionEvent) {
        this.f8881a = false;
        this.d = null;
        for (GlobalAction globalAction : this.e) {
            if (globalAction != null) {
                globalAction.reset();
                globalAction.handleActionDown(motionEvent);
            }
        }
        return false;
    }

    public boolean cCh_(MotionEvent motionEvent) {
        if (this.f8881a) {
            return false;
        }
        GlobalAction globalAction = this.d;
        if (globalAction != null) {
            globalAction.handleActionMove(motionEvent);
            return this.d.inMode() && this.d.isModeInterceptMove(motionEvent);
        }
        Iterator<GlobalAction> it = this.e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            GlobalAction next = it.next();
            if (next != null) {
                next.handleActionMove(motionEvent);
                if (next.inMode()) {
                    this.d = next;
                    break;
                }
            }
        }
        GlobalAction globalAction2 = this.d;
        return globalAction2 != null && globalAction2.inMode() && this.d.isModeInterceptMove(motionEvent);
    }

    public boolean cCf_(MotionEvent motionEvent) {
        if (this.f8881a) {
            return false;
        }
        for (GlobalAction globalAction : this.e) {
            if (globalAction != null) {
                globalAction.handleActionCancel(motionEvent);
                globalAction.reset();
            }
        }
        this.d = null;
        return false;
    }

    public void b() {
        this.f8881a = true;
    }

    abstract class BaseGlobalAction implements GlobalAction {
        long mActionDownClockTime;
        private boolean mIsInMode;
        MPPointF mTouchStartPoint;

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean handleActionCancel(MotionEvent motionEvent) {
            return false;
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean handleActionMove(MotionEvent motionEvent) {
            return false;
        }

        private BaseGlobalAction() {
            this.mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);
            this.mIsInMode = false;
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean inMode() {
            return this.mIsInMode;
        }

        void enableMode() {
            this.mIsInMode = true;
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean handleActionDown(MotionEvent motionEvent) {
            saveTouchStart(motionEvent);
            return false;
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public void reset() {
            this.mIsInMode = false;
        }

        private void saveTouchStart(MotionEvent motionEvent) {
            if (motionEvent == null) {
                return;
            }
            this.mTouchStartPoint.x = motionEvent.getX();
            this.mTouchStartPoint.y = motionEvent.getY();
            this.mActionDownClockTime = SystemClock.elapsedRealtime();
        }
    }

    class b extends BaseGlobalAction {
        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean isModeInterceptMove(MotionEvent motionEvent) {
            return false;
        }

        private b() {
            super();
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalActionMonitor.BaseGlobalAction, com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean handleActionCancel(MotionEvent motionEvent) {
            super.handleActionCancel(motionEvent);
            float abs = Math.abs(motionEvent.getX() - this.mTouchStartPoint.getX());
            float abs2 = Math.abs(motionEvent.getY() - this.mTouchStartPoint.getY());
            LogUtil.c("HealthChart_GlobalActionMonitor", "SystemClock.elapsedRealtime() - mActionDownClockTime = " + (SystemClock.elapsedRealtime() - this.mActionDownClockTime));
            if (SystemClock.elapsedRealtime() - this.mActionDownClockTime >= 200 || abs >= TouchModeDelegateMgr.f8883a || abs2 >= TouchModeDelegateMgr.f8883a) {
                return false;
            }
            cCl_(motionEvent);
            enableMode();
            return false;
        }

        private void cCl_(MotionEvent motionEvent) {
            GlobalActionMonitor.this.c.highlightValuePxCorrectByUnix(cCk_(motionEvent), false);
            GlobalActionMonitor.this.c.invalidateForce();
        }

        private float cCk_(MotionEvent motionEvent) {
            return GlobalActionMonitor.this.cCe_(motionEvent);
        }
    }

    class e extends BaseGlobalAction {
        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean isModeInterceptMove(MotionEvent motionEvent) {
            return true;
        }

        private e() {
            super();
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalActionMonitor.BaseGlobalAction, com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean handleActionMove(MotionEvent motionEvent) {
            super.handleActionMove(motionEvent);
            if (inMode()) {
                cCj_(motionEvent);
                return true;
            }
            float abs = Math.abs(motionEvent.getX() - this.mTouchStartPoint.getX());
            float abs2 = Math.abs(motionEvent.getY() - this.mTouchStartPoint.getY());
            if (SystemClock.elapsedRealtime() - this.mActionDownClockTime <= 250 || abs >= TouchModeDelegateMgr.f8883a || abs2 >= TouchModeDelegateMgr.f8883a) {
                return false;
            }
            cCj_(motionEvent);
            enableMode();
            return true;
        }

        @Override // com.huawei.ui.commonui.linechart.common.touch.GlobalActionMonitor.BaseGlobalAction, com.huawei.ui.commonui.linechart.common.touch.GlobalAction
        public boolean handleActionCancel(MotionEvent motionEvent) {
            super.handleActionCancel(motionEvent);
            if (!(GlobalActionMonitor.this.c instanceof HwHealthBaseScrollBarLineChart)) {
                return false;
            }
            ((HwHealthBaseScrollBarLineChart) GlobalActionMonitor.this.c).adsorbMarkerViewToSelectedDataByDataArea();
            return false;
        }

        private void cCj_(MotionEvent motionEvent) {
            GlobalActionMonitor.this.c.highlightValuePx(cCi_(motionEvent), false);
            GlobalActionMonitor.this.c.invalidateForce();
        }

        private float cCi_(MotionEvent motionEvent) {
            return GlobalActionMonitor.this.cCe_(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float cCe_(MotionEvent motionEvent) {
        if (motionEvent.getX() > this.c.getViewPortHandler().contentRight()) {
            return this.c.getViewPortHandler().contentRight();
        }
        if (motionEvent.getX() < this.c.getViewPortHandler().contentLeft()) {
            return this.c.getViewPortHandler().contentLeft();
        }
        return motionEvent.getX();
    }
}
