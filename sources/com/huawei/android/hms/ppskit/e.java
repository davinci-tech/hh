package com.huawei.android.hms.ppskit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.android.hms.ppskit.d;

/* loaded from: classes2.dex */
public interface e extends IInterface {
    void a();

    void a(String str, String str2, d dVar);

    public static abstract class a extends Binder implements e {

        static class c implements e {
            public static e d;
            private IBinder c;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.android.hms.ppskit.e
            public void a(String str, String str2, d dVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSServiceApi");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (this.c.transact(2, obtain, obtain2, 0) || a.b() == null) {
                        obtain2.readException();
                    } else {
                        a.b().a(str, str2, dVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hms.ppskit.e
            public void a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSServiceApi");
                    if (this.c.transact(1, obtain, obtain2, 0) || a.b() == null) {
                        obtain2.readException();
                    } else {
                        a.b().a();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            c(IBinder iBinder) {
                this.c = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.android.hms.ppskit.IPPSServiceApi");
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSServiceApi");
                a();
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSServiceApi");
                a(parcel.readString(), parcel.readString(), d.a.a(parcel.readStrongBinder()));
            }
            parcel2.writeNoException();
            return true;
        }

        public static e b() {
            return c.d;
        }

        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.android.hms.ppskit.IPPSServiceApi");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof e)) ? new c(iBinder) : (e) queryLocalInterface;
        }
    }
}
