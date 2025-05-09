package com.huawei.thirdpartservice.komootapi;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.thirdpartservice.OauthStatusCallback;

/* loaded from: classes6.dex */
public interface KomootProviderApi {
    void getRoad(String str, ResultCallback<String> resultCallback);

    void getRoadList(String str, int i, ResultCallback<String> resultCallback);

    void isOauth(OauthStatusCallback oauthStatusCallback);

    void performOauth(Context context);
}
