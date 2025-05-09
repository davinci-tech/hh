package com.huawei.hianalytics.core.transport;

import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.f;
import com.huawei.hianalytics.core.g;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.net.TransportHandler;
import com.huawei.hms.network.NetworkKit;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class TransportHandlerFactory {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f3843a = false;

    public static TransportHandler create(String str, Map<String, String> map, byte[] bArr, int i, boolean z) {
        try {
            Class.forName("com.huawei.hms.network.NetworkKit");
            a();
            return new g(str, map, bArr, i, z);
        } catch (Exception unused) {
            HiLog.w("TransportHandler", "visit NetworkKit Exception");
            return new f(str, map, bArr);
        }
    }

    public class a extends NetworkKit.Callback {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f3844a;

        @Override // com.huawei.hms.network.NetworkKit.Callback
        public void onResult(boolean z) {
            this.f3844a.countDown();
        }

        public a(CountDownLatch countDownLatch) {
            this.f3844a = countDownLatch;
        }
    }

    public static TransportHandler create(String str, Map<String, String> map, byte[] bArr, int i) {
        return create(str, map, bArr, i, false);
    }

    public static void a() {
        if (f3843a) {
            return;
        }
        f3843a = true;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        NetworkKit.init(EnvUtils.getAppContext(), new a(countDownLatch));
        try {
            HiLog.i("TransportHandler", "initNetworkKitSync await " + countDownLatch.await(3000L, TimeUnit.MILLISECONDS));
        } catch (InterruptedException unused) {
            HiLog.w("TransportHandler", "InterruptedException");
        }
    }
}
