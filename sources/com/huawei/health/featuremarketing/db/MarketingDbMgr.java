package com.huawei.health.featuremarketing.db;

import android.content.Context;
import com.huawei.hwbasemgr.HwBaseManager;

/* loaded from: classes3.dex */
public class MarketingDbMgr extends HwBaseManager {
    private static volatile MarketingDbMgr d;

    public MarketingDbMgr(Context context) {
        super(context);
        d();
    }

    public static MarketingDbMgr d(Context context) {
        if (d == null) {
            synchronized (MarketingEventDbMgr.class) {
                if (d == null) {
                    d = new MarketingEventDbMgr(context);
                }
            }
        }
        return d;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10100;
    }

    private void d() {
        createStorageDataTable("marketing_data", 1, "id integer primary key autoincrement, positionId text not null,data text not null,latestModifyTime text not null");
        createStorageDataTable("marketing_trigger_event", 1, "id integer primary key autoincrement, huid text not null,positionId text not null,typeId text not null,resourceId text not null,triggerTime text not null,value text not null,reservedField text");
    }
}
