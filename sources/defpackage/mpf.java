package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.pluginhealthalgorithm.MenstrualAlgorithmApi;

/* loaded from: classes6.dex */
public class mpf extends AppBundlePluginProxy<MenstrualAlgorithmApi> implements MenstrualAlgorithmApi {
    private static volatile mpf d;
    private MenstrualAlgorithmApi b;

    private mpf() {
        super("PpgHeartStudyAlgorithmProxy", "PluginHealthAlgorithm", "com.huawei.health.pluginhealthalgorithm.menstrual.MenstrualAlgorithmImpl");
        this.b = createPluginApi();
    }

    public static mpf e() {
        mpf mpfVar;
        if (d == null) {
            synchronized (mpf.class) {
                if (d == null) {
                    d = new mpf();
                }
                mpfVar = d;
            }
            return mpfVar;
        }
        return d;
    }

    @Override // com.huawei.pluginhealthalgorithm.MenstrualAlgorithmApi
    public String predict(String str) {
        MenstrualAlgorithmApi menstrualAlgorithmApi = this.b;
        if (menstrualAlgorithmApi != null) {
            return menstrualAlgorithmApi.predict(str);
        }
        loadPlugin();
        return "";
    }

    @Override // com.huawei.pluginhealthalgorithm.MenstrualAlgorithmApi
    public String editPredict(String str) {
        MenstrualAlgorithmApi menstrualAlgorithmApi = this.b;
        if (menstrualAlgorithmApi != null) {
            return menstrualAlgorithmApi.editPredict(str);
        }
        loadPlugin();
        return "";
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.b != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void initialize(MenstrualAlgorithmApi menstrualAlgorithmApi) {
        this.b = menstrualAlgorithmApi;
    }
}
