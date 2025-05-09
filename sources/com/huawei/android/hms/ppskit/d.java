package com.huawei.android.hms.ppskit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface d extends IInterface {
    void a(String str, int i, String str2);

    public static abstract class a extends Binder implements d {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.android.hms.ppskit.IPPSResultCallback");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSResultCallback");
            a(parcel.readString(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        static class b implements d {
            public static d e;
            private IBinder c;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.android.hms.ppskit.d
            public void a(String str, int i, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSResultCallback");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (this.c.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(str, i, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            b(IBinder iBinder) {
                this.c = iBinder;
            }
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.android.hms.ppskit.IPPSResultCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof d)) ? new b(iBinder) : (d) queryLocalInterface;
        }

        public static d a() {
            return b.e;
        }

        public a() {
            attachInterface(this, "com.huawei.android.hms.ppskit.IPPSResultCallback");
        }
    }
}
