package defpackage;

import android.text.TextUtils;
import com.huawei.android.hicloud.sync.util.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes8.dex */
public class eyi {
    public static byte[] a(String str) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        eym.b(FileUtil.TAG, "getByteArray filePath: " + str);
        File file = new File(str);
        byte[] bArr = new byte[0];
        if (!file.exists()) {
            eym.e(FileUtil.TAG, "getByteArray, file is not exists");
            return bArr;
        }
        if (file.length() == 0) {
            eym.e(FileUtil.TAG, "getByteArray, file is empty");
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream;
                byteArrayOutputStream2 = byteArrayOutputStream2;
            }
        } catch (IOException unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr2);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                fileInputStream.close();
            } catch (IOException unused3) {
                eym.c(FileUtil.TAG, "getByteArray, close stream Exception");
            }
        } catch (IOException unused4) {
            byteArrayOutputStream2 = byteArrayOutputStream;
            eym.c(FileUtil.TAG, "getByteArray, read data Exception");
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException unused5) {
                    eym.c(FileUtil.TAG, "getByteArray, close stream Exception");
                }
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return bArr;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException unused6) {
                    eym.c(FileUtil.TAG, "getByteArray, close stream Exception");
                    throw th;
                }
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return bArr;
    }

    public static boolean d(String str, byte[] bArr, int i) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(str);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        try {
            fileOutputStream.write(bArr, 0, i);
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException unused3) {
                eym.c(FileUtil.TAG, "write, close fos IOException");
                return true;
            }
        } catch (FileNotFoundException unused4) {
            fileOutputStream2 = fileOutputStream;
            eym.c(FileUtil.TAG, "write, FileNotFoundException: " + str);
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException unused5) {
                    eym.c(FileUtil.TAG, "write, close fos IOException");
                }
            }
            return false;
        } catch (IOException unused6) {
            fileOutputStream2 = fileOutputStream;
            eym.c(FileUtil.TAG, "write, IOException");
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException unused7) {
                    eym.c(FileUtil.TAG, "write, close fos IOException");
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused8) {
                    eym.c(FileUtil.TAG, "write, close fos IOException");
                }
            }
            throw th;
        }
    }

    public static String d(String str) {
        String e = e(str);
        if (TextUtils.isEmpty(e)) {
            eym.c(FileUtil.TAG, "filterFilePath, legalPath is empty");
            return null;
        }
        try {
            return new File(e).getCanonicalPath();
        } catch (IOException unused) {
            eym.c(FileUtil.TAG, "filterFilePath IOException");
            return null;
        }
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            eym.c(FileUtil.TAG, "filterFullFilePath sourcePath is empty");
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(String.valueOf(str.charAt(i)))) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }
}
