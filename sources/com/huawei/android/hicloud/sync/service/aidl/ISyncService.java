package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import java.util.List;

/* loaded from: classes8.dex */
public interface ISyncService extends IInterface {

    public static abstract class Stub extends Binder implements ISyncService {
        private static final String DESCRIPTOR = "com.huawei.android.hicloud.sync.service.aidl.ISyncService";
        static final int TRANSACTION_checkRisk = 27;
        static final int TRANSACTION_doAutoSyncContact = 1;
        static final int TRANSACTION_doAutoSyncWlan = 2;
        static final int TRANSACTION_downUnstructFile = 10;
        static final int TRANSACTION_downUnstructFileForTransTooLarge = 18;
        static final int TRANSACTION_endSync = 9;
        static final int TRANSACTION_endSyncForTransTooLarge = 17;
        static final int TRANSACTION_endSyncV100 = 21;
        static final int TRANSACTION_getExceedLimitNum = 31;
        static final int TRANSACTION_getHisyncNewVersion = 20;
        static final int TRANSACTION_getHisyncVersionCode = 19;
        static final int TRANSACTION_getStructData = 5;
        static final int TRANSACTION_getStructDataForTransTooLarge = 15;
        static final int TRANSACTION_getSyncLostList = 32;
        static final int TRANSACTION_notifyMsg = 37;
        static final int TRANSACTION_notifySyncModel = 36;
        static final int TRANSACTION_registerCallback = 3;
        static final int TRANSACTION_reportEvent = 26;
        static final int TRANSACTION_reportInfo = 29;
        static final int TRANSACTION_reportSDKVersionCode = 12;
        static final int TRANSACTION_reportSyncInfo = 35;
        static final int TRANSACTION_reportSyncRsn = 28;
        static final int TRANSACTION_saveSyncResult = 7;
        static final int TRANSACTION_saveSyncResultForTransTooLarge = 14;
        static final int TRANSACTION_startSync = 6;
        static final int TRANSACTION_startSyncForTransTooLarge = 16;
        static final int TRANSACTION_startSyncV100 = 22;
        static final int TRANSACTION_startSyncV101 = 23;
        static final int TRANSACTION_unregisterCallback = 4;
        static final int TRANSACTION_updateCtag = 11;
        static final int TRANSACTION_uploadData = 8;
        static final int TRANSACTION_uploadDataForTransTooLarge = 13;
        static final int TRANSACTION_uploadDataV102 = 24;
        static final int TRANSACTION_uploadDataV102ForTransTooLarge = 25;
        static final int TRANSACTION_uploadDataV104 = 30;
        static final int TRANSACTION_uploadDataWithLost = 33;
        static final int TRANSACTION_uploadDataWithLostForTransTooLarge = 34;

        static class e implements ISyncService {
            public static ISyncService d;
            private IBinder b;

