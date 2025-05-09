package defpackage;

import android.app.Activity;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.pluginsocialshare.activity.DownloadInterface;
import com.huawei.pluginsocialshare.cloud.bean.DownloadCallback;
import com.huawei.pluginsocialshare.cloud.bean.ShareDataCallBack;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mva {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<DownloadInterface> f15190a;
    private mug b = null;
    private CustomProgressDialog.Builder c;
    private WeakReference<Activity> d;
    private CustomProgressDialog e;
    private e f;
    private int g;
    private boolean i;
    private fea j;

    public mva(DownloadInterface downloadInterface) {
        this.f15190a = new WeakReference<>(downloadInterface);
    }

    public mva() {
    }

    public void cqf_(boolean z, Activity activity, int i) {
        cqg_(z, activity, i, false);
    }

    public void cqg_(boolean z, Activity activity, int i, boolean z2) {
        LogUtil.a("Share_ShareSourceDownloadManager", "updateShareData add healthData");
        this.d = new WeakReference<>(activity);
        this.g = i;
        this.i = z;
        this.j = new fea();
        this.f = new e(this);
        ShareDataCallBack shareDataCallBack = new ShareDataCallBack() { // from class: mva.1
            @Override // com.huawei.pluginsocialshare.cloud.bean.ShareDataCallBack
            public void onFailure(int i2, String str) {
                LogUtil.b("Share_ShareSourceDownloadManager", "updateShareData onFailure errorCode: ", Integer.valueOf(i2), " errorInfo:", str);
                if (mva.this.i) {
                    mva.this.f.sendEmptyMessage(105);
                }
            }

            @Override // com.huawei.pluginsocialshare.cloud.bean.ShareDataCallBack
            public void onSuccess(int i2, fea feaVar) {
                LogUtil.a("Share_ShareSourceDownloadManager", "onSuccess resultCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    mva.this.j = feaVar;
                    final List<ShareDataInfo> c = mva.this.e(feaVar).c();
                    if (!mva.this.j.c().isEmpty() && c.isEmpty()) {
                        mva.this.f.sendEmptyMessage(102);
                    }
                    if (c.isEmpty()) {
                        return;
                    }
                    final Activity activity2 = (Activity) mva.this.d.get();
                    if (activity2 == null || !CommonUtil.ah(activity2) || !mva.this.i) {
                        mva.this.e(c);
                    } else {
                        activity2.runOnUiThread(new Runnable() { // from class: mva.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                mva.this.cqa_(mva.this.c((List<ShareDataInfo>) c), c, activity2);
                            }
                        });
                    }
                }
            }
        };
        if (z2) {
            mvm.c().c(this.g, shareDataCallBack);
        } else {
            mvm.c().d(this.g, shareDataCallBack);
        }
    }

    public void cqh_(boolean z, Activity activity, int i) {
        this.d = new WeakReference<>(activity);
        this.g = i;
        this.i = z;
        this.j = new fea();
        this.f = new e(this);
        if (!CommonUtil.aa(BaseApplication.e())) {
            this.f.sendEmptyMessage(109);
        } else {
            final ShareDataCallBack shareDataCallBack = new ShareDataCallBack() { // from class: mva.3
                @Override // com.huawei.pluginsocialshare.cloud.bean.ShareDataCallBack
                public void onFailure(int i2, String str) {
                    LogUtil.b("Share_ShareSourceDownloadManager", "updateShareData onFailure errorCode: ", Integer.valueOf(i2), " errorInfo:", str);
                    if (mva.this.i) {
                        mva.this.f.sendEmptyMessage(108);
                    }
                }

                @Override // com.huawei.pluginsocialshare.cloud.bean.ShareDataCallBack
                public void onSuccess(int i2, fea feaVar) {
                    LogUtil.a("Share_ShareSourceDownloadManager", "onSuccess resultCode:", Integer.valueOf(i2));
                    if (i2 == 0) {
                        mva.this.j = feaVar;
                        mva.this.f.sendEmptyMessage(103);
                    }
                }
            };
            ThreadPoolManager.d().execute(new Runnable() { // from class: mva.4
                @Override // java.lang.Runnable
                public void run() {
                    mvm.c().a(mva.this.g, shareDataCallBack);
                }
            });
        }
    }

    public void cqd_(List<ShareDataInfo> list, boolean z, Activity activity, DownloadCallback downloadCallback) {
        this.i = z;
        this.d = new WeakReference<>(activity);
        this.f = new e(this);
        if (list == null || list.size() == 0) {
            return;
        }
        a(list, downloadCallback);
    }

    private void a(List<ShareDataInfo> list, DownloadCallback downloadCallback) {
        ThreadPoolManager.d().execute(new mug(list, downloadCallback, this.g));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(List<ShareDataInfo> list) {
        int i = 0;
        for (ShareDataInfo shareDataInfo : list) {
            if (shareDataInfo != null) {
                i += shareDataInfo.getImageSize();
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<ShareDataInfo> list) {
        this.b = new mug(list, this.i ? new d(list.size()) : null, this.g);
        this.f.sendEmptyMessage(100);
        ThreadPoolManager.d().execute(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cqb_(Activity activity) {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.b("Share_ShareSourceDownloadManager", "startDownloadProgress progress exist");
            return;
        }
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(activity);
        this.c = builder;
        builder.d(activity.getString(R.string._2130843900_res_0x7f0218fc)).cyH_(new View.OnClickListener() { // from class: mvh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mva.this.cqe_(view);
            }
        });
        CustomProgressDialog e2 = this.c.e();
        this.e = e2;
        e2.setCanceledOnTouchOutside(false);
        this.e.show();
        this.c.d(0);
        this.c.e(jed.b(0.0d, 2, 0));
        this.c.b();
        LogUtil.a("Share_ShareSourceDownloadManager", "mCustomProgressDialog.show()");
        this.f.sendMessageDelayed(this.f.obtainMessage(110), OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    /* synthetic */ void cqe_(View view) {
        LogUtil.a("Share_ShareSourceDownloadManager", "startDownLoadProgress onclick cancel");
        this.b.d(true);
        muj.d();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        CustomProgressDialog.Builder builder;
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || (builder = this.c) == null) {
            return;
        }
        builder.d(i);
        this.c.e(jed.b(i, 2, 0));
    }

    public void cqc_(Activity activity) {
        if (this.e != null && activity != null) {
            LogUtil.a("Share_ShareSourceDownloadManager", "closeDownloadProgress mCustomProgressDialog dismiss");
            this.e.dismiss();
            this.e = null;
        }
        CustomProgressDialog.Builder builder = this.c;
        if (builder != null) {
            builder.cyH_(null);
            this.c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public fea e(fea feaVar) {
        fea feaVar2 = new fea();
        List<ShareDataInfo> b = mvm.c().b(2, feaVar);
        List<ShareDataInfo> b2 = mvm.c().b(1, feaVar);
        List<ShareDataInfo> b3 = mvm.c().b(3, feaVar);
        List<ShareDataInfo> b4 = mvm.c().b(4, feaVar);
        feaVar2.a(b);
        feaVar2.f(b2);
        feaVar2.g(b3);
        feaVar2.j(b4);
        return feaVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cqa_(int i, List<ShareDataInfo> list, Activity activity) {
        String b = SharedPreferenceManager.b(activity, Integer.toString(20002), "lastTimeShowDialog");
        if (!TextUtils.isEmpty(b) && "-1".equals(b)) {
            e(list);
            return;
        }
        double d2 = i / 1048576.0d;
        if (d2 < 0.1d) {
            d2 = 0.1d;
        }
        cpZ_(String.format(Locale.ENGLISH, activity.getString(R.string._2130843018_res_0x7f02158a), activity.getString(R.string.IDS_device_upgrade_file_size_mb, new Object[]{UnitUtil.e(d2, 1, 1)})), list, activity);
    }

    private void cpZ_(String str, final List<ShareDataInfo> list, final Activity activity) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(activity).e(str).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: mva.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.e(activity, Integer.toString(20002), "lastTimeShowDialog", "-1", new StorageParams());
                mva.this.e((List<ShareDataInfo>) list);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: mva.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    class d implements DownloadCallback {

        /* renamed from: a, reason: collision with root package name */
        private int f15195a = 0;
        private int d;

        d(int i) {
            this.d = i;
        }

        @Override // com.huawei.pluginsocialshare.cloud.bean.DownloadCallback
        public void onSuccess(JSONObject jSONObject, ShareDataInfo shareDataInfo) {
            synchronized (this) {
                int i = this.f15195a + 1;
                this.f15195a = i;
                int i2 = (i * 100) / this.d;
                Message obtainMessage = mva.this.f.obtainMessage(101);
                obtainMessage.arg1 = i2;
                mva.this.f.sendMessage(obtainMessage);
                LogUtil.a("Share_ShareSourceDownloadManager", "mDownloadNum: ", Integer.valueOf(this.d), " finish num: ", Integer.valueOf(this.f15195a), " progress:", Integer.valueOf(i2));
            }
        }

        @Override // com.huawei.pluginsocialshare.cloud.bean.DownloadCallback
        public void onFailure(int i, String str) {
            synchronized (this) {
                LogUtil.a("Share_ShareSourceDownloadManager", "ShareFileCallback onFailure errCode:", Integer.valueOf(i), "errorInfo:", str);
                mva.this.f.sendMessage(mva.this.f.obtainMessage(104));
            }
        }
    }

    static class e extends BaseHandler<mva> {
        e(mva mvaVar) {
            super(mvaVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cqj_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(mva mvaVar, Message message) {
            if (message != null && mvaVar.f15190a != null) {
                Activity activity = (Activity) mvaVar.d.get();
                DownloadInterface downloadInterface = (DownloadInterface) mvaVar.f15190a.get();
                int i = message.what;
                if (i == 100) {
                    if (!mvaVar.i || activity == null) {
                        return;
                    }
                    mvaVar.cqb_(activity);
                    return;
                }
                if (i == 109) {
                    LogUtil.a("Share_ShareSourceDownloadManager", "handleMessage MSG_NETWORK_FAIL");
                    if (activity != null) {
                        nrh.e(activity.getApplicationContext(), R.string._2130841392_res_0x7f020f30);
                    }
                    if (downloadInterface != null) {
                        downloadInterface.notifyBackgroudFail();
                        return;
                    }
                    return;
                }
                if (i != 110) {
                    switch (i) {
                        case 104:
                            if (mvaVar.i) {
                                mvaVar.cqc_(activity);
                            }
                            c(downloadInterface);
                            break;
                        case 105:
                            c(downloadInterface);
                            break;
                        case 106:
                            if (downloadInterface != null) {
                                downloadInterface.refreshShareLayoutNoRecommend();
                                break;
                            }
                            break;
                        case 107:
                            LogUtil.a("Share_ShareSourceDownloadManager", "handleMessage MSG_DOWNLOAD_THEME_FAIL");
                            if (activity != null) {
                                nrh.e(activity.getApplicationContext(), R.string._2130850096_res_0x7f023130);
                                break;
                            }
                            break;
                        default:
                            if (downloadInterface != null) {
                                cqi_(mvaVar, message, activity, downloadInterface);
                                break;
                            }
                            break;
                    }
                    return;
                }
                if (mvaVar.e == null || !mvaVar.e.isShowing()) {
                    return;
                }
                ReleaseLogUtil.d("Share_ShareSourceDownloadManager", "ProgressHandler MSG_DOWNLOAD_OVERTIME");
                mvaVar.cqc_(activity);
                downloadInterface.notifyDownloadDataFail();
                return;
            }
            LogUtil.h("Share_ShareSourceDownloadManager", "handleMessage in createInstanceOfHandler, no msg is received.");
        }

        private void cqi_(mva mvaVar, Message message, Activity activity, DownloadInterface downloadInterface) {
            LogUtil.a("Share_ShareSourceDownloadManager", "updateStatus to enter");
            int i = message.what;
            if (i != 108) {
                switch (i) {
                    case 101:
                        mvaVar.d(message.arg1);
                        if (message.arg1 == 100) {
                            mvaVar.cqc_(activity);
                            List<ShareDataInfo> e = mwd.e(mvaVar.j.a());
                            mwd.b(e);
                            downloadInterface.notifyShareDataChanged(mvaVar.j);
                            downloadInterface.refreshDataMark(e);
                            break;
                        }
                        break;
                    case 102:
                        downloadInterface.notifyShareDataChanged(mvaVar.j);
                        break;
                    case 103:
                        LogUtil.a("Share_ShareSourceDownloadManager", "updateStatus MSG_UPDATE_BACKGROUD :", mvaVar.j.d(), ", size: ", Integer.valueOf(mvaVar.j.d().size()));
                        downloadInterface.notifyBackgroudChanged(mvaVar.j);
                        break;
                    default:
                        LogUtil.h("Share_ShareSourceDownloadManager", "updateStatus is default");
                        break;
                }
            }
            LogUtil.a("Share_ShareSourceDownloadManager", "updateStatus MSG_UPDATE_FAIL");
            if (activity != null) {
                nrh.e(activity.getApplicationContext(), R.string._2130850096_res_0x7f023130);
            }
            downloadInterface.notifyBackgroudFail();
        }

        private void c(DownloadInterface downloadInterface) {
            if (downloadInterface != null) {
                downloadInterface.showDownloadError();
                downloadInterface.notifyDownloadDataFail();
            }
        }
    }

    public void e() {
        e eVar = this.f;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
    }
}
