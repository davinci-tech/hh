package com.huawei.health.manager.migration;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPerferenceUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MigrationAtheneTwoToAtheneThree implements InterfaceMigration {
    private List<FileDeleteList> c = new ArrayList(5);

    static class FileDeleteList {

        /* renamed from: a, reason: collision with root package name */
        String[] f2790a;
        File d;

        FileDeleteList(File file, String[] strArr) {
            this.d = file;
            this.f2790a = strArr;
        }
    }

    public MigrationAtheneTwoToAtheneThree() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(File.separator);
        sb.append("sdcard");
        sb.append(File.separator);
        sb.append("huawei");
        sb.append(File.separator);
        sb.append("com.huawei.health");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder(16);
        sb3.append(sb2);
        sb3.append(File.separator);
        sb3.append("com.huawei.health");
        String sb4 = sb3.toString();
        this.c.add(new FileDeleteList(new File(sb4), new String[]{"log.0", "log.1", "log.2", "log.3", "log.4"}));
        StringBuilder sb5 = new StringBuilder(16);
        sb5.append(sb2);
        sb5.append(File.separator);
        sb5.append("com.huawei.health_DaemonService");
        String sb6 = sb5.toString();
        this.c.add(new FileDeleteList(new File(sb6), new String[]{"log.0", "log.1", "log.2", "log.3", "log.4"}));
        StringBuilder sb7 = new StringBuilder(16);
        sb7.append(sb2);
        sb7.append(File.separator);
        sb7.append("com.huawei.health_leakcanary");
        String sb8 = sb7.toString();
        this.c.add(new FileDeleteList(new File(sb8), new String[]{"log.0", "log.1", "log.2", "log.3", "log.4"}));
        StringBuilder sb9 = new StringBuilder(16);
        sb9.append(sb2);
        sb9.append(File.separator);
        sb9.append("com.huawei.health_pushservice");
        String sb10 = sb9.toString();
        this.c.add(new FileDeleteList(new File(sb10), new String[]{"log.0", "log.1", "log.2", "log.3", "log.4"}));
        this.c.add(new FileDeleteList(new File(sb2), new String[]{"huawei_crashLog_0.txt", "huawei_crashLog_1.txt", "huawei_crashLog_2.txt", "leak.txt"}));
    }

    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public boolean filter(String str) {
        return "Athene2".equals(str);
    }

    @Override // com.huawei.health.manager.migration.InterfaceMigration
    public void migration(Context context) {
        if (context == null) {
            LogUtil.a("Step_MigrationAtheneTwoToAtheneThree", "context is null");
        } else {
            SharedPerferenceUtils.b(context, "Athene3");
        }
    }

    public static void b() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.manager.migration.MigrationAtheneTwoToAtheneThree.1
            @Override // java.lang.Runnable
            public void run() {
                if (new File(File.separator + "sdcard" + File.separator + "huawei" + File.separator + "com.huawei.health").exists()) {
                    new MigrationAtheneTwoToAtheneThree().a();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Iterator<FileDeleteList> it = this.c.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    private void e(FileDeleteList fileDeleteList) {
        if (fileDeleteList == null || fileDeleteList.d == null) {
            return;
        }
        LogUtil.c("Step_MigrationAtheneTwoToAtheneThree", "check file");
        if (fileDeleteList.d.exists()) {
            LogUtil.c("Step_MigrationAtheneTwoToAtheneThree", "file exist");
            for (int i = 0; i < fileDeleteList.f2790a.length; i++) {
                File file = new File(fileDeleteList.d + File.separator + fileDeleteList.f2790a[i]);
                if (file.exists() && !file.delete()) {
                    LogUtil.h("Step_MigrationAtheneTwoToAtheneThree", "item delete failed");
                }
            }
            String[] list = fileDeleteList.d.list();
            if (list == null) {
                LogUtil.h("Step_MigrationAtheneTwoToAtheneThree", "fileDeleteList.mDirectory.list ret null");
                return;
            }
            for (String str : list) {
                LogUtil.h("Step_MigrationAtheneTwoToAtheneThree", "exist:", str);
            }
            if (list.length != 0 || fileDeleteList.d.delete()) {
                return;
            }
            LogUtil.h("Step_MigrationAtheneTwoToAtheneThree", "file delete failed");
        }
    }
}
