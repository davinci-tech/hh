package com.huawei.hwversionmgr.utils.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kxj;
import defpackage.kxl;
import defpackage.kxu;

/* loaded from: classes5.dex */
public abstract class AppCheckNewVersionHandler extends Handler {
    private static final String TAG = "AppCheckNewVersionHandler";
    private boolean mIsAw70;
    private boolean mIsScale;

    public abstract void handleCheckFailed(int i);

    public abstract void handleCheckSuccess(kxj kxjVar);

    public void setIsAw70(boolean z) {
        this.mIsAw70 = z;
    }

    public boolean getIsAw70() {
        return this.mIsAw70;
    }

    public void setIsScale(boolean z) {
        this.mIsScale = z;
    }

    public AppCheckNewVersionHandler() {
        this.mIsAw70 = false;
        this.mIsScale = false;
    }

    public AppCheckNewVersionHandler(Looper looper) {
        super(looper);
        this.mIsAw70 = false;
        this.mIsScale = false;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i == 0) {
            LogUtil.c(TAG, "handleMessage check new version failed,FAILED_REASON_NETWORK");
            try {
                handleCheckFailed(1);
                return;
            } catch (Exception unused) {
                LogUtil.b(TAG, "handleMessage handleCheckFailed");
                return;
            }
        }
        if (i == 1) {
            LogUtil.c(TAG, "handleMessage check app new version success");
            checkAppStatusSuccess(message.obj);
        } else if (i == 2) {
            LogUtil.c(TAG, "handleMessage check band new version success");
            checkBandStatusSuccess(message.obj);
        } else {
            LogUtil.c(TAG, "handleMessage default");
        }
    }

    private void checkAppStatusSuccess(Object obj) {
        try {
            LogUtil.c(TAG, "checkAppStatusSuccess status", Integer.valueOf(kxu.e().p()));
            if (kxu.e().p() == 1) {
                handleCheckFailed(0);
                return;
            }
            if (kxu.e().p() == -1) {
                handleCheckFailed(2);
                return;
            }
            if (kxu.e().p() == 0) {
                if (obj != null && !(obj instanceof kxj)) {
                    LogUtil.b(TAG, "checkAppStatusSuccess object instance error");
                    return;
                } else {
                    handleCheckSuccess((kxj) obj);
                    return;
                }
            }
            handleCheckFailed(3);
        } catch (Exception unused) {
            LogUtil.b(TAG, "checkAppStatusSuccess error");
        }
    }

    private void checkBandStatusSuccess(Object obj) {
        if (obj != null) {
            try {
                if (!(obj instanceof kxj)) {
                    LogUtil.b(TAG, "checkBandStatusSuccess object instance error");
                    return;
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
                LogUtil.b(TAG, "checkBandStatusSuccess Exception");
                return;
            }
        }
        kxj kxjVar = (kxj) obj;
        if (this.mIsAw70) {
            checkDeviceStatus(kxu.d().p(), kxjVar);
            return;
        }
        if (this.mIsScale) {
            kxl l = kxu.l();
            if (l != null) {
                checkDeviceStatus(l.p(), kxjVar);
                return;
            }
            return;
        }
        checkBandDeviceStatus(kxjVar);
    }

    private void checkBandDeviceStatus(kxj kxjVar) {
        kxl b = kxu.b();
        if (b != null) {
            checkDeviceStatus(b.p(), kxjVar);
        }
    }

    private void checkDeviceStatus(int i, kxj kxjVar) {
        try {
            LogUtil.c(TAG, "checkDeviceStatus status", Integer.valueOf(i));
            if (i == 1) {
                handleCheckFailed(0);
            } else if (i == -1) {
                handleCheckFailed(2);
            } else if (i == 0) {
                handleCheckSuccess(kxjVar);
            } else {
                handleCheckFailed(3);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
            LogUtil.b(TAG, "checkDeviceStatus Exception");
        }
    }
}
