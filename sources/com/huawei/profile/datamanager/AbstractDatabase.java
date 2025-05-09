package com.huawei.profile.datamanager;

import com.huawei.profile.kv.DBEntity;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class AbstractDatabase {
    public abstract String get(String str, String str2);

    public abstract List<KvEntity> get(String str);

    public abstract boolean put(String str, DBEntity dBEntity);

    public abstract boolean remove(String str, String str2);

    public abstract boolean removeList(String str, List<String> list);

    public abstract boolean removeStartWithKey(String str, String str2);
}
