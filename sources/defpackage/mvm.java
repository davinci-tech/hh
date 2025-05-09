package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.pluginsocialshare.cloud.bean.ShareDataCallBack;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes6.dex */
public class mvm {
    private mvl c;
    private int e;

    private mvm() {
        this.c = mvl.b();
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final mvm f15204a = new mvm();
    }

    public static final mvm c() {
        return d.f15204a;
    }

    public void d(int i, final ShareDataCallBack shareDataCallBack) {
        this.e = i;
        final mui muiVar = new mui();
        muiVar.b(Integer.valueOf(i));
        ThreadPoolManager.d().execute(new Runnable() { // from class: mvt
            @Override // java.lang.Runnable
            public final void run() {
                mvm.this.b(muiVar, shareDataCallBack);
            }
        });
    }

    /* synthetic */ void b(mui muiVar, final ShareDataCallBack shareDataCallBack) {
        muj.e(muiVar, new ResultCallback<muk>() { // from class: mvm.4
            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
            }

            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(muk mukVar) {
                if (mukVar.getResultCode().equals(0)) {
                    mvm.this.a(mukVar, mvm.this.c(mukVar), shareDataCallBack);
                } else {
                    shareDataCallBack.onFailure(mukVar.getResultCode().intValue(), "server return error.");
                }
            }
        });
    }

    private static void e(fea feaVar) {
        for (ShareDataInfo shareDataInfo : feaVar.e()) {
            if (shareDataInfo instanceof fdy) {
                fdy fdyVar = (fdy) shareDataInfo;
                fdyVar.setType(1);
                fdyVar.setUrl(fdyVar.b());
                fdyVar.setImageSize(fdyVar.c());
                fdyVar.setPath(mus.b + File.separator + c(shareDataInfo.getUrl()));
            }
        }
        for (ShareDataInfo shareDataInfo2 : feaVar.d()) {
            shareDataInfo2.setType(2);
            shareDataInfo2.setPath(mus.f15185a + File.separator + c(shareDataInfo2.getUrl()));
        }
        e(feaVar.a());
        for (ShareDataInfo shareDataInfo3 : feaVar.b()) {
            shareDataInfo3.setType(4);
            shareDataInfo3.setPath(mus.o + File.separator + c(shareDataInfo3.getUrl()));
        }
    }

    private static void e(List<ShareDataInfo> list) {
        for (ShareDataInfo shareDataInfo : list) {
            if (shareDataInfo instanceof fef) {
                fef fefVar = (fef) shareDataInfo;
                fefVar.setType(3);
                String d2 = fefVar.d();
                int a2 = fefVar.a();
                fefVar.e(fefVar.getUrl());
                fefVar.a(fefVar.getImageSize());
                fefVar.setUrl(d2);
                fefVar.setImageSize(a2);
                if (StringUtils.i(fefVar.d())) {
                    fefVar.d(mus.l + File.separator + c(fefVar.d()));
                }
                fefVar.setPath(mus.l + File.separator + c(fefVar.getUrl()));
            }
        }
    }

    private static String c(String str) {
        if (StringUtils.g(str)) {
            return "";
        }
        String[] split = str.split("\\?")[0].split("/");
        return split.length > 0 ? split[split.length - 1] : "";
    }

    public void a(final int i, ShareDataCallBack shareDataCallBack) {
        try {
            List list = (List) ThreadPoolManager.d().submit(new Callable() { // from class: mvn
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    List d2;
                    d2 = muj.d(i, true);
                    return d2;
                }
            }).get(3L, TimeUnit.SECONDS);
            fea feaVar = new fea();
            if (list != null) {
                feaVar.d().addAll(list);
                shareDataCallBack.onSuccess(0, feaVar);
            } else {
                shareDataCallBack.onFailure(0, AuthInternalPickerConstant.UNKOWN_ERROR);
                LogUtil.b("TAG_ShareDataManager", "getThemeList unknow error");
            }
        } catch (InterruptedException e) {
            e = e;
            shareDataCallBack.onFailure(0, VastAttribute.ERROR);
            LogUtil.b("TAG_ShareDataManager", "getThemeList error :", e.getMessage());
        } catch (ExecutionException e2) {
            e = e2;
            shareDataCallBack.onFailure(0, VastAttribute.ERROR);
            LogUtil.b("TAG_ShareDataManager", "getThemeList error :", e.getMessage());
        } catch (TimeoutException e3) {
            shareDataCallBack.onFailure(0, "timeout error");
            ReleaseLogUtil.c("TAG_ShareDataManager", "getThemeList error :", e3.getMessage());
        }
    }

    public void c(final int i, final ShareDataCallBack shareDataCallBack) {
        this.e = i;
        Callable callable = new Callable() { // from class: mvo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List d2;
                d2 = muj.d(i, false);
                return d2;
            }
        };
        final mui muiVar = new mui();
        muiVar.b(Integer.valueOf(i));
        final Future submit = ThreadPoolManager.d().submit(callable);
        ThreadPoolManager.d().submit(new Callable() { // from class: mvk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return mvm.this.e(muiVar, submit, shareDataCallBack);
            }
        });
    }

    /* synthetic */ Object e(mui muiVar, final Future future, final ShareDataCallBack shareDataCallBack) throws Exception {
        muj.e(muiVar, new ResultCallback<muk>() { // from class: mvm.1
            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
            }

            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(muk mukVar) {
                if (mukVar.getResultCode().equals(0)) {
                    fea c = mvm.this.c(mukVar);
                    try {
                        List list = (List) future.get();
                        if (list != null) {
                            c.d().addAll(list);
                        }
                        mvm.this.a(mukVar, c, shareDataCallBack);
                        return;
                    } catch (InterruptedException | ExecutionException e) {
                        shareDataCallBack.onFailure(0, VastAttribute.ERROR);
                        LogUtil.b("TAG_ShareDataManager", "getThemeAndHealthList error :", e.getMessage());
                        return;
                    }
                }
                shareDataCallBack.onFailure(mukVar.getResultCode().intValue(), "server return error.");
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(muk mukVar, fea feaVar, ShareDataCallBack shareDataCallBack) {
        shareDataCallBack.onSuccess(mukVar.getResultCode().intValue(), feaVar);
        d(2, feaVar);
        d(1, feaVar);
        d(3, feaVar);
        d(4, feaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public fea c(muk mukVar) {
        fea feaVar = new fea();
        feaVar.c(mukVar.e());
        feaVar.d(mukVar.c());
        feaVar.e(mukVar.a());
        feaVar.b(mukVar.b());
        e(feaVar);
        return feaVar;
    }

    public List<ShareDataInfo> b(int i, fea feaVar) {
        List<ShareDataInfo> d2 = feaVar.d(i);
        ArrayList arrayList = new ArrayList();
        for (ShareDataInfo shareDataInfo : d2) {
            if (!(shareDataInfo instanceof mut)) {
                if (b(i, shareDataInfo)) {
                    arrayList.add(shareDataInfo);
                } else {
                    mvl.b().c(this.e, shareDataInfo, shareDataInfo.getType());
                }
            }
        }
        LogUtil.a("TAG_ShareDataManager", "download resource resourceType = ", Integer.valueOf(i), " downloadList.size() = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private void d(int i, fea feaVar) {
        mvq a2 = this.c.a(this.e);
        if (a2 == null) {
            return;
        }
        List<ShareDataInfo> d2 = feaVar.d(i);
        for (ShareDataInfo shareDataInfo : this.c.a(a2.a(i), i).values()) {
            if (!d2.contains(shareDataInfo)) {
                shareDataInfo.setSportTypes(a(this.e, shareDataInfo.getSportTypes()));
                LogUtil.a("TAG_ShareDataManager", "need delete resource:", shareDataInfo.getPath());
                this.c.e(this.e, shareDataInfo, i);
            }
        }
    }

    private String a(int i, String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            return sb.toString();
        }
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            if (CommonUtil.h(split[i2].trim()) != i) {
                if (i2 == split.length - 1) {
                    sb.append(split[i2]);
                } else {
                    sb.append(split[i2]);
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    private boolean b(int i, ShareDataInfo shareDataInfo) {
        ShareDataInfo a2 = this.c.a(i, shareDataInfo.getId());
        if (a2 == null) {
            LogUtil.b("TAG_ShareDataManager", "local not exist.need download resource:", shareDataInfo.getPath());
            return true;
        }
        if (a2.getModifyTime() >= shareDataInfo.getModifyTime()) {
            return false;
        }
        LogUtil.a("TAG_ShareDataManager", "modify time update.need download resource:", shareDataInfo.getPath());
        return true;
    }
}
