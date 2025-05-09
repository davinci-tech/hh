package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwcloudmodel.model.unite.SportDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.iku;
import defpackage.ikv;
import defpackage.ism;
import defpackage.iss;
import defpackage.iup;
import defpackage.iut;
import defpackage.iuz;
import defpackage.iwj;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HiValidatorUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class SportDataSwitch {

    /* renamed from: a, reason: collision with root package name */
    private iss f4216a;
    private iku b;
    private Context c;

    public SportDataSwitch(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        this.b = iku.a(applicationContext);
        this.f4216a = iss.c();
    }

    public List<HiHealthData> c(List<SportDetail> list, int i, int i2) throws iut {
        List<HiHealthData> c;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int i3 = 0;
        for (SportDetail sportDetail : list) {
            if (sportDetail != null && (c = c(sportDetail, i, i2)) != null && !c.isEmpty()) {
                arrayList.add(c);
                i3 += c.size();
            }
        }
        ArrayList arrayList2 = new ArrayList(i3);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.addAll((List) it.next());
        }
        LogUtil.c("Debug_SportDataSwtich", "allCloudDataToHiHealthDatas hiHealthDatas size ", Integer.valueOf(arrayList2.size()));
        return arrayList2;
    }

    private List<HiHealthData> c(SportDetail sportDetail, int i, int i2) throws iut {
        if (i2 == 2) {
            return c(sportDetail, i);
        }
        if (i2 == 3) {
            return e(sportDetail, i);
        }
        LogUtil.h("Debug_SportDataSwtich", "cloudSportDatasToHiHealthDatas no such hiSyncModel");
        return null;
    }

    public List<SportDetail> a(List<HiHealthData> list, int i, int i2) {
        if (i2 == 2) {
            return this.f4216a.b(list, i);
        }
        if (i2 == 3) {
            return this.f4216a.d(list);
        }
        LogUtil.h("Debug_SportDataSwtich", "localDataToCloud no such hiSyncModel");
        return null;
    }

    private List<HiHealthData> c(SportDetail sportDetail, int i) throws iut {
        long d = HiDateUtil.d(sportDetail.getStartTime().longValue());
        long longValue = sportDetail.getEndTime().longValue();
        SportBasicInfo[] sportBasicInfos = sportDetail.getSportBasicInfos();
        long length = sportBasicInfos.length;
        ikv a2 = this.b.a(sportDetail.getAppType().intValue(), i, sportDetail.getDeviceCode().longValue());
        if (a2 == null) {
            ReleaseLogUtil.c("Debug_SportDataSwtich", "switchToHiHealthDatas hiHealthContext is null");
            return null;
        }
        a2.i(1);
        int b = iwj.b(sportDetail.getSportType().intValue());
        String timeZone = sportDetail.getTimeZone();
        String metadata = sportDetail.getMetadata();
        if (b > 22000 && b < 22099) {
            int ceil = (int) Math.ceil((longValue - d) / 60000.0f);
            if (ceil <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(ceil);
            long j = d;
            while (j < longValue) {
                long j2 = j + 60000;
                ikv ikvVar = a2;
                arrayList.add(e(j, j2, b, 0.0d, 1, ikvVar, metadata, timeZone, (sportDetail.getMergedFlag() == null || ism.e()) ? 999 : sportDetail.getMergedFlag().intValue()));
                j = j2;
                a2 = ikvVar;
                b = b;
            }
            return arrayList;
        }
        List<String> mergedFields = sportDetail.getMergedFields();
        ArrayList arrayList2 = new ArrayList(((int) length) * 5);
        int i2 = 0;
        while (true) {
            long j3 = i2;
            if (j3 >= length) {
                return arrayList2;
            }
            long j4 = d + (j3 * 60000);
            int i3 = i2;
            List<HiHealthData> e = e(sportBasicInfos[i2], j4, j4 + 60000, b, a2, metadata, timeZone, mergedFields);
            if (!e.isEmpty()) {
                arrayList2.addAll(e);
            }
            i2 = i3 + 1;
        }
    }

    private List<HiHealthData> e(SportDetail sportDetail, int i) throws iut {
        List<SamplePoint> samplePoints = sportDetail.getSamplePoints();
        if (samplePoints == null || samplePoints.isEmpty()) {
            LogUtil.h("Debug_SportDataSwtich", "cloudSportDataToHiHealthDatasBySamplePoint samplePoints is null or empty");
            return null;
        }
        ikv a2 = this.b.a(iuz.d(this.c), i, sportDetail.getDeviceCode().longValue());
        if (a2 == null) {
            LogUtil.b("Debug_SportDataSwtich", "switchToHiHealthDatas hiHealthContext is null");
            return null;
        }
        a2.i(1);
        String timeZone = sportDetail.getTimeZone();
        String metadata = sportDetail.getMetadata();
        long size = samplePoints.size();
        ArrayList arrayList = new ArrayList((int) size);
        for (int i2 = 0; i2 < size; i2++) {
            HiHealthData b = iup.b(samplePoints.get(i2));
            if (b != null) {
                b.setTimeZone(timeZone);
                b.setMetaData(metadata);
                ikv.b(b, a2);
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    private List<HiHealthData> e(SportBasicInfo sportBasicInfo, long j, long j2, int i, ikv ikvVar, String str, String str2, List<String> list) {
        double d;
        int i2;
        float f;
        int i3;
        ArrayList arrayList = new ArrayList(5);
        if (i > 0) {
            arrayList.add(e(j, j2, i, 0.0d, 1, ikvVar, str, str2, 0));
        }
        int fetchSteps = sportBasicInfo.fetchSteps();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        float fetchFloor = sportBasicInfo.fetchFloor() * 30;
        float fetchAltitude = sportBasicInfo.fetchAltitude() * 10.0f;
        float f2 = ((double) (fetchFloor - fetchAltitude)) > 1.0E-6d ? fetchFloor : fetchAltitude;
        int fetchDistance = sportBasicInfo.fetchDistance();
        if (HiValidatorUtil.c(fetchSteps) && HiValidatorUtil.d(fetchCalorie)) {
            double d2 = f2;
            if (HiValidatorUtil.e(d2)) {
                if (HiValidatorUtil.b(fetchSteps)) {
                    d = d2;
                    i2 = fetchDistance;
                    f = f2;
                    i3 = fetchCalorie;
                    arrayList.add(e(j, j2, 2, fetchSteps, 1, ikvVar, str, str2, d(list, MedalConstants.EVENT_STEPS)));
                } else {
                    d = d2;
                    i2 = fetchDistance;
                    f = f2;
                    i3 = fetchCalorie;
                }
                if (HiValidatorUtil.b(i3)) {
                    arrayList.add(e(j, j2, 4, i3, 1, ikvVar, str, str2, d(list, "calorie")));
                }
                if (HiValidatorUtil.b(i2)) {
                    arrayList.add(e(j, j2, 3, i2, 1, ikvVar, str, str2, d(list, "distance")));
                }
                if (HiValidatorUtil.e(f)) {
                    arrayList.add(e(j, j2, 5, d, 1, ikvVar, str, str2, d(list, "altitude")));
                }
                return arrayList;
            }
        }
        LogUtil.b("Debug_SportDataSwtich", "getSportHealth STEP or CALORIE is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
        return arrayList;
    }

    private int d(List<String> list, String str) {
        if (HiCommonUtil.d(list) || ism.e()) {
            return 999;
        }
        return (list.contains(str) || list.contains("*")) ? 0 : 1;
    }

    private HiHealthData e(long j, long j2, int i, double d, int i2, ikv ikvVar, String str, String str2, int i3) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j2);
        hiHealthData.setType(i);
        hiHealthData.setValue(d);
        hiHealthData.setMetaData(str);
        hiHealthData.setTimeZone(str2);
        hiHealthData.setMergedStatus(i3);
        hiHealthData.setSyncStatus(i2);
        ikv.b(hiHealthData, ikvVar);
        return hiHealthData;
    }
}
