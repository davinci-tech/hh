package defpackage;

import android.media.MediaPlayer;
import android.net.Uri;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class had {

    /* renamed from: a, reason: collision with root package name */
    private static volatile had f13042a;

    private had() {
    }

    public static had d() {
        LogUtil.c("Track_SmartCoachVoicePlayHandle", "SmartCoachVoicePlayHandler getInstance()");
        if (f13042a == null) {
            synchronized (had.class) {
                if (f13042a == null) {
                    f13042a = new had();
                }
            }
        }
        return f13042a;
    }

    public String d(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return b() + str + AudioConstant.AUDIO;
    }

    public String b() {
        return gzv.e + "1.1.1" + File.separator + "mp3" + File.separator + "zh-CN" + File.separator + "female" + File.separator + "mandarin" + File.separator;
    }

    public long b(String str) {
        long j = 0;
        if (str != null && !str.isEmpty()) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(str);
                mediaPlayer.prepare();
                j = mediaPlayer.getDuration();
            } catch (IOException e) {
                LogUtil.a("Track_SmartCoachVoicePlayHandle", LogAnonymous.b((Throwable) e));
            }
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        return j;
    }

    public long a(int i) {
        long j;
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(BaseApplication.getContext(), Uri.parse("android.resource://com.huawei.health/" + i));
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.prepare();
            j = mediaPlayer.getDuration();
        } catch (Exception e) {
            LogUtil.a("Track_SmartCoachVoicePlayHandle", LogAnonymous.b((Throwable) e));
            j = 0;
        }
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        return j;
    }

    public long b(List<Object> list, Map<Integer, Integer> map) {
        long j = 0;
        if (koq.b(list)) {
            LogUtil.h("Track_SmartCoachVoicePlayHandle", "getTotalAudioFileVoiceTime objectList is empty.");
            return 0L;
        }
        for (Object obj : list) {
            if (obj instanceof String) {
                j += b((String) obj);
            }
            if (obj instanceof Integer) {
                j += a(map.get(Integer.valueOf(((Integer) obj).intValue())).intValue());
            }
        }
        LogUtil.c("Track_SmartCoachVoicePlayHandle", "getTotalAudioFileVoiceTime totalDuration = ", Long.valueOf(j));
        return j;
    }
}
