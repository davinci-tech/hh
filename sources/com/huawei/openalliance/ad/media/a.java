package com.huawei.openalliance.ad.media;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.dl;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes5.dex */
public class a implements IMultiMediaPlayingManager {

    /* renamed from: a, reason: collision with root package name */
    private static a f7251a;
    private static final byte[] b = new byte[0];
    private MediaPlayerAgent d;
    private Context f;
    private final byte[] c = new byte[0];
    private Queue<C0199a> e = new ConcurrentLinkedQueue();
    private MediaStateListener g = new MediaStateListener() { // from class: com.huawei.openalliance.ad.media.a.1
        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onProgress(int i, int i2) {
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "onMediaStop: %s", mediaPlayerAgent);
            }
            a();
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "onMediaPause: %s", mediaPlayerAgent);
            }
            a();
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "onMediaCompletion: %s", mediaPlayerAgent);
            }
            a.this.a();
        }

        private void a() {
            synchronized (a.this.c) {
                if (ho.a()) {
                    ho.a("MultiMediaPlayingManager", "checkAndPlayNext current player: %s", a.this.d);
                }
                if (a.this.d == null) {
                    a.this.a();
                }
            }
        }
    };
    private MediaErrorListener h = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.media.a.2
        @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
        public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "onError: %s", mediaPlayerAgent);
            }
            synchronized (a.this.c) {
                mediaPlayerAgent.removeMediaErrorListener(this);
            }
            a.this.a();
        }
    };

    @Override // com.huawei.openalliance.ad.media.IMultiMediaPlayingManager
    public void stop(String str, MediaPlayerAgent mediaPlayerAgent) {
        if (TextUtils.isEmpty(str) || mediaPlayerAgent == null) {
            return;
        }
        synchronized (this.c) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "stop - url: %s player: %s  currentPlayer: %s", dl.a(str), mediaPlayerAgent, this.d);
            }
            if (mediaPlayerAgent == this.d) {
                ho.b("MultiMediaPlayingManager", "stop current");
                this.d = null;
                mediaPlayerAgent.stopWhenUrlMatchs(str);
            } else {
                ho.b("MultiMediaPlayingManager", "stop - remove from queue");
                this.e.remove(new C0199a(str, mediaPlayerAgent));
                removeListenersForMediaPlayerAgent(mediaPlayerAgent);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.media.IMultiMediaPlayingManager
    public void removeMediaPlayerAgent(MediaPlayerAgent mediaPlayerAgent) {
        if (mediaPlayerAgent == null) {
            return;
        }
        synchronized (this.c) {
            MediaPlayerAgent mediaPlayerAgent2 = this.d;
            if (mediaPlayerAgent == mediaPlayerAgent2) {
                removeListenersForMediaPlayerAgent(mediaPlayerAgent2);
                this.d = null;
            }
            Iterator<C0199a> it = this.e.iterator();
            while (it.hasNext()) {
                C0199a next = it.next();
                if (next.b == mediaPlayerAgent) {
                    removeListenersForMediaPlayerAgent(next.b);
                    it.remove();
                }
            }
        }
    }

    @Override // com.huawei.openalliance.ad.media.IMultiMediaPlayingManager
    public void removeListenersForMediaPlayerAgent(MediaPlayerAgent mediaPlayerAgent) {
        synchronized (this.c) {
            if (mediaPlayerAgent != null) {
                mediaPlayerAgent.removeMediaStateListener(this.g);
                mediaPlayerAgent.removeMediaErrorListener(this.h);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.media.IMultiMediaPlayingManager
    public void pause(String str, MediaPlayerAgent mediaPlayerAgent) {
        if (TextUtils.isEmpty(str) || mediaPlayerAgent == null) {
            return;
        }
        synchronized (this.c) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "pause - url: %s player: %s  currentPlayer: %s", dl.a(str), mediaPlayerAgent, this.d);
            }
            if (mediaPlayerAgent == this.d) {
                ho.b("MultiMediaPlayingManager", "pause current");
                mediaPlayerAgent.pauseWhenUrlMatchs(str);
            } else {
                ho.b("MultiMediaPlayingManager", "pause - remove from queue");
                this.e.remove(new C0199a(str, mediaPlayerAgent));
                removeListenersForMediaPlayerAgent(mediaPlayerAgent);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.media.IMultiMediaPlayingManager
    public void manualPlay(String str, MediaPlayerAgent mediaPlayerAgent) {
        if (TextUtils.isEmpty(str) || mediaPlayerAgent == null) {
            return;
        }
        synchronized (this.c) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "manualPlay - url: %s player: %s  currentPlayer: %s", dl.a(str), mediaPlayerAgent, this.d);
            }
            MediaPlayerAgent mediaPlayerAgent2 = this.d;
            if (mediaPlayerAgent2 != null && mediaPlayerAgent != mediaPlayerAgent2) {
                mediaPlayerAgent2.stop();
                ho.b("MultiMediaPlayingManager", "manualPlay - stop other");
            }
            ho.b("MultiMediaPlayingManager", "manualPlay - play new");
            mediaPlayerAgent.addMediaStateListener(this.g);
            mediaPlayerAgent.addMediaErrorListener(this.h);
            mediaPlayerAgent.playWhenUrlMatchs(str);
            this.d = mediaPlayerAgent;
            this.e.remove(new C0199a(str, mediaPlayerAgent));
        }
    }

    @Override // com.huawei.openalliance.ad.media.IMultiMediaPlayingManager
    public void autoPlay(String str, MediaPlayerAgent mediaPlayerAgent) {
        String str2;
        if (TextUtils.isEmpty(str) || mediaPlayerAgent == null) {
            return;
        }
        synchronized (this.c) {
            if (ho.a()) {
                ho.a("MultiMediaPlayingManager", "autoPlay - url: %s player: %s currentPlayer: %s", dl.a(str), mediaPlayerAgent, this.d);
            }
            MediaPlayerAgent mediaPlayerAgent2 = this.d;
            if (mediaPlayerAgent != mediaPlayerAgent2 && mediaPlayerAgent2 != null) {
                C0199a c0199a = new C0199a(str, mediaPlayerAgent);
                this.e.remove(c0199a);
                this.e.add(c0199a);
                str2 = "autoPlay - add to queue";
                ho.b("MultiMediaPlayingManager", str2);
            }
            mediaPlayerAgent.addMediaStateListener(this.g);
            mediaPlayerAgent.addMediaErrorListener(this.h);
            mediaPlayerAgent.playWhenUrlMatchs(str);
            this.d = mediaPlayerAgent;
            str2 = "autoPlay - play directly";
            ho.b("MultiMediaPlayingManager", str2);
        }
    }

    /* renamed from: com.huawei.openalliance.ad.media.a$a, reason: collision with other inner class name */
    static class C0199a {

        /* renamed from: a, reason: collision with root package name */
        final String f7254a;
        final MediaPlayerAgent b;

        public String toString() {
            return "Task [" + dl.a(this.f7254a) + "]";
        }

        public int hashCode() {
            String str = this.f7254a;
            int hashCode = str != null ? str.hashCode() : -1;
            MediaPlayerAgent mediaPlayerAgent = this.b;
            return hashCode & super.hashCode() & (mediaPlayerAgent != null ? mediaPlayerAgent.hashCode() : -1);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof C0199a)) {
                if (this == obj) {
                    return true;
                }
                C0199a c0199a = (C0199a) obj;
                if (TextUtils.equals(this.f7254a, c0199a.f7254a) && this.b == c0199a.b) {
                    return true;
                }
            }
            return false;
        }

        C0199a(String str, MediaPlayerAgent mediaPlayerAgent) {
            this.f7254a = str;
            this.b = mediaPlayerAgent;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (bv.c(this.f)) {
            synchronized (this.c) {
                C0199a poll = this.e.poll();
                if (ho.a()) {
                    ho.a("MultiMediaPlayingManager", "playNextTask - task: %s currentPlayer: %s", poll, this.d);
                }
                if (poll != null) {
                    if (ho.a()) {
                        ho.a("MultiMediaPlayingManager", "playNextTask - play: %s", poll.b);
                    }
                    poll.b.addMediaStateListener(this.g);
                    poll.b.addMediaErrorListener(this.h);
                    poll.b.playWhenUrlMatchs(poll.f7254a);
                    this.d = poll.b;
                } else {
                    this.d = null;
                }
            }
        }
    }

    public static a a(Context context) {
        a aVar;
        synchronized (b) {
            if (f7251a == null) {
                f7251a = new a(context);
            }
            aVar = f7251a;
        }
        return aVar;
    }

    private a(Context context) {
        this.f = context.getApplicationContext();
    }
}
