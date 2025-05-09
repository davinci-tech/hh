package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;
import com.huawei.multisimsdk.odsa.response.OdsaResponseParam;
import com.huawei.multisimsdk.odsa.view.BaseWebActivity;
import com.tencent.open.apireq.BaseResp;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes5.dex */
public class loy {
    private static final Object b = new Object();
    private static loy c;

    /* renamed from: a, reason: collision with root package name */
    private Handler f14820a;
    private AbsPrimaryDevice g;
    private Message h;
    private HandlerThread i;
    private AbsPairedDevice j;
    private AuthParam d = null;
    private Context e = null;
    private int l = -1;
    private ResponseHandler f = new ResponseHandler() { // from class: loy.1
        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(String str) {
            loq.c("ResponseHandler->", "onCallback" + str);
        }

        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(int i, String str) {
            OdsaResponseParam c2;
            String str2;
            int parseInt;
            loq.b("ResponseHandler->", "onCallback type=" + i + ", result=", str);
            if (lop.c(str) && (parseInt = Integer.parseInt(str)) != 302) {
                loy.this.b(parseInt);
                return;
            }
            int lastIndexOf = str.lastIndexOf("&");
            String substring = str.substring(lastIndexOf + 1);
            if (lastIndexOf >= 0) {
                str = str.substring(0, lastIndexOf);
            }
            try {
                if (substring.contains("json")) {
                    c2 = lpe.a(str);
                } else {
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    if (newPullParser != null) {
                        newPullParser.setInput(new ByteArrayInputStream(str.getBytes("UTF-8")), "UTF-8");
                    }
                    c2 = lpi.c(newPullParser);
                }
                if (c2 == null) {
                    str2 = "";
                } else {
                    str2 = c2.getToken();
                }
                if (!TextUtils.isEmpty(str2)) {
                    lpu.a(str2);
                }
                loy.this.b(i, c2);
            } catch (IOException e) {
                loy.this.b(BaseResp.CODE_UNSUPPORTED_BRANCH);
                loq.e("OdsaHandleController", "onCallback->e: ", e);
            } catch (IllegalStateException e2) {
                loy.this.b(-1010);
                loq.e("OdsaHandleController", "onCallback->e: ", e2);
            } catch (XmlPullParserException e3) {
                loy.this.b(BaseResp.CODE_PERMISSION_NOT_GRANTED);
                loq.e("OdsaHandleController", "onCallback->e: ", e3);
            }
        }
    };

    private loy() {
        HandlerThread handlerThread = new HandlerThread("OdsaHandleController");
        this.i = handlerThread;
        handlerThread.start();
        this.f14820a = new b(this.i.getLooper());
    }

    public static loy e() {
        loy loyVar;
        synchronized (b) {
            if (c == null) {
                c = new loy();
            }
            loyVar = c;
        }
        return loyVar;
    }

    public void b(Context context, AuthParam authParam) {
        this.e = context;
        this.d = authParam;
        this.l = authParam.getSlotId();
    }

    public Context b() {
        return this.e;
    }

    public AuthParam c() {
        return this.d;
    }

    public int d() {
        return this.l;
    }

    public boolean bYG_(Context context, AuthParam authParam, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        if (authParam == null || context == null || message == null) {
            loq.b("OdsaHandleController", "authparam is null or context is null or callbackMessage is null");
            return false;
        }
        if (!lop.bYo_(message)) {
            return false;
        }
        this.h = message;
        this.g = absPrimaryDevice;
        this.j = absPairedDevice;
        this.d = authParam;
        if (!lop.d(0, absPrimaryDevice, absPairedDevice, (Map<String, String>) null)) {
            return false;
        }
        int authType = authParam.getAuthType();
        loq.c("OdsaHandleController", "startAuth->authType is " + authType);
        if (authType == 2 || authType == 5) {
            new lpa(context, authParam, this.f14820a.obtainMessage(2)).a(this.g);
            return true;
        }
        if (authType != 4) {
            return false;
        }
        new lpd(context, authParam, absPrimaryDevice, absPairedDevice, this.f14820a.obtainMessage(1)).startAuthLogin();
        return true;
    }

