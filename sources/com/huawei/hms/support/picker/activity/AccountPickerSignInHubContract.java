package com.huawei.hms.support.picker.activity;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.hms.support.api.entity.hwid.AccountPickerSignInRequest;
import com.huawei.hwidauth.ui.b;
import org.json.JSONException;

/* loaded from: classes4.dex */
public interface AccountPickerSignInHubContract {

    public static abstract class Presenter extends b {
        public abstract void checkMinVersion();

        public abstract void doSilentTokenRequest();

        public abstract void doTokenRequest(String str);

        public abstract AccountPickerSignInRequest getSignInRequest();

        public abstract boolean initIntent(Intent intent);

        public abstract boolean isNeedRequestToken(String str);

        public abstract void onSaveInstanceState(Bundle bundle);

        public abstract void onSignInFailed(int i);

        public abstract void onSignInSuccess(String str) throws JSONException;

        public abstract void report(int i);

        public abstract void restoreInstanceState(Bundle bundle);

        public abstract void startWebViewActivity();
    }

    public interface View {
        void exit(int i, Intent intent);

        void startSignInActivity(Intent intent, int i);
    }
}
