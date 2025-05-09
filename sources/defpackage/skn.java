package defpackage;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.uikit.hwalphaindexerlistview.widget.HwAlphaIndexerListView;
import com.huawei.uikit.hwalphaindexerlistview.widget.HwSortedTextListAdapter;

/* loaded from: classes9.dex */
public class skn {

    /* renamed from: a, reason: collision with root package name */
    private HwSortedTextListAdapter f17090a;
    private HwAlphaIndexerListView b;
    private ListView c;
    private boolean d;
    private boolean e;
    private int h = 0;
    private AbsListView.OnScrollListener j = new a();
    private HwAlphaIndexerListView.OnItemClickListener f = new d();

    class a implements AbsListView.OnScrollListener {
        a() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            View childAt;
            if (skn.this.f17090a == null) {
                return;
            }
            int i4 = i2 + i;
            if (i4 < i3 || skn.this.c.getSelectedItemPosition() == i) {
                skn.this.d = false;
            }
            if (i4 == i3 && (childAt = skn.this.c.getChildAt(skn.this.c.getChildCount() - 1)) != null && childAt.getBottom() != skn.this.c.getHeight()) {
                skn.this.d = false;
            }
            if (!skn.this.d) {
                skn.this.b.invalidate();
                skn.this.b.setOverLayInfo(skn.this.e(skn.this.f17090a.getSectionForPosition(i)));
            }
            if (!skn.this.e || Math.abs(i - skn.this.h) <= 2) {
                return;
            }
            skn.this.b.c();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            skn.this.d = false;
            if (i == 0) {
                skn.this.e = false;
                skn.this.b.d();
            } else {
                if (i != 2) {
                    return;
                }
                skn.this.e = true;
                skn sknVar = skn.this;
                sknVar.h = sknVar.c.getFirstVisiblePosition();
            }
        }
    }

    class d implements HwAlphaIndexerListView.OnItemClickListener {
        d() {
        }

        @Override // com.huawei.uikit.hwalphaindexerlistview.widget.HwAlphaIndexerListView.OnItemClickListener
        public void onItemClick(String str, int i) {
            String str2;
            if (str == null || skn.this.f17090a == null) {
                return;
            }
            skn.this.d = false;
            Object[] sections = skn.this.f17090a.getSections();
            if (sections instanceof String[]) {
                String[] strArr = (String[]) sections;
                int i2 = 0;
                while (true) {
                    if (i2 >= strArr.length) {
                        str2 = null;
                        i2 = i;
                        break;
                    } else {
                        if (skn.this.b.b(str, strArr[i2], i)) {
                            str2 = strArr[i2];
                            break;
                        }
                        i2++;
                    }
                }
                if (str2 != null) {
                    skn.this.d(str2, i2, i);
                    return;
                }
                if (!skn.this.b.c(i)) {
                    skn.this.b.d(str);
                } else if (skn.this.b.b()) {
                    skn.this.c.setSelection(skn.this.c.getCount() - 1);
                } else {
                    skn.this.c.setSelection(0);
                }
            }
        }
    }

    public skn(ListView listView, HwAlphaIndexerListView hwAlphaIndexerListView) {
        this.c = listView;
        this.b = hwAlphaIndexerListView;
        hwAlphaIndexerListView.setListViewAttachTo(listView);
        ListAdapter adapter = listView.getAdapter();
        if (adapter instanceof HwSortedTextListAdapter) {
            this.f17090a = (HwSortedTextListAdapter) adapter;
            this.b.setOverLayInfo(e(this.f17090a.getSectionForPosition(this.c.getFirstVisiblePosition())));
        }
    }

    public void d() {
        ListView listView = this.c;
        if (listView == null || this.b == null) {
            return;
        }
        listView.setOnScrollListener(this.j);
        this.b.setOnItemClickListener(this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(int i) {
        if (this.f17090a.getSections().length > i && i >= 0) {
            Object obj = this.f17090a.getSections()[i];
            if (obj instanceof String) {
                return (String) obj;
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, int i, int i2) {
        int positionForSection = this.f17090a.getPositionForSection(i);
        int lastVisiblePosition = (this.c.getLastVisiblePosition() - this.c.getFirstVisiblePosition()) + 1;
        if (positionForSection != -1 && positionForSection + lastVisiblePosition <= this.c.getCount()) {
            this.c.setSelection(positionForSection);
        }
        if (positionForSection + lastVisiblePosition > this.c.getCount()) {
            this.d = true;
            ListView listView = this.c;
            listView.setSelection(listView.getCount() - 1);
        }
        this.b.setOverLayInfo(i2, str);
        this.b.d(str);
    }
}
