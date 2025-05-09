package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public final class ac {

    /* renamed from: a, reason: collision with root package name */
    public static final int f4366a = 0;
    private static final String b = "ExtractNativeUtils";
    private static final int c = -1;
    private static final int d = 128;
    private static final int e = 50;
    private static final int f = 52428800;
    private static final Pattern g = Pattern.compile("lib/([^/]+)/(.*\\.so)$");

    public static boolean a(Context context, String str) {
        try {
            if ((context.getPackageManager().getPackageArchiveInfo(str, 128).applicationInfo.flags & 268435456) == 268435456) {
                af.b(b, "The extract-native-flag has set, need to extract.");
                return true;
            }
            af.b(b, "The extract-native-flag has not set, No need to extract.");
            return false;
        } catch (Exception unused) {
            af.c(b, "Get package name failed: name not found.");
            return true;
        }
    }

    public static void a(Enumeration enumeration, Set<a> set, String str) throws ZipException {
        while (enumeration.hasMoreElements()) {
            Object nextElement = enumeration.nextElement();
            if (nextElement != null && (nextElement instanceof ZipEntry)) {
                ZipEntry zipEntry = (ZipEntry) nextElement;
                String name = zipEntry.getName();
                if (name.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
                    throw new ZipException("Unsafe zip name!");
                }
                Matcher matcher = g.matcher(name);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    if (group.equals(str)) {
                        set.add(new a(zipEntry, group2, group, (byte) 0));
                    }
                }
            }
        }
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        String f4367a;
        ZipEntry b;
        String c;

        /* synthetic */ a(ZipEntry zipEntry, String str, String str2, byte b) {
            this(zipEntry, str, str2);
        }

        private a(ZipEntry zipEntry, String str, String str2) {
            this.b = zipEntry;
            this.f4367a = str;
            this.c = str2;
        }
    }

    public static int a(ZipFile zipFile, Set<a> set, String str) {
        af.b(b, "begin extractNativeLibrary ");
        int i = 0;
        for (a aVar : set) {
            File file = new File(str);
            if (!file.exists()) {
                ad.a(str);
            }
            file.setExecutable(true, false);
            int a2 = a(zipFile, aVar, str);
            if (a2 != 0) {
                return a2;
            }
            new File(str, aVar.f4367a).setReadable(true, false);
            i = a2;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
    
        com.huawei.hms.ads.uiengineloader.af.d(com.huawei.hms.ads.uiengineloader.ac.b, "so file too big , " + r9.c + " , " + r9.f4367a);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(java.util.zip.ZipFile r8, com.huawei.hms.ads.uiengineloader.ac.a r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "ExtractNativeUtils"
            r1 = 4096(0x1000, float:5.74E-42)
            r2 = -1
            r3 = 0
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e java.io.FileNotFoundException -> L62
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e java.io.FileNotFoundException -> L62
            java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e java.io.FileNotFoundException -> L62
            java.lang.String r6 = r9.f4367a     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e java.io.FileNotFoundException -> L62
            r5.<init>(r10, r6)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e java.io.FileNotFoundException -> L62
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e java.io.FileNotFoundException -> L62
            java.util.zip.ZipEntry r10 = r9.b     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            java.io.InputStream r3 = r8.getInputStream(r10)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            r8 = 0
            r10 = r8
        L1c:
            int r5 = r3.read(r1)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            if (r5 <= 0) goto L49
            int r10 = r10 + r5
            r6 = 52428800(0x3200000, float:4.7019774E-37)
            if (r10 <= r6) goto L45
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            java.lang.String r10 = "so file too big , "
            r8.<init>(r10)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            java.lang.String r10 = r9.c     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            r8.append(r10)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            java.lang.String r10 = " , "
            r8.append(r10)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            java.lang.String r9 = r9.f4367a     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            r8.append(r9)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            com.huawei.hms.ads.uiengineloader.af.d(r0, r8)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            goto L4a
        L45:
            r4.write(r1, r8, r5)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L58
            goto L1c
        L49:
            r2 = r8
        L4a:
            com.huawei.hms.ads.uiengineloader.aj.a(r4)
            com.huawei.hms.ads.uiengineloader.aj.a(r3)
            goto L6e
        L51:
            r8 = move-exception
            r9 = r3
            r3 = r4
            goto L73
        L55:
            r8 = r3
            r3 = r4
            goto L5f
        L58:
            r8 = r3
            r3 = r4
            goto L63
        L5b:
            r8 = move-exception
            r9 = r3
            goto L73
        L5e:
            r8 = r3
        L5f:
            java.lang.String r9 = "IOException"
            goto L65
        L62:
            r8 = r3
        L63:
            java.lang.String r9 = "FileNotFoundException"
        L65:
            com.huawei.hms.ads.uiengineloader.af.d(r0, r9)     // Catch: java.lang.Throwable -> L6f
            com.huawei.hms.ads.uiengineloader.aj.a(r3)
            com.huawei.hms.ads.uiengineloader.aj.a(r8)
        L6e:
            return r2
        L6f:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
        L73:
            com.huawei.hms.ads.uiengineloader.aj.a(r3)
            com.huawei.hms.ads.uiengineloader.aj.a(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.uiengineloader.ac.a(java.util.zip.ZipFile, com.huawei.hms.ads.uiengineloader.ac$a, java.lang.String):int");
    }

    private static int a(Enumeration enumeration, HashMap<String, HashSet<a>> hashMap, int i) {
        while (enumeration.hasMoreElements()) {
            Object nextElement = enumeration.nextElement();
            if (nextElement != null && (nextElement instanceof ZipEntry)) {
                ZipEntry zipEntry = (ZipEntry) nextElement;
                String name = zipEntry.getName();
                if (name.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
                    af.d(b, "Unsafe zip name!");
                    return -1;
                }
                Matcher matcher = g.matcher(name);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    HashSet<a> hashSet = hashMap.get(group);
                    if (hashSet == null) {
                        hashSet = new HashSet<>();
                        hashMap.put(group, hashSet);
                    }
                    hashSet.add(new a(zipEntry, group2, group, (byte) 0));
                    i++;
                }
            }
        }
        return i;
    }

    public static int a(File file, String str) {
        ZipFile zipFile;
        af.b(b, "begin extractNativeLibrary");
        int i = 0;
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(file);
                try {
                    try {
                        Enumeration<? extends ZipEntry> entries = zipFile.entries();
                        HashMap hashMap = new HashMap();
                        int a2 = a(entries, (HashMap<String, HashSet<a>>) hashMap, 0);
                        if (a2 == -1) {
                            af.d(b, "Unsafe zip name!");
                            aj.a(zipFile);
                            return -1;
                        }
                        if (a2 > 50) {
                            af.d(b, "the total number is larger than the max");
                            aj.a(zipFile);
                            return -1;
                        }
                        Iterator it = hashMap.keySet().iterator();
                        int i2 = 0;
                        while (it.hasNext()) {
                            try {
                                Set<a> set = (Set) hashMap.get((String) it.next());
                                if (set == null) {
                                    af.d(b, "Get nativeZipEntries failed.");
                                    aj.a(zipFile);
                                    return -1;
                                }
                                for (a aVar : set) {
                                    String str2 = str + File.separator + aVar.c;
                                    ad.a(str2);
                                    new File(str2).setExecutable(true, false);
                                    i2 = a(zipFile, aVar, str2);
                                    if (i2 != 0) {
                                        aj.a(zipFile);
                                        return i2;
                                    }
                                    new File(str2, aVar.f4367a).setReadable(true, false);
                                }
                            } catch (IOException unused) {
                                i = i2;
                                zipFile2 = zipFile;
                                af.d(b, "catch IOException");
                                aj.a(zipFile2);
                                return i;
                            }
                        }
                        aj.a(zipFile);
                        return i2;
                    } catch (IOException unused2) {
                    }
                } catch (Throwable th) {
                    th = th;
                    aj.a(zipFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                zipFile = zipFile2;
            }
        } catch (IOException unused3) {
        }
    }
}
