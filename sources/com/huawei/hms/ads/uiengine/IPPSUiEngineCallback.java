package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public interface IPPSUiEngineCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.ads.uiengine.IPPSUiEngineCallback";

    void onCallResult(String str, Bundle bundle);

    public static abstract class a extends Binder implements IPPSUiEngineCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString(IPPSUiEngineCallback.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(IPPSUiEngineCallback.DESCRIPTOR);
            onCallResult(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* renamed from: com.huawei.hms.ads.uiengine.IPPSUiEngineCallback$a$a, reason: collision with other inner class name */
        static class C0083a implements IPPSUiEngineCallback {

            /* renamed from: a, reason: collision with root package name */
            public static IPPSUiEngineCallback f4347a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.IPPSUiEngineCallback
            public void onCallResult(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPPSUiEngineCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onCallResult(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            C0083a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        public static IPPSUiEngineCallback a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IPPSUiEngineCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IPPSUiEngineCallback)) ? new C0083a(iBinder) : (IPPSUiEngineCallback) queryLocalInterface;
        }

        public static IPPSUiEngineCallback a() {
            return C0083a.f4347a;
        }

        public a() {
            attachInterface(this, IPPSUiEngineCallback.DESCRIPTOR);
        }
    }
}
