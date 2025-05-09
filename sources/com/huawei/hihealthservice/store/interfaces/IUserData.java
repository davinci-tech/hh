package com.huawei.hihealthservice.store.interfaces;

import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiUserInfo;
import defpackage.ikv;
import java.util.List;

/* loaded from: classes7.dex */
public interface IUserData {
    HiAccountInfo fetchAccountInfo(ikv ikvVar);

    List<HiUserInfo> fetchUserData(int i);

    List<HiGoalInfo> getGoalInfo(int i, int i2, int i3);

    boolean hiLogin(HiAccountInfo hiAccountInfo, ikv ikvVar);

    int hiLogout(ikv ikvVar);

    boolean setGoalInfo(int i, int i2, HiGoalInfo hiGoalInfo);

    long setUserData(HiUserInfo hiUserInfo, ikv ikvVar);
}
