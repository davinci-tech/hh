package com.huawei.android.appbundle.splitinstall.protocol;

import android.os.Bundle;
import android.os.Parcel;
import com.huawei.android.appbundle.binder.BinderWrapper;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import defpackage.wy;

/* loaded from: classes8.dex */
public abstract class SplitInstallServiceCallback extends BinderWrapper implements SplitInstallServiceCallbackProxy {
    public SplitInstallServiceCallback() {
        super(ISplitInstallServiceCallback.DESCRIPTOR);
    }

    @Override // com.huawei.android.appbundle.binder.BinderWrapper
    public final boolean dispatchTransact(int i, Parcel parcel) {
        switch (i) {
            case 1:
                onStartInstall(parcel.readInt(), (Bundle) wy.ea_(parcel, Bundle.CREATOR));
                return true;
            case 2:
                int readInt = parcel.readInt();
                wy.ea_(parcel, Bundle.CREATOR);
                onCompleteInstall(readInt);
                return true;
            case 3:
                onCancelInstall(parcel.readInt(), (Bundle) wy.ea_(parcel, Bundle.CREATOR));
                return true;
            case 4:
                onGetSession(parcel.readInt(), (Bundle) wy.ea_(parcel, Bundle.CREATOR));
                return true;
            case 5:
                onDeferredUninstall((Bundle) wy.ea_(parcel, Bundle.CREATOR));
                return true;
            case 6:
                onDeferredInstall((Bundle) wy.ea_(parcel, Bundle.CREATOR));
                return true;
            case 7:
                onGetSessionStates(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 8:
                onError((Bundle) wy.ea_(parcel, Bundle.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
