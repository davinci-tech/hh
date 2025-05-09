package com.huawei.hms.mlsdk.asr.energy.vo;

/* loaded from: classes9.dex */
public class VoiceDetectParams {
    private int artiSpeechThd;
    private int beta;
    private int blockVadEnable;
    private int chopNumThd;
    private int currAveAmpThd;
    private int currFrmAlpha;
    private int smoothAmpThd;
    private int smoothFrmAlpha;
    private int smoothUpdateThd;
    private int voiceVarianceThres;

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private int f5057a = 0;
        private int b = 300;
        private int c = 200;
        private int d = 300;
        private int e = 22937;
        private int f = 9830;
        private int g = 12;
        private int h = 300;
        private int i = 8;
        private int j = 0;

        public b a(int i) {
            this.h = i;
            return this;
        }

        public VoiceDetectParams a() {
            return new VoiceDetectParams(this.f5057a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }
    }

    public int getArtiSpeechThd() {
        return this.artiSpeechThd;
    }

    public int getBeta() {
        return this.beta;
    }

    public int getBlockVadEnable() {
        return this.blockVadEnable;
    }

    public int getChopNumThd() {
        return this.chopNumThd;
    }

    public int getCurrAveAmpThd() {
        return this.currAveAmpThd;
    }

    public int getCurrFrmAlpha() {
        return this.currFrmAlpha;
    }

    public int getSmoothAmpThd() {
        return this.smoothAmpThd;
    }

    public int getSmoothFrmAlpha() {
        return this.smoothFrmAlpha;
    }

    public int getSmoothUpdateThd() {
        return this.smoothUpdateThd;
    }

    public int getVoiceVarianceThres() {
        return this.voiceVarianceThres;
    }

    private VoiceDetectParams(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.voiceVarianceThres = i;
        this.currAveAmpThd = i2;
        this.smoothAmpThd = i3;
        this.smoothUpdateThd = i4;
        this.smoothFrmAlpha = i5;
        this.currFrmAlpha = i6;
        this.beta = i7;
        this.artiSpeechThd = i8;
        this.chopNumThd = i9;
        this.blockVadEnable = i10;
    }
}
