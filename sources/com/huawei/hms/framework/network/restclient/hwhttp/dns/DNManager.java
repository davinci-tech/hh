package com.huawei.hms.framework.network.restclient.hwhttp.dns;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.restclient.hwhttp.dns.dnresolver.dnkeeper.DNKeeper;
import com.huawei.hms.network.NetworkKit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class DNManager {
    public static final String ENABLE_HTTPDNS = "enable_httpdns";
    private static final String TAG = "DNManager";
    private static volatile DNManager instance;

    public void init(Context context, DNKeeper dNKeeper) {
    }

    private DNManager() {
    }

    public static DNManager getInstance() {
        if (instance == null) {
            synchronized (DNManager.class) {
                if (instance == null) {
                    instance = new DNManager();
                }
            }
        }
        return instance;
    }

    public void setHttpDnsEnabled(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ENABLE_HTTPDNS, z);
            NetworkKit.getInstance().setOptions(jSONObject.toString());
        } catch (JSONException unused) {
            Logger.w("DNManager", "DNManager setHttpDnsEnabled catch a JSONException");
        }
    }
}
