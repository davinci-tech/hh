package defpackage;

import android.content.Context;
import com.huawei.ui.commonui.R$color;

/* loaded from: classes6.dex */
public class nrm {

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f15462a = {70.0f, 80.0f, 90.0f, 100.0f};
    private static final float[] d = {68.0f, 76.0f, 84.0f, 92.0f, 100.0f};

    public static boolean b(int i) {
        return i <= 100 && i >= 70;
    }

    public static int e(double d2) {
        return d2 < 70.0d ? (int) ((d2 / 70.0d) * 62.0d) : d2 < 90.0d ? ((int) (((d2 - 70.0d) * 266.0d) / 20.0d)) + 64 : (int) (332 + ((128 * (d2 - 90.0d)) / 10.0d));
    }

    public static int d(Context context, int i, boolean z) {
        if (context == null || !b(i)) {
            return 0;
        }
        if (z) {
            if (i >= 90) {
                return context.getResources().getColor(R$color.blood_oxygen_level_deep_green_color);
            }
            return context.getResources().getColor(R$color.blood_oxygen_level_orange_color);
        }
        if (i >= 90) {
            return context.getResources().getColor(R$color.blood_oxygen_level_deep_green_selected_color);
        }
        return context.getResources().getColor(R$color.blood_oxygen_level_orange_selected_color);
    }

    public static int a(Context context, int i) {
        if (context == null || !b(i)) {
            return 0;
        }
        if (i >= 90) {
            return context.getResources().getColor(R$color.blood_oxygen_level_deep_green_selected_color);
        }
        return context.getResources().getColor(R$color.blood_oxygen_level_orange_selected_color);
    }

    public static float[] e(boolean z) {
        if (z) {
            return (float[]) d.clone();
        }
        return (float[]) f15462a.clone();
    }
}
