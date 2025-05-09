package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class psb {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f16243a = false;
    private static long b;

    public static void e(long j, long j2, final Map<String, ResponseCallback<Map<Long, IStorageModel>>> map) {
        final String b2 = nsj.b(j);
        final SparseArray<List<HiHealthData>> dsB_ = prz.dsB_(b2);
        if (f16243a && dsB_.size() > 0) {
            LogUtil.a("HeartRateStorageHelper", "queryDayDayData use cache");
            dsG_(dsB_, map);
        }
        HiDataReadOption c = c(j, j2 - 1);
        LogUtil.a("HeartRateStorageHelper", "queryDayDayData start");
        b = System.currentTimeMillis();
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(c, new HiDataReadResultListener() { // from class: psb.3
            private boolean b = false;

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("HeartRateStorageHelper", "queryDayDayData data ", obj, " errorCode ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
                if (this.b) {
                    return;
                }
                this.b = i2 == 2;
                LogUtil.a("HeartRateStorageHelper", "queryDayDayData success: ", Long.valueOf(System.currentTimeMillis() - psb.b), "ms");
                if (!(obj instanceof SparseArray)) {
                    Iterator it = map.values().iterator();
                    while (it.hasNext()) {
                        ((ResponseCallback) it.next()).onResult(-1, null);
                    }
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (psb.f16243a) {
                    boolean unused = psb.f16243a = false;
                    long currentTimeMillis = System.currentTimeMillis();
                    boolean dsD_ = prz.dsD_(dsB_, sparseArray);
                    LogUtil.a("HeartRateStorageHelper", "queryDayDayData diff time: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
                    if (!dsD_) {
                        LogUtil.a("HeartRateStorageHelper", "queryDayDayData same cache");
                        return;
                    }
                    prz.dsE_(b2, sparseArray);
                }
                psb.dsG_(sparseArray, map);
            }
        });
    }

    private static HiDataReadOption c(long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{2002, 2018, 2105, 2101, 2102});
        hiDataReadOption.setReadType(0);
        return hiDataReadOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dsG_(SparseArray<List<HiHealthData>> sparseArray, Map<String, ResponseCallback<Map<Long, IStorageModel>>> map) {
        for (Map.Entry<String, ResponseCallback<Map<Long, IStorageModel>>> entry : map.entrySet()) {
            String key = entry.getKey();
            HashMap hashMap = new HashMap(16);
            if (HwHealthChartHolder.LAYER_ID_NORMAL_HR.equals(key)) {
                c(sparseArray.get(2018), hashMap, "HR_NORMAL_DETAIL");
                c(sparseArray.get(2105), hashMap, "HR_NORMAL_DETAIL");
                c(sparseArray.get(2002), hashMap, "HR_NORMAL_DETAIL");
                psg.d(hashMap, true);
            } else if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(key)) {
                c(psg.b(sparseArray.get(2018), sparseArray.get(2105)), hashMap, "HR_REST_DETAIL");
                psg.c((Map<Long, IStorageModel>) hashMap, true);
            } else if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(key)) {
                psg.e(hashMap, "HR_WARNING_DETAIL", sparseArray.get(2101), null);
                psg.b((Map<Long, IStorageModel>) hashMap, true);
            } else if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(key)) {
                psg.e(hashMap, "BRADYCARDIA_DETAIL", sparseArray.get(2102), null);
                psg.a((Map<Long, IStorageModel>) hashMap, true);
            } else {
                LogUtil.h("HeartRateStorageHelper", "showModeArg.acquireDataLayerId(): ", key);
            }
            LogUtil.a("HeartRateStorageHelper", "parsesDayData resultMap ", hashMap);
            entry.getValue().onResult(0, hashMap);
        }
    }

    private static void c(List<HiHealthData> list, Map<Long, IStorageModel> map, String str) {
        StorageGenericModel storageGenericModel;
        LogUtil.a("HeartRateStorageHelper", "loadDayValue resultMap ", map, " hrKey ", str);
        if (koq.b(list)) {
            return;
        }
        LogUtil.a("HeartRateStorageHelper", "loadDayValue dataList size", Integer.valueOf(list.size()));
        long currentTimeMillis = System.currentTimeMillis();
        float f = 0.0f;
        long j = 0;
        float f2 = 0.0f;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("HeartRateStorageHelper", "loadDayValue healthData is null");
            } else {
                long startTime = hiHealthData.getStartTime();
                if (map.containsKey(Long.valueOf(startTime))) {
                    storageGenericModel = (StorageGenericModel) map.get(Long.valueOf(startTime));
                } else {
                    storageGenericModel = new StorageGenericModel();
                }
                if (storageGenericModel != null) {
                    storageGenericModel.c(str, (float) hiHealthData.getValue());
                    map.put(Long.valueOf(startTime), storageGenericModel);
                }
                if ("HR_NORMAL_DETAIL".equals(str)) {
                    if (j < startTime) {
                        f2 = hiHealthData.getFloat(str);
                        j = startTime;
                    }
                    if (currentTimeMillis > startTime) {
                        f = hiHealthData.getFloat(str);
                        currentTimeMillis = startTime;
                    }
                }
            }
        }
        LogUtil.a("HeartRateStorageHelper", "loadDayValue firstTime ", Long.valueOf(currentTimeMillis), " firstHrValue ", Float.valueOf(f), " lastTime ", Long.valueOf(j), " lastHrValue ", Float.valueOf(f2), " resultMap ", map, " size ", Integer.valueOf(map.size()));
    }

    public static void d(boolean z) {
        f16243a = z;
    }
}
