package defpackage;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.a.a.a;
import com.huawei.android.os.ServiceManagerEx;

/* loaded from: classes3.dex */
class bws {
    private static a b = null;
    private static boolean c = false;

    private bws() {
        throw new UnsupportedOperationException("this class can not be instantiated");
    }

    static a b() {
        synchronized (bws.class) {
            a aVar = b;
            if (aVar != null) {
                return aVar;
            }
            IBinder service = ServiceManagerEx.getService("hwservicebusmanager");
            if (service == null) {
                Log.e("DefKitLib_Helper", "DefAccessService is null.");
            } else {
                try {
                    service.linkToDeath(new IBinder.DeathRecipient() { // from class: bwu
                        @Override // android.os.IBinder.DeathRecipient
                        public final void binderDied() {
                            bws.e();
                        }
                    }, 0);
                } catch (RemoteException unused) {
                    Log.e("DefKitLib_Helper", "Loader linkToDeath failed with RemoteException.");
                }
            }
            b = a.AbstractBinderC0031a.b(service);
            Log.d("DefKitLib_Helper", "def access service binder: ".concat(String.valueOf(service)));
            if (!c) {
                Handler handler = new Handler(Looper.getMainLooper());
                bwj.a(handler);
                bwo.Ae_(handler);
                c = true;
            }
            return b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e() {
        Log.w("DefKitLib_Helper", "DefAccessService died.");
        synchronized (bws.class) {
            b = null;
        }
    }
}
