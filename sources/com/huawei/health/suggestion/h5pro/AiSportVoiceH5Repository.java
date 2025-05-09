package com.huawei.health.suggestion.h5pro;

import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.suggestion.h5pro.h5params.PlayOrPauseParam;
import com.huawei.health.suggestion.h5pro.h5params.SpeakParams;
import com.huawei.health.suggestion.h5pro.h5params.VolumeParam;
import com.huawei.hwlogsmodel.LogUtil;

@H5ProService(name = "SportVoice")
/* loaded from: classes.dex */
public class AiSportVoiceH5Repository {
    private static final String TAG = "AISportVoiceH5Repository";

    @H5ProMethod(name = "playBackgroundMusic")
    public static void playBackgroundMusic(AiSportH5Cbk aiSportH5Cbk) {
        AiSportVoiceHelper.getInstance().playOrPauseBackground(true);
        aiSportH5Cbk.onSuccess(0);
    }

    @H5ProMethod(name = "speak")
    public static void speak(SpeakParams speakParams, AiSportH5Cbk aiSportH5Cbk) {
        int type = speakParams.getType();
        if (type == 0) {
            LogUtil.a(TAG, "play by scene code: ", speakParams.getCode());
            AiSportVoiceHelper.getInstance().playByScene(speakParams.getLanguage(), speakParams.getCode(), speakParams, aiSportH5Cbk);
        } else if (type == 1) {
            AiSportVoiceHelper.getInstance().playByUrl(speakParams.getUrl(), aiSportH5Cbk);
        }
    }

    @H5ProMethod(name = "playOrPause")
    public static void playOrPause(PlayOrPauseParam playOrPauseParam, AiSportH5Cbk aiSportH5Cbk) {
        AiSportVoiceHelper.getInstance().playOrPauseBeat(playOrPauseParam.isCounterPlay());
        AiSportVoiceHelper.getInstance().playOrPauseGuide(playOrPauseParam.isGuiderPlay());
        AiSportVoiceHelper.getInstance().playOrPauseBackground(playOrPauseParam.isBackgroundPlay());
        aiSportH5Cbk.onSuccess(0);
        AiSportVoiceHelper.getInstance().abandonAudioFocus();
    }

    @H5ProMethod(name = "volume")
    public static void volume(VolumeParam volumeParam, AiSportH5Cbk aiSportH5Cbk) {
        AiSportVoiceHelper.getInstance().setBeatVolume(volumeParam.getCounter());
        AiSportVoiceHelper.getInstance().setGuideVolume(volumeParam.getGuider());
        AiSportVoiceHelper.getInstance().setBackgroundVolume(volumeParam.getBackground());
        aiSportH5Cbk.onSuccess(0);
    }

    @H5ProMethod(name = "getVolume")
    public static void getVolume(AiSportH5Cbk aiSportH5Cbk) {
        aiSportH5Cbk.onSuccess(AiSportVoiceHelper.getInstance().getAllVolume());
    }

    @H5ProMethod(name = "stop")
    public static void stop(AiSportH5Cbk aiSportH5Cbk) {
        AiSportVoiceHelper.getInstance().stopAll();
        aiSportH5Cbk.onSuccess(0);
    }
}
