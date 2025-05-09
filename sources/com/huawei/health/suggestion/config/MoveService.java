package com.huawei.health.suggestion.config;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ash;
import defpackage.ggo;
import defpackage.gic;
import defpackage.sqq;
import defpackage.squ;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.IOException;

/* loaded from: classes4.dex */
public class MoveService extends IntentService {
    private boolean b;
    private boolean c;
    private boolean d;

    public MoveService() {
        super("MoveService");
        this.c = false;
        this.d = false;
        this.b = false;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        LogUtil.c("Suggestion_MoveService", "onCreate()");
        super.onCreate();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onStart(Intent intent, int i) {
        LogUtil.a("Suggestion_MoveService", "onStart(intent, startId), mIsCopyDoing = ", Boolean.valueOf(d()), ", mIsDeleteDoing = ", Boolean.valueOf(a()), ", mIsCopyCancel = ", Boolean.valueOf(e()));
        if (intent == null) {
            LogUtil.h("Suggestion_MoveService", "onStart intent == null");
            return;
        }
        int intExtra = intent.getIntExtra("TYPE_KEY", 0);
        LogUtil.c("Suggestion_MoveService", "onStart(intent, startId)  typeValue = ", Integer.valueOf(intExtra));
        if (intExtra == 1) {
            if (d() || a()) {
                return;
            } else {
                b(true);
            }
        } else if (intExtra != 2) {
            LogUtil.h("Suggestion_MoveService", "onStart else typeValue = ", Integer.valueOf(intExtra));
            return;
        } else {
            if (a()) {
                return;
            }
            if (d()) {
                c(true);
            }
            a(true);
        }
        super.onStart(intent, i);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        LogUtil.a("Suggestion_MoveService", "onHandleIntent(Intent intent), mIsCopyDoing = ", Boolean.valueOf(d()), ", mIsDeleteDoing = ", Boolean.valueOf(a()), ", mIsCopyCancel = ", Boolean.valueOf(e()));
        if (intent == null) {
            LogUtil.h("Suggestion_MoveService", "onHandleIntent(Intent intent) intent == null");
            return;
        }
        int intExtra = intent.getIntExtra("TYPE_KEY", 0);
        LogUtil.c("Suggestion_MoveService", "onHandleIntent(Intent intent)  typeValue = ", Integer.valueOf(intExtra));
        if (intExtra == 1) {
            if (!f() || squ.g() || b()) {
                squ.o();
            }
            b(false);
            c(false);
        } else if (intExtra == 2) {
            c();
            squ.n();
            squ.o();
            g();
            a(false);
        } else {
            LogUtil.h("Suggestion_MoveService", "onHandleIntent type abnormal");
        }
        LogUtil.c("Suggestion_MoveService", "onHandleIntent end");
    }

    private void c() {
        b(squ.f());
        if (f()) {
            for (String str : squ.h()) {
                b(str);
            }
        }
    }

    private void g() {
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent("com.huawei.health.suggestion.config.MoveService.DEL_ACTION"));
    }

    private void b(String str) {
        for (String str2 : squ.e()) {
            c(new File(str + "/" + str2));
        }
    }

