package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ClickEventRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
class qd extends qc {

    /* renamed from: a, reason: collision with root package name */
    private static final Executor f7470a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new com.huawei.openalliance.ad.utils.n("Click-Event"));
    private long b;

    @Override // com.huawei.openalliance.ad.qc
    protected long d() {
        long j;
        synchronized (qd.class) {
            j = this.b;
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.qc
    protected String c() {
        return "ClickEventReporter";
    }

    @Override // com.huawei.openalliance.ad.qc
    protected Executor b() {
        return f7470a;
    }

    @Override // com.huawei.openalliance.ad.qc
    protected void a(long j) {
        synchronized (qd.class) {
            this.b = j;
        }
    }

    @Override // com.huawei.openalliance.ad.qc
    protected Class<? extends EventRecord> a() {
        return ClickEventRecord.class;
    }

    qd(Context context, sl slVar) {
        super(context, slVar);
    }
}
