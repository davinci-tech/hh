package com.huawei.openalliance.ad;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public interface xi extends IInterface {
    String a(String str, String str2, String str3);

    public static abstract class a extends Binder implements xi {

        /* renamed from: com.huawei.openalliance.ad.xi$a$a, reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        static class C0222a implements xi {

            /* renamed from: a, reason: collision with root package name */
            public static xi f8209a;
            private IBinder b;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.openalliance.ad.xi
            public String a(String str, String str2, String str3) {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = a.a().a(str, str2, str3);
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0222a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.heytap.openid.IOpenID");
                return true;
            }
            parcel.enforceInterface("com.heytap.openid.IOpenID");
            String a2 = a(parcel.readString(), parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(a2);
            return true;
        }

        public static xi a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            try {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
                return (queryLocalInterface == null || !(queryLocalInterface instanceof xi)) ? new C0222a(iBinder) : (xi) queryLocalInterface;
            } catch (Throwable th) {
                ho.c("IOpenID", "IOpenID err: " + th.getClass().getSimpleName());
                return null;
            }
        }

        public static xi a() {
            return C0222a.f8209a;
        }

        public a() {
            attachInterface(this, "com.heytap.openid.IOpenID");
        }
    }
}
