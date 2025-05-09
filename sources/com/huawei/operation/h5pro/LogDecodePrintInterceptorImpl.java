package com.huawei.operation.h5pro;

import android.text.TextUtils;
import com.huawei.health.h5pro.utils.LogPrintOperate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class LogDecodePrintInterceptorImpl implements LogPrintOperate.LogDecodePrintInterceptor {
    private static final List<String> DECODE_PRINT_VERIFY_CONTAINER_HPK;

    static {
        ArrayList arrayList = new ArrayList();
        DECODE_PRINT_VERIFY_CONTAINER_HPK = arrayList;
        arrayList.add("com.huawei.health.h5.health-headlines");
    }

    @Override // com.huawei.health.h5pro.utils.LogPrintOperate.LogDecodePrintInterceptor
    public boolean isDecodePrint(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return DECODE_PRINT_VERIFY_CONTAINER_HPK.contains(str);
    }
}
