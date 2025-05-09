package com.huawei.health.device.ui.measure.service;

import android.content.Context;
import android.database.Cursor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.openservice.OpenServiceConstant;
import com.huawei.ui.openservice.db.model.ServiceTable;
import defpackage.cnt;
import defpackage.cny;
import defpackage.cob;
import defpackage.cpb;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ServiceForDatabaseManager {
    private cny d;

    public ServiceForDatabaseManager(Context context) {
        this.d = new cny(context.getApplicationContext(), "service", cob.d());
    }

    public List<cnt> b() {
        StringBuffer stringBuffer = new StringBuffer();
        int c = cpb.c(System.currentTimeMillis());
        LogUtil.a("ServiceForDatabaseManager", "queryUserLegalService today is: ", Integer.valueOf(c));
        stringBuffer.append("start_date <=? and end_date >=? and is_service_card =? ");
        String[] strArr = {Integer.toString(c), Integer.toString(c), Integer.toString(1)};
        LogUtil.a("ServiceForDatabaseManager", "queryUserLegalService sbSelector is: ", stringBuffer);
        return Jq_(this.d.Jo_(stringBuffer.toString(), strArr, null, null, null));
    }

    private List<cnt> Jq_(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor == null) {
            return arrayList;
        }
        while (cursor.moveToNext()) {
            try {
                arrayList.add(Jp_(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private cnt Jp_(Cursor cursor) {
        cnt cntVar = new cnt();
        cntVar.k(cursor.getString(cursor.getColumnIndex("service_id")));
        cntVar.r(cursor.getString(cursor.getColumnIndex("service_type")));
        cntVar.f(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_PRODUCT_NAME)));
        cntVar.c(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_DESCRIPTION)));
        cntVar.m(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_ICON)));
        cntVar.e(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_HOME_PAGE_ICON)));
        cntVar.l(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_PROVIDER)));
        cntVar.q(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_URL)));
        cntVar.i(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_REQUIRED_VERSION)));
        cntVar.a(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_HMS_AUTH)));
        cntVar.a(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_AUTH_TYPE)));
        cntVar.g(cursor.getInt(cursor.getColumnIndex("start_date")));
        cntVar.d(cursor.getInt(cursor.getColumnIndex("end_date")));
        cntVar.h(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_SUB_POSITION)));
        cntVar.b(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_APP_ID)));
        cntVar.g(cursor.getString(cursor.getColumnIndex("label")));
        cntVar.e(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_IS_SERVICE_CARD)));
        cntVar.b(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_NEED_RECOMMEND)));
        cntVar.c(cursor.getInt(cursor.getColumnIndex(ServiceTable.COLUMN_IS_PLUGIN)));
        cntVar.j(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_PLUGIN_URL)));
        cntVar.s(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_SOURCE)));
        cntVar.n(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_DETAIL)));
        cntVar.o(cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_MID_ICON)));
        String string = cursor.getString(cursor.getColumnIndex(ServiceTable.COLUMN_SERVICE_KEEP_1));
        cntVar.t(cpb.e(string, OpenServiceConstant.MAP_KEY_SERVICE_VERSION));
        cntVar.d(cpb.e(string, OpenServiceConstant.MAP_KEY_DOWNLOAD_WEB_URL));
        cntVar.h(cpb.e(string, "packageName"));
        return cntVar;
    }
}
