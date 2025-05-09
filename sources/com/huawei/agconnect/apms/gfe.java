package com.huawei.agconnect.apms;

import android.text.TextUtils;
import com.squareup.okhttp.internal.Version;

/* loaded from: classes8.dex */
public class gfe {
    public static final String abc;
    public int bcd;
    public int cde;

    static {
        String str;
        try {
            str = Version.userAgent();
        } catch (Throwable unused) {
            str = "";
        }
        abc = str;
    }

    public gfe() {
        if (!TextUtils.isEmpty(abc)) {
            abc();
        } else {
            this.bcd = 2;
            this.cde = 0;
        }
    }

    public final void abc() {
        try {
            String[] split = abc.split("/")[1].split("\\.");
            if (split.length > 2) {
                this.bcd = Integer.parseInt(split[0]);
                this.cde = Integer.parseInt(split[1]);
                Integer.parseInt(split[2]);
            } else {
                this.bcd = 2;
                this.cde = 0;
            }
        } catch (Throwable unused) {
            this.bcd = 2;
            this.cde = 0;
        }
    }
}
