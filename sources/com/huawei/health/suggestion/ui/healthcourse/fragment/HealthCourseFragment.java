package com.huawei.health.suggestion.ui.healthcourse.fragment;

import android.os.Bundle;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.healthcourse.adapter.HealthCourseAdapter;
import com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter;
import com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes4.dex */
public class HealthCourseFragment extends PolymericDataFragment {
    private Map<String, Object> c;

    public static HealthCourseFragment d(Map<String, Object> map) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("RESOURCE_FRAGMENT_BI", (Serializable) map);
        HealthCourseFragment healthCourseFragment = new HealthCourseFragment();
        healthCourseFragment.setArguments(bundle);
        return healthCourseFragment;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment
    public void initArgument() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.a("Suggestion_HealthCourseFragment", "initArgument, arguments is null.");
        } else {
            this.c = (Map) arguments.getSerializable("RESOURCE_FRAGMENT_BI");
        }
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment
    public PolymericDataAdapter newPolymericDataAdapter() {
        Map<String, Object> map = this.c;
        if (map != null) {
            map.put("labelId", e());
        }
        HealthCourseAdapter healthCourseAdapter = new HealthCourseAdapter(new ArrayList(), this.mRecycleViewFitWorks, R.layout.sug_item_health_all_course_list, this.c);
        healthCourseAdapter.setIsShowSquare(b());
        return healthCourseAdapter;
    }

    private String e() {
        Navigation c = this.mDataViewModel.c(this.mDataViewModel.c());
        return c != null ? c.getName() : "";
    }

    private boolean b() {
        LogUtil.a("Suggestion_HealthCourseFragment", "mDataViewModel.getCurrentNavigation():", Integer.valueOf(this.mDataViewModel.c()));
        Navigation c = this.mDataViewModel.c(this.mDataViewModel.c());
        if (c != null) {
            return c.isDisplaySquareStyle();
        }
        return false;
    }
}