    public void bYC_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        if (message == null) {
            loq.b("OdsaHandleController", "addMultiSimRequest: callbackMessage is null");
            return;
        }
        this.h = message;
        if (!lop.d(1, absPrimaryDevice, absPairedDevice, (Map<String, String>) null)) {
            b(BaseResp.CODE_QQ_LOW_VERSION);
            return;
        }
        HashMap hashMap = new HashMap();
        if (lni.e("android.permission.READ_PRIVILEGED_PHONE_STATE", this.e)) {
            hashMap.put("terminal_iccid", lop.d());
        }
        hashMap.put("operation_type", String.valueOf(0));
        loi.d().b(absPrimaryDevice, absPairedDevice, hashMap, this.f, 1);
    }

    public void bYD_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        if (message == null) {
            loq.b("OdsaHandleController", "changeMultiSimRequest: callbackMessage is null");
            return;
        }
        this.h = message;
        if (!lop.d(1, absPrimaryDevice, absPairedDevice, (Map<String, String>) null)) {
            b(BaseResp.CODE_QQ_LOW_VERSION);
            return;
        }
        HashMap hashMap = new HashMap();
        if (lni.e("android.permission.READ_PRIVILEGED_PHONE_STATE", this.e)) {
            hashMap.put("terminal_iccid", lop.d());
        }
        hashMap.put("operation_type", String.valueOf(2));
        loi.d().b(absPrimaryDevice, absPairedDevice, hashMap, this.f, 1);
    }

    public void bYE_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        if (message == null) {
            return;
        }
        this.h = message;
        if (!lop.d(3, absPrimaryDevice, absPairedDevice, (Map<String, String>) null)) {
            b(BaseResp.CODE_QQ_LOW_VERSION);
        } else {
            loi.d().b(absPrimaryDevice, absPairedDevice, null, this.f, 3);
        }
    }

    public void bYF_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message, int i) {
        if (message == null) {
            return;
        }
        this.h = message;
        if (!lop.d(i, absPrimaryDevice, absPairedDevice, (Map<String, String>) null)) {
            b(BaseResp.CODE_QQ_LOW_VERSION);
        } else {
            loq.c("OdsaHandleController", "getMultiSimServiceStatus start");
            loi.d().b(absPrimaryDevice, absPairedDevice, null, this.f, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, OdsaResponseParam odsaResponseParam) {
        if (odsaResponseParam.getOperationResult() == 1) {
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        b(1000);
                    } else if (i == 3) {
                        c(odsaResponseParam);
                    } else if (i == 5) {
                        loq.c("OdsaHandleController", "handleAcquireConfigurationLooping");
                        e(odsaResponseParam);
                    }
                } else if (TextUtils.isEmpty(odsaResponseParam.getSubscriptionServiceUrl())) {
                    b(BaseResp.CODE_QQ_LOW_VERSION);
                } else {
                    b(0);
                    a(odsaResponseParam);
                }
            } else if (!lop.e("ODSA_CHECK_COMPANION_DEVICE_SERVICES", (Boolean) false).booleanValue()) {
                b(1000);
            } else if (odsaResponseParam.getCompanionDeviceServices().contains("SharedNumber") || (odsaResponseParam.getCompanionAppEligibility().equals("1") && !TextUtils.isEmpty(odsaResponseParam.getNotEnabledUrl()) && !TextUtils.isEmpty(odsaResponseParam.getNotEnabledUserData()))) {
                b(1000);
            } else {
                b(1055);
            }
            loq.e("OdsaHandleController", "operation success");
            return;
        }
        b(odsaResponseParam.getOperationResult());
        loq.e("OdsaHandleController", "operation failed");
    }

    private void c(OdsaResponseParam odsaResponseParam) {
        if ("1".equals(odsaResponseParam.getServiceStatus())) {
            String d = d(odsaResponseParam);
            if (!TextUtils.isEmpty(d)) {
                if (this.h != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ActivationCode", d);
                    this.h.setData(bundle);
                }
                b(1200);
                return;
            }
            b(-1011);
            return;
        }
        b(1000);
    }

    private String d(OdsaResponseParam odsaResponseParam) {
        if (!TextUtils.isEmpty(odsaResponseParam.getProfileActivationCode())) {
            return odsaResponseParam.getProfileActivationCode();
        }
        if (TextUtils.isEmpty(odsaResponseParam.getProfileSmdpAddress())) {
            return "";
        }
        return "LPA:1$" + odsaResponseParam.getProfileSmdpAddress() + SampleEvent.SEPARATOR;
    }

    private void e(OdsaResponseParam odsaResponseParam) {
        if (!"1".equals(odsaResponseParam.getServiceStatus())) {
            int b2 = b(odsaResponseParam);
            loq.c("OdsaHandleController", "pollingInterval = " + b2);
            this.f14820a.sendEmptyMessageDelayed(6, (long) b2);
            return;
        }
        c(odsaResponseParam);
    }

    private int b(OdsaResponseParam odsaResponseParam) {
        if (odsaResponseParam.getPollingInterval() != 0) {
            return odsaResponseParam.getPollingInterval();
        }
        return 0;
    }

    public void a() {
        HandlerThread handlerThread = this.i;
        if (handlerThread != null) {
            handlerThread.quit();
            this.i = null;
        }
        if (this.f14820a != null) {
            this.f14820a = null;
        }
        if (c != null) {
            a((loy) null);
        }
        this.e = null;
    }

    private static void a(loy loyVar) {
        c = loyVar;
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
            loq.e("OdsaHandleController", "looper " + looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.arg1;
            switch (message.what) {
                case 1:
                    loy.this.bYB_(message);
                    break;
                case 2:
                    loy.this.bYy_(message);
                    break;
                case 3:
                    loy.this.bYz_(message);
                    break;
                case 4:
                    loy.this.bYx_(message);
                    break;
                case 5:
                    loy.this.bYA_(message);
                    break;
                case 6:
                    loy loyVar = loy.this;
                    loyVar.bYF_(loyVar.g, loy.this.j, loy.this.h, 5);
                    loq.c("OdsaHandleController", "getMultiSimServiceStatus polling success");
                    break;
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bYB_(Message message) {
        int i = message.arg1;
        loq.c("OdsaHandleController", "handleOpenIdAuthResult, resultCode " + i);
        if (i == 1000) {
            lpu.a(lox.d(this.e).d().d());
            loi.d().b(this.g, this.j, null, this.f, 0);
        } else {
            b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bYy_(Message message) {
        int i = message.arg1;
        loq.c("OdsaHandleController", "handleAkaAuthResult, resultCode " + i);
        if (i == 1100) {
            loi.d().b(this.g, this.j, null, this.f, 0);
        } else if (this.d.getAuthType() == 5) {
            new lpd(this.e, this.d, this.g, this.j, this.f14820a.obtainMessage(1)).startAuthLogin();
        } else {
            b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bYz_(Message message) {
        loq.c("OdsaHandleController", "handleCheckEligibility, resultCode " + message.arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bYx_(Message message) {
        loq.c("OdsaHandleController", "handleAcquireConfiguration, resultCode " + message.arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bYA_(Message message) {
        loq.c("OdsaHandleController", "handleManageSubscription, resultCode " + message.arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (this.h != null) {
            loq.c("OdsaHandleController", "response result " + i + " to " + this.h.getTarget());
            this.h.arg1 = i;
            lop.bYq_(this.h);
            this.h = null;
        }
    }

    private void a(OdsaResponseParam odsaResponseParam) {
        Context context = this.e;
        if (!(context instanceof Activity)) {
            loq.b("OdsaHandleController", "startWebView fail context not activity.");
            return;
        }
        if (odsaResponseParam == null) {
            loq.b("OdsaHandleController", "startWebView fail odsaResponseParam is null.");
            return;
        }
        Activity activity = (Activity) context;
        Intent intent = new Intent(activity, (Class<?>) BaseWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("OdsaResponseParamBundle", odsaResponseParam);
        intent.putExtra("OdsaResponseParam", bundle);
        loq.c("OdsaHandleController", "startWebView " + intent);
        activity.startActivityForResult(intent, 101);
    }
}
