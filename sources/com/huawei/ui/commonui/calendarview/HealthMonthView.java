package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import defpackage.koq;
import defpackage.nkw;

/* loaded from: classes9.dex */
public abstract class HealthMonthView extends HealthCalendarBaseMonthView {
    protected abstract void onDrawMark(Canvas canvas, HealthCalendar healthCalendar, int i, int i2);

    protected abstract boolean onDrawSelected(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, boolean z);

    protected abstract void onDrawText(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, boolean z);

    public HealthMonthView(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mLineCount == 0) {
            return;
        }
        updateItemsPresentDay();
        if (!this.mManager.aj()) {
            drawWhenNotDisplayFullMonth(canvas);
            return;
        }
        int i = this.mLineCount;
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.mLineCount) {
            int i4 = i2;
            for (int i5 = 0; i5 < 7; i5++) {
                if (koq.b(this.mItems, i4)) {
                    return;
                }
                HealthCalendar healthCalendar = this.mItems.get(i4);
                if (this.mManager.p() == 1) {
                    if (i4 > this.mItems.size() - this.mNextOffset) {
                        return;
                    }
                    if (!healthCalendar.isPresentMonth()) {
                        continue;
                        i4++;
                    }
                } else if (this.mManager.p() == 2 && i4 >= 7 * i) {
                    return;
                }
                if (healthCalendar.isPresentDay() && !this.mManager.ao()) {
                    draw(canvas, healthCalendar, i3, i5, i4);
                    return;
                } else {
                    draw(canvas, healthCalendar, i3, i5, i4);
                    i4++;
                }
            }
            i3++;
            i2 = i4;
        }
    }

    private void drawWhenNotDisplayFullMonth(Canvas canvas) {
        int i = this.mLineCount;
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.mLineCount) {
            int i4 = i2;
            for (int i5 = 0; i5 < 7; i5++) {
                if (koq.b(this.mItems, i4)) {
                    return;
                }
                HealthCalendar healthCalendar = this.mItems.get(i4);
                if (i4 >= 7 * i) {
                    return;
                }
                draw(canvas, healthCalendar, i3, i5, i4);
                i4++;
            }
            i3++;
            i2 = i4;
        }
    }

    private void draw(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, int i3) {
        int i4;
        int i5;
        if (getLayoutDirection() == 1) {
            i4 = 6;
            i5 = -1;
        } else {
            i4 = 0;
            i5 = 1;
        }
        int a2 = this.mManager.a() + ((i4 + (i5 * i2)) * (this.mHorizontalSpacing + this.mItemWidth));
        boolean z = i3 == this.mCurrentItem && this.mManager.ae();
        boolean hasMark = healthCalendar.hasMark();
        int l = (this.mItemHeight * i) + (this.mMarkedRowCount[i] * this.mManager.l());
        if (hasMark) {
            onDrawMark(canvas, healthCalendar, a2, l);
        }
        if (z) {
            onDrawSelected(canvas, healthCalendar, a2, l, hasMark);
        }
        onDrawText(canvas, healthCalendar, a2, l, z);
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView, com.huawei.ui.commonui.calendarview.HealthCalendarBaseView
    void update() {
        super.update();
        updateExSpace();
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
        if (this.mManager.p() == 1 && !index.isPresentMonth()) {
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
        if (this.mManager.aj() && !index.isPresentMonth() && this.mHealthMonthViewPager != null) {
            int currentItem = this.mHealthMonthViewPager.getCurrentItem();
            this.mHealthMonthViewPager.setCurrentItem(this.mCurrentItem < 7 ? currentItem - 1 : currentItem + 1);
        }
        if (this.mManager.c != null) {
            this.mManager.c.onMonthDateSelected(index, true);
        }
        if (this.mParentLayout != null) {
            if (index.isPresentMonth()) {
                this.mParentLayout.b(this.mItems.indexOf(index));
            } else {
                this.mParentLayout.e(nkw.a(index, this.mManager.z()));
            }
        }
        if (this.mManager.d != null) {
            this.mManager.d.onCalendarSelect(index, true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
