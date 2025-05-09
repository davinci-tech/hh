package defpackage;

import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.R$string;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;
import java.util.MissingFormatArgumentException;

/* loaded from: classes7.dex */
public class rre {
    public static String f(int i) {
        double d = i;
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903205_res_0x7f0300a5, qrp.a(d), UnitUtil.e(d, 1, 0));
    }

    public static String a(double d) {
        double d2;
        int i;
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d < 100.0d) {
            if (UnitUtil.h()) {
                d2 = UnitUtil.e(d, 1);
                i = R.plurals._2130903217_res_0x7f0300b1;
            } else {
                StringBuilder sb = new StringBuilder();
                String string = BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
                String e = UnitUtil.e(d, 1, 0);
                if (LanguageUtil.ai(BaseApplication.getContext())) {
                    sb.append(string);
                    sb.append(" ");
                    sb.append(e);
                } else {
                    sb.append(e);
                    sb.append(" ");
                    sb.append(string);
                }
                return sb.toString();
            }
        } else {
            d2 = d / 1000.0d;
            if (UnitUtil.h()) {
                d2 = UnitUtil.e(d2, 3);
                i = R.plurals._2130903218_res_0x7f0300b2;
            } else {
                i = R.plurals._2130903239_res_0x7f0300c7;
            }
        }
        String e2 = UnitUtil.e(d2, 1, 2);
        return BaseApplication.getContext().getResources().getQuantityString(i, qrp.a(d2), e2 + " ");
    }

    public static String c(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        double d = f / 10.0f;
        if (UnitUtil.h()) {
            double d2 = UnitUtil.j(d)[0];
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903217_res_0x7f0300b1, qrp.a(d2), UnitUtil.e(d2, 1, 1));
        }
        String e = UnitUtil.e(d, 1, 1);
        StringBuilder sb = new StringBuilder();
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            sb.append(string);
            sb.append(" ");
            sb.append(e);
        } else {
            sb.append(e);
            sb.append(" ");
            sb.append(string);
        }
        return sb.toString();
    }

    public static String b(double d, boolean z) {
        String e = qsj.e(d, 1);
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_healthdata_weight_average).toUpperCase(Locale.ROOT));
            sb.append(" ");
            sb.append(e);
            return sb.toString();
        }
        sb.append(e);
        return sb.toString();
    }

    public static String d(double d) {
        if (d < 100.0d) {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903073_res_0x7f030021, qrp.a(d), UnitUtil.e(d, 1, 0));
        }
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_calorie_unit);
        return String.format(Locale.ROOT, "%1$s %2$s", UnitUtil.e(d / 1000.0d, 1, 2), string);
    }

    public static String i(int i) {
        int i2 = i / 60;
        int i3 = i % 60;
        try {
            String a2 = nsf.a(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(i3, 1, 0));
            String a3 = nsf.a(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0));
            StringBuilder sb = new StringBuilder();
            if (i2 == 0) {
                sb.append(a2);
            } else if (i3 == 0) {
                sb.append(a3);
            } else {
                sb.append(BaseApplication.getContext().getString(R$string.IDS_two_parts, a3, a2));
            }
            return sb.toString();
        } catch (MissingFormatArgumentException unused) {
            LogUtil.h("DataSourceUnitUtil", "getSleepTitle MissingFormatArgumentException");
            return "";
        }
    }

    public static String c(int i, boolean z) {
        String e = sdh.e(i);
        StringBuilder sb = new StringBuilder();
        String e2 = UnitUtil.e(i, 1, 0);
        if (z) {
            sb.append(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_healthdata_weight_average).toUpperCase(Locale.ROOT));
            sb.append(" ");
            sb.append(e2);
        } else {
            sb.append(e2);
        }
        return String.format(Locale.ROOT, "%1$s %2$s", sb.toString(), e);
    }

    public static String b(int i, int i2) {
        String str;
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            str = BaseApplication.getContext().getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string) + " %1$s - %2$s";
        } else {
            str = "%1$s - %2$s " + BaseApplication.getContext().getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string);
        }
        return String.format(Locale.ROOT, str, UnitUtil.e(i2, 1, 0), UnitUtil.e(i, 1, 0));
    }

    public static String a(int i) {
        return String.format(Locale.ROOT, "%1$s " + BaseApplication.getContext().getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string), UnitUtil.e(i, 1, 0));
    }

    public static String a(int i, int i2) {
        return String.format(Locale.ROOT, "%1$s-%2$s", UnitUtil.e(i2, 2, 0), UnitUtil.e(i, 2, 0));
    }

    public static String d(int i) {
        return UnitUtil.e(i, 2, 0);
    }

    public static String c(int i, int i2) {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_health_blood_oxygen_range_two, UnitUtil.e(i2, 2, 0), UnitUtil.e(i, 2, 0));
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_device_measure_pressure_value_unit);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            sb.append(string);
            sb.append(" ");
            sb.append(str);
        } else {
            sb.append(str);
            sb.append(" ");
            sb.append(string);
        }
        return sb.toString();
    }

    public static String b(double d) {
        String e = UnitUtil.e(d, 1, 1);
        StringBuilder sb = new StringBuilder();
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_device_measure_sugar_value_unit);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            sb.append(string);
            sb.append(e);
        } else {
            sb.append(e);
            sb.append(string);
        }
        return sb.toString();
    }

    public static String e(double d, double d2) {
        String a2;
        String a3;
        if (UnitUtil.d()) {
            a2 = UnitUtil.e(d, 1, 1);
            a3 = UnitUtil.e(d2, 1, 1);
        } else {
            a2 = a((float) d);
            a3 = a((float) d2);
        }
        return String.format(Locale.ROOT, BaseApplication.getContext().getResources().getString(UnitUtil.d() ? R$string.IDS_settings_health_temperature_unit : R$string.IDS_temp_unit_fahrenheit), a3 + Constants.LINK + a2);
    }

    public static String c(double d, int i) {
        String a2;
        int i2;
        Resources resources = BaseApplication.getContext().getResources();
        if (UnitUtil.d()) {
            a2 = UnitUtil.e(d, 1, 1);
            i2 = R$string.IDS_settings_health_temperature_unit;
        } else {
            a2 = a((float) d);
            i2 = R$string.IDS_temp_unit_fahrenheit;
        }
        String format = String.format(resources.getString(i2), a2);
        if (DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value() != i) {
            return format;
        }
        return String.format(resources.getString(R$string.IDS_temp_desc_suspect), String.format(resources.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_greater_than), format));
    }

    public static String c(double d) {
        return c(d, 0);
    }

    private static String a(float f) {
        return UnitUtil.e((f * 1.8f) + 32.0f, 1, 1);
    }

    public static String c(int i) {
        return (i == Integer.MIN_VALUE || i == 0) ? "--" : BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903084_res_0x7f03002c, i, Integer.valueOf(i));
    }

    public static String e(int i) {
        if (i == Integer.MIN_VALUE) {
            return String.valueOf(Integer.MIN_VALUE);
        }
        if (UnitUtil.h()) {
            int i2 = UnitUtil.j(i)[0];
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903217_res_0x7f0300b1, qrp.a(i2), String.valueOf(i2));
        }
        String e = UnitUtil.e(i, 1, 0);
        StringBuilder sb = new StringBuilder();
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            sb.append(string);
            sb.append(e);
        } else if (LanguageUtil.bg(BaseApplication.getContext()) || LanguageUtil.t(BaseApplication.getContext())) {
            sb.append(e);
            sb.append(" ");
            sb.append(string);
        } else {
            sb.append(e);
            sb.append(string);
        }
        return sb.toString();
    }

    public static String b(int i) {
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903317_res_0x7f030115, i, Integer.valueOf(i));
    }
}
