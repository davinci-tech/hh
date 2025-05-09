package com.huawei.health.suggestion.ui.fitness.module;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nrr;

/* loaded from: classes4.dex */
public class ToolsLayout extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private MediaProgress f3192a;
    private int b;
    private boolean c;
    private int d;
    private boolean e;
    private int h;
    private int i;

    private float b(int i, int i2, int i3) {
        if (i != 0) {
            return i2 - i3;
        }
        return 0.0f;
    }

    public ToolsLayout(Context context) {
        super(context);
        this.d = 0;
        this.c = true;
    }

    public ToolsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0;
        this.c = true;
    }

    public ToolsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.c = true;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.i = i;
        this.b = i2;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        this.h = i;
        if (getChildCount() > 0) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                int left = childAt.getLeft();
                int right = childAt.getRight();
                if (childAt instanceof TimeProgressPlus) {
                    LogUtil.h("Suggestion_ToolsLayout", "childAt instanceof TimeProgressPlus");
                    return;
                }
                if (!this.c && childAt.getId() == R.id.sug_coach_iv_lock) {
                    childAt.setVisibility(i);
                } else if (bottom < nrr.e(getContext(), 136.0f)) {
                    aEz_(childAt, b(i, 0, bottom));
                } else if (this.b - top < nrr.e(getContext(), 136.0f)) {
                    aEw_(childAt, top, i, 0.0f);
                } else if (right < nrr.e(getContext(), 136.0f)) {
                    aEy_(childAt, b(i, 0, right));
                } else {
                    aEy_(childAt, b(i, this.i, left));
                }
            }
        }
    }

    private void aEw_(View view, int i, int i2, float f) {
        if ((view instanceof LinearLayout) && this.f3192a == null) {
            LinearLayout linearLayout = (LinearLayout) view;
            int childCount = linearLayout.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                aEx_(linearLayout, i3);
            }
        }
        int i4 = this.d;
        LogUtil.a("Suggestion_ToolsLayout", "childAt:", view.getClass().getName());
        if (i2 != 0) {
            f = this.b - (i + i4);
        }
        aEz_(view, f);
    }

    private void aEx_(LinearLayout linearLayout, int i) {
        View childAt = linearLayout.getChildAt(i);
        LogUtil.a("Suggestion_ToolsLayout", childAt.getClass().getName());
        if (childAt instanceof MediaProgress) {
            MediaProgress mediaProgress = (MediaProgress) childAt;
            this.f3192a = mediaProgress;
            mediaProgress.setVisibility(0);
        }
        if (childAt instanceof LinearLayout) {
            this.d = childAt.getTop();
        }
    }

    @Override // android.view.View
    public int getVisibility() {
        return this.h;
    }

    public void setIsShowBottomProgress(boolean z) {
        if (this.e == z || this.f3192a == null) {
            return;
        }
        LogUtil.a("Suggestion_ToolsLayout", "isShowBottomProgress:", Boolean.valueOf(z));
        this.e = z;
        if (z && this.h != 0) {
            this.f3192a.setVisibility(0);
        } else {
            this.f3192a.setVisibility(4);
        }
    }

    private void aEz_(View view, float f) {
        view.animate().translationY(f).setDuration(300L).setListener(new AnimatorListenerAdapter() { // from class: com.huawei.health.suggestion.ui.fitness.module.ToolsLayout.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (ToolsLayout.this.f3192a != null) {
                    ToolsLayout.this.f3192a.setVisibility(4);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (!ToolsLayout.this.e || ToolsLayout.this.f3192a == null) {
                    return;
                }
                if (ToolsLayout.this.h == 0) {
                    ToolsLayout.this.f3192a.setVisibility(4);
                } else {
                    ToolsLayout.this.f3192a.setVisibility(0);
                }
            }
        }).start();
    }

    private void aEy_(View view, float f) {
        view.animate().translationX(f).setDuration(300L).start();
    }

    public void setIsShowLockAnim(boolean z) {
        this.c = z;
    }
}
