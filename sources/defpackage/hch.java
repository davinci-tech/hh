package defpackage;

import com.huawei.health.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hch {
    private static LinkedHashMap<String, Integer> c;

    static {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        c = linkedHashMap;
        linkedHashMap.put("[28]", Integer.valueOf(R.drawable._2131428104_res_0x7f0b0308));
        c.put("[29]", Integer.valueOf(R.drawable._2131428105_res_0x7f0b0309));
        c.put("[30]", Integer.valueOf(R.drawable._2131428107_res_0x7f0b030b));
        c.put("[31]", Integer.valueOf(R.drawable._2131428108_res_0x7f0b030c));
        c.put("[32]", Integer.valueOf(R.drawable._2131428109_res_0x7f0b030d));
        c.put("[33]", Integer.valueOf(R.drawable._2131428110_res_0x7f0b030e));
        c.put("[34]", Integer.valueOf(R.drawable._2131428111_res_0x7f0b030f));
        c.put("[35]", Integer.valueOf(R.drawable._2131428112_res_0x7f0b0310));
        c.put("[36]", Integer.valueOf(R.drawable._2131428113_res_0x7f0b0311));
        c.put("[37]", Integer.valueOf(R.drawable._2131428114_res_0x7f0b0312));
        c.put("[38]", Integer.valueOf(R.drawable._2131428115_res_0x7f0b0313));
        c.put("[39]", Integer.valueOf(R.drawable._2131428116_res_0x7f0b0314));
        c.put("[40]", Integer.valueOf(R.drawable._2131428118_res_0x7f0b0316));
        c.put("[41]", Integer.valueOf(R.drawable._2131428119_res_0x7f0b0317));
        c.put("[42]", Integer.valueOf(R.drawable._2131428120_res_0x7f0b0318));
        c.put("[43]", Integer.valueOf(R.drawable._2131428121_res_0x7f0b0319));
        c.put("[44]", Integer.valueOf(R.drawable._2131428122_res_0x7f0b031a));
        c.put("[45]", Integer.valueOf(R.drawable._2131428123_res_0x7f0b031b));
        c.put("[46]", Integer.valueOf(R.drawable._2131428124_res_0x7f0b031c));
        c.put("[47]", Integer.valueOf(R.drawable._2131428125_res_0x7f0b031d));
        c.put("[48]", Integer.valueOf(R.drawable._2131428126_res_0x7f0b031e));
        c.put("[49]", Integer.valueOf(R.drawable._2131428127_res_0x7f0b031f));
        c.put("[50]", Integer.valueOf(R.drawable._2131428129_res_0x7f0b0321));
        c.put("[51]", Integer.valueOf(R.drawable._2131428130_res_0x7f0b0322));
        c.put("[52]", Integer.valueOf(R.drawable._2131428131_res_0x7f0b0323));
        c.put("[53]", Integer.valueOf(R.drawable._2131428132_res_0x7f0b0324));
        c.put("[54]", Integer.valueOf(R.drawable._2131428133_res_0x7f0b0325));
        c.put("[55]", Integer.valueOf(R.drawable._2131428134_res_0x7f0b0326));
        c.put("[10]", Integer.valueOf(R.drawable._2131428085_res_0x7f0b02f5));
        c.put("[11]", Integer.valueOf(R.drawable._2131428086_res_0x7f0b02f6));
        c.put("[12]", Integer.valueOf(R.drawable._2131428087_res_0x7f0b02f7));
        c.put("[14]", Integer.valueOf(R.drawable._2131428089_res_0x7f0b02f9));
        c.put("[15]", Integer.valueOf(R.drawable._2131428090_res_0x7f0b02fa));
        c.put("[16]", Integer.valueOf(R.drawable._2131428091_res_0x7f0b02fb));
        c.put("[17]", Integer.valueOf(R.drawable._2131428092_res_0x7f0b02fc));
        c.put("[18]", Integer.valueOf(R.drawable._2131428093_res_0x7f0b02fd));
        c.put("[19]", Integer.valueOf(R.drawable._2131428094_res_0x7f0b02fe));
        c.put("[20]", Integer.valueOf(R.drawable._2131428096_res_0x7f0b0300));
        c.put("[21]", Integer.valueOf(R.drawable._2131428097_res_0x7f0b0301));
        c.put("[01]", Integer.valueOf(R.drawable._2131428084_res_0x7f0b02f4));
        c.put("[02]", Integer.valueOf(R.drawable._2131428095_res_0x7f0b02ff));
        c.put("[03]", Integer.valueOf(R.drawable._2131428106_res_0x7f0b030a));
        c.put("[04]", Integer.valueOf(R.drawable._2131428117_res_0x7f0b0315));
        c.put("[05]", Integer.valueOf(R.drawable._2131428128_res_0x7f0b0320));
        c.put("[06]", Integer.valueOf(R.drawable._2131428136_res_0x7f0b0328));
        c.put("[07]", Integer.valueOf(R.drawable._2131428137_res_0x7f0b0329));
        c.put("[08]", Integer.valueOf(R.drawable._2131428138_res_0x7f0b032a));
        c.put("[09]", Integer.valueOf(R.drawable._2131428139_res_0x7f0b032b));
        c.put("[13]", Integer.valueOf(R.drawable._2131428088_res_0x7f0b02f8));
        c.put("[22]", Integer.valueOf(R.drawable._2131428098_res_0x7f0b0302));
        c.put("[23]", Integer.valueOf(R.drawable._2131428099_res_0x7f0b0303));
        c.put("[24]", Integer.valueOf(R.drawable._2131428100_res_0x7f0b0304));
        c.put("[25]", Integer.valueOf(R.drawable._2131428101_res_0x7f0b0305));
        c.put("[26]", Integer.valueOf(R.drawable._2131428102_res_0x7f0b0306));
        c.put("[27]", Integer.valueOf(R.drawable._2131428103_res_0x7f0b0307));
        c.put("[56]", Integer.valueOf(R.drawable._2131428135_res_0x7f0b0327));
    }

    public static int c(String str) {
        Integer num = c.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public static List<hce> d() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Integer> entry : c.entrySet()) {
            arrayList.add(new hce(entry.getKey(), entry.getValue().intValue()));
        }
        return arrayList;
    }
}
