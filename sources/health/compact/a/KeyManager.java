package health.compact.a;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.utils.Constants;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.DbManager;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class KeyManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f13123a;
    private static final byte[] b;
    private static final List<String> c;
    private static final Uri d;
    private static final String e;
    private static boolean f;
    private static Map<Integer, byte[]> h;
    private static String j;

    static {
        String str = "content://" + BaseApplication.getAppPackage() + ".data.backup.provider/";
        e = str;
        f13123a = Uri.parse(str + "backupCp");
        d = Uri.parse(DbManager.e + "module_1009_backup_table");
        b = new byte[1];
        ArrayList arrayList = new ArrayList(10);
        c = arrayList;
        f = true;
        h = new HashMap();
        arrayList.add("com_huawei_health1.db");
        arrayList.add("com_huawei_health7.db");
        arrayList.add("com_huawei_health10.db");
        arrayList.add("com_huawei_health22.db");
        arrayList.add("com_huawei_health32.db");
        arrayList.add("com_huawei_health1020.db");
        arrayList.add("com_huawei_health10004.db");
        arrayList.add("com_huawei_health10031.db");
        arrayList.add("com_huawei_health10033.db");
        arrayList.add("com_huawei_health10034.db");
        arrayList.add("com_huawei_health20003.db");
        arrayList.add("com_huawei_health20004.db");
        arrayList.add("com_huawei_health20005.db");
        arrayList.add("com_huawei_health20008.db");
        arrayList.add("com_huawei_health100007.db");
        arrayList.add("com_huawei_health101010.db");
        arrayList.add("hihealth_003.db");
        arrayList.add("com_huawei_health_UdsDevicesDatas.db");
        arrayList.add("contact_sync_data.db");
        arrayList.add("calendar_sync_data.db");
    }

    private KeyManager() {
    }

    public static void b() {
        if (KeyStoreUtils.b) {
            return;
        }
        synchronized (b) {
            j = null;
            f = false;
        }
    }

    public static byte[] a(int i) {
        return d(i);
    }

    static byte[] d(int i) {
        byte[] bArr;
        synchronized (b) {
            try {
                if (KeyStoreUtils.b) {
                    bArr = c(i + 1000);
                } else {
                    bArr = a(i, true);
                }
            } catch (Exception unused) {
                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "getWorkKey Exception");
                bArr = null;
            }
        }
        return bArr;
    }

    private static byte[] c(int i) {
        byte[] bArr = h.get(Integer.valueOf(i));
        if ((bArr == null || bArr.length == 0) && (bArr = h(i)) != null && bArr.length > 0) {
            h.put(Integer.valueOf(i), bArr);
        }
        return bArr;
    }

    private static byte[] h(int i) {
        String k = k(i);
        if (TextUtils.isEmpty(k) || k.length() < 88) {
            return j(i);
        }
        return KeyStoreUtils.e(k.substring(0, 88));
    }

    private static byte[] a(int i, boolean z) {
        byte[] bArr = h.get(Integer.valueOf(i));
        if ((bArr == null || bArr.length == 0) && (bArr = c(i, z)) != null && bArr.length > 0) {
            h.put(Integer.valueOf(i), bArr);
        }
        return bArr;
    }

    private static byte[] c(int i, boolean z) {
        byte[] g;
        synchronized (b) {
            try {
                try {
                    try {
                        byte[] bytes = o().getBytes(StandardCharsets.UTF_8);
                        String b2 = b(i);
                        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByContentProvider get work key xxx", e(b2));
                        if (TextUtils.isEmpty(b2) && z) {
                            int i2 = 0;
                            while (true) {
                                if (i2 > 30) {
                                    break;
                                }
                                try {
                                    b.wait(300L);
                                } catch (InterruptedException unused) {
                                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByContentProvider sleep 1000");
                                }
                                b2 = b(i);
                                if (!"".equals(b2)) {
                                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByContentProvider retry work sleep==ok ");
                                    break;
                                }
                                i2++;
                            }
                        }
                        if (!TextUtils.isEmpty(b2) && b2.length() >= 24) {
                            String substring = b2.substring(0, 24);
                            String substring2 = b2.substring(24);
                            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByContentProvider get work key iv ", substring, "key ", substring2);
                            try {
                                g = d(Base64.e(substring2), bytes, Base64.e(substring));
                            } catch (GeneralSecurityException unused2) {
                                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "getWorkKeyByContentProvider desEncryptByte Exception");
                                g = g(i);
                            }
                            if (com.huawei.hwlogsmodel.common.LogConfig.a()) {
                                com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByContentProvider finish dec,secret is:", HEXUtils.a(g));
                            }
                            return g;
                        }
                        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByContentProvider secretInfo is null");
                        return d(i, bytes);
                    } catch (RuntimeException e2) {
                        throw e2;
                    }
                } catch (Exception unused3) {
                    com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "getWorkKeyByContentProvider Exception");
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static byte[] g(int i) {
        synchronized (b) {
            j = null;
        }
        return d(i, o().getBytes(StandardCharsets.UTF_8));
    }

    private static String o() {
        synchronized (b) {
            if (f && !TextUtils.isEmpty(j)) {
                return j;
            }
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getRootKeyByContentProvider");
            String i = i(1);
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getRootKeyByContentProvider oldFirstStringAll:", e(i));
            String substring = i.substring(2);
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getRootKeyByContentProvider oldFirstString:", e(substring));
            String i2 = i(2);
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getRootKeyByContentProvider oldSecondString:", e(i2));
            String i3 = i(3);
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getRootKeyByContentProvider oldThirdString:", e(i3));
            String d2 = d(substring, i2, i3);
            if (f) {
                j = d2;
            }
            return d2;
        }
    }

    static String b(int i) {
        String k;
        synchronized (b) {
            k = k(i);
        }
        return k;
    }

    private static String k(int i) {
        return bPr_(String.valueOf(i), d);
    }

    private static String a(String str) {
        return bPr_(str, d);
    }

    private static String g(String str) {
        return BaseApplication.getContext().getSharedPreferences(str, 0).getString("id", "");
    }

    private static void q() {
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "moveAllRootToContentProvider begin");
        String g = g("encrypt_sharedpreferences_name4");
        if (TextUtils.isEmpty(g)) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "moveAllRootToContentProvider c1 save to Cp", e(g));
        d("key_1", g);
        String g2 = g("encrypt_sharedpreferences_name2");
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "moveAllRootToContentProvider c2 save to Cp", e(g2));
        d("key_2", g2);
        String g3 = g("encrypt_sharedpreferences_name3");
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "moveAllRootToContentProvider c3 save to Cp", e(g3));
        d("key_3", g3);
    }

    private static String o(int i) {
        return BaseApplication.getContext().getSharedPreferences("encrypt_sharedpreferences_name1", 0).getString(String.valueOf(i), "");
    }

    private static void p(int i) {
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "moveWorkKeyToContentProvider begin", Integer.valueOf(i));
        String o = o(i);
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "moveWorkKeyToContentProvider key ", e(o));
        if (TextUtils.isEmpty(o)) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "moveWorkKeyToContentProvider save to Cp");
        d(String.valueOf(i), o);
    }

    private static String i(int i) {
        String a2 = a("key_" + i);
        WhiteBoxManager d2 = WhiteBoxManager.d();
        if (TextUtils.isEmpty(a2)) {
            int i2 = 0;
            while (true) {
                if (i2 > 30) {
                    break;
                }
                try {
                    b.wait(300L);
                } catch (Exception unused) {
                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getByContentProvider retry sleep ");
                }
                a2 = a("key_" + i);
                if (!TextUtils.isEmpty(a2)) {
                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getByContentProvider retry sleep ok break for Cp", Integer.valueOf(i));
                    break;
                }
                i2++;
            }
        }
        if (TextUtils.isEmpty(a2)) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getByContentProvider get always is null for C", Integer.valueOf(i));
            return e(i);
        }
        try {
            String str = new String(d2.a(Base64.e(a2)), StandardCharsets.UTF_8);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "getByContentProvider getError for C", Integer.valueOf(i));
            return e(i);
        } catch (Exception unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "getByContentProvider Exception");
            return e(i);
        }
    }

    private static byte[] f() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, secureRandom);
            return keyGenerator.generateKey().getEncoded();
        } catch (RuntimeException unused) {
            return new byte[0];
        } catch (Exception unused2) {
            return new byte[0];
        }
    }

    static byte[] e() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, secureRandom);
            return keyGenerator.generateKey().getEncoded();
        } catch (RuntimeException unused) {
            return new byte[0];
        } catch (Exception unused2) {
            return new byte[0];
        }
    }

    private static byte[] n() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(192, secureRandom);
            return keyGenerator.generateKey().getEncoded();
        } catch (RuntimeException unused) {
            return new byte[0];
        } catch (Exception unused2) {
            return new byte[0];
        }
    }

    private static String c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        String substring = Base64.a(bArr).substring(0, 32);
        String substring2 = Base64.a(bArr2).substring(0, 32);
        String substring3 = Base64.a(bArr3).substring(0, 32);
        char[] charArray = substring.toCharArray();
        char[] charArray2 = substring2.toCharArray();
        char[] charArray3 = substring3.toCharArray();
        int length = charArray.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) ((((charArray[i] << 4) ^ charArray2[i]) >> 6) ^ charArray3[i]);
        }
        return String.valueOf(cArr);
    }

    private static void a(String str, byte[] bArr, byte[] bArr2) {
        String str2 = Base64.a(bArr2) + Base64.a(bArr);
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "saveWorkKeyToContentProvider, ok; dataType:", str);
        d(str, str2);
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(bArr3));
        return cipher.doFinal(bArr);
    }

    static byte[] d(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (com.huawei.hwlogsmodel.common.LogConfig.a()) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "desEncryptByte data is:", e(HEXUtils.a(bArr)), ";secret is:", e(HEXUtils.a(bArr2)), ";iv is:", e(HEXUtils.a(bArr3)));
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(bArr3));
        return cipher.doFinal(bArr);
    }

    private static String d(String str, String str2, String str3) {
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        char[] charArray3 = str3.toCharArray();
        int length = charArray.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) ((((charArray[i] << 4) ^ charArray2[i]) >> 6) ^ charArray3[i]);
        }
        return String.valueOf(cArr);
    }

    private static void d(String str, String str2) {
        d(str, str2, false);
    }

    private static void d(String str, String str2, boolean z) {
        synchronized (b) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "backUpSecretKey, key: ", str + ", value: " + str2 + ", updateIfExist: " + z);
            b(str, str2);
            DbManager.c(BaseApplication.getContext(), String.valueOf(1009), "backup_table", 2, "backupKey varchar primary key ,backupValue varchar");
            ContentValues contentValues = new ContentValues();
            contentValues.put("backupKey", str);
            contentValues.put("backupValue", str2);
            if (!bPs_(str, d)) {
                DbManager.bGC_(BaseApplication.getContext(), String.valueOf(1009), "backup_table", 2, contentValues);
            } else if (z) {
                DbManager.b bVar = new DbManager.b();
                bVar.b(BaseApplication.getContext());
                bVar.e(String.valueOf(1009));
                bVar.c("backup_table");
                bVar.a(2);
                DbManager.bGI_(bVar, contentValues, "backupKey like ?", new String[]{str});
            }
            e(str, str2, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.StringBuilder] */
    public static void c() {
        ?? r5;
        File file;
        RandomAccessFile randomAccessFile;
        synchronized (b) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initAllKey begin");
            FileLock fileLock = null;
            try {
                r5 = BaseApplication.getContext().getFilesDir().getCanonicalPath();
            } catch (IOException e2) {
                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey(),", e2.getMessage());
                r5 = 0;
            }
            try {
                file = new File(r5 + "/lock.xml");
            } catch (Throwable th) {
                th = th;
            }
            try {
                if (!file.exists()) {
                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initAllKey create file result: ", Boolean.valueOf(file.createNewFile()));
                }
                randomAccessFile = new RandomAccessFile(file, "rw");
                while (fileLock == null) {
                    try {
                        try {
                            try {
                                fileLock = randomAccessFile.getChannel().lock();
                            } catch (Exception unused) {
                                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey lock get Exception");
                            }
                        } catch (Exception unused2) {
                            com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey Exception");
                            if (fileLock != null) {
                                try {
                                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initAllKey release");
                                    fileLock.release();
                                } catch (IOException unused3) {
                                    com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey IOException");
                                }
                            }
                            IoUtils.e(randomAccessFile);
                        }
                    } catch (FileNotFoundException unused4) {
                        com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey FileNotFoundException");
                        if (fileLock != null) {
                            try {
                                com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initAllKey release");
                                fileLock.release();
                            } catch (IOException unused5) {
                                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey IOException");
                            }
                        }
                        IoUtils.e(randomAccessFile);
                    } catch (IOException unused6) {
                        com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey IOException ");
                        if (fileLock != null) {
                            try {
                                com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initAllKey release");
                                fileLock.release();
                            } catch (IOException unused7) {
                                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey IOException");
                            }
                        }
                        IoUtils.e(randomAccessFile);
                    }
                }
                e(!KeyStoreUtils.b);
                l(14);
                l(13);
                l(1);
                l(2);
                if (fileLock != null) {
                    try {
                        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initAllKey release");
                        fileLock.release();
                    } catch (IOException unused8) {
                        com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey IOException");
                    }
                }
            } catch (FileNotFoundException unused9) {
                randomAccessFile = null;
            } catch (IOException unused10) {
                randomAccessFile = null;
            } catch (Exception unused11) {
                randomAccessFile = null;
            } catch (Throwable th2) {
                th = th2;
                r5 = 0;
                if (0 != 0) {
                    try {
                        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initAllKey release");
                        fileLock.release();
                    } catch (IOException unused12) {
                        com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initAllKey IOException");
                    }
                }
                IoUtils.e((Closeable) r5);
                throw th;
            }
            IoUtils.e(randomAccessFile);
        }
    }

    private static void e(boolean z) {
        if (z) {
            l();
            k();
            m();
        }
    }

    private static void l() {
        synchronized (b) {
            String format = new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis()));
            String a2 = a("key_1");
            WhiteBoxManager d2 = WhiteBoxManager.d();
            if (TextUtils.isEmpty(a2)) {
                q();
                a2 = a("key_1");
                com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initFirstByContentProvider retry c1 ", a2);
            }
            if (TextUtils.isEmpty(a2)) {
                d("key_1", Base64.a(d2.b(format + Base64.a(n()))));
            } else {
                try {
                    e("key_1", a2);
                } catch (Exception unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initFirstByContentProvider Exception");
                }
            }
        }
    }

    private static void k() {
        synchronized (b) {
            String a2 = a("key_2");
            WhiteBoxManager d2 = WhiteBoxManager.d();
            if ("".equals(a2)) {
                d("key_2", Base64.a(d2.b(Base64.a(n()))));
            } else {
                try {
                    e("key_2", a2);
                } catch (Exception unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initSecondByContentProvider Exception");
                }
            }
        }
    }

    private static void m() {
        synchronized (b) {
            String a2 = a("key_3");
            WhiteBoxManager d2 = WhiteBoxManager.d();
            if ("".equals(a2)) {
                d("key_3", Base64.a(d2.b(Base64.a(n()))));
            } else {
                try {
                    e("key_3", a2);
                } catch (Exception unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initThirdByContentProvider Exception");
                }
            }
        }
    }

    private static void l(int i) {
        if (!KeyStoreUtils.b) {
            if (bPs_(String.valueOf(i + 1000), d)) {
                a(true);
                return;
            } else {
                n(i);
                return;
            }
        }
        try {
            m(i);
        } catch (Exception e2) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("HiH_KeyManager", "initSecurityKeyNew Exception:", ExceptionUtils.d(e2));
        }
    }

    private static void m(int i) {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "initSecurityKeyForKeyStore dataType:", Integer.valueOf(i));
        int i2 = i + 1000;
        String k = k(i2);
        if (!TextUtils.isEmpty(k)) {
            String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.getContext());
            if (TextUtils.isEmpty(androidId)) {
                e(String.valueOf(i2), k);
                return;
            }
            String substring = k.length() >= 88 ? k.substring(88) : null;
            if (TextUtils.isEmpty(substring)) {
                substring = d(androidId);
            }
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKeyForKeyStore, newAndroidID: ", androidId, ", oldAndroidID: ", substring);
            if (androidId.equals(substring)) {
                e(String.valueOf(i2), k);
                return;
            } else {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "androidId not consistent, do clean...");
                if (i()) {
                    return;
                }
            }
        }
        com.huawei.hwlogsmodel.LogUtil.a("KeyManager", "secret key is empty, creating new secure key... dataType: ", Integer.valueOf(i));
        c(i, i2);
    }

    private static boolean i() {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "clearApplicationData begin");
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        Uri uri = d;
        Uri uri2 = f13123a;
        bPq_(1014, uri, uri2);
        bPq_(1013, uri, uri2);
        bPq_(1001, uri, uri2);
        bPq_(1002, uri, uri2);
        FileUtils.b(AppInfoUtils.b(null), false);
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "deleteDirectory end");
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("is_clone", 0).edit();
        edit.putBoolean("is_clone", true);
        edit.putString("clone_user_id", e2);
        edit.commit();
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "clean clone data end, exit by app kill self");
        Process.killProcess(Process.myPid());
        return true;
    }

    public static boolean a(boolean z) {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "cleanCloneData begin isClone=", Boolean.valueOf(z));
        g();
        Uri uri = d;
        Uri uri2 = f13123a;
        bPq_(1014, uri, uri2);
        bPq_(1013, uri, uri2);
        bPq_(1001, uri, uri2);
        bPq_(1002, uri, uri2);
        if (z) {
            SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("is_clone", 0).edit();
            edit.putBoolean("is_clone", true);
            edit.putString("clone_user_id", "1".equalsIgnoreCase(VersionDbManager.e(BaseApplication.getContext()).e("allow_login_or_not")) ? KeyValDbManager.b(BaseApplication.getContext()).e("user_id") : "");
            edit.commit();
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "clean clone data end, exit by app kill self");
        Process.killProcess(Process.myPid());
        return true;
    }

    public static boolean h() {
        return BaseApplication.getContext().getSharedPreferences("is_clone", 0).getBoolean("is_clone", false);
    }

    public static String a() {
        String string = BaseApplication.getContext().getSharedPreferences("is_clone", 0).getString("clone_user_id", "");
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getCloneUserId cloneUserId = ", string);
        return string;
    }

    public static void d() {
        com.huawei.hwlogsmodel.LogUtil.a("KeyManager", "clearCloneFlag enter");
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("is_clone", 0).edit();
        edit.putBoolean("is_clone", false);
        edit.putString("clone_user_id", "");
        edit.commit();
    }

    private static byte[] f(int i) {
        if (KeyStoreUtils.b && i == 14) {
            e(true);
        }
        byte[] c2 = c(i, false);
        if (c2 == null && !TextUtils.isEmpty(o(i))) {
            n(i);
            c2 = c(i, false);
            if (c2 != null) {
                com.huawei.hwlogsmodel.LogUtil.a("KeyManager", "initSecurityKey has 2.0 move");
            }
        }
        return c2;
    }

    private static void c(int i, int i2) {
        String str;
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "createNewSecureKey, dataType: " + i);
        byte[] f2 = f(i);
        if (f2 == null) {
            str = j();
        } else {
            str = new String(f2, StandardCharsets.UTF_8);
        }
        d(String.valueOf(i2), KeyStoreUtils.b(str), true);
        if (f2 != null) {
            bPq_(i, d, f13123a);
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("HiH_KeyManager", "createNewSecureKey end dataType:", Integer.valueOf(i));
    }

    private static void n(int i) {
        synchronized (b) {
            int myPid = Process.myPid();
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey dataType:", Integer.valueOf(i), ";process id:", Integer.valueOf(myPid), ";process name:", CommonUtil.d(myPid), "; main process:", BaseApplication.getAppPackage());
            try {
                com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey begin");
                String b2 = b(i);
                com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey get work key xxx", e(b2));
                if (TextUtils.isEmpty(b2)) {
                    p(i);
                    b2 = b(i);
                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey retry workKey", e(b2));
                }
                if (TextUtils.isEmpty(b2)) {
                    byte[] e2 = e();
                    byte[] bytes = j().getBytes(StandardCharsets.UTF_8);
                    if (com.huawei.hwlogsmodel.common.LogConfig.a()) {
                        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey get work key secret is ", e(HEXUtils.a(bytes)));
                    }
                    byte[] b3 = b(bytes, o().getBytes(StandardCharsets.UTF_8), e2);
                    if (com.huawei.hwlogsmodel.common.LogConfig.a()) {
                        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurity encryptKeys work key:", e(HEXUtils.a(b3)));
                    }
                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey clearSharedPreferences1, ok: dataType:", Integer.valueOf(i));
                    a(String.valueOf(i), b3, e2);
                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey completed");
                } else {
                    try {
                        e(String.valueOf(i), b2);
                    } catch (Exception unused) {
                        com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initSecurityKey backUpSecretKeyRetry Exception");
                    }
                    com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "initSecurityKey get work key2");
                }
            } catch (Exception unused2) {
                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "initSecurityKey Exception");
            }
        }
    }

    private static String j() {
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "createInitWorkKey");
        return c(f(), f(), f());
    }

    private static String e(int i) {
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getByBackupContentProvider begin C", Integer.valueOf(i));
        String b2 = b("key_" + i);
        WhiteBoxManager d2 = WhiteBoxManager.d();
        if (TextUtils.isEmpty(b2)) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getByBackupContentProvider is null, in Cp", Integer.valueOf(i));
            return "";
        }
        try {
            String str = new String(d2.a(Base64.e(b2)), StandardCharsets.UTF_8);
            a("key_" + i, b2);
            return str;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "getByBackupContentProvider Cp", Integer.valueOf(i), " Exception");
            return "";
        }
    }

    private static byte[] j(int i) {
        String valueOf = String.valueOf(i);
        String b2 = b(valueOf);
        if (TextUtils.isEmpty(b2) || b2.length() < 88) {
            com.huawei.hwlogsmodel.LogUtil.b("KeyManager", " getNewWorkKeyBackup length error");
            return null;
        }
        byte[] e2 = KeyStoreUtils.e(b2.substring(0, 88));
        if (e2 != null) {
            a(valueOf, b2);
        }
        return e2;
    }

    private static byte[] d(int i, byte[] bArr) {
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByBackupContentProvider begin");
        String valueOf = String.valueOf(i);
        String b2 = b(valueOf);
        if (TextUtils.isEmpty(b2) || b2.length() < 24) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByBackupContentProvider key length error");
            return null;
        }
        String substring = b2.substring(0, 24);
        String substring2 = b2.substring(24);
        com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getWorkKeyByBackupContentProvider get work key2, iv is:", e(substring2));
        try {
            byte[] d2 = d(Base64.e(substring2), bArr, Base64.e(substring));
            a(valueOf, b2);
            return d2;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "getWorkKeyByBackupContentProvider work key iv is:", e(substring2));
            return null;
        }
    }

    private static String b(String str) {
        return bPr_(str, f13123a);
    }

    private static void e(String str, String str2) {
        if (EnvironmentUtils.b()) {
            e(str, str2, false);
        }
    }

    private static void e(String str, String str2, boolean z) {
        synchronized (b) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "backUpSecretKeyRetry, key: ", str + ", value: " + str2 + ", updateIfExist: " + z);
            ContentValues contentValues = new ContentValues();
            contentValues.put("backupKey", str);
            contentValues.put("backupValue", str2);
            Uri uri = f13123a;
            if (!bPs_(str, uri)) {
                BaseApplication.getContext().getContentResolver().insert(uri, contentValues);
            } else if (z) {
                BaseApplication.getContext().getContentResolver().update(uri, contentValues, "backupKey like ?", new String[]{str});
            }
        }
    }

    private static void a(String str, String str2) {
        synchronized (b) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "updateSecretKey:", str);
            b(str, str2);
            DbManager.c(BaseApplication.getContext(), String.valueOf(1009), "backup_table", 2, "backupKey varchar primary key ,backupValue varchar");
            ContentValues contentValues = new ContentValues();
            contentValues.put("backupKey", str);
            contentValues.put("backupValue", str2);
            Uri uri = d;
            if (bPs_(str, uri)) {
                try {
                    BaseApplication.getContext().getContentResolver().update(uri, contentValues, "backupKey like ?", new String[]{str});
                } catch (SQLiteException unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "updateSecretKey SQLiteException");
                }
            }
        }
    }

    public static boolean c(String str) {
        return c.contains(str);
    }

    private static void g() {
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            BaseApplication.getContext().deleteDatabase(it.next());
        }
        BaseApplication.getContext().deleteDatabase(FileSensitiveProtectUtil.e("hihealth_sensitive.db"));
    }

    private static void bPq_(int i, Uri uri, Uri uri2) {
        com.huawei.hwlogsmodel.LogUtil.a("KeyManager", "deleteSecretKey:", Integer.valueOf(i));
        String[] strArr = {String.valueOf(i)};
        if (uri != null) {
            try {
                SharedPreferenceManager.d("preference_save_module", String.valueOf(i));
                BaseApplication.getContext().getContentResolver().delete(uri, "backupKey like ?", strArr);
            } catch (SQLiteException e2) {
                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "deleteSecretKey uri, ", e2.getClass().getSimpleName());
            }
        }
        if (uri2 != null) {
            try {
                BaseApplication.getContext().getContentResolver().delete(uri2, "backupKey like ?", strArr);
            } catch (SQLiteException e3) {
                com.huawei.hwlogsmodel.LogUtil.b("KeyManager", "deleteSecretKey backupUri, ", e3.getClass().getSimpleName());
            }
        }
    }

    private static String bPr_(String str, Uri uri) {
        String i = i(str);
        if (!TextUtils.isEmpty(i)) {
            return i;
        }
        String str2 = "";
        Cursor cursor = null;
        try {
            try {
                cursor = BaseApplication.getContext().getContentResolver().query(uri, null, "backupKey like ?", new String[]{str}, null);
                if (cursor != null && cursor.moveToNext()) {
                    str2 = cursor.getString(cursor.getColumnIndex("backupValue"));
                }
            } catch (SQLiteException e2) {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.c("HiH_KeyManager", "getSecretKey, ", e2.getClass().getSimpleName());
            }
            bPp_(cursor);
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("KeyManager", "key: ", str, " save to MMKV");
            b(str, str2);
            com.huawei.hwlogsmodel.LogUtil.c("KeyManager", "getSecretKey, key:", str, ";value is:", e(str2));
            return str2;
        } catch (Throwable th) {
            bPp_(cursor);
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002b, code lost:
    
        if (r9.getCount() > 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean bPs_(java.lang.String r9, android.net.Uri r10) {
        /*
            java.lang.String r0 = "KeyManager"
            java.lang.String r1 = i(r9)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 1
            if (r1 != 0) goto Le
            return r2
        Le:
            java.lang.String[] r7 = new java.lang.String[]{r9}
            r9 = 0
            r1 = 0
            android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L34 android.database.sqlite.SQLiteException -> L36
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L34 android.database.sqlite.SQLiteException -> L36
            r5 = 0
            java.lang.String r6 = "backupKey like ?"
            r8 = 0
            r4 = r10
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L34 android.database.sqlite.SQLiteException -> L36
            if (r9 == 0) goto L2e
            int r10 = r9.getCount()     // Catch: java.lang.Throwable -> L34 android.database.sqlite.SQLiteException -> L36
            if (r10 <= 0) goto L2e
            goto L2f
        L2e:
            r2 = r1
        L2f:
            bPp_(r9)
            r1 = r2
            goto L4e
        L34:
            r10 = move-exception
            goto L5c
        L36:
            r10 = move-exception
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L34
            java.lang.String r4 = "isHaveSecretKey, "
            r3[r1] = r4     // Catch: java.lang.Throwable -> L34
            java.lang.Class r10 = r10.getClass()     // Catch: java.lang.Throwable -> L34
            java.lang.String r10 = r10.getSimpleName()     // Catch: java.lang.Throwable -> L34
            r3[r2] = r10     // Catch: java.lang.Throwable -> L34
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> L34
            bPp_(r9)
        L4e:
            java.lang.String r9 = "isHaveSecretKey:"
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r10}
            com.huawei.hwlogsmodel.LogUtil.c(r0, r9)
            return r1
        L5c:
            bPp_(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.KeyManager.bPs_(java.lang.String, android.net.Uri):boolean");
    }

    private static void bPp_(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return Constants.NULL;
        }
        if (!com.huawei.hwlogsmodel.common.LogConfig.a()) {
            return "";
        }
        if (str.length() <= 5) {
            return str;
        }
        return str.substring(0, 5) + "...";
    }

    private static String i(String str) {
        return SharedPreferenceManager.e("preference_save_module", str, "");
    }

    private static void b(String str, String str2) {
        SharedPreferenceManager.c("preference_save_module", str, str2);
    }

    private static String d(String str) {
        StorageParams storageParams = new StorageParams(1);
        KeyValDbManager b2 = KeyValDbManager.b(BaseApplication.getContext());
        String d2 = b2.d("AndroidId", storageParams);
        if (!TextUtils.isEmpty(d2)) {
            return d2;
        }
        b2.a("AndroidId", str, storageParams);
        return str;
    }
}
