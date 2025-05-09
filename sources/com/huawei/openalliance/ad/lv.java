package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public class lv {
    public static lw a(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return new lu();
        }
        if (contentRecord.R() != null || (contentRecord.S() != null && "video/mp4".equals(contentRecord.S().a()))) {
            if (ly.e()) {
                return new ly();
            }
        } else if (lr.a()) {
            return new lr();
        }
        return new lu();
    }
}
