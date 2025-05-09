package com.huawei.ui.openservice.db.manager;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.ui.openservice.db.model.HomePageServiceOrder;
import com.huawei.ui.openservice.db.model.UserHomePageServiceTable;
import java.util.List;

/* loaded from: classes7.dex */
public class UserHomePageServiceDbManager {
    private OpenServiceDbCommon dbCommon;

    public UserHomePageServiceDbManager(Context context) {
        this.dbCommon = new OpenServiceDbCommon(context.getApplicationContext(), UserHomePageServiceTable.TABLE_NAME, UserHomePageServiceTable.getColumns());
    }

    public boolean deleteAllService(String str) {
        return this.dbCommon.delete("huid =? ", new String[]{str}) > 0;
    }

    public boolean insertService(List<HomePageServiceOrder> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                this.dbCommon.insert(buildOpenServiceVersionValues(list.get(i)));
            }
        }
        return true;
    }

    public boolean refreshOrders(String str, List<HomePageServiceOrder> list) {
        return refreshOrdersSync(str, list);
    }

    private boolean refreshOrdersSync(String str, List<HomePageServiceOrder> list) {
        boolean insertService;
        synchronized (this) {
            deleteAllService(str);
            insertService = insertService(list);
        }
        return insertService;
    }

    private ContentValues buildOpenServiceVersionValues(HomePageServiceOrder homePageServiceOrder) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", homePageServiceOrder.getHuid());
        contentValues.put("service_id", homePageServiceOrder.getServiceID());
        contentValues.put(UserHomePageServiceTable.COLUMN_SERVICE_POSITION, Integer.valueOf(homePageServiceOrder.getPosition()));
        contentValues.put("modify_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }
}
