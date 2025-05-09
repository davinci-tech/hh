package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.koq;
import defpackage.rsb;
import defpackage.rsg;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class DoubleGroupDataAdapter extends RecyclerView.Adapter {
    private List<b> b;
    private int c;
    private int d;
    private OnItemClickListener e;
    private OnItemLongClickListener g;
    private int i;

    /* renamed from: a, reason: collision with root package name */
    private boolean f10417a = true;
    private int j = 1;

    public interface ContentEntityListCallback {
        void response(List<d> list);
    }

    public interface OnItemClickListener {
        void onItemClick(h hVar);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(h hVar);
    }

    public int a() {
        return R.layout.privacy_list_father_item;
    }

    public int c() {
        return R.layout.privacy_list_father_item;
    }

    public int d() {
        return R.layout.privacy_list_child_item;
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    public void b(OnItemLongClickListener onItemLongClickListener) {
        this.g = onItemLongClickListener;
    }

    public void e(boolean z) {
        this.f10417a = z;
        notifyDataSetChanged();
    }

    public void c(int i) {
        this.j = i;
        if (i == 1) {
            c(false);
        } else {
            notifyDataSetChanged();
        }
    }

    public int b() {
        return this.d;
    }

    public int j() {
        return this.i;
    }

    public void c(final boolean z) {
        if (z) {
            this.d = this.i;
        } else {
            this.d = 0;
        }
        c(new ContentEntityListCallback() { // from class: rry
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter.ContentEntityListCallback
            public final void response(List list) {
                DoubleGroupDataAdapter.e(z, list);
            }
        });
        notifyDataSetChanged();
    }

    public static /* synthetic */ void e(boolean z, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((d) it.next()).d = z;
        }
    }

    public PrivacyDataModel d(h hVar) {
        d a2 = a(hVar);
        if (a2 == null) {
            return null;
        }
        return a2.c;
    }

    public List<PrivacyDataModel> e() {
        final ArrayList arrayList = new ArrayList(10);
        c(new ContentEntityListCallback() { // from class: rrw
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter.ContentEntityListCallback
            public final void response(List list) {
                DoubleGroupDataAdapter.b(arrayList, list);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void b(List list, List list2) {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            if (dVar.d) {
                list.add(dVar.c);
            }
        }
    }

    public void d(List<rsb> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.i = 0;
        this.d = 0;
        this.b = new ArrayList(10);
        for (int i = 0; i < list.size(); i++) {
            rsb rsbVar = list.get(i);
            b bVar = new b();
            bVar.e = rsbVar.a();
            bVar.c = true;
            this.b.add(bVar);
            List<rsg> e2 = rsbVar.e();
            if (e2 != null && e2.size() != 0) {
                ArrayList arrayList = new ArrayList(10);
                for (int i2 = 0; i2 < e2.size(); i2++) {
                    rsg rsgVar = e2.get(i2);
                    a aVar = new a();
                    aVar.b = rsgVar.e();
                    aVar.f10418a = true;
                    arrayList.add(aVar);
                    List<PrivacyDataModel> d2 = rsgVar.d();
                    if (d2 != null && d2.size() != 0) {
                        ArrayList arrayList2 = new ArrayList(10);
                        for (PrivacyDataModel privacyDataModel : d2) {
                            this.i++;
                            d dVar = new d();
                            dVar.c = privacyDataModel;
                            arrayList2.add(dVar);
                        }
                        aVar.c = arrayList2;
                    }
                }
                bVar.b = arrayList;
            }
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        h e2 = e(i);
        if (e2 == null) {
            return 0;
        }
        return e2.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new g(LayoutInflater.from(viewGroup.getContext()).inflate(c(), viewGroup, false));
        }
        if (i == 2) {
            return new c(LayoutInflater.from(viewGroup.getContext()).inflate(a(), viewGroup, false));
        }
        return dQo_(LayoutInflater.from(viewGroup.getContext()).inflate(d(), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            if (viewHolder instanceof g) {
                a((g) viewHolder, i);
            }
        } else if (itemViewType == 2) {
            if (viewHolder instanceof c) {
                b((c) viewHolder, i);
            }
        } else if (viewHolder instanceof e) {
            d((e) viewHolder, i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.b)) {
            return 0;
        }
        this.c = 0;
        for (int i = 0; i < this.b.size(); i++) {
            this.c++;
            b bVar = this.b.get(i);
            List list = bVar.b;
            if (bVar.c && !koq.b(list)) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    this.c++;
                    a aVar = (a) list.get(i2);
                    List list2 = aVar.c;
                    if (aVar.f10418a && !koq.b(list2)) {
                        for (int i3 = 0; i3 < list2.size(); i3++) {
                            this.c++;
                        }
                    }
                }
            }
        }
        return this.c;
    }

    public e dQo_(View view) {
        return new e(view);
    }

    private void c(ContentEntityListCallback contentEntityListCallback) {
        if (koq.b(this.b)) {
            return;
        }
        Iterator<b> it = this.b.iterator();
        while (it.hasNext()) {
            List list = it.next().b;
            if (!koq.b(list)) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    List<d> list2 = ((a) it2.next()).c;
                    if (!koq.b(list2)) {
                        contentEntityListCallback.response(list2);
                    }
                }
            }
        }
    }

    private void dQn_(View view, boolean z) {
        if (view.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (z) {
                layoutParams.setMargins(0, 0, 0, dimensionPixelSize);
            } else {
                layoutParams.setMargins(0, 0, 0, 0);
            }
            view.setLayoutParams(layoutParams);
        }
    }

    private void a(g gVar, final int i) {
        b b2 = b(e(i));
        if (b2 == null) {
            return;
        }
        if (b2.c) {
            gVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
        } else {
            gVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
        }
        gVar.a(b2.e);
        if (b2.e.contains(Constants.LINK)) {
            gVar.d.setVisibility(8);
        } else {
            gVar.d.setVisibility(0);
            gVar.f10420a.setOnClickListener(new View.OnClickListener() { // from class: rrx
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DoubleGroupDataAdapter.this.dQs_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void dQs_(int i, View view) {
        b(e(i)).c = !r2.c;
        notifyDataSetChanged();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(c cVar, final int i) {
        a c2 = c(e(i));
        if (c2 == null) {
            return;
        }
        if (c2.f10418a) {
            cVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
            cVar.f10419a.setBackgroundResource(R.drawable._2131431108_res_0x7f0b0ec4);
        } else {
            cVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
            cVar.f10419a.setBackgroundResource(R.drawable._2131431106_res_0x7f0b0ec2);
        }
        dQn_(cVar.f10419a, !c2.f10418a);
        cVar.e(c2.b);
        cVar.f10419a.setOnClickListener(new View.OnClickListener() { // from class: rrv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DoubleGroupDataAdapter.this.dQp_(i, view);
            }
        });
    }

    public /* synthetic */ void dQp_(int i, View view) {
        c(e(i)).f10418a = !r2.f10418a;
        notifyDataSetChanged();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(e eVar, int i) {
        final h e2 = e(i);
        final d a2 = a(e2);
        if (a2 == null) {
            return;
        }
        a(eVar, a2.d, e2.e);
        eVar.e(a2.c);
        eVar.j.setOnClickListener(new View.OnClickListener() { // from class: rsa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DoubleGroupDataAdapter.this.dQq_(a2, e2, view);
            }
        });
        eVar.j.setOnLongClickListener(new View.OnLongClickListener() { // from class: rse
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return DoubleGroupDataAdapter.this.dQr_(a2, e2, view);
            }
        });
    }

    public /* synthetic */ void dQq_(d dVar, h hVar, View view) {
        if (this.j == 2) {
            c(dVar);
        }
        OnItemClickListener onItemClickListener = this.e;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(hVar);
        }
        notifyDataSetChanged();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ boolean dQr_(d dVar, h hVar, View view) {
        if (this.j == 2) {
            c(dVar);
        } else {
            dVar.d = true;
            this.d++;
        }
        OnItemLongClickListener onItemLongClickListener = this.g;
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(hVar);
        }
        notifyDataSetChanged();
        return true;
    }

    private void c(d dVar) {
        boolean z = dVar.d;
        dVar.d = !z;
        if (z) {
            this.d--;
        } else {
            this.d++;
        }
    }

    private void a(e eVar, boolean z, boolean z2) {
        Context context = eVar.j.getContext();
        if (LanguageUtil.bc(context)) {
            eVar.e.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        if (z2) {
            eVar.j.setBackgroundResource(R.drawable._2131431107_res_0x7f0b0ec3);
            eVar.f.setVisibility(8);
        } else {
            eVar.j.setBackgroundColor(ContextCompat.getColor(context, R.color._2131296665_res_0x7f090199));
            eVar.f.setVisibility(0);
        }
        if (this.j == 2) {
            eVar.e.setVisibility(8);
            eVar.i.setVisibility(0);
            eVar.i.setChecked(z);
        } else {
            eVar.i.setVisibility(8);
            if (this.f10417a) {
                eVar.e.setVisibility(0);
            } else {
                eVar.e.setVisibility(8);
            }
        }
        dQn_(eVar.j, z2);
    }

    private b b(h hVar) {
        if (hVar != null && !koq.b(this.b)) {
            for (int i = 0; i < this.b.size(); i++) {
                if (i == hVar.f10421a) {
                    return this.b.get(i);
                }
            }
        }
        return null;
    }

    private a c(h hVar) {
        a aVar = null;
        if (hVar != null && !koq.b(this.b)) {
            for (int i = 0; i < this.b.size(); i++) {
                if (i == hVar.f10421a) {
                    List list = this.b.get(i).b;
                    if (!koq.b(list)) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= list.size()) {
                                break;
                            }
                            if (i2 == hVar.d) {
                                aVar = (a) list.get(i2);
                                break;
                            }
                            i2++;
                        }
                    }
                }
            }
        }
        return aVar;
    }

    private d a(h hVar) {
        d dVar = null;
        if (hVar != null && !koq.b(this.b)) {
            for (int i = 0; i < this.b.size(); i++) {
                if (i == hVar.f10421a) {
                    List list = this.b.get(i).b;
                    if (koq.b(list)) {
                        continue;
                    } else {
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            if (i2 == hVar.d && (dVar = e((a) list.get(i2), hVar)) != null) {
                                return dVar;
                            }
                        }
                    }
                }
            }
        }
        return dVar;
    }

    private d e(a aVar, h hVar) {
        if (aVar != null && hVar != null) {
            List list = aVar.c;
            if (koq.b(list)) {
                return null;
            }
            for (int i = 0; i < list.size(); i++) {
                if (i == hVar.c) {
                    return (d) list.get(i);
                }
            }
        }
        return null;
    }

    private h e(int i) {
        h hVar = null;
        if (koq.b(this.b)) {
            return null;
        }
        this.c = -1;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            int i3 = this.c + 1;
            this.c = i3;
            if (i == i3) {
                return new h(1, i2, 0, 0, false);
            }
            b bVar = this.b.get(i2);
            List<a> list = bVar.b;
            if (bVar.c && !koq.b(list)) {
                h c2 = c(i, i2, list);
                if (c2 != null) {
                    return c2;
                }
                hVar = c2;
            }
        }
        return hVar;
    }

    private h c(int i, int i2, List<a> list) {
        if (koq.b(list)) {
            return null;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            int i4 = this.c + 1;
            this.c = i4;
            if (i == i4) {
                return new h(2, i2, i3, 0, false);
            }
            a aVar = list.get(i3);
            List list2 = aVar.c;
            if (aVar.f10418a && !koq.b(list2)) {
                int i5 = 0;
                while (i5 < list2.size()) {
                    int i6 = this.c + 1;
                    this.c = i6;
                    if (i == i6) {
                        return new h(3, i2, i3, i5, i5 == list2.size() - 1);
                    }
                    i5++;
                }
            }
        }
        return null;
    }

    public static class h {

        /* renamed from: a, reason: collision with root package name */
        private int f10421a;
        private int b;
        private int c;
        private int d;
        private boolean e;

        h(int i, int i2, int i3, int i4, boolean z) {
            this.b = i;
            this.f10421a = i2;
            this.d = i3;
            this.c = i4;
            this.e = z;
        }
    }

    static class b {
        private List<a> b;
        private boolean c;
        private String e;

        private b() {
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f10418a;
        private String b;
        private List<d> c;

        private a() {
        }
    }

    public static class d {
        private PrivacyDataModel c;
        private boolean d;

        private d() {
        }
    }

    class g extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        View f10420a;
        HealthTextView c;
        ImageView d;

        g(View view) {
            super(view);
            this.f10420a = view.findViewById(R.id.privacy_title_layout);
            this.c = (HealthTextView) view.findViewById(R.id.privacy_title);
            this.d = (ImageView) view.findViewById(R.id.content_item_arrow);
        }

        void a(String str) {
            this.c.setText(str);
        }
    }

    class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        View f10419a;
        ImageView d;
        HealthTextView e;

        c(View view) {
            super(view);
            this.f10419a = view.findViewById(R.id.privacy_title_layout);
            this.e = (HealthTextView) view.findViewById(R.id.privacy_title);
            this.d = (ImageView) view.findViewById(R.id.content_item_arrow);
        }

        void e(String str) {
            this.e.setText(str);
        }
    }

    class e extends RecyclerView.ViewHolder {
        ImageView e;
        View f;
        HealthTextView g;
        HealthCheckBox i;
        View j;
        HealthTextView k;

        e(View view) {
            super(view);
            this.j = view.findViewById(R.id.privacy_item_rl);
            this.k = (HealthTextView) view.findViewById(R.id.privacy_content_title);
            this.g = (HealthTextView) view.findViewById(R.id.privacy_time);
            this.e = (ImageView) view.findViewById(R.id.content_item_arrow);
            HealthCheckBox healthCheckBox = (HealthCheckBox) view.findViewById(R.id.content_item_check);
            this.i = healthCheckBox;
            healthCheckBox.setClickable(false);
            this.f = view.findViewById(R.id.data_line);
        }

        void e(PrivacyDataModel privacyDataModel) {
            this.k.setText(privacyDataModel.getDataTitle());
            this.g.setText(privacyDataModel.getDataDesc());
        }
    }
}
