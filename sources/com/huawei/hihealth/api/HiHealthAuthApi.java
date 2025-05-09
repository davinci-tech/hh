package com.huawei.hihealth.api;

import android.content.Context;
import com.huawei.hihealth.HiAuthorizationOption;
import com.huawei.hihealth.data.listener.HiAuthorizationListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import java.util.List;

/* loaded from: classes.dex */
public class HiHealthAuthApi implements IAuthApi {
    private static Context b;

    /* renamed from: a, reason: collision with root package name */
    private HiHealthNativeApi f4055a;

    private HiHealthAuthApi() {
        this.f4055a = HiHealthNativeApi.a(b);
    }

    @Override // com.huawei.hihealth.api.IAuthApi
    public void requestAuthorization(HiAuthorizationOption hiAuthorizationOption, HiAuthorizationListener hiAuthorizationListener) {
        this.f4055a.requestAuthorization(hiAuthorizationOption, hiAuthorizationListener);
    }

    @Override // com.huawei.hihealth.api.IAuthApi
    public void subscribeHiHealthData(List<Integer> list, HiSubscribeListener hiSubscribeListener) {
        this.f4055a.subscribeHiHealthData(list, hiSubscribeListener);
    }

    @Override // com.huawei.hihealth.api.IAuthApi
    public void unSubscribeHiHealthData(List<Integer> list, HiUnSubscribeListener hiUnSubscribeListener) {
        this.f4055a.unSubscribeHiHealthData(list, hiUnSubscribeListener);
    }

    static class Instance {
        public static final HiHealthAuthApi b = new HiHealthAuthApi();

        private Instance() {
        }
    }
}
