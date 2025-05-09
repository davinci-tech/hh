package defpackage;

import com.huawei.health.loginaccount.PluginThirdAuthorizeApi;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ehs implements PluginThirdAuthorizeApi {

    /* renamed from: a, reason: collision with root package name */
    private PluginThirdAuthorizeApi f12018a;

    static class e {
        public static final ehs d = new ehs();
    }

    private ehs() {
        try {
            this.f12018a = (PluginThirdAuthorizeApi) Class.forName("niq").newInstance();
            LogUtil.a("PluginAccountLogin_PluginThirdAuthorizeApi", "init PluginThirdAuthorizeProxy ok");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            LogUtil.b("PluginAccountLogin_PluginThirdAuthorizeApi", "ClassNotFoundException");
        }
    }

    public static ehs d() {
        return e.d;
    }

    @Override // com.huawei.health.loginaccount.PluginThirdAuthorizeApi
    public void initThirdAuthorizeMgr() {
        PluginThirdAuthorizeApi pluginThirdAuthorizeApi = this.f12018a;
        if (pluginThirdAuthorizeApi != null) {
            pluginThirdAuthorizeApi.initThirdAuthorizeMgr();
        } else {
            LogUtil.h("PluginAccountLogin_PluginThirdAuthorizeApi", "initThirdAuthorizeMgr mPluginThirdAuthorizeApi is null");
        }
    }
}
