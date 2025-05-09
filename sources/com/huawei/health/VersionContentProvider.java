package com.huawei.health;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.huawei.common.util.VersionIsCloud;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.ApplicationLazyLoad;
import health.compact.a.CloudImpl;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.VersionSQLiteOpenHelper;

/* loaded from: classes.dex */
public class VersionContentProvider extends ContentProvider {
    private volatile VersionSQLiteOpenHelper d;
    private static final UriMatcher e = new UriMatcher(-1);

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2186a = new Object();

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    private void d() {
        e.addURI(CommonUtils.f(".version.provider"), "module_200007_keyvaldb", 1);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        LogUtil.c("VersionContentProvider", "onCreate", getContext());
        d();
        return true;
    }

    private VersionSQLiteOpenHelper c() {
        if (this.d == null) {
            synchronized (f2186a) {
                if (this.d == null) {
                    this.d = new VersionSQLiteOpenHelper(getContext(), "HwVersion.db");
                }
            }
        }
        return this.d;
    }

    private void BX_(SQLiteDatabase sQLiteDatabase) {
        String checkCloudState = VersionIsCloud.checkCloudState(getContext());
        SharedPreferenceManager.c("preference_save_module", "have_cloud_or_not", checkCloudState);
        String country = getContext().getResources().getConfiguration().locale.getCountry();
        SharedPreferenceManager.c("preference_save_module", "local_country_code", country);
        String language = getContext().getResources().getConfiguration().locale.getLanguage();
        SharedPreferenceManager.c("preference_save_module", "local_language_code", language);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedalConstants.EVENT_KEY, "have_cloud_or_not");
        contentValues.put("value", checkCloudState);
        sQLiteDatabase.insertOrThrow("module_200007_keyvaldb", null, contentValues);
        contentValues.put(MedalConstants.EVENT_KEY, "local_country_code");
        contentValues.put("value", country);
        sQLiteDatabase.insertOrThrow("module_200007_keyvaldb", null, contentValues);
        contentValues.put(MedalConstants.EVENT_KEY, "local_language_code");
        contentValues.put("value", language);
        sQLiteDatabase.insertOrThrow("module_200007_keyvaldb", null, contentValues);
    }

    private void BY_(SQLiteDatabase sQLiteDatabase) {
        String checkLoginState = VersionIsCloud.checkLoginState(getContext());
        SharedPreferenceManager.c("preference_save_module", "allow_login_or_not", checkLoginState);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedalConstants.EVENT_KEY, "allow_login_or_not");
        contentValues.put("value", checkLoginState);
        sQLiteDatabase.insertOrThrow("module_200007_keyvaldb", null, contentValues);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        e();
        if (str == null) {
            LogUtil.a("VersionContentProvider", "query selection == null");
            return null;
        }
        SQLiteDatabase writableDatabase = c().getWritableDatabase();
        if (e.match(uri) != 1) {
            return null;
        }
        Cursor query = writableDatabase.query("module_200007_keyvaldb", strArr, str, strArr2, str2, null, null);
        if (query != null && query.moveToNext()) {
            return query;
        }
        if (query != null) {
            query.close();
        }
        if (LoginInit.getCloudapi() == null) {
            CloudImpl.c(null);
        }
        BX_(writableDatabase);
        BY_(writableDatabase);
        return writableDatabase.query("module_200007_keyvaldb", strArr, str, strArr2, str2, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005a A[Catch: all -> 0x008a, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:15:0x005a, B:26:0x0086, B:27:0x0089, B:23:0x007e), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void BZ_(android.content.ContentValues r14) {
        /*
            r13 = this;
            monitor-enter(r13)
            health.compact.a.VersionSQLiteOpenHelper r0 = r13.c()     // Catch: java.lang.Throwable -> L8a
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch: java.lang.Throwable -> L8a
            r9 = 0
            r10 = 1
            r11 = 0
            java.lang.String r1 = "key"
            java.lang.String r1 = r14.getAsString(r1)     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            java.lang.String r2 = "value"
            java.lang.String r2 = r14.getAsString(r2)     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            if (r1 == 0) goto L62
            if (r2 != 0) goto L1f
            goto L62
        L1f:
            java.lang.String[] r12 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            r1 = 2
            java.lang.String[] r3 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            java.lang.String r1 = "key"
            r3[r9] = r1     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            java.lang.String r1 = "value"
            r3[r10] = r1     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            java.lang.String r2 = "module_200007_keyvaldb"
            java.lang.String r4 = "key = ?"
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            r5 = r12
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            if (r1 == 0) goto L52
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L5e android.database.SQLException -> L60
            if (r2 != 0) goto L48
            goto L52
        L48:
            java.lang.String r2 = "module_200007_keyvaldb"
            java.lang.String r3 = "key = ?"
            r0.update(r2, r14, r3, r12)     // Catch: java.lang.Throwable -> L5e android.database.SQLException -> L60
            goto L58
        L52:
            java.lang.String r2 = "module_200007_keyvaldb"
            r0.insertOrThrow(r2, r11, r14)     // Catch: java.lang.Throwable -> L5e android.database.SQLException -> L60
        L58:
            if (r1 == 0) goto L81
            r1.close()     // Catch: java.lang.Throwable -> L8a
            goto L81
        L5e:
            r14 = move-exception
            goto L84
        L60:
            r11 = r1
            goto L71
        L62:
            java.lang.Object[] r14 = new java.lang.Object[r10]     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            java.lang.String r0 = "insertOrUpDate key or value is null"
            r14[r9] = r0     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            java.lang.String r0 = "VersionContentProvider"
            health.compact.a.LogUtil.a(r0, r14)     // Catch: java.lang.Throwable -> L6f android.database.SQLException -> L71
            monitor-exit(r13)
            return
        L6f:
            r14 = move-exception
            goto L83
        L71:
            java.lang.Object[] r14 = new java.lang.Object[r10]     // Catch: java.lang.Throwable -> L6f
            java.lang.String r0 = "insertOrUpDate SQLException"
            r14[r9] = r0     // Catch: java.lang.Throwable -> L6f
            java.lang.String r0 = "VersionContentProvider"
            health.compact.a.LogUtil.e(r0, r14)     // Catch: java.lang.Throwable -> L6f
            if (r11 == 0) goto L81
            r11.close()     // Catch: java.lang.Throwable -> L8a
        L81:
            monitor-exit(r13)
            return
        L83:
            r1 = r11
        L84:
            if (r1 == 0) goto L89
            r1.close()     // Catch: java.lang.Throwable -> L8a
        L89:
            throw r14     // Catch: java.lang.Throwable -> L8a
        L8a:
            r14 = move-exception
            monitor-exit(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.VersionContentProvider.BZ_(android.content.ContentValues):void");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        e();
        if (uri == null) {
            LogUtil.a("VersionContentProvider", "insert() uri = null");
            return null;
        }
        if (e.match(uri) == 1) {
            BZ_(contentValues);
        }
        return ContentUris.withAppendedId(uri, 0L);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        e();
        if (uri == null) {
            LogUtil.a("VersionContentProvider", "delete() uri = null");
            return 0;
        }
        SQLiteDatabase writableDatabase = c().getWritableDatabase();
        if (e.match(uri) != 1) {
            return 0;
        }
        return writableDatabase.delete("module_200007_keyvaldb", str, strArr);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        e();
        SQLiteDatabase writableDatabase = c().getWritableDatabase();
        if (uri == null) {
            LogUtil.a("VersionContentProvider", "update() uri = null");
            return 0;
        }
        if (e.match(uri) != 1) {
            return 0;
        }
        return writableDatabase.update("module_200007_keyvaldb", contentValues, str, strArr);
    }

    private void e() {
        ApplicationLazyLoad.b();
    }
}
