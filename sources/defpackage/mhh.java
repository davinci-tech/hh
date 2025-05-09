package defpackage;

import com.google.android.gms.wearable.WearableStatusCodes;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.pluginachievement.report.bean.AnnualReportCycle;
import com.huawei.pluginachievement.report.bean.AnnualReportFitness;
import com.huawei.pluginachievement.report.bean.AnnualReportInital;
import com.huawei.pluginachievement.report.bean.AnnualReportMarathon;
import com.huawei.pluginachievement.report.bean.AnnualReportReward;
import com.huawei.pluginachievement.report.bean.AnnualReportRun;
import com.huawei.pluginachievement.report.bean.AnnualReportSleep;
import com.huawei.pluginachievement.report.bean.AnnualReportStep;
import com.huawei.pluginachievement.report.bean.AnnualReportSumary;
import com.huawei.pluginachievement.report.bean.AnnualReportWeight;
import com.huawei.pluginachievement.report.bean.MarathonGradleDetail;
import com.huawei.pluginachievement.report.constant.EnumAnnual;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mhh {
    public static int d(long j, AnnualReportStep annualReportStep, AnnualReportInital annualReportInital) {
        if (annualReportStep != null) {
            if (annualReportStep.acquireTotalStep() == 0) {
                return EnumAnnual.DATA_SYNC_FAIL.getValue();
            }
            return EnumAnnual.DATA_SUCCESS.getValue();
        }
        if (annualReportInital == null) {
            return EnumAnnual.DATA_NOT_ENOUGH.getValue();
        }
        long acquireFirstUseDate = annualReportInital.acquireFirstUseDate();
        if (acquireFirstUseDate < j && acquireFirstUseDate != 0) {
            return EnumAnnual.DATA_SYNC_FAIL.getValue();
        }
        return EnumAnnual.DATA_NOT_ENOUGH.getValue();
    }

    public static boolean b(mcz mczVar, meh mehVar) {
        if (mehVar == null || mczVar == null) {
            LogUtil.a("PLGACHIEVE_AnnualDBManager", "insertData null");
            return false;
        }
        return mehVar.c(mczVar);
    }

    public static mcz a(int i, String str, int i2) {
        mcz mczVar = new mcz(19);
        mczVar.setYear(i);
        mczVar.setType(str);
        mczVar.setKey(i2);
        return mczVar;
    }

    public static AnnualReportInital a(meh mehVar, int i) {
        if (mehVar == null) {
            return new AnnualReportInital();
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", EnumAnnualType.REPORT_INITAL.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (koq.b(b)) {
            return null;
        }
        AnnualReportInital annualReportInital = new AnnualReportInital();
        for (mcz mczVar : b) {
            if (mczVar != null) {
                switch (mczVar.getKey()) {
                    case 9001:
                        annualReportInital.saveFirstUseDate(mht.a(mczVar.getValues()));
                        break;
                    case 9002:
                        annualReportInital.saveTotalDays(mht.b(mczVar.getValues()));
                        break;
                    case 9003:
                        annualReportInital.saveTotalSportDistance(mht.b(mczVar.getValues()));
                        break;
                }
            }
        }
        return annualReportInital;
    }

    public static AnnualReportRun f(meh mehVar, int i) {
        if (mehVar == null) {
            return new AnnualReportRun();
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", EnumAnnualType.REPORT_RUN.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (koq.b(b)) {
            return null;
        }
        AnnualReportRun annualReportRun = new AnnualReportRun();
        for (mcz mczVar : b) {
            if (mczVar != null) {
                int key = mczVar.getKey();
                if (key != 9007) {
                    switch (key) {
                        case 2001:
                            annualReportRun.saveTotalDistance(mht.b(mczVar.getValues()));
                            break;
                        case 2002:
                            annualReportRun.saveNumberOfTimes(mht.b(mczVar.getValues()));
                            break;
                        case 2003:
                            annualReportRun.saveMaxDistance(mht.b(mczVar.getValues()));
                            break;
                        case 2004:
                            annualReportRun.saveMaxDistanceDay(mht.a(mczVar.getValues()));
                            break;
                        case 2005:
                            annualReportRun.saveTimeOfDay(mht.b(mczVar.getValues()));
                            break;
                        case 2006:
                            annualReportRun.saveDescription(mht.b(mczVar.getValues()));
                            break;
                    }
                } else {
                    annualReportRun.saveYearSumDistances(mczVar.getValues());
                }
            }
        }
        return annualReportRun;
    }

    public static AnnualReportCycle d(meh mehVar, int i) {
        if (mehVar == null) {
            return new AnnualReportCycle();
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", EnumAnnualType.REPORT_CYCLE.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (koq.b(b)) {
            return null;
        }
        AnnualReportCycle annualReportCycle = new AnnualReportCycle();
        for (mcz mczVar : b) {
            if (mczVar != null) {
                switch (mczVar.getKey()) {
                    case 1001:
                        annualReportCycle.saveTotalDistance(mht.b(mczVar.getValues()));
                        break;
                    case 1002:
                        annualReportCycle.saveNumberOfTimes(mht.b(mczVar.getValues()));
                        break;
                    case 1003:
                        annualReportCycle.saveMaxDistance(mht.b(mczVar.getValues()));
                        break;
                    case 1004:
                        annualReportCycle.saveMaxDistanceDay(mht.a(mczVar.getValues()));
                        break;
                }
            }
        }
        return annualReportCycle;
    }

    public static AnnualReportStep g(meh mehVar, int i) {
        if (mehVar == null) {
            return new AnnualReportStep();
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", EnumAnnualType.REPORT_STEP.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (koq.b(b)) {
            return null;
        }
        AnnualReportStep annualReportStep = new AnnualReportStep();
        for (mcz mczVar : b) {
            if (mczVar != null) {
                int key = mczVar.getKey();
                if (key != 9006) {
                    switch (key) {
                        case 3001:
                            annualReportStep.saveTotalStep(mht.b(mczVar.getValues()));
                            break;
                        case 3002:
                            annualReportStep.saveTotalStepDistance(mht.b(mczVar.getValues()));
                            break;
                        case 3003:
                            annualReportStep.saveMaxStep(mht.b(mczVar.getValues()));
                            break;
                        case IEventListener.EVENT_ID_DEVICE_CONN_FAIL /* 3004 */:
                            annualReportStep.saveMaxStepDay(mczVar.getValues());
                            break;
                        case IEventListener.EVENT_ID_DEVICE_DISCONN_SUCC /* 3005 */:
                            annualReportStep.saveMaxWalkMonth(mht.b(mczVar.getValues()));
                            break;
                    }
                } else {
                    annualReportStep.saveYearAvgSteps(mczVar.getValues());
                }
            }
        }
        return annualReportStep;
    }

    public static AnnualReportFitness c(meh mehVar, int i) {
        if (mehVar == null) {
            return new AnnualReportFitness();
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", EnumAnnualType.REPORT_FITNESS.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (b == null || b.isEmpty()) {
            return null;
        }
        AnnualReportFitness annualReportFitness = new AnnualReportFitness();
        for (mcz mczVar : b) {
            if (mczVar != null) {
                switch (mczVar.getKey()) {
                    case 4001:
                        annualReportFitness.saveTotalDuration(mht.b(mczVar.getValues()));
                        break;
                    case 4002:
                        annualReportFitness.saveNumberOfTimes(mht.b(mczVar.getValues()));
                        break;
                    case WearableStatusCodes.DATA_ITEM_TOO_LARGE /* 4003 */:
                        annualReportFitness.saveMaxDuration(mht.b(mczVar.getValues()));
                        break;
                    case WearableStatusCodes.INVALID_TARGET_NODE /* 4004 */:
                        annualReportFitness.saveMaxDurationDay(mht.a(mczVar.getValues()));
                        break;
                    case 4005:
                        annualReportFitness.saveDescription(mht.b(mczVar.getValues()));
                        break;
                }
            }
        }
        return annualReportFitness;
    }

    public static AnnualReportSleep i(meh mehVar, int i) {
        if (mehVar == null) {
            return new AnnualReportSleep();
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", EnumAnnualType.REPORT_SLEEP.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (b == null || b.isEmpty()) {
            return null;
        }
        AnnualReportSleep annualReportSleep = new AnnualReportSleep();
        for (mcz mczVar : b) {
            if (mczVar != null) {
                switch (mczVar.getKey()) {
                    case 8001:
                        annualReportSleep.saveSleepScore(mht.d(mczVar.getValues()));
                        break;
                    case 8002:
                        annualReportSleep.saveSleepDuration(mht.b(mczVar.getValues()));
                        break;
                    case MainLoginCallBack.MSG_HMS_VERSION_ERROR /* 8003 */:
                        annualReportSleep.setBreLevel(mht.b(mczVar.getValues()));
                        break;
                    case MainLoginCallBack.MSG_HWID_ACCOUNT_NOT_LOGIN /* 8004 */:
                        annualReportSleep.saveFallAsleepTime(mczVar.getValues());
                        break;
                    case MainLoginCallBack.MSG_NO_NETWORK /* 8005 */:
                        annualReportSleep.saveWakeupTime(mczVar.getValues());
                        break;
                    case MainLoginCallBack.MSG_HWID_STOPED /* 8006 */:
                        annualReportSleep.saveAnimalImage(mht.b(mczVar.getValues()));
                        break;
                    case MainLoginCallBack.MSG_START_APK_SERVICE_ERROR /* 8007 */:
                        annualReportSleep.setSleepTimes(mht.b(mczVar.getValues()));
                        break;
                }
            }
        }
        return annualReportSleep;
    }

    public static AnnualReportWeight l(meh mehVar, int i) {
        if (mehVar == null) {
            return new AnnualReportWeight();
        }
        HashMap hashMap = new HashMap(8);
        hashMap.put("type", EnumAnnualType.REPORT_WEIGHT.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (b == null || b.isEmpty()) {
            return null;
        }
        AnnualReportWeight annualReportWeight = new AnnualReportWeight();
        for (mcz mczVar : b) {
            if (mczVar != null) {
                switch (mczVar.getKey()) {
                    case 7001:
                        annualReportWeight.setWeightChange(mht.d(mczVar.getValues()));
                        break;
                    case 7002:
                        annualReportWeight.setMax(mht.d(mczVar.getValues()));
                        break;
                    case 7003:
                        annualReportWeight.setMin(mht.d(mczVar.getValues()));
                        break;
                }
            }
        }
        return annualReportWeight;
    }

    public static AnnualReportMarathon e(meh mehVar, int i) {
        AnnualReportMarathon annualReportMarathon = new AnnualReportMarathon();
        if (mehVar == null) {
            return annualReportMarathon;
        }
        HashMap hashMap = new HashMap(8);
        hashMap.put("type", EnumAnnualType.REPORT_MARATHON.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (b != null && !b.isEmpty()) {
            for (mcz mczVar : b) {
                if (mczVar != null) {
                    int key = mczVar.getKey();
                    if (key == 9004) {
                        annualReportMarathon.setHalfMarathon((MarathonGradleDetail) HiJsonUtil.e(mczVar.getValues(), MarathonGradleDetail.class));
                    } else if (key == 9005) {
                        annualReportMarathon.setFullMarathon((MarathonGradleDetail) HiJsonUtil.e(mczVar.getValues(), MarathonGradleDetail.class));
                    }
                }
            }
        }
        return annualReportMarathon;
    }

    public static AnnualReportSumary j(meh mehVar, int i) {
        AnnualReportSumary annualReportSumary = new AnnualReportSumary();
        if (mehVar == null) {
            return annualReportSumary;
        }
        HashMap hashMap = new HashMap(8);
        hashMap.put("type", EnumAnnualType.REPORT_SUMARY.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (b != null && !b.isEmpty()) {
            for (mcz mczVar : b) {
                if (mczVar != null) {
                    switch (mczVar.getKey()) {
                        case 6001:
                            annualReportSumary.saveMedalSum(mht.b(mczVar.getValues()));
                            break;
                        case 6002:
                            annualReportSumary.saveCurrentLevel(mht.b(mczVar.getValues()));
                            break;
                        case AuthCode.StatusCode.CERT_FINGERPRINT_ERROR /* 6003 */:
                            annualReportSumary.saveTotalDistance(mht.b(mczVar.getValues()));
                            break;
                        case AuthCode.StatusCode.PERMISSION_NOT_EXIST /* 6004 */:
                            annualReportSumary.saveStepOverGoal(mht.b(mczVar.getValues()));
                            break;
                        case AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED /* 6005 */:
                            annualReportSumary.saveTotalCalorie(mht.a(mczVar.getValues()));
                            break;
                    }
                }
            }
        }
        return annualReportSumary;
    }

    public static AnnualReportReward h(meh mehVar, int i) {
        AnnualReportReward annualReportReward = new AnnualReportReward();
        if (mehVar == null) {
            return annualReportReward;
        }
        HashMap hashMap = new HashMap(8);
        hashMap.put("type", EnumAnnualType.REPORT_REWARD.value());
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (b == null || b.isEmpty()) {
            return null;
        }
        for (mcz mczVar : b) {
            if (mczVar != null && mczVar.getKey() == 5001) {
                annualReportReward.saveMedalIdList(mczVar.getValues());
            }
        }
        return annualReportReward;
    }

    public static int a(long j, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject != null) {
            if (jSONObject.getInt(mgr.f123do.a()) == 0) {
                return EnumAnnual.DATA_SYNC_FAIL.getValue();
            }
            return EnumAnnual.DATA_SUCCESS.getValue();
        }
        if (jSONObject2 == null) {
            return EnumAnnual.DATA_NOT_ENOUGH.getValue();
        }
        long j2 = jSONObject2.getLong(mgr.ag.a());
        if (j2 < j && j2 != 0) {
            return EnumAnnual.DATA_SYNC_FAIL.getValue();
        }
        return EnumAnnual.DATA_NOT_ENOUGH.getValue();
    }

    public static JSONObject b(meh mehVar, int i) {
        List<String> annualPageByYear = new mhf().a(i).getAnnualPageByYear();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            for (String str : annualPageByYear) {
                jSONObject2.put(str, e(mehVar, i, str));
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject(EnumAnnualType.REPORT_INITAL.value());
            int a2 = a(mht.b(i, false), jSONObject2.getJSONObject(EnumAnnualType.REPORT_STEP.value()), jSONObject3);
            jSONObject.put("reportData", jSONObject2);
            jSONObject.put("resultCode", a2);
            jSONObject.put("year", i);
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_AnnualDBManager", "getAnnualReport");
        }
        return jSONObject;
    }

    public static JSONObject e(meh mehVar, int i, String str) {
        HashMap hashMap = new HashMap(8);
        hashMap.put("type", str);
        hashMap.put("year", String.valueOf(i));
        List<mcz> b = mehVar.b(19, hashMap);
        if (koq.b(b)) {
            return null;
        }
        return e(b);
    }

    private static JSONObject e(List<mcz> list) {
        JSONObject jSONObject = new JSONObject();
        for (mcz mczVar : list) {
            mgr a2 = a(mczVar);
            if (a2 != null) {
                try {
                    jSONObject.put(a2.a(), e(a2.e(), mczVar.getValues()));
                } catch (JSONException unused) {
                    LogUtil.b("PLGACHIEVE_AnnualDBManager", "getFieldJSONObject");
                }
            }
        }
        return jSONObject;
    }

    private static mgr a(mcz mczVar) {
        if (mczVar != null) {
            return mgw.b(mczVar.getKey());
        }
        return null;
    }

    private static Object e(int i, String str) {
        if (i == 1) {
            return Integer.valueOf(mht.b(str));
        }
        if (i == 4) {
            return Long.valueOf(mht.a(str));
        }
        if (i != 5) {
            return i != 6 ? str : mht.e(str);
        }
        return Double.valueOf(mht.d(str));
    }
}
