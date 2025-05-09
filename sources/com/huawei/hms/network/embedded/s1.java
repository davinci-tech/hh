package com.huawei.hms.network.embedded;

import com.huawei.hms.network.base.common.trans.ByteString;

/* loaded from: classes9.dex */
public class s1 {
    public static final int BUFFER_SIZE = 8192;
    public static final int MAX_SIZE = 16777216;

    public static String key(String str) {
        if (str == null) {
            return null;
        }
        return ByteString.encodeUtf8(str).sha256().hex();
    }
}
