package com.huawei.hihealthservice.auth;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ijt;
import defpackage.iko;
import health.compact.a.HiCommonUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HiAuthManager implements IAuth {
    private static final String LOG_TAG = "Debug_HiAuthManager";
    private ijt mRawQueryManager;

    private HiAuthManager() {
        this.mRawQueryManager = ijt.b();
    }

    static class Instance {
        private static final HiAuthManager INSTANCE = new HiAuthManager();

        private Instance() {
        }
    }

    public static HiAuthManager getInstance() {
        return Instance.INSTANCE;
    }

    @Override // com.huawei.hihealthservice.auth.IAuth
    public void checkInsertAuth(int i, int i2, List<HiHealthData> list) throws HiAuthException {
        boolean d;
        List<String> d2 = HiScopeUtil.d(list);
        LogUtil.c(LOG_TAG, "checkInsertAuth permissions are ", d2);
        if (HiCommonUtil.d(d2)) {
            LogUtil.h(LOG_TAG, "checkInsertAuth permissions is null");
            return;
        }
        for (String str : d2) {
            if (iko.a().a(i, i2) != null) {
                d = iko.a().e(i, i2, str);
                LogUtil.c(LOG_TAG, "checkInsertAuth permissions from cache, isExist = ", Boolean.valueOf(d));
            } else {
                d = this.mRawQueryManager.d(i, i2, 1, str);
                LogUtil.c(LOG_TAG, "checkInsertAuth permissions from database, isExist = ", Boolean.valueOf(d));
            }
            if (!d) {
                LogUtil.h(LOG_TAG, "checkInsertAuth permissions is not exist, permissions = ", d2);
                throw new HiAuthException("checkInsertAuth fail not exist permission : " + str);
            }
        }
    }

    @Override // com.huawei.hihealthservice.auth.IAuth
    public void checkReadAuth(int i, int i2, int[] iArr) throws HiAuthException {
        List<String> b = HiScopeUtil.b(iArr);
        List<String> a2 = HiScopeUtil.a(iArr);
        LogUtil.c(LOG_TAG, "checkReadAuth permissions are ", b, " or ", a2);
        if (HiCommonUtil.d(b) && HiCommonUtil.d(a2)) {
            LogUtil.h(LOG_TAG, "checkReadAuth permissions is null");
            return;
        }
        boolean isNotExist = isNotExist(i, i2, b);
        boolean isNotExist2 = isNotExist(i, i2, a2);
        if (isNotExist && isNotExist2) {
            LogUtil.h(LOG_TAG, "checkReadAuth permissions is not exist, permissions = ", b);
            throw new HiAuthException("checkReadAuth fail not read & write permission ");
        }
    }

    private boolean isNotExist(int i, int i2, List<String> list) throws HiAuthException {
        boolean d;
        if (list == null) {
            LogUtil.h(LOG_TAG, "checkReadAuth permissions is null");
            return false;
        }
        for (String str : list) {
            if (iko.a().a(i, i2) != null) {
                d = iko.a().e(i, i2, str);
                LogUtil.c(LOG_TAG, "checkReadAuth permissions from cache, isExist = ", Boolean.valueOf(d));
            } else {
                d = this.mRawQueryManager.d(i, i2, 1, str);
                LogUtil.c(LOG_TAG, "checkReadAuth permissions from database, isExist = ", Boolean.valueOf(d));
            }
            if (!d) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.hihealthservice.auth.IAuth
    public void checkDeleteAuth(int i, int i2, int[] iArr) throws HiAuthException {
        boolean d;
        List<String> e = HiScopeUtil.e(iArr);
        LogUtil.c(LOG_TAG, "checkDeleteAuth permissions are ", e);
        if (HiCommonUtil.d(e)) {
            LogUtil.h(LOG_TAG, "checkDeleteAuth permissions is null");
            return;
        }
        for (String str : e) {
            if (iko.a().a(i, i2) != null) {
                d = iko.a().e(i, i2, str);
                LogUtil.c(LOG_TAG, "checkDeleteAuth permissions from cache, isExist = ", Boolean.valueOf(d));
            } else {
                d = this.mRawQueryManager.d(i, i2, 1, str);
                LogUtil.c(LOG_TAG, "checkDeleteAuth permissions from database, isExist = ", Boolean.valueOf(d));
            }
            if (!d) {
                LogUtil.h(LOG_TAG, "checkDeleteAuth permissions is not exist, permissions = ", e);
                throw new HiAuthException("checkDeleteAuth fail not exist permission : " + str);
            }
        }
    }
}
