package com.huawei.phoneservice.feedback.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.l;
import com.huawei.phoneservice.faq.base.util.n;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter;
import com.huawei.phoneservice.feedback.entity.FeedbackBean;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity;
import com.huawei.phoneservice.feedback.widget.FeedbackNoticeView;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import com.huawei.phoneservice.feedbackcommon.utils.CharInputFilter;
import com.huawei.phoneservice.feedbackcommon.utils.EmojiFilter;
import com.huawei.phoneservice.feedbackcommon.utils.FaqRegexMatches;
import com.huawei.phoneservice.feedbackcommon.utils.NetworkUtils;
import com.huawei.phoneservice.feedbackcommon.utils.SdkProblemListener;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ProblemSuggestForDeepLinkActivity extends FeedbackBaseActivity<com.huawei.phoneservice.feedback.mvp.presenter.a> implements View.OnClickListener, com.huawei.phoneservice.feedback.mvp.contract.a, View.OnFocusChangeListener, ProblemSuggestPhotoAdapter.b {
    private com.huawei.phoneservice.feedback.mvp.presenter.a b;
    private String e;
    private TextView f;
    private EditText g;
    private FeedbackBean h;
    private TextView i;
    private TextView j;
    private Button k;
    private TextView l;
    private ProblemSuggestPhotoAdapter m;
    private EditText n;
    private LinearLayout o;
    private LinearLayout p;
    private FeedbackNoticeView q;
    private boolean r;
    private ScrollView s;
    private Button t;
    private String u;
    private GridView v;
    private String w;
    private boolean x;
    private String y;

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_deeplink;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("FeedbackBean", this.h);
        bundle.putString("CacheMap", com.huawei.phoneservice.faq.base.util.j.c().getMapOnSaveInstance());
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && "Y".equals(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_IS_DEEPLICK))) {
            i.e("ProblemSuggestForDeep", "ONKEYDOWN IS DEEPLINK RELEASE");
            com.huawei.phoneservice.faq.base.util.j.c().release();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        EditText editText = (EditText) view;
        int id = view.getId();
        if (id == R.id.edit_contact || id == R.id.edit_desc) {
            ceW_(z, editText);
        }
        ViewScrollInstrumentation.focusChangeOnView(view, z);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity, com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            SafeBundle safeBundle = new SafeBundle(bundle);
            String string = safeBundle.getString("CacheMap");
            this.h = (FeedbackBean) safeBundle.getParcelable("FeedbackBean");
            com.huawei.phoneservice.faq.base.util.j.c().saveMapOnSaveInstanceState(string);
        }
        super.onCreate(bundle);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        this.v.setNumColumns(v());
        Button button = this.k;
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
        } else if (view.getId() == R.id.feedback_problem_noticeView) {
            l();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    public void onBackPressed(View view) {
        super.onBackPressed(view);
        if ("Y".equals(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_IS_DEEPLICK))) {
            i.e("ProblemSuggestForDeep", "ONBACKPRESSED IS DEEPLINK RELEASE");
            com.huawei.phoneservice.faq.base.util.j.c().release();
        }
        a(this.h);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        List<MediaItem> a2;
        super.onActivityResult(i, i2, intent);
        SafeIntent safeIntent = new SafeIntent(intent);
        if (i != 100 || safeIntent.getData() == null || (a2 = a(safeIntent, this.h)) == null) {
            return;
        }
        this.h.setMedias(a2);
        this.m.c(a2);
        this.b.b(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        TextView textView = (TextView) findViewById(R.id.txt_style);
        this.f = textView;
        textView.setSelected(true);
        TextView textView2 = (TextView) findViewById(R.id.txt_channel);
        this.i = textView2;
        textView2.setSelected(true);
        this.g = (EditText) findViewById(R.id.edit_question);
        this.l = (TextView) findViewById(R.id.txt_number);
        this.j = (TextView) findViewById(R.id.tv_progress);
        this.l.setText(String.format(getResources().getString(R.string._2130850899_res_0x7f023453), 0, 500));
        this.m = new ProblemSuggestPhotoAdapter(this);
        GridView gridView = (GridView) findViewById(R.id.list_media);
        this.v = gridView;
        gridView.setAdapter((ListAdapter) this.m);
        this.v.setNumColumns(v());
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_HIDE_ADD_ATTACHMENT);
        if (sdk == null || !"Y".equals(sdk.toUpperCase(Locale.ROOT))) {
            this.v.setVisibility(0);
        } else {
            this.v.setVisibility(8);
        }
        this.n = (EditText) findViewById(R.id.edit_contact);
        Button button = (Button) findViewById(R.id.btn_submit);
        this.k = button;
        com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.a(this, button);
        this.p = (LinearLayout) findViewById(R.id.layout_loading);
        this.t = (Button) findViewById(R.id.bg_dismiss);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);
        this.s = scrollView;
        scrollView.setOverScrollMode(0);
        this.o = (LinearLayout) findViewById(R.id.ll_phone_or_email);
        this.q = (FeedbackNoticeView) findViewById(R.id.feedback_problem_noticeView);
        g();
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.m.e(this);
        this.g.addTextChangedListener(new a());
        this.n.addTextChangedListener(new b());
        this.k.setOnClickListener(this);
        this.q.setOnClickListener(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        setTitle(R.string._2130850845_res_0x7f02341d);
        try {
            this.q.a(FeedbackNoticeView.c.PROGRESS);
            h();
            if (!com.huawei.phoneservice.faq.base.util.b.b(this)) {
                this.q.c(FaqConstants.FaqErrorCode.INTERNET_ERROR);
                return;
            }
            this.h = new FeedbackBean();
            this.b.d(this, this.u, this.w, this.e);
            this.o.setVisibility(this.x ? 0 : 8);
        } catch (RuntimeException e2) {
            i.e("ProblemSuggestForDeep", e2.getMessage());
            finish();
        }
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int[] k() {
        return new int[]{R.id.ll_channel, R.id.txt_channel, R.id.rl_description, R.id.ll_contact, R.id.edit_contact, R.id.ll_category, R.id.txt_style, R.id.ll_description};
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void f() {
        x();
    }

    public void d(String str) {
        i.d("ProblemSuggestForDeep", "openUploadFlow......" + str);
        long filesSize = this.h.getFilesSize();
        if (filesSize == 0) {
            filesSize = 1;
        }
        a(filesSize, new d(), new c());
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void e() {
        e(R.string._2130850856_res_0x7f023428);
        this.t.setVisibility(0);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void c(String str) {
        n.a(this, str);
        Log.e("ProblemSuggestForDeep", "showToast...." + str);
        if (this.r) {
            this.k.setEnabled(true);
        }
        this.r = false;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void c(int i) {
        Resources resources;
        int i2;
        i.d("ProblemSuggestForDeep", "showErrorCode:" + i);
        if (i == 401) {
            resources = getResources();
            i2 = R.string._2130850886_res_0x7f023446;
        } else {
            if (i != 405) {
                a(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR);
                t();
                this.q.setVisibility(0);
                this.q.setEnabled(true);
                this.s.setVisibility(8);
            }
            resources = getResources();
            i2 = R.string._2130850923_res_0x7f02346b;
        }
        e(resources.getString(i2));
        t();
        this.q.setVisibility(0);
        this.q.setEnabled(true);
        this.s.setVisibility(8);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.e
    public FeedbackBean c() {
        return this.h;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void b(List<com.huawei.phoneservice.feedback.entity.c> list) {
        if (l.e(ModuleConfigUtils.getChannelName())) {
            a(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
            i.d("ProblemSuggestForDeep", "channelName is NULL......");
            return;
        }
        i.d("ProblemSuggestForDeep", "showStyles......");
        this.i.setText(ModuleConfigUtils.getChannelName());
        t();
        this.s.setVisibility(0);
        this.q.setVisibility(8);
        ArrayList<com.huawei.phoneservice.feedback.entity.c> arrayList = new ArrayList(list);
        Iterator<com.huawei.phoneservice.feedback.entity.c> it = list.iterator();
        while (it.hasNext()) {
            for (com.huawei.phoneservice.feedback.entity.a aVar : it.next().f8256a) {
                com.huawei.phoneservice.feedback.entity.c cVar = new com.huawei.phoneservice.feedback.entity.c();
                cVar.b = aVar.f8255a;
                cVar.d = aVar.b;
                cVar.c = aVar.d;
                arrayList.add(cVar);
            }
        }
        for (com.huawei.phoneservice.feedback.entity.c cVar2 : arrayList) {
            if (this.y.equals(cVar2.c)) {
                this.f.setText(cVar2.b);
                this.h.setProblemType(cVar2.c, null);
                return;
            }
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void b(String str) {
        i.d("ProblemSuggestForDeep", "uploadFileDone......" + str);
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void b(int i) {
        a(this.h, i);
        this.h.remove(i);
        this.m.c(this.h.getMedias());
        this.v.setAdapter((ListAdapter) this.m);
        this.b.b(this);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public FeedbackInfo b() {
        return this.h.getInfo();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(boolean z) {
        i.d("ProblemSuggestForDeep", "isCompress......" + z);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(String str) {
        AlertDialog alertDialog = this.d;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d.dismiss();
        }
        c(getString(R.string._2130850924_res_0x7f02346c));
        String problemId = TextUtils.isEmpty(this.h.getProblemId()) ? str : this.h.getProblemId();
        this.h.setProblemId(str);
        SdkProblemListener sdkListener = com.huawei.phoneservice.feedbackcommon.utils.b.e().getSdkListener();
        this.k.setEnabled(true);
        this.t.setVisibility(0);
        this.t.postDelayed(new f(sdkListener, str, problemId), 2000L);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a(com.huawei.phoneservice.feedback.utils.a aVar) {
        i.d("ProblemSuggestForDeep", "zipCompressFinished......" + aVar);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(FaqConstants.FaqErrorCode faqErrorCode) {
        i.d("ProblemSuggestForDeep", "showError......" + faqErrorCode);
        t();
        this.q.c(faqErrorCode);
        this.q.setVisibility(0);
        this.q.setEnabled(true);
        this.s.setVisibility(8);
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void a(int i) {
        FeedbackBean feedbackBean = this.h;
        if (feedbackBean != null) {
            a(feedbackBean.getMediaItem(i));
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a() {
        this.p.setVisibility(8);
        this.t.setVisibility(8);
        o();
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
    public void aa() {
        this.r = true;
        this.h.setProblemName(this.f.getText().toString());
        try {
            this.h.setAssociatedId(Long.parseLong(this.w));
        } catch (NumberFormatException unused) {
            i.e("ProblemSuggestForDeep", "NumberFormatException");
        }
        this.h.setUniqueCode(this.e);
        com.huawei.phoneservice.faq.base.util.j.e().onClick(getClass().getName(), "Submit", this.h);
        this.b.c((Context) this, false);
    }

    private void ab() {
        if (!NetworkUtils.isNetworkConnected(this)) {
            this.t.setVisibility(8);
            c(getResources().getString(R.string._2130850891_res_0x7f02344b));
        } else if ((!this.h.haveMedias() && 0 == this.h.getLogsSize()) || NetworkUtils.isWifiConnected(this)) {
            aa();
        } else {
            this.t.setVisibility(8);
            d("wifi");
        }
    }

    private void e(String str) {
        this.q.c(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
        this.q.e(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, R.drawable._2131430129_res_0x7f0b0af1);
        this.q.c(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, getResources().getDimensionPixelOffset(R.dimen._2131362784_res_0x7f0a03e0));
        this.q.getNoticeTextView().setText(str);
    }

    private void e(int i) {
        this.j.setVisibility(0);
        this.j.setText(i);
        this.p.setVisibility(0);
        this.k.setEnabled(false);
    }

    private void ceW_(boolean z, EditText editText) {
        String obj;
        if (z) {
            editText.setTag(editText.getHint().toString());
            obj = "";
        } else {
            obj = editText.getTag().toString();
        }
        editText.setHint(obj);
    }

    private void y() {
        FeedbackBean feedbackBean = this.h;
        if (feedbackBean == null) {
            return;
        }
        if (TextUtils.isEmpty(feedbackBean.getProblemDesc()) || this.h.getProblemDesc().trim().length() < 10) {
            c(getString(R.string._2130850879_res_0x7f02343f, new Object[]{10}));
            return;
        }
        if (this.o.getVisibility() == 0) {
            String contact = this.h.getContact();
            if (TextUtils.isEmpty(contact)) {
                c(getResources().getString(R.string._2130850900_res_0x7f023454));
                return;
            }
            boolean isEmail = FaqRegexMatches.isEmail(contact);
            boolean isMobile = FaqRegexMatches.isMobile(contact);
            if (!isEmail && !isMobile) {
                c(getResources().getString(R.string._2130850901_res_0x7f023455));
                return;
            }
        }
        this.t.setVisibility(0);
        ab();
    }

    private void o() {
        this.k.setEnabled(true);
    }

    class a implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextView textView;
            ProblemSuggestForDeepLinkActivity problemSuggestForDeepLinkActivity;
            int i;
            if (ProblemSuggestForDeepLinkActivity.this.h != null) {
                String trim = ProblemSuggestForDeepLinkActivity.this.g.getText().toString().trim();
                ProblemSuggestForDeepLinkActivity.this.h.setProblemDesc(trim);
                if (trim.length() >= 500) {
                    textView = ProblemSuggestForDeepLinkActivity.this.l;
                    problemSuggestForDeepLinkActivity = ProblemSuggestForDeepLinkActivity.this;
                    i = R.color.feedback_sdk_problem_question_max_number;
                } else {
                    textView = ProblemSuggestForDeepLinkActivity.this.l;
                    problemSuggestForDeepLinkActivity = ProblemSuggestForDeepLinkActivity.this;
                    i = R.color.feedback_sdk_problem_question_number;
                }
                textView.setTextColor(ContextCompat.getColor(problemSuggestForDeepLinkActivity, i));
                ProblemSuggestForDeepLinkActivity.this.l.setText(String.format(ProblemSuggestForDeepLinkActivity.this.getResources().getString(R.string._2130850899_res_0x7f023453), Integer.valueOf(trim.length()), 500));
            }
        }

        a() {
        }
    }

    class b implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            EditText editText;
            int i;
            if (ProblemSuggestForDeepLinkActivity.this.h != null) {
                String trim = ProblemSuggestForDeepLinkActivity.this.n.getText().toString().trim();
                if (trim.length() >= 100) {
                    editText = ProblemSuggestForDeepLinkActivity.this.n;
                    i = R.drawable.feedback_sdk_problem_max_num_rectangle_bg;
                } else {
                    editText = ProblemSuggestForDeepLinkActivity.this.n;
                    i = R.drawable.feedback_sdk_problem_rectangle_bg;
                }
                editText.setBackgroundResource(i);
                ProblemSuggestForDeepLinkActivity.this.h.setContact(trim);
            }
        }

        b() {
        }
    }

    class c implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProblemSuggestForDeepLinkActivity.this.s();
            ViewClickInstrumentation.clickOnView(view);
        }

        c() {
        }
    }

    class d implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ProblemSuggestForDeepLinkActivity.this.s();
            ProblemSuggestForDeepLinkActivity.this.aa();
            ViewClickInstrumentation.clickOnView(view);
        }

        d() {
        }
    }

    class e implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view.getId() == R.id.edit_desc && com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.cez_(ProblemSuggestForDeepLinkActivity.this.g)) {
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

    class f implements Runnable {
        final /* synthetic */ SdkProblemListener b;
        final /* synthetic */ String c;
        final /* synthetic */ String e;

        @Override // java.lang.Runnable
        public void run() {
            SdkProblemListener sdkProblemListener = this.b;
            if (sdkProblemListener != null) {
                sdkProblemListener.onSubmitResult(0, this.e, this.c, ProblemSuggestForDeepLinkActivity.this.h.getSrCode(), null);
            }
            Intent intent = new Intent();
            intent.putExtra("parentProblemId", this.c);
            intent.putExtra("problemId", this.e);
            intent.putExtra("srCode", ProblemSuggestForDeepLinkActivity.this.h.getSrCode());
            ProblemSuggestForDeepLinkActivity.this.setResult(-1, intent);
            ProblemSuggestForDeepLinkActivity.this.finish();
        }

        f(SdkProblemListener sdkProblemListener, String str, String str2) {
            this.b = sdkProblemListener;
            this.e = str;
            this.c = str2;
        }
    }

    static class j implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }

        /* synthetic */ j(e eVar) {
            this();
        }

        private j() {
        }
    }

    private void g() {
        CharInputFilter charInputFilter = new CharInputFilter("[&<>\"'()\"\\n\" ]");
        EmojiFilter emojiFilter = new EmojiFilter();
        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(500);
        InputFilter.LengthFilter lengthFilter2 = new InputFilter.LengthFilter(100);
        this.g.setFilters(new InputFilter[]{lengthFilter, new com.huawei.phoneservice.faq.base.util.c()});
        this.n.setFilters(new InputFilter[]{charInputFilter, emojiFilter, lengthFilter2});
        this.g.setOnFocusChangeListener(this);
        this.n.setOnFocusChangeListener(this);
        this.n.setOnTouchListener(new j(null));
        this.g.setHint(getResources().getQuantityString(R.plurals._2130903530_res_0x7f0301ea, 10, 10));
        this.g.setOnTouchListener(new e());
    }

    private void h() {
        SafeBundle safeBundle = new SafeBundle(new SafeIntent(getIntent()).getExtras());
        this.x = "Y".equals(safeBundle.getString("showContact"));
        this.u = safeBundle.getString("appId");
        this.y = safeBundle.getString("categoryId");
        this.w = safeBundle.getString("problemId");
        this.e = safeBundle.getString("unicode");
    }
}
