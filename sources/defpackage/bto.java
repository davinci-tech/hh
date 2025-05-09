package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeleteRouteDetailReq;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.networkclient.cache.ICacheStrategy;
import com.huawei.route.AddRouteDetailReq;
import com.huawei.route.GetRouteDetailReq;
import com.huawei.route.GetRouteDetailRsp;
import com.huawei.route.UpdateRouteDetailReq;
import health.compact.a.LanguageUtil;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class bto {
    public static void e(final int i, final long j, final RouteResultCallBack<GetRouteDetailRsp> routeResultCallBack) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bto.5
            @Override // java.lang.Runnable
            public void run() {
                GetRouteDetailReq getRouteDetailReq = new GetRouteDetailReq();
                getRouteDetailReq.setType(i);
                getRouteDetailReq.setVersion(j);
                if (i == 0) {
                    lqi.d().b(getRouteDetailReq.getUrl(), bto.c().getHeaders(), lql.b(bto.c().getBody(getRouteDetailReq)), GetRouteDetailRsp.class, bto.j(routeResultCallBack));
                } else {
                    lqi.d().d(getRouteDetailReq.getUrl(), bto.c().getHeaders(), lql.b(bto.c().getBody(getRouteDetailReq)), GetRouteDetailRsp.class, bto.e(routeResultCallBack), bto.c(getRouteDetailReq, j));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ResultCallback<GetRouteDetailRsp> j(final RouteResultCallBack<GetRouteDetailRsp> routeResultCallBack) {
        return new ResultCallback<GetRouteDetailRsp>() { // from class: bto.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(GetRouteDetailRsp getRouteDetailRsp) {
                RouteResultCallBack.this.onSuccess(getRouteDetailRsp);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                RouteResultCallBack.this.onFailure(th);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ResultCallback<lqq<GetRouteDetailRsp>> e(final RouteResultCallBack<GetRouteDetailRsp> routeResultCallBack) {
        return new ResultCallback<lqq<GetRouteDetailRsp>>() { // from class: bto.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(lqq<GetRouteDetailRsp> lqqVar) {
                RouteResultCallBack routeResultCallBack2 = RouteResultCallBack.this;
                if (routeResultCallBack2 != null) {
                    routeResultCallBack2.onSuccess(lqqVar.c());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                RouteResultCallBack routeResultCallBack2 = RouteResultCallBack.this;
                if (routeResultCallBack2 != null) {
                    routeResultCallBack2.onFailure(th);
                }
            }
        };
    }

    public static void b(final RouteData routeData, final ResultCallback<CloudCommonReponse> resultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bto.1
            @Override // java.lang.Runnable
            public void run() {
                UpdateRouteDetailReq updateRouteDetailReq = new UpdateRouteDetailReq();
                updateRouteDetailReq.setType(1);
                updateRouteDetailReq.setRouteData(RouteData.this);
                lqi.d().b(updateRouteDetailReq.getUrl(), bto.c().getHeaders(), lql.b(bto.c().getBody(updateRouteDetailReq)), CloudCommonReponse.class, resultCallback);
            }
        });
    }

    public static void a(final List<Long> list, final RouteResultCallBack<CloudCommonReponse> routeResultCallBack) {
        if (koq.b(list)) {
            LogUtil.h("MyRouteInfoManager", "deleteRouteDetail routeVersions is empty");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bto.3
                @Override // java.lang.Runnable
                public void run() {
                    DeleteRouteDetailReq deleteRouteDetailReq = new DeleteRouteDetailReq();
                    deleteRouteDetailReq.setType(1);
                    deleteRouteDetailReq.setRouteVersions(list);
                    lqi.d().b(deleteRouteDetailReq.getUrl(), bto.c().getHeaders(), lql.b(bto.c().getBody(deleteRouteDetailReq)), CloudCommonReponse.class, bto.b(routeResultCallBack));
                }
            });
        }
    }

    public static void d(final List<RouteData> list, final RouteResultCallBack<CloudCommonReponse> routeResultCallBack) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bto.9
            @Override // java.lang.Runnable
            public void run() {
                AddRouteDetailReq addRouteDetailReq = new AddRouteDetailReq();
                addRouteDetailReq.setType(1);
                addRouteDetailReq.setRouteDatas(list);
                lqi.d().b(addRouteDetailReq.getUrl(), bto.c().getHeaders(), lql.b(bto.c().getBody(addRouteDetailReq)), CloudCommonReponse.class, bto.b(routeResultCallBack));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ResultCallback<CloudCommonReponse> b(final RouteResultCallBack<CloudCommonReponse> routeResultCallBack) {
        return new ResultCallback<CloudCommonReponse>() { // from class: bto.8
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                RouteResultCallBack.this.onSuccess(cloudCommonReponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                RouteResultCallBack.this.onFailure(th);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HealthDataCloudFactory c() {
        return new HealthDataCloudFactory(BaseApplication.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ICacheStrategy c(final GetRouteDetailReq getRouteDetailReq, final long j) {
        return new ICacheStrategy() { // from class: bto.6
            @Override // com.huawei.networkclient.cache.ICacheStrategy
            public int getCacheStrategy() {
                return 1;
            }

            @Override // com.huawei.networkclient.cache.ICacheStrategy
            public boolean needEncrypt() {
                return false;
            }

            @Override // com.huawei.networkclient.cache.ICacheStrategy
            public Long getDiskTimeOut() {
                return 1L;
            }

            @Override // com.huawei.networkclient.cache.ICacheStrategy
            public TimeUnit getDiskTimeUnit() {
                return TimeUnit.MINUTES;
            }

            @Override // com.huawei.networkclient.cache.ICacheStrategy
            public String getKey() {
                return lqs.c(GetRouteDetailReq.this.getUrl(), LanguageUtil.e(), String.valueOf(j));
            }
        };
    }
}
