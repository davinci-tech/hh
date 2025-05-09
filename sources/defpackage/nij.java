package defpackage;

import android.text.TextUtils;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import defpackage.nid;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nij extends nid {
    private boolean c;
    private CountDownLatch d;

    public nij(lmy lmyVar) {
        super(lmyVar);
        this.c = false;
    }

    @Override // defpackage.nid
    public boolean d(lmy lmyVar) {
        LogUtil.a("LightMedalListTask", "onTaskStart");
        return c(lmyVar);
    }

    private boolean c(lmy lmyVar) {
        this.c = false;
        this.d = new CountDownLatch(1);
        h(lmyVar);
        try {
            this.d.await(30L, TimeUnit.MINUTES);
        } catch (InterruptedException unused) {
            LogUtil.b("LightMedalListTask", "LightMedalListSuccess catch InterruptedException");
        }
        return this.c;
    }

    private void h(lmy lmyVar) {
        if (!(lmyVar instanceof lmv)) {
            this.d.countDown();
            return;
        }
        final lmv lmvVar = (lmv) lmyVar;
        if (lmvVar.a() == 2) {
            LogUtil.h("LightMedalListTask", "ReceiveMessage is end");
            this.c = true;
            if (lmvVar.e() != 200) {
                LogUtil.b("LightMedalListTask", "startLightMedalList sync fail! code ", Integer.valueOf(lmvVar.e()));
                this.c = false;
            }
            this.d.countDown();
            return;
        }
        if (lmvVar.e() == 401) {
            LogUtil.h("LightMedalListTask", "startLightMedalList request too fast!");
            b(401, false);
        } else {
            LogUtil.a("LightMedalListTask", "LightTask obtainMedalGainStatus start");
            uu.d().obtainMedalGainStatus(lmvVar.j()).addOnSuccessListener(new OnSuccessListener<ArrayList<mfs>>() { // from class: nij.4
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(ArrayList<mfs> arrayList) {
                    if (!koq.b(arrayList)) {
                        nij.this.b(lmvVar.d(), (ArrayList<mfs>) nij.this.e(arrayList));
                    } else {
                        LogUtil.h("LightMedalListTask", "CurrentMedalGainList is empty.");
                        nij.this.b(201, false);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<mfs> e(ArrayList<mfs> arrayList) {
        ArrayList<mfs> arrayList2 = new ArrayList<>();
        Iterator<mfs> it = arrayList.iterator();
        while (it.hasNext()) {
            mfs next = it.next();
            if (!"".equals(meb.e(mct.b(BaseApplication.getContext(), "_medalTextureDownload"), next.a(), next.d()) ? meb.a(next.a()) : "") && mfl.e(BaseApplication.getContext(), next.a())) {
                arrayList2.add(next);
            }
        }
        LogUtil.a("LightMedalListTask", "check3DList currentMedalGainList=", arrayList2.toString());
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, ArrayList<mfs> arrayList) {
        final String e = spz.e(spz.b, i, arrayList, e());
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("LightMedalListTask", "ZipPath isEmpty!");
            b(201, false);
        } else {
            mxo.d().e(1);
            iww.b().sendComand(c(new File(e)), new nid.e(new ResponseCallback<Integer>() { // from class: nij.1
                @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onResult(int i2, Integer num) {
                    mxo.d().e(0);
                    if (i2 == 207) {
                        nij.this.b(200, true);
                    } else {
                        nij.this.b(201, false);
                    }
                    LogUtil.a("LightMedalListTask", "lightMedalsEnd, isDeleteMedalFile == ", Boolean.valueOf(nij.this.b(e)));
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, final boolean z) {
        lmz a2 = a(1);
        a2.d(i);
        a2.b(this.f15300a);
        iww.b().sendComand(a(a2), new nid.e(new ResponseCallback<Integer>() { // from class: nij.5
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onResult(int i2, Integer num) {
                if (i2 == 207) {
                    nij.this.c = z;
                }
                nij.this.d.countDown();
            }
        }));
    }
}
