package com.alipay.sdk.m.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes7.dex */
public interface a extends IInterface {

    /* renamed from: com.alipay.sdk.m.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0008a extends Binder implements a {

        /* renamed from: com.alipay.sdk.m.a.a$a$e */
        public static class e implements a {
            public IBinder c;

            public e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            public String e(String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new e(iBinder) : (a) queryLocalInterface;
        }
    }
}
