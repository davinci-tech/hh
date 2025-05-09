package com.huawei.ui.main.stories.member;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.datatypes.MemberTypeInfo;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.datatype.Product;
import defpackage.dqj;
import defpackage.gpn;
import defpackage.jdr;
import defpackage.jdx;
import defpackage.koq;
import defpackage.npt;
import defpackage.nrv;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class MemberProductProvider extends BaseKnitDataProvider<List<Product>> {
    private Observer c;
    private List<Product> d;
    private SectionBean j;
    private int i = 0;

    /* renamed from: a, reason: collision with root package name */
    private String f10391a = "";
    private boolean b = false;
    private boolean e = true;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, final List<Product> list) {
        hashMap.put("PRODUCT", list);
        hashMap.put(CommonConstant.RETKEY.STATUS, Integer.valueOf(this.i));
        hashMap.put("IS_SHOW_TOAST", Boolean.valueOf(this.b));
        hashMap.put("CLICK_EVENT_LISTENER", new npt() { // from class: com.huawei.ui.main.stories.member.MemberProductProvider.4
            @Override // defpackage.npt, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.d(list, i)) {
                    dqj.a((Product) list.get(i));
                }
                MemberProductProvider.this.i = i;
                if (MemberProductProvider.this.j != null) {
                    MemberProductProvider.this.j.e(list);
                }
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, final SectionBean sectionBean) {
        Bundle extra;
        LogUtil.a("MemberProductProvider", "loadData: ", this);
        if (HandlerExecutor.c()) {
            LogUtil.a("MemberProductProvider", "loadData isMainTread: ", this);
            jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.member.MemberProductProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("MemberProductProvider", "loadData run: ", this);
                    MemberProductProvider.this.loadData(context, sectionBean);
                }
            });
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.a("MemberProductProvider", "loadData isBrowseMode");
            return;
        }
        b(context);
        boolean z = this.e;
        if (!z && !this.b) {
            LogUtil.a("MemberProductProvider", "mIsFirst: ", Boolean.valueOf(z), " mIsInTypeSelectActivity ", Boolean.valueOf(this.b));
            return;
        }
        e();
        if (this.e && (extra = getExtra()) != null) {
            int i = extra.getInt("MEMBER_TYPE_SELECT_INDEX", 0);
            this.f10391a = extra.getString("DEFAULT_PRODUCT_ID");
            LogUtil.a("MemberProductProvider", "extraSelectedIndex: ", Integer.valueOf(i), " mDefaultProductId", this.f10391a);
            this.i = i;
        }
        this.j = sectionBean;
        List<Product> d = d();
        List<Product> e2 = this.b ? e(d) : d;
        this.d = e2;
        b(e2);
        sectionBean.e(koq.b(d) ? null : this.d);
        ((VipApi) Services.c("vip", VipApi.class)).getVipType(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.member.MemberProductProvider.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                List e3 = MemberProductProvider.this.e(obj);
                MemberProductProvider memberProductProvider = MemberProductProvider.this;
                memberProductProvider.d = memberProductProvider.b ? MemberProductProvider.this.e((List<Product>) e3) : e3;
                MemberProductProvider memberProductProvider2 = MemberProductProvider.this;
                memberProductProvider2.b((List<Product>) memberProductProvider2.d);
                LogUtil.a("MemberProductProvider", "products: ", nrv.a(e3));
                MemberProductProvider.this.e = false;
                sectionBean.e(koq.b(e3) ? null : MemberProductProvider.this.d);
            }
        });
    }

    private void b(Context context) {
        LogUtil.a("MemberProductProvider", "judgeIsInTypeSelectActivity: context ", context);
        if ((context instanceof Activity) && (((Activity) context) instanceof MemberTypeSelectActivity)) {
            this.b = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Product> e(List<Product> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        UserMemberInfo a2 = gpn.a();
        if (a2 == null || a2.getAutoRenew() != 1 || a2.getNowTime() > a2.getExpireTime()) {
            return list;
        }
        ArrayList arrayList2 = new ArrayList();
        try {
            for (Product product : list) {
                if (product != null && product.getPurchasePolicy() != Product.SUBSCRIPTION_PURCHASE_FLAG) {
                    arrayList2.add(product);
                }
            }
        } catch (ClassCastException e2) {
            LogUtil.b("MemberProductProvider", "filterSubScribeProduct Exception, ", ExceptionUtils.d(e2), "\n after reconstruct class type is", list.get(0).getClass().getName());
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Product> list) {
        if (koq.b(list) || TextUtils.isEmpty(this.f10391a)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Product product = list.get(i);
            if (product != null && TextUtils.equals(this.f10391a, product.getProductId())) {
                this.i = i;
                LogUtil.a("MemberProductProvider", "findDefaultSelectIndex selectIndex ", Integer.valueOf(i));
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Product> e(Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(d());
        if (!koq.e(obj, MemberTypeInfo.class)) {
            LogUtil.a("MemberProductProvider", "objData not list of MemberTypeInfo");
            return arrayList;
        }
        for (MemberTypeInfo memberTypeInfo : (List) obj) {
            if (memberTypeInfo != null && memberTypeInfo.getMemberType() == 1) {
                List<Product> products = memberTypeInfo.getProducts();
                if (!koq.b(products)) {
                    Collections.sort(products, new Comparator<Product>() { // from class: com.huawei.ui.main.stories.member.MemberProductProvider.2
                        @Override // java.util.Comparator
                        /* renamed from: c, reason: merged with bridge method [inline-methods] */
                        public int compare(Product product, Product product2) {
                            return product.getPriority() - product2.getPriority();
                        }
                    });
                    a(products);
                    return products;
                }
            }
        }
        return arrayList;
    }

    private void a(List<Product> list) {
        if (koq.b(list)) {
            LogUtil.a("MemberProductProvider", "products is Empty");
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "VIP_PRODUCT_KEY", jdr.b(list, null), new StorageParams());
        LogUtil.a("MemberProductProvider", "setCacheProducts success");
    }

    private List<Product> d() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "VIP_PRODUCT_KEY");
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(b)) {
            jdr.d(b, MemberProductProvider.class.getClassLoader(), null, arrayList);
            LogUtil.a("MemberProductProvider", "getVipProductsFromSp success");
        }
        return arrayList;
    }

    private void e() {
        Bundle extra = getExtra();
        if (this.c != null) {
            LogUtil.h("MemberProductProvider", "already registered");
        }
        if (extra == null) {
            LogUtil.b("MemberProductProvider", "registerObserver bundle == null");
            return;
        }
        String string = extra.getString("MEMBER_PRODUCT_TAG", null);
        if (string == null) {
            LogUtil.b("MemberProductProvider", "registerObserver observerTag == null");
            return;
        }
        LogUtil.a("MemberProductProvider", "registerObserver observerTag: ", string);
        e eVar = new e(this);
        this.c = eVar;
        ObserverManagerUtil.d(eVar, string);
    }

    static class e implements Observer {
        private WeakReference<MemberProductProvider> b;

        public e(MemberProductProvider memberProductProvider) {
            this.b = new WeakReference<>(memberProductProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            WeakReference<MemberProductProvider> weakReference;
            MemberProductProvider memberProductProvider;
            if (objArr == null || objArr.length == 0 || !(objArr[0] instanceof Integer) || (weakReference = this.b) == null || (memberProductProvider = weakReference.get()) == null) {
                return;
            }
            int intValue = ((Integer) objArr[0]).intValue();
            memberProductProvider.i = intValue;
            LogUtil.a("MemberProductProvider", "notify selectedIndex: ", Integer.valueOf(intValue));
            memberProductProvider.j.e(memberProductProvider.d);
        }
    }
}
