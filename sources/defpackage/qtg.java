package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class qtg extends qta {
    private BodyReportRecycleItem c;
    private Context d;

    public qtg(Context context, BodyReportRecycleItem bodyReportRecycleItem) {
        super(context, bodyReportRecycleItem);
        this.d = context;
        this.c = bodyReportRecycleItem;
    }

    @Override // defpackage.qta
    public String b() {
        int i;
        if (this.d == null) {
            return super.b();
        }
        if (BodyReportRecycleItem.BodyReportType.IMPORTANT_INDICATORS.equals(d().a())) {
            i = R$string.IDS_hw_weight_report_important_indicators;
        } else {
            i = R$string.IDS_hw_weight_report_other_indicators;
        }
        return BaseApplication.getContext().getResources().getString(i);
    }

    @Override // defpackage.qta
    public View dIW_() {
        Context context = this.d;
        if (context == null) {
            return super.dIW_();
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.human_analysis_other_index_layout, (ViewGroup) null);
        dIO_(inflate);
        return inflate;
    }

    @Override // defpackage.qta
    public BodyReportRecycleItem d() {
        BodyReportRecycleItem bodyReportRecycleItem = this.c;
        return bodyReportRecycleItem == null ? super.d() : bodyReportRecycleItem;
    }

    private void dIO_(View view) {
        int i;
        if (view == null || this.c == null) {
            LogUtil.h("BodyReportOtherIndicatorsView", "initView illegal argument");
            return;
        }
        if (Utils.o()) {
            view.findViewById(R.id.header_vertical_line_2).setVisibility(8);
            view.findViewById(R.id.header_standard_value).setVisibility(8);
            view.findViewById(R.id.header_name).setLayoutParams(new LinearLayout.LayoutParams(0, -1, 6.0f));
            view.findViewById(R.id.header_value).setLayoutParams(new LinearLayout.LayoutParams(0, -1, 4.0f));
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.table_header);
        if (LanguageUtil.bj(this.d) || LanguageUtil.v(this.d) || LanguageUtil.bq(this.d)) {
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = nsn.c(this.d, 24.0f);
            linearLayout.setLayoutParams(layoutParams);
            i = 188;
        } else {
            i = 200;
        }
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.report_index_table_recycle_view);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.d, 1, false));
        b bVar = new b(this.c.e(), nsn.c(this.d, i));
        healthRecycleView.setAdapter(bVar);
        bVar.notifyDataSetChanged();
    }

    class b extends RecyclerView.Adapter {
        private BodyReportRecycleItem.c[] c;
        private int d;

        b(BodyReportRecycleItem.c[] cVarArr, int i) {
            this.c = cVarArr;
            this.d = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (viewGroup == null) {
                return null;
            }
            return new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_body_analysis_report_index_table_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            BodyReportRecycleItem.c[] cVarArr;
            if (!(viewHolder instanceof c) || (cVarArr = this.c) == null) {
                LogUtil.h("BodyReportOtherIndicatorsView", "onBindViewHolder illegal argument");
                return;
            }
            if (i > cVarArr.length - 1 || i < 0 || cVarArr[i] == null) {
                LogUtil.h("BodyReportOtherIndicatorsView", "onBindViewHolder illegal argument");
                return;
            }
            int length = this.d / cVarArr.length;
            c cVar = (c) viewHolder;
            cVar.e.setLayoutParams(new LinearLayout.LayoutParams(-1, length));
            BodyReportRecycleItem.c cVar2 = this.c[i];
            c(cVar.b, cVar2.e());
            if (TextUtils.isEmpty(cVar2.c())) {
                cVar.f.setVisibility(8);
            } else {
                c(cVar.f, Constants.LEFT_BRACKET_ONLY + cVar2.c() + Constants.RIGHT_BRACKET_ONLY);
            }
            a(cVar.i, cVar2.a());
            if (Utils.o()) {
                cVar.h.setVisibility(8);
                cVar.f16577a.setVisibility(8);
                cVar.c.setLayoutParams(new LinearLayout.LayoutParams(0, length, 6.0f));
                cVar.i.setLayoutParams(new LinearLayout.LayoutParams(0, length, 4.0f));
            } else {
                c(cVar.h, cVar2.d());
            }
            if (i == this.c.length - 1) {
                cVar.j.setVisibility(8);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.c.length;
        }

        private void c(HealthTextView healthTextView, String str) {
            if (healthTextView == null || TextUtils.isEmpty(str)) {
                LogUtil.h("BodyReportOtherIndicatorsView", "set text illegal arguments");
            } else {
                healthTextView.setText(str);
            }
        }

        private void a(HealthTextView healthTextView, String str) {
            if (healthTextView == null || TextUtils.isEmpty(str)) {
                LogUtil.h("BodyReportOtherIndicatorsView", "set value illegal arguments");
            } else if ("0".equals(str)) {
                healthTextView.setText("--");
            } else {
                healthTextView.setText(str);
            }
        }

        class c extends RecyclerView.ViewHolder {

            /* renamed from: a, reason: collision with root package name */
            private HealthTextView f16577a;
            private HealthTextView b;
            private LinearLayout c;
            private LinearLayout e;
            private HealthTextView f;
            private HealthTextView h;
            private HealthTextView i;
            private View j;

            c(View view) {
                super(view);
                this.e = (LinearLayout) view.findViewById(R.id.report_index_table_item_content);
                this.c = (LinearLayout) view.findViewById(R.id.index_name_and_unit);
                this.b = (HealthTextView) view.findViewById(R.id.index_name);
                this.f = (HealthTextView) view.findViewById(R.id.index_unit);
                this.i = (HealthTextView) view.findViewById(R.id.index_measure_value);
                this.f16577a = (HealthTextView) view.findViewById(R.id.dash_vertical_line_2);
                this.h = (HealthTextView) view.findViewById(R.id.index_normal_range);
                this.j = view.findViewById(R.id.row_seperator);
            }
        }
    }
}
