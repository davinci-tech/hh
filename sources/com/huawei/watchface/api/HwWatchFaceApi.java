package com.huawei.watchface.api;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.hihealthkit.auth.IAuthorizationListener;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.activity.SafeService;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.secure.android.common.util.UrlUtil;
import com.huawei.watchface.R$drawable;
import com.huawei.watchface.R$string;
import com.huawei.watchface.ao;
import com.huawei.watchface.au;
import com.huawei.watchface.b;
import com.huawei.watchface.ba;
import com.huawei.watchface.cw;
import com.huawei.watchface.cx;
import com.huawei.watchface.di;
import com.huawei.watchface.dy;
import com.huawei.watchface.dz;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.eo;
import com.huawei.watchface.eu;
import com.huawei.watchface.fh;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.h;
import com.huawei.watchface.mvp.model.datatype.TransFileInfo;
import com.huawei.watchface.mvp.model.datatype.TransferBusiType;
import com.huawei.watchface.mvp.model.datatype.TransferFileReqType;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.n;
import com.huawei.watchface.o;
import com.huawei.watchface.t;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceBitmapUtil;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.WebViewUtils;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.ILoginCallback;
import com.huawei.watchface.utils.callback.IPhotoFileCallback;
import com.huawei.watchface.utils.callback.PluginOperationAdapter;
import com.huawei.watchface.utils.callback.WatchFaceHealthResponseListener;
import com.huawei.watchface.utils.permission.PermissionUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HwWatchFaceApi extends PluginBase {
    public static final String ACTION_FOREGROUND_STATUS = "com.huawei.haf.intent.action.FOREGROUND_STATUS_CHANGE";
    private static final String DEFAULT_WATCHFACE_SITE = "watchFace";
    private static final int GOTO_WATCHFACE_SHOP = -1;
    private static final String TAG = "HwWatchFaceApi";
    private static final int TRUE_VERSION = 1;
    private static final String URL_DETAIL_SUFFIX = "#/dial-detail";
    private static final String URL_END_SUFFIX = "cch5/health/watchFace/index.html";
    private static final String URL_FROM_SHORTCUTSE = "?from=shortcutse";
    private static final String URL_FROM_SUFFIX = "from=health_app";
    private static final String URL_INDEX_SUFFIX = "#/index";
    private static final String URL_PAY_VIP_COUPONS_SUFFIX = "#/member-coupon";
    private static final String URL_PAY_VIP_SUFFIX = "#/member-pay";
    private static final String URL_SUFFIX = "#/detail/native/";
    private static final String URL_SUFFIX_FROM = "?from=";
    private static final String URL_SUFFIX_FROM_SUB = "&from_sub=";
    private static final String WATCH_FACE_BETA_URL = "sandbox/cch5/health/watchFace/index.html";
    private static volatile HwWatchFaceApi sInstance;
    private Integer connectStatus;
    private String countryCodeCache;
    private fh crashReport;
    Map<String, Object> mAccountInfoMap;
    private Context mContext;
    Map<String, String> mDeviceInfo;
    private List<Map<String, String>> mDisableWearInfoList;
    private HwWatchFaceAdapter mHwWatchFaceAdapter;
    Map<String, Integer> mVersionInfo;
    private long initTime = 0;
    private boolean releaseLogSwitches = true;

    public interface RefreshAccessTokenListener {
        void onLoginFailed(Object obj);

        void onLoginSuccess(Object obj);
    }

    public int getAccessTokenType() {
        return 2;
    }

    public static HwWatchFaceApi getInstance(Context context) {
        if (context == null) {
            return null;
        }
        if (sInstance == null) {
            synchronized (HwWatchFaceApi.class) {
                if (sInstance == null) {
                    sInstance = new HwWatchFaceApi(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private HwWatchFaceApi(Context context) {
        clearCountryCode();
        this.mContext = context;
        Environment.setApplication((Application) context);
        registerBackgroundBroadcast();
        initCrashConfig();
        initNetworkClient();
    }

    public void setReleaseLogSwitches(boolean z) {
        HwLog.i(TAG, "setReleaseLogSwitches:" + z);
        this.releaseLogSwitches = z;
    }

    private void registerBackgroundBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_FOREGROUND_STATUS);
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(new BroadcastReceiver() { // from class: com.huawei.watchface.api.HwWatchFaceApi.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    boolean booleanExtra = intent.getBooleanExtra("isForeground", false);
                    long longExtra = intent.getLongExtra("time", 0L);
                    CommonUtils.b(booleanExtra);
                    HwLog.i(HwWatchFaceApi.TAG, "isForeground:" + booleanExtra + ",time:" + longExtra);
                } catch (Exception e) {
                    HwLog.e(HwWatchFaceApi.TAG, "onReceive isForeground Exception:" + HwLog.printException(e));
                }
            }
        }, intentFilter);
    }

    private void initNetworkClient() {
        try {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceApi$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    eu.a().d();
                }
            });
        } catch (Exception e) {
            HwLog.e(TAG, "initNetworkClient  Exception : " + HwLog.printException(e));
        }
    }

    public int getServerMode() {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getServerMode() mHwWatchFaceAdapter is null.");
            return 1;
        }
        if (isTestVersion()) {
            return 2;
        }
        return (!isOversea() || TextUtils.equals("CN", getCommonCountryCode())) ? 1 : 3;
    }

    public long getInitTime() {
        return this.initTime;
    }

    public String getToken() {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getToken() mHwWatchFaceAdapter is null.");
            return "";
        }
        String str = TAG;
        HwLog.d(str, "getToken() enter.");
        String c = n.a(this.mContext).c();
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        HwLog.e(str, "getToken() token is empty.");
        return "";
    }

    public String getAccessToken() {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getAccessToken() mHwWatchFaceAdapter is null.");
            return "";
        }
        String str = TAG;
        HwLog.d(str, "getAccessToken() enter.");
        String accessToken = this.mHwWatchFaceAdapter.getAccessToken();
        if (!TextUtils.isEmpty(accessToken)) {
            return accessToken;
        }
        HwLog.e(str, "getAccessToken() accessToken is empty.");
        return "";
    }

    public void setDisableWearInfoList(List<Map<String, String>> list) {
        this.mDisableWearInfoList = list;
    }

    public String getDeviceType() {
        String str;
        Object singleAccountInfo = getSingleAccountInfo("deviceType");
        if (singleAccountInfo instanceof String) {
            HwLog.i(TAG, "getDeviceType() deviceTypeObj instanceof String.");
            str = (String) singleAccountInfo;
        } else {
            HwLog.i(TAG, "getDeviceType() deviceTypeObj not instanceof String.");
            str = "";
        }
        HwLog.i(TAG, "getDeviceType() deviceType: " + str);
        return str;
    }

    public Integer getNonPersonalizedAdSwitch(String str) {
        Integer num;
        Object singleAccountInfo = getSingleAccountInfo(str);
        String str2 = TAG;
        HwLog.i(str2, "getNonPersonalizedAdSwitch:" + singleAccountInfo);
        if (singleAccountInfo instanceof Integer) {
            HwLog.i(str2, "getNonPersonalizedAdSwitch() nonPersonalizedAdObj instanceof Integer.");
            num = (Integer) singleAccountInfo;
        } else {
            num = r2;
        }
        r2 = num.intValue() == 0 ? num : 1;
        HwLog.i(str2, "getNonPersonalizedAdSwitch type:" + str + ",value:" + r2);
        return r2;
    }

    public String getDeviceId() {
        String str;
        Object singleAccountInfo = getSingleAccountInfo("deviceId");
        if (singleAccountInfo instanceof String) {
            HwLog.i(TAG, "getDeviceId() deviceIdObj instanceof String.");
            str = (String) singleAccountInfo;
        } else {
            HwLog.i(TAG, "getDeviceId() deviceIdObj not instanceof String.");
            str = "";
        }
        FlavorConfig.safeHwLog(TAG, "getDeviceId deviceId:" + str);
        return str;
    }

    private String getDeviceIdAsTheme() {
        if (CommonUtils.i()) {
            String generateDeviceIDWithSeparator = MobileInfoHelper.generateDeviceIDWithSeparator();
            String substring = SafeString.substring(generateDeviceIDWithSeparator, 0, generateDeviceIDWithSeparator.indexOf(":"));
            HwLog.i(TAG, "getDeviceIdAsTheme() isEmui&SDK>=26.");
            return substring;
        }
        String deviceId = getDeviceId();
        HwLog.i(TAG, "getDeviceIdAsTheme() !isEmui&SDK>=26.");
        return deviceId;
    }

    public String getDeviceTypeNew() {
        if (CommonUtils.i()) {
            String generateDeviceIDWithSeparator = MobileInfoHelper.generateDeviceIDWithSeparator();
            String substring = SafeString.substring(generateDeviceIDWithSeparator, generateDeviceIDWithSeparator.indexOf("=") + 1);
            HwLog.i(TAG, "getDeviceTypeNew() isEmui&SDK>=26.");
            return substring;
        }
        String deviceType = getDeviceType();
        HwLog.i(TAG, "getDeviceTypeNew() !isEmui&SDK>=26.");
        return deviceType;
    }

    public String getDeviceIdAsTheme(boolean z) {
        if (z) {
            String c = ao.c(dz.c("device_id_phone"), "savePw");
            if (!TextUtils.isEmpty(c)) {
                return c;
            }
            String deviceIdAsTheme = getDeviceIdAsTheme();
            dz.b("device_id_phone", ao.a(deviceIdAsTheme, "savePw"));
            return deviceIdAsTheme;
        }
        return getDeviceIdAsTheme();
    }

    public int getSiteId() {
        int i;
        Object singleAccountInfo = getSingleAccountInfo("siteId");
        if (singleAccountInfo instanceof Integer) {
            HwLog.i(TAG, "getSiteId() siteIdObj instanceof Integer.");
            i = ((Integer) singleAccountInfo).intValue();
        } else {
            HwLog.i(TAG, "getSiteId() siteIdObj not instanceof Integer.");
            i = -1;
        }
        FlavorConfig.safeHwLog(TAG, "siteId:" + i);
        return i;
    }

    public String getUserId() {
        Object singleAccountInfo = getSingleAccountInfo("uid");
        if (singleAccountInfo instanceof String) {
            HwLog.i(TAG, "getUsetId() userIdObj instanceof String.");
            return (String) singleAccountInfo;
        }
        HwLog.i(TAG, "getUsetId() userIdObj not instanceof String.");
        return "";
    }

    public String getAccountBrand() {
        Object singleAccountInfo = getSingleAccountInfo("appBrand");
        if (singleAccountInfo instanceof String) {
            HwLog.i(TAG, "getAccountBrand() accountBrandObj instanceof String.");
            String str = (String) singleAccountInfo;
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        } else {
            HwLog.i(TAG, "getAccountBrand() accountBrandObj not instanceof String.");
        }
        return "1";
    }

    public String getAccountToken() {
        String str = TAG;
        HwLog.i(str, "getAccountToken() enter");
        String accessToken = 2 == getAccessTokenType() ? getAccessToken() : "";
        HwLog.i(str, "getAccountToken---isNull:" + TextUtils.isEmpty(accessToken));
        return accessToken;
    }

    public void refreshAccessToken(final RefreshAccessTokenListener refreshAccessTokenListener) {
        if (refreshAccessTokenListener == null) {
            HwLog.e(TAG, "refreshAccessToken() refreshAccessTokenListener is null.");
            return;
        }
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "refreshAccessToken() mHwWatchFaceAdapter is null.");
            refreshAccessTokenListener.onLoginFailed("Adapter can not be null");
        } else {
            hwWatchFaceAdapter.refreshAccessToken(new ILoginCallback() { // from class: com.huawei.watchface.api.HwWatchFaceApi.2
                @Override // com.huawei.watchface.utils.callback.ILoginCallback
                public void onLoginSuccess(Object obj) {
                    if (!(obj instanceof String)) {
                        refreshAccessTokenListener.onLoginFailed("AccessToken should be string");
                        return;
                    }
                    String str = (String) obj;
                    if (TextUtils.isEmpty(str)) {
                        str = HwWatchFaceApi.this.mHwWatchFaceAdapter.getAccessToken();
                    }
                    refreshAccessTokenListener.onLoginSuccess(str);
                }

                @Override // com.huawei.watchface.utils.callback.ILoginCallback
                public void onLoginFailed(Object obj) {
                    refreshAccessTokenListener.onLoginFailed(obj);
                }
            });
        }
    }

    public String getCommonCountryCode() {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getCommonCountryCode() mHwWatchFaceAdapter is null.");
            return "";
        }
        if (TextUtils.isEmpty(this.countryCodeCache)) {
            this.countryCodeCache = this.mHwWatchFaceAdapter.getCommonCountryCode();
        }
        HwLog.i(TAG, "getCommonCountryCode() countryCodeCache: " + this.countryCodeCache);
        return this.countryCodeCache;
    }

    public void clearCountryCode() {
        if (TextUtils.isEmpty(this.countryCodeCache)) {
            return;
        }
        this.countryCodeCache = "";
    }

    public boolean isOversea() {
        boolean isKeyVersion = isKeyVersion("NoCloudVersion");
        HwLog.i(TAG, "isOversea() isOverSea: " + isKeyVersion);
        return isKeyVersion;
    }

    public boolean isTestVersion() {
        return isKeyVersion("Test");
    }

    private boolean isKeyVersion(String str) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "isKeyVersion() mHwWatchFaceAdapter is null.");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.mVersionInfo == null) {
            this.mVersionInfo = this.mHwWatchFaceAdapter.getHealthVersionType();
        }
        Map<String, Integer> map = this.mVersionInfo;
        if (map == null) {
            HwLog.e(TAG, "isKeyVersion() versionInfo is null.");
            return false;
        }
        Integer num = map.get(str);
        if (num != null) {
            return num.intValue() == 1;
        }
        HwLog.e(TAG, "isKeyVersion() version is null.");
        return false;
    }

    @Override // com.huawei.watchface.api.PluginBase
    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
        super.setAdapter(pluginBaseAdapter);
        if (pluginBaseAdapter instanceof HwWatchFaceAdapter) {
            this.initTime = System.currentTimeMillis();
            this.mHwWatchFaceAdapter = (HwWatchFaceAdapter) pluginBaseAdapter;
            getInstance(this.mContext).setDeviceInfo(null);
            getInstance(this.mContext).setAccountInfoMap(null);
            HwWatchFaceManager.getInstance(this.mContext).init();
            n.a(this.mContext).a(false);
            initOperationAdapter(this.mContext);
        }
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter != null) {
            hwWatchFaceAdapter.initFullSdkGrs();
            initHA();
            JsSafeUrlSystemParamManager.getInstance().d();
        }
    }

    @Override // com.huawei.watchface.api.PluginBase
    public HwWatchFaceAdapter getAdapter() {
        return this.mHwWatchFaceAdapter;
    }

    @Override // com.huawei.watchface.api.PluginBase
    public void finish() {
        super.finish();
    }

    private Object getSingleAccountInfo(String str) {
        String str2 = TAG;
        HwLog.i(str2, "getSingleAccountInfo key:" + str);
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(str2, "getSingleAccountInfo() mHwWatchFaceAdapter is null.");
            return "";
        }
        if (!ArrayUtils.a(this.mAccountInfoMap)) {
            Object obj = this.mAccountInfoMap.get(str);
            if (!TextUtils.equals("uid", str)) {
                return obj;
            }
            if ((obj instanceof String) && !TextUtils.isEmpty((String) obj)) {
                return obj;
            }
        }
        this.mAccountInfoMap = this.mHwWatchFaceAdapter.getHmsLiteAccountInfo(this.mContext);
        HwLog.i(str2, "getSingleAccountInfo() getHmsLiteAccountInfo.");
        Map<String, Object> map = this.mAccountInfoMap;
        if (map == null) {
            HwLog.e(str2, "getSingleAccountInfo() infoMap is null.");
            return "";
        }
        return map.get(str);
    }

    public boolean isLogin() {
        return -1 != getSiteId();
    }

    public boolean isLoginParamSuitable() {
        return (TextUtils.isEmpty(getUserId()) || TextUtils.equals("0", getUserId()) || TextUtils.isEmpty(getToken()) || TextUtils.isEmpty(getDeviceType())) ? false : true;
    }

    public boolean ifAllowLogin() {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "ifAllowLogin() mHwWatchFaceAdapter is null.");
            return false;
        }
        return hwWatchFaceAdapter.ifAllowLogin();
    }

    public boolean isBetaVersion() {
        return isKeyVersion("Beta");
    }

    public boolean isDebugVersion() {
        return isKeyVersion("Debug");
    }

    public boolean isGooglePlayOemDisable() {
        return isKeyVersion("GooglePlayOemDisable");
    }

    public boolean isRelease() {
        return isKeyVersion("Release");
    }

    public String getDeviceIdentify() {
        return getDeviceInfoByKey("device_Identify");
    }

    public String getSoftVersion() {
        if (Environment.sIsAarInTheme) {
            return HwWatchFaceBtManager.getInstance(this.mContext).getSoftVersion();
        }
        return getDeviceInfoByKey(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION);
    }

    public String getDeviceName() {
        if (Environment.sIsAarInTheme) {
            return HwWatchFaceBtManager.getInstance(this.mContext).getDeviceName();
        }
        return getDeviceInfoByKey(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
    }

    public String getIsSupportMyWatchface() {
        return getDeviceInfoByKey("isSupportMyWatch");
    }

    public String getConnectedEquipment() {
        if (Environment.sIsAarInTheme) {
            return HwWatchFaceBtManager.getInstance(this.mContext).getDeviceName();
        }
        return getDeviceNameOrLast(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
    }

    private String getDeviceNameOrLast(String str) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getDeviceInfoByKey() mHwWatchFaceAdapter is null.");
            return "";
        }
        Map<String, String> deviceInfo = getDeviceInfo();
        if (ArrayUtils.a(deviceInfo)) {
            List<Map<String, String>> disableWearInfo = this.mHwWatchFaceAdapter.getDisableWearInfo();
            if (ArrayUtils.isEmpty(disableWearInfo)) {
                return "";
            }
            Collections.sort(disableWearInfo, new Comparator() { // from class: com.huawei.watchface.api.HwWatchFaceApi$$ExternalSyntheticLambda2
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return HwWatchFaceApi.lambda$getDeviceNameOrLast$1((Map) obj, (Map) obj2);
                }
            });
            return disableWearInfo.get(0).get(str);
        }
        return deviceInfo.get(str);
    }

    static /* synthetic */ int lambda$getDeviceNameOrLast$1(Map map, Map map2) {
        try {
            return (int) (Long.parseLong((String) map2.get("last_connected_time")) - Long.parseLong((String) map.get("last_connected_time")));
        } catch (Exception unused) {
            HwLog.d(TAG, "getDeviceNameOrLast NumberFormatException");
            return 0;
        }
    }

    public String getDeviceModel() {
        if (Environment.sIsAarInTheme) {
            return HwWatchFaceBtManager.getInstance(this.mContext).getDeviceModel();
        }
        return getDeviceInfoByKey(PluginPayAdapter.KEY_DEVICE_INFO_MODEL);
    }

    private String getDeviceInfoByKey(String str) {
        String str2 = "";
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getDeviceInfoByKey() mHwWatchFaceAdapter is null.");
            return "";
        }
        Map<String, String> deviceInfo = getDeviceInfo();
        if (ArrayUtils.a(deviceInfo)) {
            str2 = getDisableWearInfoByKey(str);
            if (TextUtils.isEmpty(str2)) {
                String b = ao.b(dz.a(str), "storagePw");
                HwLog.e(TAG, "getDeviceInfoByKey() infoMap is null key: " + str);
                return b;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = deviceInfo.get(str);
        }
        if (!TextUtils.equals(str, "device_Identify")) {
            HwLog.i(TAG, "getDeviceInfoByKey() key: " + str + ", value: " + str2);
        } else {
            FlavorConfig.safeHwLog(TAG, "getDeviceInfoByKey() key: " + str + ", value: " + str2);
        }
        if (!TextUtils.isEmpty(str2)) {
            dz.a(str, ao.a(str2, "storagePw"));
        }
        return str2;
    }

    private String getDisableWearInfoByKey(String str) {
        String str2;
        String disableWearInfo = getDisableWearInfo();
        if (!TextUtils.isEmpty(disableWearInfo)) {
            List<Map> list = (List) GsonHelper.fromJson(disableWearInfo, new TypeToken<List<Map<String, String>>>() { // from class: com.huawei.watchface.api.HwWatchFaceApi.3
            }.getType());
            if (!ArrayUtils.isEmpty(list)) {
                for (Map map : list) {
                    if (map.containsKey(str)) {
                        str2 = (String) map.get(str);
                        break;
                    }
                }
            }
        }
        str2 = "";
        return TextUtils.isEmpty(str2) ? HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getVirtualWatchInfo(str) : str2;
    }

    public Map<String, String> getDeviceInfo() {
        if (!ArrayUtils.a(this.mDeviceInfo)) {
            return this.mDeviceInfo;
        }
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getDeviceInfo() mHwWatchFaceAdapter is null.");
            return null;
        }
        this.mDeviceInfo = hwWatchFaceAdapter.getDeviceInfo();
        HwLog.i(TAG, "mHwWatchFaceAdapter getDeviceInfo.");
        return this.mDeviceInfo;
    }

    public void setDeviceInfo(Map<String, String> map) {
        this.mDeviceInfo = map;
    }

    public void setAccountInfoMap(Map<String, Object> map) {
        this.mAccountInfoMap = map;
    }

    public String getDisableWearInfo() {
        synchronized (this) {
            String str = "";
            HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
            if (hwWatchFaceAdapter == null) {
                HwLog.e(TAG, "getDisableWearInfo() mHwWatchFaceAdapter is null.");
                return "";
            }
            List<Map<String, String>> list = this.mDisableWearInfoList;
            if (list != null) {
                if (list.isEmpty()) {
                    return "";
                }
                return GsonHelper.toJson(this.mDisableWearInfoList);
            }
            List<Map<String, String>> disableWearInfo = hwWatchFaceAdapter.getDisableWearInfo();
            this.mDisableWearInfoList = disableWearInfo;
            if (!ArrayUtils.isEmpty(disableWearInfo)) {
                str = GsonHelper.toJson(this.mDisableWearInfoList);
            } else {
                this.mDisableWearInfoList = new CopyOnWriteArrayList();
                HwLog.i(TAG, "getDisableWearInfo DisableWearInfo is null");
            }
            return str;
        }
    }

    public String getHeadUrl() {
        if (this.mHwWatchFaceAdapter != null) {
            return " ";
        }
        HwLog.e(TAG, "getHeadUrl() mHwWatchFaceAdapter is null.");
        return " ";
    }

    public String getNickName() {
        if (this.mHwWatchFaceAdapter != null) {
            return " ";
        }
        HwLog.e(TAG, "getNickName() mHwWatchFaceAdapter is null.");
        return " ";
    }

    public String requestSign() {
        if (this.mHwWatchFaceAdapter != null) {
            return " ";
        }
        HwLog.e(TAG, "requestSign() mHwWatchFaceAdapter is null.");
        return " ";
    }

    public void gotoDetailOrMarket(Activity activity, int i) {
        if (!CommonUtils.l(activity)) {
            HwLog.e(TAG, "gotoDetailOrMarket() context is null.");
        } else {
            judgeDataShareAuth(activity, i);
        }
    }

    private void goDetailOrMarket(Activity activity, int i) {
        String str;
        String str2 = TAG;
        HwLog.i(str2, "gotoDetailOrMarket() gotoWatchFace position：" + i);
        if (isBetaVersion() || isDebugVersion()) {
            HwLog.i(str2, "gotoDetailOrMarket() gotoWatchFace BetaVersion.");
            Intent intent = new Intent();
            intent.setClass(activity, WebViewActivity.class);
            String developerInfo = getDeveloperInfo();
            HwLog.i(str2, "gotoDetailOrMarket() developer: " + developerInfo);
            String a2 = b.a().a("watchFaceH5", getCommonCountryCode());
            if (!TextUtils.isEmpty(developerInfo) && developerInfo.equals("1")) {
                String customAppWatchFaceUrl = getCustomAppWatchFaceUrl();
                HwLog.i(str2, "gotoDetailOrMarket() appWatchFace:" + customAppWatchFaceUrl);
                if (customAppWatchFaceUrl.length() == 0) {
                    customAppWatchFaceUrl = DEFAULT_WATCHFACE_SITE;
                }
                String str3 = "sandbox/cch5/health/" + customAppWatchFaceUrl + "/index.html";
                if (i != -1) {
                    str = a2 + str3 + URL_SUFFIX + i;
                } else {
                    str = a2 + str3 + URL_INDEX_SUFFIX;
                }
            } else if (i != -1) {
                str = a2 + "sandbox/cch5/health/watchFace/index.html#/detail/native/" + i;
            } else {
                str = a2 + "sandbox/cch5/health/watchFace/index.html#/index";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str.contains("?") ? "&" : "?");
            sb.append(URL_FROM_SUFFIX);
            intent.putExtra("url", sb.toString());
            try {
                activity.startActivityForResult(intent, 102);
                return;
            } catch (Exception e) {
                HwLog.e(TAG, "gotoActivity " + HwLog.printException(e));
                return;
            }
        }
        new MyAsyncTask(activity, i).execute(new Void[0]);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void openPlugin(Activity activity, String str, String str2) {
        char c;
        HwLog.i(TAG, "openPlugin " + str);
        Intent intent = new Intent();
        intent.setFlags(268435456);
        str.hashCode();
        switch (str.hashCode()) {
            case -2035802222:
                if (str.equals("wearPlugin")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 7512706:
                if (str.equals("kaleidoscopePlugin")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 289574181:
                if (str.equals("galleryPlugin")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 949741454:
                if (str.equals("videoPlugin")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            intent.setClassName(this.mContext.getPackageName(), ComponentInfo.PluginWatchFace_A_5);
        } else if (c == 1) {
            intent.setClassName(this.mContext.getPackageName(), ComponentInfo.PluginWatchFace_A_0);
        } else if (c == 2) {
            intent.setClassName(this.mContext.getPackageName(), ComponentInfo.PluginWatchFace_A_4);
        } else if (c == 3) {
            intent.setClassName(this.mContext.getPackageName(), ComponentInfo.PluginWatchFace_A_1);
        }
        intent.putExtra("url", str2);
        this.mHwWatchFaceAdapter.launchActivity(activity, intent);
    }

    public String getWatchFaceUrl(boolean z) {
        HwLog.i(TAG, "getWatchFaceUrl() enter");
        return getUrl(getCommonCountryCode(), z ? URL_INDEX_SUFFIX : URL_DETAIL_SUFFIX);
    }

    public String getGalleryWatchFaceUrl() {
        HwLog.i(TAG, "getWatchFaceUrl() enter");
        return getUrl(getCommonCountryCode(), "");
    }

    public String getMagicWatchFaceDetailUrl(String str, String str2, String str3, String str4) {
        HwLog.i(TAG, "getMagicWatchFaceDetailUrl() enter");
        StringBuilder sb = new StringBuilder("#/dial-detail?hitopId=");
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(Constants.VERSION);
            sb.append(str2);
        }
        sb.append("&from=");
        sb.append(str3);
        sb.append(URL_SUFFIX_FROM_SUB);
        sb.append(str4);
        return getUrl(getCommonCountryCode(), sb.toString());
    }

    public String getVipPayUrl(String str, String str2) {
        HwLog.i(TAG, "getVipPayUrl() enter");
        return getUrl(getCommonCountryCode(), "#/member-pay?from=" + str + URL_SUFFIX_FROM_SUB + str2);
    }

    public String getVipCouponsUrl(String str, String str2) {
        HwLog.i(TAG, "getVipCouponsUrl() enter");
        return getUrl(getCommonCountryCode(), "#/member-coupon?from=" + str + URL_SUFFIX_FROM_SUB + str2);
    }

    private String getUrl(final String str, String str2) {
        String str3;
        if (isBetaVersion()) {
            String str4 = TAG;
            HwLog.i(str4, "getWatchFaceUrl() gotoWatchFace BetaVersion.");
            String developerInfo = getDeveloperInfo();
            HwLog.i(str4, "getWatchFaceUrl() developer: " + developerInfo);
            String a2 = b.a().a("watchFaceH5", str);
            if (!TextUtils.isEmpty(developerInfo) && developerInfo.equals("1")) {
                String customAppWatchFaceUrl = getCustomAppWatchFaceUrl();
                HwLog.i(str4, "getWatchFaceUrl() appWatchFace:" + customAppWatchFaceUrl);
                if (TextUtils.isEmpty(customAppWatchFaceUrl)) {
                    customAppWatchFaceUrl = DEFAULT_WATCHFACE_SITE;
                }
                return a2 + ("sandbox/cch5/health/" + customAppWatchFaceUrl + "/index.html") + str2;
            }
            return a2 + WATCH_FACE_BETA_URL + str2;
        }
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.huawei.watchface.api.HwWatchFaceApi$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String a3;
                a3 = b.a().a("watchFaceH5", str);
                return a3;
            }
        });
        WatchFaceHttpUtil.b.execute(futureTask);
        try {
            str3 = (String) futureTask.get();
        } catch (Exception e) {
            HwLog.e(TAG, "getBaseUrl Exception" + HwLog.printException(e));
            str3 = "";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = b.a().a("watchFaceH5", str);
        }
        if (getInstance(Environment.getApplicationContext()).isTestVersion()) {
            return str3 + str2;
        }
        return str3 + getWatchfaceSite() + str2;
    }

    public String getWatchfaceSite() {
        String f = JsSafeUrlSystemParamManager.getInstance().f();
        if (TextUtils.isEmpty(f)) {
            return URL_END_SUFFIX;
        }
        String replace = SafeString.replace(WATCH_FACE_BETA_URL, DEFAULT_WATCHFACE_SITE, f);
        HwLog.i(TAG, "getWatchfaceSite replace greyWatchface:" + f);
        return replace;
    }

    public String getWatchfaceSiteForAll() {
        if (isBetaVersion()) {
            String developerInfo = getDeveloperInfo();
            if (TextUtils.isEmpty(developerInfo) || !developerInfo.equals("1")) {
                return WATCH_FACE_BETA_URL;
            }
            String customAppWatchFaceUrl = getCustomAppWatchFaceUrl();
            if (TextUtils.isEmpty(customAppWatchFaceUrl)) {
                customAppWatchFaceUrl = DEFAULT_WATCHFACE_SITE;
            }
            return "sandbox/cch5/health/" + customAppWatchFaceUrl + "/index.html";
        }
        if (isTestVersion()) {
            return getWatchfaceTestSite(b.a().a("watchFaceH5", getCommonCountryCode()));
        }
        return getWatchfaceSite();
    }

    public String getWatchfaceTestSite(String str) {
        String developerInfo = getDeveloperInfo();
        String str2 = TAG;
        HwLog.i(str2, "getWatchfaceTestSite() developer: " + developerInfo);
        if (!TextUtils.isEmpty(developerInfo) && developerInfo.equals("1")) {
            String customAppWatchFaceUrl = getCustomAppWatchFaceUrl();
            HwLog.i(str2, "getWatchfaceTestSite() appWatchFace:" + customAppWatchFaceUrl);
            if (TextUtils.isEmpty(customAppWatchFaceUrl)) {
                return str;
            }
            return str.replaceFirst("\\/sandbox\\/cch5\\/health\\/[^\\/]+\\/", com.huawei.operation.utils.Constants.H5_URL_BASE_PATH_BETA + customAppWatchFaceUrl + "/");
        }
        String f = JsSafeUrlSystemParamManager.getInstance().f();
        if (!TextUtils.isEmpty(f)) {
            str = str.replaceFirst("\\/sandbox\\/cch5\\/health\\/[^\\/]+\\/", com.huawei.operation.utils.Constants.H5_URL_BASE_PATH_BETA + f + "/");
        }
        HwLog.i(str2, "getWatchfaceTestSite watchfaceSite:" + str);
        return str;
    }

    public String getWatchfaceReleaseSite(String str) {
        if (str == null) {
            return null;
        }
        String f = JsSafeUrlSystemParamManager.getInstance().f();
        if (TextUtils.isEmpty(f)) {
            String str2 = TAG;
            HwLog.i(str2, "getWatchfaceReleaseSite greyWatchface is null");
            if (!getInstance(this.mContext).isRelease()) {
                return str;
            }
            String watchFaceLoadUrl = getWatchFaceLoadUrl(str, com.huawei.operation.utils.Constants.H5_URL_BASE_PATH_RELEASE, DEFAULT_WATCHFACE_SITE);
            HwLog.i(str2, "getWatchfaceReleaseSite isRelease watchfaceSite");
            FlavorConfig.safeHwLog(str2, "getWatchfaceReleaseSite isRelease watchfaceSite:" + watchFaceLoadUrl);
            return watchFaceLoadUrl;
        }
        String watchFaceLoadUrl2 = getWatchFaceLoadUrl(str, com.huawei.operation.utils.Constants.H5_URL_BASE_PATH_BETA, f);
        String str3 = TAG;
        HwLog.i(str3, "getWatchfaceReleaseSite watchfaceSite end");
        FlavorConfig.safeHwLog(str3, "getWatchfaceReleaseSite watchfaceSite:" + watchFaceLoadUrl2);
        return watchFaceLoadUrl2;
    }

    private String getWatchFaceLoadUrl(String str, String str2, String str3) {
        Matcher matcher = Pattern.compile("\\/sandbox\\/cch5\\/health\\/[^\\/]+\\/").matcher(str);
        if (matcher != null && matcher.find()) {
            return str.replaceFirst("\\/sandbox\\/cch5\\/health\\/[^\\/]+\\/", str2 + str3 + "/");
        }
        return str.replaceFirst("\\/cch5\\/health\\/[^\\/]+\\/", str2 + str3 + "/");
    }

    private void initOperationAdapter(Context context) {
        String str = TAG;
        HwLog.i(str, "initOperationAdapter() enter.");
        if (((PluginOperationAdapter) ba.a(context).getAdapter()) == null) {
            HwLog.i(str, "initOperationAdapter() adapter is null.");
            ba a2 = ba.a(context);
            a2.setAdapter(au.a(context));
            a2.init(context);
        }
    }

    public void reportInterceptedCrash(Throwable th, Boolean bool) {
        fh fhVar = this.crashReport;
        if (fhVar != null) {
            fhVar.a(th, bool);
        }
    }

    public void jumpOtherUrlActivity(Context context, String str) {
        try {
            String optString = new JSONObject(str).optString("url");
            if (TextUtils.isEmpty(optString) || !UrlUtil.isValidUrl(optString)) {
                return;
            }
            WebViewUtils.a(context, optString);
        } catch (JSONException e) {
            HwLog.e(TAG, "jumpOtherUrlActivity error =" + HwLog.printException((Exception) e));
        }
    }

    public int getDeviceConnectState() {
        int i;
        if (ArrayUtils.a(getDeviceInfo())) {
            i = !TextUtils.isEmpty(getDisableWearInfo()) ? 2 : 0;
        } else {
            i = 1;
        }
        HwLog.i(TAG, "getDeviceConnectState connectState==" + i);
        return i;
    }

    public int getDeviceConnectStateForAnalytics() {
        Integer num = this.connectStatus;
        if (num != null) {
            return num.intValue();
        }
        Integer valueOf = Integer.valueOf(getDeviceConnectState());
        this.connectStatus = valueOf;
        return valueOf.intValue();
    }

    public void setConnectStatus(Integer num) {
        this.connectStatus = num;
    }

    static class MyAsyncTask extends AsyncTask<Void, Void, String> {
        private WeakReference<Activity> mObj;
        private int position;

        MyAsyncTask(Activity activity, int i) {
            this.position = i;
            this.mObj = new WeakReference<>(activity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            if (this.mObj.get() == null) {
                HwLog.i(HwWatchFaceApi.TAG, "doInBackground() activity is null.");
                return "";
            }
            return b.a().a("watchFaceH5", HwWatchFaceApi.getInstance(this.mObj.get()).getCommonCountryCode());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            String str2;
            super.onPostExecute((MyAsyncTask) str);
            WeakReference<Activity> weakReference = this.mObj;
            if (weakReference == null || weakReference.get() == null) {
                HwLog.e(HwWatchFaceApi.TAG, "onPostExecute() activity is null.");
                return;
            }
            if (TextUtils.isEmpty(str)) {
                HwLog.e(HwWatchFaceApi.TAG, "onPostExecute() httpUrl is empty.");
                return;
            }
            Intent intent = new Intent();
            intent.setClass(this.mObj.get(), WebViewActivity.class);
            if (this.position != -1) {
                if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isTestVersion()) {
                    str2 = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getWatchfaceTestSite(str) + HwWatchFaceApi.URL_SUFFIX + this.position;
                } else {
                    str2 = str + HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getWatchfaceSite() + HwWatchFaceApi.URL_SUFFIX + this.position;
                }
            } else if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isTestVersion()) {
                str2 = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getWatchfaceTestSite(str) + HwWatchFaceApi.URL_INDEX_SUFFIX;
            } else {
                str2 = str + HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getWatchfaceSite() + HwWatchFaceApi.URL_INDEX_SUFFIX;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(str2.contains("?") ? "&" : "?");
            sb.append(HwWatchFaceApi.URL_FROM_SUFFIX);
            intent.putExtra("url", sb.toString());
            Activity activity = this.mObj.get();
            if (activity != null) {
                try {
                    activity.startActivityForResult(intent, 102);
                } catch (Exception e) {
                    HwLog.e(HwWatchFaceApi.TAG, "gotoActivity " + HwLog.printException(e));
                }
            }
        }
    }

    private Object getHealthSettingInfo(String str) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getHealthSettingInfo() mHwWatchFaceAdapter is null.");
            return null;
        }
        Map<String, Object> healthSettingInfo = hwWatchFaceAdapter.getHealthSettingInfo();
        if (healthSettingInfo == null) {
            HwLog.e(TAG, "getHealthSettingInfo() infoMap is null.");
            return null;
        }
        return healthSettingInfo.get(str);
    }

    private String getDeveloperInfo() {
        Object healthSettingInfo = getHealthSettingInfo("developer");
        if (healthSettingInfo != null) {
            return healthSettingInfo instanceof String ? (String) healthSettingInfo : "";
        }
        HwLog.e(TAG, "getDeveloperInfo() developerObJ is null.");
        return "";
    }

    private String getCustomAppWatchFaceUrl() {
        Object healthSettingInfo = getHealthSettingInfo("appWatchFace");
        if (healthSettingInfo != null) {
            return healthSettingInfo instanceof String ? (String) healthSettingInfo : "";
        }
        HwLog.e(TAG, "getCustomAppWatchFaceUrl() developerObJ is null.");
        return "";
    }

    public boolean isHmsLiteEnable() {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "isHmsLiteEnable() mHwWatchFaceAdapter is null.");
            return false;
        }
        return hwWatchFaceAdapter.isHmsLiteEnable(this.mContext);
    }

    public void loginByHealthHmsLite(Context context, IBaseResponseCallback iBaseResponseCallback) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "loginByHealthHmsLite() mHwWatchFaceAdapter is null.");
        } else {
            hwWatchFaceAdapter.loginByHealthHmsLite(context, iBaseResponseCallback);
        }
    }

    public void loginByHealthHms(Context context, ILoginCallback iLoginCallback) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "loginByHealthHms() mHwWatchFaceAdapter is null.");
        } else {
            hwWatchFaceAdapter.loginByHealthHms(context, iLoginCallback);
        }
    }

    private void judgeDataShareAuth(final Activity activity, final int i) {
        if (Environment.sIsAarInTheme) {
            HwWatchFaceBtManager.getInstance(activity).judgeDataShareAuth(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceApi$$ExternalSyntheticLambda1
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public final void onResponse(int i2, Object obj) {
                    HwWatchFaceApi.this.m875x85aed619(activity, i, i2, obj);
                }
            });
        } else {
            goDetailOrMarket(activity, i);
        }
    }

    /* renamed from: lambda$judgeDataShareAuth$3$com-huawei-watchface-api-HwWatchFaceApi, reason: not valid java name */
    /* synthetic */ void m875x85aed619(Activity activity, int i, int i2, Object obj) {
        HwLog.i(TAG, "judgeDataShareAuth() err_code: " + i2);
        if (101 == i2) {
            JsSafeUrlSystemParamManager.getInstance().b();
            goDetailOrMarket(activity, i);
        } else {
            if (2 == i2) {
                return;
            }
            requestAuthorization();
        }
    }

    private void requestAuthorization() {
        HwLog.i(TAG, "requestAuthorization() enter.");
        h.a(this.mContext, new IAuthorizationListener() { // from class: com.huawei.watchface.api.HwWatchFaceApi$$ExternalSyntheticLambda5
            @Override // com.huawei.hihealthkit.auth.IAuthorizationListener
            public final void onResult(int i, Object obj) {
                HwLog.i(HwWatchFaceApi.TAG, "requestAuthorization() onResult i: " + i);
            }
        });
    }

    public void printLog(String str, String str2, int i, String str3) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            dy.a(str, str2, i, str3);
        } else if (this.releaseLogSwitches) {
            hwWatchFaceAdapter.printReleaseLog(str, str2, i, str3);
        } else {
            hwWatchFaceAdapter.printLog(str, str2, i, str3);
        }
    }

    public void printBetaLog(String str, String str2, int i, String str3) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            dy.a(str, str2, i, str3);
        } else {
            hwWatchFaceAdapter.printLog(str, str2, i, str3);
        }
    }

    public void sendBluetoothCommand(int i, int i2, ByteBuffer byteBuffer) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "sendBluetoothCommand() mHwWatchFaceAdapter is null.");
        } else {
            hwWatchFaceAdapter.sendBluetoothCommand(i, i2, byteBuffer);
        }
    }

    public void registerHealthResponseListener(WatchFaceHealthResponseListener watchFaceHealthResponseListener) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "registerHealthResponseListener() mHwWatchFaceAdapter is null.");
        } else {
            HwLog.i(TAG, "registerHealthResponseListener() enter.");
            this.mHwWatchFaceAdapter.registerHealthResponseListener(watchFaceHealthResponseListener);
        }
    }

    public void unRegisterHealthResponseListener() {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "unRegisterHealthResponseListener() mHwWatchFaceAdapter is null.");
        } else {
            HwLog.i(TAG, "unRegisterHealthResponseListener() enter.");
            this.mHwWatchFaceAdapter.unRegisterHealthResponseListener();
        }
    }

    public void registerPhotoAndVideoCallback(IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, List<Integer> list, TransferFileReqType transferFileReqType) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "registerPhotoAndVideoCallback() mHwWatchFaceAdapter is null.");
            return;
        }
        TransFileInfo transFileInfo = new TransFileInfo();
        transFileInfo.setRequestType(transferFileReqType);
        transFileInfo.setAttentionTypes(list);
        this.mHwWatchFaceAdapter.commonTransferFile(TransferBusiType.FIVE_FORTY, transFileInfo, iAppTransferFileResultAIDLCallback);
    }

    public void commonTransferFile(String str, String str2, int i, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "commonTransferFile() mHwWatchFaceAdapter is null.");
            return;
        }
        String str3 = TAG;
        FlavorConfig.safeHwLog(str3, "commonTransferFile filePath: " + str);
        FlavorConfig.safeHwLog(str3, "commonTransferFile fileName: " + str2);
        TransFileInfo transFileInfo = new TransFileInfo();
        transFileInfo.setFilePath(str);
        transFileInfo.setFileName(str2);
        transFileInfo.setFileType(i);
        transFileInfo.setRequestType(TransferFileReqType.APP_DELIVERY);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        transFileInfo.setAttentionTypes(arrayList);
        if (di.a().c(str2)) {
            di.a().b(str2);
        }
        cw a2 = cx.a().a(str2, FlavorConfig.SERVICE_WATCH_FACE);
        if (a2 == null || a2.b() == null) {
            HwLog.e(str3, "FileContent is null");
            iAppTransferFileResultAIDLCallback.onUpgradeFailed(20000, "");
            return;
        }
        di.a().a(a2.b(), str2);
        di.a a3 = di.a().a(str2);
        if (a3.b() != null) {
            this.mHwWatchFaceAdapter.commonTransferFile(TransferBusiType.FIVE_FORTY, transFileInfo, a3.b(), a3.a(), iAppTransferFileResultAIDLCallback);
        } else {
            FileHelper.a(a2.b(), new File(str));
            this.mHwWatchFaceAdapter.commonTransferFile(TransferBusiType.FIVE_FORTY, transFileInfo, iAppTransferFileResultAIDLCallback);
        }
    }

    public void startRequestFile(String str, int i, boolean z, IPhotoFileCallback iPhotoFileCallback) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "startRequestFile() mHwWatchFaceAdapter is null.");
        } else {
            hwWatchFaceAdapter.startRequestFile(str, i, z, iPhotoFileCallback);
        }
    }

    public void commonStopTransfer(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "commonStopTransfer() mHwWatchFaceAdapter is null.");
        } else {
            hwWatchFaceAdapter.commonStopTransfer(str, i, iBaseResponseCallback);
        }
    }

    public void destroy() {
        HwWatchFaceManager.getInstance(this.mContext).destroy();
        o.a(this.mContext).a();
        t.a().b();
        n.a(this.mContext).j();
    }

    public boolean isSupportedH5TitleBar() {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "isSupportedH5TitleBar() mHwWatchFaceAdapter is null.");
            return false;
        }
        if (hwWatchFaceAdapter instanceof HwWatchFaceExtendAdapter) {
            return ((HwWatchFaceExtendAdapter) hwWatchFaceAdapter).isSupportedH5TitleBar();
        }
        return true;
    }

    public Map<String, String> getWatchFaceUrlMap(String str) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put(DEFAULT_WATCHFACE_SITE, WatchFaceHttpUtil.d(str));
            hashMap.put("watchFaceH5", WatchFaceHttpUtil.c(str));
        } catch (Exception e) {
            HwLog.e(TAG, "getWatchFaceUrlMap Exception:" + HwLog.printException(e));
        }
        return hashMap;
    }

    private void initHA() {
        try {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceApi$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    HwWatchFaceApi.this.m874lambda$initHA$5$comhuaweiwatchfaceapiHwWatchFaceApi();
                }
            });
        } catch (Exception e) {
            HwLog.e(TAG, "initHiAnalytics  Exception : " + HwLog.printException(e));
        }
    }

    /* renamed from: lambda$initHA$5$com-huawei-watchface-api-HwWatchFaceApi, reason: not valid java name */
    /* synthetic */ void m874lambda$initHA$5$comhuaweiwatchfaceapiHwWatchFaceApi() {
        eo.a().a(this.mContext);
    }

    private void initCrashConfig() {
        this.crashReport = new fh();
    }

    public void updateInstallAppState(int i) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "updateInstallAppState() mHwWatchFaceAdapter is null.");
            return;
        }
        HwLog.i(TAG, "updateInstallAppState() state:" + i);
    }

    public void installApp(String str) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "installApp() mHwWatchFaceAdapter is null.");
        } else if (TextUtils.isEmpty(str)) {
            HwLog.e(TAG, "installApp() packageName is null.");
        } else {
            this.mHwWatchFaceAdapter.installApp(str);
        }
    }

    public boolean getIsTaskExecuting() {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "getIsTaskExecuting() mHwWatchFaceAdapter is null.");
            return false;
        }
        return hwWatchFaceAdapter.getIsTaskExecuting();
    }

    public void createBitPngFile(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            HwLog.e(TAG, "createBitPngFile() binFilePath is null.");
        } else if (TextUtils.isEmpty(str2)) {
            HwLog.e(TAG, "createBitPngFile() pngFilePath is null.");
        } else {
            WatchFaceBitmapUtil.getInstance().a(str, str2);
        }
    }

    public void createBinFile(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            HwLog.e(TAG, "createBinFile() pngFilePath is null.");
        } else if (TextUtils.isEmpty(str2)) {
            HwLog.e(TAG, "createBinFile() binFilePath is null.");
        } else {
            WatchFaceBitmapUtil.getInstance().a(str, str2, i);
        }
    }

    public boolean createShortcuts() {
        String str = TAG;
        HwLog.e(str, "CreateShortcuts() int .");
        int a2 = PermissionUtil.a(this.mContext);
        if (a2 == -1) {
            HwLog.e(str, "CreateShortcuts() PERMISSION_DENIED.");
            PermissionUtil.f(this.mContext);
            return false;
        }
        if (a2 == 0) {
            HwLog.e(str, "CreateShortcuts() PERMISSION_GRANTED.");
            return createPinnedShortcut("watchface_supermarket", DensityUtil.getStringById(R$string.dial_market_new), IconCompat.createWithResource(this.mContext, R$drawable.watchface_ic_shortcut), CommonConstant.ACTION.HWID_SCHEME_URL);
        }
        HwLog.e(str, "CreateShortcuts() PERMISSION_UNKNOWN");
        return createPinnedShortcut("watchface_supermarket", DensityUtil.getStringById(R$string.dial_market_new), IconCompat.createWithResource(this.mContext, R$drawable.watchface_ic_shortcut), CommonConstant.ACTION.HWID_SCHEME_URL);
    }

    public boolean createPinnedShortcut(String str, String str2, IconCompat iconCompat, String str3) {
        PendingIntent pendingIntent;
        String str4 = TAG;
        HwLog.i(str4, "createPinnedShortcut() enter");
        if (!ShortcutManagerCompat.isRequestPinShortcutSupported(this.mContext)) {
            HwLog.e(str4, "createPinnedShortcut not support");
            return false;
        }
        HwLog.i(str4, "createPinnedShortcut() int Build.VERSION");
        ShortcutManager shortcutManager = (ShortcutManager) this.mContext.getSystemService(ShortcutManager.class);
        Intent intent = new Intent();
        String str5 = getInstance(this.mContext).getWatchFaceUrl(true) + URL_FROM_SHORTCUTSE;
        intent.setAction(str3);
        intent.setClassName(this.mContext.getPackageName(), "com.huawei.health.browseraction.HwHealthBrowserActionActivity");
        intent.setData(Uri.parse("huaweihealth://huaweihealth.app/openwith?type=aar&address=" + str5));
        intent.addFlags(268435456);
        intent.addFlags(AppRouterExtras.COLDSTART);
        ShortcutInfoCompat build = new ShortcutInfoCompat.Builder(this.mContext, str).setIcon(iconCompat).setShortLabel(str2).setIntent(intent).build();
        try {
            pendingIntent = PendingIntent.getService(this.mContext, 0, new Intent(this.mContext, (Class<?>) ShortCutService.class), CommonUtils.G() ? 167772160 : AMapEngineUtils.HALF_MAX_P20_WIDTH);
        } catch (Exception unused) {
            HwLog.e(TAG, "get successCallback error");
            pendingIntent = null;
        }
        if (pendingIntent == null) {
            HwLog.e(TAG, "successCallback is null");
            return false;
        }
        String str6 = TAG;
        HwLog.i(str6, "createPinnedShortcut foreground sdk: " + Build.VERSION.SDK_INT + "::" + isExistShortCut(str));
        if (!isExistShortCut(str)) {
            boolean s = CommonUtils.s();
            HwLog.i(str6, "createPinnedShortcut foreground : " + s);
            if (s) {
                boolean requestPinShortcut = ShortcutManagerCompat.requestPinShortcut(this.mContext, build, pendingIntent.getIntentSender());
                HwLog.i(str6, "createPinnedShortcut requestPinShortcut : " + requestPinShortcut);
                return requestPinShortcut;
            }
        } else {
            if (shortcutManager == null) {
                return false;
            }
            HwLog.i(str6, "createPinnedShortcut foreground sdk: null ");
            shortcutManager.enableShortcuts(Collections.singletonList(str));
            shortcutManager.updateShortcuts(Collections.singletonList(build.toShortcutInfo()));
        }
        return false;
    }

    public boolean isExistShortCut(String str) {
        List<ShortcutInfo> pinnedShortcuts = ((ShortcutManager) this.mContext.getSystemService(ShortcutManager.class)).getPinnedShortcuts();
        if (pinnedShortcuts == null) {
            return false;
        }
        for (ShortcutInfo shortcutInfo : pinnedShortcuts) {
            if (shortcutInfo != null && !TextUtils.isEmpty(shortcutInfo.getId()) && shortcutInfo.getId().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void shareLink(String str) {
        HwWatchFaceAdapter hwWatchFaceAdapter = this.mHwWatchFaceAdapter;
        if (hwWatchFaceAdapter == null) {
            HwLog.e(TAG, "shareLink() mHwWatchFaceAdapter is null.");
        } else {
            hwWatchFaceAdapter.shareLink(str);
        }
    }

    public void showHealthAppSettingGuide(Context context, String str) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "showHealthAppSettingGuide() mHwWatchFaceAdapter is null.");
            return;
        }
        HwLog.i(TAG, "showHealthAppSettingGuide permission:" + str);
        this.mHwWatchFaceAdapter.showHealthAppSettingGuide(context, str, null, null);
    }

    public void showHealthAppSettingGuide(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (this.mHwWatchFaceAdapter == null) {
            HwLog.e(TAG, "showHealthAppSettingGuide() mHwWatchFaceAdapter is null.");
            return;
        }
        HwLog.i(TAG, "showHealthAppSettingGuide permission:" + str);
        this.mHwWatchFaceAdapter.showHealthAppSettingGuide(context, str, onClickListener, onClickListener2);
    }

    /* loaded from: classes9.dex */
    public static class ShortCutService extends SafeService {
        @Override // android.app.Service
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override // com.huawei.secure.android.common.activity.SafeService, android.app.Service
        public int onStartCommand(Intent intent, int i, int i2) {
            super.onStartCommand(intent, i, i2);
            HwLog.i(HwWatchFaceApi.TAG, "ShortCutService onStartCommand");
            if (Environment.getApplicationContext() == null) {
                HwLog.i(HwWatchFaceApi.TAG, "Environment.getApplicationContext is null");
                stopSelf(i2);
                return 2;
            }
            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).notifyGetShortCutIsCreate();
            stopSelf(i2);
            return 2;
        }
    }
}
