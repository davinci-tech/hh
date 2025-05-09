package com.huawei.hihealthservice.hihealthkit.model;

import com.huawei.hihealth.model.Subscriber;
import java.util.Map;

/* loaded from: classes7.dex */
public class CloudSubscriber extends Subscriber {
    private Map<String, String> pendingIntent;

    public CloudSubscriber(String str, String str2, String str3) {
        super(str, str2, str3, null);
    }

    public Map getPendingIntent() {
        return this.pendingIntent;
    }

    public void setPendingIntent(Map map) {
        this.pendingIntent = map;
    }
}
