package com.huawei.health.qrcode;

import android.app.Application;
import android.content.Intent;
import com.huawei.codevalueplatform.coderule.bean.basebean.Rule;
import com.huawei.codevalueplatform.coderule.model.CodeRulesUpdater;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bec;
import defpackage.jdy;
import java.util.List;

/* loaded from: classes3.dex */
public class CodeValueUtil {

    public interface RuleResultCallback {
        void onResponse(String str, a aVar);
    }

    public static void atY_(Application application, String str) {
        bec.pw_(application, str);
    }

    public static void c() {
        LogUtil.a("CodeValueUtil", "setLogEnable logging enabled");
        bec.g();
    }

    public static void d(String str, final RuleResultCallback ruleResultCallback) {
        LogUtil.c("CodeValueUtil", "checkToUpdateRules with url:", str);
        bec.d(str, new CodeRulesUpdater.UpdateCallback() { // from class: com.huawei.health.qrcode.CodeValueUtil.4
            @Override // com.huawei.codevalueplatform.coderule.model.CodeRulesUpdater.UpdateCallback
            public void onUpdate(String str2, List<Rule> list) {
                RuleResultCallback.this.onResponse(str2, new a(list));
            }
        });
    }

    public static a d() {
        return new a(bec.d());
    }

    public static Intent atZ_(String str, a aVar) {
        return bec.px_(str, aVar.b());
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static String f2948a = "CodeValueUtil_RuleInfo";
        private List<Rule> c;

        public a(List<Rule> list) {
            this.c = list;
        }

        public List<Rule> b() {
            return (List) jdy.d(this.c);
        }

        public int c() {
            List<Rule> list = this.c;
            if (list != null) {
                return list.size();
            }
            LogUtil.h(f2948a, "ruleList is null");
            return -1;
        }
    }
}
