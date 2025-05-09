package com.huawei.phoneservice.feedback.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.b;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackNoMoreDrawable;
import com.huawei.phoneservice.feedback.mvp.contract.e;
import com.huawei.phoneservice.feedback.widget.FeedbackNoticeView;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.util.List;

/* loaded from: classes5.dex */
public class FeedListActivity extends FeedbackBaseActivity<com.huawei.phoneservice.feedback.mvp.contract.d> implements e {
    private ListView b;
    private com.huawei.phoneservice.feedback.mvp.presenter.d e;
    private FeedbackNoticeView f;
    private com.huawei.phoneservice.feedback.adapter.a g;
    private View i;
    private String m;
    private int n;
    private boolean h = false;
    private FeedbackNoMoreDrawable j = null;
    private boolean k = false;
    private AbsListView.OnScrollListener o = new a();
    private AdapterView.OnItemClickListener l = new d();

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_feedlist;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.huawei.phoneservice.feedback.mvp.contract.d w() {
        com.huawei.phoneservice.feedback.mvp.presenter.d dVar = new com.huawei.phoneservice.feedback.mvp.presenter.d(this);
        this.e = dVar;
        return dVar;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.e
    public void setThrowableView(Throwable th) {
        if (TextUtils.isEmpty(this.m)) {
            this.f.a(th);
            this.f.setEnabled(true);
        } else {
            this.b.removeFooterView(this.i);
            this.h = false;
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.e
    public void setErrorView(FaqConstants.FaqErrorCode faqErrorCode) {
        if (!TextUtils.isEmpty(this.m)) {
            this.b.removeFooterView(this.i);
            this.h = false;
            return;
        }
        if (FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR.equals(faqErrorCode)) {
            this.f.e(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, R.drawable.feedback_sdk_ic_no_search_result);
            this.f.c(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
            this.f.c(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, getResources().getDimensionPixelOffset(R.dimen._2131362784_res_0x7f0a03e0));
            this.f.setShouldHideContactUsButton(true);
            this.f.getNoticeTextView().setText(getResources().getString(R.string._2130850844_res_0x7f02341c));
        } else {
            this.f.c(faqErrorCode);
        }
        this.f.setEnabled(true);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("CacheMap", j.c().getMapOnSaveInstance());
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity, com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            j.c().saveMapOnSaveInstanceState(new SafeBundle(bundle).getString("CacheMap"));
        }
        super.onCreate(bundle);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        setTitle(getResources().getString(R.string._2130850913_res_0x7f023461));
        this.b = (ListView) findViewById(R.id.lv_feedlist);
        this.f = (FeedbackNoticeView) findViewById(R.id.noticeview_feedlist);
        com.huawei.phoneservice.feedback.adapter.a aVar = new com.huawei.phoneservice.feedback.adapter.a();
        this.g = aVar;
        this.b.setAdapter((ListAdapter) aVar);
        this.i = LayoutInflater.from(this).inflate(R.layout.feedback_sdk_list_footer_layout, (ViewGroup) null);
        this.j = new FeedbackNoMoreDrawable(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.b.setOnScrollListener(this.o);
        this.b.setOnItemClickListener(this.l);
        this.f.setOnClickListener(new c());
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        this.f.a(FeedbackNoticeView.c.PROGRESS);
        this.f.setEnabled(false);
        if (!b.b(this)) {
            setErrorView(FaqConstants.FaqErrorCode.INTERNET_ERROR);
            this.f.setEnabled(true);
            return;
        }
        this.m = null;
        this.h = false;
        try {
            this.n = Integer.valueOf(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk(FaqConstants.FEEDBACK_ODERTYPE)).intValue();
        } catch (NumberFormatException e) {
            this.n = 3;
            i.e("FeedListActivity", e.getMessage());
        }
        this.e.c(this);
        this.e.d(c());
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.e
    public void a(List<FeedBackResponse.ProblemEnity> list, List<FeedBackResponse.ProblemEnity> list2) {
        boolean z;
        this.f.setVisibility(8);
        if (TextUtils.isEmpty(this.m)) {
            this.g.c(list2);
        } else {
            this.g.e(list2);
        }
        if (list.size() >= 50) {
            this.m = list.get(49).getProblemId();
            z = true;
        } else {
            this.m = null;
            z = false;
        }
        this.h = z;
        if (this.b.getFooterViewsCount() > 0) {
            this.b.removeFooterView(this.i);
        }
        if (TextUtils.isEmpty(this.m)) {
            this.b.setOverscrollFooter(this.j);
        }
        this.g.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FeedBackRequest c() {
        if (!TextUtils.isEmpty(this.m)) {
            this.b.setOverscrollFooter(null);
            this.b.addFooterView(this.i);
            this.g.notifyDataSetChanged();
        }
        FeedBackRequest feedBackRequest = new FeedBackRequest();
        feedBackRequest.setAccessToken(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
        feedBackRequest.setProblemId(null);
        feedBackRequest.setStartWith(this.m);
        feedBackRequest.setPageSize(50);
        feedBackRequest.setProblemSourceCode(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("channel"));
        feedBackRequest.setOrderType(this.n);
        return feedBackRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i) {
        return i == 0 && this.g != null && this.b.getLastVisiblePosition() == this.g.getCount() - 1;
    }

    class a implements AbsListView.OnScrollListener {
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (FeedListActivity.this.a(i) && FeedListActivity.this.h) {
                FeedListActivity.this.h = false;
                FeedListActivity.this.e.d(FeedListActivity.this.c());
            }
        }

        a() {
        }
    }

    class c implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
            } else {
                FeedListActivity.this.l();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        c() {
        }
    }

    class d implements AdapterView.OnItemClickListener {
        /* JADX WARN: Type inference failed for: r7v2, types: [android.widget.Adapter] */
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            FeedBackResponse.ProblemEnity problemEnity = (FeedBackResponse.ProblemEnity) adapterView.getAdapter().getItem(i);
            if (problemEnity == null) {
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            problemEnity.setIsRead(true);
            FeedListActivity.this.g.notifyDataSetChanged();
            SafeIntent safeIntent = new SafeIntent(FeedListActivity.this.getIntent());
            FeedListActivity.this.k = safeIntent.getBooleanExtra("COME_FROM", false);
            Intent intent = new Intent(FeedListActivity.this, (Class<?>) FeedDetailsActivity.class);
            intent.putExtra("questionId", problemEnity.getProblemId());
            intent.putExtra("COME_FROM", FeedListActivity.this.k);
            FeedListActivity.this.startActivity(intent);
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }

        d() {
        }
    }
}
