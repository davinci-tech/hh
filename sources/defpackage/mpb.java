package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.pluginhealthalgorithm.EcgFilterAlgorithmApi;

/* loaded from: classes9.dex */
public class mpb extends AppBundlePluginProxy<EcgFilterAlgorithmApi> implements EcgFilterAlgorithmApi {
    private static volatile mpb b;
    private EcgFilterAlgorithmApi c;

    private mpb() {
        super("EcgFilterAlgorithmProxy", "PluginHealthAlgorithm", "com.huawei.health.pluginhealthalgorithm.ecgfilter.EcgFilterAlgorithmIml");
        this.c = createPluginApi();
    }

    public static mpb e() {
        mpb mpbVar;
        if (b == null) {
            synchronized (mpb.class) {
                if (b == null) {
                    b = new mpb();
                }
                mpbVar = b;
            }
            return mpbVar;
        }
        return b;
    }

    @Override // com.huawei.pluginhealthalgorithm.EcgFilterAlgorithmApi
    public String getVersion() {
        EcgFilterAlgorithmApi ecgFilterAlgorithmApi = this.c;
        if (ecgFilterAlgorithmApi != null) {
            return ecgFilterAlgorithmApi.getVersion();
        }
        loadPlugin();
        return "";
    }

    @Override // com.huawei.pluginhealthalgorithm.EcgFilterAlgorithmApi
    public int ecgFilterInit(int i) {
        EcgFilterAlgorithmApi ecgFilterAlgorithmApi = this.c;
        if (ecgFilterAlgorithmApi != null) {
            return ecgFilterAlgorithmApi.ecgFilterInit(i);
        }
        loadPlugin();
        return 0;
    }

    @Override // com.huawei.pluginhealthalgorithm.EcgFilterAlgorithmApi
    public String getEcgAlgorithmResult(String str, int i) {
        EcgFilterAlgorithmApi ecgFilterAlgorithmApi = this.c;
        if (ecgFilterAlgorithmApi != null) {
            return ecgFilterAlgorithmApi.getEcgAlgorithmResult(str, i);
        }
        loadPlugin();
        return "";
    }

    @Override // com.huawei.pluginhealthalgorithm.EcgFilterAlgorithmApi
    public String getEcgSaveResult() {
        EcgFilterAlgorithmApi ecgFilterAlgorithmApi = this.c;
        if (ecgFilterAlgorithmApi != null) {
            return ecgFilterAlgorithmApi.getEcgSaveResult();
        }
        loadPlugin();
        return "";
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.c != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(EcgFilterAlgorithmApi ecgFilterAlgorithmApi) {
        this.c = ecgFilterAlgorithmApi;
    }
}
