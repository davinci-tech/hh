package com.huawei.phdkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public interface DiscoveryListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.phdkit.DiscoveryListener";

    /* loaded from: classes9.dex */
    public static class Default implements DiscoveryListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.phdkit.DiscoveryListener
        public void onDeviceFound(List<DvLiteDevice> list) throws RemoteException {
        }
    }

    void onDeviceFound(List<DvLiteDevice> list) throws RemoteException;

    public static abstract class Stub extends Binder implements DiscoveryListener {
        static final int TRANSACTION_onDeviceFound = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DiscoveryListener.DESCRIPTOR);
        }

        public static DiscoveryListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DiscoveryListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DiscoveryListener)) {
                return (DiscoveryListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DiscoveryListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DiscoveryListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                ArrayList createTypedArrayList = parcel.createTypedArrayList(DvLiteDevice.CREATOR);
                onDeviceFound(createTypedArrayList);
                parcel2.writeNoException();
                a.ccH_(parcel2, createTypedArrayList, 1);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements DiscoveryListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.phdkit.DiscoveryListener
            public void onDeviceFound(List<DvLiteDevice> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DiscoveryListener.DESCRIPTOR);
                    a.ccH_(obtain, list, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    obtain2.readTypedList(list, DvLiteDevice.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return DiscoveryListener.DESCRIPTOR;
            }
        }
    }

    public static class a {
        private static <T extends Parcelable> void ccI_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ccH_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                ccI_(parcel, list.get(i2), i);
            }
        }
    }
}
