package com.huawei.hwdataaccessmodel.db.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class HwContentProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    private DbContentProvider e() {
        return DbContentProvider.c(getContext());
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (uri == null) {
            LogUtil.a("HWContentProvider", "query uri is null");
            return null;
        }
        SQLiteDatabase readableDatabase = e().getReadableDatabase();
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("This is a unknown Uri" + uri2);
        }
        try {
            return readableDatabase.query(substring, strArr, str, strArr2, null, null, str2);
        } catch (SQLiteException e) {
            LogUtil.a("HWContentProvider", "query ex=", LogUtil.a(e));
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (uri == null) {
            LogUtil.a("HWContentProvider", "insert uri is null");
            return null;
        }
        if (contentValues == null) {
            LogUtil.a("HWContentProvider", "insert values is null");
            return null;
        }
        SQLiteDatabase writableDatabase = e().getWritableDatabase();
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
            LogUtil.a("HWContentProvider", "delete uri is null");
            return 0;
        }
        SQLiteDatabase writableDatabase = e().getWritableDatabase();
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("This is a unknown Uri" + uri.toString());
        }
        return writableDatabase.delete(substring, str, strArr);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (uri == null) {
            LogUtil.a("HWContentProvider", "update uri is null");
            return 0;
        }
        if (contentValues == null) {
            LogUtil.a("HWContentProvider", "update values is null");
            return 0;
        }
        SQLiteDatabase writableDatabase = e().getWritableDatabase();
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            throw new IllegalArgumentException("This is a unknown Uri" + uri.toString());
        }
        return writableDatabase.update(substring, contentValues, str, strArr);
    }
}
