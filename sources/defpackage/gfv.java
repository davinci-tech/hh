package defpackage;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.TargetConfig;

/* loaded from: classes4.dex */
public class gfv extends gfx {
    private ffg c;

    public gfv(IFitRunVoiceContentConstructor iFitRunVoiceContentConstructor) {
        super(iFitRunVoiceContentConstructor);
    }

    public void b(ffg ffgVar) {
        this.c = ffgVar;
    }

    @Override // defpackage.gfx, com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public void initStatusUtil(TargetConfig targetConfig) {
        ffg ffgVar = this.c;
        if (ffgVar == null) {
            LogUtil.h(getTag(), "initStatusUtil failed with mPaceZoneConfig is null.");
            return;
        }
        float b = ffgVar.b((int) targetConfig.getValueL(), true);
        float b2 = this.c.b((int) targetConfig.getValueL(), false);
        if (b > 0.0f && b2 > 0.0f) {
            b = moe.c(1000.0f / b);
            b2 = moe.c(1000.0f / b2);
        }
        this.mStatusUtil = new ghx(b2, b, ghx.b());
    }

    @Override // defpackage.gfx, com.huawei.health.suggestion.ui.voice.helper.BaseIntensityVoiceGuider
    public String getTag() {
        return "Suggestion_IntensityPaceZoneGuider";
    }
}
