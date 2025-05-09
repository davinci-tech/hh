package com.huawei.hms.maps;

import android.graphics.Point;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.StreetViewPanoramaCamera;
import com.huawei.hms.maps.model.StreetViewPanoramaLocation;
import com.huawei.hms.maps.model.StreetViewPanoramaOrientation;
import com.huawei.hms.maps.model.StreetViewSource;

@Deprecated
/* loaded from: classes9.dex */
public class StreetViewPanorama {

    public interface OnStreetViewPanoramaCameraChangeListener {
        void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera);
    }

    public interface OnStreetViewPanoramaChangeListener {
        void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation);
    }

    public interface OnStreetViewPanoramaClickListener {
        void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public interface OnStreetViewPanoramaLongClickListener {
        void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
    }

    public StreetViewPanoramaLocation getLocation() {
        return null;
    }

    public StreetViewPanoramaCamera getPanoramaCamera() {
        return null;
    }

    public boolean isPanningGesturesEnabled() {
        return false;
    }

    public boolean isStreetNamesEnabled() {
        return false;
    }

    public boolean isUserNavigationEnabled() {
        return false;
    }

    public boolean isZoomGesturesEnabled() {
        return false;
    }

    public Point orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        return null;
    }

    public StreetViewPanoramaOrientation pointToOrientation(Point point) {
        return null;
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
    }

    public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
    }

    public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
    }

    public final void setOnStreetViewPanoramaLongClickListener(OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
    }

    public void setPanningGesturesEnabled(boolean z) {
    }

    public void setPosition(LatLng latLng) {
    }

    public void setPosition(LatLng latLng, int i) {
    }

    public void setPosition(LatLng latLng, int i, StreetViewSource streetViewSource) {
    }

    public void setPosition(LatLng latLng, StreetViewSource streetViewSource) {
    }

    public void setPosition(String str) {
    }

    public void setStreetNamesEnabled(boolean z) {
    }

    public void setUserNavigationEnabled(boolean z) {
    }

    public void setZoomGesturesEnabled(boolean z) {
    }
}
