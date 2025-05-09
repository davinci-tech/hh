package defpackage;

import android.app.Activity;
import com.huawei.featureuserprofile.todo.TodoJsApi;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.todo.api.TodoDataApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwbasemgr.IBaseResponseCallback;

@ApiDefine(uri = TodoDataApi.class)
/* loaded from: classes3.dex */
public class buu implements TodoDataApi {
    @Override // com.huawei.health.todo.api.TodoDataApi
    public void getTodoListFromCloud(String str, IBaseResponseCallback iBaseResponseCallback) {
        bvh.d(str, iBaseResponseCallback);
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public void updateTodoInfo(gka gkaVar, IBaseResponseCallback iBaseResponseCallback) {
        bvh.e(gkaVar, iBaseResponseCallback);
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public void addNewTodoInfo(gka gkaVar, IBaseResponseCallback iBaseResponseCallback) {
        bvh.c(gkaVar, iBaseResponseCallback);
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public Class<?> getTodoJsClass() {
        return TodoJsApi.class;
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public void refreshTodoList(IBaseResponseCallback iBaseResponseCallback) {
        bvp.c().a(iBaseResponseCallback);
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public void hideOrShowTodoFloatView(Activity activity, boolean z) {
        bvp.c().vO_(activity, z);
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public void removeTodoFloatView() {
        bvp.c().e();
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public boolean isShowTodo() {
        return bvw.e(BaseApplication.e());
    }

    @Override // com.huawei.health.todo.api.TodoDataApi
    public boolean isMainSwitchChecked() {
        return bvw.b(BaseApplication.e());
    }
}
