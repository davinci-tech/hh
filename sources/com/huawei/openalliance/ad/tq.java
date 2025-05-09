package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class tq implements to {

    /* renamed from: a, reason: collision with root package name */
    private String f7541a;

    @Override // com.huawei.openalliance.ad.to
    public boolean a() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.to
    public void a(Activity activity, ts tsVar, tu tuVar) {
        if (activity == null || activity.getApplicationContext() == null) {
            return;
        }
        Context applicationContext = activity.getApplicationContext();
        StringBuilder sb = new StringBuilder();
        sb.append(tsVar.c() == null ? "" : tsVar.c());
        sb.append(tsVar.d() != null ? tsVar.d() : "");
        this.f7541a = sb.toString();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        List<Intent> a2 = a(applicationContext.getPackageManager().queryIntentActivities(intent, 131072), tuVar.d());
        Intent createChooser = Intent.createChooser(a2.remove(0), tsVar.b() != null ? tsVar.b() : "text/plain");
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) a2.toArray(new Parcelable[0]));
        createChooser.setFlags(268435456);
        applicationContext.startActivity(createChooser);
    }

    private List<Intent> a(List<ResolveInfo> list, List<String> list2) {
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : list) {
            if (list2.contains(resolveInfo.activityInfo.packageName)) {
                ho.a("MoreShareProcessor", "%s Not showing", resolveInfo.activityInfo.packageName);
            } else {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", this.f7541a);
                intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                arrayList.add(intent);
            }
        }
        return arrayList;
    }
}
