package com.huawei.ui.openservice.db.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.openservice.OpenServiceConstant;
import com.huawei.ui.openservice.OpenServiceUtil;
import com.huawei.ui.openservice.db.model.OpenService;
import com.huawei.ui.openservice.db.model.ServiceTable;
import defpackage.koq;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ServiceDbManager {
    private static final String TAG = "Opera_ServiceDBManager";
    private static final int TYPE_FOUR_SIZE = 4;
    private static final int TYPE_SEC = 2;
    private static final int TYPE_THREE_SIZE = 3;
    private OpenServiceDbCommon dbCommon;

    public ServiceDbManager(Context context) {
        this.dbCommon = new OpenServiceDbCommon(context.getApplicationContext(), "service", ServiceTable.getColumns());
    }

    public List<OpenService> queryUserLegalService() {
        int c = HiDateUtil.c(System.currentTimeMillis());
        return parseOpenServiceList(this.dbCommon.query("start_date <=? and end_date >=? and is_service_card =? ", new String[]{Integer.toString(c), Integer.toString(c), Integer.toString(1)}, null, null, null));
    }

    public OpenService queryUserLegalServiceById(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int c = HiDateUtil.c(System.currentTimeMillis());
        return parseOpenService(this.dbCommon.query("start_date <=? and end_date >=? and service_id =? and is_service_card =? ", new String[]{Integer.toString(c), Integer.toString(c), str, Integer.toString(1)}, null, null, null));
    }

    public boolean refreshAllService(List<OpenService> list) {
        return refreshAllServiceSync(list);
    }

    private boolean refreshAllServiceSync(List<OpenService> list) {
        boolean insertUserService;
        synchronized (this) {
            deleteUserAllService();
            insertUserService = insertUserService(list);
        }
        return insertUserService;
    }

    private boolean insertUserService(List<OpenService> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (OpenService openService : list) {
            if (openService != null) {
                this.dbCommon.insert(buildOpenServiceVersionValues(openService));
            }
        }
        return true;
    }

    private boolean deleteUserAllService() {
        return this.dbCommon.delete("id >=? ", new String[]{Integer.toString(1)}) > 0;
    }

    private OpenService getService(Cursor cursor) {
        OpenService openService = new OpenService();
        openService.setServiceID(cursor.getString(cursor.getColumnIndex("service_id")));
        openService.setServiceTypeID(cursor.getString(cursor.getColumnIndex("service_type")));
        openService.setProductName(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_PRODUCT_NAME)));
        openService.setDescription(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_DESCRIPTION)));
        openService.setServiceIcon(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_ICON)));
        openService.setHomePageIcon(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_HOME_PAGE_ICON)));
        openService.setServiceProvider(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_PROVIDER)));
        openService.setServiceUrl(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_URL)));
        openService.setRequiredVersion(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_REQUIRED_VERSION)));
        openService.setHmsAuth(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_HMS_AUTH)));
        openService.setAuthType(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_AUTH_TYPE)));
        openService.setStartDate(cursor.getInt(cursor.getColumnIndex("start_date")));
        openService.setEndDate(cursor.getInt(cursor.getColumnIndex("end_date")));
        openService.setSubPosition(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_SUB_POSITION)));
        openService.setAppID(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_APP_ID)));
        openService.setLabel(cursor.getString(cursor.getColumnIndex("label")));
        openService.setIsServiceCard(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_IS_SERVICE_CARD)));
        openService.setNeedRecommend(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_NEED_RECOMMEND)));
        openService.setIsPlugin(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_IS_PLUGIN)));
        openService.setPluginUrl(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_PLUGIN_URL)));
        openService.setServiceSource(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_SOURCE)));
        openService.setServiceDetail(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_DETAIL)));
        openService.setServiceMidIcon(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_MID_ICON)));
        String string = cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_KEEP_1));
        openService.setServiceVersion(OpenServiceUtil.getValueFromJson(string, OpenServiceConstant.MAP_KEY_SERVICE_VERSION));
        openService.setDownloadWebUrl(OpenServiceUtil.getValueFromJson(string, OpenServiceConstant.MAP_KEY_DOWNLOAD_WEB_URL));
        openService.setPackageName(OpenServiceUtil.getValueFromJson(string, "packageName"));
        String string2 = cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_KEEP_2));
        List<String> arrayList = new ArrayList<>();
        try {
            arrayList = (List) new Gson().fromJson(string2, new TypeToken<List<String>>() { // from class: com.huawei.ui.openservice.db.manager.ServiceDbManager.1
            }.getType());
        } catch (JsonParseException unused) {
            LogUtil.b(TAG, "getData JsonParseException");
        }
        openService.setShowLabels(arrayList);
        openService.setShowVersion(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_KEEP_3)));
        return openService;
    }

    private List<OpenService> parseOpenServiceList(Cursor cursor) {
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

    private OpenService parseOpenService(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            return cursor.moveToNext() ? getService(cursor) : null;
        } finally {
            cursor.close();
        }
    }

    private ContentValues buildOpenServiceVersionValues(OpenService openService) {
        ContentValues contentValues = new ContentValues();
        if (openService == null) {
            LogUtil.h(TAG, "buildOpenServiceVersionValues() data is null.");
            return contentValues;
        }
        contentValues.put("service_id", openService.getServiceID());
        contentValues.put("service_type", openService.getServiceTypeID());
        contentValues.put(ServiceTable.COLUMN_SERVICE_PRODUCT_NAME, openService.getProductName());
        contentValues.put(ServiceTable.COLUMN_SERVICE_DESCRIPTION, openService.getDescription());
        contentValues.put(ServiceTable.COLUMN_SERVICE_ICON, openService.getServiceIcon());
        contentValues.put(ServiceTable.COLUMN_HOME_PAGE_ICON, openService.getHomePageIcon());
        contentValues.put(ServiceTable.COLUMN_SERVICE_PROVIDER, openService.getServiceProvider());
        contentValues.put(ServiceTable.COLUMN_SERVICE_URL, openService.getServiceUrl());
        contentValues.put(ServiceTable.COLUMN_SERVICE_REQUIRED_VERSION, openService.getRequiredVersion());
        contentValues.put(ServiceTable.COLUMN_SERVICE_HMS_AUTH, Integer.valueOf(openService.getHmsAuth()));
        contentValues.put(ServiceTable.COLUMN_SERVICE_AUTH_TYPE, openService.getAuthTypeStr());
        contentValues.put("start_date", Integer.valueOf(openService.getStartDate()));
        contentValues.put("end_date", Integer.valueOf(openService.getEndDate()));
        contentValues.put(ServiceTable.COLUMN_SUB_POSITION, Integer.valueOf(openService.getSubPosition()));
        contentValues.put(ServiceTable.COLUMN_SERVICE_APP_ID, openService.getAppID());
        contentValues.put("modify_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("label", openService.getLabel());
        contentValues.put(ServiceTable.COLUMN_IS_SERVICE_CARD, Integer.valueOf(openService.getIsServiceCard()));
        contentValues.put(ServiceTable.COLUMN_NEED_RECOMMEND, Integer.valueOf(openService.getNeedRecommend()));
        contentValues.put(ServiceTable.COLUMN_IS_PLUGIN, Integer.valueOf(openService.getIsPlugin()));
        contentValues.put(ServiceTable.COLUMN_PLUGIN_URL, openService.getPluginUrl());
        contentValues.put(ServiceTable.COLUMN_SERVICE_SOURCE, openService.getServiceSource());
        contentValues.put(ServiceTable.COLUMN_SERVICE_DETAIL, openService.getServiceDetail());
        contentValues.put(ServiceTable.COLUMN_SERVICE_MID_ICON, openService.getServiceMidIcon());
        JSONObject serviceKeepJson = OpenServiceUtil.getServiceKeepJson(openService);
        if (serviceKeepJson != null) {
            contentValues.put(ServiceTable.COLUMN_SERVICE_KEEP_1, serviceKeepJson.toString());
        }
        List<String> showLabels = openService.getShowLabels();
        LogUtil.a(TAG, "showLabels:", showLabels);
        if (koq.c(showLabels)) {
            contentValues.put(ServiceTable.COLUMN_SERVICE_KEEP_2, new Gson().toJson(showLabels));
        }
        contentValues.put(ServiceTable.COLUMN_SERVICE_KEEP_3, Integer.valueOf(openService.getShowVersion()));
        return contentValues;
    }
}
