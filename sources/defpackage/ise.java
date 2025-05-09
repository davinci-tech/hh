package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.store.merge.HiDataCoreSessionMerge;
import com.huawei.hihealthservice.store.merge.HiHealthDataSessionMerge;
import com.huawei.hwcloudmodel.model.unite.DataTimeDelCondition;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ise {
    private static Context b;

    /* renamed from: a, reason: collision with root package name */
    private List<DataTimeDelCondition> f13570a;
    private String[] d;
    private int[] e;

    private ise() {
        this.e = new int[]{DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME.value()};
        this.d = new String[]{"bedTime", "risingTime", "fallAsleepTime", "wakeupTime"};
    }

    static class a {
        private static final ise c = new ise();
    }

    public static ise a(Context context) {
        if (context != null) {
            b = context.getApplicationContext();
        }
        return a.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(long r17, long r19, int[] r21, java.util.List<java.lang.Integer> r22, int r23) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ise.a(long, long, int[], java.util.List, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0152  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(long r19, long r21, int[] r23, java.util.List<java.lang.Integer> r24, int r25) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ise.b(long, long, int[], java.util.List, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0175  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(long r19, long r21, int[] r23, java.util.List<java.lang.Integer> r24, int r25) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ise.d(long, long, int[], java.util.List, int):boolean");
    }

    private void c(long j, long j2, List<Integer> list, int i) {
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCoreSleepDictData type-", Integer.valueOf(i));
        List<HiHealthData> e = ijj.a(b).e(j, j2, CommonUtil.b(HiHealthDictManager.d((Context) null).g(i)), list);
        if (HiCommonUtil.d(e)) {
            ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCoreSleepDictData isNullEmpty ", Long.valueOf(j), " endTime= ", Long.valueOf(j2), ",clientIds= ", list, ",type=", Integer.valueOf(i));
            return;
        }
        for (HiHealthData hiHealthData : e) {
            if (hiHealthData.getSyncStatus() == 0) {
                ijj.a(b).a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), hiHealthData.getClientId());
            } else {
                ijj.a(b).d(hiHealthData.getDataId(), 2, 2);
            }
        }
    }

    private void b(long j, long j2, List<Integer> list) {
        String str;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setType(this.e);
        hiAggregateOption.setConstantsKey(this.d);
        boolean z = true;
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        HiDataFilter.DataFilterExpression dataFilterExpression = new HiDataFilter.DataFilterExpression("bedTime", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, j - 86400000);
        HiDataFilter.DataFilterExpression dataFilterExpression2 = new HiDataFilter.DataFilterExpression("bedTime", HiDataFilter.DataFilterExpression.LESS_EQUAL, j2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataFilterExpression);
        arrayList.add(dataFilterExpression2);
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(0);
        List<HiHealthData> c = ijt.b().c(list, hiAggregateOption, ifl.d(true).e(HiJsonUtil.e(new HiDataFilter(arrayList, arrayList2))).b());
        String str2 = "HiH_HiHealthDataDeleteStore";
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteSleepRecordDictData healthDatas-", c);
        if (HiCommonUtil.d(c)) {
            return;
        }
        for (HiHealthData hiHealthData : c) {
            if (hiHealthData.getLong("bedTime") < j && hiHealthData.getLong("risingTime") > j) {
                d(j, hiHealthData, false);
            } else if (hiHealthData.getLong("bedTime") >= j2 || hiHealthData.getLong("risingTime") <= j2) {
                if (hiHealthData.getLong("bedTime") >= j && hiHealthData.getLong("risingTime") < j2) {
                    str = str2;
                    c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), list, DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value());
                } else {
                    str = str2;
                    ReleaseLogUtil.e(str, "deleteSleepRecordDictData not del ", hiHealthData);
                }
                str2 = str;
                z = true;
            } else {
                d(j2, hiHealthData, z);
            }
            str = str2;
            str2 = str;
            z = true;
        }
    }

    private HiHealthData e(long j, long j2, int i, long j3) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setTimeInterval(j, j2);
        hiHealthData.setType(i);
        hiHealthData.setValue(j3);
        hiHealthData.setDeviceUuid("-1");
        return hiHealthData;
    }

    private void d(long j, HiHealthData hiHealthData, boolean z) {
        if (z) {
            long j2 = j + 1;
            HiHealthData e = e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value(), j2);
            HiHealthData e2 = e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value(), j2);
            ijj a2 = ijj.a(b);
            a2.c(e, hiHealthData.getClientId(), 0);
            a2.c(e2, hiHealthData.getClientId(), 0);
            return;
        }
        long j3 = j - 1;
        HiHealthData e3 = e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value(), j3);
        HiHealthData e4 = e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME.value(), j3);
        ijj a3 = ijj.a(b);
        a3.c(e3, hiHealthData.getClientId(), 0);
        a3.c(e4, hiHealthData.getClientId(), 0);
    }

    private void d(List<HiHealthData> list) {
        isf a2 = isf.a(b);
        a2.prepareRealTimeHealthDataStat(list);
        a2.doRealTimeHealthDataStat();
    }

    private void c(List<HiHealthData> list, List<Integer> list2) {
        HiHealthDataSessionMerge hiHealthDataSessionMerge = new HiHealthDataSessionMerge(b);
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            hiHealthDataSessionMerge.merge(hiHealthData, hiHealthData.getClientId(), list2);
        }
    }

    private void d(List<HiHealthData> list, List<Integer> list2) {
        HiDataCoreSessionMerge hiDataCoreSessionMerge = new HiDataCoreSessionMerge(b);
        if (HiCommonUtil.d(list)) {
            return;
        }
        list.get(list.size() - 1).setLastDataFlag(true);
        for (HiHealthData hiHealthData : list) {
            hiDataCoreSessionMerge.merge(hiHealthData, hiHealthData.getClientId(), list2);
        }
    }

    public List<DataTimeDelCondition> e() {
        List<DataTimeDelCondition> subList;
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudSleep mSleepTimeList=" + HiJsonUtil.e(this.f13570a));
        ArrayList arrayList = new ArrayList(16);
        if (HiCommonUtil.d(this.f13570a)) {
            return arrayList;
        }
        int size = this.f13570a.size();
        int i = size / 10;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 == i) {
                subList = this.f13570a.subList(i3 * 10, size);
            } else {
                subList = this.f13570a.subList(i3 * 10, (i3 + 1) * 10);
            }
            try {
                if (!e(subList)) {
                    arrayList.addAll(subList);
                }
            } catch (iut unused) {
                ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudSleep SyncException=" + HiJsonUtil.e(subList));
                arrayList.addAll(subList);
            }
        }
        this.f13570a.clear();
        return arrayList;
    }

    private boolean e(List<DataTimeDelCondition> list) throws iut {
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudSleep deleteDataByTime=" + HiJsonUtil.e(list));
        int i = 0;
        while (true) {
            if (i >= 2) {
                break;
            }
            if (iuz.d(b, list)) {
                ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudSleep OK !  dataTimeDelConditions = ", HiJsonUtil.e(list));
                break;
            }
            ReleaseLogUtil.d("HiH_HiHealthDataDeleteStore", "deleteCloudSleep failed dataTimeDelConditions = ", HiJsonUtil.e(list), " upLoadFailCount= ", Integer.valueOf(i));
            i++;
        }
        return i < 2;
    }

    private DataTimeDelCondition a(long j, long j2, int i) {
        DataTimeDelCondition dataTimeDelCondition = new DataTimeDelCondition();
        dataTimeDelCondition.setStartTime(Long.valueOf(j));
        dataTimeDelCondition.setEndTime(Long.valueOf(j2));
        dataTimeDelCondition.setType(Integer.valueOf(i));
        dataTimeDelCondition.setDeviceCode(0L);
        return dataTimeDelCondition;
    }
}
