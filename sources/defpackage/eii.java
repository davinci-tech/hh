package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.PPGTypeRstData;
import com.huawei.hihealth.data.model.PPGTypeRstDataWrapper;
import com.huawei.hihealth.data.model.PPGTypeRstPointClone;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eii {
    public static Map a() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce");
        if (a2 == null) {
            ReleaseLogUtil.d("HeartRateCardUtils", "getExtInfo featureConfig is null");
            return null;
        }
        return a2.a();
    }

    public static String a(HiHealthData hiHealthData) {
        String metaData = hiHealthData.getMetaData();
        if (TextUtils.isEmpty(metaData)) {
            LogUtil.b("HeartRateCardUtils", "getDrawDataJsonStrWithAlgo failed, cause metaDataStr is empty!");
            return null;
        }
        eio eioVar = (eio) nrv.c(metaData, new TypeToken<eio>() { // from class: eii.2
        }.getType());
        if (eioVar == null) {
            LogUtil.b("HeartRateCardUtils", "getDrawDataJsonStrWithAlgo failed, cause ppgMetaData is null!");
            return null;
        }
        long startTime = hiHealthData.getStartTime();
        ehz ehzVar = new ehz();
        e(ehzVar, eioVar, startTime);
        int size = ehzVar.n.size();
        if (size < 4) {
            LogUtil.b("HeartRateCardUtils", "getDrawDataJsonStrWithAlgo failed, cause rriArrayLength is less than!");
            return null;
        }
        double intValue = ehzVar.n.get(size - 1).intValue();
        double intValue2 = ehzVar.n.get(size - 2).intValue();
        double intValue3 = ehzVar.n.get(size - 3).intValue();
        double pow = Math.pow(65536.0d, 2.0d);
        double intValue4 = ehzVar.n.get(size - 4).intValue();
        double pow2 = Math.pow(65536.0d, 3.0d);
        String sequenceData = hiHealthData.getSequenceData();
        if (TextUtils.isEmpty(sequenceData)) {
            LogUtil.b("HeartRateCardUtils", "getDrawDataJsonStrWithAlgo sequenceData is empty!");
            return null;
        }
        eir eirVar = new eir();
        b(eirVar, sequenceData, (long) (intValue + (intValue2 * 65536.0d) + (intValue3 * pow) + (intValue4 * pow2)), sequenceData.length() / 56, startTime);
        eiq eiqVar = new eiq();
        eiqVar.e.add(ehzVar);
        eiqVar.c = eiqVar.e.size();
        eiqVar.f12038a.add(eirVar);
        eiqVar.b = eiqVar.f12038a.size();
        return d(eiqVar);
    }

    public static void e(ehz ehzVar, eio eioVar, long j) {
        String str = eioVar.d;
        String str2 = eioVar.c;
        int i = eioVar.b;
        int i2 = eioVar.e;
        ehzVar.o = j;
        ehzVar.c = i2;
        ehzVar.l = i;
        int i3 = 0;
        if (!TextUtils.isEmpty(str)) {
            int i4 = 0;
            while (i4 < str.length()) {
                int i5 = i4 + 2;
                if (i5 <= str.length()) {
                    ehzVar.m.add(Integer.valueOf(CommonUtil.c(str.substring(i4, i5), 16)));
                }
                i4 = i5;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        while (i3 < str2.length()) {
            int i6 = i3 + 4;
            if (i6 <= str2.length()) {
                ehzVar.n.add(Integer.valueOf(CommonUtil.c(k(str2.substring(i3, i6)), 16)));
            }
            i3 = i6;
        }
    }

    public static void b(eir eirVar, String str, long j, long j2, long j3) {
        int i;
        int i2;
        long j4 = j2;
        for (int i3 = 0; i3 < 300; i3++) {
            int i4 = i3 * 56;
            if (i4 < str.length() && (i = i4 + 16) <= str.length()) {
                long b = CommonUtil.b(k(str.substring(i4, i)), 16);
                eis eisVar = new eis();
                eisVar.d = b;
                if (eisVar.d < j) {
                    j4--;
                } else {
                    for (int i5 = 0; i5 < 5; i5++) {
                        int i6 = (i5 * 8) + i;
                        if (i6 < str.length() && (i2 = i6 + 8) <= str.length()) {
                            int b2 = (int) CommonUtil.b(k(str.substring(i6, i2)), 16);
                            List<Integer> list = eisVar.f12039a;
                            if ((b2 >>> 31) > 0) {
                                b2 = -(~b2);
                            }
                            list.add(Integer.valueOf(b2));
                        }
                    }
                    eirVar.e.add(eisVar);
                }
            }
        }
        if (j4 < 240) {
            LogUtil.b("HeartRateCardUtils", "configPPGSamplePointClone error alignPpgLen = ", Long.valueOf(j4), ", is less than PPG_DATA_SAMPLE_POINT_MIN_LENGTH");
        }
        for (int size = eirVar.e.size(); size < 300; size++) {
            eirVar.e.add(new eis());
        }
        eirVar.d = j3;
    }

    public static String d(eiq eiqVar) {
        if (!AppBundle.c().isExistLocalModule("PluginHealthAlgorithm") && !AppBundle.c().isBuiltInModule(BaseApplication.e(), "PluginHealthAlgorithm")) {
            LogUtil.h("HeartRateCardUtils", "PpgHeartStudyAlgorithmProxy not exist");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(nrv.a(eiqVar));
            List<JSONObject> d = d(jSONObject.getJSONArray("ppgSamplePointClonesList"));
            List<JSONObject> d2 = d(jSONObject.getJSONArray("deviceMeasureRstBeanList"));
            LogUtil.a("HeartRateCardUtils", "getAlgoResult ppgSamplePointClonesList size=", d, ", deviceMeasureRstBeanList size=", d2);
            List<PPGTypeRstData> ppgTypeRstBeanList = ((PPGTypeRstDataWrapper) nrv.c(mpj.a().getDrawData(d, d2, d.size(), d2.size()).toString(), new TypeToken<PPGTypeRstDataWrapper>() { // from class: eii.5
            }.getType())).getPpgTypeRstBeanList();
            if (koq.b(ppgTypeRstBeanList)) {
                LogUtil.b("HeartRateCardUtils", "getAlgoResult failed, cause dataList is empty!");
                return null;
            }
            return nrv.e(ppgTypeRstBeanList.get(0), new TypeToken<PPGTypeRstData>() { // from class: eii.4
            }.getType());
        } catch (JSONException e) {
            LogUtil.b("HeartRateCardUtils", "getAlgoResult jsonException = ", ExceptionUtils.d(e));
            return null;
        }
    }

    public static List<JSONObject> d(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        try {
            LogUtil.a("HeartRateCardUtils", "toJsonList rawList size=", Integer.valueOf(jSONArray.length()));
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            LogUtil.b("HeartRateCardUtils", "toJsonList JSONException", e.getMessage());
        }
        return arrayList;
    }

    private static String k(String str) {
        StringBuilder sb = new StringBuilder();
        for (int length = str.length() - 1; length >= 0; length -= 2) {
            int i = length - 1;
            int i2 = length + 1;
            if (i < 0) {
                i = 0;
            }
            if (i2 > str.length()) {
                i2 = str.length();
            }
            sb.append(str.substring(i, i2));
        }
        return sb.toString();
    }

    private static List<fbl> b(PPGTypeRstData pPGTypeRstData) {
        ArrayList<PPGTypeRstPointClone> points = pPGTypeRstData.getPoints();
        ArrayList<Integer> arrayTypeIdx = pPGTypeRstData.getArrayTypeIdx();
        int i = 0;
        for (int i2 = 0; i2 < points.size() && arrayTypeIdx.get(i2).intValue() == 1; i2++) {
            i++;
        }
        ArrayList<Double> arrayOutsideRr = pPGTypeRstData.getArrayOutsideRr();
        ArrayList arrayList = new ArrayList();
        Iterator<Double> it = arrayOutsideRr.iterator();
        while (it.hasNext()) {
            arrayList.add(new fbl(it.next().doubleValue()));
        }
        int end = i > 0 ? points.get(i - 1).getEnd() + 1 : 0;
        if (end >= arrayList.size()) {
            LogUtil.b("HeartRateCardUtils", "getSeqDataCutInvalidStart error validSeqDataStartIndex = ", Integer.valueOf(end), " is greater than seqData.size() = ", Integer.valueOf(arrayList.size()));
            return arrayList;
        }
        while (i < points.size()) {
            if (arrayTypeIdx.get(i).intValue() == 1) {
                PPGTypeRstPointClone pPGTypeRstPointClone = points.get(i);
                int start = pPGTypeRstPointClone.getStart();
                int end2 = pPGTypeRstPointClone.getEnd();
                if (start >= arrayList.size()) {
                    break;
                }
                while (start <= end2) {
                    if (koq.d(arrayList, start)) {
                        ((fbl) arrayList.get(start)).c(true);
                    }
                    start++;
                }
            }
            i++;
        }
        return arrayList.subList(end, arrayList.size());
    }

    public static List<fbl> d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("HeartRateCardUtils", "getSeqData failed, cause drawDataJsonStr is empty!");
            return null;
        }
        try {
            PPGTypeRstData pPGTypeRstData = (PPGTypeRstData) nrv.c(str, new TypeToken<PPGTypeRstData>() { // from class: eii.1
            }.getType());
            if (pPGTypeRstData == null) {
                LogUtil.b("HeartRateCardUtils", "rstData is null");
                return null;
            }
            List<fbl> b = b(pPGTypeRstData);
            if (koq.b(b)) {
                LogUtil.b("HeartRateCardUtils", "parseEcgDiagramSeqData cause seqData is empty");
                return b;
            }
            int size = b.size();
            int i = size - 1;
            int i2 = ((int) ((i * 7000) / (i * 40))) + 1;
            return size >= i2 ? b.subList(0, i2) : b;
        } catch (Exception e) {
            LogUtil.b("HeartRateCardUtils", "onQueryDetailDone failed, exception: ", e.getMessage(), ", drawDataJsonStr = ", str);
            return null;
        }
    }

    public static List<Double> b(String str) {
        List<fbl> d = d(str);
        if (koq.b(d)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (fbl fblVar : d) {
            if (fblVar.c()) {
                i++;
            } else {
                arrayList.add(Double.valueOf(fblVar.e()));
            }
        }
        LogUtil.a("HeartRateCardUtils", "unValid data list size is ", Integer.valueOf(i));
        return arrayList;
    }

    public static List<fbl> d(List<HiHealthData> list) {
        List<Double> b = b(list);
        if (b == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Double> it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(new fbl(it.next().doubleValue()));
        }
        return arrayList;
    }

    public static List<Double> b(List<HiHealthData> list) {
        List<Double> emptyList;
        try {
            emptyList = (List) nrv.c(list.get(0).getSimpleData(), new TypeToken<ArrayList<Double>>() { // from class: eii.3
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HeartRateCardUtils", "JsonSyntaxException");
            emptyList = Collections.emptyList();
        }
        if (koq.b(emptyList)) {
            LogUtil.b("HeartRateCardUtils", "parseEcgDiagramSeqData cause seqData is empty");
            return null;
        }
        int size = emptyList.size();
        int i = size - 1;
        int i2 = ((int) ((i * 7000) / (i * 40))) + 1;
        return size >= i2 ? emptyList.subList(0, i2) : emptyList;
    }

    public static boolean g(String str) {
        return drl.c("com.huawei.health_deviceFeature_config", "txt", str);
    }

    public static double d() {
        Map a2 = a();
        if (a2 == null) {
            return 0.0d;
        }
        Object obj = a2.get("dia");
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue();
        }
        return 0.0d;
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("HeartRateCardUtils", "checkMedicalType medicalType is null");
            return false;
        }
        Map a2 = a();
        if (a2 == null) {
            LogUtil.b("HeartRateCardUtils", "checkMedicalType extInfo is null!");
            ReleaseLogUtil.c("HeartRateCardUtils", "checkMedicalType extInfo is null!");
            return false;
        }
        Object obj = a2.get("medical");
        LogUtil.a("HeartRateCardUtils", "checkMedicalType medicalValue = ", obj);
        ReleaseLogUtil.e("HeartRateCardUtils", "checkMedicalType medicalValue = ", obj);
        if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
            return false;
        }
        Object obj2 = a2.get("medicalType");
        LogUtil.a("HeartRateCardUtils", "checkMedicalType medicalTypeValue = ", obj2);
        ReleaseLogUtil.e("HeartRateCardUtils", "checkMedicalType medicalTypeValue = ", obj2);
        if (obj2 instanceof String) {
            return str.equals((String) obj2);
        }
        return false;
    }

    public static boolean c() {
        return Double.compare(d(), 1.0d) == 0;
    }

    public static boolean f() {
        return CommonUtil.aq() || CommonUtil.cc() || ((h() || e()) && j("support_ecg_diagram") && !EnvironmentInfo.k());
    }

    public static boolean j() {
        return CommonUtil.aq() || CommonUtil.cc() || ((g("com.huawei.health.h5.ppg") || g()) && j("support_arrhythmia") && !EnvironmentInfo.k());
    }

    public static boolean i() {
        return CommonUtil.aq() || CommonUtil.cc() || (g("com.huawei.health.h5.vascular-health") && j("support_vascular") && !((LoginInit.getInstance(BaseApplication.e()).isBrowseMode() && Utils.i()) || EnvironmentInfo.k()));
    }

    public static boolean g() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ppg");
        if (a2 == null || a2.a() == null) {
            LogUtil.b("HeartRateCardUtils", "isSupportPpg featureConfig or getExtInfo() is null");
            return false;
        }
        Map a3 = a2.a();
        if (a3 == null) {
            LogUtil.b("HeartRateCardUtils", "isSupportPpg extInfo is null");
            return false;
        }
        Object obj = a3.get("support_ppg");
        LogUtil.a("HeartRateCardUtils", "isSupportPpg supportPpg:", obj);
        return (obj instanceof Double) && ((Double) obj).doubleValue() > 0.0d;
    }

    public static boolean e() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce");
        if (a2 == null) {
            LogUtil.h("HeartRateCardUtils", "isSupportEcgExtInfo featureConfig is null");
            return false;
        }
        Map a3 = a2.a();
        if (a3 == null) {
            LogUtil.h("HeartRateCardUtils", "isSupportEcgExtInfo extInfo is null!");
            return false;
        }
        Integer e = a2.e();
        Object obj = a3.get("support_ecg");
        if (!(obj instanceof Double)) {
            return false;
        }
        LogUtil.a("HeartRateCardUtils", "isSupportEcgExtInfo supportEcg is ", obj, " support is ", e);
        if (Double.compare(((Double) obj).doubleValue(), 1.0d) != 0) {
            return false;
        }
        Integer num = 0;
        return num.equals(e);
    }

    public static boolean h() {
        return g("com.huawei.health.h5.ecgce");
    }

    static boolean j(String str) {
        String e = jah.c().e(str);
        boolean equals = "true".equals(e);
        boolean bv = CommonUtil.bv();
        boolean bu = CommonUtil.bu();
        ReleaseLogUtil.e("HeartRateCardUtils", "isSupportConfig supportKey = ", str, ", supportValue = ", e, ", isReleaseVersion = ", Boolean.valueOf(bv), ", isDemoVersion = ", Boolean.valueOf(bu));
        return !(bv || bu) || equals;
    }

    public static void e(boolean z, String str) {
        SharedPreferenceManager.e(Integer.toString(10052), f(str), z);
    }

    public static boolean a(String str) {
        return SharedPreferenceManager.a(Integer.toString(10052), f(str), false);
    }

    public static long c(String str) {
        return SharedPreferenceManager.b(Integer.toString(10052), h(str), 0L);
    }

    public static void b(long j, String str) {
        SharedPreferenceManager.e(Integer.toString(10052), h(str), j);
    }

    private static String h(String str) {
        return str + "_newDataDotTimeStamp";
    }

    private static String f(String str) {
        return str + "_newDataDotExists";
    }

    public static boolean b() {
        Map a2;
        dqo a3 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ppg");
        if (a3 == null || (a2 = a3.a()) == null) {
            return false;
        }
        Object obj = a2.get("medicalType");
        LogUtil.a("ArrhythmiaItemHolder", "medicalType: ", obj);
        return (obj instanceof String) && obj.equals("NMPA");
    }

    public static int a(int i) {
        boolean b = b();
        LogUtil.a("HeartRateCardUtils", "isNMPA:", Boolean.valueOf(b), " originType:", Integer.valueOf(i));
        if (i == 4) {
            if (b) {
                return 3;
            }
            return i;
        }
        if (i == 5 && b) {
            return 2;
        }
        return i;
    }

    public static void i(String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            long j = jSONObject.getLong("time");
            if (j <= 0) {
                LogUtil.b("HeartRateCardUtils", "time is unvalid");
                return;
            }
            int i = jSONObject.getInt("type");
            if (i == 0) {
                str2 = "NormalItemHolder";
            } else if (i == 1) {
                str2 = "NormalDiaItemHolder";
            } else if (i == 2) {
                str2 = "ArrhythmiaItemHolder";
            } else {
                if (i != 3) {
                    LogUtil.b("HeartRateCardUtils", "unvalid type");
                    return;
                }
                str2 = "VascularItemHolder";
            }
            if (j >= c(str2)) {
                LogUtil.a("HeartRateCardUtils", "refresh cache: ", str2);
                b(j, str2);
                e(false, str2);
                ObserverManagerUtil.c(ObserveLabels.HEART_RATE, new Object[0]);
            }
        } catch (JSONException e) {
            LogUtil.b("HeartRateCardUtils", "userVisitLatestTime,", e.getMessage());
        }
    }
}
