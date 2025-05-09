package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jhp {
    public static jhz e(cwe cweVar) {
        jhz jhzVar = new jhz();
        if (cweVar == null) {
            LogUtil.h("FitnessUnTlvData", "tlvFather is null");
            return jhzVar;
        }
        ArrayList arrayList = new ArrayList(16);
        List<cwe> a2 = cweVar.a();
        for (int i = 0; i < a2.size(); i++) {
            List<cwd> e = a2.get(i).e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                String c = e.get(i2).c();
                String e2 = e.get(i2).e();
                if (!TextUtils.isEmpty(c) && !TextUtils.isEmpty(e2)) {
                    int w = CommonUtil.w(e2);
                    if (w == 2) {
                        jhzVar.d(CommonUtil.w(c));
                    } else if (w == 5) {
                        arrayList.add(Integer.valueOf(CommonUtil.w(c)));
                    } else {
                        LogUtil.h("FitnessUnTlvData", "unTlvGetFrameCountByTime default");
                    }
                }
            }
        }
        jhzVar.e(arrayList);
        return jhzVar;
    }

    public static jhv b(cwe cweVar) {
        return jhu.e(cweVar);
    }
}
