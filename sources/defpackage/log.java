package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import com.huawei.multisimsdk.multidevicemanager.common.InProgressData;
import com.huawei.multisimsdk.multidevicemanager.model.MultiDeviceWebManager;
import com.huawei.multisimsdk.multidevicemanager.model.QueryManager;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class log {

    /* renamed from: a, reason: collision with root package name */
    private static c f14802a = null;
    private static log b = null;
    private static final String d = "HandleControl";
    private AuthParam f = null;
    private HandlerThread h;
    private Context j;
    private static final HashMap<String, Integer> c = new HashMap<>();
    private static final HashMap<Integer, lns> e = new HashMap<>();
    private static final Object i = new Object();

    private int c(int i2) {
        if (i2 != 101) {
            return i2 != 102 ? 0 : 1;
        }
        return 2;
    }

    private log() {
        this.h = null;
        HandlerThread handlerThread = new HandlerThread(d);
        this.h = handlerThread;
        handlerThread.start();
        f14802a = new c(this.h.getLooper());
    }

    public static log a() {
        log logVar;
        synchronized (i) {
            if (b == null) {
                b = new log();
            }
            logVar = b;
        }
        return logVar;
    }

    public c h() {
        return f14802a;
    }

    public void b(Context context) {
        b(context, null);
    }

    public void b(Context context, AuthParam authParam) {
        this.j = context;
        this.f = authParam;
    }

    public Context c() {
        return this.j;
    }

    public AuthParam b() {
        return this.f;
    }

    public void e() {
        if (f14802a != null) {
            f14802a = null;
        }
        HandlerThread handlerThread = this.h;
        if (handlerThread != null) {
            handlerThread.quit();
            this.h = null;
        }
        if (b != null) {
            b = null;
        }
        this.j = null;
    }

    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            loq.c(log.d, "msg.what = " + message.what);
            switch (message.what) {
                case 100:
                case 101:
                case 102:
                    log.this.bXZ_(message);
                    break;
                case 106:
                case 8888:
                    log.this.bXW_(message);
                    break;
                case 107:
                    log.this.bYb_(message);
                    break;
                case 113:
                case 117:
                    log logVar = log.this;
                    logVar.bYa_(logVar.j, message);
                    break;
                case 114:
                case 124:
                    InProgressData inProgressData = message.obj instanceof InProgressData ? (InProgressData) message.obj : null;
                    QueryManager queryManager = new QueryManager(log.this.j, log.this.f);
                    queryManager.d(inProgressData);
                    queryManager.b();
                    break;
                case 115:
                case 119:
                case 126:
                case 8889:
                case 8890:
                    log.this.bXW_(message);
                    break;
                case 125:
                    log.this.bXW_(message);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bYb_(Message message) {
        loq.c(d, "Start invalid token handler ");
        if (message != null) {
            d(message.obj instanceof InProgressData ? (InProgressData) message.obj : null);
        }
        bXW_(message);
    }

    private void d(InProgressData inProgressData) {
        if (inProgressData != null) {
            String primary = inProgressData.getPrimary();
            String a2 = lop.a(this.j, primary, "Tag");
            if (!"".equals(a2)) {
                lop.b(this.j, a2, "authen_Token");
                lop.b(this.j, a2, "authorization");
                lop.b(this.j, a2, "Tag");
                lop.b(this.j, primary, "Tag");
            }
            lop.b(this.j, inProgressData.getPrimary(), "authen_Token");
            lop.b(this.j, inProgressData.getPrimary(), "authorization");
            loq.c(d, "Start to delete token info");
            return;
        }
        loq.c(d, "Start to delete token info failed, inProgressData is null ");
    }

    private InProgressData d(lns lnsVar, int i2) {
        InProgressData inProgressData = new InProgressData();
        if (lnsVar != null) {
            inProgressData.setPrimary(lnsVar.c());
            inProgressData.setPrimaryIDtype(lnsVar.i());
            inProgressData.setSecondaryID(lnsVar.f());
            inProgressData.setSecondarytype(lnsVar.g());
            inProgressData.setRsn(i2);
            inProgressData.setType(lnsVar.m());
            inProgressData.setNikename(lnsVar.a());
            inProgressData.setServiceType(lnsVar.j());
            inProgressData.setDeviceid(lnsVar.e());
            inProgressData.setSecondaryDeviceId(lnsVar.h());
        }
        return inProgressData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bXZ_(Message message) {
        lns lnsVar = message.obj instanceof lns ? (lns) message.obj : null;
        if (lnsVar != null) {
            int c2 = lop.c();
            if (bXX_(lnsVar, message).booleanValue()) {
                return;
            }
            e.put(Integer.valueOf(c2), lnsVar);
            message.obj = d(lnsVar, c2);
            bXV_(message);
        }
    }

    private void bXV_(Message message) {
        InProgressData inProgressData = message.obj instanceof InProgressData ? (InProgressData) message.obj : null;
        if (inProgressData != null) {
            int type = inProgressData.getType();
            loq.c(d, "Handle authCallbackMethod, msg.what = " + message.what + ", type = " + type);
            if (100 == type) {
                c cVar = f14802a;
                cVar.sendMessage(cVar.obtainMessage(113, message.obj));
            } else if (101 == type) {
                c cVar2 = f14802a;
                cVar2.sendMessage(cVar2.obtainMessage(117, message.obj));
            } else {
                c cVar3 = f14802a;
                cVar3.sendMessage(cVar3.obtainMessage(124, message.obj));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bXW_(Message message) {
        if (message == null) {
            loq.c(d, "Handle back data method failed, msg is null.");
            return;
        }
        Message message2 = null;
        InProgressData inProgressData = message.obj instanceof InProgressData ? (InProgressData) message.obj : null;
        if (inProgressData != null) {
            HashMap<String, Integer> hashMap = c;
            if (hashMap.containsKey(inProgressData.getSecondaryID())) {
                hashMap.remove(inProgressData.getSecondaryID());
            }
            int rsn = inProgressData.getRsn();
            HashMap<Integer, lns> hashMap2 = e;
            lns lnsVar = hashMap2.get(Integer.valueOf(rsn));
            if (lnsVar != null) {
                if (8889 != message.what) {
                    message2 = lnsVar.bXO_();
                } else {
                    message2 = lnsVar.bXN_();
                }
            }
            if (message2 != null) {
                loq.c(d, "Message.sendToTarget() message.what= " + message2.what);
                message2.obj = bXY_(message);
                lop.bYq_(message2);
                if (8889 != message.what) {
                    hashMap2.remove(Integer.valueOf(rsn));
                }
                hashMap.clear();
                return;
            }
            loq.c(d, "Message is null");
            return;
        }
        loq.c(d, "mutiProgressData is null");
    }

    private lnl bXY_(Message message) {
        lnl lnlVar = new lnl();
        InProgressData inProgressData = message.obj instanceof InProgressData ? (InProgressData) message.obj : null;
        if (inProgressData != null) {
            int i2 = message.what;
            if (i2 != 106) {
                if (i2 != 107 && i2 != 115 && i2 != 119) {
                    if (i2 != 125) {
                        if (i2 != 126) {
                            switch (i2) {
                                case 100:
                                case 101:
                                case 102:
                                    lnlVar.b(1);
                                    lnlVar.a(1);
                                    break;
                                default:
                                    switch (i2) {
                                        case 8888:
                                            lnlVar.b(1);
                                            lnlVar.a(3);
                                            break;
                                        case 8889:
                                            lnlVar.b(3);
                                            break;
                                    }
                            }
                        }
                    }
                    lnlVar.b(0);
                }
                lnlVar.b(1);
                lnlVar.a(inProgressData.getResultcode());
            } else {
                lnlVar.b(1);
                d(inProgressData);
                lnlVar.a(1004);
            }
            lnlVar.d(c(inProgressData.getType()));
            loq.c(d, "Message - multiSimAsyncResult reasonCode = " + lnlVar.b());
            lnlVar.d(inProgressData.getMultiSIMServiceInfo());
            lnlVar.d(inProgressData.getWebViewData());
        } else {
            loq.c(d, "Message - inProgressData is null ");
        }
        return lnlVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bYa_(Context context, Message message) {
        InProgressData inProgressData = message.obj instanceof InProgressData ? (InProgressData) message.obj : null;
        MultiDeviceWebManager multiDeviceWebManager = new MultiDeviceWebManager(context, this.f);
        multiDeviceWebManager.a(inProgressData);
        multiDeviceWebManager.a();
    }

    private Boolean bXX_(lns lnsVar, Message message) {
        if (lnsVar != null && 102 != message.what) {
            HashMap<String, Integer> hashMap = c;
            if (hashMap.containsKey(lnsVar.f())) {
                Message bXO_ = lnsVar.bXO_();
                if (bXO_ != null) {
                    loq.c(d, "Message.sendToTarget() msg.what= " + bXO_.what + ", msg.ag1=" + bXO_.arg1);
                    int m = lnsVar.m();
                    lnl lnlVar = new lnl();
                    lnlVar.b(2);
                    lnlVar.a(99);
                    lnlVar.d(c(m));
                    bXO_.obj = lnlVar;
                    lop.bYq_(bXO_);
                } else {
                    loq.c(d, "Message is null ");
                }
                return true;
            }
            hashMap.put(lnsVar.f(), 1);
        }
        return false;
    }
}
