package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;

/* loaded from: classes7.dex */
public final class rsn {
    private static final String d = "BloodOxygenHelper";

    public static int e() {
        return R.style.privacy_blood_oxygen_value_normal;
    }

    public static boolean h(int i) {
        return i != Integer.MIN_VALUE && i >= 2500;
    }

    public static SpannableString dQG_(Context context, String str, String str2, int i, int i2) {
        if (context == null) {
            LogUtil.h(d, "getSpannableString context == null");
            return new SpannableString("");
        }
        return UnitUtil.bCR_(context, str, str2, i, i2);
    }

    public static int a(int i) {
        int a2 = scg.a(i);
        return a2 == 2 ? R.style.privacy_blood_oxygen_value_Low : a2 == 1 ? R.style.privacy_blood_oxygen_value_little_Low : R.style.privacy_blood_oxygen_value_normal;
    }

    public static int b(int i) {
        int a2 = scg.a(i);
        return a2 == 2 ? R.style.privacy_blood_oxygen_num_low : a2 == 1 ? R.style.privacy_blood_oxygen_num_little_Low : R.style.privacy_blood_oxygen_num_normal;
    }

    public static int c(int i) {
        int a2 = scg.a(i);
        return a2 == 2 ? R.style.privacy_blood_oxygen_unit_low : a2 == 1 ? R.style.privacy_blood_oxygen_unit_little_Low : R.style.privacy_blood_oxygen_unit_normal;
    }

    public static int d(int i) {
        int intValue = scg.e.get(Integer.valueOf(i)).intValue();
        return intValue == 3 ? R.style.privacy_highland_risk_num_high : intValue == 2 ? R.style.privacy_highland_risk_num_medium : intValue == 1 ? R.style.privacy_highland_risk_num_Low : R.style.privacy_highland_risk_num_normal;
    }

    public static int i(int i) {
        int intValue = scg.e.get(Integer.valueOf(i)).intValue();
        return intValue == 3 ? R.style.privacy_highland_risk_unit_high : intValue == 2 ? R.style.privacy_highland_risk_unit_medium : intValue == 1 ? R.style.privacy_highland_risk_unit_Low : R.style.privacy_highland_risk_unit_normal;
    }

    public static String e(int i) {
        Context context = BaseApplication.getContext();
        if (i == 1002) {
            return context.getString(R$string.IDS_hw_health_blood_oxygen_lower_spo2);
        }
        return context.getString(R$string.IDS_hw_show_main_permission_blood_oxygen);
    }

    public static Drawable dQF_(int i) {
        Context context = BaseApplication.getContext();
        int intValue = scg.e.get(Integer.valueOf(i)).intValue();
        if (intValue == 3) {
            return context.getDrawable(R.drawable._2131431726_res_0x7f0b112e);
        }
        if (intValue == 2) {
            return context.getDrawable(R.drawable._2131431727_res_0x7f0b112f);
        }
        if (intValue == 1) {
            return context.getDrawable(R.drawable._2131431728_res_0x7f0b1130);
        }
        return context.getDrawable(R.drawable._2131431729_res_0x7f0b1131);
    }
}
