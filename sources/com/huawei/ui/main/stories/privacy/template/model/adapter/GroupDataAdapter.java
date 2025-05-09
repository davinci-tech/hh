package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.koq;
import defpackage.rsg;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class GroupDataAdapter extends BaseExpandableListAdapter {
    private OnItemClickListener e;
    private OnLongClickListener j;
    protected List<rsg> b = new ArrayList(10);
    private int h = 1;

    /* renamed from: a, reason: collision with root package name */
    private boolean f10422a = true;
    private ArrayList<List<Boolean>> d = new ArrayList<>(10);
    private int g = 0;
    private int c = 0;

    public interface OnItemClickListener {
        void onItemClickListener(int i, int i2);
    }

    public interface OnLongClickListener {
        void onLongClickListener(int i, int i2);
    }

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
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void a(List<rsg> list) {
        this.b.clear();
        this.b.addAll(list);
        this.c = 0;
        this.g = d(this.b);
        a();
        notifyDataSetChanged();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.b.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (i >= this.b.size()) {
            LogUtil.e("PrivacyExpandViewAdapter", "getChildrenCount is out of bounds");
            return 0;
        }
        List<PrivacyDataModel> d = this.b.get(i).d();
        if (d == null) {
            return 0;
        }
        return d.size();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public rsg getGroup(int i) {
        if (this.b.size() > i) {
            return this.b.get(i);
        }
        LogUtil.e("PrivacyExpandViewAdapter", "getGroup is out of bounds");
        return new rsg();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public PrivacyDataModel getChild(int i, int i2) {
        if (koq.d(this.b, i)) {
            List<PrivacyDataModel> d = this.b.get(i).d();
            if (koq.d(d, i2) && d.get(i2) != null) {
                return d.get(i2);
            }
            LogUtil.e("PrivacyExpandViewAdapter", "getChild is out of bounds");
        }
        return new PrivacyDataModel();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_list_father_item, viewGroup, false);
            cVar = new c(view);
            view.setTag(cVar);
        } else {
            Object tag = view.getTag();
            cVar = tag instanceof c ? (c) tag : null;
        }
        if (cVar != null) {
            cVar.c.setText(this.b.get(i).e());
            ViewGroup.LayoutParams layoutParams = cVar.b.getLayoutParams();
            if (z) {
                cVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
                cVar.b.setBackgroundResource(R.drawable._2131431108_res_0x7f0b0ec4);
            } else {
                cVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
                cVar.b.setBackgroundResource(R.drawable._2131431106_res_0x7f0b0ec2);
            }
            dQt_(cVar.b, layoutParams, !z);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = dQv_(viewGroup);
            bVar = dQu_(view);
            view.setTag(bVar);
        } else {
            Object tag = view.getTag();
            bVar = tag instanceof b ? (b) tag : null;
        }
        if (bVar != null) {
            bVar.c(this.b.get(i).d().get(i2));
            if (LanguageUtil.bc(bVar.h.getContext())) {
                bVar.e.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
            ViewGroup.LayoutParams layoutParams = bVar.h.getLayoutParams();
            if (z) {
                bVar.h.setBackgroundResource(R.drawable._2131431107_res_0x7f0b0ec3);
                bVar.f.setVisibility(8);
            } else {
                bVar.h.setBackgroundColor(ContextCompat.getColor(bVar.h.getContext(), R.color._2131296665_res_0x7f090199));
                bVar.f.setVisibility(0);
            }
            dQt_(bVar.h, layoutParams, z);
            a(bVar, i2, i);
        }
        return view;
    }

    private void dQt_(View view, ViewGroup.LayoutParams layoutParams, boolean z) {
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (z) {
                layoutParams2.setMargins(0, 0, 0, dimensionPixelSize);
            } else {
                layoutParams2.setMargins(0, 0, 0, 0);
            }
            view.setLayoutParams(layoutParams2);
        }
    }

    public View dQv_(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_list_child_item, viewGroup, false);
    }

    public b dQu_(View view) {
        return new b(view);
    }

    public PrivacyDataModel d(int i, int i2) {
        if (i >= this.b.size()) {
            return new PrivacyDataModel();
        }
        List<PrivacyDataModel> d = this.b.get(i).d();
        if (i2 >= d.size()) {
            return new PrivacyDataModel();
        }
        return d.get(i2);
    }

    protected void a(final b bVar, final int i, final int i2) {
        bVar.h.setOnClickListener(new View.OnClickListener() { // from class: rsc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GroupDataAdapter.this.dQw_(i2, i, view);
            }
        });
        if (this.h == 2) {
            bVar.f10424a.setOnCheckedChangeListener(null);
            bVar.e.setVisibility(8);
            bVar.f10424a.setVisibility(0);
            if (koq.b(this.d, i2)) {
                LogUtil.c("PrivacyExpandViewAdapter", "setChildViewData mCheckList isOutOfBounds");
                return;
            }
            List<Boolean> list = this.d.get(i2);
            if (koq.b(list, i)) {
                LogUtil.c("PrivacyExpandViewAdapter", "setChildViewData childCheckList isOutOfBounds");
                return;
            }
            Boolean bool = list.get(i);
            if (bool != null) {
                bVar.f10424a.setChecked(bool.booleanValue());
            }
            bVar.f10424a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter$$ExternalSyntheticLambda1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    GroupDataAdapter.this.dQx_(bVar, i2, i, compoundButton, z);
                }
            });
            return;
        }
        bVar.e.setVisibility(0);
        bVar.f10424a.setVisibility(8);
        if (!this.f10422a) {
            bVar.e.setVisibility(8);
        }
        bVar.h.setOnLongClickListener(new View.OnLongClickListener() { // from class: rsd
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return GroupDataAdapter.this.dQy_(i2, i, view);
            }
        });
    }

    public /* synthetic */ void dQw_(int i, int i2, View view) {
        OnItemClickListener onItemClickListener = this.e;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClickListener(i, i2);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dQx_(b bVar, int i, int i2, CompoundButton compoundButton, boolean z) {
        if (this.e != null) {
            bVar.f10424a.setChecked(z);
            this.e.onItemClickListener(i, i2);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ boolean dQy_(int i, int i2, View view) {
        this.j.onLongClickListener(i, i2);
        return true;
    }

    public void d(boolean z) {
        if (z) {
            this.c = this.g;
        } else {
            this.c = 0;
        }
        Iterator<List<Boolean>> it = this.d.iterator();
        while (it.hasNext()) {
            List<Boolean> next = it.next();
            for (int i = 0; i < next.size(); i++) {
                next.set(i, Boolean.valueOf(z));
            }
        }
        notifyDataSetChanged();
    }

    public void b(OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    public void c(OnLongClickListener onLongClickListener) {
        this.j = onLongClickListener;
    }

    public void d(int i) {
        this.h = i;
        if (i == 1) {
            d(false);
        } else {
            notifyDataSetChanged();
        }
    }

    public void a() {
        this.d.clear();
        if (koq.b(this.b)) {
            return;
        }
        for (rsg rsgVar : this.b) {
            if (rsgVar != null) {
                List<PrivacyDataModel> d = rsgVar.d();
                if (d != null) {
                    ArrayList arrayList = new ArrayList(10);
                    for (int i = 0; i < d.size(); i++) {
                        arrayList.add(false);
                    }
                    this.d.add(arrayList);
                } else {
                    this.d.add(new ArrayList(10));
                }
            }
        }
    }

    public void c(boolean z) {
        this.f10422a = z;
        notifyDataSetChanged();
    }

    public void a(int i, int i2) {
        if (koq.b(this.d, i)) {
            LogUtil.c("PrivacyExpandViewAdapter", "switchCheckStatus mCheckList isOutOfBounds");
            return;
        }
        List<Boolean> list = this.d.get(i);
        if (koq.b(list, i2)) {
            LogUtil.c("PrivacyExpandViewAdapter", "switchCheckStatus childList isOutOfBounds");
            return;
        }
        boolean booleanValue = list.get(i2).booleanValue();
        if (booleanValue) {
            this.c--;
        } else {
            this.c++;
        }
        list.set(i2, Boolean.valueOf(!booleanValue));
        notifyDataSetChanged();
    }

    public List<PrivacyDataModel> c() {
        ArrayList arrayList = new ArrayList(10);
        ArrayList<List<Boolean>> arrayList2 = this.d;
        if (arrayList2 != null && arrayList2.size() != 0) {
            for (int i = 0; i < this.d.size(); i++) {
                List<Boolean> list = this.d.get(i);
                List<PrivacyDataModel> d = this.b.get(i).d();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (list.get(i2).booleanValue()) {
                        arrayList.add(d.get(i2));
                    }
                }
            }
        }
        return arrayList;
    }

    public int e() {
        return this.c;
    }

    public int b() {
        return this.g;
    }

    private int d(List<rsg> list) {
        int i = 0;
        if (list != null && list.size() != 0) {
            Iterator<rsg> it = list.iterator();
            while (it.hasNext()) {
                i += it.next().d().size();
            }
        }
        return i;
    }

    protected static class c {
        protected View b;
        protected HealthTextView c;
        protected ImageView d;

        protected c(View view) {
            this.c = (HealthTextView) view.findViewById(R.id.privacy_title);
            this.b = view.findViewById(R.id.privacy_title_layout);
            this.d = (ImageView) view.findViewById(R.id.content_item_arrow);
        }
    }

    protected class b {

        /* renamed from: a, reason: collision with root package name */
        protected HealthCheckBox f10424a;
        protected HealthTextView b;
        protected ImageView e;
        protected View f;
        protected View h;
        protected HealthTextView j;

        b(View view) {
            this.b = (HealthTextView) view.findViewById(R.id.privacy_content_title);
            this.j = (HealthTextView) view.findViewById(R.id.privacy_time);
            this.h = view.findViewById(R.id.privacy_item_rl);
            this.f = view.findViewById(R.id.data_line);
            this.e = (ImageView) view.findViewById(R.id.content_item_arrow);
            this.f10424a = (HealthCheckBox) view.findViewById(R.id.content_item_check);
        }

        protected void c(PrivacyDataModel privacyDataModel) {
            this.b.setText(privacyDataModel.getDataTitle());
            if (LanguageUtil.bn(this.b.getContext())) {
                this.b.setAutoTextInfo(10, 1, 1);
            }
            this.j.setText(privacyDataModel.getDataDesc());
        }
    }
}
