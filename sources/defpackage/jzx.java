package defpackage;

import health.compact.a.CommonUtil;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class jzx {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14234a;
    private static final String[] e;

    static {
        if (CommonUtil.ba()) {
            f14234a = new String[]{"_id", "version", "starred", "times_contacted", "account_type", "account_name", "starred_order", "is_private"};
        } else {
            f14234a = new String[]{"_id", "version", "starred", "times_contacted", "account_type", "account_name"};
        }
        e = new String[]{"raw_contact_id", "_id", "mimetype", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", "data11", "data12", "data13", "data14", "data15", "data_sync1", "data_sync2"};
    }

    public static String[] b() {
        String[] strArr = f14234a;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public static String[] d() {
        String[] strArr = e;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }
}
