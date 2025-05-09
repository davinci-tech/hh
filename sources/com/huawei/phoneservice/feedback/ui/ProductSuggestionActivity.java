package com.huawei.phoneservice.feedback.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter;
import com.huawei.phoneservice.feedback.entity.FeedbackBean;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity;
import com.huawei.phoneservice.feedback.photolibrary.internal.widget.BadgeHelper;
import com.huawei.phoneservice.feedback.widget.FeedbackNoticeView;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemInfo;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import com.huawei.phoneservice.feedbackcommon.utils.AsCache;
import com.huawei.phoneservice.feedbackcommon.utils.NetworkUtils;
import com.huawei.phoneservice.feedbackcommon.utils.OnReadListener;
import com.huawei.phoneservice.feedbackcommon.utils.UriDeserializer;
import com.huawei.phoneservice.feedbackcommon.utils.UriSerializer;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ProductSuggestionActivity extends FeedbackBaseActivity<com.huawei.phoneservice.feedback.mvp.presenter.a> implements View.OnClickListener, com.huawei.phoneservice.feedback.mvp.contract.a, View.OnFocusChangeListener, ProblemSuggestPhotoAdapter.b {
    private com.huawei.phoneservice.feedback.mvp.presenter.a b;
    private TextView f;
    private Button g;
    private Button h;
    private EditText i;
    private LinearLayout j;
    private GridView k;
    private FeedbackNoticeView l;
    private FeedbackBean m;
    private ScrollView n;
    private AsCache o;
    private ProblemSuggestPhotoAdapter t;
    private int e = 0;
    private boolean q = false;
    private int s = 0;

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a(com.huawei.phoneservice.feedback.utils.a aVar) {
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void c(int i2) {
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_product_suggestion;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu._2131755008_res_0x7f100000, menu);
        MenuItem findItem = menu.findItem(R.id.feedback_sdk_show_history);
        View actionView = findItem.getActionView();
        new BadgeHelper(this).a(true).ceA_((ImageView) actionView.findViewById(R.id.menu_history)).setBadgeNumber(this.e);
        actionView.setOnClickListener(new c());
        if (com.huawei.phoneservice.faq.base.util.l.e(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"))) {
            this.q = false;
        }
        findItem.setVisible(this.q);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        String obj;
        EditText editText = (EditText) view;
        if (view.getId() == R.id.edit_question) {
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
            this.m = (FeedbackBean) safeBundle.getParcelable("FeedbackBean");
            com.huawei.phoneservice.faq.base.util.j.c().saveMapOnSaveInstanceState(safeBundle.getString("CacheMap"));
        }
        super.onCreate(bundle);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        this.k.setNumColumns(v());
        Button button = this.g;
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
            y();
        } else if (view == this.l) {
            l();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AlertDialog alertDialog = this.d;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d.dismiss();
            return;
        }
        if (!this.b.d()) {
            h();
        } else {
            if (this.h.getVisibility() == 0) {
                return;
            }
            a(this.m);
            com.huawei.phoneservice.faq.base.util.b.ccZ_(this);
            super.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        List<MediaItem> a2;
        super.onActivityResult(i2, i3, intent);
        SafeIntent safeIntent = new SafeIntent(intent);
        if (i2 != 100 || safeIntent.getData() == null || (a2 = a(safeIntent, this.m)) == null) {
            return;
        }
        this.m.setMedias(a2);
        this.t.c(a2);
        this.b.b(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        GridView gridView;
        this.k = (GridView) findViewById(R.id.list_media);
        this.t = new ProblemSuggestPhotoAdapter(this);
        this.g = (Button) findViewById(R.id.btn_submit);
        this.l = (FeedbackNoticeView) findViewById(R.id.feedback_problem_noticeView);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);
        this.n = scrollView;
        int i2 = 0;
        scrollView.setOverScrollMode(0);
        com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.a(this, this.g);
        this.j = (LinearLayout) findViewById(R.id.layout_loading);
        this.i = (EditText) findViewById(R.id.edit_question);
        this.h = (Button) findViewById(R.id.bg_dismiss);
        TextView textView = (TextView) findViewById(R.id.txt_number);
        this.f = textView;
        textView.setText(String.format(getResources().getString(R.string._2130850899_res_0x7f023453), 0, 500));
        this.i.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500), new com.huawei.phoneservice.faq.base.util.c()});
        this.i.setOnTouchListener(new e());
        this.k.setAdapter((ListAdapter) this.t);
        this.k.setNumColumns(v());
        this.k.requestFocus();
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_HIDE_ADD_ATTACHMENT);
        if (sdk == null || !"Y".equals(sdk.toUpperCase(Locale.ROOT))) {
            gridView = this.k;
        } else {
            gridView = this.k;
            i2 = 8;
        }
        gridView.setVisibility(i2);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.i.addTextChangedListener(new g());
        this.t.e(this);
        this.g.setOnClickListener(this);
        this.i.setOnFocusChangeListener(this);
        this.l.setOnClickListener(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        try {
            this.l.a(FeedbackNoticeView.c.PROGRESS);
            setTitle(getString(R.string._2130850907_res_0x7f02345b));
            try {
                this.o = AsCache.get(this, "productSuggest");
            } catch (IOException | RuntimeException e2) {
                com.huawei.phoneservice.faq.base.util.i.c("ProductSuggestionActivity__", e2.getMessage());
            }
            SafeIntent safeIntent = new SafeIntent(getIntent());
            ProblemInfo problemInfo = safeIntent.hasExtra("problem_info") ? (ProblemInfo) safeIntent.getParcelableExtra("problem_info") : null;
            if (problemInfo == null || !problemInfo.isDetail()) {
                this.s = 0;
                this.e = problemInfo == null ? 0 : problemInfo.getUnread();
                this.m = new FeedbackBean();
                this.q = ModuleConfigUtils.productSuggestLsEnabled();
                this.l.setVisibility(8);
            } else {
                this.s = 1;
                this.m = new FeedbackBean(problemInfo.getProblemId(), problemInfo.getProblemType(), problemInfo.getProblemType(), problemInfo.getContact());
                this.q = false;
            }
            invalidateOptionsMenu();
            this.b.d(this, false);
            if (com.huawei.phoneservice.faq.base.util.l.e(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"))) {
                return;
            }
            com.huawei.phoneservice.feedbackcommon.utils.b.e().getUnread(this, "", new b());
        } catch (RuntimeException e3) {
            com.huawei.phoneservice.faq.base.util.i.e("ProductSuggestionActivity__", e3.getMessage());
            finish();
        }
    }

    public void d(String str) {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "openUploadFlow......" + str);
        long filesSize = this.m.getFilesSize();
        if (filesSize == 0) {
            filesSize = 1;
        }
        a(filesSize, new h(), new j());
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void f() {
        x();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void e() {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "showLoading......");
        this.g.setEnabled(false);
        this.j.setVisibility(0);
        this.h.setVisibility(0);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void c(String str) {
        com.huawei.phoneservice.faq.base.util.n.a(this, str);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.e
    public FeedbackBean c() {
        return this.m;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void b(List<com.huawei.phoneservice.feedback.entity.c> list) {
        t();
        this.n.setVisibility(0);
        this.l.setVisibility(8);
        boolean o = o();
        FeedbackBean feedbackBean = this.m;
        if (feedbackBean != null) {
            this.i.setText(feedbackBean.getProblemDesc());
            if (this.m.haveMedias()) {
                this.b.b(this);
                this.t.c(this.m.getMedias());
            }
        }
        if (o) {
            g();
        }
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "showStyles......");
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void b(String str) {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "uploadFileDone......" + str);
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void b(int i2) {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "onDelete......" + i2);
        a(this.m, i2);
        this.m.remove(i2);
        this.t.c(this.m.getMedias());
        this.k.setAdapter((ListAdapter) this.t);
        this.b.b(this);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public FeedbackInfo b() {
        return this.m.getInfo();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(boolean z) {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "isCompress......" + z);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(String str) {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "submitDone......" + str);
        s();
        String problemId = TextUtils.isEmpty(this.m.getProblemId()) ? str : this.m.getProblemId();
        this.m.setProblemId(str);
        new Thread(new a(problemId)).start();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(FaqConstants.FaqErrorCode faqErrorCode) {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "showError......" + faqErrorCode);
        t();
        this.l.c(faqErrorCode);
        this.l.setVisibility(0);
        this.l.setEnabled(true);
        this.n.setVisibility(8);
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void a(int i2) {
        FeedbackBean feedbackBean = this.m;
        if (feedbackBean != null) {
            a(feedbackBean.getMediaItem(i2));
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a() {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "hideLoading......");
        this.j.setVisibility(8);
        this.h.setVisibility(8);
        ab();
        AsCache asCache = this.o;
        if (asCache != null) {
            asCache.remove("productLastSubmit");
        }
    }

    public void h() {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "openUploadExit......");
        a(getString(R.string._2130850932_res_0x7f023474), null, null, new l(), new n());
        this.b.f();
    }

    public void g() {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "openUploadContinue......");
        a(getString(R.string._2130850928_res_0x7f023470), getString(R.string._2130850848_res_0x7f023420), getString(R.string._2130850853_res_0x7f023425), new f(), new i());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public com.huawei.phoneservice.feedback.mvp.presenter.a w() {
        com.huawei.phoneservice.feedback.mvp.presenter.a aVar = new com.huawei.phoneservice.feedback.mvp.presenter.a(this, this, this);
        this.b = aVar;
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "isChangeSubmitColor......");
        this.g.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "doLastSubmit......");
        Gson create = this.m.haveMedias() ? new GsonBuilder().registerTypeAdapter(Uri.class, new UriSerializer()).create() : new Gson();
        AsCache asCache = this.o;
        if (asCache != null) {
            asCache.put("productLastSubmit", create.toJson(this.m), 172800);
        }
        com.huawei.phoneservice.faq.base.util.j.e().onClick(getClass().getName(), "Submit", this.m);
        this.b.c((Context) this, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        Intent intent = new Intent(this, (Class<?>) FeedbackSubmitSuccessActivity.class);
        intent.putExtra("problemId", str);
        intent.putExtra("COME_FROM", true);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        View inflate = getLayoutInflater().inflate(R.layout.feedback_sdk_dialog_close, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.btnClose);
        textView.setText(R.string._2130850847_res_0x7f02341f);
        textView.setOnClickListener(new d(str));
        a(inflate, false);
    }

    class a implements Runnable {
        final /* synthetic */ String d;

        class b implements Runnable {
            final /* synthetic */ boolean c;

            @Override // java.lang.Runnable
            public void run() {
                if (!this.c) {
                    a aVar = a.this;
                    ProductSuggestionActivity.this.e(aVar.d);
                } else {
                    a aVar2 = a.this;
                    ProductSuggestionActivity.this.g(aVar2.d);
                }
            }

            b(boolean z) {
                this.c = z;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ProductSuggestionActivity.this.o != null) {
                ProductSuggestionActivity.this.o.remove("productLastSubmit");
            }
            ProductSuggestionActivity.this.runOnUiThread(new b(!com.huawei.phoneservice.faq.base.util.l.e(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken")) && ProductSuggestionActivity.this.s == 0));
        }

        a(String str) {
            this.d = str;
        }
    }

    class b implements OnReadListener {
        @Override // com.huawei.phoneservice.feedbackcommon.utils.OnReadListener
        public void read(Throwable th, String str) {
        }

        @Override // com.huawei.phoneservice.feedbackcommon.utils.OnReadListener
        public void unread(Throwable th, String str, int i) {
            ProductSuggestionActivity.this.e = i;
            ProductSuggestionActivity.this.invalidateOptionsMenu();
        }

        b() {
        }
    }

    class c implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (!ProductSuggestionActivity.this.b.d() || ProductSuggestionActivity.this.h.getVisibility() == 0) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ProductSuggestionActivity.this.e = 0;
            ProductSuggestionActivity.this.invalidateOptionsMenu();
            Intent intent = new Intent(ProductSuggestionActivity.this, (Class<?>) FeedListActivity.class);
            intent.putExtra("COME_FROM", true);
            ProductSuggestionActivity.this.startActivity(intent);
            com.huawei.phoneservice.faq.base.util.j.e().onClick(ProductSuggestionActivity.this.getClass().getName(), "Click", ProductSuggestionActivity.this.m);
            ViewClickInstrumentation.clickOnView(view);
        }

        c() {
        }
    }

    class d implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8294a;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProductSuggestionActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            Intent intent = new Intent();
            intent.putExtra("parentProblemId", this.f8294a);
            intent.putExtra("problemId", ProductSuggestionActivity.this.m.getProblemId());
            intent.putExtra("srCode", ProductSuggestionActivity.this.m.getSrCode());
            ProductSuggestionActivity.this.setResult(-1, intent);
            ProductSuggestionActivity.this.finish();
            ViewClickInstrumentation.clickOnView(view);
        }

        d(String str) {
            this.f8294a = str;
        }
    }

    class e implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view.getId() == R.id.edit_desc && com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.cez_(ProductSuggestionActivity.this.i)) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                if (motionEvent.getAction() == 1) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        }

        e() {
        }
    }

    class f implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProductSuggestionActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProductSuggestionActivity.this.y();
            ViewClickInstrumentation.clickOnView(view);
        }

        f() {
        }
    }

    class g implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (ProductSuggestionActivity.this.m != null) {
                String trim = ProductSuggestionActivity.this.i.getText().toString().trim();
                ProductSuggestionActivity.this.m.setProblemDesc(trim);
                int length = trim.length();
                ProductSuggestionActivity.this.f.setTextColor(ContextCompat.getColor(ProductSuggestionActivity.this, length >= 500 ? R.color.feedback_sdk_problem_question_max_number : R.color.feedback_sdk_problem_question_number));
                ProductSuggestionActivity.this.f.setText(String.format(ProductSuggestionActivity.this.getResources().getString(R.string._2130850899_res_0x7f023453), Integer.valueOf(length), 500));
                ProductSuggestionActivity.this.ab();
            }
        }

        g() {
        }
    }

    class h implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ProductSuggestionActivity.this.s();
            ProductSuggestionActivity.this.aa();
            ViewClickInstrumentation.clickOnView(view);
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
            AlertDialog alertDialog = ProductSuggestionActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            if (ProductSuggestionActivity.this.o != null) {
                ProductSuggestionActivity.this.o.remove("productLastSubmit");
            }
            ProductSuggestionActivity productSuggestionActivity = ProductSuggestionActivity.this;
            productSuggestionActivity.a(productSuggestionActivity.m);
            ProductSuggestionActivity.this.m = new FeedbackBean();
            ProductSuggestionActivity.this.i.setText(ProductSuggestionActivity.this.m.getProblemDesc());
            ProductSuggestionActivity.this.t.c(ProductSuggestionActivity.this.m.getMedias());
            ViewClickInstrumentation.clickOnView(view);
        }

        i() {
        }
    }

    class j implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProductSuggestionActivity.this.s();
            ViewClickInstrumentation.clickOnView(view);
        }

        j() {
        }
    }

    class l implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProductSuggestionActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProductSuggestionActivity.this.b.h();
            com.huawei.phoneservice.faq.base.util.j.e().onClick(ProductSuggestionActivity.this.getClass().getName(), "Quit", ProductSuggestionActivity.this.m);
            ViewClickInstrumentation.clickOnView(view);
        }

        l() {
        }
    }

    class n implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProductSuggestionActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProductSuggestionActivity.this.b.g();
            com.huawei.phoneservice.faq.base.util.j.e().onClick(ProductSuggestionActivity.this.getClass().getName(), "Cancel", ProductSuggestionActivity.this.m);
            ViewClickInstrumentation.clickOnView(view);
        }

        n() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        com.huawei.phoneservice.faq.base.util.i.d("ProductSuggestionActivity__", "submit......");
        int i2 = 10;
        try {
            int parseInt = Integer.parseInt(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_MIN_DESC_INPUT));
            if (parseInt >= 1 && parseInt <= 10) {
                i2 = parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        if (TextUtils.isEmpty(this.m.getProblemDesc()) || this.m.getProblemDesc().trim().length() < i2) {
            c(getString(R.string._2130850879_res_0x7f02343f, new Object[]{Integer.valueOf(i2)}));
            return;
        }
        if (!NetworkUtils.isNetworkConnected(this)) {
            c(getResources().getString(R.string._2130850891_res_0x7f02344b));
        } else if (!this.m.haveMedias() || NetworkUtils.isWifiConnected(this)) {
            aa();
        } else {
            d("wifi");
        }
    }

    private boolean o() {
        if (this.o != null && TextUtils.isEmpty(this.m.getProblemId())) {
            String asString = this.o.getAsString("productLastSubmit");
            if (!TextUtils.isEmpty(asString)) {
                this.m = (FeedbackBean) new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create().fromJson(asString, FeedbackBean.class);
                return true;
            }
        }
        return false;
    }
}
