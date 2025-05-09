package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* loaded from: classes7.dex */
public class rsr {
    public static int e(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? 0 : 3;
        }
        return 2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String b(String str) {
        char c;
        Resources resources = BaseApplication.getContext().getResources();
        str.hashCode();
        switch (str.hashCode()) {
            case -828306879:
                if (str.equals("com.huawei.continuous.body_weight.statistics")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -347047149:
                if (str.equals("com.huawei.continuous.heart_rate.statistics")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -328597096:
                if (str.equals("com.huawei.continuous.steps.total")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 39245215:
                if (str.equals("com.huawei.continuous.sleep.statistics")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 944702533:
                if (str.equals("com.huawei.continuous.steps.rate.statistics")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1795795000:
                if (str.equals("com.huawei.continuous.calories.burnt.total")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2119456336:
                if (str.equals("com.huawei.continuous.distance.total")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return resources.getString(R$string.IDS_hw_health_show_healthdata_weight_unit);
            case 1:
            case 4:
                return resources.getString(R$string.IDS_awake_times);
            case 2:
                return resources.getString(R$string.IDS_settings_steps_unit);
            case 3:
                return resources.getString(R$string.IDS_hw_show_main_home_page_minutes);
            case 5:
                return resources.getString(R$string.IDS_band_data_sport_energy_unit);
            case 6:
                return resources.getString(R$string.IDS_fitness_data_list_activity_meter_unit);
            default:
                return "";
        }
    }

    public static String i(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        if (i == 100) {
            return resources.getString(R$string.IDS_hw_show_main_home_page_steps);
        }
        if (i != 103) {
            switch (i) {
                case 106:
                    return resources.getString(R$string.IDS_device_device_weather_unit_celsius);
                case 107:
                    return resources.getString(R$string.IDS_hw_health_show_healthdata_mmhg_str);
                case 108:
                    return resources.getString(R$string.IDS_device_measure_sugar_value_unit);
                case 109:
                    return resources.getString(R$string.IDS_device_measure_weight_value_unit);
                default:
                    return "";
            }
        }
        return resources.getString(R$string.IDS_hw_show_main_home_page_minutes);
    }

    public static String d(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        switch (i) {
            case 100:
                return resources.getString(R$string.IDS_motiontrack_show_detail_stemps);
            case 101:
                return resources.getString(R$string.IDS_sport_distance);
            case 102:
                return resources.getString(R$string.IDS_main_watch_heart_rate_string);
            case 103:
                return resources.getString(R$string.IDS_hw_show_main_permission_sleep);
            case 104:
                return resources.getString(R$string.IDS_settings_one_level_menu_settings_item_text_id14);
            case 105:
                return resources.getString(R$string.IDS_hw_health_blood_oxygen);
            case 106:
                return resources.getString(R$string.IDS_settings_health_temperature);
            case 107:
                return resources.getString(R$string.IDS_hw_show_main_home_page_bloodpressure);
            case 108:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar);
            case 109:
                return resources.getString(R$string.IDS_hw_health_show_healthdata_weight);
            case 110:
                return resources.getString(R$string.IDS_motiontrack_climb_stairs_tip);
            case 111:
                return resources.getString(R$string.IDS_active_caloric);
            case 112:
                return resources.getString(R$string.IDS_privacy_activity_records);
            case 113:
                return resources.getString(R$string.IDS_health_skin_temperature);
            default:
                return "UnknownType";
        }
    }

    public static String c(long j) {
        return UnitUtil.a(new Date(j), 131097);
    }

    public static String a(long j) {
        return UnitUtil.a(new Date(j), 131096);
    }

    public static String e(long j) {
        return UnitUtil.a(new Date(j), 1);
    }

    public static String b(long j) {
        return UnitUtil.a(new Date(j), 131093);
    }

    public static String i(long j) {
        return UnitUtil.a(new Date(j), 131092);
    }

    public static String d(long j) {
        return mlg.a(j, 0);
    }

    public static String c(int i, double d) {
        int i2;
        if (i == 100 || i != 109) {
            i2 = R.plurals._2130903205_res_0x7f0300a5;
        } else {
            i2 = UnitUtil.h() ? R.plurals._2130903216_res_0x7f0300b0 : R.plurals._2130903215_res_0x7f0300af;
        }
        return BaseApplication.getContext().getResources().getQuantityString(i2, qrp.a(d), "");
    }

    public static String j(long j) {
        StringBuilder sb = new StringBuilder();
        Resources resources = BaseApplication.getContext().getResources();
        long j2 = j / 60;
        long j3 = j2 / 60;
        if (j3 != 0) {
            sb.append(c(resources.getString(R$string.IDS_hw_show_main_home_page_hours), UnitUtil.e(j3, 1, 0), 103));
            sb.append(" ");
        }
        long j4 = j2 % 60;
        if (j4 != 0 || j3 == 0) {
            sb.append(c(resources.getString(R$string.IDS_hw_show_main_home_page_minutes), UnitUtil.e(j4, 1, 0), 103));
        }
        return sb.toString();
    }

    public static String c(String str, String str2, int i) {
        StringBuilder sb = new StringBuilder();
        String str3 = i == 108 ? "" : " ";
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            switch (i) {
                case 100:
                case 101:
                case 102:
                case 107:
                case 111:
                    sb.append(str);
                    sb.append(str3);
                    sb.append(str2);
                    break;
                case 103:
                    sb.append(str);
                    sb.append(str2);
                    break;
                case 108:
                    int lastIndexOf = str.lastIndexOf("/");
                    if (lastIndexOf == -1) {
                        sb.append(str);
                        sb.append(str3);
                        sb.append(str2);
                        break;
                    } else {
                        String substring = str.substring(0, lastIndexOf);
                        String substring2 = str.substring(lastIndexOf);
                        sb.append(substring);
                        sb.append(str3);
                        sb.append(str2);
                        sb.append(str3);
                        sb.append(substring2);
                        break;
                    }
                case 109:
                    sb.append(str3);
                    sb.append(str);
                    sb.append(str3);
                    sb.append(str2);
                    break;
            }
        } else {
            sb.append(str2);
            sb.append(str3);
            sb.append(str);
        }
        return sb.toString();
    }

    public static boolean d(long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(Long.valueOf(j)).equals(simpleDateFormat.format(Long.valueOf(j2)));
    }

    public static boolean a(long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        return simpleDateFormat.format(Long.valueOf(j)).equals(simpleDateFormat.format(Long.valueOf(j2)));
    }

    public static boolean c(long j, long j2) {
        int a2 = jdl.a(j, j2);
        LogUtil.a("PrivacyUtil", "thirtyDaysDiff: ", Integer.valueOf(a2));
        return a2 >= 30;
    }

    public static long f(long j) {
        return ((j / 1000) / 60) * 60;
    }

    public static String c(int i, PrivacyDataModel privacyDataModel) {
        if (privacyDataModel == null) {
            return "";
        }
        switch (i) {
            case 107:
                break;
        }
        return "";
    }

    public static List<String> c(int i) {
        Context context = BaseApplication.getContext();
        if (i != 105) {
            if (i == 107) {
                return Arrays.asList(context.getResources().getStringArray(R.array._2130968637_res_0x7f04003d));
            }
            return new ArrayList(10);
        }
        if (efb.c()) {
            return Arrays.asList(context.getResources().getStringArray(R.array._2130968650_res_0x7f04004a));
        }
        return Arrays.asList(context.getString(R$string.IDS_privacy_all_data));
    }

    public static List<Integer> f(int i) {
        if (i != 105) {
            if (i == 107) {
                return Arrays.asList(1, 2, 3);
            }
            return new ArrayList(10);
        }
        if (efb.c()) {
            return Arrays.asList(1, 2, 3, 4);
        }
        return Arrays.asList(1);
    }

    public static String[] j(int i) {
        Context context = BaseApplication.getContext();
        if (i == 106) {
            return context.getResources().getStringArray(R.array._2130968651_res_0x7f04004b);
        }
        if (i != 107) {
            return new String[0];
        }
        int[] a2 = eeu.a();
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(context.getString(R$string.IDS_bloodpressure_all_data));
        for (int length = a2.length - 1; length >= 0; length--) {
            int i2 = a2[length];
            if (i2 != 1) {
                arrayList.add(eeu.c(i2));
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static Integer[] b(int i) {
        if (i == 106) {
            return new Integer[]{1, 2};
        }
        if (i != 107) {
            return new Integer[0];
        }
        int[] a2 = eeu.a();
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(100);
        for (int length = a2.length - 1; length >= 0; length--) {
            int i2 = a2[length];
            if (i2 != 1) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        return (Integer[]) arrayList.toArray(new Integer[0]);
    }

    public static boolean a(PageModelArgs pageModelArgs) {
        if (pageModelArgs == null || pageModelArgs.getDataSource() != 3) {
            return false;
        }
        int pageType = pageModelArgs.getPageType();
        if (pageType == 106 || pageType == 107 || pageType == 113) {
            return true;
        }
        switch (pageType) {
        }
        return false;
    }

    public static boolean d(PageModelArgs pageModelArgs) {
        if (pageModelArgs == null || pageModelArgs.getDataSource() != 3) {
            return false;
        }
        int pageType = pageModelArgs.getPageType();
        return pageType == 106 || pageType == 113;
    }

    public static boolean e(PageModelArgs pageModelArgs) {
        return pageModelArgs != null && pageModelArgs.getPageType() == 103 && pageModelArgs.getModuleType() == 1;
    }

    public static boolean h(PageModelArgs pageModelArgs) {
        if (pageModelArgs == null) {
            return false;
        }
        int pageType = pageModelArgs.getPageType();
        if (pageType != 102 && pageType != 103 && pageType != 113) {
            switch (pageType) {
            }
            return false;
        }
        if (pageModelArgs.getDataSource() == 3) {
            return pageModelArgs.getModuleType() == 1 || pageModelArgs.getModuleType() == 3;
        }
        return false;
    }

    public static boolean i(PageModelArgs pageModelArgs) {
        return pageModelArgs != null && pageModelArgs.getPageType() == 106;
    }

    public static boolean j(PageModelArgs pageModelArgs) {
        return pageModelArgs != null && pageModelArgs.getPageType() == 107;
    }

    public static boolean c(PageModelArgs pageModelArgs) {
        if (pageModelArgs == null) {
            return false;
        }
        int pageType = pageModelArgs.getPageType();
        return pageType == 102 || pageType == 103 || pageType == 106 || pageType == 113;
    }

    public static Drawable dQH_(int i) {
        Context context = BaseApplication.getContext();
        if (LanguageUtil.bc(context)) {
            return nrz.cKn_(context, i);
        }
        return context.getDrawable(i);
    }

    public static PageModelArgs b(PageModelArgs pageModelArgs) {
        PageModelArgs pageModelArgs2 = new PageModelArgs();
        if (pageModelArgs != null) {
            pageModelArgs2.setPageType(pageModelArgs.getPageType());
            pageModelArgs2.setServiceId(pageModelArgs.getServiceId());
            pageModelArgs2.setUuid(pageModelArgs.getUuid());
            pageModelArgs2.setDataSource(pageModelArgs.getDataSource());
            pageModelArgs2.setModuleType(pageModelArgs.getModuleType());
            pageModelArgs2.setTimestamp(pageModelArgs.getTimestamp());
            pageModelArgs2.setClassType(pageModelArgs.getClassType());
            pageModelArgs2.setPackageName(pageModelArgs.getPackageName());
            pageModelArgs2.setOtherManufacturer(pageModelArgs.isOtherManufacturer());
            pageModelArgs2.setSourceInfo(pageModelArgs.getSourceInfo());
            pageModelArgs2.setContentValues(pageModelArgs.getContentValues());
        }
        return pageModelArgs2;
    }
}
