package com.huawei.hihealthservice.hihealthkit.util;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.DataReportModel;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.TrendQuery;
import com.huawei.hihealth.data.model.EcgMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Goal;
import com.huawei.hihealth.model.GoalInfo;
import com.huawei.hihealth.model.HealthAlarmInfo;
import com.huawei.hihealth.model.Recurrence;
import com.huawei.hihealth.model.SportStatusInfo;
import com.huawei.hihealth.model.SubscribeModel;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.hihealthkit.util.HiHealthKitDataChecker;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.fhw;
import defpackage.ima;
import defpackage.ipa;
import defpackage.ipw;
import defpackage.koq;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HiHealthKitDataChecker {
    private static final List<String> d;
    private static final List<String> e;

    /* loaded from: classes7.dex */
    public enum MethodType {
        SAVE_SAMPLES,
        DELETE_SAMPLES
    }

    private static boolean a(double d2) {
        return d2 < 0.0d;
    }

    static {
        ArrayList arrayList = new ArrayList();
        e = arrayList;
        ArrayList arrayList2 = new ArrayList();
        d = arrayList2;
        arrayList.add("hasTrackPoint");
        arrayList2.add("true");
        arrayList2.add("false");
    }

    public static boolean a(List list, MethodType methodType, boolean z) {
        if (list == null || list.isEmpty()) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "hiHealthKitDataList is empty");
            return false;
        }
        HashSet hashSet = new HashSet();
        int i = -1;
        for (Object obj : list) {
            if (!(obj instanceof HiHealthKitData)) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkerDataListValid hiHealthKitDataList has error dataType");
                return false;
            }
            HiHealthKitData hiHealthKitData = (HiHealthKitData) obj;
            if (i == -1) {
                i = hiHealthKitData.getType();
            }
            if (z && hiHealthKitData.getType() != i) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkerDataListValid multiple types are not allowed");
                return false;
            }
            hashSet.add(Boolean.valueOf(hiHealthKitData.getBoolean("insertRealTime")));
            if (hashSet.size() > 1) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkerDataListValid real-time data and non-real-time data cannot be inserted together");
                return false;
            }
            if (i == 50001 && list.size() > 1) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkerDataListValid Only one piece of real-time heart rate data can be inserted at a time");
                return false;
            }
            if (!b(hiHealthKitData, methodType)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: com.huawei.hihealthservice.hihealthkit.util.HiHealthKitDataChecker$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[MethodType.values().length];
            e = iArr;
            try {
                iArr[MethodType.SAVE_SAMPLES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[MethodType.DELETE_SAMPLES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static boolean b(HiHealthKitData hiHealthKitData, MethodType methodType) {
        int i = AnonymousClass3.e[methodType.ordinal()];
        if (i == 1) {
            return j(hiHealthKitData);
        }
        if (i != 2) {
            return false;
        }
        return d(hiHealthKitData);
    }

    private static boolean j(HiHealthKitData hiHealthKitData) {
        if (c(hiHealthKitData) || a(hiHealthKitData)) {
            return false;
        }
        int type = hiHealthKitData.getType();
        if ((HiHealthDictManager.d(BaseApplication.getContext()).l(type) && (hiHealthKitData.getMap() == null || hiHealthKitData.getMap().isEmpty())) || HiHealthDataType.e(type) == HiHealthDataType.Category.POINT || HiHealthDataType.e(type) == HiHealthDataType.Category.SESSION || HiHealthDataType.e(type) == HiHealthDataType.Category.REALTIME || HiHealthDataType.e(type) == HiHealthDataType.Category.SEQUENCE) {
            return true;
        }
        Map map = hiHealthKitData.getMap();
        if (map == null || map.isEmpty()) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "saveSampleCheckValid map is null");
            return false;
        }
        if (!e(hiHealthKitData, type)) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "saveSampleCheckValid time error");
            return false;
        }
        if (!b(map, type)) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "saveSampleCheckValid map error");
            return false;
        }
        return c(map, type);
    }

    private static boolean d(HiHealthKitData hiHealthKitData) {
        if (c(hiHealthKitData)) {
            return false;
        }
        return !b(hiHealthKitData);
    }

    private static boolean e(HiHealthKitData hiHealthKitData, int i) {
        switch (i) {
            case 10002:
            case 10006:
            case 10010:
            case 10062:
            case 61001:
            case 61002:
            case 500010:
                if (hiHealthKitData.getStartTime() == hiHealthKitData.getEndTime()) {
                }
                break;
        }
        return true;
    }

    private static boolean c(Map map, int i) {
        if (c(map)) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "map contains null");
            return false;
        }
        if (i == DicDataTypeUtil.DataType.OSA_SET.value()) {
            return d(map);
        }
        if (i == 10001 || i == 10002 || i == 10006) {
            return a(map);
        }
        if (i != 10011 && i != 10065) {
            if (i == 30029) {
                return b(map);
            }
            if (i == 31001) {
                return e(map);
            }
            switch (i) {
                case 10067:
                case 10068:
                case 10069:
                    break;
                default:
                    return true;
            }
        }
        return map.get(Integer.valueOf(i)) instanceof String;
    }

    private static boolean d(Map map) {
        Object obj;
        if (map.containsKey(Integer.valueOf(DicDataTypeUtil.DataType.OSA_LEVEL.value()))) {
            try {
                double doubleValue = Double.valueOf(String.valueOf(map.get(Integer.valueOf(DicDataTypeUtil.DataType.OSA_LEVEL.value())))).doubleValue();
                int intValue = Double.valueOf(doubleValue).intValue();
                if (doubleValue - intValue != 0.0d) {
                    ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkOsaValue osaLevel is wrong double");
                    return false;
                }
                if (intValue < 1 || intValue > 4) {
                    ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkOsaValue osaLevel error");
                    return false;
                }
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkOsaValue osaLevel parse error", LogAnonymous.b((Throwable) e2));
                return false;
            }
        }
        if (map.containsKey(Integer.valueOf(DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value())) && ((obj = map.get(Integer.valueOf(DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value()))) == null || TextUtils.isEmpty(obj.toString()))) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkOsaValue osaDetailHalfHour is empty");
            return false;
        }
        if (map.containsKey(Integer.valueOf(DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()))) {
            Object obj2 = map.get(Integer.valueOf(DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()));
            if (obj2 instanceof Double) {
                double doubleValue2 = ((Double) obj2).doubleValue();
                if (doubleValue2 < 0.0d || doubleValue2 > 1000.0d) {
                    ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkOsaValue osaAvgCntPerHour error");
                }
            }
            return false;
        }
        return true;
    }

    private static boolean c(Map map) {
        return map.containsValue(null) || map.containsValue("");
    }

    private static boolean a(Map map) {
        try {
            for (Object obj : map.entrySet()) {
                if (!(obj instanceof Map.Entry) || a(((Double) ((Map.Entry) obj).getValue()).doubleValue())) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e2) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkValueValid ClassCastException: ", e2.getMessage());
            return false;
        }
    }

    private static <T> boolean d(Map map, T t) {
        return map.get(t) instanceof String;
    }

    private static <T> boolean e(Map map, T t) {
        return map.get(t) instanceof Integer;
    }

    private static <T> boolean b(Map map, T t) {
        return map.get(t) instanceof Long;
    }

    private static boolean e(Map map) {
        if (!d(map, "detail_data") || !d(map, "simple_data") || !d(map, "meta_data")) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "ecg data is invalid");
            return false;
        }
        String c = ima.c(map, "detail_data");
        String c2 = ima.c(map, "simple_data");
        String c3 = ima.c(map, "meta_data");
        try {
            List list = (List) HiJsonUtil.b(c, new TypeToken<List<Float>>() { // from class: com.huawei.hihealthservice.hihealthkit.util.HiHealthKitDataChecker.1
            }.getType());
            List list2 = (List) HiJsonUtil.b(c2, new TypeToken<List<Float>>() { // from class: com.huawei.hihealthservice.hihealthkit.util.HiHealthKitDataChecker.5
            }.getType());
            EcgMetaData ecgMetaData = (EcgMetaData) HiJsonUtil.e(c3, EcgMetaData.class);
            if (list != null && list2 != null && ecgMetaData != null) {
                if (ecgMetaData.getAverageHeartRate() >= 0 && ecgMetaData.getEcgDataLength() >= 0) {
                    if (ecgMetaData.getEcgArrhyType() >= 0 && ecgMetaData.getEcgArrhyType() <= 127) {
                        if (ecgMetaData.getUserSymptom() >= 0 && ecgMetaData.getUserSymptom() <= 1023) {
                            return true;
                        }
                        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "ecg UserSymptom < 0 or UserSymptom > 1023");
                        return false;
                    }
                    ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "ecg EcgArrhyType < 0 or EcgArrhyType > 127");
                    return false;
                }
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "ecg AverageHeartRate < 0 or EcgDataLength < 0");
                return false;
            }
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "ecg detailData, simpleData or metaData is empty");
            return false;
        } catch (JsonSyntaxException e2) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkEcgValueValid JsonSyntaxException: ", e2.getMessage());
            return false;
        }
    }

    private static boolean b(Map map) {
        if (!e(map, BleConstants.SPORT_TYPE) || !b(map, "totalTime") || !e(map, BleConstants.TOTAL_CALORIES)) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "RopeSkip sportType, totalTime or totalCalories is invalid");
            return false;
        }
        if (!d(map, "skipNum") || !d(map, "skipSpeed")) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "RopeSkip skipNum or skipSpeed is invalid");
            return false;
        }
        try {
            if (ima.a(map, BleConstants.SPORT_TYPE) != 283) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "RopeSkip sportType is invalid");
                return false;
            }
            long e2 = ima.e(map, "totalTime");
            int a2 = ima.a(map, BleConstants.TOTAL_CALORIES);
            String c = ima.c(map, "skipNum");
            String c2 = ima.c(map, "skipSpeed");
            if (e2 >= 0 && a2 >= 0 && Long.parseLong(c) >= 0 && Long.parseLong(c2) >= 0) {
                return true;
            }
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "RopeSkip totalTime, totalCalories, skipNum or skipSpeed < 0");
            return false;
        } catch (NumberFormatException e3) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkRopeSkipValueValid NumberFormatException: ", e3.getMessage());
            return false;
        }
    }

    private static boolean b(Map map, int i) {
        if (i == DicDataTypeUtil.DataType.OSA_SET.value()) {
            return b(map, b(i));
        }
        if (i == 10001) {
            return b(map, (List) ipa.c);
        }
        if (i == 10002) {
            return b(map, (List) ipa.e);
        }
        if (i == 10006) {
            return b(map, (List) ipa.f);
        }
        if (i != 10062) {
            if (i == 10065) {
                return map.containsKey(10065);
            }
            if (i == 10067) {
                return map.containsKey(10067);
            }
            if (i == 30029) {
                return b(map, (List) ipa.f13526a);
            }
            if (i == 31001) {
                return b(map, (List) ipa.d);
            }
            if (i == 10010) {
                return b(map, b(i));
            }
            if (i == 10011) {
                return map.containsKey(10011);
            }
            switch (i) {
                case 61001:
                case 61002:
                    break;
                default:
                    return true;
            }
        }
        return b(map, (List) ipa.b);
    }

    private static List b(int i) {
        int[] d2 = HiHealthDataType.d(i);
        ArrayList arrayList = new ArrayList();
        for (int i2 : d2) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    private static boolean b(Map map, List list) {
        return list.containsAll(map.keySet());
    }

    private static boolean c(HiHealthKitData hiHealthKitData) {
        if (hiHealthKitData == null) {
            return true;
        }
        return i(hiHealthKitData);
    }

    private static boolean i(HiHealthKitData hiHealthKitData) {
        boolean z = true;
        if (hiHealthKitData.getStartTime() < 0 || hiHealthKitData.getEndTime() < 0) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "isWrongTimeRange startTime or endTime < 0");
            return true;
        }
        if (hiHealthKitData.getType() == 10010) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (hiHealthKitData.getStartTime() <= currentTimeMillis && hiHealthKitData.getEndTime() <= currentTimeMillis) {
            z = false;
        }
        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "isWrongTimeRange startTime or endTime > currentTime");
        return z;
    }

    private static boolean b(HiHealthKitData hiHealthKitData) {
        return hiHealthKitData.getStartTime() > hiHealthKitData.getEndTime();
    }

    public static boolean a(long j, long j2) {
        if (j > j2) {
            ReleaseLogUtil.d("HiH_HiHealthKitDataChecker", "startTime > endTime");
        }
        return j > j2;
    }

    public static boolean e(HealthKitDictQuery healthKitDictQuery) {
        ArrayList<String> filterKeys = healthKitDictQuery.getFilterKeys();
        int size = filterKeys == null ? 0 : filterKeys.size();
        ArrayList<String> filterValues = healthKitDictQuery.getFilterValues();
        if (size != (filterValues == null ? 0 : filterValues.size())) {
            ReleaseLogUtil.d("HiH_HiHealthKitDataChecker", "wrong filter size");
            return false;
        }
        if (size == 0) {
            return true;
        }
        ArrayList arrayList = new ArrayList(filterKeys);
        ArrayList arrayList2 = new ArrayList(filterValues);
        arrayList.removeAll(e);
        arrayList2.removeAll(d);
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            return true;
        }
        ReleaseLogUtil.d("HiH_HiHealthKitDataChecker", "wrong filter key or value");
        return false;
    }

    private static boolean a(HiHealthKitData hiHealthKitData) {
        if (hiHealthKitData == null) {
            return true;
        }
        if (hiHealthKitData.getType() == DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()) {
            return !e(hiHealthKitData);
        }
        return false;
    }

    private static boolean e(HiHealthKitData hiHealthKitData) {
        ReleaseLogUtil.e("HiH_HiHealthKitDataChecker", "enter checkSleepOnOffBedRecordParam");
        long startTime = hiHealthKitData.getStartTime();
        long j = hiHealthKitData.getLong(String.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value()));
        long j2 = hiHealthKitData.getLong(String.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value()));
        long j3 = hiHealthKitData.getLong(String.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME.value()));
        long j4 = hiHealthKitData.getLong(String.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value()));
        boolean z = false;
        if (!e(hiHealthKitData, hiHealthKitData.getType())) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "SleepOnOffBedRecord startTime or endTime error");
            return false;
        }
        if (j2 != 0 || j3 != 0 ? !(j <= 0 || j > j2 || j2 >= j3 || j3 > j4 || j4 > startTime) : !(j <= 0 || j >= j4 || j4 > startTime)) {
            z = true;
        }
        if (!z) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "SleepOnOffBedRecord time error");
        }
        return z;
    }

    public static boolean e(StartSportParam startSportParam) {
        int i = startSportParam.getInt(BleConstants.SPORT_TYPE);
        if (!e(i)) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "sport type is not supported");
            return false;
        }
        if (!d(startSportParam.getInt("isSaveData", CharacteristicConstant.SportRecordSaveModel.SAVE.getValue()))) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "sport type is not supported");
            return false;
        }
        if (!fhw.b.contains(Integer.valueOf(i)) && !fhw.d.contains(Integer.valueOf(i))) {
            String string = startSportParam.getString("macAddress");
            if (!TextUtils.isEmpty(string)) {
                return b(string) && startSportParam.getInt("deviceType") != 0;
            }
            ReleaseLogUtil.e("HiH_HiHealthKitDataChecker", "mac address is empty, not check the mac address, return true");
        }
        return true;
    }

    private static boolean d(int i) {
        boolean z = false;
        for (CharacteristicConstant.SportRecordSaveModel sportRecordSaveModel : CharacteristicConstant.SportRecordSaveModel.values()) {
            if (sportRecordSaveModel.getValue() == i) {
                z = true;
            }
        }
        ReleaseLogUtil.e("HiH_HiHealthKitDataChecker", "checkSportRecordSaveModel result = ", Boolean.valueOf(z));
        return z;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "macAddress == null");
            return false;
        }
        if (Pattern.compile("[0-9A-F]{2}(:[0-9A-F]{2}){5}").matcher(str).find()) {
            return true;
        }
        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "Invalid MAC address");
        return false;
    }

    private static boolean e(int i) {
        boolean z = false;
        for (CharacteristicConstant.EnhanceSportType enhanceSportType : CharacteristicConstant.EnhanceSportType.values()) {
            if (enhanceSportType.getEnhanceSportTypeValue() == i) {
                z = true;
            }
        }
        ReleaseLogUtil.e("HiH_HiHealthKitDataChecker", "checkSportType result = ", Boolean.valueOf(z));
        return z;
    }

    public static boolean e(DataReportModel dataReportModel) {
        if (dataReportModel == null) {
            return false;
        }
        int dataType = dataReportModel.getDataType();
        if (CharacteristicConstant.ReportDataType.containsType(dataType)) {
            int reportType = dataReportModel.getReportType();
            if (!CharacteristicConstant.ReportType.containsType(reportType)) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkAutoReportModel unsupported report type ", Integer.valueOf(reportType));
                return false;
            }
            if (reportType == CharacteristicConstant.ReportType.TARGET.getReportTypeValue()) {
                LogUtil.a("HiHealthKitDataChecker", "checkAutoReportModel target report type");
                if (dataReportModel.getReportValue() > 0 && dataReportModel.getMaxReportValue() > dataReportModel.getReportValue()) {
                    return true;
                }
            } else {
                if (reportType == CharacteristicConstant.ReportType.VALUE_INTERVAL.getReportTypeValue()) {
                    LogUtil.a("HiHealthKitDataChecker", "checkAutoReportModel interval report type");
                    return (dataType == CharacteristicConstant.ReportDataType.DATA_POINT_STEP_SUM.getDataType() && CharacteristicConstant.StepValueInterval.containsValue(dataReportModel.getReportValue())) || (dataType == CharacteristicConstant.ReportDataType.DATA_POINT_CALORIES_SUM.getDataType() && CharacteristicConstant.CaloriesValueInterval.containsValue(dataReportModel.getReportValue())) || (dataType == CharacteristicConstant.ReportDataType.DATA_POINT_DISTANCE_SUM.getDataType() && CharacteristicConstant.DistanceValueInterval.containsValue(dataReportModel.getReportValue()));
                }
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkAutoReportModel unsupported report type ", Integer.valueOf(reportType));
                return false;
            }
        }
        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkAutoReportModel fail ", dataReportModel.toString());
        return false;
    }

    public static boolean c(SubscribeModel subscribeModel) {
        for (int i : subscribeModel.getDataTypes()) {
            if (!CharacteristicConstant.SubscribeType.containsType(i)) {
                return false;
            }
        }
        return true;
    }

    public static int d(Subscriber subscriber) {
        Subscriber subscriber2;
        if (subscriber == null || TextUtils.isEmpty(subscriber.getAppId()) || TextUtils.isEmpty(subscriber.getSubscriberId()) || TextUtils.isEmpty(subscriber.getSecret())) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "subscriber is invalid. ");
            return 2;
        }
        ArrayList arrayList = new ArrayList(10);
        int b = ipw.b(subscriber.getAppId(), arrayList);
        if (koq.b(arrayList)) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "There is no subscriber in this appId. ");
            return b;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                subscriber2 = null;
                break;
            }
            subscriber2 = (Subscriber) it.next();
            if (subscriber2.getSubscriberId().equals(subscriber.getSubscriberId())) {
                break;
            }
        }
        return (subscriber2 != null && subscriber2.getSecret().equals(subscriber.getSecret()) && subscriber2.getMode() == subscriber.getMode() && subscriber2.getMode() == 2) ? 0 : 2;
    }

    public static boolean e(EventTypeInfo eventTypeInfo, boolean z) {
        if (TextUtils.isEmpty(eventTypeInfo.getEventType()) || TextUtils.isEmpty(eventTypeInfo.getSubType())) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "eventInfo type is invalid. ");
            return false;
        }
        if (eventTypeInfo instanceof HealthAlarmInfo) {
            if (eventTypeInfo.getSubType().equals(HealthAlarmInfo.Type.TACHYCARDIA.getName()) || eventTypeInfo.getSubType().equals(HealthAlarmInfo.Type.BRADYCARDIA.getName())) {
                return true;
            }
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "eventInfo subtype is invalid.");
            return false;
        }
        if (eventTypeInfo instanceof SportStatusInfo) {
            if (eventTypeInfo.getSubType().equals(SportStatusInfo.Type.SPORT_START.getName()) || eventTypeInfo.getSubType().equals(SportStatusInfo.Type.SPORT_END.getName())) {
                return true;
            }
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "eventInfo subtype is invalid.");
            return false;
        }
        GoalInfo goalInfo = (GoalInfo) eventTypeInfo;
        if (z && goalInfo.getStartDay() < HiDateUtil.c(System.currentTimeMillis())) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "invalid start day");
            return false;
        }
        Recurrence recurrence = goalInfo.getRecurrence();
        if (recurrence.getUnit() != 1 || recurrence.getCount() <= 0 || recurrence.getCount() > 366) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "invalid recurrence");
            return false;
        }
        List<Goal> goals = goalInfo.getGoals();
        if (koq.b(goals) || goals.size() > 3) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "eventInfo goals is invalid. ");
            return false;
        }
        TreeSet treeSet = new TreeSet(new Comparator() { // from class: iqu
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HiHealthKitDataChecker.d((Goal) obj, (Goal) obj2);
            }
        });
        treeSet.addAll(goals);
        if (treeSet.size() >= goals.size()) {
            return true;
        }
        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "The goalType and dataType can't be same.");
        return false;
    }

    public static /* synthetic */ int d(Goal goal, Goal goal2) {
        return (goal.getGoalType() == goal2.getGoalType() && goal.getDataType() == goal2.getDataType()) ? 0 : 1;
    }

    public static boolean e(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "syncDataTypes is empty");
            return false;
        }
        for (int i : iArr) {
            if (!HiSyncType.d(i)) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "syncDataType = ", Integer.valueOf(i), " is not right");
                return false;
            }
        }
        return true;
    }

    public static boolean e(TrendQuery trendQuery, int i) {
        if (trendQuery == null) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "TrendQuery is null");
            return false;
        }
        if (trendQuery.getDataTypes().length == 0 || trendQuery.getTrendPeriods().length == 0) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "TrendQuery data is empty");
            return false;
        }
        if (i >= 0) {
            return true;
        }
        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "TrendQuery timeout < 0");
        return false;
    }

    public static boolean a(String str) {
        LogUtil.a("HiHealthKitDataChecker", "checkDeviceControlInstruction controlOptions:", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("model")) {
                return c(jSONObject.getInt("power"));
            }
            int i = jSONObject.getInt("model");
            if (i == 1) {
                return c(jSONObject.getInt("power"));
            }
            if (i == 2 || i == 3) {
                ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkDeviceControlInstruction not supported model:", Integer.valueOf(i));
                return false;
            }
            if (i == 4) {
                return b(jSONObject.getDouble("grade"));
            }
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkDeviceControlInstruction invalid model:", Integer.valueOf(i));
            return false;
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "checkDeviceControlInstruction JSONException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    private static boolean b(double d2) {
        if (d2 <= 2.0d && d2 >= -2.0d) {
            return true;
        }
        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "indoor bike slope error");
        return false;
    }

    public static boolean c(int i) {
        if (i > 0 && i <= 32767) {
            return true;
        }
        ReleaseLogUtil.c("HiH_HiHealthKitDataChecker", "indoor bike power error");
        return false;
    }
}
