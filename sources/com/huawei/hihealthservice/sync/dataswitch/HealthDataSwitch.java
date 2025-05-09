package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiRelationInformation;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.iip;
import defpackage.iir;
import defpackage.iis;
import defpackage.ijf;
import defpackage.iku;
import defpackage.ikv;
import defpackage.isl;
import defpackage.isr;
import defpackage.iuf;
import defpackage.iuh;
import defpackage.iuj;
import defpackage.iup;
import defpackage.iut;
import defpackage.iuz;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HealthDataSwitch {
    private iku b;
    private Context d;

    public HealthDataSwitch(Context context) {
        this.d = context;
        this.b = iku.a(context);
    }

    public List<HealthDetail> b(List<HiHealthData> list, int i, int i2) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (i2 != 3 && i2 != 7 && i2 != 9) {
            switch (i2) {
                case 11:
                    break;
                case 12:
                case 13:
                case 14:
                case 16:
                    return c(list, i, i2);
                case 15:
                    return e(list, i, i2);
                default:
                    return b(list, i);
            }
        }
        return a(list, i, i2);
    }

    public List<iuf> d(List<HiHealthData> list, int i) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("Debug_HealthDataSwitch", "hiHealthDataToCloudExtendedServiceData businessDataList is null");
            return null;
        }
        if (i == 5000 || i == 5001) {
            return e(list, i);
        }
        return null;
    }

    public List<HiHealthData> c(HealthDetail healthDetail, int i) throws iut {
        List<HiHealthData> d;
        List<SamplePoint> samplePoints = healthDetail.getSamplePoints();
        if (samplePoints == null || samplePoints.isEmpty()) {
            LogUtil.h("Debug_HealthDataSwitch", "cloudToLocal samplePoints is null or empty");
            return null;
        }
        ikv e = this.b.e(iuz.d(this.d), i, healthDetail.getDeviceCode().longValue(), true);
        if (e == null) {
            ReleaseLogUtil.c("Debug_HealthDataSwitch", "cloudToLocal hiHealthContext is null");
            return null;
        }
        e.i(1);
        String timeZone = healthDetail.getTimeZone();
        long size = samplePoints.size();
        ArrayList arrayList = new ArrayList((int) size);
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            SamplePoint samplePoint = samplePoints.get(i3);
            if (samplePoint != null && (d = iup.d(samplePoint, healthDetail)) != null && !d.isEmpty()) {
                for (HiHealthData hiHealthData : d) {
                    hiHealthData.setTimeZone(timeZone);
                    ikv.b(hiHealthData, e);
                }
                arrayList.add(d);
                i2 += d.size();
            }
        }
        ArrayList arrayList2 = new ArrayList(i2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.addAll((List) it.next());
        }
        return arrayList2;
    }

    public List<HiHealthData> e(List<HealthDetail> list, int i, long j) throws IllegalArgumentException, iut {
        List<SamplePoint> c2;
        ikv b;
        SamplePoint samplePoint;
        if (HiCommonUtil.d(list)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        int i2 = 0;
        for (HealthDetail healthDetail : list) {
            if (healthDetail != null && healthDetail.getVersion() > j && (c2 = c(healthDetail)) != null && (b = b(i, healthDetail)) != null && (samplePoint = c2.get(0)) != null) {
                List<HiHealthData> build = isr.e(samplePoint).build(samplePoint, b, healthDetail);
                if (TextUtils.isEmpty(samplePoint.getFieldsModifyTime())) {
                    Iterator<HiHealthData> it = build.iterator();
                    while (it.hasNext()) {
                        it.next().setModifiedTime(0L);
                    }
                }
                arrayList.add(build);
                i2 += build.size();
            }
        }
        ArrayList arrayList2 = new ArrayList(i2);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.addAll((List) it2.next());
        }
        return arrayList2;
    }

    private ikv b(int i, HealthDetail healthDetail) throws iut {
        ikv e = this.b.e(iuz.d(this.d), i, healthDetail.getDeviceCode().longValue(), true);
        if (e == null) {
            ReleaseLogUtil.c("Debug_HealthDataSwitch", "hiHealthContext is null, device = ", healthDetail.getDeviceCode());
            return null;
        }
        e.i(1);
        return e;
    }

    private List<SamplePoint> c(HealthDetail healthDetail) {
        if (healthDetail == null) {
            return null;
        }
        List<SamplePoint> samplePoints = healthDetail.getSamplePoints();
        if (HiCommonUtil.d(samplePoints)) {
            return null;
        }
        int size = samplePoints.size();
        if (size <= 1) {
            return samplePoints;
        }
        throw new IllegalArgumentException("the data " + healthDetail.getType() + " has " + size + " sample points");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:11|(2:12|13)|(10:16|17|18|19|(4:21|22|23|24)(4:29|30|31|33)|25|26|27|28|14)|37|38|39|40|(1:42)|43|44|28|9) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0151, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0152, code lost:
    
        health.compact.a.hwlogsmodel.ReleaseLogUtil.d("HiH_HealthDataSwitch", "json exception extendAttribute= ", r0.getMessage());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.huawei.hwcloudmodel.model.unite.HealthDetail> d(java.util.List<com.huawei.hihealth.HiHealthData> r17, int r18, int r19) throws java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch.d(java.util.List, int, int):java.util.List");
    }

    private void b(JSONObject jSONObject, HiHealthDictField hiHealthDictField, HiHealthData hiHealthData) throws JSONException {
        Double valueOf = Double.valueOf(hiHealthData.getValue());
        String e = hiHealthDictField.e();
        if (e == null) {
            jSONObject.put(hiHealthDictField.a(), valueOf);
            return;
        }
        e.hashCode();
        if (e.equals("Integer")) {
            jSONObject.put(hiHealthDictField.a(), valueOf.intValue());
        } else if (e.equals("Long")) {
            jSONObject.put(hiHealthDictField.a(), valueOf.longValue());
        } else {
            jSONObject.put(hiHealthDictField.a(), valueOf);
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        long f4215a;
        List<HiHealthData> b = new ArrayList();
        long e;

        private c() {
        }

        static List<c> c(List<HiHealthData> list) {
            ArrayList arrayList = new ArrayList(list.size());
            boolean[] zArr = new boolean[list.size()];
            for (int i = 0; i < list.size(); i++) {
                if (!zArr[i]) {
                    c cVar = new c();
                    cVar.f4215a = list.get(i).getStartTime();
                    cVar.e = list.get(i).getEndTime();
                    cVar.b.add(list.get(i));
                    zArr[i] = true;
                    for (int i2 = i + 1; i2 < list.size(); i2++) {
                        if (!zArr[i2]) {
                            long startTime = list.get(i2).getStartTime();
                            long endTime = list.get(i2).getEndTime();
                            if (startTime == cVar.f4215a && endTime == cVar.e) {
                                cVar.b.add(list.get(i2));
                                zArr[i2] = true;
                            }
                        }
                    }
                    arrayList.add(cVar);
                }
            }
            return arrayList;
        }
    }

    private List<HealthDetail> b(List<HiHealthData> list, int i) {
        HealthDetail a2;
        ikv f = iis.d().f(i);
        if (f == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && (a2 = iup.a(hiHealthData, iup.b(hiHealthData.getType()))) != null) {
                a2.setDeviceCode(Long.valueOf(f.a()));
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    public HiHealthData a(iuf iufVar, int i) {
        String e = iufVar.e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("Debug_HealthDataSwitch", "businessCloudToLocal extendedServiceData is null");
            return null;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setStartTime(iufVar.a());
        hiHealthData.setEndTime(iufVar.c());
        hiHealthData.setTimeZone(iufVar.d());
        hiHealthData.setUserId(i);
        hiHealthData.setAppId(0);
        hiHealthData.setDeviceId(0);
        hiHealthData.setType(iup.d(iufVar.f()));
        hiHealthData.setDataSource(iufVar.b());
        ikv.b(hiHealthData, this.b.e(0, i, 0));
        hiHealthData.setSyncStatus(1);
        iuj iujVar = (iuj) HiJsonUtil.e(e, iuj.class);
        hiHealthData.setValue(iujVar.e());
        hiHealthData.setMetaData(iujVar.d());
        List<iuh> b = iujVar.b();
        if (b == null || b.isEmpty()) {
            LogUtil.h("Debug_HealthDataSwitch", "businessCloudToLocal relatedDataList is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<iuh> it = b.iterator();
        while (it.hasNext()) {
            e(arrayList, it.next(), i);
        }
        if (arrayList.isEmpty()) {
            LogUtil.h("Debug_HealthDataSwitch", "businessCloudToLocal relationInformations is null");
            return null;
        }
        hiHealthData.setRelationInformations(new Gson().toJson(arrayList, new TypeToken<List<HiRelationInformation>>() { // from class: com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch.4
        }.getType()));
        hiHealthData.setRelationFlag(true);
        return hiHealthData;
    }

    private void e(List<HiRelationInformation> list, iuh iuhVar, int i) {
        String d = iuhVar.d();
        String b = iuhVar.b();
        int a2 = ijf.d(this.d).a(d);
        int a3 = iip.b().a(b);
        LogUtil.a("Debug_HealthDataSwitch", "deviceUniqueCode is ", Integer.valueOf(a2), ", packageName is ", Integer.valueOf(a3), ", userId is ", Integer.valueOf(i));
        ikv d2 = iis.d().d(i, a3, a2);
        if (d2 == null) {
            LogUtil.h("Debug_HealthDataSwitch", "buildHiHealthData hiHealthContext is null");
            return;
        }
        int d3 = iup.d(iuhVar.a());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(d2.b()));
        List<HiHealthData> d4 = isl.b(this.d).d(d3, iuhVar.c(), iuhVar.e(), arrayList);
        if (d4 != null) {
            for (HiHealthData hiHealthData : d4) {
                HiRelationInformation hiRelationInformation = new HiRelationInformation();
                hiRelationInformation.e(CommonUtil.h(String.valueOf(hiHealthData.getDataId())));
                hiRelationInformation.a(hiHealthData.getType());
                list.add(hiRelationInformation);
            }
        }
    }

    private List<HealthDetail> c(List<HiHealthData> list, int i, int i2) {
        ikv f = iis.d().f(i);
        if (f == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        iup.a(arrayList, list, i2, f.a());
        return arrayList;
    }

    private List<HealthDetail> e(List<HiHealthData> list, int i, int i2) {
        ikv f = iis.d().f(i);
        if (f == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        iup.e(arrayList, list, i2, f.a());
        return arrayList;
    }

    private List<iuf> e(List<HiHealthData> list, int i) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            iuf iufVar = new iuf();
            iufVar.d(i);
            iufVar.a(hiHealthData.getTimeZone());
            iufVar.b(hiHealthData.getStartTime());
            iufVar.d(hiHealthData.getEndTime());
            iufVar.e(hiHealthData.getDataSource());
            iuj e = e(hiHealthData);
            if (e != null) {
                iufVar.b(HiJsonUtil.e(e));
                arrayList.add(iufVar);
            }
        }
        return arrayList;
    }

    private iuj e(HiHealthData hiHealthData) {
        int b;
        List<HiHealthData> d = iir.d().d(hiHealthData.getDataId(), hiHealthData.getType());
        if (d == null || d.isEmpty()) {
            LogUtil.h("Debug_HealthDataSwitch", "buildServiceData businessRelationList is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(d.size());
        int i = 0;
        for (HiHealthData hiHealthData2 : d) {
            arrayList.add(Integer.valueOf(hiHealthData2.getInt("relation_id")));
            i = hiHealthData2.getInt("relation_type");
        }
        List<HiHealthData> a2 = isl.b(this.d).a(i, arrayList);
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("Debug_HealthDataSwitch", "buildServiceData originalDataList is null");
            return null;
        }
        ArrayList arrayList2 = new ArrayList(a2.size());
        for (HiHealthData hiHealthData3 : a2) {
            iuh a3 = a(hiHealthData3);
            if (a3 != null && (b = iup.b(hiHealthData3.getType())) != -1) {
                a3.b(b);
                a3.e(hiHealthData3.getStartTime());
                a3.a(hiHealthData3.getEndTime());
                arrayList2.add(a3);
            }
        }
        if (HiCommonUtil.d(arrayList2)) {
            LogUtil.h("Debug_HealthDataSwitch", "relatedDataList is null or empty");
            return null;
        }
        int value = (int) hiHealthData.getValue();
        iuj iujVar = new iuj();
        iujVar.b(arrayList2);
        iujVar.b(value);
        iujVar.a(hiHealthData.getMetaData());
        return iujVar;
    }

    private iuh a(HiHealthData hiHealthData) {
        iuh iuhVar = new iuh();
        ikv f = iis.d().f(hiHealthData.getClientId());
        if (f == null) {
            LogUtil.h("Debug_HealthDataSwitch", "getRelateInfo hiHealthContext is null");
            return null;
        }
        HiAppInfo c2 = iip.b().c(f.e());
        if (c2 == null) {
            return null;
        }
        iuhVar.b(c2.getPackageName());
        HiDeviceInfo a2 = ijf.d(this.d).a(f.d());
        if (a2 == null) {
            return null;
        }
        iuhVar.a(a2.getDeviceUniqueCode());
        return iuhVar;
    }

    private List<HealthDetail> a(List<HiHealthData> list, int i, int i2) {
        ikv f = iis.d().f(i);
        if (f == null) {
            return null;
        }
        return iup.d(list, i2, f.a());
    }
}
