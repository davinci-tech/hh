package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.airopeskipping.RopeSkippingResourcesApi;

/* loaded from: classes6.dex */
public class mwr extends AppBundlePluginProxy<RopeSkippingResourcesApi> implements RopeSkippingResourcesApi {
    private RopeSkippingResourcesApi c;

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final mwr f15223a = new mwr();
    }

    public static mwr b() {
        return d.f15223a;
    }

    private mwr() {
        super("Track_RopeSkippingResourcesProxy", "RopeSkippingResources", "com.huawei.ropeskippingresources.RopeSkippingResourcesImpl");
        LogUtil.a("Track_RopeSkippingResourcesProxy", "RopeSkippingResourcesProxy()");
        this.c = createPluginApi();
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        LogUtil.a("Track_RopeSkippingResourcesProxy", "isPluginAvaiable() mRopeSkippingResourcesApi: ", this.c);
        return this.c != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(RopeSkippingResourcesApi ropeSkippingResourcesApi) {
        LogUtil.a("Track_RopeSkippingResourcesProxy", "initialize() pluginApi: ", ropeSkippingResourcesApi);
        this.c = ropeSkippingResourcesApi;
    }
}
