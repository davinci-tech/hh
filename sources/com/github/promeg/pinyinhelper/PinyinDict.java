package com.github.promeg.pinyinhelper;

import java.util.Set;

/* loaded from: classes2.dex */
public interface PinyinDict {
    String[] toPinyin(String str);

    Set<String> words();
}
