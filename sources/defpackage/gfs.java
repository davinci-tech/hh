package defpackage;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.TargetConfig;
import java.io.File;

/* loaded from: classes4.dex */
public class gfs implements IFitRunVoiceContentConstructor {

    /* renamed from: a, reason: collision with root package name */
    private String f12791a;
    private String d;
    private String e;

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getSpeedLowerLimit() {
        return d("K013");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getSpeedUpperLimit() {
        return d("K012");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getRelativeLowerLimit() {
        return d("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getRelativeUpperLimit() {
        return d("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getAbsoluteLowerLimit() {
        return d("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getAbsoluteUpperLimit() {
        return d("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getHeartRateDeviceError() {
        return d("K016");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getHeartRateDeviceConnected() {
        return d("K017");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalHeartRate() {
        return d("K027");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalPace() {
        return d("K028");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalSpeed() {
        return d("K029");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalStepRate() {
        return d("K030");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeNextAction(String str, int i, int i2, TargetConfig targetConfig) {
        return b(str, i, i2, "K002");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeLastAction(String str, int i, int i2, TargetConfig targetConfig) {
        return b(str, i, i2, "K003");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeFirstAction(String str, int i, int i2, TargetConfig targetConfig) {
        return b(str, i, i2, "K001");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRLowerLimit() {
        return d("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRUpperLimit() {
        return d("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRIntervalLowerLimit() {
        return d("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRIntervalUpperLimit() {
        return d("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getMAF180LowerLimit() {
        return d("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getMAF180UpperLimit() {
        return d("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getPaceZoneLowerLimit() {
        return d("K022");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getPaceZoneUpperLimit() {
        return d("K022");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getStepRateLowerLimit() {
        return d("K032");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getStepRateUpperLimit() {
        return d("K031");
    }

    private Object b(String str, int i, int i2, String str2) {
        String d;
        if (str == null || "".equals(str)) {
            LogUtil.b("Suggestion_ChineseFitRunVoiceContentConstructor", "getChangeAction nameUrl == NULL || \"\".equals(nameUrl)");
            return null;
        }
        LogUtil.a("Suggestion_ChineseFitRunVoiceContentConstructor", "value = ", Integer.valueOf(i), "nameUrl = ", str, "measurementType = ", Integer.valueOf(i2), "firstAudio = ", str2);
        if (str.startsWith("http")) {
            d = a(str);
        } else {
            d = d(str);
            if (!new File(d).exists()) {
                LogUtil.b("Suggestion_ChineseFitRunVoiceContentConstructor", "getChangeAction !new File(audiosFilePath).exists() = true , audiosFilePath = ", d);
                return null;
            }
        }
        if (i2 == 1) {
            return e(i, str2, d);
        }
        if (i2 == 0) {
            return a(i, str2, d);
        }
        return null;
    }

    private String[] e(int i, String str, String str2) {
        String str3;
        String str4;
        if (i < 60) {
            if (i < 10) {
                str4 = "H00" + i;
            } else {
                str4 = "H0" + i;
            }
            return new String[]{d(str), str2, d(str4), d("I009")};
        }
        int i2 = i / 60;
        if (i2 > 0 && i2 < 10) {
            str3 = "H00" + i2;
        } else if (i2 >= 10 && i2 <= 60) {
            str3 = "H0" + i2;
        } else {
            LogUtil.h("Suggestion_ChineseFitRunVoiceContentConstructor", "MEASUREMENT TYPE_TIME value = ", Integer.valueOf(i));
            return null;
        }
        return new String[]{d(str), str2, d(str3), d("I007")};
    }

    private String[] a(int i, String str, String str2) {
        if (i > 100 && i < 1000) {
            return new String[]{d(str), str2, d("H00" + (i / 100)), d("I002")};
        }
        if (i >= 1000 && i < 10000) {
            int i2 = i / 1000;
            int i3 = (i % 1000) / 100;
            if (i3 == 0) {
                return new String[]{d(str), str2, d("H00" + i2), d("I003")};
            }
            return new String[]{d(str), str2, d("H00" + i2), d("I005"), d("H00" + i3), d("I002")};
        }
        LogUtil.h("Suggestion_ChineseFitRunVoiceContentConstructor", "MEASUREMENT TYPE_DISTANCE value = ", Integer.valueOf(i));
        return null;
    }

    public static String a(String str) {
        return squ.c(str);
    }

    private String d(String str) {
        return b(str, this.f12791a, this.d, this.e);
    }

    private String b(String str, String str2, String str3, String str4) {
        return squ.a(str, str2, str3, str4);
    }
}
