package com.huawei.wearengine.p2p;

import android.os.RemoteException;
import com.huawei.wearengine.p2p.ReceiverCallback;
import defpackage.tov;
import defpackage.tpx;
import defpackage.tpy;

/* loaded from: classes9.dex */
public class P2pClient$12 extends ReceiverCallback.Stub {
    final /* synthetic */ tpx this$0;
    final /* synthetic */ Receiver val$receiver;

    P2pClient$12(tpx tpxVar, Receiver receiver) {
        this.this$0 = tpxVar;
        this.val$receiver = receiver;
    }

    @Override // com.huawei.wearengine.p2p.ReceiverCallback
    public void onReceiveMessage(byte[] bArr) throws RemoteException {
        tov.b("P2pClient", "onReceiveMessage enter!");
        tpy.b bVar = new tpy.b();
        bVar.a(bArr);
        this.val$receiver.onReceiveMessage(bVar.a());
    }

    @Override // com.huawei.wearengine.p2p.ReceiverCallback
    public void onReceiveFileMessage(MessageParcel messageParcel) throws RemoteException {
        tov.b("P2pClient", "onReceiveFileMessage enter!");
        this.this$0.a(this.val$receiver, messageParcel);
    }
}
