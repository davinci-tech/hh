package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.IMultiDataClientListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.HiDataOperation;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ifw extends HiDataOperation {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13347a;
    private IMultiDataClientListener b;
    private HiTimeInterval c;
    private List<Integer> d;
    private int e;

    ifw(Context context) {
        super(context);
    }

    @Override // com.huawei.hihealthservice.HiDataOperation
    public void execute(ifq ifqVar) {
        if (this.f13347a) {
            igb.c(this.b, isb.e(this.mContext).getHealthClientMergedListByTime(this.d, this.c, this.e));
            return;
        }
        HashMap hashMap = new HashMap(16);
        for (Integer num : new HashSet(this.d)) {
            hashMap.put(num, isb.e(this.mContext).getHealthClientListByTime(num.intValue(), this.c, this.e));
        }
        igb.e(this.b, hashMap);
    }

    public boolean b(List list, HiTimeInterval hiTimeInterval, boolean z, IMultiDataClientListener iMultiDataClientListener) throws RemoteException {
        if (iMultiDataClientListener == null) {
            LogUtil.b("HiH_HiFetchDSByTypOp", "fetchDataSourceByTypes listener is null");
            return false;
        }
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiFetchDSByTypOp", "fetchDataSourceByTypes() appType is invalid");
            a();
            return false;
        }
        if (hiTimeInterval == null) {
            LogUtil.h("HiH_HiFetchDSByTypOp", "fetchDataSourceByTypes timeInterval is null");
            a();
            return false;
        }
        List<Integer> e = e(list);
        if (e.size() != list.size()) {
            LogUtil.h("HiH_HiFetchDSByTypOp", "fetchDataSourceByTypes types contains invalid value(s)");
            a();
            return false;
        }
        this.e = igd.b().a();
        this.d = e;
        this.c = hiTimeInterval;
        this.f13347a = z;
        this.b = iMultiDataClientListener;
        ReleaseLogUtil.e("HiH_HiFetchDSByTypOp", "fetchDataSourceByTypes=", HiJsonUtil.e(list), ",tmIntval=", hiTimeInterval, ",mge=", Boolean.valueOf(z));
        return true;
    }

    private List<Integer> e(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int a2 = CommonUtil.a(it.next().toString(), 10);
            if (a2 != -1) {
                arrayList.add(Integer.valueOf(a2));
            }
        }
        return arrayList;
    }

    private void a() {
        if (this.f13347a) {
            igb.c(this.b, (List) null);
        } else {
            igb.e(this.b, (Map) null);
        }
    }
}
