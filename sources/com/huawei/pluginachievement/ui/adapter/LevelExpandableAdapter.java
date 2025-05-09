package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.ExperienceHistoryBean;
import com.huawei.pluginachievement.manager.model.NewLevelDetailDataBean;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jed;
import defpackage.koq;
import defpackage.mct;
import defpackage.mfm;
import defpackage.mlc;
import defpackage.nrt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes9.dex */
public class LevelExpandableAdapter extends BaseExpandableListAdapter {
    private static final Integer[] b;
    private static final Integer[] d;

    /* renamed from: a, reason: collision with root package name */
    private List<NewLevelDetailDataBean> f8428a;
    private Context c;
    private ArrayList<String> e;

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        RelativeLayout f8429a;
        LinearLayout b;
        HealthTextView c;
        HealthTextView d;
        HealthTextView e;
        HealthTextView f;
        ImageView g;
        ImageView h;
        ImageView i;
        View j;
        ImageView k;
        ImageView l;
        ImageView m;
        LinearLayout n;
        ImageView o;
        HealthTextView p;
        LinearLayout r;
        HealthTextView s;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f8430a;
        HealthTextView b;
        ImageView c;
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
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    static {
        Integer valueOf = Integer.valueOf(R.mipmap._2131821358_res_0x7f11032e);
        Integer valueOf2 = Integer.valueOf(R.mipmap._2131821362_res_0x7f110332);
        Integer valueOf3 = Integer.valueOf(R.mipmap._2131821359_res_0x7f11032f);
        Integer valueOf4 = Integer.valueOf(R.mipmap._2131821361_res_0x7f110331);
        b = new Integer[]{valueOf, valueOf2, valueOf3, valueOf4, Integer.valueOf(R.mipmap._2131821360_res_0x7f110330), Integer.valueOf(R.mipmap._2131821357_res_0x7f11032d), valueOf4};
        Integer valueOf5 = Integer.valueOf(R.drawable._2131430777_res_0x7f0b0d79);
        d = new Integer[]{valueOf5, valueOf5, Integer.valueOf(R.drawable._2131430774_res_0x7f0b0d76), Integer.valueOf(R.drawable._2131430776_res_0x7f0b0d78), Integer.valueOf(R.drawable._2131430775_res_0x7f0b0d77), Integer.valueOf(R.drawable._2131430772_res_0x7f0b0d74), Integer.valueOf(R.drawable._2131430773_res_0x7f0b0d75)};
    }

    public LevelExpandableAdapter(Context context, List<ExperienceHistoryBean> list) {
        this.c = context;
        if (list == null) {
            return;
        }
        e(list);
    }

    public void b(List<ExperienceHistoryBean> list) {
        if (koq.b(list)) {
            return;
        }
        e(list);
        notifyDataSetChanged();
    }

    private void e(List<ExperienceHistoryBean> list) {
        this.f8428a = d(list);
        String b2 = mct.b(BaseApplication.getContext(), "levelTypeData");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        this.e = mlc.c(b2);
    }

    private List<NewLevelDetailDataBean> d(List<ExperienceHistoryBean> list) {
        ArrayList arrayList = new ArrayList(8);
        if (koq.b(list)) {
            return arrayList;
        }
        List<String> a2 = a(list);
        List<String> c = c(list);
        for (String str : a2) {
            int a3 = a(list, str);
            NewLevelDetailDataBean newLevelDetailDataBean = new NewLevelDetailDataBean();
            newLevelDetailDataBean.setDate(str);
            newLevelDetailDataBean.setCount(a3);
            newLevelDetailDataBean.setChildLevelDataList(c(list, c, str));
            arrayList.add(newLevelDetailDataBean);
        }
        return arrayList;
    }

    private ArrayList<NewLevelDetailDataBean.ChildLevelDataBean> c(List<ExperienceHistoryBean> list, List<String> list2, String str) {
        ArrayList<NewLevelDetailDataBean.ChildLevelDataBean> arrayList = new ArrayList<>(8);
        if (!koq.b(list) && !koq.b(list2)) {
            Iterator<String> it = list2.iterator();
            while (true) {
                boolean z = true;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                for (ExperienceHistoryBean experienceHistoryBean : list) {
                    if (str.equals(experienceHistoryBean.getMonthOfYear()) && next.equals(experienceHistoryBean.getDayOfMonth())) {
                        NewLevelDetailDataBean.ChildLevelDataBean childLevelDataBean = new NewLevelDetailDataBean.ChildLevelDataBean();
                        childLevelDataBean.setEachFirstChildTitle(z);
                        childLevelDataBean.setEndChild(false);
                        childLevelDataBean.setFirstChild(false);
                        childLevelDataBean.setDate(next);
                        childLevelDataBean.setName(experienceHistoryBean.getReasonDesc());
                        childLevelDataBean.setTime(experienceHistoryBean.getTime());
                        childLevelDataBean.setNewLevel(experienceHistoryBean.getNewLevel());
                        childLevelDataBean.setValue(experienceHistoryBean.getExperienceValue());
                        arrayList.add(childLevelDataBean);
                        z = false;
                    }
                }
            }
            if (koq.d(arrayList, 0)) {
                arrayList.get(0).setFirstChild(true);
            }
            if (koq.d(arrayList, arrayList.size() - 1)) {
                arrayList.get(arrayList.size() - 1).setEndChild(true);
            }
        }
        return arrayList;
    }

