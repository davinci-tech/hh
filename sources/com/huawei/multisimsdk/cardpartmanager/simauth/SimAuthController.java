package com.huawei.multisimsdk.cardpartmanager.simauth;

import android.content.Context;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

/* loaded from: classes5.dex */
public class SimAuthController {
    private final TelephonyManager b;
    private Context c;
    private final SubscriptionManager e;

    public String b(String str) {
        return str;
    }

    public SimAuthController(Context context) {
        this.c = context;
        this.b = (TelephonyManager) context.getSystemService("phone");
        this.e = SubscriptionManager.from(this.c);
    }

    public String d() {
        String str;
        String str2;
        String subscriberId = this.b.getSubscriberId();
        if (subscriberId != null) {
            str = subscriberId.substring(0, 3);
            str2 = subscriberId.substring(3, 5);
        } else {
            str = null;
            str2 = null;
        }
        return "0" + subscriberId + "@nai.epc.mnc0" + str2 + ".mcc" + str + ".3gppnetwork.org";
    }
}
