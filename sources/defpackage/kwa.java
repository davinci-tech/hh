package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.login.ui.login.LoginInit;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class kwa {
    public static void c(final int i, final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kwa.4
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(5);
                SmartMsgDbObject d = kvw.c(BaseApplication.getContext()).d(kvs.b(BaseApplication.getContext()).c(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), i), i, 0);
                if (d != null) {
                    LogUtil.a("SMART_SmarterData", "Smart card msg = ", Integer.valueOf(d.getId()));
                    arrayList.add(d);
                }
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, arrayList);
                }
            }
        });
    }
}
