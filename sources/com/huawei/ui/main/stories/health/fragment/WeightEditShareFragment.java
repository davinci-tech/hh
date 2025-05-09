package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.main.stories.health.adapter.WeightEditShareGridAdapter;
import com.huawei.ui.main.stories.health.interactors.healthdata.WeightShareEditListener;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class WeightEditShareFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private GridView f10183a;
    private int b;
    private List<Bitmap> c = new ArrayList();
    private Context d;
    private WeightEditShareGridAdapter e;
    private WeightShareEditListener f;
    private View h;

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("WeightEditShareFragment", "onCreate");
        this.d = getActivity();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.b = arguments.getInt("share_source_type");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("WeightEditShareFragment", "onCreateView");
        if (this.h == null) {
            this.h = layoutInflater.inflate(R.layout.hw_health_edit_share_fragment, viewGroup, false);
            e();
            this.f10183a = (GridView) this.h.findViewById(R.id.hw_health_edit_share_gridview);
            this.e = new WeightEditShareGridAdapter(this.d, this.c, this.b);
            this.f10183a.setNumColumns(4);
            this.f10183a.setAdapter((ListAdapter) this.e);
            this.f10183a.setOnItemClickListener(this);
            this.f10183a.setFocusable(false);
            if (LanguageUtil.bc(this.d)) {
                this.f10183a.setRotationY(180.0f);
            }
        }
        return this.h;
    }

    private void e() {
        this.c.clear();
        if (this.b == 0) {
            this.c = CardConstants.d();
        } else if (LanguageUtil.h(this.d)) {
            this.c = CardConstants.c();
        } else {
            this.c = CardConstants.dFd_(getResources());
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (koq.b(this.c, i) || this.f == null) {
            LogUtil.h("WeightEditShareFragment", "position is OutOfBounds mBitmapIds or listener = null");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        View findViewById = view.findViewById(R.id.hw_health_edit_share_select);
        if (findViewById == null) {
            LogUtil.h("WeightEditShareFragment", "shareView = null");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        int i2 = this.b;
        if (i2 == 0) {
            if (i == 0) {
                LogUtil.a("WeightEditShareFragment", "need go to camera");
                this.f.gotoCamera();
            } else {
                for (int i3 = 0; i3 < adapterView.getCount(); i3++) {
                    View childAt = adapterView.getChildAt(i3);
                    if (i == i3) {
                        childAt.findViewById(R.id.hw_health_edit_share_select).setVisibility(0);
                        LogUtil.a("WeightEditShareFragment", "refreshWeightShareBackground position = ", Integer.valueOf(i));
                        this.f.refreshWeightShareBackground(i - 1);
                    } else {
                        childAt.findViewById(R.id.hw_health_edit_share_select).setVisibility(8);
                    }
                }
            }
        } else if (i2 == 1) {
            if (findViewById.getVisibility() != 0) {
                LogUtil.a("WeightEditShareFragment", "refreshWeightShareData show the view, position = ", Integer.valueOf(i));
                findViewById.setVisibility(0);
                this.f.refreshWeightShareData(i, true);
            } else {
                LogUtil.a("WeightEditShareFragment", "refreshWeightShareData not show the view, position = ", Integer.valueOf(i));
                findViewById.setVisibility(8);
                this.f.refreshWeightShareData(i, false);
            }
        } else {
            LogUtil.h("WeightEditShareFragment", "errorType = ", Integer.valueOf(i2));
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    public void d(WeightShareEditListener weightShareEditListener) {
        this.f = weightShareEditListener;
    }

    public void c() {
        if (this.f10183a == null || this.e == null) {
            LogUtil.h("WeightEditShareFragment", "refreshGrid position mGridView is null");
            return;
        }
        LogUtil.a("WeightEditShareFragment", "refreshGrid position");
        for (int i = 0; i < this.e.getCount(); i++) {
            this.f10183a.getChildAt(i).findViewById(R.id.hw_health_edit_share_select).setVisibility(8);
        }
    }
}
