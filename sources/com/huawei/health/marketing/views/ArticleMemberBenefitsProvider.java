package com.huawei.health.marketing.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.ArticleImage;
import com.huawei.health.marketing.datatype.ArticleInfo;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.datatypes.MemberTypeInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ehv;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrv;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes8.dex */
public class ArticleMemberBenefitsProvider extends BaseKnitDataProvider<HashMap<Integer, List<Drawable>>> {

    /* renamed from: a, reason: collision with root package name */
    private static final Integer f2819a = 1;
    private SectionBean f;
    private HashMap<Integer, List<Drawable>> c = new HashMap<>();
    private HashMap<Integer, Integer> e = new HashMap<>();
    private List<Integer> g = new ArrayList(10);
    private List<ArticleImage> b = new ArrayList(10);
    private ReentrantReadWriteLock d = new ReentrantReadWriteLock();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        d(context, (HashMap<String, Object>) hashMap, (HashMap<Integer, List<Drawable>>) obj);
    }

    public void d(Context context, HashMap<String, Object> hashMap, HashMap<Integer, List<Drawable>> hashMap2) {
        this.c = hashMap2;
        hashMap.put("CARD_TITLE_INDEX", this.g);
        hashMap.put("GRID_ORIENTATION", this.e);
        hashMap.put("CARD_COMBIN_IMAGES", this.c);
        hashMap.put("ARTICLE_IMAGE_LIST", this.b);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, final SectionBean sectionBean) {
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: com.huawei.health.marketing.views.ArticleMemberBenefitsProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    ArticleMemberBenefitsProvider.this.loadData(context, sectionBean);
                }
            });
        } else {
            this.f = sectionBean;
            e(context);
        }
    }

    private void e(final Context context) {
        ((VipApi) Services.c("vip", VipApi.class)).getVipType(new IBaseResponseCallback() { // from class: com.huawei.health.marketing.views.ArticleMemberBenefitsProvider.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                int a2 = ArticleMemberBenefitsProvider.this.a(context, obj);
                LogUtil.a("ArticleMemberBenefitsProvider", "articleId: ", Integer.valueOf(a2));
                ArticleMemberBenefitsProvider.this.b(context, a2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Context context, int i) {
        ehv.d().a(i, new IBaseResponseCallback() { // from class: com.huawei.health.marketing.views.ArticleMemberBenefitsProvider.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                List a2 = ArticleMemberBenefitsProvider.this.a(obj);
                try {
                    ArticleMemberBenefitsProvider.this.d.writeLock().lock();
                    if (ArticleMemberBenefitsProvider.this.b.isEmpty() && !a2.isEmpty()) {
                        ArticleMemberBenefitsProvider.this.b.addAll(a2);
                    }
                    ArticleMemberBenefitsProvider.this.d.writeLock().unlock();
                    ArticleMemberBenefitsProvider.this.a(context);
                    LogUtil.a("ArticleMemberBenefitsProvider", "articleImages: ", nrv.a(a2));
                } catch (Throwable th) {
                    ArticleMemberBenefitsProvider.this.d.writeLock().unlock();
                    throw th;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            this.d.writeLock().lock();
            ArrayList arrayList = new ArrayList();
            for (ArticleImage articleImage : this.b) {
                if (articleImage.getCombinStatus() == f2819a.intValue()) {
                    arrayList.add(Integer.valueOf(this.b.indexOf(articleImage)));
                    this.e.put(Integer.valueOf(this.b.indexOf(articleImage)), Integer.valueOf(articleImage.getCombinStyle()));
                }
            }
            this.g = arrayList;
            this.d.writeLock().unlock();
            d(context);
        } catch (Throwable th) {
            this.d.writeLock().unlock();
            throw th;
        }
    }

    private void d(final Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ArticleMemberBenefitsProvider.4
            @Override // java.lang.Runnable
            public void run() {
                ArticleMemberBenefitsProvider.this.b(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        if (this.g.isEmpty() || this.b.isEmpty()) {
            return;
        }
        try {
            this.d.writeLock().lock();
            Iterator<Integer> it = this.g.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                ArticleImage articleImage = this.b.get(intValue);
                int combinNums = articleImage.getCombinNums();
                this.e.put(Integer.valueOf(intValue), Integer.valueOf(articleImage.getCombinStyle()));
                b(context, intValue, combinNums);
            }
        } finally {
            this.d.writeLock().unlock();
        }
    }

    private void b(Context context, final int i, final int i2) {
        final ArrayList arrayList = new ArrayList();
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ArticleMemberBenefitsProvider.2
            @Override // java.lang.Runnable
            public void run() {
                CountDownLatch countDownLatch = new CountDownLatch(i2);
                int i3 = i;
                while (true) {
                    try {
                        if (i3 < i + i2) {
                            if (koq.d(ArticleMemberBenefitsProvider.this.b, i3)) {
                                String imgName = ((ArticleImage) ArticleMemberBenefitsProvider.this.b.get(i3)).getImgName();
                                Drawable drawable = null;
                                for (int i4 = 0; drawable == null && i4 < 3; i4++) {
                                    drawable = nrf.cIb_(BaseApplication.e(), imgName);
                                }
                                arrayList.add(drawable);
                                countDownLatch.countDown();
                            }
                            i3++;
                        } else {
                            try {
                                break;
                            } catch (InterruptedException unused) {
                                LogUtil.b("ArticleMemberBenefitsProvider", "load tab img async is interrupted!");
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    if (((Drawable) it.next()) == null) {
                                        LogUtil.b("ArticleMemberBenefitsProvider", "load tab img async failed");
                                    }
                                }
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            if (((Drawable) it2.next()) == null) {
                                LogUtil.b("ArticleMemberBenefitsProvider", "load tab img async failed");
                            }
                        }
                        throw th;
                    }
                }
                countDownLatch.await();
                ArticleMemberBenefitsProvider.this.c.put(Integer.valueOf(i), arrayList);
                if (ArticleMemberBenefitsProvider.this.f != null) {
                    ArticleMemberBenefitsProvider.this.f.e(ArticleMemberBenefitsProvider.this.c);
                }
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    if (((Drawable) it3.next()) == null) {
                        LogUtil.b("ArticleMemberBenefitsProvider", "load tab img async failed");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(Context context, Object obj) {
        Integer valueOf;
        if (!koq.e(obj, MemberTypeInfo.class)) {
            LogUtil.a("ArticleMemberBenefitsProvider", "objData not list of MemberTypeInfo");
            return 0;
        }
        List<MemberTypeInfo> list = (List) obj;
        LogUtil.a("ArticleMemberBenefitsProvider", "memberTypeInfos ", list);
        for (MemberTypeInfo memberTypeInfo : list) {
            if (memberTypeInfo != null && memberTypeInfo.getMemberType() == 1) {
                String marketingArticleId = memberTypeInfo.getMarketingArticleId() == null ? "0" : memberTypeInfo.getMarketingArticleId();
                String darkMarketingArticleId = memberTypeInfo.getDarkMarketingArticleId() == null ? "0" : memberTypeInfo.getDarkMarketingArticleId();
                LogUtil.a("ArticleMemberBenefitsProvider", "darkArticleId = ", darkMarketingArticleId);
                int intValue = Integer.valueOf(marketingArticleId).intValue();
                if (!nsn.v(context)) {
                    return intValue;
                }
                if (darkMarketingArticleId != "0") {
                    valueOf = Integer.valueOf(darkMarketingArticleId);
                } else {
                    valueOf = Integer.valueOf(marketingArticleId);
                }
                return valueOf.intValue();
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ArticleImage> a(Object obj) {
        ArrayList arrayList = new ArrayList();
        if (!(obj instanceof ArticleInfo)) {
            LogUtil.a("ArticleMemberBenefitsProvider", "objData not instanceof ArticleInfo");
            return arrayList;
        }
        ArticleInfo articleInfo = (ArticleInfo) obj;
        if (articleInfo == null) {
            LogUtil.a("ArticleMemberBenefitsProvider", "articleInfo is null");
            return arrayList;
        }
        return articleInfo.getArticleImgList();
    }
}
