package com.huawei.hms.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import androidx.core.content.PermissionChecker;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.internal.ICancelableCallback;
import com.huawei.hms.maps.internal.IHuaweiMapDelegate;
import com.huawei.hms.maps.internal.IInfoWindowAdapter;
import com.huawei.hms.maps.internal.IOnCameraChangeListener;
import com.huawei.hms.maps.internal.IOnCameraIdleListener;
import com.huawei.hms.maps.internal.IOnCameraMoveCanceledListener;
import com.huawei.hms.maps.internal.IOnCameraMoveListener;
import com.huawei.hms.maps.internal.IOnCameraMoveStartedListener;
import com.huawei.hms.maps.internal.IOnCircleClickListener;
import com.huawei.hms.maps.internal.IOnGroundOverlayClickListener;
import com.huawei.hms.maps.internal.IOnIndoorViewListener;
import com.huawei.hms.maps.internal.IOnInfoWindowClickListener;
import com.huawei.hms.maps.internal.IOnInfoWindowCloseListener;
import com.huawei.hms.maps.internal.IOnInfoWindowLongClickListener;
import com.huawei.hms.maps.internal.IOnMapClickListener;
import com.huawei.hms.maps.internal.IOnMapLoadedCallback;
import com.huawei.hms.maps.internal.IOnMapLongClickListener;
import com.huawei.hms.maps.internal.IOnMarkerClickListener;
import com.huawei.hms.maps.internal.IOnMarkerDragListener;
import com.huawei.hms.maps.internal.IOnMyLocationButtonClickListener;
import com.huawei.hms.maps.internal.IOnMyLocationClickListener;
import com.huawei.hms.maps.internal.IOnPoiClickListener;
import com.huawei.hms.maps.internal.IOnPolygonClickListener;
import com.huawei.hms.maps.internal.IOnPolylineClickListener;
import com.huawei.hms.maps.internal.ISnapshotReadyCallback;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.Circle;
import com.huawei.hms.maps.model.CircleOptions;
import com.huawei.hms.maps.model.GroundOverlay;
import com.huawei.hms.maps.model.GroundOverlayOptions;
import com.huawei.hms.maps.model.HeatMap;
import com.huawei.hms.maps.model.HeatMapOptions;
import com.huawei.hms.maps.model.IndoorBuilding;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.LatLngBounds;
import com.huawei.hms.maps.model.MapStyleOptions;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;
import com.huawei.hms.maps.model.MyLocationStyle;
import com.huawei.hms.maps.model.PointOfInterest;
import com.huawei.hms.maps.model.Polygon;
import com.huawei.hms.maps.model.PolygonOptions;
import com.huawei.hms.maps.model.Polyline;
import com.huawei.hms.maps.model.PolylineOptions;
import com.huawei.hms.maps.model.TileOverlay;
import com.huawei.hms.maps.model.TileOverlayOptions;
import com.huawei.hms.maps.model.internal.ICircleDelegate;
import com.huawei.hms.maps.model.internal.IGroundOverlayDelegate;
import com.huawei.hms.maps.model.internal.IMarkerDelegate;
import com.huawei.hms.maps.model.internal.IPolygonDelegate;
import com.huawei.hms.maps.model.internal.IPolylineDelegate;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes4.dex */
public class HuaweiMap {
    public static final int MAP_TYPE_EAGLE = 8;
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;

    /* renamed from: a, reason: collision with root package name */
    private boolean f4903a = false;
    private MyLocationStyle b = null;
    private IHuaweiMapDelegate c;
    private UiSettings d;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    /* loaded from: classes9.dex */
    public interface IndoorViewListener {
        void onIndoorFocus(IndoorBuilding indoorBuilding);

        void onIndoorLeave();
    }

