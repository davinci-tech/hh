package defpackage;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.open.data.model.IBloodSugar;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.utils.StringUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class deb extends HealthData implements IBloodSugar {
    private int c = 0;
    private float d;
    private int e;

    /* JADX WARN: Removed duplicated region for block: B:4:0x001b A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0019 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static short b(int r0, float r1) {
        /*
            switch(r0) {
                case 2008: goto L12;
                case 2009: goto La;
                case 2010: goto L12;
                case 2011: goto La;
                case 2012: goto L12;
                case 2013: goto La;
                default: goto L3;
            }
        L3:
            r0 = 1090519040(0x41000000, float:8.0)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 >= 0) goto L1b
            goto L19
        La:
            r0 = 1091357901(0x410ccccd, float:8.8)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 >= 0) goto L1b
            goto L19
        L12:
            r0 = 1086534451(0x40c33333, float:6.1)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 >= 0) goto L1b
        L19:
            r0 = 1
            goto L1c
        L1b:
            r0 = 2
        L1c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.deb.b(int, float):short");
    }

    private static boolean d(int i) {
        return i >= 8 && i < 10;
    }

    private static boolean f(int i) {
        return i >= 12 && i < 16;
    }

    private static boolean g(int i) {
        return i >= 5 && i < 8;
    }

    private static boolean h(int i) {
        return i >= 0 && i < 5;
    }

    private static boolean i(int i) {
        return i >= 18 && i < 20;
    }

    private static boolean j(int i) {
        return i >= 16 && i < 18;
    }

    private static boolean l(int i) {
        return i >= 20 && i < 24;
    }

    private static boolean m(int i) {
        return i >= 10 && i < 12;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IBloodSugar
    public float getBloodSugar() {
        return this.d;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IBloodSugar
    public void setBloodSugar(float f) {
        this.d = f;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IBloodSugar
    public void setSequenceNumber(int i) {
        this.e = i;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IBloodSugar
    public int getSequenceNumber() {
        return this.e;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IBloodSugar
    public int getTimePeriod() {
        return this.c;
    }

    public static short e(int i, float f) {
        if (b(f)) {
            return HealthData.INVALID_VALUES_SHORT;
        }
        if (f < 4.4f) {
            return (short) 0;
        }
        return b(i, f);
    }

    public static int e(long j) {
        int c = c(j);
        if (!h(c)) {
            if (g(c)) {
                return 2008;
            }
            if (d(c)) {
                return 2009;
            }
            if (m(c)) {
                return 2010;
            }
            if (f(c)) {
                return 2011;
            }
            if (j(c)) {
                return 2012;
            }
            if (i(c)) {
                return 2013;
            }
            if (l(c)) {
                return 2014;
            }
            LogUtil.h("UIHLH_OPEN_BLOODSUGAR", "getGlucoseSubType period is not exist", Integer.valueOf(c));
        }
        return 2015;
    }

    public static int c(long j) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(j);
        return calendar.get(11);
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public String toString() {
        return "BloodSugar [mBloodSugar=" + this.d + "mTimePeriod=" + this.c + super.toString() + "]";
    }

    public static int c(Context context, int i, float f) {
        if (context == null || f < 0.0f || f > 33.0f || Math.abs(f - Float.MIN_VALUE) < 1.0E-6d) {
            return 1000;
        }
        float floatValue = new BigDecimal(f).setScale(1, RoundingMode.HALF_UP).floatValue();
        if (floatValue <= 3.9f) {
            return 1001;
        }
        if (floatValue < 4.4f) {
            return 1002;
        }
        if ((floatValue > 7.0f || i != 2008) && (floatValue > 10.0f || i == 2008)) {
            return floatValue < 13.9f ? 1004 : 1005;
        }
        return 1003;
    }

    private static boolean b(float f) {
        return f < 0.0f || f > 100.0f || ((double) Math.abs(f - Float.MIN_VALUE)) < 1.0E-6d;
    }

    public static void b(String str, String str2) {
        SharedPreferenceManager.c("preference_module_blood_sugar", str, str2);
    }

    public static String d(String str) {
        return SharedPreferenceManager.e("preference_module_blood_sugar", str, "");
    }

    public static float[] b() {
        float[] fArr = {10.0f, 3.9f};
        String e = SharedPreferenceManager.e("preference_module_blood_sugar", "BLOOD_SUGAR_CONTINUE_MAX_THRESHOLD", (String) null);
        if (StringUtils.i(e)) {
            try {
                fArr[0] = Float.parseFloat(e);
            } catch (NumberFormatException e2) {
                LogUtil.b("UIHLH_OPEN_BLOODSUGAR", "Fail to parse blood sugar continue thresholds", e2);
            }
        }
        String e3 = SharedPreferenceManager.e("preference_module_blood_sugar", "BLOOD_SUGAR_CONTINUE_MIN_THRESHOLD", (String) null);
        if (StringUtils.i(e3)) {
            try {
                fArr[1] = Float.parseFloat(e3);
            } catch (NumberFormatException e4) {
                LogUtil.b("UIHLH_OPEN_BLOODSUGAR", "Fail to parse blood sugar continue thresholds", e4);
            }
        }
        return fArr;
    }

    public static List<Float> c(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(4.4f));
        if (i == 2008) {
            arrayList.add(Float.valueOf(7.05f));
        } else {
            arrayList.add(Float.valueOf(10.05f));
        }
        arrayList.add(Float.valueOf(33.0f));
        return arrayList;
    }

    public static int[] e() {
        Context context = BaseApplication.getContext();
        return new int[]{ContextCompat.getColor(context, R.color._2131296798_res_0x7f09021e), ContextCompat.getColor(context, R.color._2131296800_res_0x7f090220), ContextCompat.getColor(context, R.color._2131296796_res_0x7f09021c), ContextCompat.getColor(context, R.color._2131296797_res_0x7f09021d), ContextCompat.getColor(context, R.color._2131296799_res_0x7f09021f), ContextCompat.getColor(context, R.color._2131296795_res_0x7f09021b)};
    }

    public static void d(HealthTextView healthTextView, int i) {
        if (healthTextView == null) {
            LogUtil.h("UIHLH_OPEN_BLOODSUGAR", "setBloodSugarLevelTextAndColor, valueLevelView is null!");
        }
        Resources resources = BaseApplication.getContext().getResources();
        switch (i) {
            case 1001:
                healthTextView.setText(resources.getString(R.string._2130843962_res_0x7f02193a));
                healthTextView.setTextColor(resources.getColor(R.color._2131296797_res_0x7f09021d));
                break;
            case 1002:
                healthTextView.setText(resources.getString(R.string._2130843960_res_0x7f021938));
                healthTextView.setTextColor(resources.getColor(R.color._2131296797_res_0x7f09021d));
                break;
            case 1003:
                healthTextView.setText(resources.getString(R.string._2130843959_res_0x7f021937));
                healthTextView.setTextColor(resources.getColor(R.color._2131296799_res_0x7f09021f));
                break;
            case 1004:
                healthTextView.setText(resources.getString(R.string._2130843961_res_0x7f021939));
                healthTextView.setTextColor(resources.getColor(R.color._2131296795_res_0x7f09021b));
                break;
            case 1005:
                healthTextView.setText(resources.getString(R.string._2130843966_res_0x7f02193e));
                healthTextView.setTextColor(resources.getColor(R.color._2131296795_res_0x7f09021b));
                break;
        }
    }

    public static String b(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        switch (i) {
            case 1000:
                return resources.getString(R.string._2130850004_res_0x7f0230d4);
            case 1001:
                return resources.getString(R.string._2130843962_res_0x7f02193a);
            case 1002:
                return resources.getString(R.string._2130843960_res_0x7f021938);
            case 1003:
                return resources.getString(R.string._2130843959_res_0x7f021937);
            case 1004:
                return resources.getString(R.string._2130843961_res_0x7f021939);
            case 1005:
                return resources.getString(R.string._2130843966_res_0x7f02193e);
            default:
                return "";
        }
    }

    public static String a(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        if (i != 2106) {
            switch (i) {
                case 2008:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_before_breakfast);
                case 2009:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_after_breakfast);
                case 2010:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_before_lunch);
                case 2011:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_after_lunch);
                case 2012:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_before_dinner);
                case 2013:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_after_dinner);
                case 2014:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_before_sleep);
                case 2015:
                    return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_early_morning);
                default:
                    LogUtil.b("UIHLH_OPEN_BLOODSUGAR", "unsupported timePeriod = ", Integer.valueOf(i));
                    return "";
            }
        }
        return resources.getString(R.string.IDS_hw_show_healthdata_bloodsugar_random_time);
    }

    public static String e(int i) {
        Context context = BaseApplication.getContext();
        Float valueOf = Float.valueOf(10.0f);
        Float valueOf2 = Float.valueOf(4.4f);
        switch (i) {
            case 2008:
                return context.getString(R.string._2130845924_res_0x7f0220e4, valueOf2, Float.valueOf(7.0f));
            case 2009:
                return context.getString(R.string._2130845925_res_0x7f0220e5, valueOf2, valueOf);
            case 2010:
                return context.getString(R.string._2130845926_res_0x7f0220e6, valueOf2, valueOf);
            case 2011:
                return context.getString(R.string._2130845927_res_0x7f0220e7, valueOf2, valueOf);
            default:
                return c(i, context);
        }
    }

    private static String c(int i, Context context) {
        Float valueOf = Float.valueOf(10.0f);
        Float valueOf2 = Float.valueOf(4.4f);
        switch (i) {
            case 2012:
                return context.getString(R.string._2130845928_res_0x7f0220e8, valueOf2, valueOf);
            case 2013:
                return context.getString(R.string._2130845929_res_0x7f0220e9, valueOf2, valueOf);
            case 2014:
                return context.getString(R.string._2130845930_res_0x7f0220ea, valueOf2, valueOf);
            case 2015:
                return context.getString(R.string._2130845931_res_0x7f0220eb, valueOf2, valueOf);
            default:
                return context.getString(R.string._2130845934_res_0x7f0220ee, valueOf2, valueOf);
        }
    }
}
