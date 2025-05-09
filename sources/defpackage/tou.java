package defpackage;

import com.huawei.wearengine.core.common.OnFinishedCallback;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class tou implements OnFinishedCallback {
    private final CountDownLatch c;

    public tou(int i) {
        this.c = new CountDownLatch(i);
    }

    public boolean e(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.c.await(j, timeUnit);
    }

    @Override // com.huawei.wearengine.core.common.OnFinishedCallback
    public void onFinish() {
        this.c.countDown();
    }
}
