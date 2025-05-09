package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.FilterOption;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.SportAllCourseListFragment;
import com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer;
import com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fsn implements PolymericCustomer {

    /* renamed from: a, reason: collision with root package name */
    private int f12634a;
    private int b;
    private ArrayList<String> c;
    private String i;
    private String j;
    private int f = 0;
    private int e = 0;
    private boolean d = false;
    private boolean h = false;

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public PolymericDataFragment createPolymericDataFragment() {
        return SportAllCourseListFragment.e(this.d, this.c, this.h);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public void getPolymericIntentData(Intent intent) {
        String stringExtra = intent.getStringExtra("SKIP_ALL_COURSE_KEY");
        this.j = stringExtra;
        if (stringExtra == null) {
            this.j = "";
        }
        this.b = CommonUtil.h(intent.getStringExtra("COURSE_PAGE_TYPE"));
        this.f = intent.getIntExtra("index", 0);
        this.e = intent.getIntExtra("COURSE_CATEGORY_KEY", 0);
        int c = jds.c(intent.getStringExtra("KEY_SECOND_CATEGORY_INDEX"), 10);
        if (c > 0) {
            this.f12634a = c;
        }
        this.d = intent.getBooleanExtra("KEY_SUG_COURSE_ADD_STATUS", false);
        this.h = intent.getBooleanExtra("KEY_SUG_COURSE_REPLACE_STATUS", false);
        this.i = intent.getStringExtra("KEY_SUG_COURSE_REPLACE_ID");
        try {
            this.c = intent.getStringArrayListExtra("KEY_SUG_COURSE_ADD_IDS");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("Suggestion_SportPolymericCustomer", "get added course id fail");
        }
        ReleaseLogUtil.e("Suggestion_SportPolymericCustomer", "mPageType:", this.j, "mEquipmentId:", Integer.valueOf(this.f12634a), " mTabIndex:", Integer.valueOf(this.f), " mCourseCategory:", Integer.valueOf(this.e), " mIsAddCourse:", Boolean.valueOf(this.d), " mAddCourseId:", this.c);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public boolean isSupportFilter() {
        return !this.j.equals("RUNNING_COURSE");
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public String getTitleText(Context context) {
        if (TextUtils.equals(this.j, "FITNESS_COURSE")) {
            return context.getString(R.string._2130848519_res_0x7f022b07);
        }
        if (TextUtils.equals(this.j, "RUNNING_COURSE")) {
            return context.getString(R.string._2130848518_res_0x7f022b06);
        }
        return context.getString(R.string._2130845613_res_0x7f021fad);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public Navigation initActiveNavigation(List<Navigation> list) {
        if (koq.b(list)) {
            return null;
        }
        Navigation b = b(list, this.e);
        if (b != null) {
            return b;
        }
        c();
        Navigation b2 = b(list, this.f);
        if (b2 != null) {
            return b2;
        }
        for (Navigation navigation : list) {
            if (navigation.getValue() == 0) {
                return navigation;
            }
        }
        return list.get(0);
    }

    private void c() {
        if (this.f == 13546) {
            this.f = 283;
        }
        if (this.f == 11087) {
            this.f = 137;
        }
    }

    private Navigation b(List<Navigation> list, int i) {
        if (i == 0) {
            return null;
        }
        for (Navigation navigation : list) {
            if (navigation.getCategoryId() == i) {
                return navigation;
            }
        }
        return null;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public List<FilterCondition> initFilterConditions(Navigation navigation) {
        ArrayList arrayList = new ArrayList();
        if (navigation != null && navigation.getFilterConditions() != null) {
            for (FilterCondition filterCondition : navigation.getFilterConditions()) {
                if (filterCondition.getFilterOptions() != null) {
                    a(arrayList, filterCondition, this.f12634a);
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer
    public Map<String, Object> acquireFragmentParam() {
        HashMap hashMap = new HashMap();
        hashMap.put("SKIP_ALL_COURSE_KEY", this.j);
        hashMap.put("KEY_SUG_COURSE_ADD_STATUS", Boolean.valueOf(this.d));
        hashMap.put("KEY_SUG_COURSE_REPLACE_STATUS", Boolean.valueOf(this.h));
        hashMap.put("KEY_SUG_COURSE_ADD_IDS", this.c);
        hashMap.put("KEY_SUG_COURSE_REPLACE_ID", this.i);
        hashMap.put("COURSE_PAGE_TYPE", Integer.valueOf(this.b));
        return hashMap;
    }

    private void a(List<FilterCondition> list, FilterCondition filterCondition, int i) {
        for (FilterOption filterOption : filterCondition.getFilterOptions()) {
            if (i != 0 && filterOption.getCategoryId() == i) {
                list.add(new FilterCondition.Builder().value(filterCondition.getValue()).filterOptions(Collections.singletonList(filterOption)).type(filterCondition.getType()).categoryId(filterCondition.getCategoryId()).build());
                return;
            }
        }
    }
}
