package defpackage;

import com.huawei.wear.oversea.overseamanger.QueryReviewResultCallback;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class svq {
    private ScheduledFuture<?> b;

    /* renamed from: a, reason: collision with root package name */
    private int f17253a = 0;
    private boolean d = false;

    public svq(final QueryReviewResultCallback queryReviewResultCallback) {
        stq.b("QueryReviewResultScheduleTask", "QueryReviewResultScheduleTask");
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.b = newSingleThreadScheduledExecutor.scheduleAtFixedRate(new Runnable() { // from class: svr
            @Override // java.lang.Runnable
            public final void run() {
                svq.this.d(queryReviewResultCallback, countDownLatch);
            }
        }, 2000L, 2000L, TimeUnit.MILLISECONDS);
        try {
            boolean await = countDownLatch.await(62000L, TimeUnit.MILLISECONDS);
            if (!await) {
                d(queryReviewResultCallback, "1", this.b);
            }
            stq.c("QueryReviewResultScheduleTask", "awaitResult=" + await, true);
        } catch (InterruptedException unused) {
            stq.b("QueryReviewResultScheduleTask", "InterruptedException");
        }
    }

    /* synthetic */ void d(QueryReviewResultCallback queryReviewResultCallback, CountDownLatch countDownLatch) {
        this.f17253a++;
        stq.b("QueryReviewResultScheduleTask", "retryTimes:" + this.f17253a);
        if (queryReviewResultCallback.checkReviewResult()) {
            d(queryReviewResultCallback, "2", this.b);
        } else {
            if (this.f17253a >= 30) {
                stq.b("QueryReviewResultScheduleTask", "max times");
                countDownLatch.countDown();
                d(queryReviewResultCallback, "1", this.b);
                return;
            }
            stq.b("QueryReviewResultScheduleTask", "checkReviewResult again");
        }
    }

    private void d(QueryReviewResultCallback queryReviewResultCallback, String str, ScheduledFuture<?> scheduledFuture) {
        synchronized (this) {
            if (!this.d) {
                this.d = true;
                scheduledFuture.cancel(true);
                queryReviewResultCallback.onQueryReviewResultCallback(str);
            }
        }
    }
}
