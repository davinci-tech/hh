package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.pluginhealthalgorithm.NutritionTableOcrApi;
import health.compact.a.util.LogUtil;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class mph extends AppBundlePluginProxy<NutritionTableOcrApi> implements NutritionTableOcrApi {

    /* renamed from: a, reason: collision with root package name */
    private static volatile mph f15095a;
    private NutritionTableOcrApi d;

    private mph() {
        super("NutritionTableOcrProxy", "PluginHealthAlgorithm", "com.huawei.health.pluginhealthalgorithm.nutritiontableocr.NutritionTableOcrIml");
        LogUtil.d("NutritionTableOcrProxy", "createPluginApi");
        this.d = createPluginApi();
    }

    public static mph b() {
        mph mphVar;
        if (f15095a == null) {
            synchronized (mph.class) {
                if (f15095a == null) {
                    f15095a = new mph();
                }
                mphVar = f15095a;
            }
            return mphVar;
        }
        return f15095a;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.d != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(NutritionTableOcrApi nutritionTableOcrApi) {
        this.d = nutritionTableOcrApi;
    }

    @Override // com.huawei.pluginhealthalgorithm.NutritionTableOcrApi
    public String getNutritionTableOcrResult(Context context, Bitmap bitmap) {
        if (isPluginAvaiable()) {
            return this.d.getNutritionTableOcrResult(context, bitmap);
        }
        LogUtil.d("NutritionTableOcrProxy", "getNutritionTableOcrResult loadPlugin");
        a(null);
        return null;
    }

    @Override // com.huawei.pluginhealthalgorithm.NutritionTableOcrApi
    public void release() {
        NutritionTableOcrApi nutritionTableOcrApi = this.d;
        if (nutritionTableOcrApi != null) {
            nutritionTableOcrApi.release();
        }
    }

    @Override // com.huawei.pluginhealthalgorithm.NutritionTableOcrApi
    public Class<? extends JsModuleBase> getOcrJsApi(String str) {
        if (isPluginAvaiable()) {
            return this.d.getOcrJsApi(str);
        }
        LogUtil.d("NutritionTableOcrProxy", "getCommonJsModule loadPlugin");
        a(null);
        return JsModuleBase.class;
    }

    public void b(boolean z, int i) {
        int i2 = z ? 1 : 2;
        LogUtil.d("NutritionTableOcrProxy", "snackControlEntranceBiAnalytic from:", Integer.valueOf(i2), "entranceType:", Integer.valueOf(i));
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i2));
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SNACK_CONTROL_ENTRANCE.value(), hashMap, 0);
    }

    public void d(int i, int i2, int i3) {
        LogUtil.d("NutritionTableOcrProxy", "snackControlOcrResultBiAnalytic result:", Integer.valueOf(i), " event: ", Integer.valueOf(i2), " pageType:", Integer.valueOf(i3));
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("result", Integer.valueOf(i));
        hashMap.put("event", Integer.valueOf(i2));
        if (i3 != -1) {
            hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(i3));
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SNACK_CONTROL_ACTION.value(), hashMap, 0);
    }

    public void a(AppBundleLauncher.InstallCallback installCallback) {
        LogUtil.d("NutritionTableOcrProxy", "load nutritionTableOcr plugin");
        loadPlugin(installCallback);
    }
}
