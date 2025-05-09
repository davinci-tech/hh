package com.huawei.hms.feature.dynamic.e;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.common.util.Logger;
import com.huawei.hms.feature.dynamic.DynamicModule;

/* loaded from: classes4.dex */
public class d implements DynamicModule.VersionPolicy {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4518a = "d";

    @Override // com.huawei.hms.feature.dynamic.DynamicModule.VersionPolicy
    public Bundle getModuleInfo(Context context, String str) throws DynamicModule.LoadingException {
        Bundle remoteModuleInfo = DynamicModule.getRemoteModuleInfo(context, str);
        Bundle localModuleInfo = DynamicModule.getLocalModuleInfo(context, str);
        String str2 = f4518a;
        Logger.i(str2, "The version of remote module " + str + ":" + remoteModuleInfo.getInt("module_version"));
        Logger.i(str2, "The version of local module " + str + ":" + localModuleInfo.getInt("local_module_version"));
        if (remoteModuleInfo.getInt("module_version") >= localModuleInfo.getInt("local_module_version")) {
            Logger.i(str2, "Choose remote module info.");
            return remoteModuleInfo;
        }
        Logger.i(str2, "Choose local module info.");
        return localModuleInfo;
    }
}
