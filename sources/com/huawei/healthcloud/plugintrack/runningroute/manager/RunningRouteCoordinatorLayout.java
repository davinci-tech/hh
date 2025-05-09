package com.huawei.healthcloud.plugintrack.runningroute.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;

/* loaded from: classes4.dex */
public class RunningRouteCoordinatorLayout extends CoordinatorLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f3553a;
    private boolean d;
    private HealthBottomSheet e;

    public RunningRouteCoordinatorLayout(Context context) {
        super(context);
        this.d = true;
    }

    public RunningRouteCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = true;
    }

    public RunningRouteCoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        HealthBottomSheet healthBottomSheet;
        HealthBottomSheet healthBottomSheet2;
        int action = motionEvent.getAction();
        if (action == 0) {
            HealthBottomSheet healthBottomSheet3 = this.e;
            if ((healthBottomSheet3 != null && healthBottomSheet3.getSheetState() == HwBottomSheet.SheetState.EXPANDED) || ((healthBottomSheet = this.e) != null && healthBottomSheet.getSheetState() == HwBottomSheet.SheetState.ANCHORED)) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            this.f3553a = (int) motionEvent.getY();
        } else if (action == 2) {
            HealthBottomSheet healthBottomSheet4 = this.e;
            if (healthBottomSheet4 != null && healthBottomSheet4.getSheetState() == HwBottomSheet.SheetState.COLLAPSED) {
                return false;
            }
            HealthBottomSheet healthBottomSheet5 = this.e;
            if ((healthBottomSheet5 != null && healthBottomSheet5.getSheetState() == HwBottomSheet.SheetState.EXPANDED && this.d && motionEvent.getY() - this.f3553a > 0.0f) || ((healthBottomSheet2 = this.e) != null && healthBottomSheet2.getSheetState() == HwBottomSheet.SheetState.ANCHORED && motionEvent.getY() - this.f3553a < 0.0f)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (action == 3) {
            return false;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setIsOnTop(HealthBottomSheet healthBottomSheet, boolean z) {
        this.e = healthBottomSheet;
        this.d = z;
    }

    public boolean getIsOnTop() {
        return this.d;
    }
}
