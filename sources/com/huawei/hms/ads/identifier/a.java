package com.huawei.hms.ads.identifier;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.huawei.common.Constant;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class a implements ServiceConnection {

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadPoolExecutor f4315a = new ThreadPoolExecutor(0, 3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(2048), new ThreadPoolExecutor.DiscardPolicy());
    boolean b = false;
    private final LinkedBlockingQueue<IBinder> c = new LinkedBlockingQueue<>(1);

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d("PPSSerivceConnection", "onServiceDisconnected " + System.currentTimeMillis());
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        Log.d("PPSSerivceConnection", Constant.SERVICE_CONNECT_MESSAGE);
        f4315a.execute(new Runnable() { // from class: com.huawei.hms.ads.identifier.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Log.d("PPSSerivceConnection", "onServiceConnected " + System.currentTimeMillis());
                    a.this.c.offer(iBinder);
                } catch (Throwable th) {
                    Log.w("PPSSerivceConnection", "onServiceConnected  " + th.getClass().getSimpleName());
                }
            }
        });
    }

    public IBinder a() throws InterruptedException {
        if (this.b) {
            throw new IllegalStateException();
        }
        this.b = true;
        return this.c.take();
    }
}
