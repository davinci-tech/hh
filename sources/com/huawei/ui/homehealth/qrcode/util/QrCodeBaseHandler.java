package com.huawei.ui.homehealth.qrcode.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public abstract class QrCodeBaseHandler {
    protected static final int MSG_FAMILY_MAIN = 20000;
    protected static final int MSG_FAMILY_QR = 2000;
    protected static final int MSG_WIFI_MAIN = 10000;
    protected static final int MSG_WIFI_QR = 1000;
    private static final String TAG = "QrCodeBaseHandler";
    private static final String TAG_RELEASE = "R_QrCode_QrCodeBaseHandler";
    public WeakReference<Activity> mActivity;
    protected WeakReference<CommBaseCallbackInterface> mCallback;
    protected Bundle mExtraValues;
    protected Handler mHandler;
    private HandlerThread mHandlerThread;
    public Handler mMainThreadHandler;
    protected QrCodeDataBase mQrCodeDataBase;

    public abstract void execute();

    protected abstract void handleMessage(Message message);

    protected abstract void mainHandleMessage(Message message, Activity activity);

    public abstract QrCodeDataBase parser(String str, Object obj);

    public abstract boolean verify(QrCodeDataBase qrCodeDataBase);

    public QrCodeBaseHandler(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        this(activity, commBaseCallbackInterface, null);
    }

    public QrCodeBaseHandler(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface, Bundle bundle) {
        this.mActivity = new WeakReference<>(activity);
        this.mCallback = new WeakReference<>(commBaseCallbackInterface);
        this.mMainThreadHandler = new a(Looper.getMainLooper());
        this.mExtraValues = bundle;
    }

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Activity activity = QrCodeBaseHandler.this.getActivity();
            if (activity != null) {
                QrCodeBaseHandler.this.mainHandleMessage(message, activity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.mActivity;
        if (weakReference != null) {
            Activity activity = weakReference.get();
            if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                return activity;
            }
            LogUtil.b(TAG_RELEASE, " getActivity Activity is Destroyed");
            return null;
        }
        LogUtil.b(TAG_RELEASE, " getActivity mActivity is null");
        return null;
    }

    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            QrCodeBaseHandler.this.threadHandlerMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void threadHandlerMessage(Message message) {
        handleMessage(message);
    }

    public void initHandlerThread(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new d(this.mHandlerThread.getLooper());
    }

    public void sendCallBack(int i, String str, Object obj) {
        WeakReference<CommBaseCallbackInterface> weakReference = this.mCallback;
        if (weakReference != null) {
            CommBaseCallbackInterface commBaseCallbackInterface = weakReference.get();
            if (commBaseCallbackInterface != null) {
                commBaseCallbackInterface.onResult(i, str, obj);
                return;
            } else {
                LogUtil.h(TAG_RELEASE, "sendCallBack callback is null");
                return;
            }
        }
        LogUtil.h(TAG_RELEASE, "sendCallBack mCallback is null");
    }
}
