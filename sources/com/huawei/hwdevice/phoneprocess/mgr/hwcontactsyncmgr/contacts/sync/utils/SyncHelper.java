package com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils;

import java.util.UUID;

/* loaded from: classes5.dex */
public class SyncHelper {

    public enum Encode {
        NORMAL,
        ENCODE
    }

    public enum Transferred {
        NORMAL,
        TRANSFERRED
    }

    public static String a() {
        String uuid = UUID.randomUUID().toString();
        if (uuid.length() <= 24) {
            return "";
        }
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
}
