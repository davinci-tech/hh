package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;

/* loaded from: classes5.dex */
public class bs extends MediaPlayerAgent implements MediaErrorListener, MediaStateListener {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.hms.ads.uiengine.a f6651a;
    private MediaStateListener b;
    private MediaErrorListener c;

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void stopWhenUrlMatchs(String str) {
        try {
            this.f6651a.b(str);
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "stopWhenUrlMatchs err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void stop() {
        try {
            this.f6651a.b();
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "stop err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void removeMediaStateListener(MediaStateListener mediaStateListener) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBinder("listener", (IBinder) ObjectWrapper.wrap(this));
            this.f6651a.c(bundle);
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "removeMediaErrorListener err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void removeMediaErrorListener(MediaErrorListener mediaErrorListener) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBinder("listener", (IBinder) ObjectWrapper.wrap(this));
            this.f6651a.d(bundle);
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "removeMediaErrorListener err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void playWhenUrlMatchs(String str) {
        try {
            this.f6651a.a(str);
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "playWhenUrlMatchs err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void pauseWhenUrlMatchs(String str) {
        try {
            this.f6651a.c(str);
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "pauseWhenUrlMatchs er: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onProgress(int i, int i2) {
        MediaStateListener mediaStateListener = this.b;
        if (mediaStateListener != null) {
            mediaStateListener.onProgress(i, i2);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
        MediaStateListener mediaStateListener = this.b;
        if (mediaStateListener != null) {
            mediaStateListener.onMediaStop(this, i);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
        MediaStateListener mediaStateListener = this.b;
        if (mediaStateListener != null) {
            mediaStateListener.onMediaStart(this, i);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
        MediaStateListener mediaStateListener = this.b;
        if (mediaStateListener != null) {
            mediaStateListener.onMediaPause(this, i);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
    public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
        MediaStateListener mediaStateListener = this.b;
        if (mediaStateListener != null) {
            mediaStateListener.onMediaCompletion(this, i);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
    public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
        MediaErrorListener mediaErrorListener = this.c;
        if (mediaErrorListener != null) {
            mediaErrorListener.onError(this, i, i2, i3);
        }
    }

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void addMediaStateListener(MediaStateListener mediaStateListener) {
        this.b = mediaStateListener;
        try {
            Bundle bundle = new Bundle();
            bundle.putBinder("listener", (IBinder) ObjectWrapper.wrap(this));
            this.f6651a.a(bundle);
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "addMediaStateListener err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.media.MediaPlayerAgent
    public void addMediaErrorListener(MediaErrorListener mediaErrorListener) {
        this.c = mediaErrorListener;
        try {
            Bundle bundle = new Bundle();
            bundle.putBinder("listener", (IBinder) ObjectWrapper.wrap(this));
            this.f6651a.b(bundle);
        } catch (Throwable th) {
            ho.b("MediaPlayerAgentProxy", "addMediaErrorListener err: %s", th.getClass().getSimpleName());
        }
    }

    public bs(Context context, com.huawei.hms.ads.uiengine.a aVar) {
        super(context);
        this.f6651a = aVar;
    }
}
