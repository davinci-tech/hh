package com.huawei.threecircle.api;

import com.huawei.basefitnessadvice.callback.UiCallback;
import defpackage.njb;
import defpackage.nje;
import defpackage.njg;
import java.util.Map;

/* loaded from: classes6.dex */
public interface ThreeCircleApi {
    void checkUpdateForThreeCircle();

    String getPromptMessage(njg njgVar, Map<String, String> map);

    void queryEncourage(String str, int i, UiCallback<nje> uiCallback);

    njg queryRules(String str, String str2);

    void querySummary(String str, int i, UiCallback<njb> uiCallback);
}
