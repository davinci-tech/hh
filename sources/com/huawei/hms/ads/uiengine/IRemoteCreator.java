package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.uiengine.IGlobalUtil;
import com.huawei.hms.ads.uiengine.IRemoteViewDelegate;
import com.huawei.hms.ads.uiengine.ISplashApi;
import com.huawei.hms.ads.uiengine.c;
import com.huawei.hms.ads.uiengine.d;
import com.huawei.hms.ads.uiengine.e;

/* loaded from: classes4.dex */
public interface IRemoteCreator extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.ads.uiengine.IRemoteCreator";

    void bindData(IObjectWrapper iObjectWrapper, String str);

    void destroyView(IObjectWrapper iObjectWrapper);

    e getUiEngineUtil();

    String getVersion();

    IObjectWrapper newBannerTemplateView(Bundle bundle);

    IObjectWrapper newInterstitialTemplateView(Bundle bundle);

    IObjectWrapper newNativeTemplateView(Bundle bundle, c cVar);

    IRemoteViewDelegate newRemoteViewDelegate(IObjectWrapper iObjectWrapper, String str, Bundle bundle);

    IObjectWrapper newRewardTemplateView(Bundle bundle, d dVar);

    IObjectWrapper newSplashTemplateView(Bundle bundle, ISplashApi iSplashApi);

    void setGlobalUtil(IGlobalUtil iGlobalUtil);

    void setSdkInfo(int i, int i2, Bundle bundle);

    public static abstract class a extends Binder implements IRemoteCreator {

        /* renamed from: com.huawei.hms.ads.uiengine.IRemoteCreator$a$a, reason: collision with other inner class name */
        static class C0084a implements IRemoteCreator {

            /* renamed from: a, reason: collision with root package name */
            public static IRemoteCreator f4348a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public void setSdkInfo(int i, int i2, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(11, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().setSdkInfo(i, i2, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public void setGlobalUtil(IGlobalUtil iGlobalUtil) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    obtain.writeStrongBinder(iGlobalUtil != null ? iGlobalUtil.asBinder() : null);
                    if (this.b.transact(2, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().setGlobalUtil(iGlobalUtil);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public IObjectWrapper newSplashTemplateView(Bundle bundle, ISplashApi iSplashApi) {
                IObjectWrapper asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSplashApi != null ? iSplashApi.asBinder() : null);
                    if (this.b.transact(3, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        asInterface = IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                    } else {
                        asInterface = a.a().newSplashTemplateView(bundle, iSplashApi);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public IObjectWrapper newRewardTemplateView(Bundle bundle, d dVar) {
                IObjectWrapper asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (this.b.transact(10, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        asInterface = IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                    } else {
                        asInterface = a.a().newRewardTemplateView(bundle, dVar);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public IRemoteViewDelegate newRemoteViewDelegate(IObjectWrapper iObjectWrapper, String str, Bundle bundle) {
                IRemoteViewDelegate a2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(7, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        a2 = IRemoteViewDelegate.a.a(obtain2.readStrongBinder());
                    } else {
                        a2 = a.a().newRemoteViewDelegate(iObjectWrapper, str, bundle);
                    }
                    return a2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public IObjectWrapper newNativeTemplateView(Bundle bundle, c cVar) {
                IObjectWrapper asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (this.b.transact(4, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        asInterface = IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                    } else {
                        asInterface = a.a().newNativeTemplateView(bundle, cVar);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public IObjectWrapper newInterstitialTemplateView(Bundle bundle) {
                IObjectWrapper asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(12, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        asInterface = IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                    } else {
                        asInterface = a.a().newInterstitialTemplateView(bundle);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public IObjectWrapper newBannerTemplateView(Bundle bundle) {
                IObjectWrapper asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(5, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        asInterface = IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                    } else {
                        asInterface = a.a().newBannerTemplateView(bundle);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public String getVersion() {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = a.a().getVersion();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public e getUiEngineUtil() {
                e a2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    if (this.b.transact(9, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        a2 = e.a.a(obtain2.readStrongBinder());
                    } else {
                        a2 = a.a().getUiEngineUtil();
                    }
                    return a2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public void destroyView(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (this.b.transact(8, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().destroyView(iObjectWrapper);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IRemoteCreator
            public void bindData(IObjectWrapper iObjectWrapper, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCreator.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeString(str);
                    if (this.b.transact(6, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().bindData(iObjectWrapper, str);
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

            C0084a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        String version = getVersion();
                        parcel2.writeNoException();
                        parcel2.writeString(version);
                        return true;
                    case 2:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        setGlobalUtil(IGlobalUtil.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        IObjectWrapper newSplashTemplateView = newSplashTemplateView(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, ISplashApi.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(newSplashTemplateView != null ? newSplashTemplateView.asBinder() : null);
                        return true;
                    case 4:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        IObjectWrapper newNativeTemplateView = newNativeTemplateView(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, c.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(newNativeTemplateView != null ? newNativeTemplateView.asBinder() : null);
                        return true;
                    case 5:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        IObjectWrapper newBannerTemplateView = newBannerTemplateView(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(newBannerTemplateView != null ? newBannerTemplateView.asBinder() : null);
                        return true;
                    case 6:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        bindData(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        IRemoteViewDelegate newRemoteViewDelegate = newRemoteViewDelegate(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(newRemoteViewDelegate != null ? newRemoteViewDelegate.asBinder() : null);
                        return true;
                    case 8:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        destroyView(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        e uiEngineUtil = getUiEngineUtil();
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(uiEngineUtil != null ? uiEngineUtil.asBinder() : null);
                        return true;
                    case 10:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        IObjectWrapper newRewardTemplateView = newRewardTemplateView(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, d.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(newRewardTemplateView != null ? newRewardTemplateView.asBinder() : null);
                        return true;
                    case 11:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        setSdkInfo(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface(IRemoteCreator.DESCRIPTOR);
                        IObjectWrapper newInterstitialTemplateView = newInterstitialTemplateView(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(newInterstitialTemplateView != null ? newInterstitialTemplateView.asBinder() : null);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(IRemoteCreator.DESCRIPTOR);
            return true;
        }

        public static IRemoteCreator a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRemoteCreator.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteCreator)) ? new C0084a(iBinder) : (IRemoteCreator) queryLocalInterface;
        }

        public static IRemoteCreator a() {
            return C0084a.f4348a;
        }
    }
}
