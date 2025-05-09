package defpackage;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.tasks.Task;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtilsApi;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@ApiDefine(uri = MarketingApi.class)
/* loaded from: classes3.dex */
public class dlh implements MarketingApi {
    @Override // com.huawei.health.marketing.api.MarketingApi
    public void requestMarketingResource(MarketingOption marketingOption) {
        if (LoginInit.getInstance(BaseApplication.e()).isKidAccount() || Utils.l()) {
            LogUtil.c("MarketingImpl", "requestMarketingResource isKidAccount or isOverseaNoCloudVersion. return.");
        } else {
            dmi.e(marketingOption);
        }
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public void triggerMarketingResourceEvent(final MarketingOption marketingOption) {
        ThreadPoolManager.d().c("MarketingImpl", new Runnable() { // from class: dlh.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("MarketingImpl", "triggerMarketingResourceEvent... triggerEventType:" + marketingOption.getTriggerEventType());
                if (LoginInit.getInstance(BaseApplication.e()).isKidAccount() || Utils.l()) {
                    LogUtil.c("MarketingImpl", "triggerMarketingResourceEvent isKidAccount or isOverseaNoCloudVersion. return.");
                    return;
                }
                if (Utils.o() && !((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isOperation(LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010))) {
                    LogUtil.c("MarketingImpl", "triggerMarketingResourceEvent isOperation false. return.");
                    return;
                }
                if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
                    LogUtil.a("MarketingImpl", "triggerMarketingResourceEvent not using marketing 2.0");
                } else {
                    if (marketingOption.getContext() == null) {
                        LogUtil.d("MarketingImpl", "caller is dead, no need to triggerEvent");
                        return;
                    }
                    dms.e().b(marketingOption, marketingOption.getPageId(), marketingOption.getTriggerEventType());
                }
            }
        });
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Map<Integer, ResourceResultInfo> parseMarketingResource(JSONObject jSONObject, List<Integer> list) {
        return dmi.e(jSONObject, list);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(int i) {
        return getResourceResultInfo(i, (String) null);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        return getResourceResultInfo(arrayList, str);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(List<Integer> list) {
        return getResourceResultInfo(list, (String) null);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(List<Integer> list, String str) {
        return dmi.a(list, str);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Map<Integer, ResourceResultInfo> acquireResourceInfoFromDb(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        return dmi.a(arrayList);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Map<Integer, ResourceResultInfo> filterMarketingRules(MarketingOption marketingOption) {
        return dmm.d(marketingOption.getResultInfoMap(), marketingOption.getTriggerEventParams(), marketingOption.getTriggerEventType());
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public Map<Integer, ResourceResultInfo> filterMarketingRules(Map<Integer, ResourceResultInfo> map) {
        return dmm.d(map, null, 1);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public List<View> getMarketingViewList(Context context, Map<Integer, ResourceResultInfo> map) {
        return getMarketingViewList(context, map, null);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public List<View> getMarketingViewList(Context context, Map<Integer, ResourceResultInfo> map, FragmentManager fragmentManager) {
        return dml.d(context, map, fragmentManager);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public void recordResourcePresent(int i, int i2, ResourceBriefInfo resourceBriefInfo) {
        dml.c(i, i2, resourceBriefInfo);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public boolean isValidUserLabels(String str) {
        return new dnf().c(str);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public void setViewBiEvent(int i, ResourceBriefInfo resourceBriefInfo) {
        dml.d(i, resourceBriefInfo);
    }

    @Override // com.huawei.health.marketing.api.MarketingApi
    public void setDisplayLimit(Map<String, Boolean> map) {
        dnj.d(map);
    }
}
