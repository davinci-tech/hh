package defpackage;

import android.text.TextUtils;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jhy;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jhr {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Integer> f13858a = new LinkedHashMap(16);
    private static final Map<Integer, Integer> d = new HashMap(16);

    public static int c(byte[] bArr) throws bmk {
        if (bArr == null || bArr.length <= 2) {
            return 0;
        }
        bmq c = new bmt().c(bArr, 2);
        if (c == null) {
            LogUtil.h("FitnessUnTlvSample", "unTlvGetSampleFrameCount is null");
            return 0;
        }
        List<bmq> a2 = c.a();
        int i = 0;
        for (int i2 = 0; i2 < a2.size(); i2++) {
            List<bmu> d2 = a2.get(i2).d();
            for (int i3 = 0; i3 < d2.size(); i3++) {
                if (d2.get(i3).a() == 2) {
                    i = cvx.c(d2.get(i3).c(), -1);
                } else {
                    LogUtil.h("FitnessUnTlvSample", "unTlvGetSampleFrameCount default");
                }
            }
        }
        return i;
    }

    private static List<jie> d(String str) {
        ArrayList arrayList = new ArrayList(16);
        if (str.length() == 0 || str.length() < 2) {
            LogUtil.h("FitnessUnTlvSample", "value.length is 0 or value.length() < position + SINGLE_BYTE_STRING_LEN");
            return arrayList;
        }
        int e = e(str);
        byte[] a2 = cvx.a(str.substring(0, e));
        String substring = str.substring(e);
        if (TextUtils.isEmpty(substring)) {
            return arrayList;
        }
        Map<Integer, Integer> c = c();
        if (!b(a2, c, substring)) {
            LogUtil.h("FitnessUnTlvSample", "watch or band send tlv is wrong, please check : ", str);
            return arrayList;
        }
        d(a2, substring, c, arrayList);
        return arrayList;
    }

    private static void d(byte[] bArr, String str, Map<Integer, Integer> map, List<jie> list) {
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int intValue = entry.getKey().intValue();
            if (CommonUtil.a(bArr, intValue)) {
                int intValue2 = (entry.getValue().intValue() * 2) + i;
                list.add(b(str.substring(i, intValue2), intValue));
                i = intValue2;
            }
        }
    }

    private static jie b(String str, int i) {
        int w = CommonUtil.w(str);
        Integer num = d().get(Integer.valueOf(i));
        if (num != null) {
            i = num.intValue();
        }
        return new jie(i, w);
    }

    private static int e(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        while (true) {
            i = i2 + 2;
            if (i > length || !CommonUtil.a(cvx.a(str.substring(i2, i)), 7)) {
                break;
            }
            i2 = i;
        }
        return i;
    }

    private static boolean b(byte[] bArr, Map<Integer, Integer> map, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (CommonUtil.a(bArr, entry.getKey().intValue())) {
                i += entry.getValue().intValue() * 2;
            }
        }
        return str.length() >= i;
    }

    private static Map<Integer, Integer> c() {
        Map<Integer, Integer> map = f13858a;
        if (map.isEmpty()) {
            map.put(0, 2);
            map.put(1, 2);
            map.put(2, 2);
            map.put(3, 2);
            map.put(4, 2);
            map.put(5, 1);
            map.put(6, 1);
            map.put(8, 1);
            map.put(9, 1);
            b();
        }
        return map;
    }

    private static void b() {
        if (CommonUtil.as()) {
            int i = -1;
            for (Map.Entry<Integer, Integer> entry : f13858a.entrySet()) {
                int intValue = entry.getKey().intValue();
                i = i % 8 == 6 ? i + 2 : i + 1;
                if (intValue != i) {
                    throw new RuntimeException("PARAMETER_INDEX_AND_LENGTH index must increase 1, if last index is (8 * n + 7),increase 2,you support index is " + intValue + ",you should put key is : " + i);
                }
                if (entry.getValue().intValue() <= 0) {
                    throw new RuntimeException("you put support index : " + intValue + " byteLength is <= 0, please check.");
                }
            }
        }
    }

    private static Map<Integer, Integer> d() {
        Map<Integer, Integer> map = d;
        if (map.isEmpty()) {
            map.put(0, 4);
            map.put(4, 5);
            map.put(5, 6);
            map.put(6, 7);
        }
        return map;
    }

    private static List<jie> d(List<jie> list, long j, int i, int i2, int i3) {
        long j2 = i * 60;
        for (jie jieVar : list) {
            jieVar.c(j + j2);
            jieVar.b(i2);
            jieVar.a(i3);
        }
        return list;
    }

    public static jic b(byte[] bArr) throws bmk {
        jic jicVar = new jic();
        if (bArr != null && bArr.length > 2) {
            bmq c = new bmt().c(bArr, 2);
            if (c == null) {
                LogUtil.h("FitnessUnTlvSample", "unTlvGetSampleFrame is null");
                return jicVar;
            }
            ArrayList arrayList = new ArrayList(16);
            List<bmq> a2 = c.a();
            int i = 0;
            for (int i2 = 0; i2 < a2.size(); i2++) {
                List<bmu> d2 = a2.get(i2).d();
                for (int i3 = 0; i3 < d2.size(); i3++) {
                    byte[] c2 = d2.get(i3).c();
                    if (d2.get(i3).a() == 3) {
                        i = cvx.c(c2, -1);
                        jicVar.a(i);
                    } else {
                        LogUtil.h("FitnessUnTlvSample", "unTlvGetSampleFrame default");
                    }
                }
                a(arrayList, i2, a2, i);
            }
            jicVar.b(arrayList);
        }
        return jicVar;
    }

    private static void a(List<jie> list, int i, List<bmq> list2, int i2) {
        if (i < 0 || i >= list2.size()) {
            LogUtil.h("FitnessUnTlvSample", "dealSamplePointList out of index");
            return;
        }
        List<bmq> a2 = list2.get(i).a();
        for (int i3 = 0; i3 < a2.size(); i3++) {
            List<bmu> d2 = a2.get(i3).d();
            ArrayList arrayList = new ArrayList(16);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < d2.size(); i7++) {
                byte[] c = d2.get(i7).c();
                byte a3 = d2.get(i7).a();
                if (a3 == 5) {
                    i4 = cvx.c(c, -1);
                } else if (a3 == 6) {
                    arrayList.addAll(d(cvx.d(c)));
                } else if (a3 == 8) {
                    i5 = cvx.c(c, -1);
                } else if (a3 == 9) {
                    i6 = cvx.c(c, -1);
                } else {
                    arrayList.add(new jie(a3, cvx.c(c, -1)));
                }
            }
            d(arrayList, i2, i4, i5, i6);
            list.addAll(arrayList);
        }
    }

    public static int a(cwe cweVar) {
        int i = 0;
        if (cweVar == null) {
            LogUtil.h("FitnessUnTlvSample", "unTlvGetDesFrameCount is null");
            return 0;
        }
        for (cwd cwdVar : cweVar.e()) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                LogUtil.h("FitnessUnTlvSample", "unTlvGetDesFrameCount default");
            }
        }
        return i;
    }

    public static jhy b(cwe cweVar) {
        jhy jhyVar = new jhy();
        if (cweVar == null) {
            LogUtil.h("FitnessUnTlvSample", "unTlvGetDesFrame is null");
            return jhyVar;
        }
        List<cwd> e = cweVar.e();
        List<cwe> a2 = cweVar.a();
        for (cwd cwdVar : e) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                jhyVar.e(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.h("FitnessUnTlvSample", "unTlvGetDesFrame default");
            }
        }
        Iterator<cwe> it = a2.iterator();
        while (it.hasNext()) {
            d(jhyVar, it.next());
            LogUtil.c("FitnessUnTlvSample", "desFrame.getDatas().add(frame)", jhyVar.toString());
        }
        return jhyVar;
    }

    public static HeartRateThresholdConfig c(cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("FitnessUnTlvSample", "unTlvGetHeartConfig tlvFather is null");
            return null;
        }
        List<cwd> e = cweVar.e();
        HeartRateThresholdConfig studentHeartRateThresholdData = ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getStudentHeartRateData().getStudentHeartRateThresholdData();
        for (cwd cwdVar : e) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 2:
                    LogUtil.a("FitnessUnTlvSample", "unTlvGetHeartConfig ", cwdVar.c());
                    studentHeartRateThresholdData.setFitnessThreshold(CommonUtil.w(cwdVar.c()));
                    break;
                case 3:
                    studentHeartRateThresholdData.setWarmUpThreshold(CommonUtil.w(cwdVar.c()));
                    break;
                case 4:
                    studentHeartRateThresholdData.setFatBurnThreshold(CommonUtil.w(cwdVar.c()));
                    break;
                case 5:
                    studentHeartRateThresholdData.setAerobicThreshold(CommonUtil.w(cwdVar.c()));
                    break;
                case 6:
                    studentHeartRateThresholdData.setAnaerobicThreshold(CommonUtil.w(cwdVar.c()));
                    break;
                case 7:
                    studentHeartRateThresholdData.setMaxThreshold(CommonUtil.w(cwdVar.c()));
                    break;
                case 8:
                    studentHeartRateThresholdData.setWarningEnable(CommonUtil.w(cwdVar.c()) == 1);
                    break;
                case 9:
                    studentHeartRateThresholdData.setWarningLimit(CommonUtil.w(cwdVar.c()));
                    break;
                default:
                    d(cwdVar, studentHeartRateThresholdData);
                    break;
            }
        }
        d(studentHeartRateThresholdData);
        return studentHeartRateThresholdData;
    }

    private static void d(HeartRateThresholdConfig heartRateThresholdConfig) {
        int maxThreshold = heartRateThresholdConfig.getMaxThreshold();
        heartRateThresholdConfig.setOldMaxThreshold(maxThreshold);
        int studentAge = 220 - ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getStudentAge();
        boolean z = maxThreshold != studentAge;
        boolean z2 = heartRateThresholdConfig.getWarningLimit() != studentAge;
        LogUtil.a("FitnessUnTlvSample", "isSetMax ", Boolean.valueOf(z), " isSetLimit ", Boolean.valueOf(z2));
        heartRateThresholdConfig.getHeartZoneStateConfig().setIsSetMaxHeart(z);
        heartRateThresholdConfig.getHeartZoneStateConfig().setIsSetWarningLimit(z2);
    }

    private static void d(cwd cwdVar, HeartRateThresholdConfig heartRateThresholdConfig) {
        switch (CommonUtil.w(cwdVar.e())) {
            case 10:
                heartRateThresholdConfig.setClassifyMethod(CommonUtil.w(cwdVar.c()));
                break;
            case 11:
                heartRateThresholdConfig.setMaxThreshold(CommonUtil.w(cwdVar.c()));
                break;
            case 12:
                heartRateThresholdConfig.setRestHeartRate(CommonUtil.w(cwdVar.c()));
                break;
            case 13:
                heartRateThresholdConfig.setAerobicBaseThreshold(CommonUtil.w(cwdVar.c()));
                break;
            case 14:
                heartRateThresholdConfig.setAerobicAdvanceThreshold(CommonUtil.w(cwdVar.c()));
                break;
            case 15:
                heartRateThresholdConfig.setLacticAcidThreshold(CommonUtil.w(cwdVar.c()));
                break;
            case 16:
                heartRateThresholdConfig.setAnaerobicBaseThreshold(CommonUtil.w(cwdVar.c()));
                break;
            case 17:
                heartRateThresholdConfig.setAnaerobicAdvanceThreshold(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.h("FitnessUnTlvSample", "unTlvGetHeartConfigOther default");
                break;
        }
    }

    private static void d(jhy jhyVar, cwe cweVar) {
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            jhy.a aVar = new jhy.a();
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 4) {
                    aVar.d(CommonUtil.w(cwdVar.c()));
                } else if (w == 5) {
                    aVar.c(CommonUtil.w(cwdVar.c()));
                } else if (w == 6) {
                    aVar.a(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.h("FitnessUnTlvSample", "unTlvGetDesFrameCount default");
                }
            }
            jhyVar.e().add(aVar);
        }
    }
}
