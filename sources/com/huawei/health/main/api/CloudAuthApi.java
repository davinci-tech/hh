package com.huawei.health.main.api;

import android.content.Context;
import com.huawei.health.main.model.AppLangItem;
import defpackage.ehp;
import java.util.List;

/* loaded from: classes3.dex */
public interface CloudAuthApi {
    void constructInstance(Context context, String str);

    List<AppLangItem> getAppLangItem(String str, String str2, String str3);

    String getAppLangItemUrl(String str, String str2);

    String getLang();

    ehp getScopeLangItem(String str, String str2, String str3);

    String getScopeLangItemUrl(String str, String str2);
}
