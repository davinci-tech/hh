package com.huawei.ui.device.activity.questionsuggestions;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.device.activity.questionsuggestions.WeightQuestionSuggestionActivity;
import defpackage.ceo;
import defpackage.cft;
import defpackage.cfv;
import defpackage.cld;
import defpackage.cpa;
import defpackage.ixj;
import defpackage.jrx;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes.dex */
public class WeightQuestionSuggestionActivity extends BaseQuestionSuggestionActivity {

    /* renamed from: a, reason: collision with root package name */
    private static int f9210a;
    private volatile long c = 0;
    private String e;
    private cld f;
    private String g;
    private static final String d = jrx.c + File.separator + "WSP-BLE-LOG-FILE";
    private static volatile boolean b = false;

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity, android.view.View.OnClickListener
    public /* synthetic */ void onClick(View view) {
        super.onClick(view);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* loaded from: classes8.dex */
    static final class c implements WspFileTransferCallback {

        /* renamed from: a, reason: collision with root package name */
        private final String f9211a;
        private final WeakReference<WeightQuestionSuggestionActivity> c;

        private c(WeightQuestionSuggestionActivity weightQuestionSuggestionActivity, String str) {
            this.c = new WeakReference<>(weightQuestionSuggestionActivity);
            this.f9211a = str;
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onStart(int i) {
            ReleaseLogUtil.e("R_Weight_QuestionSuggestionActivity", "getLogFileFromDevice onStart fileListSize = ", Integer.valueOf(i));
            int unused = WeightQuestionSuggestionActivity.f9210a = i;
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onSuccess(int i, int i2, String str) {
            ReleaseLogUtil.e("R_Weight_QuestionSuggestionActivity", "getLogFileFromDevice onSuccess, fileIndex = ", Integer.valueOf(i), ", successCode = ", Integer.valueOf(i2));
            if (i + 1 >= WeightQuestionSuggestionActivity.f9210a) {
                boolean unused = WeightQuestionSuggestionActivity.b = false;
                WeightQuestionSuggestionActivity weightQuestionSuggestionActivity = this.c.get();
                if (weightQuestionSuggestionActivity == null || weightQuestionSuggestionActivity.isFinishing() || weightQuestionSuggestionActivity.isDestroyed()) {
                    ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "activity is null");
                } else if (weightQuestionSuggestionActivity.mLogHandler != null) {
                    Message obtainMessage = weightQuestionSuggestionActivity.mLogHandler.obtainMessage();
                    obtainMessage.what = 3;
                    weightQuestionSuggestionActivity.mLogHandler.sendMessage(obtainMessage);
                }
            }
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "getLogFileFromDevice onFailure errorCode = ", Integer.valueOf(i));
            boolean unused = WeightQuestionSuggestionActivity.b = false;
            WeightQuestionSuggestionActivity weightQuestionSuggestionActivity = this.c.get();
            if (weightQuestionSuggestionActivity == null || weightQuestionSuggestionActivity.isFinishing() || weightQuestionSuggestionActivity.isDestroyed()) {
                ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "activity is null");
            } else if (weightQuestionSuggestionActivity.mLogHandler != null) {
                Message obtainMessage = weightQuestionSuggestionActivity.mLogHandler.obtainMessage();
                obtainMessage.what = 2;
                weightQuestionSuggestionActivity.mLogHandler.sendMessage(obtainMessage);
            }
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onProgress(int i, int i2, String str) {
            ReleaseLogUtil.e("R_Weight_QuestionSuggestionActivity", "getLogFileFromDevice onProgress progress = ", Integer.valueOf(i2), ", fileIndex = ", Integer.valueOf(i));
            if (i2 >= 100) {
                i2 = 100;
            }
            WeightQuestionSuggestionActivity weightQuestionSuggestionActivity = this.c.get();
            if (weightQuestionSuggestionActivity != null) {
                weightQuestionSuggestionActivity.c = System.currentTimeMillis();
                if (weightQuestionSuggestionActivity.mLogHandler != null) {
                    Message obtainMessage = weightQuestionSuggestionActivity.mLogHandler.obtainMessage();
                    obtainMessage.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putInt("CURRENT_PROCESS", i2);
                    bundle.putString("CURRENT_PROCESS_DESCRIPTION", UnitUtil.e(i + 1, 1, 0) + "/" + UnitUtil.e(WeightQuestionSuggestionActivity.f9210a, 1, 0));
                    obtainMessage.setData(bundle);
                    weightQuestionSuggestionActivity.mLogHandler.sendMessage(obtainMessage);
                    return;
                }
                return;
            }
            ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "activity is null");
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public String getFilePath() {
            return this.f9211a;
        }
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mErrorTipContent = String.format(Locale.ENGLISH, this.mContext.getString(R.string._2130846559_res_0x7f02235f), 1);
        if (nsn.ae(BaseApplication.getContext())) {
            this.mErrorTipContent = String.format(Locale.ENGLISH, this.mContext.getString(R.string._2130846560_res_0x7f022360), 1);
        }
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity
    void processIntentData(Intent intent) {
        if (intent == null) {
            ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "intent null");
            return;
        }
        this.g = intent.getStringExtra("uniqueId");
        MeasurableDevice d2 = ceo.d().d(this.g, false);
        if (d2 == null) {
            ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "device is null");
        } else {
            this.e = d2.getProductId();
        }
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity
    void onAgreeAndContinueClick() {
        ReleaseLogUtil.e("R_Weight_QuestionSuggestionActivity", "productId: ", CommonUtil.l(this.e), ", uniqueId: ", CommonUtil.l(this.g));
        cld HJ_ = cld.HJ_(this, this.e, this.g);
        this.f = HJ_;
        if (HJ_.b()) {
            ReleaseLogUtil.e("R_Weight_QuestionSuggestionActivity", "start get device log, isCollecting: ", Boolean.valueOf(b));
            showLogsCollecting(this.mErrorTipContent);
            this.c = System.currentTimeMillis();
            cfv b2 = cfv.b();
            String str = d;
            b2.a(new c(str));
            if (!b) {
                deleteLogFiles(new File(str));
                String packageName = BaseApplication.getContext().getPackageName();
                new cft().c(packageName, packageName);
                b = true;
            }
            e();
            return;
        }
        showSuggestionErrorMsg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.mButton == null || this.mLogHandler == null) {
            ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "check time out failed");
            return;
        }
        if (!b) {
            ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "log collecting end");
            return;
        }
        if (Math.abs(System.currentTimeMillis() - this.c) >= 5000) {
            ReleaseLogUtil.d("R_Weight_QuestionSuggestionActivity", "update progress time out");
            b = false;
            Message obtainMessage = this.mLogHandler.obtainMessage();
            obtainMessage.what = 2;
            this.mLogHandler.sendMessage(obtainMessage);
            return;
        }
        this.mButton.postDelayed(new Runnable() { // from class: nxn
            @Override // java.lang.Runnable
            public final void run() {
                WeightQuestionSuggestionActivity.this.e();
            }
        }, 5000L);
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity
    void openQuestionSuggestion() {
        ReleaseLogUtil.e("R_Weight_QuestionSuggestionActivity", "openQuestionSuggestion");
        ReleaseLogUtil.e("R_Weight_QuestionSuggestionActivity", "openQuestionSuggestion() errorCode is: ", Integer.valueOf(ixj.b().bCQ_(this, true, true, cpa.i(this.e), cpa.k(this.g))));
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        cld cldVar = this.f;
        if (cldVar != null) {
            cldVar.HS_(null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
