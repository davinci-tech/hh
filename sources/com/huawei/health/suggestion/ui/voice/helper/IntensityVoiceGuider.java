package com.huawei.health.suggestion.ui.voice.helper;

import com.huawei.pluginfitnessadvice.TargetConfig;

/* loaded from: classes4.dex */
public interface IntensityVoiceGuider {
    void broadcastLowerLimit();

    void broadcastNo();

    void broadcastNormal();

    void broadcastUpperLimit();

    void guide(TargetConfig targetConfig, float f);

    void reset();
}
