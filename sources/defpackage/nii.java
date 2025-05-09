package defpackage;

import android.text.TextUtils;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import defpackage.nid;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nii extends nid {
    private boolean b;
    private CountDownLatch c;
    private boolean d;

    public nii(lmy lmyVar) {
        super(lmyVar);
        this.b = false;
        this.d = false;
    }

    @Override // defpackage.nid
    public boolean d(lmy lmyVar) {
        LogUtil.a("SyncMedalTask", "onTaskStart");
        if (lmyVar instanceof lmz) {
            return c(lmyVar);
        }
        return g(lmyVar);
    }

    private boolean c(lmy lmyVar) {
        if (!(lmyVar instanceof lmz)) {
            return false;
        }
        this.b = false;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        lmz b = b(lmyVar);
        if (b == null) {
            LogUtil.h("SyncMedalTask", "SendStartSuccess sendMessage is null.");
            countDownLatch.countDown();
            return false;
        }
        iww.b().sendComand(a(b), new nid.e(new ResponseCallback<Integer>() { // from class: nii.3
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Integer num) {
                if (i == 207) {
                    nii.this.b = true;
                }
                countDownLatch.countDown();
            }
        }));
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("SyncMedalTask", "isSendStartSuccess catch interruptedException");
        }
        return this.b;
    }

    private boolean g(lmy lmyVar) {
        this.c = new CountDownLatch(1);
        this.d = false;
        f(lmyVar);
        try {
            this.c.await(30L, TimeUnit.MINUTES);
        } catch (InterruptedException unused) {
            LogUtil.b("SyncMedalTask", "SyncMedalsSuccess catch InterruptedException");
        }
        return this.d;
    }

    private void f(lmy lmyVar) {
        if (!(lmyVar instanceof lmv)) {
            this.c.countDown();
            return;
        }
        lmv lmvVar = (lmv) lmyVar;
        if (lmvVar.a() == 2) {
            LogUtil.h("SyncMedalTask", "ReceiveMessage is end");
            this.d = true;
            if (lmvVar.e() != 200) {
                LogUtil.b("SyncMedalTask", "startSyncMedals sync fail! code ", Integer.valueOf(lmvVar.e()));
                this.d = false;
            }
            this.c.countDown();
            return;
        }
        Task<mfo> obtainMedalInfo = uu.d().obtainMedalInfo(lmvVar.i());
        LogUtil.a("SyncMedalTask", "startSyncMedals lastTime Timestamp == ", Long.valueOf(lmvVar.h()));
        if (obtainMedalInfo == null) {
            LogUtil.h("SyncMedalTask", "startSyncMedals getMedalConfig is null");
            this.c.countDown();
        } else {
            obtainMedalInfo.addOnSuccessListener(new OnSuccessListener<mfo>() { // from class: nii.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mfo mfoVar) {
                    if (mfoVar == null) {
                        LogUtil.h("SyncMedalTask", "startSyncMedals medalConfigMap is null");
                        nii.this.c(201, false);
                    } else {
                        LogUtil.a("SyncMedalTask", "startSyncMedals medalConfig == ", mfoVar.toString());
                        nii.this.c(mfoVar);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(mfo mfoVar) {
        if (mfoVar == null) {
            this.c.countDown();
            return;
        }
        String e = e();
        LogUtil.a("SyncMedalTask", "syncingToDevice session == ", e);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("SyncMedalTask", "syncingToDevice receiveFromDevice sync fail!");
            this.c.countDown();
            return;
        }
        final String b = spz.b(mfoVar, e);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("SyncMedalTask", "syncingToDevice zipPath isEmpty, medalid = ", mfoVar.a());
            c(201, false);
        } else {
            LogUtil.a("SyncMedalTask", "syncingToDevice zipPath == ", b);
            mxo.d().e(1);
            iww.b().sendComand(c(new File(b)), new SendCallback() { // from class: nii.1
                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i) {
                    mxo.d().e(0);
                    if (i == 500000) {
                        return;
                    }
                    LogUtil.a("SyncMedalTask", "SendMessageCallback code ", Integer.valueOf(i));
                    if (i != 207) {
                        nii.this.c(201, false);
                    } else {
                        nii.this.c(200, true);
                        LogUtil.a("SyncMedalTask", "lightMedalsEnd, isDeleteMedalFile == ", Boolean.valueOf(nii.this.b(b)));
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j) {
                    if (j % 10 == 0) {
                        LogUtil.a("SyncMedalTask", "SendFileCallback count ", Long.valueOf(j));
                    }
                    mxo.d().e(1);
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str) {
                    LogUtil.a("SyncMedalTask", "onFileTransferReport transferWay: ", str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, final boolean z) {
        lmz a2 = a(3);
        a2.d(i);
        a2.b(this.f15300a);
        iww.b().sendComand(a(a2), new nid.e(new ResponseCallback<Integer>() { // from class: nii.4
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResult(int i2, Integer num) {
                if (i2 == 207) {
                    nii.this.d = z;
                }
                nii.this.c.countDown();
            }
        }));
    }
}
