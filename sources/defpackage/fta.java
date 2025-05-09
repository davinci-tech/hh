package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.healthcourse.fragment.HealthCourseFragment;
import com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer;
import com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment;
import com.huawei.operation.utils.WebViewHelp;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fta implements PolymericCustomer {

    /* renamed from: a, reason: collision with root package name */
    private String f12638a;
    private String b;
    private int c;
    private String d;
    private int e;
    private String i;

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public List<FilterCondition> initFilterConditions(Navigation navigation) {
        return null;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public boolean isSupportFilter() {
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public PolymericDataFragment createPolymericDataFragment() {
        return HealthCourseFragment.d(acquireFragmentParam());
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public void getPolymericIntentData(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("SKIP_ALL_COURSE_KEY");
            this.f12638a = stringExtra;
            if (stringExtra == null) {
                this.f12638a = "HEALTH_COURSE";
            }
            this.c = intent.getIntExtra("index", 0);
            this.b = intent.getStringExtra(WebViewHelp.BI_KEY_PULL_FROM);
            this.d = intent.getStringExtra("resourceId");
            this.i = intent.getStringExtra("resourceName");
            this.e = CommonUtil.h(intent.getStringExtra("COURSE_PAGE_TYPE"));
        }
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public String getTitleText(Context context) {
        return context.getString(R.string._2130845613_res_0x7f021fad);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public Navigation initActiveNavigation(List<Navigation> list) {
        if (list == null) {
            return null;
        }
        if (this.c != 0) {
            for (Navigation navigation : list) {
                if (navigation.getCategoryId() == this.c) {
                    return navigation;
                }
            }
        }
        return list.get(0);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public Map<String, Object> acquireFragmentParam() {
        HashMap hashMap = new HashMap(3);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, this.b);
        hashMap.put("resourceId", this.d);
        hashMap.put("resourceName", this.i);
        hashMap.put("COURSE_PAGE_TYPE", Integer.valueOf(this.e));
        return hashMap;
    }
}
