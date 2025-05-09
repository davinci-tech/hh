package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy;
import com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallService;
import java.util.List;

/* loaded from: classes8.dex */
public class ye extends wx implements SplitInstallServiceProxy {
    public ye(IBinder iBinder) {
        super(iBinder, ISplitInstallService.DESCRIPTOR);
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy
    public final void startInstall(String str, List<Bundle> list, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException {
        Parcel dY_ = dY_();
        dY_.writeString(str);
        dY_.writeTypedList(list);
        wy.ec_(dY_, bundle);
        wy.eb_(dY_, splitInstallServiceCallbackProxy);
        dZ_(1, dY_);
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy
    public void cancelInstall(String str, int i, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException {
        Parcel dY_ = dY_();
        dY_.writeString(str);
        dY_.writeInt(i);
        wy.ec_(dY_, bundle);
        wy.eb_(dY_, splitInstallServiceCallbackProxy);
        dZ_(2, dY_);
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy
    public void getSessionState(String str, int i, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException {
        Parcel dY_ = dY_();
        dY_.writeString(str);
        dY_.writeInt(i);
        wy.eb_(dY_, splitInstallServiceCallbackProxy);
        dZ_(3, dY_);
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy
    public void getSessionStates(String str, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException {
        Parcel dY_ = dY_();
        dY_.writeString(str);
        wy.eb_(dY_, splitInstallServiceCallbackProxy);
        dZ_(4, dY_);
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy
    public void deferredInstall(String str, List<Bundle> list, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException {
        Parcel dY_ = dY_();
        dY_.writeString(str);
        dY_.writeTypedList(list);
        wy.ec_(dY_, bundle);
        wy.eb_(dY_, splitInstallServiceCallbackProxy);
        dZ_(5, dY_);
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy
    public void deferredUninstall(String str, List<Bundle> list, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException {
        Parcel dY_ = dY_();
        dY_.writeString(str);
        dY_.writeTypedList(list);
        wy.ec_(dY_, bundle);
        wy.eb_(dY_, splitInstallServiceCallbackProxy);
        dZ_(6, dY_);
    }
}
