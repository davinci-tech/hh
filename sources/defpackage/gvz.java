package defpackage;

import android.text.TextUtils;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class gvz {
    public static MotionData c(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        int requestTotalDistance;
        if (motionPath == null || motionPathSimplify == null) {
            LogUtil.a("Track_ConvertDataUtils", "motionPath or motionPathSimplify is null");
            return null;
        }
        MotionData motionData = new MotionData();
        motionData.setTotalTime(motionPathSimplify.requestTotalTime());
        if (motionPathSimplify.requestSportType() == 222) {
            requestTotalDistance = motionPathSimplify.getExtendDataInt("wayPointDistance");
        } else {
            requestTotalDistance = motionPathSimplify.requestTotalDistance();
        }
        motionData.setTotalDistance(requestTotalDistance);
        motionData.setTotalCalories(motionPathSimplify.requestTotalCalories());
        motionData.setSportType(motionPathSimplify.requestSportType());
        motionData.setHeartRateList(motionPath.requestHeartRateList());
        motionData.setPaceMap(motionPath.requestPaceMap());
        motionData.setBritishPaceMap(motionPath.requestBritishPaceMap());
        motionData.setLbsDataMap(motionPath.requestLbsDataMap());
        motionData.setStepRateList(motionPath.requestStepRateList());
        motionData.saveAltitudeList(motionPath.requestAltitudeList());
        motionData.saveMarkPointList(motionPath.requestMarkPointList());
        motionData.saveInvalidHeartRateList(motionPath.requestInvalidHeartRateList());
        motionData.saveRunningPostureList(motionPath.requestRunningPostureList());
        motionData.saveStartSteps(motionPathSimplify.requireStartSteps());
        motionData.saveRealTimeSteps(motionPathSimplify.requestRealTimeSteps());
        motionData.saveSegmentList(motionPath.requestSegmentList());
        motionData.setMessageList(motionPath.getMessageList());
        motionData.setExtendDetailList(motionPath.getExtendDetailList());
        motionData.saveRealTimeSpeedList(motionPath.requestSpeedList());
        motionData.saveSpo2List(motionPath.requestSpo2List());
        return motionData;
    }

    public static MotionPath a(String str, int i) {
        TreeMap treeMap;
        String str2;
        ArrayList arrayList;
        ArrayList<MarkPoint> arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        ArrayList<ffs> arrayList7;
        ArrayList arrayList8;
        ArrayList arrayList9;
        TreeMap treeMap2 = new TreeMap();
        TreeMap treeMap3 = new TreeMap();
        TreeMap treeMap4 = new TreeMap();
        TreeMap treeMap5 = new TreeMap();
        TreeMap treeMap6 = new TreeMap();
        ArrayList<HeartRateData> arrayList10 = new ArrayList<>(16);
        ArrayList<StepRateData> arrayList11 = new ArrayList<>(16);
        ArrayList<knz> arrayList12 = new ArrayList<>(16);
        ArrayList<MarkPoint> arrayList13 = new ArrayList<>(16);
        ArrayList arrayList14 = new ArrayList(16);
        ArrayList<HeartRateData> arrayList15 = new ArrayList<>(16);
        ArrayList arrayList16 = new ArrayList(16);
        ArrayList arrayList17 = new ArrayList(16);
        String str3 = "Track_ConvertDataUtils";
        ArrayList arrayList18 = new ArrayList(16);
        ArrayList arrayList19 = arrayList14;
        ArrayList arrayList20 = new ArrayList(16);
        ArrayList<MarkPoint> arrayList21 = arrayList13;
        ArrayList<ffs> arrayList22 = new ArrayList<>(16);
        ArrayList arrayList23 = arrayList20;
        ArrayList arrayList24 = new ArrayList(16);
        ArrayList<ffs> arrayList25 = arrayList22;
        ArrayList arrayList26 = new ArrayList(16);
        ArrayList arrayList27 = new ArrayList(16);
        ArrayList arrayList28 = new ArrayList(16);
        ArrayList arrayList29 = new ArrayList(16);
        ArrayList arrayList30 = new ArrayList(16);
        Class segmentClass = CommonSegment.getSegmentClass(i);
        ArrayList arrayList31 = new ArrayList(16);
        ArrayList arrayList32 = new ArrayList(16);
        ArrayList arrayList33 = new ArrayList();
        ArrayList arrayList34 = new ArrayList();
        ArrayList arrayList35 = arrayList32;
        ArrayList arrayList36 = new ArrayList(16);
        String[] split = str != null ? str.split(System.lineSeparator()) : new String[1];
        ArrayList arrayList37 = arrayList36;
        int length = split.length;
        ArrayList arrayList38 = arrayList18;
        int i2 = 0;
        while (i2 < length) {
            String str4 = split[i2];
            int i3 = length;
            String[] split2 = str4.split(";");
            String[] strArr = split;
            int length2 = split2.length;
            int i4 = i2;
            String normalize = Normalizer.normalize(str4, Normalizer.Form.NFKC);
            try {
                if (normalize.contains(MotionPath.LBS_DATA_MAP_TAG)) {
                    try {
                        a(treeMap2, split2, length2);
                        treeMap = treeMap2;
                        str2 = str3;
                        arrayList = arrayList19;
                        arrayList2 = arrayList21;
                    } catch (IndexOutOfBoundsException e) {
                        e = e;
                        treeMap = treeMap2;
                        arrayList = arrayList19;
                        arrayList2 = arrayList21;
                        str2 = str3;
                        LogUtil.h(str2, e.getMessage());
                        arrayList19 = arrayList;
                        str3 = str2;
                        arrayList21 = arrayList2;
                        treeMap2 = treeMap;
                        split = strArr;
                        length = i3;
                        i2 = i4 + 1;
                    } catch (NumberFormatException e2) {
                        e = e2;
                        treeMap = treeMap2;
                        arrayList = arrayList19;
                        arrayList2 = arrayList21;
                        str2 = str3;
                        LogUtil.h(str2, e.getMessage());
                        arrayList19 = arrayList;
                        str3 = str2;
                        arrayList21 = arrayList2;
                        treeMap2 = treeMap;
                        split = strArr;
                        length = i3;
                        i2 = i4 + 1;
                    }
                } else {
                    int i5 = 3;
                    TreeMap treeMap7 = treeMap2;
                    if (length2 >= 3) {
                        try {
                            if (normalize.contains(MotionPath.PACE_MAP_TAG)) {
                                try {
                                    treeMap3.put(Integer.valueOf(Integer.parseInt(split2[1].split("=")[1])), Float.valueOf(Float.parseFloat(split2[2].split("=")[1])));
                                    arrayList3 = arrayList31;
                                    arrayList4 = arrayList34;
                                    arrayList5 = arrayList38;
                                    arrayList38 = arrayList5;
                                    arrayList31 = arrayList3;
                                    arrayList34 = arrayList4;
                                    str2 = str3;
                                    arrayList = arrayList19;
                                    arrayList2 = arrayList21;
                                    treeMap = treeMap7;
                                } catch (IndexOutOfBoundsException e3) {
                                    e = e3;
                                    arrayList = arrayList19;
                                    arrayList2 = arrayList21;
                                    treeMap = treeMap7;
                                    str2 = str3;
                                    LogUtil.h(str2, e.getMessage());
                                    arrayList19 = arrayList;
                                    str3 = str2;
                                    arrayList21 = arrayList2;
                                    treeMap2 = treeMap;
                                    split = strArr;
                                    length = i3;
                                    i2 = i4 + 1;
                                } catch (NumberFormatException e4) {
                                    e = e4;
                                    arrayList = arrayList19;
                                    arrayList2 = arrayList21;
                                    treeMap = treeMap7;
                                    str2 = str3;
                                    LogUtil.h(str2, e.getMessage());
                                    arrayList19 = arrayList;
                                    str3 = str2;
                                    arrayList21 = arrayList2;
                                    treeMap2 = treeMap;
                                    split = strArr;
                                    length = i3;
                                    i2 = i4 + 1;
                                }
                            } else {
                                i5 = 3;
                            }
                        } catch (IndexOutOfBoundsException e5) {
                            e = e5;
                            arrayList3 = arrayList31;
                            arrayList4 = arrayList34;
                            arrayList5 = arrayList38;
                            arrayList38 = arrayList5;
                            arrayList31 = arrayList3;
                            arrayList34 = arrayList4;
                            arrayList = arrayList19;
                            arrayList2 = arrayList21;
                            treeMap = treeMap7;
                            str2 = str3;
                            LogUtil.h(str2, e.getMessage());
                            arrayList19 = arrayList;
                            str3 = str2;
                            arrayList21 = arrayList2;
                            treeMap2 = treeMap;
                            split = strArr;
                            length = i3;
                            i2 = i4 + 1;
                        } catch (NumberFormatException e6) {
                            e = e6;
                            arrayList3 = arrayList31;
                            arrayList4 = arrayList34;
                            arrayList5 = arrayList38;
                            arrayList38 = arrayList5;
                            arrayList31 = arrayList3;
                            arrayList34 = arrayList4;
                            arrayList = arrayList19;
                            arrayList2 = arrayList21;
                            treeMap = treeMap7;
                            str2 = str3;
                            LogUtil.h(str2, e.getMessage());
                            arrayList19 = arrayList;
                            str3 = str2;
                            arrayList21 = arrayList2;
                            treeMap2 = treeMap;
                            split = strArr;
                            length = i3;
                            i2 = i4 + 1;
                        }
                    }
                    if (length2 >= i5 && normalize.contains(MotionPath.BRITISH_PACE_MAP_TAG)) {
                        treeMap4.put(Integer.valueOf(Integer.parseInt(split2[1].split("=")[1])), Float.valueOf(Float.parseFloat(split2[2].split("=")[1])));
                    } else {
                        try {
                            if (normalize.contains(MotionPath.HEART_RATE_LIST_TAG)) {
                                b(arrayList10, split2);
                            } else if (normalize.contains(MotionPath.STEP_RATE_LIST_TAG)) {
                                k(arrayList11, split2);
                            } else {
                                int i6 = 3;
                                if (length2 >= 3) {
                                    if (normalize.contains(MotionPath.INTERVAL_NORMAL_PACE_MAP_TAG)) {
                                        treeMap5.put(Integer.valueOf(Integer.parseInt(split2[1].split("=")[1])), Float.valueOf(Float.parseFloat(split2[2].split("=")[1])));
                                    } else {
                                        i6 = 3;
                                    }
                                }
                                if (length2 >= i6 && normalize.contains(MotionPath.INTERVAL_BRITISH_PACE_MAP_TAG)) {
                                    treeMap6.put(Integer.valueOf(Integer.parseInt(split2[1].split("=")[1])), Float.valueOf(Float.parseFloat(split2[2].split("=")[1])));
                                } else if (normalize.contains(MotionPath.ALTITUDE_TAG)) {
                                    e(arrayList12, split2);
                                } else if (normalize.contains(MotionPath.SPO2_TAG)) {
                                    m(arrayList24, split2);
                                } else if (normalize.contains(MotionPath.INVALID_HEART_RATE_LIST_TAG)) {
                                    b(arrayList15, split2);
                                } else if (normalize.contains(MotionPath.SWOLF_TAG)) {
                                    d((List<kol>) arrayList16, split2);
                                } else if (normalize.contains(MotionPath.PULL_FREQ_TAG)) {
                                    a((List<kog>) arrayList17, split2);
                                } else {
                                    if (normalize.contains(MotionPath.REAL_TIME_SPEED_TAG)) {
                                        arrayList5 = arrayList38;
                                        try {
                                            e((List<koe>) arrayList5, split2);
                                        } catch (IndexOutOfBoundsException e7) {
                                            e = e7;
                                            arrayList3 = arrayList31;
                                            arrayList4 = arrayList34;
                                            arrayList38 = arrayList5;
                                            arrayList31 = arrayList3;
                                            arrayList34 = arrayList4;
                                            arrayList = arrayList19;
                                            arrayList2 = arrayList21;
                                            treeMap = treeMap7;
                                            str2 = str3;
                                            LogUtil.h(str2, e.getMessage());
                                            arrayList19 = arrayList;
                                            str3 = str2;
                                            arrayList21 = arrayList2;
                                            treeMap2 = treeMap;
                                            split = strArr;
                                            length = i3;
                                            i2 = i4 + 1;
                                        } catch (NumberFormatException e8) {
                                            e = e8;
                                            arrayList3 = arrayList31;
                                            arrayList4 = arrayList34;
                                            arrayList38 = arrayList5;
                                            arrayList31 = arrayList3;
                                            arrayList34 = arrayList4;
                                            arrayList = arrayList19;
                                            arrayList2 = arrayList21;
                                            treeMap = treeMap7;
                                            str2 = str3;
                                            LogUtil.h(str2, e.getMessage());
                                            arrayList19 = arrayList;
                                            str3 = str2;
                                            arrayList21 = arrayList2;
                                            treeMap2 = treeMap;
                                            split = strArr;
                                            length = i3;
                                            i2 = i4 + 1;
                                        }
                                    } else {
                                        arrayList5 = arrayList38;
                                        try {
                                            if (normalize.contains(MotionPath.POWER_TAG)) {
                                                ArrayList arrayList39 = arrayList29;
                                                try {
                                                    j(arrayList39, split2);
                                                    arrayList29 = arrayList39;
                                                } catch (IndexOutOfBoundsException e9) {
                                                    e = e9;
                                                    arrayList29 = arrayList39;
                                                    arrayList3 = arrayList31;
                                                    arrayList4 = arrayList34;
                                                    arrayList38 = arrayList5;
                                                    arrayList31 = arrayList3;
                                                    arrayList34 = arrayList4;
                                                    arrayList = arrayList19;
                                                    arrayList2 = arrayList21;
                                                    treeMap = treeMap7;
                                                    str2 = str3;
                                                    LogUtil.h(str2, e.getMessage());
                                                    arrayList19 = arrayList;
                                                    str3 = str2;
                                                    arrayList21 = arrayList2;
                                                    treeMap2 = treeMap;
                                                    split = strArr;
                                                    length = i3;
                                                    i2 = i4 + 1;
                                                } catch (NumberFormatException e10) {
                                                    e = e10;
                                                    arrayList29 = arrayList39;
                                                    arrayList3 = arrayList31;
                                                    arrayList4 = arrayList34;
                                                    arrayList38 = arrayList5;
                                                    arrayList31 = arrayList3;
                                                    arrayList34 = arrayList4;
                                                    arrayList = arrayList19;
                                                    arrayList2 = arrayList21;
                                                    treeMap = treeMap7;
                                                    str2 = str3;
                                                    LogUtil.h(str2, e.getMessage());
                                                    arrayList19 = arrayList;
                                                    str3 = str2;
                                                    arrayList21 = arrayList2;
                                                    treeMap2 = treeMap;
                                                    split = strArr;
                                                    length = i3;
                                                    i2 = i4 + 1;
                                                }
                                            } else {
                                                ArrayList arrayList40 = arrayList29;
                                                try {
                                                    if (normalize.contains(MotionPath.SKIP_SPEED_TAG)) {
                                                        arrayList6 = arrayList35;
                                                        try {
                                                            l(arrayList6, split2);
                                                            arrayList29 = arrayList40;
                                                            arrayList9 = arrayList28;
                                                        } catch (IndexOutOfBoundsException e11) {
                                                            e = e11;
                                                            arrayList29 = arrayList40;
                                                            arrayList9 = arrayList28;
                                                            arrayList28 = arrayList9;
                                                            arrayList35 = arrayList6;
                                                            arrayList3 = arrayList31;
                                                            arrayList4 = arrayList34;
                                                            arrayList38 = arrayList5;
                                                            arrayList31 = arrayList3;
                                                            arrayList34 = arrayList4;
                                                            arrayList = arrayList19;
                                                            arrayList2 = arrayList21;
                                                            treeMap = treeMap7;
                                                            str2 = str3;
                                                            LogUtil.h(str2, e.getMessage());
                                                            arrayList19 = arrayList;
                                                            str3 = str2;
                                                            arrayList21 = arrayList2;
                                                            treeMap2 = treeMap;
                                                            split = strArr;
                                                            length = i3;
                                                            i2 = i4 + 1;
                                                        } catch (NumberFormatException e12) {
                                                            e = e12;
                                                            arrayList29 = arrayList40;
                                                            arrayList9 = arrayList28;
                                                            arrayList28 = arrayList9;
                                                            arrayList35 = arrayList6;
                                                            arrayList3 = arrayList31;
                                                            arrayList4 = arrayList34;
                                                            arrayList38 = arrayList5;
                                                            arrayList31 = arrayList3;
                                                            arrayList34 = arrayList4;
                                                            arrayList = arrayList19;
                                                            arrayList2 = arrayList21;
                                                            treeMap = treeMap7;
                                                            str2 = str3;
                                                            LogUtil.h(str2, e.getMessage());
                                                            arrayList19 = arrayList;
                                                            str3 = str2;
                                                            arrayList21 = arrayList2;
                                                            treeMap2 = treeMap;
                                                            split = strArr;
                                                            length = i3;
                                                            i2 = i4 + 1;
                                                        }
                                                    } else {
                                                        arrayList29 = arrayList40;
                                                        arrayList6 = arrayList35;
                                                        try {
                                                            if (normalize.contains(MotionPath.PADDLE_TAG)) {
                                                                arrayList9 = arrayList28;
                                                                try {
                                                                    g(arrayList9, split2);
                                                                } catch (IndexOutOfBoundsException e13) {
                                                                    e = e13;
                                                                    arrayList28 = arrayList9;
                                                                    arrayList35 = arrayList6;
                                                                    arrayList3 = arrayList31;
                                                                    arrayList4 = arrayList34;
                                                                    arrayList38 = arrayList5;
                                                                    arrayList31 = arrayList3;
                                                                    arrayList34 = arrayList4;
                                                                    arrayList = arrayList19;
                                                                    arrayList2 = arrayList21;
                                                                    treeMap = treeMap7;
                                                                    str2 = str3;
                                                                    LogUtil.h(str2, e.getMessage());
                                                                    arrayList19 = arrayList;
                                                                    str3 = str2;
                                                                    arrayList21 = arrayList2;
                                                                    treeMap2 = treeMap;
                                                                    split = strArr;
                                                                    length = i3;
                                                                    i2 = i4 + 1;
                                                                } catch (NumberFormatException e14) {
                                                                    e = e14;
                                                                    arrayList28 = arrayList9;
                                                                    arrayList35 = arrayList6;
                                                                    arrayList3 = arrayList31;
                                                                    arrayList4 = arrayList34;
                                                                    arrayList38 = arrayList5;
                                                                    arrayList31 = arrayList3;
                                                                    arrayList34 = arrayList4;
                                                                    arrayList = arrayList19;
                                                                    arrayList2 = arrayList21;
                                                                    treeMap = treeMap7;
                                                                    str2 = str3;
                                                                    LogUtil.h(str2, e.getMessage());
                                                                    arrayList19 = arrayList;
                                                                    str3 = str2;
                                                                    arrayList21 = arrayList2;
                                                                    treeMap2 = treeMap;
                                                                    split = strArr;
                                                                    length = i3;
                                                                    i2 = i4 + 1;
                                                                }
                                                            } else {
                                                                arrayList35 = arrayList6;
                                                                ArrayList arrayList41 = arrayList28;
                                                                try {
                                                                    if (normalize.contains(MotionPath.RESISTANCE_TAG)) {
                                                                        ArrayList arrayList42 = arrayList26;
                                                                        try {
                                                                            f(arrayList42, split2);
                                                                            arrayList28 = arrayList41;
                                                                            arrayList26 = arrayList42;
                                                                        } catch (IndexOutOfBoundsException e15) {
                                                                            e = e15;
                                                                            arrayList28 = arrayList41;
                                                                            arrayList26 = arrayList42;
                                                                            arrayList3 = arrayList31;
                                                                            arrayList4 = arrayList34;
                                                                            arrayList38 = arrayList5;
                                                                            arrayList31 = arrayList3;
                                                                            arrayList34 = arrayList4;
                                                                            arrayList = arrayList19;
                                                                            arrayList2 = arrayList21;
                                                                            treeMap = treeMap7;
                                                                            str2 = str3;
                                                                            LogUtil.h(str2, e.getMessage());
                                                                            arrayList19 = arrayList;
                                                                            str3 = str2;
                                                                            arrayList21 = arrayList2;
                                                                            treeMap2 = treeMap;
                                                                            split = strArr;
                                                                            length = i3;
                                                                            i2 = i4 + 1;
                                                                        } catch (NumberFormatException e16) {
                                                                            e = e16;
                                                                            arrayList28 = arrayList41;
                                                                            arrayList26 = arrayList42;
                                                                            arrayList3 = arrayList31;
                                                                            arrayList4 = arrayList34;
                                                                            arrayList38 = arrayList5;
                                                                            arrayList31 = arrayList3;
                                                                            arrayList34 = arrayList4;
                                                                            arrayList = arrayList19;
                                                                            arrayList2 = arrayList21;
                                                                            treeMap = treeMap7;
                                                                            str2 = str3;
                                                                            LogUtil.h(str2, e.getMessage());
                                                                            arrayList19 = arrayList;
                                                                            str3 = str2;
                                                                            arrayList21 = arrayList2;
                                                                            treeMap2 = treeMap;
                                                                            split = strArr;
                                                                            length = i3;
                                                                            i2 = i4 + 1;
                                                                        }
                                                                    } else if (normalize.contains(MotionPath.CADENCE_TAG)) {
                                                                        ArrayList arrayList43 = arrayList27;
                                                                        try {
                                                                            a((ArrayList<ffn>) arrayList43, split2);
                                                                            arrayList28 = arrayList41;
                                                                            arrayList27 = arrayList43;
                                                                        } catch (IndexOutOfBoundsException e17) {
                                                                            e = e17;
                                                                            arrayList28 = arrayList41;
                                                                            arrayList27 = arrayList43;
                                                                            arrayList3 = arrayList31;
                                                                            arrayList4 = arrayList34;
                                                                            arrayList38 = arrayList5;
                                                                            arrayList31 = arrayList3;
                                                                            arrayList34 = arrayList4;
                                                                            arrayList = arrayList19;
                                                                            arrayList2 = arrayList21;
                                                                            treeMap = treeMap7;
                                                                            str2 = str3;
                                                                            LogUtil.h(str2, e.getMessage());
                                                                            arrayList19 = arrayList;
                                                                            str3 = str2;
                                                                            arrayList21 = arrayList2;
                                                                            treeMap2 = treeMap;
                                                                            split = strArr;
                                                                            length = i3;
                                                                            i2 = i4 + 1;
                                                                        } catch (NumberFormatException e18) {
                                                                            e = e18;
                                                                            arrayList28 = arrayList41;
                                                                            arrayList27 = arrayList43;
                                                                            arrayList3 = arrayList31;
                                                                            arrayList4 = arrayList34;
                                                                            arrayList38 = arrayList5;
                                                                            arrayList31 = arrayList3;
                                                                            arrayList34 = arrayList4;
                                                                            arrayList = arrayList19;
                                                                            arrayList2 = arrayList21;
                                                                            treeMap = treeMap7;
                                                                            str2 = str3;
                                                                            LogUtil.h(str2, e.getMessage());
                                                                            arrayList19 = arrayList;
                                                                            str3 = str2;
                                                                            arrayList21 = arrayList2;
                                                                            treeMap2 = treeMap;
                                                                            split = strArr;
                                                                            length = i3;
                                                                            i2 = i4 + 1;
                                                                        }
                                                                    } else {
                                                                        if (normalize.contains(MotionPath.RUNNING_POSTURE_TAG)) {
                                                                            arrayList7 = arrayList25;
                                                                            try {
                                                                                h(arrayList7, split2);
                                                                                arrayList28 = arrayList41;
                                                                                arrayList8 = arrayList23;
                                                                            } catch (IndexOutOfBoundsException e19) {
                                                                                e = e19;
                                                                                arrayList28 = arrayList41;
                                                                                arrayList8 = arrayList23;
                                                                                arrayList23 = arrayList8;
                                                                                arrayList25 = arrayList7;
                                                                                arrayList3 = arrayList31;
                                                                                arrayList4 = arrayList34;
                                                                                arrayList38 = arrayList5;
                                                                                arrayList31 = arrayList3;
                                                                                arrayList34 = arrayList4;
                                                                                arrayList = arrayList19;
                                                                                arrayList2 = arrayList21;
                                                                                treeMap = treeMap7;
                                                                                str2 = str3;
                                                                                LogUtil.h(str2, e.getMessage());
                                                                                arrayList19 = arrayList;
                                                                                str3 = str2;
                                                                                arrayList21 = arrayList2;
                                                                                treeMap2 = treeMap;
                                                                                split = strArr;
                                                                                length = i3;
                                                                                i2 = i4 + 1;
                                                                            } catch (NumberFormatException e20) {
                                                                                e = e20;
                                                                                arrayList28 = arrayList41;
                                                                                arrayList8 = arrayList23;
                                                                                arrayList23 = arrayList8;
                                                                                arrayList25 = arrayList7;
                                                                                arrayList3 = arrayList31;
                                                                                arrayList4 = arrayList34;
                                                                                arrayList38 = arrayList5;
                                                                                arrayList31 = arrayList3;
                                                                                arrayList34 = arrayList4;
                                                                                arrayList = arrayList19;
                                                                                arrayList2 = arrayList21;
                                                                                treeMap = treeMap7;
                                                                                str2 = str3;
                                                                                LogUtil.h(str2, e.getMessage());
                                                                                arrayList19 = arrayList;
                                                                                str3 = str2;
                                                                                arrayList21 = arrayList2;
                                                                                treeMap2 = treeMap;
                                                                                split = strArr;
                                                                                length = i3;
                                                                                i2 = i4 + 1;
                                                                            }
                                                                        } else {
                                                                            arrayList28 = arrayList41;
                                                                            arrayList7 = arrayList25;
                                                                            try {
                                                                                if (normalize.contains(MotionPath.JUMP_TAG)) {
                                                                                    arrayList8 = arrayList23;
                                                                                    try {
                                                                                        d((ArrayList<ixt>) arrayList8, split2);
                                                                                    } catch (IndexOutOfBoundsException e21) {
                                                                                        e = e21;
                                                                                        arrayList23 = arrayList8;
                                                                                        arrayList25 = arrayList7;
                                                                                        arrayList3 = arrayList31;
                                                                                        arrayList4 = arrayList34;
                                                                                        arrayList38 = arrayList5;
                                                                                        arrayList31 = arrayList3;
                                                                                        arrayList34 = arrayList4;
                                                                                        arrayList = arrayList19;
                                                                                        arrayList2 = arrayList21;
                                                                                        treeMap = treeMap7;
                                                                                        str2 = str3;
                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                        arrayList19 = arrayList;
                                                                                        str3 = str2;
                                                                                        arrayList21 = arrayList2;
                                                                                        treeMap2 = treeMap;
                                                                                        split = strArr;
                                                                                        length = i3;
                                                                                        i2 = i4 + 1;
                                                                                    } catch (NumberFormatException e22) {
                                                                                        e = e22;
                                                                                        arrayList23 = arrayList8;
                                                                                        arrayList25 = arrayList7;
                                                                                        arrayList3 = arrayList31;
                                                                                        arrayList4 = arrayList34;
                                                                                        arrayList38 = arrayList5;
                                                                                        arrayList31 = arrayList3;
                                                                                        arrayList34 = arrayList4;
                                                                                        arrayList = arrayList19;
                                                                                        arrayList2 = arrayList21;
                                                                                        treeMap = treeMap7;
                                                                                        str2 = str3;
                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                        arrayList19 = arrayList;
                                                                                        str3 = str2;
                                                                                        arrayList21 = arrayList2;
                                                                                        treeMap2 = treeMap;
                                                                                        split = strArr;
                                                                                        length = i3;
                                                                                        i2 = i4 + 1;
                                                                                    }
                                                                                } else {
                                                                                    arrayList25 = arrayList7;
                                                                                    ArrayList arrayList44 = arrayList23;
                                                                                    try {
                                                                                        if (normalize.contains("tp=sec")) {
                                                                                            Class cls = segmentClass;
                                                                                            arrayList23 = arrayList44;
                                                                                            arrayList3 = arrayList31;
                                                                                            try {
                                                                                                a(arrayList3, split2, cls);
                                                                                                segmentClass = cls;
                                                                                            } catch (IndexOutOfBoundsException e23) {
                                                                                                e = e23;
                                                                                                segmentClass = cls;
                                                                                                arrayList4 = arrayList34;
                                                                                                arrayList38 = arrayList5;
                                                                                                arrayList31 = arrayList3;
                                                                                                arrayList34 = arrayList4;
                                                                                                arrayList = arrayList19;
                                                                                                arrayList2 = arrayList21;
                                                                                                treeMap = treeMap7;
                                                                                                str2 = str3;
                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                arrayList19 = arrayList;
                                                                                                str3 = str2;
                                                                                                arrayList21 = arrayList2;
                                                                                                treeMap2 = treeMap;
                                                                                                split = strArr;
                                                                                                length = i3;
                                                                                                i2 = i4 + 1;
                                                                                            } catch (NumberFormatException e24) {
                                                                                                e = e24;
                                                                                                segmentClass = cls;
                                                                                                arrayList4 = arrayList34;
                                                                                                arrayList38 = arrayList5;
                                                                                                arrayList31 = arrayList3;
                                                                                                arrayList34 = arrayList4;
                                                                                                arrayList = arrayList19;
                                                                                                arrayList2 = arrayList21;
                                                                                                treeMap = treeMap7;
                                                                                                str2 = str3;
                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                arrayList19 = arrayList;
                                                                                                str3 = str2;
                                                                                                arrayList21 = arrayList2;
                                                                                                treeMap2 = treeMap;
                                                                                                split = strArr;
                                                                                                length = i3;
                                                                                                i2 = i4 + 1;
                                                                                            }
                                                                                        } else {
                                                                                            arrayList23 = arrayList44;
                                                                                            arrayList3 = arrayList31;
                                                                                            try {
                                                                                                if (normalize.contains(MotionPath.HEART_RATE_RECOVERY_LIST_TAG)) {
                                                                                                    ArrayList arrayList45 = arrayList37;
                                                                                                    try {
                                                                                                        o(arrayList45, split2);
                                                                                                        arrayList37 = arrayList45;
                                                                                                    } catch (IndexOutOfBoundsException e25) {
                                                                                                        e = e25;
                                                                                                        arrayList37 = arrayList45;
                                                                                                        arrayList4 = arrayList34;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    } catch (NumberFormatException e26) {
                                                                                                        e = e26;
                                                                                                        arrayList37 = arrayList45;
                                                                                                        arrayList4 = arrayList34;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    }
                                                                                                } else if (normalize.contains(MotionPath.WEIGHT_TAG)) {
                                                                                                    ArrayList arrayList46 = arrayList30;
                                                                                                    try {
                                                                                                        n(arrayList46, split2);
                                                                                                        arrayList30 = arrayList46;
                                                                                                    } catch (IndexOutOfBoundsException e27) {
                                                                                                        e = e27;
                                                                                                        arrayList30 = arrayList46;
                                                                                                        arrayList4 = arrayList34;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    } catch (NumberFormatException e28) {
                                                                                                        e = e28;
                                                                                                        arrayList30 = arrayList46;
                                                                                                        arrayList4 = arrayList34;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    }
                                                                                                } else if (normalize.contains(MotionPath.EXTEND_TAG)) {
                                                                                                    ArrayList arrayList47 = arrayList33;
                                                                                                    try {
                                                                                                        c((ArrayList<kod>) arrayList47, split2);
                                                                                                        arrayList33 = arrayList47;
                                                                                                    } catch (IndexOutOfBoundsException e29) {
                                                                                                        e = e29;
                                                                                                        arrayList33 = arrayList47;
                                                                                                        arrayList4 = arrayList34;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    } catch (NumberFormatException e30) {
                                                                                                        e = e30;
                                                                                                        arrayList33 = arrayList47;
                                                                                                        arrayList4 = arrayList34;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    }
                                                                                                } else if (normalize.contains(MotionPath.MSG_TAG)) {
                                                                                                    arrayList4 = arrayList34;
                                                                                                    try {
                                                                                                        i(arrayList4, split2);
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        str2 = str3;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                    } catch (IndexOutOfBoundsException e31) {
                                                                                                        e = e31;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    } catch (NumberFormatException e32) {
                                                                                                        e = e32;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList31 = arrayList3;
                                                                                                        arrayList34 = arrayList4;
                                                                                                        arrayList = arrayList19;
                                                                                                        arrayList2 = arrayList21;
                                                                                                        treeMap = treeMap7;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    }
                                                                                                } else if (normalize.contains(MotionPath.MARK_POINT_TAG)) {
                                                                                                    arrayList31 = arrayList3;
                                                                                                    arrayList2 = arrayList21;
                                                                                                    treeMap = treeMap7;
                                                                                                    try {
                                                                                                        d(treeMap, arrayList2, split2);
                                                                                                        arrayList38 = arrayList5;
                                                                                                        str2 = str3;
                                                                                                        arrayList = arrayList19;
                                                                                                    } catch (IndexOutOfBoundsException e33) {
                                                                                                        e = e33;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList = arrayList19;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    } catch (NumberFormatException e34) {
                                                                                                        e = e34;
                                                                                                        arrayList38 = arrayList5;
                                                                                                        arrayList = arrayList19;
                                                                                                        str2 = str3;
                                                                                                        LogUtil.h(str2, e.getMessage());
                                                                                                        arrayList19 = arrayList;
                                                                                                        str3 = str2;
                                                                                                        arrayList21 = arrayList2;
                                                                                                        treeMap2 = treeMap;
                                                                                                        split = strArr;
                                                                                                        length = i3;
                                                                                                        i2 = i4 + 1;
                                                                                                    }
                                                                                                } else {
                                                                                                    arrayList38 = arrayList5;
                                                                                                    arrayList31 = arrayList3;
                                                                                                    arrayList2 = arrayList21;
                                                                                                    treeMap = treeMap7;
                                                                                                    try {
                                                                                                        if (normalize.contains(MotionPath.HRA_TAG)) {
                                                                                                            arrayList = arrayList19;
                                                                                                            try {
                                                                                                                c((List<kny>) arrayList, split2);
                                                                                                                str2 = str3;
                                                                                                            } catch (IndexOutOfBoundsException e35) {
                                                                                                                e = e35;
                                                                                                                str2 = str3;
                                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                                arrayList19 = arrayList;
                                                                                                                str3 = str2;
                                                                                                                arrayList21 = arrayList2;
                                                                                                                treeMap2 = treeMap;
                                                                                                                split = strArr;
                                                                                                                length = i3;
                                                                                                                i2 = i4 + 1;
                                                                                                            } catch (NumberFormatException e36) {
                                                                                                                e = e36;
                                                                                                                str2 = str3;
                                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                                arrayList19 = arrayList;
                                                                                                                str3 = str2;
                                                                                                                arrayList21 = arrayList2;
                                                                                                                treeMap2 = treeMap;
                                                                                                                split = strArr;
                                                                                                                length = i3;
                                                                                                                i2 = i4 + 1;
                                                                                                            }
                                                                                                        } else {
                                                                                                            arrayList = arrayList19;
                                                                                                            str2 = str3;
                                                                                                            try {
                                                                                                                LogUtil.a(str2, "unknown dataType");
                                                                                                            } catch (IndexOutOfBoundsException e37) {
                                                                                                                e = e37;
                                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                                arrayList19 = arrayList;
                                                                                                                str3 = str2;
                                                                                                                arrayList21 = arrayList2;
                                                                                                                treeMap2 = treeMap;
                                                                                                                split = strArr;
                                                                                                                length = i3;
                                                                                                                i2 = i4 + 1;
                                                                                                            } catch (NumberFormatException e38) {
                                                                                                                e = e38;
                                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                                arrayList19 = arrayList;
                                                                                                                str3 = str2;
                                                                                                                arrayList21 = arrayList2;
                                                                                                                treeMap2 = treeMap;
                                                                                                                split = strArr;
                                                                                                                length = i3;
                                                                                                                i2 = i4 + 1;
                                                                                                            }
                                                                                                        }
                                                                                                    } catch (IndexOutOfBoundsException e39) {
                                                                                                        e = e39;
                                                                                                        str2 = str3;
                                                                                                        arrayList = arrayList19;
                                                                                                    } catch (NumberFormatException e40) {
                                                                                                        e = e40;
                                                                                                        str2 = str3;
                                                                                                        arrayList = arrayList19;
                                                                                                    }
                                                                                                }
                                                                                            } catch (IndexOutOfBoundsException e41) {
                                                                                                e = e41;
                                                                                                arrayList38 = arrayList5;
                                                                                                arrayList31 = arrayList3;
                                                                                                str2 = str3;
                                                                                                arrayList = arrayList19;
                                                                                                arrayList2 = arrayList21;
                                                                                                treeMap = treeMap7;
                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                arrayList19 = arrayList;
                                                                                                str3 = str2;
                                                                                                arrayList21 = arrayList2;
                                                                                                treeMap2 = treeMap;
                                                                                                split = strArr;
                                                                                                length = i3;
                                                                                                i2 = i4 + 1;
                                                                                            } catch (NumberFormatException e42) {
                                                                                                e = e42;
                                                                                                arrayList38 = arrayList5;
                                                                                                arrayList31 = arrayList3;
                                                                                                str2 = str3;
                                                                                                arrayList = arrayList19;
                                                                                                arrayList2 = arrayList21;
                                                                                                treeMap = treeMap7;
                                                                                                LogUtil.h(str2, e.getMessage());
                                                                                                arrayList19 = arrayList;
                                                                                                str3 = str2;
                                                                                                arrayList21 = arrayList2;
                                                                                                treeMap2 = treeMap;
                                                                                                split = strArr;
                                                                                                length = i3;
                                                                                                i2 = i4 + 1;
                                                                                            }
                                                                                        }
                                                                                        arrayList4 = arrayList34;
                                                                                        arrayList38 = arrayList5;
                                                                                        arrayList31 = arrayList3;
                                                                                        arrayList34 = arrayList4;
                                                                                        str2 = str3;
                                                                                        arrayList = arrayList19;
                                                                                        arrayList2 = arrayList21;
                                                                                        treeMap = treeMap7;
                                                                                    } catch (IndexOutOfBoundsException e43) {
                                                                                        e = e43;
                                                                                        arrayList38 = arrayList5;
                                                                                        arrayList23 = arrayList44;
                                                                                    } catch (NumberFormatException e44) {
                                                                                        e = e44;
                                                                                        arrayList38 = arrayList5;
                                                                                        arrayList23 = arrayList44;
                                                                                    }
                                                                                }
                                                                            } catch (IndexOutOfBoundsException e45) {
                                                                                e = e45;
                                                                                arrayList38 = arrayList5;
                                                                                arrayList25 = arrayList7;
                                                                            } catch (NumberFormatException e46) {
                                                                                e = e46;
                                                                                arrayList38 = arrayList5;
                                                                                arrayList25 = arrayList7;
                                                                            }
                                                                        }
                                                                        arrayList23 = arrayList8;
                                                                        arrayList25 = arrayList7;
                                                                    }
                                                                } catch (IndexOutOfBoundsException e47) {
                                                                    e = e47;
                                                                    arrayList38 = arrayList5;
                                                                    arrayList28 = arrayList41;
                                                                } catch (NumberFormatException e48) {
                                                                    e = e48;
                                                                    arrayList38 = arrayList5;
                                                                    arrayList28 = arrayList41;
                                                                }
                                                            }
                                                        } catch (IndexOutOfBoundsException e49) {
                                                            e = e49;
                                                            arrayList38 = arrayList5;
                                                            arrayList35 = arrayList6;
                                                        } catch (NumberFormatException e50) {
                                                            e = e50;
                                                            arrayList38 = arrayList5;
                                                            arrayList35 = arrayList6;
                                                        }
                                                    }
                                                    arrayList28 = arrayList9;
                                                    arrayList35 = arrayList6;
                                                } catch (IndexOutOfBoundsException e51) {
                                                    e = e51;
                                                    arrayList38 = arrayList5;
                                                    arrayList29 = arrayList40;
                                                } catch (NumberFormatException e52) {
                                                    e = e52;
                                                    arrayList38 = arrayList5;
                                                    arrayList29 = arrayList40;
                                                }
                                            }
                                        } catch (IndexOutOfBoundsException e53) {
                                            e = e53;
                                            arrayList38 = arrayList5;
                                        } catch (NumberFormatException e54) {
                                            e = e54;
                                            arrayList38 = arrayList5;
                                        }
                                    }
                                    arrayList3 = arrayList31;
                                    arrayList4 = arrayList34;
                                    arrayList38 = arrayList5;
                                    arrayList31 = arrayList3;
                                    arrayList34 = arrayList4;
                                    str2 = str3;
                                    arrayList = arrayList19;
                                    arrayList2 = arrayList21;
                                    treeMap = treeMap7;
                                }
                            }
                        } catch (IndexOutOfBoundsException e55) {
                            e = e55;
                        } catch (NumberFormatException e56) {
                            e = e56;
                        }
                    }
                    arrayList3 = arrayList31;
                    arrayList4 = arrayList34;
                    arrayList5 = arrayList38;
                    arrayList38 = arrayList5;
                    arrayList31 = arrayList3;
                    arrayList34 = arrayList4;
                    str2 = str3;
                    arrayList = arrayList19;
                    arrayList2 = arrayList21;
                    treeMap = treeMap7;
                }
            } catch (IndexOutOfBoundsException e57) {
                e = e57;
                treeMap = treeMap2;
                str2 = str3;
                arrayList = arrayList19;
                arrayList2 = arrayList21;
            } catch (NumberFormatException e58) {
                e = e58;
                treeMap = treeMap2;
                str2 = str3;
                arrayList = arrayList19;
                arrayList2 = arrayList21;
            }
            arrayList19 = arrayList;
            str3 = str2;
            arrayList21 = arrayList2;
            treeMap2 = treeMap;
            split = strArr;
            length = i3;
            i2 = i4 + 1;
        }
        MotionPath motionPath = new MotionPath();
        motionPath.saveLbsDataMap(treeMap2);
        motionPath.savePaceMap(treeMap3);
        motionPath.saveBritishPaceMap(treeMap4);
        motionPath.saveNormalIntervalPaceMap(treeMap5);
        motionPath.saveBritishIntervalPaceMap(treeMap6);
        motionPath.saveHeartRateList(arrayList10);
        motionPath.saveStepRateList(arrayList11);
        motionPath.saveAltitudeList(arrayList12);
        motionPath.saveMarkPointList(arrayList21);
        motionPath.saveSpo2List(arrayList24);
        motionPath.saveInvalidHeartRateList(arrayList15);
        motionPath.savePullFreqList(arrayList17);
        motionPath.saveSwolfList(arrayList16);
        motionPath.saveSpeedList(arrayList38);
        motionPath.saveJumpDataList(arrayList23);
        motionPath.saveRunningPostureList(arrayList25);
        motionPath.savePaddleFrequencyList(arrayList28);
        motionPath.saveSkippingSpeed(arrayList35);
        motionPath.saveRidePostureDataList(arrayList27);
        motionPath.savePowerList(arrayList29);
        motionPath.saveResistanceList(arrayList26);
        motionPath.saveSegmentList(arrayList31);
        motionPath.saveHeartRecoveryRateList(arrayList37);
        motionPath.saveWeightList(arrayList30);
        motionPath.setMessageList(arrayList34);
        motionPath.setExtendDetailList(arrayList33);
        motionPath.setHeartRateAreaList(arrayList19);
        return motionPath;
    }

    private static void n(ArrayList<kom> arrayList, String[] strArr) {
        if (strArr.length < 3 || strArr[1] == null || strArr[2] == null) {
            return;
        }
        try {
            kom komVar = new kom();
            komVar.b(Long.parseLong(strArr[1].split("=")[1]));
            komVar.d(Integer.parseInt(strArr[2].split("=")[1]));
            arrayList.add(komVar);
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructWeightData temp is not long format");
        }
    }

    private static void o(ArrayList<HeartRateData> arrayList, String[] strArr) {
        if (strArr == null || strArr.length < 3) {
            return;
        }
        try {
            HeartRateData heartRateData = new HeartRateData();
            heartRateData.saveTime(Long.parseLong(strArr[1].split("=")[1]));
            heartRateData.saveHeartRate(Integer.parseInt(strArr[2].split("=")[1]));
            arrayList.add(heartRateData);
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "consturctHeartRateRecoveryData temp is not long format");
        }
    }

    private static <T extends CommonSegment> T a(List<CommonSegment> list, String[] strArr, Class<T> cls) {
        try {
            T newInstance = cls.newInstance();
            if ((newInstance instanceof kwu) || strArr.length >= newInstance.getFieldNum()) {
                newInstance.fromTrackString(strArr);
                list.add(newInstance);
            }
            newInstance.fromTrackString(strArr);
            return newInstance;
        } catch (IllegalAccessException | InstantiationException e) {
            LogUtil.h("Track_ConvertDataUtils", e.getMessage());
            return null;
        }
    }

    private static void m(ArrayList<kof> arrayList, String[] strArr) {
        try {
            arrayList.add(new kof(Long.parseLong(strArr[1].split("=")[1]), Integer.parseInt(strArr[2].split("=")[1])));
        } catch (NumberFormatException e) {
            LogUtil.b("Track_ConvertDataUtils", "NumberFormatException = ", LogAnonymous.b((Throwable) e));
        }
    }

    private static void a(Map<Long, double[]> map, String[] strArr, int i) {
        if (i >= 2) {
            double[] dArr = new double[i - 2];
            try {
                long parseLong = Long.parseLong(strArr[1].split("=")[1]);
                for (int i2 = 2; i2 < i; i2++) {
                    dArr[i2 - 2] = Double.parseDouble(strArr[i2].split("=")[1]);
                }
                map.put(Long.valueOf(parseLong), dArr);
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructLocationBaseData temp is not long format");
            }
        }
    }

    private static void e(ArrayList<knz> arrayList, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                arrayList.add(new knz(Long.parseLong(strArr[1].split("=")[1]), Double.parseDouble(strArr[2].split("=")[1])));
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructAltitudeData temp is not long format");
            }
        }
    }

    private static void d(Map<Long, double[]> map, ArrayList<MarkPoint> arrayList, String[] strArr) {
        if (strArr.length > 7) {
            try {
                MarkPoint markPoint = new MarkPoint();
                markPoint.e(Integer.parseInt(strArr[1].split("=")[1]));
                markPoint.c(Integer.parseInt(strArr[2].split("=")[1]));
                markPoint.d(Integer.parseInt(strArr[3].split("=")[1]));
                markPoint.a(Long.parseLong(strArr[4].split("=")[1]));
                markPoint.c(Double.parseDouble(strArr[5].split("=")[1]));
                markPoint.a(Double.parseDouble(strArr[6].split("=")[1]));
                markPoint.b(Integer.parseInt(strArr[7].split("=")[1]));
                arrayList.add(markPoint);
                map.put(Long.valueOf(markPoint.acquireTime()), new double[]{markPoint.d(), markPoint.a()});
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructAltitudeData temp is not long format");
            }
        }
    }

    private static void d(List<kol> list, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                list.add(new kol(Long.parseLong(strArr[1].split("=")[1]), Integer.parseInt(strArr[2].split("=")[1])));
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructSwolfData temp is not long format");
            }
        }
    }

    private static void a(List<kog> list, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                list.add(new kog(Long.parseLong(strArr[1].split("=")[1]), Integer.parseInt(strArr[2].split("=")[1])));
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructPullFreqData temp is not long format");
            }
        }
    }

    private static void e(List<koe> list, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                list.add(new koe(Long.parseLong(strArr[1].split("=")[1]), Float.parseFloat(strArr[2].split("=")[1])));
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructRealTimeSpeed temp is not long format");
            }
        }
    }

    private static void k(ArrayList<StepRateData> arrayList, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                StepRateData stepRateData = new StepRateData();
                stepRateData.saveTime(Long.parseLong(strArr[1].split("=")[1]));
                stepRateData.saveStepRate(Integer.parseInt(strArr[2].split("=")[1]));
                arrayList.add(stepRateData);
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructStepRateData temp is not long format");
            }
        }
    }

    private static void g(ArrayList<knw> arrayList, String[] strArr) {
        if (strArr.length < 3 || strArr[1] == null || strArr[2] == null) {
            return;
        }
        try {
            knw knwVar = new knw();
            knwVar.a(Long.parseLong(strArr[1].split("=")[1]));
            knwVar.b(Float.parseFloat(strArr[2].split("=")[1]));
            arrayList.add(knwVar);
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructPaddleData temp is not long format");
        }
    }

    private static void l(ArrayList<kob> arrayList, String[] strArr) {
        if (strArr.length < 3 || strArr[1] == null || strArr[2] == null) {
            return;
        }
        try {
            kob kobVar = new kob();
            kobVar.d(Long.parseLong(strArr[1].split("=")[1]));
            kobVar.c(Integer.parseInt(strArr[2].split("=")[1]));
            arrayList.add(kobVar);
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructPaddleData temp is not long format");
        }
    }

    private static void j(ArrayList<koc> arrayList, String[] strArr) {
        if (strArr.length < 3 || strArr[1] == null || strArr[2] == null) {
            return;
        }
        try {
            koc kocVar = new koc();
            kocVar.c(Long.parseLong(strArr[1].split("=")[1]));
            kocVar.c(Integer.parseInt(strArr[2].split("=")[1]));
            arrayList.add(kocVar);
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructPowerData temp is not long format");
        }
    }

    private static void f(ArrayList<Integer> arrayList, String[] strArr) {
        String str;
        if (strArr.length < 1 || (str = strArr[1]) == null) {
            return;
        }
        try {
            arrayList.add(Integer.valueOf(Integer.parseInt(str.split("=")[1])));
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructResistanceData temp is not int format");
        }
    }

    private static void a(ArrayList<ffn> arrayList, String[] strArr) {
        if (strArr.length < 3 || strArr[1] == null || strArr[2] == null) {
            return;
        }
        try {
            ffn ffnVar = new ffn();
            ffnVar.c(Long.parseLong(strArr[1].split("=")[1]));
            ffnVar.e(Integer.parseInt(strArr[2].split("=")[1]));
            arrayList.add(ffnVar);
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructCadenceRateData temp is not long format");
        }
    }

    private static void b(ArrayList<HeartRateData> arrayList, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                HeartRateData heartRateData = new HeartRateData();
                heartRateData.saveTime(Long.parseLong(strArr[1].split("=")[1]));
                heartRateData.saveHeartRate(Integer.parseInt(strArr[2].split("=")[1]));
                arrayList.add(heartRateData);
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructHeartData temp is not long format");
            }
        }
    }

    private static void h(ArrayList<ffs> arrayList, String[] strArr) {
        if (strArr.length >= 9) {
            try {
                ffs ffsVar = new ffs();
                ffsVar.e(Long.parseLong(strArr[1].split("=")[1]));
                ffsVar.a(Integer.parseInt(strArr[2].split("=")[1]));
                ffsVar.d(Integer.parseInt(strArr[3].split("=")[1]));
                ffsVar.h(Integer.parseInt(strArr[4].split("=")[1]));
                ffsVar.c(Integer.parseInt(strArr[5].split("=")[1]));
                ffsVar.b(Integer.parseInt(strArr[6].split("=")[1]));
                ffsVar.f(Integer.parseInt(strArr[7].split("=")[1]));
                ffsVar.e(Integer.parseInt(strArr[8].split("=")[1]));
                if (strArr.length >= 11) {
                    ffsVar.g(Integer.parseInt(strArr[9].split("=")[1]));
                    ffsVar.i(Integer.parseInt(strArr[10].split("=")[1]));
                }
                if (strArr.length > 14) {
                    ffsVar.a(Float.parseFloat(strArr[11].split("=")[1]));
                    ffsVar.e(Float.parseFloat(strArr[12].split("=")[1]));
                    ffsVar.b(Float.parseFloat(strArr[13].split("=")[1]));
                    ffsVar.c(Float.parseFloat(strArr[14].split("=")[1]));
                }
                arrayList.add(ffsVar);
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ConvertDataUtils", "constructRunningPosture temp is not long format");
            }
        }
    }

    private static void i(ArrayList<koa> arrayList, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                arrayList.add(new koa(strArr));
            } catch (NumberFormatException e) {
                LogUtil.b("Track_ConvertDataUtils", "constructMessageList ", LogAnonymous.b((Throwable) e));
            }
        }
    }

    private static void c(ArrayList<kod> arrayList, String[] strArr) {
        if (strArr.length >= 3) {
            try {
                arrayList.add(new kod(strArr));
            } catch (NumberFormatException e) {
                LogUtil.b("Track_ConvertDataUtils", "constructExtendList ", LogAnonymous.b((Throwable) e));
            }
        }
    }

    private static void d(ArrayList<ixt> arrayList, String[] strArr) {
        try {
            arrayList.add(new ixt(Long.parseLong(strArr[1].split("=")[1]), Integer.parseInt(strArr[2].split("=")[1]), Integer.parseInt(strArr[3].split("=")[1])));
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructJumpList temp format is wrong");
        }
    }

    private static void c(List<kny> list, String[] strArr) {
        try {
            list.add(new kny(CommonUtils.h(strArr[1].split("=")[1]), CommonUtils.j(strArr[2].split("=")[1]), CommonUtils.j(strArr[3].split("=")[1]), CommonUtils.j(strArr[4].split("=")[1]), CommonUtils.j(strArr[5].split("=")[1])));
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_ConvertDataUtils", "constructJumpList temp format is wrong");
        }
    }

    public static List<gyc> b(String str) {
        ArrayList arrayList = new ArrayList(16);
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split(System.lineSeparator())) {
            String[] split = str2.split("#");
            try {
                if (split.length > 0) {
                    gyc gycVar = new gyc();
                    gycVar.e(Long.parseLong(split[0]));
                    gycVar.d(Long.parseLong(split[1]));
                    gycVar.d(Float.parseFloat(split[2]));
                    gycVar.a(Float.parseFloat(split[3]));
                    arrayList.add(gycVar);
                }
            } catch (IndexOutOfBoundsException e) {
                LogUtil.h("Track_ConvertDataUtils", e.getMessage());
            } catch (NumberFormatException e2) {
                LogUtil.h("Track_ConvertDataUtils", e2.getMessage());
            }
        }
        return arrayList;
    }

    public static List<ffn> e(String str) {
        ArrayList arrayList = new ArrayList(16);
        for (String str2 : str != null ? str.split(System.lineSeparator()) : new String[1]) {
            String[] split = str2.split(";");
            try {
                if (Normalizer.normalize(str2, Normalizer.Form.NFKC).contains(MotionPath.CADENCE_TAG)) {
                    a((ArrayList<ffn>) arrayList, split);
                } else {
                    LogUtil.b("Track_ConvertDataUtils", "constructMotionPathToCadence unknown dataType");
                }
            } catch (NumberFormatException e) {
                LogUtil.h("Track_ConvertDataUtils", LogAnonymous.b((Throwable) e));
            }
        }
        return arrayList;
    }
}
