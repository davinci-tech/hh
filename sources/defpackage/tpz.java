package defpackage;

import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.wearengine.NotifyManager;
import com.huawei.wearengine.WearEngineBinderClient;
import com.huawei.wearengine.WearEngineClientInner;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class tpz implements NotifyManager, WearEngineBinderClient {

    /* renamed from: a, reason: collision with root package name */
    private final Object f17340a = new Object();
    private IBinder.DeathRecipient e = new IBinder.DeathRecipient() { // from class: tpz.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tov.b("NotifyServiceProxy", "binderDied enter");
            if (tpz.this.d != null) {
                tpz.this.d.asBinder().unlinkToDeath(tpz.this.e, 0);
                tpz.this.d = null;
            }
        }
    };
    private NotifyManager d = null;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    public tpz() {
        d();
    }

    private void e() throws RemoteException {
        synchronized (this.f17340a) {
            if (this.d == null) {
                WearEngineClientInner.c().d();
                IBinder fcR_ = WearEngineClientInner.c().fcR_(4);
                if (fcR_ == null) {
                    throw new tnx(2);
                }
                NotifyManager asInterface = NotifyManager.Stub.asInterface(fcR_);
                this.d = asInterface;
                asInterface.asBinder().linkToDeath(this.e, 0);
            }
        }
    }

    @Override // com.huawei.wearengine.NotifyManager
    public int notify(Device device, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) {
        try {
            e();
            if (this.d == null) {
                return 6;
            }
            if (!tra.a("notify_notify")) {
                tov.d("NotifyServiceProxy", "notify Health version is low");
                throw new tnx(14);
            }
            return this.d.notify(device, notificationParcel, notifySendCallback);
        } catch (RemoteException unused) {
            tov.d("NotifyServiceProxy", "notify RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.WearEngineBinderClient
    public void clearBinderProxy() {
        this.d = null;
        tov.b("NotifyServiceProxy", "clearBinderProxy");
    }

    private void d() {
        WearEngineClientInner.c().a(new tob(new WeakReference(this)));
    }
}
