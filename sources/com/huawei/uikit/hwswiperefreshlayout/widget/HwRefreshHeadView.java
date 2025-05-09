package com.huawei.uikit.hwswiperefreshlayout.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.skg;

/* loaded from: classes7.dex */
public class HwRefreshHeadView extends RelativeLayout {
    private int b;
    private RelativeLayout c;
    private HwTextView d;
    private HwProgressBar e;

    public HwRefreshHeadView(Context context) {
        super(context);
    }

    private void b() {
        this.c = (RelativeLayout) findViewById(R.id.hwswiperefreshlayout_layout);
        HwProgressBar hwProgressBar = (HwProgressBar) findViewById(R.id.hwswiperefreshlayout_progressbar);
        this.e = hwProgressBar;
        hwProgressBar.setVisibility(4);
        e();
    }

    private void c() {
        HwTextView hwTextView = (HwTextView) findViewById(R.id.hwswiperefreshlayout_textview);
        this.d = hwTextView;
        hwTextView.setVisibility(4);
    }

    void a() {
        Drawable indeterminateDrawable = this.e.getIndeterminateDrawable();
        if (indeterminateDrawable instanceof skg) {
            ((skg) indeterminateDrawable).e();
        }
    }

    public void a(Context context) {
        b();
        c();
    }

    public void d(int i) {
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, i);
        }
    }

    void e() {
        Drawable indeterminateDrawable = this.e.getIndeterminateDrawable();
        if (indeterminateDrawable instanceof skg) {
            ((skg) indeterminateDrawable).d();
        }
    }

    public void setProgressBarAlpha(float f) {
        this.e.setAlpha(f);
    }

    public void setProgressBarColor(int i) {
        Drawable indeterminateDrawable = this.e.getIndeterminateDrawable();
        if (indeterminateDrawable instanceof skg) {
            ((skg) indeterminateDrawable).d(i);
        }
    }

    void setProgressBarDragFraction(float f) {
        Drawable indeterminateDrawable = this.e.getIndeterminateDrawable();
        if (indeterminateDrawable instanceof skg) {
            indeterminateDrawable.setLevel((int) (f * 10000.0f));
        }
    }

    @Deprecated
    public void setProgressBarRollingStatus(int i) {
    }

    public void setProgressBarScaleSize(int i) {
        this.b = i;
    }

    public void setProgressBarScaleX(float f) {
        this.e.setScaleX(f);
    }

    public void setProgressBarScaleY(float f) {
        this.e.setScaleY(f);
    }

    public void setProgressBarVisibility(int i) {
        this.e.setVisibility(i);
    }

    public void setTextViewAlpha(float f) {
        this.d.setAlpha(f);
    }

    public void setTextViewColor(int i) {
        this.d.setTextColor(i);
    }

    public void setTextViewText(CharSequence charSequence) {
        this.d.setText(charSequence);
    }

    public void setTextViewVisibility(int i) {
        this.d.setVisibility(i);
    }

    public HwRefreshHeadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HwRefreshHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
