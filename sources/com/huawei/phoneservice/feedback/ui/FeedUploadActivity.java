package com.huawei.phoneservice.feedback.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.entity.FeedbackBean;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.phoneservice.feedbackcommon.utils.AsCache;
import com.huawei.phoneservice.feedbackcommon.utils.NetworkUtils;
import com.huawei.phoneservice.feedbackcommon.utils.UriDeserializer;
import com.huawei.phoneservice.feedbackcommon.utils.UriSerializer;
import com.huawei.phoneservice.feedbackcommon.utils.ZipUtil;
import com.huawei.secure.android.common.intent.SafeBundle;
import java.io.File;
import java.io.IOException;

/* loaded from: classes9.dex */
public class FeedUploadActivity extends FeedbackBaseActivity<com.huawei.phoneservice.feedback.mvp.presenter.c> implements View.OnClickListener, View.OnFocusChangeListener, com.huawei.phoneservice.feedback.mvp.contract.f {
    private TextView b;
    private Button e;
    private EditText f;
    private com.huawei.phoneservice.feedback.mvp.presenter.c g;
    private AsCache h;
    private Button i;
    private FeedbackBean j;
    private TextView k;
    private TextView l;
    private boolean m;
    private LinearLayout n;
    private boolean o = false;

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_uploadfile;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("FeedbackBean", this.j);
        bundle.putString("CacheMap", com.huawei.phoneservice.faq.base.util.j.c().getMapOnSaveInstance());
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        String obj;
        EditText editText = (EditText) view;
        if (view.getId() == R.id.edit_desc) {
            if (z) {
                editText.setTag(editText.getHint().toString());
                obj = "";
            } else {
                obj = editText.getTag().toString();
            }
            editText.setHint(obj);
        }
        ViewScrollInstrumentation.focusChangeOnView(view, z);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity, com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            SafeBundle safeBundle = new SafeBundle(bundle);
            this.j = (FeedbackBean) safeBundle.getParcelable("FeedbackBean");
            com.huawei.phoneservice.faq.base.util.j.c().saveMapOnSaveInstanceState(safeBundle.getString("CacheMap"));
        }
        super.onCreate(bundle);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        Button button = this.e;
        if (button != null) {
            com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.a(this, button);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (s.cdx_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.btn_submit) {
            com.huawei.phoneservice.faq.base.util.b.ccZ_(this);
            ab();
        } else if (view.getId() == R.id.tv_tryagain) {
            this.l.setVisibility(8);
            ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(this)));
            new Handler().postDelayed(new j(), 500L);
            c(R.string._2130850951_res_0x7f023487);
            this.n.setVisibility(0);
            this.m = false;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AlertDialog alertDialog = this.d;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d.dismiss();
        } else if (!this.g.d()) {
            h();
        } else {
            if (this.i.getVisibility() == 0) {
                return;
            }
            super.onBackPressed();
        }
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        setTitle(R.string._2130850950_res_0x7f023486);
        TextView textView = (TextView) findViewById(R.id.txt_number);
        this.b = textView;
        textView.setText(String.format(getResources().getString(R.string._2130850899_res_0x7f023453), 0, 500));
        this.e = (Button) findViewById(R.id.btn_submit);
        this.f = (EditText) findViewById(R.id.edit_desc);
        this.n = (LinearLayout) findViewById(R.id.layout_loading);
        this.i = (Button) findViewById(R.id.bg_dismiss);
        this.k = (TextView) findViewById(R.id.tv_progress);
        this.l = (TextView) findViewById(R.id.tv_tryagain);
        this.f.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500), new com.huawei.phoneservice.faq.base.util.c()});
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.e.setOnClickListener(this);
        this.f.setOnFocusChangeListener(this);
        this.l.setOnClickListener(this);
        this.f.setOnTouchListener(new a());
        this.f.addTextChangedListener(new h());
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        try {
            this.h = AsCache.get(this, AsCache.FEED_BACK_CACHE_FILE_NAME);
        } catch (IOException | RuntimeException e2) {
            com.huawei.phoneservice.faq.base.util.i.c("FeedUploadActivity", e2.getMessage());
        }
        boolean aa = aa();
        this.m = aa;
        if (aa) {
            g();
        } else {
            this.j = new FeedbackBean();
            o();
        }
        this.j.setShowLog(true);
        this.f.setText(this.j.getProblemDesc());
        this.f.setSelection(this.j.getProblemDesc().length());
        y();
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int[] k() {
        return new int[]{R.id.rl_uploadfile};
    }

    public void d(String str) {
        long logsSize = this.j.getLogsSize();
        if (logsSize == 0) {
            logsSize = 1;
        }
        a(logsSize, new i(), new f());
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void e() {
        c(R.string._2130850856_res_0x7f023428);
        this.i.setVisibility(0);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void c(String str) {
        com.huawei.phoneservice.faq.base.util.n.a(this, str);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.e
    public FeedbackBean c() {
        return this.j;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public FeedbackInfo b() {
        return this.j.getInfo();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.f
    public void a(String str) {
        AlertDialog alertDialog = this.d;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d.dismiss();
        }
        ad();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a(com.huawei.phoneservice.feedback.utils.a aVar) {
        int i2;
        if (aVar != com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_SUCCESS) {
            if (aVar == com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_FAILED) {
                i2 = R.string._2130850953_res_0x7f023489;
            }
            new Handler().postDelayed(new c(aVar), 500L);
        }
        this.o = true;
        i2 = R.string._2130850955_res_0x7f02348b;
        c(i2);
        new Handler().postDelayed(new c(aVar), 500L);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a() {
        AsCache asCache = this.h;
        if (asCache != null) {
            asCache.remove("lastSubmitzip");
        }
        this.n.setVisibility(8);
        this.i.setVisibility(8);
        this.e.setEnabled(true);
    }

    public void h() {
        a(getString(R.string._2130850932_res_0x7f023474), null, null, new k(), new b());
        this.g.c();
    }

    public void g() {
        a(getString(R.string._2130850928_res_0x7f023470), getString(R.string._2130850848_res_0x7f023420), getString(R.string._2130850853_res_0x7f023425), new g(), new n());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public com.huawei.phoneservice.feedback.mvp.presenter.c w() {
        com.huawei.phoneservice.feedback.mvp.presenter.c cVar = new com.huawei.phoneservice.feedback.mvp.presenter.c(this, this);
        this.g = cVar;
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        Gson create = this.j.haveMedias() ? new GsonBuilder().registerTypeAdapter(Uri.class, new UriSerializer()).create() : new Gson();
        AsCache asCache = this.h;
        if (asCache != null) {
            asCache.put("lastSubmitzip", create.toJson(this.j), 172800);
        }
        this.g.e(0, 0);
        this.g.e((Context) this, true);
    }

    private void ad() {
        View inflate = getLayoutInflater().inflate(R.layout.feedback_sdk_dialog_uploadzip, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.close)).setOnClickListener(new e());
        a(inflate, false);
    }

    private void c(int i2) {
        this.k.setText(i2);
        this.n.setVisibility(0);
        this.e.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        if (this.j == null) {
            return;
        }
        int i2 = 10;
        try {
            int parseInt = Integer.parseInt(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_MIN_DESC_INPUT));
            if (parseInt >= 1 && parseInt <= 10) {
                i2 = parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        if (TextUtils.isEmpty(this.j.getProblemDesc()) || this.j.getProblemDesc().trim().length() < i2) {
            c(getString(R.string._2130850879_res_0x7f02343f, new Object[]{Integer.valueOf(i2)}));
            return;
        }
        if (!NetworkUtils.isNetworkConnected(this)) {
            c(getResources().getString(R.string._2130850891_res_0x7f02344b));
        } else if (0 == this.j.getLogsSize() || NetworkUtils.isWifiConnected(this)) {
            ac();
        } else {
            d("wifi");
        }
    }

    private boolean aa() {
        AsCache asCache = this.h;
        if (asCache == null) {
            return false;
        }
        String asString = asCache.getAsString("lastSubmitzip");
        if (TextUtils.isEmpty(asString)) {
            return false;
        }
        this.j = (FeedbackBean) new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create().fromJson(asString, FeedbackBean.class);
        return true;
    }

    private void y() {
        if (this.e.getMeasuredWidth() < com.huawei.phoneservice.faq.base.util.b.d(this)) {
            com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.a(this, this.e);
        }
    }

    class a implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            FeedUploadActivity.this.f.setFocusableInTouchMode(true);
            if (view.getId() == R.id.edit_desc && com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.cez_(FeedUploadActivity.this.f)) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                if (motionEvent.getAction() == 1) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        }

        a() {
        }
    }

    class b implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = FeedUploadActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            FeedUploadActivity.this.g.e();
            com.huawei.phoneservice.faq.base.util.j.e().onClick(FeedUploadActivity.this.getClass().getName(), "Cancel", FeedUploadActivity.this.j);
            ViewClickInstrumentation.clickOnView(view);
        }

        b() {
        }
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.phoneservice.feedback.utils.a f8279a;

        @Override // java.lang.Runnable
        public void run() {
            FeedUploadActivity.this.n.setVisibility(8);
            com.huawei.phoneservice.feedback.utils.a aVar = this.f8279a;
            if (aVar == com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_FAILED) {
                FeedUploadActivity.this.l.setVisibility(0);
            } else if (aVar == com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_SUCCESS && FeedUploadActivity.this.m) {
                FeedUploadActivity.this.ab();
                return;
            }
            FeedUploadActivity.this.f();
        }

        c(com.huawei.phoneservice.feedback.utils.a aVar) {
            this.f8279a = aVar;
        }
    }

    class d implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            FeedUploadActivity.this.g.j();
        }

        d() {
        }
    }

    class e implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FeedUploadActivity.this.finish();
            ViewClickInstrumentation.clickOnView(view);
        }

        e() {
        }
    }

    class f implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AlertDialog alertDialog = FeedUploadActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        f() {
        }
    }

    class g implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = FeedUploadActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            FeedUploadActivity.this.o();
            ViewClickInstrumentation.clickOnView(view);
        }

        g() {
        }
    }

    class h implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (FeedUploadActivity.this.j != null) {
                String trim = FeedUploadActivity.this.f.getText().toString().trim();
                FeedUploadActivity.this.j.setProblemDesc(trim);
                int length = trim.length();
                FeedUploadActivity.this.b.setTextColor(ContextCompat.getColor(FeedUploadActivity.this, length >= 500 ? R.color.feedback_sdk_problem_question_max_number : R.color.feedback_sdk_problem_question_number));
                FeedUploadActivity.this.b.setText(String.format(FeedUploadActivity.this.getResources().getString(R.string._2130850899_res_0x7f023453), Integer.valueOf(length), 500));
            }
        }

        h() {
        }
    }

    class i implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = FeedUploadActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            FeedUploadActivity.this.ac();
            ViewClickInstrumentation.clickOnView(view);
        }

        i() {
        }
    }

    class j implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            FeedUploadActivity.this.g.j();
        }

        j() {
        }
    }

    class k implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = FeedUploadActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            FeedUploadActivity.this.g.f();
            com.huawei.phoneservice.faq.base.util.j.e().onClick(FeedUploadActivity.this.getClass().getName(), "Quit", FeedUploadActivity.this.j);
            ViewClickInstrumentation.clickOnView(view);
        }

        k() {
        }
    }

    class n implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = FeedUploadActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            if (FeedUploadActivity.this.h != null) {
                FeedUploadActivity.this.h.remove("lastSubmitzip");
            }
            FeedUploadActivity.this.j = new FeedbackBean();
            FeedUploadActivity.this.f.setText(FeedUploadActivity.this.j.getProblemDesc());
            FeedUploadActivity.this.f.setSelection(FeedUploadActivity.this.j.getProblemDesc().length());
            FeedUploadActivity.this.m = false;
            FeedUploadActivity.this.o();
            ViewClickInstrumentation.clickOnView(view);
        }

        n() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(this)));
        c(R.string._2130850954_res_0x7f02348a);
        new Handler().postDelayed(new d(), 500L);
        this.n.setVisibility(0);
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.e.setEnabled(this.o);
    }
}
