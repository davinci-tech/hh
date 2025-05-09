package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.ads.uiengine.a;

/* loaded from: classes4.dex */
public interface b extends IInterface {
    void a(com.huawei.hms.ads.uiengine.a aVar);

    void a(String str, com.huawei.hms.ads.uiengine.a aVar);

    void b(com.huawei.hms.ads.uiengine.a aVar);

    void b(String str, com.huawei.hms.ads.uiengine.a aVar);

    void c(String str, com.huawei.hms.ads.uiengine.a aVar);

    void d(String str, com.huawei.hms.ads.uiengine.a aVar);

    public static abstract class a extends Binder implements b {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.hms.ads.uiengine.b$a$a, reason: collision with other inner class name */
        static class C0089a implements b {

            /* renamed from: a, reason: collision with root package name */
            public static b f4352a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.b
            public void d(String str, com.huawei.hms.ads.uiengine.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(4, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().d(str, aVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.b
            public void c(String str, com.huawei.hms.ads.uiengine.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(3, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().c(str, aVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.b
            public void b(String str, com.huawei.hms.ads.uiengine.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(2, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().b(str, aVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.b
            public void b(com.huawei.hms.ads.uiengine.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(6, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().b(aVar);
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

            @Override // com.huawei.hms.ads.uiengine.b
            public void a(String str, com.huawei.hms.ads.uiengine.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(str, aVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.b
            public void a(com.huawei.hms.ads.uiengine.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.b.transact(5, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(aVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0089a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                        a(parcel.readString(), a.AbstractBinderC0087a.a(parcel.readStrongBinder()));
                        break;
                    case 2:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                        b(parcel.readString(), a.AbstractBinderC0087a.a(parcel.readStrongBinder()));
                        break;
                    case 3:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                        c(parcel.readString(), a.AbstractBinderC0087a.a(parcel.readStrongBinder()));
                        break;
                    case 4:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                        d(parcel.readString(), a.AbstractBinderC0087a.a(parcel.readStrongBinder()));
                        break;
                    case 5:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                        a(a.AbstractBinderC0087a.a(parcel.readStrongBinder()));
                        break;
                    case 6:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
                        b(a.AbstractBinderC0087a.a(parcel.readStrongBinder()));
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeNoException();
                return true;
            }
            parcel2.writeString("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
            return true;
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0089a(iBinder) : (b) queryLocalInterface;
        }

        public static b a() {
            return C0089a.f4352a;
        }

        public a() {
            attachInterface(this, "com.huawei.hms.ads.uiengine.IMultiMPlayingManager");
        }
    }
}
