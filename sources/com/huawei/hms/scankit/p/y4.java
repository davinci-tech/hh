package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public class y4 {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f5927a;
    private static byte[] b;
    private static byte[] c;

    public static byte[] a() {
        return b;
    }

    public static byte[] b() {
        return c;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:16:0x005e
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static void c(android.content.Context r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "loadModel inputStream.close() IOException"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "load model...."
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "MsModel"
            com.huawei.hms.scankit.p.o4.d(r2, r1)
            byte[] r1 = com.huawei.hms.scankit.p.y4.f5927a
            if (r1 == 0) goto L1a
            return
        L1a:
            r1 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.io.InputStream r1 = r3.open(r4)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.String r4 = "inputStream"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r3.append(r1)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            com.huawei.hms.scankit.p.o4.d(r2, r3)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            int r3 = r1.available()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.read(r3)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.close()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            com.huawei.hms.scankit.p.y4.f5927a = r3     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L46:
            r3 = move-exception
            goto L62
        L48:
            java.lang.String r3 = "loadModel Exception"
            com.huawei.hms.scankit.p.o4.b(r2, r3)     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L53:
            java.lang.String r3 = "loadModel IOException"
            com.huawei.hms.scankit.p.o4.b(r2, r3)     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L5e:
            com.huawei.hms.scankit.p.o4.b(r2, r0)
        L61:
            return
        L62:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L68
            goto L6b
        L68:
            com.huawei.hms.scankit.p.o4.b(r2, r0)
        L6b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.y4.c(android.content.Context, java.lang.String):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:16:0x005e
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static void a(android.content.Context r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "loadAngleModel inputStream.close() IOException"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "load angle model...."
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "MsModel"
            com.huawei.hms.scankit.p.o4.d(r2, r1)
            byte[] r1 = com.huawei.hms.scankit.p.y4.b
            if (r1 == 0) goto L1a
            return
        L1a:
            r1 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.io.InputStream r1 = r3.open(r4)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.String r4 = "3inputStream"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r3.append(r1)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            com.huawei.hms.scankit.p.o4.d(r2, r3)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            int r3 = r1.available()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.read(r3)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.close()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            com.huawei.hms.scankit.p.y4.b = r3     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L46:
            r3 = move-exception
            goto L62
        L48:
            java.lang.String r3 = "loadAngleModel Exception"
            com.huawei.hms.scankit.p.o4.b(r2, r3)     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L53:
            java.lang.String r3 = "loadAngleModel IOException"
            com.huawei.hms.scankit.p.o4.b(r2, r3)     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L5e:
            com.huawei.hms.scankit.p.o4.b(r2, r0)
        L61:
            return
        L62:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L68
            goto L6b
        L68:
            com.huawei.hms.scankit.p.o4.b(r2, r0)
        L6b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.y4.a(android.content.Context, java.lang.String):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:16:0x005e
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static void b(android.content.Context r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "loadConerModel inputStream.close() IOException"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "load corner model...."
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "MsModel"
            com.huawei.hms.scankit.p.o4.d(r2, r1)
            byte[] r1 = com.huawei.hms.scankit.p.y4.c
            if (r1 == 0) goto L1a
            return
        L1a:
            r1 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.io.InputStream r1 = r3.open(r4)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.String r4 = "4inputStream"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r3.append(r1)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            com.huawei.hms.scankit.p.o4.d(r2, r3)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            int r3 = r1.available()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.read(r3)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.close()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            com.huawei.hms.scankit.p.y4.c = r3     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 java.io.IOException -> L53
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L46:
            r3 = move-exception
            goto L62
        L48:
            java.lang.String r3 = "loadConerModel Exception"
            com.huawei.hms.scankit.p.o4.b(r2, r3)     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L53:
            java.lang.String r3 = "loadConerModel IOException"
            com.huawei.hms.scankit.p.o4.b(r2, r3)     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L61
        L5e:
            com.huawei.hms.scankit.p.o4.b(r2, r0)
        L61:
            return
        L62:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L68
            goto L6b
        L68:
            com.huawei.hms.scankit.p.o4.b(r2, r0)
        L6b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.y4.b(android.content.Context, java.lang.String):void");
    }

    public static byte[] c() {
        return f5927a;
    }
}
