package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import com.huawei.secure.android.common.encrypt.rsa.RSASign;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.util.HexUtil;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmLicense;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ak {
    public static boolean a(File file, ByteArrayOutputStream byteArrayOutputStream) {
        a a2 = a(file);
        if (a2 != null) {
            String a3 = a2.a();
            HwLog.e("DrmGcmUtils", "decryptFileByLicenseKey() productId2：" + a3);
            b c = c(a3);
            if (c == null) {
                HwLog.e("DrmGcmUtils", "decryptFileByLicenseKey() info is null in decryptFile.");
                return false;
            }
            return a(file, byteArrayOutputStream, c);
        }
        HwLog.e("DrmGcmUtils", "decryptFileByLicenseKey() Not gcm decryption.");
        return false;
    }

    public static boolean a(File file, ByteArrayOutputStream byteArrayOutputStream, b bVar) {
        HwLog.i("DrmGcmUtils", "decryptCipherV2() in.");
        File b2 = PversionSdUtils.b(Environment.getApplicationContext().getCacheDir(), "cacheCipherV2");
        if (b2 == null) {
            HwLog.e("DrmGcmUtils", "decryptCipherV2() cacheCipherV2Dir is null");
            return false;
        }
        if (!b2.exists() && !b2.mkdirs()) {
            HwLog.e("DrmGcmUtils", "decryptCipherV2() !mkdirs");
            return false;
        }
        byte[] bArr = new byte[1024];
        try {
            a(PversionSdUtils.b(file), 74, bArr, byteArrayOutputStream, HexUtil.hexStr2ByteArray(bVar.a()), HexUtil.hexStr2ByteArray(bVar.b));
            HwLog.i("DrmGcmUtils", "decryptCipherV2() out.size=" + byteArrayOutputStream.size());
        } catch (IOException unused) {
            HwLog.e("DrmGcmUtils", "IOException 1111");
        }
        String str = new String(bArr, StandardCharsets.UTF_8);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean verifySign = RSASign.verifySign(byteArrayOutputStream.toByteArray(), an.c(str.trim()), (PublicKey) EncryptUtil.getPublicKey("MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAlPkfn+ejz+p1Ayo5+Ci4VxCbqPZTmeJRYz4pT0NtLrDsenBCdJ7A5Zzawy9bWkYgIkS4jazt4WKUuX58GU4ebXDEoQaO0vdZWblQMpdV2J09uI/DA4EjLq7PU2VQbEgFsC9wywaU1cTyhcfhx7+" + DensityUtil.b(R.array._2130968634_res_0x7f04003a)[0] + Environment.getApplicationContext().getResources().getString(R$string.GCMDecodeKeys)), true);
        StringBuilder sb = new StringBuilder("decryptCipherV2() check file rsa sign : ");
        sb.append(verifySign);
        HwLog.i("DrmGcmUtils", sb.toString());
        return verifySign;
    }

    public static void a(InputStream inputStream, int i, byte[] bArr, OutputStream outputStream, byte[] bArr2, byte[] bArr3) throws IOException {
        byte[] bArr4 = new byte[5242896];
        if (inputStream.read(bArr4, 0, i) == -1) {
            return;
        }
        boolean z = false;
        while (true) {
            int read = inputStream.read(bArr4);
            if (read == -1) {
                return;
            }
            byte[] decrypt = AesGcm.decrypt(Arrays.copyOfRange(bArr4, 0, read), bArr2, bArr3);
            if (!z) {
                System.arraycopy(decrypt, 0, bArr, 0, bArr.length);
                outputStream.write(decrypt, bArr.length, decrypt.length - bArr.length);
                z = true;
            } else {
                outputStream.write(decrypt, 0, decrypt.length);
            }
        }
    }

    public static a a(File file) {
        String a2 = a(file, 74);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        String trim = SafeString.substring(a2, 0, 32).trim();
        HwLog.i("DrmGcmUtils", "parserCipherV2HeaderInfo() --mark--" + trim);
        if (!a.c(trim)) {
            return null;
        }
        String trim2 = SafeString.substring(a2, 64, 66).trim();
        HwLog.i("DrmGcmUtils", "parserCipherV2HeaderInfo() --cipherVersion--" + trim2);
        if (!a.b(trim2)) {
            return null;
        }
        String trim3 = SafeString.substring(a2, 32, 64).trim();
        HwLog.i("DrmGcmUtils", "parserCipherV2HeaderInfo() --hitopId--" + trim3);
        if (!a.a(trim3)) {
            return null;
        }
        String trim4 = SafeString.substring(a2, 66, 70).trim();
        HwLog.i("DrmGcmUtils", "parserCipherV2HeaderInfo() --fileTypeStr--" + trim4);
        if (!a.d(trim4)) {
            return null;
        }
        String trim5 = SafeString.substring(a2, 70).trim();
        HwLog.i("DrmGcmUtils", "parserCipherV2HeaderInfo() --subTypeStr--" + trim5);
        return new a(trim, trim3, trim2, trim4, trim5);
    }

    private static b c(String str) {
        try {
            HwDrmLicense c = g.c(Environment.getApplicationContext(), str);
            if (c == null) {
                HwLog.e("DrmGcmUtils", "parseInfoDrmLocalLicense() hwDrmLicense is null in GetLocalLicense");
                return null;
            }
            b d = d(new String(c.getSecretInfo(), StandardCharsets.UTF_8));
            if (d == null) {
                HwLog.i("DrmGcmUtils", "parseInfoDrmLocalLicense() info == null ");
                return null;
            }
            d.a(str);
            d.c = al.a(c.getEncrkey()).toUpperCase(Locale.ENGLISH);
            HwLog.i("DrmGcmUtils", "parseInfoDrmLocalLicense() return info message in parseInfoDrmLocalLicense");
            return d;
        } catch (HwDrmException e) {
            HwLog.e("DrmGcmUtils", "parseInfoDrmLocalLicense() HwDrmException is caught in parseInfoDrmLocalLicense :" + HwLog.printException((Exception) e));
            return null;
        }
    }

    private static b d(String str) {
        b bVar = new b();
        try {
            bVar.b = new JSONObject(str).optString("iv");
            HwLog.i("DrmGcmUtils", "getPkgInfo() return info message in getPkgInfo");
            return bVar;
        } catch (JSONException e) {
            HwLog.e("DrmGcmUtils", "getPkgInfo() JSONException is caught in getPkgInfo " + HwLog.printException((Exception) e));
            return null;
        }
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.e("DrmGcmUtils", "getDecryptWatchFacePath() originPath null");
            return null;
        }
        return c(PversionSdUtils.getFile(str));
    }

    private static byte[] c(File file) {
        if (!file.exists()) {
            HwLog.e("DrmGcmUtils", "getDecryptFile() --encryptFile do not exists");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((int) file.length()) * 2);
        a(file, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] a(MappedByteBuffer mappedByteBuffer, int i, int i2) {
        byte[] bArr = new byte[i2];
        if (mappedByteBuffer != null) {
            mappedByteBuffer.position(i);
            mappedByteBuffer.get(bArr, 0, i2);
            HwLog.i("DrmGcmUtils", "getByteFromMapBuffer() get productIds or hMacSha message from encryptFile in getByteFromMapBuffer");
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0125 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0171 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.io.File r14, int r15) {
        /*
            Method dump skipped, instructions count: 404
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.ak.a(java.io.File, int):java.lang.String");
    }

    public static String b(File file) {
        if (file == null) {
            HwLog.e("DrmGcmUtils", "getFileName() file is null");
            return "";
        }
        return b(file.getPath());
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.e("DrmGcmUtils", "getFileName() file is null");
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? str : SafeString.substring(str, lastIndexOf + 1);
    }

    public static class a {
        public static final Pattern f = Pattern.compile("^[a-z0-9A-Z]+$");

        /* renamed from: a, reason: collision with root package name */
        String f10893a;
        String b;
        String c;
        String d;
        String e;

        public a(String str, String str2, String str3, String str4, String str5) {
            this.e = str;
            this.f10893a = str2;
            this.b = str3;
            this.c = str4;
            this.d = str5;
        }

        public String a() {
            return this.f10893a + this.c;
        }

        public static boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return f.matcher(str).matches();
        }

        public static boolean b(String str) {
            return "2".equals(str);
        }

        public static boolean c(String str) {
            return "themeV2Cipher".equals(str);
        }

        public static boolean d(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return TextUtils.isDigitsOnly(str);
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f10894a;
        private String b;
        private String c;

        private b() {
        }

        public void a(String str) {
            this.f10894a = str;
        }

        public String a() {
            return this.c;
        }
    }
}
