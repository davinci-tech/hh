package com.huawei.hms.support.feature.result;

import com.huawei.hms.common.api.CommonStatusCodes;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public abstract class AbstractAuthPickerResultCode extends CommonStatusCodes {
    public static final int ACCOUNT_PICKER_INNER_EXCEPTION = 2015;
    private static final Map<Integer, String> HUAWEIID_AUTH_RESULT_DESC_MAP;
    public static final int RESULT_CODE_CANCELLED = 2012;
    public static final int RESULT_CODE_EXECUTING = 2013;
    public static final int RESULT_CODE_FAILED = 2014;

    static {
        HashMap hashMap = new HashMap();
        HUAWEIID_AUTH_RESULT_DESC_MAP = hashMap;
        hashMap.put(2012, "Auth canceled by subscriber");
        hashMap.put(2013, "Auth executing");
        hashMap.put(2014, "An unrecoverable auth failure occurred");
    }
}
