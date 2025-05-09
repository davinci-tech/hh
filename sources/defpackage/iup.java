package defpackage;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.location.LocationRequestCompat;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.EcgMetaData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.syncdata.Electrocardiogram;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBloodOxygenRemind;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBloodOxygenSaturation;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBodyThermometer;
import com.huawei.hihealthservice.sync.syncdata.HiSyncUricAcid;
import com.huawei.hihealthservice.sync.util.CloudIntensity;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class iup {
    public static HiHealthData b(SamplePoint samplePoint) {
        if (samplePoint == null) {
            return null;
        }
        int a2 = a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "switchSportSamplePoint so such type");
            return null;
        }
        HiHealthData hiHealthData = new HiHealthData();
        try {
            hiHealthData.setValue(Double.parseDouble(samplePoint.getValue()));
            hiHealthData.setType(a2);
            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
            hiHealthData.setPointUnit(0);
            LogUtil.c("HiDataSwitchUtil", "switchSportSamplePoint hiHealthData is ", hiHealthData);
            return hiHealthData;
        } catch (NumberFormatException e) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "switchSportSamplePoint NumberFormatException", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    private static int a(String str, String str2) {
        return iuq.a(str, str2);
    }

    public static List<HiHealthData> d(SamplePoint samplePoint, HealthDetail healthDetail) {
        if (samplePoint == null || healthDetail == null) {
            return null;
        }
        return iux.e(samplePoint, healthDetail);
    }

    public static HealthDetail a(HiHealthData hiHealthData, int i) {
        SamplePoint j;
        if (hiHealthData == null || i <= -1) {
            return null;
        }
        HealthDetail healthDetail = new HealthDetail();
        ArrayList arrayList = new ArrayList(10);
        if (i == 4) {
            j = j(hiHealthData);
            healthDetail.setMetadata(hiHealthData.getMetaData());
        } else if (i == 19) {
            j = i(hiHealthData);
            healthDetail.setMetadata(hiHealthData.getMetaData());
        } else {
            j = c(hiHealthData, i);
        }
        if (j == null) {
            return null;
        }
        arrayList.add(j);
        healthDetail.setStartTime(Long.valueOf(hiHealthData.getStartTime()));
        healthDetail.setEndTime(Long.valueOf(hiHealthData.getEndTime()));
        healthDetail.setTimeZone(hiHealthData.getTimeZone());
        healthDetail.setType(Integer.valueOf(i));
        healthDetail.setSamplePoints(arrayList);
        return healthDetail;
    }

    public static void a(List<HealthDetail> list, List<HiHealthData> list2, int i, long j) {
        if (list == null || list2 == null || list2.size() <= 0) {
            return;
        }
        HealthDetail healthDetail = new HealthDetail();
        ArrayList arrayList = new ArrayList(list2.size());
        healthDetail.setType(Integer.valueOf(i));
        healthDetail.setTimeZone(list2.get(0).getTimeZone());
        healthDetail.setDeviceCode(Long.valueOf(j));
        int c = HiDateUtil.c(list2.get(0).getStartTime());
        HealthDetail healthDetail2 = healthDetail;
        ArrayList arrayList2 = arrayList;
        int i2 = c;
        long j2 = Long.MIN_VALUE;
        long j3 = LocationRequestCompat.PASSIVE_INTERVAL;
        for (HiHealthData hiHealthData : list2) {
            long startTime = hiHealthData.getStartTime();
            long endTime = hiHealthData.getEndTime();
            int c2 = HiDateUtil.c(startTime);
            SamplePoint c3 = c(hiHealthData, i);
            if (c3 != null) {
                if (i == 12) {
                    healthDetail2.setMergedFlag(Integer.valueOf(hiHealthData.getMergedStatus()));
                }
                if (i2 != c2) {
                    d(list, j2, j3, healthDetail2, arrayList2);
                    healthDetail2 = new HealthDetail();
                    arrayList2 = new ArrayList(list2.size());
                    healthDetail2.setType(Integer.valueOf(i));
                    healthDetail2.setTimeZone(list2.get(0).getTimeZone());
                    healthDetail2.setDeviceCode(Long.valueOf(j));
                    if (i == 12) {
                        healthDetail2.setMergedFlag(Integer.valueOf(hiHealthData.getMergedStatus()));
                    }
                    j2 = Long.MIN_VALUE;
                    j3 = LocationRequestCompat.PASSIVE_INTERVAL;
                }
                arrayList2.add(c3);
                if (endTime >= j2) {
                    j2 = endTime;
                }
                if (startTime <= j3) {
                    j3 = startTime;
                }
                i2 = HiDateUtil.c(hiHealthData.getStartTime());
            }
        }
        if (arrayList2.size() > 0) {
            d(list, j2, j3, healthDetail2, arrayList2);
        }
        LogUtil.c("HiDataSwitchUtil", "integrateData data finished");
    }

    public static void e(List<HealthDetail> list, List<HiHealthData> list2, int i, long j) {
        if (list == null || list2 == null) {
            return;
        }
        for (HiHealthData hiHealthData : list2) {
            HealthDetail healthDetail = new HealthDetail();
            healthDetail.setType(Integer.valueOf(i));
            healthDetail.setTimeZone(hiHealthData.getTimeZone());
            healthDetail.setDeviceCode(Long.valueOf(j));
            healthDetail.setStartTime(Long.valueOf(hiHealthData.getStartTime()));
            healthDetail.setEndTime(Long.valueOf(hiHealthData.getEndTime()));
            SamplePoint c = c(hiHealthData, i);
            if (c != null) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(c);
                healthDetail.setSamplePoints(arrayList);
                list.add(healthDetail);
            }
        }
        LogUtil.c("HiDataSwitchUtil", "integrateOneSamplePointData data finished");
    }

    public static List<HealthDetail> d(List<HiHealthData> list, int i, long j) {
        SamplePoint c;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && (c = c(hiHealthData, i)) != null) {
                ArrayList arrayList2 = new ArrayList(1);
                arrayList2.add(c);
                HealthDetail healthDetail = new HealthDetail();
                healthDetail.setType(Integer.valueOf(i));
                healthDetail.setTimeZone(hiHealthData.getTimeZone());
                healthDetail.setDeviceCode(Long.valueOf(j));
                if (i == 9) {
                    healthDetail.setMergedFlag(Integer.valueOf(hiHealthData.getMergedStatus()));
                }
                d(arrayList, hiHealthData.getEndTime(), hiHealthData.getStartTime(), healthDetail, arrayList2);
            }
        }
        return arrayList;
    }

    private static void d(List<HealthDetail> list, long j, long j2, HealthDetail healthDetail, List<SamplePoint> list2) {
        if (list == null) {
            return;
        }
        healthDetail.setStartTime(Long.valueOf(j2));
        healthDetail.setEndTime(Long.valueOf(j));
        healthDetail.setSamplePoints(list2);
        list.add(healthDetail);
    }

    private static SamplePoint c(HiHealthData hiHealthData, int i) {
        String str;
        if (hiHealthData == null) {
            return null;
        }
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        String j = j(hiHealthData.getType());
        if (j == null) {
            return null;
        }
        if (i == 3 || i == 9) {
            str = "";
        } else if (i == 18) {
            str = b(hiHealthData);
        } else if (i != 21) {
            switch (i) {
                case 11:
                case 13:
                case 14:
                    str = hiHealthData.getMetaData();
                    break;
                case 12:
                    str = e((int) hiHealthData.getValue());
                    break;
                case 15:
                    str = e(hiHealthData);
                    break;
                case 16:
                    str = d(hiHealthData.getValue());
                    break;
                default:
                    str = Double.toString(hiHealthData.getValue());
                    break;
            }
        } else {
            str = c(hiHealthData);
        }
        return d(j, str, startTime, endTime, Integer.toString(hiHealthData.getPointUnit()));
    }

    private static SamplePoint j(HiHealthData hiHealthData) {
        String j = j(hiHealthData.getType());
        if (j == null) {
            return null;
        }
        String c = c(hiHealthData.getType());
        iur iurVar = new iur();
        iurVar.b(c, hiHealthData.getValue());
        String b = iurVar.b();
        if (b == null) {
            return null;
        }
        iur iurVar2 = new iur();
        iurVar2.b(c, hiHealthData.getModifiedTime());
        iurVar2.b(BleConstants.IS_CONFIRMED_DB, hiHealthData.getModifiedTime());
        return c(j, b, hiHealthData.getStartTime(), hiHealthData.getEndTime(), Integer.toString(hiHealthData.getPointUnit()), iurVar2.b());
    }

    private static SamplePoint i(HiHealthData hiHealthData) {
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        String j = j(hiHealthData.getType());
        if (j == null) {
            return null;
        }
        return d(j, d(hiHealthData), startTime, endTime, Integer.toString(hiHealthData.getPointUnit()));
    }

    private static String j(int i) {
        return iuq.b(i);
    }

    public static SamplePoint d(String str, String str2, long j, long j2, String str3) {
        SamplePoint samplePoint = new SamplePoint();
        samplePoint.setKey(str);
        samplePoint.setValue(str2);
        samplePoint.setStartTime(Long.valueOf(j));
        samplePoint.setEndTime(Long.valueOf(j2));
        samplePoint.setUnit(str3);
        return samplePoint;
    }

    public static SamplePoint c(String str, String str2, long j, long j2, String str3, String str4) {
        SamplePoint samplePoint = new SamplePoint();
        samplePoint.setKey(str);
        samplePoint.setValue(str2);
        samplePoint.setStartTime(Long.valueOf(j));
        samplePoint.setEndTime(Long.valueOf(j2));
        samplePoint.setUnit(str3);
        samplePoint.setFieldsModifyTime(str4);
        return samplePoint;
    }

    public static SportBasicInfo e(Integer num, Integer num2, Integer num3, Float f, Integer num4, Integer num5, Integer num6) {
        SportBasicInfo sportBasicInfo = new SportBasicInfo();
        sportBasicInfo.configAltitude(f.floatValue());
        sportBasicInfo.configCalorie(num3.intValue());
        sportBasicInfo.configDistance(num2.intValue());
        sportBasicInfo.configDuration(num5.intValue());
        sportBasicInfo.configFloor(num4.intValue());
        sportBasicInfo.configSteps(num.intValue());
        sportBasicInfo.configCount(num6.intValue());
        return sportBasicInfo;
    }

    public static igo d(int i, double d, int i2) {
        igo igoVar = new igo();
        igoVar.a(d);
        igoVar.d(i);
        igoVar.h(i2);
        return igoVar;
    }

    public static int b(int i) {
        int d = iuq.d(i);
        return (d != -1 || HiHealthDictManager.d((Context) null).d(i) == null) ? d : i;
    }

    public static int d(int i) {
        int c = iuq.c(i);
        return (c != -1 || HiHealthDictManager.d((Context) null).d(i) == null) ? c : i;
    }

    public static int a(int i) {
        return iuq.a(i);
    }

    public static String c(int i) {
        return iuq.e(i);
    }

    public static boolean g(int i) {
        return iuq.j(i);
    }

    public static String e(int i) {
        return HiJsonUtil.e(new CloudIntensity(i));
    }

    public static String d(double d) {
        return HiJsonUtil.e(new HiSyncBloodOxygenSaturation(d));
    }

    public static String e(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return "";
        }
        Electrocardiogram electrocardiogram = new Electrocardiogram();
        EcgMetaData ecgMetaData = (EcgMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), EcgMetaData.class);
        if (ecgMetaData == null) {
            LogUtil.h("HiDataSwitchUtil", "getCloudEcgStr metadata null");
            return "";
        }
        electrocardiogram.setAverageHeartRate(ecgMetaData.getAverageHeartRate());
        electrocardiogram.setEcgArrhyType(ecgMetaData.getEcgArrhyType());
        electrocardiogram.setEcgDataLength(ecgMetaData.getEcgDataLength());
        electrocardiogram.setUserSymptom(ecgMetaData.getUserSymptom());
        electrocardiogram.setDetails(hiHealthData.getSequenceData());
        List<Float> a2 = a(hiHealthData);
        if (koq.c(a2)) {
            electrocardiogram.setEcgDataList(a2);
        }
        electrocardiogram.setMetadata(HiJsonUtil.e(ecgMetaData));
        return HiJsonUtil.e(electrocardiogram);
    }

    private static List a(HiHealthData hiHealthData) {
        String simpleData = hiHealthData.getSimpleData();
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(simpleData)) {
            return arrayList;
        }
        try {
            Float[] fArr = (Float[]) HiJsonUtil.e(simpleData, Float[].class);
            return fArr != null ? Arrays.asList(fArr) : arrayList;
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.d("HiH_HiDataSwitchUtil", "simpleData parse fail", LogAnonymous.b((Throwable) e));
            return arrayList;
        }
    }

    public static String b(HiHealthData hiHealthData) {
        return hiHealthData == null ? "" : HiJsonUtil.e(new HiSyncBodyThermometer(hiHealthData.getValue()));
    }

    public static String d(HiHealthData hiHealthData) {
        return hiHealthData == null ? "" : HiJsonUtil.e(new HiSyncBloodOxygenRemind(hiHealthData.getIntValue()));
    }

    public static String c(HiHealthData hiHealthData) {
        return hiHealthData == null ? "" : HiJsonUtil.e(new HiSyncUricAcid(hiHealthData.getIntValue()));
    }

    public static CloudIntensity c(String str) {
        try {
            return (CloudIntensity) HiJsonUtil.e(str, CloudIntensity.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getCloudIntensityValue JsonSyntaxException", LogAnonymous.b((Throwable) e));
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getCloudIntensityValue Exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public static HiSyncBloodOxygenSaturation b(String str) {
        try {
            return (HiSyncBloodOxygenSaturation) HiJsonUtil.e(str, HiSyncBloodOxygenSaturation.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncBloodOxygenValue JsonSyntaxException", LogAnonymous.b((Throwable) e));
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncBloodOxygenValue Exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public static HiSyncBodyThermometer e(String str) {
        try {
            return (HiSyncBodyThermometer) HiJsonUtil.e(str, HiSyncBodyThermometer.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncBodyThermometer JsonSyntaxException", LogAnonymous.b((Throwable) e));
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncBodyThermometer Exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public static HiSyncBloodOxygenRemind a(String str) {
        try {
            return (HiSyncBloodOxygenRemind) HiJsonUtil.e(str, HiSyncBloodOxygenRemind.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncBloodOxygenRemind JsonSyntaxException", LogAnonymous.b((Throwable) e));
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncBloodOxygenRemind Exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public static Electrocardiogram d(String str) {
        try {
            return (Electrocardiogram) HiJsonUtil.e(str, Electrocardiogram.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncEcgValue JsonSyntaxException", LogAnonymous.b((Throwable) e));
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncEcgValue Exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public static HiSyncUricAcid i(String str) {
        try {
            return (HiSyncUricAcid) HiJsonUtil.e(str, HiSyncUricAcid.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncUricAcid JsonSyntaxException", LogAnonymous.b((Throwable) e));
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "getSyncUricAcid Exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }
}
