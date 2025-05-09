package com.huawei.phoneservice.feedback.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.VideoCallBack;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.n;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.adapter.FeedDetailAdapter;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity;
import com.huawei.phoneservice.feedback.mvp.base.FeedbackCITListView;
import com.huawei.phoneservice.feedback.mvp.contract.c;
import com.huawei.phoneservice.feedback.utils.SdkFeedbackProblemManager;
import com.huawei.phoneservice.feedback.widget.FeedbackNoticeView;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemInfo;
import com.huawei.phoneservice.feedbackcommon.photolibrary.MimeType;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import com.huawei.phoneservice.feedbackcommon.utils.NetworkUtils;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class FeedDetailsActivity extends FeedbackBaseActivity<com.huawei.phoneservice.feedback.mvp.contract.b> implements c, FeedDetailAdapter.f, View.OnClickListener {
    private FeedbackCITListView b;
    private Button e;
    private FeedbackNoticeView f;
    private String g;
    private FeedDetailAdapter h;
    private com.huawei.phoneservice.feedback.mvp.presenter.b i;
    private FeedBackResponse.ProblemEnity j;
    private boolean l;

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_feeddetails;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.c
    public void setThrowableView(Throwable th) {
        this.f.a(th);
        this.f.setEnabled(true);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.c
    public void setErrorView(FaqConstants.FaqErrorCode faqErrorCode) {
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
        a();
        if (com.huawei.phoneservice.faq.base.util.b.c()) {
            this.h.notifyDataSetChanged();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (s.cdx_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.e == view && this.j != null) {
            ProblemInfo problemInfo = new ProblemInfo(this.g, this.j.getProblemCatalogCode());
            problemInfo.setContact(this.j.getContact());
            if (this.l) {
                SdkFeedbackProblemManager.getManager().gotoProductSuggest(this, problemInfo, 1);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            SdkFeedbackProblemManager.getManager().gotoFeedback(this, problemInfo, 1);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            this.i.b(h());
        }
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        setTitle(getResources().getString(R.string._2130850909_res_0x7f02345d));
        this.b = (FeedbackCITListView) findViewById(R.id.lv_chatlist);
        this.e = (Button) findViewById(R.id.continuefeed_btn);
        this.f = (FeedbackNoticeView) findViewById(R.id.noticeview_feeddetail);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.f.setOnClickListener(new d());
        this.e.setOnClickListener(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        this.f.a(FeedbackNoticeView.c.PROGRESS);
        this.l = new SafeIntent(getIntent()).getBooleanExtra("COME_FROM", false);
        this.h = new FeedDetailAdapter(this, this.l);
        if (com.huawei.phoneservice.faq.base.util.b.b(this)) {
            c();
            a();
        } else {
            this.f.c(FaqConstants.FaqErrorCode.INTERNET_ERROR);
            this.f.setEnabled(true);
        }
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int[] k() {
        return new int[]{R.id.lv_chatlist};
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.c
    public void d() {
        if (this.j != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.j);
            this.h.a(arrayList);
        }
        this.f.setVisibility(8);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.c
    public void c(com.huawei.phoneservice.feedback.entity.d dVar) {
        dVar.b().setEnabled(true);
        dVar.a().setEnabled(true);
        n.a(this, getResources().getString(R.string._2130850843_res_0x7f02341b));
    }

    @Override // com.huawei.phoneservice.feedback.adapter.FeedDetailAdapter.f
    public void b(com.huawei.phoneservice.feedback.entity.d dVar) {
        a("1", true, dVar);
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.c
    public void a(boolean z, com.huawei.phoneservice.feedback.entity.d dVar) {
        TextView cdR_;
        Resources resources;
        int i;
        if (dVar.d() == 0) {
            try {
                this.h.a(0).setScore(dVar.c());
            } catch (Exception e2) {
                i.c("FeedDetailsActivity", e2.getMessage());
            }
        }
        dVar.cdS_().setVisibility(0);
        if (z) {
            dVar.a().setVisibility(8);
            dVar.b().setImageResource(R.drawable.feedback_sdk_ic_comment_useful_gray);
            cdR_ = dVar.cdR_();
            resources = getResources();
            i = R.string._2130850911_res_0x7f02345f;
        } else {
            dVar.b().setVisibility(8);
            dVar.a().setImageResource(R.drawable.feedback_sdk_ic_comment_useless_gray);
            cdR_ = dVar.cdR_();
            resources = getResources();
            i = R.string._2130850910_res_0x7f02345e;
        }
        cdR_.setText(resources.getString(i));
    }

    @Override // com.huawei.phoneservice.feedback.adapter.FeedDetailAdapter.f
    public void a(List<MediaItem> list, int i) {
        if (list == null || i < 0 || i >= list.size()) {
            return;
        }
        a(list.get(i));
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.c
    public void a(List<FeedBackResponse.ProblemEnity> list) {
        ArrayList arrayList = new ArrayList();
        this.f.setVisibility(8);
        FeedBackResponse.ProblemEnity problemEnity = this.j;
        if (problemEnity != null) {
            arrayList.add(problemEnity);
        }
        arrayList.addAll(list);
        this.h.a(arrayList);
    }

    @Override // com.huawei.phoneservice.feedback.adapter.FeedDetailAdapter.f
    public void a(String str, String str2, ImageView imageView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2, long j) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            n.a(this, getResources().getString(R.string._2130850891_res_0x7f02344b));
            imageView2.setVisibility(0);
        } else if (!MimeType.isVideoFromUrl(str) || NetworkUtils.isWifiConnected(this)) {
            this.i.b(str, ceB_(imageView, relativeLayout, relativeLayout2, imageView2), str2, com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
        } else {
            ceD_(str, str2, relativeLayout, relativeLayout2, imageView, imageView2, j);
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.contract.c
    public void a(FeedBackResponse.ProblemEnity problemEnity) {
        this.j = problemEnity;
        this.i.b(h());
    }

    @Override // com.huawei.phoneservice.feedback.adapter.FeedDetailAdapter.f
    public void a(com.huawei.phoneservice.feedback.entity.d dVar) {
        a("0", false, dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.phoneservice.feedback.mvp.base.FeedbackBaseActivity
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.huawei.phoneservice.feedback.mvp.contract.b w() {
        com.huawei.phoneservice.feedback.mvp.presenter.b bVar = new com.huawei.phoneservice.feedback.mvp.presenter.b(this);
        this.i = bVar;
        return bVar;
    }

    private FeedBackRequest h() {
        FeedBackRequest feedBackRequest = new FeedBackRequest();
        feedBackRequest.setAccessToken(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
        feedBackRequest.setProblemId(this.g);
        feedBackRequest.setStartWith(null);
        feedBackRequest.setPageSize(50);
        feedBackRequest.setProblemSourceCode(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("channel"));
        feedBackRequest.setOrderType(1);
        return feedBackRequest;
    }

    private void e() {
        SafeIntent safeIntent = new SafeIntent(getIntent());
        this.g = safeIntent.getStringExtra("questionId");
        this.l = safeIntent.getBooleanExtra("COME_FROM", false);
    }

    private void a(String str, boolean z, com.huawei.phoneservice.feedback.entity.d dVar) {
        if (!com.huawei.phoneservice.faq.base.util.b.b(this)) {
            n.a(this, getResources().getString(R.string._2130850892_res_0x7f02344c));
            return;
        }
        FeedBackRateRequest feedBackRateRequest = new FeedBackRateRequest();
        feedBackRateRequest.setAccessToken(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
        feedBackRateRequest.setProblemId(dVar.e());
        feedBackRateRequest.setScore(str);
        dVar.a().setEnabled(false);
        dVar.b().setEnabled(false);
        this.i.b(feedBackRateRequest, z, dVar);
    }

    private void ceD_(String str, String str2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, ImageView imageView2, long j) {
        a(j, new e(str, imageView, relativeLayout, relativeLayout2, imageView2, str2), new b(imageView2));
    }

    class a implements VideoCallBack {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RelativeLayout f8274a;
        final /* synthetic */ ImageView b;
        final /* synthetic */ RelativeLayout c;
        final /* synthetic */ ImageView e;

        class c implements Runnable {
            final /* synthetic */ File e;

            @Override // java.lang.Runnable
            public void run() {
                if (this.e != null) {
                    a.this.e.setVisibility(0);
                    Glide.with(FeedDetailsActivity.this.getApplicationContext()).load(this.e).into(a.this.e);
                } else {
                    a.this.b.setVisibility(0);
                    a.this.c.setVisibility(0);
                }
                a.this.f8274a.setVisibility(8);
            }

            c(File file) {
                this.e = file;
            }
        }

        @Override // com.huawei.phoneservice.faq.base.util.VideoCallBack
        public void setChangeImage(File file, boolean z) {
            FeedDetailsActivity.this.runOnUiThread(new c(file));
        }

        a(ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
            this.e = imageView;
            this.b = imageView2;
            this.c = relativeLayout;
            this.f8274a = relativeLayout2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VideoCallBack ceB_(ImageView imageView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2) {
        relativeLayout.setVisibility(8);
        relativeLayout2.setVisibility(0);
        return new a(imageView, imageView2, relativeLayout, relativeLayout2);
    }

    class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ImageView f8276a;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = FeedDetailsActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            this.f8276a.setVisibility(0);
            ViewClickInstrumentation.clickOnView(view);
        }

        b(ImageView imageView) {
            this.f8276a = imageView;
        }
    }

    class d implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
            } else {
                FeedDetailsActivity.this.l();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        d() {
        }
    }

    class e implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RelativeLayout f8277a;
        final /* synthetic */ ImageView b;
        final /* synthetic */ ImageView c;
        final /* synthetic */ RelativeLayout d;
        final /* synthetic */ String e;
        final /* synthetic */ String h;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            AlertDialog alertDialog = FeedDetailsActivity.this.d;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            FeedDetailsActivity.this.i.b(this.e, FeedDetailsActivity.this.ceB_(this.b, this.d, this.f8277a, this.c), this.h, com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
            ViewClickInstrumentation.clickOnView(view);
        }

        e(String str, ImageView imageView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2, String str2) {
            this.e = str;
            this.b = imageView;
            this.d = relativeLayout;
            this.f8277a = relativeLayout2;
            this.c = imageView2;
            this.h = str2;
        }
    }

    private void a() {
        if (this.e.getMeasuredWidth() < com.huawei.phoneservice.faq.base.util.b.d(this)) {
            com.huawei.phoneservice.feedback.photolibrary.internal.utils.e.a(this, this.e);
        }
    }

    private void c() {
        this.i.c(this);
        e();
        this.b.setLayoutManager(new LinearLayoutManager(this));
        this.h.c(this);
        this.b.setAdapter(this.h);
        this.i.c(h());
    }
}
