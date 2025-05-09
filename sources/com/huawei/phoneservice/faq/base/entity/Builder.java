package com.huawei.phoneservice.faq.base.entity;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.d;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.l;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class Builder {
    private static final String TAG = "Builder";
    private Map<String, String> map = new ConcurrentHashMap();

    public Builder setUri(Uri uri) {
        String message;
        try {
            for (String str : uri.getQueryParameterNames()) {
                String queryParameter = uri.getQueryParameter(str);
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(queryParameter)) {
                    this.map.put(str, queryParameter);
                }
            }
        } catch (UnsupportedOperationException e) {
            message = e.getMessage();
            i.c(TAG, message);
            return this;
        } catch (Exception e2) {
            message = e2.getMessage();
            i.c(TAG, message);
            return this;
        }
        return this;
    }

    public Builder setJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(jSONObject.optString(next))) {
                    this.map.put(next, jSONObject.optString(next));
                }
            }
        } catch (JSONException unused) {
            Log.e(TAG, "  JSONException  ");
        }
        return this;
    }

    public Builder set(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.map.put(str, str2);
        }
        return this;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.map.get("language")) || TextUtils.isEmpty(this.map.get("country")) || TextUtils.isEmpty(this.map.get("channel")) || TextUtils.isEmpty(this.map.get("appVersion"));
    }

    public Map<String, String> build() {
        if (this.map.containsKey("language")) {
            this.map.put("language", getLanguageCode(this.map.get("language"), this.map.get("country")));
        }
        if (!this.map.containsKey("model") || TextUtils.isEmpty(this.map.get("model"))) {
            String e = d.e();
            if (!TextUtils.isEmpty(e)) {
                this.map.put("model", e);
            }
        }
        if (!this.map.containsKey(FaqConstants.FAQ_EMUIVERSION) || TextUtils.isEmpty(this.map.get(FaqConstants.FAQ_EMUIVERSION))) {
            String c = d.c();
            if (!TextUtils.isEmpty(c)) {
                this.map.put(FaqConstants.FAQ_EMUIVERSION, c);
            }
        }
        if (!this.map.containsKey("osVersion") || TextUtils.isEmpty(this.map.get("osVersion"))) {
            this.map.put("osVersion", d.d());
        }
        if ((!this.map.containsKey(FaqConstants.FAQ_LANGUAGE) || TextUtils.isEmpty(this.map.get(FaqConstants.FAQ_LANGUAGE))) && !TextUtils.isEmpty(this.map.get("language"))) {
            Map<String, String> map = this.map;
            map.put(FaqConstants.FAQ_LANGUAGE, map.get("language"));
        }
        setIsDeepLink();
        return this.map;
    }

    private void setIsDeepLink() {
        Map<String, String> map;
        String str;
        if (!this.map.containsKey(FaqConstants.FAQ_IS_DEEPLICK) || TextUtils.isEmpty(this.map.get(FaqConstants.FAQ_IS_DEEPLICK))) {
            map = this.map;
            str = "N";
        } else {
            map = this.map;
            str = map.get(FaqConstants.FAQ_IS_DEEPLICK);
        }
        map.put(FaqConstants.FAQ_IS_DEEPLICK, str);
    }

    private String getLanguageCode(String str, String str2) {
        if (str.contains("_")) {
            str = str.replace("_", Constants.LINK);
        }
        if (!str.contains(Constants.LINK) || str.split(Constants.LINK).length <= 0) {
            return str;
        }
        if (str.split(Constants.LINK).length != 1 && !l.e(str.split(Constants.LINK)[1])) {
            return str;
        }
        return str.split(Constants.LINK)[0] + Constants.LINK + str2;
    }
}
