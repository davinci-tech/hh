package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.track.post.process.callback.CloudTrackCallback;
import com.huawei.health.track.post.process.callback.TrackProcessCallback;
import com.huawei.health.trackprocess.api.TrackPostProcessApi;
import com.huawei.health.trackprocess.callback.PluginCloudTrackCallback;
import com.huawei.health.trackprocess.callback.PluginTrackMapCallback;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.utils.NspClient;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackprocess.TrackProcess;
import com.huawei.hwfoundationmodel.trackprocess.TrajectoryPoint;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.up.utils.NSPException;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiDefine(uri = TrackPostProcessApi.class)
@Singleton
/* loaded from: classes4.dex */
public class gkb implements TrackPostProcessApi {
    private static final int c = Build.VERSION.SDK_INT;
    private gkj d;

    @Override // com.huawei.health.trackprocess.api.TrackPostProcessApi
    public int getPostProcessingTrack(String str, String str2, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        boolean a2 = a();
        ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "getPostProcessingTrack");
        LogUtil.a("TrackPostProcessImpl", "isSupportGpsFile:", Boolean.valueOf(a2));
        return a(LogConfig.m() + "/tracktest/" + str, LogConfig.m() + "/tracktest/" + str2, a2, iBaseResponseCallback, z);
    }

    private int a(String str, String str2, boolean z, IBaseResponseCallback iBaseResponseCallback, boolean z2) {
        ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "getOptimizedTrack enter");
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("BTSYNC_TrackPostProcessImpl", "getOptimizedTrack callback is null");
            return -1;
        }
        if (c > 24) {
            ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "sdk version is more than version N");
            gkj b = gkj.b();
            this.d = b;
            if (b.isPluginAvaiable()) {
                ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "PluginLocationProxy is available");
                return d(str, str2, z, iBaseResponseCallback, z2);
            }
            ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "mPluginLocationProxy is not available,load plugin");
            this.d.loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: gkc
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context, Intent intent) {
                    return gkb.aNo_(context, intent);
                }
            });
        }
        return c(str, str2, z, iBaseResponseCallback, z2);
    }

    static /* synthetic */ boolean aNo_(Context context, Intent intent) {
        ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "PluginLocationProxy loadPlugin success");
        return false;
    }

    private int d(String str, String str2, boolean z, final IBaseResponseCallback iBaseResponseCallback, boolean z2) {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainHealthcommonHicloud");
        boolean contains = str2.contains("mobile_phone");
        DeviceInfo c2 = !contains ? c() : null;
        if (!TextUtils.isEmpty(url) && ((c2 != null && !TextUtils.isEmpty(c2.getDeviceName())) || contains)) {
            this.d.setExtraData(CompileParameterUtil.a("IS_DEBUG_VERSION"), CommonUtil.as(), BaseApplication.getContext(), url + "/commonAbility/configCenter/getConfigInfo");
            PluginTrackMapCallback pluginTrackMapCallback = new PluginTrackMapCallback() { // from class: gke
                @Override // com.huawei.health.trackprocess.callback.PluginTrackMapCallback
                public final void onResponse(int i, Map map) {
                    gkb.c(IBaseResponseCallback.this, i, map);
                }
            };
            e(null, true);
            String deviceName = c2 != null ? c2.getDeviceName() : Build.BRAND;
            LogUtil.a("TrackPostProcessImpl", "deviceName:", deviceName, " isFromPhone ", Boolean.valueOf(contains));
            int optimizedTrack = this.d.getOptimizedTrack(str, str2, z, pluginTrackMapCallback, z2, deviceName);
            LogUtil.a("TrackPostProcessImpl", "runNewOptimizedTrack result ", Integer.valueOf(optimizedTrack));
            if (optimizedTrack == 0) {
                ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "use new lib");
                return optimizedTrack;
            }
        }
        return c(str, str2, z, iBaseResponseCallback, z2);
    }

    static /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, int i, Map map) {
        ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "runNewOptimizedTrack errorCode:", Integer.valueOf(i));
        if (i == 0) {
            iBaseResponseCallback.d(10000, map);
        } else {
            LogUtil.h("TrackPostProcessImpl", "getWorkOutDetailFromDevice() callback error :", map);
            iBaseResponseCallback.d(10001, "so calucate error");
        }
    }

    private int c(String str, String str2, boolean z, IBaseResponseCallback iBaseResponseCallback, boolean z2) {
        ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "use old lib");
        TrackProcess trackProcess = new TrackProcess();
        e(trackProcess, false);
        int optimizedTrack = trackProcess.getOptimizedTrack(str, str2, b(str, str2, z, iBaseResponseCallback, z2));
        ReleaseLogUtil.e("BTSYNC_TrackPostProcessImpl", "old optimizedTrack: ", Integer.valueOf(optimizedTrack));
        return optimizedTrack;
    }

    private DeviceInfo c() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "TrackPostProcessImpl");
        if (!koq.b(deviceList)) {
            return deviceList.get(0);
        }
        LogUtil.a("TrackPostProcessImpl", "deviceInfo is empty");
        return null;
    }

    private TrackProcessCallback b(final String str, final String str2, final boolean z, final IBaseResponseCallback iBaseResponseCallback, final boolean z2) {
        return new TrackProcessCallback() { // from class: gkg
            @Override // com.huawei.health.track.post.process.callback.TrackProcessCallback
            public final void onResponse(int i, ArrayList arrayList) {
                gkb.this.a(z, str, str2, z2, iBaseResponseCallback, i, arrayList);
            }
        };
    }

    /* synthetic */ void a(boolean z, String str, String str2, boolean z2, IBaseResponseCallback iBaseResponseCallback, int i, ArrayList arrayList) {
        LogUtil.a(a.t, 0, "TrackPostProcessImpl", "errorCode :", Integer.valueOf(i), "is beta version :", Boolean.valueOf(CommonUtil.as()), "isSupportGpsFileEnabled :", Boolean.valueOf(z));
        if (!CompileParameterUtil.a("IS_DEBUG_VERSION") && (!CommonUtil.as() || !z)) {
            File file = new File(str);
            if (file.exists()) {
                LogUtil.a(a.t, 0, "TrackPostProcessImpl", "delete result :", Boolean.valueOf(file.delete()));
            } else {
                ReleaseLogUtil.d("BTSYNC_TrackPostProcessImpl", "gpsFile is not exists");
            }
            File file2 = new File(str2);
            if (file2.exists()) {
                LogUtil.a(a.t, 0, "TrackPostProcessImpl", "delete result :", Boolean.valueOf(file2.delete()));
            } else {
                ReleaseLogUtil.d("BTSYNC_TrackPostProcessImpl", "pdrFile is not exists");
            }
        }
        if (i == 0) {
            iBaseResponseCallback.d(10000, a(arrayList, z2));
        } else {
            LogUtil.h("TrackPostProcessImpl", "getWorkOutDetailFromDevice() callback error :", arrayList);
            iBaseResponseCallback.d(10001, "so calucate error");
        }
    }

    private void e(final TrackProcess trackProcess, final boolean z) {
        LogUtil.a("TrackPostProcessImpl", "getCloudTrack enter");
        if (z) {
            this.d.getCloudTrack(new PluginCloudTrackCallback() { // from class: gkf
                @Override // com.huawei.health.trackprocess.callback.PluginCloudTrackCallback
                public final void onResponse(int i, Object obj) {
                    gkb.this.c(trackProcess, z, i, obj);
                }
            });
        } else {
            trackProcess.getCloudTrack(new CloudTrackCallback() { // from class: gkd
                @Override // com.huawei.health.track.post.process.callback.CloudTrackCallback
                public final void onResponse(int i, Object obj) {
                    gkb.this.e(trackProcess, z, i, obj);
                }
            });
        }
    }

    /* synthetic */ void c(TrackProcess trackProcess, boolean z, int i, Object obj) {
        LogUtil.a("TrackPostProcessImpl", "new getCloudTrack onResponse");
        if (obj instanceof Map) {
            b(trackProcess, z, (Map) obj);
        } else {
            LogUtil.a("TrackPostProcessImpl", "objectData type is not correct");
        }
    }

    /* synthetic */ void e(TrackProcess trackProcess, boolean z, int i, Object obj) {
        LogUtil.a("TrackPostProcessImpl", "old getCloudTrack onResponse");
        if (obj instanceof Map) {
            b(trackProcess, z, (Map) obj);
        } else {
            LogUtil.a("TrackPostProcessImpl", "objectData type is not correct");
        }
    }

    private <T extends Map<String, Object>> void b(TrackProcess trackProcess, boolean z, T t) {
        if (t != null) {
            try {
                String call = new NspClient(BaseApplication.getContext()).call("/dataAnalyse/app/getMotionPathInGeoHash", t, 30, 30, 2);
                gko gkoVar = (gko) new Gson().fromJson(call, gko.class);
                if (gkoVar != null && gkoVar.d() == 0) {
                    List<gkn> c2 = gkoVar.c();
                    Object[] objArr = new Object[2];
                    objArr[0] = "getCloudTrack motionPathInfoList size:";
                    objArr[1] = !koq.b(c2) ? Integer.valueOf(c2.size()) : "0";
                    LogUtil.a("TrackPostProcessImpl", objArr);
                    if (z) {
                        this.d.postMotionPath(c2);
                        return;
                    } else {
                        trackProcess.postMotionPath(c2);
                        return;
                    }
                }
                LogUtil.h("TrackPostProcessImpl", "getCloudTrack json:", call);
            } catch (JsonSyntaxException | NSPException e) {
                LogUtil.b("TrackPostProcessImpl", "dealCloudData exception:", ExceptionUtils.d(e), ", json:", null);
            }
        }
    }

    private Map<Long, double[]> a(ArrayList<TrajectoryPoint> arrayList, boolean z) {
        HashMap hashMap = new HashMap(16);
        LogUtil.a(a.t, 0, "TrackPostProcessImpl", "getWorkoutDetailFromDevice() size1 is :", Integer.valueOf(arrayList.size()));
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

    private boolean a() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("gps_files_switch_screen");
        if (userPreference == null) {
            LogUtil.a("TrackPostProcessImpl", "isSupportGpsFile preference is null");
            return b(HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.wear_common_setting"));
        }
        return "1".equals(userPreference.getValue());
    }

    private boolean b(HiUserPreference hiUserPreference) {
        if (hiUserPreference != null && !TextUtils.isEmpty(hiUserPreference.getValue())) {
            String value = hiUserPreference.getValue();
            LogUtil.a("TrackPostProcessImpl", "value:", value);
            String substring = value.substring(1, value.length() - 1);
            for (String str : substring.split(",")) {
                if ("gps_files_switch_screen".equals(str.split("=")[0].trim())) {
                    substring = str.split("=")[1];
                }
            }
            LogUtil.a("TrackPostProcessImpl", "GPS file enabled :", substring);
            return "1".equals(substring);
        }
        e();
        return true;
    }

    private void e() {
        LogUtil.a("TrackPostProcessImpl", "setDefaultGpsFileValue enter");
        HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("gps_files_switch_screen", "1"), false);
    }
}
