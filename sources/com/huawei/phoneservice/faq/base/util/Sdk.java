package com.huawei.phoneservice.faq.base.util;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import com.huawei.phoneservice.faq.base.entity.Builder;

/* loaded from: classes5.dex */
public interface Sdk {
    int apply();

    String getMap();

    String getMapOnSaveInstance();

    String getSdk(String str);

    SdkListener getSdkListener();

    String getSdkVersion();

    int init(Activity activity, String str);

    int init(Application application, Uri uri, SdkListener sdkListener);

    int init(Application application, Builder builder, SdkListener sdkListener);

    int init(Application application, String str, SdkListener sdkListener);

    boolean init();

    boolean init(Application application);

    boolean initIsDone();

    void release();

    void saveMapOnSaveInstanceState(String str);

    Sdk saveSdk(String str, String str2);

    void setCallback(FaqBaseCallback faqBaseCallback);

    void setClickListener(SdkClickListener sdkClickListener);

    void setLanguage(String str);

    void setSdkListener(SdkListener sdkListener);

    void setUriFromFaq(Uri uri);
}
