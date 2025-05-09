package com.huawei.operation.h5pro.prerequest;

import com.huawei.health.h5pro.load.expression.ExpressionFunction;
import com.huawei.health.h5pro.utils.LogUtil;
import health.compact.a.Utils;

/* loaded from: classes.dex */
public class CloudFunctions {
    private static final String TAG = "H5PRO_CloudFunctions";

    @ExpressionFunction(alias = "getWeekStartDay")
    public int getWeekStartDay() {
        LogUtil.i(TAG, "getWeekStartDay");
        return Utils.o() ? 7 : 1;
    }
}
