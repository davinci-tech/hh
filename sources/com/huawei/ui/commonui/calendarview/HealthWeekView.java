package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import defpackage.nkw;

/* loaded from: classes9.dex */
public abstract class HealthWeekView extends HealthBaseWeekView {
    protected abstract void onDrawMark(Canvas canvas, HealthCalendar healthCalendar, int i);

    protected abstract boolean onDrawSelected(Canvas canvas, HealthCalendar healthCalendar, int i, boolean z);

    protected abstract void onDrawText(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, boolean z);

    public HealthWeekView(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mItems.size() == 0) {
            return;
        }
        updateItemsPresentDay();
        int i = 0;
        while (i < this.mItems.size()) {
            HealthCalendar healthCalendar = this.mItems.get(i);
            int i2 = getLayoutDirection() == 1 ? 6 - i : i;
            boolean z = i == this.mCurrentItem && this.mManager.ae();
            int a2 = (i2 * (this.mItemWidth + this.mHorizontalSpacing)) + this.mManager.a();
            boolean hasMark = healthCalendar.hasMark();
            if (hasMark) {
                onDrawMark(canvas, healthCalendar, a2);
            }
            if (z) {
                onDrawSelected(canvas, healthCalendar, a2, hasMark);
            }
            onDrawText(canvas, healthCalendar, i, a2, z);
            i++;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.mIsClick) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HealthCalendar index = getIndex();
        if (index == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if ((!this.mManager.ao() || !this.mManager.al()) && nkw.j(index, this.mManager)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!isInRange(index) || (isInRangeOfGray(index) && !this.mManager.ak())) {
            if (this.mManager.d != null) {
                this.mManager.d.onCalendarOutOfRange(index);
            }
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.mCurrentItem = this.mItems.indexOf(index);
        if (this.mManager.c != null) {
            this.mManager.c.onWeekDateSelected(index, true);
        }
        if (this.mParentLayout != null) {
            this.mParentLayout.e(nkw.a(index, this.mManager.z()));
        }
        if (this.mManager.d != null) {
            this.mManager.d.onCalendarSelect(index, true);
        }
        invalidate();
        ViewClickInstrumentation.clickOnView(view);
    }
}
