package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.openalliance.ad.ho;
import com.huawei.ui.openservice.db.model.ChildServiceTable;

/* loaded from: classes5.dex */
public class h {
    public static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        ho.a("AnalyticsKitUtils", "ana_tag_kit broadcastEvent sourcePkg = %s", str);
        Intent intent = new Intent("com.huawei.hms.analytics.pps.event");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        bundle.putString("event_type", str2);
        bundle.putString(ChildServiceTable.COLUMN_SUB_TYPE, str3);
        bundle.putString("slot_id", str4);
        if (str5 == null) {
            bundle.putString("activity_name", "");
        } else {
            bundle.putString("activity_name", str5);
        }
        intent.putExtra("event_detail", bundle);
        context.sendBroadcast(intent);
        ho.a("AnalyticsKitUtils", "ana_tag sendBroadcast successfully!");
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        ho.a("AnalyticsKitUtils", "ana_tag_kit broadcastEvent sourcePkg = %s", str);
        Intent intent = new Intent("com.huawei.hms.analytics.pps.event");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        bundle.putString("event_type", str2);
        bundle.putString("slot_id", str3);
        if (str4 == null) {
            bundle.putString("activity_name", "");
        } else {
            bundle.putString("activity_name", str4);
        }
        intent.putExtra("event_detail", bundle);
        context.sendBroadcast(intent);
        ho.a("AnalyticsKitUtils", "ana_tag sendBroadcast successfully!");
    }
}
