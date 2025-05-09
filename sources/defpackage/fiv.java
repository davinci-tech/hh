package defpackage;

import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.suggestion.cloud.DietCloudApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import health.compact.a.LogAnonymous;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class fiv implements DietCloudApi {
    @Override // com.huawei.health.suggestion.cloud.DietCloudApi
    public void getFoodRecommendDetailCloud(String str, String str2, fit fitVar, DataCallback dataCallback) {
        if (dataCallback == null) {
            LogUtil.h("Suggestion_DietCloudApiImpl", "getFoodRecommendDetailCloud DataCallback == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("iVersion", "2");
            jSONObject.put("planId", str);
            jSONObject.put("recipesDate", str2);
            jSONObject.put("foodMatchConditions", new JSONObject(new Gson().toJson(fitVar)));
            jSONObject.put("management", fitVar.a());
            LogUtil.a("Suggestion_DietCloudApiImpl", "foodConditions" + fitVar.toString());
            fix.a("/healthExpansion/food/recommend/detail", jSONObject, dataCallback);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_DietCloudApiImpl", LogAnonymous.b((Throwable) e));
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
    }

    @Override // com.huawei.health.suggestion.cloud.DietCloudApi
    public void saveReplaceFoodCloud(fiz fizVar, DataCallback dataCallback) {
        if (dataCallback == null) {
            LogUtil.h("Suggestion_DietCloudApiImpl", "saveReplaceFoodCloud DataCallback == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("iVersion", "2");
            jSONObject.put("replaceType", fizVar.j());
            jSONObject.put("planId", fizVar.b());
            jSONObject.put("recipesDate", fizVar.c());
            jSONObject.put("foodMatchConditions", new JSONObject(new Gson().toJson(fizVar.a())));
            jSONObject.put("recommendDetail", new JSONObject(new Gson().toJson(fizVar.e())));
            jSONObject.put("mealType", fizVar.d());
            fix.a("/healthExpansion/food/saveReplaceFood", jSONObject, dataCallback);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_DietCloudApiImpl", LogAnonymous.b((Throwable) e));
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
    }

    @Override // com.huawei.health.suggestion.cloud.DietCloudApi
    public void getDietSummaryCloud(double d, double d2, int i, int i2, DataCallback dataCallback) {
        if (dataCallback == null) {
            LogUtil.h("Suggestion_DietCloudApiImpl", "getDietSummaryCloud DataCallback == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("iVersion", "1");
            jSONObject.put("weight", d);
            jSONObject.put("height", d2);
            jSONObject.put("averageDailyEnergyCommend", i);
            jSONObject.put("averageDailyEnergy", i2);
            fix.a("/healthExpansion/diet/getDietSummary", jSONObject, dataCallback);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_DietCloudApiImpl", LogAnonymous.b((Throwable) e));
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
    }

    @Override // com.huawei.health.suggestion.cloud.DietCloudApi
    public void batchGetFood(Set<String> set, DataCallback dataCallback) {
        if (dataCallback == null) {
            LogUtil.h("Suggestion_DietCloudApiImpl", "batchGetFood DataCallback == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("iVersion", "1");
            jSONObject.put("foodIds", jSONArray);
            fix.a("/healthExpansion/food/list", jSONObject, dataCallback);
            LogUtil.a("Suggestion_DietCloudApiImpl", "batch get food params is " + jSONObject);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_DietCloudApiImpl", LogAnonymous.b((Throwable) e));
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
    }

    @Override // com.huawei.health.suggestion.cloud.DietCloudApi
    public void getReplaceList(String str, int i, List<String> list, DataCallback dataCallback) {
        if (dataCallback == null) {
            LogUtil.h("Suggestion_DietCloudApiImpl", "getReplaceList DataCallback == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("iVersion", "2");
            jSONObject.put("replaceType", String.valueOf(2));
            jSONObject.put("recipesDate", str);
            jSONObject.put("mealType", i);
            if (koq.c(list)) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put("immutableFoodIds", jSONArray);
            }
            fix.a("/healthExpansion/food/getReplaceList", jSONObject, dataCallback);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_DietCloudApiImpl", LogAnonymous.b((Throwable) e));
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
    }

    @Override // com.huawei.health.suggestion.cloud.DietCloudApi
    public void getCustomFoods(int i, int i2, Set<String> set, DataCallback dataCallback) {
        if (dataCallback == null) {
            LogUtil.h("Suggestion_DietCloudApiImpl", "getCustomFoods() callback == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("iVersion", "1");
            jSONObject.put("pageNum", i);
            jSONObject.put(IAchieveDBMgr.PARAM_PAGE_SIZE, i2);
            if (koq.c(set)) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put("customFoodIds", jSONArray);
            }
            fix.a("/healthExpansion/food/getCustomFoods", jSONObject, dataCallback);
            LogUtil.a("Suggestion_DietCloudApiImpl", "getCustomFoods() params: " + jSONObject);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_DietCloudApiImpl", LogAnonymous.b((Throwable) e));
            dataCallback.onFailure(-5, ResultUtil.d(-5));
        }
    }
}
