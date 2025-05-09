package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class sjt {
    private static volatile ConcurrentHashMap<String, sjt> c = new ConcurrentHashMap<>();
    private String b;
    private HashSet<Long> e;

    public static sjt e(String str) {
        sjt sjtVar;
        if (c.get(str) == null) {
            synchronized (sjt.class) {
                if (c.get(str) == null) {
                    c.put(str, new sjt(str));
                }
                sjtVar = c.get(str);
            }
            return sjtVar;
        }
        return c.get(str);
    }

    private sjt(String str) {
        this.b = str;
    }

    public boolean c(HiHealthData hiHealthData) {
        return this.e.contains(Long.valueOf(hiHealthData.getStartTime()));
    }

    public void b() {
        if (this.e != null) {
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference(this.b);
        String value = userPreference != null ? userPreference.getValue() : "";
        if (TextUtils.isEmpty(value)) {
            this.e = new HashSet<>();
        } else {
            a(value);
        }
    }

    private void a(String str) {
        try {
            Type type = new TypeToken<HashSet<Long>>() { // from class: sjt.3
            }.getType();
            Gson gson = new Gson();
            LogUtil.c("SynchronizedDataTool", this.b, " :", str);
            HashSet<Long> hashSet = (HashSet) gson.fromJson(str, type);
            this.e = hashSet;
            if (hashSet == null) {
                this.e = new HashSet<>();
            }
        } catch (JsonSyntaxException unused) {
            this.e = new HashSet<>();
        }
    }

    public void a(long j) {
        LogUtil.c("SynchronizedDataTool", "updateSyncTime ", this.b, " :", Long.valueOf(j));
        HashSet<Long> hashSet = this.e;
        if (hashSet == null) {
            return;
        }
        hashSet.add(Long.valueOf(j));
        HiHealthManager.d(BaseApplication.e()).setUserPreference(new HiUserPreference(this.b, new Gson().toJson(this.e)));
    }

    public void c() {
        c.remove(this.b);
    }
}
