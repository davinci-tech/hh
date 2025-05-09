package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import java.util.List;

/* loaded from: classes8.dex */
public interface IToolSyncService extends IInterface {

    public static abstract class Stub extends Binder implements IToolSyncService {
        private static final String DESCRIPTOR = "com.huawei.android.hicloud.sync.service.aidl.IToolSyncService";
        static final int TRANSACTION_downUnstructFile = 5;
        static final int TRANSACTION_downUnstructFileForTransTooLarge = 6;
        static final int TRANSACTION_getExceedLimitNum = 4;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_reportInfo = 3;
        static final int TRANSACTION_unregisterCallback = 2;

        static class b implements IToolSyncService {
            public static IToolSyncService e;
            private IBinder d;

            b(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IToolSyncService
            public void downUnstructFile(String str, String str2, List<UnstructData> list, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeString(str3);
                    if (this.d.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().downUnstructFile(str, str2, list, str3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IToolSyncService
            public void downUnstructFileForTransTooLarge(String str, String str2, byte[] bArr, boolean z, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str3);
                    if (this.d.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().downUnstructFileForTransTooLarge(str, str2, bArr, z, str3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IToolSyncService
            public void getExceedLimitNum(String str, List<String> list, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeString(str2);
                    if (this.d.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getExceedLimitNum(str, list, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IToolSyncService
            public boolean registerCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.d.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(iSyncServiceCallback, str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IToolSyncService
            public void reportInfo(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (this.d.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportInfo(str, str2, str3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IToolSyncService
            public void unregisterCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.d.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallback(iSyncServiceCallback, str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IToolSyncService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IToolSyncService)) ? new b(iBinder) : (IToolSyncService) queryLocalInterface;
        }

        public static IToolSyncService getDefaultImpl() {
            return b.e;
        }

        public static boolean setDefaultImpl(IToolSyncService iToolSyncService) {
            if (b.e != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iToolSyncService == null) {
                return false;
            }
            b.e = iToolSyncService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerCallback = registerCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerCallback ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportInfo(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    getExceedLimitNum(parcel.readString(), parcel.createStringArrayList(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    downUnstructFile(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    downUnstructFileForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void downUnstructFile(String str, String str2, List<UnstructData> list, String str3) throws RemoteException;

    void downUnstructFileForTransTooLarge(String str, String str2, byte[] bArr, boolean z, String str3) throws RemoteException;

    void getExceedLimitNum(String str, List<String> list, String str2) throws RemoteException;

    boolean registerCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException;

    void reportInfo(String str, String str2, String str3) throws RemoteException;

    void unregisterCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException;
}
