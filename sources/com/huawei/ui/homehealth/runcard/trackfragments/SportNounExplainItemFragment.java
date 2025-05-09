package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.homehealth.runcard.trackfragments.adapters.SportNounListAdapter;
import com.huawei.ui.homehealth.runcard.trackfragments.models.SportNounChildData;
import defpackage.oro;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class SportNounExplainItemFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private SportNounListAdapter f9571a;
    private int b;
    private int c;
    private List<oro> d;
    private Context e;
    private View g;
    private SportNounChildData i;
    private ListView j;

    public SportNounExplainItemFragment() {
        this.c = 0;
        this.d = new ArrayList(10);
    }

    public SportNounExplainItemFragment(int i, int i2) {
        this.c = 0;
        this.d = new ArrayList(10);
        this.c = i;
        this.b = i2;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.a("SportNounExplainItemFragment", "inflater is null");
            return null;
        }
        LogUtil.a("SportNounExplainItemFragment", "onCreateView");
        this.g = layoutInflater.inflate(R.layout.fragment_sport_noun_explain_list_item, viewGroup, false);
        FragmentActivity activity = getActivity();
        this.e = activity;
        this.i = new SportNounChildData(activity);
        b();
        dgn_(this.g);
        return this.g;
    }

    private void b() {
        LogUtil.a("SportNounExplainItemFragment", "initData");
        if (this.d != null) {
            this.d = null;
        }
        switch (this.c) {
            case 1:
                this.d = this.i.a();
                break;
            case 2:
                this.d = this.i.j();
                break;
            case 3:
                this.d = this.i.g();
                break;
            case 4:
                this.d = this.i.e(this.b);
                break;
            case 5:
                this.d = this.i.i();
                break;
            case 6:
                this.d = this.i.d();
                break;
            case 7:
                this.d = this.i.e();
                break;
            case 8:
                this.d = this.i.b();
                break;
            case 9:
                this.d = this.i.f();
                break;
        }
    }

    private void dgn_(View view) {
        LogUtil.a("SportNounExplainItemFragment", "initView");
        this.j = (ListView) view.findViewById(R.id.list_sport_noun_explain_listview);
        SportNounListAdapter sportNounListAdapter = new SportNounListAdapter(this.e, this.d);
        this.f9571a = sportNounListAdapter;
        this.j.setAdapter((ListAdapter) sportNounListAdapter);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("SportNounExplainItemFragment", "onDestroy");
        super.onDestroy();
    }
}
