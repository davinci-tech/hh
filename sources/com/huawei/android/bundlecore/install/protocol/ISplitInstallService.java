package com.huawei.android.bundlecore.install.protocol;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import java.util.List;

/* loaded from: classes8.dex */
public interface ISplitInstallService extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.android.bundlecore.install.protocol.ISplitInstallService";

    void cancelInstall(String str, int i, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException;

    void deferredInstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException;

    void deferredUninstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException;

    void getSessionState(String str, int i, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException;

    void getSessionStates(String str, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException;

    void startInstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISplitInstallService {
        static final int TRANSACTION_cancelInstall = 2;
        static final int TRANSACTION_deferredInstall = 5;
        static final int TRANSACTION_deferredUninstall = 6;
        static final int TRANSACTION_getSessionState = 3;
        static final int TRANSACTION_getSessionStates = 4;
        static final int TRANSACTION_startInstall = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISplitInstallService.DESCRIPTOR);
        }

        public static ISplitInstallService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISplitInstallService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISplitInstallService)) {
                return (ISplitInstallService) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISplitInstallService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISplitInstallService.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    startInstall(parcel.readString(), parcel.createTypedArrayList(Bundle.CREATOR), (Bundle) a.ex_(parcel, Bundle.CREATOR), ISplitInstallServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    cancelInstall(parcel.readString(), parcel.readInt(), (Bundle) a.ex_(parcel, Bundle.CREATOR), ISplitInstallServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    getSessionState(parcel.readString(), parcel.readInt(), ISplitInstallServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    getSessionStates(parcel.readString(), ISplitInstallServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    deferredInstall(parcel.readString(), parcel.createTypedArrayList(Bundle.CREATOR), (Bundle) a.ex_(parcel, Bundle.CREATOR), ISplitInstallServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    deferredUninstall(parcel.readString(), parcel.createTypedArrayList(Bundle.CREATOR), (Bundle) a.ex_(parcel, Bundle.CREATOR), ISplitInstallServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class d implements ISplitInstallService {
            private IBinder b;

            d(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
            public void startInstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplitInstallService.DESCRIPTOR);
                    obtain.writeString(str);
                    a.ey_(obtain, list, 0);
                    a.ez_(obtain, bundle, 0);
                    obtain.writeStrongInterface(iSplitInstallServiceCallback);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
            public void cancelInstall(String str, int i, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplitInstallService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    a.ez_(obtain, bundle, 0);
                    obtain.writeStrongInterface(iSplitInstallServiceCallback);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
            public void getSessionState(String str, int i, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplitInstallService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iSplitInstallServiceCallback);
                    this.b.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
            public void getSessionStates(String str, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplitInstallService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iSplitInstallServiceCallback);
                    this.b.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
            public void deferredInstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplitInstallService.DESCRIPTOR);
                    obtain.writeString(str);
                    a.ey_(obtain, list, 0);
                    a.ez_(obtain, bundle, 0);
                    obtain.writeStrongInterface(iSplitInstallServiceCallback);
                    this.b.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
            public void deferredUninstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplitInstallService.DESCRIPTOR);
                    obtain.writeString(str);
                    a.ey_(obtain, list, 0);
                    a.ez_(obtain, bundle, 0);
                    obtain.writeStrongInterface(iSplitInstallServiceCallback);
                    this.b.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T ex_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ez_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ey_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                ez_(parcel, list.get(i2), i);
            }
        }
    }
}
