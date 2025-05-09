package com.huawei.uikit.hwrecyclerview.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView;
import com.huawei.uikit.hwunifiedinteract.widget.HwCompoundEventDetector;
import defpackage.slc;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
class bqmxo {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10733a;
    private ActionMode d;
    private boolean e;
    private int h;
    private HwRecyclerView i;
    private HwRecyclerView.MultiChoiceModeListener k;
    private e l;
    private SparseBooleanArray m;
    private LongSparseArray<Integer> n;
    private c o;
    private HwCompoundEventDetector.OnMultiSelectListener t;
    private boolean b = true;
    private boolean c = true;
    private int g = -1;
    private int j = -1;
    private int f = 0;

    class a implements HwCompoundEventDetector.OnMultiSelectListener {
        a() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwCompoundEventDetector.OnMultiSelectListener
        public boolean onCancel(MotionEvent motionEvent) {
            return false;
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwCompoundEventDetector.OnMultiSelectListener
        public boolean onSelectContinuous(boolean z, MotionEvent motionEvent) {
            return bqmxo.this.egu_(motionEvent);
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwCompoundEventDetector.OnMultiSelectListener
        public boolean onSelectDiscrete(MotionEvent motionEvent) {
            return bqmxo.this.egw_(motionEvent);
        }
    }

    static class akxao extends View.BaseSavedState {
        public static final Parcelable.Creator<akxao> CREATOR = new c();

        /* renamed from: a, reason: collision with root package name */
        private boolean f10734a;
        private int b;
        private SparseBooleanArray c;
        private LongSparseArray<Integer> d;
        private int e;
        private int f;

        class c implements Parcelable.Creator<akxao> {
            c() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: egB_, reason: merged with bridge method [inline-methods] */
            public akxao createFromParcel(Parcel parcel) {
                return new akxao(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public akxao[] newArray(int i) {
                return new akxao[i];
            }
        }

        /* synthetic */ akxao(Parcel parcel, a aVar) {
            this(parcel);
        }

        public String toString() {
            return "HwRecyclerView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " mCheckState=" + this.c + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel == null) {
                return;
            }
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.f10734a ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.b);
            parcel.writeSparseBooleanArray(this.c);
            LongSparseArray<Integer> longSparseArray = this.d;
            int size = longSparseArray != null ? longSparseArray.size() : 0;
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeLong(this.d.keyAt(i2));
                parcel.writeInt(this.d.valueAt(i2).intValue());
            }
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
        }

        akxao(Parcelable parcelable) {
            super(parcelable);
        }

        private akxao(Parcel parcel) {
            super(parcel);
            this.f10734a = parcel.readByte() != 0;
            this.b = parcel.readInt();
            this.c = parcel.readSparseBooleanArray();
            int readInt = parcel.readInt();
            if (readInt > 0 && readInt <= parcel.dataAvail() + 4) {
                this.d = new LongSparseArray<>();
                for (int i = 0; i < readInt; i++) {
                    this.d.put(parcel.readLong(), Integer.valueOf(parcel.readInt()));
                }
            }
            this.e = parcel.readInt();
            this.f = parcel.readInt();
        }
    }

    class c implements HwRecyclerView.MultiChoiceModeListener {
        private HwRecyclerView.MultiChoiceModeListener b;

        c() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            HwRecyclerView.MultiChoiceModeListener multiChoiceModeListener = this.b;
            if (multiChoiceModeListener == null) {
                return false;
            }
            return multiChoiceModeListener.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            HwRecyclerView.MultiChoiceModeListener multiChoiceModeListener = this.b;
            if (multiChoiceModeListener == null || !multiChoiceModeListener.onCreateActionMode(actionMode, menu)) {
                return false;
            }
            bqmxo.this.i.setDetectoredLongpressEnabled(false);
            RecyclerView.Adapter adapter = bqmxo.this.i.getAdapter();
            if (adapter == null || !adapter.hasStableIds()) {
                return true;
            }
            bqmxo.this.l = new e(bqmxo.this, null);
            adapter.registerAdapterDataObserver(bqmxo.this.l);
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            HwRecyclerView.MultiChoiceModeListener multiChoiceModeListener = this.b;
            if (multiChoiceModeListener != null) {
                multiChoiceModeListener.onDestroyActionMode(actionMode);
            }
            bqmxo.this.d = null;
            bqmxo.this.i.b();
            bqmxo.this.i.requestLayout();
            RecyclerView.Adapter adapter = bqmxo.this.i.getAdapter();
            if (adapter != null && bqmxo.this.l != null) {
                adapter.unregisterAdapterDataObserver(bqmxo.this.l);
                bqmxo.this.l = null;
            }
            bqmxo.this.i.setDetectoredLongpressEnabled(true);
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView.MultiChoiceModeListener
        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
            HwRecyclerView.MultiChoiceModeListener multiChoiceModeListener = this.b;
            if (multiChoiceModeListener == null || actionMode == null) {
                return;
            }
            multiChoiceModeListener.onItemCheckedStateChanged(actionMode, i, j, z);
            if (bqmxo.this.a() == 0) {
                actionMode.finish();
                bqmxo.this.o();
            }
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            HwRecyclerView.MultiChoiceModeListener multiChoiceModeListener = this.b;
            if (multiChoiceModeListener == null) {
                return false;
            }
            return multiChoiceModeListener.onPrepareActionMode(actionMode, menu);
        }

