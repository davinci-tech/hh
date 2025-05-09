package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.maps.internal.IProjectionDelegate;
import com.huawei.hms.maps.internal.IUiSettingsDelegate;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.CameraUpdateParam;
import com.huawei.hms.maps.model.CircleOptions;
import com.huawei.hms.maps.model.GroundOverlayOptions;
import com.huawei.hms.maps.model.HeatMapOptions;
import com.huawei.hms.maps.model.LatLngBounds;
import com.huawei.hms.maps.model.MapStyleOptions;
import com.huawei.hms.maps.model.MarkerOptions;
import com.huawei.hms.maps.model.MyLocationStyle;
import com.huawei.hms.maps.model.PolygonOptions;
import com.huawei.hms.maps.model.PolylineOptions;
import com.huawei.hms.maps.model.TileOverlayOptions;
import com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate;
import com.huawei.hms.maps.model.internal.ICircleDelegate;
import com.huawei.hms.maps.model.internal.IGroundOverlayDelegate;
import com.huawei.hms.maps.model.internal.IHeatMapDelegate;
import com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate;
import com.huawei.hms.maps.model.internal.IMarkerDelegate;
import com.huawei.hms.maps.model.internal.IPolygonDelegate;
import com.huawei.hms.maps.model.internal.IPolylineDelegate;
import com.huawei.hms.maps.model.internal.ITileOverlayDelegate;

/* loaded from: classes4.dex */
public interface IHuaweiMapDelegate extends IInterface {
    ICircleDelegate addCircle(CircleOptions circleOptions);

    IGroundOverlayDelegate addGroundOverlay(GroundOverlayOptions groundOverlayOptions);

    IHeatMapDelegate addHeatMap(String str, HeatMapOptions heatMapOptions);

    IMarkerDelegate addMarker(MarkerOptions markerOptions);

    IPolygonDelegate addPolygon(PolygonOptions polygonOptions);

    IPolylineDelegate addPolyline(PolylineOptions polylineOptions);

    ITileOverlayDelegate addTileOverlay(TileOverlayOptions tileOverlayOptions);

    void animateCamera(CameraUpdateParam cameraUpdateParam);

    void animateCameraWithCallback(CameraUpdateParam cameraUpdateParam, ICancelableCallback iCancelableCallback);

    void animateCameraWithDurationAndCallback(CameraUpdateParam cameraUpdateParam, int i, ICancelableCallback iCancelableCallback);

    void clear();

    IBitmapDescriptorDelegate getBitmapDescriptor();

    CameraPosition getCameraPosition();

    String getCurrentFloor();

    IIndoorBuildingDelegate getFocusedBuilding();

    int getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    IProjectionDelegate getProjection();

    float getScalePerPixel();

    IUiSettingsDelegate getUiSettings();

    boolean isBuildingsEnabled();

    boolean isDark();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    void moveCamera(CameraUpdateParam cameraUpdateParam);

    void previewId(String str);

    void resetMinMaxZoomPreference();

    void setBuildingsEnabled(boolean z);

    void setCameraIdleListener(IOnCameraIdleListener iOnCameraIdleListener);

    void setCameraMoveCanceledListener(IOnCameraMoveCanceledListener iOnCameraMoveCanceledListener);

    void setCameraMoveListener(IOnCameraMoveListener iOnCameraMoveListener);

    void setCameraMoveStartedListener(IOnCameraMoveStartedListener iOnCameraMoveStartedListener);

    void setCircleClickListener(IOnCircleClickListener iOnCircleClickListener);

    void setCommonDir(int i, String str);

    void setContentDescription(String str);

    void setDark(boolean z);

    void setFrameTime(int i);

    void setGcj02CoordinateEnabled(boolean z);

    void setGeoPoliticalView(String str);

    boolean setIndoorEnabled(boolean z);

    void setIndoorViewListener(IOnIndoorViewListener iOnIndoorViewListener);

    void setInfoWindowAdapter(IInfoWindowAdapter iInfoWindowAdapter);

    void setLanguage(String str);

