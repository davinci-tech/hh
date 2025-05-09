package defpackage;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.TargetConfig;
import health.compact.a.CommonUtil;

/* loaded from: classes4.dex */
public class gfz extends gfy {
    public gfz(IFitRunVoiceContentConstructor iFitRunVoiceContentConstructor) {
        super(iFitRunVoiceContentConstructor);
    }

    @Override // defpackage.gfy, com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public void initStatusUtil(TargetConfig targetConfig) {
        if (this.d == null) {
            LogUtil.h("Suggestion_RelativeHeartRateGuider", "initStatusUtil failed with null mHeartZoneConf.");
            return;
        }
        int valueL = (int) targetConfig.getValueL();
        int valueH = (int) targetConfig.getValueH();
        int h = CommonUtil.h(targetConfig.getId());
        this.mStatusUtil = new ghx(ghp.b(this.d, h, valueL, true), ghp.b(this.d, h, valueH, false), ghx.e());
    }

    @Override // defpackage.gfy, com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public String getTag() {
        return "Suggestion_RelativeHeartRateGuider";
    }
}
