package com.huawei.health.suggestion.ui.voice.helper;

import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.pluginfitnessadvice.TargetConfig;
import defpackage.eme;
import defpackage.fis;
import defpackage.ghx;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public abstract class BaseIntensityVoiceGuider implements IntensityVoiceGuider {
    protected ghx mStatusUtil;
    protected IFitRunVoiceContentConstructor mVoiceContentConstructor;

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void broadcastNo() {
    }

    protected abstract String getTag();

    protected abstract void initStatusUtil(TargetConfig targetConfig);

    public BaseIntensityVoiceGuider(IFitRunVoiceContentConstructor iFitRunVoiceContentConstructor) {
        this.mVoiceContentConstructor = iFitRunVoiceContentConstructor;
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void guide(TargetConfig targetConfig, float f) {
        if (targetConfig == null) {
            return;
        }
        if (this.mStatusUtil == null) {
            LogUtil.c(getTag(), "guide with init StatusUtil.");
            initStatusUtil(targetConfig);
        }
        ghx ghxVar = this.mStatusUtil;
        if (ghxVar == null) {
            LogUtil.c(getTag(), "guide failed with StatusUtil is null.");
            return;
        }
        ghxVar.c(f);
        int a2 = this.mStatusUtil.a();
        if (a2 == 1) {
            broadcastLowerLimit();
            return;
        }
        if (a2 == 2) {
            broadcastUpperLimit();
        } else if (a2 == 3) {
            broadcastNormal();
        } else {
            if (a2 != 4) {
                return;
            }
            broadcastNo();
        }
    }

    protected void playSound(Object obj) {
        playSound(obj, null);
    }

    protected void playSound(Object obj, String str) {
        if (UnitUtil.h() || !eme.b().getVoiceEnable()) {
            return;
        }
        if (obj == null) {
            LogUtil.e(getTag(), "playSound sound == null");
        } else {
            fis.d().a(obj, str);
        }
    }

    @Override // com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider
    public void reset() {
        this.mStatusUtil = null;
    }
}
