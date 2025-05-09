package com.huawei.hms.ads.uiengine;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.ads.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public interface ISplashApi extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.ads.uiengine.ISplashApi";

    void callMethod(String str, IObjectWrapper iObjectWrapper, Bundle bundle);

    Bundle callMethodForResult(String str, IObjectWrapper iObjectWrapper, Bundle bundle);

    boolean isDestroyed();

    boolean isFinishing();

    void notifyAdDismissed();

    void notifyAdFailedToLoad(int i);

    String notifyAdLoaded();

    void onAdFailToDisplay();

    void onAdShowEnd(long j, int i);

    void onDisplayTimeUp();

    void onEasterEggPrepare();

    void onFeedback(int i);

    void onMaterialLoadFailed();

    void onMaterialLoaded();

    void onSkipAd(int i, int i2);

    void onStartEasterEggFailed(Bundle bundle);

    boolean onTouch(int i, int i2, long j, String str, int i3);

    boolean processWhyEventUnified();

    void removeExSplashBlock();

    void reportEvents(String str, Bundle bundle);

    void reportShowStartEvent();

    void reportSplashEvent(Bundle bundle);

    void toShowSpare(int i);

    void updatePhyShowStart(long j);

    public static abstract class a extends Binder implements ISplashApi {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.hms.ads.uiengine.ISplashApi$a$a, reason: collision with other inner class name */
        static class C0086a implements ISplashApi {

            /* renamed from: a, reason: collision with root package name */
            public static ISplashApi f4350a;
            private IBinder b;

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void updatePhyShowStart(long j) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (this.b.transact(4, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().updatePhyShowStart(j);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void toShowSpare(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.b.transact(8, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().toShowSpare(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void reportSplashEvent(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(22, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().reportSplashEvent(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void reportShowStartEvent() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(3, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().reportShowStartEvent();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void reportEvents(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(16, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().reportEvents(str, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void removeExSplashBlock() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(10, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().removeExSplashBlock();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public boolean processWhyEventUnified() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (!this.b.transact(21, obtain, obtain2, 0) && a.a() != null) {
                        return a.a().processWhyEventUnified();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public boolean onTouch(int i, int i2, long j, String str, int i3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeInt(i3);
                    try {
                        if (!this.b.transact(1, obtain, obtain2, 0) && a.a() != null) {
                            boolean onTouch = a.a().onTouch(i, i2, j, str, i3);
                            obtain2.recycle();
                            obtain.recycle();
                            return onTouch;
                        }
                        obtain2.readException();
                        boolean z = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onStartEasterEggFailed(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(17, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onStartEasterEggFailed(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onSkipAd(int i, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (this.b.transact(13, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onSkipAd(i, i2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onMaterialLoaded() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(5, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onMaterialLoaded();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onMaterialLoadFailed() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(11, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onMaterialLoadFailed();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onFeedback(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.b.transact(14, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onFeedback(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onEasterEggPrepare() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(20, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onEasterEggPrepare();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onDisplayTimeUp() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(12, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onDisplayTimeUp();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onAdShowEnd(long j, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    if (this.b.transact(15, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onAdShowEnd(j, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void onAdFailToDisplay() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(6, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().onAdFailToDisplay();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public String notifyAdLoaded() {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(2, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = a.a().notifyAdLoaded();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void notifyAdFailedToLoad(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.b.transact(9, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().notifyAdFailedToLoad(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void notifyAdDismissed() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (this.b.transact(7, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().notifyAdDismissed();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public boolean isFinishing() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (!this.b.transact(18, obtain, obtain2, 0) && a.a() != null) {
                        return a.a().isFinishing();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public boolean isDestroyed() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    if (!this.b.transact(19, obtain, obtain2, 0) && a.a() != null) {
                        return a.a().isDestroyed();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public Bundle callMethodForResult(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(24, obtain, obtain2, 0) && a.a() != null) {
                        return a.a().callMethodForResult(str, iObjectWrapper, bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.ads.uiengine.ISplashApi
            public void callMethod(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISplashApi.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(23, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().callMethod(str, iObjectWrapper, bundle);
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

            C0086a(IBinder iBinder) {
                this.b = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        boolean onTouch = onTouch(parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(onTouch ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        String notifyAdLoaded = notifyAdLoaded();
                        parcel2.writeNoException();
                        parcel2.writeString(notifyAdLoaded);
                        return true;
                    case 3:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        reportShowStartEvent();
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        updatePhyShowStart(parcel.readLong());
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onMaterialLoaded();
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onAdFailToDisplay();
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        notifyAdDismissed();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        toShowSpare(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        notifyAdFailedToLoad(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        removeExSplashBlock();
                        parcel2.writeNoException();
                        return true;
                    case 11:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onMaterialLoadFailed();
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onDisplayTimeUp();
                        parcel2.writeNoException();
                        return true;
                    case 13:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onSkipAd(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 14:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onFeedback(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 15:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onAdShowEnd(parcel.readLong(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 16:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        reportEvents(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 17:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onStartEasterEggFailed(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 18:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        boolean isFinishing = isFinishing();
                        parcel2.writeNoException();
                        parcel2.writeInt(isFinishing ? 1 : 0);
                        return true;
                    case 19:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        boolean isDestroyed = isDestroyed();
                        parcel2.writeNoException();
                        parcel2.writeInt(isDestroyed ? 1 : 0);
                        return true;
                    case 20:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        onEasterEggPrepare();
                        parcel2.writeNoException();
                        return true;
                    case 21:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        boolean processWhyEventUnified = processWhyEventUnified();
                        parcel2.writeNoException();
                        parcel2.writeInt(processWhyEventUnified ? 1 : 0);
                        return true;
                    case 22:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        reportSplashEvent(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 23:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        callMethod(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 24:
                        parcel.enforceInterface(ISplashApi.DESCRIPTOR);
                        Bundle callMethodForResult = callMethodForResult(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        if (callMethodForResult != null) {
                            parcel2.writeInt(1);
                            callMethodForResult.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(ISplashApi.DESCRIPTOR);
            return true;
        }

        public static ISplashApi a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISplashApi.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISplashApi)) ? new C0086a(iBinder) : (ISplashApi) queryLocalInterface;
        }

        public static ISplashApi a() {
            return C0086a.f4350a;
        }

        public a() {
            attachInterface(this, ISplashApi.DESCRIPTOR);
        }
    }
}
