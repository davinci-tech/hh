package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.homehealth.runcard.trackfragments.adapters.SportNounExpandableListAdapter;
import defpackage.koq;
import defpackage.orn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class SportNounExplainFragment extends BaseFragment {
    private ExpandableListView b;
    private Context d;
    private SportNounExpandableListAdapter e;
    private View g;

    /* renamed from: a, reason: collision with root package name */
    private List<orn> f9570a = new ArrayList(10);
    private String[] c = null;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.a("SportNounExplainFragment", "onCreateView inflater is null");
            return null;
        }
        LogUtil.a("SportNounExplainFragment", "onCreateView");
        this.g = layoutInflater.inflate(R.layout.fragment_sport_noun_explain_list, viewGroup, false);
        this.d = getActivity();
        c();
        dgm_(this.g);
        a(0);
        return this.g;
    }

    private void c() {
        LogUtil.a("SportNounExplainFragment", "initData");
        if (this.c != null) {
            this.c = null;
        }
        Resources resources = getResources();
        this.c = new String[]{resources.getString(R.string._2130841430_res_0x7f020f56), resources.getString(R.string._2130842718_res_0x7f02145e), resources.getString(R.string._2130842721_res_0x7f021461), resources.getString(R.string._2130842877_res_0x7f0214fd), resources.getString(R.string._2130842878_res_0x7f0214fe), resources.getString(R.string._2130843171_res_0x7f021623), resources.getString(R.string._2130843953_res_0x7f021931), resources.getString(R.string._2130844069_res_0x7f0219a5)};
        int i = 0;
        while (true) {
            String[] strArr = this.c;
            if (i >= strArr.length) {
                return;
            }
            this.f9570a.add(new orn(strArr[i], i, this.d));
            i++;
        }
    }

    private void dgm_(View view) {
        LogUtil.a("SportNounExplainFragment", "initView");
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.list_sport_noun_explain_simplify);
        this.b = expandableListView;
        BaseActivity.cancelLayoutById(expandableListView);
        BaseActivity.setViewSafeRegion(false, this.b);
        SportNounExpandableListAdapter sportNounExpandableListAdapter = new SportNounExpandableListAdapter(getContext());
        this.e = sportNounExpandableListAdapter;
        this.b.setAdapter(sportNounExpandableListAdapter);
        this.e.a(this.f9570a);
        d();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("SportNounExplainFragment", "onDestroy");
        super.onDestroy();
    }

    private void d() {
        LogUtil.a("SportNounExplainFragment", "setTouchListener");
        this.b.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportNounExplainFragment.5
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                if (!koq.b(SportNounExplainFragment.this.f9570a, i)) {
                    orn ornVar = (orn) SportNounExplainFragment.this.f9570a.get(i);
                    if (ornVar == null) {
                        LogUtil.h("SportNounExplainFragment", "groupData is null");
                        ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i);
                        return true;
                    }
                    LogUtil.a("SportNounExplainFragment", "groupPosition: ", Integer.valueOf(i), " size: ", Integer.valueOf(ornVar.e()));
                    ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i);
                    return false;
                }
                LogUtil.a("SportNounExplainFragment", "onGroupClick groupPosition is out of bounds");
                ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i);
                return false;
            }
        });
    }

    private void a(int i) {
        this.b.expandGroup(i);
        if (koq.d(this.f9570a, i)) {
            this.f9570a.get(i).b(true);
        }
    }
}
