package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import java.util.List;

/* loaded from: classes8.dex */
public interface IRequireDownService extends IInterface {

    public static abstract class Stub extends Binder implements IRequireDownService {
        private static final String DESCRIPTOR = "com.huawei.android.hicloud.sync.service.aidl.IRequireDownService";
        static final int TRANSACTION_cancelDownloadFile = 6;
        static final int TRANSACTION_cancelDownloadFileForTransTooLarge = 10;
        static final int TRANSACTION_downLoadFile = 3;
        static final int TRANSACTION_downLoadFileForTransTooLarge = 7;
        static final int TRANSACTION_pauseDownloadFile = 5;
        static final int TRANSACTION_pauseDownloadFileForTransTooLarge = 9;
        static final int TRANSACTION_registerSingleCallback = 1;
        static final int TRANSACTION_startDownloadFile = 4;
        static final int TRANSACTION_startDownloadFileForTransTooLarge = 8;
        static final int TRANSACTION_unregisterSingleCallback = 2;

        static class e implements IRequireDownService {
            public static IRequireDownService c;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1837a;

            e(IBinder iBinder) {
                this.f1837a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1837a;
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void cancelDownloadFile(String str, String str2, List<UnstructData> list, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (this.f1837a.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().cancelDownloadFile(str, str2, list, str3, str4);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void cancelDownloadFileForTransTooLarge(String str, String str2, String str3, byte[] bArr, boolean z, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str4);
                    try {
                        if (this.f1837a.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().cancelDownloadFileForTransTooLarge(str, str2, str3, bArr, z, str4);
                            obtain2.recycle();
                            obtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void downLoadFile(String str, String str2, List<UnstructData> list, boolean z, boolean z2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    try {
                        if (this.f1837a.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().downLoadFile(str, str2, list, z, z2, str3, str4);
                            obtain2.recycle();
                            obtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void downLoadFileForTransTooLarge(String str, String str2, boolean z, boolean z2, String str3, byte[] bArr, boolean z3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    obtain.writeString(str3);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z3 ? 1 : 0);
                    obtain.writeString(str4);
                    try {
                        if (this.f1837a.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().downLoadFileForTransTooLarge(str, str2, z, z2, str3, bArr, z3, str4);
                            obtain2.recycle();
                            obtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void pauseDownloadFile(String str, String str2, List<UnstructData> list, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (this.f1837a.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().pauseDownloadFile(str, str2, list, str3, str4);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void pauseDownloadFileForTransTooLarge(String str, String str2, String str3, byte[] bArr, boolean z, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str4);
                    try {
                        if (this.f1837a.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().pauseDownloadFileForTransTooLarge(str, str2, str3, bArr, z, str4);
                            obtain2.recycle();
                            obtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public boolean registerSingleCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.f1837a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerSingleCallback(iSyncServiceCallback, str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void startDownloadFile(String str, String str2, List<UnstructData> list, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (this.f1837a.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().startDownloadFile(str, str2, list, str3, str4);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void startDownloadFileForTransTooLarge(String str, String str2, String str3, byte[] bArr, boolean z, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str4);
                    try {
                        if (this.f1837a.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().startDownloadFileForTransTooLarge(str, str2, str3, bArr, z, str4);
                            obtain2.recycle();
                            obtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IRequireDownService
            public void unregisterSingleCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.f1837a.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterSingleCallback(iSyncServiceCallback, str, str2);
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

        public static IRequireDownService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IRequireDownService)) ? new e(iBinder) : (IRequireDownService) queryLocalInterface;
        }

        public static IRequireDownService getDefaultImpl() {
            return e.c;
        }

        public static boolean setDefaultImpl(IRequireDownService iRequireDownService) {
            if (e.c != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRequireDownService == null) {
                return false;
            }
            e.c = iRequireDownService;
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
                    boolean registerSingleCallback = registerSingleCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerSingleCallback ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterSingleCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    downLoadFile(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    startDownloadFile(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseDownloadFile(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelDownloadFile(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    downLoadFileForTransTooLarge(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    startDownloadFileForTransTooLarge(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseDownloadFileForTransTooLarge(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelDownloadFileForTransTooLarge(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void cancelDownloadFile(String str, String str2, List<UnstructData> list, String str3, String str4) throws RemoteException;

    void cancelDownloadFileForTransTooLarge(String str, String str2, String str3, byte[] bArr, boolean z, String str4) throws RemoteException;

    void downLoadFile(String str, String str2, List<UnstructData> list, boolean z, boolean z2, String str3, String str4) throws RemoteException;

    void downLoadFileForTransTooLarge(String str, String str2, boolean z, boolean z2, String str3, byte[] bArr, boolean z3, String str4) throws RemoteException;

    void pauseDownloadFile(String str, String str2, List<UnstructData> list, String str3, String str4) throws RemoteException;

    void pauseDownloadFileForTransTooLarge(String str, String str2, String str3, byte[] bArr, boolean z, String str4) throws RemoteException;

    boolean registerSingleCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException;

    void startDownloadFile(String str, String str2, List<UnstructData> list, String str3, String str4) throws RemoteException;

    void startDownloadFileForTransTooLarge(String str, String str2, String str3, byte[] bArr, boolean z, String str4) throws RemoteException;

    void unregisterSingleCallback(ISyncServiceCallback iSyncServiceCallback, String str, String str2) throws RemoteException;
}
