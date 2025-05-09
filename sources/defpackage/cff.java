package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import health.compact.a.CommonUtil;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class cff {

    /* renamed from: a, reason: collision with root package name */
    private static Set<String> f677a;

    public static int a(int i) {
        return (i == 94 || i == 95 || i == 140 || i == 168 || i == 385 || i == 672) ? 2 : 1;
    }

    public static byte c(byte b) {
        if (b == 2) {
            return (byte) 1;
        }
        return b;
    }

    private static double e(double d, double d2) {
        double d3 = 10;
        if (((int) ((d / d2) % d3)) + ((int) ((d2 / d2) % d3)) == 10) {
            return 0.0d;
        }
        return d2;
    }

    static {
        HashSet hashSet = new HashSet();
        f677a = hashSet;
        hashSet.add("zh_CN_#Hans");
        f677a.add("zh_MO_#Hans");
        f677a.add("zh_HK_#Hans");
        f677a.add("zh_SG_#Hans");
        f677a.add(ProfileRequestConstants.X_LANGUAGE_VALUE);
        f677a.add("es_ES");
        f677a.add("de_DE");
        f677a.add("de_LU");
        f677a.add("de_BE");
        f677a.add("de_CH");
        f677a.add("de_AT");
        f677a.add("de_LI");
        f677a.add("fr_BE");
        f677a.add("fr_FR");
        f677a.add("fr_CH");
        f677a.add("pt_PT");
        f677a.add("en_US");
        f677a.add("en_GB");
    }

    private static String d(cfi cfiVar) {
        String i = cfiVar.i();
        return b(i) ? "0" : i;
    }

    public static boolean b(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(str) || "0".equals(str)) {
            LogUtil.a("WeightDataUtils", "isMainUser, uuid is empty");
            return true;
        }
        if (!Constants.NULL.equalsIgnoreCase(str) && !str.equals(accountInfo)) {
            return false;
        }
        LogUtil.a("WeightDataUtils", "isMainUser");
        return true;
    }

    public static void a(cfi cfiVar, HiAggregateListener hiAggregateListener) {
        if (cfiVar == null) {
            LogUtil.h("WeightDataUtils", "getUserLastWeightData user is null");
            return;
        }
        if (hiAggregateListener == null) {
            LogUtil.h("WeightDataUtils", "getUserLastWeightData hiAggregateListener is null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(0L, currentTimeMillis);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        String d = d(cfiVar);
        LogUtil.a("WeightDataUtils", "getUserLastWeightData userId:", CommonUtil.l(d));
        hiAggregateOption.setFilter(d);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, hiAggregateListener);
    }

    public static HiAggregateOption a() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setCount(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        hiAggregateOption.setTimeRange(0L, System.currentTimeMillis());
        return hiAggregateOption;
    }

    public static int e(double d, int i) {
        return (a(i) != 2 || (((b(d) - c(d)) > 0.0d ? 1 : ((b(d) - c(d)) == 0.0d ? 0 : -1)) == 0)) ? 1 : 2;
    }

    private static double b(double d) {
        try {
            return Double.parseDouble(String.valueOf(new BigDecimal(e(d, 1.0E-4d) + d).setScale(2, 4)));
        } catch (NumberFormatException e) {
            LogUtil.b("WeightDataUtils", "getTwoDecimalPlacesRoundHalfUp exception = ", e.getMessage());
            return d;
        }
    }

    private static double c(double d) {
        try {
            return Double.parseDouble(String.valueOf(new BigDecimal(e(d, 0.001d) + d).setScale(1, 4)));
        } catch (NumberFormatException e) {
            LogUtil.b("WeightDataUtils", "getOneDecimalRoundHalfUp exception = ", e.getMessage());
            return d;
        }
    }
}
