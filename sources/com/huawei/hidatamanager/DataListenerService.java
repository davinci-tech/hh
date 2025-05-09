package com.huawei.hidatamanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hidatamanager.IDataDispatchInterface;
import com.huawei.hidatamanager.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public abstract class DataListenerService extends Service {
    private static final String TAG = "DataListenerService";
    private Handler mHandler;
    private final Object mLock = new Object();
    protected DataListenerWrapper mWrapper = null;

    protected abstract void onFeatureDataAdded(long j, long j2, String str);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtils.d(TAG, "onBind");
        if (this.mWrapper == null) {
            this.mWrapper = new DataListenerWrapper();
        }
        return this.mWrapper;
    }

    @Override // android.app.Service, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        this.mHandler = new MyHandler(getMainLooper());
    }

    protected class DataListenerWrapper extends IDataDispatchInterface.Stub {
        protected DataListenerWrapper() {
        }

        @Override // com.huawei.hidatamanager.IDataDispatchInterface
        public void onFeatureDataAdded(long j, long j2, String str) throws RemoteException {
            synchronized (DataListenerService.this.mLock) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = str;
                DataListenerService.this.mHandler.sendMessage(obtain);
            }
        }
    }

    final class MyHandler extends Handler {
        public static final int MSG_ON_FEATURE_DATA_CHANGED = 1;

        public MyHandler(Looper looper) {
            super(looper, null);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            String str = (String) message.obj;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                DataListenerService.this.onFeatureDataAdded(jSONObject.optLong("dataType"), jSONObject.optLong("timestamp"), str);
            } catch (JSONException unused) {
                LogUtils.w(DataListenerService.TAG, " json data exception!");
            }
        }
    }
}
