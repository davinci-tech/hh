package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.notify.NotificationHarmony;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifyHanrmonyCallback;
import com.huawei.wearengine.notify.NotifySendCallback;

/* loaded from: classes7.dex */
public interface NotifyManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.NotifyManager";

    int notify(Device device, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) throws RemoteException;

    int notifyHarmonyEx(Device device, NotificationHarmony notificationHarmony, NotifyHanrmonyCallback notifyHanrmonyCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements NotifyManager {
        static final int TRANSACTION_notify = 1;
        static final int TRANSACTION_notifyHarmonyEx = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, NotifyManager.DESCRIPTOR);
        }

        public static NotifyManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(NotifyManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof NotifyManager)) {
                return (NotifyManager) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(NotifyManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(NotifyManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int notify = notify((Device) e.fcz_(parcel, Device.CREATOR), (NotificationParcel) e.fcz_(parcel, NotificationParcel.CREATOR), NotifySendCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(notify);
            } else if (i == 2) {
                int notifyHarmonyEx = notifyHarmonyEx((Device) e.fcz_(parcel, Device.CREATOR), (NotificationHarmony) e.fcz_(parcel, NotificationHarmony.CREATOR), NotifyHanrmonyCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(notifyHarmonyEx);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class c implements NotifyManager {
            private IBinder d;

            c(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.wearengine.NotifyManager
            public int notify(Device device, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(NotifyManager.DESCRIPTOR);
                    e.fcA_(obtain, device, 0);
                    e.fcA_(obtain, notificationParcel, 0);
                    obtain.writeStrongInterface(notifySendCallback);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.NotifyManager
            public int notifyHarmonyEx(Device device, NotificationHarmony notificationHarmony, NotifyHanrmonyCallback notifyHanrmonyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(NotifyManager.DESCRIPTOR);
                    e.fcA_(obtain, device, 0);
                    e.fcA_(obtain, notificationHarmony, 0);
                    obtain.writeStrongInterface(notifyHanrmonyCallback);
                    this.d.transact(2, obtain, obtain2, 0);
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
        public static <T> T fcz_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcA_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
