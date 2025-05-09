package com.huawei.phoneservice.feedback.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter;
import com.huawei.phoneservice.feedback.adapter.f;
import com.huawei.phoneservice.feedback.entity.FeedbackBean;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity;
import com.huawei.phoneservice.feedback.photolibrary.internal.widget.BadgeHelper;
import com.huawei.phoneservice.feedback.widget.AutoLineLayoutManager;
import com.huawei.phoneservice.feedback.widget.ClickUrlSpan;
import com.huawei.phoneservice.feedback.widget.FeedbackNoticeView;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemInfo;
import com.huawei.phoneservice.feedbackcommon.entity.QueryIsoLanguageResponse;
import com.huawei.phoneservice.feedbackcommon.entity.QueryNoticeResponse;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import com.huawei.phoneservice.feedbackcommon.utils.AsCache;
import com.huawei.phoneservice.feedbackcommon.utils.CharInputFilter;
import com.huawei.phoneservice.feedbackcommon.utils.EmojiFilter;
import com.huawei.phoneservice.feedbackcommon.utils.FaqRegexMatches;
import com.huawei.phoneservice.feedbackcommon.utils.NetworkUtils;
import com.huawei.phoneservice.feedbackcommon.utils.OnReadListener;
import com.huawei.phoneservice.feedbackcommon.utils.SdkProblemListener;
import com.huawei.phoneservice.feedbackcommon.utils.UriDeserializer;
import com.huawei.phoneservice.feedbackcommon.utils.UriSerializer;
import com.huawei.phoneservice.feedbackcommon.utils.ZipUtil;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ProblemSuggestActivity extends FeedbackBaseActivity<com.huawei.phoneservice.feedback.mvp.presenter.a> implements View.OnClickListener, com.huawei.phoneservice.feedback.mvp.contract.a, View.OnFocusChangeListener, ProblemSuggestPhotoAdapter.b {
    private com.huawei.phoneservice.feedback.mvp.presenter.a aa;
    private AsCache ab;
    private boolean ac;
    private TextView aj;
    private TextView ak;
    private TextView al;
    private LinearLayout am;
    private TextView an;
    private EditText ao;
    private com.huawei.phoneservice.feedback.adapter.f ap;
    private ProblemSuggestPhotoAdapter aq;
    private RecyclerView ar;
    private TextView as;
    private LinearLayout au;
    private EditText ax;
    private RelativeLayout b;
    private CheckBox e;
    private LinearLayout f;
    private TextView g;
    private Button h;
    private TextView i;
    private Button j;
    private boolean k;
    private FeedbackNoticeView l;
    private boolean m;
    private boolean n;
    private boolean o;
    private GridView p;
    private ScrollView q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private FeedbackBean z;
    private int ad = 0;
    private boolean ag = false;
    private boolean ae = false;
    private boolean ai = false;
    private boolean af = true;
    private int ah = 0;
    private int u = 10;

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void b(String str) {
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void c(int i2) {
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_problem_suggest;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("FeedbackBean", this.z);
        bundle.putString("CacheMap", com.huawei.phoneservice.faq.base.util.j.c().getMapOnSaveInstance());
        bundle.putBoolean("isCompress", this.ai);
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu._2131755008_res_0x7f100000, menu);
        MenuItem findItem = menu.findItem(R.id.feedback_sdk_show_history);
        View actionView = findItem.getActionView();
        new BadgeHelper(this).a(true).ceA_((ImageView) actionView.findViewById(R.id.menu_history)).setBadgeNumber(this.ad);
        actionView.setOnClickListener(new ad());
        findItem.setVisible(this.ag);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        int id = view.getId();
        if (id == R.id.edit_contact || id == R.id.edit_desc) {
            b(view, z);
        }
        ViewScrollInstrumentation.focusChangeOnView(view, z);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity, com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            SafeBundle safeBundle = new SafeBundle(bundle);
            this.z = (FeedbackBean) safeBundle.getParcelable("FeedbackBean");
            com.huawei.phoneservice.faq.base.util.j.c().saveMapOnSaveInstanceState(safeBundle.getString("CacheMap"));
            FeedbackBean feedbackBean = this.z;
            this.v = feedbackBean != null;
            if (feedbackBean != null) {
                this.af = feedbackBean.getLogsSize() == 0;
            }
            this.ai = safeBundle.getBoolean("isCompress");
        }
        super.onCreate(bundle);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        this.p.setNumColumns(v());
        Button button = this.h;
        if (button != null) {
            com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.a(this, button);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.txt_style) {
            c(this.aa.e());
        } else if (id == R.id.btn_submit) {
            com.huawei.phoneservice.faq.base.util.b.ccZ_(this);
            ap();
        } else if (view.getId() == R.id.feedback_problem_noticeView) {
            l();
        } else if (view.getId() == R.id.tv_tryagain) {
            this.r = true;
            this.i.setVisibility(8);
            ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(this)));
            e(R.string._2130850951_res_0x7f023487);
            this.s = false;
            new Handler().postDelayed(new n(), 500L);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        FeedbackBean feedbackBean;
        AsCache asCache;
        AlertDialog alertDialog = this.d;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d.dismiss();
            return;
        }
        if (!this.aa.d()) {
            g();
            return;
        }
        if (this.j.getVisibility() == 0) {
            return;
        }
        com.huawei.phoneservice.faq.base.util.b.ccZ_(this);
        if (as()) {
            al();
            return;
        }
        if (this.ah == 0 && (feedbackBean = this.z) != null && TextUtils.isEmpty(feedbackBean.getParentId()) && TextUtils.isEmpty(this.z.getProblemDesc()) && !this.z.haveMedias() && (asCache = this.ab) != null) {
            asCache.remove("feedBackCache");
        }
        super.onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        List<MediaItem> a2;
        super.onActivityResult(i2, i3, intent);
        SafeIntent safeIntent = new SafeIntent(intent);
        if (i2 != 100 || safeIntent.getData() == null || (a2 = a(safeIntent, this.z)) == null) {
            return;
        }
        this.z.setMedias(a2);
        this.aq.c(a2);
        this.aa.b(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        boolean equals = "1".equals(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk(FaqConstants.FAQ_ISSELECTED));
        this.o = equals;
        this.w = !equals;
        setTitle(R.string._2130850845_res_0x7f02341d);
        TextView textView = (TextView) findViewById(R.id.description);
        textView.getPaint().setFakeBoldText(true);
        textView.setContentDescription(getString(R.string._2130850903_res_0x7f023457) + " " + getString(R.string._2130850918_res_0x7f023466));
        TextView textView2 = (TextView) findViewById(R.id.feedback_contact_view);
        textView2.getPaint().setFakeBoldText(true);
        textView2.setContentDescription(getString(R.string._2130850898_res_0x7f023452) + " " + getString(R.string._2130850918_res_0x7f023466));
        this.am = (LinearLayout) findViewById(R.id.ll_category);
        TextView textView3 = (TextView) findViewById(R.id.txt_style_title);
        this.aj = textView3;
        textView3.setContentDescription(getString(R.string._2130850905_res_0x7f023459) + " " + getString(R.string._2130850888_res_0x7f023448));
        this.an = (TextView) findViewById(R.id.txt_style);
        this.al = (TextView) findViewById(R.id.txt_style_2);
        this.ar = (RecyclerView) findViewById(R.id.grid_styles);
        this.ao = (EditText) findViewById(R.id.edit_desc);
        this.as = (TextView) findViewById(R.id.txt_number);
        this.i = (TextView) findViewById(R.id.tv_tryagain);
        this.ak = (TextView) findViewById(R.id.tv_progress);
        this.as.setText(String.format(getResources().getString(R.string._2130850899_res_0x7f023453), 0, 500));
        aa();
        ImageView imageView = (ImageView) findViewById(R.id.feedback_contact_red_star);
        boolean equalsIgnoreCase = "Y".equalsIgnoreCase(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk(FaqConstants.FAQ_CONTACT_NOT_NECESSARY));
        this.ac = equalsIgnoreCase;
        imageView.setVisibility(equalsIgnoreCase ? 8 : 0);
        this.ax = (EditText) findViewById(R.id.edit_contact);
        this.b = (RelativeLayout) findViewById(R.id.cbx_host);
        this.e = (CheckBox) findViewById(R.id.cbx_log);
        this.g = (TextView) findViewById(R.id.problem_suggest_tv_privacy);
        Button button = (Button) findViewById(R.id.btn_submit);
        this.h = button;
        com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.a(this, button);
        this.f = (LinearLayout) findViewById(R.id.layout_loading);
        this.j = (Button) findViewById(R.id.bg_dismiss);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);
        this.q = scrollView;
        scrollView.setOverScrollMode(0);
        this.au = (LinearLayout) findViewById(R.id.ll_phone_or_email);
        this.l = (FeedbackNoticeView) findViewById(R.id.feedback_problem_noticeView);
        ai();
        this.aj.getPaint().setFakeBoldText(true);
        this.an.getPaint().setFakeBoldText(true);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.aq.e(this);
        af();
        this.an.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.i.setOnClickListener(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        this.l.a(FeedbackNoticeView.c.PROGRESS);
        ab();
        if (!com.huawei.phoneservice.faq.base.util.b.b(this)) {
            a(FaqConstants.FaqErrorCode.INTERNET_ERROR);
            return;
        }
        try {
            this.ab = AsCache.get(this, AsCache.FEED_BACK_CACHE_FILE_NAME);
        } catch (IOException | RuntimeException e2) {
            com.huawei.phoneservice.faq.base.util.i.e("ProblemSuggestActivity_", e2.getMessage());
        }
        SafeIntent safeIntent = new SafeIntent(getIntent());
        d(safeIntent.hasExtra("problem_info") ? (ProblemInfo) safeIntent.getParcelableExtra("problem_info") : null);
        this.e.setChecked(this.o);
        boolean isChecked = this.e.isChecked();
        this.m = isChecked;
        this.z.setShowLog(isChecked);
        com.huawei.phoneservice.faq.base.util.i.c("ProblemSuggestActivity_", "   init data isChooseZip：" + this.m);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int[] k() {
        return new int[]{R.id.ll_category, R.id.txt_style, R.id.txt_style_2, R.id.grid_styles, R.id.ll_description, R.id.rl_description, R.id.ll_contact, R.id.problem_suggest_tv_privacy, R.id.edit_contact, R.id.cbx_log};
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void f() {
        x();
    }

    public void c(List<com.huawei.phoneservice.feedback.entity.c> list) {
        int size = list == null ? 0 : list.size();
        String[] strArr = new String[size];
        int i2 = -1;
        for (int i3 = 0; i3 < size; i3++) {
            strArr[i3] = list.get(i3).b;
            if (list.get(i3).c.equals(this.z.getParentId())) {
                i2 = i3;
            }
        }
        a(this, getString(R.string._2130850905_res_0x7f023459), getString(R.string._2130850853_res_0x7f023425), strArr, i2, new ac(list));
    }

    public void d(String str) {
        long filesSize = this.m ? this.z.getFilesSize() + this.z.getLogsSize() : this.z.getFilesSize();
        if (filesSize == 0) {
            filesSize = 1;
        }
        a(filesSize, new c(), new a());
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void e() {
        e(R.string._2130850856_res_0x7f023428);
        this.j.setVisibility(0);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void c(String str) {
        com.huawei.phoneservice.faq.base.util.n.a(this, str);
        Log.e("ProblemSuggestActivity_", "showToast...." + str);
        if (this.t) {
            this.h.setEnabled(true);
        }
        this.t = false;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.e
    public FeedbackBean c() {
        return this.z;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void b(List<com.huawei.phoneservice.feedback.entity.c> list) {
        AsCache asCache;
        t();
        this.q.setVisibility(0);
        this.l.setVisibility(8);
        boolean an = an();
        this.s = an;
        if (!an && (asCache = this.ab) != null && !this.v) {
            String asString = asCache.getAsString("feedBackCache");
            if (!TextUtils.isEmpty(asString)) {
                FeedbackBean feedbackBean = (FeedbackBean) new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create().fromJson(asString, FeedbackBean.class);
                this.z = feedbackBean;
                if (feedbackBean != null) {
                    this.y = true;
                    feedbackBean.setLogsSize(0L);
                } else {
                    FeedbackBean feedbackBean2 = new FeedbackBean();
                    this.z = feedbackBean2;
                    feedbackBean2.setShowLog(this.o);
                }
            }
        }
        d(list);
        aj();
        if (this.z.haveMedias()) {
            this.aa.b(this);
            this.aq.c(this.z.getMedias());
        }
        if (this.s) {
            h();
        } else if (this.y) {
            ak();
        }
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void b(int i2) {
        a(this.z, i2);
        this.z.remove(i2);
        this.aq.c(this.z.getMedias());
        this.p.setAdapter((ListAdapter) this.aq);
        this.aa.b(this);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public FeedbackInfo b() {
        return this.z.getInfo();
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(boolean z) {
        StringBuilder sb = new StringBuilder("isCompress()  ");
        sb.append(z);
        sb.append(" ");
        sb.append((this.ae || this.af) ? false : true);
        com.huawei.phoneservice.faq.base.util.i.d("ProblemSuggestActivity_", sb.toString());
        boolean z2 = !z;
        this.ai = z2;
        if (this.ae || this.af) {
            return;
        }
        this.h.setEnabled(z2);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(String str) {
        AlertDialog alertDialog = this.d;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d.dismiss();
        }
        if (TextUtils.isEmpty(this.z.getSrCode())) {
            c(getString(!TextUtils.isEmpty(this.z.getProblemId()) ? R.string._2130850852_res_0x7f023424 : R.string._2130850924_res_0x7f02346c));
        }
        String problemId = TextUtils.isEmpty(this.z.getProblemId()) ? str : this.z.getProblemId();
        this.z.setProblemId(str);
        SdkProblemListener sdkListener = com.huawei.phoneservice.feedbackcommon.utils.b.e().getSdkListener();
        this.h.setEnabled(false);
        this.j.setVisibility(0);
        this.j.postDelayed(new i(sdkListener, str, problemId), 2000L);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a(com.huawei.phoneservice.feedback.utils.a aVar) {
        int i2;
        this.af = false;
        this.k = (this.o && !this.n) || (this.r && this.m) || (this.t && this.m);
        StringBuilder sb = new StringBuilder("zipCompressFinished  ");
        sb.append(this.o);
        sb.append(" ");
        sb.append(!this.n);
        sb.append(" ");
        sb.append(this.r);
        sb.append(" ");
        sb.append(this.t);
        sb.append(" ");
        sb.append(this.m);
        sb.append(" ");
        sb.append(aVar.toString());
        com.huawei.phoneservice.faq.base.util.i.d("ProblemSuggestActivity_", sb.toString());
        if (aVar == com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_SUCCESS) {
            this.ai = true;
            this.ae = false;
            if (this.k) {
                i2 = R.string._2130850955_res_0x7f02348b;
                e(i2);
            }
        } else if (aVar == com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_FAILED) {
            this.ai = false;
            this.ae = true;
            if (this.k) {
                i2 = R.string._2130850953_res_0x7f023489;
                e(i2);
            }
        }
        new Handler().postDelayed(new l(aVar), 500L);
    }

    public void e(com.huawei.phoneservice.feedback.entity.c cVar) {
        List<com.huawei.phoneservice.feedback.entity.a> list;
        int size = (cVar == null || (list = cVar.f8256a) == null) ? 0 : list.size();
        String[] strArr = new String[size];
        int i2 = -1;
        for (int i3 = 0; i3 < size; i3++) {
            strArr[i3] = cVar.f8256a.get(i3).f8255a;
            if (cVar.f8256a.get(i3).d.equals(this.z.getChildId())) {
                i2 = i3;
            }
        }
        if (cVar == null) {
            return;
        }
        a(this, cVar.b, getString(R.string._2130850853_res_0x7f023425), strArr, i2, new b(cVar));
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.a
    public void a(FaqConstants.FaqErrorCode faqErrorCode) {
        FeedbackBean feedbackBean;
        t();
        if (faqErrorCode == FaqConstants.FaqErrorCode.INTERNET_ERROR) {
            this.l.c(faqErrorCode);
            this.l.setVisibility(0);
            this.l.setEnabled(true);
            return;
        }
        this.x = true;
        this.l.setVisibility(8);
        this.am.setVisibility(8);
        if (this.ah == 0 && (feedbackBean = this.z) != null) {
            feedbackBean.setProblemType("1004003", null);
        }
        FeedbackBean feedbackBean2 = this.z;
        if (feedbackBean2 != null && !TextUtils.isEmpty(feedbackBean2.getProblemId())) {
            this.ag = false;
            invalidateOptionsMenu();
        } else if (!com.huawei.phoneservice.faq.base.util.l.e(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken")) && ModuleConfigUtils.feedbackHistoryEnabled()) {
            this.ag = true;
            invalidateOptionsMenu();
            if (this.ad <= 0) {
                ad();
            }
        }
        if (ModuleConfigUtils.feedbackContactEnabled()) {
            this.au.setVisibility(0);
        }
        this.ao.setText(this.z.getProblemDesc());
        this.ax.setText(this.z.getContact());
        aj();
    }

    @Override // com.huawei.phoneservice.feedback.adapter.ProblemSuggestPhotoAdapter.b
    public void a(int i2) {
        FeedbackBean feedbackBean = this.z;
        if (feedbackBean != null) {
            a(feedbackBean.getMediaItem(i2));
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.b
    public void a() {
        String srCode = this.z.getSrCode();
        AsCache asCache = this.ab;
        if (asCache != null) {
            asCache.remove(TextUtils.isEmpty(srCode) ? "lastSubmit" : "lastSubmit_srCode");
        }
        this.f.setVisibility(8);
        this.j.setVisibility(8);
        ag();
    }

    public void g() {
        a(getString(R.string._2130850932_res_0x7f023474), null, null, new f(), new g());
        this.aa.f();
    }

    public void h() {
        a(getString(R.string._2130850928_res_0x7f023470), getString(R.string._2130850848_res_0x7f023420), getString(R.string._2130850853_res_0x7f023425), new e(), new j());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public com.huawei.phoneservice.feedback.mvp.presenter.a w() {
        com.huawei.phoneservice.feedback.mvp.presenter.a aVar = new com.huawei.phoneservice.feedback.mvp.presenter.a(this, this, this);
        this.aa = aVar;
        return aVar;
    }

    private void ar() {
        this.j.setVisibility(0);
        if (this.af && this.m) {
            this.t = true;
            e(R.string._2130850954_res_0x7f02348a);
            return;
        }
        if (this.ae && this.m) {
            this.j.setVisibility(8);
            this.t = false;
            c(getResources().getString(R.string._2130850953_res_0x7f023489));
            this.i.setVisibility(0);
            this.h.setEnabled(false);
            return;
        }
        if (!NetworkUtils.isNetworkConnected(this)) {
            this.j.setVisibility(8);
            c(getResources().getString(R.string._2130850891_res_0x7f02344b));
        } else if ((!this.z.haveMedias() && (0 == this.z.getLogsSize() || !this.m)) || NetworkUtils.isWifiConnected(this)) {
            ac();
        } else {
            this.j.setVisibility(8);
            d("wifi");
        }
    }

    private boolean as() {
        FeedbackBean feedbackBean;
        return (this.ah != 0 || (feedbackBean = this.z) == null || ((this.x || TextUtils.isEmpty(feedbackBean.getParentId())) && TextUtils.isEmpty(this.z.getProblemDesc()) && !this.z.haveMedias())) ? false : true;
    }

    private void h(List<com.huawei.phoneservice.feedback.entity.c> list) {
        TextView textView;
        String str;
        List<com.huawei.phoneservice.feedback.entity.a> list2;
        this.ar.setVisibility(8);
        this.an.setVisibility(0);
        this.an.setEnabled(true);
        if (TextUtils.isEmpty(this.z.getProblemType()) || list == null) {
            return;
        }
        for (com.huawei.phoneservice.feedback.entity.c cVar : list) {
            String str2 = cVar.c;
            if (str2 != null && str2.equals(this.z.getParentId())) {
                if (!TextUtils.isEmpty(this.z.getChildId()) && (list2 = cVar.f8256a) != null && !list2.isEmpty()) {
                    for (com.huawei.phoneservice.feedback.entity.a aVar : cVar.f8256a) {
                        String str3 = aVar.d;
                        if (str3 != null && str3.equals(this.z.getChildId())) {
                            textView = this.an;
                            str = aVar.f8255a;
                        }
                    }
                    return;
                }
                textView = this.an;
                str = cVar.b;
                textView.setText(str);
                return;
            }
        }
    }

    private void j(List<com.huawei.phoneservice.feedback.entity.c> list) {
        this.ar.setVisibility(0);
        this.an.setVisibility(8);
        this.ar.setLayoutManager(new AutoLineLayoutManager());
        com.huawei.phoneservice.feedback.adapter.f fVar = new com.huawei.phoneservice.feedback.adapter.f(list, this);
        this.ap = fVar;
        fVar.a(new m(list));
        this.ar.setAdapter(this.ap);
        if (TextUtils.isEmpty(this.z.getProblemType())) {
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            com.huawei.phoneservice.feedback.entity.c cVar = list.get(i2);
            if (cVar != null && cVar.c.equals(this.z.getParentId())) {
                this.ap.e(i2);
                this.an.setText(cVar.b);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        Intent intent = new Intent(this, (Class<?>) FeedbackSubmitSuccessActivity.class);
        intent.putExtra("problemId", str);
        startActivity(intent);
        AsCache asCache = this.ab;
        if (asCache != null) {
            asCache.remove("feedBackCache");
        }
        finish();
    }

    private void d(List<com.huawei.phoneservice.feedback.entity.c> list) {
        if (TextUtils.isEmpty(this.z.getSrCode())) {
            this.aj.setVisibility(0);
            this.al.setVisibility(8);
            if (a(list)) {
                if (this.ah == 1) {
                    e(list);
                }
                h(list);
            } else {
                j(list);
            }
            if (!TextUtils.isEmpty(this.z.getProblemId())) {
                this.ag = false;
                invalidateOptionsMenu();
            } else if (!com.huawei.phoneservice.faq.base.util.l.e(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken")) && ModuleConfigUtils.feedbackHistoryEnabled()) {
                this.ag = true;
                invalidateOptionsMenu();
                if (this.ad <= 0) {
                    ad();
                }
            }
            this.au.setVisibility(8);
            if (ModuleConfigUtils.feedbackContactEnabled()) {
                this.au.setVisibility(0);
            }
        } else {
            this.an.setVisibility(8);
            if (TextUtils.isEmpty(this.z.getProblemName())) {
                this.aj.setVisibility(8);
                this.al.setVisibility(8);
                this.ar.setVisibility(8);
            } else {
                this.aj.setVisibility(0);
                this.al.setVisibility(0);
                this.ar.setVisibility(8);
                this.al.setText(this.z.getProblemName());
            }
            this.ag = false;
            invalidateOptionsMenu();
            this.au.setVisibility(8);
        }
        this.ao.setText(this.z.getProblemDesc());
        this.ao.setSelection(this.z.getProblemDesc().length());
        this.aq.c(this.z.getMedias());
        this.ax.setText(this.z.getContact());
        this.e.setChecked(this.z.getShowLog());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.queryNotice(this, str, new x(QueryNoticeResponse.class, this));
    }

    private boolean a(List<com.huawei.phoneservice.feedback.entity.c> list) {
        for (com.huawei.phoneservice.feedback.entity.c cVar : list) {
            if (cVar != null && !com.huawei.phoneservice.faq.base.util.b.b(cVar.f8256a) && cVar.f8256a.size() > 0) {
                return true;
            }
        }
        return false;
    }

    private void e(int i2) {
        this.ak.setVisibility(0);
        this.ak.setText(i2);
        this.f.setVisibility(0);
        this.h.setEnabled(false);
    }

    private void e(List<com.huawei.phoneservice.feedback.entity.c> list) {
        List<com.huawei.phoneservice.feedback.entity.a> list2;
        for (com.huawei.phoneservice.feedback.entity.c cVar : list) {
            if (!TextUtils.isEmpty(this.z.getChildId()) && (list2 = cVar.f8256a) != null && !list2.isEmpty()) {
                Iterator<com.huawei.phoneservice.feedback.entity.a> it = cVar.f8256a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String str = it.next().d;
                    if (str != null && str.equals(this.z.getChildId())) {
                        this.z.setParentId(cVar.c);
                        break;
                    }
                }
            }
        }
    }

    private void b(View view, boolean z) {
        String obj;
        EditText editText = (EditText) view;
        if (editText == null) {
            return;
        }
        if (z) {
            CharSequence hint = editText.getHint();
            if (hint != null) {
                editText.setTag(hint.toString());
            }
            obj = "";
        } else {
            Object tag = editText.getTag();
            if (tag == null) {
                return;
            } else {
                obj = tag.toString();
            }
        }
        editText.setHint(obj);
    }

    private void d(ProblemInfo problemInfo) {
        if (problemInfo != null && problemInfo.isInteract()) {
            this.ah = 2;
            this.z = new FeedbackBean(problemInfo.getSrCode(), problemInfo.getProblemId(), problemInfo.getProblemType(), problemInfo.getProblemType(), problemInfo.getProblemName());
            b(this.aa.e());
            return;
        }
        if (problemInfo == null || !problemInfo.isDetail()) {
            this.ad = problemInfo == null ? 0 : problemInfo.getUnread();
            this.ah = 0;
            if (this.z == null) {
                this.z = new FeedbackBean();
            }
        } else {
            this.ah = 1;
            if (this.z == null) {
                this.z = new FeedbackBean(problemInfo.getProblemId(), problemInfo.getProblemType(), problemInfo.getProblemType(), problemInfo.getContact());
            }
        }
        this.aa.d(this, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ap() {
        String string;
        Resources resources;
        int i2;
        FeedbackBean feedbackBean = this.z;
        if (feedbackBean == null) {
            return;
        }
        if (!TextUtils.isEmpty(feedbackBean.getSrCode()) || !TextUtils.isEmpty(this.z.getProblemType())) {
            if (TextUtils.isEmpty(this.z.getProblemDesc()) || this.z.getProblemDesc().trim().length() < this.u) {
                string = getString(R.string._2130850879_res_0x7f02343f, new Object[]{Integer.valueOf(this.u)});
                c(string);
            }
            if (this.au.getVisibility() == 0) {
                String contact = this.z.getContact();
                if (this.ac || !TextUtils.isEmpty(contact)) {
                    boolean isEmail = FaqRegexMatches.isEmail(contact);
                    boolean isMobile = FaqRegexMatches.isMobile(contact);
                    String string2 = getResources().getString(R.string._2130850901_res_0x7f023455);
                    if (this.ac) {
                        if (!TextUtils.isEmpty(contact) && !isEmail && !isMobile) {
                            c(string2);
                            return;
                        }
                    } else if (!isEmail && !isMobile) {
                        c(string2);
                        return;
                    }
                } else {
                    resources = getResources();
                    i2 = R.string._2130850900_res_0x7f023454;
                }
            }
            ar();
            return;
        }
        resources = getResources();
        i2 = R.string._2130850897_res_0x7f023451;
        string = resources.getString(i2);
        c(string);
    }

    private void aj() {
        if (ModuleConfigUtils.feedbackLogEnabled()) {
            if (!TextUtils.isEmpty(this.z.getSrCode()) || TextUtils.isEmpty(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID))) {
                this.b.setVisibility(8);
                return;
            }
            this.b.setVisibility(0);
            if (0 == this.z.getLogsSize()) {
                ae();
            }
        }
    }

    private void ak() {
        a(getString(R.string._2130850927_res_0x7f02346f), getString(R.string._2130850865_res_0x7f023431), getString(R.string._2130850859_res_0x7f02342b), new p(), new s());
    }

    private boolean an() {
        if (this.ab == null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.z.getSrCode())) {
            String asString = this.ab.getAsString("lastSubmit_srCode");
            if (!TextUtils.isEmpty(asString)) {
                FeedbackBean feedbackBean = (FeedbackBean) new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create().fromJson(asString, FeedbackBean.class);
                if (this.z.getSrCode().equals(feedbackBean.getSrCode())) {
                    this.z = feedbackBean;
                    return true;
                }
            }
        } else if (TextUtils.isEmpty(this.z.getProblemId())) {
            String asString2 = this.ab.getAsString("lastSubmit");
            if (!TextUtils.isEmpty(asString2)) {
                this.z = (FeedbackBean) new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create().fromJson(asString2, FeedbackBean.class);
                return true;
            }
        }
        return false;
    }

    private void al() {
        a(getString(R.string._2130850930_res_0x7f023472), getString(R.string._2130850865_res_0x7f023431), getString(R.string._2130850859_res_0x7f02342b), new r(), new q());
    }

    private void am() {
        this.ax.setOnTouchListener(new d(null));
        try {
            int parseInt = Integer.parseInt(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_MIN_DESC_INPUT));
            this.u = parseInt;
            if (parseInt < 1 || parseInt > 10) {
                this.u = 10;
            }
        } catch (NumberFormatException unused) {
            this.u = 10;
        }
        EditText editText = this.ao;
        Resources resources = getResources();
        int i2 = this.u;
        editText.setHint(resources.getQuantityString(R.plurals._2130903530_res_0x7f0301ea, i2, Integer.valueOf(i2)));
        this.ao.setOnTouchListener(new v());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(this)));
        boolean z = (this.o && !this.n) || this.r || this.t;
        this.k = z;
        if (z) {
            e(R.string._2130850954_res_0x7f02348a);
        }
        new Handler().postDelayed(new o(), 500L);
    }

    private void ag() {
        this.h.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        if (!this.w) {
            this.h.setEnabled(this.ai);
        } else {
            this.h.setEnabled(true);
            this.w = false;
        }
    }

    private void ai() {
        CharInputFilter charInputFilter = new CharInputFilter("[&<>\"'()\"\\n\" ]");
        EmojiFilter emojiFilter = new EmojiFilter();
        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(500);
        InputFilter.LengthFilter lengthFilter2 = new InputFilter.LengthFilter(100);
        this.ao.setFilters(new InputFilter[]{lengthFilter, new com.huawei.phoneservice.faq.base.util.c()});
        this.ax.setFilters(new InputFilter[]{charInputFilter, emojiFilter, lengthFilter2});
        this.ao.setOnFocusChangeListener(this);
        this.ax.setOnFocusChangeListener(this);
        this.e.setOnCheckedChangeListener(new k());
        findViewById(R.id.cbx_text).setOnClickListener(new t());
        am();
    }

    private void af() {
        this.ao.addTextChangedListener(new u());
        this.ax.addTextChangedListener(new y());
    }

    private void aa() {
        GridView gridView;
        int i2;
        this.p = (GridView) findViewById(R.id.list_media);
        ProblemSuggestPhotoAdapter problemSuggestPhotoAdapter = new ProblemSuggestPhotoAdapter(this);
        this.aq = problemSuggestPhotoAdapter;
        this.p.setAdapter((ListAdapter) problemSuggestPhotoAdapter);
        this.p.setNumColumns(v());
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_HIDE_ADD_ATTACHMENT);
        if (sdk == null || !"Y".equals(sdk.toUpperCase(Locale.ROOT))) {
            gridView = this.p;
            i2 = 0;
        } else {
            gridView = this.p;
            i2 = 8;
        }
        gridView.setVisibility(i2);
    }

    private void ad() {
        com.huawei.phoneservice.feedbackcommon.utils.b.e().getUnread(this, "", new h());
    }

    private void ab() {
        if (!ModuleConfigUtils.feedbackLogEnabled() || !ModuleConfigUtils.feedbackNoticeEnabled()) {
            this.g.setVisibility(8);
            return;
        }
        String lowerCase = Locale.getDefault().getLanguage().toLowerCase(Locale.ENGLISH);
        if (MLAsrConstants.LAN_ZH.equals(lowerCase)) {
            lowerCase = Locale.getDefault().toLanguageTag().contains("zh-Hant") ? "zh-tw" : "zh-cn";
        }
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.queryIsoLanguage(this, lowerCase, new w(QueryIsoLanguageResponse.class, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        this.z.setParentId("");
        this.z.setChildId("");
        this.z.setProblemDesc("");
        a(this.z);
        List<MediaItem> medias = this.z.getMedias();
        if (medias != null) {
            medias.clear();
        }
        this.aa.c();
        this.z.setContact("");
        this.z.setShowLog(this.o);
        com.huawei.phoneservice.feedback.adapter.f fVar = this.ap;
        if (fVar != null) {
            fVar.e(-1);
        }
        this.an.setText("");
        this.ao.setText(this.z.getProblemDesc());
        this.aq.c(this.z.getMedias());
        this.ax.setText(this.z.getContact());
        this.e.setChecked(this.z.getShowLog());
        AlertDialog alertDialog = this.d;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        this.t = true;
        Gson create = this.z.haveMedias() ? new GsonBuilder().registerTypeAdapter(Uri.class, new UriSerializer()).create() : new Gson();
        this.z.setProblemName((this.ah == 2 ? this.al : this.an).getText().toString());
        String srCode = this.z.getSrCode();
        AsCache asCache = this.ab;
        if (asCache != null) {
            asCache.put(TextUtils.isEmpty(srCode) ? "lastSubmit" : "lastSubmit_srCode", create.toJson(this.z), 172800);
        }
        com.huawei.phoneservice.faq.base.util.j.e().onClick(getClass().getName(), "Submit", this.z);
        this.aa.b(0, 0);
        this.aa.c(this, this.m);
    }

    class h implements OnReadListener {
        @Override // com.huawei.phoneservice.feedbackcommon.utils.OnReadListener
        public void read(Throwable th, String str) {
        }

        @Override // com.huawei.phoneservice.feedbackcommon.utils.OnReadListener
        public void unread(Throwable th, String str, int i) {
            ProblemSuggestActivity.this.ad = i;
            ProblemSuggestActivity.this.invalidateOptionsMenu();
        }

        h() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        FeedbackBean feedbackBean;
        AlertDialog alertDialog = this.d;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        a(this.z);
        String srCode = this.z.getSrCode();
        AsCache asCache = this.ab;
        if (asCache != null) {
            asCache.remove(TextUtils.isEmpty(srCode) ? "lastSubmit" : "lastSubmit_srCode");
        }
        if (this.b.getVisibility() == 0) {
            this.e.setChecked(this.z.getShowLog());
        }
        if (TextUtils.isEmpty(this.z.getSrCode())) {
            com.huawei.phoneservice.feedback.adapter.f fVar = this.ap;
            if (fVar != null) {
                fVar.e(-1);
            }
            this.an.setText("");
            feedbackBean = new FeedbackBean();
        } else {
            String problemId = this.z.getProblemId();
            String problemType = this.z.getProblemType();
            feedbackBean = new FeedbackBean(srCode, problemId, problemType, problemType, this.z.getProblemName());
        }
        this.z = feedbackBean;
        this.aa.c();
        this.z.setShowLog(this.o);
        this.e.setChecked(this.o);
        com.huawei.phoneservice.faq.base.util.i.d("ProblemSuggestActivity_", "break cancel " + this.z.getLogsSize() + " " + this.z.getShowLog() + " " + this.m);
        d(this.aa.e());
        this.s = false;
        if (this.z.getShowLog()) {
            ae();
        }
    }

    class a implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AlertDialog alertDialog = ProblemSuggestActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        a() {
        }
    }

    class ac implements DialogInterface.OnClickListener {
        final /* synthetic */ List d;

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            List list = this.d;
            if (list == null || i < 0 || i >= list.size()) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                return;
            }
            com.huawei.phoneservice.feedback.entity.c cVar = (com.huawei.phoneservice.feedback.entity.c) this.d.get(i);
            boolean equals = cVar.c.equals(ProblemSuggestActivity.this.z.getParentId());
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
            List<com.huawei.phoneservice.feedback.entity.a> list2 = cVar.f8256a;
            if (list2 != null && list2.size() > 0) {
                ProblemSuggestActivity.this.e(cVar);
            } else if (!equals) {
                ProblemSuggestActivity.this.z.setProblemType(cVar.c, null);
                ProblemSuggestActivity.this.an.setText(cVar.b);
            } else {
                ProblemSuggestActivity.this.z.setProblemType(null, null);
                ProblemSuggestActivity.this.an.setText("");
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        ac(List list) {
            this.d = list;
        }
    }

    class ad implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (!ProblemSuggestActivity.this.aa.d() || ProblemSuggestActivity.this.j.getVisibility() == 0) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ProblemSuggestActivity.this.ad = 0;
            ProblemSuggestActivity.this.invalidateOptionsMenu();
            ProblemSuggestActivity.this.startActivity(new Intent(ProblemSuggestActivity.this, (Class<?>) FeedListActivity.class));
            com.huawei.phoneservice.faq.base.util.j.e().onClick(ProblemSuggestActivity.this.getClass().getName(), "Click", ProblemSuggestActivity.this.z);
            ViewClickInstrumentation.clickOnView(view);
        }

        ad() {
        }
    }

    class b implements DialogInterface.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.phoneservice.feedback.entity.c f8286a;

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            com.huawei.phoneservice.feedback.entity.a aVar = this.f8286a.f8256a.get(i);
            boolean equals = aVar.d.equals(ProblemSuggestActivity.this.z.getChildId());
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
            if (!equals) {
                ProblemSuggestActivity.this.z.setProblemType(this.f8286a.c, aVar.d);
                ProblemSuggestActivity.this.an.setText(aVar.f8255a);
            } else {
                ProblemSuggestActivity.this.z.setProblemType(null, null);
                ProblemSuggestActivity.this.an.setText("");
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        b(com.huawei.phoneservice.feedback.entity.c cVar) {
            this.f8286a = cVar;
        }
    }

    class c implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProblemSuggestActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProblemSuggestActivity.this.ac();
            ViewClickInstrumentation.clickOnView(view);
        }

        c() {
        }
    }

    static class d implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }

        /* synthetic */ d(k kVar) {
            this();
        }

        private d() {
        }
    }

    class e implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProblemSuggestActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProblemSuggestActivity.this.t = true;
            com.huawei.phoneservice.faq.base.util.i.d("ProblemSuggestActivity_", "break submit " + ProblemSuggestActivity.this.z.getLogsSize() + " " + ProblemSuggestActivity.this.z.getShowLog() + " " + ProblemSuggestActivity.this.m);
            if (!ProblemSuggestActivity.this.z.getShowLog()) {
                ProblemSuggestActivity.this.ac();
            } else {
                ProblemSuggestActivity.this.j.setVisibility(0);
                ProblemSuggestActivity.this.ae();
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        e() {
        }
    }

    class f implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProblemSuggestActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProblemSuggestActivity.this.aa.h();
            com.huawei.phoneservice.faq.base.util.j.e().onClick(ProblemSuggestActivity.this.getClass().getName(), "Quit", ProblemSuggestActivity.this.z);
            ViewClickInstrumentation.clickOnView(view);
        }

        f() {
        }
    }

    class g implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProblemSuggestActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProblemSuggestActivity.this.aa.g();
            com.huawei.phoneservice.faq.base.util.j.e().onClick(ProblemSuggestActivity.this.getClass().getName(), "Cancel", ProblemSuggestActivity.this.z);
            ViewClickInstrumentation.clickOnView(view);
        }

        g() {
        }
    }

    class i implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8288a;
        final /* synthetic */ String b;
        final /* synthetic */ SdkProblemListener c;

        @Override // java.lang.Runnable
        public void run() {
            SdkProblemListener sdkProblemListener = this.c;
            if (sdkProblemListener != null) {
                sdkProblemListener.onSubmitResult(0, this.f8288a, this.b, ProblemSuggestActivity.this.z.getSrCode(), null);
            }
            if (com.huawei.phoneservice.faq.base.util.l.e(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken")) || ProblemSuggestActivity.this.ah != 0) {
                Intent intent = new Intent();
                intent.putExtra("parentProblemId", this.b);
                intent.putExtra("problemId", this.f8288a);
                intent.putExtra("srCode", ProblemSuggestActivity.this.z.getSrCode());
                ProblemSuggestActivity.this.setResult(-1, intent);
                if (ProblemSuggestActivity.this.ab != null) {
                    ProblemSuggestActivity.this.ab.remove("feedBackCache");
                }
                ProblemSuggestActivity.this.finish();
                return;
            }
            ProblemSuggestActivity.this.g(this.f8288a);
        }

        i(SdkProblemListener sdkProblemListener, String str, String str2) {
            this.c = sdkProblemListener;
            this.f8288a = str;
            this.b = str2;
        }
    }

    class j implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
            } else {
                ProblemSuggestActivity.this.y();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        j() {
        }
    }

    class k implements CompoundButton.OnCheckedChangeListener {
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ProblemSuggestActivity.this.m = z;
            boolean z2 = true;
            if (!z) {
                ProblemSuggestActivity.this.n = true;
                ProblemSuggestActivity.this.i.setVisibility(8);
                ProblemSuggestActivity.this.ak.setVisibility(8);
                ProblemSuggestActivity.this.f.setVisibility(8);
                ProblemSuggestActivity.this.ah();
                ProblemSuggestActivity.this.r = false;
            }
            ProblemSuggestActivity problemSuggestActivity = ProblemSuggestActivity.this;
            if ((!problemSuggestActivity.o || ProblemSuggestActivity.this.n) && !ProblemSuggestActivity.this.r && !ProblemSuggestActivity.this.t) {
                z2 = false;
            }
            problemSuggestActivity.k = z2;
            ProblemSuggestActivity.this.z.setShowLog(z);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }

        k() {
        }
    }

    class l implements Runnable {
        final /* synthetic */ com.huawei.phoneservice.feedback.utils.a d;

        @Override // java.lang.Runnable
        public void run() {
            ProblemSuggestActivity.this.f.setVisibility(8);
            boolean z = true;
            ProblemSuggestActivity.this.h.setEnabled(true);
            com.huawei.phoneservice.feedback.utils.a aVar = this.d;
            com.huawei.phoneservice.feedback.utils.a aVar2 = com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_SUCCESS;
            if ((aVar != aVar2 || !ProblemSuggestActivity.this.s) && (this.d != aVar2 || (!ProblemSuggestActivity.this.t && !ProblemSuggestActivity.this.r))) {
                z = false;
            }
            if (this.d != com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_FAILED) {
                if (z) {
                    ProblemSuggestActivity.this.ap();
                    return;
                }
                return;
            }
            ProblemSuggestActivity.this.j.setVisibility(8);
            if (ProblemSuggestActivity.this.m) {
                if (ProblemSuggestActivity.this.t || ((ProblemSuggestActivity.this.o && !ProblemSuggestActivity.this.n) || ProblemSuggestActivity.this.r)) {
                    ProblemSuggestActivity.this.h.setEnabled(false);
                    ProblemSuggestActivity.this.i.setVisibility(0);
                }
            }
        }

        l(com.huawei.phoneservice.feedback.utils.a aVar) {
            this.d = aVar;
        }
    }

    class m implements f.c {
        final /* synthetic */ List b;

        @Override // com.huawei.phoneservice.feedback.adapter.f.c
        public void a(int i) {
            if (i < 0) {
                ProblemSuggestActivity.this.z.setProblemType(null, null);
                ProblemSuggestActivity.this.an.setText("");
            } else {
                com.huawei.phoneservice.feedback.entity.c cVar = (com.huawei.phoneservice.feedback.entity.c) this.b.get(i);
                ProblemSuggestActivity.this.z.setProblemType(cVar.c, null);
                ProblemSuggestActivity.this.an.setText(cVar.b);
            }
        }

        m(List list) {
            this.b = list;
        }
    }

    class n implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            ProblemSuggestActivity.this.af = true;
            ProblemSuggestActivity.this.aa.i();
        }

        n() {
        }
    }

    class o implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            ProblemSuggestActivity.this.af = true;
            ProblemSuggestActivity.this.aa.i();
        }

        o() {
        }
    }

    class p implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = ProblemSuggestActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        p() {
        }
    }

    class q implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (ProblemSuggestActivity.this.ab != null) {
                ProblemSuggestActivity.this.ab.remove("feedBackCache");
            }
            ProblemSuggestActivity problemSuggestActivity = ProblemSuggestActivity.this;
            problemSuggestActivity.a(problemSuggestActivity.z);
            ProblemSuggestActivity.this.finish();
            ViewClickInstrumentation.clickOnView(view);
        }

        q() {
        }
    }

    class r implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ProblemSuggestActivity.this.o();
            ProblemSuggestActivity.this.finish();
            ViewClickInstrumentation.clickOnView(view);
        }

        r() {
        }
    }

    class s implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.huawei.phoneservice.faq.base.util.s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (ProblemSuggestActivity.this.z == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (ProblemSuggestActivity.this.ab != null) {
                ProblemSuggestActivity.this.ab.remove("feedBackCache");
            }
            ProblemSuggestActivity.this.z();
            ViewClickInstrumentation.clickOnView(view);
        }

        s() {
        }
    }

    class t implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProblemSuggestActivity.this.e.setChecked(!ProblemSuggestActivity.this.e.isChecked());
            ViewClickInstrumentation.clickOnView(view);
        }

        t() {
        }
    }

    class u implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (ProblemSuggestActivity.this.z != null) {
                String trim = ProblemSuggestActivity.this.ao.getText().toString().trim();
                ProblemSuggestActivity.this.z.setProblemDesc(trim);
                int length = trim.length();
                ProblemSuggestActivity.this.as.setTextColor(ContextCompat.getColor(ProblemSuggestActivity.this, length >= 500 ? R.color.feedback_sdk_problem_question_max_number : R.color.feedback_sdk_problem_question_number));
                ProblemSuggestActivity.this.as.setText(String.format(ProblemSuggestActivity.this.getResources().getString(R.string._2130850899_res_0x7f023453), Integer.valueOf(length), 500));
            }
        }

        u() {
        }
    }

    class v implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view.getId() == R.id.edit_desc && com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.cez_(ProblemSuggestActivity.this.ao)) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                if (motionEvent.getAction() == 1) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        }

        v() {
        }
    }

    class w extends FaqCallback<QueryIsoLanguageResponse> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, QueryIsoLanguageResponse queryIsoLanguageResponse) {
            if (th == null && queryIsoLanguageResponse != null) {
                String langCode = queryIsoLanguageResponse.getLangCode();
                if (!TextUtils.isEmpty(langCode)) {
                    ProblemSuggestActivity.this.e(langCode);
                    return;
                }
            }
            ProblemSuggestActivity.this.g.setVisibility(8);
        }

        w(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    class x extends FaqCallback<QueryNoticeResponse> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, QueryNoticeResponse queryNoticeResponse) {
            TextView textView;
            int i;
            if (th == null && queryNoticeResponse != null) {
                String content = queryNoticeResponse.getContent();
                if (!TextUtils.isEmpty(content)) {
                    ProblemSuggestActivity.this.g.setText(Html.fromHtml(content));
                    ClickUrlSpan.a(ProblemSuggestActivity.this.g);
                    textView = ProblemSuggestActivity.this.g;
                    i = 0;
                    textView.setVisibility(i);
                }
            }
            textView = ProblemSuggestActivity.this.g;
            i = 8;
            textView.setVisibility(i);
        }

        x(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    class y implements TextWatcher {
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
            if (ProblemSuggestActivity.this.z != null) {
                String trim = ProblemSuggestActivity.this.ax.getText().toString().trim();
                if (trim.length() >= 100) {
                    editText = ProblemSuggestActivity.this.ax;
                    i = R.drawable.feedback_sdk_problem_max_num_rectangle_bg;
                } else {
                    editText = ProblemSuggestActivity.this.ax;
                    i = R.drawable.feedback_sdk_problem_rectangle_bg;
                }
                editText.setBackgroundResource(i);
                ProblemSuggestActivity.this.z.setContact(trim);
            }
        }

        y() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        Gson create = new GsonBuilder().registerTypeAdapter(Uri.class, new UriSerializer()).create();
        FeedbackBean feedbackBean = this.z;
        if (feedbackBean != null) {
            feedbackBean.setContact("");
        }
        AsCache asCache = this.ab;
        if (asCache != null) {
            asCache.put(this, "feedBackCache", create.toJson(this.z));
        }
    }
}
