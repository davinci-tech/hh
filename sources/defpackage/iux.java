package defpackage;

import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.syncdata.Electrocardiogram;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBloodOxygenRemind;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBloodOxygenSaturation;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBodyThermometer;
import com.huawei.hihealthservice.sync.syncdata.HiSyncUricAcid;
import com.huawei.hihealthservice.sync.util.CloudIntensity;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.sensor.Sensor;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class iux {
    private static volatile List<String> b;
    private static volatile List<String> d;
    private static volatile List<String> e;

    private static List<HiHealthData> d(HiHealthData hiHealthData) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(hiHealthData);
        return arrayList;
    }

    private static List<HiHealthData> e(SamplePoint samplePoint) {
        HiHealthData hiHealthData = new HiHealthData();
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint so such type");
            return null;
        }
        hiHealthData.setType(a2);
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(0);
        try {
            if (Double.parseDouble(samplePoint.getValue()) <= 1.401298464324817E-45d) {
                LogUtil.b("HiDataSwitchUtil", "switchHealthSamplePoint value less than MIN_VALUE");
                return null;
            }
            hiHealthData.setValue(Double.parseDouble(samplePoint.getValue()));
            return d(hiHealthData);
        } catch (NumberFormatException e2) {
            ReleaseLogUtil.c("HiH_HiDataSwitchUtil", "switchHealthSamplePoint NumberFormatException", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static List<HiHealthData> a(SamplePoint samplePoint) {
        HiHealthData hiHealthData = new HiHealthData();
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint so such type");
            return null;
        }
        String value = samplePoint.getValue();
        if (value == null || value.isEmpty()) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint type is ,", Integer.valueOf(a2), " metaData is null");
            return null;
        }
        if (a2 == 2034) {
            HiStressMetaData a3 = a(samplePoint.getValue());
            if (a3 == null || a3.fetchStressScore() <= 0) {
                return null;
            }
            hiHealthData.setValue(a3.fetchStressScore());
        }
        hiHealthData.setType(a2);
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(0);
        hiHealthData.setMetaData(samplePoint.getValue());
        return d(hiHealthData);
    }

    private static List<HiHealthData> b(SamplePoint samplePoint, HealthDetail healthDetail) {
        HiHealthData hiHealthData = new HiHealthData();
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint so such type");
            return null;
        }
        CloudIntensity c = iup.c(samplePoint.getValue());
        if (c == null) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint type is ,", Integer.valueOf(a2), " value is error");
            return null;
        }
        hiHealthData.setValue(c.a());
        hiHealthData.setType(a2);
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(0);
        hiHealthData.setMergedStatus((healthDetail.getMergedFlag() == null || ism.e()) ? 999 : healthDetail.getMergedFlag().intValue());
        return d(hiHealthData);
    }

    private static List<HiHealthData> b(SamplePoint samplePoint) {
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "samplePointToBodyThermometer so such type");
            return null;
        }
        HiSyncBodyThermometer e2 = iup.e(samplePoint.getValue());
        if (e2 == null) {
            LogUtil.h("HiDataSwitchUtil", "samplePointToBodyThermometer type is ,", Integer.valueOf(a2), " value is error");
            return null;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setValue(e2.getBodyThermometer());
        hiHealthData.setType(a2);
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(0);
        return d(hiHealthData);
    }

    private static List<HiHealthData> c(SamplePoint samplePoint, HealthDetail healthDetail) {
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "samplePointToBloodOxygenRemind so such type");
            return null;
        }
        HiSyncBloodOxygenRemind a3 = iup.a(samplePoint.getValue());
        if (a3 == null) {
            LogUtil.h("HiDataSwitchUtil", "samplePointToBloodOxygenRemind type is ,", Integer.valueOf(a2), " value is error");
            return null;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setValue(a3.getBloodOxygenRemind());
        hiHealthData.setType(a2);
        hiHealthData.setMetaData(healthDetail.getMetadata());
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(0);
        return d(hiHealthData);
    }

    private static List<HiHealthData> c(SamplePoint samplePoint) {
        HiHealthData hiHealthData = new HiHealthData();
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint so such type");
            return null;
        }
        HiSyncBloodOxygenSaturation b2 = iup.b(samplePoint.getValue());
        if (b2 == null) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint type is ,", Integer.valueOf(a2), " value is error");
            return null;
        }
        hiHealthData.setValue(b2.getAvgSaturation());
        hiHealthData.setType(a2);
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(0);
        return d(hiHealthData);
    }

    private static List<HiHealthData> d(SamplePoint samplePoint) {
        HiHealthData hiHealthData = new HiHealthData();
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint so such type");
            return null;
        }
        Electrocardiogram d2 = iup.d(samplePoint.getValue());
        if (d2 == null) {
            LogUtil.h("HiDataSwitchUtil", "switchHealthSamplePoint type is ,", Integer.valueOf(a2), " electrocardiogram is error");
            return null;
        }
        hiHealthData.setMetaData(d2.getMetadata());
        hiHealthData.setSequenceData(d2.getDetails());
        hiHealthData.setType(a2);
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(18);
        List<Float> ecgDataList = d2.getEcgDataList();
        if (koq.c(ecgDataList)) {
            hiHealthData.setSimpleData(ecgDataList.toString());
        }
        return d(hiHealthData);
    }

    private static HiStressMetaData a(String str) {
        try {
            return (HiStressMetaData) HiJsonUtil.e(str, HiStressMetaData.class);
        } catch (JsonSyntaxException e2) {
            ReleaseLogUtil.d("HiH_HiDataSwitchUtil", "transStress error !", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static List<HiHealthData> h(SamplePoint samplePoint) {
        HiHealthData hiHealthData = new HiHealthData();
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "samplePointToUricAcid no such type");
            return null;
        }
        HiSyncUricAcid i = iup.i(samplePoint.getValue());
        if (i == null) {
            LogUtil.h("HiDataSwitchUtil", "samplePointToUricAcid type is ", Integer.valueOf(a2), ", value is error");
            return null;
        }
        hiHealthData.setValue(i.getUricAcid());
        hiHealthData.setType(a2);
        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
        hiHealthData.setPointUnit(0);
        return d(hiHealthData);
    }

    private static List<HiHealthData> a(SamplePoint samplePoint, HealthDetail healthDetail) {
        if (samplePoint != null && healthDetail != null) {
            Map<String, Double> c = new iur(samplePoint.getValue()).c();
            if (c == null || c.isEmpty()) {
                LogUtil.h("HiDataSwitchUtil", "samplePointToBloodSugar error map is null or empty");
            } else {
                ArrayList arrayList = new ArrayList(c.size());
                HiHealthData hiHealthData = new HiHealthData();
                Iterator<Map.Entry<String, Double>> it = c.entrySet().iterator();
                while (true) {
                    double d2 = 0.0d;
                    if (it.hasNext()) {
                        Map.Entry<String, Double> next = it.next();
                        int d3 = iuq.d(next.getKey());
                        double doubleValue = next.getValue().doubleValue();
                        LogUtil.c("HiDataSwitchUtil", "samplePointToBloodSugar type = " + d3);
                        if (doubleValue <= 0.0d) {
                            LogUtil.b("HiDataSwitchUtil", "samplePointToBloodSugar error value !");
                            return null;
                        }
                        hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                        hiHealthData.setType(d3);
                        hiHealthData.setPointUnit(6);
                        hiHealthData.setValue(doubleValue);
                        hiHealthData.setMetaData(healthDetail.getMetadata());
                    } else {
                        Map<String, Double> c2 = new iur(samplePoint.getFieldsModifyTime()).c();
                        if (c2 != null && !c2.isEmpty()) {
                            for (Map.Entry<String, Double> entry : c2.entrySet()) {
                                if (entry.getValue().doubleValue() > d2) {
                                    d2 = entry.getValue().doubleValue();
                                }
                            }
                        }
                        hiHealthData.setModifiedTime((long) d2);
                        arrayList.add(hiHealthData);
                        return arrayList;
                    }
                }
            }
        }
        return null;
    }

    private static List<HiHealthData> d(SamplePoint samplePoint, HealthDetail healthDetail) {
        int a2 = iuq.a(samplePoint.getKey(), samplePoint.getValue());
        if (a2 <= 0) {
            LogUtil.h("HiDataSwitchUtil", "samplePointToSleep so such type");
            return null;
        }
        long d2 = HiDateUtil.d(samplePoint.getStartTime().longValue());
        long longValue = samplePoint.getEndTime().longValue();
        ArrayList arrayList = new ArrayList(Math.max((int) Math.ceil((longValue - d2) / 60000.0f), 0));
        while (d2 < longValue) {
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setType(a2);
            hiHealthData.setPointUnit(15);
            hiHealthData.setMergedStatus((healthDetail.getMergedFlag() == null || ism.e()) ? 999 : healthDetail.getMergedFlag().intValue());
            long j = 60000 + d2;
            hiHealthData.setTimeInterval(d2, j);
            arrayList.add(hiHealthData);
            d2 = j;
        }
        return arrayList;
    }

    static List<HiHealthData> e(SamplePoint samplePoint, HealthDetail healthDetail) {
        String key;
        if (samplePoint == null || healthDetail == null || (key = samplePoint.getKey()) == null) {
            return null;
        }
        List<String> c = c();
        List<String> b2 = b();
        List<String> d2 = d();
        if (c.contains(key)) {
            return e(samplePoint);
        }
        if (b2.contains(key)) {
            return d(samplePoint, healthDetail);
        }
        if ("BLOODGLUCOSE_BLOODSUGAR".equals(key)) {
            return a(samplePoint, healthDetail);
        }
        if (d2.contains(key)) {
            return a(samplePoint);
        }
        if ("EXERCISE_INTENSITY".equals(key)) {
            return b(samplePoint, healthDetail);
        }
        if ("BLOOD_OXYGEN_SATURATION".equals(key)) {
            return c(samplePoint);
        }
        if (Sensor.NAME_ECG.equals(key)) {
            return d(samplePoint);
        }
        if ("BODY_THERMOMETER".equals(key)) {
            return b(samplePoint);
        }
        if ("BLOOD_OXYGEN_REMIND".equals(key)) {
            return c(samplePoint, healthDetail);
        }
        if ("URIC_ACID".equals(key)) {
            return h(samplePoint);
        }
        return null;
    }

    private static List<String> d() {
        if (e == null) {
            e = Arrays.asList("STRESS_DATA", "BIOFEEDBACK_GAMES", "RELAX_ASSESSMENT", "BREATHE_TRAINING", "HEART_RATE_RISE_ALARM", "BRADYCARDIA_ALARM");
        }
        return e;
    }

    private static List<String> b() {
        if (d == null) {
            d = Arrays.asList("SLEEP_DEEP", "SLEEP_LIGHT", "SLEEP_AWAKE", "PROFESSIONAL_SLEEP_SHALLOW", "PROFESSIONAL_SLEEP_DREAM", "PROFESSIONAL_SLEEP_DEEP", "PROFESSIONAL_SLEEP_WAKE", "PROFESSIONAL_SLEEP_NOON", "PROFESSIONAL_SLEEP_BED", "PROFESSIONAL_SLEEP_ON");
        }
        return d;
    }

    private static List<String> c() {
        if (b == null) {
            b = Arrays.asList("DATA_POINT_DYNAMIC_HEARTRATE", "DATA_POINT_REST_HEARTRATE", "DATA_POINT_NEW_REST_HEARTRATE", "STRESS_PASSIVE_MEASUREMENT", "STRESS_POSITIVE_MEASUREMENT", "BREATHING_RELAXATION");
        }
        return b;
    }
}
