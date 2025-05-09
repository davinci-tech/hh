package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nku;
import defpackage.nkv;
import defpackage.nkw;
import defpackage.skh;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes9.dex */
public final class HealthDefaultWeekView extends HealthWeekView {
    private CalendarTouchHelper c;
    private final a d;
    private HashMap<HealthCalendar, String> e;

    public HealthDefaultWeekView(Context context) {
        super(context);
        this.e = new HashMap<>();
        this.d = new a(this);
    }

    private void a() {
        CalendarTouchHelper calendarTouchHelper = new CalendarTouchHelper(this);
        this.c = calendarTouchHelper;
        ViewCompat.setAccessibilityDelegate(this, calendarTouchHelper);
        ObserverManagerUtil.d(this.d, ObserveLabels.CALENDAR_ACCESSIBILITY_CONTENT);
        ObserverManagerUtil.c(ObserveLabels.CALENDAR_VIEW_INITIALIZED, new Object[0]);
    }

    static class a implements Observer {
        private WeakReference<HealthDefaultWeekView> b;
        private boolean c = true;

        a(HealthDefaultWeekView healthDefaultWeekView) {
            this.b = new WeakReference<>(healthDefaultWeekView);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            HealthDefaultWeekView healthDefaultWeekView = this.b.get();
            if (objArr.length != 4 || healthDefaultWeekView == null || !this.c || healthDefaultWeekView.c == null) {
                return;
            }
            healthDefaultWeekView.c.d(objArr);
            this.c = false;
        }
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthWeekView
    protected boolean onDrawSelected(Canvas canvas, HealthCalendar healthCalendar, int i, boolean z) {
        if (this.mManager.am() && !healthCalendar.hasMark()) {
            return false;
        }
        int i2 = this.mItemWidth / 2;
        int i3 = this.mTextMidY;
        this.mSelectedPaint.setStyle(healthCalendar.isPresentDay() ? Paint.Style.FILL : Paint.Style.STROKE);
        canvas.drawCircle(i + i2, i3, this.mRadius, this.mSelectedPaint);
        return true;
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthWeekView
    protected void onDrawMark(Canvas canvas, HealthCalendar healthCalendar, int i) {
        nkv.cxd_(canvas, healthCalendar, i + (this.mItemWidth / 2), this.mTextOccupiedHeight + this.mManager.k, this.mManager, this.mRadius, this.mMarkPaint, this.mMarkTextPaint);
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthWeekView
    protected void onDrawText(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, boolean z) {
        Paint paint;
        String b;
        int i3 = i2 + (this.mItemWidth / 2);
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
        if (paint != this.mOtherMonthTextPaint && this.c != null) {
            nku nkuVar = new nku(UnitUtil.c(healthCalendar.transformCalendar(), 18), i3, this.mTextMidY, this.mRadius);
            nkuVar.d(Boolean.valueOf(z));
            nkuVar.b(Boolean.valueOf(healthCalendar.isPresentDay()));
            nkuVar.e(healthCalendar.hasMark());
            if (this.c.a(healthCalendar.toString())) {
                CalendarTouchHelper calendarTouchHelper = this.c;
                calendarTouchHelper.a(calendarTouchHelper.b(healthCalendar.toString()), nkuVar);
            } else {
                this.c.a(healthCalendar.toString(), Integer.valueOf(this.c.d()));
                this.c.d(nkuVar);
            }
        }
        String str = this.e.get(healthCalendar);
        if (!TextUtils.isEmpty(str) && !this.mManager.ar()) {
            canvas.drawText(str, i3, this.mTextBaseLine, paint);
            return;
        }
        if (this.mManager.ar()) {
            if (healthCalendar.isPresentDay() && LanguageUtil.h(BaseApplication.e())) {
                b = nkw.d();
            } else {
                b = nkw.b(getContext(), i, this.mManager.z());
            }
        } else {
            b = skh.b(healthCalendar.getDay());
        }
        this.e.put(healthCalendar, b);
        canvas.drawText(b, i3, this.mTextBaseLine, paint);
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarBaseView
    void updateItemHeight() {
        super.updateItemHeight();
        this.mRadius = this.mManager.m / 2;
        Paint.FontMetrics fontMetrics = this.mCurMonthTextPaint.getFontMetrics();
        float f = (fontMetrics.ascent + fontMetrics.descent) / 2.0f;
        int i = this.mManager.t;
        this.mTextMidY = this.mRadius + i;
        float f2 = this.mTextMidY;
        if (fontMetrics.descent < (-fontMetrics.ascent)) {
            f = -f;
        }
        this.mTextBaseLine = f2 + f;
        this.mTextOccupiedHeight = (int) (i + Math.max(this.mRadius * 2.0f, fontMetrics.bottom - fontMetrics.top));
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthBaseWeekView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int dimensionPixelSize = this.mTextOccupiedHeight + getContext().getResources().getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb);
        if (b()) {
            dimensionPixelSize += this.mManager.f + this.mManager.j + this.mManager.l();
        }
        setMeasuredDimension(getMeasuredWidth(), dimensionPixelSize);
    }

    private boolean b() {
        if (this.mItems == null) {
            return false;
        }
        for (HealthCalendar healthCalendar : this.mItems) {
            if (healthCalendar != null && healthCalendar.hasMark()) {
                for (HealthMark healthMark : healthCalendar.getMarks()) {
                    if (healthMark.cxx_() != null || healthMark.cxy_() != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        LogUtil.a("HealthDefaultWeekView", "enter into dispatchHoverEvent");
        CalendarTouchHelper calendarTouchHelper = this.c;
        if (calendarTouchHelper != null && calendarTouchHelper.dispatchHoverEvent(motionEvent)) {
            LogUtil.a("HealthDefaultWeekView", "enter into dispatchHoverEvent != null");
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        LogUtil.a("HealthDefaultWeekView", "enter into dispatchKeyEvent");
        CalendarTouchHelper calendarTouchHelper = this.c;
        if (calendarTouchHelper != null && calendarTouchHelper.dispatchKeyEvent(keyEvent)) {
            LogUtil.a("HealthDefaultWeekView", "enter into dispatchKeyEvent != null");
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // com.huawei.ui.commonui.calendarview.HealthBaseWeekView, com.huawei.ui.commonui.calendarview.HealthCalendarBaseView
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ObserverManagerUtil.c(this.d);
    }
}
