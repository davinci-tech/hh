package com.huawei.hihealth.dictionary.config;

import android.content.Context;
import com.huawei.hihealth.dictionary.model.HiHealthDictionary;

/* loaded from: classes.dex */
public interface ConfigureLoader {
    String getName();

    HiHealthDictionary loadConfig(Context context);
}
