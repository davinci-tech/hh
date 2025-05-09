package defpackage;

import android.content.Context;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
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

/* loaded from: classes8.dex */
public class eee implements IChartStorageHelper {

    /* renamed from: a, reason: collision with root package name */
    private long f11981a;
    private long b;
    private HwHealthChartHolder.b c;
    private Context d;
    private ResponseCallback<Map<Long, IStorageModel>> e;
    private DataInfos h;

    public eee() {
        ObserverManagerUtil.d(new Observer() { // from class: eee.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (eee.this.e == null) {
                    return;
                }
                eee eeeVar = eee.this;
                int b = eeeVar.b(eeeVar.h);
                eee eeeVar2 = eee.this;
                eeeVar2.b(eeeVar2.b, eee.this.f11981a, b, eee.this.e);
            }
        }, "FINISH_SLEEP_TAG");
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        this.d = context;
        this.b = j;
        this.f11981a = j2;
        this.h = dataInfos;
        this.c = bVar;
        this.e = responseCallback;
        b(j, j2, b(dataInfos), responseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(long r26, long r28, int r30, com.huawei.ui.commonui.linechart.utils.ResponseCallback<java.util.Map<java.lang.Long, com.huawei.ui.commonui.linechart.icommon.IStorageModel>> r31) {
        /*
            r25 = this;
            r0 = r30
            r1 = r31
            java.lang.String r2 = "startTime: "
            java.lang.Long r3 = java.lang.Long.valueOf(r26)
            java.lang.String r4 = " endtime: "
            java.lang.Long r5 = java.lang.Long.valueOf(r28)
            java.lang.String r6 = " dataType: "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r30)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3, r4, r5, r6, r7}
            java.lang.String r3 = "UIHLH_SleepModuleChartStorageHelper"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            r2 = 0
            r3 = 1
            if (r0 != r3) goto L26
            r4 = 4
        L24:
            r5 = r3
            goto L32
        L26:
            r4 = 2
            r5 = 3
            if (r0 != r4) goto L2c
            r4 = r5
            goto L24
        L2c:
            if (r0 != r5) goto L30
            r4 = 5
            goto L32
        L30:
            r4 = r2
            goto L24
        L32:
            if (r4 != 0) goto L3a
            r0 = -1
            r2 = 0
            r1.onResult(r0, r2)
            return
        L3a:
            r6 = 18
            int[] r6 = new int[r6]
            r6 = {x0090: FILL_ARRAY_DATA , data: [44102, 44103, 44101, 44107, 44203, 44201, 44202, 44208, 44106, 44105, 44108, 44001, 44002, 44003, 44104, 44005, 44206, 44109} // fill-array
            java.lang.String r7 = "core_sleep_deep_key"
            java.lang.String r8 = "core_sleep_light_key"
            java.lang.String r9 = "core_sleep_wake_dream_key"
            java.lang.String r10 = "core_sleep_wake_count_key"
            java.lang.String r11 = "core_sleep_score_key"
            java.lang.String r12 = "core_sleep_fall_key"
            java.lang.String r13 = "core_sleep_wake_up_key"
            java.lang.String r14 = "core_sleep_snore_freq_key"
            java.lang.String r15 = "core_sleep_deep_sleep_part_key"
            java.lang.String r16 = "core_sleep_total_sleep_time_key"
            java.lang.String r17 = "core_sleep_day_sleep_time_key"
            java.lang.String r18 = "sleep_deep_key"
            java.lang.String r19 = "sleep_light_key"
            java.lang.String r20 = "sleep_wake_key"
            java.lang.String r21 = "core_sleep_wake_key"
            java.lang.String r22 = "sleep_wake_count_key"
            java.lang.String r23 = "core_sleep_valid_data_key"
            java.lang.String r24 = "data_session_manual_sleep_bed_time_key"
            java.lang.String[] r7 = new java.lang.String[]{r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24}
            com.huawei.hihealth.HiAggregateOption r8 = new com.huawei.hihealth.HiAggregateOption
            r8.<init>()
            r9 = r26
            r8.setStartTime(r9)
            r9 = r28
            r8.setEndTime(r9)
            r8.setAggregateType(r5)
            r8.setConstantsKey(r7)
            r8.setType(r6)
            r8.setGroupUnitSize(r3)
            r8.setGroupUnitType(r4)
            r8.setReadType(r2)
            r2 = r25
            r2.c(r1, r8, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eee.b(long, long, int, com.huawei.ui.commonui.linechart.utils.ResponseCallback):void");
    }

    private void c(ResponseCallback<Map<Long, IStorageModel>> responseCallback, HiAggregateOption hiAggregateOption, int i) {
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new e(responseCallback, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback, int i) {
        ResponseCallback<Map<Long, IStorageModel>> responseCallback2 = responseCallback;
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
            int round6 = Math.round(next.getFloat("core_sleep_total_sleep_time_key"));
            int round7 = Math.round(next.getFloat("data_session_manual_sleep_bed_time_key"));
            ede edeVar = new ede();
            boolean z = round > 0 || round2 > 0 || round3 > 0;
            boolean z2 = round5 > 0;
            boolean z3 = round7 > 0;
            int i2 = next.getInt("sleep_deep_key") / 60;
            Iterator<HiHealthData> it2 = it;
            int i3 = next.getInt("sleep_light_key") / 60;
            int i4 = next.getInt("sleep_wake_key") / 60;
            if (z) {
                edeVar.e(round);
                edeVar.d(round2);
                edeVar.b(round3);
                edeVar.h(round4);
                edeVar.a(round5);
                if (i == 3) {
                    edeVar.c(round7);
                }
                edeVar.e(31);
                int i5 = round + round2 + round3 + round4;
                float f = round5 + i5;
                if (i5 == 0 && round7 != 0) {
                    f = round7;
                }
                edeVar.f(round6);
                hashMap.put(Long.valueOf(startTime), new eeg(f, edeVar));
            } else if (i2 > 0 || i3 > 0 || i4 > 0) {
                edeVar.e(i2);
                edeVar.d(i3);
                edeVar.h(i4);
                edeVar.e(30);
                hashMap.put(Long.valueOf(startTime), new eeg(i2 + i3 + i4, edeVar));
            } else if (z3) {
                edeVar.e(round);
                edeVar.d(round2);
                edeVar.b(round3);
                edeVar.h(round4);
                edeVar.a(round5);
                float f2 = round7;
                edeVar.c(f2);
                edeVar.e(31);
                int i6 = round + round2 + round3 + round4;
                float f3 = round5 + i6;
                if (i6 != 0 || round7 == 0) {
                    f2 = f3;
                }
                edeVar.f(round6);
                hashMap.put(Long.valueOf(startTime), new eeg(f2, edeVar));
            } else if (z2) {
                float f4 = round5;
                edeVar.a(f4);
                edeVar.e(31);
                edeVar.f(f4);
                hashMap.put(Long.valueOf(startTime), new eeg(f4, edeVar));
            }
            it = it2;
            responseCallback2 = responseCallback;
        }
        if (responseCallback2 != null) {
            if (hashMap.size() > 0) {
                responseCallback2.onResult(0, hashMap);
            } else {
                responseCallback2.onResult(-1, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(DataInfos dataInfos) {
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

    static class e implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<eee> f11983a;
        private ResponseCallback<Map<Long, IStorageModel>> c;
        private int e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private e(eee eeeVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback, int i) {
            this.f11983a = new WeakReference<>(eeeVar);
            this.c = responseCallback;
            this.e = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            eee eeeVar = this.f11983a.get();
            if (eeeVar == null) {
                LogUtil.h("UIHLH_SleepModuleChartStorageHelper", "DataReadListener storageHelper is null");
                return;
            }
            if (this.c == null) {
                LogUtil.h("UIHLH_SleepModuleChartStorageHelper", "DataReadListener mCallback is null");
            } else if (!koq.b(list)) {
                eeeVar.e(new ArrayList(list), this.c, this.e);
                this.c = null;
            } else {
                this.c.onResult(-1, null);
            }
        }
    }
}
