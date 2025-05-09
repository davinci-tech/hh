package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class LandpageAppWhiteList implements Serializable {
    private static final String ALLOW = "-1";
    private static final String REJECT = "-2";
    private static final String TAG = "LandpageAppWhiteList";
    private static final long serialVersionUID = -1613710950822978059L;
    private String type = "-1";
    private Set<String> packageList = null;
    private Map<String, Boolean> promptMap = new HashMap();

    public Boolean c(String str) {
        return this.promptMap.get(str);
    }

    public boolean b(String str) {
        if ("-1".equals(this.type)) {
            return true;
        }
        if ("-2".equals(this.type)) {
            return false;
        }
        Set<String> set = this.packageList;
        return set == null || set.contains(str);
    }

    public void a(Map<String, Boolean> map) {
        if (map != null) {
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                this.promptMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void a(String str, boolean z) {
        this.promptMap.put(str, Boolean.valueOf(z));
    }

    public void a(String str) {
        String str2;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if ("-2".equals(str)) {
                this.type = "-2";
                return;
            }
            if ("-1".equals(str)) {
                this.type = "-1";
                return;
            }
            if (this.packageList == null) {
                this.packageList = new HashSet();
            }
            this.packageList.clear();
            String[] split = str.split(",");
            int length = split.length;
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    if (!TextUtils.isEmpty(split[i])) {
                        this.packageList.add(split[i]);
                    }
                }
            }
            if (this.packageList.size() > 0) {
                this.type = null;
            }
        } catch (RuntimeException unused) {
            str2 = "fromString RuntimeException";
            ho.c(TAG, str2);
        } catch (Exception unused2) {
            str2 = "fromString Exception";
            ho.c(TAG, str2);
        }
    }
}
