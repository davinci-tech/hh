package defpackage;

import android.text.SpannableString;
import android.util.SparseArray;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes9.dex */
public class pjh {
    public static void dqd_(SparseArray<Object> sparseArray, pkr pkrVar, boolean z) {
        if (sparseArray.size() <= 0) {
            LogUtil.a("BloodOxygenDataUtil", "map.size is 0");
            return;
        }
        List<HiHealthData> arrayList = new ArrayList<>(16);
        Object obj = sparseArray.get(2103);
        if (obj instanceof List) {
            arrayList = (List) obj;
            LogUtil.a("BloodOxygenDataUtil", "oxygenMeasureValueList.size :", Integer.valueOf(arrayList.size()));
        }
        if (koq.c(arrayList)) {
            HiHealthData hiHealthData = arrayList.get(0);
            int intValue = hiHealthData.getIntValue();
            String e = scg.e(BaseApplication.getContext(), hiHealthData.getLong("start_time"));
            String e2 = UnitUtil.e(intValue, 2, 0);
            pkrVar.b(e);
            pkrVar.d(e2);
        }
        List<HiHealthData> arrayList2 = new ArrayList<>(16);
        Object obj2 = sparseArray.get(2107);
        if (obj2 instanceof List) {
            arrayList2 = (List) obj2;
            LogUtil.a("BloodOxygenDataUtil", "dealDataMap oxygenRemindValueList: ", arrayList2.toString());
        }
        List<HiHealthData> arrayList3 = new ArrayList(16);
        Object obj3 = sparseArray.get(DicDataTypeUtil.DataType.ALTITUDE_TYPE.value());
        if (obj3 instanceof List) {
            arrayList3 = (List) obj3;
            for (HiHealthData hiHealthData2 : arrayList3) {
                hiHealthData2.putInt("altitudeKey", hiHealthData2.getIntValue());
            }
            LogUtil.a("BloodOxygenDataUtil", "altitudeValueList.size :", Integer.valueOf(arrayList3.size()));
        }
        List<HiHealthData> arrayList4 = new ArrayList<>(16);
        Object obj4 = sparseArray.get(DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value());
        if (obj4 instanceof List) {
            arrayList4 = (List) obj4;
            LogUtil.a("BloodOxygenDataUtil", "lakeLouiseValueList.size :", Integer.valueOf(arrayList4.size()));
        }
        final List<HiHealthData>[] listArr = {new ArrayList(16)};
        final List[] listArr2 = {new ArrayList(16)};
        final List[] listArr3 = {new ArrayList(16)};
        if (z) {
            rlo rloVar = new rlo();
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            rloVar.a(0, arrayList4, new DataSourceCallback() { // from class: pjf
                @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                public final void onResponse(int i, Object obj5) {
                    pjh.e(listArr, countDownLatch, i, (List) obj5);
                }
            });
            a(countDownLatch);
            final CountDownLatch countDownLatch2 = new CountDownLatch(1);
            rloVar.a(1, listArr[0], new DataSourceCallback() { // from class: pjj
                @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                public final void onResponse(int i, Object obj5) {
                    pjh.c(listArr2, countDownLatch2, i, (List) obj5);
                }
            });
            a(countDownLatch2);
            final CountDownLatch countDownLatch3 = new CountDownLatch(1);
            rloVar.a(1, arrayList2, new DataSourceCallback() { // from class: pji
                @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                public final void onResponse(int i, Object obj5) {
                    pjh.a(listArr3, countDownLatch3, i, (List) obj5);
                }
            });
            a(countDownLatch3);
        } else {
            listArr[0] = arrayList;
            listArr2[0] = arrayList3;
            listArr3[0] = arrayList3;
        }
        List<HiHealthData> d = scg.d(arrayList4, listArr[0], listArr2[0], true);
        scg.b(arrayList2, listArr3[0]);
        a(d, arrayList2, pkrVar);
    }

    static /* synthetic */ void e(List[] listArr, CountDownLatch countDownLatch, int i, List list) {
        if (i == 0) {
            listArr[0] = list;
        }
        countDownLatch.countDown();
    }

    static /* synthetic */ void c(List[] listArr, CountDownLatch countDownLatch, int i, List list) {
        if (i == 0) {
            listArr[0] = list;
        }
        countDownLatch.countDown();
    }

    static /* synthetic */ void a(List[] listArr, CountDownLatch countDownLatch, int i, List list) {
        if (i == 0) {
            listArr[0] = list;
        }
        countDownLatch.countDown();
    }

