package defpackage;

import android.text.TextUtils;
import com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyGroupDataFragment;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes7.dex */
public class rsi extends rso {

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, String> f16895a = new HashMap<Integer, String>(16) { // from class: rsi.3
        private static final long serialVersionUID = 4061556043911392072L;

        {
            put(107, "com.huawei.instantaneous.blood_pressure");
            put(108, "com.huawei.instantaneous.blood_glucose");
            put(109, "com.huawei.instantaneous.body_weight");
            put(100, "com.huawei.continuous.steps.delta");
            put(112, "com.huawei.activityrecord");
        }
    };
    private String d;
    private boolean e = false;
    private boolean c = false;
    private boolean b = false;

    public boolean b() {
        return this.e;
    }

    @Override // defpackage.rso
    public void d(PageModelArgs pageModelArgs) {
        e(pageModelArgs);
    }

    private void e(PageModelArgs pageModelArgs) {
        if (pageModelArgs == null) {
            return;
        }
        int dataSource = pageModelArgs.getDataSource();
        if (dataSource == 1) {
            c(pageModelArgs.getPageType(), pageModelArgs.getUuid(), pageModelArgs.getPackageName(), pageModelArgs.getClassType());
        } else if (dataSource == 2) {
            c(pageModelArgs.getPageType(), pageModelArgs.getUuid());
        } else {
            if (dataSource != 3) {
                return;
            }
            b(pageModelArgs);
        }
    }

    private void b(PageModelArgs pageModelArgs) {
        int pageType = pageModelArgs.getPageType();
        if (pageType != 102) {
            if (pageType == 103) {
                c(pageType);
                g(pageModelArgs);
                return;
            } else if (pageType == 105) {
                i(pageModelArgs);
                return;
            } else if (pageType == 106) {
                c(pageType);
                c(pageModelArgs);
                return;
            } else if (pageType != 113) {
                new row(pageType).a(new e(this));
                return;
            }
        }
        c(pageType);
        a(pageModelArgs);
    }

    private void c(int i) {
        if (this.b) {
            return;
        }
        new rqt().c(i, new b(this));
        this.b = true;
    }

    private void a(PageModelArgs pageModelArgs) {
        SourceInfo sourceInfo = pageModelArgs.getSourceInfo();
        if (sourceInfo != null) {
            new row(pageModelArgs.getPageType()).d(rsr.e(sourceInfo.getSourceType()), sourceInfo.getDeviceUniqueCode(), sourceInfo.getPackageName(), new e(this));
        } else {
            new row(pageModelArgs.getPageType()).a(new e(this));
        }
    }

    private void g(PageModelArgs pageModelArgs) {
        rkb rkbVar;
        SourceInfo sourceInfo = pageModelArgs.getSourceInfo();
        if (sourceInfo != null) {
            rkbVar = new rkb(0L, System.currentTimeMillis(), rsr.e(sourceInfo.getSourceType()), sourceInfo.getDeviceUniqueCode(), sourceInfo.getPackageName());
        } else {
            rkbVar = new rkb(0L, System.currentTimeMillis(), 0, "", "");
        }
        rkbVar.d(pageModelArgs.getDataSource());
        new row(pageModelArgs.getPageType()).a(rkbVar, new e(this));
    }

    private void i(PageModelArgs pageModelArgs) {
        rkb rkbVar = new rkb(0L, System.currentTimeMillis(), 0, "", "");
        rkbVar.d(pageModelArgs.getDataSource());
        rkbVar.e("Subpage", pageModelArgs.getInt("Subpage"));
        new row(pageModelArgs.getPageType()).a(rkbVar, new e(this));
    }

    private void c(PageModelArgs pageModelArgs) {
        rkb rkbVar;
        SourceInfo sourceInfo = pageModelArgs.getSourceInfo();
        if (sourceInfo != null) {
            rkbVar = new rkb(0L, System.currentTimeMillis(), rsr.e(sourceInfo.getSourceType()), sourceInfo.getDeviceUniqueCode(), sourceInfo.getPackageName());
        } else {
            rkbVar = new rkb(0L, System.currentTimeMillis(), 0, "", "");
        }
        new row(pageModelArgs.getPageType()).e(rkbVar, new a(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, List<rsg> list) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyGroupDataFragment) {
            PrivacyGroupDataFragment privacyGroupDataFragment = (PrivacyGroupDataFragment) e2;
            if (!isViewAttached()) {
                LogUtil.a("GroupDataFragmentPresenter", "notifyGroupDataChange isViewAttached false");
                return;
            }
            if (i != 0) {
                list = new ArrayList<>(10);
            }
            privacyGroupDataFragment.e(list);
        }
    }

