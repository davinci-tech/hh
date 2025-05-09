package com.huawei.hms.health;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;

/* loaded from: classes9.dex */
public class aacr implements OnCompleteListener {
    private TaskCompletionSource aab;
    private aacw aaba;

    @Override // com.huawei.hmf.tasks.OnCompleteListener
    public void onComplete(Task task) {
        if (task == null) {
            this.aab.setResult(this.aaba.aab(new NullPointerException()));
        } else if (!task.isSuccessful()) {
            this.aab.setException(task.getException());
        } else {
            this.aab.setResult(this.aaba.aab(task.getResult()));
        }
    }

    public aacr(TaskCompletionSource taskCompletionSource, aacw aacwVar) {
        this.aab = taskCompletionSource;
        this.aaba = aacwVar;
    }
}
