package defpackage;

import com.huawei.health.hwhealthlinkage.linkage.parsedata.LinkageDataHandler;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class duw {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, LinkageDataHandler> f11863a;

    static {
        HashMap hashMap = new HashMap();
        f11863a = hashMap;
        hashMap.put(50004765, new dvj());
        hashMap.put(50004838, new dvk());
        hashMap.put(50004799, new dvz());
        hashMap.put(50004396, new dvi());
        hashMap.put(50004721, new dvc());
        hashMap.put(50004684, new dwa());
        hashMap.put(50004829, new dvd());
        hashMap.put(50004146, new dvh());
        hashMap.put(50004979, new dvr());
        hashMap.put(50004395, new dwb());
        hashMap.put(50004774, new dwd());
        hashMap.put(50004286, new dwf());
        hashMap.put(50004172, new dva());
        hashMap.put(50004881, new duz());
        hashMap.put(50004011, new dvs());
        hashMap.put(50004549, new dwc());
        hashMap.put(50004770, new dvv());
        hashMap.put(50004428, new dvx());
        hashMap.put(50004160, new dvn());
        hashMap.put(50004589, new duy());
        hashMap.put(50004934, new dvp());
        hashMap.put(50004861, new dvy());
        hashMap.put(50004757, new dvt());
        hashMap.put(50004184, new dwe());
        hashMap.put(50004232, new dvw());
        hashMap.put(50004254, new dve());
        hashMap.put(50004872, new dux());
        hashMap.put(50004858, new dvf());
        hashMap.put(50004935, new dvo());
        hashMap.put(50004388, new dvm());
        hashMap.put(50004540, new dvl());
        hashMap.put(50004642, new dvq());
        hashMap.put(50004579, new dvu());
        hashMap.put(50004617, new dvb());
    }

    public static LinkageDataHandler e(int i) {
        LogUtil.a("LINKAGE_HandlerFactory", "getParsor dicId: ", Integer.valueOf(i));
        Map<Integer, LinkageDataHandler> map = f11863a;
        if (map.get(Integer.valueOf(i)) != null) {
            return map.get(Integer.valueOf(i));
        }
        return new dvg();
    }
}
