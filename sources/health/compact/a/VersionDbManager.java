package health.compact.a;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.jex;

/* loaded from: classes.dex */
public class VersionDbManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile VersionDbManager f13146a;
    private static final String[] c = {MedalConstants.EVENT_KEY, "value"};
    private static Context d;
    private Uri b;

    private VersionDbManager(Context context) {
        com.huawei.hwlogsmodel.LogUtil.a("VersionDbManager", "Enter KeyValDbManager");
        d = context.getApplicationContext();
        this.b = jex.bGM_("module_200007_keyvaldb");
    }

    public static VersionDbManager e(Context context) {
        if (f13146a == null) {
            synchronized (VersionDbManager.class) {
                if (f13146a == null) {
                    if (context == null) {
                        f13146a = new VersionDbManager(BaseApplication.getContext());
                    } else {
                        f13146a = new VersionDbManager(context.getApplicationContext());
                    }
                }
            }
        }
        return f13146a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(StorageDataCallback storageDataCallback, StorageResult storageResult) {
        if (storageDataCallback != null) {
            storageDataCallback.onProcessed(storageResult);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
    
        if (r1 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0069, code lost:
    
        if (r1 == null) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String e(java.lang.String r13) {
        /*
            r12 = this;
            java.lang.String r0 = "VersionDbManager"
            r1 = 0
            if (r13 != 0) goto Lf
            java.lang.String r13 = "getValue, key is null"
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r13)
            return r1
        Lf:
            java.lang.String r2 = ""
            java.lang.String r3 = "preference_save_module"
            java.lang.String r2 = health.compact.a.SharedPreferenceManager.e(r3, r13, r2)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L28
            java.lang.String r13 = "get value from mmkv"
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            com.huawei.hwlogsmodel.LogUtil.c(r0, r13)
            return r2
        L28:
            java.lang.String[] r8 = new java.lang.String[]{r13}
            r10 = 0
            r11 = 1
            android.content.Context r4 = health.compact.a.VersionDbManager.d     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
            android.net.Uri r5 = r12.b     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
            java.lang.String[] r6 = health.compact.a.VersionDbManager.c     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
            java.lang.String r7 = "key = ?"
            r9 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
            if (r1 == 0) goto L4c
            boolean r4 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
            if (r4 == 0) goto L4c
            java.lang.String r2 = r1.getString(r11)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
        L4c:
            health.compact.a.SharedPreferenceManager.c(r3, r13, r2)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54 android.database.SQLException -> L60
            if (r1 == 0) goto L6e
            goto L6b
        L52:
            r13 = move-exception
            goto L6f
        L54:
            java.lang.Object[] r13 = new java.lang.Object[r11]     // Catch: java.lang.Throwable -> L52
            java.lang.String r3 = "getValue, queryStorage Exception"
            r13[r10] = r3     // Catch: java.lang.Throwable -> L52
            com.huawei.hwlogsmodel.LogUtil.b(r0, r13)     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L6e
            goto L6b
        L60:
            java.lang.Object[] r13 = new java.lang.Object[r11]     // Catch: java.lang.Throwable -> L52
            java.lang.String r3 = "getValue, queryStorage SQLException"
            r13[r10] = r3     // Catch: java.lang.Throwable -> L52
            com.huawei.hwlogsmodel.LogUtil.b(r0, r13)     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L6e
        L6b:
            r1.close()
        L6e:
            return r2
        L6f:
            if (r1 == 0) goto L74
            r1.close()
        L74:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.VersionDbManager.e(java.lang.String):java.lang.String");
    }

    public void a(final String str, final String str2, final StorageDataCallback storageDataCallback) {
        com.huawei.hwlogsmodel.LogUtil.c("VersionDbManager", "setValue, key=", str, ", value=", str2);
        ThreadPoolManager.d().execute(new Runnable() { // from class: health.compact.a.VersionDbManager.3
            @Override // java.lang.Runnable
            public void run() {
                String str3;
                StorageResult storageResult = new StorageResult();
                String str4 = str;
                if (str4 == null || (str3 = str2) == null) {
                    com.huawei.hwlogsmodel.LogUtil.h("VersionDbManager", "setValue, key or value is null");
                    storageResult.a(AwarenessConstants.ERROR_UNKNOWN_CODE);
                } else {
                    SharedPreferenceManager.c("preference_save_module", str4, str3);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MedalConstants.EVENT_KEY, str);
                    contentValues.put("value", str2);
                    try {
                        Uri insert = VersionDbManager.d.getContentResolver().insert(VersionDbManager.this.b, contentValues);
                        if (insert == null) {
                            com.huawei.hwlogsmodel.LogUtil.h("VersionDbManager", "setValue failed with null.");
                            storageResult.a(201000);
                        } else if (ContentUris.parseId(insert) != -1) {
                            com.huawei.hwlogsmodel.LogUtil.a("VersionDbManager", "setValue success");
                            storageResult.a(0);
                        } else {
                            com.huawei.hwlogsmodel.LogUtil.h("VersionDbManager", "setValue failed");
                            storageResult.a(201000);
                        }
                    } catch (Exception unused) {
                        com.huawei.hwlogsmodel.LogUtil.b("VersionDbManager", "setValue failed with Exception");
                        storageResult.a(201000);
                        VersionDbManager.d(storageDataCallback, storageResult);
                    }
                }
                VersionDbManager.d(storageDataCallback, storageResult);
            }
        });
    }
}
