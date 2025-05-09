package defpackage;

import android.text.TextUtils;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import defpackage.nid;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nih extends nid {
    private boolean c;
    private CountDownLatch d;
    private boolean e;

    public nih(lmy lmyVar) {
        super(lmyVar);
        this.c = false;
        this.e = false;
    }

    @Override // defpackage.nid
    public boolean d(lmy lmyVar) {
        if (lmyVar instanceof lmz) {
            return h(lmyVar);
        }
        return c(lmyVar);
    }

    private boolean h(lmy lmyVar) {
        if (!(lmyVar instanceof lmz)) {
            return false;
        }
        this.c = false;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        b(lmyVar, countDownLatch);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("LightMedalMessageTask", "isSendStartSuccess catch InterruptedException");
        }
        return this.c;
    }

    private void b(final lmy lmyVar, final CountDownLatch countDownLatch) {
        String c = lmyVar.c();
        LogUtil.a("LightMedalMessageTask", "isSendMedalSuccess medalId == ", c);
        Task<mfo> obtainMedalInfo = uu.d().obtainMedalInfo(c);
        if (obtainMedalInfo == null) {
            LogUtil.h("LightMedalMessageTask", "isSendMedalSuccess getMedalConfig is null");
            countDownLatch.countDown();
            this.c = false;
            return;
        }
        obtainMedalInfo.addOnSuccessListener(new OnSuccessListener<mfo>() { // from class: nih.5
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(mfo mfoVar) {
                if (mfoVar != null) {
                    nih.this.d(lmyVar, mfoVar, countDownLatch);
                } else {
                    LogUtil.h("LightMedalMessageTask", "isSendMedalSuccess medalConfigMap is null");
                    countDownLatch.countDown();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(lmy lmyVar, mfo mfoVar, final CountDownLatch countDownLatch) {
        lmx b = b(lmyVar, mfoVar);
        LogUtil.a("LightMedalMessageTask", "isSendMedalSuccess medalConfig == ", b.toString());
        iws.c().sendComand(d(b), new nid.e(new ResponseCallback<Integer>() { // from class: nih.1
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Integer num) {
                LogUtil.a("LightMedalMessageTask", "sendMessageToDevice errCode：", Integer.valueOf(i));
                if (i == 207) {
                    nih.this.c = true;
                    LogUtil.a("LightMedalMessageTask", "sendMessageToDevice success.");
                }
                countDownLatch.countDown();
            }
        }));
    }

    private lmx b(lmy lmyVar, mfo mfoVar) {
        String str;
        lmx lmxVar = new lmx();
        lmxVar.i(lmyVar.b());
        lmxVar.g(this.f15300a);
        lmxVar.d(5);
        lmxVar.a(1);
        String e = mfoVar.e();
        if (!TextUtils.isEmpty(e)) {
            e = e.replace("/n", System.lineSeparator());
        }
        lmxVar.d(e);
        lmxVar.a(d(mfoVar.b()));
        lmxVar.b(mfoVar.a());
        lmxVar.c(mfoVar.d());
        lmxVar.e(mfoVar.g());
        lmxVar.c(1);
        lmxVar.a("huaweischeme://healthapp/router/achievement?module=medal&pName=com.huawei.health&from=4&id=" + mfoVar.a());
        if (meb.e(mct.b(BaseApplication.getContext(), "_medalTextureDownload"), mfoVar.a(), mfoVar.c())) {
            str = meb.a(mfoVar.a());
            LogUtil.a("LightMedalMessageTask", "Support path=", str);
        } else {
            str = "";
        }
        if ("".equals(str) || !mfl.e(BaseApplication.getContext(), mfoVar.a())) {
            LogUtil.h("LightMedalMessageTask", "Support2D IMG");
            lmxVar.e(0);
        } else {
            LogUtil.h("LightMedalMessageTask", "Support3D medal");
            lmxVar.e(1);
        }
        return lmxVar;
    }

    private long d(String str) {
        LogUtil.a("LightMedalMessageTask", "getGainTime gainTime == ", str);
        try {
            long currentTimeMillis = (TextUtils.isEmpty(str) ? System.currentTimeMillis() : Long.parseLong(str)) / 1000;
            LogUtil.a("LightMedalMessageTask", "getGainTime time == ", Long.valueOf(currentTimeMillis));
            return currentTimeMillis;
        } catch (NumberFormatException unused) {
            LogUtil.b("LightMedalMessageTask", "setGainTime NumberFormatException");
            return 0L;
        }
    }

    private boolean c(lmy lmyVar) {
        this.e = false;
        this.d = new CountDownLatch(1);
        i(lmyVar);
        try {
            this.d.await(30L, TimeUnit.MINUTES);
        } catch (InterruptedException unused) {
            LogUtil.b("LightMedalMessageTask", "LightMedalSuccess catch InterruptedException");
        }
        return this.e;
    }

    private void i(lmy lmyVar) {
        if (!(lmyVar instanceof lmw)) {
            this.d.countDown();
            return;
        }
        lmw lmwVar = (lmw) lmyVar;
        int a2 = lmwVar.a();
        int e = lmwVar.e();
        if (a2 == 2) {
            LogUtil.h("LightMedalMessageTask", "SendMedalSource end! code == ", Integer.valueOf(e));
        } else {
            LogUtil.h("LightMedalMessageTask", "startSendMedalSource.");
            e(lmwVar);
        }
    }

    private void e(lmw lmwVar) {
        String b;
        String j = lmwVar.j();
        if (lmwVar.d() == 3) {
            b = spz.d(lmwVar.b(), j);
        } else {
            b = spz.b(lmwVar.b(), j);
        }
        LogUtil.h("LightMedalMessageTask", "defaultImgPath: ", b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("LightMedalMessageTask", "sendSourceToDevice File unExists ,medalId ", j);
        } else {
            final String b2 = lmwVar.b();
            iws.c().sendComand(c(new File(b)), new nid.e(new ResponseCallback<Integer>() { // from class: nih.2
                @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onResult(int i, Integer num) {
                    LogUtil.h("LightMedalMessageTask", "sendSourceToDevice errCode:", Integer.valueOf(i));
                    if (i == 207) {
                        LogUtil.h("LightMedalMessageTask", "sendSourceToDevice SEND_SUCCEED!session: ", b2);
                    }
                    LogUtil.a("LightMedalMessageTask", "sendSourceToDevice, isDeleteMedalFile == ", Boolean.valueOf(nih.this.b(spz.e)));
                    nih.this.d.countDown();
                }
            }));
        }
    }
}
