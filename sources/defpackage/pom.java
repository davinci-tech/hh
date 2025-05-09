package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.syncmgr.SyncOption;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData;
import com.huawei.ui.main.stories.recommendcloud.data.SleepRecommendData;
import com.huawei.ui.main.stories.recommendcloud.util.RecommendControl;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pom {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f16208a = false;
    private static Context c = null;
    private static boolean d = false;
    private static pom e;
    private IBaseResponseCallback b;
    private Date f;
    private pxd g;
    private RecommendControl l;
    private String m;
    private sdk q;
    private boolean i = false;
    private int o = -1;
    private boolean n = false;
    private int[] k = {SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_CORE_SLEEP_RECOMMEND, SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_RECOMMEND};
    private boolean j = false;
    private final Handler r = new Handler() { // from class: pom.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            pom.this.h();
        }
    };
    private final BroadcastReceiver h = new BroadcastReceiver() { // from class: pom.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null || intent.getIntExtra("refresh_type", -1) != 0) {
                return;
            }
            LogUtil.c("TimeEat_SleepRefreshDataInteator", "BroadcastReceiver: requestSuggestData ");
            pom.this.a();
        }
    };

    private pom(Context context) {
        CommonUtil.u("TimeEat_SleepRefreshDataInteatorEnter SleepRefreshDataInteator");
        c = context;
        i();
        this.q = sdk.c();
        this.g = new pxd();
        this.f = jec.e();
        m();
        RecommendControl newInstance = RecommendControl.newInstance(c);
        this.l = newInstance;
        newInstance.initCoreSleepTagConfig();
        CommonUtil.u("TimeEat_SleepRefreshDataInteatorLeave SleepRefreshDataInteator");
    }

    private void i() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pos
                @Override // java.lang.Runnable
                public final void run() {
                    pom.this.c();
                }
            });
        }
    }

    /* synthetic */ void c() {
        String url = GRSManager.a(c).getUrl("messageCenterUrl");
        this.m = url;
        LogUtil.c("TimeEat_SleepRefreshDataInteator", "initHostFromGrs mMessageCenterHost = ", url);
    }

    public static pom d() {
        pom pomVar;
        synchronized (pom.class) {
            if (e == null) {
                e = new pom(BaseApplication.getContext());
            }
            pomVar = e;
        }
        return pomVar;
    }

    private static boolean k() {
        return d;
    }

    private static void b(boolean z) {
        d = z;
    }

    private static boolean j() {
        return f16208a;
    }

    private static void c(boolean z) {
        f16208a = z;
    }

    public void e() {
        Date date = this.f;
        if (date != null) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "START:requestDatas(), mCurrentDay = ", jec.x(date));
            long n = jec.n(this.f);
            pxd pxdVar = this.g;
            if (pxdVar != null) {
                pxdVar.d(n, 1, new CommonUiBaseResponse() { // from class: por
                    @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                    public final void onResponse(int i, Object obj) {
                        pom.this.c(i, obj);
                    }
                });
                return;
            } else {
                LogUtil.b("R_Sleep_TimeEat_SleepRefreshDataInteator", "mInteractor = null");
                return;
            }
        }
        LogUtil.b("R_Sleep_TimeEat_SleepRefreshDataInteator", "mCurrentDay = null");
    }

    /* synthetic */ void c(int i, Object obj) {
        int i2 = this.g.i();
        int l = this.g.l();
        int e2 = this.g.e();
        double o = this.g.o();
        boolean z = i2 > 0 && l > 0;
        boolean z2 = e2 == 0 && o == 1.0d;
        if (z && z2) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "fallTime: ", Integer.valueOf(i2), ", wakeupTime: ", Integer.valueOf(l));
        } else {
            a(this.f);
        }
    }

    private void a(Date date) {
        pxd pxdVar = this.g;
        if (pxdVar == null) {
            return;
        }
        pxdVar.e(date, 7, new CommonUiBaseResponse() { // from class: pot
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i, Object obj) {
                pom.this.a(i, obj);
            }
        });
    }

    /* synthetic */ void a(int i, Object obj) {
        LogUtil.a("R_Sleep_TimeEat_SleepRefreshDataInteator", "requestSuggestData errCode = ", Integer.valueOf(i));
        if (i == 0 && obj != null && (obj instanceof CoreSleepTotalData)) {
            LogUtil.c("TimeEat_SleepRefreshDataInteator", "onResponse success objData = ", obj.toString());
            SleepRecommendData a2 = a(((CoreSleepTotalData) obj).getAdNum0() + "");
            if (a2 != null) {
                LogUtil.a("TimeEat_SleepRefreshDataInteator", "Category: ", a2.getCategory());
                if ("1".equals(a2.getCategory())) {
                    String title = a2.getTitle();
                    String url = a2.getUrl();
                    String description = a2.getDescription();
                    LogUtil.a("TimeEat_SleepRefreshDataInteator", "jumpWebTitle: ", title, ", jumpWebUrl: ", url);
                    kvs.b(c).d(SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_RECOMMEND);
                    if (TextUtils.isEmpty(this.l.getSmartCardCoreSleepTime())) {
                        return;
                    }
                    LogUtil.a("TimeEat_SleepRefreshDataInteator", "getSmartCardCoreSleepTime: ", this.l.getSmartCardCoreSleepTime());
                    d(new String[]{description, title, url, this.l.getSmartCardCoreSleepTime()}, SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_CORE_SLEEP_RECOMMEND, 2);
                    return;
                }
                LogUtil.a("R_Sleep_TimeEat_SleepRefreshDataInteator", "requestSuggestData error code", Integer.valueOf(i), ", not music service");
                return;
            }
            LogUtil.a("R_Sleep_TimeEat_SleepRefreshDataInteator", "requestSuggestData error code", Integer.valueOf(i), ", no suggest");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.q.j()) {
            e();
            if (TextUtils.isEmpty(this.m)) {
                LogUtil.b("R_Sleep_TimeEat_SleepRefreshDataInteator", "createSmartCard mMessageCenterHost is empty");
                return;
            }
            c(new String[]{c.getString(R$string.IDS_fitness_core_sleep_sleep_questionnaire), "", this.m + "/messageH5/sleephtml/QuestionnaireFrontpage.html", SmartMsgConstant.DEFAULT_SHOW_TIME}, SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_CORE_SLEEP_SLEEP_QUESTIONNAIRE, 5, System.currentTimeMillis(), System.currentTimeMillis());
        }
    }

    public void a() {
        if (Utils.g()) {
            return;
        }
        if (TextUtils.isEmpty(this.m)) {
            i();
            new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: pop
                @Override // java.lang.Runnable
                public final void run() {
                    pom.this.f();
                }
            });
        } else {
            f();
        }
    }

    private void d(String[] strArr, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        long t = jdl.t(currentTimeMillis);
        boolean z = currentTimeMillis - this.q.h() <= 86400000;
        boolean z2 = currentTimeMillis - jdl.e(currentTimeMillis, 20, 0) > 0;
        if (!z) {
            this.n = false;
        }
        LogUtil.a("R_Sleep_TimeEat_SleepRefreshDataInteator", "!isToday: ", Boolean.valueOf(!z), ", isNeedSmartCard: ", Boolean.valueOf(z2), ", !mIsTodayShow: ", Boolean.valueOf(!this.n));
        if (z2) {
            if (i == 80001) {
                if (z || this.n) {
                    return;
                }
                LogUtil.a("TimeEat_SleepRefreshDataInteator", "create jingzhun Card and insert message");
                c(strArr, i, i2, currentTimeMillis, t);
                this.n = true;
                return;
            }
            if (e(currentTimeMillis)) {
                LogUtil.a("TimeEat_SleepRefreshDataInteator", "create normal card and insert message");
                c(strArr, i, i2, currentTimeMillis, t);
            } else {
                LogUtil.a("TimeEat_SleepRefreshDataInteator", "today created normal smart card");
            }
        }
    }

    private void c(String[] strArr, int i, int i2, long j, long j2) {
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(i);
        smartMsgDbObject.setMsgSrc(8);
        smartMsgDbObject.setMsgContentType(2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", strArr[1]);
            jSONObject.put(SmartMsgConstant.MSG_CONTENT_SLEEP_TITLE, strArr[0]);
            jSONObject.put(SmartMsgConstant.MSG_CONTENT_MESSAGE_CENTER_DETAIL_URL, strArr[2]);
            jSONObject.put("createTime", j);
        } catch (JSONException e2) {
            LogUtil.b("TimeEat_SleepRefreshDataInteator", "JSONException exception = ", e2.getMessage());
        }
        smartMsgDbObject.setMsgContent(jSONObject.toString());
        smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD);
        smartMsgDbObject.setShowTime(strArr[3]);
        smartMsgDbObject.setStatus(1);
        smartMsgDbObject.setMessagePriority(i2);
        g();
        kvs.b(c).a(smartMsgDbObject);
        if (i == 80004) {
            this.q.e(false);
        } else {
            this.q.a(j2);
            this.q.d(false);
        }
    }

    private boolean e(long j) {
        return this.q.i() || !(((j - this.q.h()) > 86400000L ? 1 : ((j - this.q.h()) == 86400000L ? 0 : -1)) <= 0);
    }

    private void g() {
        for (int i : this.k) {
            kvs.b(c).d(i);
        }
    }

    private SleepRecommendData a(String str) {
        SleepRecommendData sleepRecommendData = RecommendControl.newInstance(c).getSleepRecommendData(str);
        if (sleepRecommendData != null) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "sleepRecommendData :", sleepRecommendData);
            return sleepRecommendData;
        }
        LogUtil.h("TimeEat_SleepRefreshDataInteator", "sleepRecommendData is null ");
        return null;
    }

    static class e implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<pom> f16209a;

        e(pom pomVar) {
            this.f16209a = new WeakReference<>(pomVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            pom pomVar = this.f16209a.get();
            Object[] objArr = new Object[4];
            objArr[0] = "errcode is ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = "objData ";
            objArr[3] = obj != null ? obj.toString() : null;
            LogUtil.a("TimeEat_SleepRefreshDataInteator", objArr);
            if (pomVar != null) {
                pomVar.d(i, obj);
            } else {
                LogUtil.h("TimeEat_SleepRefreshDataInteator", " CoreSleepResponseCallback sleepCardData = null");
            }
        }
    }

    public boolean b() {
        return this.j;
    }

    public void c(boolean z, boolean z2) {
        DeviceInfo a2 = jpt.a("TimeEat_SleepRefreshDataInteator");
        if (a2 == null) {
            a2 = jpu.d("TimeEat_SleepRefreshDataInteator");
        }
        if (a2 == null) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "current is no device,don't do sync data.");
            nrq.a().d(100);
            this.j = false;
            b(1010);
            return;
        }
        if (a2.getDeviceConnectState() != 2) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "current device is disconnect,don't sync data from device.");
            nrq.a().d(100);
            this.j = false;
            b(1010);
            return;
        }
        DeviceInfo d2 = jpu.d("TimeEat_SleepRefreshDataInteator");
        if (d2 != null && d2.getDeviceConnectState() == 2) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "current device is aw70,don't sync data from device.");
            nrq.a().d(100);
            this.j = true;
            b(1010);
            return;
        }
        if (a2.getDeviceConnectState() == 2 && (cvs.d() == null || !cvs.d().isDeviceSupportCoreSleep())) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "current device is not support coreSleep.");
            nrq.a().d(100);
            this.j = true;
            b(1010);
            return;
        }
        this.j = true;
        c(z);
        b(z2);
        LogUtil.a("TimeEat_SleepRefreshDataInteator", "enter refreshDayData isCoreSleep:", Boolean.valueOf(z));
        q();
    }

    private void q() {
        nrq.a().d(0);
        if (this.b == null) {
            this.b = new e(this);
        }
        LogUtil.a("TimeEat_SleepRefreshDataInteator", "ui startSynCoreSleep");
        nhu.c().startSynCoreSleep(SyncOption.builder().e(true).c("isUiInterface", "uiInterface").c(), this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj) {
        if (i == 0) {
            r();
            return;
        }
        if (i == 10000) {
            l();
            return;
        }
        if (i == 20000) {
            e(obj);
            return;
        }
        if (i == 22000) {
            n();
            return;
        }
        if (i != 30002) {
            switch (i) {
                case 21000:
                    o();
                    break;
                case 21001:
                case 21002:
                case 21003:
                case 21004:
                case 21005:
                    c(i);
                    break;
                case 21006:
                    d(i);
                    break;
                default:
                    nrq.a().d(100);
                    LogUtil.a("TimeEat_SleepRefreshDataInteator", "core sleep sync cpc error = ", Integer.valueOf(i), ",mIsCoreSleep = ", Boolean.valueOf(j()));
                    b(1009);
                    break;
            }
            return;
        }
        p();
    }

    private void p() {
        this.r.removeMessages(1);
        if (this.b == null) {
            this.b = new e(this);
        }
        this.i = true;
        b(1008);
    }

    private void d(int i) {
        this.r.removeMessages(1);
        this.r.sendEmptyMessage(1);
        LogUtil.a("TimeEat_SleepRefreshDataInteator", "core sleep sync cpc error = ", Integer.valueOf(i));
        b(1007);
    }

    private void c(int i) {
        if (!k()) {
            this.r.removeMessages(1);
            this.r.sendEmptyMessage(1);
        }
        LogUtil.a("TimeEat_SleepRefreshDataInteator", "doCoreSleepSyncActionByError errorCode = ", Integer.valueOf(i));
        b(1006);
    }

    private void o() {
        if (jpt.a("TimeEat_SleepRefreshDataInteator") != null || k()) {
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "setSyncNotConnected mLastSyncCode = ", Integer.valueOf(this.o));
            if (this.o < 100) {
                this.o = -1;
                this.r.removeMessages(1);
                this.r.sendEmptyMessage(1);
            }
        } else {
            LogUtil.h("TimeEat_SleepRefreshDataInteator", "deviceInfo == null");
            nrq.a().d(100);
        }
        b(1005);
    }

    private void e(Object obj) {
        if (this.r.hasMessages(1)) {
            this.r.removeMessages(1);
        }
        int m = CommonUtil.m(c, obj == null ? "0" : obj.toString());
        this.o = m;
        LogUtil.a("TimeEat_SleepRefreshDataInteator", "setSleepProgress mLastSyncCode = ", Integer.valueOf(m));
        b(1004);
    }

    private void l() {
        if (!k()) {
            this.r.removeMessages(1);
            this.r.sendEmptyMessage(1);
        }
        b(1003);
    }

    private void n() {
        this.r.removeMessages(1);
        this.r.sendEmptyMessage(1);
        b(1002);
    }

    private void r() {
        LogUtil.a("TimeEat_SleepRefreshDataInteator", "setSyncSuccess");
        this.r.removeMessages(1);
        b(1001);
    }

    private void b(int i) {
        Intent intent = new Intent();
        intent.setAction("action_send_core_sleep_sync_rate");
        intent.putExtra("core_sleep_sync_status", i);
        BroadcastManagerUtil.bFI_(c, intent);
        if (i == 1008 || i == 1004) {
            return;
        }
        nrq.a().d(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("TimeEat_SleepRefreshDataInteator", "handleWhenSyncTimeOut() loadingAnimation.stop()");
        Intent intent = new Intent();
        intent.setAction("action_send_core_sleep_sync_rate");
        intent.putExtra("core_sleep_sync_rate_key", 100);
        BroadcastManagerUtil.bFI_(c, intent);
    }

    private void m() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(c);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(this.h, intentFilter);
            LogUtil.a("TimeEat_SleepRefreshDataInteator", "mHiBroadcasetReceiver registe success");
        } else {
            LogUtil.h("TimeEat_SleepRefreshDataInteator", "mHiBroadcasetReceiver registe fail");
        }
        LogUtil.c("TimeEat_SleepRefreshDataInteator", "mHiBroadcasetReceiver registe coresleep");
    }
}
