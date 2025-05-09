package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class DefaultTemplate implements Serializable {
    private static final String INVALID_ID = "-1";
    private static final long serialVersionUID = 3193003283515036853L;
    private String defTptId;
    private Integer tptFcCtl;

    public boolean c() {
        return (TextUtils.isEmpty(this.defTptId) || "-1".equals(this.defTptId)) ? false : true;
    }

    public Integer b() {
        return this.tptFcCtl;
    }

    public void a(String str) {
        this.defTptId = str;
    }

    public void a(Integer num) {
        this.tptFcCtl = num;
    }

    public String a() {
        return this.defTptId;
    }
}
