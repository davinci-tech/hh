package defpackage;

import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.wearengine.P2pManager;
import com.huawei.wearengine.WearEngineBinderClient;
import com.huawei.wearengine.WearEngineClientInner;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import com.huawei.wearengine.p2p.ReceiverCallback;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class tqe implements P2pManager, WearEngineBinderClient {
    private static volatile tqe b;
    private final Object d = new Object();
    private IBinder.DeathRecipient e = new IBinder.DeathRecipient() { // from class: tqe.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tov.b("P2pServiceProxy", "binderDied enter");
            if (tqe.this.f17344a != null) {
                tqe.this.f17344a.asBinder().unlinkToDeath(tqe.this.e, 0);
                tqe.this.f17344a = null;
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private P2pManager f17344a = null;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    private tqe() {
        d();
    }

    private void c() throws RemoteException {
        synchronized (this.d) {
            if (this.f17344a == null) {
                WearEngineClientInner.c().d();
                IBinder fcR_ = WearEngineClientInner.c().fcR_(2);
                if (fcR_ == null) {
                    throw new tnx(2);
                }
                P2pManager asInterface = P2pManager.Stub.asInterface(fcR_);
                this.f17344a = asInterface;
                asInterface.asBinder().linkToDeath(this.e, 0);
            }
        }
    }

    public static tqe a() {
        if (b == null) {
            synchronized (tqe.class) {
                if (b == null) {
                    b = new tqe();
                }
            }
        }
        return b;
    }

    @Override // com.huawei.wearengine.P2pManager
    public int ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) {
        try {
            c();
            if (this.f17344a == null) {
                return 6;
            }
            tov.b("P2pServiceProxy", "Start ping");
            return this.f17344a.ping(device, str, str2, p2pPingCallback);
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "ping RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int send(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) {
        try {
            c();
            P2pManager p2pManager = this.f17344a;
            if (p2pManager != null) {
                return p2pManager.send(device, messageParcel, identityInfo, identityInfo2, p2pSendCallback);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "send RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int sendExtra(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) {
        try {
            c();
            if (!tra.a("p2p_send_extra")) {
                tov.c("P2pServiceProxy", "sendExtra health not support");
                return 14;
            }
            P2pManager p2pManager = this.f17344a;
            if (p2pManager != null) {
                return p2pManager.sendExtra(device, messageParcelExtra, identityInfo, identityInfo2, p2pSendCallback);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "send RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int sendInternal(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) {
        try {
            c();
            P2pManager p2pManager = this.f17344a;
            if (p2pManager != null) {
                return p2pManager.sendInternal(device, messageParcelExtra, identityInfo, identityInfo2, p2pSendCallback);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "sendInternal RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int getDeviceAppVersionCode(Device device, String str, String str2) {
        tov.d("P2pServiceProxy", "getDeviceAppVersionCode");
        try {
            c();
            if (this.f17344a != null) {
                if (!tra.a("p2p_get_device_app_version_code")) {
                    tov.d("P2pServiceProxy", "getDeviceAppVersionCode Health version is low");
                    throw new tnx(14);
                }
                return this.f17344a.getDeviceAppVersionCode(device, str, str2);
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "getDeviceAppVersionCode RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int registerReceiver(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) {
        try {
            c();
            P2pManager p2pManager = this.f17344a;
            if (p2pManager != null) {
                return p2pManager.registerReceiver(device, identityInfo, identityInfo2, receiverCallback, i);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "registerReceiver RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int registerReceiverInternal(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) {
        try {
            c();
            P2pManager p2pManager = this.f17344a;
            if (p2pManager != null) {
                return p2pManager.registerReceiverInternal(device, identityInfo, identityInfo2, receiverCallback, i);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "registerReceiverInternal RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) {
        try {
            c();
            if (!tra.a("p2p_cancel_file_transfer")) {
                tov.c("P2pServiceProxy", "cancelFileTransfer health not support");
                return 14;
            }
            P2pManager p2pManager = this.f17344a;
            if (p2pManager != null) {
                return p2pManager.cancelFileTransfer(device, fileIdentificationParcel, identityInfo, identityInfo2, p2pCancelFileTransferCallBack);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "cancelFileTransfer RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            tov.d("P2pServiceProxy", "cancelFileTransfer IllegalStateException");
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int unregisterReceiver(ReceiverCallback receiverCallback, int i) {
        try {
            c();
            P2pManager p2pManager = this.f17344a;
            if (p2pManager != null) {
                return p2pManager.unregisterReceiver(receiverCallback, i);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("P2pServiceProxy", "unregisterReceiver RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.WearEngineBinderClient
    public void clearBinderProxy() {
        this.f17344a = null;
        tov.b("P2pServiceProxy", "clearBinderProxy");
    }

    private void d() {
        WearEngineClientInner.c().a(new tob(new WeakReference(this)));
    }
}
