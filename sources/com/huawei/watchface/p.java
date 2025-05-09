package com.huawei.watchface;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.mvp.model.datatype.DownloadQueryBean;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceInfo;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.filedownload.FilePuller;
import com.huawei.watchface.mvp.model.filedownload.PullListenerInterface;
import com.huawei.watchface.mvp.model.filedownload.PullResult;
import com.huawei.watchface.mvp.model.filedownload.PullTask;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.callback.OperationCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class p {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11157a = "p";
    private static p b;
    private Context g;
    private String h;
    private OperateWatchFaceCallback i;
    private OperateWatchFaceCallback j;
    private LinkedHashMap<String, String> c = new LinkedHashMap<>(32);
    private ConcurrentHashMap<String, String> d = new ConcurrentHashMap<>(32);
    private ConcurrentHashMap<String, String> e = new ConcurrentHashMap<>(32);
    private ConcurrentHashMap<String, Integer> f = new ConcurrentHashMap<>(32);
    private final Type k = new TypeToken<List<PullTask>>() { // from class: com.huawei.watchface.p.1
    }.getType();

    private p(Context context) {
        this.g = context;
        this.h = this.g.getFilesDir().getAbsolutePath() + File.separator + "watchface" + File.separator;
    }

    public static p a(Context context) {
        if (b == null) {
            synchronized (p.class) {
                if (b == null) {
                    b = new p(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback, boolean z) {
        if (z) {
            this.i = operateWatchFaceCallback;
            this.j = null;
        } else {
            this.j = operateWatchFaceCallback;
        }
    }

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            HwLog.w(f11157a, "hiTopId or version is null");
        } else {
            this.c.put(str, str2);
        }
    }

    public LinkedHashMap<String, String> a() {
        return this.c;
    }

    public ConcurrentHashMap<String, String> b() {
        return this.d;
    }

    public ConcurrentHashMap<String, String> c() {
        return this.e;
    }

    public void b(String str, String str2) {
        this.e.put(str, str2);
    }

    public void a(final String str, final String str2, final int i, final InstallWatchFaceBean installWatchFaceBean) {
        String g = d.a().g(str);
        final String c = d.a().c(str);
        String buildTaskId = FilePuller.getInstance(this.g).buildTaskId(c, str2);
        String str3 = f11157a;
        HwLog.i(str3, "continueDownloadWatchFace, taskId:" + buildTaskId + " ,operationCode:" + i + " ,hiTopId:" + str + " ,version:" + str2 + " ,deviceHitopId:" + g + " ,h5HitopId:" + c);
        if (this.c.containsKey(g) || this.d.containsKey(g)) {
            HwLog.i(str3, "continueDownloadWatchFace, mWaitInstallMap contain hiTopId");
            if (i == 1) {
                HwLog.i(str3, "continueDownloadWatchFace, PAUSE_TASK");
                this.c.remove(g);
                this.d.put(g, str2);
                FilePuller.getInstance(this.g).notifyDownloadStatus(buildTaskId, 3);
                OperateWatchFaceCallback operateWatchFaceCallback = this.i;
                if (operateWatchFaceCallback != null) {
                    operateWatchFaceCallback.transmitDownloadWatchFaceResult(c, str2, 3);
                }
                OperateWatchFaceCallback operateWatchFaceCallback2 = this.j;
                if (operateWatchFaceCallback2 != null) {
                    operateWatchFaceCallback2.transmitDownloadWatchFaceResult(c, str2, 3);
                    return;
                }
                return;
            }
            HwLog.i(str3, "continueDownloadWatchFace, RESTART_TASK");
            this.d.remove(g);
            if (this.c.containsKey(g) || this.d.containsKey(g)) {
                FilePuller.getInstance(this.g).notifyDownloadStatus(buildTaskId, 5);
            } else {
                FilePuller.getInstance(this.g).notifyDownloadStatus(buildTaskId, -8);
            }
            HwWatchFaceManager.getInstance(this.g).applyWatchFace(g, str2, 1, installWatchFaceBean.getWatchScreen(), installWatchFaceBean.getInstallationType());
            OperateWatchFaceCallback operateWatchFaceCallback3 = this.i;
            if (operateWatchFaceCallback3 != null) {
                operateWatchFaceCallback3.transmitDownloadWatchFaceResult(c, str2, 4);
            }
            OperateWatchFaceCallback operateWatchFaceCallback4 = this.j;
            if (operateWatchFaceCallback4 != null) {
                operateWatchFaceCallback4.transmitDownloadWatchFaceResult(c, str2, 4);
                return;
            }
            return;
        }
        HwLog.w(str3, "mWaitInstallMap or mPauseInstallMap not contains key");
        FilePuller.getInstance(this.g).operationDownloadTask(c, str2, i, new OperationCallback() { // from class: com.huawei.watchface.p.2
            @Override // com.huawei.watchface.utils.callback.OperationCallback
            public void operationResult(int i2) {
                p.this.a(c, 0);
                if (i2 != 0 || p.this.i == null) {
                    return;
                }
                HwLog.i(p.f11157a, "continueDownloadWatchFace, operationResult");
                p.this.i.transmitDownloadWatchFaceResult(c, str2, i == 1 ? 3 : 4);
                if (i == 2) {
                    if (!StringUtils.strEquals(str, c)) {
                        installWatchFaceBean.setWatchFaceHiTopId(c);
                    }
                    HwWatchFaceManager.getInstance(p.this.g).installWatchFace(installWatchFaceBean);
                }
                if (p.this.j != null) {
                    p.this.j.transmitDownloadWatchFaceResult(c, str2, i == 1 ? 3 : 4);
                }
            }
        });
    }

    public void c(String str, String str2) {
        if (str != null && str2 != null) {
            this.c.put(str, str2);
        }
        HwLog.i(f11157a, "deviceDisconnectedPauseTask() hiTopId: " + str + ", version: " + str2 + ", mWaitInstallMap size: " + this.c.size());
        FilePuller.getInstance(this.g).notifyMapTaskPause(this.c, 3);
        OperateWatchFaceCallback operateWatchFaceCallback = this.i;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitRefreshPullData();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.j;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitRefreshPullData();
        }
    }

    public void a(boolean z) {
        if (HwWatchFaceBtManager.getInstance(this.g).getConnectWatchDeviceInfo() == null) {
            HwLog.w(f11157a, "syncDownloadData, current device info is null");
            return;
        }
        String deviceIdentify = HwWatchFaceApi.getInstance(this.g).getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            HwLog.w(f11157a, "syncDownloadData, deviceId is null");
            return;
        }
        String t = t();
        if (t != null && t.equals(deviceIdentify) && !z) {
            if (i() == null || !FilePuller.getInstance(this.g).getDownloadTaskList().isEmpty()) {
                HwLog.i(f11157a, "syncDownloadData, don't sync data");
                return;
            } else {
                HwLog.i(f11157a, "syncDownloadData, setDownloadTaskList");
                return;
            }
        }
        HwLog.i(f11157a, "syncDownloadData, deviceImei diff");
        j();
        d();
        FilePuller.getInstance(this.g).clearTask();
        f(ao.a(deviceIdentify, "savePw"));
    }

    public void d() {
        HwLog.i(f11157a, "clearInstallTask");
        this.c.clear();
        this.d.clear();
    }

    public boolean e() {
        return (TextUtils.isEmpty(HwWatchFaceManager.getInstance(this.g).getCurrentInstallWatchFaceHiTopId()) || TextUtils.isEmpty(HwWatchFaceManager.getInstance(this.g).getCurrentInstallWatchFaceVersion())) ? false : true;
    }

    public boolean f() {
        String str = f11157a;
        HwLog.i(str, "clearAllInstallTasks -- enter");
        if (!e() && FilePuller.getInstance(this.g).getDownloadTaskList().isEmpty()) {
            HwLog.i(str, "clearAllInstallTasks -- there is no task to clear");
            return false;
        }
        g();
        h();
        d();
        return true;
    }

    public void g() {
        if (e()) {
            String currentInstallWatchFaceHiTopId = HwWatchFaceManager.getInstance(this.g).getCurrentInstallWatchFaceHiTopId();
            HwWatchFaceManager.getInstance(this.g).cancelInstallWatchFace(currentInstallWatchFaceHiTopId, HwWatchFaceManager.getInstance(this.g).getCurrentInstallWatchFaceVersion());
            a(this.g).d(currentInstallWatchFaceHiTopId);
        }
    }

    public void h() {
        ConcurrentHashMap<String, PullTask> downloadTaskList = FilePuller.getInstance(this.g).getDownloadTaskList();
        if (downloadTaskList.isEmpty()) {
            HwLog.w(f11157a, "getDownloadTaskList is empty.");
            return;
        }
        Iterator<Map.Entry<String, PullTask>> it = downloadTaskList.entrySet().iterator();
        while (it.hasNext()) {
            String[] split = it.next().getKey().split("_");
            if (split.length >= 2) {
                HwWatchFaceManager.getInstance(this.g).cancelInstallWatchFace(split[0], split[1]);
            }
        }
    }

    public boolean a(String str) {
        ConcurrentHashMap<String, PullTask> downloadTaskList = FilePuller.getInstance(this.g).getDownloadTaskList();
        if (downloadTaskList.isEmpty()) {
            HwLog.w(f11157a, "getDownloadTaskList is empty.");
            return false;
        }
        Iterator<Map.Entry<String, PullTask>> it = downloadTaskList.entrySet().iterator();
        while (it.hasNext()) {
            String[] split = it.next().getKey().split("_");
            if (split.length >= 2 && split[0].equals(str)) {
                HwLog.w(f11157a, "hiTopId is in download task list.");
                return true;
            }
        }
        return false;
    }

    public String i() {
        Context context = this.g;
        if (context == null) {
            HwLog.e(f11157a, "getSharedPreferencesDownloadTask() mContext is null.");
            return null;
        }
        String string = context.getSharedPreferences("watchFaceDownload", 0).getString("downloadWatchFaceData", null);
        HwLog.d(f11157a, "getSharedPreferencesDownloadTask() completed.");
        return string;
    }

    public void j() {
        Context context = this.g;
        if (context == null) {
            HwLog.w(f11157a, "clearSharedPreferencesData, mContext is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("watchFaceDownload", 0).edit();
        edit.clear();
        edit.apply();
        HwLog.i(f11157a, "clearSharedPreferencesData completed");
    }

    public String k() {
        String str = f11157a;
        HwLog.d(str, "getWatchFaceDownloadData() enter.");
        JSONArray jSONArray = new JSONArray();
        ConcurrentHashMap<String, PullTask> downloadTaskList = FilePuller.getInstance(this.g).getDownloadTaskList();
        if (downloadTaskList.isEmpty()) {
            HwLog.w(str, "getWatchFaceDownloadData() downloadList is empty.");
            return jSONArray.toString();
        }
        for (Map.Entry<String, PullTask> entry : downloadTaskList.entrySet()) {
            String[] split = entry.getKey().split("_");
            if (split.length >= 2) {
                String str2 = split[0];
                String str3 = split[1];
                String str4 = str2 + "_" + str3;
                int fetchStatus = entry.getValue().fetchStatus();
                if (!d(str2, str3)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("id", str2);
                        jSONObject.put("version", str3);
                        jSONObject.put("status", fetchStatus);
                    } catch (Exception e) {
                        HwLog.e(f11157a, "getWatchFaceDownloadData() Exception." + HwLog.printException(e));
                    }
                    jSONArray.put(jSONObject);
                } else {
                    HwLog.d(f11157a, "getWatchFaceDownloadData() remove taskId: " + str4);
                    FilePuller.getInstance(this.g).cancelTask(str2, str3, false);
                    this.c.remove(str2);
                }
            }
        }
        return jSONArray.toString();
    }

    private boolean d(String str, String str2) {
        String str3 = f11157a;
        HwLog.i(str3, "isDeviceContain() hiTopId: " + str + ", version: " + str2);
        ArrayList<WatchResourcesInfo> allWatchInfo = HwWatchFaceBtManager.getInstance(this.g).getAllWatchInfo();
        boolean z = false;
        if (ArrayUtils.isEmpty(allWatchInfo)) {
            HwLog.e(str3, "isDeviceContain() allWatchFaceInfo is null.");
            return false;
        }
        Iterator<WatchResourcesInfo> it = allWatchInfo.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WatchResourcesInfo next = it.next();
            if (next.getWatchInfoId().equals(str) && next.getWatchInfoVersion().equals(str2)) {
                z = true;
                break;
            }
        }
        HwLog.i(f11157a, "isDeviceContain() isDeviceContain: " + z);
        return z;
    }

    public int l() {
        HwLog.i(f11157a, "getWatchFaceDownloadItemNum");
        return FilePuller.getInstance(this.g).getDownloadTaskList().size();
    }

    public String a(final int i, final DownloadQueryBean downloadQueryBean) {
        String str = f11157a;
        HwLog.i(str, "sendContinueDownloadWatchFace enter.");
        final String hitopId = downloadQueryBean.getHitopId();
        final String version = downloadQueryBean.getVersion();
        final InstallWatchFaceBean installWatchFaceBean = new InstallWatchFaceBean();
        installWatchFaceBean.setWatchFaceHiTopId(hitopId);
        installWatchFaceBean.setVersion(version);
        installWatchFaceBean.setFileSize(downloadQueryBean.getFileSize());
        installWatchFaceBean.setWatchScreen(downloadQueryBean.getScreen());
        installWatchFaceBean.setProductId(downloadQueryBean.getProductId());
        installWatchFaceBean.setUpdate(downloadQueryBean.isUpdate());
        installWatchFaceBean.setOneClick(downloadQueryBean.isOneClick());
        installWatchFaceBean.setZip(downloadQueryBean.isZip());
        installWatchFaceBean.setInstallationType(2);
        installWatchFaceBean.setVipFreeWatchFace(HwWatchFaceManager.getInstance(this.g).isVipFreeWatchFaceContain(downloadQueryBean.getHitopId()));
        if (t.a().i(hitopId, version)) {
            HwLog.i(str, "sendContinueDownloadWatchFace -- isWaitTryOutWatchFaceId");
            HashMap<String, String> e = t.a().e(hitopId, version);
            if (ArrayUtils.a(e)) {
                HwLog.e(str, "sendContinueDownloadWatchFace -- tryOutDownUrlHashcode is empty");
                return "1";
            }
            installWatchFaceBean.setInstallationType(1);
            installWatchFaceBean.setFileUrl(e.get(RecommendConstants.DOWNLOAD_URL));
            installWatchFaceBean.setHashCode(e.get(WatchFaceConstant.HASHCODE));
            a(hitopId, version, i, installWatchFaceBean);
            return "0";
        }
        HashMap<String, String> a2 = k.a().a(hitopId, version);
        if (!ArrayUtils.a(a2)) {
            String str2 = a2.get(RecommendConstants.DOWNLOAD_URL);
            installWatchFaceBean.setHashCode(a2.get(WatchFaceConstant.HASHCODE));
            installWatchFaceBean.setFileUrl(str2);
            a(hitopId, version, i, installWatchFaceBean);
            return "0";
        }
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.p.3
            @Override // java.lang.Runnable
            public void run() {
                int a3 = k.a().a(downloadQueryBean, installWatchFaceBean);
                if (a3 != 1) {
                    HwLog.e(p.f11157a, "sendContinueDownloadWatchFace -- return a non-zero value, resultCode==" + a3);
                    return;
                }
                BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.p.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HwLog.i(p.f11157a, "sendContinueDownloadWatchFace -- start install WatchFace");
                        p.this.a(hitopId, version, i, installWatchFaceBean);
                    }
                });
            }
        });
        return "0";
    }

    public void a(String str, String str2, String str3, WatchFaceInfo watchFaceInfo, boolean z, boolean z2, PullListenerInterface pullListenerInterface) {
        PullTask pullTask = new PullTask();
        if (TextUtils.isEmpty(str3)) {
            str3 = b(str);
        }
        pullTask.configDestPath(str3);
        pullTask.configHttpUrl(str2);
        pullTask.configFileUrlJson(null);
        pullTask.configListener(pullListenerInterface);
        pullTask.configOption(CommonUtils.b(watchFaceInfo.fetchFileType()));
        pullTask.configTotalSize(watchFaceInfo.fetchFileSize());
        pullTask.configParam(e(null));
        pullTask.configUUID(str);
        pullTask.configFiledID(null);
        pullTask.configDigest(watchFaceInfo.fetchDigest());
        pullTask.configZip(z);
        pullTask.setInstallationType(watchFaceInfo.getInstallationType());
        pullTask.configOneCLick(z2);
        if (PversionSdUtils.a() > watchFaceInfo.fetchFileSize() + FilePuller.getInstance(this.g).getDownloadingMapTotalSize()) {
            HwLog.i(f11157a, "addDownloadTask() pull plugin task.");
            FilePuller.getInstance(this.g).addTask(pullTask);
            if (str != null) {
                a(str.split("_")[0], 0);
                return;
            }
            return;
        }
        HwLog.w(f11157a, "addDownloadTask() this storage is not enough.");
        PullResult pullResult = new PullResult();
        pullResult.configStatus(-9);
        pullResult.setInstallationType(watchFaceInfo.getInstallationType());
        pullListenerInterface.onPullingChange(pullTask, pullResult);
    }

    public String b(String str) {
        return this.h + str;
    }

    private String e(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(RecommendConstants.FILE_ID, str);
            jSONObject.put(RecommendConstants.VER, "0");
        } catch (JSONException unused) {
            HwLog.e(f11157a, "JSONException");
        }
        return jSONObject.toString();
    }

    public void m() {
        LinkedHashMap<String, String> a2 = a(this.g).a();
        String str = f11157a;
        HwLog.i(str, "deviceConnectRestartTask, map:" + a2.size());
        if (a2.isEmpty()) {
            HwLog.w(str, "deviceConnectRestartTask, map is empty");
            return;
        }
        FilePuller.getInstance(this.g).notifyMapTaskPause(a2, 5);
        OperateWatchFaceCallback operateWatchFaceCallback = this.i;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitRefreshPullData();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.j;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitRefreshPullData();
        }
        Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
        String str2 = null;
        while (it.hasNext()) {
            String key = it.next().getKey();
            String str3 = a2.get(key);
            if (!cx.a().a(key + "_" + str3)) {
                HwLog.i(f11157a, "deviceConnectRestartTask file not exist hitopId:" + key);
                it.remove();
                a(this.g).b().remove(key);
                FilePuller.getInstance(this.g).notifyDownloadStatus(key + "_" + str3, 3);
                OperateWatchFaceCallback operateWatchFaceCallback3 = this.i;
                if (operateWatchFaceCallback3 != null) {
                    operateWatchFaceCallback3.transmitDownloadWatchFaceResult(key, str3, 3);
                }
                OperateWatchFaceCallback operateWatchFaceCallback4 = this.j;
                if (operateWatchFaceCallback4 != null) {
                    operateWatchFaceCallback4.transmitDownloadWatchFaceResult(key, str3, 3);
                }
            } else if (TextUtils.isEmpty(str2)) {
                str2 = key;
            }
        }
        HwLog.i(f11157a, "deviceConnectRestartTask nextStartHitopId:" + str2);
        if (TextUtils.isEmpty(str2) || a2.get(str2) == null) {
            return;
        }
        HwWatchFaceManager.getInstance(this.g).applyWatchFace(str2, a2.get(str2), 1, "");
    }

    public void n() {
        r();
    }

    private static void r() {
        synchronized (p.class) {
            if (b != null) {
                b = null;
            }
        }
    }

    public void a(String str, int i) {
        HwLog.i(f11157a, "setProgressMap hiTopId :" + str);
        this.f.put(str, Integer.valueOf(i));
    }

    public int c(String str) {
        if (str != null && this.f.containsKey(str)) {
            return this.f.get(str).intValue();
        }
        return -1;
    }

    public void d(String str) {
        if (str == null || !this.f.containsKey(str)) {
            return;
        }
        this.f.remove(str);
    }

    private String s() {
        return HwWatchFaceApi.getInstance(this.g).getDeviceIdentify();
    }

    public boolean o() {
        String t = t();
        if (TextUtils.isEmpty(t)) {
            HwLog.w(f11157a, "isNeedInstallTask() sharedPreferencesDeviceId is empty.");
            return true;
        }
        String s = s();
        if (TextUtils.isEmpty(s)) {
            HwLog.w(f11157a, "isNeedInstallTask() deviceId is empty.");
            j();
            FilePuller.getInstance(this.g).clearTask();
            d();
            return false;
        }
        if (t.equals(s)) {
            return true;
        }
        HwLog.w(f11157a, "isNeedInstallTask() sharedPreferencesDeviceId diff");
        j();
        FilePuller.getInstance(this.g).clearTask();
        d();
        return false;
    }

    public void p() {
        HwLog.i(f11157a, "enter saveCurrentDeviceId");
        f(ao.a(s(), "savePw"));
    }

    private void f(String str) {
        if (this.g == null) {
            HwLog.w(f11157a, "saveDownloadDeviceId() mContext is null");
        } else if (TextUtils.isEmpty(str)) {
            HwLog.w(f11157a, "saveDownloadDeviceId() deviceId is empty.");
        } else {
            this.g.getSharedPreferences("watchFaceDownload", 0).edit().putString("downloadDeviceId", str).apply();
            HwLog.i(f11157a, "saveDownloadDeviceId() completed.");
        }
    }

    private String t() {
        Context context = this.g;
        if (context == null) {
            HwLog.w(f11157a, "getSharedPreferencesDownloadDeviceId() mContext is null.");
            return null;
        }
        String b2 = ao.b(context.getSharedPreferences("watchFaceDownload", 0).getString("downloadDeviceId", null), "savePw");
        String str = f11157a;
        HwLog.d(str, "getSharedPreferencesDownloadDeviceId() completed.");
        if (!TextUtils.isEmpty(b2)) {
            return b2;
        }
        HwLog.d(str, "getSharedPreferencesDownloadDeviceId() deviceId is empty.");
        return null;
    }
}
