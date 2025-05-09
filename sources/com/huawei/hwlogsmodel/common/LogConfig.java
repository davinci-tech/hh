package com.huawei.hwlogsmodel.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.watchface.mvp.ui.activity.ScanImageActivity;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.BuildConfigProperties;
import health.compact.a.BuildTypeConfig;
import health.compact.a.DataAccessLayer;
import health.compact.a.HwLogger;
import health.compact.a.LogAllowListConfig;
import health.compact.a.LogDateUtil;
import health.compact.a.LogPathConfig;
import health.compact.a.LogZipUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ThreadManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class LogConfig {

    /* renamed from: a, reason: collision with root package name */
    private static LogPathConfig f6386a;
    private static LogAllowListConfig c;
    private String f;
    private String g;
    private boolean h;
    private int i;
    private double k;
    private String l;
    private double m;
    public static final String d = LogPathConfig.d + "/log/phonelogcat";
    private static final Object e = new Object();
    private static List<Model> j = new ArrayList(10);
    private static BroadcastReceiver b = new BroadcastReceiver() { // from class: com.huawei.hwlogsmodel.common.LogConfig.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                Log.w("LogUtil_LogConfig", "sLogConfigReceiver receive null,deal failed,return");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                Log.w("LogUtil_LogConfig", "sLogConfigReceiver receive action null,deal failed,return");
                return;
            }
            Log.i("LogUtil_LogConfig", "sLogConfigReceiver receive,deal:" + action);
            if (action.equals("com.huawei.health.update_log_config_area")) {
                LogConfig.d(context, "ACTION_UPDATE_LOG_CONFIG_AREA message deal for process:" + ProcessUtil.b() + " pid:" + Process.myPid());
                LogUtil.h();
                return;
            }
            if (action.equals("com.huawei.health.update_log_config_user")) {
                LogConfig.s();
            } else {
                Log.w("LogUtil_LogConfig", "action match failed");
            }
        }
    };

    public interface Model {
        void clearLogCache();
    }

    private LogConfig() {
        this(new d(null));
    }

    private LogConfig(d dVar) {
        this.l = dVar.j;
        this.f = dVar.f6387a;
        this.g = dVar.c;
        this.i = dVar.b;
        this.k = dVar.g;
        this.h = dVar.e;
        this.m = dVar.h;
        f6386a = dVar.f;
        c = dVar.d;
    }

    public void e(Context context) {
        if (context == null) {
            Log.w("LogUtil_LogConfig", "init context null, init failed, return");
        } else {
            h(context);
            d(context, "init log config");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final Context context, final String str) {
        synchronized (e) {
            ThreadPoolManager.d().c("LogUtil_LogConfig", new Runnable() { // from class: ktq
                @Override // java.lang.Runnable
                public final void run() {
                    LogConfig.e(context, str);
                }
            });
        }
    }

    public static /* synthetic */ void e(Context context, String str) {
        boolean c2 = DataAccessLayer.c(context);
        LogAllowListConfig.e(c2);
        Log.i("LogUtil_LogConfig", "updateOverseaSwitchByDb:" + c2 + " for cause:" + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void s() {
        ThreadManager.bRj_().post(new Runnable() { // from class: com.huawei.hwlogsmodel.common.LogConfig.4
            @Override // java.lang.Runnable
            public void run() {
                Log.i("LogUtil_LogConfig", "clearNativeMemoryAndFilesLogAsync memory");
                LogConfig.p();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void p() {
        Iterator<Model> it = j.iterator();
        while (it.hasNext()) {
            it.next().clearLogCache();
        }
    }

    public static void c(boolean z) {
        boolean e2 = BuildConfigProperties.e("IS_OUTPUT_LOG", false);
        if (z) {
            health.compact.a.LogUtil.b(new HwLogger(), e2, 1, true, false);
        } else {
            health.compact.a.LogUtil.b(new HwLogger(), e2, ((i() || d()) && !BuildConfigProperties.e("FORCE_ALL_LOG", false)) ? 2 : 0, true ^ i(), false);
        }
    }

    public static void d(Model model) {
        j.add(model);
    }

    private static void h(Context context) {
        Log.i("LogUtil_LogConfig", "registerLogConfigReceiver process:" + ProcessUtil.b() + " pid:" + Process.myPid());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.update_log_config_area");
        intentFilter.addAction("com.huawei.health.update_log_config_user");
        BroadcastManagerUtil.bFA_(context, b, intentFilter, SecurityConstant.d, null);
    }

    public static boolean i() {
        return BuildTypeConfig.a();
    }

    public static boolean d() {
        return BuildTypeConfig.e();
    }

    public static boolean a() {
        return BuildTypeConfig.c();
    }

    public static boolean g() {
        return BuildTypeConfig.b();
    }

    public static boolean h() {
        return BuildTypeConfig.d();
    }

    public static String m() {
        return f6386a.a();
    }

    public static String o() {
        return f6386a.c();
    }

    public static String j() {
        return f6386a.e();
    }

    public static File f() {
        return f6386a.b();
    }

    public String n() {
        if (TextUtils.isEmpty(this.l)) {
            return null;
        }
        String str = this.l;
        if (TextUtils.isEmpty(this.f)) {
            return str;
        }
        return str + "_" + this.f;
    }

    private int t() {
        return this.i;
    }

    private double q() {
        if (!this.h) {
            return this.m;
        }
        int i = (int) (((a() || (d() && this.k == 0.0d)) ? 10485760 : ScanImageActivity.SCAN_IMAGE_LENGTH_LIMIT) + this.k);
        Log.d("LogUtil_LogConfig", "acquireMaxLogFileLengthContainsOffset describe: " + n() + " result:" + i);
        return i;
    }

    public String l() {
        String str = this.l;
        if (TextUtils.isEmpty(str)) {
            str = ProcessUtil.b();
        }
        if (TextUtils.isEmpty(str)) {
            str = "health";
        }
        String replaceAll = str.replaceAll(":", "_");
        StringBuilder sb = new StringBuilder(16);
        sb.append(o());
        sb.append(replaceAll);
        if (!TextUtils.isEmpty(this.f)) {
            sb.append("_" + this.f);
        }
        sb.append("/");
        Log.d("LogUtil_LogConfig", "getLogFileRootPath mDetail:" + this.f + " result:" + sb.toString());
        return sb.toString();
    }

    public File k() {
        if (this.g == null) {
            this.g = l();
        }
        Log.d("LogUtil_LogConfig", "getLogFile(): logFilePath = " + this.g);
        File file = new File(this.g);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.w("LogUtil_LogConfig", "create log directory failed");
            }
        } else {
            w();
        }
        File file2 = new File(this.g, "log.0");
        if (file2.exists() && file2.length() > q()) {
            v();
        }
        return file2;
    }

    private void v() {
        try {
            File[] listFiles = new File(this.g).listFiles();
            if (listFiles == null) {
                Log.w("LogUtil_LogConfig", "zipLogFile fileList == null");
                return;
            }
            String str = ProcessUtil.d() ? this.g + "local_" : this.g;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH);
            for (File file : listFiles) {
                if (file.exists()) {
                    b(str, simpleDateFormat, file);
                }
            }
            if (ProcessUtil.b().endsWith(":PhoneService")) {
                y();
            } else if (ProcessUtil.d()) {
                LogZipUtil.a(this.g);
            } else {
                r();
            }
        } catch (IOException unused) {
            LogUtil.b("LogUtil_LogConfig", "zipLogFile IOException");
        }
    }

    private void b(String str, SimpleDateFormat simpleDateFormat, File file) throws IOException {
        if ("log.0".equals(file.getName())) {
            String format = simpleDateFormat.format(Long.valueOf(System.currentTimeMillis()));
            File file2 = new File(str + format);
            LogZipUtil.d(file.getCanonicalPath(), str + format, true);
            LogZipUtil.e(file2, new File(str + format + ".zip"));
            if (file.delete() && file2.delete()) {
                return;
            }
            Log.w("LogUtil_LogConfig", "compareFileName delete childFile or toZipFile file failed");
            return;
        }
        if ("log.1".equals(file.getName()) || "log.2".equals(file.getName()) || "log.3".equals(file.getName())) {
            String format2 = simpleDateFormat.format(Long.valueOf(file.lastModified()));
            File file3 = new File(str + format2);
            LogZipUtil.d(file.getCanonicalPath(), str + format2, true);
            LogZipUtil.e(file3, new File(str + format2 + ".zip"));
            if (file.delete() && file3.delete()) {
                return;
            }
            Log.w("LogUtil_LogConfig", "compareFileName delete childFile or toModifiedZipFile file failed");
            return;
        }
        if (file.getName().contains("log_file_zip")) {
            if (file.delete()) {
                return;
            }
            Log.w("LogUtil_LogConfig", "compareFileName delete log_file_zip failed");
            return;
        }
        Log.w("LogUtil_LogConfig", "compareFileName other zip file");
    }

    private void y() {
        ArrayList arrayList = new ArrayList(10);
        File[] listFiles = new File(this.g).listFiles();
        if (listFiles == null) {
            Log.w("LogUtil_LogConfig", "deletePhoneServiceZip zipFileList == null");
            return;
        }
        long j2 = 0;
        for (File file : listFiles) {
            String name = file.getName();
            if (!"log.0".equals(name)) {
                j2 += file.length();
                arrayList.add(Long.valueOf(LogDateUtil.d(name, HwDrmConstant.TIME_FORMAT)));
            }
        }
        if (arrayList.isEmpty()) {
            Log.w("LogUtil_LogConfig", "deletePhoneServiceZip fileNameList.isEmpty");
            return;
        }
        while (j2 > 36700160) {
            if (arrayList.isEmpty()) {
                Log.w("LogUtil_LogConfig", "deletePhoneServiceZip while fileNameList.isEmpty");
                return;
            }
            long longValue = ((Long) Collections.min(arrayList)).longValue();
            arrayList.remove(Long.valueOf(longValue));
            String d2 = LogDateUtil.d(longValue, HwDrmConstant.TIME_FORMAT);
            File file2 = new File(this.g + d2 + ".zip");
            StringBuilder sb = new StringBuilder();
            sb.append(this.g);
            sb.append(d2);
            File file3 = new File(sb.toString());
            if (file2.exists()) {
                j2 -= file2.length();
                Log.i("LogUtil_LogConfig", "deletePhoneServiceZip minFileZip is " + file2.delete());
            }
            if (file3.exists()) {
                j2 -= file3.length();
                Log.i("LogUtil_LogConfig", "deletePhoneServiceZip minFile is " + file3.delete());
            }
        }
    }

    private void r() {
        ArrayList arrayList = new ArrayList(10);
        File[] listFiles = new File(this.g).listFiles();
        if (listFiles == null) {
            Log.w("LogUtil_LogConfig", "deleteLastFile zipFileList == null");
            return;
        }
        for (File file : listFiles) {
            String name = file.getName();
            if (!"log.0".equals(name)) {
                arrayList.add(Long.valueOf(LogDateUtil.d(name, HwDrmConstant.TIME_FORMAT)));
            }
        }
        if (arrayList.isEmpty()) {
            Log.w("LogUtil_LogConfig", "deleteLastFile fileNameList.isEmpty");
            return;
        }
        while (arrayList.size() >= t()) {
            if (arrayList.isEmpty()) {
                Log.w("LogUtil_LogConfig", "deleteLastFile while fileNameList.isEmpty");
                return;
            }
            long longValue = ((Long) Collections.min(arrayList)).longValue();
            arrayList.remove(Long.valueOf(longValue));
            String d2 = LogDateUtil.d(longValue, HwDrmConstant.TIME_FORMAT);
            File file2 = new File(this.g + d2 + ".zip");
            StringBuilder sb = new StringBuilder();
            sb.append(this.g);
            sb.append(d2);
            File file3 = new File(sb.toString());
            if (file2.exists()) {
                Log.i("LogUtil_LogConfig", "deleteLastFile minFileZip is " + file2.delete());
            }
            if (file3.exists()) {
                Log.i("LogUtil_LogConfig", "deleteLastFile minFile is " + file3.delete());
            }
        }
    }

    private void w() {
        if (TextUtils.isEmpty(this.f)) {
            if (i() || d()) {
                File file = new File(this.g + "log.4");
                if (file.exists()) {
                    Log.d("LogUtil_LogConfig", "deleteSuccess = " + file.delete());
                }
            }
        }
    }

    public static void a(Context context) {
        Log.i("LogUtil_LogConfig", "confirmOverseaGlobalRefresh");
        a(context, "com.huawei.health.update_log_config_area");
    }

    public static void c(final Context context) {
        if (!i()) {
            Log.i("LogUtil_LogConfig", "confirmAccountChangedAsync-not release,no effect,return");
        } else {
            Log.i("LogUtil_LogConfig", "confirmAccountChangedAsync");
            ThreadManager.bRj_().post(new Runnable() { // from class: com.huawei.hwlogsmodel.common.LogConfig.2
                @Override // java.lang.Runnable
                public void run() {
                    LogConfig.b(context);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        Log.i("LogUtil_LogConfig", "confirmAccountChanged");
        d(f());
        a(context, "com.huawei.health.update_log_config_user");
        Log.i("LogUtil_LogConfig", "confirmAccountChanged end in main process, memory clear in broadcast receiver");
    }

    public static void d(File file) {
        if (file == null) {
            Log.w("LogUtil_LogConfig", "file is null, delete failed");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                Log.i("LogUtil_LogConfig", "current file is " + file2.getName());
                d(file2);
            }
        }
        Log.i("LogUtil_LogConfig", file.getName() + " delete result: " + file.delete());
    }

    public static boolean c() {
        boolean h;
        synchronized (e) {
            h = LogAllowListConfig.h();
        }
        return h;
    }

    private static void a(Context context, String str) {
        Intent intent = new Intent(str);
        intent.setPackage(BaseApplication.d());
        context.sendBroadcast(intent, SecurityConstant.d);
    }

    public static class d {
        private String j;

        /* renamed from: a, reason: collision with root package name */
        private String f6387a = null;
        private String c = null;
        private int b = 4;
        private double g = 0.0d;
        private boolean e = true;
        private double h = 5242880.0d;
        private LogPathConfig f = new LogPathConfig();
        private LogAllowListConfig d = new LogAllowListConfig();

        public d(String str) {
            this.j = str;
        }

        public d c(String str) {
            this.f6387a = str;
            return this;
        }

        public d b(int i) {
            this.b = i;
            return this;
        }

        public d e(double d) {
            this.g = d;
            return this;
        }

        public d c(boolean z) {
            this.e = z;
            return this;
        }

        public d c(double d) {
            this.h = d;
            return this;
        }

        public d e(LogAllowListConfig logAllowListConfig) {
            this.d = logAllowListConfig;
            return this;
        }

        public LogConfig e() {
            return new LogConfig(this);
        }
    }
}
