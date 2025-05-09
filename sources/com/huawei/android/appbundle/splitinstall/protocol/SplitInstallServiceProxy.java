package com.huawei.android.appbundle.splitinstall.protocol;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes8.dex */
public interface SplitInstallServiceProxy extends IInterface {
    void cancelInstall(String str, int i, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException;

    void deferredInstall(String str, List<Bundle> list, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException;

    void deferredUninstall(String str, List<Bundle> list, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException;

    void getSessionState(String str, int i, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException;

    void getSessionStates(String str, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException;

    void startInstall(String str, List<Bundle> list, Bundle bundle, SplitInstallServiceCallbackProxy splitInstallServiceCallbackProxy) throws RemoteException;
}
