package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class QuerySetCompeteDayFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private boolean[] f3336a;
    private List<View> b = new ArrayList();
    private List<View> c = new ArrayList();
    private String[] d;
    private Context e;
    private HealthColumnLinearLayout f;
    private RunPlanCreateActivity.OnNextPageListener g;
    private HealthTextView h;
    private RelativeLayout i;
    private HealthRecycleView j;
    private ImageView l;
    private HealthProgressBar n;

    public static QuerySetCompeteDayFragment b() {
        return new QuerySetCompeteDayFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_is_set_compete_day, viewGroup, false);
        aKz_(inflate);
        return inflate;
    }

    private void aKz_(View view) {
        if (view == null) {
            LogUtil.b("Suggestion_QuerySetCompeteDayFragment", "initView view is null.");
            return;
        }
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.i = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.n = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.l = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        healthTextView.setText(R.string._2130848571_res_0x7f022b3b);
        this.b.add(healthTextView);
        this.f = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        this.h = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary);
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.recycle_yes_or_no);
        this.j = healthRecycleView;
        BaseActivity.setViewSafeRegion(true, healthRecycleView);
        this.b.add(this.j);
        this.c.add(this.j);
        this.d = new String[]{getResources().getString(R.string._2130841561_res_0x7f020fd9), getResources().getString(R.string._2130841444_res_0x7f020f64)};
        this.f3336a = new boolean[2];
        this.j.setAdapter(d());
        this.j.setLayoutManager(c());
        this.j.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.QuerySetCompeteDayFragment.3
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view2, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view2, recyclerView, state);
                int dimension = ((int) QuerySetCompeteDayFragment.this.getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8)) / 2;
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view2);
                if (LanguageUtil.bc(QuerySetCompeteDayFragment.this.e)) {
                    if (childAdapterPosition == 0) {
                        rect.left = dimension;
                        return;
                    } else {
                        rect.right = dimension;
                        return;
                    }
                }
                if (childAdapterPosition == 0) {
                    rect.right = dimension;
                } else {
                    rect.left = dimension;
                }
            }
        });
        gdk.b(true, this.b);
        gdr.aLy_(this.e, 70, this.n, this.l);
    }

    private GridLayoutManager c() {
        return new GridLayoutManager(this.e, 2) { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.QuerySetCompeteDayFragment.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
    }

    private GroupBtnSelectedAdapter d() {
        final GroupBtnSelectedAdapter groupBtnSelectedAdapter = new GroupBtnSelectedAdapter(this.e, this.d, this.f3336a, R.layout.item_query_button);
        groupBtnSelectedAdapter.cwI_(this.e.getResources().getDrawable(R.drawable._2131430720_res_0x7f0b0d40), this.e.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        groupBtnSelectedAdapter.cwJ_(this.e.getResources().getDrawable(R.drawable._2131430718_res_0x7f0b0d3e), this.e.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
        groupBtnSelectedAdapter.c(new GroupBtnSelectedAdapter.OnItemClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.QuerySetCompeteDayFragment.2
            @Override // com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter.OnItemClickListener
            public void onItemClick(View view, HealthButton healthButton, int i) {
                FragmentActivity activity = QuerySetCompeteDayFragment.this.getActivity();
                if (activity == null || nsn.a(500)) {
                    LogUtil.h("Suggestion_QuerySetCompeteDayFragment", "adapter onItemClick activity is null or click too fast.");
                    return;
                }
                if (i == 0) {
                    QuerySetCompeteDayFragment.this.f3336a[i] = true;
                    QuerySetCompeteDayFragment.this.f3336a[1] = false;
                    QuerySetCompeteDayFragment.this.h.setText(QuerySetCompeteDayFragment.this.getString(R.string._2130841561_res_0x7f020fd9));
                } else {
                    QuerySetCompeteDayFragment.this.f3336a[0] = false;
                    QuerySetCompeteDayFragment.this.f3336a[i] = true;
                    QuerySetCompeteDayFragment.this.h.setText(QuerySetCompeteDayFragment.this.getString(R.string._2130841444_res_0x7f020f64));
                }
                groupBtnSelectedAdapter.notifyDataSetChanged();
                if (QuerySetCompeteDayFragment.this.g != null) {
                    QuerySetCompeteDayFragment.this.g.backLock();
                    gdk.b(false, QuerySetCompeteDayFragment.this.c);
                    gdk.aLq_(QuerySetCompeteDayFragment.this.i, QuerySetCompeteDayFragment.this.f, QuerySetCompeteDayFragment.this.g, QuerySetCompeteDayFragment.this.f3336a[0]);
                    return;
                }
                activity.finish();
            }
        });
        return groupBtnSelectedAdapter;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_QuerySetCompeteDayFragment", "isHidden fragment.");
        } else {
            this.i.setVisibility(0);
            gdk.b(true, this.b);
        }
    }

    public void d(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.g = onNextPageListener;
    }
}
