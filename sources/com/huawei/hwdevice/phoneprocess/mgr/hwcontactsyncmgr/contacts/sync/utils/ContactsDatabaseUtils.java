package com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class ContactsDatabaseUtils {

    public interface ResultCallback<R> {
        void onResult(R r);
    }

    public static void b(a aVar, ResultCallback<Cursor> resultCallback) {
        if (aVar == null || resultCallback == null) {
            LogUtil.h("ContactsDatabaseUtils", "query: input parameter is null.");
            return;
        }
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        if (contentResolver == null) {
            LogUtil.h("ContactsDatabaseUtils", "query: resolver is null.");
            return;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = contentResolver.query(aVar.f6339a, aVar.d, aVar.e, aVar.b, aVar.c);
                resultCallback.onResult(cursor);
                if (cursor == null) {
                    return;
                }
            } catch (CursorIndexOutOfBoundsException | SQLiteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException unused) {
                LogUtil.b("ContactsDatabaseUtils", "getContactIdList: occurred Exception.");
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static void bMk_(Cursor cursor, int i, ResultCallback<String> resultCallback) {
        if (bMi_(cursor)) {
            LogUtil.h("ContactsDatabaseUtils", "parseDataList: cursor is null or cannot move.");
            return;
        }
        if (resultCallback == null) {
            LogUtil.h("ContactsDatabaseUtils", "parseDataList: out data list is null.");
        } else if (!bMj_(cursor, i)) {
            LogUtil.h("ContactsDatabaseUtils", "parseDataList: columnIndex is invalid.");
        } else {
            do {
                resultCallback.onResult(cursor.getString(i));
            } while (cursor.moveToNext());
        }
    }

    public static boolean bMi_(Cursor cursor) {
        return cursor == null || !cursor.moveToFirst();
    }

    private static boolean bMj_(Cursor cursor, int i) {
        if (cursor != null) {
            return i >= 0 && i < cursor.getColumnCount();
        }
        LogUtil.h("ContactsDatabaseUtils", "isValidIndex: cursor is null.");
        return false;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private Uri f6339a;
        private String[] b;
        private String c;
        private String[] d;
        private String e;

        public a bMm_(Uri uri) {
            this.f6339a = uri;
            return this;
        }

        public a d(String[] strArr) {
            if (strArr != null) {
                this.d = (String[]) Arrays.copyOf(strArr, strArr.length);
            }
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }

        public a c(String[] strArr) {
            if (strArr != null) {
                this.b = (String[]) Arrays.copyOf(strArr, strArr.length);
            }
            return this;
        }

        public a e(String str) {
            this.c = str;
            return this;
        }
    }
}
