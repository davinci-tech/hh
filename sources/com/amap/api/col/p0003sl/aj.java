package com.amap.api.col.p0003sl;

import android.graphics.Point;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;

/* loaded from: classes2.dex */
public final class aj {
    public static AbstractCameraUpdateMessage a() {
        ai aiVar = new ai();
        aiVar.nowType = AbstractCameraUpdateMessage.Type.zoomBy;
        aiVar.amount = 1.0f;
        return aiVar;
    }

    public static AbstractCameraUpdateMessage b() {
        ai aiVar = new ai();
        aiVar.nowType = AbstractCameraUpdateMessage.Type.zoomBy;
        aiVar.amount = -1.0f;
        return aiVar;
    }

    public static AbstractCameraUpdateMessage a(float f, float f2) {
        ah ahVar = new ah();
        ahVar.nowType = AbstractCameraUpdateMessage.Type.scrollBy;
        ahVar.xPixel = f;
        ahVar.yPixel = f2;
        return ahVar;
    }

    public static AbstractCameraUpdateMessage a(float f) {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        agVar.zoom = f;
        return agVar;
    }

    public static AbstractCameraUpdateMessage b(float f) {
        return a(f, (Point) null);
    }

    public static AbstractCameraUpdateMessage a(float f, Point point) {
        ai aiVar = new ai();
        aiVar.nowType = AbstractCameraUpdateMessage.Type.zoomBy;
        aiVar.amount = f;
        aiVar.focus = point;
        return aiVar;
    }

    public static AbstractCameraUpdateMessage a(CameraPosition cameraPosition) {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        if (cameraPosition != null && cameraPosition.target != null) {
            DPoint latLongToPixelsDouble = VirtualEarthProjection.latLongToPixelsDouble(cameraPosition.target.latitude, cameraPosition.target.longitude, 20);
            agVar.geoPoint = new DPoint(latLongToPixelsDouble.x, latLongToPixelsDouble.y);
            agVar.zoom = cameraPosition.zoom;
            agVar.bearing = cameraPosition.bearing;
            agVar.tilt = cameraPosition.tilt;
            agVar.cameraPosition = cameraPosition;
        }
        return agVar;
    }

    public static AbstractCameraUpdateMessage a(Point point) {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        agVar.geoPoint = new DPoint(point.x, point.y);
        return agVar;
    }

    public static AbstractCameraUpdateMessage c(float f) {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        agVar.tilt = f;
        return agVar;
    }

    public static AbstractCameraUpdateMessage d(float f) {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        agVar.bearing = f;
        return agVar;
    }

    public static AbstractCameraUpdateMessage b(float f, Point point) {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        agVar.geoPoint = new DPoint(point.x, point.y);
        agVar.bearing = f;
        return agVar;
    }

    public static AbstractCameraUpdateMessage a(LatLng latLng) {
        return a(CameraPosition.builder().target(latLng).zoom(Float.NaN).bearing(Float.NaN).tilt(Float.NaN).build());
    }

    public static AbstractCameraUpdateMessage a(LatLng latLng, float f) {
        return a(CameraPosition.builder().target(latLng).zoom(f).bearing(Float.NaN).tilt(Float.NaN).build());
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i) {
        af afVar = new af();
        afVar.nowType = AbstractCameraUpdateMessage.Type.newLatLngBounds;
        afVar.bounds = latLngBounds;
        afVar.paddingLeft = i;
        afVar.paddingRight = i;
        afVar.paddingTop = i;
        afVar.paddingBottom = i;
        return afVar;
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i, int i2, int i3) {
        af afVar = new af();
        afVar.nowType = AbstractCameraUpdateMessage.Type.newLatLngBoundsWithSize;
        afVar.bounds = latLngBounds;
        afVar.paddingLeft = i3;
        afVar.paddingRight = i3;
        afVar.paddingTop = i3;
        afVar.paddingBottom = i3;
        afVar.width = i;
        afVar.height = i2;
        return afVar;
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        af afVar = new af();
        afVar.nowType = AbstractCameraUpdateMessage.Type.newLatLngBounds;
        afVar.bounds = latLngBounds;
        afVar.paddingLeft = i;
        afVar.paddingRight = i2;
        afVar.paddingTop = i3;
        afVar.paddingBottom = i4;
        return afVar;
    }

    public static AbstractCameraUpdateMessage c() {
        return new ag();
    }
}
