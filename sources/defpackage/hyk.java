package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.healthosa.HealthOsaApi;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class hyk extends AppBundlePluginProxy<HealthOsaApi> implements HealthOsaApi {
    private static volatile hyk c;

    /* renamed from: a, reason: collision with root package name */
    private HealthOsaApi f13324a;
    private String b;
    private Context d;

    private hyk() {
        super("HealthOsa_HealthOsaProxy", "HealthOsa", "com.huawei.healthosa.location.OsaServiceForLocationInteraction");
        this.b = "";
        this.f13324a = createPluginApi();
    }

    public static hyk b() {
        hyk hykVar;
        if (c == null) {
            synchronized (hyk.class) {
                if (c == null) {
                    c = new hyk();
                }
                hykVar = c;
            }
            return hykVar;
        }
        return c;
    }

    @Override // com.huawei.healthosa.HealthOsaApi
    public void startOsaH5(Context context, String str) {
        if (context == null) {
            LogUtil.h("HealthOsa_HealthOsaProxy", "startOsaH5 context is null");
            return;
        }
        this.d = context;
        if (this.f13324a != null) {
            LogUtil.a("HealthOsa_HealthOsaProxy", "startOsaH5");
            this.f13324a.startOsaH5(context, str);
        } else {
            this.b = str;
            loadPlugin();
        }
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.f13324a != null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public void initialize(HealthOsaApi healthOsaApi) {
        this.f13324a = healthOsaApi;
        LogUtil.a("HealthOsa_HealthOsaProxy", "initialize startOsaH5, mBiFrom is ", this.b);
        startOsaH5(this.d, this.b);
        this.b = "";
    }

    @Override // com.huawei.healthosa.HealthOsaApi
    public Class<? extends JsBaseModule> getOsaJsModule(String str) {
        HealthOsaApi healthOsaApi = this.f13324a;
        if (healthOsaApi == null) {
            LogUtil.h("HealthOsa_HealthOsaProxy", "getOsaJsModule: mHealthOsaApi is null");
            return JsBaseModule.class;
        }
        return healthOsaApi.getOsaJsModule(str);
    }
}
