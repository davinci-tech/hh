package com.huawei.hihealth.api;

import android.content.Context;
import com.huawei.hihealth.data.listener.HiCommonListener;

/* loaded from: classes.dex */
public class HiHealthUserInfoApi implements UserInfoApi {
    private static Context d;
    private HiHealthNativeApi e;

    private HiHealthUserInfoApi() {
        this.e = HiHealthNativeApi.a(d);
    }

    @Override // com.huawei.hihealth.api.UserInfoApi
    public void fetchUserData(HiCommonListener hiCommonListener) {
        this.e.fetchUserData(hiCommonListener);
    }

    static class Instance {
        public static final HiHealthUserInfoApi e = new HiHealthUserInfoApi();

        private Instance() {
        }
    }
}
