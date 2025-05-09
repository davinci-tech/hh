package com.huawei.wearengine.notify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface NotifyHanrmonyCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.notify.NotifyHanrmonyCallback";

    void onError(NotificationHarmony notificationHarmony, int i) throws RemoteException;

    void onResult(NotificationHarmony notificationHarmony, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements NotifyHanrmonyCallback {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, NotifyHanrmonyCallback.DESCRIPTOR);
        }

        public static NotifyHanrmonyCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(NotifyHanrmonyCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof NotifyHanrmonyCallback)) {
                return (NotifyHanrmonyCallback) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(NotifyHanrmonyCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(NotifyHanrmonyCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                NotificationHarmony notificationHarmony = (NotificationHarmony) b.fdI_(parcel, NotificationHarmony.CREATOR);
                onResult(notificationHarmony, parcel.readInt());
                parcel2.writeNoException();
                b.fdJ_(parcel2, notificationHarmony, 1);
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                NotificationHarmony notificationHarmony2 = (NotificationHarmony) b.fdI_(parcel, NotificationHarmony.CREATOR);
                onError(notificationHarmony2, parcel.readInt());
                parcel2.writeNoException();
                b.fdJ_(parcel2, notificationHarmony2, 1);
            }
            return true;
        }

        static class c implements NotifyHanrmonyCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f11232a;

            c(IBinder iBinder) {
                this.f11232a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11232a;
            }

            @Override // com.huawei.wearengine.notify.NotifyHanrmonyCallback
            public void onResult(NotificationHarmony notificationHarmony, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(NotifyHanrmonyCallback.DESCRIPTOR);
                    b.fdJ_(obtain, notificationHarmony, 0);
                    obtain.writeInt(i);
                    this.f11232a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        notificationHarmony.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.notify.NotifyHanrmonyCallback
            public void onError(NotificationHarmony notificationHarmony, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(NotifyHanrmonyCallback.DESCRIPTOR);
                    b.fdJ_(obtain, notificationHarmony, 0);
                    obtain.writeInt(i);
                    this.f11232a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        notificationHarmony.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fdI_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fdJ_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
