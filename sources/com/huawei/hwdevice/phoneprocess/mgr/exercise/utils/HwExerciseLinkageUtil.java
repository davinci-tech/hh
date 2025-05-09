package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.ffo;
import defpackage.ffr;
import defpackage.kkm;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class HwExerciseLinkageUtil {
    private static final int CADENCE = 3;
    private static final int CIRCLE_CADENCE = 15;
    private static final int EVERSION_EXCURSION = 11;
    private static final int FOREFOOT_STRIKE_PATTERN = 8;
    private static final int GROUND_CONTACT_TIME = 5;
    private static final int GROUND_IMPACT_ACCERLERATION = 6;
    private static final int HANG_TIME = 13;
    private static final int HIND_PAW_STRIKE_PATTERN = 10;
    private static final int IMPACT_HANG_RATE = 14;
    private static final int SPORT_TYPE_CYCLE = 3;
    private static final int STEP_LENGTH = 4;
    private static final int SWING_ANGLE = 7;
    private static final String TAG = "HwExerciseLinkageUtil";
    private static final int TIME_INFO = 12;
    private static final int WHOLE_FOOT_STRIKE_PATTERN = 9;

    private HwExerciseLinkageUtil() {
    }

    public static Map<String, Object> parseLinkageData(List<cwe> list, int i) {
        if (list == null) {
            LogUtil.h(TAG, "handleRunPostureData tlvFatherList is null");
            return null;
        }
        LogUtil.a(TAG, "parseLinkageData sportType : ", Integer.valueOf(i));
        if (i == 3) {
            return parseCycle(list);
        }
        return parsePosture(list);
    }

    private static Map<String, Object> parseCycle(List<cwe> list) {
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                List<cwd> e = it2.next().e();
                ffo ffoVar = new ffo();
                Iterator<cwd> it3 = e.iterator();
                while (it3.hasNext()) {
                    parseCycleTlv(ffoVar, it3.next());
                }
                arrayList.add(ffoVar);
            }
        }
        return kkm.d(arrayList, "notificationAw70LinkInfo");
    }

    private static void parseCycleTlv(ffo ffoVar, cwd cwdVar) {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 12) {
            ffoVar.a(CommonUtil.w(cwdVar.c()));
        } else if (w == 15) {
            ffoVar.b(CommonUtil.w(cwdVar.c()));
        } else {
            LogUtil.h(TAG, "parseCycleTlv default :", Integer.valueOf(CommonUtil.w(cwdVar.e())));
        }
    }

    private static Map<String, Object> parsePosture(List<cwe> list) {
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                List<cwd> e = it2.next().e();
                ffr ffrVar = new ffr();
                Iterator<cwd> it3 = e.iterator();
                while (it3.hasNext()) {
                    configRunPostureInfoParams(ffrVar, it3.next());
                }
                arrayList.add(ffrVar);
            }
        }
        return kkm.d(arrayList, "notificationAw70LinkInfo");
    }

    private static void configRunPostureInfoParams(ffr ffrVar, cwd cwdVar) {
        if (ffrVar == null || cwdVar == null) {
            LogUtil.h(TAG, "configRunPostureInfoParams params is null");
            return;
        }
        switch (CommonUtil.w(cwdVar.e())) {
            case 3:
                ffrVar.e(CommonUtil.w(cwdVar.c()));
                break;
            case 4:
                ffrVar.f(CommonUtil.w(cwdVar.c()));
                break;
            case 5:
                ffrVar.c(CommonUtil.w(cwdVar.c()));
                break;
            case 6:
                ffrVar.d(CommonUtil.w(cwdVar.c()));
                break;
            case 7:
                ffrVar.n(CommonUtil.w(cwdVar.c()));
                break;
            case 8:
                ffrVar.a(CommonUtil.w(cwdVar.c()));
                break;
            case 9:
                ffrVar.o(CommonUtil.w(cwdVar.c()));
                break;
            case 10:
                ffrVar.j(CommonUtil.w(cwdVar.c()));
                break;
            case 11:
                ffrVar.b(CommonUtil.w(cwdVar.c()));
                break;
            case 12:
                ffrVar.c(CommonUtil.w(cwdVar.c()));
                break;
            case 13:
                ffrVar.i(CommonUtil.w(cwdVar.c()));
                break;
            case 14:
                ffrVar.h(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.h(TAG, "default :", Integer.valueOf(CommonUtil.w(cwdVar.e())));
                break;
        }
    }
}
