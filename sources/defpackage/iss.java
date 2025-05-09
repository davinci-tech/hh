package defpackage;

import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwcloudmodel.model.unite.SportDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiValidatorUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class iss {
    private iip d;
    private iis e;

    private iss() {
        this.d = iip.b();
        this.e = iis.d();
    }

    static class e {
        private static final iss d = new iss();
    }

    public static iss c() {
        return e.d;
    }

    public List<SportDetail> b(List<HiHealthData> list, int i) {
        if (i == 1) {
            return a(list);
        }
        if (i != 3) {
            return null;
        }
        return e(list);
    }

    public List<SportDetail> d(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                List<SamplePoint> a2 = a(hiHealthData);
                SportDetail sportDetail = new SportDetail();
                sportDetail.setSamplePoints(a2);
                sportDetail.setStartTime(Long.valueOf(hiHealthData.getStartTime()));
                sportDetail.setEndTime(Long.valueOf(hiHealthData.getEndTime()));
                sportDetail.setTimeZone(hiHealthData.getTimeZone());
                sportDetail.setMetadata(hiHealthData.getMetaData());
                ikv f = this.e.f(hiHealthData.getClientId());
                if (f != null) {
                    sportDetail.setDeviceCode(Long.valueOf(f.a()));
                    HiAppInfo c = this.d.c(f.e());
                    if (c != null) {
                        sportDetail.setAppType(Integer.valueOf(ivw.b(c.getPackageName())));
                        arrayList.add(sportDetail);
                    }
                }
            }
        }
        return arrayList;
    }

    private List<SamplePoint> a(HiHealthData hiHealthData) {
        ArrayList arrayList = new ArrayList(5);
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        arrayList.add(d(startTime, endTime, "BASIC_STEP", hiHealthData.getDouble("step"), 1));
        arrayList.add(d(startTime, endTime, "BASIC_DISTANCE", hiHealthData.getDouble("calorie"), 3));
        arrayList.add(d(startTime, endTime, "BASIC_CALORIE", hiHealthData.getDouble("distance"), 2));
        arrayList.add(d(startTime, endTime, "BASIC_ALTITUDE", hiHealthData.getDouble("altitude_offset"), 4));
        arrayList.add(d(startTime, endTime, "BASIC_SESSION_TYPE", hiHealthData.getType(), 0));
        return arrayList;
    }

    private SamplePoint d(long j, long j2, String str, double d, int i) {
        SamplePoint samplePoint = new SamplePoint();
        samplePoint.setStartTime(Long.valueOf(j));
        samplePoint.setEndTime(Long.valueOf(j2));
        samplePoint.setUnit(Integer.toString(i));
        samplePoint.setValue(Double.toString(d));
        samplePoint.setKey(str);
        return samplePoint;
    }

    private List<SportDetail> a(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            SportDetail sportDetail = new SportDetail();
            sportDetail.setSportType(Integer.valueOf(iwj.e(hiHealthData.getType())));
            SportBasicInfo[] sportBasicInfoArr = new SportBasicInfo[1];
            SportBasicInfo sportBasicInfo = new SportBasicInfo();
            int i = hiHealthData.getInt("step");
            int i2 = hiHealthData.getInt("calorie");
            float f = hiHealthData.getFloat("altitude_offset");
            int i3 = hiHealthData.getInt("distance");
            if (!HiValidatorUtil.c(i) || !HiValidatorUtil.d(i2) || !HiValidatorUtil.d(f)) {
                LogUtil.b("Debug_SportDataSwtich", "localSportToCloud data is out of rang data = ", hiHealthData);
            } else {
                sportBasicInfo.configSteps(i);
                sportBasicInfo.configCalorie(i2);
                sportBasicInfo.configAltitude(f / 10.0f);
                sportBasicInfo.configDistance(i3);
                sportBasicInfo.configFloor(0);
                sportBasicInfo.configDuration(1);
                sportBasicInfoArr[0] = sportBasicInfo;
                sportDetail.setSportBasicInfos(sportBasicInfoArr);
                ArrayList arrayList2 = new ArrayList(4);
                if (i > 0 && hiHealthData.getInt("stepMerged") == 0) {
                    arrayList2.add(MedalConstants.EVENT_STEPS);
                }
                if (i2 > 0 && hiHealthData.getInt("calorieMerged") == 0) {
                    arrayList2.add("calorie");
                }
                if (f > 0.0f && hiHealthData.getInt("altitude_offsetMerged") == 0) {
                    arrayList2.add("altitude");
                }
                if (i3 > 0 && hiHealthData.getInt("distanceMerged") == 0) {
                    arrayList2.add("distance");
                }
                if (HiCommonUtil.d(arrayList2)) {
                    arrayList2.add(Constants.LINK);
                }
                sportDetail.setMergedFields(arrayList2);
                sportDetail.setStartTime(Long.valueOf(hiHealthData.getStartTime()));
                sportDetail.setEndTime(Long.valueOf(hiHealthData.getEndTime()));
                sportDetail.setTimeZone(hiHealthData.getTimeZone());
                sportDetail.setMetadata(hiHealthData.getMetaData());
                ikv f2 = this.e.f(hiHealthData.getClientId());
                if (f2 != null) {
                    sportDetail.setDeviceCode(Long.valueOf(f2.a()));
                    HiAppInfo c = this.d.c(f2.e());
                    if (c != null) {
                        sportDetail.setAppType(Integer.valueOf(ivw.b(c.getPackageName())));
                        arrayList.add(sportDetail);
                    }
                }
            }
        }
        return arrayList;
    }

    private List<SportDetail> e(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        ikv f = this.e.f(list.get(0).getClientId());
        if (f == null) {
            return null;
        }
        for (HiHealthData hiHealthData : list) {
            SportDetail sportDetail = new SportDetail();
            sportDetail.setSportType(Integer.valueOf(iwj.e(hiHealthData.getType())));
            SportBasicInfo e2 = iup.e(0, 0, 0, Float.valueOf(0.0f), 0, 1, 0);
            sportDetail.setStartTime(Long.valueOf(hiHealthData.getStartTime()));
            sportDetail.setEndTime(Long.valueOf(hiHealthData.getEndTime()));
            sportDetail.setTimeZone(hiHealthData.getTimeZone());
            sportDetail.setMetadata(hiHealthData.getMetaData());
            sportDetail.setDeviceCode(Long.valueOf(f.a()));
            sportDetail.setMergedFlag(Integer.valueOf(hiHealthData.getMergedStatus()));
            HiAppInfo c = this.d.c(f.e());
            if (c != null) {
                sportDetail.setSportBasicInfos(new SportBasicInfo[]{e2});
                sportDetail.setAppType(Integer.valueOf(ivw.b(c.getPackageName())));
                arrayList.add(sportDetail);
            }
        }
        return arrayList;
    }
}
