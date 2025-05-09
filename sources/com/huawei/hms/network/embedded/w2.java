package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes9.dex */
public class w2 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5554a = "FileRecordManager";
    public static final w2 b = new w2();

    public void saveToLocalFile(String str) {
    }

    public String getDate() {
        String format = new SimpleDateFormat("yyyy-MM-dd&&HH:mm:ss:SSSS", Locale.ENGLISH).format(new Date());
        Logger.v(f5554a, "the time is : %s", format);
        return format;
    }

    public static w2 getInstance() {
        return b;
    }
}
