package com.huawei.phoneservice.feedbackcommon.utils;

import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import defpackage.ura;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ZipUtil {
    public static final int DEFAULT_SPLIT_VOLUME_SIZE = 33554432;
    private static final int FILE_MIN_NUMBER = 1;
    private static final int INITIAL_CAPACITY = 16;
    private static final String TAG = "ZipUtil";
    private static long size;

    public static void setSize(long j) {
        size = j;
    }

    public static String mapToString(String str) {
        Set<Map.Entry<String, Object>> entrySet = getMap(str).entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : entrySet) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        return sb.length() != 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    public static int isZipSplitVolumeKeepFolder(String str, String str2, long j) {
        String message;
        try {
            File file = new File(str);
            File file2 = new File(str2);
            if (file2.exists()) {
                file2.delete();
            }
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            ura uraVar = new ura(file2);
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.a(false);
            zipParameters.e(CompressionMethod.DEFLATE);
            zipParameters.c(CompressionLevel.MAXIMUM);
            if (0 == j) {
                uraVar.d(file, zipParameters, true, 33554432L);
            } else {
                uraVar.d(file, zipParameters, true, j);
            }
            return 2;
        } catch (ZipException e) {
            message = e.getMessage();
            com.huawei.phoneservice.faq.base.util.i.c(TAG, message);
            return 1;
        } catch (Exception e2) {
            message = e2.getMessage();
            com.huawei.phoneservice.faq.base.util.i.c(TAG, message);
            return 1;
        }
    }

    private static boolean isValidFile(File file) {
        return file != null && file.exists() && file.length() > 0;
    }

    public static void getSize(File[] fileArr) {
        if (fileArr == null || fileArr.length <= 0) {
            return;
        }
        for (File file : fileArr) {
            if (file.isFile()) {
                size += file.length();
            }
            if (file.isDirectory()) {
                getSize(file.listFiles());
            }
        }
    }

    public static long getSize() {
        return size;
    }

    private static Map<String, Object> getMap(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap(16);
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next));
            }
            return hashMap;
        } catch (JSONException e) {
            com.huawei.phoneservice.faq.base.util.i.c(TAG, e.getMessage());
            return null;
        }
    }

    public static Map<String, String> getCommonHeaderParameter(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-type", FeedbackWebConstants.HEAD_CONTENT_TYPE_VALUE);
        if (str != null) {
            hashMap.put("Authorization", str);
        }
        return hashMap;
    }

    public static int fileToZip(String str, String str2, String str3, long j) {
        String message;
        String str4;
        try {
            File canonicalFile = new File(str).getCanonicalFile();
            if (canonicalFile.exists()) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                String str5 = File.separator;
                sb.append(str5);
                sb.append(str3);
                sb.append(".zip");
                File canonicalFile2 = new File(sb.toString()).getCanonicalFile();
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (canonicalFile2.exists()) {
                    canonicalFile2.delete();
                }
                File[] listFiles = canonicalFile.listFiles();
                if (listFiles != null && listFiles.length >= 1) {
                    return isZipSplitVolumeKeepFolder(str, str2 + str5 + str3 + ".zip", j);
                }
                str4 = "SOURCE_FOLDER IS EMPTY";
            } else {
                str4 = "SOURCE_FILE NOT EXISTS";
            }
            com.huawei.phoneservice.faq.base.util.i.e(TAG, str4);
        } catch (FileNotFoundException unused) {
            message = "FileNotFoundException Exception";
            com.huawei.phoneservice.faq.base.util.i.c(TAG, message);
            return 0;
        } catch (IOException e) {
            message = e.getMessage();
            com.huawei.phoneservice.faq.base.util.i.c(TAG, message);
            return 0;
        } catch (Exception e2) {
            message = e2.getMessage();
            com.huawei.phoneservice.faq.base.util.i.c(TAG, message);
            return 0;
        }
        return 0;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:6|(3:7|8|9)|(4:10|11|(2:12|(1:14)(1:15))|(2:17|18))|20|21|22) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0081, code lost:
    
        com.huawei.phoneservice.faq.base.util.i.c(com.huawei.phoneservice.feedbackcommon.utils.ZipUtil.TAG, r8.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007a, code lost:
    
        if (r4 == null) goto L36;
     */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x003b: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:35:0x003b */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String fileSha256(java.io.File r8) {
        /*
            java.lang.String r0 = "ZipUtil"
            boolean r1 = isValidFile(r8)
            java.lang.String r2 = "FileSHA256"
            if (r1 != 0) goto L12
            java.lang.String r8 = "file is not valid"
            com.huawei.phoneservice.faq.base.util.i.e(r2, r8)
            java.lang.String r8 = ""
            return r8
        L12:
            r1 = 0
            java.lang.String r3 = "SHA-256"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43 java.security.NoSuchAlgorithmException -> L60
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43 java.security.NoSuchAlgorithmException -> L60
            r4.<init>(r8)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43 java.security.NoSuchAlgorithmException -> L60
            r8 = 8192(0x2000, float:1.148E-41)
            byte[] r8 = new byte[r8]     // Catch: java.io.IOException -> L3d java.security.NoSuchAlgorithmException -> L3f java.lang.Throwable -> L89
            r5 = 0
            r6 = r5
        L24:
            int r7 = r4.read(r8)     // Catch: java.io.IOException -> L3d java.security.NoSuchAlgorithmException -> L3f java.lang.Throwable -> L89
            if (r7 <= 0) goto L2f
            r3.update(r8, r5, r7)     // Catch: java.io.IOException -> L3d java.security.NoSuchAlgorithmException -> L3f java.lang.Throwable -> L89
            r6 = 1
            goto L24
        L2f:
            if (r6 == 0) goto L7c
            byte[] r8 = r3.digest()     // Catch: java.io.IOException -> L3d java.security.NoSuchAlgorithmException -> L3f java.lang.Throwable -> L89
            java.lang.String r8 = com.huawei.secure.android.common.util.HexUtil.byteArray2HexStr(r8)     // Catch: java.io.IOException -> L3d java.security.NoSuchAlgorithmException -> L3f java.lang.Throwable -> L89
            r1 = r8
            goto L7c
        L3b:
            r1 = r4
            goto L8b
        L3d:
            r8 = move-exception
            goto L45
        L3f:
            r8 = move-exception
            goto L62
        L41:
            r8 = move-exception
            goto L8b
        L43:
            r8 = move-exception
            r4 = r1
        L45:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89
            r3.<init>()     // Catch: java.lang.Throwable -> L89
            java.lang.String r5 = "IOException"
            r3.append(r5)     // Catch: java.lang.Throwable -> L89
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L89
            r3.append(r8)     // Catch: java.lang.Throwable -> L89
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> L89
            com.huawei.phoneservice.faq.base.util.i.e(r2, r8)     // Catch: java.lang.Throwable -> L89
            if (r4 == 0) goto L88
            goto L7c
        L60:
            r8 = move-exception
            r4 = r1
        L62:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89
            r3.<init>()     // Catch: java.lang.Throwable -> L89
            java.lang.String r5 = "NoSuchAlgorithmException"
            r3.append(r5)     // Catch: java.lang.Throwable -> L89
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L89
            r3.append(r8)     // Catch: java.lang.Throwable -> L89
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> L89
            com.huawei.phoneservice.faq.base.util.i.e(r2, r8)     // Catch: java.lang.Throwable -> L89
            if (r4 == 0) goto L88
        L7c:
            r4.close()     // Catch: java.io.IOException -> L80
            goto L88
        L80:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r8)
        L88:
            return r1
        L89:
            r8 = move-exception
            goto L3b
        L8b:
            if (r1 == 0) goto L99
            r1.close()     // Catch: java.io.IOException -> L91
            goto L99
        L91:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r0, r1)
        L99:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.ZipUtil.fileSha256(java.io.File):java.lang.String");
    }

    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        for (File file2 : listFiles) {
            deleteFile(file2);
        }
        return file.delete();
    }
}
