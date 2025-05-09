package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class gts {
    private static Context b;
    private static volatile gts e;

    public int[] b() {
        return new int[]{42052, 42102, 42152, 42202, 42304, 42252, 42404, 42352, 42054, 42104, 42154, 42204, 42303, 42254, 42404, 42354, 42053, 42103, 42153, 42203, 42302, 42253, 42403, 42353, 42055, 42105, 42155, 42205, 42303, 42255, 42405, 42355, 42056, 42106, 42156, 42206, 42311, 42256, 42406, 42356, 42005, Constants.REBACK_MARKET_ENTRANCE, Constants.REBACK_MARKET_RESULT_CODE, 42008, 22, 21};
    }

    private gts() {
    }

    public static gts b(Context context) {
        if (context != null) {
            b = context.getApplicationContext();
        } else {
            b = BaseApplication.getContext();
        }
        if (e == null) {
            synchronized (gts.class) {
                if (e == null) {
                    e = new gts();
                }
            }
        }
        return e;
    }

    public void e(long j, long j2, int i, int i2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HwHealthTrackMonthDataManager", "requestTrackMonthData sportType = ", Integer.valueOf(i2), " timeUnit = ", Integer.valueOf(i));
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(j);
        hiSportStatDataAggregateOption.setEndTime(j2);
        hiSportStatDataAggregateOption.setAggregateType(1);
        if (i2 != 0) {
            hiSportStatDataAggregateOption.setHiHealthTypes(c(i2));
        }
        int[] e2 = e(i2);
        String[] d = d(i2);
        LogUtil.a("Common_HwHealthTrackMonthDataManager", "types", e2);
        hiSportStatDataAggregateOption.setConstantsKey(d);
        hiSportStatDataAggregateOption.setType(e2);
        hiSportStatDataAggregateOption.setGroupUnitType(i);
        hiSportStatDataAggregateOption.setReadType(0);
        HiHealthNativeApi.a(b).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: gts.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                if (iBaseResponseCallback == null) {
                    LogUtil.h("Common_HwHealthTrackMonthDataManager", "requestTrackMonthData callback is null");
                } else if (koq.b(list)) {
                    LogUtil.h("Common_HwHealthTrackMonthDataManager", "requestTrackMonthData() all run data null or empty ");
                    iBaseResponseCallback.d(i3, list);
                } else {
                    iBaseResponseCallback.d(i3, list);
                }
            }
        });
    }

    private String[] d(int i) {
        String[] a2 = hkc.a(b, i);
        if (i == 0) {
            a2 = (String[]) c(d(), a2);
        }
        return i == 286 ? (String[]) c(hkc.a(b, HeartRateThresholdConfig.HEART_RATE_LIMIT), a2) : a2;
    }

    private int[] e(int i) {
        int[] c = hkc.c(b, i);
        if (i == 0) {
            int[] b2 = b();
            int[] iArr = new int[b2.length + c.length];
            System.arraycopy(b2, 0, iArr, 0, b2.length);
            System.arraycopy(c, 0, iArr, b2.length, c.length);
            c = iArr;
        }
        if (i != 286) {
            return c;
        }
        int[] iArr2 = new int[c.length + 3];
        System.arraycopy(new int[]{42404, 42403, 42405}, 0, iArr2, 0, 3);
        System.arraycopy(c, 0, iArr2, 3, c.length);
        return iArr2;
    }

    private static int[] c(int i) {
        return i == 287 ? new int[]{i, 291, ComponentInfo.PluginPay_A_N, 288} : a(i);
    }

    public String[] d() {
        return new String[]{"Track_2572", "Track_2582", "Track_2592", "Track_2622", "Track_2715", "Track_5122", "Track_2204", "Track_2172", "Track_2574", "Track_2584", "Track_2594", "Track_2624", "Track_2714", "Track_5124", "Track_2204", "Track_2174", "Track_2573", "Track_2583", "Track_2593", "Track_2623", "Track_2713", "Track_5123", "Track_2203", "Track_2173", "Track_2575", "Track_2585", "Track_2595", "Track_2625", "Track_2714", "Track_5125", "Track_2205", "Track_2175", "Track_2576", "Track_2586", "Track_2596", "Track_2626", "Track_2716", "Track_5126", "Track_2206", "Track_2176", "Track_Count_Sum", "Track_Duration_Sum", "Track_Calorie_Sum", "Track_Abnormal_Count_Sum", "Track_28722", "Track_28721"};
    }

    private static <T> T[] c(T[] tArr, T[]... tArr2) {
        int length = tArr.length;
        for (T[] tArr3 : tArr2) {
            length += tArr3.length;
        }
        T[] tArr4 = (T[]) Arrays.copyOf(tArr, length);
        int length2 = tArr.length;
        for (T[] tArr5 : tArr2) {
            System.arraycopy(tArr5, 0, tArr4, length2, tArr5.length);
            length2 += tArr5.length;
        }
        return tArr4;
    }

    public static int[] a(int i) {
        return i != 286 ? new int[]{i} : new int[]{i, 30001};
    }
}
