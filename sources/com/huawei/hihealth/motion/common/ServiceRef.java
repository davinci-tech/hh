package com.huawei.hihealth.motion.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class ServiceRef<T> {
    private static final int RETRY_BIND_DALAY = 5000;
    private static final int RETRY_BIND_TIMES = 5;
    private static final int TIME_MAX_WAIT = 5000;
    private static final ScheduledExecutorService sExecutor = Executors.newScheduledThreadPool(1);
    private String TAG;
    private ServiceConnection mConn;
    private Context mContext;
    private CountDownLatch mCountDownLatch;
    private boolean mNeedCallOnRebind;
    private Runnable mRetryBindAction;
    private int mRetryCount;
    private T mSelf;
    private Intent mServiceIntent;
    private boolean mStartBeforeBind;

    public abstract void onRebind();

    public abstract void onServiceException();

    public abstract void onServiceFailed();

    public abstract void onServiceReady();

    public abstract T transf(IBinder iBinder);

    static /* synthetic */ int access$108(ServiceRef serviceRef) {
        int i = serviceRef.mRetryCount;
        serviceRef.mRetryCount = i + 1;
        return i;
    }

    public ServiceRef(Context context, Intent intent) {
        this(context, intent, false);
    }

    public ServiceRef(Context context, Intent intent, boolean z) {
        this.TAG = "";
        this.mContext = null;
        this.mServiceIntent = null;
        this.mSelf = null;
        this.mConn = new MyConn();
        this.mRetryCount = 0;
        this.mStartBeforeBind = false;
        this.mNeedCallOnRebind = true;
        this.mRetryBindAction = new Runnable() { // from class: com.huawei.hihealth.motion.common.ServiceRef.1
            @Override // java.lang.Runnable
            public void run() {
                ServiceRef.access$108(ServiceRef.this);
                Log.i(ServiceRef.this.TAG, "rebind times = " + ServiceRef.this.mRetryCount);
                if (ServiceRef.this.mRetryCount > 5) {
                    ServiceRef.this.mRetryCount = 0;
                    Log.w(ServiceRef.this.TAG, "retyr 3 times, stop retry");
                    return;
                }
                ServiceRef.this.mCountDownLatch = new CountDownLatch(1);
                boolean bindService = ServiceRef.this.bindService();
                if (bindService && (bindService = ServiceRef.this.waitForReady()) && ServiceRef.this.mNeedCallOnRebind) {
                    ServiceRef.this.onRebind();
                    ServiceRef.this.mNeedCallOnRebind = false;
                }
                if (bindService) {
                    return;
                }
                ServiceRef.sExecutor.schedule(this, ServiceRef.this.mRetryCount * 5000, TimeUnit.MILLISECONDS);
            }
        };
        this.mStartBeforeBind = z;
        this.mContext = context;
        this.mServiceIntent = intent;
        this.TAG = "ServiceRef_" + intent.getComponent().getClassName();
        bindService();
    }

    public T get() {
        Log.d(this.TAG, "get mSelf = " + this.mSelf);
        if (this.mSelf == null) {
            Log.w(this.TAG, "get mSelf null");
        }
        return this.mSelf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean waitForReady() {
        if (this.mSelf != null) {
            Log.i(this.TAG, "waitForReady: null != mSelf");
            return true;
        }
        boolean z = false;
        try {
            z = this.mCountDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            if (!z) {
                Log.w(this.TAG, "rebind " + this.mServiceIntent.getComponent() + " time out");
            }
        } catch (InterruptedException unused) {
            Log.e(this.TAG, "ServiceRef_ InterruptedException");
        }
        this.mCountDownLatch = null;
        Log.i(this.TAG, "waitForReady success = " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bindService() {
        Log.i(this.TAG, "bindService mSelf = " + this.mSelf);
        if (this.mSelf != null) {
            return true;
        }
        if (this.mStartBeforeBind) {
            Log.i(this.TAG, "start service!!!");
            this.mContext.startService(this.mServiceIntent);
        }
        boolean bindService = this.mContext.bindService(this.mServiceIntent, this.mConn, 1);
        Log.i(this.TAG, "bindService ret = " + bindService);
        if (bindService) {
            return bindService;
        }
        Log.w(this.TAG, "bind ret false");
        onServiceFailed();
        return false;
    }

    private void unbindService() {
        if (this.mSelf == null) {
            return;
        }
        this.mContext.unbindService(this.mConn);
        this.mSelf = null;
    }

    class MyConn implements ServiceConnection {
        private MyConn() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(ServiceRef.this.TAG, "onServiceConnected " + componentName);
            ServiceRef serviceRef = ServiceRef.this;
            serviceRef.mSelf = serviceRef.transf(iBinder);
            synchronized (MyConn.class) {
                if (ServiceRef.this.mCountDownLatch != null) {
                    ServiceRef.this.mCountDownLatch.countDown();
                }
            }
            ServiceRef.this.onServiceReady();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.w(ServiceRef.this.TAG, "onServiceDisconnected " + componentName);
            ServiceRef.this.mSelf = null;
            ServiceRef.this.onServiceException();
            ServiceRef.this.mNeedCallOnRebind = true;
            ServiceRef.this.mRetryCount = 0;
            ServiceRef.sExecutor.schedule(ServiceRef.this.mRetryBindAction, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    public void release() {
        unbindService();
    }
}
