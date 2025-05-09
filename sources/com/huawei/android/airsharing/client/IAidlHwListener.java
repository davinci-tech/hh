package com.huawei.android.airsharing.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.airsharing.api.Event;
import com.huawei.android.airsharing.api.ProjectionDevice;

/* loaded from: classes2.dex */
public interface IAidlHwListener extends IInterface {
    int getId() throws RemoteException;

    void onDisplayUpdate(int i, String str, String str2, int i2) throws RemoteException;

    boolean onEvent(int i, String str) throws RemoteException;

    void onEventHandle(Event event) throws RemoteException;

    void onMirrorUpdate(int i, String str, String str2, int i2, boolean z) throws RemoteException;

    void onProjectionDeviceUpdate(int i, ProjectionDevice projectionDevice) throws RemoteException;

    public static abstract class Stub extends Binder implements IAidlHwListener {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.client.IAidlHwListener";
        static final int TRANSACTION_getId = 4;
        static final int TRANSACTION_onDisplayUpdate = 2;
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onEventHandle = 6;
        static final int TRANSACTION_onMirrorUpdate = 3;
        static final int TRANSACTION_onProjectionDeviceUpdate = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAidlHwListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAidlHwListener)) {
                return (IAidlHwListener) queryLocalInterface;
            }
            return new e(iBinder);
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
                    boolean onEvent = onEvent(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(onEvent ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDisplayUpdate(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMirrorUpdate(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int id = getId();
                    parcel2.writeNoException();
                    parcel2.writeInt(id);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onProjectionDeviceUpdate(parcel.readInt(), parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onEventHandle(parcel.readInt() != 0 ? Event.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class e implements IAidlHwListener {
            public static IAidlHwListener c;
            private IBinder d;

            e(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwListener
            public boolean onEvent(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.d.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onEvent(i, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwListener
            public void onDisplayUpdate(int i, String str, String str2, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i2);
                    if (!this.d.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayUpdate(i, str, str2, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwListener
            public void onMirrorUpdate(int i, String str, String str2, int i2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.d.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirrorUpdate(i, str, str2, i2, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwListener
            public int getId() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.d.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getId();
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwListener
            public void onProjectionDeviceUpdate(int i, ProjectionDevice projectionDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (projectionDevice != null) {
                        obtain.writeInt(1);
                        projectionDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.d.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionDeviceUpdate(i, projectionDevice);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwListener
            public void onEventHandle(Event event) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        obtain.writeInt(1);
                        event.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.d.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEventHandle(event);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAidlHwListener iAidlHwListener) {
            if (e.c != null || iAidlHwListener == null) {
                return false;
            }
            e.c = iAidlHwListener;
            return true;
        }

        public static IAidlHwListener getDefaultImpl() {
            return e.c;
        }
    }
}
