package defpackage;

import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mvw {
    private Map<Integer, ShareDataHandlerInterface> d;

    static class a {
        private static final mvw c = new mvw();
    }

    private mvw() {
        HashMap hashMap = new HashMap(4);
        this.d = hashMap;
        hashMap.put(2, new mvy());
        this.d.put(1, new mvx());
        this.d.put(3, new mvu());
        this.d.put(4, new mvv());
    }

    public static final mvw d() {
        return a.c;
    }

    public ShareDataHandlerInterface a(int i) {
        return this.d.get(Integer.valueOf(i));
    }

    public void a() {
        Iterator<ShareDataHandlerInterface> it = this.d.values().iterator();
        while (it.hasNext()) {
            it.next().writeJson();
        }
    }

    public fea c(mvq mvqVar) {
        fea feaVar = new fea();
        for (Map.Entry<Integer, ShareDataHandlerInterface> entry : this.d.entrySet()) {
            int intValue = entry.getKey().intValue();
            List<ShareDataInfo> e = mwd.e(entry.getValue().getShareDataByIdList(mvqVar.a(intValue)));
            mwd.b(e);
            feaVar.a(intValue, e);
        }
        return feaVar;
    }
}
