package defpackage;

import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView;
import com.huawei.up.utils.ErrorCode;

/* loaded from: classes5.dex */
public class jcv {
    public static String c(int i) {
        switch (i) {
            case 100000:
                return MonitorResult.SUCCESS;
            case 100001:
                return "UNKNOW ERROR";
            case ErrorCode.HWID_IS_STOPED /* 100002 */:
                return "NOT SUPPORT THIS REQUEST";
            case 100003:
                return "NO PERMISSION";
            case 100004:
                return "SYSTEM BUSY";
            case AdapterWithBottomView.TYPE_BOTTOM /* 100005 */:
                return "REQUEST FORMAT ERROR";
            case 100006:
                return "REQUEST PARAMETER ERROR";
            case 100007:
                return "MEMORY ERROR";
            case 100008:
            case 100009:
                return "RESPONSE TIMEOUT";
            default:
                switch (i) {
                    case 104001:
                        return "单板不支持OTA升级";
                    case 104002:
                        return "低电量 ";
                    default:
                        return "";
                }
        }
    }
}
