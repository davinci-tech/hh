package com.huawei.hms.mlsdk.asr.energy.vo;

/* loaded from: classes9.dex */
public class DetectResult {
    public static final int VOICE_TYPE_SILENCE = 1;
    public static final int VOICE_TYPE_SPEECH = 2;
    private float energy;
    private int voiceType;

    public DetectResult(int i, float f) {
        this.voiceType = i;
        this.energy = f;
    }

    public float getEnergy() {
        return this.energy;
    }

    public int getVoiceType() {
        return this.voiceType;
    }

    public void setEnergy(float f) {
        this.energy = f;
    }

    public void setVoiceType(int i) {
        this.voiceType = i;
    }
}
