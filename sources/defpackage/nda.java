package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.huawei.hihealth.HiDataFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class nda {
    private static final String e = "code_cache" + File.separator + "secondary-dexes";

    private static SharedPreferences ctc_(Context context) {
        return context.getSharedPreferences("multidex.version", 4);
    }

    public static Set<String> c(Context context, final String str) throws PackageManager.NameNotFoundException, IOException, InterruptedException {
        final HashSet hashSet = new HashSet();
        List<String> c = c(context);
        final CountDownLatch countDownLatch = new CountDownLatch(c.size());
        for (final String str2 : c) {
            nco.b().execute(new Runnable() { // from class: nda.1
                /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
                
                    if (r0 == null) goto L20;
                 */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() {
                    /*
                        r4 = this;
                        r0 = 0
                        java.lang.String r1 = r1     // Catch: java.lang.Throwable -> L4f
                        java.lang.String r2 = ".zip"
                        boolean r1 = r1.endsWith(r2)     // Catch: java.lang.Throwable -> L4f
                        if (r1 == 0) goto L26
                        java.lang.String r1 = r1     // Catch: java.lang.Throwable -> L4f
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f
                        r2.<init>()     // Catch: java.lang.Throwable -> L4f
                        java.lang.String r3 = r1     // Catch: java.lang.Throwable -> L4f
                        r2.append(r3)     // Catch: java.lang.Throwable -> L4f
                        java.lang.String r3 = ".tmp"
                        r2.append(r3)     // Catch: java.lang.Throwable -> L4f
                        java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L4f
                        r3 = 0
                        dalvik.system.DexFile r0 = dalvik.system.DexFile.loadDex(r1, r2, r3)     // Catch: java.lang.Throwable -> L4f
                        goto L2e
                    L26:
                        dalvik.system.DexFile r1 = new dalvik.system.DexFile     // Catch: java.lang.Throwable -> L4f
                        java.lang.String r2 = r1     // Catch: java.lang.Throwable -> L4f
                        r1.<init>(r2)     // Catch: java.lang.Throwable -> L4f
                        r0 = r1
                    L2e:
                        java.util.Enumeration r1 = r0.entries()     // Catch: java.lang.Throwable -> L4f
                    L32:
                        boolean r2 = r1.hasMoreElements()     // Catch: java.lang.Throwable -> L4f
                        if (r2 == 0) goto L4c
                        java.lang.Object r2 = r1.nextElement()     // Catch: java.lang.Throwable -> L4f
                        java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L4f
                        java.lang.String r3 = r2     // Catch: java.lang.Throwable -> L4f
                        boolean r3 = r2.startsWith(r3)     // Catch: java.lang.Throwable -> L4f
                        if (r3 == 0) goto L32
                        java.util.Set r3 = r3     // Catch: java.lang.Throwable -> L4f
                        r3.add(r2)     // Catch: java.lang.Throwable -> L4f
                        goto L32
                    L4c:
                        if (r0 == 0) goto L69
                        goto L66
                    L4f:
                        r1 = move-exception
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6f
                        r2.<init>()     // Catch: java.lang.Throwable -> L6f
                        java.lang.String r3 = "Scan map file in dex files made error."
                        r2.append(r3)     // Catch: java.lang.Throwable -> L6f
                        r2.append(r1)     // Catch: java.lang.Throwable -> L6f
                        java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L6f
                        defpackage.ncx.e(r1)     // Catch: java.lang.Throwable -> L6f
                        if (r0 == 0) goto L69
                    L66:
                        r0.close()     // Catch: java.lang.Throwable -> L69
                    L69:
                        java.util.concurrent.CountDownLatch r0 = r4
                        r0.countDown()
                        return
                    L6f:
                        r1 = move-exception
                        if (r0 == 0) goto L75
                        r0.close()     // Catch: java.lang.Throwable -> L75
                    L75:
                        java.util.concurrent.CountDownLatch r0 = r4
                        r0.countDown()
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: defpackage.nda.AnonymousClass1.run():void");
                }
            });
        }
        countDownLatch.await();
        ncx.d("Filter " + hashSet.size() + " classes by packageName <" + str + HiDataFilter.DataFilterExpression.BIGGER_THAN);
        return hashSet;
    }

    public static List<String> c(Context context) throws PackageManager.NameNotFoundException, IOException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        ArrayList arrayList = new ArrayList();
        arrayList.add(applicationInfo.sourceDir);
        String str = file.getName() + ".classes";
        if (!d()) {
            int i = ctc_(context).getInt("dex.number", 1);
            File file2 = new File(applicationInfo.dataDir, e);
            for (int i2 = 2; i2 <= i; i2++) {
                File file3 = new File(file2, str + i2 + ".zip");
                if (file3.isFile()) {
                    arrayList.add(file3.getAbsolutePath());
                } else {
                    throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + "'");
                }
            }
        }
        if (ncp.e()) {
            arrayList.addAll(ctd_(applicationInfo));
        }
        return arrayList;
    }

    private static List<String> ctd_(ApplicationInfo applicationInfo) {
        ArrayList arrayList = new ArrayList();
        if (applicationInfo.splitSourceDirs != null) {
            arrayList.addAll(Arrays.asList(applicationInfo.splitSourceDirs));
            ncx.d("Found InstantRun support");
        } else {
            try {
                File file = new File((String) Class.forName("com.android.tools.fd.runtime.Paths").getMethod("getDexFileDirectory", String.class).invoke(null, applicationInfo.packageName));
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2 != null && file2.exists() && file2.isFile() && file2.getName().endsWith(".dex")) {
                            arrayList.add(file2.getAbsolutePath());
                        }
                    }
                    ncx.d("Found InstantRun support");
                }
            } catch (Exception e2) {
                ncx.e("InstantRun support error, " + e2.getMessage());
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
    
        if (java.lang.Integer.valueOf(java.lang.System.getProperty("ro.build.version.sdk")).intValue() >= 21) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004c, code lost:
    
        if (r3 < 1) goto L22;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0060  */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean d() {
        /*
            r0 = 0
            boolean r1 = a()     // Catch: java.lang.Exception -> L50
            r2 = 1
            if (r1 == 0) goto L1d
            java.lang.String r1 = "'YunOS'"
            java.lang.String r3 = "ro.build.version.sdk"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch: java.lang.Throwable -> L51
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L51
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L51
            r4 = 21
            if (r3 < r4) goto L51
            goto L4e
        L1d:
            java.lang.String r1 = "'Android'"
            java.lang.String r3 = "java.vm.version"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto L51
            java.lang.String r4 = "(\\d+)\\.(\\d+)(\\.\\d+)?"
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch: java.lang.Throwable -> L51
            java.util.regex.Matcher r3 = r4.matcher(r3)     // Catch: java.lang.Throwable -> L51
            boolean r4 = r3.matches()     // Catch: java.lang.Throwable -> L51
            if (r4 == 0) goto L51
            java.lang.String r4 = r3.group(r2)     // Catch: java.lang.Throwable -> L51
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.Throwable -> L51
            r5 = 2
            java.lang.String r3 = r3.group(r5)     // Catch: java.lang.Throwable -> L51
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Throwable -> L51
            if (r4 > r5) goto L4e
            if (r4 != r5) goto L51
            if (r3 < r2) goto L51
        L4e:
            r0 = r2
            goto L51
        L50:
            r1 = 0
        L51:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "VM with name "
            r2.<init>(r3)
            r2.append(r1)
            if (r0 == 0) goto L60
            java.lang.String r1 = " has multidex support"
            goto L62
        L60:
            java.lang.String r1 = " does not have multidex support"
        L62:
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            defpackage.ncx.c(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nda.d():boolean");
    }

    private static boolean a() {
        String property;
        String property2;
        try {
            property = System.getProperty("ro.yunos.version");
            property2 = System.getProperty("java.vm.name");
        } catch (Exception unused) {
        }
        if (property2 == null || !property2.toLowerCase().contains("lemur")) {
            if (property != null) {
                if (property.trim().length() > 0) {
                }
            }
            return false;
        }
        return true;
    }
}
