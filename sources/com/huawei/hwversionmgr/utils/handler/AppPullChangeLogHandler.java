package com.huawei.hwversionmgr.utils.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kxm;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class AppPullChangeLogHandler extends Handler {
    private static final String TAG = "AppPullChangeLogHandler";

    public abstract void pullChangeLogFailed();

    public abstract void pullChangeLogSuccess(List<kxm> list);

    public AppPullChangeLogHandler() {
    }

    public AppPullChangeLogHandler(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        List<kxm> list;
        super.handleMessage(message);
        int i = message.what;
        if (i == 0) {
            LogUtil.a(TAG, "handleMessage pull changelog failed!");
            pullChangeLogFailed();
            return;
        }
        if (i == 1) {
            if (message.obj != null) {
                if (message.obj instanceof List) {
                    LogUtil.a(TAG, "handleMessage success msg.obj is instanceof List<?>!");
                    list = (List) message.obj;
                    pullChangeLogSuccess(list);
                    return;
                }
                LogUtil.a(TAG, "handleMessage success msg.obj is not instanceof List<?>!");
            } else {
                LogUtil.a(TAG, "handleMessage success msg.obj is null!");
            }
            list = null;
            pullChangeLogSuccess(list);
            return;
        }
        LogUtil.a(TAG, "handleMessage default!");
    }
}
