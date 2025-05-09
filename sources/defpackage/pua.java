package defpackage;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class pua {
    public static boolean b(int i) {
        return i >= 60 && i < 80;
    }

    public static boolean d(int i) {
        return i >= 30 && i < 60;
    }

    public static boolean e(int i) {
        return i >= 80 && i <= 99;
    }

    public static boolean j(int i) {
        return i > 0 && i < 30;
    }

    public static String c(int i) {
        Context context = BaseApplication.getContext();
        if (i > 0 && i < 30) {
            return context.getString(R$string.IDS_hw_pressure_relaxed);
        }
        if (i >= 30 && i < 60) {
            return context.getString(R$string.IDS_hw_pressure_normal);
        }
        if (i >= 60 && i < 80) {
            return context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_3);
        }
        if (i >= 80 && i < 100) {
            return context.getString(R$string.IDS_hw_pressure_highly);
        }
        LogUtil.a("UiUtils", "invalid stress value, please check.");
        return "";
    }

    public static String[] a(int i) {
        Context context = BaseApplication.getContext();
        String string = context.getString(R$string.IDS_settings_one_level_menu_settings_item_text_id14);
        String string2 = context.getString(R$string.IDS_hw_pressure_ratio);
        if (i != 10001) {
            string2 = string + string2;
            if (i == 10002) {
                string = context.getString(R$string.IDS_hw_pressure_ratio_in_week);
            } else if (i == 10003) {
                string = context.getString(R$string.IDS_hw_pressure_ratio_in_month);
            } else if (i == 10004) {
                string = context.getString(R$string.IDS_hw_pressure_ration_in_year);
            } else {
                LogUtil.h("UiUtils", "updateDescForPieChart failed, cause type = ", Integer.valueOf(i), " is unknown!");
                string = "";
            }
        }
        return new String[]{string, string2};
    }

    public static List<nkz> e(List<sdf> list) {
        int[] iArr = new int[4];
        Iterator<sdf> it = list.iterator();
        while (it.hasNext()) {
            int c = it.next().c();
            if (j(c)) {
                iArr[3] = iArr[3] + 1;
            } else if (d(c)) {
                iArr[2] = iArr[2] + 1;
            } else if (b(c)) {
                iArr[1] = iArr[1] + 1;
            } else if (e(c)) {
                iArr[0] = iArr[0] + 1;
            } else {
                LogUtil.h("UiUtils", "get chart items for pressure invalid pressure value = " + c);
            }
        }
        List<String> c2 = c();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Context context = BaseApplication.getContext();
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298936_res_0x7f090a78)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298937_res_0x7f090a79)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298940_res_0x7f090a7c)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298941_res_0x7f090a7d)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298944_res_0x7f090a80)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298945_res_0x7f090a81)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298948_res_0x7f090a84)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298949_res_0x7f090a85)));
        ArrayList arrayList3 = new ArrayList(4);
        int i = 0;
        while (i < 4) {
            arrayList3.add(new nkz(i < c2.size() ? c2.get(i) : "", iArr[i], i < arrayList2.size() ? ((Integer) arrayList2.get(i)).intValue() : 0, i < arrayList.size() ? ((Integer) arrayList.get(i)).intValue() : 0));
            i++;
        }
        return arrayList3;
    }

    private static List<String> c() {
        String e = UnitUtil.e(1.0d, 1, 0);
        String e2 = UnitUtil.e(29.0d, 1, 0);
        String e3 = UnitUtil.e(30.0d, 1, 0);
        String e4 = UnitUtil.e(59.0d, 1, 0);
        String e5 = UnitUtil.e(60.0d, 1, 0);
        String e6 = UnitUtil.e(79.0d, 1, 0);
        String e7 = UnitUtil.e(80.0d, 1, 0);
        String e8 = UnitUtil.e(99.0d, 1, 0);
        Context context = BaseApplication.getContext();
        String string = context.getString(R$string.IDS_hw_pressure_grade_range, e, e2);
        String string2 = context.getString(R$string.IDS_hw_pressure_grade_range, e3, e4);
        String string3 = context.getString(R$string.IDS_hw_pressure_grade_range, e5, e6);
        String string4 = context.getString(R$string.IDS_hw_pressure_grade_range, e7, e8);
        if (LanguageUtil.bc(context)) {
            string = context.getString(R$string.IDS_hw_pressure_grade_range, e2, e);
            string2 = context.getString(R$string.IDS_hw_pressure_grade_range, e4, e3);
            string3 = context.getString(R$string.IDS_hw_pressure_grade_range, e6, e5);
            string4 = context.getString(R$string.IDS_hw_pressure_grade_range, e8, e7);
        }
        String string5 = context.getString(R$string.IDS_hw_pressure_relaxed);
        String string6 = context.getString(R$string.IDS_hw_pressure_normal);
        String string7 = context.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_3);
        String string8 = context.getString(R$string.IDS_hw_pressure_highly);
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(string8 + " " + string4);
        arrayList.add(string7 + " " + string3);
        arrayList.add(string6 + " " + string2);
        arrayList.add(string5 + " " + string);
        return arrayList;
    }

    public static SpannableStringBuilder dtI_(List<sdf> list, String str, int i) {
        Context context = BaseApplication.getContext();
        if (i != 10001) {
            int e = pwr.e(list);
            return dtH_(str, UnitUtil.e(e, 1, 0), sdh.e(e));
        }
        float c = nsn.c();
        String string = context.getString(R$string.IDS_hw_pressure_and_trend_curves);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) string).append((CharSequence) System.lineSeparator()).append((CharSequence) pwr.b(list));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color._2131299241_res_0x7f090ba9)), 0, string.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) (c * 14.0f), true), 0, string.length(), 33);
        return spannableStringBuilder;
    }

    public static SpannableStringBuilder dtH_(String str, String str2, String str3) {
        float c = nsn.c();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str).append((CharSequence) System.lineSeparator()).append((CharSequence) str2).append((CharSequence) " ").append((CharSequence) str3);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131299241_res_0x7f090ba9)), 0, str.length(), 33);
        int i = (int) (c * 14.0f);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i, true), 0, str.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i, true), spannableStringBuilder.length() - str3.length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public static int b(ArrayList<HiStressMetaData> arrayList) {
        Iterator<HiStressMetaData> it = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            int fetchStressScore = it.next().fetchStressScore();
            if (fetchStressScore > 0) {
                i2 += fetchStressScore;
                i++;
            }
        }
        LogUtil.a("UiUtils", "getAvg count = ", Integer.valueOf(i));
        if (i > 0) {
            return i2 / i;
        }
        return 0;
    }

    public static int d(List<sdf> list) {
        Iterator<sdf> it = list.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            int c = it.next().c();
            if (c > 0) {
                i2 += c;
                i++;
            }
        }
        LogUtil.a("UiUtils", "getAvg count = ", Integer.valueOf(i));
        if (i > 0) {
            return i2 / i;
        }
        return 0;
    }
}
