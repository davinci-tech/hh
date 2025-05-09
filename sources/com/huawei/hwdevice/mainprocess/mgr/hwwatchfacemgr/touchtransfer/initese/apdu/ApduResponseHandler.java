package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.apdu;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public abstract class ApduResponseHandler {
    private static final int COMMAND_INDEX = 1;
    private static final int ERROR_INDEX = 4;
    private static final int MESSAGE_CONTENT_LENGTH = 5;
    private static final int MESSAGE_FAILURE = 2;
    private static final int MESSAGE_SEND_NEXT = 1;
    private static final int MESSAGE_SEND_NEXT_ERROR = 3;
    private static final int MESSAGE_SUCCESS = 0;
    private static final int RESP_INDEX = 2;
    private static final int RESULT_INDEX = 0;
    private static final int STATUS_WORD_INDEX = 3;
    private static final String TAG = "ApduResponseHandler";
    private b mHandler;
    private boolean mIsUseSynchronousMode;

    public abstract void onFailure(int i, Error error);

    public abstract void onSendNext(int i, int i2, String str, String str2);

    public abstract void onSendNextError(int i, int i2, String str, String str2, Error error);

    public abstract void onSuccess(String str);

    public ApduResponseHandler() {
        setUseSynchronousMode(true);
    }

    private void setUseSynchronousMode(boolean z) {
        if (!z && Looper.myLooper() == null) {
            z = true;
        }
        if (!z && this.mHandler == null) {
            this.mHandler = new b(this);
        } else if (z && this.mHandler != null) {
            this.mHandler = null;
        } else {
            LogUtil.h(TAG, "no match condition");
        }
        this.mIsUseSynchronousMode = z;
    }

    private void sendMessage(Message message) {
        if (this.mIsUseSynchronousMode) {
            handleMessage(message);
        } else {
            LogUtil.h(TAG, "sendMessage, do not need to deal the message");
        }
    }

    public void handleMessage(Message message) {
        if (message == null) {
            LogUtil.h(TAG, "handleMessage, message is null.");
            return;
        }
        LogUtil.a(TAG, "handleMessage:", Integer.valueOf(message.what));
        String str = null;
        Object[] objArr = message.obj instanceof Object[] ? (Object[]) message.obj : null;
        try {
            int i = message.what;
            if (i == 0) {
                if (objArr == null || objArr.length < 1) {
                    return;
                }
                Object obj = objArr[0];
                if (obj != null) {
                    str = obj.toString();
                }
                onSuccess(str);
                return;
            }
            if (i == 1) {
                if (objArr == null || objArr.length < 4) {
                    return;
                }
                onSendNext(Integer.parseInt(objArr[0].toString()), Integer.parseInt(objArr[1].toString()), objArr[2].toString(), objArr[3].toString());
                return;
            }
            if (i == 2) {
                if (objArr == null || objArr.length < 2) {
                    return;
                }
                onFailure(Integer.parseInt(objArr[0].toString()), parseErrorObject(objArr[1]));
                return;
            }
            if (i == 3) {
                if (objArr == null || objArr.length < 5) {
                    return;
                }
                onSendNextError(Integer.parseInt(objArr[0].toString()), Integer.parseInt(objArr[1].toString()), objArr[2].toString(), objArr[3].toString(), parseErrorObject(objArr[4]));
                return;
            }
            LogUtil.h(TAG, "handleMessage, message.what is not support");
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "handleMessage, NumberFormatException");
        }
    }

    private Error parseErrorObject(Object obj) {
        if (obj instanceof Error) {
            return (Error) obj;
        }
        return null;
    }

    public void sendSuccessMessage(String str) {
        sendMessage(obtainMessage(0, new Object[]{str}));
    }

    public void sendSendNextMessage(int i, int i2, String str, String str2) {
        sendMessage(obtainMessage(1, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str, str2}));
    }

    public void sendSendNextErrorMessage(int i, int i2, String str, String str2, Error error) {
        sendMessage(obtainMessage(3, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str, str2, error}));
    }

    public void sendFailureMessage(int i, Error error) {
        sendMessage(obtainMessage(2, new Object[]{Integer.valueOf(i), error}));
    }

    static class b extends Handler {
        private final ApduResponseHandler e;

        b(ApduResponseHandler apduResponseHandler) {
            this.e = apduResponseHandler;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            ApduResponseHandler apduResponseHandler = this.e;
            if (apduResponseHandler != null) {
                apduResponseHandler.handleMessage(message);
            }
        }
    }

    private Message obtainMessage(int i, Object obj) {
        b bVar = this.mHandler;
        if (bVar == null) {
            Message obtain = Message.obtain();
            if (obtain == null) {
                return obtain;
            }
            obtain.what = i;
            obtain.obj = obj;
            return obtain;
        }
        return Message.obtain(bVar, i, obj);
    }
}
