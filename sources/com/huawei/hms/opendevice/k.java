package com.huawei.hms.opendevice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes9.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private ServiceConnection f5656a;
    private Messenger b = null;

    class a implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Bundle f5657a;
        final /* synthetic */ Context b;

        a(Bundle bundle, Context context) {
            this.f5657a = bundle;
            this.b = context;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            HMSLog.i("RemoteService", "remote service onConnected");
            k.this.b = new Messenger(iBinder);
            Message obtain = Message.obtain();
            obtain.setData(this.f5657a);
            try {
                k.this.b.send(obtain);
            } catch (RemoteException unused) {
                HMSLog.i("RemoteService", "remote service message send failed");
            }
            HMSLog.i("RemoteService", "remote service unbindservice");
            this.b.unbindService(k.this.f5656a);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            HMSLog.i("RemoteService", "remote service onDisconnected");
            k.this.b = null;
        }
    }

    public boolean a(Context context, Bundle bundle, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        this.f5656a = new a(bundle, applicationContext);
        HMSLog.i("RemoteService", "remote service bind service start");
        return applicationContext.bindService(intent, this.f5656a, 1);
    }
}
