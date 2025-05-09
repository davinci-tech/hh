package com.huawei.hms.scankit;

import android.os.RemoteException;
import com.huawei.hms.hmsscankit.api.IRemoteCreator;
import com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate;
import com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate;
import com.huawei.hms.scankit.p.o4;

/* loaded from: classes9.dex */
public class Creator extends IRemoteCreator.Stub {
    private static final String TAG = "Creator";
    private e iRemoteViewDelegate = null;
    private f iRemoteDecoderDelegate = null;
    private g iRemoteHmsDecoderDelegate = null;

    @Override // com.huawei.hms.hmsscankit.api.IRemoteCreator
    public IRemoteDecoderDelegate newRemoteDecoderDelegate() throws RemoteException {
        o4.d(TAG, "newRemoteDecoderDelegate()");
        f a2 = f.a();
        this.iRemoteDecoderDelegate = a2;
        return a2;
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteCreator
    public IRemoteHmsDecoderDelegate newRemoteHmsDecoderDelegate() throws RemoteException {
        o4.d(TAG, "newRemoteHmsDecoderDelegate()");
        g a2 = g.a();
        this.iRemoteHmsDecoderDelegate = a2;
        return a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bc  */
    @Override // com.huawei.hms.hmsscankit.api.IRemoteCreator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.hmsscankit.api.IRemoteViewDelegate newRemoteViewDelegate(com.huawei.hms.feature.dynamic.IObjectWrapper r14, com.huawei.hms.feature.dynamic.IObjectWrapper r15) throws android.os.RemoteException {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.Creator.newRemoteViewDelegate(com.huawei.hms.feature.dynamic.IObjectWrapper, com.huawei.hms.feature.dynamic.IObjectWrapper):com.huawei.hms.hmsscankit.api.IRemoteViewDelegate");
    }
}
