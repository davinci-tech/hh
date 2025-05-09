package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseIntArray;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class qqy {
    private static SparseIntArray e = new SparseIntArray();

    private static Resources dHB_() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("HealthAnalysis", "getResources Context is null");
            return null;
        }
        return context.getResources();
    }

    private static void d() {
        e.clear();
        e.put(0, 0);
        e.put(1, 1);
        e.put(2, 2);
        e.put(4, 3);
        e.put(8, 6);
        e.put(5, 4);
        e.put(10, 8);
        e.put(6, 5);
        e.put(9, 7);
    }

    private static void a() {
        e.clear();
        e.put(0, 0);
        e.put(1, 1);
        e.put(2, 2);
        e.put(17, 10);
        e.put(33, 19);
        e.put(18, 11);
        e.put(34, 20);
        e.put(4, 3);
        e.put(8, 6);
        e.put(20, 12);
        e.put(36, 21);
        e.put(24, 15);
        e.put(40, 24);
        e.put(5, 4);
        e.put(10, 8);
        e.put(6, 5);
        e.put(22, 14);
        e.put(38, 23);
        e.put(9, 7);
        e.put(25, 16);
        e.put(41, 25);
        e.put(21, 13);
        e.put(37, 22);
        e.put(26, 17);
        e.put(42, 26);
        e.put(16, 9);
        e.put(32, 18);
    }

    private static String c(String[] strArr, int i) {
        int length = strArr.length;
        return (length > 0 && i > 0 && i <= length) ? strArr[i - 1] : "";
    }

    public static String a(int i, int i2) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBodyMassIndex Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_hw_weight_bmi_grade_low), dHB_.getString(R$string.IDS_hw_weight_details_grade_standard), dHB_.getString(R$string.IDS_hw_weight_details_grade_super_weight), dHB_.getString(R$string.IDS_hw_weight_details_grade_fat)}, i2);
        }
        if (i != 1) {
            return i != 2 ? "" : c(dHB_.getStringArray(R.array._2130968594_res_0x7f040012), i2);
        }
        return c(dHB_.getStringArray(R.array._2130968605_res_0x7f04001d), i2);
    }

    public static String f(int i, int i2) {
        return c(i, i2, 0);
    }

    public static String c(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getFatRate Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_weight_body_data_grade_low), dHB_.getString(R$string.IDS_weight_body_data_grade_standard), dHB_.getString(R$string.IDS_weight_body_data_slightly_high), dHB_.getString(R$string.IDS_weight_body_data_high)}, i2);
        }
        if (i == 1) {
            if (doj.d(i3) && i2 == 4) {
                return dHB_.getString(R$string.IDS_result_desc_fat_rate_child_high);
            }
            return c(dHB_.getStringArray(R.array._2130968589_res_0x7f04000d), i2);
        }
        if (i != 2) {
            return "";
        }
        if (doj.d(i3) && i2 == 4) {
            return dHB_.getString(R$string.IDS_suggest_fat_rate_child_high);
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968595_res_0x7f040013);
        stringArray[2] = String.format(stringArray[2], 30);
        return c(stringArray, i2);
    }

    public static String c(int i, int i2) {
        return d(i, i2, 0);
    }

    public static String d(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getFatFree Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{a(dHB_.getString(R$string.IDS_weight_less), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_low)), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_standard), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_fat_free_mass_high)}, i2);
        }
        if (i == 1) {
            String[] stringArray = dHB_.getStringArray(R.array._2130968590_res_0x7f04000e);
            if (doj.d(i3)) {
                String[] stringArray2 = dHB_.getStringArray(R.array._2130968612_res_0x7f040024);
                return c(new String[]{stringArray2[0], stringArray[1], stringArray2[1]}, i2);
            }
            return c(stringArray, i2);
        }
        if (i != 2) {
            return "";
        }
        String[] stringArray3 = dHB_.getStringArray(R.array._2130968596_res_0x7f040014);
        stringArray3[0] = String.format(stringArray3[0], 2, 3, 60);
        stringArray3[1] = String.format(stringArray3[1], 3, 30);
        return c(stringArray3, i2);
    }

    public static String h(int i, int i2) {
        return b(i, i2, 0);
    }

    public static String b(int i, int i2, int i3) {
        String[] stringArray;
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getMuscleMass Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_details_sleep_grade_low), dHB_.getString(R$string.IDS_hw_weight_details_grade_standard), dHB_.getString(R$string.IDS_weight_more)}, i2);
        }
        if (i == 1) {
            if (doj.d(i3)) {
                stringArray = dHB_.getStringArray(R.array._2130968613_res_0x7f040025);
            } else {
                stringArray = dHB_.getStringArray(R.array._2130968606_res_0x7f04001e);
            }
            return c(stringArray, i2);
        }
        if (i != 2) {
            return "";
        }
        String[] stringArray2 = dHB_.getStringArray(R.array._2130968597_res_0x7f040015);
        String str = stringArray2[0];
        String str2 = stringArray2[1];
        return c(new String[]{str, str2, str2}, i2);
    }

    public static String l(int i, int i2) {
        return f(i, i2, 0);
    }

    public static String f(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getSkeletalMuscle Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{a(dHB_.getString(R$string.IDS_weight_less), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_low)), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_standard), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_skeletal_muscle_mass_high)}, i2);
        }
        if (i == 1) {
            String[] stringArray = dHB_.getStringArray(R.array._2130968607_res_0x7f04001f);
            if (doj.d(i3)) {
                String[] stringArray2 = dHB_.getStringArray(R.array._2130968614_res_0x7f040026);
                return c(new String[]{stringArray2[0], stringArray[1], stringArray2[1]}, i2);
            }
            return c(stringArray, i2);
        }
        if (i != 2) {
            return "";
        }
        if (doj.d(i3)) {
            String[] stringArray3 = dHB_.getStringArray(R.array._2130968616_res_0x7f040028);
            String str = stringArray3[0];
            String str2 = stringArray3[1];
            return c(new String[]{str, str2, str2}, i2);
        }
        String[] stringArray4 = dHB_.getStringArray(R.array._2130968598_res_0x7f040016);
        stringArray4[0] = String.format(stringArray4[0], 3, 4, 7, 8);
        stringArray4[1] = String.format(stringArray4[1], 3, 4, 30, 2, 3);
        stringArray4[2] = String.format(stringArray4[2], 3, 4, 30, 60);
        return c(stringArray4, i2);
    }

    public static String k(int i, int i2) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getRelativeAppendicularSkeletalMuscle Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_details_sleep_grade_low), dHB_.getString(R$string.IDS_hw_weight_details_grade_standard), dHB_.getString(R$string.IDS_weight_higher)}, i2);
        }
        if (i == 1) {
            return c(dHB_.getStringArray(R.array._2130968591_res_0x7f04000f), i2);
        }
        if (i != 2) {
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968599_res_0x7f040017);
        stringArray[0] = String.format(stringArray[0], 3, 4);
        stringArray[1] = String.format(stringArray[1], 4, 7, 9);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            stringArray[1] = "";
        }
        stringArray[2] = String.format(stringArray[2], 3, 4, 30, 60);
        return c(stringArray, i2);
    }

    public static String n(int i, int i2) {
        return j(i, i2, 0);
    }

    public static String j(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getVisceralFatGrade Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_weight_body_data_standard_health), dHB_.getString(R$string.IDS_weight_body_data_standard_critical), dHB_.getString(R$string.IDS_hw_health_index_slightly_high), dHB_.getString(R$string.IDS_hw_health_index_high)}, i2);
        }
        if (i == 1) {
            String[] stringArray = dHB_.getStringArray(R.array._2130968592_res_0x7f040010);
            if (doj.d(i3)) {
                String[] stringArray2 = dHB_.getStringArray(R.array._2130968615_res_0x7f040027);
                return c(new String[]{stringArray2[0], stringArray2[1], stringArray[2], stringArray2[2]}, i2);
            }
            return c(stringArray, i2);
        }
        if (i != 2) {
            return "";
        }
        if (doj.d(i3)) {
            return c(dHB_.getStringArray(R.array._2130968617_res_0x7f040029), i2);
        }
        String[] stringArray3 = dHB_.getStringArray(R.array._2130968576_res_0x7f040000);
        stringArray3[2] = String.format(stringArray3[2], 3);
        return c(stringArray3, i2);
    }

    public static String m(int i, int i2) {
        return i(i, i2, 0);
    }

    public static String i(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getTotalBodyWater Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_low), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_standard), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_body_water_high)}, i2);
        }
        if (i == 1) {
            return c(dHB_.getStringArray(R.array._2130968608_res_0x7f040020), i2);
        }
        if (i != 2) {
            return "";
        }
        if (doj.d(i3) && i2 == 1) {
            return dHB_.getString(R$string.IDS_suggest_total_body_water_child_low);
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968600_res_0x7f040018);
        stringArray[0] = String.format(stringArray[0], 1500);
        stringArray[1] = String.format(stringArray[1], 1500);
        return c(stringArray, i2);
    }

    public static String j(int i, int i2) {
        return g(i, i2, 0);
    }

    public static String g(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getProtein Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{a(dHB_.getString(R$string.IDS_weight_less), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_low)), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_standard), a(dHB_.getString(R$string.IDS_weight_more), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_protein_high))}, i2);
        }
        if (i != 1) {
            return i != 2 ? "" : c(dHB_.getStringArray(R.array._2130968601_res_0x7f040019), i2);
        }
        if (doj.d(i3) && i2 == 3) {
            return dHB_.getString(R$string.IDS_result_desc_protein_child_high);
        }
        return c(dHB_.getStringArray(R.array._2130968609_res_0x7f040021), i2);
    }

    public static String d(int i, int i2) {
        return a(i, i2, 0);
    }

    public static String a(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBoneMineralContent Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{a(dHB_.getString(R$string.IDS_weight_less), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_low)), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_standard), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_bone_mineral_content_high)}, i2);
        }
        if (i == 1) {
            if (doj.d(i3) && i2 == 1) {
                return dHB_.getString(R$string.IDS_result_desc_bone_mineral_child_low);
            }
            return c(dHB_.getStringArray(R.array._2130968610_res_0x7f040022), i2);
        }
        if (i != 2) {
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968602_res_0x7f04001a);
        String str = stringArray[0];
        String str2 = stringArray[1];
        return c(new String[]{str, str2, str2}, i2);
    }

    private static String a(String str, String str2) {
        return CommonUtil.as() ? str : str2;
    }

    public static String b(int i, int i2) {
        return e(i, i2, 0);
    }

    public static String e(int i, int i2, int i3) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBasalMetabolicRate Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_low), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_standard), dHB_.getString(R$string.IDS_hw_health_healthdata_body_data_basal_metabolic_rate_high)}, i2);
        }
        if (i == 1) {
            if (doj.d(i3) && i2 == 1) {
                return dHB_.getString(R$string.IDS_result_desc_basal_metabolic_child_low);
            }
            return c(dHB_.getStringArray(R.array._2130968611_res_0x7f040023), i2);
        }
        if (i != 2) {
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968603_res_0x7f04001b);
        stringArray[0] = String.format(stringArray[0], 3, 4, 30);
        stringArray[1] = String.format(stringArray[1], 3);
        return c(stringArray, i2);
    }

    public static String i(int i, int i2) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getHeartRate Resources is null");
            return "";
        }
        if (i != 0) {
            return i != 1 ? "" : c(dHB_.getStringArray(R.array._2130968593_res_0x7f040011), i2);
        }
        return c(new String[]{dHB_.getString(R$string.IDS_hw_health_show_healthdata_status_low), dHB_.getString(R$string.IDS_hw_health_show_healthdata_status_normal), dHB_.getString(R$string.IDS_hw_health_index_slightly_high), dHB_.getString(R$string.IDS_hw_health_index_high)}, i2);
    }

    public static String o(int i, int i2) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getWaistToHipRatio Resources is null");
            return "";
        }
        if (i == 0) {
            return c(new String[]{dHB_.getString(R$string.IDS_details_sleep_grade_normal), dHB_.getString(R$string.IDS_details_sleep_grade_high)}, i2);
        }
        if (i != 1) {
            return i != 2 ? "" : c(a(dHB_.getStringArray(R.array._2130968588_res_0x7f04000c)), i2);
        }
        return c(a(dHB_.getStringArray(R.array._2130968587_res_0x7f04000b)), i2);
    }

    private static String[] a(String[] strArr) {
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            strArr2[i] = strArr[(strArr.length - i) - 1];
        }
        return strArr2;
    }

    public static String g(int i, int i2) {
        Resources dHB_ = dHB_();
        if (dHB_ != null) {
            return i != 0 ? "" : c(new String[]{dHB_.getString(R$string.IDS_hw_pressure_relaxed), dHB_.getString(R$string.IDS_hw_pressure_normal), dHB_.getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_3), dHB_.getString(R$string.IDS_hw_pressure_highly)}, i2);
        }
        LogUtil.h("HealthAnalysis", "getPressureIndex Resources is null");
        return "";
    }

    public static String h(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getSkeletalMuscleBalanceEvaluation Resources is null");
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968579_res_0x7f040003);
        switch (i) {
            case 1:
            case 2:
            case 17:
            case 18:
            case 33:
            case 34:
                return stringArray[1];
            case 3:
            case 7:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            case 23:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 35:
            case 39:
            default:
                return stringArray[0];
            case 4:
            case 8:
            case 20:
            case 24:
            case 36:
            case 40:
                return stringArray[2];
            case 5:
            case 6:
            case 9:
            case 10:
            case 21:
            case 22:
            case 25:
            case 26:
            case 37:
            case 38:
            case 41:
            case 42:
                return stringArray[3];
            case 16:
                return stringArray[4];
            case 32:
                return stringArray[5];
        }
    }

    public static String f(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getSkeletalMuscleBalanceResultDescription Resources is null");
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968580_res_0x7f040004);
        a();
        return stringArray[e.get(i)];
    }

    public static String k(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getSkeletalMuscleBalanceSuggest Resources is null");
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968581_res_0x7f040005);
        if (i == 0) {
            return stringArray[0];
        }
        return stringArray[1];
    }

    public static String j(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getFatBalanceEvaluation Resources is null");
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968577_res_0x7f040001);
        switch (i) {
            case 1:
            case 2:
                return stringArray[1];
            case 3:
            case 7:
            default:
                return stringArray[0];
            case 4:
            case 8:
                return stringArray[2];
            case 5:
            case 6:
            case 9:
            case 10:
                return stringArray[3];
        }
    }

    public static String g(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getFatBalanceResultDescription Resources is null");
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968578_res_0x7f040002);
        d();
        return stringArray[e.get(i)];
    }

    public static String d(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBodyShapeEvaluation Resources is null");
            return "";
        }
        if (i == 0) {
            return dHB_.getString(R$string.IDS_weight_well_proportioned_type);
        }
        if (i == 1) {
            return dHB_.getString(R$string.IDS_weight_apple_type);
        }
        if (i == 2) {
            return dHB_.getString(R$string.IDS_weight_pear_type);
        }
        if (i == 3) {
            return dHB_.getString(R$string.IDS_weight_chili_type);
        }
        if (i != 4) {
            return i != 5 ? "" : dHB_.getString(R$string.IDS_weight_hourglass_type);
        }
        return dHB_.getString(R$string.IDS_weight_inverted_triangle_type);
    }

    public static String b(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBodyShapeResultDescription Resources is null");
            return "";
        }
        if (i == 1) {
            return dHB_.getString(R$string.IDS_analysis_result_description_body_shape_apple);
        }
        if (i == 2) {
            return dHB_.getString(R$string.IDS_analysis_result_description_body_shape_pear);
        }
        if (i != 3) {
            return i != 5 ? "" : dHB_.getString(R$string.IDS_analysis_result_description_body_shape_hourglass);
        }
        return dHB_.getString(R$string.IDS_analysis_result_description_body_shape_chili);
    }

    public static String c(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBodyShapeSuggest Resources is null");
            return "";
        }
        if (i == 0) {
            return dHB_.getString(R$string.IDS_analysis_suggest_body_shape_well_proportioned);
        }
        if (i == 1) {
            return dHB_.getString(R$string.IDS_analysis_suggest_body_shape_apple);
        }
        if (i == 2) {
            return dHB_.getString(R$string.IDS_analysis_suggest_body_shape_pear);
        }
        if (i == 3) {
            return dHB_.getString(R$string.IDS_analysis_suggest_body_shape_chili);
        }
        if (i != 4) {
            return i != 5 ? "" : dHB_.getString(R$string.IDS_analysis_suggest_body_shape_hourglass);
        }
        return dHB_.getString(R$string.IDS_analysis_suggest_body_shape_inverted_triangle);
    }

    public static String a(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBodyTypeEvaluation Resources is null");
            return "";
        }
        switch (i) {
        }
        return "";
    }

    public static String e(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBodyTypeResultDescription Resources is null");
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968582_res_0x7f040006);
        String[] stringArray2 = dHB_.getStringArray(R.array._2130968604_res_0x7f04001c);
        String[] stringArray3 = dHB_.getStringArray(R.array._2130968583_res_0x7f040007);
        switch (i) {
        }
        return "";
    }

    public static String i(int i) {
        Resources dHB_ = dHB_();
        if (dHB_ == null) {
            LogUtil.h("HealthAnalysis", "getBodyTypeSuggest Resources is null");
            return "";
        }
        String[] stringArray = dHB_.getStringArray(R.array._2130968584_res_0x7f040008);
        String[] stringArray2 = dHB_.getStringArray(R.array._2130968585_res_0x7f040009);
        String[] stringArray3 = dHB_.getStringArray(R.array._2130968586_res_0x7f04000a);
        switch (i) {
        }
        return "";
    }

    public static String e(int i, int i2) {
        if (dHB_() == null) {
            LogUtil.h("HealthAnalysis", "getBodyType Resources is null");
            return "";
        }
        if (i == 0) {
            return a(i2);
        }
        if (i != 1) {
            return i != 2 ? "" : i(i2);
        }
        return e(i2);
    }
}
