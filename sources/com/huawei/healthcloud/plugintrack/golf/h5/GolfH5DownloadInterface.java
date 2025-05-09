package com.huawei.healthcloud.plugintrack.golf.h5;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.healthcloud.plugintrack.golf.bean.UpdateCbkResult;
import com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadTaskUtils;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GolfClubListInfoDataRsp;
import com.huawei.healthcloud.plugintrack.golf.device.CloudHelper;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ghb;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GolfH5DownloadInterface extends JsBaseModule {
    public static final int COURSE_IN_USE = 4;
    public static final int DEFAULT_ERROR_CODE = 1;
    public static final int DEFAULT_SUCCESS_CODE = 0;
    public static final int DEVICE_NO_SPACE = 2;
    private static final int RETRY_MAX_TIMES = 3;
    private static final String TAG = "Track_GolfH5DownloadInterface";

    @JavascriptInterface
    public void downloadMap(long j, long j2) {
        ReleaseLogUtil.e(TAG, "call downloadMap startOldInterface: ", Long.valueOf(j2));
        downloadMap(j, j2, false);
    }

    @JavascriptInterface
    public void downloadMapNew(long j, String str) {
        try {
            LogUtil.a(TAG, "call downloadMapNew: ", str);
            JSONObject jSONObject = new JSONObject(str);
            downloadMap(j, jSONObject.optLong("courseId", 0L), jSONObject.optBoolean("isAnon", false));
        } catch (JSONException e) {
            LogUtil.b(TAG, "downloadMap jsonException", e.getMessage());
            onFailureCallback(j, "parse param error");
        }
    }

    private void downloadMap(final long j, final long j2, final boolean z) {
        ReleaseLogUtil.e(TAG, "call downloadMap startNewInterface: ", Long.valueOf(j2), "isAnon is: " + z);
        GolfDeviceProxy.getInstance().isDevicesPinged(new CommonSingleCallback<Boolean>() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5DownloadInterface.1
            @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
            public void callback(Boolean bool) {
                if (GolfH5DownloadInterface.this.mH5ProInstance == null) {
                    ReleaseLogUtil.d(GolfH5DownloadInterface.TAG, "downloadMap h5ProInstance is null");
                    return;
                }
                InvokerBundle invokerBundle = new InvokerBundle(GolfH5DownloadInterface.this.mH5ProInstance.mH5ProJsCbkInvoker, j);
                if (bool.booleanValue()) {
                    if (CloudHelper.getVersion(j2, z) > 0) {
                        GolfDownloadTaskUtils.getInstance().downloadHandler(j2, GolfDeviceProxy.getInstance().getWatchType(), -1, invokerBundle, z);
                        return;
                    } else {
                        invokerBundle.getInvoker().onComplete(1, "", invokerBundle.getCallbackId());
                        LogUtil.h(GolfH5DownloadInterface.TAG, "invalid course version");
                        return;
                    }
                }
                invokerBundle.getInvoker().onComplete(1, "App ping devices failed in downloadMap", invokerBundle.getCallbackId());
                ReleaseLogUtil.d(GolfH5DownloadInterface.TAG, "App ping devices failed in downloadMap");
            }
        });
    }

    @JavascriptInterface
    public static void cancelDownload(long j, long j2) {
        ReleaseLogUtil.e(TAG, "call cancelDownload start: ", Long.valueOf(j2));
        GolfDownloadTaskUtils.getInstance().cancelDownloadTask(j2);
        GolfDeviceProxy.getInstance().sendCancelCourseMap((int) j2);
    }

    @JavascriptInterface
    public void deleteMap(long j, final String str) {
        ReleaseLogUtil.e(TAG, "call deleteMap start");
        if (this.mH5ProInstance == null) {
            ReleaseLogUtil.d(TAG, "deleteMap h5ProInstance is null");
            return;
        }
        final InvokerBundle invokerBundle = new InvokerBundle(this.mH5ProInstance.mH5ProJsCbkInvoker, j);
        if (str == null || str.trim().isEmpty()) {
            LogUtil.h(TAG, "deleteMap receive invalid courseIds");
            invokerBundle.getInvoker().onComplete(1, "deleteMap receive invalid courseIds", invokerBundle.getCallbackId());
        } else {
            GolfDeviceProxy.getInstance().isDevicesPinged(new CommonSingleCallback<Boolean>() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5DownloadInterface.2
                @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                public void callback(Boolean bool) {
                    if (bool.booleanValue()) {
                        GolfDeviceProxy.getInstance().sendDelCourses((Long[]) new Gson().fromJson(CommonUtil.z(str), Long[].class), 3, invokerBundle);
                    } else {
                        invokerBundle.getInvoker().onComplete(1, "App ping devices failed in deleteMap", invokerBundle.getCallbackId());
                        ReleaseLogUtil.d(GolfH5DownloadInterface.TAG, "App ping devices failed in deleteMap");
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void getAllCourseData(final long j) {
        ReleaseLogUtil.e(TAG, "getAllCourseData start");
        GolfDeviceProxy.getInstance().isDevicesPinged(new CommonSingleCallback<Boolean>() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5DownloadInterface.3
            @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
            public void callback(Boolean bool) {
                if (GolfH5DownloadInterface.this.mH5ProInstance == null) {
                    ReleaseLogUtil.d(GolfH5DownloadInterface.TAG, "getAllCourseData h5ProInstance is null");
                    return;
                }
                InvokerBundle invokerBundle = new InvokerBundle(GolfH5DownloadInterface.this.mH5ProInstance.mH5ProJsCbkInvoker, j);
                if (bool.booleanValue()) {
                    GolfDeviceProxy.getInstance().getDeviceDownloadedCourses(3, invokerBundle);
                } else {
                    invokerBundle.getInvoker().onComplete(1, "App ping devices failed in getAllCourseData", invokerBundle.getCallbackId());
                    ReleaseLogUtil.d(GolfH5DownloadInterface.TAG, "App ping devices failed in getAllCourseData");
                }
            }
        });
    }

    @JavascriptInterface
    public void isCourseTransmitting(long j, long j2) {
        ReleaseLogUtil.e(TAG, "isCourseTransmitting start ", Long.valueOf(j2));
        if (this.mH5ProInstance == null) {
            ReleaseLogUtil.d(TAG, "isCourseDownload h5ProInstance is null");
            return;
        }
        InvokerBundle invokerBundle = new InvokerBundle(this.mH5ProInstance.mH5ProJsCbkInvoker, j);
        boolean isCourseDownloading = GolfDownloadTaskUtils.getInstance().isCourseDownloading(j2);
        LogUtil.a(TAG, "isCourseDownloading is ", Boolean.valueOf(isCourseDownloading));
        if (isCourseDownloading) {
            invokerBundle.getInvoker().onComplete(0, "Course is transmitting", invokerBundle.getCallbackId());
        } else {
            invokerBundle.getInvoker().onComplete(1, "Course is not transmitting", invokerBundle.getCallbackId());
        }
    }

    @JavascriptInterface
    public void rebindCallback(long j, long j2) {
        LogUtil.a(TAG, "rebinding callback" + j + " CourseID : " + j2);
        if (this.mH5ProInstance == null) {
            ReleaseLogUtil.d(TAG, "rebindCallback h5ProInstance is null");
        } else {
            GolfDownloadTaskUtils.getInstance().putDownloadInvoker(new InvokerBundle(this.mH5ProInstance.mH5ProJsCbkInvoker, j), j2);
        }
    }

    @JavascriptInterface
    public void updateMapsCallback(long j) {
        LogUtil.a(TAG, "bgUpdateCallback " + j);
        if (this.mH5ProInstance == null) {
            ReleaseLogUtil.d(TAG, "bgUpdateCallback h5ProInstance is null");
            return;
        }
        InvokerBundle bgUpdateInvoker = GolfDownloadTaskUtils.getInstance().getBgUpdateInvoker();
        long[] bgUpdateCourseIds = GolfDownloadTaskUtils.getInstance().getBgUpdateCourseIds();
        Object[] objArr = new Object[3];
        objArr[0] = "bgUpdateCallback courseIds " + Arrays.toString(bgUpdateCourseIds);
        objArr[1] = " invokerBundle == null is ";
        objArr[2] = Boolean.valueOf(bgUpdateInvoker == null);
        LogUtil.a(TAG, objArr);
        boolean z = bgUpdateCourseIds.length > 0;
        if (bgUpdateInvoker == null) {
            bgUpdateInvoker = new InvokerBundle(this.mH5ProInstance.mH5ProJsCbkInvoker, j);
            if (z) {
                GolfDownloadTaskUtils.getInstance().putDownloadInvoker(bgUpdateInvoker, bgUpdateCourseIds);
            }
        } else {
            bgUpdateInvoker.setCallbackId(j);
            bgUpdateInvoker.setInvoker(this.mH5ProInstance.mH5ProJsCbkInvoker);
        }
        bgUpdateInvoker.onSuccess("");
        if (z) {
            return;
        }
        bgUpdateInvoker.getInvoker().onComplete(0, "not bgUpdateCallback", j);
    }

    @JavascriptInterface
    public void sendGolfClubInfo(long j, String str) {
        ReleaseLogUtil.e(TAG, "sendGolfClubInfo callbackId: ", Long.valueOf(j), "golfClubInfo:", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "sendGolfClubInfo golfClubInfo isEmpty");
            onFailureCallback(j, "sendGolfClubInfo golfClubInfo isEmpty");
        } else {
            GolfDeviceProxy.getInstance().sendGolfClubInfo((GolfClubListInfoDataRsp) ghb.a(str, GolfClubListInfoDataRsp.class), new InvokerBundle(this.mH5ProInstance.mH5ProJsCbkInvoker, j));
        }
    }

    /* loaded from: classes4.dex */
    public static class InvokerBundle {
        private long callbackId;
        private H5ProJsCbkInvoker<Object> invoker;

        public InvokerBundle(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, long j) {
            this.invoker = h5ProJsCbkInvoker;
            this.callbackId = j;
        }

        public H5ProJsCbkInvoker<Object> getInvoker() {
            return this.invoker;
        }

        public void setInvoker(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker) {
            this.invoker = h5ProJsCbkInvoker;
        }

        public void setCallbackId(long j) {
            this.callbackId = j;
        }

        public long getCallbackId() {
            return this.callbackId;
        }

        public void onSuccess(String str) {
            onSuccess(str, 0, 0);
        }

        public void onSuccess(String str, int i, int i2) {
            boolean isBgUpdate = GolfDownloadTaskUtils.getInstance().isBgUpdate(i);
            LogUtil.a(GolfH5DownloadInterface.TAG, "InvokerBundle onSuccess progress", str, " courseId = ", Integer.valueOf(i), " status = ", Integer.valueOf(i2), " isBgUpdate ", Boolean.valueOf(isBgUpdate));
            if (isBgUpdate) {
                this.invoker.onSuccess(new UpdateCbkResult(str, i, true, i2), this.callbackId);
            } else {
                this.invoker.onSuccess(str, this.callbackId);
            }
        }

        public void onFailure(int i, String str, int i2) {
            ReleaseLogUtil.e(GolfH5DownloadInterface.TAG, "InvokerBundle onFailure errorCode = ", Integer.valueOf(i), " dec = ", str, " courseId ", Integer.valueOf(i2));
            GolfDownloadTaskUtils.getInstance().removeBgUpdate(i2);
            this.invoker.onFailure(i, str, this.callbackId);
        }

        public void onComplete(int i, String str, int i2) {
            ReleaseLogUtil.e(GolfH5DownloadInterface.TAG, "InvokerBundle onComplete restValue = ", Integer.valueOf(i), " dec = ", str, " courseId ", Integer.valueOf(i2));
            long j = i2;
            if (GolfDownloadTaskUtils.getInstance().isBgUpdate(j)) {
                GolfDownloadTaskUtils.getInstance().clearBgUpdate();
            } else {
                GolfDownloadTaskUtils.getInstance().removeBgUpdate(j);
            }
            this.invoker.onComplete(i, str, this.callbackId);
        }
    }
}
