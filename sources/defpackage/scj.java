package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class scj {
    public static int b(int i) {
        if (i == 10) {
            return 0;
        }
        if (i != 20) {
            return i != 30 ? -1 : 2;
        }
        return 1;
    }

    public static boolean d(int i) {
        return (i == 32 || i == 1 || i == 10001) ? false : true;
    }

    public static int e(int i) {
        if (i == 2106) {
            return 9;
        }
        switch (i) {
            case 2008:
                return 1;
            case 2009:
                return 2;
            case 2010:
                return 3;
            case 2011:
                return 4;
            case 2012:
                return 5;
            case 2013:
                return 6;
            case 2014:
                return 7;
            case 2015:
                return 8;
            default:
                return 0;
        }
    }

    public static String b(qul qulVar) {
        StringBuilder sb = new StringBuilder();
        List<quk> c = qulVar.c();
        if (bkz.e(c)) {
            return sb.toString();
        }
        for (quk qukVar : c) {
            float i = qukVar.i();
            if (i >= 70.0f) {
                sb.append(qukVar.c() + ": " + i + " ");
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static float a(qul qulVar) {
        List<quk> c = qulVar.c();
        float f = 0.0f;
        if (bkz.e(c)) {
            return 0.0f;
        }
        Iterator<quk> it = c.iterator();
        while (it.hasNext()) {
            f += it.next().d();
        }
        return f;
    }

    public static String b(Context context, int i) {
        if (i == 10 || i == 11) {
            return context.getResources().getString(R$string.IDS_bsdiet_highgi_breakfast);
        }
        if (i == 20 || i == 21) {
            return context.getResources().getString(R$string.IDS_bsdiet_highgi_lunch);
        }
        if (i == 30 || i == 31) {
            return context.getResources().getString(R$string.IDS_bsdiet_highgi_dinner);
        }
        LogUtil.h("BloodSugarUtil", "TYPE is wrong");
        return null;
    }

    public static HiHealthData dVL_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2008);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVK_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2009);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVP_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2010);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVO_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2011);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVN_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2012);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVM_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2013);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVQ_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2014);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVI_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2015);
        if (obj != null) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_early_morning));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static HiHealthData dVJ_(SparseArray<Object> sparseArray, HiHealthData hiHealthData) {
        Object obj = sparseArray.get(2106);
        if (obj != null && (obj instanceof List)) {
            for (HiHealthData hiHealthData2 : (List) obj) {
                if (hiHealthData2.getEndTime() < System.currentTimeMillis() && hiHealthData.getEndTime() < hiHealthData2.getEndTime()) {
                    hiHealthData2.putString("innerTimePeriod", BaseApplication.e().getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_random_time));
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    public static int e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(11);
        if (i >= 0 && i < 8) {
            return 2008;
        }
        if (i >= 8 && i < 10) {
            return 2009;
        }
        if (i >= 10 && i < 12) {
            return 2010;
        }
        if (i >= 12 && i < 16) {
            return 2011;
        }
        if (i < 16 || i >= 19) {
            return (i < 19 || i >= 21) ? 2014 : 2013;
        }
        return 2012;
    }
}
