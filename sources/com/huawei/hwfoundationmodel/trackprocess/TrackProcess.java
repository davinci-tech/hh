package com.huawei.hwfoundationmodel.trackprocess;

import com.huawei.health.track.post.process.callback.CloudTrackCallback;
import com.huawei.health.track.post.process.callback.TrackProcessCallback;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.gkn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class TrackProcess {
    private static final int ERROR_CODE_FAIL = -1;
    private static final String TAG = "TrackProcessClass";
    private static final String TAG_RELEASE = "BTSYNC_TrackProcessClass";
    private CloudTrackCallback mCloudTrackCallback;
    private TrackProcessCallback mTrackCallback;

    private native void nativeInitList(String str);

    private native void nativePostEnd(int i, double d);

    private native void nativePostItemGpsPoint(double d, double d2, double d3);

    public native int native_postProcessing(char[] cArr, int i, char[] cArr2, int i2);

    static {
        try {
            System.loadLibrary("TrackProcess");
            LogUtil.a(TAG, "load lib TrackProcess success");
        } catch (UnsatisfiedLinkError e) {
            LogUtil.b(TAG, "load TrackProcess error:", e.getMessage());
        }
    }

    public int getOptimizedTrack(String str, String str2, TrackProcessCallback trackProcessCallback) {
        if (trackProcessCallback == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "callback is null");
            return -1;
        }
        if (str == null || str2 == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "filePath is null");
            trackProcessCallback.onResponse(-1, null);
            return -1;
        }
        LogUtil.c(TAG, "getOptimizedTrack gpsFilePath:", str, ";pdrFilePath:", str2);
        this.mTrackCallback = trackProcessCallback;
        return native_postProcessing(str.toCharArray(), str.length(), str2.toCharArray(), str2.length());
    }

    public void getCloudTrack(CloudTrackCallback cloudTrackCallback) {
        if (cloudTrackCallback == null) {
            LogUtil.h(TAG, "getCloudTrack CloudTrackCallback is null");
        } else {
            this.mCloudTrackCallback = cloudTrackCallback;
        }
    }

    void getPostProcessingResult(ArrayList<TrajectoryPoint> arrayList) {
        LogUtil.c(TAG, "callback processresult：", Integer.valueOf(arrayList.size()));
        this.mTrackCallback.onResponse(0, arrayList);
    }

    void requestDownloadMotionPath(String str, double d, double d2, int i, int i2) {
        LogUtil.a(TAG, "requestDownloadMotionPath");
        HashMap hashMap = new HashMap(16);
        hashMap.put(JsbMapKeyNames.H5_LOC_LON, Double.valueOf(d));
        hashMap.put(JsbMapKeyNames.H5_LOC_LAT, Double.valueOf(d2));
        hashMap.put("pathId", str);
        hashMap.put("searchScope", Integer.valueOf(i));
        hashMap.put("type", Integer.valueOf(i2));
        CloudTrackCallback cloudTrackCallback = this.mCloudTrackCallback;
        if (cloudTrackCallback == null) {
            LogUtil.h(TAG, "requestDownloadMotionPath CloudTrackCallback is null");
        } else {
            cloudTrackCallback.onResponse(0, hashMap);
        }
    }

    public void postMotionPath(List<gkn> list) {
        if (list == null) {
            LogUtil.h(TAG, "postMotionPath pathInfoList is null");
            return;
        }
        for (gkn gknVar : list) {
            if (gknVar == null) {
                LogUtil.h(TAG, "postMotionPath pathInfo is null");
                return;
            }
            nativeInitList(gknVar.d());
            List<GpsPoint> f = gknVar.f();
            if (f == null) {
                LogUtil.h(TAG, "postMotionPath gpsPoints is null");
                return;
            }
            LogUtil.c(TAG, "postMotionPath pointSize :", Integer.valueOf(f.size()));
            for (GpsPoint gpsPoint : f) {
                nativePostItemGpsPoint(gpsPoint.getLatitude(), gpsPoint.getLongitude(), gpsPoint.getAltitude());
            }
            nativePostEnd(gknVar.a(), gknVar.b());
        }
    }
}
