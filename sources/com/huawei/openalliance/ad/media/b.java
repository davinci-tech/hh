package com.huawei.openalliance.ad.media;

import android.content.Context;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.utils.bo;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private final Set<ReportVideoTimeListener> f7255a = new CopyOnWriteArraySet();
    private final String b = Constants.TIMEOUT_TASK_ID + hashCode();
    private long c;
    private long d;
    private long e;
    private long f;
    private boolean g;
    private boolean h;
    private int i;
    private Context j;

    public void b(int i) {
        c(i);
        long ak = fh.b(this.j).ak();
        if (ho.a()) {
            ho.a("VideoPlayTimeProcessor", "notifyVideoTimeWithVideoPause: %s", Long.valueOf(ak));
        }
        bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (ho.a()) {
                    ho.a("VideoPlayTimeProcessor", "notifyVideoTimeWithVideoPause: videoStartTime %s , videoPauseTime %s", Long.valueOf(b.this.e), Long.valueOf(b.this.f));
                }
                if (b.this.g) {
                    return;
                }
                if (b.this.e == 0) {
                    b bVar = b.this;
                    bVar.b(bVar.c);
                    return;
                }
                long j = b.this.f - b.this.e;
                b bVar2 = b.this;
                if (j > bVar2.i || j < 0) {
                    j = b.this.i;
                }
                bVar2.b(j);
                b.this.g = true;
            }
        }, this.b, ak);
    }

    public void a(ReportVideoTimeListener reportVideoTimeListener) {
        if (reportVideoTimeListener == null) {
            return;
        }
        this.f7255a.add(reportVideoTimeListener);
    }

    public void a(long j) {
        if (this.g || this.h) {
            this.g = false;
            this.h = true;
            return;
        }
        long j2 = this.c;
        if (j2 != 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.c;
            if (ho.a()) {
                ho.a("VideoPlayTimeProcessor", "notifyVideoTimeWithVideoEnd: videoTime %s  ,playTime %s", Long.valueOf(currentTimeMillis), Long.valueOf(j));
            }
            if (currentTimeMillis <= j && currentTimeMillis >= 0) {
                j = currentTimeMillis;
            }
            b(j);
            this.c = 0L;
        } else {
            b(j2);
        }
        if (this.d != 0) {
            this.d = 0L;
        }
        this.h = true;
    }

    public void a(int i) {
        if (ho.a()) {
            ho.a("VideoPlayTimeProcessor", "notifyVideoTimeWithVideoStop");
        }
        a(i);
        bo.a(this.b);
        this.c = 0L;
    }

    public void a() {
        if (ho.a()) {
            ho.a("VideoPlayTimeProcessor", "notifyVideoTimeWithVideoStart: videoStartTime %s ", Long.valueOf(this.c));
        }
        if (this.c == 0) {
            this.c = System.currentTimeMillis();
        } else {
            bo.a(this.b);
        }
        if (this.d != 0) {
            this.d = 0L;
        }
        this.h = false;
    }

    private void c(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        this.d = currentTimeMillis;
        this.f = currentTimeMillis;
        this.i = i;
        this.e = this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        ho.b("VideoPlayTimeProcessor", "notifyVideoTime: videoTime: %s ", Long.valueOf(j));
        Iterator<ReportVideoTimeListener> it = this.f7255a.iterator();
        while (it.hasNext()) {
            it.next().reportVideoTime(j);
        }
    }

    public b(Context context) {
        this.j = context;
    }
}
