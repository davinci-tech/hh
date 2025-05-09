package com.huawei.hms.feature.dynamic;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface IObjectWrapper extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.feature.dynamic.IObjectWrapper";

    /* loaded from: classes9.dex */
    public static class Default implements IObjectWrapper {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IObjectWrapper {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static class Proxy implements IObjectWrapper {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4504a;

            public String getInterfaceDescriptor() {
                return IObjectWrapper.DESCRIPTOR;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4504a;
            }

            public Proxy(IBinder iBinder) {
                this.f4504a = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString(IObjectWrapper.DESCRIPTOR);
            return true;
        }

        public static IObjectWrapper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IObjectWrapper.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IObjectWrapper)) ? new Proxy(iBinder) : (IObjectWrapper) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, IObjectWrapper.DESCRIPTOR);
        }
    }
}
