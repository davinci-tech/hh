package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class dql {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("matchRules")
    private Map<String, String> f11786a;

    @SerializedName("ts")
    private long b;

    @SerializedName("configId")
    private String c;

    @SerializedName("source")
    private int d;

    @SerializedName("greyRules")
    private Map<String, Object> e;

    public dql(String str, Map<String, String> map) {
        Objects.requireNonNull(str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("RequestBody", "configId is empty.");
            return;
        }
        this.b = System.currentTimeMillis();
        this.d = 1;
        this.c = str;
        this.f11786a = map;
        if (drn.a(str, map)) {
            HashMap hashMap = new HashMap();
            hashMap.put("version", "1.0.0");
            hashMap.put("type", 1);
            if (CommonUtil.cc() && !Utils.o()) {
                hashMap.put("Device-ID", "10.21.21.104");
            }
            this.e = hashMap;
        }
    }

    public String a() {
        return this.c;
    }
}