    private void c(int i, String str) {
        if (this.c) {
            return;
        }
        this.c = true;
        if (i != 100) {
            if (i == 103) {
                a(this.d, str, "65");
                return;
            }
            if (i == 112) {
                a(this.d, str, "");
                return;
            }
            switch (i) {
                case 107:
                case 108:
                case 109:
                    break;
                default:
                    LogUtil.h("GroupDataFragmentPresenter", "can't support the type: " + i);
                    break;
            }
            return;
        }
        a(this.d, i, str);
    }

    private void a(String str, String str2, final String str3) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyGroupDataFragment) {
            final PrivacyGroupDataFragment privacyGroupDataFragment = (PrivacyGroupDataFragment) e2;
            ipb.d().c(this, new rrm(str, str2, str3).getRequestParamsBuilder().b(new OnRequestCallBack<rrk>() { // from class: rsi.1
                @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(rrk rrkVar) {
                    rsi.this.c = false;
                    if (rrkVar == null) {
                        LogUtil.a("GroupDataFragmentPresenter", "ActivityRecordsModel from cloud is null");
                        return;
                    }
                    rsi.this.d = rrkVar.getCursor();
                    rsi.this.e = rrkVar.isHasMoreData();
                    if (TextUtils.isEmpty(str3)) {
                        PrivacyGroupDataFragment privacyGroupDataFragment2 = privacyGroupDataFragment;
                        rsi rsiVar = rsi.this;
                        privacyGroupDataFragment2.c(rsiVar.o(rsiVar.b(rrkVar)));
                    } else {
                        rsi rsiVar2 = rsi.this;
                        rsiVar2.d((List<PrivacyDataModel>) rsiVar2.k(rrkVar.getActivityRecord()));
                        PrivacyGroupDataFragment privacyGroupDataFragment3 = privacyGroupDataFragment;
                        rsi rsiVar3 = rsi.this;
                        privacyGroupDataFragment3.b(rsiVar3.c((Map<String, List<PrivacyDataModel>>) rsiVar3.a(103, rru.d().c())));
                    }
                }

                @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
                public void onFail(int i, Throwable th) {
                    rsi.this.c = false;
                    if (rsi.this.isViewAttached()) {
                        LogUtil.a("GroupDataFragmentPresenter", "HealthKitRequestManager request fail");
                        privacyGroupDataFragment.c(new ArrayList(10));
                    }
                }
            }).a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<rrh> b(rrk rrkVar) {
        List<rrh> activityRecord = rrkVar.getActivityRecord();
        Iterator<rrh> it = activityRecord.iterator();
        while (it.hasNext()) {
            if ("65".equals(String.valueOf(it.next().getActivityType()))) {
                it.remove();
            }
        }
        return activityRecord;
    }

    private void a(String str, final int i, String str2) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyGroupDataFragment) {
            final PrivacyGroupDataFragment privacyGroupDataFragment = (PrivacyGroupDataFragment) e2;
            ipb.d().c(this, new rrr(str, str2, f16895a.get(Integer.valueOf(i)), new SimpleDateFormat("Z").format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime())).getRequestParamsBuilder().b(new OnRequestCallBack<rrl>() { // from class: rsi.4
                @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(rrl rrlVar) {
                    rsi.this.c = false;
                    rsi.this.a(rrlVar, i, privacyGroupDataFragment);
                }

                @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
                public void onFail(int i2, Throwable th) {
                    rsi.this.c = false;
                    if (rsi.this.isViewAttached()) {
                        privacyGroupDataFragment.c(new ArrayList(10));
                    }
                }
            }).a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(rrl rrlVar, int i, PrivacyGroupDataFragment privacyGroupDataFragment) {
        if (rrlVar == null) {
            LogUtil.a("GroupDataFragmentPresenter", "SampleSetsModel from cloud is null");
            return;
        }
        this.d = rrlVar.getCursor();
        this.e = rrlVar.isHasMoreData();
        if (i != 100) {
            switch (i) {
                case 107:
                case 108:
                    privacyGroupDataFragment.c(a(i, rrlVar.getSamplePointList()));
                    break;
            }
            return;
        }
        d(c(i, rrlVar.getSamplePointList()));
        privacyGroupDataFragment.b(c(a(i, rru.d().c())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<PrivacyDataModel> list) {
        for (PrivacyDataModel privacyDataModel : list) {
            rru.d().b(rsr.i(privacyDataModel.getStartTime()), privacyDataModel);
        }
    }

    private List<PrivacyDataModel> c(int i, List<rrj> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (rrj rrjVar : list) {
            PrivacyDataModel privacyDataModel = new PrivacyDataModel();
            if (i == 100) {
                privacyDataModel.setIntValue(i(rrjVar.getValue()));
                privacyDataModel.setDataTitle(rsr.c(rsr.c(i, privacyDataModel.getIntValue()), UnitUtil.e(privacyDataModel.getIntValue(), 1, 0), i));
            } else if (i == 109) {
                privacyDataModel.setDoubleValue(l(rrjVar.getValue()));
                double h = UnitUtil.h() ? UnitUtil.h(l(rrjVar.getValue())) : l(rrjVar.getValue());
                privacyDataModel.setDataTitle(rsr.c(rsr.c(i, h), UnitUtil.e(h, 1, 1), i));
            }
            privacyDataModel.setDataDesc(b(i, rrjVar));
            privacyDataModel.setStartTime(rrjVar.getStartTime());
            privacyDataModel.setEndTime(rrjVar.getEndTime());
            privacyDataModel.setModifyTime(rrjVar.getModifyTime());
            privacyDataModel.setPageType(i);
            arrayList.add(privacyDataModel);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PrivacyDataModel> k(List<rrh> list) {
        String c;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (rrh rrhVar : list) {
            PrivacyDataModel privacyDataModel = new PrivacyDataModel();
            privacyDataModel.setDataTitle(rsr.j(rsr.f(rrhVar.getEndTime()) - rsr.f(rrhVar.getStartTime())));
            StringBuilder sb = new StringBuilder();
            sb.append(rsr.c(rrhVar.getStartTime()));
            sb.append(Constants.LINK);
            if (rsr.d(rrhVar.getStartTime(), rrhVar.getEndTime())) {
                c = rsr.e(rrhVar.getEndTime());
            } else {
                c = rsr.c(rrhVar.getEndTime());
            }
            sb.append(c);
            privacyDataModel.setDataDesc(sb.toString());
            privacyDataModel.setStartTime(rrhVar.getStartTime());
            privacyDataModel.setEndTime(rrhVar.getEndTime());
            privacyDataModel.setModifyTime(rrhVar.getModifyTime());
            privacyDataModel.setPageType(103);
            arrayList.add(privacyDataModel);
        }
        return arrayList;
    }

    private double l(List<rrn> list) {
        double d = 0.0d;
        for (rrn rrnVar : list) {
            if (rrnVar.getFieldName().equals("body_weight")) {
                d = rrnVar.getFloatValue();
            }
        }
        return d;
    }

    private int i(List<rrn> list) {
        for (rrn rrnVar : list) {
            if (rrnVar.getFieldName().equals("steps_delta")) {
                return rrnVar.getIntegerValue();
            }
        }
        return 0;
    }

    private String b(int i, rrj rrjVar) {
        String c;
        if (i != 100 && i != 103) {
            return i != 109 ? "" : rsr.e(rrjVar.getEndTime());
        }
        String c2 = rsr.c(rrjVar.getStartTime());
        if (rsr.d(rrjVar.getStartTime(), rrjVar.getEndTime())) {
            c = rsr.e(rrjVar.getEndTime());
        } else {
            c = rsr.c(rrjVar.getEndTime());
        }
        return c2 + Constants.LINK + c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<rsg> o(List<rrh> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        for (rrh rrhVar : list) {
            String a2 = mlg.a(rrhVar.getStartTime(), 0);
            List<PrivacyDataModel> list2 = linkedHashMap.get(a2);
            if (list2 == null) {
                list2 = new ArrayList<>();
                linkedHashMap.put(a2, list2);
            }
            PrivacyDataModel privacyDataModel = new PrivacyDataModel();
            privacyDataModel.setStartTime(rrhVar.getStartTime());
            privacyDataModel.setEndTime(rrhVar.getEndTime());
            privacyDataModel.setModifyTime(rrhVar.getModifyTime());
            privacyDataModel.setDataTitle(a(rrhVar.getActivitySummary()));
            privacyDataModel.setDataDesc(rsr.a(rrhVar.getStartTime()));
            privacyDataModel.setPageType(112);
            list2.add(privacyDataModel);
        }
        return c(linkedHashMap);
    }

    private String a(rrg rrgVar) {
        return rrgVar == null ? "" : h(rrgVar.getDataSummary());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0085, code lost:
    
        if (r4.equals("com.huawei.continuous.body_weight.statistics") == false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String h(java.util.List<defpackage.rrj> r10) {
        /*
            r9 = this;
            boolean r0 = defpackage.koq.b(r10)
            java.lang.String r1 = ""
            if (r0 == 0) goto L9
            return r1
        L9:
            java.util.Iterator r10 = r10.iterator()
            r0 = r1
            r2 = r0
            r3 = r2
        L10:
            boolean r4 = r10.hasNext()
            if (r4 == 0) goto Laa
            java.lang.Object r4 = r10.next()
            rrj r4 = (defpackage.rrj) r4
            java.util.List r5 = r4.getValue()
            boolean r6 = defpackage.koq.b(r5)
            if (r6 == 0) goto L27
            return r1
        L27:
            java.lang.String r4 = r4.getDataTypeName()
            r6 = 0
            java.lang.Object r7 = r5.get(r6)
            rrn r7 = (defpackage.rrn) r7
            r4.hashCode()
            int r8 = r4.hashCode()
            switch(r8) {
                case -828306879: goto L7f;
                case -347047149: goto L74;
                case -328597096: goto L69;
                case 39245215: goto L5e;
                case 944702533: goto L53;
                case 1795795000: goto L48;
                case 2119456336: goto L3d;
                default: goto L3c;
            }
        L3c:
            goto L87
        L3d:
            java.lang.String r6 = "com.huawei.continuous.distance.total"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L46
            goto L87
        L46:
            r6 = 6
            goto L88
        L48:
            java.lang.String r6 = "com.huawei.continuous.calories.burnt.total"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L51
            goto L87
        L51:
            r6 = 5
            goto L88
        L53:
            java.lang.String r6 = "com.huawei.continuous.steps.rate.statistics"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L5c
            goto L87
        L5c:
            r6 = 4
            goto L88
        L5e:
            java.lang.String r6 = "com.huawei.continuous.sleep.statistics"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L67
            goto L87
        L67:
            r6 = 3
            goto L88
        L69:
            java.lang.String r6 = "com.huawei.continuous.steps.total"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L72
            goto L87
        L72:
            r6 = 2
            goto L88
        L74:
            java.lang.String r6 = "com.huawei.continuous.heart_rate.statistics"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L7d
            goto L87
        L7d:
            r6 = 1
            goto L88
        L7f:
            java.lang.String r8 = "com.huawei.continuous.body_weight.statistics"
            boolean r8 = r4.equals(r8)
            if (r8 != 0) goto L88
        L87:
            r6 = -1
        L88:
            switch(r6) {
                case 0: goto La4;
                case 1: goto L9e;
                case 2: goto La4;
                case 3: goto L98;
                case 4: goto L9e;
                case 5: goto L92;
                case 6: goto L8c;
                default: goto L8b;
            }
        L8b:
            goto L10
        L8c:
            java.lang.String r0 = r9.e(r4, r7, r5)
            goto L10
        L92:
            java.lang.String r2 = r9.e(r4, r7, r5)
            goto L10
        L98:
            java.lang.String r3 = r9.b(r5)
            goto L10
        L9e:
            java.lang.String r3 = r9.e(r4, r7, r5)
            goto L10
        La4:
            java.lang.String r3 = r9.a(r4, r7, r5)
            goto L10
        Laa:
            boolean r10 = health.compact.a.utils.StringUtils.i(r0)
            if (r10 == 0) goto Lb1
            return r0
        Lb1:
            boolean r10 = health.compact.a.utils.StringUtils.i(r2)
            if (r10 == 0) goto Lb8
            return r2
        Lb8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rsi.h(java.util.List):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String e(String str, rrn rrnVar, List<rrn> list) {
        char c;
        double e2;
        int i;
        str.hashCode();
        int i2 = 0;
        switch (str.hashCode()) {
            case -347047149:
                if (str.equals("com.huawei.continuous.heart_rate.statistics")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 944702533:
                if (str.equals("com.huawei.continuous.steps.rate.statistics")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1795795000:
                if (str.equals("com.huawei.continuous.calories.burnt.total")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2119456336:
                if (str.equals("com.huawei.continuous.distance.total")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            e2 = e(list);
            i = 102;
        } else {
            if (c == 2) {
                e2 = rrnVar.getFloatValue();
                i2 = 111;
            } else if (c != 3) {
                e2 = 0.0d;
            } else {
                e2 = rrnVar.getFloatValue();
                i2 = 101;
            }
            i = i2;
            i2 = 1;
        }
        return rsr.c(rsr.b(str), UnitUtil.e(e2, 1, i2), i);
    }

    private String a(String str, rrn rrnVar, List<rrn> list) {
        double e2;
        int i;
        int i2;
        str.hashCode();
        if (str.equals("com.huawei.continuous.body_weight.statistics")) {
            if (UnitUtil.h()) {
                e2 = UnitUtil.h(e(list));
            } else {
                e2 = e(list);
            }
            i = 109;
        } else {
            i = 0;
            if (str.equals("com.huawei.continuous.steps.total")) {
                e2 = rrnVar.getIntegerValue();
                i2 = 100;
                return rsr.c(rsr.c(i2, e2), UnitUtil.e(e2, 1, i), i2);
            }
            e2 = 0.0d;
        }
        i2 = i;
        i = 1;
        return rsr.c(rsr.c(i2, e2), UnitUtil.e(e2, 1, i), i2);
    }

    private double e(List<rrn> list) {
        for (rrn rrnVar : list) {
            if (rrnVar.getFieldName().equals("avg")) {
                return rrnVar.getFloatValue();
            }
        }
        return 0.0d;
    }

    private String b(List<rrn> list) {
        Iterator<rrn> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getFieldName().equals("all_sleep_time")) {
                return rsr.j(r0.getIntegerValue() * 60);
            }
        }
        return "";
    }

    private List<rsg> a(int i, List<rrj> list) {
        String a2;
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        for (rrj rrjVar : list) {
            String a3 = mlg.a(rrjVar.getStartTime(), 0);
            List<PrivacyDataModel> list2 = linkedHashMap.get(a3);
            if (list2 == null) {
                list2 = new ArrayList<>();
                linkedHashMap.put(a3, list2);
            }
            PrivacyDataModel privacyDataModel = new PrivacyDataModel();
            privacyDataModel.setStartTime(rrjVar.getStartTime());
            privacyDataModel.setDataDesc(rsr.c(rrjVar.getEndTime()));
            if (i == 107) {
                a2 = a(rrjVar.getValue());
            } else {
                a2 = i != 108 ? "" : c(rrjVar.getValue());
            }
            privacyDataModel.setDataTitle(rsr.c(rsr.i(i), a2, i));
            privacyDataModel.setEndTime(rrjVar.getEndTime());
            privacyDataModel.setModifyTime(rrjVar.getModifyTime());
            privacyDataModel.setPageType(i);
            list2.add(privacyDataModel);
        }
        return c(linkedHashMap);
    }

    private String a(List<rrn> list) {
        String str = "";
        String str2 = "";
        for (rrn rrnVar : list) {
            if (rrnVar.getFieldName().equals("systolic_pressure")) {
                str = UnitUtil.e(rrnVar.getFloatValue(), 1, 1);
            }
            if (rrnVar.getFieldName().equals("diastolic_pressure")) {
                str2 = UnitUtil.e(rrnVar.getFloatValue(), 1, 1);
            }
        }
        return str + "/" + str2;
    }

    private String c(List<rrn> list) {
        String str = "";
        for (rrn rrnVar : list) {
            if (rrnVar.getFieldName().equals("level")) {
                str = UnitUtil.e(rrnVar.getFloatValue(), 1, 1);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, List<PrivacyDataModel>> a(int i, Map<String, List<PrivacyDataModel>> map) {
        String e2;
        String c;
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        for (Map.Entry<String, List<PrivacyDataModel>> entry : map.entrySet()) {
            long startTime = entry.getValue().get(0).getStartTime();
            String d = rsr.d(startTime);
            List list = (List) linkedHashMap.get(d);
            if (list == null) {
                list = new ArrayList();
                linkedHashMap.put(d, list);
            }
            String str = "";
            if (i == 100) {
                double j = j(entry.getValue());
                e2 = UnitUtil.e(j, 1, 0);
                c = rsr.c(i, j);
            } else if (i == 103) {
                e2 = rsr.j(g(entry.getValue()));
                c = "";
            } else if (i != 109) {
                c = "";
                e2 = c;
            } else {
                double f = f(entry.getValue());
                String c2 = rsr.c(i, f);
                e2 = UnitUtil.e(f, 1, 1);
                c = c2;
                str = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_healthdata_weight_average);
            }
            PrivacyDataModel privacyDataModel = new PrivacyDataModel();
            if (i == 103) {
                privacyDataModel.setDataTitle(e2);
            } else {
                privacyDataModel.setDataTitle(str + rsr.c(c, e2, i));
            }
            privacyDataModel.setDataDesc(rsr.a(startTime));
            privacyDataModel.setPageType(i);
            privacyDataModel.setStartTime(startTime);
            list.add(privacyDataModel);
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<rsg> c(Map<String, List<PrivacyDataModel>> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<PrivacyDataModel>> entry : map.entrySet()) {
            rsg rsgVar = new rsg();
            rsgVar.e(entry.getKey());
            rsgVar.b(entry.getValue());
            arrayList.add(rsgVar);
        }
        return arrayList;
    }

    private double f(List<PrivacyDataModel> list) {
        Iterator<PrivacyDataModel> it = list.iterator();
        double d = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            d += it.next().getDoubleValue();
            i++;
        }
        return i == 0 ? d : UnitUtil.h() ? UnitUtil.h(d / i) : d / i;
    }

    private long g(List<PrivacyDataModel> list) {
        long j = 0;
        for (PrivacyDataModel privacyDataModel : list) {
            j += rsr.f(privacyDataModel.getEndTime()) - rsr.f(privacyDataModel.getStartTime());
        }
        return j;
    }

    private int j(List<PrivacyDataModel> list) {
        Iterator<PrivacyDataModel> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getIntValue();
        }
        return i;
    }

    private void c(int i, String str, String str2, int i2) {
        if (i != 103) {
            if (i == 106) {
                new row(i).e(new rkb(0L, System.currentTimeMillis(), i2, str, str2), new a(this));
                return;
            } else if (i != 107) {
                new row(i).a(i2, str, str2, new e(this));
                return;
            }
        }
        new row(i).a(new rkb(0L, System.currentTimeMillis(), i2, str, str2), new e(this));
    }

    static class e implements DataSourceCallback<List<rsg>> {
        WeakReference<rsi> c;

        e(rsi rsiVar) {
            this.c = new WeakReference<>(rsiVar);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<rsg> list) {
            rsi rsiVar;
            WeakReference<rsi> weakReference = this.c;
            if (weakReference == null || (rsiVar = weakReference.get()) == null) {
                return;
            }
            rsiVar.b(i, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Map<Integer, List<rsg>> map) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyGroupDataFragment) {
            PrivacyGroupDataFragment privacyGroupDataFragment = (PrivacyGroupDataFragment) e2;
            if (!isViewAttached()) {
                LogUtil.a("GroupDataFragmentPresenter", "notifyAbnormalDataChange isViewAttached false");
                return;
            }
            if (i != 0) {
                map = new HashMap<>();
            }
            privacyGroupDataFragment.e(map);
        }
    }

    static class a implements DataSourceCallback<Map<Integer, List<rsg>>> {
        WeakReference<rsi> d;

        a(rsi rsiVar) {
            this.d = new WeakReference<>(rsiVar);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, Map<Integer, List<rsg>> map) {
            rsi rsiVar;
            WeakReference<rsi> weakReference = this.d;
            if (weakReference == null || (rsiVar = weakReference.get()) == null) {
                return;
            }
            rsiVar.b(i, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, List<SourceInfo> list) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyGroupDataFragment) {
            PrivacyGroupDataFragment privacyGroupDataFragment = (PrivacyGroupDataFragment) e2;
            if (!isViewAttached()) {
                LogUtil.a("GroupDataFragmentPresenter", "notifySourceResult isViewAttached false");
                return;
            }
            if (i != 0) {
                list = new ArrayList<>(10);
            }
            privacyGroupDataFragment.d(list);
        }
    }

    static class b implements DataSourceCallback<List<SourceInfo>> {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<rsi> f16898a;

        b(rsi rsiVar) {
            this.f16898a = new WeakReference<>(rsiVar);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<SourceInfo> list) {
            rsi rsiVar;
            WeakReference<rsi> weakReference = this.f16898a;
            if (weakReference == null || (rsiVar = weakReference.get()) == null) {
                return;
            }
            rsiVar.d(i, list);
        }
    }

    @Override // defpackage.rso, com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        ipb.d().e(this);
        super.detachView();
    }
}
