package com.huawei.health.arkuix;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import com.huawei.operation.utils.WebViewHelp;
import defpackage.moj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class IntentParams {
    private static final String PARAMS = "params";
    private Map<String, Object> params;

    public IntentParams(List<Builder.PageInfo> list) {
        HashMap hashMap = new HashMap();
        this.params = hashMap;
        hashMap.put("params", list);
    }

    public String toString() {
        return moj.e(this.params);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<PageInfo> infos = new ArrayList();

        public Builder addPageType(String str) {
            this.infos.add(new PageInfo(CommonUtil.PAGE_TYPE, 5, str));
            return this;
        }

        public Builder addPageId(String str) {
            this.infos.add(new PageInfo("barType", 10, "button"));
            if ("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) && !TextUtils.isEmpty(Build.MODEL) && (Build.MODEL.toLowerCase(Locale.ENGLISH).startsWith("zuy") || Build.MODEL.toLowerCase(Locale.ENGLISH).startsWith("blk"))) {
                this.infos.add(new PageInfo("bridgeFirst", 10, "1"));
            }
            this.infos.add(new PageInfo("pageId", 10, str));
            return this;
        }

        public Builder addTimeStamp(String str) {
            this.infos.add(new PageInfo(UserCloseRecord.TIME_STAMP, 9, str));
            return this;
        }

        public Builder addRecord(String str) {
            this.infos.add(new PageInfo("record", 10, str));
            return this;
        }

        public Builder addBiFrom(String str, String str2, String str3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(WebViewHelp.BI_KEY_PULL_FROM, str);
                jSONObject.put("resourceName", str2);
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put("pullOrder", str3);
                }
                this.infos.add(new PageInfo("biFrom", 10, jSONObject.toString()));
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder addInfo(String str, int i, String str2) {
            this.infos.add(new PageInfo(str, i, str2));
            return this;
        }

        public IntentParams build() {
            return new IntentParams(this.infos);
        }

        class PageInfo {
            private String key;
            private int type;
            private String value;

            public PageInfo(String str, int i, String str2) {
                this.key = str;
                this.type = i;
                this.value = str2;
            }
        }
    }
}
