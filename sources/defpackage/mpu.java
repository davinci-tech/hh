package defpackage;

import com.huawei.hiai.awareness.AwarenessConstants;

/* loaded from: classes6.dex */
public final class mpu {
    public static String b(int i) {
        if (i == 0) {
            return "Success";
        }
        if (i == 500) {
            return "http server internal error";
        }
        if (i == 503) {
            return "server is temporarily inaccessible";
        }
        if (i == 30000008) {
            return "reach max authorization limit";
        }
        switch (i) {
            case AwarenessConstants.ERROR_NO_PERMISSION_CODE /* 200001 */:
                return "Connection time out";
            case AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE /* 200002 */:
                return "Server disconnect";
            case AwarenessConstants.ERROR_TIMEOUT_CODE /* 200003 */:
                return "Internet connection failed. Please check your network settings and try again.";
            case AwarenessConstants.ERROR_UNKNOWN_CODE /* 200004 */:
                return "args valid";
            case AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE /* 200005 */:
                return "Canceled";
            default:
                switch (i) {
                    case 30000001:
                        return "reach max quota limit";
                    case 30000002:
                        return "reach max follow limit";
                    default:
                        return "";
                }
        }
    }
}
