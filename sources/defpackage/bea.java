package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.codevalueplatform.coderule.bean.basebean.IntentInfo;
import com.huawei.codevalueplatform.coderule.bean.basebean.Rule;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes3.dex */
public class bea {
    public Intent pJ_(String str, List<Rule> list) {
        Intent py_ = py_(str, list);
        if (py_ == null) {
            return null;
        }
        bes.e("CloudCodeRuleJumper", "jump intent is find in rule table");
        py_.addFlags(268435456);
        return py_;
    }

    private Intent py_(String str, List<Rule> list) {
        List<IntentInfo> arrayList;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Rule rule : list) {
            if (rule.getMatchType() == 0) {
                arrayList = a(str, rule);
            } else if (rule.getMatchType() == 1) {
                arrayList = d(str, rule);
            } else if (rule.getMatchType() == 2) {
                arrayList = c(str, rule);
            } else {
                arrayList = new ArrayList<>();
            }
            if (!arrayList.isEmpty()) {
                return pC_(str, b(arrayList));
            }
        }
        return null;
    }

    private Intent pC_(String str, IntentInfo intentInfo) {
        if (intentInfo == null) {
            bes.e("CloudCodeRuleJumper", "startIntent intentInfo is null");
            return null;
        }
        if (intentInfo.getAppType() == 0) {
            return pF_(intentInfo, str);
        }
        if (intentInfo.getAppType() == 2) {
            return pA_(str);
        }
        if (intentInfo.getAppType() == 3) {
            return pI_();
        }
        bes.e("CloudCodeRuleJumper", "default process");
        return null;
    }

    private Intent pF_(IntentInfo intentInfo, String str) {
        boolean c;
        Intent pz_;
        if (TextUtils.isEmpty(intentInfo.getPackageName())) {
            return null;
        }
        boolean isEmpty = TextUtils.isEmpty(intentInfo.getActivity());
        if (!isEmpty && TextUtils.isEmpty(intentInfo.getAppVersion())) {
            return null;
        }
        bes.e("CloudCodeRuleJumper", "check jump isImplicitJump: " + isEmpty);
        if (isEmpty) {
            Intent pD_ = pD_(pB_(intentInfo), intentInfo, str);
            c = pD_ != null ? beu.pL_(bec.e(), pD_) : false;
        } else {
            c = c(intentInfo.getPackageName(), intentInfo.getAppVersion());
        }
        bes.e("CloudCodeRuleJumper", "isJumpValid: " + c);
        if (!c) {
            return pE_(intentInfo);
        }
        if (isEmpty) {
            pz_ = pB_(intentInfo);
        } else {
            pz_ = pz_(intentInfo);
        }
        return pD_(pz_, intentInfo, str);
    }

    private Intent pB_(IntentInfo intentInfo) {
        Intent intent = new Intent(intentInfo.getAction());
        intent.setPackage(intentInfo.getPackageName());
        return intent;
    }

    private Intent pz_(IntentInfo intentInfo) {
        Intent intent = new Intent();
        intent.setAction(intentInfo.getAction());
        intent.setComponent(pG_(intentInfo));
        return intent;
    }

    private Intent pD_(Intent intent, IntentInfo intentInfo, String str) {
        String url = intentInfo.getUrlValueType() == 1 ? intentInfo.getUrl() : str;
        if (url == null) {
            url = "";
        }
        if (!TextUtils.isEmpty(intentInfo.getExtraValueKey())) {
            intent.putExtra(intentInfo.getExtraValueKey(), str);
            bes.e("CloudCodeRuleJumper", "key: " + intentInfo.getExtraValueKey());
        }
        intent.setData(pH_(intentInfo, url));
        intent.putExtra("clientId", bec.c());
        return intent;
    }

    private Intent pE_(IntentInfo intentInfo) {
        if (TextUtils.equals(intentInfo.getPackageName(), "com.huawei.pcassistant")) {
            bes.e("CloudCodeRuleJumper", "pcassistant is not pre install");
            return null;
        }
        if (TextUtils.equals(intentInfo.getPackageName(), Constants.WELINK_PKG_NAME)) {
            bes.e("CloudCodeRuleJumper", "welink is need open with browser");
            return null;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + intentInfo.getPackageName()));
        intent.setPackage("com.huawei.appmarket");
        intent.setFlags(268435456);
        return intent;
    }

    private Uri pH_(IntentInfo intentInfo, String str) {
        if (TextUtils.equals(intentInfo.getPackageName(), "com.huawei.pcassistant")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                bes.d("CloudCodeRuleJumper", "decode URL error");
                return null;
            }
        }
        return Uri.parse(str);
    }

    private ComponentName pG_(IntentInfo intentInfo) {
        if (bet.d() && TextUtils.equals(intentInfo.getPackageName(), "com.huawei.pcassistant")) {
            return new ComponentName("com.hihonor.pcassistant", "com.hihonor.pcassistant.ui.MainActivityEx");
        }
        return new ComponentName(intentInfo.getPackageName(), intentInfo.getActivity());
    }

    private boolean c(String str, String str2) {
        if (beu.d(beu.a(str), str2)) {
            return true;
        }
        bes.e("CloudCodeRuleJumper", "jump activity app limit version is not match");
        return false;
    }

    private Intent pA_(String str) {
        Intent intent = new Intent();
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setData(Uri.parse(str));
        return intent;
    }

    private Intent pI_() {
        return bex.pM_();
    }

    private IntentInfo b(List<IntentInfo> list) {
        if (list.isEmpty()) {
            bes.e("CloudCodeRuleJumper", "getJumpIntent intentInfo list is empty");
            return null;
        }
        IntentInfo a2 = a(e(list));
        if (a2 == null) {
            bes.e("CloudCodeRuleJumper", "app not available");
        }
        return a2;
    }

    private List<IntentInfo> e(List<IntentInfo> list) {
        return (List) new ArrayList(list).stream().filter(new Predicate() { // from class: bdz
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((IntentInfo) obj);
                return nonNull;
            }
        }).sorted(new Comparator() { // from class: beg
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return bea.a((IntentInfo) obj, (IntentInfo) obj2);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ int a(IntentInfo intentInfo, IntentInfo intentInfo2) {
        return intentInfo.getAppType() - intentInfo2.getAppType();
    }

    private IntentInfo a(List<IntentInfo> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            IntentInfo intentInfo = list.get(i);
            if (intentInfo.getAppType() == 2) {
                return intentInfo;
            }
            if (intentInfo.getAppType() == 3 && bec.i()) {
                return intentInfo;
            }
            if (beu.d(bec.e(), intentInfo.getPackageName())) {
                bes.e("CloudCodeRuleJumper", "available packageName:" + intentInfo.getPackageName());
                return intentInfo;
            }
        }
        return null;
    }

    private List<IntentInfo> d(String str, Rule rule) {
        if (str.startsWith(rule.getMatchedStr())) {
            return rule.getIntentInfo();
        }
        return new ArrayList();
    }

    private List<IntentInfo> c(String str, Rule rule) {
        if (str.endsWith(rule.getMatchedStr())) {
            return rule.getIntentInfo();
        }
        return new ArrayList();
    }

    private List<IntentInfo> a(String str, Rule rule) {
        String a2 = a(rule);
        bes.a("CloudCodeRuleJumper", "check deeplink: " + a2);
        if (a2 != null && str.startsWith(a2)) {
            return rule.getIntentInfo();
        }
        return new ArrayList();
    }

    private static String a(Rule rule) {
        String str;
        if (rule == null) {
            return null;
        }
        String d = bev.d(rule.getSchema());
        String d2 = bev.d(rule.getHost());
        String d3 = bev.d(rule.getPort());
        String d4 = bev.d(rule.getPath());
        if (TextUtils.isEmpty(d3)) {
            str = "";
        } else {
            str = ":" + d3;
        }
        String str2 = d + "://" + d2 + str + d4;
        return str2.endsWith("/") ? str2.substring(0, str2.length() - 1) : str2;
    }
}
