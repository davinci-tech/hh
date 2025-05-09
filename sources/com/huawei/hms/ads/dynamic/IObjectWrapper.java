package com.huawei.hms.ads.dynamic;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface IObjectWrapper extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.ads.dynamic.IObjectWrapper";

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

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString(IObjectWrapper.DESCRIPTOR);
            return true;
        }

        static class Proxy implements IObjectWrapper {
            public static IObjectWrapper sDefaultImpl;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4300a;

            public String getInterfaceDescriptor() {
                return IObjectWrapper.DESCRIPTOR;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4300a;
            }

            Proxy(IBinder iBinder) {
                this.f4300a = iBinder;
            }
        }

        public static boolean setDefaultImpl(IObjectWrapper iObjectWrapper) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iObjectWrapper == null) {
                return false;
            }
            Proxy.sDefaultImpl = iObjectWrapper;
            return true;
        }

        public static IObjectWrapper getDefaultImpl() {
            return Proxy.sDefaultImpl;
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
