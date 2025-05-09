package com.huawei.wearkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes8.dex */
public interface IDetectCommonCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearkit.IDetectCommonCallback";

    void onResult(int i, List list, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IDetectCommonCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDetectCommonCallback.DESCRIPTOR);
        }

        public static IDetectCommonCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDetectCommonCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDetectCommonCallback)) {
                return (IDetectCommonCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDetectCommonCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDetectCommonCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readArrayList(getClass().getClassLoader()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IDetectCommonCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f11249a;

            a(IBinder iBinder) {
                this.f11249a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11249a;
            }

            @Override // com.huawei.wearkit.IDetectCommonCallback
            public void onResult(int i, List list, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDetectCommonCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeList(list);
                    obtain.writeString(str);
                    this.f11249a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
