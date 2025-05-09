package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.baseapi.hiaiengine.HiAiEngineApi;
import com.huawei.health.baseapi.hiaiengine.HiAiFaceDetectorApi;
import com.huawei.health.baseapi.hiaiengine.HiAiIntelligentSleepApi;
import com.huawei.health.baseapi.hiaiengine.HiAiKitCreateListener;
import com.huawei.health.baseapi.hiaiengine.HiAiKitInitTtsListener;
import com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener;
import com.huawei.health.baseapi.hiaiengine.HiAiSmartClipApi;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class bzo extends AppBundlePluginProxy<HiAiEngineApi> implements HiAiEngineApi {

    /* renamed from: a, reason: collision with root package name */
    private static volatile bzo f572a;
    private HiAiSmartClipApi b;
    private HiAiEngineApi c;
    private HiAiIntelligentSleepApi d;
    private HiAiFaceDetectorApi e;

    private bzo() {
        super("HiAiEngineProxy", "PluginHiAiEngine", "com.huawei.health.pluginhiaiengine.HiAiEngineApiImpl");
        this.c = createPluginApi();
    }

    public static bzo c() {
        bzo bzoVar;
        if (f572a == null) {
            synchronized (bzo.class) {
                if (f572a == null) {
                    f572a = new bzo();
                }
                bzoVar = f572a;
            }
            return bzoVar;
        }
        return f572a;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.c != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(HiAiEngineApi hiAiEngineApi) {
        this.c = hiAiEngineApi;
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void initHiAiEngine() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.initHiAiEngine();
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void createKit(Context context, Intent intent, String str, HiAiKitCreateListener hiAiKitCreateListener) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.createKit(context, intent, str, hiAiKitCreateListener);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void initKitRecognizeEngine(String str, String str2, HiAiKitRecognizeListener hiAiKitRecognizeListener) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.initKitRecognizeEngine(str, str2, hiAiKitRecognizeListener);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void updateSwitch(boolean z) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.updateSwitch(z);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void initKitTtsEngine(HiAiKitInitTtsListener hiAiKitInitTtsListener) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.initKitTtsEngine(hiAiKitInitTtsListener);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void startRecognize(String str, String str2, String str3) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.startRecognize(str, str2, str3);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void writeAudio(byte[] bArr) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.writeAudio(bArr);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void updateVoiceContext(String str) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.updateVoiceContext(str);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public String getLngAndLat(Context context) {
        HiAiEngineApi hiAiEngineApi = this.c;
        return hiAiEngineApi != null ? hiAiEngineApi.getLngAndLat(context) : "";
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void textToSpeak(String str, Intent intent) {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.textToSpeak(str, intent);
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public boolean isSpeaking() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            return hiAiEngineApi.isSpeaking();
        }
        return false;
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void stopSpeak() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.stopSpeak();
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void cancelSpeak() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.cancelSpeak();
        }
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public boolean isDestroyKit() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            return hiAiEngineApi.isDestroyKit();
        }
        return false;
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public void initServiceRoute() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            hiAiEngineApi.initServiceRoute();
        }
    }

    public void b(AppBundleLauncher.InstallCallback installCallback) {
        loadPlugin(installCallback);
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public HiAiSmartClipApi getHiAiSmartClip() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            this.b = hiAiEngineApi.getHiAiSmartClip();
        } else {
            LogUtil.a("HiAiEngineProxy", "cann't get mHiAiSmartClipApi");
            loadPlugin();
        }
        return this.b;
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public HiAiIntelligentSleepApi getHiAiIntelligentSleepApi() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            this.d = hiAiEngineApi.getHiAiIntelligentSleepApi();
        } else {
            LogUtil.a("HiAiEngineProxy", "cann't get mHiAiIntelligentSleepApi");
            loadPlugin();
        }
        return this.d;
    }

    @Override // com.huawei.health.baseapi.hiaiengine.HiAiEngineApi
    public HiAiFaceDetectorApi getHiAiFaceDetectorApi() {
        HiAiEngineApi hiAiEngineApi = this.c;
        if (hiAiEngineApi != null) {
            this.e = hiAiEngineApi.getHiAiFaceDetectorApi();
        } else {
            LogUtil.a("HiAiEngineProxy", "cann't get mHiAiFaceDetectorApi");
            loadPlugin();
        }
        return this.e;
    }
}
