package defpackage;

import com.huawei.featureuserprofile.award.model.SaveInfoItem;
import com.huawei.featureuserprofile.award.presenter.WriteDeliveryInfoContract;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.networkclient.IRequest;
import com.huawei.networkclient.ResultCallback;
import defpackage.bqz;
import defpackage.brf;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class bqz implements WriteDeliveryInfoContract.Presenter {
    private WriteDeliveryInfoContract.View d;

    public bqz(WriteDeliveryInfoContract.View view) {
        this.d = view;
    }

    @Override // com.huawei.featureuserprofile.award.presenter.WriteDeliveryInfoContract.Presenter
    public void saveInfo(int i, SaveInfoItem saveInfoItem, String str, int i2) {
        brf.c cVar = new brf.c();
        cVar.a(i);
        e(cVar, saveInfoItem, str);
        e(cVar, saveInfoItem, i);
        d(cVar.c());
    }

    /* renamed from: bqz$2, reason: invalid class name */
    class AnonymousClass2 implements ResultCallback<String> {
        AnonymousClass2() {
        }

        @Override // com.huawei.networkclient.ResultCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final String str) {
            HandlerExecutor.a(new Runnable() { // from class: brb
                @Override // java.lang.Runnable
                public final void run() {
                    bqz.AnonymousClass2.this.b(str);
                }
            });
        }

        /* synthetic */ void b(String str) {
            if (bqz.this.d != null) {
                bqz.this.d.onModifyDataSuccess(str);
            }
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(final Throwable th) {
            LogUtil.e("WriteDeliveryInfoPresenter", "ACHIEVEMENT HttpRequest onFailure() error: ", th.getMessage());
            HandlerExecutor.a(new Runnable() { // from class: bra
                @Override // java.lang.Runnable
                public final void run() {
                    bqz.AnonymousClass2.this.e(th);
                }
            });
        }

        /* synthetic */ void e(Throwable th) {
            bqz.this.d.onModifyDataFailed(th.getMessage());
        }
    }

    private void d(final brf brfVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bqy
            @Override // java.lang.Runnable
            public final void run() {
                bqz.this.b(brfVar);
            }
        });
    }

    /* synthetic */ void b(brf brfVar) {
        e(brfVar, new AnonymousClass2());
    }

    private void e(brf.c cVar, SaveInfoItem saveInfoItem, String str) {
        cVar.f(saveInfoItem.getName());
        cVar.h(saveInfoItem.getTelephone());
        cVar.i(saveInfoItem.getAddress());
        cVar.j(saveInfoItem.getRemark());
        cVar.g(str);
    }

    private void e(brf.c cVar, SaveInfoItem saveInfoItem, int i) {
        if (i == 1 || i == 20001) {
            cVar.a(saveInfoItem.getActivityId()).e(saveInfoItem.getAwardRecordId());
        } else if (i == 20002) {
            cVar.c(saveInfoItem.getActivityId()).b(saveInfoItem.getAwardRecordId()).d("1");
        } else {
            LogUtil.a("WriteDeliveryInfoPresenter", "the source is error,current don't support");
        }
    }

    private void e(IRequest iRequest, final ResultCallback<String> resultCallback) {
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.e());
        lqi.d().b(iRequest.getUrl(), activityInfoListFactory.getHeaders(), lql.b(activityInfoListFactory.getBody(iRequest)), String.class, new ResultCallback<String>() { // from class: bqz.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                if (str == null) {
                    resultCallback.onFailure(new NullPointerException("the NetWork response is null"));
                }
                resultCallback.onSuccess(str);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }
}
