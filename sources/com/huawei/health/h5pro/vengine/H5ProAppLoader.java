package com.huawei.health.h5pro.vengine;

/* loaded from: classes3.dex */
public interface H5ProAppLoader {

    public interface H5ProPreloadCbk {
        void onFailure(int i, String str);

        void onSuccess();
    }

    void execJs(String str, H5ProExecJsValueCbk h5ProExecJsValueCbk);

    void loadData(String str, String str2, String str3);

    void loadLightApp(String str);

    void loadMiniProgram(String str, String str2);

    void preloadLightApp(String str, H5ProPreloadCbk h5ProPreloadCbk);

    void preloadMiniProgram(String str, H5ProPreloadCbk h5ProPreloadCbk);
}
