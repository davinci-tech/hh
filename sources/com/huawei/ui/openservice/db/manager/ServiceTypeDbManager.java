package com.huawei.ui.openservice.db.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.huawei.ui.openservice.db.model.OpenServiceGroup;
import com.huawei.ui.openservice.db.model.ServiceTypeTable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ServiceTypeDbManager {
    private static final String LOG_TAG = "ServiceTypeDBManager";
    private OpenServiceDbCommon dbCommon;

    public ServiceTypeDbManager(Context context) {
        this.dbCommon = new OpenServiceDbCommon(context.getApplicationContext(), "service_type", ServiceTypeTable.getColumns());
    }

    public boolean refreshAllTypes(List<OpenServiceGroup> list) {
        return refreshAllTypesSync(list);
    }

    private boolean refreshAllTypesSync(List<OpenServiceGroup> list) {
        boolean insertOpenServiceType;
        synchronized (this) {
            deleteAllTypes();
            insertOpenServiceType = insertOpenServiceType(list);
        }
        return insertOpenServiceType;
    }

    public boolean deleteAllTypes() {
        return this.dbCommon.delete("id >=? ", new String[]{Integer.toString(1)}) > 0;
    }

    public boolean insertOpenServiceType(List<OpenServiceGroup> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (OpenServiceGroup openServiceGroup : list) {
            if (openServiceGroup != null) {
                this.dbCommon.insert(buildOpenServiceGroupValues(openServiceGroup));
            }
        }
        return true;
    }

    private List<OpenServiceGroup> parseOpenServiceTypeList(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                arrayList.add(getGroup(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private OpenServiceGroup parseOpenServiceType(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            return cursor.moveToNext() ? getGroup(cursor) : null;
        } finally {
            cursor.close();
        }
    }

    private OpenServiceGroup getGroup(Cursor cursor) {
        OpenServiceGroup openServiceGroup = new OpenServiceGroup();
        openServiceGroup.setServiceTypeID(cursor.getString(cursor.getColumnIndex(ServiceTypeTable.COLUMN_SERVICE_TYPE_ID)));
        openServiceGroup.setServiceTypeName(cursor.getString(cursor.getColumnIndex(ServiceTypeTable.COLUMN_SERVICE_TYPE_NAME)));
        openServiceGroup.setServiceTypeDescription(cursor.getString(cursor.getColumnIndex(ServiceTypeTable.COLUMN_SERVICE_TYPE_DESCRIPTION)));
        openServiceGroup.setPosition(cursor.getInt(cursor.getColumnIndex(ServiceTypeTable.COLUMN_SERVICE_TYPE_POSITION)));
        return openServiceGroup;
    }

    public ContentValues buildOpenServiceGroupValues(OpenServiceGroup openServiceGroup) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ServiceTypeTable.COLUMN_SERVICE_TYPE_ID, openServiceGroup.getServiceTypeID());
        contentValues.put(ServiceTypeTable.COLUMN_SERVICE_TYPE_NAME, openServiceGroup.getServiceTypeName());
        contentValues.put(ServiceTypeTable.COLUMN_SERVICE_TYPE_DESCRIPTION, openServiceGroup.getServiceTypeDescription());
        contentValues.put(ServiceTypeTable.COLUMN_SERVICE_TYPE_POSITION, Integer.valueOf(openServiceGroup.getPosition()));
        contentValues.put("modify_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }
}