    private int a(List<ExperienceHistoryBean> list, String str) {
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        for (ExperienceHistoryBean experienceHistoryBean : list) {
            if (str.equals(experienceHistoryBean.getMonthOfYear())) {
                i += experienceHistoryBean.getExperienceValue();
            }
        }
        return i;
    }

    private List<String> a(List<ExperienceHistoryBean> list) {
        ArrayList arrayList = new ArrayList(8);
        if (koq.b(list)) {
            return arrayList;
        }
        for (ExperienceHistoryBean experienceHistoryBean : list) {
            if (!arrayList.contains(experienceHistoryBean.getMonthOfYear())) {
                arrayList.add(experienceHistoryBean.getMonthOfYear());
            }
        }
        return arrayList;
    }

    private List<String> c(List<ExperienceHistoryBean> list) {
        ArrayList arrayList = new ArrayList(8);
        if (koq.b(list)) {
            return arrayList;
        }
        for (ExperienceHistoryBean experienceHistoryBean : list) {
            if (!arrayList.contains(experienceHistoryBean.getDayOfMonth())) {
                arrayList.add(experienceHistoryBean.getDayOfMonth());
            }
        }
        return arrayList;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<NewLevelDetailDataBean> list = this.f8428a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        ArrayList<NewLevelDetailDataBean.ChildLevelDataBean> childLevelDataList;
        if (koq.b(this.f8428a, i) || (childLevelDataList = this.f8428a.get(i).getChildLevelDataList()) == null) {
            return 0;
        }
        return childLevelDataList.size();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public NewLevelDetailDataBean getGroup(int i) {
        return koq.b(this.f8428a, i) ? new NewLevelDetailDataBean() : this.f8428a.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public NewLevelDetailDataBean.ChildLevelDataBean getChild(int i, int i2) {
        NewLevelDetailDataBean.ChildLevelDataBean childLevelDataBean = new NewLevelDetailDataBean.ChildLevelDataBean();
        if (koq.b(this.f8428a, i)) {
            return childLevelDataBean;
        }
        ArrayList<NewLevelDetailDataBean.ChildLevelDataBean> childLevelDataList = this.f8428a.get(i).getChildLevelDataList();
        return koq.b(childLevelDataList, i2) ? childLevelDataBean : childLevelDataList.get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        return ciy_(i, z, view);
    }

    private View ciy_(int i, boolean z, View view) {
        View view2;
        d dVar;
        int i2;
        int i3;
        if (view == null) {
            dVar = new d();
            view2 = LayoutInflater.from(this.c).inflate(R.layout.achieve_level_expandable_father_item, (ViewGroup) null);
            dVar.b = (HealthTextView) mfm.cgM_(view2, R.id.group_name);
            dVar.f8430a = (HealthTextView) mfm.cgM_(view2, R.id.group_count);
            dVar.c = (ImageView) mfm.cgM_(view2, R.id.group_arrow);
            view2.setTag(dVar);
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof d)) {
                return view;
            }
            d dVar2 = (d) tag;
            view2 = view;
            dVar = dVar2;
        }
        if (koq.b(this.f8428a, i)) {
            return view2;
        }
        dVar.b.setText(this.f8428a.get(i).getDate());
        dVar.f8430a.setText(this.c.getResources().getString(R.string._2130841062_res_0x7f020de6, String.valueOf(this.f8428a.get(i).getCount())));
        if (nrt.a(this.c)) {
            i2 = R.mipmap._2131821350_res_0x7f110326;
            i3 = R.mipmap._2131821349_res_0x7f110325;
        } else {
            i2 = R.mipmap._2131821352_res_0x7f110328;
            i3 = R.mipmap._2131821351_res_0x7f110327;
        }
        if (z) {
            dVar.c.setImageResource(i2);
        } else {
            dVar.c.setImageResource(i3);
        }
        return view2;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        return cix_(i, i2, view);
    }

