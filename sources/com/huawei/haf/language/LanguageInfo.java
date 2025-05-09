package com.huawei.haf.language;

import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public interface LanguageInfo {
    List<String> getAllLanguageUuid(boolean z);

    String getLanguageName(String str, Locale locale);

    String getLanguageUuid(Locale locale);

    boolean isCloudPresetLanguage(String str);

    boolean isCloudSecondLanguage(String str);

    boolean isStorePresetLanguage(String str);
}
