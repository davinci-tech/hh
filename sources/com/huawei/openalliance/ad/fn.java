package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import java.util.List;

/* loaded from: classes5.dex */
public class fn extends ep implements gg {
    private static fn c;
    private static final byte[] d = new byte[0];

    @Override // com.huawei.openalliance.ad.ep
    public void e() {
        super.e();
        a(UserCloseRecord.class, (fi) null, (String[]) null);
    }

    @Override // com.huawei.openalliance.ad.gg
    public void a(UserCloseRecord userCloseRecord) {
        a(UserCloseRecord.class, userCloseRecord.d(this.f6846a));
    }

    @Override // com.huawei.openalliance.ad.gg
    public void a(long j) {
        a(UserCloseRecord.class, fi.USER_CLOSE_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(j)});
    }

    @Override // com.huawei.openalliance.ad.gg
    public List<UserCloseRecord> a(long j, long j2) {
        return a(UserCloseRecord.class, null, fi.USER_CLOSE_QUERY_BY_TIME_WHERE, new String[]{String.valueOf(j), String.valueOf(j2)}, null, null);
    }

    public static fn a(Context context) {
        fn fnVar;
        synchronized (d) {
            if (c == null) {
                c = new fn(context);
            }
            fnVar = c;
        }
        return fnVar;
    }

    private fn(Context context) {
        super(context);
    }
}
