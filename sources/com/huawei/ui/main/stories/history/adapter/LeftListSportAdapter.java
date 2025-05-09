package com.huawei.ui.main.stories.history.adapter;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.rdh;
import java.util.List;

/* loaded from: classes7.dex */
public class LeftListSportAdapter extends BaseRecyclerAdapter<rdh> {

    /* renamed from: a, reason: collision with root package name */
    private int f10297a;
    private int b;
    private int e;

    public LeftListSportAdapter(List<rdh> list) {
        super(list, R.layout.item_sport_left_type_recycle);
        this.e = BaseApplication.getContext().getResources().getColor(R.color._2131299237_res_0x7f090ba5);
        this.b = BaseApplication.getContext().getResources().getColor(R.color._2131299241_res_0x7f090ba9);
        LogUtil.a("Track_LeftListAdapter", "LeftListAdapter init");
    }

    public void d(int i) {
        rdh rdhVar = get(this.f10297a);
        if (rdhVar != null) {
            rdhVar.a(false);
            notifyItemChanged(this.f10297a);
        }
        rdh rdhVar2 = get(i);
        if (rdhVar2 != null) {
            rdhVar2.a(true);
            notifyItemChanged(i);
            this.f10297a = i;
        }
    }

    public int a() {
        return this.f10297a;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, rdh rdhVar) {
        if (rdhVar != null) {
            recyclerHolder.b(R.id.left_name_text, rdhVar.a());
            recyclerHolder.c(R.id.left_name_text, rdhVar.e() ? this.e : this.b);
            recyclerHolder.a(R.id.left_view, rdhVar.e() ? 0 : 8);
        }
    }
}
