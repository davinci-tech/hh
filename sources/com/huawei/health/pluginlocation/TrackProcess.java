package com.huawei.health.pluginlocation;

import com.huawei.health.baseapi.pluginlocation.callback.HwPluginCloudTrackCallback;
import com.huawei.health.baseapi.pluginlocation.callback.PluginTrackProcessCallback;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.exp;
import defpackage.exs;
import defpackage.eyd;
import defpackage.eym;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class TrackProcess {
    private static final int DEFAULT_SIZE_VALUE = 16;
    private static final int ERROR_CODE_FAIL = -1;
    private static final String TAG = "AAR,TrackProcessClass";
    private static final int WAKELOCK_TIMEOUT = 60000;
    private static boolean isLoadSuccess = false;
    private HwPluginCloudTrackCallback mCloudTrackCallback;
    private PluginTrackProcessCallback mTrackCallback;

    private native void nativeInitList(String str);

    private native void nativePostEnd(int i, double d);

    private native void nativePostItemGpsPoint(double d, double d2, double d3);

    private native int native_postProcessing(char[] cArr, int i, char[] cArr2, int i2);

    static {
        try {
            System.loadLibrary("TrackProcessNew");
            isLoadSuccess = true;
        } catch (UnsatisfiedLinkError unused) {
            eym.e(TAG, "no TrackProcessNew lib");
            isLoadSuccess = false;
        }
    }

    public int getOptimizedTrack(String str, String str2, PluginTrackProcessCallback pluginTrackProcessCallback) {
        eym.b(TAG, "getOptimizedTrack");
        if (pluginTrackProcessCallback == null) {
            eym.b(TAG, "getOptimizedTrack callback is null");
            return -1;
        }
        if (str == null || str2 == null) {
            eym.b(TAG, "getOptimizedTrack gpsFilePath or pdrFilePath is null");
            pluginTrackProcessCallback.onResponse(-1, null);
            return -1;
        }
        this.mTrackCallback = pluginTrackProcessCallback;
        if (!isLoadSuccess) {
            eym.b(TAG, "getOptimizedTrack lib not load");
            return -1;
        }
        eyd.c(60000);
        int native_postProcessing = native_postProcessing(str.toCharArray(), str.length(), str2.toCharArray(), str2.length());
        eyd.b();
        return native_postProcessing;
    }

    public void getCloudTrack(HwPluginCloudTrackCallback hwPluginCloudTrackCallback) {
        if (hwPluginCloudTrackCallback == null) {
            return;
        }
        this.mCloudTrackCallback = hwPluginCloudTrackCallback;
    }

    public void getPostProcessingResult(ArrayList<TrajectoryPoint> arrayList) {
        eym.b(TAG, "getPostProcessingResult");
        this.mTrackCallback.onResponse(0, arrayList);
    }

    void requestDownloadMotionPath(String str, double d, double d2, int i, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(JsbMapKeyNames.H5_LOC_LON, Double.valueOf(d));
        hashMap.put(JsbMapKeyNames.H5_LOC_LAT, Double.valueOf(d2));
        hashMap.put("pathId", str);
        hashMap.put("searchScope", Integer.valueOf(i));
        hashMap.put("type", Integer.valueOf(i2));
        HwPluginCloudTrackCallback hwPluginCloudTrackCallback = this.mCloudTrackCallback;
        if (hwPluginCloudTrackCallback == null) {
            return;
        }
        hwPluginCloudTrackCallback.onResponse(0, hashMap);
    }

    public void postMotionPath(List<exp> list) {
        exp next;
        if (list == null) {
            return;
        }
        Iterator<exp> it = list.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            nativeInitList(next.b());
            List<exs> a2 = next.a();
            if (a2 == null) {
                return;
            }
            for (exs exsVar : a2) {
                nativePostItemGpsPoint(exsVar.e(), exsVar.c(), exsVar.a());
            }
            nativePostEnd(next.c(), next.d());
        }
    }

    public static void printInfo(String str) {
        eym.b("TrackProcess", str);
    }

    public static void printErr(String str) {
        eym.c("TraceProcess", str);
    }
}
