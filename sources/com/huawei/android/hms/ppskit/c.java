package com.huawei.android.hms.ppskit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface c extends IInterface {
    void a(boolean z, int i);

    public static abstract class a extends Binder implements c {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.android.hms.ppskit.IPPSInstallationServiceCallback");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSInstallationServiceCallback");
            a(parcel.readInt() != 0, parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        static class b implements c {
            public static c d;
            private IBinder b;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.android.hms.ppskit.c
            public void a(boolean z, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSInstallationServiceCallback");
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(z, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            b(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.android.hms.ppskit.IPPSInstallationServiceCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof c)) ? new b(iBinder) : (c) queryLocalInterface;
        }

        public static c a() {
            return b.d;
        }

        public a() {
            attachInterface(this, "com.huawei.android.hms.ppskit.IPPSInstallationServiceCallback");
        }
    }
}
