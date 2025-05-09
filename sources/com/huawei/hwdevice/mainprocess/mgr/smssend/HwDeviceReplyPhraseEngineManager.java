package com.huawei.hwdevice.mainprocess.mgr.smssend;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwi;
import defpackage.cwl;
import defpackage.jpt;
import defpackage.spn;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class HwDeviceReplyPhraseEngineManager extends EngineLogicBaseManager {
    private static HwDeviceReplyPhraseEngineManager b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Callback f6316a;
    private List<String> d;
    private Handler e;

    public interface Callback {
        void onResponse(List<String> list);
    }

    private HwDeviceReplyPhraseEngineManager(Context context) {
        super(context);
        this.e = new Handler(Looper.getMainLooper());
        this.d = new ArrayList(16);
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "HwDeviceReplyPhraseEngineManager enter");
    }

    public static HwDeviceReplyPhraseEngineManager e() {
        HwDeviceReplyPhraseEngineManager hwDeviceReplyPhraseEngineManager;
        synchronized (c) {
            if (b == null) {
                b = new HwDeviceReplyPhraseEngineManager(BaseApplication.getContext());
            }
            hwDeviceReplyPhraseEngineManager = b;
        }
        return hwDeviceReplyPhraseEngineManager;
    }

    public void a() {
        super.onDestroy();
        g();
    }

    private static void g() {
        synchronized (c) {
            b = null;
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "onReceiveDeviceCommand errorCode:", Integer.valueOf(i));
        if (spnVar == null) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "onReceiveDeviceCommand message is null");
            return;
        }
        byte[] b2 = spnVar.b();
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "onReceiveDeviceCommand messageData:", cvx.d(b2));
        if (b2 == null || b2.length < 6) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "onReceiveDeviceCommand messageData is invalid");
            return;
        }
        String d = cvx.d(b2);
        int w = CommonUtil.w(d.substring(4, 12));
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "onReceiveDeviceCommand msgType:", Integer.valueOf(w));
        if (w == 7003) {
            d(c());
        }
        if (w == 7001) {
            b(d);
        }
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_ok));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_yes));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_thanks));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_no_thanks));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_great));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_please));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_see_you));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_later));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_busy));
        arrayList.add(BaseApplication.getContext().getResources().getString(R$string.IDS_sms_reply_text_no));
        return arrayList;
    }

    private void b(String str) {
        if (str.length() < 12) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "handleQueryMessage resultDataHex less 12");
        } else {
            e(str);
        }
    }

    private void e(String str) {
        cwe cweVar;
        try {
            cweVar = new cwl().a(str);
        } catch (cwg unused) {
            LogUtil.b("HwDeviceReplyPhraseEngineManager", "parseSmsQueryResult TlvException");
            cweVar = null;
        }
        if (cweVar == null) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "parseSmsQueryResult tlvFather is null");
            return;
        }
        List<cwe> a2 = cweVar.a();
        this.d.clear();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "parseSmsQueryResult tlvFatherList is null");
            Callback callback = this.f6316a;
            if (callback != null) {
                callback.onResponse(this.d);
            }
            d(c());
            return;
        }
        cwe cweVar2 = a2.get(0);
        if (cweVar2 == null) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "parseSmsQueryResult tlvAppList is null");
            return;
        }
        Iterator<cwe> it = cweVar2.a().iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int a3 = CommonUtil.a(cwdVar.e(), 16);
                if (a3 == 1) {
                    LogUtil.a("HwDeviceReplyPhraseEngineManager", "parseSmsQueryResult received 7001");
                } else if (a3 == 4) {
                    String e = cvx.e(cwdVar.c());
                    this.d.add(e);
                    LogUtil.a("HwDeviceReplyPhraseEngineManager", "parseSmsQueryResult smsContent: ", e);
                } else {
                    LogUtil.h("HwDeviceReplyPhraseEngineManager", "parseSmsQueryResult default");
                }
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = "mCallback is null:";
        objArr[1] = Boolean.valueOf(this.f6316a == null);
        LogUtil.a("HwDeviceReplyPhraseEngineManager", objArr);
        Callback callback2 = this.f6316a;
        if (callback2 != null) {
            callback2.onResponse(this.d);
        }
    }

    public void f() {
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "sendSmsQueryCommand enter");
        d(cvx.a(cvx.e(1) + cvx.e(4) + cvx.b(7001L)));
    }

    public void b(Callback callback) {
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "addCallback");
        this.f6316a = callback;
    }

    public void h() {
        if (d()) {
            LogUtil.a("HwDeviceReplyPhraseEngineManager", "sendCommandConnected send");
            d(cvx.a(cvx.e(1) + cvx.e(4) + cvx.b(7003L)));
        }
    }

    public void d(List<String> list) {
        String str;
        if (list == null) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "sendSmsSettingCommand smsList is null");
            return;
        }
        String str2 = cvx.e(1) + cvx.e(4) + cvx.b(7002L);
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String c2 = cvx.c(it.next());
            String str3 = cvx.e(4) + cvx.d(c2.length() / 2) + c2;
            stringBuffer.append((cvx.e(131) + cvx.d(str3.length() / 2)) + str3);
        }
        if (stringBuffer.length() == 0) {
            str = cvx.e(2) + cvx.e(0);
        } else {
            str = cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + cvx.d(stringBuffer.length() / 2);
        }
        String str4 = str2 + str + stringBuffer.toString();
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "sendSmsSettingCommand commandHex:", str4);
        d(cvx.a(str4));
    }

    public void d(byte[] bArr) {
        sendComand(new spn.b().c(bArr).e(), new SendCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager.2
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("HwDeviceReplyPhraseEngineManager", "sendCommand onSendResult: ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("HwDeviceReplyPhraseEngineManager", "sendCommand onFileTransferReport transferWay: ", str);
            }
        });
    }

    public boolean d() {
        DeviceInfo d = jpt.d("HwDeviceReplyPhraseEngineManager");
        if (d == null) {
            LogUtil.h("HwDeviceReplyPhraseEngineManager", "isSupportEditSms deviceInfo is null");
            return false;
        }
        return cwi.c(d, 82);
    }

    public void b() {
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "onChangeLanguage: ", CommonUtil.u());
        this.e.postDelayed(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager.4
            @Override // java.lang.Runnable
            public void run() {
                HwDeviceReplyPhraseEngineManager.this.f();
            }
        }, 2000L);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.SMS_EDITABLE_APP_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("HwDeviceReplyPhraseEngineManager", "onDeviceConnectionChange", connectState);
        if (ConnectState.CONNECTED.equals(connectState)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager.3
                @Override // java.lang.Runnable
                public void run() {
                    HwDeviceReplyPhraseEngineManager.this.h();
                }
            });
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.watch.home";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "603AC6A57E2023E00C9C93BB539CA653DF3003EBA4E92EA1904BA4AAA5D938F0";
    }
}
