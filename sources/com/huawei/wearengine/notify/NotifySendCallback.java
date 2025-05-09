package com.huawei.wearengine.notify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface NotifySendCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.notify.NotifySendCallback";

    /* loaded from: classes9.dex */
    public static class Default implements NotifySendCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.notify.NotifySendCallback
        public void onError(NotificationParcel notificationParcel, int i) throws RemoteException {
        }

        @Override // com.huawei.wearengine.notify.NotifySendCallback
        public void onResult(NotificationParcel notificationParcel, int i) throws RemoteException {
        }
    }

    void onError(NotificationParcel notificationParcel, int i) throws RemoteException;

    void onResult(NotificationParcel notificationParcel, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements NotifySendCallback {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, NotifySendCallback.DESCRIPTOR);
        }

        public static NotifySendCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(NotifySendCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof NotifySendCallback)) {
                return (NotifySendCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(NotifySendCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(NotifySendCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                NotificationParcel notificationParcel = (NotificationParcel) a.fdM_(parcel, NotificationParcel.CREATOR);
                onResult(notificationParcel, parcel.readInt());
                parcel2.writeNoException();
                a.fdN_(parcel2, notificationParcel, 1);
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                NotificationParcel notificationParcel2 = (NotificationParcel) a.fdM_(parcel, NotificationParcel.CREATOR);
                onError(notificationParcel2, parcel.readInt());
                parcel2.writeNoException();
                a.fdN_(parcel2, notificationParcel2, 1);
            }
            return true;
        }

        static class Proxy implements NotifySendCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.notify.NotifySendCallback
            public void onResult(NotificationParcel notificationParcel, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(NotifySendCallback.DESCRIPTOR);
                    a.fdN_(obtain, notificationParcel, 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        notificationParcel.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.notify.NotifySendCallback
            public void onError(NotificationParcel notificationParcel, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(NotifySendCallback.DESCRIPTOR);
                    a.fdN_(obtain, notificationParcel, 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        notificationParcel.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return NotifySendCallback.DESCRIPTOR;
            }
        }
    }

    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fdM_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fdN_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
