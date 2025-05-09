package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.maps.StreetViewPanoramaOptions;
import com.huawei.hms.maps.internal.IStreetViewPanoramaDelegate;

/* loaded from: classes9.dex */
public interface IStreetViewPanoramaFragmentDelegate extends IInterface {
    IStreetViewPanoramaDelegate getStreetViewPanorama();

    void getStreetViewPanoramaAsync(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback);

    boolean isReady();

    void onCreate(Bundle bundle);

    IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle);

    void onDestroy();

    void onDestroyView();

    void onInflate(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions, Bundle bundle);

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    public static abstract class Stub extends Binder implements IStreetViewPanoramaFragmentDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IStreetViewPanoramaFragmentDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4963a;

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onStop() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 14);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onStart() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 13);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onSaveInstanceState(Bundle bundle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 12, bundle);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onResume() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 11);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onPause() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 10);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onLowMemory() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 9);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onInflate(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (streetViewPanoramaOptions != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4963a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onDestroyView() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 7);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onDestroy() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 6);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeStrongBinder(iObjectWrapper2 != null ? iObjectWrapper2.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4963a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void onCreate(Bundle bundle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 4, bundle);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public boolean isReady() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 3);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public void getStreetViewPanoramaAsync(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 2, iOnStreetViewPanoramaReadyCallback != null ? iOnStreetViewPanoramaReadyCallback.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate
            public IStreetViewPanoramaDelegate getStreetViewPanorama() {
                return IStreetViewPanoramaDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4963a, "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate", 1));
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4963a;
            }

            Proxy(IBinder iBinder) {
                this.f4963a = iBinder;
            }
        }

        public static IStreetViewPanoramaFragmentDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IStreetViewPanoramaFragmentDelegate)) ? new Proxy(iBinder) : (IStreetViewPanoramaFragmentDelegate) queryLocalInterface;
        }
    }
}
