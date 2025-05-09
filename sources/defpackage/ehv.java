package defpackage;

import com.huawei.health.marketing.request.ArticleInfoApi;
import com.huawei.health.marketing.request.ArticleInfoReq;
import com.huawei.health.marketing.request.ArticleInfoRsp;
import com.huawei.health.marketing.request.GlobalSearchFactory;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class ehv {
    private static volatile ehv c;
    private ArticleInfoApi b = (ArticleInfoApi) lqi.d().b(ArticleInfoApi.class);

    /* renamed from: a, reason: collision with root package name */
    private ParamsFactory f12021a = new GlobalSearchFactory(BaseApplication.getContext());
    private String d = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);

    private ehv() {
    }

    public static ehv d() {
        if (c == null) {
            synchronized (ehv.class) {
                if (c == null) {
                    c = new ehv();
                }
            }
        }
        return c;
    }

    public void a(long j, IBaseResponseCallback iBaseResponseCallback) {
        Response<ArticleInfoRsp> execute;
        ArticleInfoReq articleInfoReq = new ArticleInfoReq();
        try {
            HashMap hashMap = new HashMap(10);
            hashMap.put("articleId", String.valueOf(j));
            hashMap.put("timeZone", jdl.q(System.currentTimeMillis()));
            execute = this.b.getArticleInfo(articleInfoReq.getUrl(), this.f12021a.getHeaders(), lql.b(hashMap)).execute();
        } catch (IOException e) {
            LogUtil.b("ArticleInfoManager", "getVipInfo fail, ", e.getMessage());
        }
        if (execute == null) {
            LogUtil.h("ArticleInfoManager", "response is null");
            iBaseResponseCallback.d(-1, null);
        } else if (!execute.isOK()) {
            LogUtil.h("ArticleInfoManager", "response result == ", Integer.valueOf(execute.getCode()));
            iBaseResponseCallback.d(-1, null);
        } else {
            LogUtil.a("ArticleInfoManager", "response is OK.");
            iBaseResponseCallback.d(0, execute.getBody().getArticleInfo());
        }
    }
}
