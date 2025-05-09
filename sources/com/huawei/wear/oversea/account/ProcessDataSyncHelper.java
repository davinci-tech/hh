package com.huawei.wear.oversea.account;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import defpackage.stq;
import defpackage.svu;

/* loaded from: classes7.dex */
public class ProcessDataSyncHelper {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ProcessDataSyncHelper f11208a;
    private static final Object c = new Object();
    private boolean e = false;
    private Handler d = svu.b().eXQ_();

    public ProcessDataSyncHelper(Context context) {
        stq.c("ProcessDataSyncHelper", "isSync " + this.e + ",isMainProcess ,isSwipeCardProcess ", false);
    }

    public static ProcessDataSyncHelper d(Context context) {
        if (f11208a == null) {
            synchronized (c) {
                if (f11208a == null) {
                    f11208a = new ProcessDataSyncHelper(context.getApplicationContext());
                }
            }
        }
        return f11208a;
    }

    public void c(Context context, final String str, final String str2, final Object obj) {
        if (!this.e || context == null || TextUtils.isEmpty(str)) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        this.d.post(new Runnable() { // from class: com.huawei.wear.oversea.account.ProcessDataSyncHelper.5
            @Override // java.lang.Runnable
            public void run() {
                if (applicationContext == null) {
                    stq.c("ProcessDataSyncHelper", "drop put operation for context is null", false);
                    return;
                }
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("sp_sync_operate_type", (Integer) 0);
                    ProcessDataSyncHelper.this.eXM_(applicationContext, contentValues, str);
                    contentValues.put("sp_sync_key", str2);
                    ProcessDataSyncHelper.this.eXL_(contentValues, "sp_sync_value", obj);
                } catch (IllegalArgumentException | SecurityException unused) {
                    stq.c("ProcessDataSyncHelper", "put failed", false);
                }
            }
        });
    }

    public void e(Context context, final String str, final String str2) {
        if (!this.e || context == null || TextUtils.isEmpty(str)) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        this.d.post(new Runnable() { // from class: com.huawei.wear.oversea.account.ProcessDataSyncHelper.4
            @Override // java.lang.Runnable
            public void run() {
                if (applicationContext == null) {
                    stq.c("ProcessDataSyncHelper", "drop remove operation for context is null", false);
                    return;
                }
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("sp_sync_operate_type", (Integer) 1);
                    ProcessDataSyncHelper.this.eXM_(applicationContext, contentValues, str);
                    contentValues.put("sp_sync_key", str2);
                } catch (IllegalArgumentException | SecurityException unused) {
                    stq.c("ProcessDataSyncHelper", "remove failed", false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eXM_(Context context, ContentValues contentValues, String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 10) {
            return;
        }
        contentValues.put("sp_sync_sp_name", str.substring(10, str.length()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eXL_(ContentValues contentValues, String str, Object obj) {
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
            return;
        }
        if (obj instanceof Integer) {
            contentValues.put(str, (Integer) obj);
            return;
        }
        if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
            return;
        }
        if (obj instanceof Boolean) {
            contentValues.put(str, (Boolean) obj);
        } else if (obj instanceof Float) {
            contentValues.put(str, (Float) obj);
        } else {
            stq.c("ProcessDataSyncHelper", "unsupported type", false);
        }
    }
}
