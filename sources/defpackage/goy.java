package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.watchface.WatchFaceApi;
import com.huawei.watchface.WatchFaceType;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes7.dex */
public class goy {
    private static final Set<Integer> d = Collections.unmodifiableSet(new HashSet<Integer>() { // from class: goy.2
        private static final long serialVersionUID = -4145847043855378968L;

        {
            add(Integer.valueOf(WatchFaceType.VIDEO.value()));
            add(Integer.valueOf(WatchFaceType.ALBUM.value()));
            add(Integer.valueOf(WatchFaceType.KALEIDOSCOPE.value()));
            add(Integer.valueOf(WatchFaceType.WEAR.value()));
        }
    });

    /* renamed from: a, reason: collision with root package name */
    private static final IBaseResponseCallback f12889a = new IBaseResponseCallback() { // from class: goy.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0 && (obj instanceof H5ProLaunchOption.Builder)) {
                H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.watchface", ((H5ProLaunchOption.Builder) obj).build());
                jqh.c("e4");
            }
        }
    };

    public static void a(String str) {
        ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFace:", str);
        if (cpl.c().d() == null) {
            ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "currentDevice is null");
            nrh.b(BaseApplication.getContext(), R.string.IDS_device_not_support_my_watch_face);
            return;
        }
        boolean isSupportMyWatch = WatchFaceUtil.isSupportMyWatch();
        Uri parse = Uri.parse(str);
        String queryParameter = parse.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        String queryParameter2 = parse.getQueryParameter("deviceType");
        String queryParameter3 = parse.getQueryParameter(BleConstants.KEY_PATH);
        ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFace from ", queryParameter);
        if (isSupportMyWatch && Objects.equals(queryParameter3, "myWatchface")) {
            a();
            return;
        }
        String queryParameter4 = parse.getQueryParameter("watchFaceType");
        if (isSupportMyWatch && "watchfaceDetail".equals(queryParameter3) && !TextUtils.isEmpty(queryParameter4)) {
            b(queryParameter4);
            return;
        }
        String queryParameter5 = parse.getQueryParameter("watchFaceId");
        String queryParameter6 = parse.getQueryParameter("watchfaceVersion");
        if (isSupportMyWatch && Objects.equals(queryParameter3, "watchfaceDetail") && !TextUtils.isEmpty(queryParameter5) && !TextUtils.isEmpty(queryParameter6)) {
            if (!c(queryParameter2)) {
                ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFace deviceType not match");
                c();
                return;
            } else {
                e(queryParameter5, queryParameter6);
                return;
            }
        }
        if ("watchfaceDetail".equals(queryParameter3)) {
            ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFace jump to album qrcode");
            pep.g();
        } else if (!isSupportMyWatch) {
            ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFace not support my watchface");
            c();
        } else {
            ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFace fail url is error");
        }
    }

    private static boolean c(String str) {
        LogUtil.a("WatchFaceDeeplinkUtils", "isDeviceTypeMatched enter:", str);
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        DeviceInfo d2 = cpl.c().d();
        if (d2 == null) {
            LogUtil.a("WatchFaceDeeplinkUtils", "isDeviceTypeMatched currentDevice is null");
            return false;
        }
        String hiLinkDeviceId = d2.getHiLinkDeviceId();
        String deviceSubModelId = d2.getDeviceSubModelId();
        LogUtil.a("WatchFaceDeeplinkUtils", "isDeviceTypeMatched hiLinkDeviceId:", hiLinkDeviceId, deviceSubModelId);
        if (TextUtils.isEmpty(hiLinkDeviceId)) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceDeeplinkUtils", "isDeviceTypeMatched hiLinkDeviceId is empty");
            return false;
        }
        String upperCase = hiLinkDeviceId.toUpperCase(Locale.ENGLISH);
        for (String str2 : str.split(",")) {
            String upperCase2 = str2.toUpperCase(Locale.ENGLISH);
            if (!upperCase2.contains(Constants.LINK)) {
                if (upperCase.equals(upperCase2)) {
                    LogUtil.a("WatchFaceDeeplinkUtils", "isDeviceTypeMatched hiLinkDeviceId matched");
                    return true;
                }
            } else {
                String[] split = upperCase2.split(Constants.LINK);
                if (split.length != 2) {
                    ReleaseLogUtil.d("DEVMGR_WatchFaceDeeplinkUtils", "isDeviceTypeMatched bad id param:", upperCase2);
                } else if (Objects.equals(upperCase, split[0]) && Objects.equals(deviceSubModelId, split[1])) {
                    LogUtil.a("WatchFaceDeeplinkUtils", "isDeviceTypeMatched matched");
                    return true;
                }
            }
        }
        return false;
    }

    private static void e(String str, String str2) {
        ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFaceByIdAndVersion enter");
        WatchFaceApi watchFaceApi = (WatchFaceApi) Services.a("WatchFaceApiManager", WatchFaceApi.class);
        if (watchFaceApi == null) {
            ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFaceH5 watchFaceApi is null");
        } else {
            watchFaceApi.openWatchFaceDetail(str, str2, f12889a);
        }
    }

    private static void b(final String str) {
        ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startDiyMyWatchFace start to Diy ", str);
        try {
            int parseInt = Integer.parseInt(str);
            if (!d.contains(Integer.valueOf(parseInt))) {
                ReleaseLogUtil.d("DEVMGR_WatchFaceDeeplinkUtils", "startDiyMyWatchFace bad diy type:", str);
                c();
                return;
            }
            WatchFaceApi watchFaceApi = (WatchFaceApi) Services.a("WatchFaceApiManager", WatchFaceApi.class);
            if (watchFaceApi == null) {
                ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFaceH5 watchFaceApi is null");
            } else {
                watchFaceApi.openWatchFaceDetail(parseInt, new IBaseResponseCallback() { // from class: goy.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0 && (obj instanceof H5ProLaunchOption.Builder)) {
                            H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.watchface", ((H5ProLaunchOption.Builder) obj).build());
                            jqh.c("e4");
                        } else {
                            ReleaseLogUtil.d("DEVMGR_WatchFaceDeeplinkUtils", "startDiyMyWatchFace diy not support:", str);
                            goy.c();
                        }
                    }
                });
            }
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceDeeplinkUtils", "startDiyMyWatchFace NumberFormatException type:", str);
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c() {
        nrh.b(BaseApplication.getContext(), R.string.IDS_device_not_support_my_watch_face_deeplink);
    }

    private static void a() {
        ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFaceHome enter");
        WatchFaceApi watchFaceApi = (WatchFaceApi) Services.a("WatchFaceApiManager", WatchFaceApi.class);
        if (watchFaceApi == null) {
            ReleaseLogUtil.e("DEVMGR_WatchFaceDeeplinkUtils", "startMyWatchFaceH5 watchFaceApi is null");
        } else {
            watchFaceApi.openMyWatchFace(f12889a);
        }
    }
}
