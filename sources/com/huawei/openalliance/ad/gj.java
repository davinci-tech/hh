package com.huawei.openalliance.ad;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.openalliance.ad.gi;

/* loaded from: classes5.dex */
public interface gj extends IInterface {
    void a();

    void a(gi giVar);

    void b(gi giVar);

    public static abstract class a extends Binder implements gj {

        /* renamed from: com.huawei.openalliance.ad.gj$a$a, reason: collision with other inner class name */
        static class C0185a implements gj {

            /* renamed from: a, reason: collision with root package name */
            public static gj f6880a;
            private IBinder b;

            @Override // com.huawei.openalliance.ad.gj
            public void b(gi giVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDService");
                    obtain.writeStrongBinder(giVar != null ? giVar.asBinder() : null);
                    if (this.b.transact(3, obtain, obtain2, 0) || a.b() == null) {
                        obtain2.readException();
                    } else {
                        a.b().b(giVar);
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

            @Override // com.huawei.openalliance.ad.gj
            public void a(gi giVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDService");
                    obtain.writeStrongBinder(giVar != null ? giVar.asBinder() : null);
                    if (this.b.transact(2, obtain, obtain2, 0) || a.b() == null) {
                        obtain2.readException();
                    } else {
                        a.b().a(giVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.openalliance.ad.gj
            public void a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDService");
                    if (this.b.transact(1, obtain, obtain2, 0) || a.b() == null) {
                        obtain2.readException();
                    } else {
                        a.b().a();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0185a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDService");
                a();
            } else if (i == 2) {
                parcel.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDService");
                a(gi.a.a(parcel.readStrongBinder()));
            } else {
                if (i != 3) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.hihonor.cloudservice.oaid.IOAIDService");
                    return true;
                }
                parcel.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDService");
                b(gi.a.a(parcel.readStrongBinder()));
            }
            parcel2.writeNoException();
            return true;
        }

        public static gj b() {
            return C0185a.f6880a;
        }

        public static gj a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.hihonor.cloudservice.oaid.IOAIDService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof gj)) ? new C0185a(iBinder) : (gj) queryLocalInterface;
        }
    }
}
