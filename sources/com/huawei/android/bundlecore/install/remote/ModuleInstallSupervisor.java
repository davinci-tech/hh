package com.huawei.android.bundlecore.install.remote;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import defpackage.yi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class ModuleInstallSupervisor {

    public interface Callback {
        void onCancelInstall(int i, Bundle bundle);

        void onDeferredInstall(Bundle bundle);

        void onDeferredUninstall(Bundle bundle);

        void onError(Bundle bundle);

        void onGetSession(int i, Bundle bundle);

        void onGetSessionStates(List<Bundle> list);

        void onStartInstall(int i, Bundle bundle);
    }

    public abstract void cancelInstall(int i, Callback callback) throws RemoteException;

    public abstract boolean cancelInstallWithoutUserConfirmation(int i);

    public abstract boolean continueInstallWithUserConfirmation(int i);

    public abstract void deferredInstall(List<Bundle> list, Callback callback) throws RemoteException;

    public abstract void deferredUninstall(List<Bundle> list, Callback callback) throws RemoteException;

    public abstract void getSessionState(int i, Callback callback) throws RemoteException;

    public abstract void getSessionStates(Callback callback) throws RemoteException;

    public abstract void initialize(Context context);

    public abstract void startInstall(List<Bundle> list, Callback callback) throws RemoteException;

    protected static Bundle bundleErrorCode(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("error_code", i);
        return bundle;
    }

    protected static List<String> extractBundleModuleNames(Collection<Bundle> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<Bundle> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getString("module_name"));
        }
        return arrayList;
    }

    protected static int createSessionId(Collection<yi> collection) {
        int i = 0;
        for (yi yiVar : collection) {
            i += (yiVar.f() + "@" + yiVar.c() + "@" + yiVar.i()).hashCode();
        }
        return i > 0 ? i : -i;
    }

    protected static void postTask(Context context, Runnable runnable) {
        ModuleInstallService.e(context.getPackageName(), runnable);
    }
}
