package com.huawei.pluginachievement.report.iterface;

import com.huawei.hmf.tasks.Task;
import java.util.List;

/* loaded from: classes6.dex */
public interface PageAccessible {
    Task<Void> calculateAnnualData(int i);

    List<Integer> getAnnualPageArrayByType(String str);

    List<String> getAnnualPageByYear();
}