    /* loaded from: classes9.dex */
    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    /* loaded from: classes9.dex */
    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);
    }

    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    /* loaded from: classes9.dex */
    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    /* loaded from: classes9.dex */
    public interface OnCameraMoveStartedListener {
        public static final int REASON_API_ANIMATION = 2;
        public static final int REASON_DEVELOPER_ANIMATION = 3;
        public static final int REASON_GESTURE = 1;

        void onCameraMoveStarted(int i);
    }

    /* loaded from: classes9.dex */
    public interface OnCircleClickListener {
        void onCircleClick(Circle circle);
    }

    /* loaded from: classes9.dex */
    public interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay groundOverlay);
    }

    /* loaded from: classes9.dex */
    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    /* loaded from: classes9.dex */
    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(Marker marker);
    }

    /* loaded from: classes9.dex */
    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(Marker marker);
    }

    /* loaded from: classes9.dex */
    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    /* loaded from: classes9.dex */
    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    /* loaded from: classes9.dex */
    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    /* loaded from: classes9.dex */
    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    /* loaded from: classes9.dex */
    public interface OnMyLocationClickListener {
        void onMyLocationClick(Location location);
    }

    /* loaded from: classes9.dex */
    public interface OnPoiClickListener {
        void onPoiClick(PointOfInterest pointOfInterest);
    }

    /* loaded from: classes9.dex */
    public interface OnPolygonClickListener {
        void onPolygonClick(Polygon polygon);
    }

    /* loaded from: classes9.dex */
    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline polyline);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    public boolean useViewLifecycleWhenInFragment() {
        try {
            return this.c.useViewLifecycleWhenInFragment();
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "useViewLifecycleWhenInFragment RemoteException: " + e.toString());
            return true;
        }
    }

    public void switchIndoorFloor(String str, String str2) {
        try {
            this.c.switchIndoorFloor(str, str2);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "switchIndoorFloor RemoteException: " + e.toString());
        }
    }

    public void stopAnimation() {
        try {
            this.c.stopAnimation();
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "stopAnimation" + e);
        }
    }

    public void snapshot(final SnapshotReadyCallback snapshotReadyCallback, Bitmap bitmap) {
        try {
            this.c.snapshot(new ISnapshotReadyCallback.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.18
                @Override // com.huawei.hms.maps.internal.ISnapshotReadyCallback
                public void onSnapshotReadyWrapper(IObjectWrapper iObjectWrapper) {
                }

                @Override // com.huawei.hms.maps.internal.ISnapshotReadyCallback
                public void onSnapshotReady(Bitmap bitmap2) {
                    SnapshotReadyCallback snapshotReadyCallback2 = snapshotReadyCallback;
                    if (snapshotReadyCallback2 == null) {
                        return;
                    }
                    snapshotReadyCallback2.onSnapshotReady(bitmap2);
                }
            }, ObjectWrapper.wrap(bitmap));
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "snapshot" + e);
        }
    }

    public void snapshot(final SnapshotReadyCallback snapshotReadyCallback) {
        try {
            this.c.snapshotForTest(new ISnapshotReadyCallback.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.19
                @Override // com.huawei.hms.maps.internal.ISnapshotReadyCallback
                public void onSnapshotReadyWrapper(IObjectWrapper iObjectWrapper) {
                }

                @Override // com.huawei.hms.maps.internal.ISnapshotReadyCallback
                public void onSnapshotReady(Bitmap bitmap) {
                    SnapshotReadyCallback snapshotReadyCallback2 = snapshotReadyCallback;
                    if (snapshotReadyCallback2 == null) {
                        return;
                    }
                    snapshotReadyCallback2.onSnapshotReady(bitmap);
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "snapshot" + e);
        }
    }

    public void setWatermarkEnabled(boolean z) {
        try {
            this.c.setWatermarkEnabled(z);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setWatermarkEnabled RemoteException: " + e.toString());
        }
    }

    public void setTrafficEnabled(boolean z) {
        try {
            this.c.setTrafficEnabled(z);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setTrafficEnabled RemoteException: " + e.toString());
        }
    }

    public void setStyleId(String str) {
        try {
            this.c.setStyleId(str);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setStyleId RemoteException = " + e.getMessage());
        }
    }

    public void setPointToCenter(int i, int i2) {
        try {
            this.c.setPointToCenter(i, i2);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setPointToCenter RemoteException: " + e.toString());
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        try {
            this.c.setPadding(i, i2, i3, i4);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setPadding RemoteException: " + e.toString());
        }
    }

    public void setOnPolylineClickListener(final OnPolylineClickListener onPolylineClickListener) {
        LogM.d("HuaweiMap", "setOnPolylineClickListener: ");
        try {
            this.c.setPolylineClickListener(onPolylineClickListener == null ? null : new IOnPolylineClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.13
                @Override // com.huawei.hms.maps.internal.IOnPolylineClickListener
                public void onPolylineClick(IPolylineDelegate iPolylineDelegate) {
                    LogM.i("HuaweiMap", "onPolylineClick first: ");
                    Polyline polyline = new Polyline(iPolylineDelegate);
                    OnPolylineClickListener onPolylineClickListener2 = onPolylineClickListener;
                    if (onPolylineClickListener2 != null) {
                        onPolylineClickListener2.onPolylineClick(polyline);
                    }
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnPolygonClickListener(final OnPolygonClickListener onPolygonClickListener) {
        LogM.d("HuaweiMap", "setOnPolygonClickListener: ");
        try {
            this.c.setPolygonClickListener(onPolygonClickListener == null ? null : new IOnPolygonClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.14
                @Override // com.huawei.hms.maps.internal.IOnPolygonClickListener
                public void onPolygonClick(IPolygonDelegate iPolygonDelegate) {
                    LogM.i("HuaweiMap", "onPolygonClick entrance: ");
                    Polygon polygon = new Polygon(iPolygonDelegate);
                    OnPolygonClickListener onPolygonClickListener2 = onPolygonClickListener;
                    if (onPolygonClickListener2 != null) {
                        onPolygonClickListener2.onPolygonClick(polygon);
                    }
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnPoiClickListener(final OnPoiClickListener onPoiClickListener) {
        try {
            this.c.setOnPoiClickListener(onPoiClickListener == null ? null : new IOnPoiClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.21
                @Override // com.huawei.hms.maps.internal.IOnPoiClickListener
                public void onPoiClick(PointOfInterest pointOfInterest) {
                    onPoiClickListener.onPoiClick(pointOfInterest);
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setOnPoiClickListener" + e);
        }
    }

    public void setOnMyLocationClickListener(final OnMyLocationClickListener onMyLocationClickListener) {
        try {
            this.c.setOnMyLocationClickListener(onMyLocationClickListener == null ? null : new IOnMyLocationClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.20
                @Override // com.huawei.hms.maps.internal.IOnMyLocationClickListener
                public void onMyLocationClick(Location location) {
                    onMyLocationClickListener.onMyLocationClick(location);
                }
            });
        } catch (RemoteException unused) {
            LogM.e("HuaweiMap", "setOnMyLocationClickListener RemoteException");
        }
    }

    public void setOnMyLocationButtonClickListener(final OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        LogM.d("HuaweiMap", "setOnMyLocationButtonClickListener: ");
        try {
            this.c.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener == null ? null : new IOnMyLocationButtonClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.17
                @Override // com.huawei.hms.maps.internal.IOnMyLocationButtonClickListener
                public boolean onMyLocationButtonClick() {
                    return onMyLocationButtonClickListener.onMyLocationButtonClick();
                }
            });
        } catch (RemoteException unused) {
            LogM.e("HuaweiMap", "RemoteException: ");
        }
    }

    public void setOnMarkerDragListener(final OnMarkerDragListener onMarkerDragListener) {
        LogM.d("HuaweiMap", "onMarkerDrag setListener start: ");
        try {
            this.c.setMarkerDragListener(onMarkerDragListener == null ? null : new IOnMarkerDragListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.11
                @Override // com.huawei.hms.maps.internal.IOnMarkerDragListener
                public void onMarkerDragStart(IMarkerDelegate iMarkerDelegate) {
                    LogM.i("HuaweiMap", "onMarkerDrag callback start: ");
                    onMarkerDragListener.onMarkerDragStart(new Marker(iMarkerDelegate));
                }

                @Override // com.huawei.hms.maps.internal.IOnMarkerDragListener
                public void onMarkerDragEnd(IMarkerDelegate iMarkerDelegate) {
                    LogM.i("HuaweiMap", "onMarkerDrag callback start: ");
                    onMarkerDragListener.onMarkerDragEnd(new Marker(iMarkerDelegate));
                }

                @Override // com.huawei.hms.maps.internal.IOnMarkerDragListener
                public void onMarkerDrag(IMarkerDelegate iMarkerDelegate) {
                    LogM.i("HuaweiMap", "onMarkerDrag callback start: ");
                    onMarkerDragListener.onMarkerDrag(new Marker(iMarkerDelegate));
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnMarkerClickListener(final OnMarkerClickListener onMarkerClickListener) {
        try {
            this.c.setMarkerClickListener(onMarkerClickListener == null ? null : new IOnMarkerClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.10
                @Override // com.huawei.hms.maps.internal.IOnMarkerClickListener
                public boolean onMarkerClick(IMarkerDelegate iMarkerDelegate) {
                    Marker marker = new Marker(iMarkerDelegate);
                    OnMarkerClickListener onMarkerClickListener2 = onMarkerClickListener;
                    if (onMarkerClickListener2 != null) {
                        return onMarkerClickListener2.onMarkerClick(marker);
                    }
                    return false;
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnMapLongClickListener(final OnMapLongClickListener onMapLongClickListener) {
        LogM.d("HuaweiMap", "setOnMapLongClickListener");
        try {
            this.c.setOnMapLongClickListener(onMapLongClickListener == null ? null : new IOnMapLongClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.16
                @Override // com.huawei.hms.maps.internal.IOnMapLongClickListener
                public void onMapLongClick(LatLng latLng) {
                    LogM.d("HuaweiMap", "onMapLongClick");
                    onMapLongClickListener.onMapLongClick(latLng);
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnMapLoadedCallback(final OnMapLoadedCallback onMapLoadedCallback) {
        try {
            this.c.setOnMapLoadedCallback(new IOnMapLoadedCallback.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.9
                @Override // com.huawei.hms.maps.internal.IOnMapLoadedCallback
                public void onMapLoaded() {
                    OnMapLoadedCallback onMapLoadedCallback2 = onMapLoadedCallback;
                    if (onMapLoadedCallback2 == null) {
                        return;
                    }
                    onMapLoadedCallback2.onMapLoaded();
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setOnMapLoadedCallback: " + e.getMessage());
        }
    }

    public void setOnMapClickListener(final OnMapClickListener onMapClickListener) {
        LogM.d("HuaweiMap", "setOnMapClickListener: ");
        try {
            this.c.setOnMapClickListener(onMapClickListener == null ? null : new IOnMapClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.15
                @Override // com.huawei.hms.maps.internal.IOnMapClickListener
                public void onMapClick(LatLng latLng) {
                    onMapClickListener.onMapClick(latLng);
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnInfoWindowLongClickListener(final OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        try {
            this.c.setOnInfoWindowLongClickListener(onInfoWindowLongClickListener == null ? null : new IOnInfoWindowLongClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.25
                @Override // com.huawei.hms.maps.internal.IOnInfoWindowLongClickListener
                public void onInfoWindowLongClick(IMarkerDelegate iMarkerDelegate) {
                    onInfoWindowLongClickListener.onInfoWindowLongClick(new Marker(iMarkerDelegate));
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setOnInfoWindowLongClickListener RemoteException: " + e.toString());
        }
    }

    public void setOnInfoWindowCloseListener(final OnInfoWindowCloseListener onInfoWindowCloseListener) {
        try {
            this.c.setOnInfoWindowCloseListener(onInfoWindowCloseListener == null ? null : new IOnInfoWindowCloseListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.24
                @Override // com.huawei.hms.maps.internal.IOnInfoWindowCloseListener
                public void onInfoWindowClose(IMarkerDelegate iMarkerDelegate) {
                    onInfoWindowCloseListener.onInfoWindowClose(new Marker(iMarkerDelegate));
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setOnInfoWindowCloseListener RemoteException: " + e.toString());
        }
    }

    public void setOnInfoWindowClickListener(final OnInfoWindowClickListener onInfoWindowClickListener) {
        try {
            this.c.setOnInfoWindowClickListener(onInfoWindowClickListener == null ? null : new IOnInfoWindowClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.22
                @Override // com.huawei.hms.maps.internal.IOnInfoWindowClickListener
                public void onInfoWindowClick(IMarkerDelegate iMarkerDelegate) {
                    LogM.i("HuaweiMap", "onInfoWindowClick callback start: ");
                    if (iMarkerDelegate == null) {
                        LogM.i("HuaweiMap", "markerDelegate is null");
                    } else {
                        onInfoWindowClickListener.onInfoWindowClick(new Marker(iMarkerDelegate));
                    }
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnGroundOverlayClickListener(final OnGroundOverlayClickListener onGroundOverlayClickListener) {
        try {
            this.c.setOnGroundOverlayClickListener(onGroundOverlayClickListener == null ? null : new IOnGroundOverlayClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.23
                @Override // com.huawei.hms.maps.internal.IOnGroundOverlayClickListener
                public void onGroundOverlayClick(IGroundOverlayDelegate iGroundOverlayDelegate) {
                    onGroundOverlayClickListener.onGroundOverlayClick(new GroundOverlay(iGroundOverlayDelegate));
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setOnGroundOverlayClickListener RemoteException: " + e.toString());
        }
    }

    public void setOnCircleClickListener(final OnCircleClickListener onCircleClickListener) {
        LogM.d("HuaweiMap", "onCircleClick setListener start: ");
        try {
            this.c.setCircleClickListener(onCircleClickListener == null ? null : new IOnCircleClickListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.12
                @Override // com.huawei.hms.maps.internal.IOnCircleClickListener
                public void onCircleClick(ICircleDelegate iCircleDelegate) {
                    LogM.i("HuaweiMap", "onCircleClick callback start: ");
                    Circle circle = new Circle(iCircleDelegate);
                    OnCircleClickListener onCircleClickListener2 = onCircleClickListener;
                    if (onCircleClickListener2 != null) {
                        onCircleClickListener2.onCircleClick(circle);
                    }
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnCameraMoveStartedListener(final OnCameraMoveStartedListener onCameraMoveStartedListener) {
        LogM.d("HuaweiMap", "setOnCameraMoveStartedListener: ");
        try {
            this.c.setCameraMoveStartedListener(onCameraMoveStartedListener == null ? null : new IOnCameraMoveStartedListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.1
                @Override // com.huawei.hms.maps.internal.IOnCameraMoveStartedListener
                public void onCameraMoveStarted(int i) {
                    LogM.d("HuaweiMap", "onCameraMoveStarted: " + i);
                    onCameraMoveStartedListener.onCameraMoveStarted(i);
                }
            });
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnCameraMoveListener(final OnCameraMoveListener onCameraMoveListener) {
        LogM.d("HuaweiMap", "setCameraMoveListener: ");
        try {
            this.c.setCameraMoveListener(onCameraMoveListener == null ? null : new IOnCameraMoveListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.3
                @Override // com.huawei.hms.maps.internal.IOnCameraMoveListener
                public void onCameraMove() {
                    LogM.v("HuaweiMap", "onCameraMove: ");
                    onCameraMoveListener.onCameraMove();
                }
            });
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnCameraMoveCanceledListener(final OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        LogM.d("HuaweiMap", "HuaweiMap does not support ");
        try {
            this.c.setCameraMoveCanceledListener(onCameraMoveCanceledListener == null ? null : new IOnCameraMoveCanceledListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.4
                @Override // com.huawei.hms.maps.internal.IOnCameraMoveCanceledListener
                public void onCameraMoveCanceled() {
                    LogM.d("HuaweiMap", "onCameraMoveCanceled");
                    onCameraMoveCanceledListener.onCameraMoveCanceled();
                }
            });
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnCameraIdleListener(final OnCameraIdleListener onCameraIdleListener) {
        LogM.d("HuaweiMap", "setOnCameraIdleListener: ");
        try {
            this.c.setCameraIdleListener(onCameraIdleListener == null ? null : new IOnCameraIdleListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.2
                @Override // com.huawei.hms.maps.internal.IOnCameraIdleListener
                public void onCameraIdle() {
                    LogM.d("HuaweiMap", "onCameraIdle: ");
                    onCameraIdleListener.onCameraIdle();
                }
            });
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setOnCameraChangeListener(final OnCameraChangeListener onCameraChangeListener) {
        try {
            this.c.setOnCameraChangeListener(onCameraChangeListener == null ? null : new IOnCameraChangeListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.26
                @Override // com.huawei.hms.maps.internal.IOnCameraChangeListener
                public void onCameraChange(CameraPosition cameraPosition) {
                    LogM.d("HuaweiMap", "onCameraChange");
                    onCameraChangeListener.onCameraChange(cameraPosition);
                }
            });
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        try {
            LogM.d("HuaweiMap", "set my location style");
            this.c.setMyLocationStyle(myLocationStyle);
            this.b = myLocationStyle;
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setMyLocationStyle RemoteException: " + e.toString());
        }
    }

    public void setMyLocationEnabled(boolean z) {
        Context appContext;
        if (z && !this.f4903a && (appContext = MapClientIdentify.getAppContext()) != null && PermissionChecker.checkSelfPermission(appContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && PermissionChecker.checkSelfPermission(appContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            throw new SecurityException("the permission is not granted, my location requires permission ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION.");
        }
        try {
            this.c.setMyLocationEnabled(z);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setMyLocationEnabled RemoteException: " + e.toString());
        }
    }

    public void setMinZoomPreference(float f) {
        try {
            this.c.setMinZoomPreference(f);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setMinZoomPreference RemoteException: " + e.toString());
        }
    }

    public void setMaxZoomPreference(float f) {
        try {
            this.c.setMaxZoomPreference(f);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setMaxZoomPreference RemoteException: " + e.toString());
        }
    }

    public void setMarkersClustering(boolean z) {
        try {
            this.c.setMarkersClustering(z);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setMapType(int i) {
        try {
            this.c.setMapType(i);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setMapType RemoteException: " + e.toString());
        }
    }

    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) {
        try {
            return this.c.setMapStyle(mapStyleOptions);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
            return false;
        }
    }

    public void setLocationSource(LocationSource locationSource) {
        try {
            if (locationSource == null) {
                this.f4903a = false;
                this.c.setLocationSource(null);
            } else {
                this.f4903a = true;
                this.c.setLocationSource(new com.huawei.hms.maps.internal.maa(locationSource));
            }
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        try {
            this.c.setLatLngBoundsForCameraTarget(latLngBounds);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setLanguage(String str) {
        try {
            LogM.i("HuaweiMap", "setLanguage : " + str);
            this.c.setLanguage(str);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setLanguage RemoteException: " + e.toString());
        }
    }

    public void setInfoWindowAdapter(final InfoWindowAdapter infoWindowAdapter) {
        try {
            if (infoWindowAdapter == null) {
                this.c.setInfoWindowAdapter(null);
            } else {
                this.c.setInfoWindowAdapter(new IInfoWindowAdapter.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.8
                    @Override // com.huawei.hms.maps.internal.IInfoWindowAdapter
                    public IObjectWrapper getInfoWindow(IMarkerDelegate iMarkerDelegate) {
                        InfoWindowAdapter infoWindowAdapter2 = infoWindowAdapter;
                        if (infoWindowAdapter2 == null) {
                            return null;
                        }
                        return ObjectWrapper.wrap(infoWindowAdapter2.getInfoWindow(new Marker(iMarkerDelegate)));
                    }

                    @Override // com.huawei.hms.maps.internal.IInfoWindowAdapter
                    public IObjectWrapper getInfoContents(IMarkerDelegate iMarkerDelegate) {
                        InfoWindowAdapter infoWindowAdapter2 = infoWindowAdapter;
                        if (infoWindowAdapter2 == null) {
                            return null;
                        }
                        return ObjectWrapper.wrap(infoWindowAdapter2.getInfoContents(new Marker(iMarkerDelegate)));
                    }
                });
            }
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public void setIndoorViewListener(final IndoorViewListener indoorViewListener) {
        try {
            this.c.setIndoorViewListener(indoorViewListener == null ? null : new IOnIndoorViewListener.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.5
                @Override // com.huawei.hms.maps.internal.IOnIndoorViewListener
                public void onIndoorLeave() {
                    LogM.d("HuaweiMap", "onIndoorLeave callback start: ");
                    indoorViewListener.onIndoorLeave();
                }

                @Override // com.huawei.hms.maps.internal.IOnIndoorViewListener
                public void onIndoorFocus(IndoorBuilding indoorBuilding) {
                    LogM.d("HuaweiMap", "onIndoorFocus callback start: ");
                    indoorViewListener.onIndoorFocus(indoorBuilding);
                }
            });
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setIndoorViewListener RemoteException: " + e.toString());
        }
    }

    public boolean setIndoorEnabled(boolean z) {
        try {
            return this.c.setIndoorEnabled(z);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setIndoorEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    @Deprecated
    public void setGeoPoliticalView(String str) {
        try {
            LogM.i("HuaweiMap", "setGeoPoliticalView : " + str);
            this.c.setGeoPoliticalView(str);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setGeoPoliticalView RemoteException: " + e.toString());
        }
    }

    public void setGcj02CoordinateEnabled(boolean z) {
        try {
            this.c.setGcj02CoordinateEnabled(z);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setGcj02CoordinateEnabled RemoteException: " + e.toString());
        }
    }

    public void setFrameTime(int i) {
        try {
            if (i <= 0) {
                LogM.i("HuaweiMap", "frameTime cannot be less than 0");
            } else {
                this.c.setFrameTime(i);
            }
        } catch (Exception e) {
            LogM.d("HuaweiMap", "setFrameTime RemoteException: " + e.toString());
        }
    }

    public void setDark(boolean z) {
        try {
            this.c.setDark(z);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setDark RemoteException = " + e.getMessage());
        }
    }

    public void setContentDescription(String str) {
        try {
            this.c.setContentDescription(str);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "setContentDescription RemoteException: " + e.toString());
        }
    }

    public void setCommonDir(int i, String str) {
        try {
            this.c.setCommonDir(i, str);
        } catch (Exception e) {
            LogM.d("HuaweiMap", "setCommonDir RemoteException: " + e.toString());
        }
    }

    public void setBuildingsEnabled(boolean z) {
        try {
            this.c.setBuildingsEnabled(z);
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "setBuildingsEnabled RemoteException: " + e.toString());
        }
    }

    public void resetMinMaxZoomPreference() {
        try {
            this.c.resetMinMaxZoomPreference();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "resetMinMaxZoomPreference RemoteException: " + e.toString());
        }
    }

    public void previewId(String str) {
        try {
            this.c.previewId(str);
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "previewId RemoteException = " + e.getMessage());
        }
    }

    public void moveCamera(CameraUpdate cameraUpdate) {
        try {
            LogM.d("HuaweiMap", "moveCamera begin");
            this.c.moveCamera(cameraUpdate.getCameraUpdate());
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public boolean isTrafficEnabled() {
        try {
            return this.c.isTrafficEnabled();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "isTrafficEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isMyLocationEnabled() {
        try {
            return this.c.isMyLocationEnabled();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "isMyLocationEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isIndoorEnabled() {
        try {
            return this.c.isIndoorEnabled();
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "isIndoorEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isDark() {
        try {
            return this.c.isDark();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "isDark RemoteException: " + e.toString());
            return false;
        }
    }

    public boolean isBuildingsEnabled() {
        try {
            return this.c.isBuildingsEnabled();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "isBuildingsEnabled RemoteException: " + e.toString());
            return false;
        }
    }

    public UiSettings getUiSettings() {
        try {
            UiSettings uiSettings = new UiSettings(this.c.getUiSettings());
            this.d = uiSettings;
            return uiSettings;
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "getUiSettings RemoteException: " + e.toString());
            return null;
        }
    }

    public float getScalePerPixel() {
        try {
            return this.c.getScalePerPixel();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "getScalePerPixel RemoteException: " + e.toString());
            return 0.0f;
        }
    }

    public Projection getProjection() {
        try {
            return new Projection(this.c.getProjection());
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "getProjection: " + e.getMessage());
            return null;
        }
    }

    public MyLocationStyle getMyLocationStyle() {
        return this.b;
    }

    public float getMinZoomLevel() {
        try {
            return this.c.getMinZoomLevel();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "getMinZoomLevel RemoteException: " + e.toString());
            return 0.0f;
        }
    }

    public float getMaxZoomLevel() {
        try {
            return this.c.getMaxZoomLevel();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "getMaxZoomLevel RemoteException: " + e.toString());
            return 0.0f;
        }
    }

    public int getMapType() {
        try {
            return this.c.getMapType();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "getMapType RemoteException: " + e.toString());
            return 0;
        }
    }

    public String getCurrentFloor() {
        try {
            return this.c.getCurrentFloor();
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "getCurrentFloor RemoteException: " + e.toString());
            return "CLOSE";
        }
    }

    public CameraPosition getCameraPosition() {
        try {
            LogM.v("HuaweiMap", "getCameraPosition begin");
            return this.c.getCameraPosition();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
            return null;
        }
    }

    public void clear() {
        try {
            this.c.clear();
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "clear RemoteException: " + e.toString());
        }
    }

    public void animateCamera(CameraUpdate cameraUpdate, final CancelableCallback cancelableCallback) {
        LogM.d("HuaweiMap", "animateCamera(update,callback)");
        try {
            this.c.animateCameraWithCallback(cameraUpdate.getCameraUpdate(), cancelableCallback == null ? null : new ICancelableCallback.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.6
                @Override // com.huawei.hms.maps.internal.ICancelableCallback
                public void onFinish() {
                    cancelableCallback.onFinish();
                }

                @Override // com.huawei.hms.maps.internal.ICancelableCallback
                public void onCancel() {
                    cancelableCallback.onCancel();
                }
            });
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException" + e.toString());
        }
    }

    public void animateCamera(CameraUpdate cameraUpdate, int i, final CancelableCallback cancelableCallback) {
        try {
            this.c.animateCameraWithDurationAndCallback(cameraUpdate.getCameraUpdate(), i, cancelableCallback == null ? null : new ICancelableCallback.Stub() { // from class: com.huawei.hms.maps.HuaweiMap.7
                @Override // com.huawei.hms.maps.internal.ICancelableCallback
                public void onFinish() {
                    cancelableCallback.onFinish();
                }

                @Override // com.huawei.hms.maps.internal.ICancelableCallback
                public void onCancel() {
                    cancelableCallback.onCancel();
                }
            });
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException" + e.toString());
        }
    }

    public void animateCamera(CameraUpdate cameraUpdate) {
        try {
            LogM.d("HuaweiMap", "animateCamera begin");
            this.c.animateCamera(cameraUpdate.getCameraUpdate());
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "RemoteException: " + e.toString());
        }
    }

    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        if (tileOverlayOptions.getTileProvider() == null) {
            throw new IllegalArgumentException("TileProvider must not be null");
        }
        try {
            return new TileOverlay(this.c.addTileOverlay(tileOverlayOptions));
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "addTileOverlay RemoteException: " + e.toString());
            return null;
        }
    }

    public Polyline addPolyline(PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.c.addPolyline(polylineOptions));
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "addPolyline RemoteException: " + e.toString());
            return null;
        }
    }

    public Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            return new Polygon(this.c.addPolygon(polygonOptions));
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "addPolygon RemoteException: " + e.toString());
            return null;
        }
    }

    public Marker addMarker(MarkerOptions markerOptions) {
        try {
            return new Marker(this.c.addMarker(markerOptions));
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
            return null;
        }
    }

    public HeatMap addHeatMap(String str, HeatMapOptions heatMapOptions) {
        try {
            return new HeatMap(this.c.addHeatMap(str, heatMapOptions));
        } catch (RemoteException e) {
            LogM.e("HuaweiMap", "RemoteException: " + e.toString());
            return null;
        }
    }

    public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        if (groundOverlayOptions.getImage() == null) {
            throw new IllegalArgumentException("GroundOverlayOptions image must not be null");
        }
        try {
            IGroundOverlayDelegate addGroundOverlay = this.c.addGroundOverlay(groundOverlayOptions);
            if (addGroundOverlay != null) {
                return new GroundOverlay(addGroundOverlay);
            }
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "addGroundOverlay RemoteException: " + e.toString());
        }
        return null;
    }

    public Circle addCircle(CircleOptions circleOptions) {
        try {
            return new Circle(this.c.addCircle(circleOptions));
        } catch (RemoteException e) {
            LogM.d("HuaweiMap", "addCircle RemoteException: " + e.toString());
            return null;
        }
    }

    HuaweiMap(IHuaweiMapDelegate iHuaweiMapDelegate) {
        this.c = iHuaweiMapDelegate;
        try {
            BitmapDescriptorFactory.setIBitmapDescriptorDelegate(iHuaweiMapDelegate.getBitmapDescriptor());
        } catch (RemoteException unused) {
            LogM.e("HuaweiMap", "getBitmapDescriptor RemoteException: ");
        }
    }
}
