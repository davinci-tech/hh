package com.huawei.openalliance.ad.hsf;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.hsf.c;

/* loaded from: classes5.dex */
public interface d extends IInterface {
    void a(String str, String str2, c cVar, int i);

    public static abstract class a extends Binder implements d {

        /* renamed from: com.huawei.openalliance.ad.hsf.d$a$a, reason: collision with other inner class name */
        static class C0191a implements d {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6926a;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6926a;
            }

            @Override // com.huawei.openalliance.ad.hsf.d
            public void a(String str, String str2, c cVar, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hsf.pm.service.IPackageManager");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.f6926a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0191a(IBinder iBinder) {
                this.f6926a = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.hsf.pm.service.IPackageManager");
                return true;
            }
            parcel.enforceInterface("com.huawei.hsf.pm.service.IPackageManager");
            a(parcel.readString(), parcel.readString(), c.a.a(parcel.readStrongBinder()), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            try {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hsf.pm.service.IPackageManager");
                return (queryLocalInterface == null || !(queryLocalInterface instanceof d)) ? new C0191a(iBinder) : (d) queryLocalInterface;
            } catch (Throwable th) {
                ho.c("IPackageManager", "IPackageManager err: " + th.getClass().getSimpleName());
                return null;
            }
        }
    }
}
