package com.huawei.operation.operation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.marketing.datatype.VipPageSourceArea;
import com.huawei.hihealthservice.hmsauth.HmsAuthApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.TrustListCheckerImpl;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.tencent.open.SocialOperation;
import defpackage.dqj;
import defpackage.gpo;
import defpackage.mml;
import defpackage.nrv;
import defpackage.nsa;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.Map;

/* loaded from: classes.dex */
public class PluginOperation extends mml {
    private static final String ACTION_OPEN_DISCOVER = "openDiscover";
    private static final String FROM_WHERE = "from";
    private static final String H5_PRO = "h5pro=true";
    private static final String JUMP_VIP_TAB_WITH_QRCODE = "jumpVipTabWithQrCode";
    private static final Object LOCK = new Object();
    private static final String TAG = "PluginOperation";
    private static volatile PluginOperation sInstance;
    private Context mContext;

    private PluginOperation(Context context) {
        this.mContext = context;
    }

    public static PluginOperation getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new PluginOperation(BaseApplication.getContext());
                }
            }
        }
        return sInstance;
    }

    public void startOperationWebPage(String str) {
        if (gpo.b() && vipABTestRelocation(str)) {
            return;
        }
        startOperationWebPage(str, null);
    }

    private boolean vipABTestRelocation(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Map<String, String> c = nsa.c(str);
        if (!str.contains("vip")) {
            return false;
        }
        String str2 = c.get(BleConstants.KEY_PATH);
        LogUtil.a("PluginOperation", "jump to h5 vip page");
        if (!TextUtils.isEmpty(str2) && !str2.equals("VipSubscribe") && !str2.equals("MemberCenter")) {
            LogUtil.a("PluginOperation", "not jump to vip center page");
            return false;
        }
        if (str.contains("customContent")) {
            String str3 = c.get("customContent");
            LogUtil.a("PluginOperation", "jump to h5 vip page with qrcode, customContent is", str3);
            VipPageSourceArea vipPageSourceArea = (VipPageSourceArea) nrv.b(str3, VipPageSourceArea.class);
            dqj.d(vipPageSourceArea.getProvince());
            dqj.e(vipPageSourceArea.getCity());
            AppRouter.b("/home/main").e(ACTION_OPEN_DISCOVER, true).e(JUMP_VIP_TAB_WITH_QRCODE, true).c(this.mContext);
        } else {
            LogUtil.a("PluginOperation", "jump to h5 vip page, redirect to relay vip page");
            AppRouter.b("/OperationBundle/MemberRelayActivity").e("memberTabRelayUri", str).c(this.mContext);
        }
        return true;
    }

    public void startOperationWebPage(String str, String str2) {
        startOperationWebPage(BaseApplication.getActivity(), str, str2);
    }

    public void startOperationWebPage(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PluginOperation", "startOperationWebPage() url is empty.");
            return;
        }
        if (!checkUrlParam(str)) {
            LogUtil.a("PluginOperation", "startOperationWebPage: error param.");
            return;
        }
        if (str.contains(H5_PRO)) {
            H5proUtil.jumpFromDeeplink(context, str);
            return;
        }
        if (TrustListCheckerImpl.containsXss(str)) {
            LogUtil.h("PluginOperation", "startOperationWebPage: url cannot contain Xss");
            return;
        }
        Intent intent = new Intent(this.mContext, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        intent.setFlags(268435456);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("from", str2);
        }
        this.mContext.startActivity(intent);
    }

    private static boolean checkUrlParam(String str) {
        LogUtil.a("PluginOperation", "enter checkUrlParam");
        String packageNameFromUrl = H5proUtil.getPackageNameFromUrl(str);
        packageNameFromUrl.hashCode();
        if (!packageNameFromUrl.equals("com.huawei.healthkit.auto-login-h5")) {
            return true;
        }
        Uri parse = Uri.parse(Constants.OPEN_HEALTH_SPORT_PREFIX + str);
        String queryParameter = parse.getQueryParameter("client_id");
        String queryParameter2 = parse.getQueryParameter(SocialOperation.GAME_SIGNATURE);
        String queryParameter3 = parse.getQueryParameter("thirdUrlScheme");
        boolean isValidRedirectUrl = ((HmsAuthApi) Services.c("HealthKit", HmsAuthApi.class)).isValidRedirectUrl(queryParameter, queryParameter2, queryParameter3);
        if (isValidRedirectUrl) {
            SharedPreferenceManager.c("HealthKit", "thirdUrlSchemeAppendState", Uri.parse(queryParameter3).buildUpon().appendQueryParameter("state", parse.getQueryParameter("state")).build().toString());
        }
        return isValidRedirectUrl;
    }

    public void startOperationWebPageForResult(Activity activity, String str) {
        if (TrustListCheckerImpl.containsXss(str)) {
            LogUtil.h("PluginOperation", "startOperationWebPageForResult: url cannot contain Xss");
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        activity.startActivityForResult(intent, 10);
    }

    public void startOperationWebPageForResult(Activity activity, String str, String str2) {
        if (TrustListCheckerImpl.containsXss(str)) {
            LogUtil.h("PluginOperation", "startOperationWebPageForResult: url cannot contain Xss");
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        intent.putExtra("title", str2);
        activity.startActivityForResult(intent, 10);
    }

    public void backToActivityListPage() {
        WebViewActivity.backToActivityListPage();
    }

    public void setActivity(Activity activity) {
        WebViewActivity.setActivity(activity);
    }

    public Activity getActivity() {
        return WebViewActivity.getActivity();
    }

    public void setActivityFlag(boolean z) {
        WebViewActivity.setActiveFlag(z);
    }

    public boolean getActivityFlag() {
        return WebViewActivity.getActiveFlag();
    }

    public void setIsDesigner(boolean z) {
        WebViewActivity.setIsDesigner(z);
    }

    public boolean getIsDesigner() {
        return WebViewActivity.getIsDesigner();
    }

    public boolean getIsShowBtnAdd() {
        return WebViewActivity.getIsShowBtnAdd();
    }

    public void initAdapter(Context context, PluginBaseAdapter pluginBaseAdapter) {
        PluginOperation pluginOperation = getInstance(context);
        if (pluginOperation.getAdapter() instanceof PluginOperationAdapter) {
            return;
        }
        pluginOperation.setAdapter(pluginBaseAdapter);
        pluginOperation.init(context);
    }
}
