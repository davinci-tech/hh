package com.huawei.health.suggestion.data;

import com.huawei.basefitnessadvice.callback.UiCallback;
import defpackage.fit;
import defpackage.fiu;
import defpackage.fiy;
import defpackage.fiz;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public interface DietDataApi {
    void getCustomFoods(int i, int i2, Set<String> set, UiCallback<JSONObject> uiCallback);

    void getDietSummaryCloud(double d, double d2, int i, int i2, UiCallback<JSONObject> uiCallback);

    void getFoodRecommendDetail(String str, String str2, fit fitVar, UiCallback<fiy> uiCallback);

    void getReplaceList(String str, int i, List<String> list, UiCallback<List<fiu>> uiCallback);

    void saveReplaceFood(fiz fizVar, UiCallback<String> uiCallback);
}
