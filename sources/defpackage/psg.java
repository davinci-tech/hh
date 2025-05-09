package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiHearRateUpMetaData;
import com.huawei.hihealth.data.model.HiHeartRateData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel.a;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel.e;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AggregateOptionBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes6.dex */
public class psg {
    public static void dsH_(Map<Long, IStorageModel> map, String str, SparseArray<List<HiHealthData>> sparseArray, psj psjVar) {
        StorageGenericModel storageGenericModel;
        if (psjVar == null) {
            return;
        }
        List<HiHealthData> list = sparseArray.get(psjVar.d());
        if (koq.b(list)) {
            LogUtil.a("HeartRateStorageHelper", "loadStatisticsValue, hrKey: ", str, " data is empty");
            return;
        }
        LogUtil.a("HeartRateStorageHelper", "loadStatisticsValue, hrKey: ", str, " data size: ", Integer.valueOf(list.size()));
        int b = psjVar.b();
        for (HiHealthData hiHealthData : list) {
            long e = psj.e(hiHealthData.getStartTime(), b);
            if (map.containsKey(Long.valueOf(e))) {
                storageGenericModel = (StorageGenericModel) map.get(Long.valueOf(e));
            } else {
                storageGenericModel = new StorageGenericModel();
            }
            if (storageGenericModel != null) {
                storageGenericModel.c(str, hiHealthData.getFloat(str));
                map.put(Long.valueOf(e), storageGenericModel);
            }
        }
    }

