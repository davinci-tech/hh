package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class pel extends Handler {
    private final WeakReference<WearHomeActivity> b;

    public pel(WearHomeActivity wearHomeActivity) {
        this.b = new WeakReference<>(wearHomeActivity);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message == null) {
            LogUtil.h("WearHomeHandler", "message is null.");
            return;
        }
        super.handleMessage(message);
        WearHomeActivity wearHomeActivity = this.b.get();
        LogUtil.a("WearHomeHandler", "handleMessage, handler: ", this, ", activity: ", wearHomeActivity);
        if (wearHomeActivity == null) {
            LogUtil.h("WearHomeHandler", "activity is null");
            return;
        }
        int i = message.what;
        if (i == 24) {
            if (wearHomeActivity.y != null) {
                wearHomeActivity.y.i();
                return;
            }
            return;
        }
        if (i != 1011) {
            switch (i) {
                case 1001:
                    if (wearHomeActivity.y != null) {
                        wearHomeActivity.y.d();
                        break;
                    }
                    break;
                case 1002:
                    if (wearHomeActivity.y != null) {
                        wearHomeActivity.y.b();
                        break;
                    }
                    break;
                case 1003:
                    if (wearHomeActivity.y != null) {
                        wearHomeActivity.y.dlM_(message);
                        break;
                    }
                    break;
                default:
                    dms_(message, wearHomeActivity);
                    break;
            }
            return;
        }
        if (message.obj instanceof DeviceInfo) {
            wearHomeActivity.b((DeviceInfo) message.obj);
            if (wearHomeActivity.y != null) {
                wearHomeActivity.y.d((DeviceInfo) message.obj);
            }
        }
    }

    private void dms_(Message message, WearHomeActivity wearHomeActivity) {
        int i = message.what;
        if (i == 1021) {
            LogUtil.a("WearHomeHandler", "upload log overtimes");
            if (wearHomeActivity.p != null) {
                wearHomeActivity.p.f();
                return;
            }
            return;
        }
        if (i == 1023) {
            dmu_(message, wearHomeActivity);
            return;
        }
        if (i == 1026) {
            if (wearHomeActivity.p != null) {
                wearHomeActivity.p.dlF_(message);
                return;
            }
            return;
        }
        if (i == 1033) {
            LogUtil.a("WearHomeHandler", "upload log DEVICE_GOTO_FEEDBACK");
            if (wearHomeActivity.p != null) {
                wearHomeActivity.p.c();
                return;
            }
            return;
        }
        if (i != 1041) {
            switch (i) {
                case 1004:
                    if (wearHomeActivity.y != null) {
                        wearHomeActivity.y.c();
                        break;
                    }
                    break;
                case 1005:
                    if (wearHomeActivity.y != null) {
                        wearHomeActivity.y.dlN_(message);
                        break;
                    }
                    break;
                case 1006:
                    if (wearHomeActivity.y != null) {
                        wearHomeActivity.y.e();
                        break;
                    }
                    break;
                default:
                    dmt_(message, wearHomeActivity);
                    break;
            }
            return;
        }
        if (wearHomeActivity.p != null) {
            wearHomeActivity.p.e();
        }
    }

    private void dmu_(Message message, WearHomeActivity wearHomeActivity) {
        Bundle data = message.getData();
        if (data != null) {
            int i = data.getInt("bugTypeId");
            String string = data.getString("fileLogId");
            String string2 = data.getString("dtsNumber");
            if (wearHomeActivity.p == null || TextUtils.isEmpty(string2)) {
                return;
            }
            wearHomeActivity.p.c(i, string2, string);
        }
    }

    private void dmt_(Message message, WearHomeActivity wearHomeActivity) {
        int i = message.what;
        LogUtil.a("WearHomeHandler", "processOtherHandler what:", Integer.valueOf(i));
        if (i == 8) {
            if (wearHomeActivity.p != null) {
                LogUtil.a("WearHomeHandler", "handlerRestoreFactorySuccess");
                wearHomeActivity.p.d();
                return;
            }
            return;
        }
        if (i == 1022) {
            if (wearHomeActivity.p != null) {
                wearHomeActivity.p.i();
                return;
            }
            return;
        }
        if (i == 1024) {
            if (!(message.obj instanceof String) || wearHomeActivity.p == null) {
                return;
            }
            wearHomeActivity.p.d((String) message.obj);
            return;
        }
        if (i == 10) {
            wearHomeActivity.p.b(false);
            wearHomeActivity.p.a();
            return;
        }
        if (i == 11) {
            if (wearHomeActivity.p != null) {
                LogUtil.a("WearHomeHandler", "destroyLoadingDialog");
                if (message.obj instanceof Boolean) {
                    if (((Boolean) message.obj).booleanValue()) {
                        return;
                    }
                    LogUtil.a("WearHomeHandler", "destroyLoadingDialog false");
                    wearHomeActivity.p.b(false);
                    LogUtil.a("WearHomeHandler", "destroyLoadingDialog handlerRestoreFactorySuccess");
                    return;
                }
                wearHomeActivity.p.b(true);
                return;
            }
            return;
        }
        dmq_(message, wearHomeActivity);
    }

    private void dmq_(Message message, WearHomeActivity wearHomeActivity) {
        int i = message.what;
        LogUtil.a("WearHomeHandler", "handleAnotherHandler what:", Integer.valueOf(i));
        switch (i) {
            case 1034:
                if (wearHomeActivity.r != null) {
                    wearHomeActivity.r.j();
                    break;
                }
                break;
            case 1035:
                if (wearHomeActivity.u != null) {
                    wearHomeActivity.u.a();
                    break;
                }
                break;
            case 1036:
                if (wearHomeActivity.w != null) {
                    wearHomeActivity.w.c();
                    break;
                }
                break;
            case 1037:
                if (wearHomeActivity.u != null) {
                    wearHomeActivity.u.c();
                    break;
                }
                break;
            case 1038:
                if (wearHomeActivity.w != null) {
                    wearHomeActivity.w.a();
                    break;
                }
                break;
            case 1039:
                wearHomeActivity.b();
                break;
            default:
                switch (i) {
                    case 300005:
                        if (wearHomeActivity.v != null) {
                            wearHomeActivity.v.setVisibility(8);
                            break;
                        }
                        break;
                    case 300006:
                        if (wearHomeActivity.v != null && wearHomeActivity.r != null) {
                            wearHomeActivity.r.c();
                            wearHomeActivity.d(1);
                            break;
                        }
                        break;
                    default:
                        dmr_(message, wearHomeActivity);
                        break;
                }
        }
    }

    private void dmr_(Message message, WearHomeActivity wearHomeActivity) {
        int i = message.what;
        LogUtil.a("WearHomeHandler", "handleAnotherHandlerTwo what:", Integer.valueOf(i));
        if (i == 1040) {
            if (wearHomeActivity.s != null) {
                wearHomeActivity.s.e(((Integer) message.obj).intValue());
                return;
            }
            return;
        }
        LogUtil.h("WearHomeHandler", "handleAnotherHandlerTwo default what:", Integer.valueOf(i));
    }
}
