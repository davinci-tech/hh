package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes5.dex */
public abstract class RewardMediaView extends AutoScaleSizeRelativeLayout implements IViewLifeCycle, com.huawei.openalliance.ad.views.interfaces.r {

    /* renamed from: a, reason: collision with root package name */
    protected com.huawei.openalliance.ad.inter.data.h f8002a;
    protected String b;
    protected boolean c;
    protected boolean d;
    private VideoInfo e;
    private final Set<SegmentMediaStateListener> f;
    private int g;
    private long h;
    private long i;
    private long j;
    private boolean k;
    private boolean l;
    private long m;
    private Handler n;

    public void a(long j) {
    }

    public void a(String str) {
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public void setRewardAd(IRewardAd iRewardAd) {
        String str;
        f();
        if (iRewardAd instanceof com.huawei.openalliance.ad.inter.data.h) {
            com.huawei.openalliance.ad.inter.data.h hVar = (com.huawei.openalliance.ad.inter.data.h) iRewardAd;
            this.f8002a = hVar;
            this.e = hVar.I();
            this.m = r3.getVideoDuration();
            str = this.e.getVideoDownloadUrl();
        } else {
            this.f8002a = null;
            this.e = null;
            this.n.removeMessages(1);
            str = "";
        }
        this.b = str;
    }

    public IRewardAd getRewardAd() {
        return this.f8002a;
    }

    protected void e() {
        this.k = false;
        this.l = true;
        Iterator<SegmentMediaStateListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaError(this.f8002a.getContentId(), this.b, 0, -1, -1);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        this.n.removeMessages(1);
        this.f.clear();
    }

    public void a(boolean z, boolean z2) {
        if (this.c) {
            if (!this.d) {
                e();
                return;
            }
            this.n.removeMessages(1);
            this.n.sendEmptyMessage(1);
            g();
            if (0 == this.h) {
                this.h = ao.c();
            }
            if (this.i != 0) {
                this.j += ao.c() - this.i;
            }
        }
    }

    public void a(SegmentMediaStateListener segmentMediaStateListener) {
        if (segmentMediaStateListener != null) {
            this.f.add(segmentMediaStateListener);
        }
    }

    public void a() {
        this.n.removeMessages(1);
        this.i = ao.c();
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return ((long) this.g) >= this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.k = false;
        Iterator<SegmentMediaStateListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaCompletion(this.f8002a.getContentId(), this.b, this.g);
        }
    }

    private void i() {
        Iterator<SegmentMediaStateListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaPause(this.f8002a.getContentId(), this.b, this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.m <= 0 || this.l) {
            return;
        }
        for (SegmentMediaStateListener segmentMediaStateListener : this.f) {
            String contentId = this.f8002a.getContentId();
            String str = this.b;
            int i = this.g;
            segmentMediaStateListener.onSegmentProgress(contentId, str, (int) (i / this.m), i);
        }
    }

    private void g() {
        if (this.k) {
            return;
        }
        this.k = true;
        Iterator<SegmentMediaStateListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaStart(this.f8002a.getContentId(), this.b, this.g);
        }
    }

    private void f() {
        this.m = 0L;
        this.g = 0;
        this.h = 0L;
        this.i = 0L;
        this.j = 0L;
        this.k = false;
        this.l = false;
        this.d = false;
        this.c = false;
    }

    public RewardMediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f = new CopyOnWriteArraySet();
        this.g = 0;
        this.h = 0L;
        this.i = 0L;
        this.k = false;
        this.l = false;
        this.c = false;
        this.d = false;
        this.m = 0L;
        this.n = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.RewardMediaView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str;
                try {
                    if (1 == message.what) {
                        RewardMediaView.this.g = (int) ((ao.c() - RewardMediaView.this.h) - RewardMediaView.this.j);
                        if (!RewardMediaView.this.k()) {
                            RewardMediaView.this.h();
                            RewardMediaView.this.n.removeMessages(1);
                            RewardMediaView.this.n.sendEmptyMessageDelayed(1, 100L);
                        } else {
                            RewardMediaView.this.j();
                        }
                    }
                } catch (IllegalStateException unused) {
                    str = "handleMessage IllegalStateException";
                    ho.c("RewardMediaView", str);
                } catch (Throwable th) {
                    str = "handleMessage " + th.getClass().getSimpleName();
                    ho.c("RewardMediaView", str);
                }
            }
        };
    }

    public RewardMediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new CopyOnWriteArraySet();
        this.g = 0;
        this.h = 0L;
        this.i = 0L;
        this.k = false;
        this.l = false;
        this.c = false;
        this.d = false;
        this.m = 0L;
        this.n = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.RewardMediaView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str;
                try {
                    if (1 == message.what) {
                        RewardMediaView.this.g = (int) ((ao.c() - RewardMediaView.this.h) - RewardMediaView.this.j);
                        if (!RewardMediaView.this.k()) {
                            RewardMediaView.this.h();
                            RewardMediaView.this.n.removeMessages(1);
                            RewardMediaView.this.n.sendEmptyMessageDelayed(1, 100L);
                        } else {
                            RewardMediaView.this.j();
                        }
                    }
                } catch (IllegalStateException unused) {
                    str = "handleMessage IllegalStateException";
                    ho.c("RewardMediaView", str);
                } catch (Throwable th) {
                    str = "handleMessage " + th.getClass().getSimpleName();
                    ho.c("RewardMediaView", str);
                }
            }
        };
    }

    public RewardMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new CopyOnWriteArraySet();
        this.g = 0;
        this.h = 0L;
        this.i = 0L;
        this.k = false;
        this.l = false;
        this.c = false;
        this.d = false;
        this.m = 0L;
        this.n = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.RewardMediaView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str;
                try {
                    if (1 == message.what) {
                        RewardMediaView.this.g = (int) ((ao.c() - RewardMediaView.this.h) - RewardMediaView.this.j);
                        if (!RewardMediaView.this.k()) {
                            RewardMediaView.this.h();
                            RewardMediaView.this.n.removeMessages(1);
                            RewardMediaView.this.n.sendEmptyMessageDelayed(1, 100L);
                        } else {
                            RewardMediaView.this.j();
                        }
                    }
                } catch (IllegalStateException unused) {
                    str = "handleMessage IllegalStateException";
                    ho.c("RewardMediaView", str);
                } catch (Throwable th) {
                    str = "handleMessage " + th.getClass().getSimpleName();
                    ho.c("RewardMediaView", str);
                }
            }
        };
    }

    public RewardMediaView(Context context) {
        super(context);
        this.f = new CopyOnWriteArraySet();
        this.g = 0;
        this.h = 0L;
        this.i = 0L;
        this.k = false;
        this.l = false;
        this.c = false;
        this.d = false;
        this.m = 0L;
        this.n = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.RewardMediaView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str;
                try {
                    if (1 == message.what) {
                        RewardMediaView.this.g = (int) ((ao.c() - RewardMediaView.this.h) - RewardMediaView.this.j);
                        if (!RewardMediaView.this.k()) {
                            RewardMediaView.this.h();
                            RewardMediaView.this.n.removeMessages(1);
                            RewardMediaView.this.n.sendEmptyMessageDelayed(1, 100L);
                        } else {
                            RewardMediaView.this.j();
                        }
                    }
                } catch (IllegalStateException unused) {
                    str = "handleMessage IllegalStateException";
                    ho.c("RewardMediaView", str);
                } catch (Throwable th) {
                    str = "handleMessage " + th.getClass().getSimpleName();
                    ho.c("RewardMediaView", str);
                }
            }
        };
    }
}
