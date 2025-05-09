package com.huawei.health.sportservice.manager.skip;

import android.text.TextUtils;
import defpackage.gxd;
import defpackage.mwz;
import defpackage.mxb;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public enum SkipMultilingualVoiceContent {
    PLAY_AUDIO_DATA_SOURCE_ATTACHABLE_EQUIPMENT("S318"),
    PLAY_AUDIO_LAST_TEN_SECONDS("S319"),
    PLAY_AUDIO_GUIDANCE_TONE("S320"),
    PLAY_AUDIO_BODY_INSIDE_THE_SCREEN("S321"),
    PLAY_AUDIO_COME_ON("S332"),
    PLAY_AUDIO_IS_GOOD("S322"),
    PLAY_AUDIO_IT_IS_GOOD("S323"),
    PLAY_AUDIO_IS_GREAT("S324"),
    PLAY_AUDIO_IS_AWESOME("S325"),
    PLAY_AUDIO_IS_VERY_GOOD("S326"),
    PLAY_AUDIO_IS_VERY_GREAT("S327"),
    PLAY_AUDIO_IS_VERY_EXCELLENT("S327"),
    PLAY_AUDIO_IS_PERFECTION("S328"),
    PLAY_AUDIO_IS_INCREDIBLE("S329"),
    PLAY_AUDIO_IS_AMAZING("S330"),
    PLAY_AUDIO_CLOSER_TIP("S331"),
    EMPTY("S507"),
    HEART_RATE_TOO_FAST("S256"),
    SKIP_NUM("S509");

    private static final HashMap<String, String> TRANSLATION_MAP;
    private final String mId;

    static {
        SkipMultilingualVoiceContent skipMultilingualVoiceContent = PLAY_AUDIO_DATA_SOURCE_ATTACHABLE_EQUIPMENT;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent2 = PLAY_AUDIO_LAST_TEN_SECONDS;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent3 = PLAY_AUDIO_GUIDANCE_TONE;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent4 = PLAY_AUDIO_BODY_INSIDE_THE_SCREEN;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent5 = PLAY_AUDIO_COME_ON;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent6 = PLAY_AUDIO_IS_GOOD;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent7 = PLAY_AUDIO_IT_IS_GOOD;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent8 = PLAY_AUDIO_IS_GREAT;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent9 = PLAY_AUDIO_IS_AWESOME;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent10 = PLAY_AUDIO_IS_VERY_GOOD;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent11 = PLAY_AUDIO_IS_VERY_GREAT;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent12 = PLAY_AUDIO_IS_PERFECTION;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent13 = PLAY_AUDIO_IS_INCREDIBLE;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent14 = PLAY_AUDIO_IS_AMAZING;
        SkipMultilingualVoiceContent skipMultilingualVoiceContent15 = PLAY_AUDIO_CLOSER_TIP;
        HashMap<String, String> hashMap = new HashMap<>();
        TRANSLATION_MAP = hashMap;
        hashMap.put("S003", skipMultilingualVoiceContent.value());
        hashMap.put("L001", skipMultilingualVoiceContent2.value());
        hashMap.put("L002", skipMultilingualVoiceContent3.value());
        hashMap.put("L003", skipMultilingualVoiceContent4.value());
        hashMap.put("L004", skipMultilingualVoiceContent5.value());
        hashMap.put("L005", skipMultilingualVoiceContent6.value());
        hashMap.put("L006", skipMultilingualVoiceContent7.value());
        hashMap.put("L007", skipMultilingualVoiceContent8.value());
        hashMap.put("L008", skipMultilingualVoiceContent9.value());
        hashMap.put("L009", skipMultilingualVoiceContent10.value());
        hashMap.put("L010", skipMultilingualVoiceContent11.value());
        hashMap.put("L011", skipMultilingualVoiceContent11.value());
        hashMap.put("L012", skipMultilingualVoiceContent12.value());
        hashMap.put("L013", skipMultilingualVoiceContent13.value());
        hashMap.put("L014", skipMultilingualVoiceContent14.value());
        hashMap.put("L016", skipMultilingualVoiceContent15.value());
    }

    SkipMultilingualVoiceContent(String str) {
        this.mId = str;
    }

    public String value() {
        return this.mId;
    }

    public static String getTranslationMap(String str) {
        String str2 = TRANSLATION_MAP.get(str);
        return TextUtils.isEmpty(str2) ? EMPTY.value() : str2;
    }

    public static void playMultipleSourcesVoice(String str, mwz mwzVar) {
        List<String> scenarioAudioPaths = mxb.a().getScenarioAudioPaths(str, mwzVar);
        String[] strArr = new String[scenarioAudioPaths.size()];
        for (int i = 0; i < scenarioAudioPaths.size(); i++) {
            if (!TextUtils.isEmpty(scenarioAudioPaths.get(i))) {
                strArr[i] = scenarioAudioPaths.get(i);
            }
        }
        gxd.a().b(strArr);
    }
}