    private static void c(File file) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            LogUtil.h("Suggestion_MoveService", "deleteDirWithFile directory == null || !directory.exists() || !directory.isDirectory()");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            LogUtil.h("Suggestion_MoveService", "deleteDirWithFile files == null");
            return;
        }
        for (File file2 : listFiles) {
            if (file2 != null) {
                if (file2.isFile()) {
                    LogUtil.c("Suggestion_MoveService", "delFile = ", Boolean.valueOf(file2.delete()));
                } else if (file2.isDirectory()) {
                    c(file2);
                } else {
                    LogUtil.h("Suggestion_MoveService", "deleteDirWithFile Abnormal");
                }
            }
        }
        LogUtil.a("Suggestion_MoveService", "delFileDir = ", Boolean.valueOf(file.delete()));
    }

    private boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        String f = squ.f();
        String[] h = squ.h();
        String[] e = squ.e();
        boolean z = false;
        for (String str : h) {
            for (String str2 : e) {
                z = d(new File(str + "/" + str2), new File(f + "/" + str2));
                if (!z) {
                    LogUtil.c("Suggestion_MoveService", "isCopyDone = false");
                    return false;
                }
            }
        }
        LogUtil.c("Suggestion_MoveService", "onHandleIntent copy time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", isCopyDone = ", Boolean.valueOf(z));
        return true;
    }

    private boolean d(File file, File file2) {
        String str;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            LogUtil.c("Suggestion_MoveService", "onHandleIntent files == null");
            return true;
        }
        LogUtil.c("Suggestion_MoveService", "files = ", Integer.valueOf(listFiles.length));
        for (File file3 : listFiles) {
            if (file3 == null) {
                LogUtil.c("Suggestion_MoveService", "onHandleIntent fileFrom == null");
            } else {
                if (e()) {
                    LogUtil.h("Suggestion_MoveService", "isCopyCancel() = true");
                    return false;
                }
                if (file3.isFile()) {
                    try {
                        str = file2.getCanonicalPath() + "/" + file3.getName();
                    } catch (IOException e) {
                        LogUtil.h("Suggestion_MoveService", LogAnonymous.b((Throwable) e));
                        str = "";
                    }
                    File file4 = new File(str);
                    if (file4.exists()) {
                        squ.e(file4.getName(), file4.lastModified());
                    } else if (d(file3)) {
                        try {
                            File file5 = new File(file4.getCanonicalFile() + ".temp");
                            LogUtil.c("Suggestion_MoveService", "fileToTemp = ", file5.getCanonicalPath());
                            boolean c = ggo.c(file3, file5);
                            if (c && file5.renameTo(file4)) {
                                LogUtil.c("Suggestion_MoveService", "fileToTemp.renameTo(fileTo) = true");
                                squ.e(file4.getName(), file4.lastModified());
                            } else if (!c || file5.renameTo(file4)) {
                                LogUtil.h("Suggestion_MoveService", "isCopy = false  ", file3.getName());
                            } else {
                                LogUtil.c("Suggestion_MoveService", "fileToTemp.renameTo(fileTo) = false");
                            }
                        } catch (IOException e2) {
                            LogUtil.b("Suggestion_MoveService", "exception = ", LogAnonymous.b((Throwable) e2));
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean d(File file) {
        if (squ.a(file, squ.o(file.getName()))) {
            return true;
        }
        return squ.d(file.getName(), ash.b(file.getName()));
    }

    private void c(boolean z) {
        synchronized (this) {
            this.b = z;
        }
    }

    private boolean e() {
        boolean z;
        synchronized (this) {
            z = this.b;
        }
        return z;
    }

    private void a(boolean z) {
        synchronized (this) {
            this.d = z;
        }
    }

    private boolean a() {
        boolean z;
        synchronized (this) {
            z = this.d;
        }
        return z;
    }

    private void b(boolean z) {
        synchronized (this) {
            this.c = z;
        }
    }

    private boolean d() {
        boolean z;
        synchronized (this) {
            z = this.c;
        }
        return z;
    }

    private static boolean f() {
        boolean f = sqq.b().f();
        LogUtil.c("Suggestion_MoveService", "isSdPermission() = ", Boolean.valueOf(f));
        return f;
    }

    public static void b(Context context, int i) {
        if (context == null) {
            LogUtil.h("Suggestion_MoveService", "startService context == null");
            return;
        }
        LogUtil.c("Suggestion_MoveService", "startService type = ", Integer.valueOf(i));
        try {
            if (i == 1) {
                if (!squ.i() && !squ.g()) {
                    Intent intent = new Intent(context, (Class<?>) MoveService.class);
                    intent.putExtra("TYPE_KEY", 1);
                    context.startService(intent);
                    LogUtil.c("Suggestion_MoveService", "startService TYPE_COPE");
                }
            } else if (i == 2) {
                Intent intent2 = new Intent(context, (Class<?>) MoveService.class);
                intent2.putExtra("TYPE_KEY", 2);
                context.startService(intent2);
                ash.a("clear_version", String.valueOf(gic.e((Object) ash.b("clear_version")) + 1));
                LogUtil.h("Suggestion_MoveService", "startService type TYPE_DELETE");
            } else {
                LogUtil.h("Suggestion_MoveService", "startService type abnormal");
            }
        } catch (IllegalStateException e) {
            e = e;
            LogUtil.b("Suggestion_MoveService", "startService exception:", LogAnonymous.b(e));
        } catch (SecurityException e2) {
            e = e2;
            LogUtil.b("Suggestion_MoveService", "startService exception:", LogAnonymous.b(e));
        }
    }
}
