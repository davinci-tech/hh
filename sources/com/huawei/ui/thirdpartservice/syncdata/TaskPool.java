package com.huawei.ui.thirdpartservice.syncdata;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.syncdata.SyncTask;
import defpackage.sjt;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedList;

/* loaded from: classes7.dex */
public class TaskPool implements SyncTask.UploadListener {

    /* renamed from: a, reason: collision with root package name */
    private TaskPoolListener f10565a;
    private final LinkedList<SyncTask> b = new LinkedList<>();
    private boolean c = false;
    private String e;

    public interface TaskPoolListener {
        void onTaskFinish();

        void tokenInvalidation();
    }

    TaskPool(String str, TaskPoolListener taskPoolListener) {
        this.e = str;
        sjt.e(str).b();
        this.f10565a = taskPoolListener;
    }

    void d(SyncTask syncTask) {
        this.b.add(syncTask);
        if (this.c) {
            ReleaseLogUtil.e("TaskPool", " data is Sync, no need to execute again");
        } else {
            e();
        }
    }

    void e() {
        SyncTask pop = !this.b.isEmpty() ? this.b.pop() : null;
        if (pop == null) {
            this.c = false;
            this.f10565a.onTaskFinish();
        } else {
            pop.setUploadListener(this);
            this.c = true;
            pop.upload();
        }
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask.UploadListener
    public void tokenInvalidation(SyncTask syncTask) {
        LogUtil.h("TaskPool", syncTask.getTag(), " tokenInvalidation: ");
        syncTask.setUploadListener(null);
        this.b.push(syncTask);
        this.f10565a.tokenInvalidation();
    }

    void b() {
        this.b.clear();
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.SyncTask.UploadListener
    public void uploadStatusCallback(SyncTask syncTask, boolean z) {
        ReleaseLogUtil.e("TaskPool", " uploadStatusCallback,", Boolean.valueOf(z));
        if (z) {
            syncTask.setUploadListener(null);
            LogUtil.a("TaskPool", syncTask.getTag(), " updeLastSyncDataTime: ", Long.valueOf(syncTask.getHealthData().getStartTime()));
            sjt.e(this.e).a(syncTask.getHealthData().getStartTime());
            e();
            return;
        }
        if (!CommonUtil.aa(BaseApplication.e())) {
            syncTask.setUploadListener(null);
            this.b.clear();
            e();
        } else {
            if (syncTask.canRetry()) {
                syncTask.upload();
                return;
            }
            sjt.e(this.e).a(syncTask.getHealthData().getStartTime());
            ReleaseLogUtil.d("TaskPool", syncTask.getTag(), " ignore this task: ", syncTask.toString());
            syncTask.setUploadListener(null);
            syncTask.retryTooManyTimes();
            e();
        }
    }

    boolean b(HiHealthData hiHealthData) {
        return sjt.e(this.e).c(hiHealthData);
    }

    public void a() {
        sjt.e(this.e).c();
    }

    boolean d() {
        return this.b.isEmpty();
    }
}
