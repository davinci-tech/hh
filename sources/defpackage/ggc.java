package defpackage;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider;
import com.huawei.pluginfitnessadvice.TargetConfig;

/* loaded from: classes4.dex */
public class ggc extends BaseIntensityVoiceGuider {
    public ggc(IFitRunVoiceContentConstructor iFitRunVoiceContentConstructor) {
        super(iFitRunVoiceContentConstructor);
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public void initStatusUtil(TargetConfig targetConfig) {
        this.mStatusUtil = new ghx((float) targetConfig.getValueL(), (float) targetConfig.getValueH(), ghx.d());
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastUpperLimit() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getSpeedUpperLimit());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastLowerLimit() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getSpeedLowerLimit());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastNormal() {
        if (this.mVoiceContentConstructor != null) {
            playSound(this.mVoiceContentConstructor.getNormalSpeed());
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public String getTag() {
        return "Suggestion_IntensitySpeedGuider";
    }
}
