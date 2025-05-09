package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.marketing.datatype.LogicControlInfo;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.OsType;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class dnj {

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, Boolean> f11729a = new ConcurrentHashMap<>();
    private Context b = BaseApplication.e();

    public List<SingleGridContent> e(List<SingleGridContent> list) {
        ArrayList arrayList = new ArrayList(list.size());
        if (koq.b(list)) {
            LogUtil.h("MarketingLogicControlMgr", "filterContentByLogicRules gridContents is empty");
            return arrayList;
        }
        for (SingleGridContent singleGridContent : list) {
            if (c(singleGridContent)) {
                arrayList.add(singleGridContent);
            }
        }
        return arrayList;
    }

    public boolean c(SingleGridContent singleGridContent) {
        if (singleGridContent == null) {
            return false;
        }
        if (singleGridContent.isShowLogicSwitch()) {
            return b(singleGridContent.getLogicControlCharacter());
        }
        return true;
    }

    public boolean a(ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            return false;
        }
        if (resourceBriefInfo.isShowLogicSwitch()) {
            return b(resourceBriefInfo.getLogicControlCharacter());
        }
        return true;
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        LogUtil.a("MarketingLogicControlMgr", "isValidLogicControlRules logicControlStr:", str);
        LogicControlInfo logicControlInfo = (LogicControlInfo) nrv.b(str, LogicControlInfo.class);
        if (logicControlInfo != null) {
            return b(logicControlInfo.getOsVersion(), false) && b(logicControlInfo.getChipVersion(), true) && d(logicControlInfo.getAbility());
        }
        LogUtil.h("MarketingLogicControlMgr", "isValidLogicControlRules logicControlInfo is null");
        return false;
    }

    public static void d(Map<String, Boolean> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        LogUtil.a("MarketingLogicControlMgr", "addAbility abilityMap:", map.keySet().toString());
        LogUtil.a("MarketingLogicControlMgr", "addAbility abilityMap:", map.values().toString());
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                f11729a.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private boolean d(List<String> list) {
        if (koq.b(list)) {
            return true;
        }
        for (String str : list) {
            if (!f11729a.containsKey(str) || f11729a.get(str) == null || !f11729a.get(str).booleanValue()) {
                LogUtil.h("MarketingLogicControlMgr", "isAbilityEnabled ability:", str, " is not support");
                return false;
            }
        }
        return true;
    }

    private boolean b(LogicControlInfo.VersionControlInfo versionControlInfo, boolean z) {
        String str;
        int i;
        String str2;
        int i2;
        int i3;
        if (versionControlInfo == null) {
            LogUtil.h("MarketingLogicControlMgr", "isVersionValid osOrChipVersion is null");
            return true;
        }
        if (z) {
            str = CommonUtil.a();
            str2 = a(str);
            i = CommonUtil.m(this.b, str.substring(str2.length()));
        } else {
            str = "" + Build.VERSION.SDK_INT;
            i = Build.VERSION.SDK_INT;
            str2 = OsType.ANDROID;
        }
        if (versionControlInfo.getMin() == null || !versionControlInfo.getMin().containsKey(str2)) {
            i2 = 0;
            i3 = 0;
        } else {
            i2 = CommonUtil.b(this.b, versionControlInfo.getMin().get(str2), -1);
            i3 = Integer.MAX_VALUE;
        }
        if (versionControlInfo.getMax() != null && versionControlInfo.getMax().containsKey(str2)) {
            i3 = CommonUtil.b(this.b, versionControlInfo.getMax().get(str2), -1);
        }
        List<String> arrayList = new ArrayList<>();
        List<String> arrayList2 = new ArrayList<>();
        if (versionControlInfo.getSupportList() != null && versionControlInfo.getSupportList().containsKey(OsType.ANDROID)) {
            arrayList = versionControlInfo.getSupportList().get(OsType.ANDROID);
        }
        if (versionControlInfo.getUnSupportList() != null && versionControlInfo.getUnSupportList().containsKey(OsType.ANDROID)) {
            arrayList2 = versionControlInfo.getUnSupportList().get(OsType.ANDROID);
        }
        LogUtil.a("MarketingLogicControlMgr", "isVersionValid curVersion:", Integer.valueOf(i), " minVer:", Integer.valueOf(i2), " maxVer:", Integer.valueOf(i3));
        if (!koq.b(arrayList2) && arrayList2.contains(str)) {
            return false;
        }
        if (i == 0 || i < i2 || i > i3) {
            return !koq.b(arrayList) && arrayList.contains(str);
        }
        return true;
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (!Character.isAlphabetic(c)) {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
