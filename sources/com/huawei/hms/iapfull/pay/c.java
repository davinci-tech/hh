package com.huawei.hms.iapfull.pay;

import android.view.View;
import android.widget.AdapterView;
import com.huawei.hms.iapfull.m0;
import com.huawei.hms.iapfull.network.model.MyPayType;
import com.huawei.hms.iapfull.y0;
import java.util.List;

/* loaded from: classes4.dex */
class c implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ PayActivity f4746a;

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        List list;
        String str;
        m0 m0Var;
        List list2;
        List list3;
        m0 m0Var2;
        m0 m0Var3;
        list = this.f4746a.l;
        if (list != null) {
            m0Var = this.f4746a.c;
            if (m0Var != null) {
                list2 = this.f4746a.l;
                if (list2.size() > i) {
                    list3 = this.f4746a.l;
                    if (1 != ((MyPayType) list3.get(i)).getMode()) {
                        str = "onItemClick, payType not support";
                        y0.b("PayActivity", str);
                    }
                    m0Var2 = this.f4746a.c;
                    m0Var2.a(i);
                    this.f4746a.k = i;
                    m0Var3 = this.f4746a.c;
                    m0Var3.notifyDataSetChanged();
                    return;
                }
                return;
            }
        }
        str = "onItemClick, list or adapter is null";
        y0.b("PayActivity", str);
    }

    c(PayActivity payActivity) {
        this.f4746a = payActivity;
    }
}