    private static void a(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.b("BloodOxygenDataUtil", "countDownLatch exception");
        }
    }

    public static void a(List<HiHealthData> list, List<HiHealthData> list2, pkr pkrVar) {
        List<HiHealthData> arrayList = new ArrayList<>();
        arrayList.addAll(list);
        arrayList.addAll(list2);
        Collections.sort(arrayList, new Comparator<HiHealthData>() { // from class: pjh.4
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                return hiHealthData.getLong("start_time") - hiHealthData2.getLong("start_time") > 0 ? -1 : 1;
            }
        });
        new ArrayList();
        if (arrayList.size() > 5) {
            arrayList = arrayList.subList(0, 5);
        }
        for (HiHealthData hiHealthData : arrayList) {
            if (hiHealthData.getIntValue() == 0) {
                hiHealthData.putInt("point_value", hiHealthData.getInt("bloodOxygenKey"));
            }
        }
        pkrVar.a(arrayList);
    }

    public static void a(long j, long j2, pkr pkrVar, DataInfos dataInfos, IBaseResponseCallback iBaseResponseCallback) {
        rkb rkbVar = new rkb();
        rkbVar.a(new int[]{47201, 47202});
        rkbVar.c(new String[]{"maxBloodOxygen", "minBloodOxygen"});
        rkbVar.c(new int[]{4, 5});
        LogUtil.a("BloodOxygenDataUtil", "getMeasureList start");
        a(rkbVar, j, j2, pkrVar, dataInfos, iBaseResponseCallback);
    }

    public static void a(rkb rkbVar, long j, long j2, pkr pkrVar, DataInfos dataInfos, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(10);
        long b = gib.b(j);
        LogUtil.a("BloodOxygenDataUtil", "getMeasureList starttime is ", Long.valueOf(b), "endtime is ", Long.valueOf(j2));
        for (int i : rkbVar.g()) {
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            hiAggregateOption.setReadType(0);
            hiAggregateOption.setStartTime(b);
            hiAggregateOption.setEndTime(j2);
            hiAggregateOption.setGroupUnitType(3);
            hiAggregateOption.setAggregateType(i);
            hiAggregateOption.setConstantsKey(rkbVar.d());
            hiAggregateOption.setType(rkbVar.l());
            hiAggregateOption.setSortOrder(1);
            arrayList.add(hiAggregateOption);
        }
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthDataEx(arrayList, new AnonymousClass3(iBaseResponseCallback, pkrVar, b, dataInfos));
    }

    /* renamed from: pjh$3, reason: invalid class name */
    class AnonymousClass3 implements HiAggregateListenerEx {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ IBaseResponseCallback f16157a;
        final /* synthetic */ long c;
        final /* synthetic */ pkr d;
        final /* synthetic */ DataInfos e;

        AnonymousClass3(IBaseResponseCallback iBaseResponseCallback, pkr pkrVar, long j, DataInfos dataInfos) {
            this.f16157a = iBaseResponseCallback;
            this.d = pkrVar;
            this.c = j;
            this.e = dataInfos;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            if (i != 0 || sparseArray.get(0) == null) {
                LogUtil.b("BloodOxygenDataUtil", "getMeasureList fail");
                this.f16157a.d(0, true);
                return;
            }
            List<HiHealthData> list = sparseArray.get(0);
            int i3 = -1;
            int i4 = 101;
            for (HiHealthData hiHealthData : list) {
                LogUtil.a("BloodOxygenDataUtil", "getMeasureList measureData IS ", hiHealthData.toString());
                hiHealthData.putInt("bloodOxygenCardKey", 1003);
                int i5 = hiHealthData.getInt("maxBloodOxygen");
                int i6 = hiHealthData.getInt("minBloodOxygen");
                if (i5 > i3) {
                    i3 = i5;
                }
                if (i6 < i4) {
                    i4 = i6;
                }
            }
            if (koq.c(list)) {
                String e = UnitUtil.e(i3, 1, 0);
                String e2 = UnitUtil.e(i4, 1, 0);
                SpannableString bCR_ = UnitUtil.bCR_(BaseApplication.getContext(), "[\\d]", UnitUtil.e(CommonUtil.m(BaseApplication.getActivity(), e), 2, 0), R.style.health_text_chart_extreme_value, R.style.health_text_chart_extreme_value_percent);
                this.d.e(((Object) UnitUtil.bCR_(BaseApplication.getContext(), "[\\d]", UnitUtil.e(CommonUtil.m(BaseApplication.getActivity(), e2), 2, 0), R.style.health_text_chart_extreme_value, R.style.health_text_chart_extreme_value_percent)) + " ~ " + ((Object) bCR_));
            }
            ThreadPoolManager d = ThreadPoolManager.d();
            final long j = this.c;
            final pkr pkrVar = this.d;
            final DataInfos dataInfos = this.e;
            final IBaseResponseCallback iBaseResponseCallback = this.f16157a;
            d.execute(new Runnable() { // from class: pjn
                @Override // java.lang.Runnable
                public final void run() {
                    pjh.c(j, pkrVar, dataInfos, iBaseResponseCallback);
                }
            });
        }
    }

    public static void c(long j, pkr pkrVar, DataInfos dataInfos, IBaseResponseCallback iBaseResponseCallback) {
        long e = jdl.e(j);
        long t = jdl.t(j);
        if (dataInfos == DataInfos.BloodOxygenWeekDetail) {
            e = jdl.b(t, 7);
        } else if (dataInfos == DataInfos.BloodOxygenMonthDetail) {
            if (jdl.s(t) == t) {
                e = jdl.a(t);
            } else {
                e = jdl.b(t, 30);
            }
        } else if (dataInfos == DataInfos.BloodOxygenYearDetail) {
            e = jdl.b(t, 365);
        } else {
            LogUtil.a("BloodOxygenDataUtil", "DAY data type");
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(t);
        hiAggregateOption.setEndTime(e);
        hiAggregateOption.setConstantsKey(new String[]{"altitude"});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.ALTITUDE_TYPE.value()});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setSortOrder(1);
        HiAggregateOption hiAggregateOption2 = new HiAggregateOption();
        hiAggregateOption2.setStartTime(t);
        hiAggregateOption2.setEndTime(e);
        hiAggregateOption2.setConstantsKey(new String[]{"altitude"});
        hiAggregateOption2.setAggregateType(5);
        hiAggregateOption2.setType(new int[]{DicDataTypeUtil.DataType.ALTITUDE_TYPE.value()});
        hiAggregateOption2.setGroupUnitType(3);
        hiAggregateOption2.setSortOrder(1);
        b(hiAggregateOption, hiAggregateOption2, pkrVar, iBaseResponseCallback);
    }

    private static void b(HiAggregateOption hiAggregateOption, HiAggregateOption hiAggregateOption2, final pkr pkrVar, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c((String) null).c();
        HiDataAggregateProOption c2 = HiDataAggregateProOption.builder().c(hiAggregateOption2).c((String) null).c();
        ArrayList arrayList = new ArrayList();
        arrayList.add(c);
        arrayList.add(c2);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthDataProEx(arrayList, new HiAggregateListenerEx() { // from class: pjh.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                if (i == 1 || sparseArray == null || sparseArray.size() < 2) {
                    LogUtil.b("BloodOxygenDataUtil", "aggregateMaxAndMin data null,errorcode=", Integer.valueOf(i));
                    IBaseResponseCallback.this.d(0, true);
                    return;
                }
                LogUtil.a("BloodOxygenDataUtil", "aggregateMaxAndMin end");
                List<HiHealthData> e = pjh.e(sparseArray.get(0), sparseArray.get(1));
                if (koq.c(e)) {
                    int i3 = e.get(0).getInt("maxBloodOxygen");
                    int i4 = e.get(0).getInt("minBloodOxygen");
                    String e2 = rre.e(i3);
                    String e3 = rre.e(i4);
                    SpannableString bCR_ = UnitUtil.bCR_(BaseApplication.getContext(), "[\\d]", e2, R.style.health_text_chart_extreme_value, R.style.health_text_chart_extreme_value_percent);
                    String str = ((Object) UnitUtil.bCR_(BaseApplication.getContext(), "[\\d]", e3, R.style.health_text_chart_extreme_value, R.style.health_text_chart_extreme_value_percent)) + " ~ " + ((Object) bCR_);
                    pkrVar.c(str);
                    LogUtil.a("BloodOxygenDataUtil", "aggregateMaxAndMin text: ", str);
                    IBaseResponseCallback.this.d(0, true);
                }
            }
        });
    }

    public static List<HiHealthData> e(List<HiHealthData> list, List<HiHealthData> list2) {
        if (list.size() != list2.size()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthData> it = list.iterator();
        int i = Integer.MIN_VALUE;
        while (it.hasNext()) {
            int i2 = it.next().getInt("altitude");
            if (i2 > i) {
                i = i2;
            }
        }
        Iterator<HiHealthData> it2 = list2.iterator();
        int i3 = Integer.MAX_VALUE;
        while (it2.hasNext()) {
            int i4 = it2.next().getInt("altitude");
            if (i4 < i3) {
                i3 = i4;
            }
        }
        if (i == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i == Integer.MAX_VALUE || i3 == Integer.MIN_VALUE) {
            return Collections.emptyList();
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.putInt("maxBloodOxygen", i);
        hiHealthData.putInt("minBloodOxygen", i3);
        arrayList.add(hiHealthData);
        return arrayList;
    }
}
