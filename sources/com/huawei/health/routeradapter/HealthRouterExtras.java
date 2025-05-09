package com.huawei.health.routeradapter;

import android.content.Context;
import com.huawei.haf.router.facade.service.ExtrasFormatService;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class HealthRouterExtras implements ExtrasFormatService {
    public static boolean b(int i) {
        return (i & 2) == 2;
    }

    public static boolean c(int i) {
        return (i & 1) == 1;
    }

    public static boolean d(int i) {
        return (i & 3) == 3;
    }

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    public static List<String> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.huawei.health.h5.diet-diary");
        arrayList.add("com.huawei.health.h5.ai-weight");
        return arrayList;
    }

    @Override // com.huawei.haf.router.facade.service.ExtrasFormatService
    public String formatCustomString(int i) {
        StringBuilder sb = new StringBuilder(32);
        if (c(i)) {
            e(sb, "SPORT_TRACK_INIT");
        }
        if (b(i)) {
            e(sb, "CHECK_SYSTEM_VERSION");
        }
        if (d(i)) {
            e(sb, "SYNC_ALL_DEVICE_DATA");
        }
        return sb.toString();
    }

    @Override // com.huawei.haf.router.facade.service.ExtrasFormatService
    public int fromExtrasString(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1174394145) {
            if (str.equals("CHECK_SYSTEM_VERSION")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -29980527) {
            if (hashCode == 2067343727 && str.equals("SPORT_TRACK_INIT")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("SYNC_ALL_DEVICE_DATA")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            return 2;
        }
        if (c != 1) {
            return c != 2 ? 0 : 1;
        }
        return 3;
    }

    private static void e(StringBuilder sb, String str) {
        if (sb.length() > 0) {
            sb.append('|');
        }
        sb.append(str);
    }
}
