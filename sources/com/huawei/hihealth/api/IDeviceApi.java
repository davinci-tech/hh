package com.huawei.hihealth.api;

import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiRegisterClientListener;
import java.util.List;

/* loaded from: classes.dex */
public interface IDeviceApi {
    void fetchDataClientByUniqueId(int i, String str, HiDataClientListener hiDataClientListener);

    void fetchDataSourceByType(int i, HiTimeInterval hiTimeInterval, HiDataClientListener hiDataClientListener);

    void registerDataClient(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, List<Integer> list, HiRegisterClientListener hiRegisterClientListener);

    void registerDataClient(HiDeviceInfo hiDeviceInfo, List<Integer> list, HiRegisterClientListener hiRegisterClientListener);
}
