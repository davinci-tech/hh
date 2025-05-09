package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.trackprocess.api.PluginLocationApi;
import com.huawei.health.trackprocess.callback.PluginCloudTrackCallback;
import com.huawei.health.trackprocess.callback.PluginTrackMapCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gkj extends AppBundlePluginProxy<PluginLocationApi> implements PluginLocationApi {
    private static volatile gkj b;

    /* renamed from: a, reason: collision with root package name */
    private PluginLocationApi f12839a;

    private gkj() {
        super("PluginLocation_mPluginLocationProxy", "PluginWearAbility", "com.huawei.pluginlocation.PluginLocationApiImpl");
        this.f12839a = createPluginApi();
    }

    public static gkj b() {
        gkj gkjVar;
        if (b == null) {
            synchronized (gkj.class) {
                if (b == null) {
                    b = new gkj();
                }
                gkjVar = b;
            }
            return gkjVar;
        }
        return b;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.f12839a != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(PluginLocationApi pluginLocationApi) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "initialize");
        this.f12839a = pluginLocationApi;
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public void pluginRxnNativeInit() {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "pluginRxnNativeInit");
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            pluginLocationApi.pluginRxnNativeInit();
        } else {
            LogUtil.h("PluginLocation_mPluginLocationProxy", "pluginRxnNativeInit loadPlugin()");
            loadPlugin();
        }
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public void pluginRxnGenerateEphemeris() {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "pluginRxnGenerateEphemeris");
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            pluginLocationApi.pluginRxnGenerateEphemeris();
        } else {
            LogUtil.h("PluginLocation_mPluginLocationProxy", "pluginRxnGenerateEphemeris loadPlugin()");
            loadPlugin();
        }
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public int getOptimizedTrack(String str, String str2, boolean z, PluginTrackMapCallback pluginTrackMapCallback, boolean z2, String str3) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "getOptimizedTrack");
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            return pluginLocationApi.getOptimizedTrack(str, str2, z, pluginTrackMapCallback, z2, str3);
        }
        LogUtil.h("PluginLocation_mPluginLocationProxy", "getOptimizedTrack loadPlugin()");
        loadPlugin();
        LogUtil.a("PluginLocation_mPluginLocationProxy", "getOptimizedTrack return -1");
        return -1;
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public void setExtraData(boolean z, boolean z2, Context context, String str) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "setExtraData");
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            pluginLocationApi.setExtraData(z, z2, context, str);
        } else {
            LogUtil.h("PluginLocation_mPluginLocationProxy", "setExtraData loadPlugin()");
            loadPlugin();
        }
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public void postMotionPath(List<gkn> list) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "postMotionPath");
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            pluginLocationApi.postMotionPath(list);
        } else {
            LogUtil.h("PluginLocation_mPluginLocationProxy", "postMotionPath loadPlugin()");
            loadPlugin();
        }
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public void getCloudTrack(PluginCloudTrackCallback pluginCloudTrackCallback) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "getCloudTrack");
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            pluginLocationApi.getCloudTrack(pluginCloudTrackCallback);
        } else {
            LogUtil.h("PluginLocation_mPluginLocationProxy", "getCloudTrack loadPlugin()");
            loadPlugin();
        }
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public gki getEphRequest(List<String> list) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "getEphRequest");
        gki gkiVar = new gki();
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            return pluginLocationApi.getEphRequest(list);
        }
        LogUtil.h("PluginLocation_mPluginLocationProxy", "getEphRequest loadPlugin()");
        loadPlugin();
        return gkiVar;
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public Map<String, String> getEphResponse(gkm gkmVar) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "getEphResponse");
        HashMap hashMap = new HashMap();
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            return pluginLocationApi.getEphResponse(gkmVar);
        }
        LogUtil.h("PluginLocation_mPluginLocationProxy", "getEphResponse loadPlugin()");
        loadPlugin();
        return hashMap;
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public void setContext(Context context) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "setContext");
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            pluginLocationApi.setContext(context);
        } else {
            LogUtil.h("PluginLocation_mPluginLocationProxy", "setContext loadPlugin()");
            loadPlugin();
        }
    }

    @Override // com.huawei.health.trackprocess.api.PluginLocationApi
    public void setSerCountry(String str) {
        PluginLocationApi pluginLocationApi = this.f12839a;
        if (pluginLocationApi != null) {
            pluginLocationApi.setSerCountry(str);
        } else {
            LogUtil.h("PluginLocation_mPluginLocationProxy", "setContext loadPlugin()");
            loadPlugin();
        }
    }

    public void e(AppBundleLauncher.InstallCallback installCallback) {
        LogUtil.a("PluginLocation_mPluginLocationProxy", "loadPluginWearAbility");
        loadPlugin(installCallback);
    }
}
