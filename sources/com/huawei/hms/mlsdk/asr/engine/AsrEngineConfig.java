package com.huawei.hms.mlsdk.asr.engine;

import android.os.Bundle;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class AsrEngineConfig {
    public static final int DEFAULT_FEATURE = 11;
    public static final String DEFAULT_LANGUAGE = "en-US";
    public static final int DEFAULT_MAX_AUDIO_DURATION = 60000;
    public static final int DEFAULT_RECOGNIZE_DURATION = 200;
    public static final int DEFAULT_VAD_DETECT_DURATION = 20;
    public static final int DEFAULT_VAD_END_MUTE_DURATION = 700;
    public static final int DEFAULT_VAD_START_MUTE_DETECT_TIMES = 2;
    public static final int DEFAULT_VAD_START_MUTE_DURATION = 3000;
    public static final String FEATURE = "FEATURE";
    public static final String LANGUAGE = "LANGUAGE";
    private static final Map<String, String> LAN_TRANSCODE_MAP;
    public static final String PUNCTUATION_ENABLE = "PUNCTUATION_ENABLE";
    public static final String RECOGNIZER_TYPE = "RECOGNIZER_TYPE";
    public static final String SCENES_KEY = "scenes";
    public static final String VAD_END_MUTE_DURATION = "vadEndMuteDuration";
    public static final String VAD_START_MUTE_DURATION = "vadStartMuteDuration";
    private Boolean enablePunctuation;
    private Boolean enableSilenceDetection;
    private Integer feature;
    private String language;
    private Integer maxAudioDuration;
    private Integer recognizeDuration;
    private int recognizerType;
    private String scenes;
    private boolean sentenceTimeOffset;
    private Integer silenceDurationThreshold;
    private Integer vadDetectDuration;
    private Integer vadEndMuteDuration;
    private Integer vadStartMuteDetectTimes;
    private Integer vadStartMuteDuration;
    private boolean wordTimeOffset;

    static {
        HashMap hashMap = new HashMap();
        LAN_TRANSCODE_MAP = hashMap;
        hashMap.put("zh-CN", MLAsrConstants.LAN_ZH);
        hashMap.put(MLAsrConstants.LAN_ZH, MLAsrConstants.LAN_ZH);
        hashMap.put("en-US", "en-US");
        hashMap.put(MLAsrConstants.LAN_FR_FR, "fr");
        hashMap.put(MLAsrConstants.LAN_ES_ES, "es");
        hashMap.put(MLAsrConstants.LAN_DE_DE, "de");
        hashMap.put(MLAsrConstants.LAN_RU_RU, "ru");
        hashMap.put(MLAsrConstants.LAN_IT_IT, "it");
        hashMap.put(MLAsrConstants.LAN_TH_TH, "th");
        hashMap.put(MLAsrConstants.LAN_MS_MY, "ms");
        hashMap.put(MLAsrConstants.LAN_TR_TR, "tr");
        hashMap.put(MLAsrConstants.LAN_FIL_PH, "fil");
    }

    public Boolean getEnablePunctuation() {
        return this.enablePunctuation;
    }

    public Boolean getEnableSilenceDetection() {
        return this.enableSilenceDetection;
    }

    public int getFeature() {
        if (this.feature == null) {
            this.feature = 11;
        }
        return this.feature.intValue();
    }

    public String getLanguage() {
        if (this.language == null) {
            this.language = "en-US";
        }
        return this.language;
    }

    public int getMaxAudioDuration() {
        if (this.maxAudioDuration == null) {
            this.maxAudioDuration = 60000;
        }
        return this.maxAudioDuration.intValue();
    }

    public int getRecognizeDuration() {
        if (this.recognizeDuration == null) {
            this.recognizeDuration = 200;
        }
        return this.recognizeDuration.intValue();
    }

    public int getRecognizerType() {
        return this.recognizerType;
    }

    public String getScenes() {
        return this.scenes;
    }

    public Integer getSilenceDurationThreshold() {
        return this.silenceDurationThreshold;
    }

    public int getVadDetectDuration() {
        if (this.vadDetectDuration == null) {
            this.vadDetectDuration = 20;
        }
        return this.vadDetectDuration.intValue();
    }

    public int getVadEndMuteDuration() {
        if (this.vadEndMuteDuration == null) {
            this.vadEndMuteDuration = 700;
        }
        return this.vadEndMuteDuration.intValue();
    }

    public int getVadStartMuteDetectTimes() {
        if (this.vadStartMuteDetectTimes == null) {
            this.vadStartMuteDetectTimes = 2;
        }
        return this.vadStartMuteDetectTimes.intValue();
    }

    public int getVadStartMuteDuration() {
        if (this.vadStartMuteDuration == null) {
            this.vadStartMuteDuration = 3000;
        }
        return this.vadStartMuteDuration.intValue();
    }

    public boolean isSentenceTimeOffset() {
        return this.sentenceTimeOffset;
    }

    public boolean isWordTimeOffset() {
        return this.wordTimeOffset;
    }

    public AsrEngineConfig load(Bundle bundle) {
        AsrEngineConfig asrEngineConfig = new AsrEngineConfig();
        if (bundle == null) {
            return asrEngineConfig;
        }
        if (bundle.containsKey("FEATURE")) {
            Integer valueOf = Integer.valueOf(bundle.getInt("FEATURE"));
            this.feature = valueOf;
            asrEngineConfig.setFeature(valueOf.intValue());
        }
        if (bundle.containsKey("LANGUAGE")) {
            String string = bundle.getString("LANGUAGE");
            this.language = string;
            asrEngineConfig.setLanguage(string);
        }
        if (bundle.containsKey("PUNCTUATION_ENABLE")) {
            Boolean valueOf2 = Boolean.valueOf(bundle.getBoolean("PUNCTUATION_ENABLE"));
            this.enablePunctuation = valueOf2;
            asrEngineConfig.setEnablePunctuation(valueOf2);
        }
        if (bundle.containsKey("scenes")) {
            String string2 = bundle.getString("scenes");
            this.scenes = string2;
            asrEngineConfig.setScenes(string2);
        }
        if (bundle.containsKey("vadStartMuteDuration")) {
            Integer valueOf3 = Integer.valueOf(bundle.getInt("vadStartMuteDuration"));
            this.vadStartMuteDuration = valueOf3;
            if (valueOf3.intValue() >= 3000 && this.vadStartMuteDuration.intValue() <= 60000) {
                asrEngineConfig.setVadStartMuteDuration(this.vadStartMuteDuration.intValue());
            }
        }
        if (bundle.containsKey("vadEndMuteDuration")) {
            Integer valueOf4 = Integer.valueOf(bundle.getInt("vadEndMuteDuration"));
            this.vadEndMuteDuration = valueOf4;
            asrEngineConfig.setVadEndMuteDuration(valueOf4.intValue());
        }
        return asrEngineConfig;
    }

    public void setEnablePunctuation(Boolean bool) {
        this.enablePunctuation = bool;
    }

    public void setEnableSilenceDetection(Boolean bool) {
        this.enableSilenceDetection = bool;
    }

    public void setFeature(int i) {
        this.feature = Integer.valueOf(i);
    }

    public void setLanguage(String str) {
        Map<String, String> map = LAN_TRANSCODE_MAP;
        if (map.containsKey(str)) {
            this.language = map.get(str);
        } else {
            this.language = str;
        }
    }

    public void setMaxAudioDuration(int i) {
        this.maxAudioDuration = Integer.valueOf(i);
    }

    public void setRecognizeDuration(int i) {
        this.recognizeDuration = Integer.valueOf(i);
    }

    public void setRecognizerType(int i) {
        this.recognizerType = i;
    }

    public void setScenes(String str) {
        this.scenes = str;
    }

    public void setSentenceTimeOffset(boolean z) {
        this.sentenceTimeOffset = z;
    }

    public void setSilenceDurationThreshold(Integer num) {
        this.silenceDurationThreshold = num;
    }

    public void setVadDetectDuration(int i) {
        this.vadDetectDuration = Integer.valueOf(i);
    }

    public void setVadEndMuteDuration(int i) {
        this.vadEndMuteDuration = Integer.valueOf(i);
    }

    public void setVadStartMuteDetectTimes(int i) {
        this.vadStartMuteDetectTimes = Integer.valueOf(i);
    }

    public void setVadStartMuteDuration(int i) {
        this.vadStartMuteDuration = Integer.valueOf(i);
    }

    public void setWordTimeOffset(boolean z) {
        this.wordTimeOffset = z;
    }
}
