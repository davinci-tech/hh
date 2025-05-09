package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/* loaded from: classes5.dex */
public class lsh {
    public static IBinder a() {
        IBinder cab_ = cab_("SamgrService");
        if (cab_ == null) {
            Log.e("ConnectUtils", "samgr is null");
            return cab_;
        }
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        obtain.writeInt(403);
        try {
            try {
                cab_.transact(2, obtain, obtain2, 0);
                return obtain2.readStrongBinder();
            } catch (RemoteException e) {
                Log.e("ConnectUtils", "get system ability occurs exception" + e.getMessage());
                obtain.recycle();
                obtain2.recycle();
                return null;
            }
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    private static IBinder cab_(String str) {
        try {
            Object invoke = Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, str);
            if (invoke instanceof IBinder) {
                return (IBinder) invoke;
            }
            return null;
        } catch (ReflectiveOperationException e) {
            Log.e("ConnectUtils", "getService exception" + e.getMessage());
            return null;
        }
    }
}
