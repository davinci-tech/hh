package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import java.util.Date;

/* loaded from: classes4.dex */
public class ggf {
    public static String e() {
        int hours = new Date().getHours();
        if (hours >= 0 && hours < 5) {
            return BaseApplication.e().getResources().getString(R.string._2130845000_res_0x7f021d48);
        }
        if (hours >= 5 && hours < 9) {
            return BaseApplication.e().getResources().getString(R.string._2130844810_res_0x7f021c8a);
        }
        if (hours >= 9 && hours < 11) {
            return BaseApplication.e().getResources().getString(R.string._2130844811_res_0x7f021c8b);
        }
        if (hours >= 11 && hours < 13) {
            return BaseApplication.e().getResources().getString(R.string._2130844812_res_0x7f021c8c);
        }
        if (hours >= 13 && hours < 17) {
            return BaseApplication.e().getResources().getString(R.string._2130844813_res_0x7f021c8d);
        }
        if (hours >= 17 && hours < 19) {
            return BaseApplication.e().getResources().getString(R.string._2130844814_res_0x7f021c8e);
        }
        if (hours >= 19 && hours < 21) {
            return BaseApplication.e().getResources().getString(R.string._2130844815_res_0x7f021c8f);
        }
        if (hours >= 21) {
            return BaseApplication.e().getResources().getString(R.string._2130845000_res_0x7f021d48);
        }
        return BaseApplication.e().getResources().getString(R.string._2130844810_res_0x7f021c8a);
    }

    public static String d() {
        int hours = new Date().getHours();
        if ((hours >= 0 && hours < 5) || hours >= 21) {
            return BaseApplication.e().getResources().getString(R.string._2130845001_res_0x7f021d49);
        }
        return BaseApplication.e().getResources().getString(R.string._2130844967_res_0x7f021d27);
    }
}
