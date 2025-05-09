package defpackage;

import android.os.RemoteException;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.CancelFileTransferCallBack;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import com.huawei.wearengine.p2p.PingCallback;
import com.huawei.wearengine.p2p.Receiver;
import com.huawei.wearengine.p2p.ReceiverCallback;
import com.huawei.wearengine.p2p.SendCallback;
import com.huawei.wearengine.p2p.SendExtraCallback;
import defpackage.tov;
import defpackage.tpx;
import defpackage.tpy;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class tpx {

    /* renamed from: a, reason: collision with root package name */
    private static volatile tpx f17332a;
    private String d = "";
    private String b = "";
    private Timer i = null;
    private ExecutorService e = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private tqe c = tqe.a();

    private tpx() {
    }

    public static tpx a() {
        if (f17332a == null) {
            synchronized (tpx.class) {
                if (f17332a == null) {
                    f17332a = new tpx();
                }
            }
        }
        return f17332a;
    }

    public Task<Void> d(final Device device, final PingCallback pingCallback) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tpx.4
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(device, "Device can not be null!");
                top.a(pingCallback, "PingCallback can not be null!");
                P2pPingCallback e = tpx.this.e(pingCallback);
                int ping = tpx.this.c.ping(device, trr.c().getPackageName(), tpx.this.d, e);
                if (ping == 0) {
                    return null;
                }
                throw new tnx(ping);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public P2pPingCallback e(final PingCallback pingCallback) {
        return new P2pPingCallback.Stub() { // from class: com.huawei.wearengine.p2p.P2pClient$2
            @Override // com.huawei.wearengine.p2p.P2pPingCallback
            public void onResult(int i) {
                pingCallback.onPingResult(i);
            }
        };
    }

    public Task<Void> e(final Device device, final tpy tpyVar, final SendCallback sendCallback) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tpx.1
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                String packageName = trr.c().getPackageName();
                String c = trr.c(trr.c(), packageName);
                top.a(packageName, "srcPkgName can not be null!");
                top.a(c, "srcFingerPrint can not be null!");
                top.a(device, "Device can not be null!");
                top.a(tpyVar, "Message can not be null!");
                top.a(sendCallback, "SendCallback can not be null!");
                tov.b("P2pClient", "doSend srcPkgName: " + packageName + " srcFingerPrint:" + c);
                int a2 = tpx.this.a(device, new IdentityInfo(packageName, c), new IdentityInfo(tpx.this.d, tpx.this.b), tpyVar, sendCallback);
                if (a2 == 0) {
                    return null;
                }
                throw new tnx(a2);
            }
        });
    }

    public Task<Void> d(final tqc tqcVar, final tpy tpyVar, final SendCallback sendCallback) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tpx.7
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(tqcVar, "Peer can not be null!");
                Device e = tqcVar.e();
                String packageName = trr.c().getPackageName();
                String c = trr.c(trr.c(), packageName);
                top.a(e, "Device can not be null!");
                top.a(packageName, "srcPkgName can not be null!");
                top.a(c, "srcFingerPrint can not be null!");
                top.a(tpyVar, "Message can not be null!");
                top.a(sendCallback, "SendCallback can not be null!");
                tov.b("P2pClient", "doSend srcPkgName: " + packageName + " srcFingerPrint:" + c);
                int a2 = tpx.this.a(e, new IdentityInfo(packageName, c), new IdentityInfo(tqcVar.a(), tqcVar.b()), tpyVar, sendCallback);
                if (a2 == 0) {
                    return null;
                }
                throw new tnx(a2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, tpy tpyVar, final SendCallback sendCallback) {
        if ((sendCallback instanceof SendExtraCallback) && !tra.a("p2p_send_file_transfer_way_report")) {
            tov.d("P2pClient", "query Health version is low");
            throw new tnx(14);
        }
        MessageParcel b = trn.b(tpyVar);
        MessageParcelExtra c = trn.c(tpyVar, b);
        P2pSendCallback.Stub stub = new P2pSendCallback.Stub() { // from class: com.huawei.wearengine.p2p.P2pClient$6
            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onSendResult(int i) {
                sendCallback.onSendResult(i);
            }

            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onSendProgress(long j) {
                sendCallback.onSendProgress(j);
            }

            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onFileTransferReport(String str) {
                SendCallback sendCallback2 = sendCallback;
                if (sendCallback2 instanceof SendExtraCallback) {
                    ((SendExtraCallback) sendCallback2).onFileTransferReport(str);
                }
            }
        };
        int sendExtra = this.c.sendExtra(device, c, identityInfo, identityInfo2, stub);
        return sendExtra == 14 ? this.c.send(device, b, identityInfo, identityInfo2, stub) : sendExtra;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, tqa tqaVar, SendCallback sendCallback) {
        if (tqaVar.b()) {
            return;
        }
        tqaVar.cancel();
        sendCallback.onSendResult(i);
    }

    public Task<Void> a(final tqc tqcVar, final tpv tpvVar, final CancelFileTransferCallBack cancelFileTransferCallBack) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tpx.3
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(tqcVar, "Peer can not be null!");
                Device e = tqcVar.e();
                String packageName = trr.c().getPackageName();
                String c = trr.c(trr.c(), packageName);
                top.a(packageName, "srcPkgName can not be null!");
                top.a(c, "srcFingerPrint can not be null!");
                top.a(e, "Device can not be null!");
                top.a(tpvVar, "fileIdentification can not be null!");
                top.a(cancelFileTransferCallBack, "CancelFileTransferCallBack can not be null!");
                int c2 = tpx.this.c(e, new IdentityInfo(packageName, c), new IdentityInfo(tqcVar.a(), tqcVar.b()), tpvVar, cancelFileTransferCallBack);
                if (c2 == 0) {
                    return null;
                }
                throw new tnx(c2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, tpv tpvVar, final CancelFileTransferCallBack cancelFileTransferCallBack) {
        return this.c.cancelFileTransfer(device, trn.c(tpvVar), identityInfo, identityInfo2, new P2pCancelFileTransferCallBack.Stub() { // from class: com.huawei.wearengine.p2p.P2pClient$11
            @Override // com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack
            public void onCancelFileTransferResult(int i, String str) throws RemoteException {
                tov.a("P2pClient", "cancelFileTransfer onCancelFileTransferResult, errCode: " + i);
                cancelFileTransferCallBack.onCancelFileTransferResult(i);
            }
        });
    }

    public tpx c(String str) {
        this.d = str;
        return this;
    }

    public tpx d(String str) {
        this.b = str;
        return this;
    }

    public Task<Void> a(final Device device, final Receiver receiver) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tpx.2
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                String packageName = trr.c().getPackageName();
                String c = trr.c(trr.c(), packageName);
                top.a(device, "Device can not be null!");
                top.a(packageName, "srcPkgName can not be null!");
                top.a(c, "srcFingerPrint can not be null!");
                top.a(receiver, "Receiver can not be null!");
                int e = tpx.this.e(device, receiver, new IdentityInfo(packageName, c), new IdentityInfo(tpx.this.d, tpx.this.b));
                if (e == 0) {
                    return null;
                }
                throw new tnx(e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(Device device, final Receiver receiver, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        return this.c.registerReceiver(device, identityInfo, identityInfo2, new ReceiverCallback.Stub() { // from class: com.huawei.wearengine.p2p.P2pClient$15
            @Override // com.huawei.wearengine.p2p.ReceiverCallback
            public void onReceiveMessage(byte[] bArr) throws RemoteException {
                tpy.b bVar = new tpy.b();
                bVar.a(bArr);
                receiver.onReceiveMessage(bVar.a());
            }

            @Override // com.huawei.wearengine.p2p.ReceiverCallback
            public void onReceiveFileMessage(MessageParcel messageParcel) throws RemoteException {
                tov.b("P2pClient", "onReceiveFileMessage enter");
                tpx.this.a(receiver, messageParcel);
            }
        }, System.identityHashCode(receiver));
    }

    public Task<Void> e(final Receiver receiver) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tpx.5
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(receiver, "Receiver can not be null!");
                int identityHashCode = System.identityHashCode(receiver);
                int unregisterReceiver = tpx.this.c.unregisterReceiver(tpx.this.b(), identityHashCode);
                if (unregisterReceiver == 0) {
                    return null;
                }
                throw new tnx(unregisterReceiver);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ReceiverCallback b() {
        return new ReceiverCallback.Stub() { // from class: com.huawei.wearengine.p2p.P2pClient$17
            @Override // com.huawei.wearengine.p2p.ReceiverCallback
            public void onReceiveMessage(byte[] bArr) {
                tov.b("P2pClient", "getReceiverCallback onReceiveMessage");
            }

            @Override // com.huawei.wearengine.p2p.ReceiverCallback
            public void onReceiveFileMessage(MessageParcel messageParcel) {
                tov.b("P2pClient", "getReceiverCallback onReceiveFileMessage");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Receiver receiver, MessageParcel messageParcel) {
        tov.b("P2pClient", "enter handleReceiveFile");
        if (messageParcel == null) {
            tov.d("P2pClient", "handleReceiveFile messageParcel is null");
            receiver.onReceiveMessage(new tpy.b().a());
            return;
        }
        tpy.b bVar = new tpy.b();
        int type = messageParcel.getType();
        tov.b("P2pClient", "handleReceiveFile type:" + type);
        if (type == 2) {
            bVar.d(tsa.ffn_(messageParcel.getFileName(), messageParcel.getParcelFileDescriptor()));
            receiver.onReceiveMessage(bVar.a());
        } else {
            tov.c("P2pClient", "handleReceiveFile type is not file");
        }
    }
}
