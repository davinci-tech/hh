package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hwcommonmodel.datatypes.MusicInfo;
import com.huawei.hwservicesmgr.IMusicChangedCallback;

/* loaded from: classes9.dex */
public interface IMusicControllerAIDL extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IMusicControllerAIDL";

    void controllMusic(int i) throws RemoteException;

    MusicInfo getCurrentMusicInfo() throws RemoteException;

    void initMusic() throws RemoteException;

    void remoteListener() throws RemoteException;

    void setCallback(IMusicChangedCallback iMusicChangedCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicControllerAIDL {
        static final int TRANSACTION_controllMusic = 3;
        static final int TRANSACTION_getCurrentMusicInfo = 2;
        static final int TRANSACTION_initMusic = 1;
        static final int TRANSACTION_remoteListener = 5;
        static final int TRANSACTION_setCallback = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMusicControllerAIDL.DESCRIPTOR);
        }

        public static IMusicControllerAIDL asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMusicControllerAIDL.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMusicControllerAIDL)) {
                return (IMusicControllerAIDL) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMusicControllerAIDL.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMusicControllerAIDL.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                initMusic();
                parcel2.writeNoException();
            } else if (i == 2) {
                MusicInfo currentMusicInfo = getCurrentMusicInfo();
                parcel2.writeNoException();
                b.bRJ_(parcel2, currentMusicInfo, 1);
            } else if (i == 3) {
                controllMusic(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 4) {
                setCallback(IMusicChangedCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i == 5) {
                remoteListener();
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements IMusicControllerAIDL {
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
            public void initMusic() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicControllerAIDL.DESCRIPTOR);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
            public MusicInfo getCurrentMusicInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicControllerAIDL.DESCRIPTOR);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return (MusicInfo) b.bRI_(obtain2, MusicInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
            public void controllMusic(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicControllerAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
            public void setCallback(IMusicChangedCallback iMusicChangedCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicControllerAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iMusicChangedCallback);
                    this.d.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
            public void remoteListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicControllerAIDL.DESCRIPTOR);
                    this.d.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bRI_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bRJ_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
