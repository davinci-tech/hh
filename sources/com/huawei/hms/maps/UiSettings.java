package com.huawei.hms.maps;

import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.internal.IUiSettingsDelegate;
import com.huawei.hms.maps.model.BitmapDescriptor;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes4.dex */
public class UiSettings {

    /* renamed from: a, reason: collision with root package name */
    private final IUiSettingsDelegate f4949a;

    public void setZoomGesturesEnabled(boolean z) {
        try {
            this.f4949a.setZoomGesturesEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setZoomGesturesEnabled RemoteException: " + e.toString());
        }
    }

    public void setZoomControlsEnabled(boolean z) {
        try {
            this.f4949a.setZoomControlsEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setZoomControlsEnabled RemoteException: " + e.toString());
        }
    }

    public void setTiltGesturesEnabled(boolean z) {
        try {
            this.f4949a.setTiltGesturesEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setTiltGesturesEnabled RemoteException: " + e.toString());
        }
    }

    public void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        try {
            this.f4949a.setScrollGesturesEnabledDuringRotateOrZoom(z);
        } catch (RemoteException e) {
            LogM.e("UISettings", "setScrollGesturesEnabledDuringRotateOrZoom RemoteException: " + e.toString());
        }
    }

    public void setScrollGesturesEnabled(boolean z) {
        try {
            this.f4949a.setScrollGesturesEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setScrollGesturesEnabled RemoteException: " + e.toString());
        }
    }

    public void setScaleVisible(boolean z) {
        try {
            this.f4949a.setScaleVisible(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setScaleVisible RemoteException: " + e.toString());
        }
    }

    public void setRotateGesturesEnabled(boolean z) {
        try {
            this.f4949a.setRotateGesturesEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setRotateGesturesEnabled RemoteException: " + e.toString());
        }
    }

    public void setMyLocationButtonEnabled(boolean z) {
        try {
            this.f4949a.setMyLocationButtonEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setMyLocationButtonEnabled RemoteException: " + e.toString());
        }
    }

    public void setMarkerClusterTextColor(int i) {
        try {
            this.f4949a.setMarkerClusterTextColor(i);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setMarkerClusterColor RemoteException: " + e.toString());
        }
    }

    public void setMarkerClusterIcon(BitmapDescriptor bitmapDescriptor) {
        try {
            if (bitmapDescriptor == null) {
                this.f4949a.setMarkerClusterIcon(ObjectWrapper.wrap(null));
            } else {
                this.f4949a.setMarkerClusterIcon(bitmapDescriptor.getObject());
            }
        } catch (RemoteException e) {
            LogM.d("UISettings", "setMarkerClusterIcon RemoteException: " + e.toString());
        }
    }

    public void setMarkerClusterColor(int i) {
        try {
            this.f4949a.setMarkerClusterColor(i);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setMarkerClusterColor RemoteException: " + e.toString());
        }
    }

    public void setMapToolbarEnabled(boolean z) {
        try {
            this.f4949a.setMapToolbarEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setMyLocationButtonEnabled RemoteException: " + e.toString());
        }
    }

    public void setLogoPosition(int i) {
        switch (i) {
            case 81:
            case 8388659:
            case 8388661:
            case 8388691:
            case 8388693:
                try {
                    this.f4949a.setLogoPosition(i);
                    break;
                } catch (RemoteException e) {
                    LogM.d("UISettings", "setLogoPosition RemoteException: " + e.toString());
                }
        }
    }

    public void setLogoPadding(int i, int i2, int i3, int i4) {
        try {
            this.f4949a.setLogoPadding(i, i2, i3, i4);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setLogoPadding RemoteException: " + e.toString());
        }
    }

    public void setIndoorLevelPickerPadding(int i, int i2, int i3, int i4) {
        try {
            this.f4949a.setIndoorLevelPickerPadding(i, i2, i3, i4);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setIndoorLevelPickerPadding RemoteException: " + e.toString());
        }
    }

    public void setIndoorLevelPickerEnabled(boolean z) {
        try {
            this.f4949a.setIndoorLevelPickerEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setIndoorLevelPickerEnabled RemoteException: " + e.toString());
        }
    }

    public void setGestureScaleByMapCenter(boolean z) {
        try {
            this.f4949a.setGestureScaleByMapCenter(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setGestureScaleByMapCenter RemoteException: " + e.toString());
        }
    }

    public void setCompassEnabled(boolean z) {
        try {
            this.f4949a.setCompassEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setCompassEnabled RemoteException: " + e.toString());
        }
    }

    public void setAllGesturesEnabled(boolean z) {
        try {
            this.f4949a.setAllGesturesEnabled(z);
        } catch (RemoteException e) {
            LogM.d("UISettings", "setAllGesturesEnabled RemoteException: " + e.toString());
        }
    }

    public boolean isZoomGesturesEnabled() {
        try {
            return this.f4949a.isZoomGesturesEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isZoomGesturesEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isZoomControlsEnabled() {
        try {
            return this.f4949a.isZoomControlsEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isZoomControlsEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isTiltGesturesEnabled() {
        try {
            return this.f4949a.isTiltGesturesEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isTiltGesturesEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        try {
            return this.f4949a.isScrollGesturesEnabledDuringRotateOrZoom();
        } catch (RemoteException e) {
            LogM.e("UISettings", "isScrollGesturesEnabledDuringRotateOrZoom RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isScrollGesturesEnabled() {
        try {
            return this.f4949a.isScrollGesturesEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isScrollGesturesEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isRotateGesturesEnabled() {
        try {
            return this.f4949a.isRotateGesturesEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isRotateGesturesEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isMyLocationButtonEnabled() {
        try {
            return this.f4949a.isMyLocationButtonEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isMyLocationButtonEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isMapToolbarEnabled() {
        try {
            return this.f4949a.isMapToolbarEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isMapToolbarEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isIndoorLevelPickerEnabled() {
        try {
            return this.f4949a.isIndoorLevelPickerEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isIndoorLevelPickerEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isCompassEnabled() {
        try {
            return this.f4949a.isCompassEnabled();
        } catch (RemoteException e) {
            LogM.d("UISettings", "isCompassEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        LogM.d("UISettings", "UISettings: ");
        this.f4949a = iUiSettingsDelegate;
    }
}
