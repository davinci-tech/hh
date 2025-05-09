package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class qjv extends qkg {
    public static int[] b() {
        return new int[]{2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106, 2108};
    }

    public static double d(int i) {
        return (i == 2009 || i == 2011 || i == 2013) ? 7.0d : 5.0d;
    }

    public static int[] d() {
        return new int[]{10001, 2108};
    }

    public static int[] a(Context context) {
        return new int[]{context.getColor(R.color._2131296798_res_0x7f09021e), context.getColor(R.color._2131296800_res_0x7f090220), context.getColor(R.color._2131296796_res_0x7f09021c), context.getColor(R.color._2131296797_res_0x7f09021d), context.getColor(R.color._2131296799_res_0x7f09021f), context.getColor(R.color._2131296795_res_0x7f09021b)};
    }

    public static List<Float> a(int i) {
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

    public static Map<String, String> a(Context context, int i, float f) {
        String valueOf;
        String string;
        HashMap hashMap = new HashMap();
        String valueOf2 = String.valueOf(1000);
        if (context == null) {
            LogUtil.h("UIHLH_BloodSugar", "context is null");
            hashMap.put("HEALTH_BLOOD_SUGAR_LEVEL_KEY", valueOf2);
            hashMap.put("HEALTH_BLOOD_SUGAR_LEVEL_DESC", null);
            return hashMap;
        }
        Resources resources = context.getResources();
        if (i == 2108) {
            float[] b = deb.b();
            float f2 = b[0];
            float f3 = b[1];
            if (f > f2) {
                valueOf = String.valueOf(1004);
                string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_high);
            } else if (f <= f3) {
                valueOf = String.valueOf(1002);
                string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_low);
            } else {
                valueOf = String.valueOf(1003);
                string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_normal);
            }
            hashMap.put("HEALTH_BLOOD_SUGAR_LEVEL_KEY", valueOf);
            hashMap.put("HEALTH_BLOOD_SUGAR_LEVEL_DESC", string);
            return hashMap;
        }
        return dEH_(resources, f, valueOf2, hashMap, i);
    }

    private static Map<String, String> dEH_(Resources resources, float f, String str, Map<String, String> map, int i) {
        String string;
        if (f < 0.0f || f > 33.0f || Math.abs(f - Float.MIN_VALUE) < 1.0E-6d) {
            string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_value_error);
        } else if (f <= 3.9f) {
            str = String.valueOf(1001);
            string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_too_low);
        } else if (f < 4.4f) {
            str = String.valueOf(1002);
            string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_low);
        } else if ((f <= 7.0f && i == 2008) || (f <= 10.0f && i != 2008)) {
            str = String.valueOf(1003);
            string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_normal);
        } else if (f < 13.9f) {
            str = String.valueOf(1004);
            string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_high);
        } else if (f < 16.7f) {
            str = String.valueOf(1005);
            string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_too_high);
        } else {
            str = String.valueOf(1006);
            string = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_too_high);
        }
        map.put("HEALTH_BLOOD_SUGAR_LEVEL_KEY", str);
        map.put("HEALTH_BLOOD_SUGAR_LEVEL_DESC", string);
        return map;
    }

    public static int a(int i, float f) {
        int i2;
        int i3;
        float f2;
        float f3;
        int i4;
        if (a(f)) {
            i4 = 200;
        } else if (i == 2008) {
            if (f >= 4.4f) {
                if (f < 7.0f) {
                    f2 = (f - 4.4f) / 2.6f;
                    f3 = 190.0f;
                    i4 = ((int) (f2 * f3)) + 63;
                } else {
                    i2 = (int) ((205 * (f - 7.0f)) / 26.0f);
                    i3 = 255;
                    i4 = i2 + i3;
                }
            }
            i4 = (int) ((f / 4.4f) * 61.0f);
        } else {
            if (f >= 4.4f) {
                if (f < 10.0f) {
                    f2 = (f - 4.4f) / 5.6f;
                    f3 = 270.0f;
                    i4 = ((int) (f2 * f3)) + 63;
                } else {
                    i2 = (int) ((125 * (f - 10.0f)) / 23.0f);
                    i3 = 335;
                    i4 = i2 + i3;
                }
            }
            i4 = (int) ((f / 4.4f) * 61.0f);
        }
        LogUtil.c("UIHLH_BloodSugar", "getProgressOfBloodsugar, progress = ", Integer.valueOf(i4));
        return i4;
    }

    private static boolean a(float f) {
        return f < 0.0f || f > 33.0f || ((double) Math.abs(f - Float.MIN_VALUE)) < 1.0E-6d;
    }

    public static String c(Context context, int i) {
        if (context == null) {
            LogUtil.h("UIHLH_BloodSugar", "context is null");
            return "";
        }
        Resources resources = context.getResources();
        if (i == 2106) {
            return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_random_time);
        }
        if (i == 2108) {
            return resources.getString(R$string.IDS_bloodsugar_continue);
        }
        switch (i) {
            case 2008:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast);
            case 2009:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast);
            case 2010:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch);
            case 2011:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch);
            case 2012:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner);
            case 2013:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner);
            case 2014:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep);
            case 2015:
                return resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_early_morning);
            default:
                LogUtil.b("UIHLH_BloodSugar", "unsupport timePeriod = ", Integer.valueOf(i));
                return "";
        }
    }

    public static String b(Context context, String str) {
        if (context == null) {
            LogUtil.h("UIHLH_BloodSugar", "getDataFromSharedPreference context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(String.valueOf(30006), 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(str, "");
        }
        LogUtil.h("UIHLH_BloodSugar", "getDataFromSharedPreference preferences is null");
        return "";
    }

    public static void a(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("UIHLH_BloodSugar", "setDataToSharedPreference context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(String.valueOf(30006), 0).edit();
        if (edit != null) {
            edit.putString(str, str2);
            edit.apply();
        }
    }
}
