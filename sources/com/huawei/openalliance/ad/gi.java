package com.huawei.openalliance.ad;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public interface gi extends IInterface {
    void a(int i, long j, boolean z, float f, double d, String str);

    void a(int i, Bundle bundle);

    public static abstract class a extends Binder implements gi {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.openalliance.ad.gi$a$a, reason: collision with other inner class name */
        static class C0184a implements gi {

            /* renamed from: a, reason: collision with root package name */
            public static gi f6879a;
            private IBinder b;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.openalliance.ad.gi
            public void a(int i, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(2, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(i, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.openalliance.ad.gi
            public void a(int i, long j, boolean z, float f, double d, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeFloat(f);
                    obtain.writeDouble(d);
                    obtain.writeString(str);
                    try {
                        if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                            obtain2.readException();
                        } else {
                            a.a().a(i, j, z, f, d, str);
                        }
                        obtain2.recycle();
                        obtain.recycle();
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            C0184a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                a(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
            } else {
                if (i != 2) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                    return true;
                }
                parcel.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                a(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            }
            parcel2.writeNoException();
            return true;
        }

        public static gi a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.hihonor.cloudservice.oaid.IOAIDCallBack");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof gi)) ? new C0184a(iBinder) : (gi) queryLocalInterface;
        }

        public static gi a() {
            return C0184a.f6879a;
        }

        public a() {
            attachInterface(this, "com.hihonor.cloudservice.oaid.IOAIDCallBack");
        }
    }
}
