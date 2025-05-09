package com.github.mikephil.charting.utils;

import android.content.res.Resources;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ColorTemplate {
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] LIBERTY_COLORS = {Color.rgb(a.w, 248, 246), Color.rgb(148, a.K, a.K), Color.rgb(136, 180, 187), Color.rgb(118, 174, 175), Color.rgb(42, 109, OldToNewMotionPath.SPORT_TYPE_TENNIS)};
    public static final int[] JOYFUL_COLORS = {Color.rgb(217, 80, OldToNewMotionPath.SPORT_TYPE_PILATES), Color.rgb(HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE, 149, 7), Color.rgb(HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE, 247, 120), Color.rgb(106, MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY, 134), Color.rgb(53, 194, a.A)};
    public static final int[] PASTEL_COLORS = {Color.rgb(64, 89, 128), Color.rgb(149, MachineControlPointResponse.OP_CODE_EXTENSION_SET_UNLOCK_CODE, 124), Color.rgb(217, 184, MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY), Color.rgb(191, 134, 134), Color.rgb(179, 48, 80)};
    public static final int[] COLORFUL_COLORS = {Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0), Color.rgb(106, 150, 31), Color.rgb(179, 100, 53)};
    public static final int[] VORDIPLOM_COLORS = {Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, a.z, 140), Color.rgb(140, FitnessSleepType.HW_FITNESS_WAKE, 255), Color.rgb(255, 140, 157)};
    public static final int[] MATERIAL_COLORS = {rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};

    public static int colorWithAlpha(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | ((i2 & 255) << 24);
    }

    public static int rgb(String str) {
        int parseLong = (int) Long.parseLong(str.replace("#", ""), 16);
        return Color.rgb((parseLong >> 16) & 255, (parseLong >> 8) & 255, parseLong & 255);
    }

    public static int getHoloBlue() {
        return Color.rgb(51, 181, 229);
    }

    public static List<Integer> createColors(Resources resources, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(resources.getColor(i)));
        }
        return arrayList;
    }

    public static List<Integer> createColors(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }
}
