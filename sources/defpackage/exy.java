package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.baseapi.pluginlocation.TrackOptimizationApi;
import com.huawei.health.baseapi.pluginlocation.callback.HwPluginCloudTrackCallback;
import com.huawei.health.baseapi.pluginlocation.callback.HwPluginTrackMapCallback;
import com.huawei.health.pluginlocation.TrackProcess;
import com.huawei.health.pluginlocation.Utils.BiApi;
import com.huawei.health.pluginlocation.Utils.BiReportImpl;
import com.huawei.health.pluginlocation.logger.Logger;
import java.util.List;

/* loaded from: classes8.dex */
public class exy implements TrackOptimizationApi {
    private static String[] c = {"AOD", "ULTIMATE"};
    private static boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    private TrackProcess f12379a = new TrackProcess();
    private eyj d = new eyj();

    static {
        try {
            System.loadLibrary("TrackProcessNew");
            e = true;
        } catch (UnsatisfiedLinkError unused) {
            eym.e("AAR,TrackOptimizationImpl", "can't load TrackProcessNew");
            e = false;
        }
    }

    public exy(BiApi biApi, Logger logger) {
        eym.c(logger);
        if (biApi == null) {
            eym.c("AAR,TrackOptimizationImpl", "biApi is null");
        } else {
            new BiReportImpl(biApi);
        }
    }

    private boolean e(String str) {
        for (String str2 : c) {
            if (str.contains(str2)) {
                eym.e("AAR,TrackOptimizationImpl", "isInWhiteList " + str2);
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.health.baseapi.pluginlocation.TrackOptimizationApi
    public int getOptimizedTrack(String str, String str2, boolean z, HwPluginTrackMapCallback hwPluginTrackMapCallback, boolean z2, String str3) {
        if (TextUtils.isEmpty(str3) || (!str3.contains("HUAWEI") && !e(str3))) {
            eym.b("AAR,TrackOptimizationImpl", "device not support, stop getOptimizedTrack");
            return -1;
        }
        if (!e) {
            eym.b("AAR,TrackOptimizationImpl", "getOptimizedTrack lib not load");
            return -1;
        }
        return this.d.e(str, str2, z, hwPluginTrackMapCallback, z2);
    }

    @Override // com.huawei.health.baseapi.pluginlocation.TrackOptimizationApi
    public void postMotionPath(List<exp> list) {
        this.f12379a.postMotionPath(list);
    }

    @Override // com.huawei.health.baseapi.pluginlocation.TrackOptimizationApi
    public void setExtraData(boolean z, boolean z2, Context context, String str) {
        this.d.d(z, z2, context, str);
    }

    @Override // com.huawei.health.baseapi.pluginlocation.TrackOptimizationApi
    public void getCloudTrack(HwPluginCloudTrackCallback hwPluginCloudTrackCallback) {
        this.f12379a.getCloudTrack(hwPluginCloudTrackCallback);
    }
}
