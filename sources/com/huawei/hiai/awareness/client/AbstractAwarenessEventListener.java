package com.huawei.hiai.awareness.client;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hiai.awareness.client.OnEnvelopeReceiver;
import com.huawei.hiai.awareness.util.AwarenessSdkCallPermission;
import com.huawei.hiai.awareness.util.LogUtil;

/* loaded from: classes8.dex */
public class AbstractAwarenessEventListener extends Service {
    private static final String TAG = "AbstractAwarenessEventListener";

    protected void onFenceTrigger(AwarenessFence awarenessFence) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.i(TAG, "receive bind request");
        return new OnEnvelopeReceiver.Stub() { // from class: com.huawei.hiai.awareness.client.AbstractAwarenessEventListener.1
            private final Context context;

            {
                this.context = AbstractAwarenessEventListener.this.getApplicationContext();
            }

            @Override // com.huawei.hiai.awareness.client.OnEnvelopeReceiver.Stub, android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                Context context = this.context;
                if (context == null) {
                    LogUtil.e(AbstractAwarenessEventListener.TAG, "Do onTransact, reason is Context is null.");
                    return false;
                }
                if (context.getPackageManager() == null) {
                    LogUtil.e(AbstractAwarenessEventListener.TAG, "Do onTransact, packageManager is null.");
                    return false;
                }
                if (!AwarenessSdkCallPermission.checkAppTrust(this.context, Binder.getCallingUid())) {
                    LogUtil.e(AbstractAwarenessEventListener.TAG, "Do onTransact, checkAppTrust failed.");
                    return false;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }

            @Override // com.huawei.hiai.awareness.client.OnEnvelopeReceiver
            public void onReceive(AwarenessEnvelope awarenessEnvelope) {
                AbstractAwarenessEventListener.this.onFenceTrigger(AwarenessFence.parseFrom(awarenessEnvelope));
            }
        };
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
