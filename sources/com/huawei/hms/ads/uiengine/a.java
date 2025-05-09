package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public interface a extends IInterface {
    long a();

    void a(Bundle bundle);

    void a(String str);

    void b();

    void b(Bundle bundle);

    void b(String str);

    void c(Bundle bundle);

    void c(String str);

    void d(Bundle bundle);

    /* renamed from: com.huawei.hms.ads.uiengine.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0087a extends Binder implements a {

        /* renamed from: com.huawei.hms.ads.uiengine.a$a$a, reason: collision with other inner class name */
        static class C0088a implements a {

            /* renamed from: a, reason: collision with root package name */
            public static a f4351a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.a
            public void d(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(5, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().d(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.a
            public void c(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    obtain.writeString(str);
                    if (this.b.transact(9, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().c(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.a
            public void c(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(4, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().c(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.a
            public void b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    obtain.writeString(str);
                    if (this.b.transact(8, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().b(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.a
            public void b(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(3, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().b(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.a
            public void b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    if (this.b.transact(7, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().b();
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

            @Override // com.huawei.hms.ads.uiengine.a
            public void a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    obtain.writeString(str);
                    if (this.b.transact(6, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().a(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.a
            public void a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(2, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0087a.c().a(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.a
            public long a() {
                long readLong;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                    if (this.b.transact(1, obtain, obtain2, 0) || AbstractBinderC0087a.c() == null) {
                        obtain2.readException();
                        readLong = obtain2.readLong();
                    } else {
                        readLong = AbstractBinderC0087a.c().a();
                    }
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0088a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        long a2 = a();
                        parcel2.writeNoException();
                        parcel2.writeLong(a2);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        a(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        b(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        c(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        d(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        a(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        b();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        b(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
                        c(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
            return true;
        }

        public static a c() {
            return C0088a.f4351a;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.ads.uiengine.IMediaplayerAgent");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0088a(iBinder) : (a) queryLocalInterface;
        }
    }
}
