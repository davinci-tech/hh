package com.huawei.hms.maps;

import android.graphics.Point;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.CameraUpdateParam;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.LatLngBounds;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes4.dex */
public final class CameraUpdateFactory {
    public static CameraUpdate zoomTo(float f) {
        LogM.d("CameraUpdateFactory", "zoomTo ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setZoomTo(new CameraUpdateParam.ZoomTo(f));
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate zoomOut() {
        return zoomBy(-1.0f);
    }

    public static CameraUpdate zoomIn() {
        LogM.d("CameraUpdateFactory", "zoomIn ");
        return zoomBy(1.0f);
    }

    public static CameraUpdate zoomBy(float f, Point point) {
        LogM.d("CameraUpdateFactory", "zoomBy: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setZoomByWithFocus(new CameraUpdateParam.ZoomByWithFocus(f, point));
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate zoomBy(float f) {
        LogM.d("CameraUpdateFactory", "zoomBy: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setZoomBy(new CameraUpdateParam.ZoomBy(f));
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate scrollBy(float f, float f2) {
        LogM.d("CameraUpdateFactory", "scrollBy: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setScrollBy(new CameraUpdateParam.ScrollBy(f, f2));
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f) {
        LogM.d("CameraUpdateFactory", "newLatLngZoom: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setNewLatLngZoom(new CameraUpdateParam.NewLatLngZoom(latLng, f));
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3) {
        LogM.d("CameraUpdateFactory", "newLatLngBoundsWithSize: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setNewLatLngBoundsWidthHeight(new CameraUpdateParam.NewLatLngBoundsWidthHeight(latLngBounds, i, i2, i3));
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        LogM.d("CameraUpdateFactory", "newLatLngBounds: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setNewLatLngBounds(new CameraUpdateParam.NewLatLngBounds(latLngBounds, i));
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        LogM.d("CameraUpdateFactory", "newLatLng: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setLatLng(latLng);
        return new CameraUpdate(cameraUpdateParam);
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        LogM.d("CameraUpdateFactory", "newCameraPosition: ");
        CameraUpdateParam cameraUpdateParam = new CameraUpdateParam();
        cameraUpdateParam.setCameraPosition(cameraPosition);
        return new CameraUpdate(cameraUpdateParam);
    }

    private CameraUpdateFactory() {
    }
}
