package com.huawei.agconnect.remoteconfig.internal.b;

import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.datastore.annotation.DefaultCrypto;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;
import com.huawei.agconnect.remoteconfig.internal.c.b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f1817a = b();
    private final String b;

    public void a(Map<String, String> map) {
        this.f1817a = map;
        if (map == null || map.size() == 0) {
            SharedPrefUtil.getInstance().remove("com.huawei.agc.remoteconfig", this.b);
            return;
        }
        try {
            SharedPrefUtil.getInstance().put("com.huawei.agc.remoteconfig", this.b, String.class, new JSONObject(map).toString(), DefaultCrypto.class);
        } catch (NullPointerException e) {
            Logger.e("RemoteConfig", "map to json error:" + e.getMessage());
        }
    }

    public Map<String, String> a() {
        return this.f1817a;
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap();
        String str = (String) SharedPrefUtil.getInstance().get("com.huawei.agc.remoteconfig", this.b, String.class, null, DefaultCrypto.class);
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        try {
            return b.a(new JSONObject(str));
        } catch (JSONException e) {
            Logger.e("RemoteConfig", "processJsonToMap error:" + e.getMessage());
            return hashMap;
        }
    }

    public a(AGConnectInstance aGConnectInstance) {
        this.b = "customAttributesKey_" + aGConnectInstance.getIdentifier();
    }
}
