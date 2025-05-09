package com.huawei.healthcloud.plugintrack.manager.voice;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class PrimaryVoicePlayManager {
    private Context b;
    private Map<String, VoicePlayInterface> e = new HashMap();

    public PrimaryVoicePlayManager(Context context) {
        this.b = context;
    }

    public void d(int i, String str) {
        VoicePlayInterface voicePlayer;
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "createVoicePlayer,type:", Integer.valueOf(i), " name:", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_PrimaryVoicePlayManager", "voice player name not define.");
            str = "defaultPlayer";
        }
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.resetPlayer();
            LogUtil.h("Track_PrimaryVoicePlayManager", "voice player has exist.", str);
        } else {
            if (i == 1) {
                voicePlayer = new BackAudioPlayer(this.b);
            } else {
                voicePlayer = new VoicePlayer(this.b);
            }
            this.e.put(str, voicePlayer);
        }
    }

    public void e() {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "release voice player");
        Iterator<VoicePlayInterface> it = this.e.values().iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
    }

    public void aVo_(String str, Intent intent) {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "insertVoice player name:", str);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.insertVoice(intent);
        }
    }

    public void c(String str) {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "resetVoicePlayer() enter,", str);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.resetPlayer();
        }
    }

    public void j(String str) {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "stopVoice() enter", str);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.stopPlay();
        }
    }

    public void b(String str, String str2) {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "startPlay() enter,", str, " audio url:", str2);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.startPlay(str2);
        }
    }

    public void a(String str) {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "pausePlay() enter,", str);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.pausePlay();
        }
    }

    public void d(String str) {
        LogUtil.a("Track_PrimaryVoicePlayManager", "continuePlay() enter,", str);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.continuePlay();
        }
    }

    public void b(String str) {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "muteVolume() enter,", str);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.muteVolume();
        }
    }

    public void e(String str) {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "resumeVolume() enter,", str);
        VoicePlayInterface voicePlayInterface = this.e.get(str);
        if (voicePlayInterface != null) {
            voicePlayInterface.resumeVolume();
        }
    }

    public void d() {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "dealWithPhoneCalling");
        for (VoicePlayInterface voicePlayInterface : this.e.values()) {
            if (voicePlayInterface.getType() == 1) {
                voicePlayInterface.muteVolume();
            } else if (voicePlayInterface.getType() == 0) {
                voicePlayInterface.stopPlay();
                voicePlayInterface.resetPlayer();
            }
        }
    }

    public void a() {
        ReleaseLogUtil.e("Track_PrimaryVoicePlayManager", "dealWithPhoneHangUp");
        for (VoicePlayInterface voicePlayInterface : this.e.values()) {
            if (voicePlayInterface.getType() == 1) {
                voicePlayInterface.resumeVolume();
            }
        }
    }
}
