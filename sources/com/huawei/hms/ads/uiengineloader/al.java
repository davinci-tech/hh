package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/* loaded from: classes4.dex */
public final class al implements am {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4376a = "PreHiOrDecompress";

    @Override // com.huawei.hms.ads.uiengineloader.am
    public final u a(Context context, Bundle bundle) throws com.huawei.hms.ads.dynamicloader.j {
        String string = bundle.getString("module_name");
        af.b(f4376a, "getLoadingStrategy");
        try {
            int a2 = t.a(context, string);
            int i = s.a(context, string).d;
            af.b(f4376a, "3 module_name:" + string + ", hmsModuleVersion:" + a2 + ", assetModuleVersion:0, decompressedModuleVersion:" + i);
            if (a2 > 0 && a2 > i) {
                af.b(f4376a, "Choose the HMSLoadStrategy");
                return new t();
            }
            if (i > 0) {
                af.b(f4376a, "Choose the DecompressLoadStrategy");
                return new s();
            }
            af.d(f4376a, "PreferHighestOrRemote: Cannot find a valid module version.");
            return null;
        } catch (com.huawei.hms.ads.dynamicloader.j e) {
            throw e;
        } catch (Throwable th) {
            Log.w(f4376a, "getLoadingStrategy other exception.".concat(String.valueOf(th)));
            return null;
        }
    }
}
