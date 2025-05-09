package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes4.dex */
public class AniFrameLayout extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private AniBlackView f3411a;
    private HealthTextView e;

    public AniFrameLayout(Context context) {
        super(context);
        a(context);
    }

    public AniFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.f3411a = new AniBlackView(context);
        this.e = new HealthTextView(context);
        addView(this.f3411a, -1, -1);
        addView(this.e, -2, -2);
        ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
        if (layoutParams instanceof ViewGroup.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).gravity = 17;
        }
    }

    public void e(int i) {
        this.e.a(i);
    }

    public void b() {
        a();
        this.f3411a.d(false);
    }

    public void e() {
        a();
        this.f3411a.d(true);
    }

    public void a() {
        this.f3411a.b();
    }
}
