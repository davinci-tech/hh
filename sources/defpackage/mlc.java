package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.iap.entity.OrderStatusCode;
import com.huawei.hms.network.embedded.y;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.LevelInfoDesc;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class mlc {
    private static final int[] c = {0, 400, 900, 1500, 2500, 3600, 4900, 6400, 8000, 10000, y.c, 20000, 25000, 36000, 49000, 66000, 86000, 111000, 139000, 176000};

    /* renamed from: a, reason: collision with root package name */
    private static final Integer[] f15050a = {Integer.valueOf(R.mipmap._2131821370_res_0x7f11033a), Integer.valueOf(R.mipmap._2131821375_res_0x7f11033f), Integer.valueOf(R.mipmap._2131821372_res_0x7f11033c), Integer.valueOf(R.mipmap._2131821374_res_0x7f11033e), Integer.valueOf(R.mipmap._2131821373_res_0x7f11033d), Integer.valueOf(R.mipmap._2131821371_res_0x7f11033b), Integer.valueOf(R.mipmap._2131821369_res_0x7f110339)};
    private static final Integer[] e = {Integer.valueOf(R.mipmap._2131820907_res_0x7f11016b), Integer.valueOf(R.mipmap._2131820908_res_0x7f11016c), Integer.valueOf(R.mipmap._2131820909_res_0x7f11016d), Integer.valueOf(R.mipmap._2131820910_res_0x7f11016e), Integer.valueOf(R.mipmap._2131820911_res_0x7f11016f), Integer.valueOf(R.mipmap._2131820912_res_0x7f110170), Integer.valueOf(R.mipmap._2131820913_res_0x7f110171)};
    private static final Integer[] d = {Integer.valueOf(R.mipmap._2131821386_res_0x7f11034a), Integer.valueOf(R.mipmap._2131821390_res_0x7f11034e), Integer.valueOf(R.mipmap._2131821387_res_0x7f11034b), Integer.valueOf(R.mipmap._2131821389_res_0x7f11034d), Integer.valueOf(R.mipmap._2131821388_res_0x7f11034c), Integer.valueOf(R.mipmap._2131821384_res_0x7f110348), Integer.valueOf(R.mipmap._2131821385_res_0x7f110349)};

    public static int e(double d2) {
        double d3;
        double d4;
        if (d2 < 10.0d) {
            d3 = 15.0d;
        } else {
            if (d2 < 50.0d) {
                d4 = d2 * 20.0d;
                if (d4 < 400.0d) {
                    d4 = 400.0d;
                }
                return (int) (d4 + 0.5d);
            }
            d3 = d2 < 100.0d ? 22.0d : d2 < 200.0d ? 24.0d : d2 < 500.0d ? 26.0d : d2 < 1000.0d ? 28.0d : 30.0d;
        }
        d4 = d2 * d3;
        return (int) (d4 + 0.5d);
    }

    public static int f(int i) {
        switch (i) {
            case 2:
                return 4;
            case 3:
                return 7;
            case 4:
                return 10;
            case 5:
                return 13;
            case 6:
                return 16;
            case 7:
                return 19;
            default:
                return 1;
        }
    }

    public static int g(int i) {
        switch (i) {
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 8;
            case 4:
                return 11;
            case 5:
                return 14;
            case 6:
                return 17;
            case 7:
                return 20;
            default:
                return 1;
        }
    }

    public static int h(int i) {
        switch (i) {
            case 4:
            case 5:
            case 6:
                return 2;
            case 7:
            case 8:
            case 9:
                return 3;
            case 10:
            case 11:
            case 12:
                return 4;
            case 13:
            case 14:
            case 15:
                return 5;
            case 16:
            case 17:
            case 18:
                return 6;
            case 19:
            case 20:
                return 7;
            default:
                return 1;
        }
    }

    public static int i(int i) {
        switch (i) {
            case 1:
                return 3;
            case 2:
                return 6;
            case 3:
                return 9;
            case 4:
                return 12;
            case 5:
                return 15;
            case 6:
                return 18;
            case 7:
                return 20;
            default:
                return 1;
        }
    }

    public static SpannableString ckm_(Context context, int i) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_LevelUtil", "acquireLevelContent: context is null");
            return new SpannableString("");
        }
        return mln.cky_(String.valueOf(i), String.format(Locale.ROOT, context.getString(R.string._2130840775_res_0x7f020cc7), Integer.valueOf(i)), (int) context.getResources().getDimension(R.dimen._2131363473_res_0x7f0a0691), context.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
    }

    public static int d(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_LevelUtil", "getTimeLocalMonth: context is null");
            return 0;
        }
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            LogUtil.b("PLGACHIEVE_LevelUtil", "setEvent NumberFormatException ", e2.getMessage());
            i = 0;
        }
        if (i != 1100 && i != 1200 && i != 1210) {
            if (i == 1300 || i == 1400) {
                return 20;
            }
            if (i != 1500) {
                LogUtil.a("PLGACHIEVE_LevelUtil", "invalid key");
                return 0;
            }
        }
        return 10;
    }

    public static void d(Map<Integer, Integer> map) {
        if (map == null) {
            return;
        }
        map.put(1, Integer.valueOf(R.mipmap._2131820937_res_0x7f110189));
        map.put(2, Integer.valueOf(R.mipmap._2131820938_res_0x7f11018a));
        map.put(3, Integer.valueOf(R.mipmap._2131820939_res_0x7f11018b));
        map.put(4, Integer.valueOf(R.mipmap._2131820940_res_0x7f11018c));
        map.put(5, Integer.valueOf(R.mipmap._2131820941_res_0x7f11018d));
        map.put(6, Integer.valueOf(R.mipmap._2131820942_res_0x7f11018e));
        map.put(7, Integer.valueOf(R.mipmap._2131820943_res_0x7f11018f));
        map.put(8, Integer.valueOf(R.mipmap._2131820944_res_0x7f110190));
        map.put(9, Integer.valueOf(R.mipmap._2131820945_res_0x7f110191));
        map.put(10, Integer.valueOf(R.mipmap._2131820946_res_0x7f110192));
        map.put(11, Integer.valueOf(R.mipmap._2131820948_res_0x7f110194));
        map.put(12, Integer.valueOf(R.mipmap._2131820950_res_0x7f110196));
        map.put(13, Integer.valueOf(R.mipmap._2131820952_res_0x7f110198));
        map.put(14, Integer.valueOf(R.mipmap._2131820954_res_0x7f11019a));
        map.put(15, Integer.valueOf(R.mipmap._2131820956_res_0x7f11019c));
        map.put(16, Integer.valueOf(R.mipmap._2131820958_res_0x7f11019e));
        map.put(17, Integer.valueOf(R.mipmap._2131820960_res_0x7f1101a0));
        map.put(18, Integer.valueOf(R.mipmap._2131820962_res_0x7f1101a2));
        map.put(19, Integer.valueOf(R.mipmap._2131820964_res_0x7f1101a4));
        map.put(20, Integer.valueOf(R.mipmap._2131820967_res_0x7f1101a7));
    }

    public static void e(Map<Integer, Integer> map) {
        if (map == null) {
            return;
        }
        map.put(1, Integer.valueOf(R.mipmap._2131820907_res_0x7f11016b));
        map.put(2, Integer.valueOf(R.mipmap._2131820907_res_0x7f11016b));
        map.put(3, Integer.valueOf(R.mipmap._2131820907_res_0x7f11016b));
        map.put(4, Integer.valueOf(R.mipmap._2131820908_res_0x7f11016c));
        map.put(5, Integer.valueOf(R.mipmap._2131820908_res_0x7f11016c));
        map.put(6, Integer.valueOf(R.mipmap._2131820908_res_0x7f11016c));
        map.put(7, Integer.valueOf(R.mipmap._2131820909_res_0x7f11016d));
        map.put(8, Integer.valueOf(R.mipmap._2131820909_res_0x7f11016d));
        map.put(9, Integer.valueOf(R.mipmap._2131820909_res_0x7f11016d));
        map.put(10, Integer.valueOf(R.mipmap._2131820910_res_0x7f11016e));
        map.put(11, Integer.valueOf(R.mipmap._2131820910_res_0x7f11016e));
        map.put(12, Integer.valueOf(R.mipmap._2131820910_res_0x7f11016e));
        map.put(13, Integer.valueOf(R.mipmap._2131820911_res_0x7f11016f));
        map.put(14, Integer.valueOf(R.mipmap._2131820911_res_0x7f11016f));
        map.put(15, Integer.valueOf(R.mipmap._2131820911_res_0x7f11016f));
        map.put(16, Integer.valueOf(R.mipmap._2131820912_res_0x7f110170));
        map.put(17, Integer.valueOf(R.mipmap._2131820912_res_0x7f110170));
        map.put(18, Integer.valueOf(R.mipmap._2131820912_res_0x7f110170));
        map.put(19, Integer.valueOf(R.mipmap._2131820913_res_0x7f110171));
        map.put(20, Integer.valueOf(R.mipmap._2131820913_res_0x7f110171));
    }

    public static void a(Map<Integer, Integer> map) {
        if (map == null) {
            return;
        }
        map.put(1, Integer.valueOf(R.mipmap._2131820966_res_0x7f1101a6));
        map.put(2, Integer.valueOf(R.mipmap._2131820969_res_0x7f1101a9));
        map.put(3, Integer.valueOf(R.mipmap._2131820970_res_0x7f1101aa));
        map.put(4, Integer.valueOf(R.mipmap._2131820971_res_0x7f1101ab));
        map.put(5, Integer.valueOf(R.mipmap._2131820972_res_0x7f1101ac));
        map.put(6, Integer.valueOf(R.mipmap._2131820973_res_0x7f1101ad));
        map.put(7, Integer.valueOf(R.mipmap._2131820974_res_0x7f1101ae));
        map.put(8, Integer.valueOf(R.mipmap._2131820975_res_0x7f1101af));
        map.put(9, Integer.valueOf(R.mipmap._2131820976_res_0x7f1101b0));
        map.put(10, Integer.valueOf(R.mipmap._2131820947_res_0x7f110193));
        map.put(11, Integer.valueOf(R.mipmap._2131820949_res_0x7f110195));
        map.put(12, Integer.valueOf(R.mipmap._2131820951_res_0x7f110197));
        map.put(13, Integer.valueOf(R.mipmap._2131820953_res_0x7f110199));
        map.put(14, Integer.valueOf(R.mipmap._2131820955_res_0x7f11019b));
        map.put(15, Integer.valueOf(R.mipmap._2131820957_res_0x7f11019d));
        map.put(16, Integer.valueOf(R.mipmap._2131820959_res_0x7f11019f));
        map.put(17, Integer.valueOf(R.mipmap._2131820961_res_0x7f1101a1));
        map.put(18, Integer.valueOf(R.mipmap._2131820963_res_0x7f1101a3));
        map.put(19, Integer.valueOf(R.mipmap._2131820965_res_0x7f1101a5));
        map.put(20, Integer.valueOf(R.mipmap._2131820968_res_0x7f1101a8));
    }

    public static void b(Map<Integer, Integer> map) {
        if (map == null) {
            return;
        }
        map.put(1, Integer.valueOf(R.string._2130840699_res_0x7f020c7b));
        map.put(2, Integer.valueOf(R.string._2130840700_res_0x7f020c7c));
        map.put(3, Integer.valueOf(R.string._2130840696_res_0x7f020c78));
        map.put(4, Integer.valueOf(R.string._2130840697_res_0x7f020c79));
        map.put(5, Integer.valueOf(R.string._2130840698_res_0x7f020c7a));
        map.put(6, Integer.valueOf(R.string._2130840782_res_0x7f020cce));
        map.put(7, Integer.valueOf(R.string._2130840783_res_0x7f020ccf));
        map.put(8, Integer.valueOf(R.string._2130840784_res_0x7f020cd0));
        map.put(9, Integer.valueOf(R.string._2130840785_res_0x7f020cd1));
        map.put(10, Integer.valueOf(R.string._2130840786_res_0x7f020cd2));
        map.put(11, Integer.valueOf(R.string._2130840787_res_0x7f020cd3));
        map.put(12, Integer.valueOf(R.string._2130840788_res_0x7f020cd4));
        map.put(13, Integer.valueOf(R.string._2130840789_res_0x7f020cd5));
        map.put(14, Integer.valueOf(R.string._2130840790_res_0x7f020cd6));
        map.put(15, Integer.valueOf(R.string._2130840791_res_0x7f020cd7));
        map.put(16, Integer.valueOf(R.string._2130840792_res_0x7f020cd8));
        map.put(17, Integer.valueOf(R.string._2130840793_res_0x7f020cd9));
        map.put(18, Integer.valueOf(R.string._2130840794_res_0x7f020cda));
        map.put(19, Integer.valueOf(R.string._2130840795_res_0x7f020cdb));
        map.put(20, Integer.valueOf(R.string._2130840796_res_0x7f020cdc));
    }

    public static Map<Integer, String> e() {
        HashMap hashMap = new HashMap(16);
        hashMap.put(10050, BaseApplication.getContext().getResources().getString(R.string._2130841041_res_0x7f020dd1));
        hashMap.put(Integer.valueOf(PrebakedEffectId.RT_COIN_DROP), BaseApplication.getContext().getResources().getString(R.string._2130841042_res_0x7f020dd2));
        hashMap.put(10010, BaseApplication.getContext().getResources().getString(R.string._2130841043_res_0x7f020dd3));
        hashMap.put(20050, BaseApplication.getContext().getResources().getString(R.string._2130841044_res_0x7f020dd4));
        hashMap.put(20030, BaseApplication.getContext().getResources().getString(R.string._2130841044_res_0x7f020dd4));
        hashMap.put(20010, BaseApplication.getContext().getResources().getString(R.string._2130841044_res_0x7f020dd4));
        hashMap.put(30050, BaseApplication.getContext().getResources().getString(R.string._2130841045_res_0x7f020dd5));
        hashMap.put(30030, BaseApplication.getContext().getResources().getString(R.string._2130841046_res_0x7f020dd6));
        hashMap.put(30010, BaseApplication.getContext().getResources().getString(R.string._2130841047_res_0x7f020dd7));
        hashMap.put(40050, BaseApplication.getContext().getResources().getString(R.string._2130841048_res_0x7f020dd8));
        hashMap.put(40030, BaseApplication.getContext().getResources().getString(R.string._2130841049_res_0x7f020dd9));
        hashMap.put(40010, BaseApplication.getContext().getResources().getString(R.string._2130841050_res_0x7f020dda));
        hashMap.put(Integer.valueOf(HiHealthStatusCodes.HEALTH_RECORDS_NOT_EXIST), BaseApplication.getContext().getResources().getString(R.string._2130841051_res_0x7f020ddb));
        hashMap.put(Integer.valueOf(HiHealthStatusCodes.NO_NETWORK), BaseApplication.getContext().getResources().getString(R.string._2130841052_res_0x7f020ddc));
        hashMap.put(Integer.valueOf(HiHealthStatusCodes.APP_NOT_ENABLED_ERROR), BaseApplication.getContext().getResources().getString(R.string._2130841053_res_0x7f020ddd));
        hashMap.put(Integer.valueOf(OrderStatusCode.ORDER_HWID_NOT_LOGIN), BaseApplication.getContext().getResources().getString(R.string._2130841054_res_0x7f020dde));
        hashMap.put(60030, BaseApplication.getContext().getResources().getString(R.string._2130841055_res_0x7f020ddf));
        hashMap.put(60010, BaseApplication.getContext().getResources().getString(R.string._2130841056_res_0x7f020de0));
        return hashMap;
    }

    public static int j(int i) {
        if (i < 4) {
            return Color.parseColor("#2F2521");
        }
        if (i < 7) {
            return Color.parseColor("#2B1500");
        }
        if (i < 10) {
            return Color.parseColor("#1E0038");
        }
        if (i < 13) {
            return Color.parseColor("#001600");
        }
        if (i < 16) {
            return Color.parseColor("#000133");
        }
        if (i < 19) {
            return Color.parseColor("#2C0000");
        }
        return Color.parseColor("#100000");
    }

    public static int c(int i) {
        if (i < 1) {
            return 0;
        }
        if (i > 20) {
            return c[19];
        }
        return c[i - 1];
    }

    public static double a(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0.0d;
        }
        return mkw.a((i * 100.0d) / i2, mkw.e(i2 / (i * 100)));
    }

    public static int c(double d2) {
        return e(e(d2));
    }

    public static int e(int i) {
        for (int i2 = 0; i2 < 20; i2++) {
            if (i < c[i2]) {
                return i2;
            }
        }
        return 20;
    }

    public static int k(int i) {
        switch (i) {
            case 1:
                return c[3];
            case 2:
                return c[6];
            case 3:
                return c[9];
            case 4:
                return c[12];
            case 5:
                return c[15];
            case 6:
                return c[18];
            case 7:
                return c[19];
            default:
                return 0;
        }
    }

    public static List<LevelInfoDesc> b(int i, int i2, ArrayList<String> arrayList, List<String> list) {
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(c(1, b(i, i2, arrayList, 1), list));
        arrayList2.add(c(2, b(i, i2, arrayList, 2), list));
        arrayList2.add(c(3, b(i, i2, arrayList, 3), list));
        arrayList2.add(c(4, b(i, i2, arrayList, 4), list));
        arrayList2.add(c(5, b(i, i2, arrayList, 5), list));
        arrayList2.add(c(6, b(i, i2, arrayList, 6), list));
        arrayList2.add(c(7, b(i, i2, arrayList, 7), list));
        return arrayList2;
    }

    public static String b(int i) {
        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                LogUtil.a("PLGACHIEVE_LevelUtil", "getLevelTitle: default");
                break;
        }
        return BaseApplication.getContext().getResources().getString(R.string._2130841021_res_0x7f020dbd);
    }

    public static int d(int i) {
        if (i >= 1) {
            int i2 = i - 1;
            Integer[] numArr = e;
            if (i2 < numArr.length) {
                return numArr[i2].intValue();
            }
        }
        return R.mipmap._2131820907_res_0x7f11016b;
    }

    public static int a(int i) {
        if (i >= 1) {
            int i2 = i - 1;
            Integer[] numArr = d;
            if (i2 < numArr.length) {
                return numArr[i2].intValue();
            }
        }
        return R.mipmap._2131821386_res_0x7f11034a;
    }

    public static String c(Context context, int i) {
        return context.getResources().getString(R.string._2130840814_res_0x7f020cee, Integer.valueOf(i));
    }

    private static int m(int i) {
        if (i >= 1) {
            int i2 = i - 1;
            Integer[] numArr = f15050a;
            if (i2 < numArr.length) {
                return numArr[i2].intValue();
            }
        }
        return R.mipmap._2131821370_res_0x7f11033a;
    }

    private static int n(int i) {
        int i2;
        int color = BaseApplication.getContext().getResources().getColor(R.color._2131298692_res_0x7f090984);
        return (i < 1 || (i2 = i + (-1)) >= mjv.b().length) ? color : BaseApplication.getContext().getResources().getColor(mjv.b()[i2].intValue());
    }

    private static String b(int i, int i2, ArrayList<String> arrayList, int i3) {
        String b = jed.b(0.0d, 2, 0);
        int i4 = i3 + 1;
        if (koq.d(arrayList, i4)) {
            b = arrayList.get(i4);
        }
        String c2 = c(h(i), i3, i2);
        return i3 > h(i) ? BaseApplication.getContext().getResources().getString(R.string._2130841057_res_0x7f020de1, c2) : BaseApplication.getContext().getResources().getString(R.string._2130841028_res_0x7f020dc4, b, c2);
    }

    public static String c(int i, int i2, int i3) {
        if (i2 < i) {
            return jed.b(k(i2), 1, 0) + " / " + jed.b(k(i2), 1, 0);
        }
        if (i2 == i) {
            return jed.b(i3, 1, 0) + " / " + jed.b(k(i2), 1, 0);
        }
        return " ≥ " + jed.b(k(i2 - 1), 1, 0);
    }

    public static String c(int i, ArrayList<mki> arrayList, int i2) {
        int i3;
        int i4 = i;
        if (koq.b(arrayList)) {
            return "";
        }
        if (i4 != i(i2) && i4 != g(i2) && i4 != f(i2)) {
            i4 = i(i2);
        }
        Iterator<mki> it = arrayList.iterator();
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (it.hasNext()) {
            d3 += r8.c();
            if (it.next().e() <= i4) {
                d2 += r8.c();
            }
        }
        double d4 = (d2 * 100.0d) / d3;
        LogUtil.a("PLGACHIEVE_LevelUtil", "getSuperPercent num =", Double.valueOf(d2), " sum ", Double.valueOf(d3), " percent ", Double.valueOf(d4));
        double d5 = 100.0d - d4;
        int i5 = (int) d5;
        if (i5 < 1) {
            if (d5 != 0.0d) {
                i3 = mkw.b(d5);
            }
            i3 = 0;
        } else {
            if (i5 < 10) {
                i3 = 2;
            }
            i3 = 0;
        }
        return jed.b(d4, 2, i3);
    }

    private static LevelInfoDesc c(int i, String str, List<String> list) {
        LevelInfoDesc levelInfoDesc = new LevelInfoDesc();
        levelInfoDesc.setName(b(i));
        levelInfoDesc.setDescription(str);
        levelInfoDesc.setLabelList(list);
        levelInfoDesc.setBadgeImage(d(i));
        levelInfoDesc.setIcon(m(i));
        levelInfoDesc.setBackColor(n(i));
        return levelInfoDesc;
    }

    public static ArrayList<String> c(String str) {
        String[] split;
        ArrayList<String> arrayList = new ArrayList<>(10);
        if (!TextUtils.isEmpty(str) && (split = str.replace("[", "").replace("]", "").replace(" ", "").split(",")) != null && split.length != 0) {
            Collections.addAll(arrayList, split);
        }
        return arrayList;
    }

    public static boolean a() {
        String b = mct.b(BaseApplication.getContext(), "levelTypeUpgraded");
        LogUtil.a("PLGACHIEVE_LevelUtil", "enter isHaveLevelUpgradeMsg(): levelUpgradeTime = ", b);
        if (System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), b) < 86400000) {
            return true;
        }
        boolean a2 = SharedPreferenceManager.a(String.valueOf(20003), "new_level_first_use", true);
        LogUtil.a("PLGACHIEVE_LevelUtil", "enter isHaveLevelUpgradeMsg(): isFirstUse = ", Boolean.valueOf(a2));
        return a2;
    }

    public static void a(int i, int i2, long j, String str, boolean z) {
        LogUtil.a("PLGACHIEVE_LevelUtil", "doEventClickBi event ", Integer.valueOf(i), " level ", Integer.valueOf(i2), " duration ", Long.valueOf(j), " taskIdList ", str, " isHasNewUserTask ", Boolean.valueOf(z));
        HashMap hashMap = new HashMap(8);
        hashMap.put("click", 1);
        hashMap.put("currentLevel", Integer.valueOf(i2));
        hashMap.put("taskid", str);
        hashMap.put("hasBeginnerTask", Boolean.valueOf(z));
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("duration", Long.valueOf(j));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.LEVEL_SHARE_1100070.value(), hashMap, 0);
    }

    public static String d(ArrayList<mkg> arrayList, int i) {
        mkj b;
        ArrayList arrayList2 = new ArrayList(10);
        if (koq.b(arrayList)) {
            LogUtil.h("PLGACHIEVE_LevelUtil", "getTaskId tasks is empty.");
            return arrayList2.toString();
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if ((i != 1 || i2 >= 3) && ((i != 0 || i2 < 3) && (b = arrayList.get(i2).b()) != null)) {
                if (!mle.d(b.n())) {
                    arrayList2.add(String.valueOf(b.n()));
                } else if (mle.d(b.n()) && mht.b(b.f()) < 2) {
                    arrayList2.add(String.valueOf(b.n()));
                }
            }
        }
        LogUtil.a("PLGACHIEVE_LevelUtil", "getTaskId direction = ", Integer.valueOf(i), " taskIdList ", arrayList2.toString());
        return arrayList2.toString();
    }

    public static boolean c(ArrayList<mkg> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("PLGACHIEVE_LevelUtil", "isHasBeginnerTask tasks is empty.");
            return false;
        }
        Iterator<mkg> it = arrayList.iterator();
        while (it.hasNext()) {
            mkj b = it.next().b();
            if (b != null && mle.d(b.n()) && mht.b(b.f()) < 2) {
                return true;
            }
        }
        return false;
    }
}
