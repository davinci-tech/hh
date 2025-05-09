package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.maps.internal.IStreetViewPanoramaDelegate;

/* loaded from: classes9.dex */
public interface IStreetViewPanoramaViewDelegate extends IInterface {
    IStreetViewPanoramaDelegate getStreetViewPanorama();

    void getStreetViewPanoramaAsync(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback);

    IObjectWrapper getView();

    void onCreate(Bundle bundle);

    void onDestroy();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    public static abstract class Stub extends Binder implements IStreetViewPanoramaViewDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IStreetViewPanoramaViewDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4964a;

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onStop() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 11);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onStart() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 10);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onSaveInstanceState(Bundle bundle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 9, bundle);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onResume() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 8);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onPause() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 7);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onLowMemory() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 6);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onDestroy() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 5);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void onCreate(Bundle bundle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 4, bundle);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public IObjectWrapper getView() {
                return IObjectWrapper.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 3));
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public void getStreetViewPanoramaAsync(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 2, iOnStreetViewPanoramaReadyCallback != null ? iOnStreetViewPanoramaReadyCallback.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate
            public IStreetViewPanoramaDelegate getStreetViewPanorama() {
                return IStreetViewPanoramaDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4964a, "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate", 1));
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4964a;
            }

            Proxy(IBinder iBinder) {
                this.f4964a = iBinder;
            }
        }

        public static IStreetViewPanoramaViewDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IStreetViewPanoramaViewDelegate)) ? new Proxy(iBinder) : (IStreetViewPanoramaViewDelegate) queryLocalInterface;
        }
    }
}
