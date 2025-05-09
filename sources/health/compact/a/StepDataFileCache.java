package health.compact.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.health.manager.util.TimeUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public class StepDataFileCache {
    private Context c;
    private String d = "tmpFileName";
    private String e;

    public StepDataFileCache(Context context, String str) {
        this.c = null;
        this.e = "defaultFileName";
        if (context != null && str != null) {
            this.c = context;
            this.e = str;
            c();
            File fileStreamPath = context.getFileStreamPath(this.e);
            if (fileStreamPath == null || !fileStreamPath.exists()) {
                com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "fileUsed not exists");
                return;
            }
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "(context == null) && (name == null)");
    }

    private void c() {
        if (a()) {
            b();
        }
    }

    private boolean b() {
        File file;
        String str;
        Context context = this.c;
        if (context != null && (str = this.e) != null) {
            file = context.getFileStreamPath(str);
        } else {
            com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "(mContext == null) || (mFileName == null)");
            file = null;
        }
        if (file == null || !file.exists()) {
            com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "isFileSizeValid no file,do not need check");
            return true;
        }
        long length = file.length();
        if (length > 512000) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "fileSize invalidate:", Long.valueOf(length));
            if (!file.delete()) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "fileSize invalidate,delete failed");
                return false;
            }
        }
        return true;
    }

    private boolean a() {
        File file;
        File file2;
        Context context = this.c;
        if (context != null && this.e != null) {
            file = context.getFileStreamPath(this.d);
            file2 = this.c.getFileStreamPath(this.e);
        } else {
            com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "(mContext == null) || (mFileName == null)");
            file = null;
            file2 = null;
        }
        boolean z = file != null && file.exists();
        boolean z2 = file2 != null && file2.exists();
        if (z2 && z) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "crash occur double file");
            if (!file.delete()) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "delete fileTmp failed");
                return false;
            }
        } else if (!z2 && z) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "crash occur only tmp file exists");
            if (file2 != null && !file.renameTo(file2)) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "rename failed");
                return false;
            }
        } else {
            com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "crash occur only other file exists");
        }
        return true;
    }

    public int akO_(Context context, SparseArray<OneMinuteStepData> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "(cache == null) || (cache.size() <= 0)");
            return 0;
        }
        akM_(context, sparseArray);
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean akN_(android.content.Context r10, android.util.SparseArray<health.compact.a.OneMinuteStepData> r11) {
        /*
            Method dump skipped, instructions count: 313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.StepDataFileCache.akN_(android.content.Context, android.util.SparseArray):boolean");
    }

    private boolean c(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "clearTempCacheFile context == null");
            return false;
        }
        File fileStreamPath = context.getFileStreamPath(this.d);
        if (!fileStreamPath.exists()) {
            com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "isClearTempCacheFile no file exist");
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "isClearTempCacheFile delete excute");
        return fileStreamPath.delete();
    }

    private void akM_(Context context, SparseArray<OneMinuteStepData> sparseArray) {
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "flushDataFromCacheToFile");
        if (sparseArray == null || context == null) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "flushDataFromCacheToFile,cache == null || context == null");
            return;
        }
        if (!akN_(context, sparseArray)) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "generateTmpCacheFile failed,exit");
            return;
        }
        if (!e(context)) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "deleteCacheFile failed,exit");
            return;
        }
        File fileStreamPath = context.getFileStreamPath(this.d);
        String str = this.e;
        if (str != null) {
            if (!fileStreamPath.renameTo(context.getFileStreamPath(str))) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "failed to rename from tmpFile to cache");
                return;
            } else {
                com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "success to rename");
                return;
            }
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "mFileName == null");
    }

    public void d(Context context, StepDataCache stepDataCache, long j, long j2) {
        String str;
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "initMemoryFromFile");
        if (context != null && (str = this.e) != null) {
            File fileStreamPath = context.getFileStreamPath(str);
            if (fileStreamPath == null || !fileStreamPath.exists()) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "file not exists");
                return;
            }
            long a2 = TimeUtil.a(j);
            long a3 = TimeUtil.a(j2);
            String[] a4 = a(context);
            if (a4 == null || a4.length <= 0) {
                return;
            }
            d(stepDataCache, a2, a3, a4);
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "(context == null) || (mFileName == null)");
    }

    private void d(StepDataCache stepDataCache, long j, long j2, String[] strArr) {
        String[] split;
        long parseLong;
        for (String str : strArr) {
            if (str != null && (split = str.split(",")) != null && split.length == 5) {
                String trim = split[0].trim();
                if (TextUtils.isEmpty(trim)) {
                    parseLong = 0;
                } else {
                    try {
                        parseLong = Long.parseLong(trim);
                    } catch (NumberFormatException unused) {
                        com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "NumberFormatException");
                        return;
                    }
                }
                if (parseLong >= j && parseLong <= j2) {
                    try {
                        stepDataCache.e(Integer.parseInt(split[1].trim()), parseLong * 60000, Integer.parseInt(split[2].trim()), Integer.parseInt(split[3].trim()), Integer.parseInt(split[4].trim()));
                    } catch (NumberFormatException e) {
                        com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "NumberFormatException = ", LogAnonymous.b((Throwable) e));
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x00f3: MOVE (r4 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:64:0x00f3 */
    /* JADX WARN: Type inference failed for: r12v0, types: [health.compact.a.StepDataFileCache] */
    /* JADX WARN: Type inference failed for: r13v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v24, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v29 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v8 */
    private String[] a(Context context) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        UnsupportedOperationException e;
        BufferedReader bufferedReader;
        IndexOutOfBoundsException e2;
        IOException e3;
        BufferedReader bufferedReader2;
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "parseCacheFile enter...");
        BufferedReader bufferedReader3 = null;
        r4 = null;
        BufferedReader bufferedReader4 = null;
        bufferedReader3 = null;
        r4 = null;
        String[] strArr = null;
        try {
            try {
                try {
                    String str = this.e;
                    if (str != null) {
                        context = context.openFileInput(str);
                    } else {
                        com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "mFileName == null");
                        context = 0;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader4 = bufferedReader2;
                }
                try {
                } catch (FileNotFoundException unused) {
                    inputStreamReader = null;
                    bufferedReader = null;
                    com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile FileNotFoundException");
                    d(bufferedReader, context, inputStreamReader);
                    return strArr;
                } catch (IOException e4) {
                    bufferedReader = null;
                    e3 = e4;
                    inputStreamReader = null;
                } catch (IndexOutOfBoundsException e5) {
                    bufferedReader = null;
                    e2 = e5;
                    inputStreamReader = null;
                } catch (UnsupportedOperationException e6) {
                    bufferedReader = null;
                    e = e6;
                    inputStreamReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamReader = null;
                    fileInputStream = context;
                    d(bufferedReader3, fileInputStream, inputStreamReader);
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                context = 0;
            } catch (IOException e7) {
                e3 = e7;
                context = 0;
                inputStreamReader = null;
                bufferedReader = null;
            } catch (IndexOutOfBoundsException e8) {
                e2 = e8;
                context = 0;
                inputStreamReader = null;
                bufferedReader = null;
            } catch (UnsupportedOperationException e9) {
                e = e9;
                context = 0;
                inputStreamReader = null;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                inputStreamReader = null;
            }
            if (context == 0) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_DataFileCache", "fileInputStream == null");
                d(null, context, null);
                return null;
            }
            inputStreamReader = new InputStreamReader((InputStream) context, "UTF-8");
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                try {
                    StringBuffer stringBuffer = new StringBuffer(16);
                    char[] cArr = new char[1024];
                    while (true) {
                        int read = bufferedReader.read(cArr);
                        if (read == -1) {
                            break;
                        }
                        stringBuffer.append(cArr, 0, read);
                    }
                    String trim = stringBuffer.toString().trim();
                    if (trim != null) {
                        strArr = trim.split("#");
                    }
                } catch (FileNotFoundException unused3) {
                    com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile FileNotFoundException");
                    d(bufferedReader, context, inputStreamReader);
                    return strArr;
                } catch (IOException e10) {
                    e3 = e10;
                    com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile IOException", LogAnonymous.b((Throwable) e3));
                    context = context;
                    d(bufferedReader, context, inputStreamReader);
                    return strArr;
                } catch (IndexOutOfBoundsException e11) {
                    e2 = e11;
                    com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile IndexOutOfBoundsException", e2.getMessage());
                    context = context;
                    d(bufferedReader, context, inputStreamReader);
                    return strArr;
                } catch (UnsupportedOperationException e12) {
                    e = e12;
                    com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile UnsupportedOperationException", e.getMessage());
                    context = context;
                    d(bufferedReader, context, inputStreamReader);
                    return strArr;
                }
            } catch (FileNotFoundException unused4) {
                bufferedReader = null;
            } catch (IOException e13) {
                e3 = e13;
                bufferedReader = null;
            } catch (IndexOutOfBoundsException e14) {
                e2 = e14;
                bufferedReader = null;
            } catch (UnsupportedOperationException e15) {
                e = e15;
                bufferedReader = null;
            } catch (Throwable th4) {
                th = th4;
                bufferedReader3 = bufferedReader4;
                fileInputStream = context;
                d(bufferedReader3, fileInputStream, inputStreamReader);
                throw th;
            }
            d(bufferedReader, context, inputStreamReader);
            return strArr;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader3 = bufferedReader;
            fileInputStream = context;
        }
    }

    private void d(BufferedReader bufferedReader, FileInputStream fileInputStream, InputStreamReader inputStreamReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (FileNotFoundException unused) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile reader.close() fileNotFoundException");
            } catch (IOException e) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", LogAnonymous.b((Throwable) e));
            }
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (FileNotFoundException unused2) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile fileInputStream.close() fileNotFoundException");
            } catch (IOException e2) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", LogAnonymous.b((Throwable) e2));
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (FileNotFoundException unused3) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", "parseCacheFile inputStreamReader.close() fileNotFoundException");
            } catch (IOException e3) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_DataFileCache", LogAnonymous.b((Throwable) e3));
            }
        }
    }

    private boolean e(Context context) {
        File file;
        String str;
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "clearFileCache");
        if (context != null && (str = this.e) != null) {
            file = context.getFileStreamPath(str);
        } else {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "(context == null) || (mFileName == null)");
            file = null;
        }
        if (file == null || !file.exists()) {
            com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "clearFileCache file not exists");
            return true;
        }
        boolean delete = file.delete();
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "clearFileCache delete!", Boolean.valueOf(delete));
        return delete;
    }

    public boolean b(Context context) {
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataFileCache", "clear");
        if (!c(context)) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "isClearTempCacheFile in clear failed,exit");
            return false;
        }
        if (e(context)) {
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.h("Step_DataFileCache", "clearCacheFile in clear failed,exit");
        return false;
    }
}
