package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepSeriesCourseTopic;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepCourseBean;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepSeriesCourseBean;
import defpackage.ixu;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepSeriesCourseTopic {
    private String b;
    private String c;
    private final List<SleepSeriesCourseBean> d = new ArrayList();
    private final int e;

    public interface RequestListener {
        void onRequestDone(SleepSeriesCourseTopic sleepSeriesCourseTopic);
    }

    public String c() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public List<SleepSeriesCourseBean> e() {
        return this.d;
    }

    public SleepSeriesCourseTopic(int i) {
        this.e = i;
    }

    public void e(final RequestListener requestListener) {
        LogUtil.a("SleepSeriesCourseTopic", "requestData");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            ReleaseLogUtil.c("SleepSeriesCourseTopic", "marketingApi is null");
        } else {
            marketingApi.getResourceResultInfo(this.e).addOnSuccessListener(new OnSuccessListener() { // from class: poz
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    SleepSeriesCourseTopic.this.a(marketingApi, requestListener, (Map) obj);
                }
            });
        }
    }

    public /* synthetic */ void a(MarketingApi marketingApi, RequestListener requestListener, Map map) {
        if (map == null) {
            LogUtil.b("SleepSeriesCourseTopic", "no section map for ", Integer.valueOf(this.e));
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules == null) {
            LogUtil.b("SleepSeriesCourseTopic", "no section map for ", Integer.valueOf(this.e), ", after ruling");
            return;
        }
        ResourceResultInfo resourceResultInfo = filterMarketingRules.get(Integer.valueOf(this.e));
        if (resourceResultInfo == null) {
            LogUtil.b("SleepSeriesCourseTopic", "no resourceResultInfo for ", Integer.valueOf(this.e));
            return;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (koq.b(resources)) {
            LogUtil.b("SleepSeriesCourseTopic", "no briefInfoList for ", Integer.valueOf(this.e));
        } else {
            d(resources, requestListener);
        }
    }

    private void d(List<ResourceBriefInfo> list, RequestListener requestListener) {
        LogUtil.a("SleepSeriesCourseTopic", "parseBriefInfo");
        LogUtil.a("SleepSeriesCourseTopic", "briefInfoList.size() = ", Integer.valueOf(list.size()), " briefInfoList = ", list);
        ResourceContentBase content = list.get(0).getContent();
        if (content == null) {
            LogUtil.b("SleepSeriesCourseTopic", "requestData failed cause contentBase is null!");
            return;
        }
        String content2 = content.getContent();
        if (TextUtils.isEmpty(content2)) {
            LogUtil.b("SleepSeriesCourseTopic", "requestData failed cause content is empty!");
            return;
        }
        LogUtil.a("SleepSeriesCourseTopic", "content = ", content2);
        SleepCourseBean sleepCourseBean = (SleepCourseBean) ixu.e(content2, new TypeToken<SleepCourseBean>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepSeriesCourseTopic.4
        });
        if (sleepCourseBean == null) {
            LogUtil.b("SleepSeriesCourseTopic", "sleepBean is null");
            return;
        }
        LogUtil.a("SleepSeriesCourseTopic", "sleepBean = ", sleepCourseBean);
        LogUtil.a("SleepSeriesCourseTopic", "handleData");
        List<SleepSeriesCourseBean> gridContents = sleepCourseBean.getGridContents();
        this.d.clear();
        if (koq.c(gridContents)) {
            this.d.addAll(gridContents);
        }
        this.c = sleepCourseBean.getName();
        this.b = sleepCourseBean.getSubTitle();
        requestListener.onRequestDone(this);
    }
}
