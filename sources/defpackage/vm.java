package defpackage;

import android.content.Context;
import com.huawei.ads.fund.db.BaseDbHelper;
import com.huawei.ads.fund.db.BaseDbUpdateHelper;

/* loaded from: classes2.dex */
public class vm extends BaseDbHelper {

    /* renamed from: a, reason: collision with root package name */
    private static vm f17715a;
    private static final byte[] b = new byte[0];

    @Override // com.huawei.ads.fund.db.BaseDbHelper
    public boolean needKeepData() {
        return true;
    }

    @Override // com.huawei.ads.fund.db.BaseDbHelper
    public String getTag() {
        return "AdsRecDbHelper";
    }

    @Override // com.huawei.ads.fund.db.BaseDbHelper
    public BaseDbUpdateHelper getDbUpdateHelper() {
        return new vi(this);
    }

    public static vm c(Context context) {
        vm vmVar;
        synchronized (b) {
            if (f17715a == null) {
                f17715a = new vm(wm.d(context.getApplicationContext()));
            }
            BaseDbHelper.OPEN_COUNTER.incrementAndGet();
            vmVar = f17715a;
        }
        return vmVar;
    }

    private vm(Context context) {
        super(context, "hiad_recd.db", null, 7);
    }
}
