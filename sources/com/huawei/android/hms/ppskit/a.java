package com.huawei.android.hms.ppskit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface a extends IInterface {
    String a();

    String a(String str);

    void a(String str, int i, String str2, String str3);

    void a(String str, String str2, int i);

    /* renamed from: com.huawei.android.hms.ppskit.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0038a extends Binder implements a {

        /* renamed from: com.huawei.android.hms.ppskit.a$a$a, reason: collision with other inner class name */
        static class C0039a implements a {
            public static a b;
            private IBinder e;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.android.hms.ppskit.a
            public void a(String str, String str2, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (this.e.transact(2, obtain, obtain2, 0) || AbstractBinderC0038a.b() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0038a.b().a(str, str2, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hms.ppskit.a
            public void a(String str, int i, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (this.e.transact(3, obtain, obtain2, 0) || AbstractBinderC0038a.b() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0038a.b().a(str, i, str2, str3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hms.ppskit.a
            public String a(String str) {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                    obtain.writeString(str);
                    if (this.e.transact(4, obtain, obtain2, 0) || AbstractBinderC0038a.b() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = AbstractBinderC0038a.b().a(str);
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.hms.ppskit.a
            public String a() {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                    if (this.e.transact(1, obtain, obtain2, 0) || AbstractBinderC0038a.b() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = AbstractBinderC0038a.b().a();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0039a(IBinder iBinder) {
                this.e = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                String a2 = a();
                parcel2.writeNoException();
                parcel2.writeString(a2);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                a(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
                a(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
            String a3 = a(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(a3);
            return true;
        }

        public static a b() {
            return C0039a.b;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.android.hms.ppskit.IPPSChannelInfoService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0039a(iBinder) : (a) queryLocalInterface;
        }
    }
}
