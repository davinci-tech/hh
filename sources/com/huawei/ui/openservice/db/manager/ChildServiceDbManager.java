package com.huawei.ui.openservice.db.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.ui.openservice.db.model.ChildService;
import com.huawei.ui.openservice.db.model.ChildServiceTable;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ChildServiceDbManager {
    private static final int TYPE_SEC = 2;
    private static final int TYPE_THREE_SIZE = 3;
    private OpenServiceDbCommon mDbCommon;

    public ChildServiceDbManager(Context context) {
        this.mDbCommon = new OpenServiceDbCommon(context.getApplicationContext(), ChildServiceTable.TABLE_NAME, ChildServiceTable.getColumns());
    }

    public List<ChildService> queryUserLegalLocation(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int c = HiDateUtil.c(System.currentTimeMillis());
        return parseChildServiceList(this.mDbCommon.query("start_date <=? and end_date >=? and location =? ", new String[]{Integer.toString(c), Integer.toString(c), str}, null, null, null));
    }

    public boolean refreshAllService(List<ChildService> list) {
        return refreshAllServiceSync(list);
    }

    private boolean refreshAllServiceSync(List<ChildService> list) {
        boolean insertUserService;
        synchronized (this) {
            deleteUserAllService();
            insertUserService = insertUserService(list);
        }
        return insertUserService;
    }

    private boolean insertUserService(List<ChildService> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (ChildService childService : list) {
            if (childService != null) {
                this.mDbCommon.insert(buildChildServiceVersionValues(childService));
            }
        }
        return true;
    }

    private boolean deleteUserAllService() {
        return this.mDbCommon.delete("id >=? ", new String[]{Integer.toString(1)}) > 0;
    }

    private ChildService getService(Cursor cursor) {
        ChildService childService = new ChildService();
        childService.setServiceID(cursor.getString(cursor.getColumnIndex("service_id")));
        childService.setLocation(cursor.getString(cursor.getColumnIndex("location")));
        childService.setPosition(cursor.getInt(cursor.getColumnIndex("position")));
        childService.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        childService.setImageUrl(cursor.getString(cursor.getColumnIndex(ChildServiceTable.COLUMN_IMAGE_URL)));
        childService.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        childService.setStartDate(cursor.getInt(cursor.getColumnIndex("start_date")));
        childService.setEndDate(cursor.getInt(cursor.getColumnIndex("end_date")));
        childService.setHmsAuth(cursor.getInt(cursor.getColumnIndex(ChildServiceTable.COLUMN_HMS_AUTH)));
        childService.setAuthType(cursor.getString(cursor.getColumnIndex(ChildServiceTable.COLUMN_AUTH_TYPE)));
        childService.setServiceName(cursor.getString(cursor.getColumnIndex("service_name")));
        childService.setSubType(cursor.getString(cursor.getColumnIndex(ChildServiceTable.COLUMN_SUB_TYPE)));
        return childService;
    }

    private List<ChildService> parseChildServiceList(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                arrayList.add(getService(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private ContentValues buildChildServiceVersionValues(ChildService childService) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("service_id", childService.getServiceID());
        contentValues.put("location", childService.getLocation());
        contentValues.put("position", Integer.valueOf(childService.getPosition()));
        contentValues.put("description", childService.getDescription());
        contentValues.put(ChildServiceTable.COLUMN_IMAGE_URL, childService.getImageUrl());
        contentValues.put("url", childService.getUrl());
        contentValues.put("start_date", Integer.valueOf(childService.getStartDate()));
        contentValues.put("end_date", Integer.valueOf(childService.getEndDate()));
        contentValues.put(ChildServiceTable.COLUMN_HMS_AUTH, Integer.valueOf(childService.getHmsAuth()));
        contentValues.put(ChildServiceTable.COLUMN_AUTH_TYPE, childService.getAuthTypeStr());
        contentValues.put("modify_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("service_name", childService.getServiceName());
        contentValues.put(ChildServiceTable.COLUMN_SUB_TYPE, childService.getSubType());
        return contentValues;
    }
}
