package com.huawei.ui.commonui.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.phone.hwseekbar.widget.HwSeekBar;
import defpackage.koq;
import defpackage.nsn;
import defpackage.smr;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class HealthMultiRangeSeekBar extends HwSeekBar {
    private static final int[] e = {R$color.emuiColor2, R$color.emuiColor4, R$color.emuiColor11, R$color.emuiColor9, R$color.emuiColor8};

    /* renamed from: a, reason: collision with root package name */
    private boolean f8946a;
    private int b;
    private int c;
    private int d;
    private boolean f;
    private List<OnSeekChangeListener> g;
    private float h;
    private float i;
    private PopupWindow j;
    private int k;
    private int l;
    private int m;
    private HealthTextView n;
    private int o;
    private float p;
    private List<Float> q;
    private Bitmap r;
    private float s;
    private int t;

    public interface OnSeekChangeListener {
        void onSeek(int i, float f);
    }

    public HealthMultiRangeSeekBar(Context context) {
        this(context, null);
    }

    public HealthMultiRangeSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100135_res_0x7f0601e7);
    }

    public HealthMultiRangeSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        this.l = 30;
        this.b = 100;
        this.t = 50;
        this.c = -1;
        cFV_(context, attributeSet, i);
        e();
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HealthMultiRangeSeekBar);
    }

    private void j() {
        HealthTextView healthTextView = new HealthTextView(getContext());
        this.n = healthTextView;
        healthTextView.setTextColor(-1);
        this.n.setTextSize(1, 13.0f);
        this.n.setTypeface(Typeface.SANS_SERIF);
        this.n.setBackgroundResource(R$drawable.hwseekbar_selector_tip_bubble_emui);
        this.n.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.n.setGravity(17);
        this.n.setSingleLine(true);
        PopupWindow popupWindow = new PopupWindow((View) this.n, -2, -2, false);
        this.j = popupWindow;
        popupWindow.setAnimationStyle(R.style.Animation_Emui_HwSeekBar_TipsPopupWindow);
    }

    private void h() {
        if (this.f8946a) {
            this.j.showAsDropDown(this, 0, 0, 3);
            l();
        }
    }

    private void f() {
        this.n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.m = this.n.getMeasuredWidth();
        this.k = this.n.getMeasuredHeight();
    }

    private void l() {
        if (koq.b(this.q, this.c)) {
            return;
        }
        this.n.setText(UnitUtil.e(this.q.get(this.c).floatValue(), 1, 0));
        f();
        float d = d(this.q.get(this.c).floatValue());
        if (c()) {
            d = getWidth() - d;
        }
        float f = -this.i;
        float f2 = this.k;
        float paddingBottom = getPaddingBottom();
        this.j.update(this, (int) (d - (this.m / 2.0f)), (int) ((((f - f2) - paddingBottom) - this.s) - nsn.c(getContext(), 2.0f)), this.m, this.k);
    }

    private void g() {
        if (this.f8946a) {
            this.j.dismiss();
        }
    }

    private void e() {
        this.r = BitmapFactory.decodeResource(getResources(), R.mipmap._2131821470_res_0x7f11039e);
        this.s = r0.getWidth() / 2.0f;
        this.o = nsn.c(getContext(), 4.0f);
        this.d = getResources().getDimensionPixelSize(R.dimen._2131363212_res_0x7f0a058c) / 2;
        this.p = this.s - nsn.c(getContext(), 1.0f);
        this.g = new ArrayList(1);
        this.q = new ArrayList(5);
        for (int i = 0; i < 5; i++) {
            this.q.add(Float.valueOf(this.t + (i * 10)));
        }
    }

    private void cFV_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthMultiRangeSeekBar, i, R.style.Widget_Emui_HealthMultiRangeSeekBar);
        this.l = obtainStyledAttributes.getInt(R$styleable.HealthMultiRangeSeekBar_startScale, this.l);
        this.b = obtainStyledAttributes.getInt(R$styleable.HealthMultiRangeSeekBar_endScale, this.b);
        this.f8946a = obtainStyledAttributes.getBoolean(R$styleable.HealthMultiRangeSeekBar_showPopTip, false);
        this.f = obtainStyledAttributes.getBoolean(R$styleable.HealthMultiRangeSeekBar_showThumbs, false);
        obtainStyledAttributes.recycle();
        if (this.f8946a) {
            j();
        }
    }

    @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        synchronized (this) {
            cFZ_(canvas);
            cFT_(canvas);
            cFS_(canvas);
            if (this.f) {
                cFU_(canvas);
            }
        }
    }

    void cFZ_(Canvas canvas) {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable == null || canvas == null) {
            return;
        }
        int save = canvas.save();
        if (c()) {
            canvas.translate(getWidth() - getPaddingRight(), getPaddingTop());
            canvas.scale(-1.0f, 1.0f);
        } else {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        progressDrawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = this.b - this.l;
        if (i5 > 0) {
            this.h = (((getWidth() - getPaddingStart()) - getPaddingEnd()) - this.p) / i5;
        }
        this.i = ((getHeight() - getPaddingTop()) - getPaddingBottom()) / 2.0f;
    }

    private void cFS_(Canvas canvas) {
        float d;
        float d2;
        int save = canvas.save();
        canvas.translate(0.0f, getPaddingTop() + this.i);
        if (!this.q.isEmpty() && this.q.size() > 1) {
            int i = this.d;
            float f = -i;
            float f2 = i;
            Paint paint = new Paint();
            Path path = new Path();
            RectF rectF = new RectF();
            int i2 = 0;
            while (true) {
                if (i2 >= this.q.size()) {
                    break;
                }
                if (i2 == this.q.size() - 1) {
                    List<Float> list = this.q;
                    d = d(list.get(list.size() - 1).floatValue());
                    d2 = getWidth() - getPaddingEnd();
                } else {
                    d = d(this.q.get(i2).floatValue());
                    d2 = d(this.q.get(i2 + 1).floatValue());
                }
                int width = getWidth() - getPaddingEnd();
                if (c()) {
                    d = getWidth() - d;
                    d2 = getWidth() - d2;
                    width = getWidth() - width;
                }
                rectF.set(d, f, d2, f2);
                paint.setColor(getResources().getColor(j(i2)));
                if (width == ((int) d2)) {
                    path.addRect(rectF, Path.Direction.CW);
                    break;
                } else {
                    canvas.drawRect(rectF, paint);
                    i2++;
                }
            }
            cFR_(canvas, path, rectF, paint);
        }
        canvas.restoreToCount(save);
    }

    private void cFR_(Canvas canvas, Path path, RectF rectF, Paint paint) {
        Path path2 = new Path();
        float paddingStart = getPaddingStart();
        float width = getWidth() - getPaddingEnd();
        if (c()) {
            paddingStart = getWidth() - paddingStart;
            width = getWidth() - width;
        }
        rectF.set(paddingStart, -r3, width, this.d);
        float c = nsn.c(getContext(), this.d);
        path2.addRoundRect(rectF, c, c, Path.Direction.CW);
        path2.op(path, Path.Op.INTERSECT);
        canvas.drawPath(path2, paint);
    }

    private int j(int i) {
        if (i < 0 || i >= 5) {
            return 0;
        }
        return e[i];
    }

    private void cFT_(Canvas canvas) {
        int i;
        int i2;
        float cLa_;
        HealthMultiRangeSeekBar healthMultiRangeSeekBar = this;
        Paint paint = new Paint();
        paint.setColor(getContext().getResources().getColor(R$color.listDivider));
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        paint2.setColor(getContext().getResources().getColor(R$color.textColorSecondary));
        paint2.setTextSize(getResources().getDimensionPixelSize(R.dimen._2131365066_res_0x7f0a0cca));
        paint2.setTypeface(Typeface.create(getResources().getString(R$string.emui_text_font_family_regular), 0));
        paint2.setAntiAlias(true);
        int i3 = (healthMultiRangeSeekBar.b - healthMultiRangeSeekBar.l) / 10;
        int save = canvas.save();
        canvas.translate(0.0f, healthMultiRangeSeekBar.i + healthMultiRangeSeekBar.d);
        float paddingTop = getPaddingTop();
        float f = healthMultiRangeSeekBar.o;
        float cKZ_ = nsn.cKZ_(paint2);
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 * 10) + healthMultiRangeSeekBar.l;
            float d = healthMultiRangeSeekBar.d(i5);
            if (c()) {
                d = getWidth() - d;
            }
            float f2 = d;
            if (i4 != i3) {
                float f3 = paddingTop + healthMultiRangeSeekBar.o;
                i = i5;
                i2 = i4;
                canvas.drawLine(f2, paddingTop, f2, f3, paint);
            } else {
                i = i5;
                i2 = i4;
            }
            String e2 = UnitUtil.e(i, 2, 0);
            if (i2 == 0) {
                if (c()) {
                    cLa_ = nsn.cLa_(paint2, e2);
                    f2 -= cLa_;
                }
                canvas.drawText(e2, f2, f + paddingTop + cKZ_, paint2);
                i4 = i2 + 1;
                healthMultiRangeSeekBar = this;
            } else {
                if (i2 == i3) {
                    if (!c()) {
                        cLa_ = nsn.cLa_(paint2, e2);
                    }
                    canvas.drawText(e2, f2, f + paddingTop + cKZ_, paint2);
                    i4 = i2 + 1;
                    healthMultiRangeSeekBar = this;
                } else {
                    cLa_ = nsn.cLa_(paint2, e2) / 2.0f;
                }
                f2 -= cLa_;
                canvas.drawText(e2, f2, f + paddingTop + cKZ_, paint2);
                i4 = i2 + 1;
                healthMultiRangeSeekBar = this;
            }
        }
        canvas.restoreToCount(save);
    }

    private float d(float f) {
        return ((f - this.l) * this.h) + this.p + getPaddingStart();
    }

    private float b(float f) {
        return (((f - getPaddingStart()) - this.p) / this.h) + this.l;
    }

    private void cFU_(Canvas canvas) {
        int saveCount = canvas.getSaveCount();
        float f = this.s;
        canvas.translate(-f, this.i - f);
        Iterator<Float> it = this.q.iterator();
        while (it.hasNext()) {
            float d = d(it.next().floatValue());
            if (c()) {
                d = getWidth() - d;
            }
            canvas.drawBitmap(this.r, d, getPaddingTop(), (Paint) null);
        }
        canvas.restoreToCount(saveCount);
    }

    protected boolean c() {
        return getLayoutDirection() == 1;
    }

    @Override // com.huawei.uikit.hwseekbar.widget.HwSeekBar, android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            this.c = -1;
            cFW_(motionEvent);
        }
        if (motionEvent.getAction() == 2) {
            cFX_(motionEvent);
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            i();
        }
        return true;
    }

    private void cFW_(MotionEvent motionEvent) {
        cFQ_(motionEvent);
        h();
    }

    private void cFQ_(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        for (int i = 0; i < this.q.size(); i++) {
            float b = b(i);
            float a2 = a(i);
            if (c()) {
                b = getWidth() - a(i);
                a2 = getWidth() - b(i);
            }
            if (Float.compare(x, b) > 0 && Float.compare(x, a2) < 0) {
                this.c = i;
                return;
            }
        }
    }

    private void cFX_(MotionEvent motionEvent) {
        if (this.c != -1) {
            cFY_(motionEvent);
        }
    }

    private void cFY_(MotionEvent motionEvent) {
        h();
        setThumbPos(motionEvent);
    }

    private void i() {
        g();
        List<OnSeekChangeListener> list = this.g;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (OnSeekChangeListener onSeekChangeListener : this.g) {
            int i = this.c;
            onSeekChangeListener.onSeek(i, this.q.get(i).floatValue());
        }
    }

    private float b(int i) {
        if (i == 0) {
            return 0.0f;
        }
        return (d(this.q.get(i - 1).floatValue()) - this.s) + e(i);
    }

    private float e(int i) {
        if (i == 0 || i == this.q.size()) {
            return 0.0f;
        }
        return (d(this.q.get(i).floatValue()) - d(this.q.get(i - 1).floatValue())) / 2.0f;
    }

    private float a(int i) {
        if (i == this.q.size() - 1) {
            return getWidth();
        }
        int i2 = i + 1;
        return (d(this.q.get(i2).floatValue()) - this.s) - e(i2);
    }

    private float c(int i) {
        float d;
        int width;
        if (i == 0) {
            d = this.p;
            width = getPaddingStart();
        } else {
            d = d(this.q.get(i - 1).floatValue());
            width = this.r.getWidth();
        }
        return d + width;
    }

    private float d(int i) {
        if (i == this.q.size() - 1) {
            return getWidth() - getPaddingEnd();
        }
        return d(this.q.get(i + 1).floatValue()) - this.r.getWidth();
    }

    private void setThumbPos(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        if (koq.b(this.q, this.c)) {
            return;
        }
        float c = c(this.c);
        float d = d(this.c);
        if (c()) {
            c = getWidth() - d(this.c);
            d = getWidth() - c(this.c);
        }
        if (x < c) {
            x = c;
        }
        if (x <= d) {
            d = x;
        }
        if (c()) {
            d = getWidth() - d;
        }
        this.q.set(this.c, Float.valueOf(b(d)));
        invalidate();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mThumbPositions = this.q;
        return savedState;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.q = savedState.mThumbPositions;
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.huawei.ui.commonui.seekbar.HealthMultiRangeSeekBar.SavedState.2
            @Override // android.os.Parcelable.Creator
            /* renamed from: cGa_, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private List<Float> mThumbPositions;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.mThumbPositions = new ArrayList(5);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            ArrayList arrayList = new ArrayList(5);
            this.mThumbPositions = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeList(this.mThumbPositions);
        }
    }
}
