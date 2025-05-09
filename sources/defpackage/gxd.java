package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.healthcloud.plugintrack.manager.service.VoiceEngService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class gxd {
    private static final gxd c = new gxd();
    private List<String> b = new ArrayList(10);
    private boolean d = false;

    private gxd() {
    }

    public static gxd a() {
        return c;
    }

    public void h(String str) {
        if (str == null) {
            LogUtil.b("Track_VoiceCommonUtils", "moduleName is null");
            return;
        }
        this.b.add(str);
        ReleaseLogUtil.e("Track_VoiceCommonUtils", str, " start service ", Integer.valueOf(this.b.size()));
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) VoiceEngService.class);
        intent.setAction("action_voice_soundpool_enge");
        try {
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException unused) {
            LogUtil.b("Track_VoiceCommonUtils", "startVoiceService IllegalStateException");
        }
    }

    public void d(String str, String str2, int i) {
        if (str == null) {
            LogUtil.b("Track_VoiceCommonUtils", "moduleName is null");
            return;
        }
        this.b.add(str);
        ReleaseLogUtil.e("Track_VoiceCommonUtils", str, " start service ", str2, " current size:", Integer.valueOf(this.b.size()));
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) VoiceEngService.class);
        intent.setAction("action_voice_soundpool_enge");
        intent.putExtra("player_name_key", str2);
        intent.putExtra("player_type_key", i);
        try {
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException unused) {
            LogUtil.b("Track_VoiceCommonUtils", "startVoiceService IllegalStateException moduleName ", str);
        }
    }

    public void f(String str) {
        if (str == null) {
            LogUtil.a("Track_VoiceCommonUtils", "moduleName is null");
            return;
        }
        ReleaseLogUtil.e("Track_VoiceCommonUtils", str, " stop service ", Integer.valueOf(this.b.size()));
        this.b.remove(str);
        if (this.b.size() == 0) {
            gut.aUn_(BaseApplication.getContext(), new Intent("action_stop_service"));
        }
    }

    public void c(int i) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "playSingleSourceVoice");
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("SPEAK_TYPE", 10);
        intent.putExtra("SPEAK_PARAMETER_TYPE", 0);
        intent.putExtra("SPEAK_PARAMETER", i);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void b(String str) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "playSingleSourceVoice");
        if (!str.contains("assert") && !d(str)) {
            LogUtil.h("Track_VoiceCommonUtils", "playSingleSourceVoice file not exist");
            return;
        }
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("SPEAK_TYPE", 10);
        intent.putExtra("SPEAK_PARAMETER_TYPE", 2);
        intent.putExtra("SPEAK_PARAMETER", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void a(int[] iArr) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "playMultipleSourcesVoice");
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("SPEAK_TYPE", 10);
        intent.putExtra("SPEAK_PARAMETER_TYPE", 1);
        intent.putExtra("SPEAK_PARAMETER", iArr);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void b(String[] strArr) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "playMultipleSourcesVoice");
        if (!a(strArr)) {
            LogUtil.h("Track_VoiceCommonUtils", "playMultipleSourcesVoice file not exist");
            return;
        }
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("SPEAK_TYPE", 10);
        intent.putExtra("SPEAK_PARAMETER_TYPE", 3);
        intent.putExtra("SPEAK_PARAMETER", strArr);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void e(String[] strArr, String str) {
        if (str == null) {
            b(strArr);
            return;
        }
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "playMultipleSourcesVoice carrying parameters");
        if (!a(strArr)) {
            LogUtil.h("Track_VoiceCommonUtils", "playMultipleSourcesVoice file not exist");
            return;
        }
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("SPEAK_TYPE", 10);
        intent.putExtra("SPEAK_PARAMETER_TYPE", 3);
        intent.putExtra("SPEAK_PARAMETER", strArr);
        intent.putExtra("SPEAK_EXTRA_MESSAGE", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void a(String str) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "playSkipNativeVoice() path: ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_VoiceCommonUtils", "playSkipNativeVoice() path is null or empty");
            return;
        }
        if (!str.contains("assert") && !d(str)) {
            LogUtil.h("Track_VoiceCommonUtils", "playSkipNativeVoice() file not exist");
            return;
        }
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("SPEAK_TYPE", 35);
        intent.putExtra("SPEAK_PARAMETER", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void d(gxi gxiVar) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "playStateVoice");
        if (gxiVar == null) {
            LogUtil.h("Track_VoiceCommonUtils", "playStateVoice param is null");
            return;
        }
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("SPEAK_TYPE", gxiVar.ac());
        intent.putExtra("SPEAK_PARAMETER", gxiVar);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void d(String str, String str2) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "addAudioToPlayList playerName:", str);
        LogUtil.a("Track_VoiceCommonUtils", "audioPath:", str2);
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("Track_VoiceCommonUtils", "audioPath is empty");
            return;
        }
        Intent intent = new Intent("action_play_voice");
        intent.putExtra("player_name_key", str);
        intent.putExtra("param_long_audio_url", str2);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void d(String str, int i) {
        if (i == 1) {
            c(str);
            return;
        }
        if (i == 2) {
            i(str);
            return;
        }
        if (i == 5) {
            j(str);
            return;
        }
        if (i == 101) {
            e(str);
        } else if (i == 102) {
            g(str);
        } else {
            ReleaseLogUtil.d("Track_VoiceCommonUtils", "sendPlayerCommand error, playerCommand not support.", Integer.valueOf(i));
        }
    }

    public void c(String str) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "pauseBackgroundAudio,", str);
        Intent intent = new Intent("action_pause_audio");
        intent.putExtra("player_name_key", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void i(String str) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "resumeBackgroundAudio,", str);
        Intent intent = new Intent("action_resume_audio");
        intent.putExtra("player_name_key", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void e(String str) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "muteBackgroundAudio,", str);
        Intent intent = new Intent("action_mute_audio_volume");
        intent.putExtra("player_name_key", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void g(String str) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "resumeBackgroundAudioVolume,", str);
        Intent intent = new Intent("action_resume_audio_volume");
        intent.putExtra("player_name_key", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void j(String str) {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "resetPlayIdle");
        Intent intent = new Intent("ACTION_RESET_MEDIA_IDLE");
        intent.putExtra("player_name_key", str);
        gut.aUn_(BaseApplication.getContext(), intent);
    }

    public void e() {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "stopVoicePlay");
        gut.aUn_(BaseApplication.getContext(), new Intent("action_stop_voice"));
    }

    public void d() {
        ReleaseLogUtil.e("Track_VoiceCommonUtils", "resetPlayIdle");
        gut.aUn_(BaseApplication.getContext(), new Intent("ACTION_RESET_MEDIA_IDLE"));
    }

    public boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (new File(str).exists()) {
            return true;
        }
        LogUtil.h("Track_VoiceCommonUtils", "isAudioResourceExist path not exist path = ", str);
        return false;
    }

    public boolean a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        for (String str : strArr) {
            if (!str.contains("assert") && !d(str)) {
                return false;
            }
        }
        return true;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean c() {
        return this.d;
    }
}
