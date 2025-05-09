package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils;

import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class SequenceDetailFieldConfig {
    public static final String CHAR = "char";
    public static final String DOUBLE = "double";
    public static final String FLOAT = "float";
    public static final String STRING = "string";
    public static final String UINT16 = "uint16";
    public static final String UINT32 = "uint32";
    public static final String UINT64 = "uint64";
    public static final String UINT8 = "uint8";
    private static final Map<String, String> VERSION_CONFIG_MAP = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig.1
        private static final long serialVersionUID = -941389467210366107L;

        {
            put(AdventuresConfig.ADVENTURES_VERSION1_KEY, "tp=mk|no:2:uint16;typ:1:uint8;c:1:uint8;t:8:uint64;lng:8:double;lat:8:double;m:1:uint8");
        }
    });

    private SequenceDetailFieldConfig() {
    }

    static class AdventuresConfig {
        private static final String ADVENTURES_ID;
        private static final String ADVENTURES_VERSION1 = "tp=mk|no:2:uint16;typ:1:uint8;c:1:uint8;t:8:uint64;lng:8:double;lat:8:double;m:1:uint8";
        private static final String ADVENTURES_VERSION1_KEY;

        private AdventuresConfig() {
        }

        static {
            String valueOf = String.valueOf(DicDataTypeUtil.DataType.MARK_POINT.value());
            ADVENTURES_ID = valueOf;
            ADVENTURES_VERSION1_KEY = valueOf + "1";
        }
    }

    public static String getConfig(String str, String str2) {
        return VERSION_CONFIG_MAP.get(str + str2);
    }
}
