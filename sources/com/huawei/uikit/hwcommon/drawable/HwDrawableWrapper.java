package com.huawei.uikit.hwcommon.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.DrawableWrapper;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class HwDrawableWrapper extends DrawableWrapper {

    /* renamed from: a, reason: collision with root package name */
    private OnStateChangedListener f10626a;
    private ColorStateList b;
    private int[] d;

    public interface OnStateChangedListener {
        void onStateChanged(int[] iArr, int[] iArr2, int i, int i2);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.b.isStateful();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        if (getDrawable() != null) {
            int colorForState = this.b.getColorForState(iArr, 0);
            int colorForState2 = this.b.getColorForState(this.d, 0);
            OnStateChangedListener onStateChangedListener = this.f10626a;
            if (onStateChangedListener != null) {
                onStateChangedListener.onStateChanged(iArr, this.d, colorForState, colorForState2);
            }
        }
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        int[] state = getState();
        this.d = Arrays.copyOf(state, state.length);
        return super.setState(iArr);
    }
}
