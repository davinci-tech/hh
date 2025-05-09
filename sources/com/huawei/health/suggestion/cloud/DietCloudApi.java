package com.huawei.health.suggestion.cloud;

import com.huawei.basefitnessadvice.callback.DataCallback;
import defpackage.fit;
import defpackage.fiz;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public interface DietCloudApi {
    void batchGetFood(Set<String> set, DataCallback dataCallback);

    void getCustomFoods(int i, int i2, Set<String> set, DataCallback dataCallback);

    void getDietSummaryCloud(double d, double d2, int i, int i2, DataCallback dataCallback);

    void getFoodRecommendDetailCloud(String str, String str2, fit fitVar, DataCallback dataCallback);

    void getReplaceList(String str, int i, List<String> list, DataCallback dataCallback);

    void saveReplaceFoodCloud(fiz fizVar, DataCallback dataCallback);
}
