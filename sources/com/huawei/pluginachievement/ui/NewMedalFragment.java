package com.huawei.pluginachievement.ui;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.ui.adapter.MedalExpandableAdapter;
import com.huawei.pluginachievement.ui.listenerinterface.MedalFragmentFreshListener;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.listview.HealthExpandableListView;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public class NewMedalFragment extends BaseFragment implements MedalFragmentFreshListener {
    private HealthExpandableListView b;
    private String c;
    private MedalExpandableAdapter d;
    private int e;
    private LinearLayout g;
    private Map<String, ArrayList<MedalInfoDesc>> h;
    private ArrayList<String> i;
    private AchieveMedalNewActivity j;
    private Pair<Integer, Integer> f = BaseActivity.getSafeRegionWidth();

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Integer> f8413a = new HashMap(16);

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getActivity() instanceof AchieveMedalNewActivity) {
            this.j = (AchieveMedalNewActivity) getActivity();
            this.e = getArguments().getInt("position");
            String string = getArguments().getString("kind");
            this.c = string;
            LogUtil.a("PluginAchievement_MyMedalFragment", "onCreate() kind= ", string, " category= ", Integer.valueOf(this.e));
            try {
                if (getArguments().getStringArrayList("secondtab") != null) {
                    this.i = getArguments().getStringArrayList("secondtab");
                } else {
                    Map<String, ArrayList<String>> d = this.j.d();
                    if (d != null) {
                        this.i = d.get(this.c);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("PluginAchievement_MyMedalFragment", "onCreate ArrayIndexOutOfBoundsException");
            }
            this.h = this.j.b();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.new_medal_content_fragment, (ViewGroup) null);
        this.g = (LinearLayout) inflate.findViewById(R.id.no_medal_layout);
        HealthExpandableListView healthExpandableListView = (HealthExpandableListView) inflate.findViewById(R.id.expandable_list_view);
        this.b = healthExpandableListView;
        healthExpandableListView.setPadding(((Integer) this.f.first).intValue(), 0, ((Integer) this.f.second).intValue(), 0);
        a();
        return inflate;
    }

    private void a() {
        ArrayList<String> arrayList = this.i;
        if (arrayList != null) {
            Iterator<String> it = arrayList.iterator();
            boolean z = false;
            while (it.hasNext()) {
                String next = it.next();
                Map<String, ArrayList<MedalInfoDesc>> map = this.h;
                if (map != null && map.get(next) != null && this.h.get(next).size() > 0) {
                    z = true;
                }
            }
            if (z) {
                LogUtil.a("PluginAchievement_MyMedalFragment", "onCreateView() secondTab toString = ", this.i.toString(), " flags = true");
                this.g.setVisibility(4);
                HashMap hashMap = new HashMap(16);
                for (int i = 0; i < this.i.size(); i++) {
                    hashMap.put(Integer.valueOf(i), this.i.get(i));
                    this.f8413a.put(this.i.get(i), 0);
                }
                MedalExpandableAdapter medalExpandableAdapter = new MedalExpandableAdapter(getActivity(), this.h, hashMap);
                this.d = medalExpandableAdapter;
                this.b.setAdapter(medalExpandableAdapter);
                this.j.c(this.e, cii_(this.b, this.d));
                this.b.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() { // from class: com.huawei.pluginachievement.ui.NewMedalFragment.4
                    @Override // android.widget.ExpandableListView.OnGroupExpandListener
                    public void onGroupExpand(int i2) {
                        if (koq.b(NewMedalFragment.this.i, i2)) {
                            return;
                        }
                        NewMedalFragment.this.f8413a.put((String) NewMedalFragment.this.i.get(i2), 1);
                        NewMedalFragment newMedalFragment = NewMedalFragment.this;
                        NewMedalFragment.this.j.b(NewMedalFragment.this.e, i2, newMedalFragment.cii_(newMedalFragment.b, NewMedalFragment.this.d));
                    }
                });
                this.b.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() { // from class: com.huawei.pluginachievement.ui.NewMedalFragment.5
                    @Override // android.widget.ExpandableListView.OnGroupCollapseListener
                    public void onGroupCollapse(int i2) {
                        if (koq.b(NewMedalFragment.this.i, i2)) {
                            return;
                        }
                        NewMedalFragment.this.f8413a.put((String) NewMedalFragment.this.i.get(i2), 0);
                        NewMedalFragment newMedalFragment = NewMedalFragment.this;
                        NewMedalFragment.this.j.b(NewMedalFragment.this.e, i2, newMedalFragment.cii_(newMedalFragment.b, NewMedalFragment.this.d));
                    }
                });
            }
        }
    }

    public int cii_(ExpandableListView expandableListView, MedalExpandableAdapter medalExpandableAdapter) {
        Map<String, Integer> map;
        Integer num;
        if (medalExpandableAdapter == null || (map = this.f8413a) == null || map.size() == 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < medalExpandableAdapter.getGroupCount(); i3++) {
            View groupView = medalExpandableAdapter.getGroupView(i3, false, null, expandableListView);
            if (groupView != null) {
                groupView.measure(0, 0);
                i2 += groupView.getMeasuredHeight();
                i += medalExpandableAdapter.getChildrenCount(0) - 1;
            }
        }
        LogUtil.a("PluginAchievement_MyMedalFragment", "getListHeight secondTab list = ", Integer.valueOf(i));
        for (int i4 = 0; i4 < this.f8413a.size(); i4++) {
            if (!koq.b(this.i, i4) && (num = this.f8413a.get(this.i.get(i4))) != null && num.intValue() == 1) {
                for (int i5 = 0; i5 < medalExpandableAdapter.getChildrenCount(i4); i5++) {
                    View childView = medalExpandableAdapter.getChildView(i4, i5, false, null, expandableListView);
                    if (childView != null) {
                        childView.measure(0, 0);
                        i2 += childView.getMeasuredHeight();
                        i += medalExpandableAdapter.getChildrenCount(i4) - 1;
                    }
                }
            }
        }
        int dividerHeight = i2 + (expandableListView.getDividerHeight() * i);
        LogUtil.a("PluginAchievement_MyMedalFragment", "getListHeight secondTab totallist = ", Integer.valueOf(dividerHeight));
        return dividerHeight;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        MedalExpandableAdapter medalExpandableAdapter = this.d;
        if (medalExpandableAdapter != null) {
            medalExpandableAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    public void e() {
        AchieveMedalNewActivity achieveMedalNewActivity = this.j;
        if (achieveMedalNewActivity != null) {
            this.h = achieveMedalNewActivity.b();
            Map<String, ArrayList<String>> d = this.j.d();
            if (this.h == null || d == null) {
                return;
            }
            ArrayList<String> arrayList = d.get(this.c);
            this.i = arrayList;
            if (arrayList == null) {
                return;
            }
            a();
        }
    }

    @Override // com.huawei.pluginachievement.ui.listenerinterface.MedalFragmentFreshListener
    public void onFragmentFreshListener(String str) {
        this.j.onFragmentFreshListener(str);
    }
}
