package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.tencent.open.apireq.BaseResp;

/* loaded from: classes4.dex */
public class gyi {
    public static int d(int i) {
        int i2;
        if (i != 700001) {
            switch (i) {
                case 400000:
                    i2 = 100;
                    break;
                case 400001:
                    i2 = BaseResp.CODE_NOT_LOGIN;
                    break;
                case 400002:
                    i2 = -3000;
                    break;
                case 400003:
                    i2 = -4000;
                    break;
            }
            LogUtil.a("Track_AiSportExamConstant", "getReadySuccessCode sportType:", Integer.valueOf(i), "successCode:", Integer.valueOf(i2));
            return i2;
        }
        i2 = 0;
        LogUtil.a("Track_AiSportExamConstant", "getReadySuccessCode sportType:", Integer.valueOf(i), "successCode:", Integer.valueOf(i2));
        return i2;
    }
}
