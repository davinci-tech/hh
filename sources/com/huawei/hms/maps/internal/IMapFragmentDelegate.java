package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.maps.HuaweiMapOptions;
import com.huawei.hms.maps.internal.IHuaweiMapDelegate;

/* loaded from: classes9.dex */
public interface IMapFragmentDelegate extends IInterface {
    IHuaweiMapDelegate getMap();

    void getMapAsync(IOnMapReadyCallback iOnMapReadyCallback);

    boolean isReady();

    void onAttach(IObjectWrapper iObjectWrapper);

    void onCreate(Bundle bundle);

    IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle);

    void onDestroy();

    void onDestroyView();

    void onEnterAmbient(Bundle bundle);

    void onExitAmbient();

    void onInflate(IObjectWrapper iObjectWrapper, HuaweiMapOptions huaweiMapOptions, Bundle bundle);

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void zOrderOnTop(boolean z);

    public static abstract class Stub extends Binder implements IMapFragmentDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IMapFragmentDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4958a;

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void zOrderOnTop(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 18);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onStop() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 17);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onStart() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 16);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onSaveInstanceState(Bundle bundle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 12, bundle);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onResume() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 5);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onPause() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 6);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onLowMemory() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 10);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onInflate(IObjectWrapper iObjectWrapper, HuaweiMapOptions huaweiMapOptions, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IMapFragmentDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (huaweiMapOptions != null) {
                        obtain.writeInt(1);
                        huaweiMapOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4958a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onExitAmbient() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 15);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onEnterAmbient(Bundle bundle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 14, bundle);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onDestroyView() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 8);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onDestroy() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 9);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IMapFragmentDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeStrongBinder(iObjectWrapper2 != null ? iObjectWrapper2.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4958a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onCreate(Bundle bundle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 3, new Parcelable[0]);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void onAttach(IObjectWrapper iObjectWrapper) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 7, iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public boolean isReady() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 13);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public void getMapAsync(IOnMapReadyCallback iOnMapReadyCallback) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 11, iOnMapReadyCallback != null ? iOnMapReadyCallback.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.internal.IMapFragmentDelegate
            public IHuaweiMapDelegate getMap() {
                return IHuaweiMapDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4958a, "com.huawei.hms.maps.internal.IMapFragmentDelegate", 1));
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IMapFragmentDelegate";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4958a;
            }

            Proxy(IBinder iBinder) {
                this.f4958a = iBinder;
            }
        }

        public static IMapFragmentDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IMapFragmentDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMapFragmentDelegate)) ? new Proxy(iBinder) : (IMapFragmentDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IMapFragmentDelegate");
        }
    }
}
