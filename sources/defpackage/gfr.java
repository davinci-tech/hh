package defpackage;

import android.text.TextUtils;
import com.huawei.health.suggestion.ui.fitness.helper.FitnessRunAudioScenarioId;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.TargetConfig;
import health.compact.a.CommonUtils;
import health.compact.a.UnitUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gfr extends gfq {
    private final Map<String, FitnessRunAudioScenarioId> d;

    public gfr(String str, String str2, String str3) {
        super(str, str2, str3);
        this.d = new HashMap();
        mxb.a().init(mxh.d(mxb.c(), mxb.a().b(mxb.c()), "Sport", BaseApplication.getContext().getResources().getConfiguration().locale));
        b();
    }

    private void b() {
        a("G001", FitnessRunAudioScenarioId.SLOW_WALKING);
        a("G012", FitnessRunAudioScenarioId.SLOW_WALKING);
        a("G013", FitnessRunAudioScenarioId.SLOW_WALKING);
        a("G014", FitnessRunAudioScenarioId.SLOW_WALKING);
        a("G015", FitnessRunAudioScenarioId.SLOW_WALKING);
        a("RD006", FitnessRunAudioScenarioId.SLOW_WALKING);
        a("G002", FitnessRunAudioScenarioId.FAST_WALK);
        a("G016", FitnessRunAudioScenarioId.FAST_WALK);
        a("G017", FitnessRunAudioScenarioId.FAST_WALK);
        a("G018", FitnessRunAudioScenarioId.FAST_WALK);
        a("G019", FitnessRunAudioScenarioId.FAST_WALK);
        a("G020", FitnessRunAudioScenarioId.FAST_WALK);
        a("G021", FitnessRunAudioScenarioId.FAST_WALK);
        a("RD007", FitnessRunAudioScenarioId.FAST_WALK);
        a("G003", FitnessRunAudioScenarioId.JOGGING);
        a("G006", FitnessRunAudioScenarioId.JOGGING);
        a("G011", FitnessRunAudioScenarioId.JOGGING);
        a("G022", FitnessRunAudioScenarioId.JOGGING);
        a("RD008", FitnessRunAudioScenarioId.JOGGING);
        a("G004", FitnessRunAudioScenarioId.MODERATE_RUNNING);
        a("G023", FitnessRunAudioScenarioId.MODERATE_RUNNING);
        a("RD009", FitnessRunAudioScenarioId.MODERATE_RUNNING);
        a("RD001", FitnessRunAudioScenarioId.WARM_UP);
        a("RD002", FitnessRunAudioScenarioId.RUNNING);
        a("RD003", FitnessRunAudioScenarioId.WALKING);
        a("RD004", FitnessRunAudioScenarioId.HAVE_A_REST);
        a("RD005", FitnessRunAudioScenarioId.RELAX);
        a("RD010", FitnessRunAudioScenarioId.FAST_RUNNING);
        a("RD011", FitnessRunAudioScenarioId.SLOW_WALK);
        a("RD012", FitnessRunAudioScenarioId.SPRINT);
        a("RD013", FitnessRunAudioScenarioId.BASIC_AEROBIC_RUN);
        a("RD014", FitnessRunAudioScenarioId.ADVANCED_AEROBIC_RUN);
        a("RD015", FitnessRunAudioScenarioId.TEMPO_RUN);
        a("RD016", FitnessRunAudioScenarioId.ANAEROBIC_ENDURANCE_RUN);
        a("RD017", FitnessRunAudioScenarioId.HIGH_INTENSITY_INTERVAL_RUN);
        a("RD018", FitnessRunAudioScenarioId.SPRINT_RUN);
    }

    private void a(String str, FitnessRunAudioScenarioId fitnessRunAudioScenarioId) {
        this.d.put(str, fitnessRunAudioScenarioId);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getSpeedLowerLimit() {
        return d(FitnessRunAudioScenarioId.SPEED_SLOW_SPEED_UP);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getSpeedUpperLimit() {
        return d(FitnessRunAudioScenarioId.SPEED_FAST_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getRelativeLowerLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_LOW_SPEED_UP);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getRelativeUpperLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_HIGH_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getAbsoluteLowerLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_LOW_SPEED_UP);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getAbsoluteUpperLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_HIGH_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getHeartRateDeviceError() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_DEVICE_CONNECTION_EXCEPTION);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getHeartRateDeviceConnected() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_DEVICE_CONNECTED);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalHeartRate() {
        return d(FitnessRunAudioScenarioId.CURRENT_HEART_RATE_IN_TARGET_RANGE);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalPace() {
        return d(FitnessRunAudioScenarioId.CURRENT_PACE_IN_TARGET_RANGE);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalSpeed() {
        return d(FitnessRunAudioScenarioId.CURRENT_SPEED_IN_TARGET_RANGE);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalStepRate() {
        return d(FitnessRunAudioScenarioId.CURRENT_STEP_FREQUENCY_IN_TARGET_RANGE);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRLowerLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_LOW_SPEED_UP);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRUpperLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_HIGH_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRIntervalLowerLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_LOW_SPEED_UP);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRIntervalUpperLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_HIGH_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getMAF180LowerLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_LOW_SPEED_UP);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getMAF180UpperLimit() {
        return d(FitnessRunAudioScenarioId.HEART_RATE_HIGH_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getPaceZoneLowerLimit() {
        return d(FitnessRunAudioScenarioId.PACE_SLOW_SPEED_UP);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getPaceZoneUpperLimit() {
        return d(FitnessRunAudioScenarioId.PACE_FAST_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getStepRateUpperLimit() {
        return d(FitnessRunAudioScenarioId.STEP_FREQUENCY_FAST_SLOW_DOWN);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getStepRateLowerLimit() {
        return d(FitnessRunAudioScenarioId.STEP_FREQUENCY_SLOW_HURRY);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeNextAction(String str, int i, int i2, TargetConfig targetConfig) {
        return b(str, i, i2, FitnessRunAudioScenarioId.NEXT_ACTION_RUN, targetConfig);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeLastAction(String str, int i, int i2, TargetConfig targetConfig) {
        return b(str, i, i2, FitnessRunAudioScenarioId.LAST_ACTION_RUN, targetConfig);
    }

    @Override // defpackage.gfq, com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeFirstAction(String str, int i, int i2, TargetConfig targetConfig) {
        return b(str, i, i2, FitnessRunAudioScenarioId.FIRST_ACTION_RUN, targetConfig);
    }

    private Object b(String str, int i, int i2, FitnessRunAudioScenarioId fitnessRunAudioScenarioId, TargetConfig targetConfig) {
        String c;
        if (TextUtils.isEmpty(str) || targetConfig == null) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getChangeAction() nameUrl or intensityConfig is null");
            return null;
        }
        LogUtil.a("Suggestion_MultilingualRunContentConstructor", "getChangeAction===", "value = ", Integer.valueOf(i), "nameUrl = ", str, "measurementType = ", Integer.valueOf(i2), "firstAudio = ", fitnessRunAudioScenarioId);
        if (str.startsWith("http")) {
            c = b(str);
        } else if (mxb.a().c(BaseApplication.getContext()) && this.d.containsKey(str)) {
            c = d(this.d.get(str));
        } else {
            c = c(str);
        }
        String str2 = c;
        if (!new File(str2).exists()) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getChangeAction() is no exist audiosFilePath:", str2);
            return null;
        }
        if (i2 == 0) {
            return e(i, fitnessRunAudioScenarioId, str2, targetConfig);
        }
        if (i2 == 1) {
            return c(i, fitnessRunAudioScenarioId, str2, targetConfig);
        }
        if (i2 == 3) {
            return b(i, fitnessRunAudioScenarioId, str2, targetConfig);
        }
        if (i2 == 4 || i2 == 5) {
            return a(i, fitnessRunAudioScenarioId, str2, targetConfig, i2);
        }
        if (i2 == 255) {
            c(fitnessRunAudioScenarioId, str2, targetConfig);
            return null;
        }
        LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getChangeAction() measurementType:", Integer.valueOf(i2));
        return null;
    }

    private String[] c(int i, FitnessRunAudioScenarioId fitnessRunAudioScenarioId, String str, TargetConfig targetConfig) {
        String[] a2 = a(str, fitnessRunAudioScenarioId, i, FitnessRunAudioScenarioId.FIRST_ACTION_RUN_SEC);
        return d(targetConfig) ? c(a2, targetConfig) : a2;
    }

    private String[] e(int i, FitnessRunAudioScenarioId fitnessRunAudioScenarioId, String str, TargetConfig targetConfig) {
        String[] e;
        if (i % 1000 == 0) {
            e = a(str, fitnessRunAudioScenarioId, i / 1000, FitnessRunAudioScenarioId.FIRST_ACTION_RUN_KM);
        } else {
            e = e(str, fitnessRunAudioScenarioId, UnitUtil.a((i * 1.0d) / 1000.0d, 2), FitnessRunAudioScenarioId.FIRST_ACTION_RUN_KM);
        }
        return d(targetConfig) ? c(e, targetConfig) : e;
    }

    private String[] b(int i, FitnessRunAudioScenarioId fitnessRunAudioScenarioId, String str, TargetConfig targetConfig) {
        String[] a2 = a(str, fitnessRunAudioScenarioId, i, FitnessRunAudioScenarioId.FIRST_ACTION_RUN_KCAL);
        return d(targetConfig) ? c(a2, targetConfig) : a2;
    }

    private String[] a(int i, FitnessRunAudioScenarioId fitnessRunAudioScenarioId, String str, TargetConfig targetConfig, int i2) {
        List<String> scenarioAudioPaths;
        mwz mwzVar = new mwz();
        mwzVar.d(str);
        List<String> scenarioAudioPaths2 = mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), mwzVar);
        mwz mwzVar2 = new mwz();
        mwzVar2.b(Integer.valueOf(i));
        if (i2 == 4) {
            scenarioAudioPaths = mxb.a().getScenarioAudioPaths(FitnessRunAudioScenarioId.FIRST_ACTION_RUN_HEART_RATE_UP.getId(), mwzVar2);
        } else {
            scenarioAudioPaths = mxb.a().getScenarioAudioPaths(FitnessRunAudioScenarioId.FIRST_ACTION_RUN_HEART_RATE_LOW.getId(), mwzVar2);
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.addAll(scenarioAudioPaths2);
        arrayList.addAll(scenarioAudioPaths);
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        return d(targetConfig) ? c(strArr, targetConfig) : strArr;
    }

    private String[] c(FitnessRunAudioScenarioId fitnessRunAudioScenarioId, String str, TargetConfig targetConfig) {
        mwz mwzVar = new mwz();
        mwzVar.d(str);
        String[] strArr = (String[]) mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), mwzVar).toArray(new String[0]);
        return d(targetConfig) ? c(strArr, targetConfig) : strArr;
    }

    private String[] a(String str, FitnessRunAudioScenarioId fitnessRunAudioScenarioId, int i, FitnessRunAudioScenarioId fitnessRunAudioScenarioId2) {
        mwz mwzVar = new mwz();
        mwzVar.d(str);
        List<String> scenarioAudioPaths = mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), mwzVar);
        mwz mwzVar2 = new mwz();
        mwzVar2.b(Integer.valueOf(i));
        scenarioAudioPaths.addAll(mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId2.getId(), mwzVar2));
        return (String[]) scenarioAudioPaths.toArray(new String[0]);
    }

    private String[] e(String str, FitnessRunAudioScenarioId fitnessRunAudioScenarioId, double d, FitnessRunAudioScenarioId fitnessRunAudioScenarioId2) {
        mwz mwzVar = new mwz();
        mwzVar.d(str);
        List<String> scenarioAudioPaths = mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), mwzVar);
        mwz mwzVar2 = new mwz();
        mwzVar2.b(Double.valueOf(d));
        scenarioAudioPaths.addAll(mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId2.getId(), mwzVar2));
        return (String[]) scenarioAudioPaths.toArray(new String[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String[] c(java.lang.String[] r6, com.huawei.pluginfitnessadvice.TargetConfig r7) {
        /*
            r5 = this;
            java.lang.String r0 = "Suggestion_MultilingualRunContentConstructor"
            r1 = 0
            if (r6 != 0) goto Lf
            java.lang.String r6 = "combineAudios nextAudios == null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r6)
            return r1
        Lf:
            java.lang.String r2 = r7.getId()
            int r2 = health.compact.a.CommonUtils.h(r2)
            if (r2 == 0) goto L58
            r3 = 1
            if (r2 == r3) goto L58
            r3 = 2
            if (r2 == r3) goto L58
            r3 = 4
            if (r2 == r3) goto L58
            r3 = 7
            if (r2 == r3) goto L58
            r3 = 8
            if (r2 == r3) goto L58
            switch(r2) {
                case 13: goto L53;
                case 14: goto L3f;
                case 15: goto L53;
                case 16: goto L3a;
                case 17: goto L58;
                default: goto L2c;
            }
        L2c:
            java.lang.String r2 = "combineAudios() other intensityConfig.getId():"
            java.lang.String r7 = r7.getId()
            java.lang.Object[] r7 = new java.lang.Object[]{r2, r7}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r7)
            goto L5c
        L3a:
            java.lang.String[] r1 = r5.c(r7)
            goto L5c
        L3f:
            ftd r1 = defpackage.ftd.e()
            boolean r1 = r1.d()
            if (r1 == 0) goto L4e
            java.lang.String[] r1 = r5.e(r7)
            goto L5c
        L4e:
            java.lang.String[] r1 = r5.a(r7)
            goto L5c
        L53:
            java.lang.String[] r1 = r5.a(r7)
            goto L5c
        L58:
            java.lang.String[] r1 = r5.e(r7)
        L5c:
            if (r1 != 0) goto L68
            java.lang.String r7 = "combineAudios() intensityAudio == null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r7)
            return r6
        L68:
            int r7 = r6.length
            int r0 = r1.length
            int r7 = r7 + r0
            java.lang.String[] r0 = new java.lang.String[r7]
            r2 = 0
        L6e:
            if (r2 >= r7) goto L83
            int r3 = r6.length
            if (r2 >= r3) goto L78
            r3 = r6[r2]
            r0[r2] = r3
            goto L80
        L78:
            int r3 = r1.length
            int r4 = r7 - r2
            int r3 = r3 - r4
            r3 = r1[r3]
            r0[r2] = r3
        L80:
            int r2 = r2 + 1
            goto L6e
        L83:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gfr.c(java.lang.String[], com.huawei.pluginfitnessadvice.TargetConfig):java.lang.String[]");
    }

    private String[] a(TargetConfig targetConfig) {
        int b;
        int b2;
        LogUtil.a("Suggestion_MultilingualRunContentConstructor", "getPaceAudioFilePath() intensityConfig.getId()", targetConfig.getId());
        int valueL = (int) targetConfig.getValueL();
        int valueH = (int) targetConfig.getValueH();
        if (CommonUtils.h(targetConfig.getId()) == 15) {
            b = valueL / 1000;
            b2 = valueH / 1000;
        } else {
            ffg c = ftd.e().c();
            if (c != null && c.j() != 0) {
                b = c.b(valueL, true);
                b2 = c.b(valueH, false);
                LogUtil.a("Suggestion_MultilingualRunContentConstructor", "getPaceAudioFilePath() valueLower:", Integer.valueOf(b), "and valueUpper:", Integer.valueOf(b2));
            } else {
                LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getPaceAudioFilePath() paceZoneConfig == null");
                return null;
            }
        }
        List<String> e = e(b, b2, FitnessRunAudioScenarioId.FIRST_ACTION_RUN_PACING_SCOPE);
        if (koq.b(e)) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getPaceAudioFilePath() scenarioAudioPaths is null");
            return null;
        }
        return (String[]) e.toArray(new String[0]);
    }

    private String[] e(TargetConfig targetConfig) {
        FitnessRunAudioScenarioId fitnessRunAudioScenarioId;
        LogUtil.a("Suggestion_MultilingualRunContentConstructor", "getZoneAudioFilePath() intensityConfig.getId()", targetConfig.getId());
        int c = c(targetConfig, (int) targetConfig.getValueL(), true);
        int c2 = c(targetConfig, (int) targetConfig.getValueH(), false);
        if (c <= 0 || c2 <= 0) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getZoneAudioFilePath() valueLower <= 0 || valueUpper <= 0");
            return null;
        }
        if (CommonUtils.h(targetConfig.getId()) == 0) {
            fitnessRunAudioScenarioId = FitnessRunAudioScenarioId.FIRST_ACTION_RUN_SPEED_SCOPE;
        } else {
            fitnessRunAudioScenarioId = FitnessRunAudioScenarioId.FIRST_ACTION_RUN_HEART_RATE_SCOPE;
        }
        List<String> e = e(c, c2, fitnessRunAudioScenarioId);
        if (koq.b(e)) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getZoneAudioFilePath() scenarioAudioPaths is null");
            return null;
        }
        return (String[]) e.toArray(new String[0]);
    }

    private String[] c(TargetConfig targetConfig) {
        LogUtil.a("Suggestion_MultilingualRunContentConstructor", "getZoneAudioFilePath() intensityConfig.getId()", targetConfig.getId());
        int valueL = (int) targetConfig.getValueL();
        int valueH = (int) targetConfig.getValueH();
        if (valueL <= 0 || valueH <= 0) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getStepRateAudioFilePath() valueLower <= 0 || valueUpper <= 0");
            return null;
        }
        List<String> e = e(valueL, valueH, FitnessRunAudioScenarioId.FIRST_ACTION_RUN_STEP_FREQUENCY_SCOPE);
        if (koq.b(e)) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getStepRateAudioFilePath() scenarioAudioPaths is null");
            return null;
        }
        return (String[]) e.toArray(new String[0]);
    }

    private List<String> e(int i, int i2, FitnessRunAudioScenarioId fitnessRunAudioScenarioId) {
        mwz mwzVar = new mwz();
        mwzVar.b(Integer.valueOf(i));
        mwzVar.b(Integer.valueOf(i2));
        return mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), mwzVar);
    }

    private String d(FitnessRunAudioScenarioId fitnessRunAudioScenarioId) {
        List<String> scenarioAudioPaths = mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), new mwz());
        if (koq.b(scenarioAudioPaths)) {
            LogUtil.h("Suggestion_MultilingualRunContentConstructor", "getSingleVoicePath() scenarioAudioPaths is null");
            return "";
        }
        return scenarioAudioPaths.get(0);
    }
}
