package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;

/* loaded from: classes4.dex */
public interface d extends IInterface {
    void a(Bundle bundle);

    void a(IObjectWrapper iObjectWrapper, String str, Bundle bundle);

    void a(IPPSUiEngineCallback iPPSUiEngineCallback);

    void a(String str, Bundle bundle);

    void a(String str, IObjectWrapper iObjectWrapper, Bundle bundle);

    boolean a();

    Bundle b(String str, IObjectWrapper iObjectWrapper, Bundle bundle);

    void b(String str, Bundle bundle);

    boolean b(Bundle bundle);

    void c(String str, Bundle bundle);

    void d(String str, Bundle bundle);

    void e(String str, Bundle bundle);

    public static abstract class a extends Binder implements d {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.hms.ads.uiengine.d$a$a, reason: collision with other inner class name */
        static class C0091a implements d {

            /* renamed from: a, reason: collision with root package name */
            public static d f4354a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.d
            public void e(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(6, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().e(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void d(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(8, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().d(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void c(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(5, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().c(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public boolean b(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(9, obtain, obtain2, 0) && a.c() != null) {
                        return a.c().b(bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void b(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(3, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().b(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public Bundle b(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(12, obtain, obtain2, 0) && a.c() != null) {
                        return a.c().b(str, iObjectWrapper, bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public boolean a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    if (!this.b.transact(11, obtain, obtain2, 0) && a.c() != null) {
                        return a.c().a();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void a(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(1, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().a(str, iObjectWrapper, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void a(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(2, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().a(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void a(IPPSUiEngineCallback iPPSUiEngineCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeStrongBinder(iPPSUiEngineCallback != null ? iPPSUiEngineCallback.asBinder() : null);
                    if (this.b.transact(10, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().a(iPPSUiEngineCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void a(IObjectWrapper iObjectWrapper, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(4, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().a(iObjectWrapper, str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.d
            public void a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.ads.uiengine.IRewardApi");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(7, obtain, obtain2, 0) || a.c() == null) {
                        obtain2.readException();
                    } else {
                        a.c().a(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0091a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3;
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        a(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        a(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        b(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        a(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        c(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        e(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        a(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        d(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        i3 = b(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeInt(i3);
                        return true;
                    case 10:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        a(IPPSUiEngineCallback.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 11:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        i3 = a();
                        parcel2.writeNoException();
                        parcel2.writeInt(i3);
                        return true;
                    case 12:
                        parcel.enforceInterface("com.huawei.hms.ads.uiengine.IRewardApi");
                        Bundle b = b(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        if (b != null) {
                            parcel2.writeInt(1);
                            b.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("com.huawei.hms.ads.uiengine.IRewardApi");
            return true;
        }

        public static d c() {
            return C0091a.f4354a;
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.ads.uiengine.IRewardApi");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof d)) ? new C0091a(iBinder) : (d) queryLocalInterface;
        }

        public a() {
            attachInterface(this, "com.huawei.hms.ads.uiengine.IRewardApi");
        }
    }
}
