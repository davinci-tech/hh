package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import android.os.Process;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes5.dex */
public class AsCache {
    public static final String FEED_BACK_CACHE_FILE_NAME = "feedback";
    private static final int MAX_COUNT = Integer.MAX_VALUE;
    private static final int MAX_SIZE = 50000000;
    private static final char SEPARATOR = ' ';
    private static final String TAG = "AsCache";
    public static final int TIME_DAY = 86400;
    public static final int TIME_HOUR = 3600;
    private static Map<String, AsCache> mInstanceMap = new HashMap();
    private d mCache;

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private final Map<File, Long> f8337a;
        private final int b;
        private final AtomicInteger c;
        private final AtomicLong d;
        private final long e;
        private File h;
        private ThreadPoolExecutor j;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c(String str) {
            return e(str).delete();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(File file) {
            int i = this.c.get();
            while (i + 1 > this.b) {
                this.d.addAndGet(-e());
                i = this.c.addAndGet(-1);
            }
            this.c.addAndGet(1);
            long c = c(file);
            long j = this.d.get();
            while (j + c > this.e) {
                j = this.d.addAndGet(-e());
            }
            this.d.addAndGet(c);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.f8337a.put(file, valueOf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File d(String str) {
            return new File(this.h, str.hashCode() + "");
        }

        private long e() {
            File file;
            if (this.f8337a.isEmpty()) {
                return 0L;
            }
            Set<Map.Entry<File, Long>> entrySet = this.f8337a.entrySet();
            synchronized (this.f8337a) {
                file = null;
                Long l = null;
                for (Map.Entry<File, Long> entry : entrySet) {
                    if (file == null) {
                        file = entry.getKey();
                        l = entry.getValue();
                    } else {
                        Long value = entry.getValue();
                        if (value.longValue() < l.longValue()) {
                            file = entry.getKey();
                            l = value;
                        }
                    }
                }
            }
            long c = c(file);
            if (file != null && file.delete()) {
                this.f8337a.remove(file);
            }
            return c;
        }

        private void c() {
            this.j.execute(new RunnableC0235d());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File e(String str) {
            File d = d(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            d.setLastModified(valueOf.longValue());
            this.f8337a.put(d, valueOf);
            return d;
        }

        class a implements ThreadFactory {
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(AsCache.TAG);
            }

            a() {
            }
        }

        /* renamed from: com.huawei.phoneservice.feedbackcommon.utils.AsCache$d$d, reason: collision with other inner class name */
        class RunnableC0235d implements Runnable {
            @Override // java.lang.Runnable
            public void run() {
                File[] listFiles = d.this.h.listFiles();
                if (listFiles != null) {
                    int i = 0;
                    int i2 = 0;
                    for (File file : listFiles) {
                        i = (int) (i + d.this.c(file));
                        i2++;
                        d.this.f8337a.put(file, Long.valueOf(file.lastModified()));
                    }
                    d.this.d.set(i);
                    d.this.c.set(i2);
                }
            }

            RunnableC0235d() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long c(File file) {
            if (file == null) {
                return 0L;
            }
            return file.length();
        }

        private d(File file, long j, int i) {
            this.f8337a = Collections.synchronizedMap(new HashMap());
            this.h = file;
            this.e = j;
            this.b = i;
            this.d = new AtomicLong();
            this.c = new AtomicInteger();
            this.j = new ThreadPoolExecutor(2, 4, 100L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(2), new a());
            c();
        }
    }

    static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static boolean e(String str) {
            return b(a(str));
        }

        private static byte[] a(String str) {
            try {
                return str.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                com.huawei.phoneservice.faq.base.util.i.c(AsCache.TAG, "UnsupportedEncodingException " + e.getMessage());
                return new byte[0];
            }
        }

        private static boolean b(byte[] bArr) {
            String[] d = d(bArr);
            if (d == null || d.length != 2) {
                return false;
            }
            String str = d[0];
            while (str.startsWith("0")) {
                str = str.substring(1);
            }
            return System.currentTimeMillis() > Long.parseLong(str) + (Long.parseLong(d[1]) * 1000);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String d(String str) {
            return (str == null || !c(a(str))) ? str : str.substring(str.indexOf(32) + 1, str.length());
        }

        private static boolean c(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == 45 && a(bArr, AsCache.SEPARATOR) > 14;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String c(int i, String str) {
            return c(i) + str;
        }

        private static String[] d(byte[] bArr) {
            return c(bArr) ? new String[]{new String(c(bArr, 0, 13)), new String(c(bArr, 14, a(bArr, AsCache.SEPARATOR)))} : new String[0];
        }

        private static byte[] c(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        private static String c(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append("");
            while (true) {
                String sb2 = sb.toString();
                if (sb2.length() >= 13) {
                    return sb2 + Constants.LINK + i + AsCache.SEPARATOR;
                }
                StringBuilder sb3 = new StringBuilder("0");
                sb3.append(sb2);
                sb = sb3;
            }
        }

        private static int a(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }
    }

    public boolean remove(String str) {
        return this.mCache.c(str);
    }

    public void put(String str, String str2, int i) {
        put(str, e.c(i, str2));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:2|3)|(2:5|6)|(4:8|9|10|11)|12|13|14|15|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0064, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
    
        com.huawei.phoneservice.faq.base.util.i.c(com.huawei.phoneservice.feedbackcommon.utils.AsCache.TAG, r7.getMessage());
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void put(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = "AsCache"
            com.huawei.phoneservice.feedbackcommon.utils.AsCache$d r1 = r5.mCache
            java.io.File r6 = com.huawei.phoneservice.feedbackcommon.utils.AsCache.d.d(r1, r6)
            r1 = 0
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r4 = 1024(0x400, float:1.435E-42)
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r3.write(r7)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            r3.flush()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            r2.flush()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            r3.close()     // Catch: java.io.IOException -> L22
            goto L60
        L22:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r7)
            goto L60
        L2b:
            r7 = move-exception
            goto L74
        L2d:
            r7 = move-exception
            goto L33
        L2f:
            r7 = move-exception
            goto L75
        L31:
            r7 = move-exception
            r3 = r1
        L33:
            r1 = r2
            goto L3a
        L35:
            r7 = move-exception
            r2 = r1
            goto L75
        L38:
            r7 = move-exception
            r3 = r1
        L3a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72
            java.lang.String r4 = "put failed "
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> L72
            r2.append(r7)     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = r2.toString()     // Catch: java.lang.Throwable -> L72
            com.huawei.phoneservice.faq.base.util.i.c(r0, r7)     // Catch: java.lang.Throwable -> L72
            if (r3 == 0) goto L5d
            r3.close()     // Catch: java.io.IOException -> L55
            goto L5d
        L55:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r7)
        L5d:
            if (r1 == 0) goto L6c
            r2 = r1
        L60:
            r2.close()     // Catch: java.io.IOException -> L64
            goto L6c
        L64:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r7)
        L6c:
            com.huawei.phoneservice.feedbackcommon.utils.AsCache$d r7 = r5.mCache
            com.huawei.phoneservice.feedbackcommon.utils.AsCache.d.a(r7, r6)
            return
        L72:
            r7 = move-exception
            r2 = r1
        L74:
            r1 = r3
        L75:
            if (r1 == 0) goto L83
            r1.close()     // Catch: java.io.IOException -> L7b
            goto L83
        L7b:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r1)
        L83:
            if (r2 == 0) goto L91
            r2.close()     // Catch: java.io.IOException -> L89
            goto L91
        L89:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r1)
        L91:
            com.huawei.phoneservice.feedbackcommon.utils.AsCache$d r0 = r5.mCache
            com.huawei.phoneservice.feedbackcommon.utils.AsCache.d.a(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.AsCache.put(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void put(android.content.Context r5, java.lang.String r6, java.lang.String r7) {
        /*
            r4 = this;
            java.lang.String r0 = "AsCache"
            java.io.File r1 = new java.io.File
            java.io.File r5 = r5.getCacheDir()
            java.lang.String r2 = "feedback"
            r1.<init>(r5, r2)
            boolean r5 = r1.exists()
            if (r5 != 0) goto L16
            r1.mkdirs()
        L16:
            com.huawei.phoneservice.feedbackcommon.utils.AsCache$d r5 = r4.mCache
            java.io.File r5 = com.huawei.phoneservice.feedbackcommon.utils.AsCache.d.d(r5, r6)
            r6 = 0
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4f
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4f
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r2.write(r7)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r2.flush()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r1.flush()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r2.close()     // Catch: java.io.IOException -> L36
            goto L77
        L36:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r6)
            goto L77
        L3f:
            r6 = move-exception
            goto L8c
        L41:
            r6 = move-exception
            r7 = r6
            goto L4a
        L44:
            r7 = move-exception
            r2 = r6
        L46:
            r6 = r7
            goto L8c
        L48:
            r7 = move-exception
            r2 = r6
        L4a:
            r6 = r1
            goto L51
        L4c:
            r7 = move-exception
            r1 = r6
            goto L8e
        L4f:
            r7 = move-exception
            r2 = r6
        L51:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89
            java.lang.String r3 = "put failed "
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L89
            java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> L89
            r1.append(r7)     // Catch: java.lang.Throwable -> L89
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Throwable -> L89
            com.huawei.phoneservice.faq.base.util.i.c(r0, r7)     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L74
            r2.close()     // Catch: java.io.IOException -> L6c
            goto L74
        L6c:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r7)
        L74:
            if (r6 == 0) goto L83
            r1 = r6
        L77:
            r1.close()     // Catch: java.io.IOException -> L7b
            goto L83
        L7b:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r6)
        L83:
            com.huawei.phoneservice.feedbackcommon.utils.AsCache$d r6 = r4.mCache
            com.huawei.phoneservice.feedbackcommon.utils.AsCache.d.a(r6, r5)
            return
        L89:
            r7 = move-exception
            r1 = r6
            goto L46
        L8c:
            r7 = r6
            r6 = r2
        L8e:
            if (r6 == 0) goto L9c
            r6.close()     // Catch: java.io.IOException -> L94
            goto L9c
        L94:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r6)
        L9c:
            if (r1 == 0) goto Laa
            r1.close()     // Catch: java.io.IOException -> La2
            goto Laa
        La2:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r6)
        Laa:
            com.huawei.phoneservice.feedbackcommon.utils.AsCache$d r6 = r4.mCache
            com.huawei.phoneservice.feedbackcommon.utils.AsCache.d.a(r6, r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.AsCache.put(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [boolean] */
    public String getAsString(String str) {
        BufferedReader bufferedReader;
        File e2 = this.mCache.e(str);
        ?? exists = e2.exists();
        BufferedReader bufferedReader2 = null;
        try {
            if (exists == 0) {
                return null;
            }
            try {
                bufferedReader = new BufferedReader(new FileReader(e2));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    }
                    String sb2 = sb.toString();
                    if (!e.e(sb2)) {
                        String d2 = e.d(sb2);
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            com.huawei.phoneservice.faq.base.util.i.c(TAG, "getAsString failed " + e3.getMessage());
                        }
                        return d2;
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        com.huawei.phoneservice.faq.base.util.i.c(TAG, "getAsString failed " + e4.getMessage());
                    }
                    remove(str);
                    return null;
                } catch (IOException e5) {
                    e = e5;
                    com.huawei.phoneservice.faq.base.util.i.c(TAG, "getAsString failed " + e.getMessage());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e6) {
                            com.huawei.phoneservice.faq.base.util.i.c(TAG, "getAsString failed " + e6.getMessage());
                        }
                    }
                    return null;
                }
            } catch (IOException e7) {
                e = e7;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e8) {
                        com.huawei.phoneservice.faq.base.util.i.c(TAG, "getAsString failed " + e8.getMessage());
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = exists;
        }
    }

    private static String myPid() {
        return "_" + Process.myPid();
    }

    public static AsCache get(File file, long j, int i) throws RuntimeException, IOException {
        AsCache asCache = mInstanceMap.get(file.getAbsoluteFile() + myPid());
        if (asCache != null) {
            return asCache;
        }
        AsCache asCache2 = new AsCache(file, j, i);
        mInstanceMap.put(file.getCanonicalPath() + myPid(), asCache2);
        return asCache2;
    }

    public static AsCache get(Context context, String str) throws RuntimeException, IOException {
        return get(new File(context.getCacheDir(), str), 50000000L, Integer.MAX_VALUE);
    }

    private AsCache(File file, long j, int i) throws IOException {
        if (file.exists() || file.mkdirs()) {
            this.mCache = new d(file, j, i);
        } else {
            throw new IOException("can't make dirs in " + file.getCanonicalPath());
        }
    }
}
