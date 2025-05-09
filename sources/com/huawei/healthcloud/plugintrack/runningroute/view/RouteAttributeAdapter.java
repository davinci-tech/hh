package com.huawei.healthcloud.plugintrack.runningroute.view;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.eni;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public class RouteAttributeAdapter extends ArrayAdapter {
    private final List<eni> d;

    public RouteAttributeAdapter(Context context, int i, List<eni> list) {
        super(context, i, list);
        this.d = list;
    }

    public int b(int i) {
        eni eniVar;
        if (!koq.d(this.d, i) || (eniVar = this.d.get(i)) == null) {
            return 0;
        }
        return eniVar.d();
    }

    public int d(int i) {
        if (koq.b(this.d)) {
            LogUtil.h("Track_RouteAttributeAdapter", "mPathAttributeList is empty");
            return 0;
        }
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            eni eniVar = this.d.get(i2);
            if (eniVar != null && eniVar.d() == i) {
                return i2;
            }
        }
        return 0;
    }
}
