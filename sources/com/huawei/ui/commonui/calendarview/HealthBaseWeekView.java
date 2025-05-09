package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.view.View;
import defpackage.nkw;
import java.util.Iterator;

/* loaded from: classes6.dex */
public abstract class HealthBaseWeekView extends HealthCalendarBaseView {
    protected boolean mMarkerViewClickable;

    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarBaseView
    protected void onDestroy() {
    }

    public HealthBaseWeekView(Context context) {
        super(context);
        this.mMarkerViewClickable = false;
    }

    final void init(HealthCalendar healthCalendar) {
        this.mItems = nkw.a(healthCalendar, this.mManager, this.mManager.z());
        this.mManager.e(this.mItems);
        invalidate();
    }

    final void setSelectedCalendar(HealthCalendar healthCalendar) {
        if (healthCalendar.equals(this.mManager.l)) {
            this.mCurrentItem = this.mItems.indexOf(healthCalendar);
        }
    }

    final void performClickCalendar(HealthCalendar healthCalendar, boolean z) {
        if (this.mManager.c == null || this.mItems == null || this.mItems.size() == 0) {
            return;
        }
        int dayOfWeekOffset = healthCalendar.getDayOfWeekOffset(this.mManager.z());
        if (this.mItems.contains(this.mManager.q())) {
            dayOfWeekOffset = this.mManager.q().getDayOfWeekOffset(this.mManager.z());
        }
        HealthCalendar healthCalendar2 = this.mItems.get(dayOfWeekOffset);
        if (!isInRange(healthCalendar2)) {
            dayOfWeekOffset = getEdgeIndex(isMinRangeEdge(healthCalendar2));
            healthCalendar2 = this.mItems.get(dayOfWeekOffset);
        }
        healthCalendar2.setPresentDay(healthCalendar2.equals(this.mManager.q()));
        this.mManager.c.onWeekDateSelected(healthCalendar2, false);
        int a2 = nkw.a(healthCalendar2, this.mManager.z());
        if (this.mParentLayout != null) {
            this.mParentLayout.e(a2);
        }
        if (this.mManager.d != null && z) {
            this.mManager.d.onCalendarSelect(healthCalendar2, false);
        }
        if (this.mParentLayout != null) {
            this.mParentLayout.i();
        }
        this.mCurrentItem = dayOfWeekOffset;
        this.mManager.f15354a = healthCalendar2;
        invalidate();
    }

    final boolean isMinRangeEdge(HealthCalendar healthCalendar) {
        return healthCalendar.transformCalendar().getTimeInMillis() < this.mManager.r().transformCalendar().getTimeInMillis();
    }

    final int getEdgeIndex(boolean z) {
        for (int i = 0; i < this.mItems.size(); i++) {
            boolean isInRange = isInRange(this.mItems.get(i));
            if (z && isInRange) {
                return i;
            }
            if (!z && !isInRange) {
                return i - 1;
            }
        }
        return z ? 6 : 0;
    }

    protected HealthCalendar getIndex() {
        if (this.mX <= this.mManager.a() || this.mX >= getWidth() - this.mManager.b()) {
            return null;
        }
        int markerViewExtendY = ((((int) this.mY) / (this.mItemHeight + (this.mMarkerViewClickable ? getMarkerViewExtendY() : 0))) * 7) + calIndex();
        if (markerViewExtendY < 0 || markerViewExtendY >= this.mItems.size()) {
            return null;
        }
        return this.mItems.get(markerViewExtendY);
    }

    private int getMarkerViewExtendY() {
        return this.mManager.f + this.mManager.j;
    }

    final void updateDisplayMode() {
        invalidate();
    }

    final void updateWeekStart() {
        HealthCalendar d = nkw.d(this.mManager.r(), ((Integer) getTag()).intValue() + 1, this.mManager.z());
        setSelectedCalendar(this.mManager.l);
        init(d);
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarBaseView
    void updatePresentDate() {
        if (this.mItems == null) {
            return;
        }
        if (this.mItems.contains(this.mManager.q())) {
            Iterator<HealthCalendar> it = this.mItems.iterator();
            while (it.hasNext()) {
                it.next().setPresentDay(false);
            }
            this.mItems.get(this.mItems.indexOf(this.mManager.q())).setPresentDay(true);
        }
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.mItemHeight, 1073741824));
    }

    public void setMarkerViewClickable(boolean z) {
        this.mMarkerViewClickable = z;
    }
}
