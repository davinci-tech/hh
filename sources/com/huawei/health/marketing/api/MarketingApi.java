package com.huawei.health.marketing.api;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.Task;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface MarketingApi {
    @Deprecated
    Map<Integer, ResourceResultInfo> acquireResourceInfoFromDb(int i);

    Map<Integer, ResourceResultInfo> filterMarketingRules(MarketingOption marketingOption);

    Map<Integer, ResourceResultInfo> filterMarketingRules(Map<Integer, ResourceResultInfo> map);

    List<View> getMarketingViewList(Context context, Map<Integer, ResourceResultInfo> map);

    List<View> getMarketingViewList(Context context, Map<Integer, ResourceResultInfo> map, FragmentManager fragmentManager);

    Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(int i);

    Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(int i, String str);

    Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(List<Integer> list);

    Task<Map<Integer, ResourceResultInfo>> getResourceResultInfo(List<Integer> list, String str);

    boolean isValidUserLabels(String str);

    Map<Integer, ResourceResultInfo> parseMarketingResource(JSONObject jSONObject, List<Integer> list);

    void recordResourcePresent(int i, int i2, ResourceBriefInfo resourceBriefInfo);

    void requestMarketingResource(MarketingOption marketingOption);

    void setDisplayLimit(Map<String, Boolean> map);

    void setViewBiEvent(int i, ResourceBriefInfo resourceBriefInfo);

    void triggerMarketingResourceEvent(MarketingOption marketingOption);
}
