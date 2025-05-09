package com.huawei.healthcloud.plugintrack.manager.voice;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.gxc;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class BaseSoundEffectManager {

    /* renamed from: a, reason: collision with root package name */
    private final Context f3526a;
    public final Map<String, gxc> b = new HashMap();
    private SoundPool d;

    public BaseSoundEffectManager(Context context) {
        if (context == null) {
            this.f3526a = BaseApplication.e();
        } else {
            this.f3526a = context;
        }
        this.d = new SoundPool.Builder().setMaxStreams(10).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).setContentType(4).build()).build();
    }

    public boolean a(String str) {
        return this.b.containsKey(str);
    }

    public void a(List<gxc> list) {
        if (this.d == null) {
            LogUtil.b("Track_SoundEffectManager", "soundPool is null");
            return;
        }
        for (gxc gxcVar : list) {
            AssetFileDescriptor assetFileDescriptor = null;
            try {
                try {
                    assetFileDescriptor = BaseApplication.e().getAssets().openFd(gxcVar.b());
                    gxcVar.d(this.d.load(assetFileDescriptor, gxcVar.c()));
                    this.b.put(gxcVar.a(), gxcVar);
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (IOException unused) {
                            LogUtil.b("Track_SoundEffectManager", "close AssetFileDescriptor error");
                        }
                    }
                } catch (IOException e) {
                    LogUtil.b("Track_SoundEffectManager", "AssetFileDescriptor exception: ", LogAnonymous.b((Throwable) e));
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (IOException unused2) {
                            LogUtil.b("Track_SoundEffectManager", "close AssetFileDescriptor error");
                        }
                    }
                }
            } catch (Throwable th) {
                if (assetFileDescriptor != null) {
                    try {
                        assetFileDescriptor.close();
                    } catch (IOException unused3) {
                        LogUtil.b("Track_SoundEffectManager", "close AssetFileDescriptor error");
                    }
                }
                throw th;
            }
        }
    }

    public void aVj_(SoundPool.OnLoadCompleteListener onLoadCompleteListener) {
        SoundPool soundPool = this.d;
        if (soundPool == null) {
            LogUtil.b("Track_SoundEffectManager", "soundPool is null");
        } else {
            soundPool.setOnLoadCompleteListener(onLoadCompleteListener);
        }
    }

    public void b(String str) {
        a(str, 0, 1.0f);
    }

    public void a(String str, int i, float f) {
        if (this.d == null || !this.b.containsKey(str)) {
            LogUtil.b("Track_SoundEffectManager", "mSoundPool is null");
            return;
        }
        if (!(this.f3526a.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager)) {
            LogUtil.a("Track_SoundEffectManager", "playSound object invalid type");
            return;
        }
        gxc gxcVar = this.b.get(str);
        if (gxcVar == null) {
            LogUtil.b("Track_SoundEffectManager", "option is null");
        } else {
            this.d.play(gxcVar.d(), f, f, gxcVar.c(), i, 1.0f);
            this.d.stop(gxcVar.d());
        }
    }

    public int c(int i, float f, float f2, int i2, int i3, float f3) {
        SoundPool soundPool = this.d;
        if (soundPool == null) {
            LogUtil.b("Track_SoundEffectManager", "mSoundPool is null");
            return 0;
        }
        return soundPool.play(i, f, f2, i2, i3, f3);
    }

    public void d(int i) {
        SoundPool soundPool = this.d;
        if (soundPool == null) {
            LogUtil.b("Track_SoundEffectManager", "mSoundPool is null");
        } else {
            soundPool.stop(i);
        }
    }

    public void b(int i, float f, float f2) {
        SoundPool soundPool = this.d;
        if (soundPool == null) {
            LogUtil.b("Track_SoundEffectManager", "mSoundPool is null");
        } else {
            soundPool.setVolume(i, f, f2);
        }
    }

    public void d() {
        ReleaseLogUtil.e("Track_SoundEffectManager", "destroy");
        this.b.clear();
        SoundPool soundPool = this.d;
        if (soundPool != null) {
            soundPool.release();
        }
    }
}
