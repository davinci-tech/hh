package com.huawei.healthcloud.plugintrack.manager.voice;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes4.dex */
public class BackAudioPlayer implements VoicePlayInterface {

    /* renamed from: a, reason: collision with root package name */
    private String f3524a;
    private Queue<String> b = new ConcurrentLinkedQueue();
    private MediaPlayer.OnCompletionListener c = new MediaPlayer.OnCompletionListener() { // from class: com.huawei.healthcloud.plugintrack.manager.voice.BackAudioPlayer.2
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            LogUtil.a("Track_BackAudioPlayer", "play complete.");
            if (BackAudioPlayer.this.b != null) {
                BackAudioPlayer.this.b.poll();
                String str = (String) BackAudioPlayer.this.b.peek();
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                BackAudioPlayer.this.startPlay(str);
            }
        }
    };
    private AudioManager.OnAudioFocusChangeListener d = new AudioManager.OnAudioFocusChangeListener() { // from class: com.huawei.healthcloud.plugintrack.manager.voice.BackAudioPlayer.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (BackAudioPlayer.this.g) {
                LogUtil.h("Track_BackAudioPlayer", "onAudioFocusChange is in mute. focusChange is ", Integer.valueOf(i));
                return;
            }
            if (i == -3) {
                LogUtil.a("Track_BackAudioPlayer", "AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                BackAudioPlayer.this.c();
                return;
            }
            if (i == -2) {
                LogUtil.a("Track_BackAudioPlayer", "AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT");
                BackAudioPlayer.this.c();
                return;
            }
            if (i == -1) {
                LogUtil.a("Track_BackAudioPlayer", "AudioFocus: received AUDIOFOCUS_LOSS");
                BackAudioPlayer.this.c();
                return;
            }
            if (i == 1) {
                LogUtil.a("Track_BackAudioPlayer", "AudioFocus: received AUDIOFOCUS_GAIN");
                BackAudioPlayer.this.resumeVolume();
            } else if (i == 2) {
                LogUtil.a("Track_BackAudioPlayer", "AudioFocus: received AUDIOFOCUS_GAIN_TRANSIENT");
                BackAudioPlayer.this.resumeVolume();
            } else {
                if (i != 3) {
                    return;
                }
                LogUtil.a("Track_BackAudioPlayer", "AudioFocus: received AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                BackAudioPlayer.this.resumeVolume();
            }
        }
    };
    private AudioManager e;
    private boolean g;
    private MediaPlayer h;

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public int getType() {
        return 1;
    }

    public BackAudioPlayer(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.h = mediaPlayer;
        mediaPlayer.setLooping(false);
        this.g = false;
        Object systemService = context.getSystemService(PresenterUtils.AUDIO);
        if (systemService instanceof AudioManager) {
            this.e = (AudioManager) systemService;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void startPlay(String str) {
        LogUtil.a("Track_BackAudioPlayer", "startPlay,", str);
        if (this.h == null) {
            LogUtil.h("Track_BackAudioPlayer", "media player null");
            return;
        }
        if (d() == 0) {
            LogUtil.b("Track_BackAudioPlayer", "request music focus failed.");
        }
        this.f3524a = str;
        try {
            this.h.reset();
            this.h.setDataSource(str);
            this.h.prepare();
            this.h.start();
            this.h.setOnCompletionListener(this.c);
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            LogUtil.b("Track_BackAudioPlayer", "play audio exception.", LogAnonymous.b(e));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void pausePlay() {
        LogUtil.a("Track_BackAudioPlayer", "pausePlay,", this.f3524a);
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer == null) {
            LogUtil.h("Track_BackAudioPlayer", "media player null");
        } else if (mediaPlayer.isPlaying()) {
            this.h.pause();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void continuePlay() {
        LogUtil.a("Track_BackAudioPlayer", "continuePlay,", this.f3524a);
        if (this.h == null) {
            LogUtil.h("Track_BackAudioPlayer", "media player null");
            return;
        }
        if (d() == 0) {
            LogUtil.b("Track_BackAudioPlayer", "request music focus failed.");
        }
        if (this.h.isPlaying()) {
            return;
        }
        LogUtil.a("Track_BackAudioPlayer", "continue.");
        this.h.start();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void muteVolume() {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(0.0f, 0.0f);
            this.g = true;
            LogUtil.a("Track_BackAudioPlayer", "mute volume");
        }
    }

    private int d() {
        AudioManager audioManager = this.e;
        if (audioManager == null) {
            LogUtil.b("Track_BackAudioPlayer", "mAudioManager is null.");
            return 0;
        }
        return audioManager.requestAudioFocus(this.d, 3, 1);
    }

    private int e() {
        AudioManager audioManager = this.e;
        if (audioManager == null) {
            LogUtil.b("Track_BackAudioPlayer", "mAudioManager is null.");
            return 0;
        }
        return audioManager.abandonAudioFocus(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(0.5f, 0.5f);
            LogUtil.a("Track_BackAudioPlayer", "reduce volume");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void resumeVolume() {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(1.0f, 1.0f);
            this.g = false;
            LogUtil.a("Track_BackAudioPlayer", "resume volume");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void stopPlay() {
        LogUtil.a("Track_BackAudioPlayer", "stopPlay,");
        this.h.stop();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void insertVoice(Intent intent) {
        String stringExtra = intent.getStringExtra("param_long_audio_url");
        this.b.offer(stringExtra);
        LogUtil.a("Track_BackAudioPlayer", "insertVoice audioUrl:", stringExtra, " queue:", Integer.valueOf(this.b.size()));
        if (this.b.size() == 1) {
            String peek = this.b.peek();
            if (TextUtils.isEmpty(peek)) {
                return;
            }
            startPlay(peek);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void resetPlayer() {
        LogUtil.a("Track_BackAudioPlayer", "resetPlayer,");
        if (this.b != null) {
            this.h.stop();
            this.h.reset();
            this.b.clear();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void destroy() {
        resetPlayer();
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.h = null;
            e();
            LogUtil.a("Track_BackAudioPlayer", "release,");
        }
    }
}
