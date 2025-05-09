package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.operation.share.ShareConfig;
import com.huawei.ui.commonui.R$string;
import defpackage.nkw;
import defpackage.nky;
import defpackage.nsk;
import defpackage.nsn;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class HealthCalendarBaseView extends View implements View.OnClickListener {
    protected static final int DICHOTOMY_SIZE = 2;
    protected static final float DICHOTOMY_SIZE_FLOAT = 2.0f;
    protected static final int INVALIDE_VALUE = -1;
    private static final int TOUCH_RANGE = 50;
    protected Paint mCurDaySelectedTextPaint;
    protected Paint mCurDayTextPaint;
    protected Paint mCurMonthTextPaint;
    int mCurrentItem;
    int mHorizontalSpacing;
    boolean mIsClick;
    protected int mItemHeight;
    protected int mItemWidth;
    protected List<HealthCalendar> mItems;
    protected nky mManager;
    protected Paint mMarkPaint;
    protected Paint mMarkTextPaint;
    protected Paint mOtherMonthTextPaint;
    HealthCalendarLayout mParentLayout;
    protected int mRadius;
    protected Paint mSelectTextPaint;
    protected Paint mSelectedPaint;
    protected float mTextBaseLine;
    protected int mTextMidY;
    protected int mTextOccupiedHeight;
    protected float mX;
    protected float mY;

    protected abstract void onDestroy();

    abstract void updatePresentDate();

    public HealthCalendarBaseView(Context context) {
        this(context, null);
    }

    public HealthCalendarBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurMonthTextPaint = new Paint();
        this.mOtherMonthTextPaint = new Paint();
        this.mMarkPaint = new Paint();
        this.mMarkTextPaint = new Paint();
        this.mSelectedPaint = new Paint();
        this.mSelectTextPaint = new Paint();
        this.mCurDayTextPaint = new Paint();
        this.mCurDaySelectedTextPaint = new Paint();
        this.mIsClick = true;
        this.mCurrentItem = -1;
        initPaint();
    }

    private void initPaint() {
        initTextPaint(this.mCurMonthTextPaint);
        initTextPaint(this.mOtherMonthTextPaint);
        initTextPaint(this.mSelectTextPaint);
        initTextPaint(this.mCurDayTextPaint);
        initTextPaint(this.mCurDaySelectedTextPaint);
        this.mMarkPaint.setAntiAlias(true);
        this.mMarkPaint.setStyle(Paint.Style.FILL);
        this.mMarkTextPaint.setAntiAlias(true);
        this.mMarkTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mMarkTextPaint.setTypeface(nsk.cKN_());
        this.mSelectedPaint.setAntiAlias(true);
        this.mSelectedPaint.setStyle(Paint.Style.STROKE);
        this.mSelectedPaint.setStrokeWidth(nsn.c(getContext(), 1.0f));
        setOnClickListener(this);
    }

    private void initTextPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(getResources().getString(R$string.emui_text_font_family_medium), 0));
    }

    final void init(nky nkyVar) {
        this.mManager = nkyVar;
        updateStyle();
        updateItemHeight();
    }

    final void updateStyle() {
        nky nkyVar = this.mManager;
        if (nkyVar == null) {
            return;
        }
        this.mCurDayTextPaint.setColor(nkyVar.g());
        this.mCurDaySelectedTextPaint.setColor(this.mManager.j());
        this.mCurMonthTextPaint.setColor(this.mManager.x());
        this.mOtherMonthTextPaint.setColor(this.mManager.s());
        this.mSelectTextPaint.setColor(this.mManager.w());
        this.mCurMonthTextPaint.setTextSize(this.mManager.h());
        this.mOtherMonthTextPaint.setTextSize(this.mManager.h());
        this.mCurDayTextPaint.setTextSize(this.mManager.h());
        this.mCurDaySelectedTextPaint.setTextSize(this.mManager.h());
        this.mSelectTextPaint.setTextSize(this.mManager.h());
        this.mSelectedPaint.setColor(this.mManager.u());
        this.mMarkTextPaint.setColor(this.mManager.k());
        this.mMarkTextPaint.setTextSize(this.mManager.o());
    }

    void updateItemHeight() {
        this.mItemHeight = this.mManager.c();
        Paint.FontMetrics fontMetrics = this.mCurMonthTextPaint.getFontMetrics();
        this.mTextBaseLine = ((this.mItemHeight / 2.0f) - fontMetrics.descent) + ((fontMetrics.bottom - fontMetrics.top) / 2.0f);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int a2 = this.mManager.a();
        int b = this.mManager.b();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362850_res_0x7f0a0422);
        this.mItemWidth = dimensionPixelSize;
        this.mHorizontalSpacing = (((i - a2) - b) - (dimensionPixelSize * 7)) / 6;
    }

    void removeMarks() {
        Iterator<HealthCalendar> it = this.mItems.iterator();
        while (it.hasNext()) {
            it.next().clearMark();
        }
        requestLayout();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > 1) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
            this.mIsClick = true;
        } else if (action != 1) {
            if (action == 2 && this.mIsClick) {
                this.mIsClick = Math.abs(motionEvent.getY() - this.mY) <= 50.0f;
            }
        } else {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
        }
        return super.onTouchEvent(motionEvent);
    }

    void update() {
        if (this.mManager.b == null || this.mManager.b.size() == 0) {
            removeMarks();
            invalidate();
        } else {
            this.mManager.e(this.mItems);
            requestLayout();
            invalidate();
        }
    }

    protected void updateItemsPresentDay() {
        if (this.mItems == null) {
            return;
        }
        Date date = new Date();
        HealthCalendar healthCalendar = new HealthCalendar(nkw.c("yyyy", date), nkw.c("MM", date), nkw.c("dd", date));
        this.mManager.as();
        for (HealthCalendar healthCalendar2 : this.mItems) {
            if (healthCalendar2.equals(healthCalendar)) {
                healthCalendar2.setPresentDay(true);
            } else {
                healthCalendar2.setPresentDay(false);
            }
        }
    }

    protected final boolean isInRange(HealthCalendar healthCalendar) {
        nky nkyVar = this.mManager;
        return nkyVar != null && nkw.c(healthCalendar, nkyVar);
    }

    protected final boolean isInRangeOfGray(HealthCalendar healthCalendar) {
        if (this.mManager.h == null && this.mManager.g == null) {
            return false;
        }
        if (this.mManager.h == null) {
            this.mManager.h = new HealthCalendar(1900, 1, 1);
        }
        if (this.mManager.g == null) {
            this.mManager.g = new HealthCalendar(ShareConfig.MSG_SHARE_FAIL_TOAST, 1, 1);
        }
        nky nkyVar = this.mManager;
        return nkyVar != null && nkw.c(healthCalendar, nkyVar.h, this.mManager.g);
    }

    protected int calIndex() {
        int i = this.mItemWidth + this.mHorizontalSpacing;
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        float a2 = this.mX - this.mManager.a();
        int i3 = this.mItemWidth + (this.mHorizontalSpacing / 2);
        int width = getWidth();
        if (a2 >= i3) {
            i2 = a2 > ((float) (width - i3)) ? 6 : (int) ((a2 + (this.mHorizontalSpacing / 2.0f)) / i);
        }
        return getLayoutDirection() == 1 ? 6 - i2 : i2;
    }

    protected int calIndexY() {
        return (int) (this.mY / this.mItemHeight);
    }
}
