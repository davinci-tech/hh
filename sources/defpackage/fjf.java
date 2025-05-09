package defpackage;

import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.suggestion.data.DietDataApi;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class fjf implements DietDataApi {
    @Override // com.huawei.health.suggestion.data.DietDataApi
    public void getFoodRecommendDetail(String str, String str2, fit fitVar, final UiCallback<fiy> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DietDataImpl", "getFoodRecommendDetail, Callback<Recipes> == null");
        } else {
            LogUtil.c("Suggestion_DietDataImpl", "getFoodRecommendDetail planId = ", str, " ,recipesDate = ", str2);
            fiw.d().getFoodRecommendDetailCloud(str, str2, fitVar, new DataCallback() { // from class: fjf.1
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str3) {
                    ReleaseLogUtil.c("Suggestion_DietDataImpl", "getFoodRecommendDetail() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str3);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(-1, "data == null");
                        return;
                    }
                    fiy a2 = fix.a(jSONObject);
                    LogUtil.a("Suggestion_DietDataImpl", "getFoodRecommendDetail recipes :" + a2);
                    uiCallback.onSuccess(a2);
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.data.DietDataApi
    public void saveReplaceFood(fiz fizVar, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DietDataImpl", "saveReplaceFood, Callback<String> == null");
        } else {
            LogUtil.c("Suggestion_DietDataImpl", "saveReplaceFood planId = ", fizVar.b(), " ,recipesDate = ", fizVar.c());
            fiw.d().saveReplaceFoodCloud(fizVar, new DataCallback() { // from class: fjf.3
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DietDataImpl", "saveReplaceFood() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(-1, "data == null");
                    } else {
                        uiCallback.onSuccess(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.data.DietDataApi
    public void getDietSummaryCloud(double d, double d2, int i, int i2, final UiCallback<JSONObject> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DietDataImpl", "getDietSummaryCloud, Callback<FunctionAuthResult> == null");
        } else {
            LogUtil.a("Suggestion_DietDataImpl", "getDietSummaryCloud :", Double.valueOf(d), ",", Double.valueOf(d2), ",", Integer.valueOf(i), ",", Integer.valueOf(i2));
            fiw.d().getDietSummaryCloud(d, d2, i, i2, new DataCallback() { // from class: fjf.5
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i3, String str) {
                    ReleaseLogUtil.c("Suggestion_DietDataImpl", "getDietSummaryCloud() errorCode=", Integer.valueOf(i3));
                    uiCallback.onFailure(i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(-1, "data == null");
                        return;
                    }
                    LogUtil.a("Suggestion_DietDataImpl", "data = " + jSONObject);
                    uiCallback.onSuccess(jSONObject);
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.data.DietDataApi
    public void getReplaceList(String str, int i, List<String> list, final UiCallback<List<fiu>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DietDataImpl", "getFoodRecommendDetail, Callback<Recipes> == null");
        } else {
            fiw.d().getReplaceList(str, i, list, new DataCallback() { // from class: fjf.2
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.c("Suggestion_DietDataImpl", "getReplaceList() errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(i2, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        JSONArray jSONArray = jSONObject.getJSONArray("replacementFoods");
                        int length = jSONArray.length();
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < length; i2++) {
                            arrayList.add((fiu) HiJsonUtil.e(((JSONObject) jSONArray.get(i2)).toString(), fiu.class));
                        }
                        LogUtil.a("Suggestion_DietDataImpl", "getReplaceList onSuccess。");
                        uiCallback.onSuccess(arrayList);
                    } catch (JSONException e) {
                        LogUtil.b("Suggestion_DietDataImpl", "getReplaceList() errorCode=", e);
                        uiCallback.onFailure(-1, e.toString());
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.data.DietDataApi
    public void getCustomFoods(int i, int i2, Set<String> set, final UiCallback<JSONObject> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DietDataImpl", "getCustomFoods() callback == null");
        } else {
            LogUtil.a("Suggestion_DietDataImpl", "getCustomFoods() ", Integer.valueOf(i), ", ", Integer.valueOf(i2), ", ", set);
            fiw.d().getCustomFoods(i, i2, set, new DataCallback() { // from class: fjf.4
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i3, String str) {
                    ReleaseLogUtil.c("Suggestion_DietDataImpl", "getCustomFoods() errorCode=", Integer.valueOf(i3));
                    uiCallback.onFailure(i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(-1, "getCustomFoods() data == null");
                        return;
                    }
                    LogUtil.a("Suggestion_DietDataImpl", "getCustomFoods() data = " + jSONObject);
                    uiCallback.onSuccess(jSONObject);
                }
            });
        }
    }
}
