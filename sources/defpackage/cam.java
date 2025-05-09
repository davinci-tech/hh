package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.services.core.AMapException;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class cam {

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f584a = {0.4786f, 0.49f, 0.4775f, 0.6337f, 0.6143f, 0.5453f};
    private static final float[] d = {0.7973f, 0.8222f, 0.8488f, 0.7918f, 0.8186f, 0.8736f};

    public static int e(int i) {
        switch (i) {
            case 6:
                return 1;
            case 7:
                return 2;
            case 8:
                return 5;
            case 9:
                return 6;
            default:
                return 0;
        }
    }

    public static void c(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            LogUtil.h("PaceRangeUtils", "saveEachPaceRange paceRangeValues is null.");
            return;
        }
        int[] iArr2 = (int[]) iArr.clone();
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.UserPreference_RunningPaceZone_Config");
        hiUserPreference.setValue(Arrays.toString(iArr2));
        HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference, true);
    }

    public static void a(boolean z) {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("setting_auto_calc_key_status");
        hiUserPreference.setValue(z ? "1" : "0");
        HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference, true);
    }

    public static boolean a() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("setting_auto_calc_key_status");
        if (userPreference == null) {
            LogUtil.h("PaceRangeUtils", "getAutoCalcKeyStatus userPreference is null.");
            return false;
        }
        String value = userPreference.getValue();
        if (TextUtils.isEmpty(value)) {
            LogUtil.h("PaceRangeUtils", "getAutoCalcKeyStatus keyStatus is null.");
            return false;
        }
        return "1".equals(value);
    }

    public static int e(int i, float f) {
        if (i >= 0) {
            if (i <= f584a.length) {
                return (int) ((1.0d / (r0[i] * Math.pow(f, d[i]))) * 3600.0d);
            }
        }
        LogUtil.h("PaceRangeUtils", "getPaceSecondsByRunning index is error");
        return 0;
    }

    public static String c(Context context, int i, float f, double d2) {
        double e = e(i, f) * d2;
        if (e == 0.0d) {
            return context.getResources().getString(R.string._2130849885_res_0x7f02305d);
        }
        return fhq.a(context, (float) e);
    }

    public static void d(Context context, int i, int i2, long j) {
        String[] strArr = {String.valueOf(i), String.valueOf(i2), String.valueOf(j)};
        StorageParams storageParams = new StorageParams();
        if (UnitUtil.h()) {
            SharedPreferenceManager.e(context, Integer.toString(10000), "USER_SAVE_ACHIEVEMENT_IMPERIAL_KEY", Arrays.toString(strArr), storageParams);
            SharedPreferenceManager.e(context, Integer.toString(10000), "USER_SAVE_ACHIEVEMENT_METRIC_KEY", "", storageParams);
        } else {
            SharedPreferenceManager.e(context, Integer.toString(10000), "USER_SAVE_ACHIEVEMENT_METRIC_KEY", Arrays.toString(strArr), storageParams);
            SharedPreferenceManager.e(context, Integer.toString(10000), "USER_SAVE_ACHIEVEMENT_IMPERIAL_KEY", "", storageParams);
        }
    }

    public static double c(double d2, int i) {
        double d3 = i / 60.0d;
        LogUtil.a("PaceRangeUtils", "getVo2Max minute =", Double.valueOf(d3));
        if (CommonUtil.c(d3)) {
            LogUtil.h("PaceRangeUtils", "getVo2Max minute near zero");
            return 0.0d;
        }
        double d4 = d2 / d3;
        return (((0.182258d * d4) - 4.6d) + ((1.04E-4d * d4) * d4)) / (((Math.exp((-0.012778d) * d3) * 0.1894393d) + 0.8d) + (Math.exp(d3 * (-0.1932695d)) * 0.2989558d));
    }

    public static int d(double d2) {
        return b(0.67d, d2);
    }

    public static int a(double d2) {
        return b(0.76d, d2);
    }

    public static int e(double d2) {
        return b(0.8d, d2);
    }

    public static int c(double d2) {
        return b(0.91d, d2);
    }

    public static int h(double d2) {
        return b(1.06d, d2);
    }

    public static int b(double d2) {
        return b(1.15d, d2);
    }

    public static SparseArray<cad> Ct_(Context context) {
        if (context == null) {
            LogUtil.h("PaceRangeUtils", "createDistanceKmData context null");
            return new SparseArray<>(5);
        }
        cad cadVar = new cad();
        cadVar.d(context.getResources().getQuantityString(R.plurals._2130903108_res_0x7f030044, 3, UnitUtil.e(3.0d, 1, 0)));
        cadVar.a(3000.0d);
        cadVar.a(420);
        cadVar.e(3599);
        cadVar.d(0);
        cad cadVar2 = new cad();
        cadVar2.d(context.getResources().getQuantityString(R.plurals._2130903108_res_0x7f030044, 5, UnitUtil.e(5.0d, 1, 0)));
        cadVar2.a(5000.0d);
        cadVar2.a(900);
        cadVar2.e(7199);
        cadVar2.d(1);
        cad cadVar3 = new cad();
        cadVar3.d(context.getResources().getQuantityString(R.plurals._2130903108_res_0x7f030044, 10, UnitUtil.e(10.0d, 1, 0)));
        cadVar3.a(10000.0d);
        cadVar3.a(AMapException.CODE_AMAP_CLIENT_ERRORCODE_MISSSING);
        cadVar3.e(10799);
        cadVar3.d(2);
        SparseArray<cad> sparseArray = new SparseArray<>(5);
        sparseArray.put(0, cadVar);
        sparseArray.put(1, cadVar2);
        sparseArray.put(2, cadVar3);
        Cs_(context, sparseArray);
        return sparseArray;
    }

    public static SparseArray<cad> Cu_(Context context) {
        if (context == null) {
            LogUtil.h("PaceRangeUtils", "createDistanceMileData context null");
            return new SparseArray<>(4);
        }
        cad cadVar = new cad();
        cadVar.d(context.getResources().getQuantityString(R.plurals._2130903109_res_0x7f030045, 1, UnitUtil.e(1.0d, 1, 0)));
        cadVar.a(((float) UnitUtil.d(1.0d, 3)) * 1000.0f);
        cadVar.a(120);
        cadVar.e(1859);
        cadVar.d(3);
        cad cadVar2 = new cad();
        cadVar2.d(context.getResources().getQuantityString(R.plurals._2130903109_res_0x7f030045, 2, UnitUtil.e(2.0d, 1, 0)));
        cadVar2.a(((float) UnitUtil.d(2.0d, 3)) * 1000.0f);
        cadVar2.a(GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
        cadVar2.e(3599);
        cadVar2.d(4);
        SparseArray<cad> sparseArray = new SparseArray<>(4);
        sparseArray.put(3, cadVar);
        sparseArray.put(4, cadVar2);
        Cs_(context, sparseArray);
        return sparseArray;
    }

    private static void Cs_(Context context, SparseArray<cad> sparseArray) {
        cad cadVar = new cad();
        cadVar.d(context.getString(R.string._2130841792_res_0x7f0210c0));
        cadVar.a(21097.5d);
        cadVar.a(2700);
        cadVar.e(21599);
        cadVar.d(5);
        cad cadVar2 = new cad();
        cadVar2.d(context.getString(R.string._2130841793_res_0x7f0210c1));
        cadVar2.a(42195.0d);
        cadVar2.a(4500);
        cadVar2.e(28799);
        cadVar2.d(6);
        sparseArray.put(5, cadVar);
        sparseArray.put(6, cadVar2);
    }

    private static int b(double d2, double d3) {
        double d4 = (((5.000663d * d3) + 29.54d) - ((0.007546d * d3) * d3)) * d2;
        if (CommonUtil.c(d4)) {
            LogUtil.h("PaceRangeUtils", "vo2ToVel speedFromVo2Max near zero");
            return 0;
        }
        return (int) Math.round((1000.0d / d4) * 60.0d);
    }
}
