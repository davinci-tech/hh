package com.huawei.watchface.videoedit.utils;

import java.util.HashSet;
import java.util.UUID;

/* loaded from: classes9.dex */
public class UuidManager {
    private HashSet<String> mUsedIdSet = new HashSet<>();

    public String generateNewId() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (this.mUsedIdSet.contains(uuid));
        this.mUsedIdSet.add(uuid);
        return uuid;
    }

    public String generateNewIdWithPrefix(String str) {
        String concat;
        if (str == null) {
            str = "";
        }
        do {
            concat = str.concat(UUID.randomUUID().toString());
        } while (this.mUsedIdSet.contains(concat));
        this.mUsedIdSet.add(concat);
        return concat;
    }
}
