package com.huawei.pluginhealthalgorithm;

import android.content.Context;
import android.graphics.Bitmap;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;

/* loaded from: classes8.dex */
public interface NutritionTableOcrApi {
    String getNutritionTableOcrResult(Context context, Bitmap bitmap);

    Class<? extends JsModuleBase> getOcrJsApi(String str);

    void release();
}
