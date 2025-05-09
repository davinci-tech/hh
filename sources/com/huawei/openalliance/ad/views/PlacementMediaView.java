package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaPlayerReleaseListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener;
import com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes5.dex */
public abstract class PlacementMediaView extends AutoScaleSizeRelativeLayout implements lm, IViewLifeCycle, com.huawei.openalliance.ad.views.interfaces.p {

    /* renamed from: a, reason: collision with root package name */
    protected com.huawei.openalliance.ad.inter.data.g f7996a;
    protected String b;
    protected String c;
    protected boolean d;
    protected boolean e;
    protected boolean f;
    private PlacementMediaFile g;
    private final Set<SegmentMediaStateListener> h;
    private int i;
    private long j;
    private long k;
    private long l;
    private long m;
    private boolean n;
    private boolean o;
    private Handler p;

    abstract void a();

    abstract void a(int i);

    public void a(long j) {
    }

    public void a(MediaBufferListener mediaBufferListener) {
    }

    public void a(MediaErrorListener mediaErrorListener) {
    }

    abstract void a(MediaPlayerReleaseListener mediaPlayerReleaseListener);

    public void a(MuteListener muteListener) {
    }

    public void a(PPSVideoRenderListener pPSVideoRenderListener) {
    }

    public void a(SegmentMediaStateListener segmentMediaStateListener) {
    }

    public void a(String str) {
    }

    abstract void b();

    public void b(int i) {
    }

    public void b(long j) {
    }

    public void b(MediaBufferListener mediaBufferListener) {
    }

    public void b(MediaErrorListener mediaErrorListener) {
    }

    public void b(MuteListener muteListener) {
    }

    public void c() {
    }

    public void c(int i) {
    }

    public void d() {
    }

    protected void d(int i) {
    }

    public void e() {
    }

    public void g() {
    }

    abstract ImageView getLastFrame();

    abstract MediaState getMediaState();

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    public boolean i() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
    }

    public void setAudioFocusType(int i) {
    }

    abstract void setMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener);

    public void setSoundVolume(float f) {
    }

    public void setPlacementAd(IPlacementAd iPlacementAd) {
        String str;
        j();
        if (iPlacementAd instanceof com.huawei.openalliance.ad.inter.data.g) {
            com.huawei.openalliance.ad.inter.data.g gVar = (com.huawei.openalliance.ad.inter.data.g) iPlacementAd;
            this.f7996a = gVar;
            PlacementMediaFile mediaFile = gVar.getMediaFile();
            this.g = mediaFile;
            this.k = mediaFile.getDuration();
            this.b = this.g.getUrl();
            str = iPlacementAd.getContentId();
        } else {
            this.f7996a = null;
            this.g = null;
            this.p.removeMessages(1);
            str = "";
            this.b = "";
        }
        this.c = str;
    }

    protected void h() {
        this.n = false;
        this.o = true;
        Iterator<SegmentMediaStateListener> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaError(this.c, this.b, 0, -1, -1);
        }
    }

    public IPlacementAd getPlacementAd() {
        return this.f7996a;
    }

    public long getDuration() {
        PlacementMediaFile mediaFile;
        com.huawei.openalliance.ad.inter.data.g gVar = this.f7996a;
        if (gVar == null || (mediaFile = gVar.getMediaFile()) == null) {
            return 0L;
        }
        return mediaFile.getDuration();
    }

    public void f() {
        this.p.removeMessages(1);
        this.l = ao.c();
        m();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        this.p.removeMessages(1);
        this.h.clear();
        a();
    }

    public void b(SegmentMediaStateListener segmentMediaStateListener) {
        if (segmentMediaStateListener != null) {
            this.h.add(segmentMediaStateListener);
        }
    }

    public void a(boolean z, boolean z2) {
        ho.b("PlacementMediaView", "play, mediaCached: %s, mediaAvalible: %s", Boolean.valueOf(this.d), Boolean.valueOf(this.e));
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PlacementMediaView.2
            @Override // java.lang.Runnable
            public void run() {
                if (!PlacementMediaView.this.d) {
                    PlacementMediaView.this.f = true;
                    return;
                }
                if (!PlacementMediaView.this.e) {
                    PlacementMediaView.this.h();
                    return;
                }
                PlacementMediaView.this.p.removeMessages(1);
                PlacementMediaView.this.p.sendEmptyMessage(1);
                PlacementMediaView.this.k();
                if (0 == PlacementMediaView.this.j) {
                    PlacementMediaView.this.j = ao.c();
                }
                if (PlacementMediaView.this.l != 0) {
                    PlacementMediaView.this.m += ao.c() - PlacementMediaView.this.l;
                }
            }
        }, 1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean o() {
        return ((long) this.i) >= this.k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.n = false;
        Iterator<SegmentMediaStateListener> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaCompletion(this.c, this.b, this.i);
        }
    }

    private void m() {
        Iterator<SegmentMediaStateListener> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaPause(this.c, this.b, this.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.k <= 0 || this.o) {
            return;
        }
        for (SegmentMediaStateListener segmentMediaStateListener : this.h) {
            String str = this.c;
            String str2 = this.b;
            int i = this.i;
            segmentMediaStateListener.onSegmentProgress(str, str2, (int) (i / this.k), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.n) {
            return;
        }
        this.n = true;
        Iterator<SegmentMediaStateListener> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaStart(this.c, this.b, this.i);
        }
    }

    private void j() {
        this.i = 0;
        this.j = 0L;
        this.l = 0L;
        this.k = 0L;
        this.m = 0L;
        this.n = false;
        this.o = false;
        this.e = false;
        this.d = false;
        this.f = false;
    }

    public PlacementMediaView(Context context) {
        super(context);
        this.h = new CopyOnWriteArraySet();
        this.i = 0;
        this.j = 0L;
        this.k = 0L;
        this.l = 0L;
        this.n = false;
        this.o = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.p = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.PlacementMediaView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str;
                try {
                    if (1 == message.what) {
                        PlacementMediaView.this.i = (int) ((ao.c() - PlacementMediaView.this.j) - PlacementMediaView.this.m);
                        if (!PlacementMediaView.this.o()) {
                            PlacementMediaView.this.l();
                            PlacementMediaView.this.p.removeMessages(1);
                            PlacementMediaView.this.p.sendEmptyMessageDelayed(1, 100L);
                        } else {
                            PlacementMediaView.this.n();
                        }
                    }
                } catch (IllegalStateException unused) {
                    str = "handleMessage IllegalStateException";
                    ho.c("PlacementMediaView", str);
                } catch (Throwable th) {
                    str = "handleMessage " + th.getClass().getSimpleName();
                    ho.c("PlacementMediaView", str);
                }
            }
        };
    }
}
