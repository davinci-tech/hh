package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;

/* loaded from: classes4.dex */
public interface c extends IInterface {
    void a();

    void a(int i);

    void a(long j, long j2);

    void a(Bundle bundle);

    void a(IObjectWrapper iObjectWrapper);

    void a(IObjectWrapper iObjectWrapper, int i);

    void a(IObjectWrapper iObjectWrapper, Bundle bundle);

    void a(IObjectWrapper iObjectWrapper, String str);

    void a(IObjectWrapper iObjectWrapper, String str, Bundle bundle);

    void a(IPPSUiEngineCallback iPPSUiEngineCallback);

    void a(String str, int i);

    void a(String str, long j, long j2, int i, int i2);

    void a(String str, IObjectWrapper iObjectWrapper, Bundle bundle);

    void a(boolean z);

    String b();

    void b(String str, long j, long j2, int i, int i2);

    void b(String str, Bundle bundle);

    void c();

    boolean d();

    boolean e();

    public static abstract class a extends Binder implements c {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.hms.ads.uiengine.c$a$a, reason: collision with other inner class name */
        static class C0090a implements c {

            /* renamed from: a, reason: collision with root package name */
            public static c f4353a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.c
            public boolean e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    if (!this.b.transact(10, obtain, obtain2, 0) && a.g() != null) {
                        return a.g().e();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public boolean d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    if (!this.b.transact(8, obtain, obtain2, 0) && a.g() != null) {
                        return a.g().d();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    if (this.b.transact(15, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().c();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void b(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(18, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().b(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void b(String str, long j, long j2, int i, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.b.transact(3, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().b(str, j, j2, i, i2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public String b() {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    if (this.b.transact(17, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = a.g().b();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeInt(z ? 1 : 0);
                    if (this.b.transact(4, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(20, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(str, iObjectWrapper, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(String str, long j, long j2, int i, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.b.transact(2, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(str, j, j2, i, i2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(String str, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.b.transact(1, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(str, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(IPPSUiEngineCallback iPPSUiEngineCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeStrongBinder(iPPSUiEngineCallback != null ? iPPSUiEngineCallback.asBinder() : null);
                    if (this.b.transact(14, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(iPPSUiEngineCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(IObjectWrapper iObjectWrapper, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(11, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(iObjectWrapper, str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(IObjectWrapper iObjectWrapper, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeString(str);
                    if (this.b.transact(9, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(iObjectWrapper, str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(IObjectWrapper iObjectWrapper, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(12, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(iObjectWrapper, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(IObjectWrapper iObjectWrapper, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeInt(i);
                    if (this.b.transact(7, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(iObjectWrapper, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (this.b.transact(6, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(iObjectWrapper);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(16, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(long j, long j2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    if (this.b.transact(5, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(j, j2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    obtain.writeInt(i);
                    if (this.b.transact(13, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.c
            public void a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.INativeApi");
                    if (this.b.transact(19, obtain, obtain2, 0) || a.g() == null) {
                        obtain2.readException();
                    } else {
                        a.g().a();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0090a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        b(parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(parcel.readInt() != 0);
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(parcel.readLong(), parcel.readLong());
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        boolean d = d();
                        parcel2.writeNoException();
                        parcel2.writeInt(d ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        boolean e = e();
                        parcel2.writeNoException();
                        parcel2.writeInt(e ? 1 : 0);
                        return true;
                    case 11:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 13:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 14:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(IPPSUiEngineCallback.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 15:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        c();
                        parcel2.writeNoException();
                        return true;
                    case 16:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 17:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        String b = b();
                        parcel2.writeNoException();
                        parcel2.writeString(b);
                        return true;
                    case 18:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        b(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 19:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a();
                        parcel2.writeNoException();
                        return true;
                    case 20:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.INativeApi");
                        a(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("com.huawei.hms.ads.uiengine.INativeApi");
            return true;
        }

        public static c g() {
            return C0090a.f4353a;
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.ads.uiengine.INativeApi");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof c)) ? new C0090a(iBinder) : (c) queryLocalInterface;
        }

        public a() {
            attachInterface(this, "com.huawei.hms.ads.uiengine.INativeApi");
        }
    }
}
