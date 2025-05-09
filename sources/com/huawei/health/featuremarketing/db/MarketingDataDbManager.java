package com.huawei.health.featuremarketing.db;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.common.util.Utils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;

/* loaded from: classes3.dex */
public class MarketingDataDbManager extends MarketingDbMgr {
    private static volatile MarketingDataDbManager e;
    private Context d;

    public MarketingDataDbManager(Context context) {
        super(context.getApplicationContext());
        this.d = context.getApplicationContext();
    }

    public static MarketingDataDbManager a(Context context) {
        if (e == null) {
            synchronized (MarketingDataDbManager.class) {
                if (e == null) {
                    e = new MarketingDataDbManager(context);
                }
            }
        }
        return e;
    }

    public long a(int i, String str, long j) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("MarketingDataDbManager", "InsertData is null.");
            return -1L;
        }
        if (a(i) != null) {
            LogUtil.a("MarketingDataDbManager", "data save before, update data. positionId: ", Integer.valueOf(i));
            return b(i, str, j);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("positionId", e(i));
        contentValues.put("data", str);
        contentValues.put("latestModifyTime", Long.valueOf(j));
        return MarketingDbMgr.d(this.d).insertStorageData("marketing_data", 1, contentValues) - 1;
    }

    public long b(int i, String str, long j) {
        if (str.isEmpty()) {
            LogUtil.c("MarketingDataDbManager", "update value null");
            return -1L;
        }
        try {
            LogUtil.a("MarketingDataDbManager", "update result=", Integer.valueOf(MarketingDbMgr.d(this.d).updateStorageData("marketing_data", 1, WJ_(i, str, j), "positionId=?", new String[]{e(i)})));
            return -1L;
        } catch (NumberFormatException e2) {
            LogUtil.b("MarketingDataDbManager", "update error: ", e2.getMessage());
            return 201000L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x007f, code lost:
    
        if (r3 == null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(int r9) {
        /*
            r8 = this;
            java.lang.String r0 = "MarketingDataDbManager"
            r1 = 0
            r2 = 1
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            java.lang.String r5 = "select *  from "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            android.content.Context r5 = r8.d     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            com.huawei.health.featuremarketing.db.MarketingDbMgr r5 = com.huawei.health.featuremarketing.db.MarketingDbMgr.d(r5)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            java.lang.String r6 = "marketing_data"
            java.lang.String r5 = r5.getTableFullName(r6)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            r4.append(r5)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            java.lang.String r5 = " where positionId=?"
            r4.append(r5)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            java.lang.String r6 = "query selection="
            r5[r1] = r6     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            r5[r2] = r4     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            com.huawei.hwlogsmodel.LogUtil.c(r0, r5)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            android.content.Context r5 = r8.d     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            com.huawei.health.featuremarketing.db.MarketingDbMgr r5 = com.huawei.health.featuremarketing.db.MarketingDbMgr.d(r5)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            java.lang.String r9 = r8.e(r9)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            r6[r1] = r9     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            android.database.Cursor r9 = r5.rawQueryStorageData(r2, r4, r6)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L68 android.database.SQLException -> L75
            if (r9 == 0) goto L5f
        L44:
            boolean r4 = r9.moveToNext()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57 android.database.SQLException -> L5b
            if (r4 == 0) goto L5f
            java.lang.String r4 = "data"
            int r4 = r9.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57 android.database.SQLException -> L5b
            java.lang.String r3 = r9.getString(r4)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57 android.database.SQLException -> L5b
            goto L44
        L55:
            r0 = move-exception
            goto L87
        L57:
            r7 = r3
            r3 = r9
            r9 = r7
            goto L69
        L5b:
            r7 = r3
            r3 = r9
            r9 = r7
            goto L76
        L5f:
            if (r9 == 0) goto L85
            r9.close()
            goto L85
        L65:
            r9 = move-exception
            r0 = r9
            goto L86
        L68:
            r9 = r3
        L69:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = "Exception error."
            r2[r1] = r4     // Catch: java.lang.Throwable -> L65
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)     // Catch: java.lang.Throwable -> L65
            if (r3 == 0) goto L84
            goto L81
        L75:
            r9 = r3
        L76:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = "queryStorage error: "
            r2[r1] = r4     // Catch: java.lang.Throwable -> L65
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)     // Catch: java.lang.Throwable -> L65
            if (r3 == 0) goto L84
        L81:
            r3.close()
        L84:
            r3 = r9
        L85:
            return r3
        L86:
            r9 = r3
        L87:
            if (r9 == 0) goto L8c
            r9.close()
        L8c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.featuremarketing.db.MarketingDataDbManager.a(int):java.lang.String");
    }

    private ContentValues WJ_(int i, String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("positionId", e(i));
        contentValues.put("data", str);
        contentValues.put("latestModifyTime", Long.valueOf(j));
        return contentValues;
    }

    private String e(int i) {
        if (LoginInit.getInstance(this.d).isBrowseMode()) {
            return i + "_" + Utils.getCountryCode();
        }
        return i + "_" + LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
    }
}
