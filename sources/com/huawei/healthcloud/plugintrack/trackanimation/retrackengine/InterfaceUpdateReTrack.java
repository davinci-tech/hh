package com.huawei.healthcloud.plugintrack.trackanimation.retrackengine;

import android.graphics.Bitmap;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface InterfaceUpdateReTrack {

    public enum MarkerType {
        Empty_type,
        Image_type,
        Text_type,
        Video_type
    }

    int addCurMarker(int i, MarkerType markerType);

    void addMarker(int i, MarkerType markerType);

    void addMoveMarker(int i);

    void addPhotosList(Map<Integer, List<Bitmap>> map);

    void addText(int i, String str);

    void addVideoPath(Map<Integer, ArrayList<VideoModel>> map);

    void clearCurMarker(int i);

    void clearMarker(int i);

    void deleteMoveMarker();

    int getTrackNumber();

    void moveMarkerToCurrent(int i);

    ReTrackSimplify obtainSimlify();

    void resetDismiss();

    void resetShow();

    void setPhotosNumber(int i);

    void setVideoNumber(int i);

    void updateTrack(int i, int i2);
}