    public static void e(Map<Long, IStorageModel> map, String str, List<HiHealthData> list, psj psjVar) {
        StorageGenericModel storageGenericModel;
        int b = psjVar != null ? psjVar.b() : 0;
        if (koq.b(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            long e = psj.e(hiHealthData.getStartTime(), b);
            if (map.get(Long.valueOf(e)) != null) {
                storageGenericModel = (StorageGenericModel) map.get(Long.valueOf(e));
            } else {
                storageGenericModel = new StorageGenericModel();
            }
            psk pskVar = new psk();
            pskVar.d(hiHealthData.getStartTime(), hiHealthData.getEndTime());
            HiHearRateUpMetaData hiHearRateUpMetaData = (HiHearRateUpMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiHearRateUpMetaData.class);
            if (hiHearRateUpMetaData != null) {
                pskVar.c(hiHearRateUpMetaData.getMinHeartRate(), hiHearRateUpMetaData.getMaxHeartRate());
                List<HiHeartRateData> details = hiHearRateUpMetaData.getDetails();
                ArrayList<HeartRateDetailData> arrayList = new ArrayList<>(16);
                if (koq.c(details)) {
                    for (HiHeartRateData hiHeartRateData : details) {
                        HeartRateDetailData heartRateDetailData = new HeartRateDetailData();
                        heartRateDetailData.setTime(hiHeartRateData.getTimestamp());
                        heartRateDetailData.setValue(hiHeartRateData.getHeartRate());
                        arrayList.add(heartRateDetailData);
                    }
                }
                pskVar.b(arrayList);
                storageGenericModel.e(str, pskVar);
                map.put(Long.valueOf(e), storageGenericModel);
            }
        }
    }

    public static void d(Map<Long, IStorageModel> map, boolean z) {
        StorageGenericModel.PresentStyle eVar;
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
            StorageGenericModel storageGenericModel = (StorageGenericModel) entry.getValue();
            if (z) {
                Objects.requireNonNull(storageGenericModel);
                eVar = storageGenericModel.new a("HR_NORMAL_DETAIL", new String[0]);
            } else {
                Objects.requireNonNull(storageGenericModel);
                eVar = storageGenericModel.new e("HR_NORMAL_MIN", "HR_NORMAL_MAX");
            }
            storageGenericModel.d(eVar);
            if (!storageGenericModel.b()) {
                long longValue = entry.getKey().longValue();
                arrayList.add(Long.valueOf(longValue));
                LogUtil.a("HeartRateStorageHelper", "processNormal key ", Long.valueOf(longValue));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            map.remove((Long) it.next());
        }
    }

    public static void c(Map<Long, IStorageModel> map, boolean z) {
        String str = z ? "HR_REST_DETAIL" : "HR_REST";
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
            StorageGenericModel storageGenericModel = (StorageGenericModel) entry.getValue();
            Objects.requireNonNull(storageGenericModel);
            storageGenericModel.d(storageGenericModel.new a(str, new String[0]));
            if (!storageGenericModel.b()) {
                arrayList.add(entry.getKey());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            map.remove((Long) it.next());
        }
    }

    public static void b(Map<Long, IStorageModel> map, boolean z) {
        StorageGenericModel.PresentStyle eVar;
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
            StorageGenericModel storageGenericModel = (StorageGenericModel) entry.getValue();
            if (z) {
                eVar = new StorageGenericModel.d("HR_WARNING_DETAIL");
            } else {
                Objects.requireNonNull(storageGenericModel);
                eVar = storageGenericModel.new e("HR_WARNING_MIN", "HR_WARNING_MAX");
            }
            storageGenericModel.d(eVar);
            if (!storageGenericModel.b()) {
                arrayList.add(entry.getKey());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            map.remove((Long) it.next());
        }
    }

    public static void a(Map<Long, IStorageModel> map, boolean z) {
        StorageGenericModel.PresentStyle eVar;
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
            StorageGenericModel storageGenericModel = (StorageGenericModel) entry.getValue();
            if (z) {
                eVar = new StorageGenericModel.d("BRADYCARDIA_DETAIL");
            } else {
                Objects.requireNonNull(storageGenericModel);
                eVar = storageGenericModel.new e("BRADYCARDIA_MIN", "BRADYCARDIA_MAX");
            }
            storageGenericModel.d(eVar);
            if (!storageGenericModel.b()) {
                arrayList.add(entry.getKey());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            map.remove((Long) it.next());
        }
    }

    public static void dsI_(SparseArray<List<HiHealthData>> sparseArray, DataInfos dataInfos) {
        List<HiHealthData> a2 = a(sparseArray.get(0), sparseArray.get(1));
        if (dataInfos.isYearData()) {
            a2 = e(a2);
        }
        sparseArray.put(0, a2);
    }

    public static List<HiHealthData> b(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList();
        if (!koq.c(list2)) {
            return koq.c(list) ? list : arrayList;
        }
        HiHealthData hiHealthData = list2.get(0);
        long j = 0;
        for (HiHealthData hiHealthData2 : list2) {
            if (hiHealthData2 == null) {
                LogUtil.h("HeartRateStorageHelper", "preProcessDayRestHeartRate healthData is null");
            } else {
                long startTime = hiHealthData2.getStartTime();
                if (j < startTime) {
                    hiHealthData = hiHealthData2;
                    j = startTime;
                }
            }
        }
        d(hiHealthData, 2018, "HR_SLEEP_REST_DETAIL", "HR_REST_DETAIL");
        arrayList.add(hiHealthData);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0055 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0063 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00bc  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0052 -> B:18:0x0053). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<com.huawei.hihealth.HiHealthData> a(java.util.List<com.huawei.hihealth.HiHealthData> r8, java.util.List<com.huawei.hihealth.HiHealthData> r9) {
        /*
            boolean r0 = defpackage.koq.b(r9)
            if (r0 == 0) goto L7
            return r8
        L7:
            java.util.Iterator r0 = r9.iterator()
        Lb:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L22
            java.lang.Object r1 = r0.next()
            com.huawei.hihealth.HiHealthData r1 = (com.huawei.hihealth.HiHealthData) r1
            java.lang.String r2 = "HR_SLEEP_REST"
            java.lang.String r3 = "HR_REST"
            r4 = 46018(0xb3c2, float:6.4485E-41)
            d(r1, r4, r2, r3)
            goto Lb
        L22:
            boolean r0 = defpackage.koq.b(r8)
            if (r0 == 0) goto L29
            return r9
        L29:
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.util.Iterator r8 = r8.iterator()
            java.util.Iterator r9 = r9.iterator()
            boolean r1 = r8.hasNext()
            r2 = 0
            if (r1 == 0) goto L44
            java.lang.Object r1 = r8.next()
            com.huawei.hihealth.HiHealthData r1 = (com.huawei.hihealth.HiHealthData) r1
            goto L45
        L44:
            r1 = r2
        L45:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L52
            java.lang.Object r3 = r9.next()
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3
            goto L53
        L52:
            r3 = r2
        L53:
            if (r1 == 0) goto La5
            if (r3 == 0) goto La5
            long r4 = r1.getDay()
            long r6 = r3.getDay()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L75
            r0.add(r1)
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L73
            java.lang.Object r1 = r8.next()
            com.huawei.hihealth.HiHealthData r1 = (com.huawei.hihealth.HiHealthData) r1
            goto L53
        L73:
            r1 = r2
            goto L53
        L75:
            if (r4 <= 0) goto L87
            r0.add(r3)
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L52
            java.lang.Object r3 = r9.next()
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3
            goto L53
        L87:
            r0.add(r3)
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L97
            java.lang.Object r1 = r8.next()
            com.huawei.hihealth.HiHealthData r1 = (com.huawei.hihealth.HiHealthData) r1
            goto L98
        L97:
            r1 = r2
        L98:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L52
            java.lang.Object r3 = r9.next()
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3
            goto L53
        La5:
            if (r1 == 0) goto Lba
            r0.add(r1)
        Laa:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto Lba
            java.lang.Object r1 = r8.next()
            com.huawei.hihealth.HiHealthData r1 = (com.huawei.hihealth.HiHealthData) r1
            r0.add(r1)
            goto Laa
        Lba:
            if (r3 == 0) goto Lcf
            r0.add(r3)
        Lbf:
            boolean r8 = r9.hasNext()
            if (r8 == 0) goto Lcf
            java.lang.Object r8 = r9.next()
            com.huawei.hihealth.HiHealthData r8 = (com.huawei.hihealth.HiHealthData) r8
            r0.add(r8)
            goto Lbf
        Lcf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.psg.a(java.util.List, java.util.List):java.util.List");
    }

    private static List<HiHealthData> e(List<HiHealthData> list) {
        if (koq.b(list)) {
            return list;
        }
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        HiHealthData hiHealthData = null;
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData == null) {
                linkedList2.add(hiHealthData2);
            } else if (TextUtils.equals(e(hiHealthData.getStartTime()), e(hiHealthData2.getStartTime()))) {
                linkedList2.add(hiHealthData2);
            } else {
                linkedList.add(c(linkedList2));
                linkedList2.clear();
                linkedList2.add(hiHealthData2);
            }
            hiHealthData = hiHealthData2;
        }
        if (koq.c(linkedList2)) {
            linkedList.add(c(linkedList2));
        }
        return linkedList;
    }

    private static String e(long j) {
        return j == 0 ? "" : DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_6);
    }

    private static HiHealthData c(List<HiHealthData> list) {
        if (koq.b(list)) {
            return null;
        }
        int size = list.size();
        Iterator<HiHealthData> it = list.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            f += it.next().getFloat("HR_REST");
        }
        HiHealthData hiHealthData = list.get(0);
        hiHealthData.putFloat("HR_REST", f / size);
        return hiHealthData;
    }

    public static HiHealthData d(HiHealthData hiHealthData, int i, String str, String str2) {
        if (hiHealthData == null) {
            return null;
        }
        hiHealthData.setType(i);
        hiHealthData.putFloat(str2, hiHealthData.getFloat(str));
        return hiHealthData;
    }

    public static AggregateOptionBuilder.DataGroupUnit a(DataInfos dataInfos) {
        return c(dataInfos, (String) null);
    }

    public static AggregateOptionBuilder.DataGroupUnit c(DataInfos dataInfos, String str) {
        if (dataInfos.isDayData()) {
            return AggregateOptionBuilder.DataGroupUnit.NONE;
        }
        if (dataInfos.isWeekData()) {
            return AggregateOptionBuilder.DataGroupUnit.DAY;
        }
        if (dataInfos.isMonthData()) {
            return AggregateOptionBuilder.DataGroupUnit.DAY;
        }
        if (dataInfos.isYearData()) {
            if ("HR_SLEEP_REST".equals(str) || "HR_REST".equals(str)) {
                return AggregateOptionBuilder.DataGroupUnit.DAY;
            }
            return AggregateOptionBuilder.DataGroupUnit.MONTH;
        }
        return AggregateOptionBuilder.DataGroupUnit.YEAR;
    }
}
