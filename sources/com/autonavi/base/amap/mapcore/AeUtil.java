package com.autonavi.base.amap.mapcore;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.ds;
import com.amap.api.col.p0003sl.dt;
import com.amap.api.col.p0003sl.dv;
import com.amap.api.col.p0003sl.dw;
import com.amap.api.col.p0003sl.dx;
import com.amap.api.col.p0003sl.hz;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.col.p0003sl.jd;
import com.amap.api.col.p0003sl.jf;
import com.amap.api.col.p0003sl.lb;
import com.autonavi.amap.mapcore.MsgProcessor;
import com.autonavi.base.ae.gmap.GLMapEngine;
import com.autonavi.config.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class AeUtil {
    private static final String AMAP_ASSETS_DB_CK_PATH = "ae/res.ck";
    private static final String AMAP_GLOBAL_DB_NAME = "global.db";
    private static final String AMAP_GLOBAL_SP_ITEM_MD5 = "amap_res_global_db_md5";
    private static final String AMAP_GLOBAL_SP_NAME = "amap_res_global_db";
    private static final String AMAP_INTERSECTION_ASSETS_DIR = "VM3DRes";
    public static final String AMAP_RESZIP_TARGET_DIR_NAME = "res920";
    public static final String CONFIGNAME = "GNaviConfig.xml";
    public static final boolean IS_AE = true;
    public static final String RESZIPNAME = "res.zip";
    public static final String ROOTPATH = "/amap/";
    public static final String ROOT_DATA_PATH_NAME = "data_v6";
    public static final String ROOT_DATA_PATH_OLD_NAME = "data";
    private static String global_db_path;

    public static String getGlobalDbPath() {
        return global_db_path;
    }

    public static boolean loadLib(Context context) {
        try {
            if (!a.b) {
                System.loadLibrary(a.f1653a);
                a.b = true;
            }
            return true;
        } catch (Throwable th) {
            iv.c(th, "AeUtil", "loadLib");
            dv.a(th);
            dx.b(dw.c, "load so failed " + th.getMessage());
            return false;
        }
    }

    public static void initCrashHandle(Context context) {
        hz a2;
        try {
            jf.a();
            if (!jd.a(dv.a()).a(context) || (a2 = dv.a()) == null) {
                return;
            }
            MsgProcessor.nativeInitInfo(context, jd.a(a2).b(context), a2.a(), a2.b(), a2.c(), a2.f());
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    public static GLMapEngine.InitParam initResource(final Context context) {
        final String mapBaseStorage = FileUtil.getMapBaseStorage(context);
        String str = mapBaseStorage + "/data_v6/";
        File file = new File(mapBaseStorage);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                dt.a().a(new lb() { // from class: com.autonavi.base.amap.mapcore.AeUtil.1
                    @Override // com.amap.api.col.p0003sl.lb
                    public final void runTask() {
                        AeUtil.loadEngineRes(mapBaseStorage, context);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loadEngineRes(mapBaseStorage, context);
        }
        GLMapEngine.InitParam initParam = new GLMapEngine.InitParam();
        byte[] readFileContentsFromAssets = FileUtil.readFileContentsFromAssets(context, "ae/GNaviConfig.xml");
        initParam.mRootPath = mapBaseStorage;
        if (readFileContentsFromAssets != null) {
            try {
                initParam.mConfigContent = new String(readFileContentsFromAssets, "utf-8");
                if (!initParam.mConfigContent.contains(ROOT_DATA_PATH_NAME)) {
                    throw new Exception("GNaviConfig.xml 和数据目录data_v6不匹配");
                }
            } catch (Throwable th) {
                th.printStackTrace();
                ds.c(context, "initConfig error:" + th.getMessage());
            }
        }
        initParam.mOfflineDataPath = str + "/map/";
        initParam.mP3dCrossPath = str;
        initParam.mOfflineDataPath = formatFileSeparator(initParam.mOfflineDataPath);
        initParam.mRootPath = formatFileSeparator(initParam.mRootPath);
        initParam.mP3dCrossPath = formatFileSeparator(initParam.mP3dCrossPath);
        return initParam;
    }

    public static void initIntersectionRes(final Context context, final GLMapEngine.InitParam initParam) {
        String mapBaseStorage = FileUtil.getMapBaseStorage(context);
        String str = mapBaseStorage + "/VM3DRes/";
        File file = new File(mapBaseStorage);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        initParam.mIntersectionResPath = str;
        initParam.mIntersectionResPath = formatFileSeparator(initParam.mIntersectionResPath);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                dt.a().a(new lb() { // from class: com.autonavi.base.amap.mapcore.AeUtil.2
                    @Override // com.amap.api.col.p0003sl.lb
                    public final void runTask() {
                        AeUtil.loadAndSaveIntersectionRes("map_assets/VM3DRes", GLMapEngine.InitParam.this.mIntersectionResPath, context);
                    }
                });
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        loadAndSaveIntersectionRes("map_assets/VM3DRes", initParam.mIntersectionResPath, context);
    }

    private static String formatFileSeparator(String str) {
        return str != null ? str.replace("//", "/") : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(11:0|1|(1:50)(1:5)|6|(5:8|(1:10)|12|(1:16)(2:22|23)|(2:18|19)(1:21))|47|12|(3:14|16|(0)(0))|22|23|(4:(0)|(0)|(0)|(2:25|(0)(0)))) */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0040, code lost:
    
        if (r7.equals(r4) != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0071, code lost:
    
        if (r3 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0092, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b3, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b4, code lost:
    
        r9.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0096, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
    
        r9.printStackTrace();
        com.amap.api.col.p0003sl.dv.a(r9);
        com.amap.api.col.p0003sl.ds.c(r10, "loadEngineRes error:" + r9.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b0, code lost:
    
        if (r3 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0076, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0077, code lost:
    
        r9.printStackTrace();
        com.amap.api.col.p0003sl.dv.a(r9);
        com.amap.api.col.p0003sl.ds.c(r10, "loadEngineRes error:" + r9.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0090, code lost:
    
        if (r3 == null) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void loadEngineRes(java.lang.String r9, android.content.Context r10) {
        /*
            java.lang.String r0 = "loadEngineRes error:"
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "res920"
            r1.<init>(r9, r2)
            boolean r9 = r1.exists()
            r2 = 1
            r3 = 0
            if (r9 == 0) goto L17
            boolean r9 = r1.isDirectory()
            if (r9 != 0) goto L24
        L17:
            boolean r9 = r1.mkdirs()
            if (r9 == 0) goto L24
            java.lang.String r9 = getAssetsGlobalDbMd5(r10)
            r4 = r9
            r9 = r2
            goto L26
        L24:
            r9 = 0
            r4 = r3
        L26:
            java.lang.String r5 = "amap_res_global_db_md5"
            java.lang.String r6 = "amap_res_global_db"
            if (r9 != 0) goto L43
            java.lang.String r4 = getAssetsGlobalDbMd5(r10)
            java.lang.String r7 = ""
            java.lang.String r7 = com.amap.api.col.p0003sl.dl.a(r10, r6, r5, r7)
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L44
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L43
            goto L44
        L43:
            r2 = r9
        L44:
            java.lang.String r9 = "/global.db"
            if (r2 == 0) goto L49
            goto L4f
        L49:
            boolean r7 = checkEngineRes(r1)
            if (r7 != 0) goto Lc3
        L4f:
            android.content.res.AssetManager r7 = r10.getAssets()     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            java.lang.String r8 = "ae/res.zip"
            java.io.InputStream r3 = r7.open(r8)     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            java.lang.String r7 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            com.autonavi.base.amap.mapcore.FileUtil.decompress(r3, r7)     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            r7.<init>()     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            r7.append(r1)     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            r7.append(r9)     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            java.lang.String r9 = r7.toString()     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            com.autonavi.base.amap.mapcore.AeUtil.global_db_path = r9     // Catch: java.lang.Throwable -> L74 java.lang.OutOfMemoryError -> L76 java.lang.Exception -> L96
            if (r3 == 0) goto Ld4
            goto Lb2
        L74:
            r9 = move-exception
            goto Lb8
        L76:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L74
            com.amap.api.col.p0003sl.dv.a(r9)     // Catch: java.lang.Throwable -> L74
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L74
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L74
            r1.append(r9)     // Catch: java.lang.Throwable -> L74
            java.lang.String r9 = r1.toString()     // Catch: java.lang.Throwable -> L74
            com.amap.api.col.p0003sl.ds.c(r10, r9)     // Catch: java.lang.Throwable -> L74
            if (r3 == 0) goto Ld4
        L92:
            r3.close()     // Catch: java.io.IOException -> Lb3
            goto Ld4
        L96:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L74
            com.amap.api.col.p0003sl.dv.a(r9)     // Catch: java.lang.Throwable -> L74
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L74
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L74
            r1.append(r9)     // Catch: java.lang.Throwable -> L74
            java.lang.String r9 = r1.toString()     // Catch: java.lang.Throwable -> L74
            com.amap.api.col.p0003sl.ds.c(r10, r9)     // Catch: java.lang.Throwable -> L74
            if (r3 == 0) goto Ld4
        Lb2:
            goto L92
        Lb3:
            r9 = move-exception
            r9.printStackTrace()
            goto Ld4
        Lb8:
            if (r3 == 0) goto Lc2
            r3.close()     // Catch: java.io.IOException -> Lbe
            goto Lc2
        Lbe:
            r10 = move-exception
            r10.printStackTrace()
        Lc2:
            throw r9
        Lc3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            com.autonavi.base.amap.mapcore.AeUtil.global_db_path = r9
        Ld4:
            if (r2 == 0) goto Ld9
            com.amap.api.col.p0003sl.dl.a(r10, r6, r5, r4)
        Ld9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.base.amap.mapcore.AeUtil.loadEngineRes(java.lang.String, android.content.Context):void");
    }

    private static String getAssetsGlobalDbMd5(Context context) {
        return new String(FileUtil.readFileContentsFromAssets(context, AMAP_ASSETS_DB_CK_PATH));
    }

    private static boolean checkEngineRes(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2 != null && file2.getName().contains(AMAP_GLOBAL_DB_NAME)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadAndSaveIntersectionRes(String str, String str2, Context context) {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            String[] list = context.getAssets().list(str);
            if (list == null) {
                return;
            }
            for (String str3 : list) {
                readAssetsFileAndSave(str + "/" + str3, new File(str2, str3).getAbsolutePath(), context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readAssetsFileAndSave(String str, String str2, Context context) {
        Throwable th;
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        InputStream inputStream = null;
        try {
            InputStream open = context.getAssets().open(str);
            try {
                File file = new File(str2);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = open.read(bArr, 0, 1024);
                        if (read <= 0) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, read);
                        }
                    }
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = open;
                    try {
                        th.printStackTrace();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                    } finally {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }
}
