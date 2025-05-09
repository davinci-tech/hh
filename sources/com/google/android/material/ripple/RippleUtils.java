package com.google.android.material.ripple;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.StateSet;
import androidx.core.graphics.ColorUtils;

/* loaded from: classes2.dex */
public class RippleUtils {
    public static final boolean USE_FRAMEWORK_RIPPLE = true;
    private static final int[] PRESSED_STATE_SET = {R.attr.state_pressed};
    private static final int[] HOVERED_FOCUSED_STATE_SET = {R.attr.state_hovered, R.attr.state_focused};
    private static final int[] FOCUSED_STATE_SET = {R.attr.state_focused};
    private static final int[] HOVERED_STATE_SET = {R.attr.state_hovered};
    private static final int[] SELECTED_PRESSED_STATE_SET = {R.attr.state_selected, R.attr.state_pressed};
    private static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET = {R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};
    private static final int[] SELECTED_FOCUSED_STATE_SET = {R.attr.state_selected, R.attr.state_focused};
    private static final int[] SELECTED_HOVERED_STATE_SET = {R.attr.state_selected, R.attr.state_hovered};
    private static final int[] SELECTED_STATE_SET = {R.attr.state_selected};

    private RippleUtils() {
    }

    public static ColorStateList convertToRippleDrawableColor(ColorStateList colorStateList) {
        if (USE_FRAMEWORK_RIPPLE) {
            return new ColorStateList(new int[][]{SELECTED_STATE_SET, StateSet.NOTHING}, new int[]{getColorForState(colorStateList, SELECTED_PRESSED_STATE_SET), getColorForState(colorStateList, PRESSED_STATE_SET)});
        }
        int[] iArr = SELECTED_PRESSED_STATE_SET;
        int colorForState = getColorForState(colorStateList, iArr);
        int[] iArr2 = SELECTED_HOVERED_FOCUSED_STATE_SET;
        int colorForState2 = getColorForState(colorStateList, iArr2);
        int[] iArr3 = SELECTED_FOCUSED_STATE_SET;
        int colorForState3 = getColorForState(colorStateList, iArr3);
        int[] iArr4 = SELECTED_HOVERED_STATE_SET;
        int colorForState4 = getColorForState(colorStateList, iArr4);
        int[] iArr5 = SELECTED_STATE_SET;
        int[] iArr6 = PRESSED_STATE_SET;
        int colorForState5 = getColorForState(colorStateList, iArr6);
        int[] iArr7 = HOVERED_FOCUSED_STATE_SET;
        int colorForState6 = getColorForState(colorStateList, iArr7);
        int[] iArr8 = FOCUSED_STATE_SET;
        int colorForState7 = getColorForState(colorStateList, iArr8);
        int[] iArr9 = HOVERED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, iArr2, iArr3, iArr4, iArr5, iArr6, iArr7, iArr8, iArr9, StateSet.NOTHING}, new int[]{colorForState, colorForState2, colorForState3, colorForState4, 0, colorForState5, colorForState6, colorForState7, getColorForState(colorStateList, iArr9), 0});
    }

    private static int getColorForState(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return USE_FRAMEWORK_RIPPLE ? doubleAlpha(colorForState) : colorForState;
    }

    private static int doubleAlpha(int i) {
        return ColorUtils.setAlphaComponent(i, Math.min(Color.alpha(i) * 2, 255));
    }
}
