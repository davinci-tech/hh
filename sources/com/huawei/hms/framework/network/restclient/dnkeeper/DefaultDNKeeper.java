package com.huawei.hms.framework.network.restclient.dnkeeper;

import android.content.Context;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.dns.dnresolver.dnkeeper.DNKeeper;

/* loaded from: classes9.dex */
public class DefaultDNKeeper implements DNKeeper {
    private static volatile DefaultDNKeeper instance;
    private Context context;

    private DefaultDNKeeper(Context context) {
        this.context = context;
    }

    public static DefaultDNKeeper getInstance(Context context) {
        CheckParamUtils.checkNotNull(context, "context == null");
        if (instance == null) {
            synchronized (DefaultDNKeeper.class) {
                if (instance == null) {
                    instance = new DefaultDNKeeper(context);
                }
            }
        }
        return instance;
    }
}
