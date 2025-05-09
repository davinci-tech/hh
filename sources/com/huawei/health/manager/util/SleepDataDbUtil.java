package com.huawei.health.manager.util;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SleepDataDbUtil {
    private static final Object c = new Object();

    private SleepDataDbUtil() {
    }

    public static void c(Context context, Map<String, Object> map) {
        synchronized (SleepDataDbUtil.class) {
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            int b = DateFormatUtil.b(System.currentTimeMillis());
            hiAggregateOption.setTimeInterval(String.valueOf(b), String.valueOf(b), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
            hiAggregateOption.setType(new int[]{44006, 44007, 44004, 44001, 44002, 44003, 44005, 44201, 44202, 44105, 44102, 44103, 44104, 44107});
            hiAggregateOption.setConstantsKey(new String[]{"sleep_start_time", "sleep_end_time", "sleep_duration_sum", "sleep_deep_duration", "sleep_shallow_duration", "sleep_wake_duration", "sleep_wake_count", "sleep_start_time_core", "sleep_end_time_core", "sleep_duration_sum_core", "sleep_deep_duration_core", "sleep_shallow_duration_core", "sleep_wake_duration_core", "sleep_wake_count_core"});
            LogUtil.c("Step_SleepDataDbUtil", "getSleepDataDetail...");
            hiAggregateOption.setAggregateType(1);
            hiAggregateOption.setGroupUnitType(3);
            if (context == null) {
                LogUtil.a("Step_SleepDataDbUtil", "getSleepDataDetail mContext is null");
                return;
            }
            if (map == null) {
                LogUtil.a("Step_SleepDataDbUtil", "getSleepDataDetail sleepDataMap is null");
                return;
            }
            HiHealthNativeApi.a(context).aggregateHiHealthData(hiAggregateOption, new ReadSleepStaticDealCallback(map));
            Object obj = c;
            synchronized (obj) {
                try {
                    obj.wait(3000L);
                } catch (InterruptedException e) {
                    LogUtil.a("Step_SleepDataDbUtil", "getSleepDataDetail InterruptedException", e.getMessage());
                }
            }
        }
    }

    static class ReadSleepStaticDealCallback implements HiAggregateListener {
        private Map<String, Object> b;

        protected ReadSleepStaticDealCallback(Map<String, Object> map) {
            this.b = map;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            if (koq.b(list)) {
                LogUtil.a("Step_SleepDataDbUtil", "ReadSleepStaticDealCallback onResult data is null");
                synchronized (SleepDataDbUtil.c) {
                    SleepDataDbUtil.c.notifyAll();
                }
                return;
            }
            try {
                b(list.get(0));
                synchronized (SleepDataDbUtil.c) {
                    SleepDataDbUtil.c.notifyAll();
                }
            } catch (NumberFormatException unused) {
                LogUtil.h("Step_SleepDataDbUtil", "ReadSleepStaticDealCallback NumberFormatException");
            }
        }

        private void b(HiHealthData hiHealthData) {
            long j;
            long j2;
            int i;
            int i2;
            int i3;
            int i4;
            String str;
            int i5;
            String str2;
            if (hiHealthData == null) {
                LogUtil.h("Step_SleepDataDbUtil", "putHiHealthData data is null.");
                return;
            }
            long j3 = hiHealthData.getLong("sleep_start_time_core");
            long j4 = hiHealthData.getLong("sleep_end_time_core");
            int i6 = hiHealthData.getInt("sleep_duration_sum_core");
            int i7 = hiHealthData.getInt("sleep_deep_duration_core");
            int i8 = hiHealthData.getInt("sleep_shallow_duration_core");
            int i9 = hiHealthData.getInt("sleep_wake_duration_core");
            int i10 = hiHealthData.getInt("sleep_wake_count_core");
            if (i6 <= 0) {
                LogUtil.h("Step_SleepDataDbUtil", "core sleep data validate false! ");
                j = hiHealthData.getLong("sleep_start_time");
                j2 = hiHealthData.getLong("sleep_end_time");
                i = hiHealthData.getInt("sleep_duration_sum") / 60;
                i2 = hiHealthData.getInt("sleep_deep_duration") / 60;
                i3 = hiHealthData.getInt("sleep_shallow_duration") / 60;
                i4 = hiHealthData.getInt("sleep_wake_duration") / 60;
                str = "sleep_wake_count";
                i5 = hiHealthData.getInt("sleep_wake_count");
                str2 = "";
            } else {
                str2 = "_core";
                i = i6;
                j = j3;
                i3 = i8;
                i4 = i9;
                i2 = i7;
                j2 = j4;
                i5 = i10;
                str = "sleep_wake_count";
            }
            LogUtil.a("Step_SleepDataDbUtil", "sleep data from suffix:", str2);
            this.b.put("sleep_start_time", DateFormatUtil.a(j, DateFormatUtil.DateFormatType.DATE_FORMAT_8));
            this.b.put("sleep_end_time", DateFormatUtil.a(j2, DateFormatUtil.DateFormatType.DATE_FORMAT_8));
            this.b.put("sleep_duration_sum", Integer.valueOf(i));
            this.b.put("sleep_deep_duration", Integer.valueOf(i2));
            this.b.put("sleep_shallow_duration", Integer.valueOf(i3));
            this.b.put("sleep_wake_duration", Integer.valueOf(i4));
            this.b.put(str, Integer.valueOf(i5));
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            Object[] objArr = new Object[5];
            objArr[0] = "ReadSleepStaticDealCallback onResultIntent ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = list != null ? Integer.valueOf(list.size()) : "dataList is null.";
            objArr[3] = Integer.valueOf(i2);
            objArr[4] = Integer.valueOf(i3);
            LogUtil.a("Step_SleepDataDbUtil", objArr);
            synchronized (SleepDataDbUtil.c) {
                SleepDataDbUtil.c.notifyAll();
            }
        }
    }
}
