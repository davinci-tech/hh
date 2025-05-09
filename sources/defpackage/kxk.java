package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.versionmgr.betaversionmgr.BetaVersionMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.activity.AppUpdateDialogActivity;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.hwversionmgr.utils.service.UpdateService;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class kxk {

    /* renamed from: a, reason: collision with root package name */
    private static volatile kxk f14683a;
    private static final Object b = new Object();
    private Context f;
    private int e = 0;
    private String g = null;
    private String c = null;
    private String d = null;
    private MessageCenterApi i = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);

    private kxk(Context context) {
        this.f = context;
    }

    public static kxk d() {
        if (f14683a == null) {
            synchronized (b) {
                if (f14683a == null) {
                    f14683a = new kxk(BaseApplication.getContext());
                }
            }
        }
        return f14683a;
    }

    public void a() {
        LogUtil.a("R_AppUpdateInteractor", "autoCheckAppNewVersionService");
        Context context = this.f;
        if (context != null) {
            HwVersionManager.c(context).c(true);
        }
    }

    public void g() {
        Context context = this.f;
        if (context != null) {
            HwVersionManager.c(context).c(false);
        }
    }

    public void b() {
        LogUtil.a("R_AppUpdateInteractor", "doDownloadAppFile ");
        Context context = this.f;
        if (context != null) {
            HwVersionManager.c(context).c();
        }
    }

    public void c() {
        LogUtil.a("R_AppUpdateInteractor", "cancelDownloadApp");
        Context context = this.f;
        if (context != null) {
            HwVersionManager.c(context).d();
        }
    }

    public void m() {
        LogUtil.a("R_AppUpdateInteractor", "installApk");
        Intent intent = new Intent(this.f, (Class<?>) UpdateService.class);
        intent.setAction("action_app_install_new_version");
        this.f.startService(intent);
    }

    public boolean a(long j) {
        LogUtil.a("R_AppUpdateInteractor", "checkMemory needSize = ", Long.valueOf(j));
        try {
            StatFs statFs = new StatFs(this.f.getFilesDir().getCanonicalPath());
            return ((double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()))) * 0.9d > ((double) j);
        } catch (IOException unused) {
            LogUtil.b("R_AppUpdateInteractor", "checkMemory:getCanonicalPath IOException");
            return false;
        }
    }

    public boolean n() {
        NetworkInfo activeNetworkInfo;
        LogUtil.a("R_AppUpdateInteractor", "isWifiConnected");
        return (this.f.getSystemService("connectivity") instanceof ConnectivityManager) && (activeNetworkInfo = ((ConnectivityManager) this.f.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    public String a(String str) {
        Context context = this.f;
        return context != null ? HwVersionManager.c(context).t(str) : "";
    }

    public void c(Context context) {
        a(context, 3);
    }

    public void e(Context context, Boolean bool) {
        if (bool.booleanValue()) {
            a(context, 1);
        }
    }

    public int j() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public String i() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String h() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String f() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    private void a(Context context, int i) {
        if (!k()) {
            LogUtil.h("R_AppUpdateInteractor", "update message inserted");
            return;
        }
        MessageObject messageObject = new MessageObject();
        messageObject.setModule(String.valueOf(19));
        messageObject.setType("device_app_update");
        messageObject.setMsgType(2);
        messageObject.setPosition(i);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(SharedPreferenceUtil.getInstance(context).getUserID());
        messageObject.setMsgContent("");
        LogUtil.a("R_AppUpdateInteractor", "app update contentStr = ", "");
        String string = context.getString(R.string.IDS_messagecenter_device_app_new_version_title);
        messageObject.setMsgTitle(string);
        messageObject.setMetadata(string);
        messageObject.setExpireTime(0L);
        messageObject.setReadFlag(0);
        LogUtil.a("R_AppUpdateInteractor", "app update mstTitle = ", string);
        messageObject.setCreateTime(System.currentTimeMillis());
        if (l()) {
            messageObject.setDetailUri("messagecenter://device_app_update");
        } else {
            messageObject.setDetailUri("messagecenter://device_app_update_health");
        }
        messageObject.setImgUri("assets://localMessageIcon/ic_update.webp");
        messageObject.setMsgId(this.i.getMessageId(String.valueOf(19), "device_app_update"));
        LogUtil.a("R_AppUpdateInteractor", "makingMessage insertMessage isSuccessful:", Boolean.valueOf(this.i.insertMessage(messageObject)));
    }

    private boolean k() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.a("R_AppUpdateInteractor", "canInsertMessage isBrowseMode");
            return false;
        }
        String i = i();
        if (Arrays.asList(kxx.b().c().split(",")).contains(i)) {
            return false;
        }
        kxx.b().a(i);
        return true;
    }

    public void e() {
        LogUtil.a("R_AppUpdateInteractor", "enter deleteMessage");
        ThreadPoolManager.d().execute(new Runnable() { // from class: kxk.5
            @Override // java.lang.Runnable
            public void run() {
                List<MessageObject> messages = kxk.this.i.getMessages(String.valueOf(19), "device_app_update");
                LogUtil.a("R_AppUpdateInteractor", "makeMessage, delete messageList, messageList.size() = ", Integer.valueOf(messages.size()));
                for (MessageObject messageObject : messages) {
                    if (messageObject != null) {
                        try {
                            if (!TextUtils.isEmpty(messageObject.getMsgId())) {
                                String msgId = messageObject.getMsgId();
                                LogUtil.a("R_AppUpdateInteractor", "makingMessage deleteMessage isSuccessful:", Boolean.valueOf(kxk.this.i.deleteMessage(msgId)));
                                int parseInt = Integer.parseInt(msgId.substring(1));
                                jdh.c().a(parseInt);
                                LogUtil.a("R_AppUpdateInteractor", "message id:", Integer.valueOf(parseInt));
                            }
                        } catch (NumberFormatException unused) {
                            LogUtil.b("R_AppUpdateInteractor", "deleteMessage NumberFormatException");
                        }
                    }
                }
            }
        });
    }

    public void bSt_(Context context, Intent intent) {
        if (context == null) {
            LogUtil.h("R_AppUpdateInteractor", "showNewVersionDialog context is null");
            return;
        }
        if (intent == null) {
            LogUtil.h("R_AppUpdateInteractor", "showNewVersionDialog intent is null");
            return;
        }
        if (CommonUtil.as()) {
            bSs_(context, intent);
            return;
        }
        if (intent.hasExtra("name")) {
            b(intent.getStringExtra("name"));
            if (!com.huawei.haf.application.BaseApplication.j()) {
                LogUtil.h("R_AppUpdateInteractor", "showAutoCheckNewVersionDialog CommonUtil.isRunningForeground is false");
                return;
            }
        }
        if (CommonUtil.bh()) {
            g();
        } else {
            intent.setClass(context, AppUpdateDialogActivity.class);
            context.startActivity(intent);
        }
    }

    private void bSs_(Context context, Intent intent) {
        if (!com.huawei.haf.application.BaseApplication.j() && !intent.getBooleanExtra("isManual", false)) {
            LogUtil.h("R_AppUpdateInteractor", "showAutoCheckNewVersionDialog CommonUtil.isRunningForeground is false");
        } else if (intent.getBooleanExtra("hasNewBetaVersion", false)) {
            ((BetaVersionMgrApi) Services.c("HWBetaVersionMgr", BetaVersionMgrApi.class)).showNewBetaVersionDialog(context, intent.getBooleanExtra("isManual", false));
        } else {
            LogUtil.a("R_AppUpdateInteractor", "not have new beta version dialog to show");
        }
    }

    private boolean l() {
        if (!"com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            LogUtil.a("R_AppUpdateInteractor", "Package name is ", BaseApplication.getAppPackage());
            return false;
        }
        LogUtil.a("R_AppUpdateInteractor", "Package name is com.huawei.bone.");
        return true;
    }

    public void c(Boolean bool) {
        Context context = this.f;
        if (context != null) {
            HwVersionManager.c(context).d(true, bool.booleanValue());
        }
    }
}
