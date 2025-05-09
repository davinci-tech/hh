package com.huawei.health.suggestion.ui.fitness.helper.inter;

import com.huawei.health.suggestion.ui.fitness.module.Motion;

/* loaded from: classes4.dex */
public interface VoiceGuideInterface {
    VoiceGuideInterface countdownGo();

    VoiceGuideInterface customGuide(String str);

    VoiceGuideInterface firstMotion(Motion motion);

    VoiceGuideInterface groupRepeats(int i, int i2);

    VoiceGuideInterface lastGroup();

    VoiceGuideInterface lastMotion(Motion motion);

    VoiceGuideInterface motionName(String str);

    VoiceGuideInterface motionPoint(String str);

    VoiceGuideInterface nextGroup();

    VoiceGuideInterface nextMotion(Motion motion);

    VoiceGuideInterface preMotion(Motion motion);

    VoiceGuideInterface restEnd(int i);

    VoiceGuideInterface voiceGuideContinue();

    VoiceGuideInterface voiceGuidePause();

    VoiceGuideInterface voiceGuideRest();

    VoiceGuideInterface voiceGuideStop();

    VoiceGuideInterface wellDone();
}
