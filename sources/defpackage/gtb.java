package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gtb {

    /* renamed from: a, reason: collision with root package name */
    private int f12921a;
    private List<gsy.b> b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private gww h;
    private Map<Integer, List<gsy.b>> i;
    private int j;
    private Map<Integer, List<gsy.b>> n;

    private gtb() {
    }

    static class d {
        private static final gtb e = new gtb();
    }

    public static gtb c() {
        return d.e;
    }

    public void d(HashMap<String, Object> hashMap, int i) {
        LogUtil.a("Track_BoltUpdateImageStatus", "updateImageBolt");
        if (!SportSupportUtil.f(i)) {
            LogUtil.h("Track_BoltUpdateImageStatus", "updateImageBolt() current sport type is not support bolt sport");
            return;
        }
        this.h = gsy.e();
        List<gsy.b> a2 = gsy.b().a(false);
        this.b = a2;
        c(hashMap, a2, i);
    }

    private void c(HashMap<String, Object> hashMap, List<gsy.b> list, int i) {
        LogUtil.a("Track_BoltUpdateImageStatus", " setRightTopImage()");
        d(list, true);
        if (SportSupportUtil.b(i)) {
            g();
            d(hashMap, this.f12921a, this.g, this.d, this.j);
        }
        if (i == 259) {
            e();
            d(hashMap, this.f12921a, this.e, this.d, this.c);
        }
    }

    private void e() {
        if (this.f12921a <= 0 || this.e <= 0) {
            LogUtil.a("Track_BoltUpdateImageStatus", "connectBoltDevice");
            gsy.b().c();
        }
    }

    private void g() {
        if (this.f12921a <= 0 || this.g <= 0) {
            LogUtil.a("Track_BoltUpdateImageStatus", "connectRunSportBoltDevice");
            gsy.b().c();
        }
    }

    private void d(List<gsy.b> list, boolean z) {
        b(list);
        d();
        b();
        a();
        if (z) {
            int a2 = a(this.i, -1);
            this.f = a2;
            LogUtil.a("Track_BoltUpdateImageStatus", "getWearingPositionBoltList : mNoWearingBoltDeviceNumber = ", Integer.valueOf(a2));
        }
    }

    public void b(List<gsy.b> list) {
        this.i = gsy.b().a(list, true);
        this.n = gsy.b().a(list, false);
    }

    public int d() {
        this.f12921a = a(this.i, 0);
        int a2 = a(this.n, 0);
        this.d = a2;
        return a2;
    }

    public int b() {
        this.g = a(this.i, 1);
        int a2 = a(this.n, 1);
        this.j = a2;
        return a2;
    }

    public int a() {
        this.e = a(this.i, 2);
        int a2 = a(this.n, 2);
        this.c = a2;
        return a2;
    }

    private void d(HashMap<String, Object> hashMap, int i, int i2, int i3, int i4) {
        Context context = BaseApplication.getContext();
        String e = cwa.e(554, context, context.getPackageName());
        Integer valueOf = Integer.valueOf(R.drawable._2131429805_res_0x7f0b09ad);
        if (i3 == 0 && i4 == 0) {
            if (this.f > 0) {
                hashMap.put("RIGHT_TOP_IMAGE_SECOND", valueOf);
                hashMap.put("RIGHT_TOP_SECOND_BUBBLE", context.getString(R.string._2130840098_res_0x7f020a22, e));
                return;
            } else {
                hashMap.remove("RIGHT_TOP_IMAGE_SECOND");
                hashMap.remove("RIGHT_TOP_SECOND_BUBBLE");
                return;
            }
        }
        if (i == 0 && i2 == 0) {
            hashMap.put("RIGHT_TOP_IMAGE_SECOND", valueOf);
            hashMap.put("RIGHT_TOP_SECOND_BUBBLE", context.getString(R.string._2130840098_res_0x7f020a22, e));
            return;
        }
        if ((i == 0 && i2 > 0) || (i > 0 && i2 == 0)) {
            hashMap.put("RIGHT_TOP_IMAGE_SECOND", Integer.valueOf(R.drawable._2131429806_res_0x7f0b09ae));
            hashMap.put("RIGHT_TOP_SECOND_BUBBLE", context.getString(R.string._2130840098_res_0x7f020a22, e));
        } else {
            if (i <= 0 || i2 <= 0) {
                return;
            }
            hashMap.remove("RIGHT_TOP_SECOND_BUBBLE");
            hashMap.put("RIGHT_TOP_IMAGE_SECOND", Integer.valueOf(R.drawable._2131429807_res_0x7f0b09af));
        }
    }

    private int a(Map<Integer, List<gsy.b>> map, int i) {
        if (map == null) {
            LogUtil.h("Track_BoltUpdateImageStatus", "getBoltNumberOfWears : wearingPositionBoltList is null");
            return 0;
        }
        List<gsy.b> list = map.get(Integer.valueOf(i));
        if (koq.b(list)) {
            LogUtil.h("Track_BoltUpdateImageStatus", "getBoltNumberOfWears : boltDeviceWaistList is empty");
            return 0;
        }
        c(i, list);
        if (koq.b(list)) {
            return 0;
        }
        return list.size();
    }

    private void c(int i, List<gsy.b> list) {
        if (list.size() == 0) {
            LogUtil.h("Track_BoltUpdateImageStatus", "setDefaultSelectedDevice :", " boltDeviceLists size is 0, current wearingPositionWaist is ", Integer.valueOf(i));
            return;
        }
        if (this.h == null) {
            this.h = gsy.e();
        }
        String deviceIdentify = list.get(0).e().getDeviceIdentify();
        if (i == 0) {
            String y = this.h.y();
            boolean d2 = d(list, y);
            if (TextUtils.isEmpty(y) || !d2) {
                this.h.l(deviceIdentify);
            }
        }
        if (i == 1) {
            String ad = this.h.ad();
            boolean d3 = d(list, ad);
            if (TextUtils.isEmpty(ad) || !d3) {
                this.h.o(deviceIdentify);
            }
        }
        if (i == 2) {
            String u = this.h.u();
            boolean d4 = d(list, u);
            if (TextUtils.isEmpty(u) || !d4) {
                this.h.k(deviceIdentify);
            }
        }
    }

    private boolean d(List<gsy.b> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            gsy.b bVar = list.get(i);
            if (bVar != null) {
                arrayList.add(bVar.e().getDeviceIdentify());
            }
        }
        return arrayList.contains(str);
    }

    public void e(Context context, int i) {
        LogUtil.a("Track_BoltUpdateImageStatus", "onClickRightTopImage");
        d(gsy.b().a(true), true);
        if (SportSupportUtil.b(i)) {
            e(context, i, this.j);
        }
        if (i == 259) {
            e(context, i, this.c);
        }
    }

    private void e(Context context, int i, int i2) {
        int i3 = this.d;
        if (i3 > 0 || i2 > 0) {
            BoltCustomDialog.a().b(context, i, (BoltCustomDialog.OnConfirmCallBack) null, 0);
        } else {
            if (i3 + i2 != 0 || this.f <= 0) {
                return;
            }
            BoltCustomDialog.a().e(context, i);
        }
    }
}
