package defpackage;

import android.text.TextUtils;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import java.util.Map;

/* loaded from: classes3.dex */
public class bvh {
    public static void d(String str, final IBaseResponseCallback iBaseResponseCallback) {
        bvl bvlVar = new bvl();
        bvlVar.e(jdl.q(System.currentTimeMillis()));
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        Map<String, Object> body = healthDataCloudFactory.getBody(bvlVar);
        if (!TextUtils.isEmpty(str)) {
            body.put("toDoType", str);
        }
        lqi.d().b(bvlVar.getUrl() + "/list", healthDataCloudFactory.getHeaders(), lql.b(body), bvj.class, new ResultCallback<bvj>() { // from class: bvh.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(bvj bvjVar) {
                LogUtil.a("CalBalanceTodoManager", "getTodoList success. data: ", bvjVar.getResultCode());
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(bvjVar.getResultCode().intValue(), bvjVar.e());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("CalBalanceTodoManager", "getTodoList onFailure. throwable: ", th.toString());
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(-1, th.toString());
                }
            }
        });
    }

    public static void e(gka gkaVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (gkaVar == null) {
            LogUtil.h("CalBalanceTodoManager", "updateTodoInfo todoModel is null");
            return;
        }
        LogUtil.a("CalBalanceTodoManager", "updateTodoInfo todo name = ", gkaVar.n());
        bvl bvlVar = new bvl();
        bvlVar.e(jdl.q(System.currentTimeMillis()));
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        Map<String, Object> body = healthDataCloudFactory.getBody(bvlVar);
        body.put("toDoId", String.valueOf(gkaVar.b()));
        body.put("title", gkaVar.n());
        body.put("todoContent", gkaVar.m());
        body.put("status", String.valueOf(gkaVar.i()));
        body.put("linkUrl", gkaVar.e());
        body.put(WorkoutRecord.Extend.COURSE_TARGET_VALUE, gkaVar.g());
        body.put("currentValue", gkaVar.c());
        body.put("targetUnit", gkaVar.j());
        lqi.d().b(bvlVar.getUrl() + "/updateToDoContent", healthDataCloudFactory.getHeaders(), lql.b(body), CloudCommonReponse.class, new ResultCallback<CloudCommonReponse>() { // from class: bvh.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                LogUtil.a("CalBalanceTodoManager", "updateTodoInfo success. data: ", cloudCommonReponse.getResultCode());
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(cloudCommonReponse.getResultCode().intValue(), cloudCommonReponse.getResultDesc());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("CalBalanceTodoManager", "updateTodoInfo onFailure. throwable: ", th.toString());
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(-1, th.toString());
                }
            }
        });
    }

    public static void c(gka gkaVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (gkaVar == null) {
            LogUtil.h("CalBalanceTodoManager", "addNewTodoInfo todoModel is null");
            return;
        }
        LogUtil.a("CalBalanceTodoManager", "addNewTodoInfo todo name = ", gkaVar.n());
        bvl bvlVar = new bvl();
        bvlVar.e(jdl.q(System.currentTimeMillis()));
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        Map<String, Object> body = healthDataCloudFactory.getBody(bvlVar);
        body.put("toDoType", String.valueOf(gkaVar.k()));
        body.put("title", gkaVar.n());
        body.put("todoContent", gkaVar.m());
        body.put("status", String.valueOf(gkaVar.i()));
        body.put("linkUrl", gkaVar.e());
        body.put(WorkoutRecord.Extend.COURSE_TARGET_VALUE, gkaVar.g());
        body.put("currentValue", gkaVar.c());
        body.put("targetUnit", gkaVar.j());
        body.put("requestId", Long.valueOf(System.currentTimeMillis()));
        lqi.d().b(bvlVar.getUrl() + "/addToDoContent", healthDataCloudFactory.getHeaders(), lql.b(body), bvk.class, new ResultCallback<bvk>() { // from class: bvh.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(bvk bvkVar) {
                LogUtil.a("CalBalanceTodoManager", "addNewTodoInfo success. data: ", bvkVar.getResultCode());
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(bvkVar.getResultCode().intValue(), bvkVar.getResultDesc());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("CalBalanceTodoManager", "addNewTodoInfo onFailure. throwable: ", th.toString());
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(-1, th.toString());
                }
            }
        });
    }
}
