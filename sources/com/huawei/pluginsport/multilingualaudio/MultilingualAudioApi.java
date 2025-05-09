package com.huawei.pluginsport.multilingualaudio;

import defpackage.mwz;
import defpackage.mxh;
import java.util.List;

/* loaded from: classes6.dex */
public interface MultilingualAudioApi {
    List<String> getScenarioAudioPaths(String str, mwz mwzVar);

    void init(mxh mxhVar);

    default boolean isInitSuccess() {
        return false;
    }

    void release();
}
