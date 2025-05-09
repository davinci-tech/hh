package com.huawei.health.trackimport.algorithm;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.route.RouteOutputForApp;
import com.huawei.route.RouteTrackInfo;

/* loaded from: classes4.dex */
public class TrackFeatureExtraction {
    public native RouteOutputForApp GetTrackResultForApp();

    public native byte[] GetTrackResultForWatch();

    public native int GetTrackResultSizeForWatch();

    public native int ProcessTrackInfo(RouteTrackInfo routeTrackInfo);

    public native int TrackAlgClose();

    public native int TrackAlgInit(long j, int i, int i2, double d, boolean z);

    public native int addTrackPoint(long j, double d, double d2);

    public native int algInit(int i, float f);

    public native float obtainCompressionRadio();

    public native byte[] obtainResult();

    public native int obtainResultSize();

    public native void stopAlg();

    static {
        try {
            System.loadLibrary("track_feature_extra");
            LogUtil.a("TrackFeatureExtraction", "load .so success");
        } catch (UnsatisfiedLinkError e) {
            LogUtil.b("TrackFeatureExtraction", "load .so fail" + e.getMessage());
        }
    }
}
