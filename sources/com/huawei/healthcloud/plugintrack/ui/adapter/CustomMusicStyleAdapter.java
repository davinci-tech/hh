package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.bgm.choosedownload.MusicInformation;
import com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gww;
import defpackage.hai;
import defpackage.ham;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CustomMusicStyleAdapter extends RecyclerView.Adapter<d> {
    private DynamicTrackDownloadUtils.DownloadResponseCallback c;
    private Context e;
    private MusicItemClickListener f;
    private List<MusicInformation> g;
    private LayoutInflater h;
    private int i;
    private int j;
    private gww l;
    private long b = 0;

    /* renamed from: a, reason: collision with root package name */
    private boolean f3694a = false;
    private boolean d = true;

    public interface MusicItemClickListener {
        void onClickListener(String str, String str2);

        void onMuteClick();
    }

    public CustomMusicStyleAdapter(Context context, List<MusicInformation> list, MusicItemClickListener musicItemClickListener) {
        this.i = 0;
        this.j = 0;
        this.e = context;
        this.h = LayoutInflater.from(context);
        this.g = list;
        if (koq.b(list)) {
            MusicInformation musicInformation = new MusicInformation();
            musicInformation.setIsSelected(true);
            ArrayList arrayList = new ArrayList();
            this.g = arrayList;
            arrayList.add(musicInformation);
        }
        this.g.get(0).setIsMute(true);
        for (int i = 0; i < this.g.size(); i++) {
            if (this.g.get(i).getIsSelected()) {
                this.i = i;
                this.j = i;
            }
        }
        this.f = musicItemClickListener;
        this.l = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.g.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: bdo_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(this.h.inflate(R.layout.choose_download_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        dVar.e(this.g.get(i), dVar, i);
    }

    public void d() {
        hai.a().e();
    }

    public void e(boolean z) {
        int i = z ? this.j : this.i;
        this.j = i;
        this.i = i;
        if (!koq.b(this.g)) {
            int i2 = 0;
            while (i2 < this.g.size()) {
                this.g.get(i2).setIsDownloading(false);
                this.g.get(i2).setIsSelected(i2 == this.j);
                i2++;
            }
        }
        this.d = false;
        notifyDataSetChanged();
    }

    public void e() {
        this.d = true;
    }

    public void a() {
        if (koq.d(this.g, this.j)) {
            if (this.g.get(this.j).getIsDownloaded() || this.j == 0) {
                MusicInformation musicInformation = this.g.get(this.j);
                final String c = ham.c(musicInformation.getMusicName(), musicInformation.getIsDefault());
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CustomMusicStyleAdapter.this.j == 0) {
                            CustomMusicStyleAdapter.this.l.e((String) null);
                        } else {
                            CustomMusicStyleAdapter.this.l.e(c);
                        }
                        CustomMusicStyleAdapter.this.l.a(new Gson().toJson(CustomMusicStyleAdapter.this.g));
                    }
                });
                notifyDataSetChanged();
            }
        }
    }

    class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f3696a;
        private boolean c;
        private boolean d;
        private boolean e;
        private ImageView f;
        private HealthCheckBox g;
        private HealthTextView h;
        private ImageView i;
        private MusicInformation j;
        private int k;
        private ImageView m;

        d(View view) {
            super(view);
            this.c = false;
            this.d = false;
            this.e = false;
            this.k = 0;
            bdp_(view);
        }

        public void e(MusicInformation musicInformation, d dVar, int i) {
            this.j = musicInformation;
            if (musicInformation == null) {
                this.j = new MusicInformation();
            }
            this.e = this.j.getIsMute();
            this.c = this.j.getIsSelected();
            this.d = this.j.getIsDownloaded();
            d();
            dVar.a(this.j.getIsSelected());
            if (this.e) {
                a(dVar, i);
            } else {
                c(this.j, dVar, i);
            }
        }

        private void a(d dVar, final int i) {
            dVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!nsn.a(1000)) {
                        CustomMusicStyleAdapter.this.j = i;
                        int i2 = 0;
                        while (i2 < CustomMusicStyleAdapter.this.g.size()) {
                            ((MusicInformation) CustomMusicStyleAdapter.this.g.get(i2)).setIsSelected(i2 == i);
                            i2++;
                        }
                        if (CustomMusicStyleAdapter.this.f != null) {
                            CustomMusicStyleAdapter.this.f.onMuteClick();
                        }
                        CustomMusicStyleAdapter.this.d();
                        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.3.3
                            @Override // java.lang.Runnable
                            public void run() {
                                CustomMusicStyleAdapter.this.l.a(new Gson().toJson(CustomMusicStyleAdapter.this.g));
                            }
                        });
                        CustomMusicStyleAdapter.this.notifyDataSetChanged();
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        private void c(final MusicInformation musicInformation, d dVar, final int i) {
            dVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!nsn.a(1000)) {
                        if (!CustomMusicStyleAdapter.this.f3694a || musicInformation.getIsDownloading() || musicInformation.getIsDownloaded() || musicInformation.getIsMute()) {
                            CustomMusicStyleAdapter.this.j = i;
                            int i2 = 0;
                            while (i2 < CustomMusicStyleAdapter.this.g.size()) {
                                ((MusicInformation) CustomMusicStyleAdapter.this.g.get(i2)).setIsSelected(i2 == i);
                                i2++;
                            }
                            if (d.this.e(musicInformation)) {
                                LogUtil.c("Track_CustomMusicStyleAdapter", "setOnClickListener : The download is canceled because", "the network is abnormal or the download is in progress.");
                                ViewClickInstrumentation.clickOnView(view);
                                return;
                            } else {
                                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.2.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        CustomMusicStyleAdapter.this.l.a(new Gson().toJson(CustomMusicStyleAdapter.this.g));
                                    }
                                });
                                CustomMusicStyleAdapter.this.notifyDataSetChanged();
                                ViewClickInstrumentation.clickOnView(view);
                                return;
                            }
                        }
                        nrh.d(CustomMusicStyleAdapter.this.e, CustomMusicStyleAdapter.this.e.getResources().getString(R.string._2130843637_res_0x7f0217f5));
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e(MusicInformation musicInformation) {
            String c = CommonUtil.c(ham.c(musicInformation.getMusicName(), musicInformation.getIsDefault()));
            if (c == null || !new File(c).exists()) {
                musicInformation.setIsDownloaded(false);
            }
            if (musicInformation.getIsDownloaded()) {
                if (CustomMusicStyleAdapter.this.f != null) {
                    String musicResourcePath = musicInformation.getMusicResourcePath();
                    CustomMusicStyleAdapter.this.f.onClickListener(musicResourcePath, musicInformation.getMusicName());
                    hai.a().e();
                    hai.a().d(musicResourcePath);
                }
            } else {
                hai.a().e();
                if (!CommonUtil.aa(CustomMusicStyleAdapter.this.e)) {
                    nrh.d(CustomMusicStyleAdapter.this.e, CustomMusicStyleAdapter.this.e.getResources().getString(R.string._2130841392_res_0x7f020f30));
                    CustomMusicStyleAdapter.this.notifyDataSetChanged();
                    return true;
                }
                if (!musicInformation.getIsDownloading()) {
                    d(musicInformation, CustomMusicStyleAdapter.this.g);
                } else {
                    nrh.d(CustomMusicStyleAdapter.this.e, CustomMusicStyleAdapter.this.e.getResources().getString(R.string._2130843637_res_0x7f0217f5));
                    CustomMusicStyleAdapter.this.notifyDataSetChanged();
                    return true;
                }
            }
            return false;
        }

        private void d(final MusicInformation musicInformation, final List<MusicInformation> list) {
            CustomMusicStyleAdapter.this.c = new DynamicTrackDownloadUtils.DownloadResponseCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.1
                @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
                public void onProgress(int i) {
                }

                @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
                public void onMobile(int i) {
                    d.this.c(String.format(CustomMusicStyleAdapter.this.e.getResources().getString(R.string._2130843628_res_0x7f0217ec), CustomMusicStyleAdapter.this.e.getString(R.string.IDS_device_upgrade_file_size_mb, UnitUtil.e(i / 1048576.0d, 1, 1))), musicInformation.getUuid());
                }

                @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
                public void onStart() {
                    musicInformation.setIsDownloading(true);
                    CustomMusicStyleAdapter.this.f3694a = true;
                }

                @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
                public void onSuccess() {
                    d.this.a(musicInformation, (List<MusicInformation>) list);
                }

                @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
                public void onFail() {
                    if (d.this.b()) {
                        return;
                    }
                    musicInformation.setIsDownloading(false);
                    CustomMusicStyleAdapter.this.f3694a = false;
                    if (CustomMusicStyleAdapter.this.e != null) {
                        nrh.d(CustomMusicStyleAdapter.this.e, CustomMusicStyleAdapter.this.e.getResources().getString(R.string._2130841543_res_0x7f020fc7));
                    }
                    CustomMusicStyleAdapter.this.notifyDataSetChanged();
                }
            };
            DynamicTrackDownloadUtils.d().c(musicInformation.getUuid(), CustomMusicStyleAdapter.this.c);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(MusicInformation musicInformation, final List<MusicInformation> list) {
            musicInformation.setIsDownloading(false);
            CustomMusicStyleAdapter.this.f3694a = false;
            if (!new File(ham.c(musicInformation.getMusicName(), musicInformation.getIsDefault())).exists()) {
                CustomMusicStyleAdapter.this.c.onFail();
                return;
            }
            musicInformation.setIsDownloaded(true);
            String c = ham.c(musicInformation.getMusicName(), musicInformation.getIsDefault());
            musicInformation.setMusicResourcePath(c);
            if (CustomMusicStyleAdapter.this.f != null) {
                CustomMusicStyleAdapter.this.f.onClickListener(c, musicInformation.getMusicName());
            }
            if (CustomMusicStyleAdapter.this.d) {
                hai.a().d(c);
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.4
                @Override // java.lang.Runnable
                public void run() {
                    CustomMusicStyleAdapter.this.l.a(new Gson().toJson(list));
                }
            });
            CustomMusicStyleAdapter.this.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(String str, final String str2) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(CustomMusicStyleAdapter.this.e).e(str).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DynamicTrackDownloadUtils.d().d(str2, CustomMusicStyleAdapter.this.c);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMusicStyleAdapter.d.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            e.setCancelable(false);
            e.show();
        }

        boolean b() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - CustomMusicStyleAdapter.this.b < 3000) {
                return true;
            }
            CustomMusicStyleAdapter.this.b = elapsedRealtime;
            return false;
        }

        private void d() {
            if (this.e) {
                this.h.setText(CustomMusicStyleAdapter.this.e.getResources().getString(R.string._2130843636_res_0x7f0217f4));
                this.i.setVisibility(8);
                this.f.setVisibility(8);
                this.m.setVisibility(0);
                a();
                c();
                return;
            }
            e();
            this.h.setText(this.j.getMusicName());
        }

        private void bdp_(View view) {
            this.f3696a = (ImageView) view.findViewById(R.id.music_picture);
            this.h = (HealthTextView) view.findViewById(R.id.music_name);
            this.i = (ImageView) view.findViewById(R.id.music_status);
            this.f = (ImageView) view.findViewById(R.id.music_mask);
            this.m = (ImageView) view.findViewById(R.id.no_voice);
            this.g = (HealthCheckBox) view.findViewById(R.id.music_select);
            if (LanguageUtil.bc(CustomMusicStyleAdapter.this.e)) {
                this.m.setRotationY(180.0f);
            }
            this.k = nsn.c(CustomMusicStyleAdapter.this.e, 4.0f);
        }

        private void a() {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(CustomMusicStyleAdapter.this.e.getResources().getColor(R.color._2131296934_res_0x7f0902a6));
            int c = nsn.c(CustomMusicStyleAdapter.this.e, 64.0f);
            Bitmap createBitmap = Bitmap.createBitmap(c, c, Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(createBitmap);
            float f = c;
            RectF rectF = new RectF(0.0f, 0.0f, f, f);
            float f2 = this.k;
            canvas.drawRoundRect(rectF, f2, f2, paint);
            nrf.cHt_(CustomMusicStyleAdapter.this.e, this.f3696a);
            this.f3696a.setImageDrawable(new BitmapDrawable(CustomMusicStyleAdapter.this.e.getResources(), createBitmap));
        }

        private void c() {
            if (this.c) {
                this.g.setChecked(true);
                this.g.setVisibility(0);
            } else {
                this.g.setChecked(false);
                this.g.setVisibility(8);
            }
        }

        private void e() {
            MusicInformation musicInformation = this.j;
            if (musicInformation != null && musicInformation.getMusicBackgroundPath() != null) {
                nrf.cHt_(CustomMusicStyleAdapter.this.e, this.f3696a);
                nrf.cIs_(new File(this.j.getMusicBackgroundPath()), this.f3696a);
            }
            this.m.setVisibility(8);
            boolean z = this.c;
            if (z && this.d) {
                this.i.setVisibility(0);
                this.i.setImageDrawable(CustomMusicStyleAdapter.this.e.getResources().getDrawable(R.drawable._2131431905_res_0x7f0b11e1));
                this.f.setVisibility(8);
            } else if (!z && this.d) {
                this.i.setVisibility(8);
                this.f.setVisibility(8);
            } else {
                this.i.setVisibility(0);
                this.i.setImageDrawable(CustomMusicStyleAdapter.this.e.getResources().getDrawable(R.drawable._2131431904_res_0x7f0b11e0));
                this.f.setVisibility(0);
            }
            c();
        }

        void a(boolean z) {
            this.c = z;
            c();
            if (this.e) {
                return;
            }
            boolean z2 = this.c;
            if (z2 && this.d) {
                this.i.setVisibility(0);
                this.i.setImageDrawable(CustomMusicStyleAdapter.this.e.getResources().getDrawable(R.drawable._2131431905_res_0x7f0b11e1));
            } else if (!z2 && this.d) {
                this.i.setVisibility(8);
            } else {
                this.i.setVisibility(0);
                this.i.setImageDrawable(CustomMusicStyleAdapter.this.e.getResources().getDrawable(R.drawable._2131431904_res_0x7f0b11e0));
            }
        }
    }
}
