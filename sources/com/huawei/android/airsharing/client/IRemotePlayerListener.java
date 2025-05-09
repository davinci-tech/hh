package com.huawei.android.airsharing.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.android.airsharing.api.TrackInfoSet;

/* loaded from: classes2.dex */
public interface IRemotePlayerListener extends IInterface {
    void onKeyRequest(String str, byte[] bArr) throws RemoteException;

    void onMediaItemChanged(PlayInfo playInfo) throws RemoteException;

    void onPlaySpeedChanged(float f) throws RemoteException;

    void onPlaySpeedListChanged(float[] fArr) throws RemoteException;

    void onPlayerError(int i, String str) throws RemoteException;

    void onPlayerStatusChanged(int i, boolean z) throws RemoteException;

    void onPositionChanged(int i, int i2, int i3) throws RemoteException;

    void onRepeatModeChanged(int i) throws RemoteException;

    void onTrackChanged(TrackInfoSet trackInfoSet) throws RemoteException;

    void onVolumeChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemotePlayerListener {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.client.IRemotePlayerListener";
        static final int TRANSACTION_onKeyRequest = 9;
        static final int TRANSACTION_onMediaItemChanged = 3;
        static final int TRANSACTION_onPlaySpeedChanged = 5;
        static final int TRANSACTION_onPlaySpeedListChanged = 10;
        static final int TRANSACTION_onPlayerError = 8;
        static final int TRANSACTION_onPlayerStatusChanged = 1;
        static final int TRANSACTION_onPositionChanged = 2;
        static final int TRANSACTION_onRepeatModeChanged = 4;
        static final int TRANSACTION_onTrackChanged = 7;
        static final int TRANSACTION_onVolumeChanged = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemotePlayerListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemotePlayerListener)) {
                return (IRemotePlayerListener) queryLocalInterface;
            }
            return new a(iBinder);
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
                    onPlayerStatusChanged(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPositionChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMediaItemChanged(parcel.readInt() != 0 ? PlayInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRepeatModeChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlaySpeedChanged(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVolumeChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTrackChanged(parcel.readInt() != 0 ? TrackInfoSet.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlayerError(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onKeyRequest(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlaySpeedListChanged(parcel.createFloatArray());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IRemotePlayerListener {
            public static IRemotePlayerListener b;
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onPlayerStatusChanged(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.d.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayerStatusChanged(i, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onPositionChanged(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (!this.d.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPositionChanged(i, i2, i3);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onMediaItemChanged(PlayInfo playInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playInfo != null) {
                        obtain.writeInt(1);
                        playInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.d.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaItemChanged(playInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onRepeatModeChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.d.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRepeatModeChanged(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onPlaySpeedChanged(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    if (!this.d.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaySpeedChanged(f);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onVolumeChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.d.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVolumeChanged(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onTrackChanged(TrackInfoSet trackInfoSet) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (trackInfoSet != null) {
                        obtain.writeInt(1);
                        trackInfoSet.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.d.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTrackChanged(trackInfoSet);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onPlayerError(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.d.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayerError(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onKeyRequest(String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    if (!this.d.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyRequest(str, bArr);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
            public void onPlaySpeedListChanged(float[] fArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloatArray(fArr);
                    if (!this.d.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaySpeedListChanged(fArr);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemotePlayerListener iRemotePlayerListener) {
            if (a.b != null || iRemotePlayerListener == null) {
                return false;
            }
            a.b = iRemotePlayerListener;
            return true;
        }

        public static IRemotePlayerListener getDefaultImpl() {
            return a.b;
        }
    }
}
