package com.huawei.ui.main.stories.history.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gts;
import defpackage.hkc;
import defpackage.hln;
import defpackage.koq;
import defpackage.kwy;
import defpackage.rdl;
import defpackage.rdp;
import defpackage.rdq;
import health.compact.a.CommonUtils;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class SportRecordStatData {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private long f10317a;
    private Context b;
    private SportRecordDataAdapter c;
    private rdl f;
    private int i;
    private long m;
    private int j = 0;
    private Map<Pair<Long, Long>, List<rdq>> g = new HashMap();
    private Map<rdp, SportRecordDataAdapter> h = new HashMap();
    private Map<rdp, List<HiHealthData>> k = new HashMap();
    private Handler d = new Handler() { // from class: com.huawei.ui.main.stories.history.model.SportRecordStatData.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("Track_SportRecordStatData", "msg is null");
                return;
            }
            int i = message.what;
            if (i == 101) {
                SportRecordStatData.this.a();
            } else {
                if (i != 102) {
                    return;
                }
                SportRecordStatData.this.c(message.obj);
            }
        }
    };

    public SportRecordStatData(Context context) {
        this.b = BaseApplication.getContext();
        this.b = context;
    }

    private void d(rdp rdpVar, SportRecordDataAdapter sportRecordDataAdapter) {
        this.h.put(rdpVar, sportRecordDataAdapter);
    }

    public void d(long j, long j2, int i, int i2, SportRecordDataAdapter sportRecordDataAdapter) {
        LogUtil.a("Track_SportRecordStatData", "sportType = ", Integer.valueOf(i), ", timeUnit = ", Integer.valueOf(i2));
        synchronized (e) {
            int i3 = this.j + 1;
            this.j = i3;
            this.i = i;
            this.m = j;
            this.f10317a = j2;
            if (i3 >= Integer.MAX_VALUE) {
                this.j = 0;
            }
        }
        this.c = sportRecordDataAdapter;
        a();
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("Track_SportRecordStatData", "getData recordApi is null.");
            return;
        }
        kwy d = new kwy.a().a(j).e(j2).d(this.i).c(i2).d();
        int i4 = this.i;
        if (i4 == 0) {
            rdl rdlVar = new rdl(this.d, 2);
            this.f = rdlVar;
            c(j, j2, i2, rdlVar);
            recordApi.acquireSummaryFitnessRecord(d, this.f);
            return;
        }
        if (i4 == 10001) {
            rdl rdlVar2 = new rdl(this.d, 1);
            this.f = rdlVar2;
            recordApi.acquireSummaryFitnessRecord(d, rdlVar2);
        } else {
            rdl rdlVar3 = new rdl(this.d, 1);
            this.f = rdlVar3;
            c(j, j2, i2, rdlVar3);
        }
    }

    private void c(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setTimeInterval(j, j2);
        if (i == 4) {
            i = 7;
        }
        hiSportStatDataAggregateOption.setGroupUnitType(i);
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setReadType(0);
        hln c = hln.c(this.b);
        if (c.d(this.i) == null || c.d(this.i).getSportDataStatics() == null) {
            LogUtil.b("Track_SportRecordStatData", "can not find sport type in json");
            return;
        }
        int[] c2 = c(c);
        String[] b = b(c);
        hiSportStatDataAggregateOption.setType(c2);
        hiSportStatDataAggregateOption.setConstantsKey(b);
        int i2 = this.i;
        if (i2 != 0) {
            hiSportStatDataAggregateOption.setHiHealthTypes(gts.a(i2));
        }
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateSportStatData(hiSportStatDataAggregateOption, new e(iBaseResponseCallback));
    }

    private int[] c(hln hlnVar) {
        int[] c = hkc.c(this.b, this.i);
        int[] a2 = hlnVar.a(hlnVar.d(this.i).getSportDataStatics());
        int length = c.length + a2.length;
        int[] iArr = new int[length];
        System.arraycopy(c, 0, iArr, 0, c.length);
        System.arraycopy(a2, 0, iArr, c.length, a2.length);
        if (this.i == 0) {
            int[] b = gts.b(this.b).b();
            int[] iArr2 = new int[b.length + length];
            System.arraycopy(iArr, 0, iArr2, 0, length);
            System.arraycopy(b, 0, iArr2, length, b.length);
            iArr = iArr2;
        }
        if (this.i != 286) {
            return iArr;
        }
        int[] iArr3 = new int[iArr.length + 4];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(new int[]{42405, 42406, 42404, 42403}, 0, iArr3, iArr.length, 4);
        return iArr3;
    }

    private String[] b(hln hlnVar) {
        String[] a2 = hkc.a(this.b, this.i);
        String[] b = hlnVar.b(hlnVar.d(this.i).getSportDataStatics());
        int length = a2.length + b.length;
        String[] strArr = new String[length];
        System.arraycopy(a2, 0, strArr, 0, a2.length);
        System.arraycopy(b, 0, strArr, a2.length, b.length);
        if (this.i == 0) {
            String[] d = gts.b(this.b).d();
            String[] strArr2 = new String[d.length + length];
            System.arraycopy(strArr, 0, strArr2, 0, length);
            System.arraycopy(d, 0, strArr2, length, d.length);
            strArr = strArr2;
        }
        if (this.i != 286) {
            return strArr;
        }
        String[] b2 = hlnVar.b(hlnVar.d(HeartRateThresholdConfig.HEART_RATE_LIMIT).getSportDataStatics());
        String[] strArr3 = new String[strArr.length + b2.length];
        System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        System.arraycopy(b2, 0, strArr3, strArr.length, b2.length);
        return strArr3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.k == null || this.h == null) {
            LogUtil.h("Track_SportRecordStatData", "mTrackRecords or is mListenerList null in updateUi");
            return;
        }
        rdp c = new rdp.e().c(this.m).d(this.f10317a).a(this.i).c();
        SportRecordDataAdapter sportRecordDataAdapter = this.h.get(c);
        if (sportRecordDataAdapter != null) {
            sportRecordDataAdapter.updateUi(this.k.get(c));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        if (!(obj instanceof List)) {
            LogUtil.h("Track_SportRecordStatData", "objData wrong type");
            return;
        }
        rdp c = new rdp.e().c(this.m).d(this.f10317a).a(this.i).c();
        SportRecordDataAdapter sportRecordDataAdapter = this.c;
        if (sportRecordDataAdapter != null) {
            d(c, sportRecordDataAdapter);
        }
        List<Object> list = (List) obj;
        Iterator<Object> it = list.iterator();
        int i = 0;
        while (it.hasNext() && it.next() == null) {
            i++;
        }
        if (i == list.size()) {
            LogUtil.a("Track_SportRecordStatData", "send  is empty");
            this.d.sendEmptyMessage(101);
            return;
        }
        int i2 = this.i;
        if (i2 == 0) {
            c(list);
        } else if (i2 == 10001) {
            b(list);
        } else {
            d(list);
        }
        this.d.sendEmptyMessage(101);
    }

    private void c(List<Object> list) {
        for (Object obj : list) {
            if (koq.e(obj, HiHealthData.class)) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    e((HiHealthData) it.next());
                }
            } else if (koq.e(obj, FitnessTrackRecord.class)) {
                Iterator it2 = ((List) obj).iterator();
                while (it2.hasNext()) {
                    d((FitnessTrackRecord) it2.next());
                }
            } else {
                LogUtil.h("Track_SportRecordStatData", "wrong data type = ", obj.getClass());
            }
        }
    }

    private void e(HiHealthData hiHealthData) {
        int i = hiHealthData.getInt("hihealth_type");
        if (i == 30001) {
            String[] d = gts.b(this.b).d();
            for (int i2 = 0; i2 < 8; i2++) {
                int h = CommonUtils.h(d[i2].substring(6, 9));
                long startTime = hiHealthData.getStartTime();
                double d2 = hiHealthData.getDouble("Track_" + h + 2);
                double d3 = hiHealthData.getDouble("Track_" + h + 3);
                this.g.get(Pair.create(Long.valueOf(this.m), Long.valueOf(this.f10317a))).add(new rdq(h, startTime, hiHealthData.getDouble("Track_" + h + 4), hiHealthData.getDouble("Track_" + h + 5), d3, d2, hiHealthData.getDouble("Track_" + h + "abnormalTrack")));
            }
            return;
        }
        this.g.get(Pair.create(Long.valueOf(this.m), Long.valueOf(this.f10317a))).add(new rdq(i, hiHealthData.getStartTime(), hiHealthData.getDouble("stat4"), hiHealthData.getDouble("stat5"), hiHealthData.getDouble("stat3"), hiHealthData.getDouble("stat2"), hiHealthData.getDouble("stat6")));
    }

    private void d(FitnessTrackRecord fitnessTrackRecord) {
        this.g.get(Pair.create(Long.valueOf(this.m), Long.valueOf(this.f10317a))).add(new rdq(10001, fitnessTrackRecord.acquireMonthZeroTime(), fitnessTrackRecord.acquireSumExerciseTime(), fitnessTrackRecord.acquireSumExerciseTimes(), fitnessTrackRecord.acquireSumCalorie(), 0.0d, 0.0d));
    }

    private void d(List<Object> list) {
        if (koq.e(list.get(0), HiHealthData.class)) {
            List<HiHealthData> list2 = (List) list.get(0);
            if (koq.b(list2)) {
                LogUtil.h("Track_SportRecordStatData", "trackData records is empty");
            } else {
                this.k.put(new rdp.e().c(this.m).d(this.f10317a).a(this.i).c(), list2);
            }
        }
    }

    private void b(List<Object> list) {
        if (koq.e(list.get(0), FitnessTrackRecord.class)) {
            List<FitnessTrackRecord> list2 = (List) list.get(0);
            if (koq.b(list2)) {
                LogUtil.h("Track_SportRecordStatData", "Fitness records is empty");
                return;
            }
            for (FitnessTrackRecord fitnessTrackRecord : list2) {
                if (fitnessTrackRecord.acquireSumExerciseTime() > 0) {
                    HiHealthData hiHealthData = new HiHealthData();
                    hiHealthData.putFloat("Track_Fitness_Duration_Sum", fitnessTrackRecord.acquireSumExerciseTime());
                    hiHealthData.putInt("Track_Fitness_Count_Sum", fitnessTrackRecord.acquireSumExerciseTimes());
                    hiHealthData.putFloat("Track_Fitness_Calorie_Sum", fitnessTrackRecord.acquireSumCalorie());
                    this.k.get(new rdp.e().c(this.m).d(this.f10317a).a(this.i).c()).add(hiHealthData);
                }
            }
        }
    }

    static class e implements HiAggregateListener {
        private WeakReference<IBaseResponseCallback> d;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private e(IBaseResponseCallback iBaseResponseCallback) {
            this.d = new WeakReference<>(iBaseResponseCallback);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeakReference<IBaseResponseCallback> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("Track_SportRecordStatData", "mCallback == null");
                return;
            }
            IBaseResponseCallback iBaseResponseCallback = weakReference.get();
            if (iBaseResponseCallback == null) {
                LogUtil.h("Track_SportRecordStatData", "mCallback.get() == null");
            } else {
                iBaseResponseCallback.d(i, list);
            }
        }
    }
}
