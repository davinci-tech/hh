package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.NewMedalDataBean;
import defpackage.koq;
import defpackage.mla;
import defpackage.mlq;
import defpackage.mlr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class MedalExpandableAdapter extends BaseExpandableListAdapter {

    /* renamed from: a, reason: collision with root package name */
    private mlr f8431a;
    private mlq b;
    private Map<String, ArrayList<MedalInfoDesc>> d;
    private Map<Integer, String> e = new HashMap(8);

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

    public MedalExpandableAdapter(Context context, Map<String, ArrayList<MedalInfoDesc>> map, Map<Integer, String> map2) {
        if (map2 == null || map == null) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < map2.size(); i2++) {
            String str = map2.get(Integer.valueOf(i2));
            if (!TextUtils.isEmpty(str) && koq.c(map.get(str))) {
                this.e.put(Integer.valueOf(i), map2.get(Integer.valueOf(i2)));
                i++;
            }
        }
        this.d = map;
        Map<String, MedalInfo> c = mla.e().c(false);
        this.b = new mlq(context, map, this.e, c);
        this.f8431a = new mlr(context, map, this.e, c);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        Map<Integer, String> map = this.e;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        return this.e == null ? 0 : 1;
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public NewMedalDataBean getGroup(int i) {
        NewMedalDataBean newMedalDataBean = new NewMedalDataBean();
        Map<Integer, String> map = this.e;
        if (map != null && this.d != null) {
            String str = map.get(Integer.valueOf(i));
            if (TextUtils.isEmpty(str)) {
                return newMedalDataBean;
            }
            ArrayList<MedalInfoDesc> arrayList = this.d.get(str);
            if (koq.b(arrayList)) {
                return newMedalDataBean;
            }
            ArrayList<MedalInfoDesc> arrayList2 = new ArrayList<>(8);
            if (koq.d(arrayList, 0)) {
                arrayList2.add(arrayList.get(0));
            }
            if (koq.d(arrayList, 1)) {
                arrayList2.add(arrayList.get(1));
            }
            if (koq.d(arrayList, 2)) {
                arrayList2.add(arrayList.get(2));
            }
            NewMedalDataBean.ChildMedalDataBean childMedalDataBean = new NewMedalDataBean.ChildMedalDataBean();
            ArrayList<NewMedalDataBean.ChildMedalDataBean> arrayList3 = new ArrayList<>(8);
            childMedalDataBean.setMedalInfoList(arrayList2);
            arrayList3.add(childMedalDataBean);
            newMedalDataBean.setTitle(str);
            newMedalDataBean.setChildMedalDataList(arrayList3);
        }
        return newMedalDataBean;
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public NewMedalDataBean.ChildMedalDataBean getChild(int i, int i2) {
        NewMedalDataBean.ChildMedalDataBean childMedalDataBean = new NewMedalDataBean.ChildMedalDataBean();
        Map<Integer, String> map = this.e;
        if (map != null && this.d != null) {
            String str = map.get(Integer.valueOf(i));
            if (TextUtils.isEmpty(str)) {
                return childMedalDataBean;
            }
            ArrayList<MedalInfoDesc> arrayList = this.d.get(str);
            if (koq.b(arrayList)) {
                return childMedalDataBean;
            }
            ArrayList<MedalInfoDesc> arrayList2 = new ArrayList<>();
            if (koq.d(arrayList, 0)) {
                arrayList2.add(arrayList.get(0));
            }
            if (koq.d(arrayList, 1)) {
                arrayList2.add(arrayList.get(1));
            }
            if (koq.d(arrayList, 2)) {
                arrayList2.add(arrayList.get(2));
            }
            childMedalDataBean.setMedalInfoList(arrayList2);
        }
        return childMedalDataBean;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        return this.b.clg_(i, z, view);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        return this.f8431a.cle_(i, view);
    }
}
