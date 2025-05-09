package defpackage;

import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class fzn {
    public static moa b(Plan plan, int i) {
        List<moa> planWeekDataList = plan.getPlanWeekDataList();
        if (koq.b(planWeekDataList)) {
            LogUtil.h("Suggestion_PlanDetailCourseUtil", "getPlanWeekData planWeekDataBeanList is empty");
            return new moa();
        }
        for (moa moaVar : planWeekDataList) {
            if (moaVar != null && moaVar.f() == i) {
                return moaVar;
            }
        }
        return new moa();
    }

    public static List<CourseDataBean> b(Plan plan, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        List<mnu> j = b(plan, i).j();
        if (koq.b(j)) {
            LogUtil.h("Suggestion_PlanDetailCourseUtil", "getCourseList planDayDataBeanList is null");
            return arrayList;
        }
        for (mnu mnuVar : j) {
            if (mnuVar != null && i2 == mnuVar.c()) {
                for (CourseDataBean courseDataBean : mnuVar.a()) {
                    if (courseDataBean != null) {
                        arrayList.add(courseDataBean);
                    }
                }
            }
        }
        return arrayList;
    }
}
