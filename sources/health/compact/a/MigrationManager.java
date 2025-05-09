package health.compact.a;

import android.content.Context;
import com.huawei.health.manager.migration.InterfaceMigration;
import com.huawei.health.manager.migration.MigrationAthenePreviewToAthene;
import com.huawei.health.manager.migration.MigrationAtheneThreeToAtheneFour;
import com.huawei.health.manager.migration.MigrationAtheneToAtheneTwo;
import com.huawei.health.manager.migration.MigrationAtheneTwoToAtheneThree;
import com.huawei.health.manager.migration.MigrationVersionOneToAthene;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.SharedPerferenceUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MigrationManager {
    private List<InterfaceMigration> e;

    private MigrationManager() {
        ArrayList arrayList = new ArrayList(10);
        this.e = arrayList;
        e(arrayList);
    }

    private static void e(List<InterfaceMigration> list) {
        list.add(new MigrationVersionOneToAthene());
        list.add(new MigrationAthenePreviewToAthene());
        list.add(new MigrationAtheneToAtheneTwo());
        list.add(new MigrationAtheneTwoToAtheneThree());
        list.add(new MigrationAtheneThreeToAtheneFour());
        list.add(new InterfaceMigration() { // from class: com.huawei.health.manager.migration.MigrationManager$1
            @Override // com.huawei.health.manager.migration.InterfaceMigration
            public boolean filter(String str) {
                return true;
            }

            @Override // com.huawei.health.manager.migration.InterfaceMigration
            public void migration(Context context) {
                if (context == null) {
                    LogUtil.a("Step_MigrationManager", "context is null");
                } else {
                    SharedPerferenceUtils.b(context, "Athene4");
                }
            }
        });
    }

    public static void e(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_MigrationManager", "context is null");
        } else {
            new MigrationManager().d(context, "Athene4");
            MigrationAtheneTwoToAtheneThree.b();
        }
    }

    private void d(Context context, String str) {
        String b = b(context);
        if (b == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_MigrationManager", "migrate version == null");
            return;
        }
        if (UserInfomation.BIRTHDAY_UNSETED.equals(b)) {
            SharedPerferenceUtils.b(context, str);
            return;
        }
        if (b.equals(str)) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_MigrationManager", "migrate begin from:", b);
        while (true) {
            String b2 = b(context);
            if (!b2.equals(str)) {
                Iterator<InterfaceMigration> it = this.e.iterator();
                while (true) {
                    if (it.hasNext()) {
                        InterfaceMigration next = it.next();
                        if (next.filter(b2)) {
                            next.migration(context);
                            break;
                        }
                    }
                }
            } else {
                com.huawei.hwlogsmodel.LogUtil.a("Step_MigrationManager", "migrate end at:", b2);
                return;
            }
        }
    }

    private String b(Context context) {
        String z = SharedPerferenceUtils.z(context);
        boolean z2 = false;
        if (UserInfomation.BIRTHDAY_UNSETED.equals(z) && context.getSharedPreferences("plugin_device_preferences_settings", 0).getString("plugin_device_last_time_and_this_time", null) != null) {
            z = "v1";
        }
        if (!UserInfomation.BIRTHDAY_UNSETED.equals(z)) {
            return z;
        }
        String[] x = SharedPerferenceUtils.x(context);
        if (x != null && (!x[0].equals("0") || !x[1].equals("0") || !x[2].equals("0"))) {
            z2 = true;
        }
        return (SharedPerferenceUtils.t(context) != null || z2) ? "v2_preview" : z;
    }
}
