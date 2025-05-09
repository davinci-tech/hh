package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictStat;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteSampleSequenceRep;
import com.huawei.hwcloudmodel.model.unite.DataDeleteCondition;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class iuy {
    private static final Integer[] c = {2015, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2106, 2104, 2108};

    public static void c(Context context, int i) throws iut {
        ReleaseLogUtil.e("HiH_HiDelCloudData", "delHealthData who is ", Integer.valueOf(i));
        List<Integer> e = iis.d().e(i);
        if (e == null || e.isEmpty()) {
            LogUtil.h("Debug_HiDelCloudData", "no clientIDs get , who is ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("Debug_HiDelCloudData", "clientid list size =", Integer.valueOf(e.size()));
        Iterator<Integer> it = iuz.d().iterator();
        while (it.hasNext()) {
            c(context, i, it.next().intValue(), e);
        }
        ReleaseLogUtil.e("HiH_HiDelCloudData", "delHealthData end ");
    }

    public static void b(Context context, int i) throws iut {
        List<Integer> c2 = iuz.c("type_id", 2);
        if (HiCommonUtil.d(c2)) {
            return;
        }
        Iterator<Integer> it = c2.iterator();
        while (it.hasNext()) {
            d(context, i, it.next().intValue());
        }
    }

    public static void a(Context context, int i) throws iut {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(iis.d().h(i)));
        int i2 = 0;
        while (i2 < 2) {
            List<igo> a2 = a(context, arrayList);
            if (iuz.e(context, a2)) {
                Iterator<igo> it = a2.iterator();
                while (it.hasNext()) {
                    ivu.a(context, it.next().f()).b(r2.a());
                }
                ReleaseLogUtil.e("Debug_HiDelCloudData", "deleteHealthStatData OK !");
            } else {
                if (HiCommonUtil.d(a2)) {
                    return;
                }
                ReleaseLogUtil.d("Debug_HiDelCloudData", "deleteHealthStatData failed !");
                i2++;
            }
        }
    }

    public static void d(Context context, int i) throws iut {
        int h = iis.d().h(i);
        int i2 = 0;
        while (i2 < 2) {
            List<Integer> a2 = ijd.c(context).a(h, HiHealthDataType.a());
            if (HiCommonUtil.d(a2)) {
                return;
            }
            ReleaseLogUtil.e("Debug_HiDelCloudData", "deleteSleepStat start !", HiJsonUtil.e(a2), ",statClient", Integer.valueOf(h));
            for (Integer num : a2) {
                if (iuz.e(context, num.intValue())) {
                    ijd.c(context).e(44100, 44299, h, 2, num.intValue());
                    ijd.c(context).d(h, num.intValue(), 44100, 44299, 1);
                    ReleaseLogUtil.e("Debug_HiDelCloudData", "deleteSleepStat OK !", a2);
                } else {
                    ReleaseLogUtil.e("Debug_HiDelCloudData", "deleteSleepStat failed !", a2);
                    i2++;
                }
            }
        }
    }

    public static void e(Context context, int i) throws iut {
        if (context == null) {
            return;
        }
        LogUtil.a("HiH_HiDelCloudData", "delHealthSensitiveData who is ", Integer.valueOf(i));
        List<Integer> e = iis.d().e(i);
        if (e == null || e.isEmpty()) {
            LogUtil.h("Debug_HiDelCloudData", "no clientIDs get , who is ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("Debug_HiDelCloudData", "clientid list size =", Integer.valueOf(e.size()));
        List<Integer> b = iji.b().b("type_id", 2);
        if (HiCommonUtil.d(b)) {
            return;
        }
        Iterator<Integer> it = b.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (Arrays.asList(c).contains(Integer.valueOf(intValue))) {
                c(context, i, intValue, e);
            }
        }
        LogUtil.a("HiH_HiDelCloudData", "delHealthSensitiveData end ");
    }

    private static void c(Context context, int i, int i2, List<Integer> list) throws iut {
        ReleaseLogUtil.e("HiH_HiDelCloudData", "deleteDataByType start type = ", Integer.valueOf(i2));
        int i3 = 0;
        while (i3 < 2) {
            List<HiHealthData> a2 = a(context, i, i2, list);
            if (HiCommonUtil.d(a2)) {
                ReleaseLogUtil.e("HiH_HiDelCloudData", "nothing need to del,type is ", Integer.valueOf(i2));
                return;
            }
            List<DataDeleteCondition> b = iuz.b(context, a2);
            if (koq.b(b)) {
                ReleaseLogUtil.d("HiH_HiDelCloudData", "no recordMap get ! type is ", Integer.valueOf(i2));
                return;
            }
            ReleaseLogUtil.e("HiH_HiDelCloudData", "deleteDataByType getDataDelCondition dataDeleteConditions size = ", Integer.valueOf(b.size()));
            if (a(context, b, a2)) {
                ReleaseLogUtil.e("HiH_HiDelCloudData", "deleteDataByType OK ! type is ", Integer.valueOf(i2));
            } else {
                i3++;
                ReleaseLogUtil.d("HiH_HiDelCloudData", "deleteDataByType failed ! type is ", Integer.valueOf(i2));
            }
        }
        ReleaseLogUtil.e("HiH_HiDelCloudData", "deleteDataByType end type = ", Integer.valueOf(i2));
    }

    private static List<HiHealthData> a(Context context, int i, int i2, List<Integer> list) {
        if (HiHealthDataType.j(i2)) {
            return ivu.d(context, i2).b(new int[]{i2}, 2, list, 100);
        }
        if (HiHealthDataType.x(i2)) {
            return ijo.b(context).a(i2, 2, list, 100);
        }
        if (HiHealthDataType.r(i2)) {
            return ijn.a(context).b(i2, 2, list, 100);
        }
        if (HiHealthDataType.h(i2)) {
            return iix.a(context).e(i2, 2, list, 100);
        }
        if (i2 == 31001) {
            return iiz.a(context).e(list, 31001, 2, 10);
        }
        return null;
    }

    private static boolean a(Context context, List<DataDeleteCondition> list, List<HiHealthData> list2) throws iut {
        if (!iuz.a(context, list)) {
            return false;
        }
        int type = list2.get(0).getType();
        boolean z = true;
        if (HiHealthDataType.j(type)) {
            Iterator<HiHealthData> it = list2.iterator();
            while (it.hasNext()) {
                if (ivu.d(context, type).d(it.next().getDataId()) <= 0) {
                    z = false;
                }
            }
        } else if (HiHealthDataType.x(type)) {
            Iterator<HiHealthData> it2 = list2.iterator();
            while (it2.hasNext()) {
                if (ijo.b(context).c(it2.next().getDataId()) <= 0) {
                    z = false;
                }
            }
        } else if (HiHealthDataType.r(type)) {
            Iterator<HiHealthData> it3 = list2.iterator();
            while (it3.hasNext()) {
                if (ijn.a(context).d(it3.next().getDataId()) <= 0) {
                    z = false;
                }
            }
        } else if (HiHealthDataType.h(type)) {
            Iterator<HiHealthData> it4 = list2.iterator();
            while (it4.hasNext()) {
                if (iix.a(context).b(it4.next().getDataId()) <= 0) {
                    z = false;
                }
            }
        } else if (type == 31001) {
            Iterator<HiHealthData> it5 = list2.iterator();
            while (it5.hasNext()) {
                if (iiz.a(context).e(it5.next()) <= 0) {
                    z = false;
                }
            }
        } else {
            ReleaseLogUtil.d("HiH_HiDelCloudData", "deleteCloudAndLocalData failed ! type is ", Integer.valueOf(type));
            return false;
        }
        return z;
    }

    public static void d(Context context, int i, int i2) throws iut {
        if (context == null) {
            LogUtil.h("Debug_HiDelCloudData", "context is null");
            return;
        }
        LogUtil.a("HiH_HiDelCloudData", "delDictData start, who is ", Integer.valueOf(i), ",type ", Integer.valueOf(i2));
        int i3 = AnonymousClass5.e[HiHealthDataType.e(i2).ordinal()];
        if (i3 == 1) {
            List<Integer> c2 = iuz.c("type_id", 2);
            if (HiCommonUtil.d(c2)) {
                return;
            }
            if (c2.contains(Integer.valueOf(i2))) {
                b(context, i2, i);
            }
        } else if (i3 == 2) {
            a(context, i2, i);
        } else if (i3 == 3) {
            c(context, i2, i);
        }
        LogUtil.a("HiH_HiDelCloudData", "delDictData end ");
    }

    /* renamed from: iuy$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            e = iArr;
            try {
                iArr[HiHealthDataType.Category.SET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[HiHealthDataType.Category.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[HiHealthDataType.Category.STAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static void a(Context context, int i, int i2) throws iut {
        HiHealthDictType d;
        List<HiHealthData> b;
        List<Integer> e = iis.d().e(i2);
        if (HiCommonUtil.d(e)) {
            ReleaseLogUtil.d("Debug_HiDelCloudData", "no clientIds get , who is ", Integer.valueOf(i2));
            return;
        }
        LogUtil.a("Debug_HiDelCloudData", "clientIds list size = ", Integer.valueOf(e.size()));
        int i3 = 0;
        while (i3 < 2 && (d = HiHealthDictManager.d(context).d(i)) != null) {
            if (d.g() == 0) {
                b = iiz.a(context).b(e, new int[]{i}, new int[]{0}, 2, 10);
                if (HiCommonUtil.d(b)) {
                    return;
                }
            } else {
                b = iiz.a(context).b(e, new int[]{30001}, new int[]{d.g()}, 2, 10);
                if (HiCommonUtil.d(b)) {
                    return;
                }
                for (HiHealthData hiHealthData : b) {
                    if (hiHealthData != null) {
                        hiHealthData.setType(i);
                    }
                }
            }
            LogUtil.a("Debug_HiDelCloudData", "deleteDictSequenceData getDelHealthData delData size = ", Integer.valueOf(b.size()));
            List<DataDeleteCondition> b2 = iuz.b(context, b);
            if (koq.b(b2)) {
                ReleaseLogUtil.d("Debug_HiDelCloudData", "no dataDeleteConditions get ! type is ", Integer.valueOf(i));
                return;
            }
            LogUtil.a("Debug_HiDelCloudData", "deleteDictSequenceData getDataDelCondition size = ", Integer.valueOf(b2.size()));
            DeleteSampleSequenceRep deleteSampleSequenceRep = new DeleteSampleSequenceRep();
            deleteSampleSequenceRep.setDeleteSampleSequenceConditons(b2);
            if (ius.a(jbs.a(context).d(deleteSampleSequenceRep), false)) {
                for (HiHealthData hiHealthData2 : b) {
                    if (hiHealthData2 != null) {
                        ReleaseLogUtil.e("Debug_HiDelCloudData", "delTrackDone sequenceID is ", Long.valueOf(hiHealthData2.getDataId()), "result is ", Integer.valueOf(iiz.a(context).e(hiHealthData2)));
                    }
                }
                ReleaseLogUtil.e("Debug_HiDelCloudData", "deleteDictSequenceData OK ! type is ", Integer.valueOf(i));
            } else {
                ReleaseLogUtil.d("Debug_HiDelCloudData", "deleteDictSequenceData failed ! type is ", Integer.valueOf(i));
                i3++;
            }
        }
    }

    private static void b(Context context, int i, int i2) throws iut {
        List<Integer> e = iis.d().e(i2);
        if (HiCommonUtil.d(e)) {
            ReleaseLogUtil.d("Debug_HiDelCloudData", "no clientIds get , who is ", Integer.valueOf(i2));
            return;
        }
        LogUtil.a("Debug_HiDelCloudData", "clientIds list size = ", Integer.valueOf(e.size()));
        int i3 = 0;
        while (i3 < 2) {
            List<HiHealthData> c2 = c(context, i, e);
            if (HiCommonUtil.d(c2)) {
                return;
            }
            LogUtil.a("Debug_HiDelCloudData", "delDictData getDelHealthData delData size = ", Integer.valueOf(c2.size()));
            List<DataDeleteCondition> b = iuz.b(context, c2);
            if (koq.b(b)) {
                ReleaseLogUtil.d("Debug_HiDelCloudData", "no dataDeleteConditions get ! type is ", Integer.valueOf(i));
                return;
            }
            LogUtil.a("Debug_HiDelCloudData", "delDictData getDataDelCondition dataDeleteConditions size = ", Integer.valueOf(b.size()));
            if (iuz.a(context, b)) {
                for (HiHealthData hiHealthData : c2) {
                    ivu.d(context, hiHealthData.getType()).d(hiHealthData.getDataId());
                }
                ReleaseLogUtil.e("Debug_HiDelCloudData", "delDictData deleteDictPointData OK ! type is ", Integer.valueOf(i));
            } else {
                ReleaseLogUtil.d("Debug_HiDelCloudData", "delDictData deleteDictPointData failed ! type is ", Integer.valueOf(i));
                i3++;
            }
        }
    }

    private static void c(Context context, int i, int i2) throws iut {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(iis.d().h(i2)));
        int i3 = 0;
        while (i3 < 2) {
            List<igo> d = d(context, i, arrayList);
            if (HiCommonUtil.d(d)) {
                LogUtil.a("Debug_HiDelCloudData", "deleteDictStatData nothing need to del,type is ");
                return;
            }
            if (iuz.e(context, d)) {
                for (igo igoVar : d) {
                    ivu.a(context, igoVar.f()).b(igoVar);
                }
                ReleaseLogUtil.e("Debug_HiDelCloudData", "deleteDictStatData OK ! type is ", Integer.valueOf(i));
            } else {
                ReleaseLogUtil.d("Debug_HiDelCloudData", "deleteDictStatData failed ! type is ", Integer.valueOf(i));
                i3++;
            }
        }
    }

    private static List<HiHealthData> c(Context context, int i, List<Integer> list) {
        ArrayList arrayList = new ArrayList(10);
        List<Integer> g = HiHealthDictManager.d(context).g(i);
        if (g.size() == 0) {
            return arrayList;
        }
        int size = 100 / g.size();
        Iterator<Integer> it = g.iterator();
        while (it.hasNext()) {
            List<HiHealthData> b = ivu.d(context, i).b(new int[]{it.next().intValue()}, 2, list, size);
            if (!HiCommonUtil.d(b)) {
                arrayList.addAll(b);
            }
        }
        return arrayList;
    }

    private static List<igo> d(Context context, int i, List<Integer> list) {
        ArrayList arrayList = new ArrayList(10);
        List<HiHealthDictField> j = HiHealthDictManager.d(context).j(i);
        if (HiCommonUtil.d(j)) {
            ReleaseLogUtil.d("Debug_HiDelCloudData", "healthDictFields is null, health type is ", Integer.valueOf(i));
            return Collections.emptyList();
        }
        Iterator<HiHealthDictField> it = j.iterator();
        while (it.hasNext()) {
            Iterator<HiHealthDictStat> it2 = it.next().d().iterator();
            while (it2.hasNext()) {
                List<igo> e = ivu.a(context, i).e(it2.next().d(), 2, list, 100);
                if (!HiCommonUtil.d(e)) {
                    arrayList.addAll(e);
                }
            }
        }
        return arrayList;
    }

    private static List<igo> a(Context context, List<Integer> list) {
        ArrayList arrayList = new ArrayList(10);
        List<igo> b = ijd.c(context).b(2, list);
        List<igo> b2 = ijb.b().b(2, list);
        if (!HiCommonUtil.d(b)) {
            arrayList.addAll(b);
        }
        if (!HiCommonUtil.d(b2)) {
            arrayList.addAll(b2);
        }
        return arrayList;
    }
}
