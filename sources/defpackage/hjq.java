package defpackage;

import android.text.TextUtils;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class hjq {
    public static int[] e(String str, String str2, List<koh> list, long j) {
        int[] iArr = new int[5];
        ffg ffgVar = new ffg();
        ffgVar.c(str);
        if (!TextUtils.isEmpty(str2)) {
            a(str2, iArr);
            LogUtil.a("Track_PaceZoneUtils", "paceZoneTimeStatistic:", str2);
            if (a(iArr)) {
                return null;
            }
            return iArr;
        }
        c(list, iArr, sqb.d(list, j), ffgVar);
        if (!a(iArr)) {
            return iArr;
        }
        LogUtil.h("Track_PaceZoneUtils", "hasInvalidValue requestPaceInterDuration");
        return null;
    }

    private static boolean a(int[] iArr) {
        if (iArr == null) {
            return true;
        }
        boolean z = true;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == -1) {
                z2 = true;
            }
            if (i != 0) {
                z = false;
            }
        }
        return z || z2;
    }

    private static boolean c(float[] fArr) {
        if (fArr == null) {
            return true;
        }
        boolean z = true;
        boolean z2 = false;
        for (float f : fArr) {
            if (f == -1.0f) {
                z2 = true;
            }
            if (f != 0.0f) {
                z = false;
            }
        }
        return z || z2;
    }

    private static void c(List<koh> list, int[] iArr, int i, ffg ffgVar) {
        int d;
        if (list == null) {
            LogUtil.b("Track_PaceZoneUtils", "paceList is null");
            return;
        }
        if (ffgVar == null) {
            return;
        }
        Iterator<koh> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() != null && (d = ffgVar.d(r0.a())) != -1) {
                iArr[d] = iArr[d] + i;
            }
        }
    }

    public static List<gxs> e(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (motionPath == null || motionPathSimplify == null) {
            LogUtil.b("Track_PaceZoneUtils", "motionPath or motionPathSimplify is null");
            return Collections.emptyList();
        }
        List<koh> requestRealTimePaceList = motionPath.requestRealTimePaceList();
        String extendDataString = motionPathSimplify.getExtendDataString("runPaceZoneConfig");
        if (koq.b(requestRealTimePaceList) || TextUtils.isEmpty(extendDataString)) {
            LogUtil.b("Track_PaceZoneUtils", "rtPaceList or paceConfigStr is null");
            return Collections.emptyList();
        }
        int d = sqb.d(requestRealTimePaceList, motionPathSimplify.requestStartTime());
        HashMap hashMap = new HashMap(16);
        b(requestRealTimePaceList, hashMap, extendDataString);
        ArrayList arrayList = new ArrayList(10);
        gxs a2 = a(motionPath, motionPathSimplify, hashMap, d);
        if (a2 != null) {
            arrayList.add(a2);
        }
        gxs d2 = d(motionPath, motionPathSimplify, hashMap, d);
        if (d2 != null) {
            arrayList.add(d2);
        }
        if (motionPath.isValidRunningPostureList()) {
            ArrayList arrayList2 = new ArrayList(10);
            hjh.c((ArrayList<ffs>) arrayList2, motionPath.requestRunningPostureList());
            a(arrayList2, arrayList, hashMap);
        }
        return arrayList;
    }

    private static gxs a(MotionPath motionPath, MotionPathSimplify motionPathSimplify, Map<Long, Integer> map, int i) {
        long requestStartTime = motionPathSimplify.requestStartTime();
        List<HeartRateData> a2 = hjh.a(motionPath, requestStartTime, motionPathSimplify.getExtendDataString("isTrustHeartRate"));
        int d = sqb.d(motionPath.requestHeartRateList(), requestStartTime);
        if (koq.b(a2) || d == 60) {
            LogUtil.b("Track_PaceZoneUtils", "heartRateList is isEmpty or heartTimeInterval == MINUTE_TO_SECOND");
            return null;
        }
        if (i == 5 && d == 1) {
            a2 = e(a2);
        }
        int[] b = b(a2, map);
        if (a(b)) {
            LogUtil.h("Track_PaceZoneUtils", "hasInvalidValue heartZone");
            return null;
        }
        gxs gxsVar = new gxs();
        gxsVar.b(b, ObserveLabels.HEART_RATE);
        return gxsVar;
    }

    private static gxs d(MotionPath motionPath, MotionPathSimplify motionPathSimplify, Map<Long, Integer> map, int i) {
        ArrayList<StepRateData> requestStepRateList = motionPath.requestStepRateList();
        long requestStartTime = motionPathSimplify.requestStartTime();
        long requestTotalTime = motionPathSimplify.requestTotalTime();
        if (requestStepRateList == null) {
            LogUtil.b("Track_PaceZoneUtils", "stepRateListTemp is null");
            return null;
        }
        List arrayList = new ArrayList(16);
        hjh.a(requestStepRateList, requestStartTime, requestTotalTime, arrayList);
        int d = sqb.d(requestStepRateList, requestStartTime);
        LogUtil.a("Track_PaceZoneUtils", "stepTimeInterval:", Integer.valueOf(d));
        if (koq.b(arrayList) || d == 60) {
            LogUtil.b("Track_PaceZoneUtils", "stepRateList is isEmpty or stepTimeInterval == MINUTE_TO_SECOND");
            return null;
        }
        if (i == 5 && d == 1) {
            arrayList = b(arrayList);
        }
        int[] a2 = a((List<StepRateData>) arrayList, map);
        if (a(a2)) {
            LogUtil.h("Track_PaceZoneUtils", "hasInvalidValue stepZone");
            return null;
        }
        gxs gxsVar = new gxs();
        gxsVar.b(a2, "STEP_RATE");
        return gxsVar;
    }

    private static void a(List<ffs> list, List<gxs> list2, Map<Long, Integer> map) {
        int[] c = c(list, map, "GROUND_CONTACT_TIME");
        int[] c2 = c(list, map, "SWING_ANGLE");
        int[] c3 = c(list, map, "EVERSION_EXCURSION");
        int[] c4 = c(list, map, "GROUND_IMPACT_ACCELERATION");
        float[] a2 = a(list, map, "ACTIVE_PEAK");
        if (!a(c)) {
            gxs gxsVar = new gxs();
            gxsVar.b(c, "GROUND_CONTACT_TIME");
            list2.add(gxsVar);
        }
        if (!a(c2)) {
            gxs gxsVar2 = new gxs();
            gxsVar2.b(c2, "SWING_ANGLE");
            list2.add(gxsVar2);
        }
        if (!a(c3)) {
            gxs gxsVar3 = new gxs();
            gxsVar3.b(c3, "EVERSION_EXCURSION");
            list2.add(gxsVar3);
        }
        if (!a(c4)) {
            gxs gxsVar4 = new gxs();
            gxsVar4.b(c4, "GROUND_IMPACT_ACCELERATION");
            list2.add(gxsVar4);
        }
        if (c(a2)) {
            return;
        }
        gxs gxsVar5 = new gxs();
        gxsVar5.a(a2, "ACTIVE_PEAK");
        list2.add(gxsVar5);
    }

    private static int[] c(List<ffs> list, Map<Long, Integer> map, String str) {
        if (list == null) {
            return null;
        }
        int[] iArr = new int[5];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        Iterator<ffs> it = list.iterator();
        while (it.hasNext()) {
            e(it.next(), str, iArr, iArr2, map);
        }
        for (int i = 0; i < 5; i++) {
            int i2 = iArr2[i];
            if (i2 != 0) {
                iArr3[i] = (int) Math.ceil(iArr[i] / i2);
            }
        }
        return iArr3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void e(ffs ffsVar, String str, int[] iArr, int[] iArr2, Map<Long, Integer> map) {
        char c;
        if (ffsVar == null) {
            LogUtil.h("Track_PaceZoneUtils", "handleRunningPosture runningPosture is null");
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -2058984604:
                if (str.equals("GROUND_CONTACT_TIME")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1964372974:
                if (str.equals("SWING_ANGLE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1131061880:
                if (str.equals("ACTIVE_PEAK")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1708340310:
                if (str.equals("EVERSION_EXCURSION")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1790478369:
                if (str.equals("GROUND_IMPACT_ACCELERATION")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            a(iArr, iArr2, ffsVar.g(), ffsVar.b(), map);
            return;
        }
        if (c == 1) {
            a(iArr, iArr2, ffsVar.g(), ffsVar.i(), map);
            return;
        }
        if (c == 2) {
            a(iArr, iArr2, ffsVar.g(), (int) ffsVar.m(), map);
        } else if (c == 3) {
            a(iArr, iArr2, ffsVar.g(), ffsVar.a(), map);
        } else {
            if (c != 4) {
                return;
            }
            a(iArr, iArr2, ffsVar.g(), ffsVar.e(), map);
        }
    }

    private static float[] a(List<ffs> list, Map<Long, Integer> map, String str) {
        if (list == null) {
            return null;
        }
        float[] fArr = new float[5];
        float[] fArr2 = new float[5];
        float[] fArr3 = new float[5];
        for (ffs ffsVar : list) {
            if (ffsVar != null && "ACTIVE_PEAK".equals(str)) {
                d(fArr, fArr2, ffsVar.g(), ffsVar.m(), map);
            }
        }
        for (int i = 0; i < 5; i++) {
            float f = fArr2[i];
            if (f != 0.0f) {
                fArr3[i] = fArr[i] / f;
            }
        }
        return fArr3;
    }

    private static int[] a(List<StepRateData> list, Map<Long, Integer> map) {
        if (list == null) {
            LogUtil.h("Track_PaceZoneUtils", "stepList null");
            return null;
        }
        int[] iArr = new int[5];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        for (StepRateData stepRateData : list) {
            if (stepRateData != null) {
                a(iArr, iArr2, stepRateData.acquireTime(), stepRateData.acquireStepRate(), map);
            }
        }
        for (int i = 0; i < 5; i++) {
            int i2 = iArr2[i];
            if (i2 != 0) {
                iArr3[i] = (int) Math.ceil(iArr[i] / i2);
            }
        }
        return iArr3;
    }

    private static int[] b(List<HeartRateData> list, Map<Long, Integer> map) {
        if (list == null) {
            return null;
        }
        int[] iArr = new int[5];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        for (HeartRateData heartRateData : list) {
            if (heartRateData != null) {
                a(iArr, iArr2, heartRateData.acquireTime(), heartRateData.acquireHeartRate(), map);
            }
        }
        for (int i = 0; i < 5; i++) {
            int i2 = iArr2[i];
            if (i2 != 0) {
                iArr3[i] = (int) Math.ceil(iArr[i] / i2);
            }
        }
        return iArr3;
    }

    private static void a(int[] iArr, int[] iArr2, long j, int i, Map<Long, Integer> map) {
        if (map == null) {
            LogUtil.h("Track_PaceZoneUtils", "tempTimeMap null");
            return;
        }
        if (map.get(Long.valueOf(j)) == null || i < 0) {
            return;
        }
        int intValue = map.get(Long.valueOf(j)).intValue();
        iArr[intValue] = iArr[intValue] + i;
        int intValue2 = map.get(Long.valueOf(j)).intValue();
        iArr2[intValue2] = iArr2[intValue2] + 1;
    }

    private static void d(float[] fArr, float[] fArr2, long j, float f, Map<Long, Integer> map) {
        if (map == null) {
            LogUtil.h("Track_PaceZoneUtils", "tempTimeMap null");
            return;
        }
        if (map.get(Long.valueOf(j)) == null || f < 0.0f) {
            return;
        }
        int intValue = map.get(Long.valueOf(j)).intValue();
        fArr[intValue] = fArr[intValue] + f;
        int intValue2 = map.get(Long.valueOf(j)).intValue();
        fArr2[intValue2] = fArr2[intValue2] + 1.0f;
    }

    private static void b(List<koh> list, Map<Long, Integer> map, String str) {
        int d;
        ffg ffgVar = new ffg();
        ffgVar.c(str);
        if (list == null || map == null) {
            LogUtil.b("Track_PaceZoneUtils", "rtPaceList  or tempTimeMap is null");
            return;
        }
        for (koh kohVar : list) {
            if (kohVar != null && (d = ffgVar.d(kohVar.a())) != -1) {
                map.put(Long.valueOf(kohVar.acquireTime()), Integer.valueOf(d));
            }
        }
    }

    private static void a(String str, int[] iArr) {
        if (str == null) {
            LogUtil.b("Track_PaceZoneUtils", "paceZoneTimeStatistic is null");
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() != 5) {
                LogUtil.b("Track_PaceZoneUtils", "jsonArray.length() invalid");
                return;
            }
            for (int i = 0; i < 5; i++) {
                if (jSONArray.get(i) instanceof Integer) {
                    iArr[i] = ((Integer) jSONArray.get(i)).intValue();
                }
            }
        } catch (JSONException e) {
            LogUtil.b("Track_PaceZoneUtils", "transformPaceTimeZoneStatistic:", LogAnonymous.b((Throwable) e));
        }
    }

    private static List<HeartRateData> e(List<HeartRateData> list) {
        ArrayList arrayList = new ArrayList(10);
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                i += list.get(i2).acquireHeartRate();
                if (i2 % 5 == 0) {
                    int ceil = (int) Math.ceil(i / 5.0d);
                    HeartRateData heartRateData = new HeartRateData();
                    heartRateData.saveHeartRate(ceil);
                    heartRateData.saveTime(list.get(i2).acquireTime());
                    arrayList.add(heartRateData);
                    i = 0;
                }
            }
        }
        int size = list.size();
        for (int i3 = 1; i3 < size % 5; i3++) {
            arrayList.add(list.get(list.size() - i3));
        }
        return arrayList;
    }

    private static List<StepRateData> b(List<StepRateData> list) {
        ArrayList arrayList = new ArrayList(10);
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                i += list.get(i2).acquireStepRate();
                if (i2 % 5 == 0) {
                    int ceil = (int) Math.ceil(i / 5.0d);
                    StepRateData stepRateData = new StepRateData();
                    stepRateData.saveStepRate(ceil);
                    stepRateData.saveTime(list.get(i2).acquireTime());
                    arrayList.add(stepRateData);
                    i = 0;
                }
            }
        }
        int size = list.size();
        for (int i3 = 1; i3 < size % 5; i3++) {
            arrayList.add(list.get(list.size() - i3));
        }
        return arrayList;
    }
}
