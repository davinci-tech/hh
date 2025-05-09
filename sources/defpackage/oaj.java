package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class oaj {
    private static oaj e;
    private Context g;
    private String h;
    private Handler i;
    private dcz j;
    private boolean n;
    private String p;
    private String r;
    private int s;
    private int c = 0;
    private String b = null;

    /* renamed from: a, reason: collision with root package name */
    private String f15581a = null;
    private String d = null;
    private int m = -1;
    private String f = null;
    private String q = "";
    private boolean l = false;
    private EventBus.ICallback k = new EventBus.ICallback() { // from class: oaj.2
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if ("set_scale_version_code".equals(bVar.e()) && TextUtils.isEmpty(oaj.this.h)) {
                oaj.this.cTH_(bVar.Km_());
            }
        }
    };
    private kxp o = kxp.c();

    private oaj(Context context) {
        this.s = 0;
        this.g = context;
        this.s = 0;
    }

    public boolean r() {
        return this.l;
    }

    public void b(Boolean bool) {
        this.l = bool.booleanValue();
    }

    public String n() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public int s() {
        return this.s;
    }

    public void e(int i) {
        this.s = i;
    }

    public String t() {
        return this.q;
    }

    public int k() {
        return this.m;
    }

    public String f() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String o() {
        return this.f15581a;
    }

    public void j(String str) {
        this.f15581a = str;
    }

    public String h() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public int j() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public void g(String str) {
        this.r = str;
        this.q = l();
    }

    public void cTE_(ContentValues contentValues) {
        String asString = contentValues.getAsString("uniqueId");
        boolean j = kxy.j(this.g, asString);
        LogUtil.a("WeightUpdateInteractors", "checkCurrentDeviceVersion isNewVersion:", Boolean.valueOf(j));
        if (!j) {
            LogUtil.h("WeightUpdateInteractors", " checkCurrentDeviceVersion have not NewVersion");
            return;
        }
        this.p = asString;
        this.r = contentValues.getAsString("productId");
        String k = cpa.k(this.p);
        if (TextUtils.isEmpty(k)) {
            k = cpa.k(this.r);
        }
        String str = k;
        if (TextUtils.isEmpty(str) || "0".equals(str)) {
            return;
        }
        if (this.o == null) {
            this.o = kxp.c();
        }
        HealthDevice a2 = cjx.e().a(this.r);
        boolean d = kxy.d(kxy.b(this.g, this.r));
        LogUtil.a("WeightUpdateInteractors", " isAlreadyCheck = ", Boolean.valueOf(d));
        if (a2 == null || d) {
            return;
        }
        if (cpa.ae(this.r)) {
            String l = cpa.l(this.p);
            if (TextUtils.isEmpty(l)) {
                l = cpa.n(this.r);
            }
            this.o.a(this.r, str, l, true, this.p);
            return;
        }
        this.o.a(this.r, str, a2.getAddress(), true, this.p);
    }

    public void d() {
        Iterator<ContentValues> it = cjx.e().d(HealthDevice.HealthDeviceKind.HDK_WEIGHT).iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("productId");
            if (!cpa.v(asString)) {
                LogUtil.h("WeightUpdateInteractors", "checkHagridNewDeviceVersion is not hagrid");
            } else {
                String asString2 = next.getAsString("uniqueId");
                boolean j = kxy.j(this.g, asString2);
                LogUtil.a("WeightUpdateInteractors", "checkHagridNewDeviceVersion isNewVersion:", Boolean.valueOf(j));
                if (j) {
                    LogUtil.h("WeightUpdateInteractors", "checkHagridNewDeviceVersion have new version");
                } else {
                    String b = kxy.b(this.g, asString2);
                    if (!TextUtils.isEmpty(b) && !o(b)) {
                        LogUtil.h("WeightUpdateInteractors", "checkHagridNewDeviceVersion less than one day");
                    } else {
                        String e2 = kxy.e(this.g, asString2);
                        if (TextUtils.isEmpty(e2) || "0".equals(e2)) {
                            return;
                        }
                        if (this.o == null) {
                            this.o = kxp.c();
                        }
                        String l = cpa.l(asString2);
                        if (TextUtils.isEmpty(l)) {
                            l = cpa.n(asString);
                        }
                        kxy.d(kxz.d(), this.g, asString2);
                        this.o.a(asString, e2, l, false, this.p);
                    }
                }
            }
        }
    }

    private boolean o(String str) {
        Date a2;
        LogUtil.a("WeightUpdateInteractors", "isAlreadyOneDay: lastTime", str);
        if (TextUtils.isEmpty(str) || (a2 = kxz.a(str)) == null) {
            return false;
        }
        long time = a2.getTime();
        LogUtil.a("WeightUpdateInteractors", "isAlreadyOneDay: System.currentTimeMillis() ", Long.valueOf(System.currentTimeMillis()), " last ", Long.valueOf(time));
        return Math.abs(System.currentTimeMillis() - time) > 86400000;
    }

    public static oaj a() {
        oaj oajVar;
        synchronized (oaj.class) {
            LogUtil.a("WeightUpdateInteractors", "getInstance, mInstance " + e);
            if (e == null) {
                LogUtil.a("WeightUpdateInteractors", "new WeightUpdateInteractors()");
                e = new oaj(BaseApplication.getContext());
            }
            oajVar = e;
        }
        return oajVar;
    }

    public void cTG_(Handler handler) {
        this.i = handler;
    }

    public void c(boolean z, String str) {
        if (this.o == null) {
            this.o = kxp.c();
        }
        if (this.s == 0) {
            this.s = 1;
        }
        this.h = "";
        this.n = z;
        this.p = str;
        LogUtil.c("WeightUpdateInteractors", "doManualCheckDeviceNewVersion,deviceSoftVersion is null");
        EventBus.d(this.k, 0, "set_scale_version_code");
    }

    public void cTH_(Intent intent) {
        if (intent != null) {
            if (cpa.ae(this.r)) {
                this.h = intent.getStringExtra("bleVersion");
                this.f = intent.getStringExtra(HealthEngineRequestManager.PARAMS_DEVICE_SN);
            } else {
                int intExtra = intent.getIntExtra("bleVersion", 65535);
                int intExtra2 = intent.getIntExtra("scaleVersion", 65535);
                this.h = b(intExtra2);
                LogUtil.c("WeightUpdateInteractors", "ble device version bleVersion = ", b(intExtra), ", scale = ", b(intExtra2));
            }
            v();
        }
    }

    public void v() {
        if (this.i != null) {
            Message obtain = Message.obtain();
            obtain.what = 3;
            obtain.obj = this.h;
            this.i.sendMessage(obtain);
        }
        if (cpa.x(this.r) && !this.n && this.i != null) {
            LogUtil.a("WeightUpdateInteractors", "is not main user, send MSG_STOP_PROGRESS.");
            this.i.sendEmptyMessage(6);
        } else {
            if (this.o == null) {
                this.o = kxp.c();
            }
            this.o.a(this.r, this.h, this.f, false, this.p);
        }
    }

    private String b(int i) {
        return (i / 4096) + "." + ((i / 256) % 16) + "." + (i % 256);
    }

    public void i() {
        LogUtil.a("WeightUpdateInteractors", "doDownloadAppFile ");
        if (this.g != null) {
            kxp.c().e(this.r);
        }
    }

    public void e() {
        LogUtil.a("WeightUpdateInteractors", "cancelDownloadApp");
        if (this.g != null) {
            kxp.c().a(this.r);
        }
    }

    public String g() {
        LogUtil.a("WeightUpdateInteractors", "enter getBandCheckNewVersion");
        return this.g != null ? kxp.c().c(this.r) : "";
    }

    public String m() {
        LogUtil.a("WeightUpdateInteractors", "enter getBandStorePath");
        return this.g != null ? kxp.c().g(this.r) : "";
    }

    public boolean b(long j) {
        LogUtil.a("WeightUpdateInteractors", "checkMemory needSize = " + j);
        try {
            StatFs statFs = new StatFs(this.g.getFilesDir().getCanonicalPath());
            return ((long) (((double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()))) * 0.9d)) > j;
        } catch (IOException e2) {
            LogUtil.b("WeightUpdateInteractors", "checkMemory " + e2.getMessage());
            return false;
        }
    }

    public boolean y() {
        LogUtil.a("WeightUpdateInteractors", "isWifiConnected");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.g.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    public boolean p() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.g.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public String h(String str) {
        Context context = this.g;
        return context != null ? HwVersionManager.c(context).t(str) : "";
    }

    public dcz b(String str) {
        dcz d = ResourceManager.e().d(str);
        this.j = d;
        return d;
    }

    public void cTF_(Bundle bundle) {
        LogUtil.a("WeightUpdateInteractors", "Enter initHagridNpsRequestDeviceInfo !");
        if (bundle == null) {
            return;
        }
        jcx.k(bundle.getString("deviceUniqueId"));
        String string = bundle.getString("deviceVersion");
        String string2 = bundle.getString("deviceModel");
        int i = bundle.getInt("productType");
        String string3 = bundle.getString("deviceMac");
        jcx.i(string);
        jcx.f(string2);
        String a2 = knl.a(string3);
        jcx.h(a2);
        LogUtil.a("WeightUpdateInteractors", "initWeightNpsRequestDeviceInfo deviceVersion: ", string, " deviceModel: ", string2, " productType: ", Integer.valueOf(i), " encoder: ", a2);
        jcx.j(string2);
        LogUtil.a("WeightUpdateInteractors", "initWeightNpsRequestDeviceInfo Name : ", string2);
        jdx.b(new Runnable() { // from class: oah
            @Override // java.lang.Runnable
            public final void run() {
                oaj.this.w();
            }
        });
    }

    /* synthetic */ void w() {
        jcx.d(GRSManager.a(this.g).getCommonCountryCode());
    }

    public boolean e(String str) {
        LogUtil.a("WeightUpdateInteractors", "isOtaFileExist(): path = " + str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("WeightUpdateInteractors", "isOtaFileExist() error, file path is empty...");
            return false;
        }
        boolean exists = new File(str).exists();
        LogUtil.a("WeightUpdateInteractors", "isOtaFileExist: isExist = " + exists);
        return exists;
    }

    public void a(Boolean bool) {
        if (bool.booleanValue()) {
            d(1);
        }
    }

    public void a(String str) {
        if (cjx.e().a(this.r) == null) {
            return;
        }
        this.p = str;
        d(3);
    }

    private void d(final int i) {
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.getMessages(String.valueOf(19), "device_scale_ota", new IBaseResponseCallback() { // from class: oaj.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                List list;
                if (i2 != 0 || obj == null) {
                    return;
                }
                try {
                    list = (List) obj;
                } catch (ClassCastException e2) {
                    LogUtil.a("WeightUpdateInteractors", "ClassCastException :" + e2.getMessage());
                    list = null;
                }
                if (oaj.this.e((List<MessageObject>) list)) {
                    LogUtil.a("WeightUpdateInteractors", "has message donot makeMessage.");
                    if (i == 3) {
                        oaj.this.x();
                        return;
                    }
                    return;
                }
                messageCenterApi.getMessageId(String.valueOf(19), "device_scale_ota", new IBaseResponseCallback() { // from class: oaj.1.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i3, Object obj2) {
                        LogUtil.a("WeightUpdateInteractors", "getEventAlarm() err_code = " + i3);
                        if (i3 == 0 && obj2 != null && (obj2 instanceof String)) {
                            oaj.this.b(messageCenterApi, i, obj2);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MessageCenterApi messageCenterApi, int i, Object obj) {
        String str = (String) obj;
        LogUtil.a("WeightUpdateInteractors", "generateConnectedMessage messageId = " + str);
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgId(str);
        messageObject.setModule(String.valueOf(19));
        messageObject.setType("device_scale_ota");
        messageObject.setMsgType(2);
        messageObject.setPosition(i);
        messageObject.setHuid(SharedPreferenceUtil.getInstance(this.g).getUserID());
        String string = this.g.getString(R.string.IDS_messagecenter_device_scale);
        messageObject.setMsgTitle(string);
        LogUtil.a("WeightUpdateInteractors", "generateConnectedMessage mstTitle = " + string);
        String format = String.format(Locale.ENGLISH, this.g.getString(R.string.IDS_messagecenter_device_need_upgrade_title), l());
        messageObject.setMsgContent(format);
        messageObject.setReadFlag(0);
        LogUtil.a("WeightUpdateInteractors", "generateConnectedMessage mstContent = " + format);
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setDetailUri("messagecenter://device_scale_ota?key=" + this.r + "&uniqueId=" + this.p);
        messageCenterApi.insertMessage(messageObject);
        if (i == 3) {
            x();
        }
        LogUtil.a("WeightUpdateInteractors", "generateConnectedMessage leave");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        MessageObject messageObject = new MessageObject();
        messageObject.setModule(String.valueOf(19));
        messageObject.setType("device_scale_ota");
        messageObject.setHuid(SharedPreferenceUtil.getInstance(this.g).getUserID());
        String string = this.g.getString(R.string.IDS_messagecenter_device_scale);
        messageObject.setMsgTitle(string);
        LogUtil.a("WeightUpdateInteractors", "generateConnectedMessage mstTitle = " + string);
        String format = String.format(Locale.ENGLISH, this.g.getString(R.string.IDS_messagecenter_device_need_upgrade_title), l());
        messageObject.setMsgContent(format);
        LogUtil.a("WeightUpdateInteractors", "generateConnectedMessage mstContent = " + format);
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setDetailUri("messagecenter://device_scale_ota?key=" + this.r + "&uniqueId=" + this.p);
        messageObject.setMsgId("D201809201048");
        messageObject.setWeight(1);
        messageObject.setPosition(3);
        LogUtil.a("WeightUpdateInteractors", "end_to_set_message");
        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).updateMessageNotification(this.g, messageObject, 20180920L);
        LogUtil.a("WeightUpdateInteractors", "end_makeMessage");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(List<MessageObject> list) {
        LogUtil.a("WeightUpdateInteractors", "isScaleMessageExists enter");
        if (list == null) {
            LogUtil.h("WeightUpdateInteractors", "isScaleMessageExists messageList is null");
            return false;
        }
        Iterator<MessageObject> it = list.iterator();
        while (it.hasNext()) {
            if ("device_scale_ota".equals(Uri.parse(it.next().getDetailUri()).getHost())) {
                LogUtil.a("WeightUpdateInteractors", "isScaleMessageExists message exists");
                return true;
            }
        }
        LogUtil.a("WeightUpdateInteractors", "isScaleMessageExists message not exists");
        return false;
    }

    public String l() {
        LogUtil.a("WeightUpdateInteractors", "productId " + this.r);
        if (TextUtils.isEmpty(this.r)) {
            return "";
        }
        if ("34fa0346-d46c-439d-9cb0-2f696618846b".equals(this.r)) {
            return this.g.getResources().getString(R.string.IDS_device_huawei_body_fat_scale_name);
        }
        if ("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(this.r)) {
            return this.g.getResources().getString(R.string.IDS_device_honor_smart_scale_name);
        }
        if ("33123f39-7fc1-420b-9882-a4b0d6c61100".equals(this.r)) {
            return this.g.getResources().getString(R.string.IDS_device_huawei_body_fat_scale_name);
        }
        if ("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(this.r)) {
            return this.g.getResources().getString(R.string.IDS_device_honor_scale_name);
        }
        if ("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(this.r)) {
            return this.g.getResources().getString(R.string.IDS_device_hygride_pro_name);
        }
        LogUtil.a("WeightUpdateInteractors", "get deviceName from ProductInfo");
        return cpa.m(this.r);
    }

    private void d(boolean z) {
        LogUtil.a("WeightUpdateInteractors", "enter deleteMessage");
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        List<MessageObject> messages = messageCenterApi.getMessages(String.valueOf(19), "device_scale_ota");
        LogUtil.a("WeightUpdateInteractors", "makeMessage, delete messageList, messageList.size() = " + messages.size());
        for (int i = 0; i < messages.size(); i++) {
            try {
                if (!z) {
                    messageCenterApi.deleteMessage(messages.get(i).getMsgId());
                }
                messageCenterApi.cancelNotification(20180920);
                LogUtil.a("WeightUpdateInteractors", "cancelNotification device ota");
            } catch (NumberFormatException e2) {
                LogUtil.a("WeightUpdateInteractors", "delete error" + e2.getMessage());
                return;
            }
        }
    }

    public void a(boolean z) {
        d(z);
        LogUtil.a("WeightUpdateInteractors", "enter deleteMessage ture");
    }

    public void b() {
        d(false);
        LogUtil.a("WeightUpdateInteractors", "enter deleteMessage false");
    }

    public void q() {
        LogUtil.a("WeightUpdateInteractors", "initUpdateInteractors");
        ThreadPoolManager.d().d("WeightUpdateInteractors", new Runnable() { // from class: oag
            @Override // java.lang.Runnable
            public final void run() {
                oaj.this.b();
            }
        });
        if (this.g != null) {
            kxp.c().c(this.r, this.p);
        }
        this.s = 0;
        this.c = 0;
        this.b = null;
        this.f15581a = null;
        this.d = "";
        this.m = -1;
        this.f = null;
        this.q = null;
        this.j = null;
        this.o = null;
    }

    public void c() {
        if (this.o == null) {
            this.o = kxp.c();
        }
        LogUtil.a("WeightUpdateInteractors", "enter deleteDfu");
        this.o.d(this.r);
    }

    public void u() {
        LogUtil.a("WeightUpdateInteractors", "enter release");
        this.o = null;
        this.h = null;
        EventBus.a(this.k, "set_scale_version_code");
    }

    public void e(Boolean bool) {
        if (this.g != null) {
            kxp.c().e(bool);
        }
    }

    public void i(String str) {
        this.f = str;
    }
}
