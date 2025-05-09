package com.huawei.health.suggestion.ui.tabfragments.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.tabfragments.adapter.SocialActRecyclerAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import defpackage.bzs;
import defpackage.ceb;
import defpackage.gdy;
import defpackage.ixx;
import defpackage.jec;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class SocialActRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f3376a;
    private int b;
    private Context d;
    private Pair<Integer, Integer> e = BaseActivity.getSafeRegionWidth();
    private List<ceb> c = new ArrayList();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public SocialActRecyclerAdapter(Context context) {
        this.d = context;
        this.f3376a = LayoutInflater.from(context);
        this.b = nrr.b(this.d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this.f3376a.inflate(R.layout.configured_page_item_huawei_activity, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (!(viewHolder instanceof MyViewHolder) || koq.b(this.c, i)) {
            return;
        }
        ceb cebVar = this.c.get(i);
        if (cebVar == null) {
            LogUtil.a("Suggestion_SocialActRecyclerAdapte", "operationObject is null");
            return;
        }
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        a(cebVar, myViewHolder);
        if (list.isEmpty()) {
            h(cebVar, myViewHolder);
            e(cebVar, myViewHolder);
            b(cebVar, myViewHolder);
            c(cebVar, myViewHolder);
            d(cebVar, myViewHolder);
        }
    }

    private void h(final ceb cebVar, MyViewHolder myViewHolder) {
        myViewHolder.f3378a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.adapter.SocialActRecyclerAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("click", "1");
                hashMap.put("from", "4");
                hashMap.put("activityId", cebVar.c());
                ixx.d().d(SocialActRecyclerAdapter.this.d, AnalyticsValue.SUCCESSES_ACTIVITY_1100005.value(), hashMap, 0);
                GRSManager.a(SocialActRecyclerAdapter.this.d).e("domainContentcenterDbankcdnNew", new AnonymousClass1());
                ViewClickInstrumentation.clickOnView(view);
            }

            /* renamed from: com.huawei.health.suggestion.ui.tabfragments.adapter.SocialActRecyclerAdapter$5$1, reason: invalid class name */
            public class AnonymousClass1 implements GrsQueryCallback {
                AnonymousClass1() {
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(final String str) {
                    LogUtil.c("Suggestion_SocialActRecyclerAdapte", "GRSManager onCallBackSuccess ACTIVITY_KEY url = ", str);
                    if (!gdy.c(cebVar.i())) {
                        SocialActRecyclerAdapter.this.b();
                        return;
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    final ceb cebVar = cebVar;
                    handler.post(new Runnable() { // from class: gdv
                        @Override // java.lang.Runnable
                        public final void run() {
                            SocialActRecyclerAdapter.AnonymousClass5.AnonymousClass1.this.b(str, cebVar);
                        }
                    });
                }

                public /* synthetic */ void b(String str, ceb cebVar) {
                    SocialActRecyclerAdapter.this.a(str, cebVar);
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.c("Suggestion_SocialActRecyclerAdapte", "GRSManager onCallBackFail ACTIVITY_KEY resultCode = ", Integer.valueOf(i));
                }
            }
        });
    }

    private void e(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("Suggestion_SocialActRecyclerAdapte", "setLayout() operationObject or holder is null.");
            return;
        }
        int a2 = a();
        RelativeLayout relativeLayout = myViewHolder.f3378a;
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = a2;
        layoutParams.height = (a2 * 9) / 21;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void b(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("Suggestion_SocialActRecyclerAdapte", "setImageUi() operationObject or holder is null.");
            return;
        }
        String d = cebVar.d();
        if (!TextUtils.isEmpty(d)) {
            int a2 = a();
            myViewHolder.c.setLayoutParams(new RelativeLayout.LayoutParams(a2, (a2 * 9) / 21));
            nrf.cIS_(myViewHolder.c, d, nrf.d, 0, 0);
            return;
        }
        LogUtil.h("Suggestion_SocialActRecyclerAdapte", "setImageUi() imageUrl is empty.");
    }

    private int a() {
        if (nsn.ag(this.d)) {
            return (((this.d.getResources().getDisplayMetrics().widthPixels - this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)) - this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)) - this.b) / 2;
        }
        int i = this.d.getResources().getDisplayMetrics().widthPixels;
        int dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        return (((i - dimensionPixelSize) - this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)) - ((Integer) this.e.first).intValue()) - ((Integer) this.e.second).intValue();
    }

    private void a(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("Suggestion_SocialActRecyclerAdapte", "setJoinNumberUi() operationObject or holder is null.");
            return;
        }
        String o = cebVar.o();
        if (!gdy.d(cebVar) && !TextUtils.isEmpty(o)) {
            int m = CommonUtil.m(BaseApplication.getContext(), o);
            myViewHolder.e.setVisibility(0);
            myViewHolder.e.setText(this.d.getResources().getString(R$string.IDS_activity_social_people_attended, UnitUtil.e(m, 1, 0)));
            return;
        }
        myViewHolder.e.setVisibility(8);
    }

    private void c(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("Suggestion_SocialActRecyclerAdapte", "setActivityNameUi() operationObject or holder is null.");
            return;
        }
        int y = cebVar.y();
        String b = cebVar.b();
        if (y != 0 || TextUtils.isEmpty(b)) {
            myViewHolder.i.setVisibility(8);
        } else {
            myViewHolder.i.setVisibility(0);
            myViewHolder.i.setText(b);
        }
    }

    private void d(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("Suggestion_SocialActRecyclerAdapte", "setDateAndStatusUi() operationObject or holder is null.");
            return;
        }
        String t = cebVar.t();
        String n = cebVar.n();
        String v = cebVar.v();
        if (cebVar.y() != 0 || TextUtils.isEmpty(t) || TextUtils.isEmpty(n)) {
            myViewHolder.b.setVisibility(8);
        } else {
            myViewHolder.b.setVisibility(0);
            myViewHolder.g.setText(jec.b(t, v));
            myViewHolder.d.setText(jec.b(n, v));
        }
        int b = gdy.b(SharedPreferenceManager.b(this.d, Integer.toString(10011), OperationInteractorsApi.OPERATION_ACTIVITY_CURRENT_TIME), t, n);
        if (b == 0) {
            myViewHolder.f.setVisibility(0);
            myViewHolder.f.setText(this.d.getResources().getString(R$string.IDS_activity_social_coming_soon));
            myViewHolder.f.setBackground(this.d.getResources().getDrawable(R$drawable.activity_status_in_coming_bg));
        } else {
            if (b == 1) {
                myViewHolder.f.setVisibility(0);
                myViewHolder.f.setText(this.d.getResources().getString(R$string.IDS_hwh_home_group_underway));
                myViewHolder.f.setBackground(this.d.getResources().getDrawable(R$drawable.activity_status_in_progress_bg));
                return;
            }
            myViewHolder.f.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private RelativeLayout f3378a;
        private LinearLayout b;
        private ImageView c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView f;
        private HealthTextView g;
        private HealthTextView i;

        public MyViewHolder(View view) {
            super(view);
            this.f3378a = (RelativeLayout) view.findViewById(R.id.activity_layout);
            this.c = (ImageView) view.findViewById(R.id.activity_img);
            this.f = (HealthTextView) view.findViewById(R.id.activity_status);
            this.i = (HealthTextView) view.findViewById(R.id.activity_title);
            this.b = (LinearLayout) view.findViewById(R.id.activity_duration);
            this.g = (HealthTextView) view.findViewById(R.id.activity_start_date);
            this.d = (HealthTextView) view.findViewById(R.id.activity_end_date);
            this.e = (HealthTextView) view.findViewById(R.id.activity_join_num);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ceb cebVar) {
        String b = gdy.b(str, cebVar);
        gdy.d(this.d, true);
        Bundle bundle = new Bundle();
        bundle.putString("url", b);
        bundle.putString("EXTRA_BI_ID", cebVar.c());
        bundle.putString("EXTRA_BI_NAME", cebVar.b());
        bundle.putString("EXTRA_BI_SOURCE", "ACT");
        bundle.putString("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        Intent createWebViewIntent = bzs.e().createWebViewIntent(this.d, bundle, null);
        if (createWebViewIntent != null) {
            try {
                this.d.startActivity(createWebViewIntent);
            } catch (ActivityNotFoundException e) {
                LogUtil.b("Suggestion_SocialActRecyclerAdapte", "ActivityNotFoundException", e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        new NoTitleCustomAlertDialog.Builder(this.d).e(this.d.getResources().getString(com.huawei.health.servicesui.R$string.IDS_hw_update_app_tips)).czB_(com.huawei.health.servicesui.R$string.IDS_user_permission_know, R.color._2131296970_res_0x7f0902ca, new View.OnClickListener() { // from class: gdt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }
}
