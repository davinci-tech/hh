package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter;
import com.huawei.ui.commonui.tablewidget.BaseViewHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class hgf extends BaseHealthTableAdapter<BaseViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final LayoutInflater f13157a;
    private boolean b;
    private Context c;
    private String d;
    private final float[] e;
    private float f;
    private Set<Integer> g;
    private final nre<hjj, hjc, hjc, hjc, hjc, hjc> h;

    public hgf(Context context, nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, boolean z) {
        this(context, nreVar, z, 0.0f);
    }

    public hgf(Context context, nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, boolean z, float f) {
        this.e = new float[]{59.75f, 53.75f, 47.5f, 50.75f, 52.0f, 49.0f};
        this.g = new HashSet();
        this.f13157a = LayoutInflater.from(context);
        this.h = nreVar;
        this.c = context;
        this.d = context.getString(R$string.IDS_motiontrack_show_invalid_data);
        this.b = z;
        this.f = f;
    }

    @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getRowCount() {
        return this.h.getRowsCount();
    }

    @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getColumnCount() {
        return this.h.getColumnsCount();
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: bdA_, reason: merged with bridge method [inline-methods] */
    public BaseViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        return new c(this.f13157a.inflate(R.layout.item_content, viewGroup, false));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: bdz_, reason: merged with bridge method [inline-methods] */
    public BaseViewHolder onCreateColumnHeaderViewHolder(ViewGroup viewGroup) {
        return new b(this.f13157a.inflate(R.layout.item_header_column, viewGroup, false));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: bdC_, reason: merged with bridge method [inline-methods] */
    public BaseViewHolder onCreateRowHeaderViewHolder(ViewGroup viewGroup) {
        return new c(this.f13157a.inflate(R.layout.item_header_row, viewGroup, false));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: bdB_, reason: merged with bridge method [inline-methods] */
    public BaseViewHolder onCreateRowColumnHeaderViewHolder(ViewGroup viewGroup) {
        return new b(this.f13157a.inflate(R.layout.item_header_rowcolumn, viewGroup, false));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: bdD_, reason: merged with bridge method [inline-methods] */
    public BaseViewHolder onCreateStatisticHeaderViewHolder(ViewGroup viewGroup) {
        return new c(this.f13157a.inflate(R.layout.item_statistic_header, viewGroup, false));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: bdE_, reason: merged with bridge method [inline-methods] */
    public BaseViewHolder onCreateStatisticViewHolder(ViewGroup viewGroup) {
        return new c(this.f13157a.inflate(R.layout.item_statistic, viewGroup, false));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindContentViewHolder(BaseViewHolder baseViewHolder, int i, int i2) {
        if (baseViewHolder instanceof c) {
            c cVar = (c) baseViewHolder;
            if (i2 == 0) {
                cVar.b(a());
            }
            hjc contentData = this.h.getContentData(i, i2);
            String str = this.d;
            if (contentData != null) {
                str = contentData.getValue();
            }
            cVar.b(str, this.b);
            if (this.g.contains(Integer.valueOf(i))) {
                bdy_(cVar.getItemView(), i2);
            } else {
                bdx_(cVar.getItemView(), i2);
            }
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindColumnHeaderViewHolder(BaseViewHolder baseViewHolder, int i, int i2) {
        if (baseViewHolder instanceof b) {
            b bVar = (b) baseViewHolder;
            hjj columnHeaderData = this.h.getColumnHeaderData(i, i2);
            if (i2 != 0) {
                if (i2 == getColumnCount() - 1) {
                    bVar.c(0);
                }
            } else {
                bVar.e(a());
            }
            String str = this.d;
            if (columnHeaderData != null) {
                str = columnHeaderData.getValue();
                bVar.e(bVar.c, columnHeaderData.d(), this.b);
            }
            bVar.e(bVar.e, str, this.b);
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindRowHeaderViewHolder(BaseViewHolder baseViewHolder, int i, int i2) {
        if (baseViewHolder instanceof c) {
            c cVar = (c) baseViewHolder;
            hjc rowHeaderData = this.h.getRowHeaderData(i, i2);
            String str = this.d;
            if (rowHeaderData != null && !TextUtils.isEmpty(rowHeaderData.getValue())) {
                str = rowHeaderData.getValue();
            }
            if (i2 == 0) {
                cVar.b(a());
            }
            cVar.b(str, this.b);
            if (this.g.contains(Integer.valueOf(i))) {
                bdy_(cVar.getItemView(), i2);
            } else {
                bdx_(cVar.getItemView(), i2);
            }
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindRowColumnHeaderViewHolder(BaseViewHolder baseViewHolder, int i, int i2) {
        if (baseViewHolder instanceof b) {
            b bVar = (b) baseViewHolder;
            hjc rowColumnHeaderData = this.h.getRowColumnHeaderData(i, i2);
            if (i2 == 0) {
                bVar.e(a());
            }
            if (rowColumnHeaderData != null) {
                bVar.e(bVar.e, rowColumnHeaderData.getValue(), this.b);
            }
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindStatisticHeaderViewHolder(BaseViewHolder baseViewHolder, int i, int i2) {
        if (baseViewHolder instanceof c) {
            c cVar = (c) baseViewHolder;
            hjc statisticHeaderData = this.h.getStatisticHeaderData(i, i2);
            String str = this.d;
            if (statisticHeaderData != null && !TextUtils.isEmpty(statisticHeaderData.getValue())) {
                str = statisticHeaderData.getValue();
            }
            if (i2 == 0) {
                cVar.b(a());
            }
            cVar.b(str, this.b);
            if (getStatisticNum() <= 1) {
                bdx_(cVar.getItemView(), i2);
            } else if (i == getRowCount() - 1) {
                bdw_(cVar.getItemView(), i2);
                cVar.e(this.c.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
            } else {
                cVar.getItemView().setBackgroundColor(0);
                cVar.e(this.c.getResources().getColor(R.color._2131296651_res_0x7f09018b));
            }
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void onBindStatisticViewHolder(BaseViewHolder baseViewHolder, int i, int i2) {
        if (baseViewHolder instanceof c) {
            c cVar = (c) baseViewHolder;
            hjc statisticData = this.h.getStatisticData(i, i2);
            String str = this.d;
            if (statisticData != null && !TextUtils.isEmpty(statisticData.getValue())) {
                str = statisticData.getValue();
            }
            if (i2 == 0) {
                cVar.b(a());
            }
            cVar.b(str, this.b);
            if (getStatisticNum() <= 1) {
                bdx_(cVar.getItemView(), i2);
                return;
            }
            if (i == getRowCount() - 1) {
                if (koq.b(this.g)) {
                    cVar.b((String) null, this.b);
                }
                bdw_(cVar.getItemView(), i2);
                cVar.e(this.c.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
                return;
            }
            cVar.getItemView().setBackgroundColor(0);
            cVar.e(this.c.getResources().getColor(R.color._2131296651_res_0x7f09018b));
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getColumnWidth(int i) {
        Context context = this.c;
        float[] fArr = this.e;
        return nsn.c(context, fArr[i % fArr.length]);
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getRowHeight(int i) {
        if (i != 0) {
            return nsn.c(this.c, this.b ? 25.0f : 20.0f);
        }
        Context context = this.c;
        float f = this.f;
        if (f == 0.0f) {
            f = this.b ? 46.5f : 34.5f;
        }
        return nsn.c(context, f);
    }

    @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getRowHeaderNum() {
        return this.h.d();
    }

    @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getStatisticNum() {
        return this.h.e();
    }

    @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getColumnHeaderNum() {
        return this.h.c();
    }

    public void a(int i) {
        this.g.add(Integer.valueOf(i));
    }

    public int a() {
        return nsn.c(this.c, 8.0f);
    }

    public void c(int i) {
        this.g.remove(Integer.valueOf(i));
    }

    private void bdx_(View view, int i) {
        if (e(i)) {
            view.setBackgroundResource(R.drawable._2131431733_res_0x7f0b1135);
        } else if (b(i)) {
            view.setBackgroundResource(R.drawable._2131431734_res_0x7f0b1136);
        } else {
            view.setBackgroundResource(R.drawable._2131431735_res_0x7f0b1137);
        }
    }

    private void bdy_(View view, int i) {
        if (e(i)) {
            view.setBackgroundResource(R.drawable._2131431736_res_0x7f0b1138);
        } else if (b(i)) {
            view.setBackgroundResource(R.drawable._2131431737_res_0x7f0b1139);
        } else {
            view.setBackgroundResource(R.drawable._2131431738_res_0x7f0b113a);
        }
    }

    private void bdw_(View view, int i) {
        if (e(i)) {
            view.setBackgroundResource(R.drawable._2131431730_res_0x7f0b1132);
        } else if (b(i)) {
            view.setBackgroundResource(R.drawable._2131431731_res_0x7f0b1133);
        } else {
            view.setBackgroundResource(R.drawable._2131431732_res_0x7f0b1134);
        }
    }

    private boolean e(int i) {
        if (isRtl()) {
            if (i == getColumnCount() - 1) {
                return true;
            }
        } else if (i == 0) {
            return true;
        }
        return false;
    }

    private boolean b(int i) {
        if (isRtl()) {
            if (i == 0) {
                return true;
            }
        } else if (i == getColumnCount() - 1) {
            return true;
        }
        return false;
    }

    static class c extends BaseViewHolder {
        HealthTextView b;

        private c(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.tvText);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i) {
            View itemView = getItemView();
            if (itemView != null) {
                getItemView().setPaddingRelative(i, itemView.getPaddingTop(), itemView.getPaddingEnd(), itemView.getPaddingBottom());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(String str, boolean z) {
            HealthTextView healthTextView = this.b;
            if (healthTextView != null) {
                if (z) {
                    healthTextView.setConnectionSymbol(false);
                    this.b.setSplittable(true);
                    this.b.setMaxLines(3);
                } else {
                    healthTextView.setMaxLines(1);
                    this.b.setEllipsize(TextUtils.TruncateAt.END);
                }
                this.b.setText(str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(int i) {
            HealthTextView healthTextView = this.b;
            if (healthTextView != null) {
                healthTextView.setTextColor(i);
            }
        }
    }

    static class b extends BaseViewHolder {
        HealthTextView c;
        HealthTextView e;

        private b(View view) {
            super(view);
            this.e = (HealthTextView) view.findViewById(R.id.tvTitle);
            this.c = (HealthTextView) view.findViewById(R.id.tvUnit);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(int i) {
            View itemView = getItemView();
            if (itemView != null) {
                getItemView().setPaddingRelative(i, itemView.getPaddingTop(), itemView.getPaddingEnd(), itemView.getPaddingBottom());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(int i) {
            View itemView = getItemView();
            if (itemView != null) {
                getItemView().setPaddingRelative(itemView.getPaddingStart(), itemView.getPaddingTop(), i, itemView.getPaddingBottom());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(HealthTextView healthTextView, String str, boolean z) {
            if (healthTextView != null) {
                if (z) {
                    healthTextView.setConnectionSymbol(false);
                    healthTextView.setSplittable(false);
                    healthTextView.setMaxLines(3);
                } else {
                    healthTextView.setMaxLines(1);
                    healthTextView.setEllipsize(TextUtils.TruncateAt.END);
                }
                healthTextView.setText(str);
            }
        }
    }
}
