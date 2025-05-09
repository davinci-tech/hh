package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public interface IUiSettingsDelegate extends IInterface {
    boolean getGestureScaleByMapCenter();

    boolean isCompassEnabled();

    boolean isIndoorLevelPickerEnabled();

    boolean isMapToolbarEnabled();

    boolean isMyLocationButtonEnabled();

    boolean isRotateGesturesEnabled();

    boolean isScrollGesturesEnabled();

    boolean isScrollGesturesEnabledDuringRotateOrZoom();

    boolean isTiltGesturesEnabled();

    boolean isZoomControlsEnabled();

    boolean isZoomGesturesEnabled();

    void setAllGesturesEnabled(boolean z);

    void setCompassEnabled(boolean z);

    void setGestureScaleByMapCenter(boolean z);

    void setIndoorLevelPickerEnabled(boolean z);

    void setIndoorLevelPickerPadding(int i, int i2, int i3, int i4);

    void setLogoPadding(int i, int i2, int i3, int i4);

    void setLogoPosition(int i);

    void setMapToolbarEnabled(boolean z);

    void setMarkerClusterColor(int i);

    void setMarkerClusterIcon(IObjectWrapper iObjectWrapper);

    void setMarkerClusterTextColor(int i);

    void setMyLocationButtonEnabled(boolean z);

    void setRotateGesturesEnabled(boolean z);

    void setScaleVisible(boolean z);

    void setScrollGesturesEnabled(boolean z);

    void setScrollGesturesEnabledDuringRotateOrZoom(boolean z);

    void setTiltGesturesEnabled(boolean z);

    void setZoomControlsEnabled(boolean z);

    void setZoomGesturesEnabled(boolean z);

    public static abstract class Stub extends Binder implements IUiSettingsDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IUiSettingsDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4965a;

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setZoomGesturesEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 21);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setZoomControlsEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 20);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setTiltGesturesEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 19);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 18);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setScrollGesturesEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 17);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setScaleVisible(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 30);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setRotateGesturesEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 16);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setMyLocationButtonEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 15);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setMarkerClusterTextColor(int i) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 26, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setMarkerClusterIcon(IObjectWrapper iObjectWrapper) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 24, iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setMarkerClusterColor(int i) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 25, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setMapToolbarEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 14);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setLogoPosition(int i) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 27, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setLogoPadding(int i, int i2, int i3, int i4) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 28, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setIndoorLevelPickerPadding(int i, int i2, int i3, int i4) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 29, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setIndoorLevelPickerEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 13);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setGestureScaleByMapCenter(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 22);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setCompassEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 12);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public void setAllGesturesEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 11);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isZoomGesturesEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 10);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isZoomControlsEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 9);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isTiltGesturesEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 8);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isScrollGesturesEnabledDuringRotateOrZoom() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 7);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isScrollGesturesEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 6);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isRotateGesturesEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 5);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isMyLocationButtonEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 4);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isMapToolbarEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 3);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isIndoorLevelPickerEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 2);
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean isCompassEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 1);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IUiSettingsDelegate";
            }

            @Override // com.huawei.hms.maps.internal.IUiSettingsDelegate
            public boolean getGestureScaleByMapCenter() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4965a, "com.huawei.hms.maps.internal.IUiSettingsDelegate", 23);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4965a;
            }

            Proxy(IBinder iBinder) {
                this.f4965a = iBinder;
            }
        }

        public static IUiSettingsDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IUiSettingsDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IUiSettingsDelegate)) ? new Proxy(iBinder) : (IUiSettingsDelegate) queryLocalInterface;
        }
    }
}
