package com.huawei.ui.main.stories.soical.store;

import android.content.ActivityNotFoundException;
import android.content.Context;
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
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import com.huawei.ui.main.stories.soical.store.SocialActRecyclerAdapter;
import defpackage.ceb;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class SocialActRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final LayoutInflater f10489a;
    private final List<ceb> b;
    private final Context d;
    private final OperationWebActivityIntentBuilderApi e = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
    private final OperationInteractorsApi c = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);

    public SocialActRecyclerAdapter(Context context, List<ceb> list) {
        this.d = context;
        this.b = list;
        this.f10489a = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this.f10489a.inflate(R.layout.item_social_act_recycler_view, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            if (koq.b(this.b, i)) {
                LogUtil.h("SocialActRecyclerAdapte", "mOperationObjectList is out of bounds.");
                return;
            }
            final ceb cebVar = this.b.get(i);
            if (cebVar == null) {
                LogUtil.h("SocialActRecyclerAdapte", "operationObject == null");
                return;
            }
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: rxl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SocialActRecyclerAdapter.this.dSZ_(cebVar, view);
                }
            });
            d(myViewHolder, i);
            if (nsn.ag(this.d)) {
                e(myViewHolder, i);
            }
            a(myViewHolder, cebVar);
        }
    }

    public /* synthetic */ void dSZ_(ceb cebVar, View view) {
        e(cebVar);
        if (CommonUtil.bu()) {
            try {
                this.d.startActivity(this.e.builder(this.d, cebVar.a()).setBI(cebVar.c(), cebVar.b(), "ACT", "SHOW_TIME_BI").build());
            } catch (ActivityNotFoundException e) {
                LogUtil.h("SocialActRecyclerAdapte", "ActivityNotFoundException", e.getMessage());
            }
        } else {
            d(cebVar);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(MyViewHolder myViewHolder, ceb cebVar) {
        nrf.cIS_(myViewHolder.c, cebVar.d(), nrf.d, 0, 0);
        myViewHolder.h.setText(cebVar.b());
        if (this.c.isHuaweiNoTemplateActivity(cebVar)) {
            myViewHolder.d.setVisibility(8);
        } else {
            myViewHolder.d.setVisibility(0);
            myViewHolder.d.setText(this.d.getResources().getString(R.string._2130841921_res_0x7f021141, cebVar.o()));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        long currentTimeMillis = System.currentTimeMillis();
        String format = simpleDateFormat.format(new Date(currentTimeMillis));
        String format2 = simpleDateFormat.format(new Date(currentTimeMillis + 2592000000L));
        myViewHolder.g.setText(format);
        myViewHolder.e.setText(Constants.LINK);
        myViewHolder.f10490a.setText(format2);
        myViewHolder.i.setText(this.d.getResources().getString(R.string._2130842666_res_0x7f02142a));
    }

    private void d(MyViewHolder myViewHolder, int i) {
        if (i == 0) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, nrr.e(this.d, 109.0f));
            layoutParams.setMargins(0, nrr.e(this.d, 8.0f), 0, 0);
            myViewHolder.c.setLayoutParams(layoutParams);
        }
    }

    private void e(MyViewHolder myViewHolder, int i) {
        if (i == 1) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, nrr.e(this.d, 109.0f));
            layoutParams.setMargins(0, nrr.e(this.d, 8.0f), 0, 0);
            myViewHolder.c.setLayoutParams(layoutParams);
        }
    }

    private void e(ceb cebVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", "4");
        hashMap.put("activityId", cebVar.c());
        ixx.d().d(this.d, AnalyticsValue.SUCCESSES_ACTIVITY_1100005.value(), hashMap, 0);
    }

    private void d(final ceb cebVar) {
        if (cebVar == null) {
            return;
        }
        GRSManager.a(this.d).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.store.SocialActRecyclerAdapter.5
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("SocialActRecyclerAdapte", "GRSManager onCallBackSuccess ACTIVITY_KEY url = ", str);
                if (!SocialActRecyclerAdapter.this.c.judgeVersionSupport(cebVar.i())) {
                    SocialActRecyclerAdapter.this.c();
                } else {
                    SocialActRecyclerAdapter.this.b(str, cebVar);
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("SocialActRecyclerAdapte", "GRSManager onCallBackFail ACTIVITY_KEY code = ", Integer.valueOf(i));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f10490a;
        LinearLayout b;
        ImageView c;
        HealthTextView d;
        HealthTextView e;
        HealthTextView g;
        HealthTextView h;
        HealthTextView i;

        public MyViewHolder(View view) {
            super(view);
            this.b = (LinearLayout) view.findViewById(R.id.activity);
            this.c = (ImageView) view.findViewById(R.id.activity_img);
            this.h = (HealthTextView) view.findViewById(R.id.activity_title);
            this.d = (HealthTextView) view.findViewById(R.id.activity_join_num);
            this.i = (HealthTextView) view.findViewById(R.id.activity_status);
            this.g = (HealthTextView) view.findViewById(R.id.activity_start_date);
            this.f10490a = (HealthTextView) view.findViewById(R.id.activity_end_date);
            this.e = (HealthTextView) view.findViewById(R.id.activity_divide_date);
        }
    }

    public void b(String str, ceb cebVar) {
        String operationActivityUrl = this.c.getOperationActivityUrl(str, cebVar);
        this.c.setNeedUpdateActivity(this.d, true);
        try {
            Context context = this.d;
            context.startActivity(this.e.builder(context, operationActivityUrl).setBI(cebVar.c(), cebVar.b(), "ACT", "SHOW_TIME_BI").build());
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SocialActRecyclerAdapte", "mOperationWebActivity NotFound ", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        new NoTitleCustomAlertDialog.Builder(this.d).e(this.d.getResources().getString(R.string._2130839533_res_0x7f0207ed)).czB_(R.string._2130841554_res_0x7f020fd2, R.color._2131296970_res_0x7f0902ca, new View.OnClickListener() { // from class: rxj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }
}
