package com.huawei.hms.push;

import com.huawei.hms.push.notification.SubscribedItem;
import com.huawei.hms.support.api.client.Result;
import java.util.List;

/* loaded from: classes9.dex */
public class SubscribeResult extends Result {

    /* renamed from: a, reason: collision with root package name */
    private String f5673a;
    private List<SubscribedItem> b;

    public String getErrorMsg() {
        return this.f5673a;
    }

    public List<SubscribedItem> getSubscribedItems() {
        return this.b;
    }

    public void setErrorMsg(String str) {
        this.f5673a = str;
    }

    public void setSubscribedItems(List<SubscribedItem> list) {
        this.b = list;
    }
}
