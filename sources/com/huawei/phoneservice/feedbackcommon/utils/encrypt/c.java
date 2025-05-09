package com.huawei.phoneservice.feedbackcommon.utils.encrypt;

import android.content.Context;
import com.huawei.phoneservice.feedbackcommon.utils.g;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;

/* loaded from: classes6.dex */
public class c {
    public static String d(Context context) {
        return AesGcm.decrypt(g.a(context, "r_config", "faq_normal_config"), e.e(context));
    }
}
