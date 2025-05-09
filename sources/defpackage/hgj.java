package defpackage;

import android.app.Activity;
import android.view.View;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.bgm.choosedownload.MusicInformation;
import com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils;
import com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hgj {

    /* renamed from: a, reason: collision with root package name */
    private Activity f13158a;
    private CustomProgressDialog.Builder b;
    private CustomMusicStyleAdapter c;
    private CustomProgressDialog e;
    private HealthRecycleView f;
    private String h;
    private CustomMusicStyleAdapter.MusicItemClickListener i;
    private List<MusicInformation> j = new ArrayList(16);
    private boolean d = false;

    public hgj(Activity activity) {
        this.f13158a = activity;
        ThreadPoolManager.d().execute(new Runnable() { // from class: hgj.2
            @Override // java.lang.Runnable
            public void run() {
                gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
                hgj.this.h = gwwVar.l();
                hgj hgjVar = hgj.this;
                hgjVar.j = (List) gvv.a(hgjVar.h, new TypeToken<ArrayList<MusicInformation>>() { // from class: hgj.2.2
                });
            }
        });
    }

    public void b(CustomMusicStyleAdapter.MusicItemClickListener musicItemClickListener) {
        this.i = musicItemClickListener;
    }

    public void a(final boolean z) {
        DynamicTrackDownloadUtils.e();
        DynamicTrackDownloadUtils.d().a(new DynamicTrackDownloadUtils.DownloadResponseCallback() { // from class: hgj.4
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
            public void onStart() {
                hgj.this.c();
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
            public void onProgress(int i) {
                hgj.this.d(i);
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
            public void onSuccess() {
                hgj.this.d();
                hgj.this.h = DynamicTrackDownloadUtils.d().h();
                hgj hgjVar = hgj.this;
                hgjVar.j = (List) gvv.a(hgjVar.h, new TypeToken<ArrayList<MusicInformation>>() { // from class: hgj.4.1
                });
                if (z) {
                    hgj.this.e();
                }
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
            public void onFail() {
                hgj.this.d();
                if (hgj.this.f13158a != null) {
                    nrh.d(hgj.this.f13158a, hgj.this.f13158a.getResources().getString(R.string._2130841543_res_0x7f020fc7));
                }
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
            public void onMobile(int i) {
                hgj.this.c(String.format(hgj.this.f13158a.getResources().getString(R.string._2130843628_res_0x7f0217ec), hgj.this.f13158a.getString(R.string.IDS_device_upgrade_file_size_mb, new Object[]{UnitUtil.e(i / 1048576.0d, 1, 1)})));
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f13158a).e(str).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: hgj.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DynamicTrackDownloadUtils.d().a();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: hgj.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public void bdh_(View view) {
        if (view == null) {
            LogUtil.b("Track_MusicInteractor", "[handleChooseMusic] view is null");
            return;
        }
        this.f = (HealthRecycleView) view.findViewById(R.id.track_music_recycleview);
        if (DynamicTrackDownloadUtils.d().g()) {
            a(true);
        } else {
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f == null) {
            LogUtil.b("Track_MusicInteractor", "[setRecyclerView] mMusicScrollView is null");
            return;
        }
        if (this.d) {
            return;
        }
        this.c = new CustomMusicStyleAdapter(this.f13158a, this.j, this.i);
        HealthLinearLayoutManager healthLinearLayoutManager = new HealthLinearLayoutManager(this.f13158a);
        healthLinearLayoutManager.setOrientation(0);
        this.f.setLayoutManager(healthLinearLayoutManager);
        this.f.setAdapter(this.c);
        this.d = true;
    }

    public void b() {
        CustomMusicStyleAdapter customMusicStyleAdapter = this.c;
        if (customMusicStyleAdapter != null) {
            customMusicStyleAdapter.e();
        }
    }

    public void e(boolean z) {
        CustomMusicStyleAdapter customMusicStyleAdapter = this.c;
        if (customMusicStyleAdapter != null) {
            customMusicStyleAdapter.d();
            this.c.e(z);
            this.c.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.b("Track_MusicInteractor", "startDownloadProgress progress exist");
            return;
        }
        this.e = new CustomProgressDialog(this.f13158a);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.f13158a);
        this.b = builder;
        builder.d(this.f13158a.getString(R.string._2130843637_res_0x7f0217f5)).cyH_(new View.OnClickListener() { // from class: hgj.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_MusicInteractor", "startDownLoadProgress onclick cancel");
                DynamicTrackDownloadUtils.d().b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e = this.b.e();
        this.e = e;
        e.setCanceledOnTouchOutside(false);
        if (!this.f13158a.isFinishing()) {
            this.e.show();
            this.b.d(0);
            this.b.e(UnitUtil.e(0.0d, 2, 0));
            this.b.b();
        }
        LogUtil.a("Track_MusicInteractor", "mCustomProgressDialog.show()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog == null || !customProgressDialog.isShowing()) {
            return;
        }
        this.b.d(i);
        this.b.e(UnitUtil.e(i, 2, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.f13158a.isFinishing()) {
            return;
        }
        this.e.cancel();
    }
}