        void e(HwRecyclerView.MultiChoiceModeListener multiChoiceModeListener) {
            this.b = multiChoiceModeListener;
        }
    }

    class e extends RecyclerView.AdapterDataObserver {
        private e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            bqmxo.this.m();
            super.onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            bqmxo.this.m();
            super.onItemRangeChanged(i, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            bqmxo.this.m();
            super.onItemRangeInserted(i, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            bqmxo.this.m();
            super.onItemRangeMoved(i, i2, i3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            bqmxo.this.m();
            super.onItemRangeRemoved(i, i2);
        }

        /* synthetic */ e(bqmxo bqmxoVar, a aVar) {
            this();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            bqmxo.this.m();
            super.onItemRangeChanged(i, i2, obj);
        }
    }

    bqmxo(HwRecyclerView hwRecyclerView) {
        this.i = hwRecyclerView;
        b(hwRecyclerView.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        RecyclerView.Adapter adapter = this.i.getAdapter();
        if (this.f == 0 || adapter == null || !adapter.hasStableIds()) {
            return;
        }
        k();
    }

    int a() {
        return this.h;
    }

    protected HwCompoundEventDetector.OnMultiSelectListener c() {
        return new a();
    }

    long[] d() {
        LongSparseArray<Integer> longSparseArray;
        if (this.f == 0 || (longSparseArray = this.n) == null) {
            return new long[0];
        }
        int size = longSparseArray.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = this.n.keyAt(i);
        }
        return jArr;
    }

    SparseBooleanArray egA_() {
        if (this.f == 3) {
            return this.m;
        }
        return null;
    }

    HwCompoundEventDetector.OnMultiSelectListener f() {
        if (this.t == null) {
            this.t = c();
        }
        return this.t;
    }

    int g() {
        return this.f;
    }

    ActionMode i() {
        return this.d;
    }

    HwRecyclerView.MultiChoiceModeListener j() {
        return this.k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean n() {
        if (this.f != 3 || this.m == null) {
            return false;
        }
        int childCount = this.i.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.i.getChildAt(i);
            if (childAt != 0) {
                boolean z = this.m.get(this.i.getChildAdapterPosition(childAt));
                if (childAt instanceof Checkable) {
                    Checkable checkable = (Checkable) childAt;
                    if (checkable.isChecked() != z) {
                        checkable.setChecked(z);
                        childAt.jumpDrawablesToCurrentState();
                    }
                } else {
                    childAt.setActivated(z);
                    childAt.jumpDrawablesToCurrentState();
                }
            }
        }
        return true;
    }

    private void k() {
        ActionMode actionMode;
        this.m.clear();
        RecyclerView.Adapter adapter = this.i.getAdapter();
        if (adapter == null || this.n == null) {
            return;
        }
        int i = 0;
        boolean z = false;
        while (i < this.n.size()) {
            long keyAt = this.n.keyAt(i);
            int intValue = this.n.valueAt(i).intValue();
            if (keyAt != adapter.getItemId(intValue)) {
                int i2 = intValue - 20;
                int i3 = intValue + 20;
                int itemCount = adapter.getItemCount();
                if (!b(adapter, i, keyAt, i2 <= 0 ? 0 : i2, i3 <= itemCount ? i3 : itemCount)) {
                    this.n.delete(keyAt);
                    i--;
                    this.h--;
                    a(keyAt, intValue);
                    z = true;
                }
            } else {
                this.m.put(intValue, true);
            }
            i++;
        }
        if (!z || (actionMode = this.d) == null) {
            return;
        }
        actionMode.invalidate();
    }

    private int[] l() {
        int[] iArr = new int[2];
        int i = this.g;
        int i2 = this.j;
        if (i <= i2) {
            iArr[0] = i;
            iArr[1] = i2;
        } else {
            iArr[0] = i2;
            iArr[1] = i;
        }
        return iArr;
    }

    boolean c(MotionEvent motionEvent) {
        View findChildViewUnder;
        if (this.f != 3 || this.d != null || (findChildViewUnder = this.i.findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null || !b(this.i.getChildAdapterPosition(findChildViewUnder))) {
            return false;
        }
        this.i.setPressed(false);
        findChildViewUnder.setPressed(false);
        return true;
    }

    void b() {
        SparseBooleanArray sparseBooleanArray = this.m;
        if (sparseBooleanArray != null) {
            sparseBooleanArray.clear();
        }
        LongSparseArray<Integer> longSparseArray = this.n;
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
        this.h = 0;
    }

    private void b(Context context) {
        Method b = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b == null) {
            return;
        }
        HwRecyclerView hwRecyclerView = this.i;
        Boolean bool = Boolean.TRUE;
        Object c2 = slc.c((Object) null, b, new Object[]{context, hwRecyclerView, "consecutiveSelectEnabled", bool});
        if (c2 instanceof Boolean) {
            e(true, ((Boolean) c2).booleanValue());
        }
        Object c3 = slc.c((Object) null, b, new Object[]{context, this.i, "quickSelectEnabled", bool});
        if (c3 instanceof Boolean) {
            e(false, ((Boolean) c3).booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.g = -1;
        this.j = -1;
    }

    akxao egz_(Parcelable parcelable) {
        akxao akxaoVar = new akxao(parcelable);
        akxaoVar.f10734a = this.f == 3 && this.d != null;
        SparseBooleanArray sparseBooleanArray = this.m;
        if (sparseBooleanArray != null) {
            akxaoVar.c = sparseBooleanArray.clone();
        }
        if (this.n != null) {
            LongSparseArray longSparseArray = new LongSparseArray();
            int size = this.n.size();
            for (int i = 0; i < size; i++) {
                longSparseArray.put(this.n.keyAt(i), this.n.valueAt(i));
            }
            akxaoVar.d = longSparseArray;
        }
        akxaoVar.b = this.h;
        akxaoVar.e = this.g;
        akxaoVar.f = this.j;
        return akxaoVar;
    }

    void c(int i) {
        this.f = i;
        ActionMode actionMode = this.d;
        if (actionMode != null) {
            actionMode.finish();
            o();
            this.d = null;
        }
        if (this.f == 3) {
            RecyclerView.Adapter adapter = this.i.getAdapter();
            if (this.m == null) {
                this.m = new SparseBooleanArray();
            }
            if (this.n == null && adapter != null && adapter.hasStableIds()) {
                this.n = new LongSparseArray<>();
            }
            b();
            this.i.setDetectoredLongpressEnabled(true);
        }
    }

    void e(boolean z) {
        int[] l = l();
        int i = this.g;
        int i2 = this.j;
        for (int i3 = l[0]; i3 <= l[1]; i3++) {
            if (i3 != this.g || z) {
                this.i.setItemChecked(i3, z);
            }
        }
        this.g = i;
        this.j = i2;
    }

    private void e(int i) {
        this.g = i;
        this.j = -1;
    }

    boolean b(int i) {
        c cVar;
        if (this.f != 3) {
            return false;
        }
        if (this.d == null && (cVar = this.o) != null) {
            ActionMode startActionMode = this.i.startActionMode(cVar);
            this.d = startActionMode;
            if (startActionMode != null) {
                this.i.setItemChecked(i, true);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean egw_(MotionEvent motionEvent) {
        View findChildViewUnder;
        if (!this.b || (findChildViewUnder = this.i.findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null) {
            return false;
        }
        int childAdapterPosition = this.i.getChildAdapterPosition(findChildViewUnder);
        if (this.f != 3 || this.d != null || childAdapterPosition == -1) {
            return false;
        }
        this.i.setItemChecked(childAdapterPosition, true);
        this.f10733a = true;
        return true;
    }

    void c(int i, boolean z) {
        RecyclerView.Adapter adapter = this.i.getAdapter();
        if (adapter == null || this.f != 3) {
            return;
        }
        if (z && this.d == null) {
            c cVar = this.o;
            if (cVar != null && cVar.b != null) {
                this.d = this.i.startActionMode(this.o);
            } else {
                Log.e("HwMultipleChoiceModeHelper", "The multi-select mode condition is not established");
                return;
            }
        }
        if (this.f == 3) {
            e(i);
            boolean z2 = this.m.get(i);
            this.m.put(i, z);
            c(z, adapter, i);
            boolean z3 = z2 != z;
            if (z3) {
                if (z) {
                    this.h++;
                } else {
                    this.h--;
                }
            }
            if (this.d != null) {
                this.o.onItemCheckedStateChanged(this.d, i, adapter.getItemId(i), z);
            }
            if (z3) {
                this.i.requestLayout();
            }
        }
    }

    private void c(boolean z, RecyclerView.Adapter adapter, int i) {
        if (this.n == null || !adapter.hasStableIds()) {
            return;
        }
        if (z) {
            this.n.put(adapter.getItemId(i), Integer.valueOf(i));
        } else {
            this.n.delete(adapter.getItemId(i));
        }
    }

    private boolean b(RecyclerView.Adapter adapter, int i, long j, int i2, int i3) {
        while (i2 < i3) {
            if (j == adapter.getItemId(i2)) {
                this.m.put(i2, true);
                this.n.setValueAt(i, Integer.valueOf(i2));
                return true;
            }
            i2++;
        }
        return false;
    }

    private void a(long j, int i) {
        c cVar;
        ActionMode actionMode = this.d;
        if (actionMode == null || (cVar = this.o) == null) {
            return;
        }
        cVar.onItemCheckedStateChanged(actionMode, i, j, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void a(View view, int i) {
        if (this.f != 3 || this.d == null) {
            return;
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.m.get(i));
        } else {
            view.setActivated(this.m.get(i));
        }
    }

    void d(RecyclerView.Adapter adapter) {
        if (adapter != null && this.f != 0 && adapter.hasStableIds() && this.n == null) {
            this.n = new LongSparseArray<>();
        }
        b();
        ActionMode actionMode = this.d;
        if (actionMode != null) {
            actionMode.finish();
            this.d = null;
        }
    }

    void e(boolean z, boolean z2) {
        if (z) {
            this.c = z2;
        } else {
            this.b = z2;
        }
    }

    void d(HwRecyclerView.MultiChoiceModeListener multiChoiceModeListener) {
        if (this.o == null) {
            this.o = new c();
        }
        this.k = multiChoiceModeListener;
        this.o.e(multiChoiceModeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean egu_(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.c) {
            return false;
        }
        View findChildViewUnder = this.i.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder != null) {
            int childAdapterPosition = this.i.getChildAdapterPosition(findChildViewUnder);
            if (this.f == 3 && this.d != null && childAdapterPosition != -1) {
                int i = this.j;
                if (i != -1 && i != childAdapterPosition) {
                    e(false);
                }
                z = true;
                if (this.j != childAdapterPosition) {
                    this.j = childAdapterPosition;
                    e(true);
                }
                this.e = true;
            }
        }
        return z;
    }

    Parcelable egy_(Parcelable parcelable) {
        c cVar;
        if (!(parcelable instanceof akxao)) {
            return null;
        }
        akxao akxaoVar = (akxao) parcelable;
        if (akxaoVar.c != null) {
            this.m = akxaoVar.c;
        }
        if (akxaoVar.d != null) {
            this.n = akxaoVar.d;
        }
        this.h = akxaoVar.b;
        if (akxaoVar.f10734a && this.f == 3 && (cVar = this.o) != null) {
            this.d = this.i.startActionMode(cVar);
        }
        this.g = akxaoVar.e;
        this.j = akxaoVar.f;
        this.i.requestLayout();
        return akxaoVar.getSuperState();
    }

    void e() {
        SparseBooleanArray sparseBooleanArray = this.m;
        if (sparseBooleanArray != null) {
            int size = sparseBooleanArray.size();
            for (int i = 0; i < size; i++) {
                c(this.m.keyAt(i), false);
            }
        }
    }
}
