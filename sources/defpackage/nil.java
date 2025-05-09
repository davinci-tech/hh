package defpackage;

import android.text.TextUtils;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import defpackage.nid;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nil extends nid {
    private boolean b;
    private boolean c;
    private CountDownLatch e;

    public nil(lmy lmyVar) {
        super(lmyVar);
        this.b = false;
        this.c = false;
    }

    @Override // defpackage.nid
    public boolean d(lmy lmyVar) {
        LogUtil.a("LightMedalTask", "onTaskStart");
        if (lmyVar instanceof lmz) {
            return g(lmyVar);
        }
        return c(lmyVar);
    }

    private boolean g(lmy lmyVar) {
        if (!(lmyVar instanceof lmz)) {
            return false;
        }
        this.b = false;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        lmz b = b(lmyVar);
        if (b == null) {
            LogUtil.h("LightMedalTask", "SendStartSuccess sendMessage is null.");
            return false;
        }
        iww.b().sendComand(a(b), new nid.e(new ResponseCallback<Integer>() { // from class: nil.1
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Integer num) {
                if (i == 207) {
                    nil.this.b = true;
                }
                countDownLatch.countDown();
            }
        }));
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("LightMedalTask", "isSendStartSuccess catch InterruptedException");
        }
        return this.b;
    }

    private boolean c(lmy lmyVar) {
        this.c = false;
        this.e = new CountDownLatch(1);
        h(lmyVar);
        try {
            this.e.await(30L, TimeUnit.MINUTES);
        } catch (InterruptedException unused) {
            LogUtil.b("LightMedalTask", "LightMedalSuccess catch InterruptedException");
        }
        return this.c;
    }

    private void h(lmy lmyVar) {
        if (!(lmyVar instanceof lmv)) {
            this.e.countDown();
            return;
        }
        final lmv lmvVar = (lmv) lmyVar;
        if (lmvVar.a() == 2) {
            LogUtil.h("LightMedalTask", "ReceiveMessage is end");
            this.c = true;
            if (lmvVar.e() != 200) {
                LogUtil.b("LightMedalTask", "startLightMedal sync fail! code ", Integer.valueOf(lmvVar.e()));
                this.c = false;
            }
            this.e.countDown();
            return;
        }
        uu.d().obtainCurrentMedalGainStatus(lmvVar.j()).addOnSuccessListener(new OnSuccessListener<ArrayList<mfs>>() { // from class: nil.3
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ArrayList<mfs> arrayList) {
                if (!koq.b(arrayList)) {
                    nil.this.b(lmvVar.d(), arrayList);
                } else {
                    LogUtil.h("LightMedalTask", "CurrentMedalGainList is empty.");
                    nil.this.e(201, false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, ArrayList<mfs> arrayList) {
        final String e = spz.e(spz.b, i, arrayList, e());
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("LightMedalTask", "ZipPath isEmpty!");
            e(201, false);
        } else {
            iww.b().sendComand(c(new File(e)), new nid.e(new ResponseCallback<Integer>() { // from class: nil.5
                @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onResult(int i2, Integer num) {
                    if (i2 == 207) {
                        nil.this.e(200, true);
                    } else {
                        nil.this.e(201, false);
                    }
                    LogUtil.a("LightMedalTask", "lightMedalsEnd, isDeleteMedalFile == ", Boolean.valueOf(nil.this.b(e)));
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, final boolean z) {
        lmz a2 = a(2);
        a2.d(i);
        a2.b(this.f15300a);
        iww.b().sendComand(a(a2), new nid.e(new ResponseCallback<Integer>() { // from class: nil.4
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResult(int i2, Integer num) {
                if (i2 == 207) {
                    nil.this.c = z;
                }
                nil.this.e.countDown();
            }
        }));
    }
}
