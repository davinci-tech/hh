package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class pxf implements IChartStorageHelper {
    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        a(j, j2, d(dataInfos), responseCallback);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(long r24, long r26, int r28, com.huawei.ui.commonui.linechart.utils.ResponseCallback<java.util.Map<java.lang.Long, com.huawei.ui.commonui.linechart.icommon.IStorageModel>> r29) {
        /*
            r23 = this;
            r0 = r28
            r1 = r29
            java.lang.String r2 = "startTime: "
            java.lang.Long r3 = java.lang.Long.valueOf(r24)
            java.lang.String r4 = " endtime: "
            java.lang.Long r5 = java.lang.Long.valueOf(r26)
            java.lang.String r6 = " dataType: "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r28)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3, r4, r5, r6, r7}
            java.lang.String r3 = "UIHLH_SleepModuleChartStorageHelper"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            r2 = 0
            r3 = 1
            if (r0 != r3) goto L26
            r0 = 4
        L24:
            r5 = r3
            goto L32
        L26:
            r4 = 2
            r5 = 3
            if (r0 != r4) goto L2c
            r0 = r5
            goto L24
        L2c:
            if (r0 != r5) goto L30
            r0 = 5
            goto L32
        L30:
            r0 = r2
            goto L24
        L32:
            if (r0 != 0) goto L3a
            r0 = -1
            r2 = 0
            r1.onResult(r0, r2)
            return
        L3a:
            r4 = 17
            int[] r4 = new int[r4]
            r4 = {x008e: FILL_ARRAY_DATA , data: [44102, 44103, 44101, 44107, 44203, 44201, 44202, 44208, 44106, 44105, 44108, 44001, 44002, 44003, 44104, 44005, 44206} // fill-array
            java.lang.String r6 = "core_sleep_deep_key"
            java.lang.String r7 = "core_sleep_light_key"
            java.lang.String r8 = "core_sleep_wake_dream_key"
            java.lang.String r9 = "core_sleep_wake_count_key"
            java.lang.String r10 = "core_sleep_score_key"
            java.lang.String r11 = "core_sleep_fall_key"
            java.lang.String r12 = "core_sleep_wake_up_key"
            java.lang.String r13 = "core_sleep_snore_freq_key"
            java.lang.String r14 = "core_sleep_deep_sleep_part_key"
            java.lang.String r15 = "core_sleep_total_sleep_time_key"
            java.lang.String r16 = "core_sleep_day_sleep_time_key"
            java.lang.String r17 = "sleep_deep_key"
            java.lang.String r18 = "sleep_light_key"
            java.lang.String r19 = "sleep_wake_key"
            java.lang.String r20 = "core_sleep_wake_key"
            java.lang.String r21 = "sleep_wake_count_key"
            java.lang.String r22 = "core_sleep_valid_data_key"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22}
            com.huawei.hihealth.HiAggregateOption r7 = new com.huawei.hihealth.HiAggregateOption
            r7.<init>()
            r8 = r24
            r7.setStartTime(r8)
            r8 = r26
            r7.setEndTime(r8)
            r7.setAggregateType(r5)
            r7.setConstantsKey(r6)
            r7.setType(r4)
            r7.setGroupUnitSize(r3)
            r7.setGroupUnitType(r0)
            r7.setReadType(r2)
            r0 = r23
            r0.e(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pxf.a(long, long, int, com.huawei.ui.commonui.linechart.utils.ResponseCallback):void");
    }

    private void e(ResponseCallback<Map<Long, IStorageModel>> responseCallback, HiAggregateOption hiAggregateOption) {
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new b(responseCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HashMap hashMap = new HashMap(16);
        LogUtil.a("UIHLH_SleepModuleChartStorageHelper", "sumDataList.size = ", Integer.valueOf(list.size()));
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HiHealthData next = it.next();
            long startTime = next.getStartTime();
            int round = Math.round(next.getFloat("core_sleep_deep_key"));
            int round2 = Math.round(next.getFloat("core_sleep_light_key"));
            int round3 = Math.round(next.getFloat("core_sleep_wake_dream_key"));
            int round4 = Math.round(next.getFloat("core_sleep_wake_key"));
            int round5 = Math.round(next.getFloat("core_sleep_day_sleep_time_key"));
            qah qahVar = new qah();
            boolean z = round > 0 || round2 > 0 || round3 > 0;
            boolean z2 = round4 > 0 || round5 > 0;
            if (z || z2) {
                qahVar.d(round);
                qahVar.b(round2);
                qahVar.a(round3);
                qahVar.e(round4);
                qahVar.c(round5);
                qahVar.e(31);
                hashMap.put(Long.valueOf(startTime), new qai(round + round2 + round3 + round4 + round5, qahVar));
            } else {
                int i = next.getInt("sleep_deep_key") / 60;
                int i2 = next.getInt("sleep_light_key") / 60;
                int i3 = next.getInt("sleep_wake_key") / 60;
                if (i > 0 || i2 > 0 || i3 > 0) {
                    qahVar.d(i);
                    qahVar.b(i2);
                    qahVar.e(i3);
                    qahVar.e(30);
                    hashMap.put(Long.valueOf(startTime), new qai(i + i2 + i3, qahVar));
                }
            }
        }
        if (responseCallback != null) {
            if (hashMap.size() > 0) {
                responseCallback.onResult(0, hashMap);
            } else {
                responseCallback.onResult(-1, null);
            }
        }
    }

    private int d(DataInfos dataInfos) {
        if (dataInfos.isCoreSleepData()) {
            if (dataInfos.isWeekData()) {
                return 1;
            }
            if (dataInfos.isMonthData()) {
                return 2;
            }
            if (dataInfos.isYearData()) {
                return 3;
            }
            LogUtil.a("UIHLH_SleepModuleChartStorageHelper", "Sleep type does not exist");
        }
        return 40002;
    }

    static class b implements HiAggregateListener {
        private WeakReference<pxf> b;
        private ResponseCallback<Map<Long, IStorageModel>> e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private b(pxf pxfVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.b = new WeakReference<>(pxfVar);
            this.e = responseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            pxf pxfVar = this.b.get();
            if (pxfVar == null) {
                LogUtil.h("UIHLH_SleepModuleChartStorageHelper", "DataReadListener storageHelper is null");
                return;
            }
            if (this.e == null) {
                LogUtil.h("UIHLH_SleepModuleChartStorageHelper", "DataReadListener mCallback is null");
            } else if (!koq.b(list)) {
                pxfVar.a(new ArrayList(list), this.e);
                this.e = null;
            } else {
                this.e.onResult(-1, null);
            }
        }
    }
}
