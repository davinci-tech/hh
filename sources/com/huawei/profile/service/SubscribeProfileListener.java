package com.huawei.profile.service;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ParcelableDeviceProfileChange;
import com.huawei.profile.profile.SubscribeInfo;
import com.huawei.profile.subscription.event.EventInfo;
import java.util.List;

/* loaded from: classes9.dex */
public interface SubscribeProfileListener extends IInterface {

    public static class Default implements SubscribeProfileListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.profile.service.SubscribeProfileListener
        public void onNotifyDeviceListChange(int i, List<DeviceProfile> list, Bundle bundle) throws RemoteException {
        }

        @Override // com.huawei.profile.service.SubscribeProfileListener
        public void onNotifyDeviceListInfoChange(int i, List<DeviceProfile> list, Bundle bundle) throws RemoteException {
        }

        @Override // com.huawei.profile.service.SubscribeProfileListener
        public void onNotifyDeviceProfileChange(SubscribeInfo subscribeInfo, Bundle bundle) throws RemoteException {
        }

        @Override // com.huawei.profile.service.SubscribeProfileListener
        public void onNotifyDeviceProfileChangeList(com.huawei.profile.subscription.deviceinfo.SubscribeInfo subscribeInfo, List<ParcelableDeviceProfileChange> list, Bundle bundle) throws RemoteException {
        }

        @Override // com.huawei.profile.service.SubscribeProfileListener
        public void onNotifyEvent(EventInfo eventInfo, String str) throws RemoteException {
        }
    }

    void onNotifyDeviceListChange(int i, List<DeviceProfile> list, Bundle bundle) throws RemoteException;

    void onNotifyDeviceListInfoChange(int i, List<DeviceProfile> list, Bundle bundle) throws RemoteException;

    void onNotifyDeviceProfileChange(SubscribeInfo subscribeInfo, Bundle bundle) throws RemoteException;

    void onNotifyDeviceProfileChangeList(com.huawei.profile.subscription.deviceinfo.SubscribeInfo subscribeInfo, List<ParcelableDeviceProfileChange> list, Bundle bundle) throws RemoteException;

    void onNotifyEvent(EventInfo eventInfo, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements SubscribeProfileListener {
        private static final String DESCRIPTOR = "com.huawei.profile.service.SubscribeProfileListener";
        static final int TRANSACTION_onNotifyDeviceListChange = 2;
        static final int TRANSACTION_onNotifyDeviceListInfoChange = 4;
        static final int TRANSACTION_onNotifyDeviceProfileChange = 1;
        static final int TRANSACTION_onNotifyDeviceProfileChangeList = 3;
        static final int TRANSACTION_onNotifyEvent = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static SubscribeProfileListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof SubscribeProfileListener)) {
                return (SubscribeProfileListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onNotifyDeviceProfileChange(parcel.readInt() != 0 ? SubscribeInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onNotifyDeviceListChange(parcel.readInt(), parcel.createTypedArrayList(DeviceProfile.CREATOR), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onNotifyDeviceProfileChangeList(parcel.readInt() != 0 ? com.huawei.profile.subscription.deviceinfo.SubscribeInfo.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(ParcelableDeviceProfileChange.CREATOR), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onNotifyDeviceListInfoChange(parcel.readInt(), parcel.createTypedArrayList(DeviceProfile.CREATOR), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onNotifyEvent(parcel.readInt() != 0 ? EventInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements SubscribeProfileListener {
            public static SubscribeProfileListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceProfileChange(SubscribeInfo subscribeInfo, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (subscribeInfo != null) {
                        obtain.writeInt(1);
                        subscribeInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyDeviceProfileChange(subscribeInfo, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceListChange(int i, List<DeviceProfile> list, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyDeviceListChange(i, list, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceProfileChangeList(com.huawei.profile.subscription.deviceinfo.SubscribeInfo subscribeInfo, List<ParcelableDeviceProfileChange> list, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (subscribeInfo != null) {
                        obtain.writeInt(1);
                        subscribeInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyDeviceProfileChangeList(subscribeInfo, list, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceListInfoChange(int i, List<DeviceProfile> list, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyDeviceListInfoChange(i, list, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyEvent(EventInfo eventInfo, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (eventInfo != null) {
                        obtain.writeInt(1);
                        eventInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotifyEvent(eventInfo, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(SubscribeProfileListener subscribeProfileListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (subscribeProfileListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = subscribeProfileListener;
            return true;
        }

        public static SubscribeProfileListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
