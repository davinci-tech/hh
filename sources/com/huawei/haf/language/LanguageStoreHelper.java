package com.huawei.haf.language;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileFilterUtils;
import com.huawei.haf.common.os.FileLockHelper;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes.dex */
final class LanguageStoreHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f2126a;
    private final LanguageInfo b;
    private boolean c;
    private int d;
    private final File e;

    LanguageStoreHelper(LanguageInfo languageInfo) {
        this.b = languageInfo;
        File file = new File(c(), BaseApplication.a());
        FileUtils.a(file);
        this.e = new File(file, "base-all.lpk");
        this.d = 1;
    }

    static void b() {
        FileUtils.d(c(), new FileFilterUtils.FileNotEqualsCollectFilter(BaseApplication.a()));
    }

    boolean b(String str, Locale locale) {
        c(locale);
        e(str, locale);
        File b = b(locale);
        if (!e(b)) {
            c(!e(), b);
            if (!b.exists()) {
                return false;
            }
        }
        if (e()) {
            return false;
        }
        return LanguageInstallHelper.addResources("HAF_LanguageStore", BaseApplication.e(), b);
    }

    void yO_(String str, Configuration configuration) {
        e(str, configuration.locale);
        int size = configuration.getLocales().size();
        if (this.d != size) {
            yN_(configuration.getLocales());
            this.d = size;
        }
    }

    File a(Locale locale) {
        if (e()) {
            return null;
        }
        return b(locale);
    }

    private File b(Locale locale) {
        return this.e;
    }

    private boolean e() {
        return this.c;
    }

    private void c(Locale locale) {
        String languageUuid = this.b.getLanguageUuid(locale);
        this.f2126a = this.b.getLanguageName(languageUuid, locale);
        this.c = this.b.isStorePresetLanguage(languageUuid);
    }

    private void yN_(LocaleList localeList) {
        for (int i = 0; i < localeList.size(); i++) {
            Locale locale = localeList.get(i);
            if (locale != null) {
                File b = b(locale);
                if (!e(b)) {
                    c(false, b);
                }
            }
        }
    }

    private void e(String str, Locale locale) {
        LogUtil.c("HAF_LanguageStore", str, ", isPreset=", Boolean.valueOf(e()), ", language=", this.f2126a, ", tag=", locale.toLanguageTag(), ", locale=", locale.toString(), ", display=", locale.getDisplayLanguage(), ", sdkVersion=", Integer.valueOf(Build.VERSION.SDK_INT));
    }

    private static boolean e(File file) {
        if (!file.exists()) {
            return false;
        }
        File c = c(file);
        if (!c.exists()) {
            return false;
        }
        if ((!file.canWrite() && c.lastModified() == file.lastModified()) || LanguageInstallHelper.checkUsedResources(file)) {
            return true;
        }
        LogUtil.a("HAF_LanguageStore", "Detected attacks and sabotage, file '" + file.getName() + "' has been illegally modified.");
        return false;
    }

    private static boolean d(File file, String str, File file2) {
        File[] listFiles = file.listFiles(new FileFilterUtils.FilePrefixCollectFilter(str));
        if (CollectionUtils.b(listFiles)) {
            return true;
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (!file3.equals(file2)) {
                z = FileUtils.d(file3) && z;
            }
        }
        return z;
    }

    private static File c() {
        return AppInfoUtils.f("language_plugins");
    }

    private static File c(File file) {
        StringBuilder sb = new StringBuilder(32);
        sb.append(file.getName());
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(Math.abs(AppInfoUtils.d() - 1588291688888L));
        sb.append(".done");
        return new File(file.getParentFile(), sb.toString());
    }

    private static File d(File file) {
        return new File(file.getParentFile(), file.getName() + ".lock");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(boolean z, final File file) {
        if (!z) {
            ThreadPoolManager.d().c("HAF_LanguageStore", new Runnable() { // from class: com.huawei.haf.language.LanguageStoreHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    LanguageStoreHelper.c(true, file);
                }
            });
            return;
        }
        File d = d(file);
        FileLockHelper fileLockHelper = null;
        try {
            try {
                fileLockHelper = FileLockHelper.b(d);
                for (int i = 0; i < 3; i++) {
                    if (d(file, d)) {
                        break;
                    }
                }
            } catch (IOException e) {
                LogUtil.a("HAF_LanguageStore", "extractLanguages getFileLock fail, ex=", LogUtil.a(e));
                d(file, d);
            }
        } finally {
            FileUtils.d(fileLockHelper);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e A[Catch: all -> 0x0072, Exception -> 0x0074, Merged into TryCatch #0 {all -> 0x0072, Exception -> 0x0074, blocks: (B:13:0x0014, B:15:0x001e, B:18:0x0025, B:19:0x0048, B:21:0x004e, B:23:0x0054, B:29:0x005c, B:31:0x0029, B:36:0x0075), top: B:12:0x0014, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006a A[Catch: all -> 0x009f, DONT_GENERATE, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:4:0x0003, B:10:0x000c, B:25:0x006a, B:33:0x0098, B:34:0x009e, B:37:0x0090, B:13:0x0014, B:15:0x001e, B:18:0x0025, B:19:0x0048, B:21:0x004e, B:23:0x0054, B:29:0x005c, B:31:0x0029, B:36:0x0075), top: B:3:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean d(java.io.File r10, java.io.File r11) {
        /*
            java.lang.Class<com.huawei.haf.language.LanguageStoreHelper> r0 = com.huawei.haf.language.LanguageStoreHelper.class
            monitor-enter(r0)
            boolean r1 = e(r10)     // Catch: java.lang.Throwable -> L9f
            r2 = 1
            if (r1 == 0) goto Lc
            monitor-exit(r0)
            return r2
        Lc:
            java.io.File r1 = c(r10)     // Catch: java.lang.Throwable -> L9f
            r3 = 3
            r4 = 2
            r5 = 4
            r6 = 0
            java.io.File r7 = r1.getParentFile()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            boolean r8 = r10.exists()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r8 != 0) goto L29
            boolean r8 = r1.exists()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r8 == 0) goto L25
            goto L29
        L25:
            com.huawei.haf.common.os.FileUtils.a(r7)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            goto L48
        L29:
            java.lang.String r8 = r10.getName()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            boolean r11 = d(r7, r8, r11)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.Object[] r7 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r9 = "delete old or bad file "
            r7[r6] = r9     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r7[r2] = r8     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r8 = ", result="
            r7[r4] = r8     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r7[r3] = r11     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r11 = "HAF_LanguageStore"
            health.compact.a.LogUtil.c(r11, r7)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
        L48:
            boolean r11 = a(r10)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r11 == 0) goto L67
            boolean r11 = com.huawei.haf.common.os.FileUtils.e(r1, r2)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r11 == 0) goto L5c
            long r7 = r10.lastModified()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r1.setLastModified(r7)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            goto L68
        L5c:
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r7 = "extractLanguages createNewFile fail."
            r11[r6] = r7     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r7 = "HAF_LanguageStore"
            health.compact.a.LogUtil.a(r7, r11)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
        L67:
            r2 = r6
        L68:
            if (r2 != 0) goto L70
            com.huawei.haf.common.os.FileUtils.d(r10)     // Catch: java.lang.Throwable -> L9f
            com.huawei.haf.common.os.FileUtils.d(r1)     // Catch: java.lang.Throwable -> L9f
        L70:
            r6 = r2
            goto L96
        L72:
            r11 = move-exception
            goto L98
        L74:
            r11 = move-exception
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = "extract package "
            r5[r6] = r7     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = r10.getPath()     // Catch: java.lang.Throwable -> L72
            r5[r2] = r7     // Catch: java.lang.Throwable -> L72
            java.lang.String r2 = " file fail. exception:"
            r5[r4] = r2     // Catch: java.lang.Throwable -> L72
            java.lang.String r11 = health.compact.a.LogUtil.a(r11)     // Catch: java.lang.Throwable -> L72
            r5[r3] = r11     // Catch: java.lang.Throwable -> L72
            java.lang.String r11 = "HAF_LanguageStore"
            health.compact.a.LogUtil.e(r11, r5)     // Catch: java.lang.Throwable -> L72
            com.huawei.haf.common.os.FileUtils.d(r10)     // Catch: java.lang.Throwable -> L9f
            com.huawei.haf.common.os.FileUtils.d(r1)     // Catch: java.lang.Throwable -> L9f
        L96:
            monitor-exit(r0)
            return r6
        L98:
            com.huawei.haf.common.os.FileUtils.d(r10)     // Catch: java.lang.Throwable -> L9f
            com.huawei.haf.common.os.FileUtils.d(r1)     // Catch: java.lang.Throwable -> L9f
            throw r11     // Catch: java.lang.Throwable -> L9f
        L9f:
            r10 = move-exception
            monitor-exit(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.language.LanguageStoreHelper.d(java.io.File, java.io.File):boolean");
    }

    private static boolean a(File file) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        Context e = BaseApplication.e();
        BaseLanguageInfo b = BaseLanguageInfo.b(e);
        InputStream open = e.getAssets().open("language_plugins/base-all.lpk");
        try {
            long a2 = FileUtils.a(open, file);
            FileUtils.f(file);
            if (b.d(file)) {
                Object[] objArr = new Object[8];
                objArr[0] = "decode language package from assets, result=";
                objArr[1] = true;
                objArr[2] = ", times=";
                objArr[3] = Long.valueOf(System.currentTimeMillis() - currentTimeMillis);
                objArr[4] = ", fileSize=";
                objArr[5] = Long.valueOf(file.length());
                objArr[6] = ", valid=";
                objArr[7] = Boolean.valueOf(b.e > 0);
                LogUtil.c("HAF_LanguageStore", objArr);
                r18 = true;
            } else {
                LogUtil.a("HAF_LanguageStore", "decode language package from assets, result=", false, ", times=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", fileSize=", Long.valueOf(file.length()), ", decodeSize=", Long.valueOf(a2), ", hopeSize=", Long.valueOf(b.e));
            }
            return r18;
        } finally {
            FileUtils.d(open);
        }
    }

    static class BaseLanguageInfo {
        private final String c;
        private final long e;

        private BaseLanguageInfo(long j, String str) {
            this.e = j;
            this.c = str;
        }

        boolean d(File file) {
            if (!file.exists()) {
                return false;
            }
            long j = this.e;
            if (j > 0 && j != file.length()) {
                return false;
            }
            if (TextUtils.isEmpty(this.c)) {
                return true;
            }
            return this.c.equals(SecurityUtils.c(file));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0056  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        static com.huawei.haf.language.LanguageStoreHelper.BaseLanguageInfo b(android.content.Context r6) {
            /*
                r0 = 0
                android.content.res.AssetManager r6 = r6.getAssets()     // Catch: java.lang.Throwable -> L34 org.json.JSONException -> L36 java.io.IOException -> L38
                java.lang.String r1 = "language_plugins/base-all.json"
                java.io.InputStream r6 = r6.open(r1)     // Catch: java.lang.Throwable -> L34 org.json.JSONException -> L36 java.io.IOException -> L38
                r1 = 5242880(0x500000, double:2.590327E-317)
                java.lang.String r1 = com.huawei.haf.common.os.FileUtils.a(r6, r1)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30 java.io.IOException -> L32
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30 java.io.IOException -> L32
                r2.<init>(r1)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30 java.io.IOException -> L32
                java.lang.String r1 = "fileSize"
                long r3 = r2.getLong(r1)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30 java.io.IOException -> L32
                java.lang.String r1 = "digest"
                java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30 java.io.IOException -> L32
                com.huawei.haf.language.LanguageStoreHelper$BaseLanguageInfo r2 = new com.huawei.haf.language.LanguageStoreHelper$BaseLanguageInfo     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30 java.io.IOException -> L32
                r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30 java.io.IOException -> L32
                com.huawei.haf.common.os.FileUtils.d(r6)
                r0 = r2
                goto L53
            L2e:
                r0 = move-exception
                goto L60
            L30:
                r1 = move-exception
                goto L3b
            L32:
                r1 = move-exception
                goto L3b
            L34:
                r6 = move-exception
                goto L63
            L36:
                r6 = move-exception
                goto L39
            L38:
                r6 = move-exception
            L39:
                r1 = r6
                r6 = r0
            L3b:
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L2e
                java.lang.String r3 = "readBaseLanguageInfo fail, ex="
                r4 = 0
                r2[r4] = r3     // Catch: java.lang.Throwable -> L2e
                java.lang.String r1 = health.compact.a.LogUtil.a(r1)     // Catch: java.lang.Throwable -> L2e
                r3 = 1
                r2[r3] = r1     // Catch: java.lang.Throwable -> L2e
                java.lang.String r1 = "HAF_LanguageStore"
                health.compact.a.LogUtil.a(r1, r2)     // Catch: java.lang.Throwable -> L2e
                com.huawei.haf.common.os.FileUtils.d(r6)
            L53:
                if (r0 == 0) goto L56
                goto L5f
            L56:
                com.huawei.haf.language.LanguageStoreHelper$BaseLanguageInfo r0 = new com.huawei.haf.language.LanguageStoreHelper$BaseLanguageInfo
                r1 = 0
                java.lang.String r6 = ""
                r0.<init>(r1, r6)
            L5f:
                return r0
            L60:
                r5 = r0
                r0 = r6
                r6 = r5
            L63:
                com.huawei.haf.common.os.FileUtils.d(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.language.LanguageStoreHelper.BaseLanguageInfo.b(android.content.Context):com.huawei.haf.language.LanguageStoreHelper$BaseLanguageInfo");
        }
    }
}
