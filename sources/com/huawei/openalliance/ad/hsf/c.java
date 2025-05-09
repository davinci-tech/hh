package com.huawei.openalliance.ad.hsf;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public interface c extends IInterface {
    void a(String str, int i);

    public static abstract class a extends Binder implements c {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.openalliance.ad.hsf.c$a$a, reason: collision with other inner class name */
        static class C0190a implements c {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6925a;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6925a;
            }

            @Override // com.huawei.openalliance.ad.hsf.c
            public void a(String str, int i) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hsf.pm.service.IPackageInstalledCallback");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.f6925a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            C0190a(IBinder iBinder) {
                this.f6925a = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hsf.pm.service.IPackageInstalledCallback");
                a(parcel.readString(), parcel.readInt());
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.huawei.hsf.pm.service.IPackageInstalledCallback");
            return true;
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            try {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hsf.pm.service.IPackageInstalledCallback");
                return (queryLocalInterface == null || !(queryLocalInterface instanceof c)) ? new C0190a(iBinder) : (c) queryLocalInterface;
            } catch (Throwable th) {
                ho.c("IPackageInstalledCallback", "IPackageInstalledCallback err: " + th.getClass().getSimpleName());
                return null;
            }
        }

        public a() {
            attachInterface(this, "com.huawei.hsf.pm.service.IPackageInstalledCallback");
        }
    }
}
