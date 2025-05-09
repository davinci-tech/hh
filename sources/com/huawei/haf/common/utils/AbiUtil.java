package com.huawei.haf.common.utils;

import android.os.Build;
import com.huawei.haf.common.exception.HafRuntimeException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class AbiUtil {
    private AbiUtil() {
    }

    public static String b(List<String> list) {
        List<String> c = c();
        if (list == null && !c.isEmpty()) {
            return c.get(0);
        }
        for (String str : c) {
            if (list.contains(str)) {
                return str;
            }
        }
        throw new HafRuntimeException("No supported abi for this device.");
    }

    private static List<String> c() {
        return Arrays.asList(Build.SUPPORTED_ABIS);
    }
}
