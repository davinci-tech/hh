package defpackage;

import android.database.ContentObserver;
import android.os.Handler;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kdm extends ContentObserver {
    public kdm(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        LogUtil.a("StorePutBackObserver", "StorePutBackObserver onChange.");
        kdl.c().d();
    }
}
