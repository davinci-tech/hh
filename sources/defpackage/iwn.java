package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiConfigDataType;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.OperationKey;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiValidatorUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class iwn {
    private static boolean d(int i) {
        switch (i) {
            case 47401:
            case 47402:
            case 47403:
            case 47404:
            case 47405:
                return true;
            default:
                return false;
        }
    }

    public static List<HiHealthData> d(HiDataInsertOption hiDataInsertOption, boolean z) throws iwt {
        if (hiDataInsertOption == null) {
            throw new iwt("HiDataInsertOption is null");
        }
        return b(hiDataInsertOption.getDatas(), z);
    }

    private static List<HiHealthData> b(List<HiHealthData> list, boolean z) throws iwt {
        if (list == null || list.size() == 0 || list.get(0) == null) {
            throw new iwt("List<HiHealthData> is null or datas.get(0) null");
        }
        String deviceUuid = list.get(0).getDeviceUuid();
        if (TextUtils.isEmpty(deviceUuid)) {
            throw new iwt("deviceUUID is null");
        }
        ArrayList arrayList = new ArrayList(10);
        int ownerId = list.get(0).getOwnerId();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            HiHealthData next = it.next();
            try {
                d(next, deviceUuid, ownerId, z);
            } catch (iwt e) {
                if (!e.b()) {
                    it.remove();
                    arrayList.add(next);
                } else {
                    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
                    linkedHashMap.put("validInsertStatus", "fail " + e.getMessage());
                    ivz.d(BaseApplication.getContext()).e(OperationKey.HEALTH_DATA_INSERT_VALID_FAIL_2129013.value(), linkedHashMap, false);
                }
                ReleaseLogUtil.c("HiH_HiDataInsValid", "validHiHealthData Exception = ", e.getMessage());
            }
        }
        if (HiCommonUtil.d(list)) {
            throw new iwt("all datas is invalid!");
        }
        return arrayList;
    }

    private static void d(HiHealthData hiHealthData, String str, int i, boolean z) throws iwt {
        if (hiHealthData == null) {
            throw new iwt("HiHealthData is null");
        }
        if (!str.equals(hiHealthData.getDeviceUuid())) {
            throw new iwt("deviceUUID is not the same");
        }
        if (i != hiHealthData.getOwnerId()) {
            throw new iwt("ownerID is not the same");
        }
        if (z && !HiHealthDataType.Category.REALTIME.equals(HiHealthDataType.e(hiHealthData.getType()))) {
            throw new iwt("insert realtime invalid");
        }
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        if (startTime > endTime || startTime <= 0) {
            throw new iwt("startTime > endTime or startTime <= 0");
        }
        if (String.valueOf(startTime).length() != 13 || String.valueOf(endTime).length() != 13) {
            throw new iwt("time length not right");
        }
        if (HiHealthDictManager.d(BaseApplication.getContext()).l(hiHealthData.getType())) {
            j(hiHealthData);
        } else {
            a(hiHealthData.getType(), hiHealthData);
        }
    }

    /* renamed from: iwn$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            b = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[HiHealthDataType.Category.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[HiHealthDataType.Category.SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[HiHealthDataType.Category.STAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[HiHealthDataType.Category.SESSION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[HiHealthDataType.Category.REALTIME.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[HiHealthDataType.Category.CONFIG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[HiHealthDataType.Category.CONFIGSTAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[HiHealthDataType.Category.BUSINESS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private static void a(int i, HiHealthData hiHealthData) throws iwt {
        switch (AnonymousClass5.b[HiHealthDataType.e(i).ordinal()]) {
            case 1:
                e(i, hiHealthData);
                return;
            case 2:
                d(hiHealthData);
                return;
            case 3:
            case 6:
            case 8:
                return;
            case 4:
                b(i, hiHealthData);
                return;
            case 5:
                h(hiHealthData);
                return;
            case 7:
                a(hiHealthData);
                return;
            case 9:
                d(i, hiHealthData);
                return;
            default:
                throw new iwt("Unknown data type: " + i);
        }
    }

    private static void j(HiHealthData hiHealthData) throws iwt {
        int i = AnonymousClass5.b[HiHealthDataType.e(hiHealthData.getType()).ordinal()];
        if (i == 1) {
            b(hiHealthData);
            return;
        }
        if (i == 2) {
            e(hiHealthData);
            return;
        }
        if (i != 3) {
            if (i == 4) {
                return;
            }
            if (i != 6) {
                throw new iwt("Unknown data type: " + hiHealthData.getType());
            }
        }
        c(hiHealthData);
    }

    private static void b(HiHealthData hiHealthData) throws iwt {
        HashMap hashMap = new HashMap(1);
        HiHealthDictManager d = HiHealthDictManager.d(BaseApplication.getContext());
        HiHealthDictField b = d.b(hiHealthData.getType());
        if (b == null) {
            throw new iwt("dictField is null, type is: " + hiHealthData.getType());
        }
        hashMap.put(b.a(), Double.valueOf(hiHealthData.getValue()));
        HiHealthDictType f = d.f(hiHealthData.getType());
        if (f == null) {
            throw new iwt("dictType is null, type is: " + hiHealthData.getType());
        }
        if (f.c(hashMap)) {
            return;
        }
        throw new iwt("dic validate result is false, type is: " + hiHealthData.getType() + ", value is " + hiHealthData.getValue());
    }

    private static void e(HiHealthData hiHealthData) throws iwt {
        if (hiHealthData.getSequenceData() == null && hiHealthData.getSequenceFileUrl() == null) {
            throw new iwt("SEQUENCE sequenceData and sequenceFileUrl all null");
        }
        if (hiHealthData.getMetaData() == null) {
            return;
        }
        HiHealthDictManager d = HiHealthDictManager.d(BaseApplication.getContext());
        if (d == null) {
            throw new iwt("dictManager is null, type is: " + hiHealthData.getType());
        }
        Map<String, Double> d2 = d(hiHealthData, d);
        if (d2.isEmpty()) {
            return;
        }
        HiHealthDictType d3 = d.d(hiHealthData.getType());
        if (d3 == null) {
            throw new iwt("dictType is null, type is: " + hiHealthData.getType());
        }
        if (d3.c(d2)) {
            return;
        }
        throw new iwt("dic validate result is false, type is: " + hiHealthData.getType() + ", metaData is " + hiHealthData.getMetaData());
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0099 A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:3:0x000b, B:5:0x001a, B:8:0x0026, B:9:0x002d, B:11:0x002e, B:13:0x0034, B:14:0x003f, B:15:0x004b, B:17:0x0051, B:19:0x006b, B:21:0x0073, B:25:0x007f, B:29:0x008b, B:31:0x0099, B:33:0x00a5, B:34:0x00c3, B:37:0x00c6, B:42:0x00ce, B:44:0x00dc, B:48:0x00e9, B:49:0x0107), top: B:2:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00dc A[Catch: JSONException -> 0x0109, TryCatch #0 {JSONException -> 0x0109, blocks: (B:3:0x000b, B:5:0x001a, B:8:0x0026, B:9:0x002d, B:11:0x002e, B:13:0x0034, B:14:0x003f, B:15:0x004b, B:17:0x0051, B:19:0x006b, B:21:0x0073, B:25:0x007f, B:29:0x008b, B:31:0x0099, B:33:0x00a5, B:34:0x00c3, B:37:0x00c6, B:42:0x00ce, B:44:0x00dc, B:48:0x00e9, B:49:0x0107), top: B:2:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.String, java.lang.Double> d(com.huawei.hihealth.HiHealthData r10, com.huawei.hihealth.dictionary.HiHealthDictManager r11) throws defpackage.iwt {
        /*
            Method dump skipped, instructions count: 290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iwn.d(com.huawei.hihealth.HiHealthData, com.huawei.hihealth.dictionary.HiHealthDictManager):java.util.Map");
    }

    private static void c(HiHealthData hiHealthData) throws iwt {
        try {
            HashMap hashMap = (HashMap) new Gson().fromJson(hiHealthData.getFieldsValue(), new TypeToken<HashMap<Integer, Double>>() { // from class: iwn.4
            }.getType());
            if (hashMap == null || hashMap.isEmpty()) {
                return;
            }
            HiHealthDictManager d = HiHealthDictManager.d(BaseApplication.getContext());
            HashMap hashMap2 = new HashMap(16);
            for (Map.Entry entry : hashMap.entrySet()) {
                HiHealthDictField b = d.b(((Integer) entry.getKey()).intValue());
                if (b != null) {
                    hashMap2.put(b.a(), (Double) entry.getValue());
                }
            }
            HiHealthDictType d2 = d.d(hiHealthData.getType());
            if (d2 == null) {
                throw new iwt("dictType is null, type is: " + hiHealthData.getType());
            }
            if (d2.c(hashMap2)) {
                return;
            }
            throw new iwt("dic validate result is false, type is: " + hiHealthData.getType() + ", value is " + hiHealthData.getFieldsValue());
        } catch (JsonSyntaxException e) {
            throw new iwt("FromJson JsonSyntaxException " + e.getMessage());
        }
    }

    private static void d(int i, HiHealthData hiHealthData) throws iwt {
        if (HiValidatorUtil.a(i, hiHealthData.getValue())) {
            return;
        }
        throw new iwt("BUSINESS businessValue is out of range type = " + i);
    }

    private static void a(HiHealthData hiHealthData) throws iwt {
        int type = hiHealthData.getType();
        if (!HiConfigDataType.i(type)) {
            throw new iwt("Unknow data type:" + type);
        }
        int intValue = HiConfigDataType.e(type).intValue();
        Object d = HiConfigDataType.d(intValue, 3);
        Object d2 = HiConfigDataType.d(intValue, 4);
        if (d != null && CommonUtil.a(d.toString()) < hiHealthData.getValue()) {
            throw new iwt("max value is too large");
        }
        if (d2 != null && CommonUtil.a(d2.toString()) > hiHealthData.getValue()) {
            throw new iwt("min value is too little");
        }
    }

    private static void e(int i, HiHealthData hiHealthData) throws iwt {
        if (!HiValidatorUtil.e(i, hiHealthData)) {
            throw new iwt("POINT pointValue is out of range type = " + i + ", value is " + hiHealthData.getValue(), 901 == i);
        }
        if (!HiHealthDataType.p(i) || a(hiHealthData.getStartTime(), hiHealthData.getEndTime())) {
            return;
        }
        throw new iwt("time is not one minite or not currentMinite type = " + i);
    }

    private static void d(HiHealthData hiHealthData) throws iwt {
        if (hiHealthData.getMetaData() == null) {
            throw new iwt("SEQUENCE metaData is null");
        }
        if (hiHealthData.getSequenceData() == null && hiHealthData.getSequenceFileUrl() == null) {
            throw new iwt("SEQUENCE sequenceData and sequenceFileUrl is null");
        }
        try {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(hiHealthData.getMetaData(), HiTrackMetaData.class);
            if (hiTrackMetaData != null) {
                if (hiTrackMetaData.getPartTimeMap().size() > 10000 || hiTrackMetaData.getPaceMap().size() > 10000) {
                    throw new iwt("SEQUENCE metaData partTimeMap is bigger than 10000!");
                }
                if (Float.isInfinite(hiTrackMetaData.getAvgPace())) {
                    throw new iwt("SEQUENCE metaData avgPace is Infinity!");
                }
                if (hiTrackMetaData.getPaceMap() != null) {
                    for (Map.Entry<Integer, Float> entry : hiTrackMetaData.getPaceMap().entrySet()) {
                        if (!iuz.e(entry.getValue().floatValue(), 1000000.0d, 0.0d)) {
                            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
                            linkedHashMap.put("pace", String.valueOf(entry.getValue()));
                            ivz.d(BaseApplication.getContext()).e(OperationKey.HEALTH_APP_PACE_2129008.value(), linkedHashMap, false);
                        }
                    }
                }
                if (hiTrackMetaData.getPartTimeMap() != null) {
                    for (Map.Entry<Double, Double> entry2 : hiTrackMetaData.getPartTimeMap().entrySet()) {
                        if (!iuz.e(entry2.getValue().doubleValue(), 1000000.0d, 0.0d)) {
                            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(5);
                            linkedHashMap2.put("pace", String.valueOf(entry2.getValue()));
                            ivz.d(BaseApplication.getContext()).e(OperationKey.HEALTH_APP_PACE_2129008.value(), linkedHashMap2, false);
                        }
                    }
                }
            }
        } catch (JsonSyntaxException unused) {
            throw new iwt("FromJson JsonSyntaxException");
        }
    }

    private static void b(int i, HiHealthData hiHealthData) throws iwt {
        if (HiValidatorUtil.e(i, hiHealthData.getValue())) {
            return;
        }
        throw new iwt("STAT statValue is out of range type = " + i + ", value is " + hiHealthData.getValue(), d(i));
    }

    private static void h(HiHealthData hiHealthData) throws iwt {
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        if (a(startTime, endTime)) {
            return;
        }
        throw new iwt("SESSION time is not one minite or not currentMinite startTime = " + startTime + ",endTime = " + endTime);
    }

    private static boolean a(long j, long j2) {
        return j % 60000 == 0 && j2 % 60000 == 0 && j2 - j == 60000;
    }

    public static List<HiSampleConfig> b(List<HiSampleConfig> list) throws iwt {
        if (HiCommonUtil.d(list)) {
            throw new iwt("sampleConfigs is null or empty!");
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator<HiSampleConfig> it = list.iterator();
        while (it.hasNext()) {
            HiSampleConfig next = it.next();
            try {
                a(next);
            } catch (iwt e) {
                it.remove();
                arrayList.add(next);
                ReleaseLogUtil.c("HiH_HiDataInsValid", "validSampleConfigs Exception = ", e.getMessage());
            }
        }
        if (HiCommonUtil.d(list)) {
            throw new iwt("all sampleConfigs is invalid!");
        }
        return arrayList;
    }

    private static void a(HiSampleConfig hiSampleConfig) throws iwt {
        b(hiSampleConfig);
        d(hiSampleConfig);
    }

    private static void b(HiSampleConfig hiSampleConfig) throws iwt {
        if (hiSampleConfig == null) {
            throw new iwt("SampleConfig is null!");
        }
        if (hiSampleConfig.getType() == null) {
            throw new iwt("Type is null!");
        }
        if (hiSampleConfig.getConfigId() == null) {
            throw new iwt("ConfigId is null!");
        }
        if (hiSampleConfig.getScopeApp() == null) {
            throw new iwt("ScopeApp is null!!");
        }
        if (hiSampleConfig.getScopeDeviceType() == null) {
            throw new iwt("ScopeDeviceType is null!");
        }
        if (hiSampleConfig.getConfigData() == null) {
            throw new iwt("ConfigData is null!");
        }
        if (hiSampleConfig.getDeviceUniqueId() == null) {
            throw new iwt("DeviceUniqueId is null!");
        }
        if (hiSampleConfig.getModifiedTime() == 0) {
            throw new iwt("ModifiedTime is zero!");
        }
    }

    private static void d(HiSampleConfig hiSampleConfig) throws iwt {
        if (hiSampleConfig.getType().length() > 100) {
            throw new iwt("Type length is greater than 100!");
        }
        if (hiSampleConfig.getConfigId().length() > 100) {
            throw new iwt("ConfigId length is greater than 100!");
        }
        if (hiSampleConfig.getScopeApp().length() > 100) {
            throw new iwt("AppType length is greater than 100!");
        }
        if (hiSampleConfig.getScopeDeviceType().length() > 100) {
            throw new iwt("DeviceUniqueId length is greater than 100!");
        }
        if (hiSampleConfig.getConfigData().length() > 100000) {
            throw new iwt("ConfigData length is greater than 100000!");
        }
        if (hiSampleConfig.getConfigDescription() != null && hiSampleConfig.getConfigDescription().length() > 100) {
            throw new iwt("ConfigDescription length is greater than 100!");
        }
        if (hiSampleConfig.getMetaData() != null && hiSampleConfig.getMetaData().length() > 100000) {
            throw new iwt("MetaData length is greater than 100000!");
        }
    }

    public static void d(HiSampleConfigProcessOption hiSampleConfigProcessOption) throws iwt {
        if (hiSampleConfigProcessOption == null) {
            throw new iwt("sampleConfigGetOption is null!");
        }
        if (HiCommonUtil.d(hiSampleConfigProcessOption.getSampleConfigKeyList())) {
            throw new iwt("SampleConfigKeyList is null or empty!");
        }
        Iterator<HiSampleConfigKey> it = hiSampleConfigProcessOption.getSampleConfigKeyList().iterator();
        while (it.hasNext()) {
            if (it.next().getType() == null) {
                throw new iwt("SampleConfigKey type is null!");
            }
        }
    }

    public static void e(HiSampleConfigProcessOption hiSampleConfigProcessOption) throws iwt {
        if (hiSampleConfigProcessOption == null) {
            throw new iwt("sampleConfigGetOption is null!");
        }
        if (HiCommonUtil.d(hiSampleConfigProcessOption.getSampleConfigKeyList())) {
            throw new iwt("SampleConfigKeyList is null or empty!");
        }
        for (HiSampleConfigKey hiSampleConfigKey : hiSampleConfigProcessOption.getSampleConfigKeyList()) {
            if (hiSampleConfigKey.getType() == null) {
                throw new iwt("SampleConfigKey type is null!");
            }
            if (hiSampleConfigKey.getScopeApp() == null) {
                throw new iwt("SampleConfigKey appType is null!");
            }
            if (hiSampleConfigKey.getScopeDeviceType() == null) {
                throw new iwt("SampleConfigKey deviceUniqueId is null!");
            }
        }
    }
}
