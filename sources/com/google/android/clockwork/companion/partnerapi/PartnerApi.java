package com.google.android.clockwork.companion.partnerapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.clockwork.companion.partnerapi.BatchSetAppNotificationConfigsCallback;
import com.google.android.clockwork.companion.partnerapi.DeviceRemovalCallback;
import java.util.List;

/* loaded from: classes2.dex */
public interface PartnerApi extends IInterface {
    public static final String DESCRIPTOR = "com.google.android.clockwork.companion.partnerapi.PartnerApi";

    int batchSetAppNotificationConfigs(List<AppNotificationConfig> list, BatchSetAppNotificationConfigsCallback batchSetAppNotificationConfigsCallback) throws RemoteException;

    List<AppNotificationConfig> getAppNotificationConfigs() throws RemoteException;

    boolean reconnectByNodeId(String str) throws RemoteException;

    boolean removeDeviceByNodeId(String str, DeviceRemovalCallback deviceRemovalCallback) throws RemoteException;

    int setAppNotificationConfig(AppNotificationConfig appNotificationConfig) throws RemoteException;

    public static abstract class Stub extends Binder implements PartnerApi {
        static final int TRANSACTION_batchSetAppNotificationConfigs = 5;
        static final int TRANSACTION_getAppNotificationConfigs = 3;
        static final int TRANSACTION_reconnectByNodeId = 1;
        static final int TRANSACTION_removeDeviceByNodeId = 2;
        static final int TRANSACTION_setAppNotificationConfig = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, PartnerApi.DESCRIPTOR);
        }

        public static PartnerApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(PartnerApi.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof PartnerApi)) {
                return (PartnerApi) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(PartnerApi.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(PartnerApi.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                boolean reconnectByNodeId = reconnectByNodeId(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(reconnectByNodeId ? 1 : 0);
            } else if (i == 2) {
                boolean removeDeviceByNodeId = removeDeviceByNodeId(parcel.readString(), DeviceRemovalCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(removeDeviceByNodeId ? 1 : 0);
            } else if (i == 3) {
                List<AppNotificationConfig> appNotificationConfigs = getAppNotificationConfigs();
                parcel2.writeNoException();
                e.bL_(parcel2, appNotificationConfigs, 1);
            } else if (i == 4) {
                int appNotificationConfig = setAppNotificationConfig((AppNotificationConfig) e.bK_(parcel, AppNotificationConfig.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(appNotificationConfig);
            } else if (i == 5) {
                int batchSetAppNotificationConfigs = batchSetAppNotificationConfigs(parcel.createTypedArrayList(AppNotificationConfig.CREATOR), BatchSetAppNotificationConfigsCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(batchSetAppNotificationConfigs);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements PartnerApi {
            private IBinder e;

            a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.google.android.clockwork.companion.partnerapi.PartnerApi
            public boolean reconnectByNodeId(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(PartnerApi.DESCRIPTOR);
                    obtain.writeString(str);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.google.android.clockwork.companion.partnerapi.PartnerApi
            public boolean removeDeviceByNodeId(String str, DeviceRemovalCallback deviceRemovalCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(PartnerApi.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(deviceRemovalCallback);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.google.android.clockwork.companion.partnerapi.PartnerApi
            public List<AppNotificationConfig> getAppNotificationConfigs() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(PartnerApi.DESCRIPTOR);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(AppNotificationConfig.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.google.android.clockwork.companion.partnerapi.PartnerApi
            public int setAppNotificationConfig(AppNotificationConfig appNotificationConfig) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(PartnerApi.DESCRIPTOR);
                    e.bM_(obtain, appNotificationConfig, 0);
                    this.e.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.google.android.clockwork.companion.partnerapi.PartnerApi
            public int batchSetAppNotificationConfigs(List<AppNotificationConfig> list, BatchSetAppNotificationConfigsCallback batchSetAppNotificationConfigsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(PartnerApi.DESCRIPTOR);
                    e.bL_(obtain, list, 0);
                    obtain.writeStrongInterface(batchSetAppNotificationConfigsCallback);
                    this.e.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bK_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bM_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bL_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bM_(parcel, list.get(i2), i);
            }
        }
    }
}
