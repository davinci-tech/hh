package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.marketing.request.RecommendResourceReq;
import com.huawei.health.marketing.request.RecommendResourceRsp;
import com.huawei.health.marketing.request.RecommendResourcesApi;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class eip {
    private RecommendResourcesApi c = (RecommendResourcesApi) lqi.d().b(RecommendResourcesApi.class);

    public void d(List<String> list, int i, IBaseResponseCallback iBaseResponseCallback) {
        Response<RecommendResourceRsp> execute;
        RecommendResourceReq recommendResourceReq = new RecommendResourceReq();
        try {
            recommendResourceReq.setScenarioIds(list);
            recommendResourceReq.setItemNum(i);
            HashMap<String, String> e = ntd.e();
            for (String str : e.keySet()) {
                if (e.get(str) == null) {
                    e.put(str, "");
                }
            }
            e.put("x-version", CommonUtil.c(BaseApplication.e()));
            execute = this.c.getRecommendResourceInfos(recommendResourceReq.getUrl(), e).execute();
        } catch (Exception e2) {
            LogUtil.b("RecommendResourceInfoManager", "getVipInfo fail, ", e2.getMessage());
        }
        if (execute == null) {
            LogUtil.h("RecommendResourceInfoManager", "response is null");
            iBaseResponseCallback.d(-1, null);
        } else if (!execute.isOK()) {
            LogUtil.h("RecommendResourceInfoManager", "response result == ", Integer.valueOf(execute.getCode()));
            iBaseResponseCallback.d(-1, null);
        } else {
            LogUtil.a("RecommendResourceInfoManager", "response is OK.");
            iBaseResponseCallback.d(0, execute.getBody().getResultInfo());
        }
    }
}
