package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nku;
import defpackage.nkv;
import defpackage.nkw;
import defpackage.skh;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public final class HealthDefaultMonthView extends HealthMonthView {

    /* renamed from: a, reason: collision with root package name */
    private final a f8776a;
    private CalendarTouchHelper d;
    private int e;

    public HealthDefaultMonthView(Context context) {
        super(context);
        this.f8776a = new a(this);
    }

    private void e() {
        CalendarTouchHelper calendarTouchHelper = new CalendarTouchHelper(this);
        this.d = calendarTouchHelper;
        ViewCompat.setAccessibilityDelegate(this, calendarTouchHelper);
        ObserverManagerUtil.d(this.f8776a, ObserveLabels.CALENDAR_ACCESSIBILITY_CONTENT);
        ObserverManagerUtil.c(ObserveLabels.CALENDAR_VIEW_INITIALIZED, new Object[0]);
    }

    static class a implements Observer {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<HealthDefaultMonthView> f8777a;
        private boolean d = true;

        a(HealthDefaultMonthView healthDefaultMonthView) {
            this.f8777a = new WeakReference<>(healthDefaultMonthView);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            HealthDefaultMonthView healthDefaultMonthView = this.f8777a.get();
            if (objArr.length != 4 || healthDefaultMonthView == null || !this.d || healthDefaultMonthView.d == null) {
                return;
            }
            healthDefaultMonthView.d.d(objArr);
            this.d = false;
        }
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView, com.huawei.ui.commonui.calendarview.HealthCalendarBaseView
    void updateItemHeight() {
        super.updateItemHeight();
        this.e = this.mManager.ac() / 2;
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthMonthView
    protected boolean onDrawSelected(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, boolean z) {
        int i3 = this.mItemWidth / 2;
        int i4 = this.mItemHeight / 2;
        if (!this.mManager.aj() || healthCalendar.isPresentDay()) {
            this.mSelectedPaint.setStyle(Paint.Style.FILL);
        } else {
            this.mSelectedPaint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawCircle(i + i3, i2 + i4, this.e, this.mSelectedPaint);
        return true;
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthMonthView
    protected void onDrawMark(Canvas canvas, HealthCalendar healthCalendar, int i, int i2) {
        nkv.cxd_(canvas, healthCalendar, i + (this.mItemWidth / 2), i2 + (this.mItemHeight / 2) + this.e, this.mManager, this.e, this.mMarkPaint, this.mMarkTextPaint);
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthMonthView
    protected void onDrawText(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, boolean z) {
        Paint paint;
        int i3 = i + (this.mItemWidth / 2);
        boolean j = nkw.j(healthCalendar, this.mManager);
        if (z && healthCalendar.isPresentDay()) {
            paint = this.mCurDaySelectedTextPaint;
        } else if (healthCalendar.isPresentDay()) {
            paint = this.mCurDayTextPaint;
        } else if (z) {
            paint = this.mCurMonthTextPaint;
        } else if ((this.mManager.am() && !healthCalendar.hasMark()) || ((this.mManager.an() && j) || isInRangeOfGray(healthCalendar))) {
            paint = this.mOtherMonthTextPaint;
        } else if (healthCalendar.isPresentMonth()) {
            paint = this.mCurMonthTextPaint;
        } else {
            paint = this.mOtherMonthTextPaint;
        }
        if (!this.mManager.aj()) {
            if (z) {
                paint = this.mCurDaySelectedTextPaint;
            } else {
                paint = healthCalendar.isInRange() ? this.mCurMonthTextPaint : this.mOtherMonthTextPaint;
            }
        }
        if (paint != this.mOtherMonthTextPaint && this.d != null) {
            nku nkuVar = new nku(UnitUtil.c(healthCalendar.transformCalendar(), 8), i3, (this.mItemHeight / 2) + i2, this.e);
            nkuVar.d(Boolean.valueOf(z));
            nkuVar.b(Boolean.valueOf(healthCalendar.isPresentDay()));
            nkuVar.e(healthCalendar.hasMark());
            if (this.d.a(healthCalendar.toString())) {
                CalendarTouchHelper calendarTouchHelper = this.d;
                calendarTouchHelper.a(calendarTouchHelper.b(healthCalendar.toString()), nkuVar);
            } else {
                this.d.a(healthCalendar.toString(), Integer.valueOf(this.d.d()));
                this.d.d(nkuVar);
            }
        }
        canvas.drawText(skh.b(healthCalendar.getDay()), i3, this.mTextBaseLine + i2, paint);
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        LogUtil.a("HealthDefaultMonthView", "enter into dispatchHoverEvent");
        CalendarTouchHelper calendarTouchHelper = this.d;
        if (calendarTouchHelper != null && calendarTouchHelper.dispatchHoverEvent(motionEvent)) {
            LogUtil.a("HealthDefaultMonthView", "enter into dispatchHoverEvent != null");
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        LogUtil.a("HealthDefaultMonthView", "enter into dispatchKeyEvent");
        CalendarTouchHelper calendarTouchHelper = this.d;
        if (calendarTouchHelper != null && calendarTouchHelper.dispatchKeyEvent(keyEvent)) {
            LogUtil.a("HealthDefaultMonthView", "enter into dispatchKeyEvent != null");
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView, com.huawei.ui.commonui.calendarview.HealthCalendarBaseView
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ObserverManagerUtil.c(this.f8776a);
    }
}
