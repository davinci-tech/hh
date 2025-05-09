package defpackage;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.AuthorizeSubUserInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.ctk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class csd {
    private static final Object b = new Object();
    private static volatile csd e;
    private final ExtendHandler d = HandlerCenter.yt_(new e(this), "Inquire_Subuser");

    private csd() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LB_(Message message) {
        cpw.a(false, "InquireAuthUserHandler", "sendPushMsg in");
        Bundle data = message.getData();
        if (data == null) {
            LogUtil.b("InquireAuthUserHandler", "sendPushMsg data is null.");
            return;
        }
        String string = data.getString("deviceid");
        String string2 = data.getString("account");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            cpw.d(false, "InquireAuthUserHandler", "sendPushMsg device is null or account is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("pushType", "auth_success");
        bundle.putString("pushContent", string2);
        bundle.putString("deviceId", string);
        EventBus.d(new EventBus.b("multi_user_auto_cancle_dialog", bundle));
    }

    public static csd e() {
        if (e == null) {
            synchronized (b) {
                if (e == null) {
                    e = new csd();
                }
            }
        }
        return e;
    }

    static class e extends BaseHandlerCallback<csd> {
        public e(csd csdVar) {
            super(csdVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: LC_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(csd csdVar, Message message) {
            LogUtil.a("InquireAuthUserHandler", "msg is ", message);
            switch (message.what) {
                case 1000:
                    csdVar.d();
                    return true;
                case 1001:
                    if (message.obj != null && (message.obj instanceof String)) {
                        csdVar.d((String) message.obj, message.arg1);
                        return true;
                    }
                    cpw.d(false, "InquireAuthUserHandler", " handleMessage deviceId is null");
                    return true;
                case 1002:
                default:
                    cpw.a(false, "InquireAuthUserHandler", "unknown Message");
                    return false;
                case 1003:
                    csdVar.LB_(message);
                    return true;
                case 1004:
                    csdVar.a();
                    return true;
            }
        }
    }

    private boolean c(ctk ctkVar) {
        ctk.a b2 = ctkVar.b();
        if (b2 != null) {
            return b2.k() == 2;
        }
        LogUtil.h("InquireAuthUserHandler", "isAuthorizedDevice deviceInfo is null:", ctkVar.getProductId());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ArrayList<ctk> e2 = ctq.e();
        if (koq.b(e2)) {
            LogUtil.h("InquireAuthUserHandler", "updateAllDeviceSubUser allDevices is null");
            return;
        }
        for (ctk ctkVar : e2) {
            if (!c(ctkVar)) {
                csf.e(ctkVar.d(), new CommBaseCallbackInterface() { // from class: csd.3
                    @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface
                    public void onResult(int i, String str, Object obj) {
                        LogUtil.a("InquireAuthUserHandler", "updateAllDeviceSubUser errorCode:", Integer.valueOf(i));
                    }
                });
            }
        }
    }

    public void c(String str) {
        cpw.a(false, "InquireAuthUserHandler", "start msg ", str);
        this.d.removeTasksAndMessages();
        this.d.sendEmptyMessage(1000);
    }

    public void c() {
        LogUtil.a("InquireAuthUserHandler", "updataDeviceSubUser in");
        this.d.sendEmptyMessage(1004);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ArrayList<String> d = ctq.d();
        if (d != null && d.size() > 0) {
            Iterator<String> it = d.iterator();
            while (it.hasNext()) {
                c(it.next(), 0);
            }
            return;
        }
        cpw.e(false, "getAllDeviceSubUser local has not device", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, final int i) {
        WifiDeviceGetAuthorizeSubUserReq wifiDeviceGetAuthorizeSubUserReq = new WifiDeviceGetAuthorizeSubUserReq();
        wifiDeviceGetAuthorizeSubUserReq.setDevId(str);
        jbs.a(cpp.a()).a(wifiDeviceGetAuthorizeSubUserReq, new ICloudOperationResult<WifiDeviceGetAuthorizeSubUserRsp>() { // from class: csd.1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceGetAuthorizeSubUserRsp wifiDeviceGetAuthorizeSubUserRsp, String str2, boolean z) {
                int i2;
                String str3;
                if (!z) {
                    if (wifiDeviceGetAuthorizeSubUserRsp != null) {
                        i2 = wifiDeviceGetAuthorizeSubUserRsp.getResultCode().intValue();
                        str3 = wifiDeviceGetAuthorizeSubUserRsp.getResultDesc();
                    } else {
                        i2 = Constants.CODE_UNKNOWN_ERROR;
                        str3 = "unknown error";
                    }
                    csd.this.c(str, i);
                    cpw.d(false, "InquireAuthUserHandler", "getUserAllDevice() errCode = ", Integer.valueOf(i2), ",resultDesc:", str3);
                    return;
                }
                if (wifiDeviceGetAuthorizeSubUserRsp == null) {
                    cpw.a(false, "InquireAuthUserHandler", "getUserAllDevice rsp is null");
                    return;
                }
                cpw.a(false, "InquireAuthUserHandler", "getUserAllDevice reg subUser success :", wifiDeviceGetAuthorizeSubUserRsp.toString());
                List<AuthorizeSubUserInfo> authorizeSubUserList = wifiDeviceGetAuthorizeSubUserRsp.getAuthorizeSubUserList();
                Object[] objArr = new Object[2];
                objArr[0] = "getUserAllDevice reg subUser :";
                objArr[1] = authorizeSubUserList == null ? Constants.NULL : Integer.valueOf(authorizeSubUserList.size());
                cpw.a(false, "InquireAuthUserHandler", objArr);
                if (csd.this.a(authorizeSubUserList, str)) {
                    return;
                }
                csd.this.c(str, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(List<AuthorizeSubUserInfo> list, String str) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        return e(str, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i) {
        cpw.a(false, "InquireAuthUserHandler", "sendQuerySubUserMsg queryNum:", Integer.valueOf(i));
        if (i <= 3) {
            Message obtain = Message.obtain();
            obtain.obj = str;
            obtain.arg1 = i + 1;
            obtain.what = 1001;
            this.d.sendMessage(obtain);
            return;
        }
        cpw.e(false, "InquireAuthUserHandler", "sendQuerySubUserMsg is finish ");
    }

    private boolean e(String str, List<AuthorizeSubUserInfo> list) {
        crw a2 = ctq.a(str);
        boolean z = false;
        if (list == null) {
            cpw.a(false, "InquireAuthUserHandler", "compareSubUser subUserInfos is null");
            return false;
        }
        if (a2 != null && koq.c(a2.b())) {
            ArrayList<crx> b2 = a2.b();
            for (AuthorizeSubUserInfo authorizeSubUserInfo : list) {
                if (e(str, authorizeSubUserInfo, b2, authorizeSubUserInfo.getSubHuid())) {
                    z = true;
                }
            }
        } else {
            for (AuthorizeSubUserInfo authorizeSubUserInfo2 : list) {
                if (!TextUtils.isEmpty(authorizeSubUserInfo2.getSubHuid())) {
                    c(str, authorizeSubUserInfo2);
                    z = true;
                }
            }
        }
        return z;
    }

    private boolean e(String str, AuthorizeSubUserInfo authorizeSubUserInfo, ArrayList<crx> arrayList, String str2) {
        if (TextUtils.isEmpty(str2) || a(arrayList, str2)) {
            return false;
        }
        c(str, authorizeSubUserInfo);
        return true;
    }

    private void c(String str, AuthorizeSubUserInfo authorizeSubUserInfo) {
        crx crxVar = new crx();
        crxVar.e(authorizeSubUserInfo.getSubHuid());
        crxVar.b(authorizeSubUserInfo.getUserAccount());
        crxVar.d(authorizeSubUserInfo.getNickName());
        ctq.a(str, crxVar);
        Bundle bundle = new Bundle();
        bundle.putString("deviceid", str);
        bundle.putString("account", crxVar.c());
        Message obtain = Message.obtain();
        obtain.setData(bundle);
        obtain.what = 1003;
        this.d.sendMessage(obtain);
    }

    private boolean a(ArrayList<crx> arrayList, String str) {
        Iterator<crx> it = arrayList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().e())) {
                return true;
            }
        }
        return false;
    }
}
