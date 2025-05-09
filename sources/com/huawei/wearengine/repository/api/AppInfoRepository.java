package com.huawei.wearengine.repository.api;

import com.huawei.wearengine.auth.HiAppInfo;
import java.util.List;

/* loaded from: classes9.dex */
public interface AppInfoRepository {
    boolean deleteAppInfo(String str);

    List<HiAppInfo> getAllHiAppInfo();

    HiAppInfo getHiAppInfo(String str);

    boolean insertAppInfo(HiAppInfo hiAppInfo);
}
