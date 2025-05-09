package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.adapter.ClaimDataExpandableListViewAdapter;
import com.huawei.ui.main.stories.health.interactors.healthdata.SelectUserInterface;
import defpackage.cfi;
import defpackage.csh;
import defpackage.koq;
import defpackage.nsy;
import defpackage.qpz;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class ClaimDataExpandableListViewAdapter extends BaseExpandableListAdapter {
    private final SelectUserInterface c;
    private int e;

    /* renamed from: a, reason: collision with root package name */
    private List<cfi> f10118a = new ArrayList(16);
    private List<List<csh>> d = new ArrayList(16);
    private final Context b = BaseApplication.getContext();

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public ClaimDataExpandableListViewAdapter(SelectUserInterface selectUserInterface) {
        this.c = selectUserInterface;
    }

    public void a(List<cfi> list, List<List<csh>> list2) {
        if (koq.b(list)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "setList userList is empty");
            return;
        }
        if (koq.b(list2)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "setList childList is empty");
            return;
        }
        this.f10118a.clear();
        this.d.clear();
        this.f10118a.addAll(list);
        this.d.addAll(list2);
        notifyDataSetChanged();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public csh getChild(int i, int i2) {
        if (koq.b(this.d, i)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "getChild weightBeanList is out of bounds");
            return null;
        }
        if (koq.b(this.d.get(i), i2)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "getChild weightBeanList is out of bounds");
            return null;
        }
        return this.d.get(i).get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = View.inflate(this.b, R.layout.item_claim_data_fatrate_fluctuation, null);
            BaseActivity.setViewSafeRegion(false, view);
            cVar = new c(view);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        c cVar2 = cVar;
        if (koq.b(this.d, i)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "getChild weightBeanList is out of bounds");
            return view;
        }
        if (koq.b(this.d.get(i), i2)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "onBindViewHolder mList is out of bounds");
            return view;
        }
        csh cshVar = this.d.get(i).get(i2);
        if (cshVar == null) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "onBindViewHolder dataBean is null");
            return view;
        }
        HiHealthData c2 = cshVar.c();
        if (c2 == null) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "onBindViewHolder hiHealthData is null");
            return view;
        }
        b(i, i2, cVar2, cshVar, c2);
        return view;
    }

    private void b(int i, int i2, c cVar, final csh cshVar, HiHealthData hiHealthData) {
        int i3 = hiHealthData.getInt("trackdata_deviceType");
        double a2 = UnitUtil.a(cshVar.d());
        cVar.b.setText(qsj.e(a2, qsj.c(a2, i3)) + " " + UnitUtil.e(hiHealthData.getDouble(BleConstants.BODY_FAT_RATE), 2, 1));
        String a3 = cshVar.a();
        if (!TextUtils.isEmpty(a3)) {
            cVar.c.setText(a3);
        }
        cVar.e.setChecked(cshVar.f());
        cVar.d.setVisibility(i2 == this.d.get(i).size() - 1 ? 8 : 0);
        cVar.e.setOnClickListener(new View.OnClickListener() { // from class: qga
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClaimDataExpandableListViewAdapter.this.dCv_(cshVar, view);
            }
        });
        if (i2 == 0) {
            cVar.f10119a.setBackgroundResource(R.drawable._2131431108_res_0x7f0b0ec4);
        } else if (i2 == this.d.get(i).size() - 1) {
            cVar.f10119a.setBackgroundResource(R.drawable._2131431107_res_0x7f0b0ec3);
        } else {
            cVar.f10119a.setBackgroundColor(ContextCompat.getColor(this.b, R.color._2131296665_res_0x7f090199));
        }
    }

    public /* synthetic */ void dCv_(csh cshVar, View view) {
        if (cshVar.f()) {
            this.e--;
        } else {
            this.e++;
        }
        LogUtil.a("HealthWeight_ClaimDataExpandableListViewAdapter", "mSelectStatus onClick ... dataBean.isChoose = ", Boolean.valueOf(cshVar.f()), ", mSize = ", Integer.valueOf(this.e));
        this.c.selectItem(c(), this.e);
        cshVar.e(!cshVar.f());
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (koq.b(this.d, i)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "getChild weightBeanList is out of bounds");
            return 0;
        }
        return this.d.get(i).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        if (koq.b(this.f10118a, i)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "getGroup is out of bounds");
            return null;
        }
        return this.f10118a.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.f10118a.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = View.inflate(this.b, R.layout.item_claim_measure_user_data, null);
            BaseActivity.setViewSafeRegion(false, view);
            aVar = new a(view);
            view.setTag(aVar);
        } else if (view.getTag() instanceof a) {
            aVar = (a) view.getTag();
        } else {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "view.getTag() is not FatherViewHolder");
            return view;
        }
        if (koq.b(this.f10118a, i)) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "getGroupView groupPosition is out of bounds");
            return view;
        }
        cfi cfiVar = this.f10118a.get(i);
        if (cfiVar == null) {
            LogUtil.h("HealthWeight_ClaimDataExpandableListViewAdapter", "getView user is null");
        } else {
            qpz.dHr_(cfiVar, aVar.d, aVar.e);
        }
        return view;
    }

    public void b() {
        this.e = 0;
        Iterator<List<csh>> it = this.d.iterator();
        while (it.hasNext()) {
            for (csh cshVar : it.next()) {
                if (cshVar.f()) {
                    cshVar.e(false);
                }
            }
        }
    }

    public int c() {
        Iterator<List<csh>> it = this.d.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().size();
        }
        return i;
    }

    public void a() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.f10118a.size(); i++) {
            List<csh> list = this.d.get(i);
            ArrayList arrayList3 = new ArrayList();
            for (csh cshVar : list) {
                if (!cshVar.f()) {
                    arrayList3.add(cshVar);
                }
            }
            if (arrayList3.size() > 0) {
                arrayList2.add(this.f10118a.get(i));
                arrayList.add(arrayList3);
            }
        }
        this.f10118a = arrayList2;
        this.d = arrayList;
        this.e = 0;
        this.c.selectItem(c(), this.e);
        notifyDataSetChanged();
    }

    public Map<cfi, ArrayList<csh>> e() {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < this.f10118a.size(); i++) {
            List<csh> list = this.d.get(i);
            ArrayList arrayList = new ArrayList();
            for (csh cshVar : list) {
                if (cshVar.f()) {
                    arrayList.add(cshVar);
                }
            }
            if (arrayList.size() > 0) {
                hashMap.put(this.f10118a.get(i), arrayList);
            }
        }
        return hashMap;
    }

    public ArrayList<csh> d() {
        ArrayList<csh> arrayList = new ArrayList<>();
        Iterator<List<csh>> it = this.d.iterator();
        while (it.hasNext()) {
            for (csh cshVar : it.next()) {
                if (cshVar.f()) {
                    arrayList.add(cshVar);
                }
            }
        }
        return arrayList;
    }

    public void c(boolean z) {
        LogUtil.a("HealthWeight_ClaimDataExpandableListViewAdapter", "setAllChooseItem isAllSelect = ", Boolean.valueOf(z));
        Iterator<List<csh>> it = this.d.iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator<csh> it2 = it.next().iterator();
            while (it2.hasNext()) {
                i++;
                it2.next().e(z);
            }
        }
        if (z) {
            this.e = i;
        } else {
            this.e = 0;
        }
        notifyDataSetChanged();
        this.c.selectItem(c(), this.e);
    }

    static class a {
        private HealthTextView d;
        private ImageView e;

        public a(View view) {
            this.e = (ImageView) nsy.cMd_(view, R.id.item_weight_current_user_photo);
            this.d = (HealthTextView) nsy.cMd_(view, R.id.item_right_title_text);
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        RelativeLayout f10119a;
        HealthTextView b;
        HealthTextView c;
        HealthDivider d;
        HealthCheckBox e;

        public c(View view) {
            this.b = (HealthTextView) view.findViewById(R.id.tv_claim_weight_data_body_weight);
            this.b = (HealthTextView) view.findViewById(R.id.tv_claim_weight_data_body_weight);
            this.c = (HealthTextView) view.findViewById(R.id.tv_claim_weight_data_measur_time);
            this.e = (HealthCheckBox) view.findViewById(R.id.iv_claim_weight_data_select_status);
            this.d = (HealthDivider) view.findViewById(R.id.view_claim_weight_data_line);
            this.f10119a = (RelativeLayout) view.findViewById(R.id.item_content_rl);
        }
    }
}
