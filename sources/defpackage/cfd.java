package defpackage;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class cfd {
    private static final byte[] b = new byte[1];
    private static final String c = "AssetsDatabaseManager";
    private static cfd e;

    /* renamed from: a, reason: collision with root package name */
    private Map<String, SQLiteDatabase> f675a = new HashMap(16);

    private cfd() {
    }

    public SQLiteDatabase Et_(String str) {
        Map<String, SQLiteDatabase> map = this.f675a;
        if (map == null) {
            return null;
        }
        if (map.get(str) != null) {
            return this.f675a.get(str);
        }
        LogUtil.a(c, " open33 = dbfile");
        return Eu_(str);
    }

    public boolean d(String str) {
        String str2 = c;
        LogUtil.a(str2, " start closeDatabase ");
        if (this.f675a.get(str) != null) {
            SQLiteDatabase sQLiteDatabase = this.f675a.get(str);
            LogUtil.a(str2, " open33 = " + sQLiteDatabase.isOpen());
            sQLiteDatabase.close();
            LogUtil.a(str2, " close12 = " + sQLiteDatabase.isOpen());
            this.f675a.remove(str);
            return true;
        }
        LogUtil.a("database", " end closeDatabase ");
        return false;
    }

    public static cfd e() {
        cfd cfdVar;
        LogUtil.a("database", " start getManager ");
        synchronized (b) {
            cfdVar = e;
        }
        return cfdVar;
    }

    public SQLiteDatabase Eu_(String str) {
        LogUtil.a("database", " start getSQLiteDatabase ");
        Map<String, SQLiteDatabase> map = this.f675a;
        if (map == null) {
            LogUtil.h(c, "databaseMap is null");
            return null;
        }
        map.clear();
        String b2 = b();
        String c2 = c(str);
        File file = new File(c2);
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(cfd.class.toString(), 0);
        LogUtil.a("database", " ing getSQLiteDatabase ");
        if (!sharedPreferences.getBoolean(str, false) || !file.exists()) {
            File file2 = new File(b2);
            if ((!file2.exists() && !file2.mkdirs()) || !a(str, c2)) {
                return null;
            }
            LogUtil.a("database", " ing2 getSQLiteDatabase ");
            sharedPreferences.edit().putBoolean(str, true).commit();
        }
        LogUtil.a("database", " ing end getSQLiteDatabase ");
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(c2, null, 16);
        LogUtil.a("database", " open32 = " + openDatabase.isOpen());
        this.f675a.put(str, openDatabase);
        return openDatabase;
    }

    public static void a() {
        LogUtil.a("database", " start initManager ");
        synchronized (b) {
            if (e == null) {
                e = new cfd();
            }
        }
    }

    private String b() {
        return String.format(Locale.ENGLISH, "/data/data/%s/database", BaseApplication.getContext().getApplicationInfo().packageName);
    }

    private String c(String str) {
        return b() + "/" + str;
    }

    private boolean a(String str, String str2) {
        int i;
        LogUtil.a("database", " start copyAssetsToFilesystem ");
        try {
            InputStream open = BaseApplication.getContext().getAssets().open(str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                try {
                    LogUtil.a("database", " ing copyAssetsToFilesystem ");
                    byte[] bArr = new byte[1024];
                    int i2 = 0;
                    while (true) {
                        i = i2 + 1024;
                        if (i > 52428800 || open.read(bArr) <= 0) {
                            break;
                        }
                        int read = open.read(bArr);
                        fileOutputStream.write(bArr, 0, read);
                        i2 += read;
                    }
                    if (i > 52428800) {
                        LogUtil.h(c, "assetsSrc is too big");
                    }
                    open.close();
                    fileOutputStream.close();
                    LogUtil.a("database", " ing2 copyAssetsToFilesystem ");
                    fileOutputStream.close();
                    if (open != null) {
                        open.close();
                    }
                    LogUtil.a("database", " close copyAssetsToFilesystem ");
                    return true;
                } finally {
                }
            } finally {
            }
        } catch (IOException e2) {
            LogUtil.b(c, e2.getMessage());
            return false;
        }
    }
}
