package com.huawei.hms.feature.dynamic;

import android.os.Bundle;
import com.huawei.hms.common.util.Logger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes9.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4514a = "d";
    private static final d b = new d();
    public Set<String> c;

    public void a(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("installed_module_name");
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            Logger.w(f4514a, "Get installed module name failed.");
            this.c = new HashSet();
            return;
        }
        Logger.i(f4514a, "Installed module name:" + stringArrayList);
        this.c = new HashSet(stringArrayList);
    }

    public static d a() {
        return b;
    }

    private d() {
    }
}
