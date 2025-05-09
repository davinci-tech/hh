package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.google.gson.Gson;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.data.type.HiConfigDataType;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class isi {
    private static Context e;

    /* renamed from: a, reason: collision with root package name */
    private iix f13576a;
    private iiv b;
    private iit c;
    private iiu d;
    private iiw f;
    private ijn g;
    private ijo h;
    private iiy i;
    private ijc j;
    private HiHealthDictManager n;

    private isi() {
        this.j = ijc.d(e);
        this.i = iiy.e(e);
        this.g = ijn.a(e);
        this.f13576a = iix.a(e);
        this.h = ijo.b(e);
        this.f = iiw.e();
        this.c = iit.d();
        this.d = iiu.c();
        this.b = iiv.d();
        this.n = HiHealthDictManager.d(e);
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final isi f13577a = new isi();
    }

    public static isi a(Context context) {
        e = context.getApplicationContext();
        return d.f13577a;
    }

    public List<HiHealthData> a(int i, List<Integer> list, HiDataReadOption hiDataReadOption) {
        if (list == null || hiDataReadOption == null) {
            return null;
        }
        LogUtil.c("HiH_HiHealthDataRd", "readSessionData()");
        int readType = hiDataReadOption.getReadType();
        if (i == 20001) {
            if (readType == 0) {
                return this.j.a(hiDataReadOption, list, i, 21000);
            }
            return this.j.c(hiDataReadOption, list, i, 21000);
        }
        if (i != 22000) {
            if (i != 22100) {
                return b(i, list, hiDataReadOption, readType);
            }
            if (readType == 0) {
                return this.f13576a.e(hiDataReadOption, list, 22100, 22199);
            }
            return this.f13576a.d(hiDataReadOption, list, 22100, 22199);
        }
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(22001);
        arrayList.add(22002);
        if (readType == 0) {
            return iwf.b(this.g.a(hiDataReadOption, list, arrayList));
        }
        return iwf.b(this.g.d(hiDataReadOption, list, arrayList));
    }

    public boolean c(int i, List<Integer> list, HiDataReadOption hiDataReadOption) {
        if (list != null && hiDataReadOption != null) {
            LogUtil.c("HiH_HiHealthDataRd", "readSleepAllData()");
            if (i == 22000) {
                ArrayList arrayList = new ArrayList(10);
                arrayList.add(22001);
                arrayList.add(22002);
                arrayList.add(22003);
                return this.g.b(hiDataReadOption, list, arrayList);
            }
            if (i == 22100) {
                return this.f13576a.b(hiDataReadOption, list, 22100, 22199);
            }
            ReleaseLogUtil.e("HiH_HiHealthDataRd", "readSleepAllData other");
        }
        return false;
    }

    private List<HiHealthData> b(int i, List<Integer> list, HiDataReadOption hiDataReadOption, int i2) {
        if (i <= 21000) {
            if (i2 == 0) {
                return this.j.b(hiDataReadOption, list, i);
            }
            return this.j.e(hiDataReadOption, list, i);
        }
        if (i <= 22099) {
            if (i2 == 0) {
                return this.g.d(hiDataReadOption, list, i);
            }
            return this.g.b(hiDataReadOption, list, i);
        }
        if (i2 == 0) {
            return this.f13576a.c(hiDataReadOption, list, i);
        }
        return this.f13576a.e(hiDataReadOption, list, i);
    }

    public List<HiHealthData> e(int i, int i2, HiDataReadOption hiDataReadOption, ifl iflVar) {
        if (hiDataReadOption == null) {
            return null;
        }
        LogUtil.c("HiH_HiHealthDataRd", "readStatData()");
        if (i2 <= 0) {
            return null;
        }
        if (42007 == i) {
            return ivu.a(e, i).d(i, i2);
        }
        return ivu.a(e, i).d(hiDataReadOption, i, i2, iflVar);
    }

    public List<HiHealthData> b(int i, List<Integer> list, HiDataReadOption hiDataReadOption, ifl iflVar) {
        if (list == null || hiDataReadOption == null) {
            return null;
        }
        LogUtil.c("HiH_HiHealthDataRd", "readPointData()");
        if (hiDataReadOption.getReadType() == 0) {
            if (i < 2000) {
                return this.i.d(hiDataReadOption, i, list, iflVar);
            }
            if (HiHealthDataType.g(i)) {
                return ivu.d(e, i).b(hiDataReadOption, i, list, iflVar);
            }
            return this.h.d(hiDataReadOption, i, list, iflVar);
        }
        if (i < 2000) {
            return this.i.b(hiDataReadOption, i, list, iflVar);
        }
        if (HiHealthDataType.g(i)) {
            return ivu.d(e, i).d(hiDataReadOption, i, list, iflVar);
        }
        return this.h.a(hiDataReadOption, i, list, iflVar);
    }

    public List<HiHealthData> d(int i, List<Integer> list, HiDataReadOption hiDataReadOption) {
        if (list == null || hiDataReadOption == null) {
            return null;
        }
        if (!HiConfigDataType.i(i)) {
            LogUtil.h("HiH_HiHealthDataRd", "readConfigData is not support, pointType=", Integer.valueOf(i));
            return null;
        }
        int intValue = HiConfigDataType.e(i).intValue();
        Integer num = HiConfigDataType.d(intValue, 1) instanceof Integer ? (Integer) HiConfigDataType.d(intValue, 1) : null;
        if (num == null) {
            LogUtil.h("HiH_HiHealthDataRd", "config type is not set tablename");
            return null;
        }
        String a2 = iwj.a(num.intValue());
        if (hiDataReadOption.getReadType() == 0) {
            return this.c.d(a2, hiDataReadOption, i, list);
        }
        return this.c.b(a2, hiDataReadOption, i, list);
    }

    public List<HiHealthData> a(int i, int i2, HiDataReadOption hiDataReadOption) {
        if (hiDataReadOption == null || i2 <= 0) {
            return null;
        }
        if (!HiConfigDataType.f(i)) {
            LogUtil.h("HiH_HiHealthDataRd", "readConfigStatData statType is not support, statType=", Integer.valueOf(i));
            return null;
        }
        int intValue = HiConfigDataType.d(i).intValue();
        Integer num = HiConfigDataType.d(intValue, 7) instanceof Integer ? (Integer) HiConfigDataType.d(intValue, 7) : null;
        if (num == null) {
            LogUtil.h("HiH_HiHealthDataRd", "statType not set table name");
            return null;
        }
        return this.d.b(iwj.a(num.intValue()), hiDataReadOption, i, i2);
    }

    public void e(int i, List<Integer> list, HiDataReadOption hiDataReadOption, ifl iflVar, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        if (list == null || hiDataReadOption == null || iDataReadResultListener == null) {
            return;
        }
        if (this.n.l(i)) {
            a(i, list, hiDataReadOption, iflVar, iDataReadResultListener);
        } else {
            ish.a(i, list, hiDataReadOption, iDataReadResultListener, e, iflVar);
        }
    }

    public List<HiHealthData> e(int i, List<Integer> list, HiDataReadOption hiDataReadOption, ifl iflVar) {
        List<HiHealthData> a2;
        if (list == null || hiDataReadOption == null) {
            return null;
        }
        LogUtil.c("HiH_HiHealthDataRd", "readSetData()");
        int[] d2 = HiHealthDataType.d(i);
        if (i == 10001 || i == 10006) {
            if (hiDataReadOption.getReadType() == 0) {
                a2 = ivu.d(e, i).c(hiDataReadOption, d2, list);
            } else {
                a2 = ivu.d(e, i).a(hiDataReadOption, d2, list);
            }
            return iflVar.m() ? d(a2) : a2;
        }
        ArrayList arrayList = new ArrayList(d2.length);
        for (int i2 : d2) {
            arrayList.add(Integer.valueOf(i2));
        }
        if (i == DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()) {
            arrayList.add(2018);
        }
        ArrayList arrayList2 = new ArrayList(10);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.addAll(b(((Integer) it.next()).intValue(), list, hiDataReadOption, (ifl) null));
        }
        return iflVar.m() ? d(arrayList2) : arrayList2;
    }

    private List<HiHealthData> d(List<HiHealthData> list) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                long startTime = hiHealthData.getStartTime();
                if (hashMap.containsKey(Long.valueOf(startTime))) {
                    HiHealthData hiHealthData2 = (HiHealthData) hashMap.get(Long.valueOf(startTime));
                    hashMap2.put("point_value", Double.valueOf(hiHealthData.getValue()));
                    hashMap2.put("metadata", hiHealthData.getMetaData());
                    hiHealthData2.putString(String.valueOf(hiHealthData.getType()), HiJsonUtil.e(hashMap2));
                } else {
                    hashMap2.put("point_value", Double.valueOf(hiHealthData.getValue()));
                    hashMap2.put("metadata", hiHealthData.getMetaData());
                    hiHealthData.getValueHolder().remove("point_value");
                    hiHealthData.getValueHolder().remove("metadata");
                    hiHealthData.putString(String.valueOf(hiHealthData.getType()), HiJsonUtil.e(hashMap2));
                    hashMap.put(Long.valueOf(startTime), hiHealthData);
                }
            }
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add((HiHealthData) ((Map.Entry) it.next()).getValue());
        }
        return arrayList;
    }

    public List<HiHealthData> e(int i, List<Integer> list, String str) {
        if (list == null || str == null) {
            return null;
        }
        LogUtil.c("HiH_HiHealthDataRd", "readRealTimeData()");
        return this.f.c(i, list, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.huawei.hihealth.HiHealthData> d(int r14, com.huawei.hihealth.HiDataReadOption r15) {
        /*
            r13 = this;
            if (r15 != 0) goto L4
            r14 = 0
            return r14
        L4:
            java.lang.String r0 = "checkAlreadyDownload()"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HiH_HiHealthDataRd"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            r0.<init>(r1)
            com.huawei.hihealth.HiHealthData r1 = new com.huawei.hihealth.HiHealthData
            r2 = 90001(0x15f91, float:1.26118E-40)
            r1.<init>(r2)
            ijr r2 = defpackage.ijr.d()
            r3 = 0
            r5 = 10015(0x271f, float:1.4034E-41)
            igq r14 = r2.e(r14, r3, r5)
            r2 = 0
            if (r14 != 0) goto L34
            r1.setValue(r2)
            r0.add(r1)
            return r0
        L34:
            int r14 = r14.d()
            long r3 = r15.getStartTime()
            int r3 = health.compact.a.HiDateUtil.c(r3)
            long r4 = r15.getEndTime()
            int r4 = health.compact.a.HiDateUtil.c(r4)
            r5 = 1
            r6 = -1
            if (r14 <= r4) goto L50
            r1.setValue(r6)
            goto L5c
        L50:
            if (r14 > r3) goto L59
            r1.setValue(r2)
            r14 = r2
            r10 = r14
            r11 = r10
            goto L5f
        L59:
            r1.setValue(r6)
        L5c:
            r11 = r14
            r10 = r3
            r14 = r5
        L5f:
            r0.add(r1)
            if (r14 == 0) goto L96
            com.huawei.hihealth.HiSyncOption r8 = new com.huawei.hihealth.HiSyncOption
            r8.<init>()
            r8.setSyncAction(r2)
            r14 = 10018(0x2722, float:1.4038E-41)
            r8.setSyncDataType(r14)
            r14 = 2
            r8.setSyncModel(r14)
            r8.setSyncMethod(r14)
            r8.setSyncScope(r5)
            iip r14 = defpackage.iip.b()
            android.content.Context r1 = defpackage.isi.e
            java.lang.String r1 = r1.getPackageName()
            int r9 = r14.a(r1)
            int[] r14 = r15.getType()
            ism r7 = defpackage.ism.f()
            r12 = r14[r2]
            r7.c(r8, r9, r10, r11, r12)
        L96:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isi.d(int, com.huawei.hihealth.HiDataReadOption):java.util.List");
    }

    public List<HiHealthData> c(int i, int i2, HiDataReadOption hiDataReadOption) {
        if (hiDataReadOption == null) {
            return null;
        }
        int e2 = ikr.b(e).e(0, i2, 0);
        if (e2 <= 0) {
            ReleaseLogUtil.d("HiH_HiHealthDataRd", "readBusinessData stat() statClient <= 0");
            return null;
        }
        LogUtil.c("HiH_HiHealthDataRd", "readBusinessData() statClient = ", Integer.valueOf(e2));
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(Integer.valueOf(e2));
        if (hiDataReadOption.getReadType() == 0) {
            return this.b.d(hiDataReadOption, i, arrayList);
        }
        return this.b.b(hiDataReadOption, i, arrayList);
    }

    private void a(int i, List<Integer> list, HiDataReadOption hiDataReadOption, ifl iflVar, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        List<HiHealthData> c;
        int h = iflVar.h();
        if (h == 0) {
            c = iiz.a(e).c(list, hiDataReadOption, i);
        } else if (h == 1) {
            c = iiz.a(e).b(list, hiDataReadOption, i);
        } else {
            ReleaseLogUtil.d("HiH_HiHealthDataRd", "sequenceDataType is not right, ", Integer.valueOf(iflVar.h()));
            return;
        }
        List<HiHealthData> b = ish.b(c, e);
        if (HiHealthDataType.d().contains(Integer.valueOf(i))) {
            a(e, iup.a(i), b);
        }
        HiDivideUtil.d(i, b, iDataReadResultListener);
        ReleaseLogUtil.e("HiH_HiHealthDataRd", "readDicSequenceData:sz=", Integer.valueOf(b.size()));
    }

    public static void a(Context context, int i, List<HiHealthData> list) {
        ReleaseLogUtil.e("HiH_HiHealthDataRd", "addInterpretDatas start");
        if (HiCommonUtil.d(list)) {
            ReleaseLogUtil.e("HiH_HiHealthDataRd", "no source data");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            List<HiHealthData> a2 = iir.d().a(hiHealthData.getType(), hiHealthData.getDataId(), i);
            if (HiCommonUtil.d(a2)) {
                ReleaseLogUtil.d("HiH_HiHealthDataRd", "error, this source data has no relate datas");
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator<HiHealthData> it = a2.iterator();
                while (it.hasNext()) {
                    List<HiHealthData> b = iiv.d().b(it.next().getInt("business_id"));
                    if (HiCommonUtil.d(b)) {
                        ReleaseLogUtil.d("HiH_HiHealthDataRd", "error, this source data in relation table but not in business table");
                    } else {
                        HiHealthData hiHealthData2 = b.get(0);
                        iuj iujVar = new iuj();
                        iujVar.b((int) hiHealthData2.getValue());
                        iujVar.a(hiHealthData2.getEndTime());
                        iujVar.c(hiHealthData2.getDataSource());
                        iujVar.c(hiHealthData2.getStartTime());
                        iujVar.a(hiHealthData2.getMetaData());
                        arrayList.add(iujVar);
                    }
                }
                hiHealthData.putString("interpret_data", new Gson().toJson(arrayList));
            }
        }
        ReleaseLogUtil.e("HiH_HiHealthDataRd", "addInterpretDatas end");
    }
}
