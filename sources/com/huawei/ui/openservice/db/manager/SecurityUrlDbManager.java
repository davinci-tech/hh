package com.huawei.ui.openservice.db.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import com.huawei.ui.openservice.db.model.SecurityUrl;
import com.huawei.ui.openservice.db.model.SecurityUrlTable;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class SecurityUrlDbManager {
    private static final String RELEASE_TAG = "Opera_SecurityUrlDbManager";
    private OpenServiceDbCommon dbCommon;

    public SecurityUrlDbManager(Context context) {
        this.dbCommon = new OpenServiceDbCommon(context.getApplicationContext(), SecurityUrlTable.TABLE_NAME, SecurityUrlTable.getColumns());
    }

    public boolean insertSecurityUrl(List<SecurityUrl> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (SecurityUrl securityUrl : list) {
            if (securityUrl != null) {
                this.dbCommon.insert(buildSecurityUrlValues(securityUrl));
            }
        }
        return true;
    }

    public List<SecurityUrl> queryUrlList(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return parseSecurityUrlList(this.dbCommon.query("service_id =? ", new String[]{str}, null, null, null));
    }

    public List<SecurityUrl> queryAllUrl() {
        return parseSecurityUrlList(this.dbCommon.query("id >=? ", new String[]{Integer.toString(1)}, null, null, null));
    }

    public boolean refreshUrls(List<SecurityUrl> list) {
        return refreshUrlsSync(list);
    }

    private boolean refreshUrlsSync(List<SecurityUrl> list) {
        boolean insertSecurityUrl;
        synchronized (this) {
            deleteSecurityUrl();
            insertSecurityUrl = insertSecurityUrl(list);
        }
        return insertSecurityUrl;
    }

    private boolean deleteSecurityUrl() {
        return this.dbCommon.delete("id >=? ", new String[]{Integer.toString(1)}) > 0;
    }

    private ContentValues buildSecurityUrlValues(SecurityUrl securityUrl) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("service_id", securityUrl.getServiceID());
        contentValues.put("url", securityUrl.getUrl());
        contentValues.put("modify_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    private List<SecurityUrl> parseSecurityUrlList(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                try {
                    SecurityUrl securityUrl = new SecurityUrl();
                    securityUrl.setServiceID(cursor.getString(cursor.getColumnIndex("service_id")));
                    securityUrl.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                    arrayList.add(securityUrl);
                } catch (SQLiteDatabaseCorruptException unused) {
                    ReleaseLogUtil.c(RELEASE_TAG, "parseSecurityUrlList() SQLiteDatabaseCorruptException");
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }
}
