package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
class qh extends qc {

    /* renamed from: a, reason: collision with root package name */
    private static final Executor f7472a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new com.huawei.openalliance.ad.utils.n("Normal-Event"));
    private long b;

    @Override // com.huawei.openalliance.ad.qc
    protected long d() {
        long j;
        synchronized (qh.class) {
            j = this.b;
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.qc
    protected String c() {
        return "NormalEventReporter";
    }

    @Override // com.huawei.openalliance.ad.qc
    protected Executor b() {
        return f7472a;
    }

    @Override // com.huawei.openalliance.ad.qc
    protected void a(long j) {
        synchronized (qh.class) {
            this.b = j;
        }
    }

    @Override // com.huawei.openalliance.ad.qc
    protected Class<? extends EventRecord> a() {
        return EventRecord.class;
    }

    qh(Context context, sl slVar) {
        super(context, slVar);
    }
}
