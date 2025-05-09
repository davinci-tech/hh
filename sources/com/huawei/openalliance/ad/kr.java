package com.huawei.openalliance.ad;

import android.content.Context;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class kr extends kp<Object> {
    @Override // com.huawei.openalliance.ad.kp
    protected String a(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return com.huawei.openalliance.ad.utils.be.a(obj);
        } catch (JSONException e) {
            ho.c("JsonBeanConverter", "convert json JSONException!");
            throw e;
        }
    }

    @Override // com.huawei.openalliance.ad.ks
    public String a() {
        return "application/json";
    }

    public kr(Context context) {
        super(context);
    }
}