    void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds);

    void setLiteMode(boolean z);

    void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate);

    boolean setMapStyle(MapStyleOptions mapStyleOptions);

    void setMapType(int i);

    void setMarkerClickListener(IOnMarkerClickListener iOnMarkerClickListener);

    void setMarkerDragListener(IOnMarkerDragListener iOnMarkerDragListener);

    void setMarkersClustering(boolean z);

    void setMaxZoomPreference(float f);

    void setMinZoomPreference(float f);

    void setMyLocationEnabled(boolean z);

    void setMyLocationStyle(MyLocationStyle myLocationStyle);

    void setOnCameraChangeListener(IOnCameraChangeListener iOnCameraChangeListener);

    void setOnGroundOverlayClickListener(IOnGroundOverlayClickListener iOnGroundOverlayClickListener);

    void setOnIndoorStateChangeListener(IOnIndoorStateChangeListener iOnIndoorStateChangeListener);

    void setOnInfoWindowClickListener(IOnInfoWindowClickListener iOnInfoWindowClickListener);

    void setOnInfoWindowCloseListener(IOnInfoWindowCloseListener iOnInfoWindowCloseListener);

    void setOnInfoWindowLongClickListener(IOnInfoWindowLongClickListener iOnInfoWindowLongClickListener);

    void setOnMapClickListener(IOnMapClickListener iOnMapClickListener);

    void setOnMapLoadedCallback(IOnMapLoadedCallback iOnMapLoadedCallback);

    void setOnMapLongClickListener(IOnMapLongClickListener iOnMapLongClickListener);

    void setOnMyLocationButtonClickListener(IOnMyLocationButtonClickListener iOnMyLocationButtonClickListener);

    void setOnMyLocationChangeListener(IOnMyLocationChangeListener iOnMyLocationChangeListener);

    void setOnMyLocationClickListener(IOnMyLocationClickListener iOnMyLocationClickListener);

    void setOnPoiClickListener(IOnPoiClickListener iOnPoiClickListener);

    void setOnPolygonClickListener(IOnPolygonClickListener iOnPolygonClickListener);

    void setOnPolylineClickListener(IOnPolylineClickListener iOnPolylineClickListener);

    void setPadding(int i, int i2, int i3, int i4);

    void setPointToCenter(int i, int i2);

    void setPolygonClickListener(IOnPolygonClickListener iOnPolygonClickListener);

    void setPolylineClickListener(IOnPolylineClickListener iOnPolylineClickListener);

    void setStyleId(String str);

    void setTrafficEnabled(boolean z);

    void setWatermarkEnabled(boolean z);

    void snapshot(ISnapshotReadyCallback iSnapshotReadyCallback, IObjectWrapper iObjectWrapper);

    void snapshotForTest(ISnapshotReadyCallback iSnapshotReadyCallback);

    void stopAnimation();

    void switchIndoorFloor(String str, String str2);

    boolean useViewLifecycleWhenInFragment();

    public static abstract class Stub extends Binder implements IHuaweiMapDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IHuaweiMapDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4955a;

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean useViewLifecycleWhenInFragment() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 21);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void switchIndoorFloor(String str, String str2) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 76, str, str2);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void stopAnimation() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 66);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void snapshotForTest(ISnapshotReadyCallback iSnapshotReadyCallback) {
                com.huawei.hms.maps.model.internal.mab.a(iSnapshotReadyCallback != null ? iSnapshotReadyCallback.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 65);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void snapshot(ISnapshotReadyCallback iSnapshotReadyCallback, IObjectWrapper iObjectWrapper) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 64, iSnapshotReadyCallback != null ? iSnapshotReadyCallback.asBinder() : null, iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setWatermarkEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 20);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setTrafficEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 19);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setStyleId(String str) {
                com.huawei.hms.maps.model.internal.mab.a(str, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 71);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setPolylineClickListener(IOnPolylineClickListener iOnPolylineClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnPolylineClickListener != null ? iOnPolylineClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 44);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setPolygonClickListener(IOnPolygonClickListener iOnPolygonClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnPolygonClickListener != null ? iOnPolygonClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 43);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setPointToCenter(int i, int i2) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 70, Integer.valueOf(i), Integer.valueOf(i2));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setPadding(int i, int i2, int i3, int i4) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 18, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnPolylineClickListener(IOnPolylineClickListener iOnPolylineClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnPolylineClickListener != null ? iOnPolylineClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 63);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnPolygonClickListener(IOnPolygonClickListener iOnPolygonClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnPolygonClickListener != null ? iOnPolygonClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 62);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnPoiClickListener(IOnPoiClickListener iOnPoiClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnPoiClickListener != null ? iOnPoiClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 61);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnMyLocationClickListener(IOnMyLocationClickListener iOnMyLocationClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMyLocationClickListener != null ? iOnMyLocationClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 57);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnMyLocationChangeListener(IOnMyLocationChangeListener iOnMyLocationChangeListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMyLocationChangeListener != null ? iOnMyLocationChangeListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 56);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnMyLocationButtonClickListener(IOnMyLocationButtonClickListener iOnMyLocationButtonClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMyLocationButtonClickListener != null ? iOnMyLocationButtonClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 55);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnMapLongClickListener(IOnMapLongClickListener iOnMapLongClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMapLongClickListener != null ? iOnMapLongClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 53);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnMapLoadedCallback(IOnMapLoadedCallback iOnMapLoadedCallback) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMapLoadedCallback != null ? iOnMapLoadedCallback.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 30);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnMapClickListener(IOnMapClickListener iOnMapClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMapClickListener != null ? iOnMapClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 54);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnInfoWindowLongClickListener(IOnInfoWindowLongClickListener iOnInfoWindowLongClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnInfoWindowLongClickListener != null ? iOnInfoWindowLongClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 50);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnInfoWindowCloseListener(IOnInfoWindowCloseListener iOnInfoWindowCloseListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnInfoWindowCloseListener != null ? iOnInfoWindowCloseListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 49);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnInfoWindowClickListener(IOnInfoWindowClickListener iOnInfoWindowClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnInfoWindowClickListener != null ? iOnInfoWindowClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 46);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnIndoorStateChangeListener(IOnIndoorStateChangeListener iOnIndoorStateChangeListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnIndoorStateChangeListener != null ? iOnIndoorStateChangeListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 48);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnGroundOverlayClickListener(IOnGroundOverlayClickListener iOnGroundOverlayClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnGroundOverlayClickListener != null ? iOnGroundOverlayClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 47);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setOnCameraChangeListener(IOnCameraChangeListener iOnCameraChangeListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnCameraChangeListener != null ? iOnCameraChangeListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 84);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 81, myLocationStyle);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMyLocationEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 8);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMinZoomPreference(float f) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 17, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMaxZoomPreference(float f) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 16, Float.valueOf(f));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMarkersClustering(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 60);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMarkerDragListener(IOnMarkerDragListener iOnMarkerDragListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMarkerDragListener != null ? iOnMarkerDragListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 52);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMarkerClickListener(IOnMarkerClickListener iOnMarkerClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnMarkerClickListener != null ? iOnMarkerClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 51);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setMapType(int i) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 7, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean setMapStyle(MapStyleOptions mapStyleOptions) {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 59, 0, mapStyleOptions).intValue() != 0;
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) {
                com.huawei.hms.maps.model.internal.mab.a(iLocationSourceDelegate != null ? iLocationSourceDelegate.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 58);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setLiteMode(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 31);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 23, latLngBounds);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setLanguage(String str) {
                com.huawei.hms.maps.model.internal.mab.a(str, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 69);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setInfoWindowAdapter(IInfoWindowAdapter iInfoWindowAdapter) {
                com.huawei.hms.maps.model.internal.mab.a(iInfoWindowAdapter != null ? iInfoWindowAdapter.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 45);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setIndoorViewListener(IOnIndoorViewListener iOnIndoorViewListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnIndoorViewListener != null ? iOnIndoorViewListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 77);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean setIndoorEnabled(boolean z) {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 15, z);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setGeoPoliticalView(String str) {
                com.huawei.hms.maps.model.internal.mab.a(str, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 67);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setGcj02CoordinateEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 73);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setFrameTime(int i) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 83, Integer.valueOf(i));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setDark(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 78);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setContentDescription(String str) {
                com.huawei.hms.maps.model.internal.mab.a(str, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 14);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setCommonDir(int i, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IHuaweiMapDelegate");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f4955a.transact(82, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setCircleClickListener(IOnCircleClickListener iOnCircleClickListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnCircleClickListener != null ? iOnCircleClickListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 42);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setCameraMoveStartedListener(IOnCameraMoveStartedListener iOnCameraMoveStartedListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnCameraMoveStartedListener != null ? iOnCameraMoveStartedListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 37);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setCameraMoveListener(IOnCameraMoveListener iOnCameraMoveListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnCameraMoveListener != null ? iOnCameraMoveListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 38);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setCameraMoveCanceledListener(IOnCameraMoveCanceledListener iOnCameraMoveCanceledListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnCameraMoveCanceledListener != null ? iOnCameraMoveCanceledListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 39);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setCameraIdleListener(IOnCameraIdleListener iOnCameraIdleListener) {
                com.huawei.hms.maps.model.internal.mab.a(iOnCameraIdleListener != null ? iOnCameraIdleListener.asBinder() : null, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 40);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void setBuildingsEnabled(boolean z) {
                com.huawei.hms.maps.model.internal.mab.a(z, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 6);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void resetMinMaxZoomPreference() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 13);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void previewId(String str) {
                com.huawei.hms.maps.model.internal.mab.a(str, this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 72);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void moveCamera(CameraUpdateParam cameraUpdateParam) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 34, cameraUpdateParam);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean isTrafficEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 12);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean isMyLocationEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 9);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean isIndoorEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 11);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean isDark() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 79);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public boolean isBuildingsEnabled() {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 5);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IUiSettingsDelegate getUiSettings() {
                return IUiSettingsDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 22));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public float getScalePerPixel() {
                return com.huawei.hms.maps.model.internal.mab.c(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 80);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IProjectionDelegate getProjection() {
                return IProjectionDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 29));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public float getMinZoomLevel() {
                return com.huawei.hms.maps.model.internal.mab.c(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 4);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public float getMaxZoomLevel() {
                return com.huawei.hms.maps.model.internal.mab.c(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 3);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public int getMapType() {
                return com.huawei.hms.maps.model.internal.mab.b(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 10);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IHuaweiMapDelegate";
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IIndoorBuildingDelegate getFocusedBuilding() {
                return IIndoorBuildingDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 2));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public String getCurrentFloor() {
                return com.huawei.hms.maps.model.internal.mab.d(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 75);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public CameraPosition getCameraPosition() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IHuaweiMapDelegate");
                    this.f4955a.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? CameraPosition.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IBitmapDescriptorDelegate getBitmapDescriptor() {
                return IBitmapDescriptorDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.j(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 68));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void clear() {
                com.huawei.hms.maps.model.internal.mab.i(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 32);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4955a;
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void animateCameraWithDurationAndCallback(CameraUpdateParam cameraUpdateParam, int i, ICancelableCallback iCancelableCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IHuaweiMapDelegate");
                    if (cameraUpdateParam != null) {
                        obtain.writeInt(1);
                        cameraUpdateParam.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iCancelableCallback != null ? iCancelableCallback.asBinder() : null);
                    this.f4955a.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void animateCameraWithCallback(CameraUpdateParam cameraUpdateParam, ICancelableCallback iCancelableCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IHuaweiMapDelegate");
                    if (cameraUpdateParam != null) {
                        obtain.writeInt(1);
                        cameraUpdateParam.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iCancelableCallback != null ? iCancelableCallback.asBinder() : null);
                    this.f4955a.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public void animateCamera(CameraUpdateParam cameraUpdateParam) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 33, cameraUpdateParam);
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public ITileOverlayDelegate addTileOverlay(TileOverlayOptions tileOverlayOptions) {
                return ITileOverlayDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 1, tileOverlayOptions));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IPolylineDelegate addPolyline(PolylineOptions polylineOptions) {
                return IPolylineDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 27, polylineOptions));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IPolygonDelegate addPolygon(PolygonOptions polygonOptions) {
                return IPolygonDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 26, polygonOptions));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IMarkerDelegate addMarker(MarkerOptions markerOptions) {
                return IMarkerDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 24, markerOptions));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IHeatMapDelegate addHeatMap(String str, HeatMapOptions heatMapOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.internal.IHuaweiMapDelegate");
                    obtain.writeString(str);
                    if (heatMapOptions != null) {
                        obtain.writeInt(1);
                        heatMapOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4955a.transact(74, obtain, obtain2, 0);
                    obtain2.readException();
                    return IHeatMapDelegate.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public IGroundOverlayDelegate addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
                return IGroundOverlayDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 28, groundOverlayOptions));
            }

            @Override // com.huawei.hms.maps.internal.IHuaweiMapDelegate
            public ICircleDelegate addCircle(CircleOptions circleOptions) {
                return ICircleDelegate.Stub.asInterface(com.huawei.hms.maps.model.internal.mab.a(this.f4955a, "com.huawei.hms.maps.internal.IHuaweiMapDelegate", 25, circleOptions));
            }

            Proxy(IBinder iBinder) {
                this.f4955a = iBinder;
            }
        }

        public static IHuaweiMapDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IHuaweiMapDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IHuaweiMapDelegate)) ? new Proxy(iBinder) : (IHuaweiMapDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IHuaweiMapDelegate");
        }
    }
}
