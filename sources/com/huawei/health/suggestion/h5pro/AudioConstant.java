package com.huawei.health.suggestion.h5pro;

import com.huawei.health.suggestion.ui.fitness.helper.FitnessRunAudioScenarioId;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class AudioConstant {
    public static final String AUDIO = ".mp3";
    public static final HashMap<FitnessRunAudioScenarioId, String> AUDIO_LIST = new HashMap<FitnessRunAudioScenarioId, String>() { // from class: com.huawei.health.suggestion.h5pro.AudioConstant.1
        {
            put(FitnessRunAudioScenarioId.STAY_SINGLE_IN_FRONT_OF_DEVICE, "F189");
            put(FitnessRunAudioScenarioId.GO_FARTHER, "F190");
            put(FitnessRunAudioScenarioId.COME_CLOSER, "F191");
            put(FitnessRunAudioScenarioId.BACK_TO_DEVICE, "F193");
            put(FitnessRunAudioScenarioId.ADJUST_POSITION_TO_KEEP_WHOLE_BODY, "F193");
            put(FitnessRunAudioScenarioId.FACE_THE_SCREEN, "");
            put(FitnessRunAudioScenarioId.FACE_THE_SCREEN_SIDEWAYS, "");
            put(FitnessRunAudioScenarioId.PERFECT, "F196");
            put(FitnessRunAudioScenarioId.GREAT, "F197");
            put(FitnessRunAudioScenarioId.EXCELLENT, "F198");
            put(FitnessRunAudioScenarioId.REP_DI_AUDIO, "F199");
            put(FitnessRunAudioScenarioId.SEC_DI_AUDIO, "D011");
            put(FitnessRunAudioScenarioId.KEEP_THE_ENVIRONMENT_BRIGHT, "F201");
            put(FitnessRunAudioScenarioId.HOLD_ON_FIVE_SECONDS, "E069");
            put(FitnessRunAudioScenarioId.NUMBER_FIVE, "B005");
            put(FitnessRunAudioScenarioId.NUMBER_FOUR, "B004");
            put(FitnessRunAudioScenarioId.NUMBER_THREE, "B003");
            put(FitnessRunAudioScenarioId.NUMBER_TWO, "B002");
            put(FitnessRunAudioScenarioId.NUMBER_ONE, "B001");
            put(FitnessRunAudioScenarioId.COUNTDOWN_GO, "D001");
            put(FitnessRunAudioScenarioId.CONGRATULATE_COMPLETE_TRAIN, "E068");
            put(FitnessRunAudioScenarioId.TAKE_A_BREAK, "E008");
            put(FitnessRunAudioScenarioId.BREAK_OVER, "E009");
        }
    };
    public static final String BACKGROUND_MUSIC_CODE = "D010";
    public static final String GUIDE_VOLUME_CHANGE_HINT_CODE = "E071";
    public static final String MAN = "M";
    public static final String WOMAN = "F";

    private AudioConstant() {
    }
}
