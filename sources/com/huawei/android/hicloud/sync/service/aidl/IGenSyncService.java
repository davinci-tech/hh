package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import java.util.List;

/* loaded from: classes8.dex */
public interface IGenSyncService extends IInterface {

    public static abstract class Stub extends Binder implements IGenSyncService {
        private static final String DESCRIPTOR = "com.huawei.android.hicloud.sync.service.aidl.IGenSyncService";
        static final int TRANSACTION_downUnstructFile = 12;
        static final int TRANSACTION_downUnstructFileForTransTooLarge = 13;
        static final int TRANSACTION_endSync = 14;
        static final int TRANSACTION_getHisyncNewVersion = 3;
        static final int TRANSACTION_getStructData = 5;
        static final int TRANSACTION_getStructDataForTransTooLarge = 8;
        static final int TRANSACTION_getStructDataNum = 15;
        static final int TRANSACTION_getSyncLostList = 16;
        static final int TRANSACTION_notifyMsg = 20;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_reportInfo = 9;
        static final int TRANSACTION_reportSyncInfo = 19;
        static final int TRANSACTION_saveSyncResult = 6;
        static final int TRANSACTION_saveSyncResultForTransTooLarge = 7;
        static final int TRANSACTION_startSync = 4;
        static final int TRANSACTION_unregisterCallback = 2;
        static final int TRANSACTION_uploadData = 10;
        static final int TRANSACTION_uploadDataForTransTooLarge = 11;
        static final int TRANSACTION_uploadDataWithLost = 17;
        static final int TRANSACTION_uploadDataWithLostForTransTooLarge = 18;

        static class d implements IGenSyncService {
            public static IGenSyncService d;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1836a;

            d(IBinder iBinder) {
                this.f1836a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1836a;
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void downUnstructFile(String str, String str2, List<UnstructData> list, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    if (this.f1836a.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().downUnstructFile(str, str2, list, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void downUnstructFileForTransTooLarge(String str, String str2, byte[] bArr, boolean z, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (this.f1836a.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().downUnstructFileForTransTooLarge(str, str2, bArr, z, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void endSync(String str, String str2, List<String> list, List<String> list2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    obtain.writeStringList(list2);
                    if (this.f1836a.transact(14, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().endSync(str, str2, list, list2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public int getHisyncNewVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1836a.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHisyncNewVersion();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void getStructData(String str, String str2, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    if (this.f1836a.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getStructData(str, str2, list);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void getStructDataForTransTooLarge(String str, String str2, byte[] bArr, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.f1836a.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getStructDataForTransTooLarge(str, str2, bArr, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void getStructDataNum(String str, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    if (this.f1836a.transact(15, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getStructDataNum(str, list);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void getSyncLostList(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.f1836a.transact(16, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getSyncLostList(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void notifyMsg(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (this.f1836a.transact(20, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().notifyMsg(str, str2, str3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public boolean registerCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    if (!this.f1836a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(iSyncServiceCallback, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void reportInfo(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.f1836a.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportInfo(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void reportSyncInfo(String str, String str2, String str3, String str4, String str5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    obtain.writeString(str5);
                    if (this.f1836a.transact(19, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportSyncInfo(str, str2, str3, str4, str5);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void saveSyncResult(String str, String str2, List<SyncData> list, List<String> list2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeStringList(list2);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.f1836a.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().saveSyncResult(str, str2, list, list2, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void saveSyncResultForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (this.f1836a.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().saveSyncResultForTransTooLarge(str, str2, bArr, z, z2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void startSync(String str, String str2, String str3, List<CtagInfoCompatible> list, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    try {
                        if (this.f1836a.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().startSync(str, str2, str3, list, i, i2, i3);
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

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void unregisterCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    if (this.f1836a.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallback(iSyncServiceCallback, str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void uploadData(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<UnstructData> list3, List<String> list4, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    obtain.writeTypedList(list3);
                    obtain.writeStringList(list4);
                    obtain.writeInt(z ? 1 : 0);
                    try {
                        if (this.f1836a.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().uploadData(str, str2, list, list2, list3, list4, z);
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

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void uploadDataForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (this.f1836a.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().uploadDataForTransTooLarge(str, str2, bArr, z, z2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void uploadDataWithLost(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<String> list3, List<SyncDataCompatible> list4, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    obtain.writeStringList(list3);
                    obtain.writeTypedList(list4);
                    obtain.writeInt(z ? 1 : 0);
                    try {
                        if (this.f1836a.transact(17, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().uploadDataWithLost(str, str2, list, list2, list3, list4, z);
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

            @Override // com.huawei.android.hicloud.sync.service.aidl.IGenSyncService
            public void uploadDataWithLostForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (this.f1836a.transact(18, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().uploadDataWithLostForTransTooLarge(str, str2, bArr, z, z2);
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

        public static IGenSyncService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGenSyncService)) ? new d(iBinder) : (IGenSyncService) queryLocalInterface;
        }

        public static IGenSyncService getDefaultImpl() {
            return d.d;
        }

        public static boolean setDefaultImpl(IGenSyncService iGenSyncService) {
            if (d.d != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGenSyncService == null) {
                return false;
            }
            d.d = iGenSyncService;
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
                    boolean registerCallback = registerCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerCallback ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hisyncNewVersion = getHisyncNewVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(hisyncNewVersion);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSync(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createTypedArrayList(CtagInfoCompatible.CREATOR), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    getStructData(parcel.readString(), parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveSyncResult(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncData.CREATOR), parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveSyncResultForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getStructDataForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportInfo(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadData(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    downUnstructFile(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    downUnstructFileForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    endSync(parcel.readString(), parcel.readString(), parcel.createStringArrayList(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    getStructDataNum(parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    getSyncLostList(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataWithLost(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createStringArrayList(), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataWithLostForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportSyncInfo(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyMsg(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void downUnstructFile(String str, String str2, List<UnstructData> list, int i) throws RemoteException;

    void downUnstructFileForTransTooLarge(String str, String str2, byte[] bArr, boolean z, int i) throws RemoteException;

    void endSync(String str, String str2, List<String> list, List<String> list2) throws RemoteException;

    int getHisyncNewVersion() throws RemoteException;

    void getStructData(String str, String str2, List<String> list) throws RemoteException;

    void getStructDataForTransTooLarge(String str, String str2, byte[] bArr, boolean z) throws RemoteException;

    void getStructDataNum(String str, List<String> list) throws RemoteException;

    void getSyncLostList(String str, String str2) throws RemoteException;

    void notifyMsg(String str, String str2, String str3) throws RemoteException;

    boolean registerCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException;

    void reportInfo(String str, String str2) throws RemoteException;

    void reportSyncInfo(String str, String str2, String str3, String str4, String str5) throws RemoteException;

    void saveSyncResult(String str, String str2, List<SyncData> list, List<String> list2, boolean z) throws RemoteException;

    void saveSyncResultForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException;

    void startSync(String str, String str2, String str3, List<CtagInfoCompatible> list, int i, int i2, int i3) throws RemoteException;

    void unregisterCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException;

    void uploadData(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<UnstructData> list3, List<String> list4, boolean z) throws RemoteException;

    void uploadDataForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException;

    void uploadDataWithLost(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<String> list3, List<SyncDataCompatible> list4, boolean z) throws RemoteException;

    void uploadDataWithLostForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException;
}
