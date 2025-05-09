package com.huawei.agconnect.abtest.a;

import android.content.Context;
import android.os.Bundle;
import com.huawei.agconnect.common.api.HaBridge;
import com.huawei.agconnect.common.api.Logger;

/* loaded from: classes8.dex */
public class b {
    public void a(Context context) {
    }

    public void a(String str, Bundle bundle, Bundle bundle2) {
        boolean z = false;
        for (String str2 : d.c()) {
            for (a aVar : d.a(str2)) {
                if (aVar.c() != null && !aVar.d() && aVar.c().equals(str)) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("$ABTaskId", aVar.a());
                    bundle3.putString("$ABVarId", aVar.b());
                    bundle3.putString("$ABChannel", str2);
                    Logger.d("ABTest", "report trigger ab test event");
                    new HaBridge(HaBridge.HA_SERVICE_TAG_ABTEST).onEvent("$JoinABTask", bundle3);
                    z = true;
                    aVar.a(true);
                }
            }
        }
        if (z) {
            d.b();
        }
    }
}
