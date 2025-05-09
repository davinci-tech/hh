package defpackage;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider;
import com.huawei.pluginfitnessadvice.TargetConfig;

/* loaded from: classes4.dex */
public class ggb extends BaseIntensityVoiceGuider {
    public ggb(IFitRunVoiceContentConstructor iFitRunVoiceContentConstructor) {
        super(iFitRunVoiceContentConstructor);
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public void initStatusUtil(TargetConfig targetConfig) {
        this.mStatusUtil = new ghx((float) targetConfig.getValueL(), (float) targetConfig.getValueH(), ghx.c());
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastUpperLimit() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getStepRateUpperLimit());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastLowerLimit() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getStepRateLowerLimit());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastNormal() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getNormalStepRate());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public String getTag() {
        return "Suggestion_IntensityStepRateGuider";
    }
}
