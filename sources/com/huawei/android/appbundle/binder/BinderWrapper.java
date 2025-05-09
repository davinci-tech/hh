package com.huawei.android.appbundle.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes8.dex */
public class BinderWrapper extends Binder implements IInterface {
    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    protected boolean dispatchTransact(int i, Parcel parcel) {
        return false;
    }

    public BinderWrapper(String str) {
        attachInterface(this, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0009, code lost:
    
        if (super.onTransact(r2, r3, r4, r5) == false) goto L8;
     */
    @Override // android.os.Binder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean onTransact(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            r0 = 16777215(0xffffff, float:2.3509886E-38)
            if (r2 <= r0) goto Lc
            boolean r4 = super.onTransact(r2, r3, r4, r5)
            if (r4 != 0) goto L19
            goto L13
        Lc:
            java.lang.String r4 = r1.getInterfaceDescriptor()
            r3.enforceInterface(r4)
        L13:
            boolean r2 = r1.dispatchTransact(r2, r3)
            if (r2 == 0) goto L1b
        L19:
            r2 = 1
            goto L1c
        L1b:
            r2 = 0
        L1c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.android.appbundle.binder.BinderWrapper.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
