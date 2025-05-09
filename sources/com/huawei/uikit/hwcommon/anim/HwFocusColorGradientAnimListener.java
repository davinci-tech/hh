package com.huawei.uikit.hwcommon.anim;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.util.Pair;
import android.view.View;
import com.huawei.uikit.hwcommon.anim.HwGradientAnimatorMgr;
import java.util.Map;

/* loaded from: classes9.dex */
public class HwFocusColorGradientAnimListener implements HwGradientAnimatorMgr.OnAnimatorListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10623a;
    private ColorStateList b;
    private View c;
    private UpdateColorCallback d;
    private boolean e;
    private UpdateColorCallback f;
    private boolean g;
    private UpdateColorCallback h;
    private ColorStateList j;

    public interface UpdateColorCallback {
        void onEnd(ColorStateList colorStateList);

        void onStart();

        void onUpdate(int i);
    }

    private int a(int[] iArr, ColorStateList colorStateList) {
        if (colorStateList != null) {
            return colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwcommon.anim.HwGradientAnimatorMgr.OnAnimatorListener
    public void onAnimationCancel(Animator animator, String str) {
        onAnimationEnd(animator, str);
    }

    @Override // com.huawei.uikit.hwcommon.anim.HwGradientAnimatorMgr.OnAnimatorListener
    public void onAnimationEnd(Animator animator, String str) {
        UpdateColorCallback updateColorCallback;
        ColorStateList colorStateList;
        UpdateColorCallback updateColorCallback2;
        ColorStateList colorStateList2;
        if (this.e && "icon_color".equals(str) && (updateColorCallback2 = this.f) != null && (colorStateList2 = this.b) != null) {
            updateColorCallback2.onEnd(colorStateList2);
        }
        if (this.g && "text_color".equals(str) && (updateColorCallback = this.h) != null && (colorStateList = this.j) != null) {
            updateColorCallback.onEnd(colorStateList);
        }
        if ("background_color".equals(str)) {
            ColorStateList backgroundTintList = this.c.getBackgroundTintList();
            UpdateColorCallback updateColorCallback3 = this.d;
            if (updateColorCallback3 == null || backgroundTintList == null) {
                return;
            }
            updateColorCallback3.onEnd(backgroundTintList);
        }
    }

    @Override // com.huawei.uikit.hwcommon.anim.HwGradientAnimatorMgr.OnAnimatorListener
    public void onAnimationStart(Animator animator, String str) {
        UpdateColorCallback updateColorCallback;
        UpdateColorCallback updateColorCallback2;
        UpdateColorCallback updateColorCallback3;
        if (this.e && "icon_color".equals(str) && (updateColorCallback3 = this.f) != null && this.b != null) {
            updateColorCallback3.onStart();
        }
        if (this.g && "text_color".equals(str) && (updateColorCallback2 = this.h) != null && this.j != null) {
            updateColorCallback2.onStart();
        }
        if (!"background_color".equals(str) || (updateColorCallback = this.d) == null) {
            return;
        }
        updateColorCallback.onStart();
    }

    @Override // com.huawei.uikit.hwcommon.anim.HwGradientAnimatorMgr.OnAnimatorListener
    public void onAnimationUpdate(Animator animator, String str, int i) {
        UpdateColorCallback updateColorCallback;
        UpdateColorCallback updateColorCallback2;
        if (this.e && "icon_color".equals(str) && (updateColorCallback2 = this.f) != null && this.b != null) {
            updateColorCallback2.onUpdate(i);
        }
        if (this.g && "text_color".equals(str) && (updateColorCallback = this.h) != null && this.j != null) {
            updateColorCallback.onUpdate(i);
        }
        if ("background_color".equals(str)) {
            c(i);
        }
    }

    @Override // com.huawei.uikit.hwcommon.anim.HwGradientAnimatorMgr.OnAnimatorListener
    public boolean onPrepareAnimation(int[] iArr, int[] iArr2, int i, int i2, Map<String, Pair<Integer, Integer>> map) {
        int i3;
        int i4;
        boolean z;
        int i5;
        int i6;
        boolean z2;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        if (!this.f10623a) {
            return false;
        }
        boolean d = d(iArr2);
        boolean d2 = d(iArr);
        if ((!d || d2) && (d || !d2)) {
            c(i);
            return false;
        }
        boolean z3 = i != i2;
        if (!this.e || (colorStateList2 = this.b) == null || this.f == null) {
            i3 = 0;
            i4 = 0;
            z = false;
        } else {
            i3 = a(iArr2, colorStateList2);
            i4 = a(iArr, this.b);
            z = i3 != i4;
        }
        if (!this.g || (colorStateList = this.j) == null || this.h == null) {
            i5 = 0;
            i6 = 0;
            z2 = false;
        } else {
            i6 = a(iArr2, colorStateList);
            i5 = a(iArr, this.j);
            z2 = i6 != i5;
        }
        if (!z3 && !z && !z2) {
            c(i);
            return false;
        }
        if (z3) {
            map.put("background_color", new Pair<>(Integer.valueOf(i2), Integer.valueOf(i)));
        }
        if (z) {
            map.put("icon_color", new Pair<>(Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        if (z2) {
            map.put("text_color", new Pair<>(Integer.valueOf(i6), Integer.valueOf(i5)));
        }
        return true;
    }

    private boolean d(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == 16842908) {
                z = true;
            }
            if (i == 16842909) {
                z2 = true;
            }
        }
        return z && z2;
    }

    private void c(int i) {
        UpdateColorCallback updateColorCallback = this.d;
        if (updateColorCallback == null) {
            this.c.getBackground().setTint(i);
        } else {
            updateColorCallback.onUpdate(i);
        }
        this.c.invalidate();
    }
}
