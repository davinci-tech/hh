package com.huawei.featureuserprofile.award.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.trade.datatype.AwardRecordsBean;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class AwardExpiredFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private AwardExpiredAdapter f1971a;
    private RelativeLayout b;
    private List<AwardRecordsBean> c;
    private HealthRecycleView e;

    public static AwardExpiredFragment e() {
        return new AwardExpiredFragment();
    }

    public void d(List<AwardRecordsBean> list) {
        List<AwardRecordsBean> list2 = this.c;
        if (list2 == null) {
            this.c = new ArrayList(list.size());
        } else {
            list2.clear();
        }
        this.c.addAll(list);
        c();
        AwardExpiredAdapter awardExpiredAdapter = this.f1971a;
        if (awardExpiredAdapter != null) {
            awardExpiredAdapter.a(this.c);
            this.f1971a.notifyDataSetChanged();
        }
    }

    public void d() {
        HealthRecycleView healthRecycleView = this.e;
        if (healthRecycleView != null) {
            healthRecycleView.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    private void c() {
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        HealthRecycleView healthRecycleView = this.e;
        if (healthRecycleView != null) {
            healthRecycleView.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_award_expired, (ViewGroup) null);
        this.e = (HealthRecycleView) inflate.findViewById(R.id.award_recycler_view);
        this.b = (RelativeLayout) inflate.findViewById(R.id.award_empty_layout);
        this.e.setLayoutManager(new LinearLayoutManager(getActivity()));
        AwardExpiredAdapter awardExpiredAdapter = new AwardExpiredAdapter(getActivity());
        this.f1971a = awardExpiredAdapter;
        this.e.setAdapter(awardExpiredAdapter);
        return inflate;
    }
}
