package com.huawei.hms.network.inner;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.EmuiUtil;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsApp;

/* loaded from: classes9.dex */
public class DomainManager {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5644a = "DomainManager";
    public static volatile DomainManager b;

    public boolean isHwPhoneAndInChina() {
        if (!EmuiUtil.isHuaweiDevice() || !GrsApp.getInstance().getIssueCountryCode(ContextHolder.getAppContext()).equals("CN")) {
            return false;
        }
        Logger.v(f5644a, "huawei device and in china");
        return true;
    }

    public boolean isHaDomain(String str) {
        if (str == null) {
            return false;
        }
        if ((!str.contains("metrics") || !str.contains("dbankcloud")) && (!str.contains("metrics") || !str.contains("hicloud"))) {
            return false;
        }
        Logger.v(f5644a, "ha domain");
        return true;
    }

    public boolean isExcludePrefetch(String str) {
        return !isHwPhoneAndInChina() || isHaDomain(str);
    }

    public static DomainManager getInstance() {
        if (b == null) {
            synchronized (DomainManager.class) {
                if (b == null) {
                    b = new DomainManager();
                }
            }
        }
        return b;
    }
}
