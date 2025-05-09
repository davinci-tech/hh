package com.huawei.watchface.mvp.model.latona.provider;

import android.text.TextUtils;
import com.huawei.watchface.ch;
import com.huawei.watchface.ci;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.IPackageNamePathCallback;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/* loaded from: classes9.dex */
public class ResourceResolver {
    private static final int CODE_UNZIP_FAILED = 0;
    private static final String TAG = "ResourceResolver";
    private static final String UTF_8 = "utf-8";
    private static final String WATCHFACE_CONFIG_NAME = "watch_face_config.xml";
    private static final String ZIPENTRY_SUFFIX = ".zip";
    private IPackageNamePathCallback mPathCallback;
    private String mWatchFacePackageName;
    private String mWatchFaceParsingPath;
    private String mWatchFacePath;

    public ResourceResolver(String str, String str2, String str3, IPackageNamePathCallback iPackageNamePathCallback) {
        this.mWatchFacePath = str;
        this.mWatchFacePackageName = str2;
        this.mWatchFaceParsingPath = str3;
        this.mPathCallback = iPackageNamePathCallback;
    }

    private InputStream getInputStreamFromCache(File file, String str) {
        if (file == null || !file.exists() || !file.isDirectory() || TextUtils.isEmpty(str)) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists() || !file2.isFile()) {
            return null;
        }
        try {
            return new FileInputStream(new File(CommonUtils.filterFilePath(file2.getCanonicalPath())));
        } catch (FileNotFoundException unused) {
            HwLog.e(TAG, "getInputStream, FileNotFoundException");
            return null;
        } catch (IOException unused2) {
            HwLog.e(TAG, "getInputStream, IOException");
            return null;
        }
    }

    private <T> T parserXml(InputStream inputStream, Class<T> cls) {
        InputStreamReader inputStreamReader;
        if (inputStream == null) {
            HwLog.i(TAG, "parserXml inputStream is null");
            return null;
        }
        ci ciVar = new ci();
        ciVar.a(true);
        ch a2 = ciVar.a();
        try {
            inputStreamReader = new InputStreamReader(inputStream, UTF_8);
        } catch (UnsupportedEncodingException unused) {
            HwLog.e(TAG, "inputStreamReader encode failed");
            inputStreamReader = null;
        }
        if (inputStreamReader == null) {
            HwLog.i(TAG, "inputStreamReader is null");
            return null;
        }
        return (T) a2.a(cls, inputStreamReader);
    }

    public WatchFaceThemeProviders parseConfigFile() {
        HwLog.i(TAG, "parserConfigFile");
        File file = new File(this.mWatchFacePath);
        if (!file.exists() && !file.mkdir()) {
            return null;
        }
        File file2 = new File(file, this.mWatchFacePackageName);
        File file3 = new File(CommonUtils.filterFilePath(this.mWatchFaceParsingPath));
        if (!file3.exists()) {
            HwLog.i(TAG, "parserConfigFile, isParsingFileMkdir:" + file3.mkdir());
        }
        File file4 = new File(file3, this.mWatchFacePackageName);
        try {
            String canonicalPath = file4.getCanonicalPath();
            String str = canonicalPath + ".zip";
            FileHelper.copyFileToParsingDir(CommonUtils.filterFilePath(file2.getCanonicalPath()), CommonUtils.filterFilePath(str));
            if (FileHelper.a(CommonUtils.filterFilePath(str), CommonUtils.filterFilePath(canonicalPath)) > 0) {
                InputStream inputStreamFromCache = getInputStreamFromCache(file4, WATCHFACE_CONFIG_NAME);
                if (inputStreamFromCache == null) {
                    HwLog.i(TAG, "parserConfigFile, inputStream is null, try new format.");
                    inputStreamFromCache = getInputStreamFromCache(new File(CommonUtils.filterFilePath(canonicalPath) + "/watchface"), WATCHFACE_CONFIG_NAME);
                    if (this.mPathCallback != null) {
                        this.mWatchFacePackageName += "/watchface";
                        this.mPathCallback.notifyPathChanged();
                    }
                }
                WatchFaceThemeProviders watchFaceThemeProviders = (WatchFaceThemeProviders) parserXml(inputStreamFromCache, WatchFaceThemeProviders.class);
                if (inputStreamFromCache != null) {
                    inputStreamFromCache.close();
                }
                if (watchFaceThemeProviders != null) {
                    return watchFaceThemeProviders;
                }
            }
        } catch (IOException unused) {
            HwLog.e(TAG, "parseConfigFile unzipFailed");
        } catch (Exception unused2) {
            HwLog.e(TAG, "parseConfigFile exception");
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.watchface.mvp.model.latona.LatonaWatchFace parserLetonaConfigFile() {
        /*
            r11 = this;
            java.lang.String r0 = "/watchface"
            java.lang.String r1 = "watch_face_config.xml"
            java.lang.String r2 = "inputStream closeFailed"
            java.lang.String r3 = "parserLetonaConfigFile()"
            java.lang.String r4 = "ResourceResolver"
            com.huawei.watchface.utils.HwLog.i(r4, r3)
            java.io.File r3 = new java.io.File
            java.lang.String r5 = r11.mWatchFacePath
            r3.<init>(r5)
            boolean r5 = r3.exists()
            r6 = 0
            if (r5 != 0) goto L22
            boolean r5 = r3.mkdir()
            if (r5 != 0) goto L22
            return r6
        L22:
            java.io.File r5 = new java.io.File
            java.lang.String r7 = r11.mWatchFacePackageName
            r5.<init>(r3, r7)
            java.lang.String r3 = r5.getCanonicalPath()     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            r7.<init>()     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            r7.append(r3)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.lang.String r8 = ".zip"
            r7.append(r8)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            com.huawei.watchface.utils.FileHelper.copyFileToParsingDir(r3, r7)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.io.File r8 = new java.io.File     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            r8.<init>(r3)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            boolean r9 = r8.exists()     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            if (r9 == 0) goto L61
            boolean r8 = r8.delete()     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.lang.String r10 = "delete："
            r9.<init>(r10)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            r9.append(r8)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.lang.String r8 = r9.toString()     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            com.huawei.watchface.utils.HwLog.i(r4, r8)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
        L61:
            int r7 = com.huawei.watchface.utils.FileHelper.a(r7, r3)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            if (r7 <= 0) goto Ld2
            java.lang.String r7 = "parserLetonaConfigFile() > 0"
            com.huawei.watchface.utils.HwLog.i(r4, r7)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            java.io.InputStream r5 = r11.getInputStreamFromCache(r5, r1)     // Catch: java.lang.Throwable -> Lc1 java.io.IOException -> Lc3
            if (r5 != 0) goto Laf
            java.lang.String r7 = "parserLetonaConfigFile, inputStream is null, try new format."
            com.huawei.watchface.utils.HwLog.i(r4, r7)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            java.io.File r7 = new java.io.File     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r8.<init>()     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            java.lang.String r3 = com.huawei.watchface.utils.CommonUtils.filterFilePath(r3)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r8.append(r3)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r8.append(r0)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            java.lang.String r3 = r8.toString()     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r7.<init>(r3)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            java.io.InputStream r5 = r11.getInputStreamFromCache(r7, r1)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            com.huawei.watchface.utils.callback.IPackageNamePathCallback r1 = r11.mPathCallback     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            if (r1 == 0) goto Laf
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r1.<init>()     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            java.lang.String r3 = r11.mWatchFacePackageName     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r1.append(r3)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r1.append(r0)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            java.lang.String r0 = r1.toString()     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r11.mWatchFacePackageName = r0     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            com.huawei.watchface.utils.callback.IPackageNamePathCallback r0 = r11.mPathCallback     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            r0.notifyPathChanged()     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
        Laf:
            java.lang.Class<com.huawei.watchface.mvp.model.latona.LatonaWatchFace> r0 = com.huawei.watchface.mvp.model.latona.LatonaWatchFace.class
            java.lang.Object r0 = r11.parserXml(r5, r0)     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            com.huawei.watchface.mvp.model.latona.LatonaWatchFace r0 = (com.huawei.watchface.mvp.model.latona.LatonaWatchFace) r0     // Catch: java.io.IOException -> Lc4 java.lang.Throwable -> Ld3
            if (r5 == 0) goto Lc0
            r5.close()     // Catch: java.io.IOException -> Lbd
            goto Lc0
        Lbd:
            com.huawei.watchface.utils.HwLog.e(r4, r2)
        Lc0:
            return r0
        Lc1:
            r0 = move-exception
            goto Ld5
        Lc3:
            r5 = r6
        Lc4:
            java.lang.String r0 = "unzipFailed"
            com.huawei.watchface.utils.HwLog.i(r4, r0)     // Catch: java.lang.Throwable -> Ld3
            if (r5 == 0) goto Ld2
            r5.close()     // Catch: java.io.IOException -> Lcf
            goto Ld2
        Lcf:
            com.huawei.watchface.utils.HwLog.e(r4, r2)
        Ld2:
            return r6
        Ld3:
            r0 = move-exception
            r6 = r5
        Ld5:
            if (r6 == 0) goto Lde
            r6.close()     // Catch: java.io.IOException -> Ldb
            goto Lde
        Ldb:
            com.huawei.watchface.utils.HwLog.e(r4, r2)
        Lde:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.mvp.model.latona.provider.ResourceResolver.parserLetonaConfigFile():com.huawei.watchface.mvp.model.latona.LatonaWatchFace");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a4 A[Catch: IOException -> 0x00ad, TryCatch #8 {IOException -> 0x00ad, blocks: (B:50:0x009f, B:41:0x00a4, B:43:0x00a9), top: B:49:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a9 A[Catch: IOException -> 0x00ad, TRY_LEAVE, TryCatch #8 {IOException -> 0x00ad, blocks: (B:50:0x009f, B:41:0x00a4, B:43:0x00a9), top: B:49:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean writeBackConfigFile(com.huawei.watchface.mvp.model.latona.provider.WatchFaceThemeProviders r11) {
        /*
            r10 = this;
            java.lang.String r0 = "writeBackConfigFile, stream or buffer close exception"
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r10.mWatchFaceParsingPath
            r2.append(r3)
            java.lang.String r3 = java.io.File.separator
            r2.append(r3)
            java.lang.String r3 = r10.mWatchFacePackageName
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "watch_face_config.xml"
            r1.<init>(r2, r3)
            boolean r2 = r1.exists()
            r3 = 0
            java.lang.String r4 = "ResourceResolver"
            if (r2 != 0) goto L30
            java.lang.String r11 = "writeBackConfigFile failed, configFile doesn't exist"
            com.huawei.watchface.utils.HwLog.i(r4, r11)
            return r3
        L30:
            r2 = 0
            com.huawei.watchface.ci r5 = new com.huawei.watchface.ci     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7e
            r5.<init>()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7e
            r6 = 1
            r5.a(r6)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7e
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7e
            java.lang.String r1 = r1.getCanonicalPath()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7e
            java.lang.String r1 = com.huawei.watchface.utils.CommonUtils.filterFilePath(r1)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7e
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7e
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L78
            java.lang.String r8 = "utf-8"
            r1.<init>(r7, r8)     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L78
            java.io.BufferedWriter r8 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L72
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L72
            com.huawei.watchface.ch r2 = r5.a()     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6b
            r2.a(r8, r11)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6b
            r1.close()     // Catch: java.io.IOException -> L64
            r8.close()     // Catch: java.io.IOException -> L64
            r7.close()     // Catch: java.io.IOException -> L64
            goto L67
        L64:
            com.huawei.watchface.utils.HwLog.e(r4, r0)
        L67:
            return r6
        L68:
            r11 = move-exception
            r2 = r8
            goto L6e
        L6b:
            r2 = r8
            goto L72
        L6d:
            r11 = move-exception
        L6e:
            r9 = r2
            r2 = r1
            r1 = r9
            goto L9d
        L72:
            r11 = r2
            r2 = r1
            goto L80
        L75:
            r11 = move-exception
            r1 = r2
            goto L9d
        L78:
            r11 = r2
            goto L80
        L7a:
            r11 = move-exception
            r1 = r2
            r7 = r1
            goto L9d
        L7e:
            r11 = r2
            r7 = r11
        L80:
            java.lang.String r1 = "writeBackConfigFile error"
            com.huawei.watchface.utils.HwLog.e(r4, r1)     // Catch: java.lang.Throwable -> L99
            if (r2 == 0) goto L8a
            r2.close()     // Catch: java.io.IOException -> L95
        L8a:
            if (r11 == 0) goto L8f
            r11.close()     // Catch: java.io.IOException -> L95
        L8f:
            if (r7 == 0) goto L98
            r7.close()     // Catch: java.io.IOException -> L95
            goto L98
        L95:
            com.huawei.watchface.utils.HwLog.e(r4, r0)
        L98:
            return r3
        L99:
            r1 = move-exception
            r9 = r1
            r1 = r11
            r11 = r9
        L9d:
            if (r2 == 0) goto La2
            r2.close()     // Catch: java.io.IOException -> Lad
        La2:
            if (r1 == 0) goto La7
            r1.close()     // Catch: java.io.IOException -> Lad
        La7:
            if (r7 == 0) goto Lb0
            r7.close()     // Catch: java.io.IOException -> Lad
            goto Lb0
        Lad:
            com.huawei.watchface.utils.HwLog.e(r4, r0)
        Lb0:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.mvp.model.latona.provider.ResourceResolver.writeBackConfigFile(com.huawei.watchface.mvp.model.latona.provider.WatchFaceThemeProviders):boolean");
    }
}
