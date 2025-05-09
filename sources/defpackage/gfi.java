package defpackage;

import com.huawei.health.suggestion.ui.tabfragments.repository.Repository;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import org.json.JSONArray;

/* loaded from: classes8.dex */
public class gfi implements Repository {
    private JSONArray e = null;

    public static /* synthetic */ void a(HashMap hashMap, HashMap hashMap2, HttpResCallback httpResCallback, String str) {
        LogUtil.c("Suggestion_RecommendActivityRepository", "HttpPost request url:", str, "/activity/getActivities", " params:", hashMap.toString(), ",headers:", hashMap2.toString());
        jei.d(str + "/activity/getActivities", hashMap, hashMap2, httpResCallback);
    }
}
