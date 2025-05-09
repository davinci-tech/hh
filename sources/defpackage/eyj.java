package defpackage;

import android.content.Context;
import com.huawei.health.baseapi.pluginlocation.callback.HwPluginTrackMapCallback;
import com.huawei.health.baseapi.pluginlocation.callback.PluginTrackProcessCallback;
import com.huawei.health.pluginlocation.TrackProcess;
import com.huawei.health.pluginlocation.TrajectoryPoint;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes8.dex */
public class eyj {

    /* renamed from: a, reason: collision with root package name */
    private PluginTrackProcessCallback f12383a;
    private boolean b;
    private boolean c;
    private exw d = new exw();
    private Context e;
    private String i;

    public int e(final String str, final String str2, final boolean z, final HwPluginTrackMapCallback hwPluginTrackMapCallback, final boolean z2) {
        Context context = this.e;
        if (context == null || context.getExternalCacheDir() == null || this.e.getExternalCacheDir().getParentFile() == null || this.e.getExternalCacheDir().getParentFile().getPath() == null) {
            eym.c("AAR,GpsFileUtil", "context get external cache dir failed");
            if (hwPluginTrackMapCallback != null) {
                hwPluginTrackMapCallback.onResponse(1, null);
                return -1;
            }
            eym.c("AAR,GpsFileUtil", "mHwPluginTrackMapCallback is null");
            return -1;
        }
        String str3 = ((File) Objects.requireNonNull(((File) Objects.requireNonNull(this.e.getExternalCacheDir())).getParentFile())).getPath() + "/files/RunwayRoutes/roadData/";
        eym.b("AAR,GpsFileUtil", "[pluginGetOptimizedTrack] download start url = " + this.i + " savePath = " + str3);
        if (!this.i.isEmpty()) {
            this.d.c(this.e, this.i, str3);
        }
        TrackProcess trackProcess = new TrackProcess();
        PluginTrackProcessCallback pluginTrackProcessCallback = new PluginTrackProcessCallback() { // from class: eyj.5
            @Override // com.huawei.health.baseapi.pluginlocation.callback.PluginTrackProcessCallback
            public void onResponse(int i, ArrayList<TrajectoryPoint> arrayList) {
                if (!eyj.this.c && (!eyj.this.b || !z)) {
                    try {
                        File file = new File(str);
                        if (file.exists()) {
                            file.delete();
                        }
                        File file2 = new File(str2);
                        if (file2.exists()) {
                            file2.delete();
                        }
                    } catch (Exception unused) {
                        eym.e("AAR,GpsFileUtil", "pluginGetOptimizedTrack Exception");
                    }
                }
                if (i == 0) {
                    Map<Long, double[]> c = eyj.this.c(arrayList, z2);
                    HwPluginTrackMapCallback hwPluginTrackMapCallback2 = hwPluginTrackMapCallback;
                    if (hwPluginTrackMapCallback2 != null) {
                        hwPluginTrackMapCallback2.onResponse(0, c);
                        return;
                    }
                    return;
                }
                HwPluginTrackMapCallback hwPluginTrackMapCallback3 = hwPluginTrackMapCallback;
                if (hwPluginTrackMapCallback3 != null) {
                    hwPluginTrackMapCallback3.onResponse(1, null);
                }
            }
        };
        this.f12383a = pluginTrackProcessCallback;
        return trackProcess.getOptimizedTrack(str, str2, pluginTrackProcessCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Long, double[]> c(ArrayList<TrajectoryPoint> arrayList, boolean z) {
        eym.b("AAR,GpsFileUtil", "getTrackMessage");
        HashMap hashMap = new HashMap(16);
        for (int i = 0; i < arrayList.size(); i++) {
            double[] dArr = new double[4];
            dArr[0] = arrayList.get(i).getLatitude();
            dArr[1] = arrayList.get(i).getLongitude();
            if (z) {
                dArr[2] = arrayList.get(i).getAltitude();
            } else {
                dArr[2] = 0.0d;
            }
            dArr[3] = arrayList.get(i).getUtc_time();
            hashMap.put(Long.valueOf(i), dArr);
        }
        return hashMap;
    }

    public void d(boolean z, boolean z2, Context context, String str) {
        this.b = z2;
        this.c = z;
        this.e = context;
        this.i = str;
    }
}
