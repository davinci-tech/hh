package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import defpackage.ntp;
import java.util.LinkedHashMap;

/* loaded from: classes3.dex */
public class efa {

    /* renamed from: a, reason: collision with root package name */
    public static int f11993a = 0;
    public static int d = 1;
    public static int e = 2;

    public static int d(boolean z) {
        if (z) {
            return d;
        }
        return e;
    }

    public static void b(int i, ThreeCircleView threeCircleView) {
        LinkedHashMap<String, ntp> linkedHashMap = new LinkedHashMap<>();
        if (i == d) {
            d(linkedHashMap);
            threeCircleView.setIsShowTriangle(true);
        } else if (i == e) {
            a(linkedHashMap);
        } else if (i == f11993a) {
            b(linkedHashMap);
        }
        threeCircleView.setIsIconVisible(false);
        threeCircleView.a(linkedHashMap);
    }

    public static boolean c() {
        return ((Integer) BaseActivity.getSafeRegionWidth().first).intValue() > 0 || ((Integer) BaseActivity.getSafeRegionWidth().second).intValue() > 0;
    }

    private static void d(LinkedHashMap<String, ntp> linkedHashMap) {
        linkedHashMap.put("thirdCircle", new ntp.b().c(new int[]{BaseApplication.e().getColor(R.color._2131298856_res_0x7f090a28), BaseApplication.e().getColor(R$color.new_activity_circle_process_second_color), BaseApplication.e().getColor(R$color.new_activity_circle_process_third_color), BaseApplication.e().getColor(R.color._2131298854_res_0x7f090a26)}).b(new int[]{BaseApplication.e().getColor(R.color._2131299256_res_0x7f090bb8), BaseApplication.e().getColor(R.color._2131299255_res_0x7f090bb7)}).e());
        linkedHashMap.put("firstCircle", new ntp.b().c(new int[]{BaseApplication.e().getColor(R.color._2131298862_res_0x7f090a2e), BaseApplication.e().getColor(R$color.new_cal_circle_process_second_color), BaseApplication.e().getColor(R$color.new_cal_circle_process_third_color), BaseApplication.e().getColor(R.color._2131298860_res_0x7f090a2c)}).b(new int[]{BaseApplication.e().getColor(R.color._2131297705_res_0x7f0905a9), BaseApplication.e().getColor(R.color._2131297704_res_0x7f0905a8)}).e());
        linkedHashMap.put("secondCircle", new ntp.b().c(new int[]{BaseApplication.e().getColor(R.color._2131298872_res_0x7f090a38), BaseApplication.e().getColor(R$color.new_time_circle_process_second_color), BaseApplication.e().getColor(R$color.new_time_circle_process_third_color), BaseApplication.e().getColor(R.color._2131298870_res_0x7f090a36)}).b(new int[]{BaseApplication.e().getColor(R.color._2131299051_res_0x7f090aeb), BaseApplication.e().getColor(R.color._2131299050_res_0x7f090aea)}).e());
    }

    private static void a(LinkedHashMap<String, ntp> linkedHashMap) {
        linkedHashMap.put("firstCircle", new ntp.b().c(new int[]{BaseApplication.e().getColor(R.color._2131299154_res_0x7f090b52), BaseApplication.e().getColor(R.color._2131299153_res_0x7f090b51)}).b(new int[]{BaseApplication.e().getColor(R.color._2131299152_res_0x7f090b50), BaseApplication.e().getColor(R.color._2131299151_res_0x7f090b4f)}).e());
        linkedHashMap.put("secondCircle", new ntp.b().c(new int[]{BaseApplication.e().getColor(R.color._2131298872_res_0x7f090a38), BaseApplication.e().getColor(R.color._2131298870_res_0x7f090a36)}).b(new int[]{BaseApplication.e().getColor(R.color._2131299051_res_0x7f090aeb), BaseApplication.e().getColor(R.color._2131299050_res_0x7f090aea)}).e());
        linkedHashMap.put("thirdCircle", new ntp.b().c(new int[]{BaseApplication.e().getColor(R.color._2131296457_res_0x7f0900c9), BaseApplication.e().getColor(R.color._2131296456_res_0x7f0900c8)}).b(new int[]{BaseApplication.e().getColor(R.color._2131296455_res_0x7f0900c7), BaseApplication.e().getColor(R.color._2131296454_res_0x7f0900c6)}).e());
    }

    private static void b(LinkedHashMap<String, ntp> linkedHashMap) {
        ntp e2 = new ntp.b().c(new int[]{BaseApplication.e().getColor(R.color._2131299296_res_0x7f090be0), BaseApplication.e().getColor(R.color._2131299296_res_0x7f090be0)}).b(new int[]{BaseApplication.e().getColor(R.color._2131299257_res_0x7f090bb9), BaseApplication.e().getColor(R.color._2131299257_res_0x7f090bb9)}).e();
        linkedHashMap.put("firstCircle", e2);
        linkedHashMap.put("secondCircle", e2);
        linkedHashMap.put("thirdCircle", e2);
    }
}
