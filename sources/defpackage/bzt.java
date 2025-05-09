package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.baseapi.pluginaudiodevice.InitAudioDeviceKitApi;
import com.huawei.health.baseapi.pluginaudiodevice.ResultCallback;
import com.huawei.health.baseapi.pluginaudiodevice.StateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class bzt extends AppBundlePluginProxy<InitAudioDeviceKitApi> implements InitAudioDeviceKitApi {
    private static volatile bzt e;
    private InitAudioDeviceKitApi b;

    private bzt() {
        super("InitAudioDeviceKitProxy", "PluginAudioDevice", "com.huawei.health.audiodevice.AudioDeviceManager");
        this.b = createPluginApi();
    }

    public static bzt c() {
        bzt bztVar;
        LogUtil.a("InitAudioDeviceKitProxy", "InitAudioDeviceKitProxy");
        if (e == null) {
            synchronized (bzt.class) {
                if (e == null) {
                    e = new bzt();
                }
                bztVar = e;
            }
            return bztVar;
        }
        return e;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.b != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(InitAudioDeviceKitApi initAudioDeviceKitApi) {
        this.b = initAudioDeviceKitApi;
        LogUtil.a("InitAudioDeviceKitProxy", "initialize AudioDeviceManager");
        initAudioDeviceKitAdapter(BaseApplication.getContext());
    }

    @Override // com.huawei.health.baseapi.pluginaudiodevice.InitAudioDeviceKitApi
    public void initAudioDeviceKitAdapter(Context context) {
        InitAudioDeviceKitApi initAudioDeviceKitApi = this.b;
        if (initAudioDeviceKitApi != null) {
            initAudioDeviceKitApi.initAudioDeviceKitAdapter(context);
            b(context);
        }
    }

    @Override // com.huawei.health.baseapi.pluginaudiodevice.InitAudioDeviceKitApi
    public void initAudioDeviceKitAdapter(Context context, StateCallback<String> stateCallback) {
        InitAudioDeviceKitApi initAudioDeviceKitApi = this.b;
        if (initAudioDeviceKitApi != null) {
            initAudioDeviceKitApi.initAudioDeviceKitAdapter(context, stateCallback);
            b(context);
        }
    }

    @Override // com.huawei.health.baseapi.pluginaudiodevice.InitAudioDeviceKitApi
    public void obtainDeviceSn(Context context, String str, ResultCallback<String> resultCallback) {
        InitAudioDeviceKitApi initAudioDeviceKitApi = this.b;
        if (initAudioDeviceKitApi != null) {
            initAudioDeviceKitApi.obtainDeviceSn(context, str, resultCallback);
        }
    }

    @Override // com.huawei.health.baseapi.pluginaudiodevice.InitAudioDeviceKitApi
    public void registerConnectStatus(Context context, String str, StateCallback<String> stateCallback) {
        InitAudioDeviceKitApi initAudioDeviceKitApi = this.b;
        if (initAudioDeviceKitApi != null) {
            initAudioDeviceKitApi.registerConnectStatus(context, str, stateCallback);
        }
    }

    @Override // com.huawei.health.baseapi.pluginaudiodevice.InitAudioDeviceKitApi
    public void unregisterConnectStatus(Context context, String str) {
        InitAudioDeviceKitApi initAudioDeviceKitApi = this.b;
        if (initAudioDeviceKitApi != null) {
            initAudioDeviceKitApi.unregisterConnectStatus(context, str);
        }
    }

    private void b(Context context) {
        LogUtil.a("InitAudioDeviceKitProxy", "start load audioKit jni lib");
        try {
            AppBundle.b().loadLibrary(context, "c++_shared");
            AppBundle.b().loadLibrary(context, "apmapi");
            LogUtil.a("InitAudioDeviceKitProxy", "load audioKit jni lib success");
        } catch (UnsatisfiedLinkError e2) {
            LogUtil.b("InitAudioDeviceKitProxy", "load audioKit jni lib fail:", e2.getMessage());
        }
    }
}
