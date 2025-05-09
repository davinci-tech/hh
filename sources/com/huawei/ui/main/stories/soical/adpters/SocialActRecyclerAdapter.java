package com.huawei.ui.main.stories.soical.adpters;

import android.content.ActivityNotFoundException;
import android.content.Context;
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
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.soical.adpters.SocialActRecyclerAdapter;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import defpackage.ceb;
import defpackage.ixx;
import defpackage.jec;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SocialActRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context b;
    private LayoutInflater d;
    private int e;
    private List<ceb> j;
    private Pair<Integer, Integer> c = BaseActivity.getSafeRegionWidth();
    private final OperationWebActivityIntentBuilderApi i = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);

    /* renamed from: a, reason: collision with root package name */
    private final OperationInteractorsApi f10478a = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public SocialActRecyclerAdapter(Context context, List<ceb> list) {
        this.b = context;
        this.j = list;
        this.d = LayoutInflater.from(context);
        this.e = nrr.b(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this.d.inflate(R.layout.configured_page_item_huawei_activity, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (!(viewHolder instanceof MyViewHolder) || koq.b(this.j, i)) {
            return;
        }
        final ceb cebVar = this.j.get(i);
        if (cebVar == null) {
            LogUtil.a("SocialActRecyclerAdapte", "operationObject is null");
            return;
        }
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        b(cebVar, myViewHolder);
        if (list.isEmpty()) {
            myViewHolder.f10479a.setOnClickListener(new View.OnClickListener() { // from class: rxc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SocialActRecyclerAdapter.this.dSS_(cebVar, view);
                }
            });
            a(cebVar, myViewHolder);
            d(cebVar, myViewHolder);
            e(cebVar, myViewHolder);
            c(cebVar, myViewHolder);
        }
    }

    public /* synthetic */ void dSS_(ceb cebVar, View view) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("from", "4");
        hashMap.put("activityId", cebVar.c());
        ixx.d().d(this.b, AnalyticsValue.SUCCESSES_ACTIVITY_1100005.value(), hashMap, 0);
        GRSManager.a(this.b).e("domainContentcenterDbankcdnNew", new AnonymousClass4(cebVar));
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: com.huawei.ui.main.stories.soical.adpters.SocialActRecyclerAdapter$4, reason: invalid class name */
    public class AnonymousClass4 implements GrsQueryCallback {
        final /* synthetic */ ceb c;

        AnonymousClass4(ceb cebVar) {
            this.c = cebVar;
        }

        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
        public void onCallBackSuccess(final String str) {
            LogUtil.c("SocialActRecyclerAdapte", "GRSManager onCallBackSuccess ACTIVITY_KEY url = " + str);
            if (!SocialActRecyclerAdapter.this.f10478a.judgeVersionSupport(this.c.i())) {
                SocialActRecyclerAdapter.this.c();
                return;
            }
            Handler handler = new Handler(Looper.getMainLooper());
            final ceb cebVar = this.c;
            handler.post(new Runnable() { // from class: rxi
                @Override // java.lang.Runnable
                public final void run() {
                    SocialActRecyclerAdapter.AnonymousClass4.this.d(str, cebVar);
                }
            });
        }

        public /* synthetic */ void d(String str, ceb cebVar) {
            SocialActRecyclerAdapter.this.e(str, cebVar);
        }

        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
        public void onCallBackFail(int i) {
            LogUtil.c("SocialActRecyclerAdapte", "GRSManager onCallBackFail ACTIVITY_KEY code = ", Integer.valueOf(i));
        }
    }

    private void a(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("SocialActRecyclerAdapte", "setLayout() operationObject or holder is null.");
            return;
        }
        int e = e();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) myViewHolder.f10479a.getLayoutParams();
        layoutParams.width = e;
        layoutParams.height = (e * 9) / 21;
        myViewHolder.f10479a.setLayoutParams(layoutParams);
    }

    private void d(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("SocialActRecyclerAdapte", "setImageUi() operationObject or holder is null.");
            return;
        }
        String d = cebVar.d();
        if (!TextUtils.isEmpty(d)) {
            int e = e();
            myViewHolder.c.setLayoutParams(new RelativeLayout.LayoutParams(e, (e * 9) / 21));
            nrf.cIS_(myViewHolder.c, d, (int) this.b.getResources().getDimension(R.dimen._2131362006_res_0x7f0a00d6), 0, 0);
            return;
        }
        LogUtil.h("SocialActRecyclerAdapte", "setImageUi() imageUrl is empty.");
    }

    private int e() {
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        if (nsn.ag(this.b)) {
            return (((this.b.getResources().getDisplayMetrics().widthPixels - dimensionPixelSize) - dimensionPixelSize2) - this.e) / 2;
        }
        return (((this.b.getResources().getDisplayMetrics().widthPixels - dimensionPixelSize) - dimensionPixelSize2) - ((Integer) this.c.first).intValue()) - ((Integer) this.c.second).intValue();
    }

    private void b(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("SocialActRecyclerAdapte", "setJoinNumberUi() operationObject or holder is null.");
            return;
        }
        String o = cebVar.o();
        if (!this.f10478a.isHuaweiNoTemplateActivity(cebVar) && !TextUtils.isEmpty(o)) {
            int m = CommonUtil.m(BaseApplication.getContext(), o);
            myViewHolder.d.setVisibility(0);
            myViewHolder.d.setText(this.b.getResources().getString(R$string.IDS_activity_social_people_attended, UnitUtil.e(m, 1, 0)));
            return;
        }
        myViewHolder.d.setVisibility(8);
    }

    private void e(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("SocialActRecyclerAdapte", "setActivityNameUi() operationObject or holder is null.");
            return;
        }
        int y = cebVar.y();
        String b = cebVar.b();
        if (y != 0 || TextUtils.isEmpty(b)) {
            myViewHolder.h.setVisibility(8);
        } else {
            myViewHolder.h.setVisibility(0);
            myViewHolder.h.setText(b);
        }
    }

    private void c(ceb cebVar, MyViewHolder myViewHolder) {
        if (cebVar == null || myViewHolder == null) {
            LogUtil.h("SocialActRecyclerAdapte", "setDateAndStatusUi() operationObject or holder is null.");
            return;
        }
        String t = cebVar.t();
        String n = cebVar.n();
        String v = cebVar.v();
        if (cebVar.y() != 0 || TextUtils.isEmpty(t) || TextUtils.isEmpty(n)) {
            myViewHolder.b.setVisibility(8);
        } else {
            myViewHolder.b.setVisibility(0);
            myViewHolder.j.setText(jec.b(t, v));
            myViewHolder.e.setText(jec.b(n, v));
        }
        int activityStatus = this.f10478a.getActivityStatus(SharedPreferenceManager.b(this.b, Integer.toString(10011), OperationInteractorsApi.OPERATION_ACTIVITY_CURRENT_TIME), t, n);
        if (activityStatus == 0) {
            myViewHolder.g.setVisibility(0);
            myViewHolder.g.setText(this.b.getResources().getString(R$string.IDS_activity_social_coming_soon));
            myViewHolder.g.setBackground(this.b.getResources().getDrawable(R.drawable._2131427525_res_0x7f0b00c5));
        } else {
            if (activityStatus == 1) {
                myViewHolder.g.setVisibility(0);
                myViewHolder.g.setText(this.b.getResources().getString(R$string.IDS_hwh_home_group_underway));
                myViewHolder.g.setBackground(this.b.getResources().getDrawable(R.drawable._2131427526_res_0x7f0b00c6));
                return;
            }
            myViewHolder.g.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.j.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private RelativeLayout f10479a;
        private LinearLayout b;
        private ImageView c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView g;
        private HealthTextView h;
        private HealthTextView j;

        public MyViewHolder(View view) {
            super(view);
            this.f10479a = (RelativeLayout) view.findViewById(R.id.activity_layout);
            this.c = (ImageView) view.findViewById(R.id.activity_img);
            this.g = (HealthTextView) view.findViewById(R.id.activity_status);
            this.h = (HealthTextView) view.findViewById(R.id.activity_title);
            this.b = (LinearLayout) view.findViewById(R.id.activity_duration);
            this.j = (HealthTextView) view.findViewById(R.id.activity_start_date);
            this.e = (HealthTextView) view.findViewById(R.id.activity_end_date);
            this.d = (HealthTextView) view.findViewById(R.id.activity_join_num);
        }
    }

    public void e(String str, ceb cebVar) {
        String operationActivityUrl = this.f10478a.getOperationActivityUrl(str, cebVar);
        this.f10478a.setNeedUpdateActivity(this.b, true);
        try {
            Context context = this.b;
            context.startActivity(this.i.builder(context, operationActivityUrl).setBI(cebVar.c(), cebVar.b(), "ACT", "SHOW_TIME_BI").build());
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SocialActRecyclerAdapte", "gotoOperationActivityDetail exception", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        new NoTitleCustomAlertDialog.Builder(this.b).e(this.b.getResources().getString(R$string.IDS_hw_update_app_tips)).czB_(R$string.IDS_user_permission_know, R.color._2131296970_res_0x7f0902ca, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.adpters.SocialActRecyclerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }
}
