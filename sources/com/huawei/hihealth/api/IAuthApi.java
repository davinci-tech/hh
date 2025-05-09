package com.huawei.hihealth.api;

import com.huawei.hihealth.HiAuthorizationOption;
import com.huawei.hihealth.data.listener.HiAuthorizationListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import java.util.List;

/* loaded from: classes.dex */
interface IAuthApi {
    void requestAuthorization(HiAuthorizationOption hiAuthorizationOption, HiAuthorizationListener hiAuthorizationListener);

    void subscribeHiHealthData(List<Integer> list, HiSubscribeListener hiSubscribeListener);

    void unSubscribeHiHealthData(List<Integer> list, HiUnSubscribeListener hiUnSubscribeListener);
}
