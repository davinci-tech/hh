package com.huawei.ui.openservice.db.model;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes7.dex */
public class HomePageServiceOrder {
    private String huid;
    private int position;
    private String serviceID;

    public String getServiceID() {
        return this.serviceID;
    }

    public void setServiceID(String str) {
        this.serviceID = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public String getHuid() {
        return this.huid;
    }

    public void setHuid(String str) {
        this.huid = str;
    }

    public static void setHuidToList(List<HomePageServiceOrder> list, String str) {
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            return;
        }
        for (HomePageServiceOrder homePageServiceOrder : list) {
            if (homePageServiceOrder != null) {
                homePageServiceOrder.setHuid(str);
            }
        }
    }
}