            e(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void checkRisk(String str, String str2, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (this.b.transact(27, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().checkRisk(str, str2, i, i2, i3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public int doAutoSyncContact(SyncType syncType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (syncType != null) {
                        obtain.writeInt(1);
                        syncType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doAutoSyncContact(syncType);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public int doAutoSyncWlan(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.b.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doAutoSyncWlan(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void downUnstructFile(String str, String str2, List<UnstructData> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (this.b.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().downUnstructFile(str, str2, list);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void downUnstructFileForTransTooLarge(String str, String str2, byte[] bArr, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.b.transact(18, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().downUnstructFileForTransTooLarge(str, str2, bArr, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void endSync(String str, List<String> list, List<String> list2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStringList(list2);
                    if (this.b.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().endSync(str, list, list2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void endSyncForTransTooLarge(String str, byte[] bArr, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.b.transact(17, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().endSyncForTransTooLarge(str, bArr, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void endSyncV100(String str, List<String> list, List<String> list2, List<String> list3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStringList(list2);
                    obtain.writeStringList(list3);
                    if (this.b.transact(21, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().endSyncV100(str, list, list2, list3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void getExceedLimitNum(String str, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    if (this.b.transact(31, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getExceedLimitNum(str, list);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public int getHisyncNewVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.b.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHisyncNewVersion();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public int getHisyncVersionCode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.b.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHisyncVersionCode();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void getStructData(String str, String str2, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    if (this.b.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getStructData(str, str2, list);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void getStructDataForTransTooLarge(String str, String str2, byte[] bArr, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.b.transact(15, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getStructDataForTransTooLarge(str, str2, bArr, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void getSyncLostList(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.b.transact(32, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getSyncLostList(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void notifyMsg(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (this.b.transact(37, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().notifyMsg(str, str2, str3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void notifySyncModel(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (this.b.transact(36, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().notifySyncModel(str, str2, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public boolean registerCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    if (!this.b.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(iSyncServiceCallback, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void reportEvent(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.b.transact(26, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportEvent(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void reportInfo(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.b.transact(29, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportInfo(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void reportSDKVersionCode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.b.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportSDKVersionCode(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
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
                    if (this.b.transact(35, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportSyncInfo(str, str2, str3, str4, str5);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void reportSyncRsn(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (this.b.transact(28, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reportSyncRsn(str, str2, str3, str4);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
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
                    if (this.b.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().saveSyncResult(str, str2, list, list2, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
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
                    if (this.b.transact(14, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().saveSyncResultForTransTooLarge(str, str2, bArr, z, z2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void startSync(String str, String str2, List<LocalId> list, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    if (this.b.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().startSync(str, str2, list, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void startSyncForTransTooLarge(String str, String str2, byte[] bArr, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.b.transact(16, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().startSyncForTransTooLarge(str, str2, bArr, i, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void startSyncV100(String str, String str2, String str3, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    if (this.b.transact(22, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().startSyncV100(str, str2, str3, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void startSyncV101(String str, String str2, List<CtagInfoCompatible> list, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    if (this.b.transact(23, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().startSyncV101(str, str2, list, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void unregisterCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSyncServiceCallback != null ? iSyncServiceCallback.asBinder() : null);
                    obtain.writeString(str);
                    if (this.b.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallback(iSyncServiceCallback, str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void updateCtag(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.b.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().updateCtag(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void uploadData(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    obtain.writeStringList(list3);
                    obtain.writeInt(z ? 1 : 0);
                    try {
                        if (this.b.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().uploadData(str, str2, list, list2, list3, z);
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

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
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
                    if (this.b.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().uploadDataForTransTooLarge(str, str2, bArr, z, z2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void uploadDataV102(String str, String str2, List<SyncData> list, List<SyncData> list2, List<UnstructData> list3, List<String> list4, boolean z) throws RemoteException {
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
                        if (this.b.transact(24, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().uploadDataV102(str, str2, list, list2, list3, list4, z);
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

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void uploadDataV102ForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (this.b.transact(25, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().uploadDataV102ForTransTooLarge(str, str2, bArr, z, z2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
            public void uploadDataV104(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<UnstructData> list3, List<String> list4, boolean z) throws RemoteException {
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
                        if (this.b.transact(30, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                        } else {
                            Stub.getDefaultImpl().uploadDataV104(str, str2, list, list2, list3, list4, z);
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

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
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
                        if (this.b.transact(33, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
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

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncService
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
                    if (this.b.transact(34, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
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

        public static ISyncService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISyncService)) ? new e(iBinder) : (ISyncService) queryLocalInterface;
        }

        public static ISyncService getDefaultImpl() {
            return e.d;
        }

        public static boolean setDefaultImpl(ISyncService iSyncService) {
            if (e.d != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSyncService == null) {
                return false;
            }
            e.d = iSyncService;
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
                    int doAutoSyncContact = doAutoSyncContact(parcel.readInt() != 0 ? SyncType.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(doAutoSyncContact);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int doAutoSyncWlan = doAutoSyncWlan(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(doAutoSyncWlan);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerCallback = registerCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(ISyncServiceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    getStructData(parcel.readString(), parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSync(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(LocalId.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveSyncResult(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncData.CREATOR), parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadData(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncData.CREATOR), parcel.createTypedArrayList(SyncData.CREATOR), parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    endSync(parcel.readString(), parcel.createStringArrayList(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    downUnstructFile(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(UnstructData.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCtag(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportSDKVersionCode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveSyncResultForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    getStructDataForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSyncForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    endSyncForTransTooLarge(parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    downUnstructFileForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hisyncVersionCode = getHisyncVersionCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(hisyncVersionCode);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hisyncNewVersion = getHisyncNewVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(hisyncNewVersion);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    endSyncV100(parcel.readString(), parcel.createStringArrayList(), parcel.createStringArrayList(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSyncV100(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSyncV101(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(CtagInfoCompatible.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataV102(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncData.CREATOR), parcel.createTypedArrayList(SyncData.CREATOR), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataV102ForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportEvent(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    checkRisk(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportSyncRsn(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportInfo(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataV104(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createTypedArrayList(UnstructData.CREATOR), parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    getExceedLimitNum(parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    getSyncLostList(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataWithLost(parcel.readString(), parcel.readString(), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.createStringArrayList(), parcel.createTypedArrayList(SyncDataCompatible.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadDataWithLostForTransTooLarge(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportSyncInfo(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySyncModel(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyMsg(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void checkRisk(String str, String str2, int i, int i2, int i3) throws RemoteException;

    int doAutoSyncContact(SyncType syncType) throws RemoteException;

    int doAutoSyncWlan(int i) throws RemoteException;

    void downUnstructFile(String str, String str2, List<UnstructData> list) throws RemoteException;

    void downUnstructFileForTransTooLarge(String str, String str2, byte[] bArr, boolean z) throws RemoteException;

    void endSync(String str, List<String> list, List<String> list2) throws RemoteException;

    void endSyncForTransTooLarge(String str, byte[] bArr, boolean z) throws RemoteException;

    void endSyncV100(String str, List<String> list, List<String> list2, List<String> list3) throws RemoteException;

    void getExceedLimitNum(String str, List<String> list) throws RemoteException;

    int getHisyncNewVersion() throws RemoteException;

    int getHisyncVersionCode() throws RemoteException;

    void getStructData(String str, String str2, List<String> list) throws RemoteException;

    void getStructDataForTransTooLarge(String str, String str2, byte[] bArr, boolean z) throws RemoteException;

    void getSyncLostList(String str, String str2) throws RemoteException;

    void notifyMsg(String str, String str2, String str3) throws RemoteException;

    void notifySyncModel(String str, String str2, int i) throws RemoteException;

    boolean registerCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException;

    void reportEvent(String str, String str2) throws RemoteException;

    void reportInfo(String str, String str2) throws RemoteException;

    void reportSDKVersionCode(int i) throws RemoteException;

    void reportSyncInfo(String str, String str2, String str3, String str4, String str5) throws RemoteException;

    void reportSyncRsn(String str, String str2, String str3, String str4) throws RemoteException;

    void saveSyncResult(String str, String str2, List<SyncData> list, List<String> list2, boolean z) throws RemoteException;

    void saveSyncResultForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException;

    void startSync(String str, String str2, List<LocalId> list, int i) throws RemoteException;

    void startSyncForTransTooLarge(String str, String str2, byte[] bArr, int i, boolean z) throws RemoteException;

    void startSyncV100(String str, String str2, String str3, int i) throws RemoteException;

    void startSyncV101(String str, String str2, List<CtagInfoCompatible> list, int i) throws RemoteException;

    void unregisterCallback(ISyncServiceCallback iSyncServiceCallback, String str) throws RemoteException;

    void updateCtag(String str, String str2) throws RemoteException;

    void uploadData(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z) throws RemoteException;

    void uploadDataForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException;

    void uploadDataV102(String str, String str2, List<SyncData> list, List<SyncData> list2, List<UnstructData> list3, List<String> list4, boolean z) throws RemoteException;

    void uploadDataV102ForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException;

    void uploadDataV104(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<UnstructData> list3, List<String> list4, boolean z) throws RemoteException;

    void uploadDataWithLost(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<String> list3, List<SyncDataCompatible> list4, boolean z) throws RemoteException;

    void uploadDataWithLostForTransTooLarge(String str, String str2, byte[] bArr, boolean z, boolean z2) throws RemoteException;
}
