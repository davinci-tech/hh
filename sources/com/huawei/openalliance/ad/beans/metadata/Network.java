package com.huawei.openalliance.ad.beans.metadata;

import android.content.Context;
import android.util.Pair;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.utils.bv;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Network {

    @a
    private List<CellInfo> cellInfo;
    private int type;

    public List<CellInfo> b() {
        return this.cellInfo;
    }

    public int a() {
        return this.type;
    }

    public Network(Context context, boolean z) {
        Pair<Integer, Pair<String, String>> f;
        this.type = 0;
        this.cellInfo = new ArrayList(4);
        this.type = bv.d(context);
        if (!z || (f = bv.f(context)) == null) {
            return;
        }
        CellInfo cellInfo = new CellInfo();
        cellInfo.a(f);
        this.cellInfo.add(cellInfo);
    }

    public Network() {
        this.type = 0;
        this.cellInfo = new ArrayList(4);
    }
}