    private View cix_(int i, int i2, View view) {
        b bVar;
        if (view == null) {
            b bVar2 = new b();
            View inflate = LayoutInflater.from(this.c).inflate(R.layout.achieve_level_expandable_child_item, (ViewGroup) null);
            bVar2.c = (HealthTextView) mfm.cgM_(inflate, R.id.child_date);
            bVar2.d = (HealthTextView) mfm.cgM_(inflate, R.id.child_time);
            bVar2.e = (HealthTextView) mfm.cgM_(inflate, R.id.child_dec);
            bVar2.f = (HealthTextView) mfm.cgM_(inflate, R.id.child_value);
            bVar2.i = (ImageView) mfm.cgM_(inflate, R.id.image_line);
            bVar2.g = (ImageView) mfm.cgM_(inflate, R.id.head_down_line);
            bVar2.h = (ImageView) mfm.cgM_(inflate, R.id.head_up_line);
            bVar2.f8429a = (RelativeLayout) mfm.cgM_(inflate, R.id.childLayout);
            bVar2.b = (LinearLayout) mfm.cgM_(inflate, R.id.child_date_layout);
            bVar2.r = (LinearLayout) mfm.cgM_(inflate, R.id.style_layout);
            bVar2.n = (LinearLayout) mfm.cgM_(inflate, R.id.style_bg_layout);
            bVar2.o = (ImageView) mfm.cgM_(inflate, R.id.style_image_dot);
            bVar2.m = (ImageView) mfm.cgM_(inflate, R.id.style_head_down_line);
            bVar2.k = (ImageView) mfm.cgM_(inflate, R.id.style_head_up_line);
            bVar2.p = (HealthTextView) mfm.cgM_(inflate, R.id.style_name);
            bVar2.s = (HealthTextView) mfm.cgM_(inflate, R.id.style_super);
            bVar2.l = (ImageView) mfm.cgM_(inflate, R.id.level_image);
            bVar2.j = mfm.cgM_(inflate, R.id.level_data_line);
            inflate.setTag(bVar2);
            bVar = bVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (tag instanceof b) {
                bVar = (b) tag;
            }
            return view;
        }
        if (koq.b(this.f8428a, i)) {
            return view;
        }
        ArrayList<NewLevelDetailDataBean.ChildLevelDataBean> childLevelDataList = this.f8428a.get(i).getChildLevelDataList();
        if (koq.b(childLevelDataList, i2)) {
            return view;
        }
        b(bVar, childLevelDataList, childLevelDataList.get(i2), i2);
        return view;
    }

    private void b(b bVar, ArrayList<NewLevelDetailDataBean.ChildLevelDataBean> arrayList, NewLevelDetailDataBean.ChildLevelDataBean childLevelDataBean, int i) {
        bVar.c.setText(childLevelDataBean.getDate());
        bVar.d.setText(childLevelDataBean.getTime());
        bVar.e.setText(childLevelDataBean.getName());
        if (childLevelDataBean.isEachFirstChildTitle()) {
            bVar.b.setVisibility(0);
        } else {
            bVar.b.setVisibility(8);
        }
        if (i == 0) {
            bVar.f8429a.setBackgroundResource(R.drawable.level_white_background_up);
            bVar.h.setVisibility(8);
            bVar.g.setVisibility(0);
            bVar.i.setVisibility(0);
        } else if (i == arrayList.size() - 1) {
            bVar.f8429a.setBackgroundResource(R.drawable.level_white_background_bottom);
            bVar.h.setVisibility(0);
            bVar.k.setVisibility(0);
            a(bVar, childLevelDataBean);
        } else {
            bVar.i.setVisibility(0);
            bVar.h.setVisibility(0);
            bVar.g.setVisibility(0);
            bVar.f8429a.setBackgroundResource(R.color._2131296666_res_0x7f09019a);
        }
        if (arrayList.size() == 1) {
            bVar.f8429a.setBackgroundResource(R.drawable.level_white_background);
        }
        bVar.f.setText((childLevelDataBean.getValue() > 0 ? Marker.ANY_NON_NULL_MARKER : Constants.LINK) + childLevelDataBean.getValue());
        d(bVar, childLevelDataBean);
    }

    private void a(b bVar, NewLevelDetailDataBean.ChildLevelDataBean childLevelDataBean) {
        if (childLevelDataBean.getNewLevel() != 0) {
            bVar.i.setVisibility(0);
            bVar.g.setVisibility(0);
            bVar.m.setVisibility(8);
        } else {
            bVar.g.setVisibility(8);
            bVar.i.setVisibility(8);
            bVar.m.setVisibility(0);
        }
    }

    private void d(b bVar, NewLevelDetailDataBean.ChildLevelDataBean childLevelDataBean) {
        if (childLevelDataBean.getNewLevel() != 0) {
            bVar.j.setVisibility(8);
            bVar.r.setVisibility(0);
            if (childLevelDataBean.getNewLevel() > 0) {
                int newLevel = childLevelDataBean.getNewLevel();
                Integer[] numArr = b;
                if (newLevel - 1 < numArr.length) {
                    bVar.n.setBackgroundResource(d[childLevelDataBean.getNewLevel() - 1].intValue());
                    bVar.o.setImageResource(numArr[childLevelDataBean.getNewLevel() - 1].intValue());
                    bVar.l.setImageResource(mlc.d(childLevelDataBean.getNewLevel()));
                }
            }
            bVar.p.setText(mlc.b(childLevelDataBean.getNewLevel()));
            String b2 = jed.b(0.0d, 2, 0);
            if (koq.d(this.e, childLevelDataBean.getNewLevel() + 1)) {
                b2 = this.e.get(childLevelDataBean.getNewLevel() + 1);
            }
            bVar.s.setText(BaseApplication.getContext().getResources().getString(R.string._2130841065_res_0x7f020de9, b2));
            return;
        }
        bVar.j.setVisibility(0);
        bVar.r.setVisibility(8);
    }
}
