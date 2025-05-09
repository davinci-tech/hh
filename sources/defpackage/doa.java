package defpackage;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class doa {
    public static void e(final Context context, final String str, final SingleDailyMomentContent singleDailyMomentContent, final Map<String, Object> map) {
        if (context == null || str == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: doa.4
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // java.lang.Runnable
            public void run() {
                char c;
                String str2 = str;
                str2.hashCode();
                switch (str2.hashCode()) {
                    case 1485005827:
                        if (str2.equals("11100301")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1485005828:
                        if (str2.equals("11100302")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1485005829:
                        if (str2.equals("11100303")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1485005830:
                        if (str2.equals("11100304")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1485005831:
                        if (str2.equals("11100305")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1485005832:
                        if (str2.equals("11100306")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1485005833:
                        if (str2.equals("11100307")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1485005834:
                        if (str2.equals("11100308")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100301.value(), doa.d(str, singleDailyMomentContent, null), 0);
                        break;
                    case 1:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100302.value(), doa.d(str, singleDailyMomentContent, null), 0);
                        break;
                    case 2:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100303.value(), doa.d(str, singleDailyMomentContent, null), 0);
                        break;
                    case 3:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100304.value(), doa.d(str, singleDailyMomentContent, null), 0);
                        break;
                    case 4:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100305.value(), doa.d(str, singleDailyMomentContent, null), 0);
                        break;
                    case 5:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100306.value(), doa.d(str, singleDailyMomentContent, null), 0);
                        break;
                    case 6:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100307.value(), doa.d(str, singleDailyMomentContent, null), 0);
                        break;
                    case 7:
                        ixx.d().d(context, AnalyticsValue.DAILY_MOMENT_11100308.value(), doa.d(str, singleDailyMomentContent, map), 0);
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Map<String, Object> d(String str, SingleDailyMomentContent singleDailyMomentContent, Map<String, Object> map) {
        char c;
        if (map == null) {
            map = new HashMap<>(16);
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 1485005827:
                if (str.equals("11100301")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1485005828:
                if (str.equals("11100302")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1485005829:
                if (str.equals("11100303")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1485005830:
                if (str.equals("11100304")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1485005831:
                if (str.equals("11100305")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1485005832:
                if (str.equals("11100306")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1485005833:
                if (str.equals("11100307")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1485005834:
                if (str.equals("11100308")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                map.put(ParamConstants.CallbackMethod.ON_SHOW, "1");
                break;
            case 1:
                map.put("click", "1");
                break;
            case 2:
                map.put("close", "1");
                break;
            case 3:
                map.put("refresh", "1");
                break;
            case 4:
                map.put(ParamConstants.CallbackMethod.ON_SHOW, "1");
                if (singleDailyMomentContent != null) {
                    map.put("contentId", singleDailyMomentContent.getContentId());
                    map.put("linkType", singleDailyMomentContent.getCoverLinkType());
                    map.put("linkValue", singleDailyMomentContent.getCoverLinkValue());
                    map.put("customTheme", singleDailyMomentContent.getCustomTheme());
                    map.put("subTheme", singleDailyMomentContent.getSubTheme());
                    break;
                }
                break;
            case 5:
                map.put("click", "1");
                if (singleDailyMomentContent != null) {
                    map.put("contentId", singleDailyMomentContent.getContentId());
                    map.put("linkType", singleDailyMomentContent.getCoverLinkType());
                    map.put("linkValue", singleDailyMomentContent.getCoverLinkValue());
                    map.put("customTheme", singleDailyMomentContent.getCustomTheme());
                    map.put("subTheme", singleDailyMomentContent.getSubTheme());
                    break;
                }
                break;
            case 6:
            case 7:
                map.put("click", "1");
                if (singleDailyMomentContent != null) {
                    map.put("contentId", singleDailyMomentContent.getContentId());
                    map.put("linkType", singleDailyMomentContent.getButtonLinkType());
                    map.put("linkValue", singleDailyMomentContent.getButtonLinkValue());
                    map.put("customTheme", singleDailyMomentContent.getCustomTheme());
                    map.put("subTheme", singleDailyMomentContent.getSubTheme());
                    break;
                }
                break;
        }
        LogUtil.a("BiUtil", "eventId: " + str + "; eventMap: " + map.toString());
        return map;
    }
}
