package defpackage;

import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.devicelink.bean.TaskBean;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.R$string;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class bar {

    /* renamed from: a, reason: collision with root package name */
    private static int f306a;

    private static void c() {
        f306a = 0;
    }

    private static boolean a() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        LogUtil.c("HealthLife_HealthLifeDeveloperUtils", "isOpenDeveloperSwitch developerSwitch ", b);
        if ("1".equals(b)) {
            return true;
        }
        c();
        return false;
    }

    private static boolean e() {
        return f306a <= 0;
    }

    public static void c(int i) {
        f306a = i;
    }

    public static String a(List<HealthLifeBean> list) {
        List<Integer> b = b(list);
        if (CollectionUtils.d(b)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : b) {
            if (num != null) {
                sb.append(",");
                sb.append(num);
            }
        }
        String sb2 = sb.toString();
        LogUtil.c("HealthLife_HealthLifeDeveloperUtils", "getNeedAddTaskIdString needAddTaskIdString ", sb2, " sDeviceLinkageTaskListSize ", Integer.valueOf(f306a));
        return sb2;
    }

    public static List<Integer> b(List<HealthLifeBean> list) {
        int id;
        if (e() || CollectionUtils.d(list)) {
            return Collections.emptyList();
        }
        int i = 0;
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && 1 != (id = healthLifeBean.getId()) && 100001 != id) {
                i++;
            }
        }
        if (f306a <= i || !a()) {
            return Collections.emptyList();
        }
        int abs = Math.abs(f306a - i);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < abs; i2++) {
            arrayList.add(Integer.valueOf(i2 + 15));
        }
        LogUtil.c("HealthLife_HealthLifeDeveloperUtils", "getNeedAddTaskIdList list ", arrayList, " sDeviceLinkageTaskListSize ", Integer.valueOf(f306a));
        return arrayList;
    }

    public static void b(List<TaskBean> list, List<Integer> list2) {
        if (e() || CollectionUtils.d(list) || CollectionUtils.d(list2)) {
            return;
        }
        String str = nsf.h(R$string.IDS_life_developer_device_task) + Constants.LINK;
        for (Integer num : list2) {
            if (num != null) {
                TaskBean taskBean = new TaskBean();
                int intValue = num.intValue() * 1000;
                taskBean.setTaskValue(String.valueOf(intValue));
                int intValue2 = num.intValue() + 10000;
                taskBean.setTaskTarget(String.valueOf(intValue2));
                taskBean.setStatusDes(nsf.b(com.huawei.basichealthmodel.R$string.IDS_splash_no_space, UnitUtil.e(intValue, 1, 0), nsf.a(R$plurals.IDS_health_step_local, intValue2, UnitUtil.e(intValue2, 1, 0))));
                taskBean.setTaskName(str + num);
                taskBean.setTimestamp(System.currentTimeMillis());
                taskBean.setTaskId(num.intValue());
                taskBean.setRestStatus(0);
                taskBean.setStatus(0);
                list.add(taskBean);
            }
        }
        LogUtil.c("HealthLife_HealthLifeDeveloperUtils", "processNeedAddTaskList list ", list2, " taskList ", list, " sDeviceLinkageTaskListSize ", Integer.valueOf(f306a));
    }
}
