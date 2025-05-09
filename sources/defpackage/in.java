package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class in implements com.alipay.sdk.m.b.b {

    public static final class b implements IInterface {
        public IBinder c;

        public b(IBinder iBinder) {
            this.c = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.c;
        }

        public String e() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                this.c.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public static final class e implements ServiceConnection {
        public final LinkedBlockingQueue<IBinder> c;
        public boolean e;

        public e() {
            this.e = false;
            this.c = new LinkedBlockingQueue<>();
        }

        public IBinder a() throws InterruptedException {
            if (this.e) {
                throw new IllegalStateException();
            }
            this.e = true;
            return this.c.poll(5L, TimeUnit.SECONDS);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.c.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    @Override // com.alipay.sdk.m.b.b
    public String a(Context context) {
        e eVar = new e();
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        if (context.bindService(intent, eVar, 1)) {
            try {
                return new b(eVar.a()).e();
            } catch (Exception unused) {
            } finally {
                context.unbindService(eVar);
            }
        }
        return null;
    }
}
