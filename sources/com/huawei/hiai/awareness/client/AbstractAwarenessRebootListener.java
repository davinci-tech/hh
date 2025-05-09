package com.huawei.hiai.awareness.client;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hiai.awareness.client.OnResultListener;
import com.huawei.hiai.awareness.util.AwarenessSdkCallPermission;
import com.huawei.hiai.awareness.util.LogUtil;

/* loaded from: classes8.dex */
public abstract class AbstractAwarenessRebootListener extends Service {
    private static final String TAG = "AbstractAwarenessRebootListener";

    protected void onReboot(int i) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new OnResultListener.Stub() { // from class: com.huawei.hiai.awareness.client.AbstractAwarenessRebootListener.1
            private final Context context;

            {
                this.context = AbstractAwarenessRebootListener.this.getApplicationContext();
            }

            @Override // com.huawei.hiai.awareness.client.OnResultListener.Stub, android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                Context context = this.context;
                if (context == null) {
                    LogUtil.e(AbstractAwarenessRebootListener.TAG, "Do onTransact, reason is mContext is null");
                    return false;
                }
                if (context.getPackageManager() == null) {
                    LogUtil.e(AbstractAwarenessRebootListener.TAG, "Do onTransact, packageManager is null");
                    return false;
                }
                if (!AwarenessSdkCallPermission.checkAppTrust(this.context, Binder.getCallingUid())) {
                    LogUtil.e(AbstractAwarenessRebootListener.TAG, "Do onTransact, checkAppTrust failed");
                    return false;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }

            @Override // com.huawei.hiai.awareness.client.OnResultListener
            public void onResult(AwarenessResult awarenessResult) {
                AbstractAwarenessRebootListener.this.onReboot(awarenessResult.code());
            }
        };
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }
}
