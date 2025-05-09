package com.huawei.healthcloud.plugintrack.ui.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import defpackage.hak;
import defpackage.hiy;
import defpackage.hjd;
import defpackage.hjg;
import defpackage.hkx;
import defpackage.hla;
import defpackage.hlc;
import defpackage.hld;
import defpackage.hle;
import defpackage.hlg;
import defpackage.hlh;
import defpackage.hlj;
import java.util.List;

/* loaded from: classes4.dex */
public interface InterfaceHiMap {
    public static final int MAP_TYPE_NIGHT = 3;
    public static final int MAP_TYPE_NIGHT_WITHOUT_TEXT = 5;
    public static final int MAP_TYPE_NONE = 2;
    public static final int MAP_TYPE_NORMAL = 0;
    public static final int MAP_TYPE_SATELLITE = 1;
    public static final int MAP_TYPE_WITHOUT_TEXT = 4;
    public static final int POLY_LINE_MAX_SIZE = 242;

    void addEndMarker(hjd hjdVar);

    void addEndMarker(hjd hjdVar, boolean z);

    void addHiHealthMarkers(List<hjg> list);

    default void addLinePoint(hld hldVar) {
    }

    int addMarker(hlg hlgVar, GrowAnimationBuilder growAnimationBuilder);

    default void addMarkerClickListener(InterfaceMapMarkerClickCallback interfaceMapMarkerClickCallback) {
    }

    String addOverlay(hlj hljVar);

    void addSportStartMarker(hjd hjdVar, int i);

    void addStartMarker(hjd hjdVar, int i);

    void animateCamera(hjd hjdVar, long j, InterfaceMapCallback interfaceMapCallback);

    void animateCamera(hlc hlcVar, int i);

    default void animateCamera(hlh hlhVar, long j, InterfaceMapCallback interfaceMapCallback) {
    }

    void cancelAnimation();

    default void changeMapType(MapTypeDescription mapTypeDescription, hak hakVar) {
    }

    void changeTrackColor(int i);

    default void clear() {
    }

    default List<MotionData> convertCoordinate(List<MotionData> list) {
        return list;
    }

    hjd convertLocationByCoordinate(Location location);

    default void disableAllGestures() {
    }

    default void disableMarkerClick() {
    }

    void drawAddMapTracking(hjd hjdVar, hjd hjdVar2);

    void drawInterrupt(hjd hjdVar, hjd hjdVar2);

    void drawLine(hjd hjdVar, hjd hjdVar2);

    default void drawLine(hld hldVar) {
    }

    void drawLine(List<hiy> list);

    String drawLines(hle hleVar);

    void forceDrawLine();

    double[] getLastLocation(Context context, Location location);

    double[] getLastLocation(Context context, double[] dArr);

    default int getMapEngineType() {
        return -1;
    }

    void getMapScreenShot(Handler handler, MotionData motionData);

    default float getMapTilt() {
        return 0.0f;
    }

    default float getMapZoom() {
        return 0.0f;
    }

    default float getMaxZoomLevel() {
        return 0.0f;
    }

    float getZoomToSpanLevel(hjd hjdVar, hjd hjdVar2);

    void hideMarker(int i, GrowAnimationBuilder growAnimationBuilder);

    boolean isAnimationStart();

    default boolean isClockwise() {
        return false;
    }

    default void loadMapWithPreprocessData(List<hkx> list) {
    }

    void loadingEnd();

    void moveCameraByLatLng(hjd hjdVar);

    void moveCameraByZoom();

    void moveCameraByZoom(float f);

    void moveCameraLatLngBounds(List<hjd> list);

    default void moveMarker(int i, hjd hjdVar) {
    }

    void moveToCenter();

    void onCameraChangeListener(Handler handler);

    void onCreate(Bundle bundle, boolean z, boolean z2);

    default void onCreatePurely(Bundle bundle) {
    }

    void onDestroy();

    default void onDestroyPurely() {
    }

    void onMapLoaded();

    void onMapLoaded(hjd hjdVar, float f);

    void onMapLoaded(List<MotionData> list);

    void onPause();

    default void onPausePurely() {
    }

    void onResume();

    default void onResumePurely() {
    }

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void pauseSportClear();

    void registerAnimationListener(InterfaceMapCallback interfaceMapCallback);

    void registerLoadingListener(InterfaceMapCallback interfaceMapCallback);

    default void removeLinePoint() {
    }

    List<hjd> requestSimplePoints();

    void resetTrackColor();

    void saveAddress(MotionData motionData);

    void screenShotToFile(InterfaceSnapshotCallback interfaceSnapshotCallback);

    void scrollBy(float f, float f2);

    void setAllGesturesEnabled(boolean z);

    default void setCameraChangeCallback(InterfaceMapStatusChangeCallback interfaceMapStatusChangeCallback) {
    }

    void setIsStop(boolean z);

    void setLineColor(int i, boolean z);

    void setLineVisible(String str, boolean z);

    void setLogoPadding(int i, int i2, int i3, int i4);

    default void setMapLoadedCallback(InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
    }

    void setMapShowType(int i);

    default void setMapStyle(int i) {
    }

    void setMarkerIcon(int i, Bitmap bitmap);

    void setMarkerIconPath(String str, String str2);

    default void setOnMapLoadedListener(InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
    }

    void setOverlayVisible(String str, boolean z);

    void setPointToCenter(int i, int i2);

    void setPointToCenterWhole(boolean z);

    default void setPreviewStatus(hla hlaVar, int i, int i2, int i3, int i4) {
    }

    void setScaleControlsEnabled(boolean z);

    void setScreenOnOrForegrand(boolean z);

    void setShowMapEnd(boolean z);

    void setSportTabCenter(DisplayMetrics displayMetrics);

    void setZoomControlsEnabled(boolean z);

    void showMarker(int i, GrowAnimationBuilder growAnimationBuilder);

    void showOrHide(boolean z);

    default void showPureMap() {
    }

    void showSatelLiteState(boolean z, boolean z2, int i);

    void showTrackMarkers(boolean z);

    void startMapAnimation(boolean z);

    default void stopAnimation() {
    }

    void updateSportMarker(hjd hjdVar, BitmapDrawable bitmapDrawable);

    default GrowAnimationBuilder getGrowAnimation() {
        return new GrowAnimationBuilder() { // from class: com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap.1
            @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
            public void disappearAnimation() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
            public void displayAnimation() {
            }
        };
    }

    default hlh getMapStatus() {
        return new hlh();
    }

    default Point getScreenLocation(hjd hjdVar) {
        return new Point();
    }

    default hjd getLatLngByScreenPoint(Point point) {
        return new hjd(0.0d, 0.0d);
    }
}
