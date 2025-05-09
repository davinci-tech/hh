package com.huawei.ui.device.adapter;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.jcf;
import defpackage.nym;
import java.util.List;

/* loaded from: classes6.dex */
public class LeftKindAdapter extends BaseRecyclerAdapter<nym> {
    private int b;
    private int d;
    private int e;

    public LeftKindAdapter(List<nym> list) {
        super(list, R.layout.item_all_device_left_kind_recycle);
        this.d = BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b);
        this.e = BaseApplication.getContext().getResources().getColor(R.color._2131299241_res_0x7f090ba9);
        LogUtil.a("LeftKindAdapter", "LeftKindAdapter init");
    }

    public void b(int i) {
        nym nymVar = get(this.b);
        if (nymVar != null) {
            nymVar.b(false);
            notifyItemChanged(this.b);
        }
        nym nymVar2 = get(i);
        if (nymVar2 != null) {
            nymVar2.b(true);
            notifyItemChanged(i);
            this.b = i;
        }
    }

    public int d() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, nym nymVar) {
        if (nymVar != null) {
            recyclerHolder.b(R.id.left_device_kind_text, nymVar.b());
            recyclerHolder.c(R.id.left_device_kind_text, nymVar.a() ? this.d : this.e);
            if (jcf.c()) {
                String b = nymVar.b();
                if (TextUtils.isEmpty(b)) {
                    return;
                }
                jcf.bEJ_(recyclerHolder.cwK_(R.id.left_device_kind_text), b, nymVar.a());
            }
        }
    }
}
