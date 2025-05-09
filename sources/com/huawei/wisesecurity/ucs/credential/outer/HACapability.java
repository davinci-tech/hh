package com.huawei.wisesecurity.ucs.credential.outer;

import android.content.Context;
import com.huawei.wisesecurity.kfs.ha.message.ReportMsgBuilder;

/* loaded from: classes7.dex */
public interface HACapability {
    public static final String UCS_CREDENTIAL_HA_SERVICE_TAG = "UCS_CREDENTIAL";

    void onEvent(Context context, String str, ReportMsgBuilder reportMsgBuilder);
}
