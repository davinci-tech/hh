package com.huawei.hihealth.util;

import android.text.TextUtils;
import com.huawei.hihealth.api.SyncApi;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HealthSyncUtil {
    private static final List<SyncApi> c = new ArrayList();

    private HealthSyncUtil() {
    }

    public static boolean b(SyncApi syncApi) {
        boolean add;
        if (syncApi == null) {
            throw new IllegalArgumentException("add api is null");
        }
        List<SyncApi> list = c;
        synchronized (list) {
            if (list.contains(syncApi)) {
                throw new IllegalArgumentException("api " + syncApi.getClass().getSimpleName() + " existed");
            }
            add = list.add(syncApi);
        }
        return add;
    }

    public static void b(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("label is invalid");
        }
        synchronized (c) {
            i = -1;
            int i2 = 0;
            while (true) {
                List<SyncApi> list = c;
                if (i2 >= list.size()) {
                    break;
                }
                SyncApi syncApi = list.get(i2);
                if (str.equals(syncApi.getLabel())) {
                    syncApi.syncCloud();
                    i = i2;
                }
                i2++;
            }
        }
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException("label " + str + " not existed");
    }

    public static void a(String str, boolean z, Object obj) {
        boolean z2;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("label is invalid");
        }
        List<SyncApi> list = c;
        synchronized (list) {
            z2 = false;
            for (SyncApi syncApi : list) {
                if (str.equals(syncApi.getLabel())) {
                    syncApi.cleanCloud(z, obj);
                    z2 = true;
                }
            }
        }
        if (z2) {
            return;
        }
        throw new IllegalArgumentException("label " + str + " not existed");
    }

    public static boolean a(SyncApi syncApi) {
        boolean remove;
        if (syncApi == null) {
            throw new IllegalArgumentException("removeSyncModule api is null");
        }
        List<SyncApi> list = c;
        synchronized (list) {
            remove = list.remove(syncApi);
        }
        return remove;
    }
}
