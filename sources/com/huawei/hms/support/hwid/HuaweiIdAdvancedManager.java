package com.huawei.hms.support.hwid;

import android.content.Context;
import com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService;
import com.huawei.hms.support.hwid.service.b;

/* loaded from: classes4.dex */
public class HuaweiIdAdvancedManager {
    public static HuaweiIdAdvancedService getService(Context context) {
        return new b(context);
    }
}
