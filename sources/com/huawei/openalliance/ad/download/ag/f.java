package com.huawei.openalliance.ad.download.ag;

import android.content.Context;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.mr;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.dl;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class f {
    static <T> void d(Context context, AppDownloadTask appDownloadTask, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            String b = be.b(appDownloadTask);
            if (ho.a()) {
                ho.a("ApDnApi", "appDownload=%s", b);
            }
            jSONObject.put("content", b);
            ContentRecord R = appDownloadTask.R();
            if (R == null) {
                return;
            }
            a(context, jSONObject, appDownloadTask, R);
            ms.a(context).a("reserveDownloadApp", jSONObject.toString(), remoteCallResultCallback, cls);
        } catch (JSONException unused) {
            a(remoteCallResultCallback, "startDownload JSONException", "reserveDownloadApp");
        }
    }

    static <T> void c(Context context, AppDownloadTask appDownloadTask, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", be.b(appDownloadTask));
            AppInfo a2 = a(appDownloadTask);
            if (a2 != null) {
                jSONObject.put(MapKeyNames.APP_INFO, be.b(a2));
            }
            ms.a(context).a("cancelDownloadApp", jSONObject.toString(), remoteCallResultCallback, cls);
        } catch (JSONException unused) {
            a(remoteCallResultCallback, "cancelDownload JSONException", "cancelDownloadApp");
        }
    }

    public static <T> void b(Context context, boolean z, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MapKeyNames.HAS_INSTALL_PERMISSION, z);
            ms.a(context).a("reportInstallPermission", jSONObject.toString(), remoteCallResultCallback, cls);
        } catch (JSONException unused) {
            ho.c("ApDnApi", "reportInstallPermission JSONException");
        }
    }

    static <T> void b(Context context, AppDownloadTask appDownloadTask, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", be.b(appDownloadTask));
            AppInfo a2 = a(appDownloadTask);
            if (a2 != null) {
                jSONObject.put(MapKeyNames.APP_INFO, be.b(a2));
            }
            ms.a(context).a("pauseDownloadApp", jSONObject.toString(), remoteCallResultCallback, cls);
        } catch (JSONException unused) {
            a(remoteCallResultCallback, "pauseDownload JSONException", "pauseDownloadApp");
        }
    }

    private static <T> void a(RemoteCallResultCallback<T> remoteCallResultCallback, String str, String str2) {
        ho.c("ApDnApi", str);
        if (remoteCallResultCallback != null) {
            CallResult<T> callResult = new CallResult<>();
            callResult.setCode(-1);
            callResult.setMsg(str);
            remoteCallResultCallback.onRemoteCallResult(str2, callResult);
        }
    }

    public static <T> void a(Context context, boolean z, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MapKeyNames.AUTO_OPEN, z);
            mr.a(context).a("setAutoOpen", jSONObject.toString(), cls);
        } catch (JSONException unused) {
            ho.c("ApDnApi", "setAutoOpenApp JSONException");
        }
    }

    public static <T> void a(Context context, boolean z, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MapKeyNames.REMOTE_SHARED_PREF_KEY, "AutoOpenForbidden");
            jSONObject.put(MapKeyNames.REMOTE_SHARED_PREF_VALUE, z);
            ms.a(context).a("remoteSharedPrefSet", jSONObject.toString(), remoteCallResultCallback, cls);
        } catch (JSONException unused) {
            ho.c("ApDnApi", "setAutoOpenForbidden JSONException");
        }
    }

    public static <T> void a(Context context, boolean z, int i, String str, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MapKeyNames.FULL_SCREEN_NOTIFY, z);
            jSONObject.put(MapKeyNames.ACTIVATE_NOTIFY_STYLE, i);
            jSONObject.put(MapKeyNames.PARAM_KEY, str);
            ms.a(context).a("reportFullScreenNotify", jSONObject.toString(), remoteCallResultCallback, cls);
        } catch (JSONException unused) {
            ho.c("ApDnApi", "reportFullScreenNotify JSONException");
        }
    }

    private static void a(Context context, JSONObject jSONObject, AppDownloadTask appDownloadTask, ContentRecord contentRecord) {
        String c = contentRecord.c(context);
        jSONObject.put(MapKeyNames.PARAM_FROM_SERVER, c);
        if (ho.a()) {
            ho.a("ApDnApi", "pfs:%s", dl.a(c));
        }
        String b = be.b(contentRecord.a(context));
        jSONObject.put(MapKeyNames.THIRD_MONITORS, b);
        if (ho.a()) {
            ho.a("ApDnApi", "monitors=%s", dl.a(b));
        }
        String b2 = be.b(contentRecord);
        jSONObject.put(MapKeyNames.CONTENT_RECORD, b2);
        ho.b("ApDnApi", "content:" + dl.a(b2));
        AppInfo B = appDownloadTask.B();
        String uniqueId = (B == null || B.getUniqueId() == null) ? "" : B.getUniqueId();
        jSONObject.put("unique_id", uniqueId);
        ho.a("ApDnApi", "unique_id:" + uniqueId);
    }

    static <T> void a(Context context, AppDownloadTask appDownloadTask, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            String b = be.b(appDownloadTask);
            if (ho.a()) {
                ho.a("ApDnApi", "appDownload=%s", b);
            }
            jSONObject.put("content", b);
            ContentRecord R = appDownloadTask.R();
            if (R != null) {
                a(context, jSONObject, appDownloadTask, R);
                ms.a(context).a("startFatDownloadApp", jSONObject.toString(), remoteCallResultCallback, cls);
                return;
            }
            ho.b("ApDnApi", "contentRecord is empty");
            AppInfo a2 = a(appDownloadTask);
            if (a2 != null) {
                jSONObject.put(MapKeyNames.APP_INFO, be.b(a2));
            }
            ms.a(context).a("resumeDownloadApp", jSONObject.toString(), remoteCallResultCallback, cls);
        } catch (JSONException unused) {
            a(remoteCallResultCallback, "startDownload JSONException", "startFatDownloadApp");
        }
    }

    public static <T> void a(Context context, int i, String str, String str2, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MapKeyNames.AG_PROTOCOL_STATUS, i);
            jSONObject.put(MapKeyNames.AG_DOWNLOAD_PACKAGE, str);
            jSONObject.put(MapKeyNames.AG_ACTION_NAME, str2);
            mr.a(context).a("syncAgProtocolStatus", jSONObject.toString(), cls);
        } catch (JSONException unused) {
            ho.c("ApDnApi", "syncAgProcolAgreeStatus JSONException");
        }
    }

    static <T> T a(Context context, AppInfo appInfo, Class<T> cls) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", be.b(appInfo));
            return mr.a(context).a("getDownloadStatus", jSONObject.toString(), cls).getData();
        } catch (JSONException unused) {
            ho.c("ApDnApi", "queryTask JSONException");
            return null;
        }
    }

    private static AppInfo a(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null || appDownloadTask.B() == null) {
            return null;
        }
        AppInfo appInfo = new AppInfo();
        appInfo.a(appDownloadTask.B().getPackageName());
        appInfo.o(appDownloadTask.B().b(appDownloadTask.F()));
        appInfo.E(appDownloadTask.H());
        appInfo.p(appDownloadTask.B().a());
        return appInfo;
    }
}
