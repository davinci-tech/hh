package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.GRSManager;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class pew {
    private static volatile pew b;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private pex f16101a = pex.a();

    private pew() {
    }

    public static pew d() {
        if (b == null) {
            synchronized (e) {
                if (b == null) {
                    b = new pew();
                }
            }
        }
        return b;
    }

    public String e(DeviceInfo deviceInfo) {
        cuw c;
        if (deviceInfo == null) {
            return e() + "/mhw/consumer/cn/community/mhwnews/allcircle/id_10000008";
        }
        int productType = deviceInfo.getProductType();
        if (cvz.c(deviceInfo) && (c = jfu.c(productType)) != null) {
            return c.t();
        }
        if (a().contains(Integer.valueOf(productType))) {
            return e() + "/mhw/consumer/cn/community/mhwnews/allcircle/id_10000008";
        }
        return a(productType);
    }

    private List<Integer> a() {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(5);
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(7);
        arrayList.add(14);
        arrayList.add(13);
        arrayList.add(15);
        arrayList.add(8);
        arrayList.add(12);
        arrayList.add(10);
        arrayList.add(3);
        arrayList.add(11);
        arrayList.add(16);
        arrayList.add(18);
        arrayList.add(19);
        arrayList.add(20);
        arrayList.add(21);
        arrayList.add(44);
        arrayList.add(45);
        return arrayList;
    }

    private String e() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainHuafenUrl");
    }

    private String a(int i) {
        String g = jfu.c(i).g();
        if (g == null || "".equals(g)) {
            LogUtil.a("HuaFenClubInteractor", "use inner url");
            return e() + "/mhw/consumer/cn/community/mhwnews/allcircle/id_10000008";
        }
        if ((BaseApplication.getContext().getString(R$string.huafen_old_host) + "/forum.php?mod=forumdisplay&fid=4301").equals(g)) {
            return e() + "/mhw/consumer/cn/community/mhwnews/allcircle/id_10000008";
        }
        if (i != 78 && i != 57) {
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("HuaFenClubInteractor", String.valueOf(i));
        }
        return g;
    }
}
