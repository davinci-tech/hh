package defpackage;

import android.content.Context;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.jsmodules.JsInteractionCompact;
import com.huawei.pluginachievement.jsmodule.AchieveUtil;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes6.dex */
public class mqr {
    public static void c(Context context, mqt mqtVar) {
        H5proUtil.initH5pro();
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.familyspace", new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("JsInteraction", JsInteractionCompact.class).addCustomizeJsModule("healthZoneApi", PluginHealthH5InteractionUtil.class).addCustomizeJsModule("AchieveUtil", AchieveUtil.class).addCustomizeJsModule("device", bzs.e().getCommonJsModule("device")).addCustomizeArg("from", mqtVar.b()).addCustomizeArg("pushType", mqtVar.g()).addCustomizeArg("data", mqtVar.c()).addCustomizeArg(HealthZonePushReceiver.DETAIL_TYPE, mqtVar.a()).addCustomizeArg(HealthZonePushReceiver.IS_HASH_HUID, mqtVar.e()).addCustomizeArg("isFirstLoad", mqtVar.d()).addCustomizeArg("isGuestMode", String.valueOf(LoginInit.getInstance(context).isBrowseMode())).addCustomizeArg("verifyCode", mqtVar.j()).setImmerse().showStatusBar().setNeedSoftInputAdapter().setStatusBarTextBlack(true).setForceDarkMode(1).build());
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(CapabilityStatus.AWA_CAP_CODE_WIFI), "sIsFirstHealth", "1", (StorageParams) null);
    }
}
