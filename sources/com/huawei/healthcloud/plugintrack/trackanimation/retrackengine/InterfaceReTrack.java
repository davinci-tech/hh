package com.huawei.healthcloud.plugintrack.trackanimation.retrackengine;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import defpackage.hah;
import defpackage.hak;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public interface InterfaceReTrack {
    void addAlbumMarker(int i, InterfaceUpdateReTrack.MarkerType markerType);

    void addMarkerIndex(int i);

    void changeEditStatus(boolean z);

    void changeMapType(MapTypeDescription.MapType mapType, hak hakVar);

    void deleteAlbumMarker(int i);

    void deleteMarkerIndex(int i);

    double getCurrentDistance(int i);

    long getTotalDuration();

    void messageHandle(Message message);

    InterfaceUpdateReTrack obtainInterfaceUpdateReTrack();

    void onCreate(Bundle bundle);

    void onDestroy();

    void onPause();

    void onResume();

    void reset();

    void resetLine();

    void setAlgorithmStrength(TrackAnimationControl.Strength strength);

    void setAutoMatchPhotos(HashMap<Integer, List<Bitmap>> hashMap);

    void setCustomMarkInfo(hah hahVar);

    void setIsHideKmMarker(boolean z);

    void setIsSupportArea(boolean z);

    void setScreenHeight(int i);

    void setScreenWidth(int i);

    void setSumPhotosNumber(int i);

    void startReTrack();

    void updateTrackAnimationControl();
}
