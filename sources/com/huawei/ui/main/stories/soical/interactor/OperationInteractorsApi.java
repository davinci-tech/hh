package com.huawei.ui.main.stories.soical.interactor;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import defpackage.ceb;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public interface OperationInteractorsApi {
    public static final int ACTIVITY_STATUS_COMING = 0;
    public static final int ACTIVITY_STATUS_OVER = -1;
    public static final int ACTIVITY_STATUS_PROGRESS = 1;
    public static final String OPERATION_ACTIVITY_CURRENT_TIME = "OPERATION_ACTIVITY_CURRENT_TIME";

    JSONObject activityBeanToJSONObject(ceb cebVar);

    ceb expoundOperationActivity(JSONObject jSONObject);

    ArrayList getActivityPageType(ceb cebVar);

    int getActivityStatus(String str, String str2, String str3);

    String getGMTtoLocal(String str);

    String getMaxActivityId(JSONArray jSONArray);

    boolean getNeedUpdateActivity(Context context);

    String getOperationActivityUrl(String str, ceb cebVar);

    void getOperationList(Context context, int i, List<Integer> list, HttpResCallback httpResCallback);

    void getUserActivityInfo(int i, String str, List<String> list, IBaseResponseCallback iBaseResponseCallback);

    void gotoOperationActivityDetail(Context context, String str, ceb cebVar, OperationDetailActivityStartCallback operationDetailActivityStartCallback);

    boolean isHuaweiNoTemplateActivity(ceb cebVar);

    boolean isMatchPage(int i, ArrayList<Integer> arrayList);

    boolean isUserLabelActivity(ceb cebVar, List<String> list);

    boolean judgeVersionSupport(String str);

    void setNeedUpdateActivity(Context context, boolean z);
}
