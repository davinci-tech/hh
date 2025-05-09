package com.huawei.hwdataaccessmodel.db.backup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import health.compact.a.LogUtil;
import health.compact.a.SqlUtil;

/* loaded from: classes.dex */
public class HwBackupProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (uri == null) {
            LogUtil.a("HwBackupProvider", "query uri is null");
            return null;
        }
        SQLiteDatabase readableDatabase = b().getReadableDatabase();
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("This is a unknown Uri" + uri.toString());
        }
        return readableDatabase.query(substring, strArr, SqlUtil.d(str), strArr2, null, null, str2);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (uri == null) {
            LogUtil.a("HwBackupProvider", "insert uri is null");
            return null;
        }
        if (contentValues == null) {
            LogUtil.a("HwBackupProvider", "insert values is null");
            return null;
        }
        SQLiteDatabase writableDatabase = b().getWritableDatabase();
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("This is a unknown Uri" + uri.toString());
        }
        Uri withAppendedPath = Uri.withAppendedPath(uri, "/" + writableDatabase.insert(substring, null, contentValues));
        getContext().getContentResolver().notifyChange(uri, null);
        return withAppendedPath;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        if (uri == null) {
            LogUtil.a("HwBackupProvider", "delete uri is null");
            return 0;
        }
        SQLiteDatabase writableDatabase = b().getWritableDatabase();
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("This is a unknown Uri" + uri.toString());
        }
        return writableDatabase.delete(substring, SqlUtil.d(str), strArr);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (uri == null) {
            LogUtil.a("HwBackupProvider", "update uri is null");
            return 0;
        }
        if (contentValues == null) {
            LogUtil.a("HwBackupProvider", "update values is null");
            return 0;
        }
        SQLiteDatabase writableDatabase = b().getWritableDatabase();
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("This is a unknown Uri" + uri.toString());
        }
        return writableDatabase.update(substring, contentValues, SqlUtil.d(str), strArr);
    }

    private DbBackupKeyProvider b() {
        return DbBackupKeyProvider.b(getContext());
    }
}
