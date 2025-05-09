package com.huawei.ui.main.stories.fitness.views.heartrate.business;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateWarningDetailsActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData;
import com.huawei.ui.main.stories.fitness.views.heartrate.business.WarningHRDetailView;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.HistoryListView;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel;
import defpackage.nsj;
import defpackage.qbc;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class WarningHRDetailView extends HistoryListView.DetailView {
    public WarningHRDetailView(Context context) {
        super(context);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.item_heart_rate_detail_warning, this);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.HistoryListView.DetailView
    public void load(IHistoryModel iHistoryModel) {
        if (!(iHistoryModel instanceof d)) {
            LogUtil.b("WarningHRDetailView", "modelDetail not instanceof CustomDetailModel");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.health_heartrate_history_child_times);
        long queryModelStartTime = iHistoryModel.queryModelStartTime();
        long queryModelEndTime = iHistoryModel.queryModelEndTime();
        String formatDateTime = DateUtils.formatDateTime(getContext(), queryModelStartTime, 131092);
        String e = nsj.e(getContext(), queryModelStartTime, queryModelEndTime, 1);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.health_heartrate_history_child_date);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.health_heartrate_history_child_time);
        healthTextView2.setText(formatDateTime);
        healthTextView3.setText(e);
        d dVar = (d) iHistoryModel;
        healthTextView.setText(dVar.d());
        ImageView imageView = (ImageView) findViewById(R.id.warning_detail_arrow_img);
        final ArrayList<HeartRateDetailData> c = dVar.c();
        if (c != null && c.size() > 0) {
            imageView.setVisibility(0);
            if (LanguageUtil.bc(getContext())) {
                imageView.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            } else {
                imageView.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            }
            setClickable(true);
            setOnClickListener(new View.OnClickListener() { // from class: qar
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WarningHRDetailView.this.dxD_(c, view);
                }
            });
            return;
        }
        setClickable(false);
        imageView.setVisibility(4);
    }

    public /* synthetic */ void dxD_(ArrayList arrayList, View view) {
        HeartRateWarningDetailsActivity.a(getContext(), arrayList);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static class d extends qbc {

        /* renamed from: a, reason: collision with root package name */
        private float f9992a;
        private Context b;
        private float c;
        private ArrayList<HeartRateDetailData> e;

        public d(Context context, long j, long j2) {
            super(j, j2);
            if (context != null) {
                this.b = context;
            }
        }

        public void c(float f) {
            this.f9992a = f;
        }

        public void a(float f) {
            this.c = f;
        }

        public void d(ArrayList<HeartRateDetailData> arrayList) {
            this.e = arrayList;
        }

        public ArrayList<HeartRateDetailData> c() {
            return this.e;
        }

        public String d() {
            return UnitUtil.e(this.c, 1, 0) + Constants.LINK + UnitUtil.e(this.f9992a, 1, 0) + " " + this.b.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string);
        }
    }
}
