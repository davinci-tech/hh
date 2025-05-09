package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;
import com.huawei.hms.ads.uiengine.b;

/* loaded from: classes4.dex */
public interface IGlobalUtil extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.ads.uiengine.IGlobalUtil";

    Bundle callMethod(String str, IObjectWrapper iObjectWrapper, Bundle bundle);

    void getFilePath(String str, IPPSUiEngineCallback iPPSUiEngineCallback);

    String getFilePathDirect(String str);

    String getFilePathDirectByCacheType(String str, int i);

    b getMultiMediaPlayingManager();

    boolean isFreedomWindowMode(IObjectWrapper iObjectWrapper);

    void registerActivityStartCallBack(IPPSUiEngineCallback iPPSUiEngineCallback);

    void unregisterActivityStartCallBack(IPPSUiEngineCallback iPPSUiEngineCallback);

    public static abstract class a extends Binder implements IGlobalUtil {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.hms.ads.uiengine.IGlobalUtil$a$a, reason: collision with other inner class name */
        static class C0082a implements IGlobalUtil {

            /* renamed from: a, reason: collision with root package name */
            public static IGlobalUtil f4346a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public void unregisterActivityStartCallBack(IPPSUiEngineCallback iPPSUiEngineCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    obtain.writeStrongBinder(iPPSUiEngineCallback != null ? iPPSUiEngineCallback.asBinder() : null);
                    if (this.b.transact(3, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().unregisterActivityStartCallBack(iPPSUiEngineCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public void registerActivityStartCallBack(IPPSUiEngineCallback iPPSUiEngineCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    obtain.writeStrongBinder(iPPSUiEngineCallback != null ? iPPSUiEngineCallback.asBinder() : null);
                    if (this.b.transact(2, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().registerActivityStartCallBack(iPPSUiEngineCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public boolean isFreedomWindowMode(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (!this.b.transact(7, obtain, obtain2, 0) && a.a() != null) {
                        return a.a().isFreedomWindowMode(iObjectWrapper);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public b getMultiMediaPlayingManager() {
                b a2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    if (this.b.transact(6, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        a2 = b.a.a(obtain2.readStrongBinder());
                    } else {
                        a2 = a.a().getMultiMediaPlayingManager();
                    }
                    return a2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public String getFilePathDirectByCacheType(String str, int i) {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.b.transact(5, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = a.a().getFilePathDirectByCacheType(str, i);
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public String getFilePathDirect(String str) {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.b.transact(4, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = a.a().getFilePathDirect(str);
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public void getFilePath(String str, IPPSUiEngineCallback iPPSUiEngineCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPPSUiEngineCallback != null ? iPPSUiEngineCallback.asBinder() : null);
                    if (this.b.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().getFilePath(str, iPPSUiEngineCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.IGlobalUtil
            public Bundle callMethod(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGlobalUtil.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(8, obtain, obtain2, 0) && a.a() != null) {
                        return a.a().callMethod(str, iObjectWrapper, bundle);
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

            C0082a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        getFilePath(parcel.readString(), IPPSUiEngineCallback.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        registerActivityStartCallBack(IPPSUiEngineCallback.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        unregisterActivityStartCallBack(IPPSUiEngineCallback.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        String filePathDirect = getFilePathDirect(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(filePathDirect);
                        return true;
                    case 5:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        String filePathDirectByCacheType = getFilePathDirectByCacheType(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeString(filePathDirectByCacheType);
                        return true;
                    case 6:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        b multiMediaPlayingManager = getMultiMediaPlayingManager();
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(multiMediaPlayingManager != null ? multiMediaPlayingManager.asBinder() : null);
                        return true;
                    case 7:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        boolean isFreedomWindowMode = isFreedomWindowMode(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(isFreedomWindowMode ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface(IGlobalUtil.DESCRIPTOR);
                        Bundle callMethod = callMethod(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        if (callMethod != null) {
                            parcel2.writeInt(1);
                            callMethod.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(IGlobalUtil.DESCRIPTOR);
            return true;
        }

        public static IGlobalUtil a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGlobalUtil.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGlobalUtil)) ? new C0082a(iBinder) : (IGlobalUtil) queryLocalInterface;
        }

        public static IGlobalUtil a() {
            return C0082a.f4346a;
        }

        public a() {
            attachInterface(this, IGlobalUtil.DESCRIPTOR);
        }
    }
}
