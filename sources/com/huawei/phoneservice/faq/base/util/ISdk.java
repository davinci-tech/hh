package com.huawei.phoneservice.faq.base.util;

import android.app.Application;
import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.entity.ModuleConfigRequest;

/* loaded from: classes5.dex */
public interface ISdk {
    void canceledSubmit(Context context);

    boolean clearDownloadFile(Application application);

    FaqBaseCallback getCallback();

    void getModuleList();

    int getSdkInitCode();

    void getServiceUrl();

    boolean hadAddress();

    boolean haveSdkErr(String str);

    void onClick(String str, String str2, Object obj);

    void onSdkErr(String str, String str2);

    void onSdkInit(int i, String str);

    Submit queryModuleList(Context context, ModuleConfigRequest moduleConfigRequest, Callback callback);

    void registerUpdateListener(SdkUpdateListener sdkUpdateListener);

    void showReleaseLog(boolean z);

    void unregisterUpdateListener(SdkUpdateListener sdkUpdateListener);
}
