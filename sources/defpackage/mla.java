package defpackage;

import com.google.android.gms.common.util.GmsVersion;
import com.huawei.health.R;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/* loaded from: classes6.dex */
public class mla {

    /* renamed from: a, reason: collision with root package name */
    private static mla f15048a;
    private static final Object d = new Object();

    private mla() {
    }

    public static mla e() {
        mla mlaVar;
        synchronized (d) {
            if (f15048a == null) {
                f15048a = new mla();
            }
            mlaVar = f15048a;
        }
        return mlaVar;
    }

    private String b(int i, int i2) {
        return BaseApplication.getContext().getResources().getString(i, UnitUtil.e(i2, 1, 0));
    }

    private String c(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    public Map<String, MedalInfo> c(boolean z) {
        Map<String, MedalInfo> d2;
        HashMap hashMap = new HashMap(20);
        HashMap hashMap2 = new HashMap(20);
        HashMap hashMap3 = new HashMap(20);
        mlb.b(hashMap2);
        if (z) {
            mlb.d(hashMap3);
        } else {
            mlb.e(hashMap3);
        }
        if (UnitUtil.h()) {
            d2 = a(hashMap, hashMap2, hashMap3);
        } else {
            d2 = d(hashMap, hashMap2, hashMap3);
        }
        try {
            d2.put("B_7", new MedalInfo("B7", hashMap2.get("B7").intValue(), hashMap3.get("B7").intValue(), b(R.string._2130840744_res_0x7f020ca8, 7), BaseApplication.getContext().getResources().getString(R.string._2130840756_res_0x7f020cb4, c(7), c(10000))));
            d2.put("B_21", new MedalInfo("B21", hashMap2.get("B21").intValue(), hashMap3.get("B21").intValue(), b(R.string._2130840744_res_0x7f020ca8, 21), BaseApplication.getContext().getResources().getString(R.string._2130840755_res_0x7f020cb3, c(21), c(10000))));
            d2.put("B_100", new MedalInfo("B100", hashMap2.get("B100").intValue(), hashMap3.get("B100").intValue(), b(R.string._2130840744_res_0x7f020ca8, 100), BaseApplication.getContext().getResources().getString(R.string._2130840754_res_0x7f020cb2, c(100), c(10000))));
            d2.put("A2_10", b(hashMap2, hashMap3, "A2_10", 10));
            d2.put("A2_50", b(hashMap2, hashMap3, "A2_50", 50));
            d2.put("A2_100", b(hashMap2, hashMap3, "A2_100", 100));
            return c(d2, hashMap2, hashMap3);
        } catch (IllegalFormatConversionException | MissingFormatArgumentException unused) {
            LogUtil.b("PLGACHIEVE_CombinationOldMyMedals", "string format exception");
            return d2;
        }
    }

    private Map<String, MedalInfo> a(Map<String, MedalInfo> map, Map<String, Integer> map2, Map<String, Integer> map3) {
        String e = mlb.e(5.0d, 0);
        map.put("A_5", new MedalInfo("A5", map2.get("A5").intValue(), map3.get("A5").intValue(), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903168_res_0x7f030080, mlb.l(e), e), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903169_res_0x7f030081, mlb.l(e), e)));
        String e2 = mlb.e(10.0d, 0);
        map.put("A_10", new MedalInfo("A10", map2.get("A10").intValue(), map3.get("A10").intValue(), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903168_res_0x7f030080, mlb.l(e2), e2), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903169_res_0x7f030081, mlb.l(e2), e2)));
        String e3 = mlb.e(21.0975d, 4);
        map.put("A_20", new MedalInfo("A20", map2.get("A20").intValue(), map3.get("A20").intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840738_res_0x7f020ca2), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903169_res_0x7f030081, mlb.l(e3), e3)));
        String e4 = mlb.e(42.195d, 3);
        map.put("A_40", new MedalInfo("A40", map2.get("A40").intValue(), map3.get("A40").intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840739_res_0x7f020ca3), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903169_res_0x7f030081, mlb.l(e4), e4)));
        String e5 = mlb.e(100.0d, 0);
        map.put("A_100", new MedalInfo(WatchFaceConstant.PRESET_RES, map2.get(WatchFaceConstant.PRESET_RES).intValue(), map3.get(WatchFaceConstant.PRESET_RES).intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840740_res_0x7f020ca4), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903170_res_0x7f030082, mlb.l(e5), e5)));
        return map;
    }

    private Map<String, MedalInfo> d(Map<String, MedalInfo> map, Map<String, Integer> map2, Map<String, Integer> map3) {
        try {
            map.put("A_5", new MedalInfo("A5", map2.get("A5").intValue(), map3.get("A5").intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840742_res_0x7f020ca6, b(R.string._2130841421_res_0x7f020f4d, 5)), BaseApplication.getContext().getResources().getString(R.string._2130840743_res_0x7f020ca7, b(R.string._2130841421_res_0x7f020f4d, 5))));
            map.put("A_10", new MedalInfo("A10", map2.get("A10").intValue(), map3.get("A10").intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840742_res_0x7f020ca6, b(R.string._2130841421_res_0x7f020f4d, 10)), BaseApplication.getContext().getResources().getString(R.string._2130840743_res_0x7f020ca7, b(R.string._2130841421_res_0x7f020f4d, 10))));
            map.put("A_20", new MedalInfo("A20", map2.get("A20").intValue(), map3.get("A20").intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840738_res_0x7f020ca2), BaseApplication.getContext().getResources().getString(R.string._2130840743_res_0x7f020ca7, BaseApplication.getContext().getResources().getString(R.string._2130841421_res_0x7f020f4d, UnitUtil.e(21.0975d, 1, 4)))));
            map.put("A_40", new MedalInfo("A40", map2.get("A40").intValue(), map3.get("A40").intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840739_res_0x7f020ca3), BaseApplication.getContext().getResources().getString(R.string._2130840743_res_0x7f020ca7, BaseApplication.getContext().getResources().getString(R.string._2130841421_res_0x7f020f4d, UnitUtil.e(42.195d, 1, 3)))));
            map.put("A_100", new MedalInfo(WatchFaceConstant.PRESET_RES, map2.get(WatchFaceConstant.PRESET_RES).intValue(), map3.get(WatchFaceConstant.PRESET_RES).intValue(), BaseApplication.getContext().getResources().getString(R.string._2130840740_res_0x7f020ca4), BaseApplication.getContext().getResources().getString(R.string._2130840743_res_0x7f020ca7, b(R.string._2130841421_res_0x7f020f4d, 100))));
        } catch (IllegalFormatConversionException | MissingFormatArgumentException unused) {
            LogUtil.b("PLGACHIEVE_CombinationOldMyMedals", "string format exception");
        }
        return map;
    }

    private Map<String, MedalInfo> c(Map<String, MedalInfo> map, Map<String, Integer> map2, Map<String, Integer> map3) {
        map.put("C_10000", new MedalInfo("C10000", map2.get("C10000").intValue(), map3.get("C10000").intValue(), b(R.string._2130840745_res_0x7f020ca9, 10000), b(R.string._2130840752_res_0x7f020cb0, 10000)));
        map.put("C_100000", new MedalInfo("C100000", map2.get("C100000").intValue(), map3.get("C100000").intValue(), b(R.string._2130840745_res_0x7f020ca9, 100000), b(R.string._2130840751_res_0x7f020caf, 100000)));
        map.put("C_300000", new MedalInfo("C300000", map2.get("C300000").intValue(), map3.get("C300000").intValue(), b(R.string._2130840745_res_0x7f020ca9, 300000), b(R.string._2130840753_res_0x7f020cb1, 300000)));
        map.put("C_1000000", new MedalInfo("C1000000", map2.get("C1000000").intValue(), map3.get("C1000000").intValue(), b(R.string._2130840745_res_0x7f020ca9, 1000000), b(R.string._2130840750_res_0x7f020cae, 1000000)));
        map.put("C_3000000", new MedalInfo("C3000000", map2.get("C3000000").intValue(), map3.get("C3000000").intValue(), b(R.string._2130840745_res_0x7f020ca9, 3000000), b(R.string._2130840749_res_0x7f020cad, 3000000)));
        map.put("C_5000000", new MedalInfo("C5000000", map2.get("C5000000").intValue(), map3.get("C5000000").intValue(), b(R.string._2130840745_res_0x7f020ca9, GmsVersion.VERSION_LONGHORN), b(R.string._2130840748_res_0x7f020cac, GmsVersion.VERSION_LONGHORN)));
        map.put("C_10000000", new MedalInfo("C10000000", map2.get("C10000000").intValue(), map3.get("C10000000").intValue(), b(R.string._2130840745_res_0x7f020ca9, ExceptionCode.CRASH_EXCEPTION), b(R.string._2130840747_res_0x7f020cab, ExceptionCode.CRASH_EXCEPTION)));
        map.put("C_15000000", new MedalInfo("C15000000", map2.get("C15000000").intValue(), map3.get("C15000000").intValue(), b(R.string._2130840745_res_0x7f020ca9, 15000000), b(R.string._2130840746_res_0x7f020caa, 15000000)));
        return map;
    }

    private MedalInfo b(Map<String, Integer> map, Map<String, Integer> map2, String str, int i) {
        if (UnitUtil.h()) {
            String e = mlb.e(i, 0);
            LogUtil.c("PLGACHIEVE_CombinationOldMyMedals", "buildCycleMedalInfo");
            return new MedalInfo(str, map.get(str).intValue(), map2.get(str).intValue(), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903174_res_0x7f030086, mlb.l(e), e), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903173_res_0x7f030085, mlb.l(e), e));
        }
        String e2 = UnitUtil.e(i, 1, 0);
        return new MedalInfo(str, map.get(str).intValue(), map2.get(str).intValue(), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903172_res_0x7f030084, mlb.l(e2), e2), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903171_res_0x7f030083, mlb.l(e2), e2));
    }
}
