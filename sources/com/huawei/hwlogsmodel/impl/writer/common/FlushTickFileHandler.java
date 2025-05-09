package com.huawei.hwlogsmodel.impl.writer.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.LogContext;

/* loaded from: classes.dex */
public abstract class FlushTickFileHandler extends Handler {
    private static final int LOGFILE_DELAY = 30000;
    private static final int MSG_FLUSH_LOG_FILE_WATCH_DOG = 1002;
    private static final int MSG_SAVE_LOG_FILE = 1000;
    private static final int MSG_SAVE_LOG_FILE_NO_DETAIL = 1001;
    private static final String TAG = "LogUtil_FlushTickFileHandler";

    protected abstract void dataTicker(Object obj, String str);

    protected abstract void onFlushLog();

    public FlushTickFileHandler(Looper looper) {
        super(looper);
        sendEmptyMessageDelayed(1002, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    public void saveLogObj(Object obj, String str) {
        if (TextUtils.isEmpty(str)) {
            saveLogObj(obj);
            return;
        }
        Message obtainMessage = obtainMessage(1000);
        LogContext logContext = new LogContext();
        logContext.b(obj);
        logContext.c(str);
        obtainMessage.obj = logContext;
        sendMessage(obtainMessage);
    }

    public void saveLogObj(Object obj) {
        Message obtainMessage = obtainMessage(1001);
        obtainMessage.obj = obj;
        sendMessage(obtainMessage);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1000:
                removeMessages(1002);
                sendEmptyMessageDelayed(1002, OpAnalyticsConstants.H5_LOADING_DELAY);
                if (message.obj instanceof LogContext) {
                    LogContext logContext = (LogContext) message.obj;
                    dataTicker(logContext.d(), logContext.c());
                    break;
                } else {
                    LogUtil.b(TAG, "handleMessage instanceof error");
                    break;
                }
            case 1001:
                removeMessages(1002);
                sendEmptyMessageDelayed(1002, OpAnalyticsConstants.H5_LOADING_DELAY);
                dataTicker(message.obj, null);
                break;
            case 1002:
                removeMessages(1002);
                sendEmptyMessageDelayed(1002, OpAnalyticsConstants.H5_LOADING_DELAY);
                onFlushLog();
                break;
        }
    }
}
