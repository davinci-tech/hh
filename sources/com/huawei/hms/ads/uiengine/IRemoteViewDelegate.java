package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;

/* loaded from: classes4.dex */
public interface IRemoteViewDelegate extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.ads.uiengine.IRemoteViewDelegate";

    IObjectWrapper getView();

    void onCreate(Bundle bundle);

    void onDestroy();

    void onPause();

    void onRestart();

    void onResume();

    void onStart();

    void onStop();

    Bundle sendCommand(String str, Bundle bundle);

    void setCallback(IPPSUiEngineCallback iPPSUiEngineCallback);

    public static abstract class a extends Binder implements IRemoteViewDelegate {

        /* renamed from: com.huawei.hms.ads.uiengine.IRemoteViewDelegate$a$a, reason: collision with other inner class name */
        static class C0085a implements IRemoteViewDelegate {

            /* renamed from: a, reason: collision with root package name */
            public static IRemoteViewDelegate f4349a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void setCallback(IPPSUiEngineCallback iPPSUiEngineCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    obtain.writeStrongBinder(iPPSUiEngineCallback != null ? iPPSUiEngineCallback.asBinder() : null);
                    if (this.b.transact(9, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().setCallback(iPPSUiEngineCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public Bundle sendCommand(String str, Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(10, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    } else {
                        bundle2 = a.a().sendCommand(str, bundle);
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void onStop() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (this.b.transact(7, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onStop();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void onStart() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (this.b.transact(6, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onStart();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void onResume() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (this.b.transact(5, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onResume();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void onRestart() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (this.b.transact(8, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onRestart();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void onPause() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (this.b.transact(4, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onPause();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void onDestroy() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (this.b.transact(3, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onDestroy();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public void onCreate(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(2, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onCreate(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteViewDelegate
            public IObjectWrapper getView() {
                IObjectWrapper asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteViewDelegate.DESCRIPTOR);
                    if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        asInterface = IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                    } else {
                        asInterface = a.a().getView();
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            C0085a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        IObjectWrapper view = getView();
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(view != null ? view.asBinder() : null);
                        return true;
                    case 2:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        onCreate(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        break;
                    case 3:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        onDestroy();
                        break;
                    case 4:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        onPause();
                        break;
                    case 5:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        onResume();
                        break;
                    case 6:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        onStart();
                        break;
                    case 7:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        onStop();
                        break;
                    case 8:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        onRestart();
                        break;
                    case 9:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        setCallback(IPPSUiEngineCallback.a.a(parcel.readStrongBinder()));
                        break;
                    case 10:
                        parcel.enforceInterface(IRemoteViewDelegate.DESCRIPTOR);
                        Bundle sendCommand = sendCommand(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        if (sendCommand != null) {
                            parcel2.writeInt(1);
                            sendCommand.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeNoException();
                return true;
            }
            parcel2.writeString(IRemoteViewDelegate.DESCRIPTOR);
            return true;
        }

        public static IRemoteViewDelegate a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRemoteViewDelegate.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteViewDelegate)) ? new C0085a(iBinder) : (IRemoteViewDelegate) queryLocalInterface;
        }

        public static IRemoteViewDelegate a() {
            return C0085a.f4349a;
        }
    }
}
