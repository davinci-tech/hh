package com.huawei.operation;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.baseapi.pluginoperation.PluginOperationApi;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.cloud.OperationCloudUtil;
import com.huawei.operation.h5pro.H5MiniProgramSecurityHelper;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import com.huawei.operation.h5pro.jsmodules.BreathTrainJsModule;
import com.huawei.operation.h5pro.jsmodules.Device;
import com.huawei.operation.h5pro.jsmodules.EcgJsModule;
import com.huawei.operation.h5pro.jsmodules.InnerApi;
import com.huawei.operation.h5pro.jsmodules.JsInteractionCompact;
import com.huawei.operation.h5pro.jsmodules.MenstrualModule;
import com.huawei.operation.h5pro.jsmodules.NotificationUtil;
import com.huawei.operation.h5pro.jsmodules.PpgJsModule;
import com.huawei.operation.h5pro.jsmodules.Social;
import com.huawei.operation.h5pro.jsmodules.Sport;
import com.huawei.operation.h5pro.jsmodules.WeightJsApi;
import com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlApi;
import com.huawei.operation.h5pro.jsmodules.healthengine.HealthyLivingEntry;
import com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry;
import com.huawei.operation.h5pro.jsmodules.mediaCenter.MediaCenterH5Api;
import com.huawei.operation.h5pro.jsmodules.sharecaas.ShareToCaasApi;
import com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi;
import com.huawei.operation.h5pro.jsmodules.wearengine.WearEngineEntry;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.mmn;
import defpackage.mpf;
import defpackage.mph;
import defpackage.mxv;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.KeyValDbManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class PluginOperationImpl implements PluginOperationApi {
    private static final String ECG_AC_NAME = "com.huawei.health.h5.ecg";
    private static final String ECG_NAME = "com.huawei.health.h5.ecgce";
    private static final Map<String, Class<? extends JsModuleBase>> JS_MODULE_MAP = generateJsModuleMap();
    private static final String TAG = "PluginOperation_PluginOperationImpl";

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void initAdapter(Context context, PluginBaseAdapter pluginBaseAdapter) {
        PluginOperation.getInstance(context).initAdapter(context, pluginBaseAdapter);
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public PluginBaseAdapter getAdapter(Context context) {
        LogUtil.a(TAG, "getAdapter");
        if (context != null) {
            return PluginOperation.getInstance(context).getAdapter();
        }
        LogUtil.a(TAG, "Context is null");
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void launchH5Compact(Context context, String str, boolean z) {
        LogUtil.a(TAG, "launchH5Compact start");
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "launchH5Compact param empty");
            return;
        }
        H5proUtil.initH5pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("JsInteraction", JsInteractionCompact.class).build();
        if (z) {
            H5ProClient.startH5MiniProgram(context, str, build);
        } else {
            H5ProClient.startH5LightApp(context, str, build);
        }
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void initH5Pro() {
        H5proUtil.initH5pro();
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void initThirdDeviceH5Pro(String str, ContentValues contentValues, String str2) {
        new BleJsInteractionCompact().startH5Pro(BaseApplication.e(), str, contentValues, str2);
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public Class<? extends JsModuleBase> getJsInteraction() {
        return JsInteractionCompact.class;
    }

    private static Map<String, Class<? extends JsModuleBase>> generateJsModuleMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("JsInteraction", JsInteractionCompact.class);
        hashMap.put("innerapi", InnerApi.class);
        hashMap.put(RemoteMessageConst.NOTIFICATION, NotificationUtil.class);
        hashMap.put("notificationAudioControl", AudioControlApi.class);
        hashMap.put(NotificationCompat.CATEGORY_SOCIAL, Social.class);
        hashMap.put("sport", Sport.class);
        hashMap.put("ecgJsModule", EcgJsModule.class);
        hashMap.put("breathtrain", BreathTrainJsModule.class);
        hashMap.put("shareToCaasApi", ShareToCaasApi.class);
        hashMap.put("tradeApi", JsTradeApi.class);
        hashMap.put("ppgJsModule", PpgJsModule.class);
        hashMap.put("device", Device.class);
        hashMap.put("wearengine", WearEngineEntry.class);
        hashMap.put("healthengine", HealthEngineEntry.class);
        hashMap.put("healthyliving", HealthyLivingEntry.class);
        hashMap.put("weight", WeightJsApi.class);
        hashMap.put("mediaCenter", MediaCenterH5Api.class);
        hashMap.put("menstrual", MenstrualModule.class);
        return hashMap;
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public Class<? extends JsModuleBase> getCommonJsModule(String str) {
        Class<? extends JsModuleBase> cls = JS_MODULE_MAP.get(str);
        if (cls != null) {
            return cls;
        }
        LogUtil.h(TAG, "unknow JsModule:", str);
        return JsModuleBase.class;
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void loadH5ProApp(final Context context, final String str, H5ProLaunchOption.Builder builder) {
        if (context == null) {
            LogUtil.h(TAG, "loadH5ProApp: context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "loadH5ProApp: pkgName is empty");
            return;
        }
        H5proUtil.initH5pro(str);
        final H5ProLaunchOption buildOption = H5proUtil.buildOption(str, builder);
        String e = KeyValDbManager.b(BaseApplication.e()).e("AT_SCOPE_DEAL");
        LogUtil.a(TAG, "hms download flag is " + e);
        if ((ECG_AC_NAME.equals(str) || ECG_NAME.equals(str)) && !TextUtils.isEmpty(e) && "2".equals(e) && CommonUtil.z(BaseApplication.e()) && !CompileParameterUtil.a("GOOGLE_PLAY_OEM_BETTERME", false)) {
            mxv.a(context);
            return;
        }
        if (str.equals("com.huawei.health.h5.snack-control")) {
            mph.b().launchActivity(BaseApplication.e(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: com.huawei.operation.PluginOperationImpl$$ExternalSyntheticLambda0
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context2, Intent intent) {
                    return PluginOperationImpl.lambda$loadH5ProApp$0(H5ProLaunchOption.this, context, str, context2, intent);
                }
            });
            return;
        }
        if (str.equals("com.huawei.health.h5.ai-body-shape")) {
            LogUtil.a(TAG, "start body shape analysis algorithm download");
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.PluginOperationImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    mmn.b().launchActivity(BaseApplication.e(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: com.huawei.operation.PluginOperationImpl$$ExternalSyntheticLambda3
                        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                        public final boolean call(Context context2, Intent intent) {
                            return PluginOperationImpl.lambda$loadH5ProApp$1(H5ProLaunchOption.this, r2, r3, context2, intent);
                        }
                    });
                }
            });
        } else if (str.equals("com.huawei.health.h5.cycle-calendar")) {
            LogUtil.a(TAG, "start cycle calendar");
            mpf.e().launchActivity(BaseApplication.e(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: com.huawei.operation.PluginOperationImpl$$ExternalSyntheticLambda2
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context2, Intent intent) {
                    return PluginOperationImpl.lambda$loadH5ProApp$3(H5ProLaunchOption.this, context, str, context2, intent);
                }
            });
        } else {
            H5ProClient.startH5MiniProgram(context, str, buildOption);
        }
    }

    static /* synthetic */ boolean lambda$loadH5ProApp$0(H5ProLaunchOption h5ProLaunchOption, Context context, String str, Context context2, Intent intent) {
        if (h5ProLaunchOption != null) {
            h5ProLaunchOption.jsModuleClassMap.put("ocrJsModule", mph.b().getOcrJsApi("ocrJsModule"));
        }
        H5ProClient.startH5MiniProgram(context, str, h5ProLaunchOption);
        return false;
    }

    static /* synthetic */ boolean lambda$loadH5ProApp$1(H5ProLaunchOption h5ProLaunchOption, Context context, String str, Context context2, Intent intent) {
        LogUtil.a(TAG, "start body shape H5 mini program");
        if (h5ProLaunchOption != null) {
            h5ProLaunchOption.jsModuleClassMap.put("bodyShapeAnalysisApi", mmn.b().getBodyShapeAnalysisJsApi("bodyShapeAnalysisApi"));
        }
        H5ProClient.startH5MiniProgram(context, str, h5ProLaunchOption);
        return false;
    }

    static /* synthetic */ boolean lambda$loadH5ProApp$3(H5ProLaunchOption h5ProLaunchOption, Context context, String str, Context context2, Intent intent) {
        LogUtil.a(TAG, "cycle calendar plugin has download");
        if (h5ProLaunchOption != null) {
            h5ProLaunchOption.jsModuleClassMap.put("menstrual", MenstrualModule.class);
        }
        H5ProClient.startH5MiniProgram(context, str, h5ProLaunchOption);
        return false;
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public Intent createWebViewIntent(Context context, Bundle bundle, Integer num) {
        LogUtil.a(TAG, "createWebViewIntent start");
        if (context == null) {
            LogUtil.h(TAG, "createWebViewIntent context empty");
            return null;
        }
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (num != null) {
            intent.setFlags(num.intValue());
        }
        return intent;
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public boolean isSuperTrustedMiniProgram(String str, String str2) {
        return H5MiniProgramSecurityHelper.isSuperTrustedMiniProgram(str, str2);
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public boolean switchToMarketingTwo() {
        return OperationUtils.switchToMarketingTwo();
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void startOperationWebPage(Context context, String str) {
        LogUtil.a(TAG, "startOperationWebPage");
        if (context == null) {
            LogUtil.a(TAG, "context is null");
        } else {
            PluginOperation.getInstance(context).startOperationWebPage(str);
        }
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void loadEmbeddedH5(H5ProWebView h5ProWebView, String str, H5ProLaunchOption.Builder builder) {
        H5proUtil.initH5pro(str);
        H5ProClient.startH5MiniProgram(str, h5ProWebView, H5proUtil.buildOption(str, builder));
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void putBiEventFromH5Deeplink(String str, String str2) {
        H5proUtil.putBiEventFromH5Deeplink(str, str2);
    }

    @Override // com.huawei.health.baseapi.pluginoperation.PluginOperationApi
    public void functionEntrance(String str, boolean z) {
        OperationCloudUtil.functionEntrance(str, z);
    }
}
