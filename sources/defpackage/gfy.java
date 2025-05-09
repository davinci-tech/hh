package defpackage;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.pluginfitnessadvice.TargetConfig;

/* loaded from: classes4.dex */
public class gfy extends BaseIntensityVoiceGuider {
    protected HeartZoneConf d;

    public gfy(IFitRunVoiceContentConstructor iFitRunVoiceContentConstructor) {
        super(iFitRunVoiceContentConstructor);
    }

    public void b(HeartZoneConf heartZoneConf) {
        this.d = heartZoneConf;
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider, com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void guide(TargetConfig targetConfig, float f) {
        if (ghp.d((int) f)) {
            super.guide(targetConfig, f);
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public void initStatusUtil(TargetConfig targetConfig) {
        this.mStatusUtil = new ghx((int) targetConfig.getValueL(), (int) targetConfig.getValueH(), ghx.e());
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastUpperLimit() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getAbsoluteUpperLimit());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastLowerLimit() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getAbsoluteLowerLimit());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastNormal() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getNormalHeartRate());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public String getTag() {
        return "Suggestion_AbsoluteHeartRateGuider";
    }
}
