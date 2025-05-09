package com.huawei.health.h5pro.jsbridge.system.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class AudioManagerApi {

    /* renamed from: a, reason: collision with root package name */
    public final Context f2442a;
    public AudioVolumeChangeReceiver c;
    public AudioManager e;

    public interface AudioVolumeListener {
        void onFailure(int i, String str);

        void onSuccess(int i, int i2, int i3);
    }

    public void unRegisterAudioVolumeChangeListener() {
        AudioVolumeChangeReceiver audioVolumeChangeReceiver;
        Context context = this.f2442a;
        if (context != null && (audioVolumeChangeReceiver = this.c) != null) {
            context.unregisterReceiver(audioVolumeChangeReceiver);
        }
        this.c = null;
    }

    public void setAudioVolume(String str, int i, AudioVolumeListener audioVolumeListener) {
        AudioTypeEnum b = b(str);
        if (b == null) {
            audioVolumeListener.onFailure(-1, String.format(Locale.ENGLISH, "setAudioVolume: Invalid parameter '%s'", str));
        } else {
            this.e.setStreamVolume(b.audioType, Math.min(i, this.e.getStreamMaxVolume(b.audioType)), 0);
            c(b, audioVolumeListener);
        }
    }

    public void registerAudioVolumeChangeListener(String str, AudioVolumeListener audioVolumeListener) {
        unRegisterAudioVolumeChangeListener();
        AudioTypeEnum b = b(str);
        if (b == null) {
            audioVolumeListener.onFailure(-1, String.format(Locale.ENGLISH, "registerAudioVolumeChangeListener: Invalid parameter '%s'", str));
            return;
        }
        if (this.f2442a == null) {
            LogUtil.w("H5PRO_AudioManagerApi", "registerAudioVolumeChangeListener: mContext is null");
            return;
        }
        c(b, audioVolumeListener);
        AudioVolumeChangeReceiver audioVolumeChangeReceiver = new AudioVolumeChangeReceiver(b, audioVolumeListener);
        this.c = audioVolumeChangeReceiver;
        this.f2442a.registerReceiver(audioVolumeChangeReceiver, new IntentFilter(Constants.VOLUME_CHANGED_ACTION));
    }

    public void getAudioVolume(String str, AudioVolumeListener audioVolumeListener) {
        AudioTypeEnum b = b(str);
        if (b == null) {
            audioVolumeListener.onFailure(-1, String.format(Locale.ENGLISH, "getAudioVolume: Invalid parameter '%s'", str));
        } else {
            c(b, audioVolumeListener);
        }
    }

    public enum AudioTypeEnum {
        AUDIO_TYPE_MUSIC(3),
        AUDIO_TYPE_RING(2),
        AUDIO_TYPE_SYSTEM(1),
        AUDIO_TYPE_ALARM(4),
        AUDIO_TYPE_NOTIFICATION(5),
        AUDIO_TYPE_VOICE_CALL(0);

        public final int audioType;

        AudioTypeEnum(int i) {
            this.audioType = i;
        }
    }

    private AudioTypeEnum b(String str) {
        try {
            return AudioTypeEnum.valueOf(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public class AudioVolumeChangeReceiver extends BroadcastReceiver {
        public final AudioTypeEnum c;
        public final AudioVolumeListener d;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (!TextUtils.equals(action, Constants.VOLUME_CHANGED_ACTION)) {
                LogUtil.w("H5PRO_AudioManagerApi", "onReceive: action -> " + action);
                return;
            }
            int intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
            AudioTypeEnum audioTypeEnum = this.c;
            if (audioTypeEnum == null || intExtra != audioTypeEnum.audioType) {
                LogUtil.w("H5PRO_AudioManagerApi", "onReceive: audioType -> " + intExtra);
            } else {
                AudioVolumeListener audioVolumeListener = this.d;
                if (audioVolumeListener != null) {
                    AudioManagerApi.this.c(this.c, audioVolumeListener);
                }
            }
        }

        public AudioVolumeChangeReceiver(AudioTypeEnum audioTypeEnum, AudioVolumeListener audioVolumeListener) {
            this.c = audioTypeEnum;
            this.d = audioVolumeListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AudioTypeEnum audioTypeEnum, AudioVolumeListener audioVolumeListener) {
        AudioManager audioManager = this.e;
        if (audioManager == null) {
            LogUtil.w("H5PRO_AudioManagerApi", "mAudioManager is null");
        } else {
            audioVolumeListener.onSuccess(audioManager.getStreamVolume(audioTypeEnum.audioType), this.e.getStreamMaxVolume(audioTypeEnum.audioType), Build.VERSION.SDK_INT >= 28 ? this.e.getStreamMinVolume(audioTypeEnum.audioType) : 0);
        }
    }

    public AudioManagerApi(Context context) {
        this.e = null;
        this.f2442a = context;
        AudioManager audioManager = (AudioManager) context.getSystemService(AudioManager.class);
        this.e = audioManager;
        if (audioManager == null) {
            this.e = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
        }
    }
}
