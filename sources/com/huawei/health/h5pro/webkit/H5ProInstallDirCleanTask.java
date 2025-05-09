package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.LogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class H5ProInstallDirCleanTask {

    /* renamed from: a, reason: collision with root package name */
    public ExecutorService f2464a = Executors.newSingleThreadExecutor();
    public WeakReference<Context> e;

    public class Cleaner implements Runnable {
        public String b;

        @Override // java.lang.Runnable
        public void run() {
            File[] listFiles;
            LogUtil.i(e(), "start.");
            Context context = (Context) H5ProInstallDirCleanTask.this.e.get();
            if (context == null) {
                LogUtil.w(e(), "context is null");
                return;
            }
            String rootDir = HpkManager.b.getRootDir(context);
            File file = new File(String.format(Locale.ENGLISH, "%s/%s-version.json", rootDir, this.b));
            if (file.exists()) {
                file.delete();
                LogUtil.i(e(), "exist old version.json, del.");
            }
            File file2 = new File(String.format(Locale.ENGLISH, "%s/%s.installed", rootDir, this.b));
            if (file2.exists()) {
                file2.delete();
                LogUtil.i(e(), "exist old installed file, del.");
            }
            File file3 = new File(HpkManager.b.getH5WorkPath(context, this.b));
            if (!file3.exists() || (listFiles = file3.listFiles()) == null || listFiles.length == 0) {
                return;
            }
            File file4 = new File(file3 + "/entry");
            if (file4.exists()) {
                LogUtil.i(e(), "exist old entry, del.");
                FileDownloadManager.deleteFiles(file4, true);
            }
            File file5 = new File(file3 + "/manifest.json");
            if (file5.exists()) {
                file5.delete();
            }
            String userFlag = EnvironmentHelper.getInstance().getUserFlag();
            if (TextUtils.isEmpty(userFlag)) {
                LogUtil.i(e(), "user flag is empty, return.");
                return;
            }
            File file6 = new File(HpkManager.b.getInitFilePath(context, this.b));
            if (file6.exists()) {
                String installDirNamePrefix = HpkManager.b.getInstallDirNamePrefix(String.valueOf(file6.lastModified()));
                String str = installDirNamePrefix + userFlag;
                for (File file7 : listFiles) {
                    if (!TextUtils.isEmpty(installDirNamePrefix) && !file7.getName().equals(str) && file7.getName().startsWith(installDirNamePrefix)) {
                        LogUtil.i(e(), "Switch to a new user, del the old installation dir.");
                        FileDownloadManager.deleteFiles(file7, true);
                        return;
                    }
                }
            }
        }

        private String e() {
            return LogUtil.getTag(this.b, "H5ProInstallDirCleanTask");
        }

        public Cleaner(String str) {
            this.b = str;
        }
    }

    public void execute(String str) {
        this.f2464a.submit(new Cleaner(str));
    }

    private Context b(Context context) {
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    public H5ProInstallDirCleanTask(Context context) {
        this.e = new WeakReference<>(b(context));
    }
}
