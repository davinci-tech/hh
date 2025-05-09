package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.model.OperatorStatus;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.model.TrackSwimSegment;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManagerHelper;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseParams;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwLinkageServiceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.LastSyncTimestampDb;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.CommonSectionInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanParameter;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanRecord;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanRecordInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanRecordStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.StatisticExtendDataStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.DetailsFieldParser;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.hwsportmodel.CommonSegment;
import defpackage.blt;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jqj;
import defpackage.jsz;
import defpackage.juc;
import defpackage.jyp;
import defpackage.kbu;
import defpackage.kbx;
import defpackage.kkj;
import defpackage.kkm;
import defpackage.moj;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseRunPlanUtil {
    private static final int DICT_TYPE = 30288;
    private static final int END_TIME_INDEX = 1;
    private static final int FILE_TYPE = 22;
    private static final int LENGTH_REQUEST_TIME = 2;
    private static final int METER_TO_CENTIMETER = 100;
    private static final int START_TIME_INDEX = 0;
    private static final String TAG = "HwExerciseRunPlanUtil";
    private static final String TAG_RELEASE = "R_HwExerciseRunPlanUtil";
    private static String mDivingFileName;
    private static String mSequenceFileName;
    private static final Object LOCK_OBJECT = new Object();
    private static List<IBaseResponseCallback> sRunPlanParameterCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sSetRunPlanCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sSetRunPlanReminderSwitchCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetRunPlanRecordCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetRunPlanRecordInfoCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sNotificationRunPlanRecordInfoCallbackList = new ArrayList(16);

    private HwExerciseRunPlanUtil() {
    }

    public static void parseGetRunPlanParam(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            responseRunPlanParam(CommonUtil.w(list.get(0).c()));
            return;
        }
        RunPlanParameter runPlanParameter = new RunPlanParameter();
        if (list2 == null || list2.isEmpty()) {
            responseRunPlanParam(100001);
            return;
        }
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            setRunPlanParam(runPlanParameter, it.next().e());
        }
        synchronized (getRunPlanParameterCallbackList()) {
            if (!sRunPlanParameterCallbackList.isEmpty()) {
                int runPlanSyncSize = runPlanParameter.getRunPlanSyncSize();
                runPlanParameter.setRunPlanSyncSizePre(runPlanSyncSize / 256);
                runPlanParameter.setRunPlanSyncSizeSub(runPlanSyncSize % 256);
                sRunPlanParameterCallbackList.get(0).d(100000, kkm.d(runPlanParameter, "getRunPlanParameterforhealth"));
                sRunPlanParameterCallbackList.remove(0);
            }
        }
    }

    private static void setRunPlanParam(RunPlanParameter runPlanParameter, List<cwd> list) {
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 2) {
                runPlanParameter.setRunPlanTotalSign(cvx.e(cwdVar.c()));
            } else if (w == 3) {
                runPlanParameter.setRunPlanSign(cwdVar.c());
            } else if (w == 4) {
                runPlanParameter.setRunPlanAlgorithmType(CommonUtil.w(cwdVar.c()));
            } else if (w == 5) {
                runPlanParameter.setRunPlanAlgorithmVersion(cvx.e(cwdVar.c()));
            } else if (w == 6) {
                runPlanParameter.setRunPlanSyncSize(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.h(TAG, "no support tag.");
            }
        }
    }

    private static void responseRunPlanParam(int i) {
        synchronized (getRunPlanParameterCallbackList()) {
            if (!sRunPlanParameterCallbackList.isEmpty()) {
                sRunPlanParameterCallbackList.get(0).d(i, kkm.d(Integer.valueOf(i), "getRunPlanParameterforhealth"));
                sRunPlanParameterCallbackList.remove(0);
            }
        }
    }

    public static void parseSetRunPlan(List<cwd> list) {
        int i = 0;
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 127) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                LogUtil.h(TAG, "no support tlvList");
            }
        }
        synchronized (getSetRunPlanCallbackList()) {
            if (!sSetRunPlanCallbackList.isEmpty()) {
                sSetRunPlanCallbackList.get(0).d(i, kkm.d(Integer.valueOf(i), "setRunPlan"));
                sSetRunPlanCallbackList.remove(0);
            }
        }
    }

    public static void parseRunPlanSwitch(List<cwd> list) {
        int i = 0;
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 127) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                LogUtil.h(TAG, "no support tlvList.");
            }
        }
        synchronized (getSetRunPlanReminderSwitchCallbackList()) {
            if (!sSetRunPlanReminderSwitchCallbackList.isEmpty()) {
                sSetRunPlanReminderSwitchCallbackList.get(0).d(i, kkm.d(Integer.valueOf(i), "setRunPlanReminderSwitch"));
                sSetRunPlanReminderSwitchCallbackList.remove(0);
            }
        }
    }

    public static void parseRunPlanRecord(List<cwd> list, List<cwe> list2) {
        if (!list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetRunPlanRecordCallbackList()) {
                if (!sGetRunPlanRecordCallbackList.isEmpty()) {
                    int w = CommonUtil.w(list.get(0).c());
                    sGetRunPlanRecordCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getRunPlanRecord"));
                    sGetRunPlanRecordCallbackList.remove(0);
                }
            }
            return;
        }
        RunPlanRecord runPlanRecord = new RunPlanRecord();
        ArrayList arrayList = new ArrayList(16);
        for (cwe cweVar : list2) {
            for (cwd cwdVar : cweVar.e()) {
                if (CommonUtil.w(cwdVar.e()) == 2) {
                    runPlanRecord.setRunPlanRecordCount(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.h(TAG, "no support tlv.");
                }
            }
            Iterator<cwe> it = cweVar.a().iterator();
            while (it.hasNext()) {
                parseAddRunPlanRecordStruct(arrayList, it.next());
            }
        }
        if (!arrayList.isEmpty()) {
            TriathlonUtils.getInstance().saveRunPlayLastRecordId(arrayList.get(arrayList.size() - 1).getRunPlanRecordId());
        }
        runPlanRecord.setRunPlanRecordStructList(arrayList);
        transferRunPlanRecord(runPlanRecord);
    }

    private static void transferRunPlanRecord(RunPlanRecord runPlanRecord) {
        synchronized (getGetRunPlanRecordCallbackList()) {
            if (!sGetRunPlanRecordCallbackList.isEmpty()) {
                sGetRunPlanRecordCallbackList.get(0).d(100000, kkm.d(runPlanRecord, "getRunPlanRecord"));
                sGetRunPlanRecordCallbackList.remove(0);
            } else {
                LogUtil.h(TAG, "parseRunPlanRecord sGetRunPlanRecordCallbackList is empty.");
                if (HwExerciseParams.getInstance().isDetailSyncing()) {
                    HwExerciseParams.getInstance().setIsDetailSyncing(false);
                }
            }
        }
    }

    private static void parseAddRunPlanRecordStruct(List<RunPlanRecordStruct> list, cwe cweVar) {
        RunPlanRecordStruct runPlanRecordStruct = new RunPlanRecordStruct();
        for (cwd cwdVar : cweVar.e()) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 6:
                    runPlanRecordStruct.setRunPlanWorkoutId(CommonUtil.w(cwdVar.c()));
                    break;
                case 7:
                    runPlanRecordStruct.setRunPlanRecordId(CommonUtil.w(cwdVar.c()));
                    break;
                case 8:
                    runPlanRecordStruct.setRunPlanIndexCount(CommonUtil.w(cwdVar.c()));
                    break;
                case 9:
                    runPlanRecordStruct.setPaceIndexCount(CommonUtil.w(cwdVar.c()));
                    break;
                default:
                    LogUtil.h(TAG, "no support tlv.");
                    break;
            }
        }
        list.add(runPlanRecordStruct);
    }

    public static void parseRunPlanRecordInfo(List<cwd> list, List<cwe> list2) {
        if (!list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetRunPlanRecordInfoCallbackList()) {
                if (!sGetRunPlanRecordInfoCallbackList.isEmpty()) {
                    int w = CommonUtil.w(list.get(0).c());
                    sGetRunPlanRecordInfoCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getRunPlanRecordInfo"));
                    sGetRunPlanRecordInfoCallbackList.remove(0);
                }
            }
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            addRunPlanRecordInfo(arrayList, it.next());
        }
        synchronized (getGetRunPlanRecordInfoCallbackList()) {
            if (!sGetRunPlanRecordInfoCallbackList.isEmpty()) {
                sGetRunPlanRecordInfoCallbackList.get(0).d(100000, kkm.d(arrayList, "getRunPlanRecordInfo"));
                sGetRunPlanRecordInfoCallbackList.remove(0);
            }
        }
    }

    private static void addRunPlanRecordInfo(List<RunPlanRecordInfo> list, cwe cweVar) {
        List<cwd> e = cweVar.e();
        RunPlanRecordInfo runPlanRecordInfo = new RunPlanRecordInfo();
        for (cwd cwdVar : e) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 2:
                    runPlanRecordInfo.setRunPlanRecordInfoId(CommonUtil.w(cwdVar.c()));
                    break;
                case 3:
                    runPlanRecordInfo.setRunPlanRecordInfoStatus(CommonUtil.w(cwdVar.c()));
                    break;
                case 4:
                    runPlanRecordInfo.setRunPlanRecordInfoStartTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                    break;
                case 5:
                    try {
                        runPlanRecordInfo.setRunPlanRecordInfoEndTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                        break;
                    } catch (NumberFormatException e2) {
                        ReleaseLogUtil.c(TAG_RELEASE, "setRunPlanRecordInfoEndTime NumberFormatException : ", ExceptionUtils.d(e2));
                        break;
                    }
                case 6:
                    runPlanRecordInfo.setRunPlanRecordInfoCalorie(CommonUtil.w(cwdVar.c()));
                    break;
                case 7:
                    runPlanRecordInfo.setRunPlanRecordInfoDistance(CommonUtil.w(cwdVar.c()));
                    break;
                case 8:
                    runPlanRecordInfo.setRunPlanRecordInfoStep(CommonUtil.w(cwdVar.c()));
                    break;
                case 9:
                    runPlanRecordInfo.setRunPlanRecordInfoTotalTime(CommonUtil.w(cwdVar.c()));
                    break;
                case 10:
                    runPlanRecordInfo.setRunPlanRecordInfoSpeed(CommonUtil.w(cwdVar.c()) / 10.0f);
                    break;
                case 11:
                    runPlanRecordInfo.setRunPlanRecordInfoClimb(CommonUtil.w(cwdVar.c()));
                    break;
                default:
                    addRunPlanRecordInfo(CommonUtil.w(cwdVar.e()), runPlanRecordInfo, cwdVar);
                    break;
            }
        }
        list.add(runPlanRecordInfo);
    }

    private static void addRunPlanRecordInfo(int i, RunPlanRecordInfo runPlanRecordInfo, cwd cwdVar) {
        switch (i) {
            case 12:
                runPlanRecordInfo.setRunPlanRecordInfoHrabsMin(CommonUtil.w(cwdVar.c().substring(0, 2)));
                runPlanRecordInfo.setRunPlanRecordInfoHrabsMax(CommonUtil.w(cwdVar.c().substring(2, 4)));
                break;
            case 13:
                runPlanRecordInfo.setRunPlanRecordInfoLoadPeak(CommonUtil.w(cwdVar.c()));
                break;
            case 14:
                runPlanRecordInfo.setRunPlanRecordEtrainingEffect(CommonUtil.w(cwdVar.c()));
                break;
            case 15:
                runPlanRecordInfo.setRunPlanRecordAchievePercent(CommonUtil.w(cwdVar.c()));
                break;
            case 16:
                runPlanRecordInfo.setRunPlanRecordInfoEpoc(CommonUtil.w(cwdVar.c()));
                break;
            case 17:
                runPlanRecordInfo.setRunPlanRecordInfoMaxMet(CommonUtil.w(cwdVar.c()));
                break;
            case 18:
                runPlanRecordInfo.setRunPlanRecordInfoRecoveryTime(CommonUtil.w(cwdVar.c()));
                break;
            case 19:
            default:
                LogUtil.h(TAG, "no support tlv tag.");
                break;
            case 20:
                runPlanRecordInfo.setRunPlanRecordInfoExerciseDuration(CommonUtil.w(cwdVar.c()) * 1000);
                break;
            case 21:
                runPlanRecordInfo.setRunPlanRecordInfoDateInfo(CommonUtil.w(cwdVar.c()));
                break;
            case 22:
                runPlanRecordInfo.setRunPlanAlgType(CommonUtil.w(cwdVar.c()));
                break;
        }
    }

    public static void parseNotificationRunPlanRecordInfo(List<cwe> list) {
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            RunPlanRecordInfo runPlanRecordInfo = new RunPlanRecordInfo();
            for (cwd cwdVar : e) {
                switch (CommonUtil.w(cwdVar.e())) {
                    case 2:
                        runPlanRecordInfo.setRunPlanRecordInfoWourkoutId(CommonUtil.w(cwdVar.c()));
                        break;
                    case 3:
                        runPlanRecordInfo.setRunPlanRecordInfoStatus(CommonUtil.w(cwdVar.c()));
                        break;
                    case 4:
                        try {
                            runPlanRecordInfo.setRunPlanRecordInfoStartTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                            break;
                        } catch (NumberFormatException e2) {
                            ReleaseLogUtil.c(TAG_RELEASE, "setRunPlanRecordInfoStartTime NumberFormatException : ", ExceptionUtils.d(e2));
                            break;
                        }
                    case 5:
                        runPlanRecordInfo.setRunPlanRecordInfoEndTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                        break;
                    case 6:
                        runPlanRecordInfo.setRunPlanRecordInfoCalorie(CommonUtil.w(cwdVar.c()));
                        break;
                    case 7:
                        runPlanRecordInfo.setRunPlanRecordInfoDistance(CommonUtil.w(cwdVar.c()));
                        break;
                    case 8:
                        runPlanRecordInfo.setRunPlanRecordInfoStep(CommonUtil.w(cwdVar.c()));
                        break;
                    case 9:
                        runPlanRecordInfo.setRunPlanRecordInfoTotalTime(CommonUtil.w(cwdVar.c()));
                        break;
                    default:
                        parseNotificationRunPlanRecordInfo(CommonUtil.w(cwdVar.e()), runPlanRecordInfo, cwdVar);
                        break;
                }
            }
            arrayList.add(runPlanRecordInfo);
        }
        synchronized (getNotificationRunPlanRecordInfoCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sNotificationRunPlanRecordInfoCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(arrayList, "registerNotificationRunPlanRecordInfoCallbackList"));
            }
        }
    }

    private static void parseNotificationRunPlanRecordInfo(int i, RunPlanRecordInfo runPlanRecordInfo, cwd cwdVar) {
        switch (i) {
            case 10:
                runPlanRecordInfo.setRunPlanRecordInfoSpeed(CommonUtil.w(cwdVar.c()) / 10.0f);
                break;
            case 11:
                runPlanRecordInfo.setRunPlanRecordInfoClimb(CommonUtil.w(cwdVar.c()));
                break;
            case 12:
                runPlanRecordInfo.setRunPlanRecordInfoHrabsMin(CommonUtil.w(cwdVar.c().substring(0, 2)));
                runPlanRecordInfo.setRunPlanRecordInfoHrabsMax(CommonUtil.w(cwdVar.c().substring(2, 4)));
                break;
            case 13:
                runPlanRecordInfo.setRunPlanRecordInfoLoadPeak(CommonUtil.w(cwdVar.c()));
                break;
            case 14:
                runPlanRecordInfo.setRunPlanRecordEtrainingEffect(CommonUtil.w(cwdVar.c()));
                break;
            case 15:
                runPlanRecordInfo.setRunPlanRecordAchievePercent(CommonUtil.w(cwdVar.c()));
                break;
            case 16:
                runPlanRecordInfo.setRunPlanRecordInfoEpoc(CommonUtil.w(cwdVar.c()));
                break;
            case 17:
                runPlanRecordInfo.setRunPlanRecordInfoMaxMet(CommonUtil.w(cwdVar.c()));
                break;
            default:
                parseOtherNotificationRunPlanRecordInfo(i, runPlanRecordInfo, cwdVar);
                break;
        }
    }

    private static void parseOtherNotificationRunPlanRecordInfo(int i, RunPlanRecordInfo runPlanRecordInfo, cwd cwdVar) {
        switch (i) {
            case 18:
                runPlanRecordInfo.setRunPlanRecordInfoRecoveryTime(CommonUtil.w(cwdVar.c()));
                break;
            case 19:
                runPlanRecordInfo.setRunPlanRecordInfoDailyScore(CommonUtil.w(cwdVar.c()));
                break;
            case 20:
                runPlanRecordInfo.setRunPlanRecordInfoExerciseDuration(CommonUtil.w(cwdVar.c()) * 1000);
                break;
            case 21:
                runPlanRecordInfo.setRunPlanRecordInfoDateInfo(CommonUtil.w(cwdVar.c()));
                break;
            case 22:
                runPlanRecordInfo.setRunPlanAlgType(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.h(TAG, "no support value.");
                break;
        }
    }

    public static List<IBaseResponseCallback> getRunPlanParameterCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseRunPlanUtil.class) {
            list = sRunPlanParameterCallbackList;
        }
        return list;
    }

    public static List<IBaseResponseCallback> getSetRunPlanCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseRunPlanUtil.class) {
            list = sSetRunPlanCallbackList;
        }
        return list;
    }

    public static List<IBaseResponseCallback> getSetRunPlanReminderSwitchCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseRunPlanUtil.class) {
            list = sSetRunPlanReminderSwitchCallbackList;
        }
        return list;
    }

    public static List<IBaseResponseCallback> getGetRunPlanRecordCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseRunPlanUtil.class) {
            list = sGetRunPlanRecordCallbackList;
        }
        return list;
    }

    public static List<IBaseResponseCallback> getGetRunPlanRecordInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseRunPlanUtil.class) {
            list = sGetRunPlanRecordInfoCallbackList;
        }
        return list;
    }

    public static List<IBaseResponseCallback> getNotificationRunPlanRecordInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseRunPlanUtil.class) {
            list = sNotificationRunPlanRecordInfoCallbackList;
        }
        return list;
    }

    public static void getRunPlanParameter(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (LOCK_OBJECT) {
            if (iBaseResponseCallback == null) {
                return;
            }
            synchronized (getRunPlanParameterCallbackList()) {
                getRunPlanParameterCallbackList().add(iBaseResponseCallback);
            }
            jsz.b(BaseApplication.getContext()).sendDeviceData(HwExerciseCommandUtil.getRunPlanParameterCommand());
        }
    }

    public static void setRunPlanForHealth(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            if (iBaseResponseCallback == null) {
                return;
            }
            synchronized (getSetRunPlanCallbackList()) {
                getSetRunPlanCallbackList().add(iBaseResponseCallback);
            }
            jsz.b(BaseApplication.getContext()).sendDeviceData(HwExerciseCommandUtil.getSetRunPlanForHealthParam(jSONObject));
        }
    }

    public static void setRunPlanReminderSwitch(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            if (iBaseResponseCallback == null) {
                return;
            }
            synchronized (getSetRunPlanReminderSwitchCallbackList()) {
                getSetRunPlanReminderSwitchCallbackList().add(iBaseResponseCallback);
            }
            jsz.b(BaseApplication.getContext()).sendDeviceData(HwExerciseCommandUtil.getRunPlanReminderSwitchCommand(jSONObject));
        }
    }

    public static void getRunPlanRecord(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            if (iBaseResponseCallback == null) {
                return;
            }
            synchronized (getGetRunPlanRecordCallbackList()) {
                getGetRunPlanRecordCallbackList().add(iBaseResponseCallback);
            }
            jsz.b(BaseApplication.getContext()).sendDeviceData(HwExerciseCommandUtil.getRunPlanRecordCommand(jSONObject));
        }
    }

    public static void getRunPlanRecordInfo(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            if (jSONObject == null || iBaseResponseCallback == null) {
                return;
            }
            synchronized (getGetRunPlanRecordInfoCallbackList()) {
                getGetRunPlanRecordInfoCallbackList().add(iBaseResponseCallback);
            }
            jsz.b(BaseApplication.getContext()).sendDeviceData(HwExerciseCommandUtil.getRunPlanRecordInfoCommand(jSONObject));
        }
    }

    public static void parseResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null || bArr == null) {
            LogUtil.h(TAG, "param is null.");
            return;
        }
        blt.d(TAG, bArr, "HwExerciseAdviceManager getResult(): ");
        if (cvt.c(deviceInfo.getProductType())) {
            HwExerciseAdviceAw70Manager.getInstance(HwExerciseAdviceManager.getInstance()).getResult(bArr);
            return;
        }
        if (cwl.b(bArr)) {
            return;
        }
        String d = cvx.d(bArr);
        if (d.length() > 4) {
            try {
                cwe a2 = new cwl().a(d.substring(4, d.length()));
                List<cwd> e = a2.e();
                List<cwe> a3 = a2.a();
                switch (bArr[1]) {
                    case 1:
                        parseGetRunPlanParam(e, a3);
                        break;
                    case 2:
                        parseSetRunPlan(e);
                        break;
                    case 3:
                        parseRunPlanSwitch(e);
                        break;
                    case 4:
                        parseRunPlanRecord(e, a3);
                        break;
                    case 5:
                        parseRunPlanRecordInfo(e, a3);
                        break;
                    case 6:
                        parseNotificationRunPlanRecordInfo(a3);
                        break;
                    default:
                        Object[] objArr = new Object[1];
                        objArr[0] = "unknown tlv tag.";
                        LogUtil.h(TAG, objArr);
                        break;
                }
                return;
            } catch (cwg | NumberFormatException e2) {
                LogUtil.b(TAG, "wrong tlv message: ", ExceptionUtils.d(e2));
                return;
            }
        }
        LogUtil.b(TAG, "tlv message is to short.");
    }

    public static void setMetricUnit(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has("unit")) {
                    LogUtil.a(TAG, "setMetricUnit unit is set to flag ", Boolean.valueOf(jSONObject.getBoolean("unit")));
                    kkj.d(null);
                }
            } catch (JSONException unused) {
                LogUtil.b(TAG, "setMetricUnit JSONException");
            }
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100001, null);
        }
    }

    public static void setLastSyncTime(long j, HwExerciseAdviceManager hwExerciseAdviceManager) {
        if (hwExerciseAdviceManager == null) {
            LogUtil.h(TAG, "setLastSyncTime manager is null.");
        } else {
            new LastSyncTimestampDb().setLastTimestamp(hwExerciseAdviceManager, j);
        }
    }

    public static void notifyDetailSyncComplete(int i, String str, HwExerciseAdviceManager hwExerciseAdviceManager) {
        LogUtil.a(TAG, "notifyDetailSyncComplete errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
        HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
        if (hwExerciseParams.getHwExerciseAdviceManagerHandler() != null) {
            hwExerciseParams.getHwExerciseAdviceManagerHandler().removeMessages(0);
        }
        if (i == 0) {
            hwExerciseAdviceManager.changeRecordSyncMark();
            setLastSyncTime(hwExerciseParams.getCurrentTime(), hwExerciseAdviceManager);
        } else {
            hwExerciseParams.setIsDetailSyncing(false);
        }
    }

    public static boolean handleSaveRunPlayRecord(int i, boolean z, HwExerciseAdviceManager hwExerciseAdviceManager) throws JSONException {
        HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
        if (hwExerciseParams.getRunPlanRecord() != null) {
            JSONArray jSONArray = hwExerciseParams.getRunPlanRecord().getJSONArray(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_RECORD_STRUCT);
            if (jSONArray.length() > 0) {
                z = true;
            }
            JSONArray jSONArray2 = new JSONArray();
            for (JSONObject jSONObject : hwExerciseParams.getWorkoutDetailDataList()) {
                if (i == jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)) {
                    jSONArray2.put(jSONObject);
                }
            }
            JSONArray jSONArray3 = new JSONArray();
            for (JSONObject jSONObject2 : hwExerciseParams.getWorkoutRecordPaceMapList()) {
                if (i == jSONObject2.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)) {
                    jSONArray3.put(jSONObject2);
                }
            }
            LogUtil.a(TAG, "runPlayRecordId: ", Integer.valueOf(i), " detailRunPlayDataArray: ", Integer.valueOf(jSONArray2.length()));
            if (hwExerciseParams.getGpsWorkoutMap().get(Integer.valueOf(i)) != null) {
                LogUtil.a(TAG, "mGpsWorkoutMap size: ", Integer.valueOf(hwExerciseParams.getGpsWorkoutMap().get(Integer.valueOf(i)).size()));
            }
            hwExerciseParams.setSaveDataItemNumber(hwExerciseParams.getSaveDataItemNumber() + 1);
            int intValue = hwExerciseParams.getGpsWorkoutAndRunPlanTypeMap().get(Integer.valueOf(i)) != null ? hwExerciseParams.getGpsWorkoutAndRunPlanTypeMap().get(Integer.valueOf(i)).intValue() : -1;
            int workoutId = getWorkoutId(jSONArray, i, 0);
            JSONObject jSONObject3 = hwExerciseParams.getRunPlanRecordsStatisticArray().get(i);
            hwExerciseParams.getWorkoutParamMap().put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, Integer.valueOf(workoutId));
            hwExerciseParams.getWorkoutParamMap().put(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE, Integer.valueOf(intValue));
            hwExerciseParams.getWorkoutParamMap().put(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID, Integer.valueOf(i));
            HwExerciseAdviceManagerHelper.getInstance().saveDataToTrack(jSONObject3, jSONArray2, hwExerciseParams.getGpsWorkoutMap().get(Integer.valueOf(i)), jSONArray3, hwExerciseParams.getWorkoutParamMap());
        }
        return z;
    }

    private static int getWorkoutId(JSONArray jSONArray, int i, int i2) throws JSONException {
        int length = jSONArray.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (i == jSONArray.getJSONObject(i3).getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_RECORD_ID)) {
                return jSONArray.getJSONObject(i3).getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_ID);
            }
        }
        return i2;
    }

    public static void handleNotRunToTrackSportData(JSONObject jSONObject, Map<String, Integer> map) throws JSONException {
        map.put("record_id", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)));
        map.put("status", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_STATUS)));
        map.put("load_peak", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_LOAD_PEAK)));
        map.put("etraining_effect", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_ETRAINING_EFFECT)));
        map.put("extra_poc", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_EPOC)));
        map.put("max_met", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_MAX_MET)));
        map.put("recovery_time", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECOVERY_TIME)));
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_TYPE) != -1) {
            map.put("swim_stroke", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_TYPE)));
        }
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES) != -1) {
            map.put(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES, Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES)));
        }
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_PULL_RATE) != -1) {
            map.put("swim_pull_freq", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_PULL_RATE)));
        }
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH) != -1) {
            map.put(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH, Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH)));
        }
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_TRIP_TIMES) != -1) {
            map.put("swim_laps", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_TRIP_TIMES)));
        }
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF) != -1) {
            map.put(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF, Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF)));
        }
        map.put("anaerobic_exercise_etraining_effect", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_ANAEROBIC_TRAINING_EFFECT)));
    }

    private static void handleNotRunToTrackSectionStruct(List<JSONArray> list, List<TrackSwimSegment> list2, List<TrackSwimSegment> list3, int i) throws JSONException {
        JSONArray jSONArray = list.get(i);
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            TrackSwimSegment trackSwimSegment = new TrackSwimSegment();
            trackSwimSegment.saveSegmentIndex(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SECTION_NUM));
            trackSwimSegment.saveDuration(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_TIME));
            trackSwimSegment.savePace(jSONObject.getInt("pace"));
            trackSwimSegment.savePullTimes(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES));
            trackSwimSegment.saveSwolf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF));
            trackSwimSegment.saveStrokeType(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_TYPE));
            trackSwimSegment.saveLocationIndex(jSONObject.getInt(HwExerciseConstants.JSON_NAME_POINT_INDEX));
            int i3 = jSONObject.getInt("distance");
            if (jSONObject.getInt("unit") == 0) {
                trackSwimSegment.saveDistance(i3);
                list2.add(trackSwimSegment);
            } else {
                trackSwimSegment.saveDistance(new BigDecimal(UnitUtil.e(i3, 2)).setScale(0, 4).intValue());
                list3.add(trackSwimSegment);
            }
        }
    }

    private static void handleNotRunToTrackGolfInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        motionPathSimplify.addExtendDataMap("golfSwingCount", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_SWING_COUNT)));
        motionPathSimplify.addExtendDataMap("golfBackSwingTime", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_BACK_SWING_TIME)));
        motionPathSimplify.addExtendDataMap("golfDownSwingTime", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_DOWN_SWING_TIME)));
        motionPathSimplify.addExtendDataMap("golfSwingSpeed", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_SWING_SPEED)));
        motionPathSimplify.addExtendDataMap("golfMaxSwingSpeed", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_MAX_SWING_SPEED)));
        motionPathSimplify.addExtendDataMap("golfSwingTempo", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_SWING_TEMPO)));
    }

    private static void handleNotRunToTrackSkiInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        motionPathSimplify.addExtendDataMap("skiMaxSlopeDegree", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_MAX_SLOPE_DEGREE)));
        motionPathSimplify.addExtendDataMap("skiMaxSlopePercent", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_MAX_SLOPE_PERCENT)));
        motionPathSimplify.addExtendDataMap("skiTotalTime", String.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION)));
        motionPathSimplify.addExtendDataMap("skiTotalDistance", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_DISTANCE)));
        motionPathSimplify.setTotalTime(jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_TOTAL_TIME));
        motionPathSimplify.setTotalDistance(Math.round(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_TOTAL_DISTANCE) / 100.0f));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE, String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE)));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_WORKOUT_WEATHER, String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_WEATHER)));
        motionPathSimplify.addExtendDataMap("skiTripTimes", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SWIM_TRIP_TIMES)));
    }

    public static void handleNotRunToTrackAltitudeInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        motionPathSimplify.setSwolfBase(jSONObject.getInt("swolf_base_km") / 10.0f);
        motionPathSimplify.setBritishSwolfBase(jSONObject.getInt("swolf_base_mile") / 10.0f);
        LogUtil.a(TAG, "record setSwolfBase: ", Float.valueOf(jSONObject.getInt("swolf_base_km") / 10.0f), " setBritishSwolfBase: ", Float.valueOf(jSONObject.getInt("swolf_base_mile") / 10.0f));
        if (jSONObject.getInt("highest_altitude") != Integer.MAX_VALUE) {
            motionPathSimplify.setMaxAlti(jSONObject.getInt("highest_altitude") / 10.0f);
        }
        if (jSONObject.getInt("lowest_altitude") != Integer.MIN_VALUE) {
            motionPathSimplify.setMinAlti(jSONObject.getInt("lowest_altitude") / 10.0f);
        }
        motionPathSimplify.setTotalDescent(jSONObject.getInt("accumulative_drop_height"));
        LogUtil.a(TAG, "record setMaxAlti: ", Float.valueOf(jSONObject.getInt("highest_altitude") / 10.0f), " setMinAlti: ", Float.valueOf(jSONObject.getInt("lowest_altitude") / 10.0f), " setTotalDistance: ", Integer.valueOf(jSONObject.getInt("accumulative_drop_height")));
    }

    public static void handleNotRunToTrackBaseData(JSONObject jSONObject, Map<Long, double[]> map, MotionPathSimplify motionPathSimplify, float f, WorkoutDisplayInfo workoutDisplayInfo) throws JSONException {
        int i = jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_HEART_RATE_TYPE);
        LogUtil.a(TAG, "workout_heart_rate_type: ", Integer.valueOf(i));
        if (i == 0) {
            motionPathSimplify.setHeartrateZoneType(2);
        } else if (i == 1) {
            motionPathSimplify.setHeartrateZoneType(1);
        } else if (i == 3) {
            motionPathSimplify.setHeartrateZoneType(3);
        } else {
            motionPathSimplify.setHeartrateZoneType(0);
        }
        if (jSONObject.has(HwExerciseConstants.JSON_NAME_EXERCISE_ID)) {
            motionPathSimplify.setRuncourseId(jSONObject.getString(HwExerciseConstants.JSON_NAME_EXERCISE_ID));
        }
        HwExerciseUtils.handleNotRunToTrackExerciseInfo(jSONObject, motionPathSimplify);
        HwExerciseUtils.handleNotRunToTrackDisplayInfo(jSONObject, map, motionPathSimplify, workoutDisplayInfo);
        if (workoutDisplayInfo.getWorkoutType() == 262 || workoutDisplayInfo.getWorkoutType() == 266) {
            int i2 = jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_DISTANCE);
            if (i2 != 0) {
                float f2 = f / i2;
                LogUtil.a(TAG, "swim speed: ", Float.valueOf(f2));
                motionPathSimplify.setAvgPace(f2);
            } else {
                LogUtil.a(TAG, "swim speed distance is 0");
                motionPathSimplify.setAvgPace(0.0f);
            }
            if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_DISTANCE) == 0) {
                motionPathSimplify.setChiefSportDataType(2);
            } else {
                motionPathSimplify.setChiefSportDataType(0);
            }
        }
    }

    public static void handleNotRunToTrackMarathon(JSONObject jSONObject, Map<Double, Double> map, Map<Double, Double> map2) {
        if (jSONObject == null) {
            LogUtil.h(TAG, "handleNotRunToTrackMarathon total is null");
            return;
        }
        if (map == null) {
            LogUtil.h(TAG, "handleNotRunToTrackMarathon partTimePaceMap is null");
            return;
        }
        if (map2 == null) {
            LogUtil.h(TAG, "handleNotRunToTrackMarathon partTimePaceMapEnglish is null");
            return;
        }
        double optDouble = jSONObject.optDouble("half_marathon_time");
        double optDouble2 = jSONObject.optDouble(HwExerciseConstants.JSON_NAME_TOTAL_MARATHON_TIME);
        LogUtil.a(TAG, "halfMarathonTime: ", Double.valueOf(optDouble), " totalMarathonTime: ", Double.valueOf(optDouble2));
        if (optDouble > 0.0d) {
            map.put(Double.valueOf(21.0975d), Double.valueOf(optDouble));
            map2.put(Double.valueOf(13.1099865d), Double.valueOf(optDouble));
        }
        if (optDouble2 > 0.0d) {
            map.put(Double.valueOf(42.195d), Double.valueOf(optDouble2));
            map2.put(Double.valueOf(26.219973d), Double.valueOf(optDouble2));
        }
    }

    public static void handleNotRunToTrackBase(JSONObject jSONObject, JSONArray jSONArray, MotionPath motionPath, MotionPathSimplify motionPathSimplify, int i) throws JSONException {
        Map<Double, Double> hashMap = new HashMap<>(16);
        Map<Double, Double> hashMap2 = new HashMap<>(16);
        LogUtil.a(TAG, "saveDataToTrack workout paceArray");
        if (jSONArray != null) {
            Map<Integer, Float> changePaceMapStruct = HwExerciseUtils.changePaceMapStruct(jSONArray, 0);
            motionPath.setPaceMap(changePaceMapStruct);
            motionPathSimplify.setPaceMap(changePaceMapStruct);
            Map<Integer, Float> changePaceMapStruct2 = HwExerciseUtils.changePaceMapStruct(jSONArray, 1);
            motionPath.setBritishPaceMap(changePaceMapStruct2);
            motionPathSimplify.setBritishPaceMap(changePaceMapStruct2);
            hashMap = HwExerciseUtils.changePartTimePaceMapStruct(jSONArray, 0);
            hashMap2 = HwExerciseUtils.changePartTimePaceMapStruct(jSONArray, 1);
        }
        handleNotRunToTrackMarathon(jSONObject, hashMap, hashMap2);
        motionPathSimplify.setPartTimeMap(hashMap);
        motionPathSimplify.setBritishPartTimeMap(hashMap2);
        motionPathSimplify.setTotalTime(i);
        motionPathSimplify.setTotalSteps(((Integer) jSONObject.get(HwExerciseConstants.JSON_NAME_RECORD_STEP)).intValue());
    }

    public static void printWorkoutRecordInfo() {
        LogUtil.a(TAG, "enter printWorkoutRecordInfo");
        try {
            HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
            int size = hwExerciseParams.getWorkoutRecordsStatisticArray().size();
            for (int i = 0; i < size; i++) {
                JSONObject valueAt = hwExerciseParams.getWorkoutRecordsStatisticArray().valueAt(i);
                StringBuilder sb = new StringBuilder(16);
                sb.append("BloodOxygen: highestBloodOxygen=");
                sb.append(valueAt.get("highestBloodOxygen"));
                sb.append(",");
                sb.append("lowestBloodOxygen=");
                sb.append(valueAt.get("lowestBloodOxygen"));
                LogUtil.a(TAG, sb.toString());
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "printWorkoutRecordInfo JSONException");
        }
    }

    public static void printWorkoutRecordSectionInfo() {
        HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
        LogUtil.a(TAG, "printWorkoutRecordSectionInfo enter mSectionRecordsStatisticJsonObjects.size: ", Integer.valueOf(hwExerciseParams.getSectionRecordsStatisticJsonObjects().size()));
        int size = hwExerciseParams.getSectionRecordsStatisticJsonObjects().size();
        for (int i = 0; i < size; i++) {
            LogUtil.a(TAG, "printWorkoutRecordSectionInfo sectionStruct.length(): ", Integer.valueOf(hwExerciseParams.getSectionRecordsStatisticJsonObjects().get(i).length()));
        }
    }

    public static void printCommonWorkoutRecordSectionInfo() {
        LogUtil.a(TAG, "printCommonWorkoutRecordSectionInfo enter commonRecordStatisticsSectionList size: ", Integer.valueOf(HwExerciseParams.getInstance().getCommonRecordStatisticsSectionList().size()));
    }

    public static void handleSaveWorkoutDisplayInfo(Map<Long, double[]> map, MotionPathSimplify motionPathSimplify) {
        if (map == null || motionPathSimplify == null) {
            LogUtil.h(TAG, "handleSaveWorkoutDisplayInfo param is null.");
            return;
        }
        WorkoutDisplayInfo workoutDisplayInfo = new WorkoutDisplayInfo();
        HwExerciseUtils.checkWorkoutDisplayInfo(1, map, workoutDisplayInfo, motionPathSimplify);
        motionPathSimplify.setChiefSportDataType(workoutDisplayInfo.getChiefSportDataType());
        motionPathSimplify.setIsFreeMotion(workoutDisplayInfo.getFreeMotion());
        motionPathSimplify.setSportType(workoutDisplayInfo.getWorkoutType());
        motionPathSimplify.setHasTrackPoint(workoutDisplayInfo.isHasTrackPoint());
        motionPathSimplify.setSportDataSource(workoutDisplayInfo.getSportDataSource());
    }

    private static void handleNotRunToTrackSpo2(int i, MotionPath motionPath) {
        if (motionPath == null) {
            LogUtil.h(TAG, "handleNotRunToTrackSpo2 motion is null");
        } else {
            juc.b().a(i, motionPath);
        }
    }

    public static void saveDataNotRunToTrack(JSONObject jSONObject, Map<Long, double[]> map, int i, List<JSONArray> list, Map<String, JSONArray> map2) {
        JSONArray jSONArray = map2.get(HwExerciseConstants.METHOD_PARAM_WORKOUT_DETAIL_DATA);
        JSONArray jSONArray2 = map2.get(HwExerciseConstants.METHOD_PARAM_WORKOUT_PACE_DATA);
        MotionPath motionPath = new MotionPath();
        if (map != null) {
            motionPath.setLbsDataMap(map);
        }
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        HwExerciseUtils.setMapType(i, motionPathSimplify, map);
        try {
            int i2 = (int) jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION);
            handleNotRunToTrackBase(jSONObject, jSONArray2, motionPath, motionPathSimplify, i2);
            HwExerciseAdviceManagerHelper.getInstance().packTrackData(jSONArray, motionPath, motionPathSimplify, jSONObject);
            handleNotRunToTrackBaseData(jSONObject, map, motionPathSimplify, i2, new WorkoutDisplayInfo());
            handleNotRunToTrackAltitudeInfo(jSONObject, motionPathSimplify);
            handleGolfAndSkiStatistic(jSONObject, motionPathSimplify);
            handleDeviceRunPaceCapability(jSONObject, motionPathSimplify);
            handleRopeSkippingDataStatistic(jSONObject, motionPathSimplify);
            handleStatisticExtendData(jSONObject, motionPathSimplify);
            ArrayList arrayList = new ArrayList(16);
            ArrayList arrayList2 = new ArrayList(16);
            ArrayList arrayList3 = new ArrayList(16);
            HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
            handleCommonSection(hwExerciseParams, motionPath, motionPathSimplify, arrayList3, jSONObject);
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                handleNotRunToTrackSectionStruct(list, arrayList, arrayList2, i3);
            }
            motionPathSimplify.setSwimSegments(arrayList);
            motionPathSimplify.setBritishSwimSegments(arrayList2);
            LogUtil.a(TAG, "swimSegments.size: ", Integer.valueOf(arrayList.size()), " britishSwimSegments.size: ", Integer.valueOf(arrayList2.size()), " trackCommonSegments.size: ", Integer.valueOf(arrayList3.size()));
            handleNotRunToTrackSpo2(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID), motionPath);
            HashMap hashMap = new HashMap(16);
            handleNotRunToTrackSportData(jSONObject, hashMap);
            motionPathSimplify.setSportData(hashMap);
            motionPathSimplify.setTargetPercent(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TARGET_PERCENT));
            motionPathSimplify.setExerciseLevel(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_EXERCISE_LEVEL));
            HwExerciseUtils.addTriathlonToMotionPathSimplify(jSONObject, motionPathSimplify);
            saveDataNotRunToTrack(jSONObject, motionPathSimplify, hwExerciseParams, motionPath);
            LogUtil.a(TAG, "saveDataNotRunToTrack finish");
        } catch (JSONException e) {
            LogUtil.b(TAG, "saveDataNotRunToTrack workout JSONException:", LogAnonymous.b((Throwable) e));
        }
    }

    private static void saveDataNotRunToTrack(JSONObject jSONObject, MotionPathSimplify motionPathSimplify, HwExerciseParams hwExerciseParams, MotionPath motionPath) throws JSONException {
        int i = jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_FLAG);
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_RECORD_FLAG, String.valueOf(i));
        if (i == 5 || i == 6) {
            hwExerciseParams.getWorkoutRecord().put("workout_record_count", hwExerciseParams.getWorkoutRecord().getInt("workout_record_count") + 1);
            handleRunCourseStatistic(jSONObject, motionPathSimplify);
            HwExerciseUtils.packExerciseData(motionPathSimplify, motionPath);
        }
        motionPathSimplify.getDictTypeList().addAll(hwExerciseParams.getDictTypeList());
        Iterator<Integer> it = hwExerciseParams.getDictTypeList().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            getSequenceData(jSONObject, motionPathSimplify, intValue, countDownLatch, motionPath);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                LogUtil.b(TAG, "saveDataNotRunToTrack InterruptedException:", ExceptionUtils.d(e));
            }
            LogUtil.a(TAG, "saveDataNotRunToTrack dictId:", Integer.valueOf(intValue));
        }
        hwExerciseParams.getDictTypeList().clear();
        if (hwExerciseParams.getDivingEvent() == 1) {
            LogUtil.a(TAG, "saveDataNotRunToTrack sync diving data.");
            getDivingData(jSONObject, hwExerciseParams, motionPath, motionPathSimplify);
        } else {
            saveTrackData(motionPathSimplify, motionPath, jSONObject, hwExerciseParams);
        }
    }

    private static void getDivingData(final JSONObject jSONObject, final HwExerciseParams hwExerciseParams, final MotionPath motionPath, final MotionPathSimplify motionPathSimplify) throws JSONException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        jyp.c().b(getTransFileInfo(jSONObject, motionPathSimplify), new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseRunPlanUtil.1
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "errorCode: ", Integer.valueOf(i), " transferDataContent: ", str);
                String str3 = str.substring(0, str.lastIndexOf(47)) + HwExerciseRunPlanUtil.mDivingFileName;
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "getDivingData newPath:", str3);
                File file = new File(str);
                File file2 = new File(str3);
                if (file2.exists()) {
                    LogUtil.a(HwExerciseRunPlanUtil.TAG, "mDivingSequenceCallback file is delete:", Boolean.valueOf(file2.delete()));
                }
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "mDivingSequenceCallback file is rename:", Boolean.valueOf(file.renameTo(file2)));
                kbu kbuVar = (kbu) kbx.d().execute(5, str3);
                if (kbuVar != null) {
                    LogUtil.a(HwExerciseRunPlanUtil.TAG, "mDivingSequenceCallback sequenceFileData:", kbuVar.toString());
                }
                String sequenceFileDataString = SequenceFileDataUtils.getSequenceFileDataString(kbuVar);
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "mDivingSequenceCallback sampleSequenceFileDataStr:", sequenceFileDataString);
                MotionPath.this.setDivingIncidentData(sequenceFileDataString);
                countDownLatch.countDown();
                HwExerciseRunPlanUtil.saveTrackData(motionPathSimplify, MotionPath.this, jSONObject, hwExerciseParams);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                LogUtil.h(HwExerciseRunPlanUtil.TAG, "onFailure errorCode:", Integer.valueOf(i));
                countDownLatch.countDown();
                HwExerciseRunPlanUtil.saveTrackData(motionPathSimplify, MotionPath.this, jSONObject, hwExerciseParams);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "getDivingData progress: ", Integer.valueOf(i));
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b(TAG, "saveDataNotRunToTrack InterruptedException:", ExceptionUtils.d(e));
        }
    }

    private static void getSequenceData(JSONObject jSONObject, final MotionPathSimplify motionPathSimplify, int i, final CountDownLatch countDownLatch, final MotionPath motionPath) {
        jyp.c().b(getSequenceTransFileInfo(jSONObject, motionPathSimplify, i), new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseRunPlanUtil.2
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i2, String str, String str2) throws RemoteException {
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "errorCode: ", Integer.valueOf(i2), " transferDataContent: ", str);
                String str3 = str.substring(0, str.lastIndexOf(47)) + HwExerciseRunPlanUtil.mSequenceFileName;
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "getSequenceData newPath:", str3);
                File file = new File(str);
                File file2 = new File(str3);
                if (file2.exists()) {
                    LogUtil.a(HwExerciseRunPlanUtil.TAG, "getSequenceData file is delete:", Boolean.valueOf(file2.delete()));
                }
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "getSequenceData file is rename:", Boolean.valueOf(file.renameTo(file2)));
                kbu kbuVar = (kbu) kbx.d().execute(5, str3);
                if (kbuVar != null) {
                    LogUtil.a(HwExerciseRunPlanUtil.TAG, "getSequenceData sequenceFileData:", kbuVar.toString(), " sequenceFileData Version :", Integer.valueOf(kbuVar.a()));
                    MotionPath.this.getSequenceDetailData().append(new DetailsFieldParser().detailParse(kbuVar));
                    motionPathSimplify.getDictTypeList().remove(Integer.valueOf(kbuVar.b()));
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i2, String str) throws RemoteException {
                LogUtil.h(HwExerciseRunPlanUtil.TAG, "getSequenceData onFailure errorCode:", Integer.valueOf(i2));
                countDownLatch.countDown();
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i2, String str) throws RemoteException {
                LogUtil.a(HwExerciseRunPlanUtil.TAG, "getSequenceData progress: ", Integer.valueOf(i2));
            }
        });
    }

    private static jqj getSequenceTransFileInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify, int i) {
        jqj jqjVar = new jqj();
        try {
            mSequenceFileName = File.separator + jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID) + i + "_sequence_data.bin";
            jqjVar.a("sequence_data");
            jqjVar.d(22);
            jqjVar.c((TransferFileReqType) null);
            jqjVar.c(HwExerciseDeviceUtil.getCurrentDeviceMac());
            jqjVar.a(false);
            jqjVar.e(new int[]{Integer.parseInt(String.valueOf(motionPathSimplify.getStartTime() / 1000)), Integer.parseInt(String.valueOf(motionPathSimplify.getEndTime() / 1000))});
            jqjVar.b(i);
        } catch (JSONException e) {
            LogUtil.b(TAG, "getSequenceTransFileInfo JSONException:", ExceptionUtils.d(e));
        }
        return jqjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void saveTrackData(MotionPathSimplify motionPathSimplify, MotionPath motionPath, JSONObject jSONObject, HwExerciseParams hwExerciseParams) {
        try {
            HwExerciseAdviceManagerHelper.getInstance().notifySaveData(motionPathSimplify, motionPath, jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID), hwExerciseParams.getTriathlonUtils(), HwExerciseAdviceManager.getInstance());
        } catch (JSONException e) {
            LogUtil.b(TAG, "JSONException:", ExceptionUtils.d(e));
        }
    }

    private static jqj getTransFileInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        mDivingFileName = File.separator + jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID) + "_diving_sequence_data.bin";
        jqj jqjVar = new jqj();
        jqjVar.a("sequence_data");
        jqjVar.d(22);
        jqjVar.c((TransferFileReqType) null);
        jqjVar.c(HwExerciseDeviceUtil.getCurrentDeviceMac());
        jqjVar.a(false);
        jqjVar.e(new int[]{Integer.parseInt(String.valueOf(motionPathSimplify.getStartTime() / 1000)), Integer.parseInt(String.valueOf(motionPathSimplify.getEndTime() / 1000))});
        jqjVar.b(DICT_TYPE);
        return jqjVar;
    }

    private static void handleDeviceRunPaceCapability(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        DeviceCapability capability = HwExerciseDeviceUtil.getCapability();
        if (capability != null && capability.isSupportRunPaceSetCapability()) {
            handleRunPaceZoneStatistic(jSONObject, motionPathSimplify);
        } else {
            LogUtil.h(TAG, "saveDataNotRunToTrack isSupportRunPaceCapability is false");
        }
    }

    private static void handleRunPaceZoneStatistic(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        motionPathSimplify.addExtendDataMap("runPaceZoneConfig", getRunPaceZoneConfig(jSONObject));
        motionPathSimplify.addExtendDataMap("runPaceZoneStatistics", getRunPaceZoneStatistics(jSONObject));
    }

    private static String getRunPaceZoneConfig(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject.getInt(HwExerciseConstants.JSON_NAME_ROMPED_PACE_ZONE_MIN_VALUE));
        jSONArray.put(jSONObject.getInt(HwExerciseConstants.JSON_NAME_MARATHON_PACE_ZONE_MIN_VALUE));
        jSONArray.put(jSONObject.getInt(HwExerciseConstants.JSON_NAME_LACTIC_ACID_PACE_ZONE_MIN_VALUE));
        jSONArray.put(jSONObject.getInt(HwExerciseConstants.JSON_NAME_ANAEROBIC_PACE_ZONE_MIN_VALUE));
        jSONArray.put(jSONObject.getInt(HwExerciseConstants.JSON_NAME_MAX_OXYGEN_PACE_ZONE_MIN_VALUE));
        jSONArray.put(jSONObject.getInt(HwExerciseConstants.JSON_NAME_MAX_OXYGEN_PACE_ZONE_MAX_VALUE));
        LogUtil.a(TAG, "getRunPaceZoneConfig paceZoneConfigArray: ", jSONArray.toString());
        return jSONArray.toString();
    }

    private static String getRunPaceZoneStatistics(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject.getLong(HwExerciseConstants.JSON_NAME_ROMPED_TIME));
        jSONArray.put(jSONObject.getLong(HwExerciseConstants.JSON_NAME_MARATHON_TIME));
        jSONArray.put(jSONObject.getLong(HwExerciseConstants.JSON_NAME_LACTIC_ACID_TIME));
        jSONArray.put(jSONObject.getLong(HwExerciseConstants.JSON_NAME_ANAEROBIC_TIME));
        jSONArray.put(jSONObject.getLong(HwExerciseConstants.JSON_NAME_MAX_OXYGEN_TIME));
        LogUtil.a(TAG, "getRunPaceZoneStatistics paceZoneStatisticsArray: ", jSONArray.toString());
        return jSONArray.toString();
    }

    private static void handleGolfAndSkiStatistic(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) == 15 || jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) == 16) {
            handleNotRunToTrackSkiInfo(jSONObject, motionPathSimplify);
        }
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) == 18) {
            handleNotRunToTrackGolfInfo(jSONObject, motionPathSimplify);
        }
    }

    private static void handleRopeSkippingDataStatistic(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) == 21) {
            motionPathSimplify.addExtendDataMap("stumblingRope", String.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_TRIPPED)));
            motionPathSimplify.addExtendDataMap("maxSkippingTimes", String.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_LONGEST_STREAK)));
            motionPathSimplify.addExtendDataMap("skipNum", String.valueOf(motionPathSimplify.getTotalSteps()));
            motionPathSimplify.addExtendDataMap("skipSpeed", String.valueOf(motionPathSimplify.getAvgStepRate()));
        }
    }

    private static void handleRunCourseStatistic(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        motionPathSimplify.addExtendDataMap("courseName", jSONObject.getString(HwExerciseConstants.JSON_NAME_COURSE_NAME));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_PLAN_ID, jSONObject.getString(HwExerciseConstants.JSON_NAME_PLAN_ID));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_PLAN_COURSE_TIME, String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_PLAN_COURSE_TIME)));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_COURSE_TARGET_TYPE, String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_COURSE_TARGET_TYPE)));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_COURSE_TARGET_VALUE, String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_COURSE_TARGET_VALUE)));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_TRAINING_POINTS, String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_TRAINING_POINTS)));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_TRAINING_EXPERIENCE, String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_TRAINING_EXPERIENCE)));
        motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_COURSE_MODIFIED_TIME, String.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_COURSE_MODIFIED_TIME)));
        motionPathSimplify.addExtendDataMap("sportRecordType", String.valueOf(0));
        motionPathSimplify.addExtendDataMap("attachedRecordType", String.valueOf(1));
    }

    private static void handleStatisticExtendData(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("extendList");
            LogUtil.a(TAG, "handleStatisticExtendData jsonArray:", jSONArray);
            List<StatisticExtendDataStruct> b = moj.b(jSONArray.toString(), StatisticExtendDataStruct[].class);
            LogUtil.a(TAG, "handleStatisticExtendData extendDataList:", b.toString());
            for (StatisticExtendDataStruct statisticExtendDataStruct : b) {
                motionPathSimplify.addExtendDataMap(statisticExtendDataStruct.getFieldName(), statisticExtendDataStruct.getFieldValue());
                motionPathSimplify.changeKey(statisticExtendDataStruct.getFieldName(), statisticExtendDataStruct.getFieldValue());
            }
        } catch (JSONException e) {
            LogUtil.b(TAG, "handleStatisticExtendData JSONException:", ExceptionUtils.d(e));
        }
    }

    private static void handleCommonSection(HwExerciseParams hwExerciseParams, MotionPath motionPath, MotionPathSimplify motionPathSimplify, List<CommonSegment> list, JSONObject jSONObject) throws JSONException {
        HwExerciseParams hwExerciseParams2 = HwExerciseParams.getInstance();
        int i = jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID);
        ArrayList arrayList = new ArrayList(16);
        if (hwExerciseParams2.getWorkoutRecordCommonSectionMap().get(Integer.valueOf(i)) != null) {
            LogUtil.a(TAG, "handleCommonSection workoutRecordId: ", Integer.valueOf(i));
            arrayList.addAll(hwExerciseParams2.getWorkoutRecordCommonSectionMap().get(Integer.valueOf(i)));
        }
        LogUtil.a(TAG, "handleCommonSection commonSectionList size: ", Integer.valueOf(arrayList.size()));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            handleCommonData(list, i2, jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE), arrayList, jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_FLAG));
        }
        if (checkNewSectionWorkoutType(jSONObject)) {
            motionPath.setCommonSegments(list);
            motionPathSimplify.addExtendDataMap("segmentNum", String.valueOf(hwExerciseParams.getSectionCommonIndex()));
        }
        arrayList.clear();
    }

    private static void handleCommonData(List<CommonSegment> list, int i, int i2, List<List<CommonSectionInfo>> list2, int i3) {
        List<CommonSectionInfo> list3 = list2.get(i);
        int size = list3.size();
        for (int i4 = 0; i4 < size; i4++) {
            CommonSectionInfo commonSectionInfo = list3.get(i4);
            if (i2 != 1) {
                if (i2 == 3) {
                    HwExerciseCommonSegmentUtils.handleCyclingSegment(list, commonSectionInfo);
                } else if (i2 != 11) {
                    if (i2 != 25) {
                        if (i2 == 255) {
                            HwExerciseCommonSegmentUtils.handleOwnTrainingSegment(list, commonSectionInfo);
                        } else if (i2 != 15 && i2 != 16) {
                            if (i2 != 22 && i2 != 23) {
                                switch (i2) {
                                    case 18:
                                        HwExerciseCommonSegmentUtils.handleGolfSegment(list, commonSectionInfo);
                                        break;
                                    case 19:
                                        HwExerciseCommonSegmentUtils.handleGolfCourseSegment(list, commonSectionInfo);
                                        break;
                                    case 20:
                                        HwExerciseCommonSegmentUtils.handleExerciseSegment(list, commonSectionInfo);
                                        break;
                                    default:
                                        LogUtil.h(TAG, "else workout type");
                                        break;
                                }
                            }
                        } else {
                            HwExerciseCommonSegmentUtils.handleSkiSegment(list, commonSectionInfo);
                        }
                    }
                    HwExerciseCommonSegmentUtils.handleDivingOrBreathSegment(list, commonSectionInfo);
                }
            }
            if (i3 == 5 || i3 == 6) {
                HwExerciseCommonSegmentUtils.handleExerciseSegment(list, commonSectionInfo);
                HwExerciseCommonSegmentUtils.handleRunSegment(list, commonSectionInfo);
            } else {
                HwExerciseCommonSegmentUtils.handleRunSegment(list, commonSectionInfo);
            }
        }
    }

    public static void syncWorkoutDetailData(int i, JSONObject jSONObject) {
        try {
            if (jSONObject == null) {
                LogUtil.h(TAG, "syncWorkoutDetailData jsonObject is null");
                return;
            }
            HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
            JSONArray jSONArray = jSONObject.getJSONArray(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_STRUCT_LIST);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                for (int i3 = 0; i3 < jSONObject2.getInt("workout_index_count"); i3++) {
                    Integer num = jSONObject2.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID) instanceof Integer ? (Integer) jSONObject2.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID) : null;
                    if (num != null && num.intValue() == i) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID, jSONObject2.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID));
                        jSONObject3.put(HwExerciseConstants.WORKOUT_DATA_INDEX, i3);
                        hwExerciseParams.getWorkoutDataList().add(jSONObject3);
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "syncWorkoutDetailData JSONException");
        }
    }

    public static void handleSaveNotRunPlayToTrack(int i, JSONObject jSONObject, JSONArray jSONArray, JSONArray jSONArray2, List<JSONArray> list) throws JSONException {
        HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
        if (hwExerciseParams.getGpsWorkoutMap().get(Integer.valueOf(i)) != null) {
            LogUtil.a(TAG, "mGpsWorkoutMap size: ", Integer.valueOf(hwExerciseParams.getGpsWorkoutMap().get(Integer.valueOf(i)).size()));
        }
        if (HwExerciseUtils.checkSupportWorkoutType(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE))) {
            hwExerciseParams.setSaveDataItemNumber(hwExerciseParams.getSaveDataItemNumber() + 1);
            int intValue = hwExerciseParams.getGpsWorkoutAndRunPlanTypeMap().get(Integer.valueOf(i)) != null ? hwExerciseParams.getGpsWorkoutAndRunPlanTypeMap().get(Integer.valueOf(i)).intValue() : -1;
            hwExerciseParams.getWorkoutParamJsonArrayMap().put(HwExerciseConstants.METHOD_PARAM_WORKOUT_DETAIL_DATA, jSONArray);
            hwExerciseParams.getWorkoutParamJsonArrayMap().put(HwExerciseConstants.METHOD_PARAM_WORKOUT_PACE_DATA, jSONArray2);
            saveDataNotRunToTrack(jSONObject, hwExerciseParams.getGpsWorkoutMap().get(Integer.valueOf(i)), intValue, list, hwExerciseParams.getWorkoutParamJsonArrayMap());
        }
    }

    private static void checkEteStatus() {
        HwLinkageServiceManager.getInstance().getOperator(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseRunPlanUtil.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 100000) {
                    if (!(obj instanceof OperatorStatus)) {
                        LogUtil.h(HwExerciseRunPlanUtil.TAG, "objectData is not OperatorStatus");
                    } else if (((OperatorStatus) obj).getTrainMonitorState() == 1) {
                        HwExerciseParams.getInstance().setIsUsingEte(false);
                    }
                }
            }
        });
    }

    public static void handleDeviceConnected(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h(TAG, "handleDeviceConnected deviceInfo is null.");
            return;
        }
        HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
        hwExerciseParams.getHwExerciseAdviceManagerHandler().removeMessages(1);
        hwExerciseParams.getHwExerciseAdviceManagerHandler().removeMessages(1001);
        if (isSupportExercise(deviceInfo)) {
            if (hwExerciseParams.getDeviceIdentify() == null) {
                hwExerciseParams.getHwExerciseAdviceManagerHandler().sendEmptyMessage(1);
            } else if (deviceInfo.getSecurityDeviceId() == null || !hwExerciseParams.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getSecurityDeviceId())) {
                hwExerciseParams.getHwExerciseAdviceManagerHandler().sendEmptyMessage(1);
            }
            if (hwExerciseParams.isUsingEte() && hwExerciseParams.getRunPlanExecuteState() != 3) {
                checkEteStatus();
            }
        } else {
            hwExerciseParams.getHwExerciseAdviceManagerHandler().sendEmptyMessage(1);
        }
        hwExerciseParams.setDeviceIdentify(deviceInfo.getSecurityDeviceId());
    }

    public static boolean isSupportExercise() {
        DeviceCapability capability = HwExerciseDeviceUtil.getCapability();
        boolean isSupportExerciseAdvice = capability != null ? capability.isSupportExerciseAdvice() : false;
        LogUtil.a(TAG, "get Device Support isSupportExercise: ", Boolean.valueOf(isSupportExerciseAdvice));
        return isSupportExerciseAdvice;
    }

    public static boolean isSupportExercise(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h(TAG, "isSupportExercise, deviceInfo is null");
            return false;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), TAG);
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h(TAG, "isSupportExercise, deviceCapabilityHashMaps is empty");
            return false;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        boolean isSupportExerciseAdvice = deviceCapability != null ? deviceCapability.isSupportExerciseAdvice() : false;
        LogUtil.a(TAG, "get Device Support isSupportExercise: ", Boolean.valueOf(isSupportExerciseAdvice));
        return isSupportExerciseAdvice;
    }

    public static boolean handleSaveNotRunPlanRecord(int i, boolean z) throws JSONException {
        HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
        if (hwExerciseParams.getWorkoutRecord() != null) {
            if (hwExerciseParams.getWorkoutRecord().getJSONArray(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_STRUCT_LIST).length() > 0) {
                z = true;
            }
            LogUtil.a(TAG, "saveData recordId: ", Integer.valueOf(i));
            JSONObject jSONObject = hwExerciseParams.getWorkoutRecordsStatisticArray().get(i);
            if (jSONObject == null) {
                LogUtil.h(TAG, "handleSaveNotRunPlanRecord totalData is null");
                return false;
            }
            LogUtil.a(TAG, "totalData: ", jSONObject.toString());
            JSONArray jSONArray = new JSONArray();
            for (JSONObject jSONObject2 : hwExerciseParams.getWorkoutDetailDataList()) {
                if (i == jSONObject2.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)) {
                    jSONArray.put(jSONObject2);
                }
            }
            JSONArray jSONArray2 = new JSONArray();
            for (JSONObject jSONObject3 : hwExerciseParams.getWorkoutRecordPaceMapList()) {
                if (i == jSONObject3.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)) {
                    jSONArray2.put(jSONObject3);
                }
            }
            ArrayList arrayList = new ArrayList(16);
            if (hwExerciseParams.getSwimWorkoutRecordSectionMap().get(Integer.valueOf(i)) != null) {
                arrayList.addAll((ArrayList) hwExerciseParams.getSwimWorkoutRecordSectionMap().get(Integer.valueOf(i)));
                LogUtil.a(TAG, "section sectionArrayList size: ", Integer.valueOf(arrayList.size()), " workout type: ", jSONObject.get(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE));
            }
            handleSaveNotRunPlayToTrack(i, jSONObject, jSONArray, jSONArray2, arrayList);
        }
        return z;
    }

    public static boolean checkNewSectionWorkoutType(JSONObject jSONObject) {
        int optInt = jSONObject.optInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE);
        if (optInt != 1 && optInt != 3 && optInt != 11 && optInt != 25 && optInt != 255 && optInt != 15 && optInt != 16 && optInt != 22 && optInt != 23) {
            switch (optInt) {
                case 18:
                case 19:
                case 20:
                    break;
                default:
                    LogUtil.h(TAG, "checkNewSectionWorkoutType no support segment.");
                    return false;
            }
        }
        return true;
    }
}
