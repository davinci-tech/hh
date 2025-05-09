package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.featuremarketing.db.MarketingDataDbManager;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
class dmk {
    dmk() {
    }

    static Map<Integer, ResourceResultInfo> b(List<Integer> list) {
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            return hashMap;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            ResourceResultInfo d = d(intValue);
            if (d != null) {
                hashMap.put(Integer.valueOf(intValue), d);
            }
        }
        return hashMap;
    }

    private static ResourceResultInfo d(int i) {
        String a2 = MarketingDataDbManager.a(BaseApplication.e()).a(i);
        if (a2 == null) {
            LogUtil.d("MarketingDBProvider", "No resource data in database. positionId: ", Integer.valueOf(i));
            return null;
        }
        try {
            return (ResourceResultInfo) new Gson().fromJson(a2, ResourceResultInfo.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("MarketingDBProvider", "parse resource in db failed, exception: " + e);
            return null;
        }
    }

    public static void d(final Map<Integer, ResourceResultInfo> map) {
        LogUtil.c("MarketingDBProvider", "marketing enter saveResourceResultInfoData");
        if (map == null) {
            LogUtil.a("MarketingDBProvider", "marketing resourceResultInfo is Empty");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dmh
                @Override // java.lang.Runnable
                public final void run() {
                    dmk.a(map);
                }
            });
        }
    }

    static /* synthetic */ void a(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            ResourceResultInfo resourceResultInfo = (ResourceResultInfo) entry.getValue();
            if (resourceResultInfo == null) {
                LogUtil.c("MarketingDBProvider", "resourceResultInfo is null. positionId = ", Integer.valueOf(intValue));
            } else {
                long resourcesLatestModifyTime = resourceResultInfo.getResourcesLatestModifyTime();
                LogUtil.c("MarketingDBProvider", "marketing resourceResultInfo result: ", Long.valueOf(MarketingDataDbManager.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).a(intValue, new Gson().toJson(resourceResultInfo), resourcesLatestModifyTime)));
            }
        }
    }
}
