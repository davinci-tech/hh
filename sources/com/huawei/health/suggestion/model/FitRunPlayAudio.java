package com.huawei.health.suggestion.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class FitRunPlayAudio {
    public static final String OPPORTUNITY_E = "e";
    public static final String OPPORTUNITY_M = "m";
    public static final String OPPORTUNITY_P = "p";
    public static final String PLAY_TYPE_D = "d";
    public static final String PLAY_TYPE_H = "h";
    public static final String PLAY_TYPE_T = "t";
    public static final String PLAY_TYPE_V = "v";

    @SerializedName("audioId")
    private String mAudioId;

    @SerializedName("audioUrl")
    private String mAudioUrl;

    @SerializedName("isPlay")
    private boolean mIsPlay;

    @SerializedName("opportunity")
    private String mOpportunity;

    @SerializedName("playType")
    private String mPlayType;

    @SerializedName("playValue")
    private float mPlayValue;

    public String acquireAudioId() {
        return this.mAudioId;
    }

    public void saveAudioId(String str) {
        this.mAudioId = str;
    }

    public String acquirePlayType() {
        return this.mPlayType;
    }

    public void savePlayType(String str) {
        this.mPlayType = str;
    }

    public String acquireAudioUrl() {
        return this.mAudioUrl;
    }

    public void saveAudioUrl(String str) {
        this.mAudioUrl = str;
    }

    public String acquireOpportunity() {
        return this.mOpportunity;
    }

    public void saveOpportunity(String str) {
        this.mOpportunity = str;
    }

    public float acquirePlayValue() {
        return this.mPlayValue;
    }

    public void savePlayValue(float f) {
        this.mPlayValue = f;
    }

    public boolean acquireIsPlay() {
        return this.mIsPlay;
    }

    public void saveIsPlay(boolean z) {
        this.mIsPlay = z;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
