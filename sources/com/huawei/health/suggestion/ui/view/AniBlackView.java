package com.huawei.health.suggestion.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import health.compact.a.CommonUtil;

/* loaded from: classes4.dex */
public class AniBlackView extends View {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3410a;
    private ObjectAnimator d;

    public AniBlackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AniBlackView(Context context) {
        super(context);
    }

    public void d(boolean z) {
        this.f3410a = z;
        if (!CommonUtil.bu()) {
            setBackgroundColor(ContextCompat.getColor(getContext(), R$color.emui_color_bg_dark));
        }
        if (z) {
            aMb_(this, 0.0f, 1.0f, 500);
        } else {
            aMb_(this, 1.0f, 0.0f, 300);
        }
    }

    private void aMb_(View view, float f, float f2, int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "Alpha", f, f2);
        ofFloat.setDuration(i);
        ofFloat.start();
        this.d = ofFloat;
    }

    public void b() {
        LogUtil.a("Suggestion_AniBlackView", "enter cancellAlphaAnimator");
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator != null && this.f3410a) {
            objectAnimator.setCurrentPlayTime(0L);
            this.d.cancel();
            LogUtil.a("Suggestion_AniBlackView", "cancellAlphaAnimator mIsToBlack");
        }
    }
}
