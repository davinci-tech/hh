package defpackage;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.model.Subscription;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.hihealthkit.model.CloudSubscriber;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class irr {
    public boolean e(List<Subscription> list) {
        SharedPreferenceManager.c("preference_module_kit_subscribe", "subscription", HiJsonUtil.e(list));
        return true;
    }

    public List<Subscription> a() {
        String e = SharedPreferenceManager.e("preference_module_kit_subscribe", "subscription", "");
        return !TextUtils.isEmpty(e) ? (List) HiJsonUtil.b(e, new TypeToken<List<Subscription>>() { // from class: irr.1
        }.getType()) : new ArrayList(10);
    }

    public void b(Long l) {
        SharedPreferenceManager.e("preference_module_kit_subscribe", "lastSyncDate", l.longValue());
    }

    public Long d() {
        return Long.valueOf(SharedPreferenceManager.b("preference_module_kit_subscribe", "lastSyncDate", 0L));
    }

    public void d(List<CloudSubscriber> list) {
        SharedPreferenceManager.c("preference_module_kit_subscribe", "subscriber", HiJsonUtil.e(list));
    }

    public List<CloudSubscriber> e() {
        String e = SharedPreferenceManager.e("preference_module_kit_subscribe", "subscriber", "");
        return !TextUtils.isEmpty(e) ? (List) HiJsonUtil.b(e, new TypeToken<List<CloudSubscriber>>() { // from class: irr.5
        }.getType()) : new ArrayList(10);
    }

    public List<CloudSubscriber> a(String str) {
        ArrayList arrayList = new ArrayList(10);
        for (CloudSubscriber cloudSubscriber : e()) {
            if (cloudSubscriber.getAppId().equals(str)) {
                arrayList.add(cloudSubscriber);
            }
        }
        return arrayList;
    }

    public int b() {
        return SharedPreferenceManager.a("preference_module_kit_subscribe", "lastLoginUser", 0);
    }

    public void d(int i) {
        SharedPreferenceManager.e("preference_module_kit_subscribe", "lastLoginUser", i);
    }

    public void e(String str) {
        SharedPreferenceManager.c("preference_module_kit_subscribe", String.valueOf(ima.a().j()), str);
    }

    public String c() {
        return SharedPreferenceManager.e("preference_module_kit_subscribe", String.valueOf(ima.a().j()), "");
    }
}
