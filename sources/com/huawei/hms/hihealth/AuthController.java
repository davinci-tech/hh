package com.huawei.hms.hihealth;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.health.aacg;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import java.util.List;

/* loaded from: classes4.dex */
public class AuthController {
    private aacg aab;

    public Task<List<String>> queryAuthInfoByPkgName(String str) {
        return this.aab.aaba(str);
    }

    public Task<List<String>> queryAuthInfoByAppId(String str) {
        return this.aab.aab(str);
    }

    public Task<String> checkFingerprint(String str, String str2) {
        return this.aab.aab(str, str2);
    }

    @Deprecated
    public AuthController(AuthHuaweiId authHuaweiId) {
        this.aab = null;
        this.aab = aacg.aab();
    }

    AuthController() {
        this.aab = null;
        this.aab = aacg.aab();
    }
}
