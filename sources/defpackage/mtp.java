package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsleepdetection.PluginSleepDetectionApi;

/* loaded from: classes6.dex */
public class mtp extends AppBundlePluginProxy<PluginSleepDetectionApi> implements PluginSleepDetectionApi {
    private static volatile mtp b;
    private PluginSleepDetectionApi c;

    private mtp() {
        super("PluginSleepDetectionProxy", "PluginSleepDetection", "com.huawei.sleepdetection.PluginSleepDetectionImpl");
        this.c = createPluginApi();
    }

    public static mtp d() {
        mtp mtpVar;
        if (b == null) {
            synchronized (mtp.class) {
                if (b == null) {
                    b = new mtp();
                }
                mtpVar = b;
            }
            return mtpVar;
        }
        return b;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.c != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(PluginSleepDetectionApi pluginSleepDetectionApi) {
        this.c = pluginSleepDetectionApi;
    }

    public void e(Context context, String str) {
        d(context, false, str);
    }

    public void d(Context context, boolean z, String str) {
        if (context == null) {
            LogUtil.h("PluginSleepDetectionProxy", "gotoFamilyHealth context is null");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("isStart", z);
        intent.putExtra(ArkUIXConstants.FROM_TYPE, str);
        intent.setClassName(context, ComponentInfo.PluginSleepDetection_A_0);
        launchActivity(context, intent);
    }

    public void d(Context context, String str) {
        if (context == null) {
            LogUtil.h("PluginSleepDetectionProxy", "gotoFamilyHealth context is null");
            return;
        }
        if (!HandlerExecutor.c()) {
            LogUtil.h("PluginSleepDetectionProxy", "gotoH5 is not MainTread");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("h5Path", str);
        intent.setClassName(context, ComponentInfo.PluginSleepDetection_A_0);
        launchActivity(context, intent);
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public void sleepRecordStatus(IBaseResponseCallback iBaseResponseCallback) {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            pluginSleepDetectionApi.sleepRecordStatus(iBaseResponseCallback);
        }
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public void stopSleepRecord(Context context) {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            pluginSleepDetectionApi.stopSleepRecord(context);
        }
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public void disconnect() {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            pluginSleepDetectionApi.disconnect();
        }
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public int getSleepTime() {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            return pluginSleepDetectionApi.getSleepTime();
        }
        return 1380;
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public boolean isOpenNotification() {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            return pluginSleepDetectionApi.isOpenNotification();
        }
        return false;
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public Class<? extends JsModuleBase> getJsModule() {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            return pluginSleepDetectionApi.getJsModule();
        }
        return JsModuleBase.class;
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public boolean isEnableRecord() {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            return pluginSleepDetectionApi.isEnableRecord();
        }
        return false;
    }

    @Override // com.huawei.pluginsleepdetection.PluginSleepDetectionApi
    public void syncSleepAlarmInfo() {
        PluginSleepDetectionApi pluginSleepDetectionApi = this.c;
        if (pluginSleepDetectionApi != null) {
            pluginSleepDetectionApi.syncSleepAlarmInfo();
        }
    }
}
