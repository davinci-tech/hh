package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceSnapshotCallback;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class hke implements InterfaceHiMap {

    /* renamed from: a, reason: collision with root package name */
    private Context f13196a;
    private LinearLayout e;

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar, boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addHiHealthMarkers(List<hjg> list) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public int addMarker(hlg hlgVar, GrowAnimationBuilder growAnimationBuilder) {
        return 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String addOverlay(hlj hljVar) {
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addSportStartMarker(hjd hjdVar, int i) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addStartMarker(hjd hjdVar, int i) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hjd hjdVar, long j, InterfaceMapCallback interfaceMapCallback) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hlc hlcVar, int i) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void cancelAnimation() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void changeTrackColor(int i) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hjd convertLocationByCoordinate(Location location) {
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawAddMapTracking(hjd hjdVar, hjd hjdVar2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawInterrupt(hjd hjdVar, hjd hjdVar2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(hjd hjdVar, hjd hjdVar2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(List<hiy> list) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String drawLines(hle hleVar) {
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void forceDrawLine() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getZoomToSpanLevel(hjd hjdVar, hjd hjdVar2) {
        return 15.0f;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void hideMarker(int i, GrowAnimationBuilder growAnimationBuilder) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public boolean isAnimationStart() {
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void loadingEnd() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByLatLng(hjd hjdVar) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom(float f) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraLatLngBounds(List<hjd> list) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveToCenter() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCameraChangeListener(Handler handler) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCreate(Bundle bundle, boolean z, boolean z2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onDestroy() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(hjd hjdVar, float f) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(List<MotionData> list) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onPause() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onResume() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStart() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStop() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void pauseSportClear() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void registerAnimationListener(InterfaceMapCallback interfaceMapCallback) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void registerLoadingListener(InterfaceMapCallback interfaceMapCallback) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public List<hjd> requestSimplePoints() {
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void resetTrackColor() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void saveAddress(MotionData motionData) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void screenShotToFile(InterfaceSnapshotCallback interfaceSnapshotCallback) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void scrollBy(float f, float f2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setAllGesturesEnabled(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setIsStop(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineColor(int i, boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineVisible(String str, boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLogoPadding(int i, int i2, int i3, int i4) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIcon(int i, Bitmap bitmap) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIconPath(String str, String str2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setOverlayVisible(String str, boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPointToCenter(int i, int i2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPointToCenterWhole(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setScaleControlsEnabled(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setScreenOnOrForegrand(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setShowMapEnd(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setSportTabCenter(DisplayMetrics displayMetrics) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setZoomControlsEnabled(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showMarker(int i, GrowAnimationBuilder growAnimationBuilder) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showOrHide(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showSatelLiteState(boolean z, boolean z2, int i) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showTrackMarkers(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void startMapAnimation(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void updateSportMarker(hjd hjdVar, BitmapDrawable bitmapDrawable) {
    }

    public hke(Context context, LinearLayout linearLayout, boolean z) {
        this.e = linearLayout;
        this.f13196a = context;
        if (nrt.a(context)) {
            linearLayout.setBackgroundColor(this.f13196a.getResources().getColor(R.color._2131298677_res_0x7f090975));
        } else {
            linearLayout.setBackgroundColor(this.f13196a.getResources().getColor(R.color._2131298817_res_0x7f090a01));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void getMapScreenShot(Handler handler, MotionData motionData) {
        if (handler == null) {
            LogUtil.a("Track_EmptyMap", "handler is null");
            return;
        }
        Message obtain = Message.obtain(handler);
        obtain.obj = null;
        obtain.what = 1;
        obtain.sendToTarget();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapShowType(int i) {
        LinearLayout linearLayout = this.e;
        if (linearLayout == null) {
            LogUtil.h("Track_EmptyMap", "mEmptyMapLayout is null");
        } else if (i == 3) {
            linearLayout.setBackgroundColor(this.f13196a.getResources().getColor(R.color._2131298677_res_0x7f090975));
        } else {
            linearLayout.setBackgroundColor(this.f13196a.getResources().getColor(R.color._2131298817_res_0x7f090a01));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public double[] getLastLocation(Context context, double[] dArr) {
        return new double[0];
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public double[] getLastLocation(Context context, Location location) {
        return new double[0];
    }
}
