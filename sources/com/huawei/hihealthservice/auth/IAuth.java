package com.huawei.hihealthservice.auth;

import com.huawei.hihealth.HiHealthData;
import java.util.List;

/* loaded from: classes7.dex */
interface IAuth {
    void checkDeleteAuth(int i, int i2, int[] iArr) throws HiAuthException;

    void checkInsertAuth(int i, int i2, List<HiHealthData> list) throws HiAuthException;

    void checkReadAuth(int i, int i2, int[] iArr) throws HiAuthException;
}
