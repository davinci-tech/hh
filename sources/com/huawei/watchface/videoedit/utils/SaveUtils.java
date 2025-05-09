package com.huawei.watchface.videoedit.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.sysc.Optional;

/* loaded from: classes9.dex */
public class SaveUtils {
    private static final String TAG = "SaveUtils";

    private SaveUtils() {
    }

    public static Optional<Uri> insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues) {
        if (contentResolver == null || contentValues == null) {
            HwLog.w(TAG, "insert: invalid input");
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(contentResolver.insert(uri, contentValues));
        } catch (SQLiteException unused) {
            HwLog.w(TAG, "insert: SQLiteException");
            return Optional.empty();
        }
    }

    public static boolean delete(ContentResolver contentResolver, Uri uri) {
        if (contentResolver == null) {
            HwLog.w(TAG, "insert: invalid input");
            return false;
        }
        try {
            contentResolver.delete(uri, null, null);
            return true;
        } catch (Exception e) {
            HwLog.e(TAG, "delete: SQLiteException" + HwLog.printException(e));
            return false;
        }
    }
}
