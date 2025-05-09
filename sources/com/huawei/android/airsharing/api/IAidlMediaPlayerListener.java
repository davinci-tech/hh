package com.huawei.android.airsharing.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IAidlMediaPlayerListener extends IInterface {
    void onBufferedPositionChanged(int i) throws RemoteException;

    void onError(NotificationInfo notificationInfo) throws RemoteException;

    void onMediaItemChanged(NotificationInfo notificationInfo) throws RemoteException;

    void onPlayInfoUpdated(String str, NotificationInfo notificationInfo) throws RemoteException;

    void onPositionChanged(int i) throws RemoteException;

    void onRateChanged(float f) throws RemoteException;

    void onRepeatModeChanged(String str) throws RemoteException;

    void onStateChanged(NotificationInfo notificationInfo) throws RemoteException;

    void onVolumeChanged(int i) throws RemoteException;

    void onVolumeMutedChanged(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IAidlMediaPlayerListener {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.api.IAidlMediaPlayerListener";
        static final int TRANSACTION_onBufferedPositionChanged = 8;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onMediaItemChanged = 2;
        static final int TRANSACTION_onPlayInfoUpdated = 10;
        static final int TRANSACTION_onPositionChanged = 5;
        static final int TRANSACTION_onRateChanged = 6;
        static final int TRANSACTION_onRepeatModeChanged = 7;
        static final int TRANSACTION_onStateChanged = 1;
        static final int TRANSACTION_onVolumeChanged = 4;
        static final int TRANSACTION_onVolumeMutedChanged = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAidlMediaPlayerListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAidlMediaPlayerListener)) {
                return (IAidlMediaPlayerListener) queryLocalInterface;
            }
            return new d(iBinder);
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
                    onStateChanged(parcel.readInt() != 0 ? NotificationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMediaItemChanged(parcel.readInt() != 0 ? NotificationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onError(parcel.readInt() != 0 ? NotificationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPositionChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRateChanged(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRepeatModeChanged(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBufferedPositionChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeMutedChanged(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlayInfoUpdated(parcel.readString(), parcel.readInt() != 0 ? NotificationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class d implements IAidlMediaPlayerListener {
            public static IAidlMediaPlayerListener b;
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onStateChanged(NotificationInfo notificationInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationInfo != null) {
                        obtain.writeInt(1);
                        notificationInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStateChanged(notificationInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onMediaItemChanged(NotificationInfo notificationInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationInfo != null) {
                        obtain.writeInt(1);
                        notificationInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaItemChanged(notificationInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onError(NotificationInfo notificationInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationInfo != null) {
                        obtain.writeInt(1);
                        notificationInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(notificationInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onVolumeChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVolumeChanged(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onPositionChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPositionChanged(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onRateChanged(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (!this.c.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRateChanged(f);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onRepeatModeChanged(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.c.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRepeatModeChanged(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onBufferedPositionChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.c.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBufferedPositionChanged(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onVolumeMutedChanged(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.c.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVolumeMutedChanged(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.api.IAidlMediaPlayerListener
            public void onPlayInfoUpdated(String str, NotificationInfo notificationInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (notificationInfo != null) {
                        obtain.writeInt(1);
                        notificationInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.c.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayInfoUpdated(str, notificationInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAidlMediaPlayerListener iAidlMediaPlayerListener) {
            if (d.b != null || iAidlMediaPlayerListener == null) {
                return false;
            }
            d.b = iAidlMediaPlayerListener;
            return true;
        }

        public static IAidlMediaPlayerListener getDefaultImpl() {
            return d.b;
        }
    }
}
