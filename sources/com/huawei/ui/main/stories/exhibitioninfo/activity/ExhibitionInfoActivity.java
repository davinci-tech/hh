package com.huawei.ui.main.stories.exhibitioninfo.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.print.PrintAttributes;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.js.JsInteractAddition;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.exhibitioninfo.activity.ExhibitionInfoActivity;
import com.huawei.ui.main.stories.exhibitioninfo.adapter.ExhibitionAdapter;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.pgw;
import defpackage.rsr;
import defpackage.scj;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ExhibitionInfoActivity extends BaseActivity implements ExhibitionAdapter.OnReportSelectChangeListener {
    private CommonDialog21 f;
    private Bitmap g;
    private Bitmap h;
    private Context i;
    private ExhibitionBloodFragment k;
    private HealthRecycleView l;
    private Bitmap m;
    private ExhibitionAdapter n;
    private Handler o;
    private HiHealthData p;
    private double q;
    private double r;
    private RelativeLayout s;
    private LinearLayout u;
    private Bitmap v;
    private HealthToolBar w;

    /* renamed from: a, reason: collision with root package name */
    private static final String f9716a = BaseApplication.e().getString(R$string.IDS_report_electrocardiogram_analysis);
    private static final String e = BaseApplication.e().getString(R$string.IDS_report_oxygen_sugar_pressure);
    private static final String b = BaseApplication.e().getString(R$string.IDS_report_indoor_running);
    private static final String c = BaseApplication.e().getString(R$string.IDS_report_body_composition_analysis);
    private static boolean d = false;
    private static boolean j = true;
    private final List<HashMap<String, Bitmap>> x = new ArrayList();
    private ArrayList<String> y = new ArrayList<>();
    private final HealthToolBar.OnSingleTapListener t = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.main.stories.exhibitioninfo.activity.ExhibitionInfoActivity.3
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i) {
            if (nsn.a(500)) {
                LogUtil.a("ExhibitionInfoActivity", "click too fast");
                return;
            }
            if (i == 1) {
                ExhibitionInfoActivity.this.b();
                return;
            }
            if (i == 2) {
                boolean unused = ExhibitionInfoActivity.j = !ExhibitionInfoActivity.j;
                if (ExhibitionInfoActivity.j) {
                    ExhibitionInfoActivity.this.m();
                    return;
                } else {
                    ExhibitionInfoActivity.this.s();
                    return;
                }
            }
            if (i != 3) {
                if (i == 4) {
                    ExhibitionInfoActivity.this.j();
                    return;
                } else {
                    LogUtil.a("ExhibitionInfoActivity", "wrong position");
                    return;
                }
            }
            if (ExhibitionInfoActivity.this.y.size() > 0) {
                ExhibitionInfoActivity exhibitionInfoActivity = ExhibitionInfoActivity.this;
                exhibitionInfoActivity.c(exhibitionInfoActivity.y);
            }
        }
    };

    class a extends Handler {
        private a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String string;
            String string2;
            if (message == null) {
                LogUtil.h("ExhibitionInfoActivity", "handleMessage: msg is null");
                return;
            }
            super.handleMessage(message);
            switch (message.what) {
                case 100:
                    ExhibitionInfoActivity.this.s.setVisibility(8);
                    ExhibitionInfoActivity.this.u.setVisibility(0);
                    break;
                case 101:
                    ExhibitionInfoActivity.this.r = ((Double) message.obj).doubleValue();
                    ExhibitionInfoActivity.this.i();
                    break;
                case 102:
                    if (message.obj instanceof HiHealthData) {
                        ExhibitionInfoActivity.this.p = (HiHealthData) message.obj;
                        ExhibitionInfoActivity.this.i();
                        break;
                    }
                    break;
                case 103:
                    ExhibitionInfoActivity.this.q = ((Double) message.obj).doubleValue();
                    ExhibitionInfoActivity.this.i();
                    break;
                case 104:
                    ExhibitionInfoActivity.this.r();
                    Context context = ExhibitionInfoActivity.this.i;
                    if (ExhibitionInfoActivity.d) {
                        string = BaseApplication.e().getString(R$string.IDS_generating_report_success);
                    } else {
                        string = BaseApplication.e().getString(R$string.IDS_generating_report_fail);
                    }
                    nrh.d(context, string);
                    return;
                case 105:
                    boolean booleanValue = ((Boolean) message.obj).booleanValue();
                    Context context2 = ExhibitionInfoActivity.this.i;
                    if (booleanValue) {
                        string2 = BaseApplication.e().getString(R$string.IDS_delete_report_success);
                    } else {
                        string2 = BaseApplication.e().getString(R$string.IDS_delete_report_fail);
                    }
                    nrh.d(context2, string2);
                    return;
                case 106:
                    nrh.d(ExhibitionInfoActivity.this.i, BaseApplication.e().getString(R$string.IDS_report_not_exist));
                    return;
                default:
                    LogUtil.a("ExhibitionInfoActivity", "MyHandler what unknown");
                    break;
            }
            ExhibitionInfoActivity.this.x();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        o();
        ExhibitionAdapter exhibitionAdapter = this.n;
        if (exhibitionAdapter != null) {
            exhibitionAdapter.e(this.x);
            this.n.notifyDataSetChanged();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = this;
        setContentView(R.layout.activity_report);
        this.o = new a();
        n();
        l();
        o();
        f();
    }

    private void o() {
        this.x.clear();
        if (this.m != null) {
            HashMap<String, Bitmap> hashMap = new HashMap<>();
            hashMap.put(f9716a, this.m);
            this.x.add(hashMap);
        }
        HashMap<String, Bitmap> hashMap2 = new HashMap<>();
        hashMap2.put(e, this.h);
        this.x.add(hashMap2);
        if (this.v != null) {
            HashMap<String, Bitmap> hashMap3 = new HashMap<>();
            hashMap3.put(b, this.v);
            this.x.add(hashMap3);
        }
        if (this.g != null) {
            HashMap<String, Bitmap> hashMap4 = new HashMap<>();
            hashMap4.put(c, this.g);
            this.x.add(hashMap4);
        }
    }

    private void n() {
        h();
        this.u = (LinearLayout) findViewById(R.id.report_info);
        this.l = (HealthRecycleView) findViewById(R.id.report_data);
        this.s = (RelativeLayout) findViewById(R.id.report_loading);
    }

    private void h() {
        HealthToolBar healthToolBar = (HealthToolBar) findViewById(R.id.select_view);
        this.w = healthToolBar;
        healthToolBar.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        healthToolBar.setIcon(1, R.drawable._2131431179_res_0x7f0b0f0b);
        healthToolBar.setIconTitle(1, getResources().getString(R$string.IDS_contact_delete_select_all));
        healthToolBar.setIcon(2, R.drawable._2131431180_res_0x7f0b0f0c);
        healthToolBar.setIconTitle(2, getResources().getString(R$string.IDS_hw_health_blood_oxygen));
        healthToolBar.setIcon(3, R.drawable._2131431187_res_0x7f0b0f13);
        healthToolBar.setIconTitle(3, getResources().getString(R$string.IDS_report_print));
        healthToolBar.setIconEnabled(3, false);
        healthToolBar.cHe_(3).setAlpha(0.38f);
        healthToolBar.setIcon(4, R.drawable._2131430279_res_0x7f0b0b87);
        healthToolBar.setIconTitle(4, getResources().getString(R$string.IDS_report_delete));
        healthToolBar.setOnSingleTapListener(this.t);
        healthToolBar.cHf_(this);
    }

    private void l() {
        i();
        ThreadPoolManager.d().execute(new Runnable() { // from class: pgt
            @Override // java.lang.Runnable
            public final void run() {
                ExhibitionInfoActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        k();
        String b2 = SharedPreferenceManager.b(this.i, JsInteractAddition.BI_ERROR_CODE_INVALID_AT, "SP_REPORT_RUN");
        LogUtil.a("ExhibitionInfoActivity", "reportRunUri == ", b2);
        String b3 = SharedPreferenceManager.b(this.i, JsInteractAddition.BI_ERROR_CODE_INVALID_AT, "SP_REPORT_BODY");
        LogUtil.a("ExhibitionInfoActivity", "reportBodyUri == ", b3);
        if (g()) {
            ExhibitionEcgFragment exhibitionEcgFragment = new ExhibitionEcgFragment();
            exhibitionEcgFragment.d(this.i);
            exhibitionEcgFragment.c();
            this.m = exhibitionEcgFragment.dpa_();
        }
        if (!TextUtils.isEmpty(b2)) {
            ExhibitionRunFragment exhibitionRunFragment = new ExhibitionRunFragment();
            exhibitionRunFragment.c(this.i);
            exhibitionRunFragment.b();
            this.v = exhibitionRunFragment.dpo_();
        }
        if (!TextUtils.isEmpty(b3)) {
            ExhibitionBodyFragment exhibitionBodyFragment = new ExhibitionBodyFragment();
            exhibitionBodyFragment.a(this.i);
            exhibitionBodyFragment.e();
            this.g = exhibitionBodyFragment.doU_();
        }
        Message obtainMessage = this.o.obtainMessage();
        obtainMessage.what = 100;
        this.o.sendMessage(obtainMessage);
    }

    private void k() {
        m();
        p();
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ExhibitionBloodFragment exhibitionBloodFragment = new ExhibitionBloodFragment();
        this.k = exhibitionBloodFragment;
        exhibitionBloodFragment.d(this.i, this.r, this.p, this.q);
        this.k.b();
        this.h = this.k.doQ_();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.w.cHe_(1).isSelected()) {
            this.w.cHe_(1).setSelected(false);
            this.w.cHe_(3).setAlpha(0.38f);
            this.w.cHe_(3).setEnabled(false);
            this.y.clear();
        } else {
            this.w.cHe_(1).setSelected(true);
            this.w.cHe_(3).setAlpha(1.0f);
            this.w.cHe_(3).setEnabled(true);
        }
        ExhibitionAdapter exhibitionAdapter = this.n;
        if (exhibitionAdapter != null) {
            exhibitionAdapter.b();
            this.n.notifyDataSetChanged();
        }
    }

    private boolean g() {
        String str = null;
        try {
            str = getExternalFilesDir(null).getCanonicalPath() + "/h5pro/files/com.huawei.health.h5.ecg";
        } catch (IOException unused) {
            LogUtil.b("ExhibitionInfoActivity", "content and pdfPath can not be null!");
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        LogUtil.a("ExhibitionInfoActivity", "fileDir = ", str);
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            LogUtil.h("ExhibitionInfoActivity", "ecgPdfExist ecgFile not exist or not Directory");
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < listFiles.length; i++) {
            if (!listFiles[i].isDirectory()) {
                String name = listFiles[i].getName();
                if (name.trim().toLowerCase(Locale.ENGLISH).endsWith(".pdf")) {
                    arrayList.add(name);
                }
            }
        }
        return arrayList.size() > 0;
    }

    private void f() {
        this.l.setLayoutManager(new GridLayoutManager((Context) this, 2, 1, false));
        ExhibitionAdapter exhibitionAdapter = new ExhibitionAdapter(this, this.x, this);
        this.n = exhibitionAdapter;
        this.l.setAdapter(exhibitionAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<String> list) {
        FileOutputStream fileOutputStream;
        if (koq.b(list)) {
            LogUtil.b("ExhibitionInfoActivity", "content and pdfPath can not be null!");
            return;
        }
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        e(arrayList, list);
        PdfDocument pdfDocument = new PdfDocument();
        int widthMils = (PrintAttributes.MediaSize.ISO_A4.getWidthMils() * 72) / 1000;
        float width = widthMils / arrayList.get(0).getWidth();
        int height = (int) (arrayList.get(0).getHeight() * width);
        Matrix matrix = new Matrix();
        matrix.postScale(width, width);
        Paint paint = new Paint(1);
        for (int i = 0; i < arrayList.size(); i++) {
            PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(widthMils, height, i).create());
            startPage.getCanvas().drawBitmap(arrayList.get(i), matrix, paint);
            pdfDocument.finishPage(startPage);
        }
        try {
            fileOutputStream = new FileOutputStream(new File(BaseApplication.e().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "report.pdf"));
        } catch (IOException unused) {
            LogUtil.b("ExhibitionInfoActivity", "Failed to generate the PDF.");
            fileOutputStream = null;
        }
        dph_(pdfDocument, fileOutputStream);
    }

    private void e(ArrayList<Bitmap> arrayList, List<String> list) {
        arrayList.add(dpi_());
        if (list.contains(f9716a)) {
            arrayList.add(this.m);
        }
        if (list.contains(e)) {
            arrayList.add(this.h);
        }
        if (list.contains(b)) {
            arrayList.add(this.v);
        }
        if (list.contains(c)) {
            arrayList.add(this.g);
        }
    }

    public Bitmap dpi_() {
        View inflate = View.inflate(this, R.layout.report_cover_layout, null);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.report_cover_pdf);
        ((TextView) inflate.findViewById(R.id.report_date)).setText(rsr.i(System.currentTimeMillis()));
        pgw.dpt_(relativeLayout, 100);
        return pgw.dps_(inflate);
    }

    private void dph_(final PdfDocument pdfDocument, final FileOutputStream fileOutputStream) {
        d = false;
        t();
        ThreadPoolManager.d().execute(new Runnable() { // from class: pgo
            @Override // java.lang.Runnable
            public final void run() {
                ExhibitionInfoActivity.this.dpj_(pdfDocument, fileOutputStream);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void dpj_(PdfDocument pdfDocument, FileOutputStream fileOutputStream) {
        try {
            try {
                pdfDocument.writeTo(fileOutputStream);
                d = true;
                Message obtainMessage = this.o.obtainMessage();
                obtainMessage.what = 104;
                this.o.sendMessage(obtainMessage);
                pdfDocument.close();
                pdfDocument = pdfDocument;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                        pdfDocument = pdfDocument;
                    } catch (IOException unused) {
                        Object[] objArr = {"Pdf create error"};
                        LogUtil.b("ExhibitionInfoActivity", objArr);
                        pdfDocument = objArr;
                    }
                }
            } catch (Throwable th) {
                pdfDocument.close();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused2) {
                        LogUtil.b("ExhibitionInfoActivity", "Pdf create error");
                    }
                }
                throw th;
            }
        } catch (IOException unused3) {
            LogUtil.b("ExhibitionInfoActivity", "PDF CREATE FAIL");
            pdfDocument.close();
            pdfDocument = pdfDocument;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    pdfDocument = pdfDocument;
                } catch (IOException unused4) {
                    Object[] objArr2 = {"Pdf create error"};
                    LogUtil.b("ExhibitionInfoActivity", objArr2);
                    pdfDocument = objArr2;
                }
            }
        }
    }

    private void t() {
        if (this.f == null) {
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.f = a2;
            a2.e(BaseApplication.e().getString(R$string.IDS_generating_report));
            this.f.a();
            this.f.setCancelable(false);
            this.f.show();
            return;
        }
        LogUtil.a("ExhibitionInfoActivity", "showCreatingDialog: mCreatingDialog is not null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        CommonDialog21 commonDialog21 = this.f;
        if (commonDialog21 != null) {
            commonDialog21.cancel();
            this.f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        File file = new File(BaseApplication.e().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "report.pdf");
        if (file.exists()) {
            if (file.isFile()) {
                boolean delete = file.delete();
                Message obtainMessage = this.o.obtainMessage();
                obtainMessage.what = 105;
                obtainMessage.obj = Boolean.valueOf(delete);
                this.o.sendMessage(obtainMessage);
                return;
            }
            return;
        }
        Message obtainMessage2 = this.o.obtainMessage();
        obtainMessage2.what = 106;
        this.o.sendMessage(obtainMessage2);
    }

    @Override // com.huawei.ui.main.stories.exhibitioninfo.adapter.ExhibitionAdapter.OnReportSelectChangeListener
    public void setSelectReport(String str, boolean z) {
        if (z) {
            if (str != null && !this.y.contains(str)) {
                this.y.add(str);
            }
        } else {
            this.y.remove(str);
        }
        if (this.y.size() > 0) {
            this.w.cHe_(3).setAlpha(1.0f);
            this.w.cHe_(3).setEnabled(true);
            return;
        }
        this.w.cHe_(3).setAlpha(0.38f);
        this.w.cHe_(3).setEnabled(false);
        if (this.w.cHe_(1).isSelected()) {
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.r = 0.0d;
        i();
        o();
        ExhibitionAdapter exhibitionAdapter = this.n;
        if (exhibitionAdapter != null) {
            exhibitionAdapter.e(this.x);
            this.n.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long currentTimeMillis = System.currentTimeMillis();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setConstantsKey(new String[]{"lastBloodOxygenSaturation"});
        hiDataReadOption.setType(new int[]{2103, 2107});
        HiHealthNativeApi.a(this.i).readHiHealthData(hiDataReadOption, new d());
    }

    class d implements HiDataReadResultListener {
        private d() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (obj == null) {
                LogUtil.h("ExhibitionInfoActivity", "onResult data is null");
                return;
            }
            SparseArray sparseArray = new SparseArray(16);
            if (obj instanceof SparseArray) {
                sparseArray = (SparseArray) obj;
            }
            if (sparseArray.size() > 0) {
                ExhibitionInfoActivity.this.dpf_(sparseArray);
            } else {
                LogUtil.h("ExhibitionInfoActivity", "onResult sparseArray size less than or equal to zero");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("ExhibitionInfoActivity", "onResultIntent errorCode ", Integer.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dpf_(SparseArray<Object> sparseArray) {
        List arrayList = new ArrayList(16);
        Object obj = sparseArray.get(2103);
        if (obj instanceof List) {
            arrayList = (List) obj;
        }
        LogUtil.a("ExhibitionInfoActivity", "dealWithData oxygenMeasureList size ", Integer.valueOf(arrayList.size()));
        if (koq.b(arrayList)) {
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
        if (hiHealthData == null) {
            LogUtil.h("ExhibitionInfoActivity", "dealWithData healthData is null");
            return;
        }
        double d2 = hiHealthData.getInt("point_value");
        Message obtainMessage = this.o.obtainMessage();
        obtainMessage.what = 101;
        obtainMessage.obj = Double.valueOf(d2);
        this.o.sendMessage(obtainMessage);
    }

    private void p() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{2006, 2007});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC"});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setReadType(0);
        long currentTimeMillis = System.currentTimeMillis();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(currentTimeMillis);
        hiAggregateOption.setCount(1);
        HiHealthManager.d(this.i).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.exhibitioninfo.activity.ExhibitionInfoActivity.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (!koq.b(list)) {
                    ExhibitionInfoActivity.this.a(list.get(0));
                } else {
                    LogUtil.h("ExhibitionInfoActivity", "readCardData onResult dataList is empty");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a("ExhibitionInfoActivity", "readCardData errorCode ", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HiHealthData hiHealthData) {
        Message obtainMessage = this.o.obtainMessage();
        obtainMessage.what = 102;
        obtainMessage.obj = hiHealthData;
        this.o.sendMessage(obtainMessage);
    }

    private void q() {
        LogUtil.a("ExhibitionInfoActivity", "showBloodSugar readCardData called");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        LogUtil.a("ExhibitionInfoActivity", "showBloodSugar startTime = ", Long.valueOf(hiDataReadOption.getStartTime()), ", endTime = ", Long.valueOf(hiDataReadOption.getEndTime()));
        hiDataReadOption.setType(new int[]{2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106});
        HiHealthManager.d(this.i).readHiHealthData(hiDataReadOption, new c());
    }

    class c implements HiDataReadResultListener {
        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private c() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("ExhibitionInfoActivity", "showBloodSugar onResult called", Integer.valueOf(i));
            if (obj instanceof SparseArray) {
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() > 0) {
                    ExhibitionInfoActivity.this.dpg_(sparseArray);
                } else {
                    LogUtil.h("ExhibitionInfoActivity", "data none");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dpg_(SparseArray<Object> sparseArray) {
        HiHealthData dVJ_ = scj.dVJ_(sparseArray, scj.dVI_(sparseArray, scj.dVQ_(sparseArray, scj.dVM_(sparseArray, scj.dVN_(sparseArray, scj.dVO_(sparseArray, scj.dVP_(sparseArray, scj.dVK_(sparseArray, scj.dVL_(sparseArray, new HiHealthData())))))))));
        if (System.currentTimeMillis() < dVJ_.getEndTime()) {
            LogUtil.h("ExhibitionInfoActivity", "The current time is earlier than the last blood sugar time.");
            return;
        }
        Message obtainMessage = this.o.obtainMessage();
        obtainMessage.what = 103;
        obtainMessage.obj = Double.valueOf(dVJ_.getDouble("point_value"));
        this.o.sendMessage(obtainMessage);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
