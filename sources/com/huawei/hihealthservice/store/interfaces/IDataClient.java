package com.huawei.hihealthservice.store.interfaces;

import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserInfo;
import java.util.List;

/* loaded from: classes7.dex */
public interface IDataClient {
    List<HiHealthClient> getAllHealthClientList(int i);

    List<HiHealthClient> getHealthClientList(String str, int i);

    List<HiHealthClient> getHealthClientListByTime(int i, HiTimeInterval hiTimeInterval, int i2);

    List<HiHealthClient> getHealthClientListByUserId(int i, HiDataSourceFetchOption hiDataSourceFetchOption);

    List<HiHealthClient> getHealthClientMergedListByTime(List<Integer> list, HiTimeInterval hiTimeInterval, int i);

    HiHealthClient saveDeviceInfo(HiDeviceInfo hiDeviceInfo, int i);

    HiHealthClient saveDeviceInfoWithUserInfo(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, int i);
}
