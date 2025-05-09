package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.hah;
import defpackage.hak;
import defpackage.han;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrp;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class CustomMapStyleAdapter extends RecyclerView.Adapter<a> {

    /* renamed from: a, reason: collision with root package name */
    private String f3690a = null;
    private boolean b;
    private Context c;
    private a d;
    private int e;
    private MapInteractor.MapItemClickListener f;
    private int g;
    private List<hak> h;
    private boolean i;
    private LayoutInflater j;
    private MapInteractor.MapItemClickListener k;
    private int m;
    private View n;
    private List<hah> o;

    public CustomMapStyleAdapter(Context context, List<hak> list, int i, MapTypeDescription.MapType mapType, int i2, MapInteractor.MapItemClickListener mapItemClickListener) {
        this.g = 0;
        this.c = context;
        this.h = list;
        this.f = mapItemClickListener;
        if (list != null) {
            this.g = list.size();
            Iterator<hak> it = this.h.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (TextUtils.isEmpty(it.next().c())) {
                    this.b = true;
                    break;
                }
            }
        }
        this.j = LayoutInflater.from(this.c);
        this.i = true;
        this.m = i2;
        d(mapType, i, i2);
        LogUtil.a("Track_CustomMapStyleAdapter", "start mCheckBoxPosition = ", Integer.valueOf(this.e));
    }

    public void e(List<hah> list) {
        this.o = list;
        this.g = list == null ? 0 : list.size();
        e(this.e);
        notifyDataSetChanged();
    }

    public CustomMapStyleAdapter(Context context, List<hah> list, int i, MapInteractor.MapItemClickListener mapItemClickListener) {
        this.g = 0;
        this.c = context;
        this.o = list;
        this.k = mapItemClickListener;
        this.g = list == null ? 0 : list.size();
        this.j = LayoutInflater.from(this.c);
        this.i = false;
        e(i);
    }

    private void d(MapTypeDescription.MapType mapType, int i, int i2) {
        if (!this.b) {
            if (i2 == 2) {
                a(mapType, i);
                return;
            } else if (i2 == 3) {
                e(mapType, i);
                return;
            } else {
                b(mapType, i);
                return;
            }
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_CUSTOM || mapType == MapTypeDescription.MapType.DEFAULT_MAP_THREE_D) {
            e(i);
            return;
        }
        int c = c(mapType);
        for (int i3 = 0; i3 < this.g; i3++) {
            if (!TextUtils.isEmpty(this.h.get(i3).c()) && c == this.h.get(i3).b()) {
                this.e = i3;
            }
        }
    }

    public int e() {
        return this.e;
    }

    private void a(MapTypeDescription.MapType mapType, int i) {
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NORMAL) {
            this.e = 0;
            return;
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
            this.e = 1;
        } else if (mapType == MapTypeDescription.MapType.MAP_TYPE_CUSTOM) {
            e(i);
        } else {
            LogUtil.a("Track_CustomMapStyleAdapter", "checkPositionForGoogle other type");
        }
    }

    private void e(MapTypeDescription.MapType mapType, int i) {
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NORMAL) {
            this.e = 0;
            return;
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
            this.e = 1;
        } else if (mapType == MapTypeDescription.MapType.MAP_TYPE_CUSTOM || mapType == MapTypeDescription.MapType.DEFAULT_MAP_THREE_D) {
            e(i);
        } else {
            LogUtil.a("Track_CustomMapStyleAdapter", "checkPositionForHuawei other type");
        }
    }

    private void b(MapTypeDescription.MapType mapType, int i) {
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NORMAL) {
            this.e = 0;
            return;
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_SATELLITE) {
            this.e = 1;
            return;
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
            this.e = 2;
        } else if (mapType == MapTypeDescription.MapType.MAP_TYPE_NAVI) {
            this.e = 3;
        } else {
            e(i);
        }
    }

    private int c(MapTypeDescription.MapType mapType) {
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NORMAL) {
            return 1;
        }
        if (mapType != MapTypeDescription.MapType.MAP_TYPE_SATELLITE) {
            if (mapType == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
                return 3;
            }
            if (mapType == MapTypeDescription.MapType.MAP_TYPE_NAVI) {
                return 4;
            }
            LogUtil.h("Track_CustomMapStyleAdapter", "currentType error");
        }
        return 2;
    }

    private void e(int i) {
        if (i > this.g || i < 0) {
            this.e = 0;
        } else {
            this.e = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bdm_(String str, ImageView imageView) {
        if (str != null) {
            nrf.cHt_(this.c, imageView);
            nrf.cIt_(new File(str), new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(this.c.getApplicationContext(), (int) this.c.getResources().getDimension(R.dimen._2131362600_res_0x7f0a0328))), imageView);
            return;
        }
        LogUtil.h("Track_CustomMapStyleAdapter", "path = null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v6, types: [T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> T c(java.lang.String r6, java.lang.Class<T> r7) {
        /*
            r5 = this;
            java.lang.String r0 = "IOException"
            r1 = 0
            if (r6 != 0) goto L6
            return r1
        L6:
            java.lang.String r6 = r5.b(r6)
            java.lang.String r6 = health.compact.a.CommonUtil.c(r6)
            java.lang.String r2 = "Track_CustomMapStyleAdapter"
            if (r6 != 0) goto L1c
            java.lang.String r6 = "languagePath is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r6)
            return r1
        L1c:
            java.io.File r3 = new java.io.File
            r3.<init>(r6)
            boolean r6 = r3.exists()
            if (r6 == 0) goto L6a
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3f java.io.FileNotFoundException -> L41
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L3f java.io.FileNotFoundException -> L41
            java.lang.Object r1 = defpackage.ixu.d(r6, r7)     // Catch: java.lang.Throwable -> L3c java.io.FileNotFoundException -> L42
            r6.close()     // Catch: java.io.IOException -> L34
            goto L6a
        L34:
            java.lang.Object[] r6 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r6)
            goto L6a
        L3c:
            r7 = move-exception
            r1 = r6
            goto L5b
        L3f:
            r6 = move-exception
            goto L5c
        L41:
            r6 = r1
        L42:
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L3c
            java.lang.String r3 = "FileNotFoundException"
            r4 = 0
            r7[r4] = r3     // Catch: java.lang.Throwable -> L3c
            com.huawei.hwlogsmodel.LogUtil.h(r2, r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L6a
            r6.close()     // Catch: java.io.IOException -> L53
            goto L6a
        L53:
            java.lang.Object[] r6 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r6)
            goto L6a
        L5b:
            r6 = r7
        L5c:
            if (r1 == 0) goto L69
            r1.close()     // Catch: java.io.IOException -> L62
            goto L69
        L62:
            java.lang.Object[] r7 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r7)
        L69:
            throw r6
        L6a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMapStyleAdapter.c(java.lang.String, java.lang.Class):java.lang.Object");
    }

    private String b(String str) {
        if (TextUtils.isEmpty(this.f3690a)) {
            this.f3690a = han.e().d(str);
        }
        return han.e().b(str, this.f3690a);
    }

    public class e {

        @SerializedName("customStyleName")
        private String d;

        public String b() {
            return this.d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        LogUtil.a("Track_CustomMapStyleAdapter", "mItemCount = ", Integer.valueOf(this.g));
        return this.g;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: bdn_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.n = this.j.inflate(R.layout.track_map_style_select_recycleview_layout, viewGroup, false);
        a aVar = new a(this.n);
        this.d = aVar;
        return aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        if (this.i) {
            if (koq.d(this.h, i)) {
                aVar.a(this.h.get(i), i, aVar);
                return;
            } else {
                LogUtil.h("Track_CustomMapStyleAdapter", "mMapDataList is null");
                return;
            }
        }
        if (koq.d(this.o, i)) {
            aVar.a(this.o.get(i), i, aVar);
        } else {
            LogUtil.h("Track_CustomMapStyleAdapter", "mMarkDataList is null");
        }
    }

    class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f3691a;
        private ImageView b;
        private HealthCheckBox c;
        private ImageView e;

        a(View view) {
            super(view);
            this.e = (ImageView) view.findViewById(R.id.map_style_thumbnail);
            this.c = (HealthCheckBox) view.findViewById(R.id.retrack_select_satellate);
            this.f3691a = (HealthTextView) view.findViewById(R.id.map_style_description);
            this.b = (ImageView) view.findViewById(R.id.map_badge_icon);
        }

        public void a(final hah hahVar, final int i, final a aVar) {
            CustomMapStyleAdapter.this.bdm_(hahVar.e(), aVar.e);
            d(aVar, i);
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMapStyleAdapter.a.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (CustomMapStyleAdapter.this.k != null) {
                        CustomMapStyleAdapter.this.k.onClickListener(hahVar, i);
                        aVar.c.setChecked(true);
                        CustomMapStyleAdapter.this.e = i;
                        CustomMapStyleAdapter.this.notifyDataSetChanged();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        public void a(final hak hakVar, final int i, final a aVar) {
            d(hakVar, aVar, i);
            d(aVar, i);
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.CustomMapStyleAdapter.a.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (CustomMapStyleAdapter.this.f != null) {
                        CustomMapStyleAdapter.this.f.onClickListener(hakVar, i);
                        aVar.c.setChecked(true);
                        CustomMapStyleAdapter.this.e = i;
                        CustomMapStyleAdapter.this.notifyDataSetChanged();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        private void d(hak hakVar, a aVar, int i) {
            if (!TextUtils.isEmpty(hakVar.c())) {
                a(hakVar, aVar);
                return;
            }
            aVar.b.setVisibility(8);
            e eVar = (e) CustomMapStyleAdapter.this.c(hakVar.h(), e.class);
            CustomMapStyleAdapter.this.bdm_(hakVar.e(), this.e);
            if (eVar == null) {
                LogUtil.a("Track_CustomMapStyleAdapter", " customMapStyleLanguage == null   ", Integer.valueOf(i));
            } else {
                if (eVar.b() != null) {
                    LogUtil.a("Track_CustomMapStyleAdapter", "Language customMapStyleLanguage.getStyleName() = ", eVar.b());
                    this.f3691a.setText(eVar.b());
                    this.f3691a.setVisibility(0);
                    return;
                }
                LogUtil.h("Track_CustomMapStyleAdapter", " customMapStyleLanguage.getStyleName()  == null ");
            }
        }

        private void d(a aVar, int i) {
            if (i == CustomMapStyleAdapter.this.e) {
                aVar.c.setChecked(true);
                aVar.c.setVisibility(0);
            } else {
                aVar.c.setChecked(false);
                aVar.c.setVisibility(4);
            }
        }

        private void a(hak hakVar, a aVar) {
            String string;
            int i;
            int b = hakVar.b();
            nrf.cHt_(CustomMapStyleAdapter.this.c, aVar.e);
            nrf.cHt_(CustomMapStyleAdapter.this.c, aVar.b);
            if (b != 1) {
                i = R.drawable._2131431205_res_0x7f0b0f25;
                if (b == 2) {
                    string = CustomMapStyleAdapter.this.c.getResources().getString(R.string._2130839873_res_0x7f020941);
                } else if (b != 3) {
                    if (b == 4) {
                        string = CustomMapStyleAdapter.this.c.getResources().getString(R.string._2130839875_res_0x7f020943);
                        i = R.drawable._2131431203_res_0x7f0b0f23;
                    } else if (b == 5) {
                        string = CustomMapStyleAdapter.this.c.getResources().getString(R.string._2130840061_res_0x7f0209fd);
                    } else {
                        LogUtil.h("Track_CustomMapStyleAdapter", "default value is error");
                        i = -1;
                        string = "";
                    }
                } else if (CustomMapStyleAdapter.this.m == 1) {
                    string = CustomMapStyleAdapter.this.c.getResources().getString(R.string._2130843875_res_0x7f0218e3);
                    i = R.drawable._2131431202_res_0x7f0b0f22;
                } else {
                    string = CustomMapStyleAdapter.this.c.getResources().getString(R.string._2130839874_res_0x7f020942);
                    i = R.drawable._2131431206_res_0x7f0b0f26;
                }
            } else {
                string = CustomMapStyleAdapter.this.c.getResources().getString(R.string._2130839876_res_0x7f020944);
                i = R.drawable._2131431204_res_0x7f0b0f24;
            }
            b(hakVar, aVar, i);
            LogUtil.a("Track_CustomMapStyleAdapter", " defaultValue title =  ", string);
            this.f3691a.setText(string);
            this.f3691a.setVisibility(0);
        }

        private void b(hak hakVar, a aVar, int i) {
            if (i != -1) {
                nrf.cHt_(CustomMapStyleAdapter.this.c, aVar.e);
                nrf.cIo_(i, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(CustomMapStyleAdapter.this.c.getApplicationContext(), (int) CustomMapStyleAdapter.this.c.getResources().getDimension(R.dimen._2131362600_res_0x7f0a0328))), aVar.e);
            }
            if (hakVar.n()) {
                aVar.b.setVisibility(0);
                nrf.cIK_(hakVar.a(), aVar.b, 0.0f, nrf.d, 0.0f, 0.0f);
            } else {
                aVar.b.setVisibility(8);
            }
        }
    }
}
