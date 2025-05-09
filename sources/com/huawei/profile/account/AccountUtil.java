package com.huawei.profile.account;

import android.accounts.AccountManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.huawei.hms.utils.HMSPackageManager;

/* loaded from: classes6.dex */
public final class AccountUtil {
    private static final int HAS_LOGGED_IN = 1;
    private static final String HAS_LOGIN_COLUMN = "hasLogin";
    private static final String URI_HAS_LOGIN = "content://com.huawei.hwid.api.provider/has_login";

    private AccountUtil() {
    }

    public static boolean hasLogin(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse(URI_HAS_LOGIN), null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    boolean z = query.getInt(query.getColumnIndex(HAS_LOGIN_COLUMN)) == 1;
                    if (query != null) {
                        query.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
        android.accounts.Account[] accountsByType = AccountManager.get(context).getAccountsByType(HMSPackageManager.getInstance(context).getHMSPackageName());
        return accountsByType != null && accountsByType.length > 0;
    }
}
