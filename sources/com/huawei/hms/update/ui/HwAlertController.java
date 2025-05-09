package com.huawei.hms.update.ui;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.ButtonConfig;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class HwAlertController {
    CharSequence A;
    Message B;
    Drawable C;
    ScrollView D;
    Drawable F;
    ImageView G;
    TextView H;
    TextView I;
    TextView J;
    View K;
    ListAdapter L;

    /* renamed from: a, reason: collision with root package name */
    Handler f6092a;
    private final Context b;
    final Dialog c;
    final Window d;
    CharSequence f;
    CharSequence g;
    CharSequence h;
    View i;
    ListView j;
    int k;
    int m;
    int n;
    int o;
    int p;
    Button r;
    CharSequence s;
    Message t;
    Drawable u;
    Button v;
    CharSequence w;
    Message x;
    Drawable y;
    Button z;
    boolean l = false;
    HashMap<Integer, ButtonConfig> q = new HashMap<>();
    int E = 0;
    int M = -1;
    private int S = 0;
    private final View.OnClickListener T = new View.OnClickListener() { // from class: com.huawei.hms.update.ui.HwAlertController.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            HwAlertController hwAlertController = HwAlertController.this;
            Message obtain = (view != hwAlertController.r || (message3 = hwAlertController.t) == null) ? (view != hwAlertController.z || (message2 = hwAlertController.B) == null) ? (view != hwAlertController.v || (message = hwAlertController.x) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
            if (obtain != null) {
                obtain.sendToTarget();
            }
            HwAlertController hwAlertController2 = HwAlertController.this;
            hwAlertController2.f6092a.obtainMessage(1, hwAlertController2.c).sendToTarget();
        }
    };
    public int mAlertDialogLayout = ResourceLoaderUtil.getLayoutId("hw_cloud_alert_dialog_material");
    public int mBtnPanelSideLayout = ResourceLoaderUtil.getLayoutId("hw_cloud_alert_dialog_button_bar_material");
    int O = ResourceLoaderUtil.getLayoutId("hw_cloud_select_dialog_material");
    int R = ResourceLoaderUtil.getLayoutId("hw_cloud_select_dialog_multichoice_material");
    int Q = ResourceLoaderUtil.getLayoutId("hw_cloud_select_dialog_singlechoice_material");
    int P = ResourceLoaderUtil.getLayoutId("hw_cloud_select_dialog_item_material");
    private final boolean N = true;
    final int e = 0;

    /* renamed from: com.huawei.hms.update.ui.HwAlertController$2, reason: invalid class name */
    class AnonymousClass2 implements View.OnScrollChangeListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f6094a;
        final /* synthetic */ View b;

        AnonymousClass2(View view, View view2) {
            this.f6094a = view;
            this.b = view2;
        }

        @Override // android.view.View.OnScrollChangeListener
        public void onScrollChange(View view, int i, int i2, int i3, int i4) {
            HwAlertController.a(view, this.f6094a, this.b);
        }
    }

    /* renamed from: com.huawei.hms.update.ui.HwAlertController$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f6095a;
        final /* synthetic */ View b;

        AnonymousClass3(View view, View view2) {
            this.f6095a = view;
            this.b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            HwAlertController.a(HwAlertController.this.D, this.f6095a, this.b);
        }
    }

    /* renamed from: com.huawei.hms.update.ui.HwAlertController$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f6096a;
        final /* synthetic */ View b;

        AnonymousClass4(View view, View view2) {
            this.f6096a = view;
            this.b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            HwAlertController.a(HwAlertController.this.j, this.f6096a, this.b);
        }
    }

    public static class AlertParams {
        public boolean[] mChkItems;
        public View mCtmTitleView;
        public final Context mCtx;
        public Cursor mCursor;
        public boolean mForceInverseBg;
        public Drawable mIc;
        public final LayoutInflater mInflater;
        public String mIsChkColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItemArray;
        public String mLabelColumn;
        public ListAdapter mListAdapter;
        public CharSequence mMsg;
        public Drawable mNegativeBtnIcon;
        public DialogInterface.OnClickListener mNegativeBtnListener;
        public CharSequence mNegativeBtnText;
        public Drawable mNeutralBtnIcon;
        public DialogInterface.OnClickListener mNeutralBtnListener;
        public CharSequence mNeutralBtnText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCkbClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismListener;
        public AdapterView.OnItemSelectedListener mOnItemSleListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareLvListener;
        public Drawable mPositiveBtnIcon;
        public DialogInterface.OnClickListener mPositiveBtnListener;
        public CharSequence mPositiveBtnText;
        public CharSequence mSubTitle;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpcBottom;
        public int mViewSpcLeft;
        public int mViewSpcRight;
        public int mViewSpcTop;
        public int mIcId = 0;
        public int mIcAttrId = 0;
        public boolean mViewSpcSpecified = false;
        public int mChkItem = -1;
        public boolean mRecycleOnMeasure = true;
        public HashMap<Integer, ButtonConfig> btnConfigs = new HashMap<>();
        public boolean mCancelable = true;

        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView listView);
        }

        public AlertParams(Context context) {
            this.mCtx = context;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
            this.btnConfigs.put(-1, ButtonConfig.createDefault(context));
            this.btnConfigs.put(-2, ButtonConfig.createDefault(context));
            this.btnConfigs.put(-3, ButtonConfig.createDefault(context));
        }

        private void a(final HwAlertController hwAlertController) {
            ListAdapter listAdapter;
            final ListView listView = null;
            try {
                listView = (ListView) this.mInflater.inflate(hwAlertController.O, (ViewGroup) null);
            } catch (Exception e) {
                HMSLog.e("HwAlertController", "<createListView> inflate listView Exception: " + e.getMessage());
            }
            if (listView == null) {
                return;
            }
            if (!this.mIsMultiChoice) {
                int i = this.mIsSingleChoice ? hwAlertController.Q : hwAlertController.P;
                Cursor cursor = this.mCursor;
                if (cursor != null) {
                    listAdapter = new SimpleCursorAdapter(this.mCtx, i, cursor, new String[]{this.mLabelColumn}, new int[]{R.id.text1});
                } else {
                    ListAdapter listAdapter2 = this.mListAdapter;
                    if (listAdapter2 == null) {
                        listAdapter2 = new b(this.mCtx, i, R.id.text1, this.mItemArray);
                    }
                    listAdapter = listAdapter2;
                }
            } else if (this.mCursor == null) {
                final ListView listView2 = listView;
                listAdapter = new ArrayAdapter<CharSequence>(this.mCtx, hwAlertController.R, R.id.text1, this.mItemArray) { // from class: com.huawei.hms.update.ui.HwAlertController.AlertParams.1
                    @Override // android.widget.ArrayAdapter, android.widget.Adapter
                    public View getView(int i2, View view, ViewGroup viewGroup) {
                        View view2 = super.getView(i2, view, viewGroup);
                        boolean[] zArr = AlertParams.this.mChkItems;
                        if (zArr != null && zArr[i2]) {
                            listView2.setItemChecked(i2, true);
                        }
                        return view2;
                    }
                };
            } else {
                final ListView listView3 = listView;
                listAdapter = new CursorAdapter(this.mCtx, this.mCursor, false) { // from class: com.huawei.hms.update.ui.HwAlertController.AlertParams.2

                    /* renamed from: a, reason: collision with root package name */
                    private final int f6098a;
                    private final int b;

                    {
                        Cursor cursor2 = getCursor();
                        this.f6098a = cursor2.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
                        this.b = cursor2.getColumnIndexOrThrow(AlertParams.this.mIsChkColumn);
                    }

                    @Override // android.widget.CursorAdapter
                    public void bindView(View view, Context context, Cursor cursor2) {
                        ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor2.getString(this.f6098a));
                        listView3.setItemChecked(cursor2.getPosition(), cursor2.getInt(this.b) == 1);
                    }

                    @Override // android.widget.CursorAdapter
                    public View newView(Context context, Cursor cursor2, ViewGroup viewGroup) {
                        return AlertParams.this.mInflater.inflate(hwAlertController.R, viewGroup, false);
                    }
                };
            }
            OnPrepareListViewListener onPrepareListViewListener = this.mOnPrepareLvListener;
            if (onPrepareListViewListener != null) {
                onPrepareListViewListener.onPrepareListView(listView);
            }
            hwAlertController.L = listAdapter;
            hwAlertController.M = this.mChkItem;
            if (this.mOnClickListener != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.hms.update.ui.HwAlertController.AlertParams.3
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                        AlertParams.this.mOnClickListener.onClick(hwAlertController.c, i2);
                        if (AlertParams.this.mIsSingleChoice) {
                            return;
                        }
                        hwAlertController.c.dismiss();
                    }
                });
            } else if (this.mOnCkbClickListener != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.hms.update.ui.HwAlertController.AlertParams.4
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                        boolean[] zArr = AlertParams.this.mChkItems;
                        if (zArr != null) {
                            zArr[i2] = listView.isItemChecked(i2);
                        }
                        AlertParams.this.mOnCkbClickListener.onClick(hwAlertController.c, i2, listView.isItemChecked(i2));
                    }
                });
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mOnItemSleListener;
            if (onItemSelectedListener != null) {
                listView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                listView.setChoiceMode(1);
            } else if (this.mIsMultiChoice) {
                listView.setChoiceMode(2);
            }
            hwAlertController.j = listView;
        }

        public void apply(HwAlertController hwAlertController) {
            HashMap<Integer, ButtonConfig> hashMap;
            View view = this.mCtmTitleView;
            if (view != null) {
                hwAlertController.setCustomTitle(view);
            } else {
                CharSequence charSequence = this.mTitle;
                if (charSequence != null) {
                    hwAlertController.setTitle(charSequence);
                }
                CharSequence charSequence2 = this.mSubTitle;
                if (charSequence2 != null) {
                    hwAlertController.setSubTitle(charSequence2);
                }
                Drawable drawable = this.mIc;
                if (drawable != null) {
                    hwAlertController.setIcon(drawable);
                }
                int i = this.mIcId;
                if (i != 0) {
                    hwAlertController.setIcon(i);
                }
                int i2 = this.mIcAttrId;
                if (i2 != 0) {
                    hwAlertController.setIcon(hwAlertController.getIconAttributeResId(i2));
                }
            }
            HashMap<Integer, ButtonConfig> hashMap2 = hwAlertController.q;
            if (hashMap2 != null && (hashMap = this.btnConfigs) != null) {
                hashMap2.putAll(hashMap);
            }
            CharSequence charSequence3 = this.mMsg;
            if (charSequence3 != null) {
                hwAlertController.setMessage(charSequence3);
            }
            CharSequence charSequence4 = this.mPositiveBtnText;
            if (charSequence4 != null || this.mPositiveBtnIcon != null) {
                hwAlertController.setButton(-1, charSequence4, this.mPositiveBtnListener, null, this.mPositiveBtnIcon);
            }
            CharSequence charSequence5 = this.mNegativeBtnText;
            if (charSequence5 != null || this.mNegativeBtnIcon != null) {
                hwAlertController.setButton(-2, charSequence5, this.mNegativeBtnListener, null, this.mNegativeBtnIcon);
            }
            CharSequence charSequence6 = this.mNeutralBtnText;
            if (charSequence6 != null || this.mNeutralBtnIcon != null) {
                hwAlertController.setButton(-3, charSequence6, this.mNeutralBtnListener, null, this.mNeutralBtnIcon);
            }
            if (this.mItemArray != null || this.mCursor != null || this.mListAdapter != null) {
                a(hwAlertController);
            }
            View view2 = this.mView;
            if (view2 != null) {
                if (this.mViewSpcSpecified) {
                    hwAlertController.setView(view2, this.mViewSpcLeft, this.mViewSpcTop, this.mViewSpcRight, this.mViewSpcBottom);
                    return;
                } else {
                    hwAlertController.setView(view2);
                    return;
                }
            }
            int i3 = this.mViewLayoutResId;
            if (i3 != 0) {
                hwAlertController.setView(i3);
            }
        }
    }

    public static class RecycleListView extends ListView {

        /* renamed from: a, reason: collision with root package name */
        private final int f6101a;
        private final int b;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public void setHasDecor(boolean z, boolean z2) {
            if (z && z2) {
                return;
            }
            setPadding(getPaddingLeft(), z ? getPaddingTop() : this.f6101a, getPaddingRight(), z2 ? getPaddingBottom() : this.b);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = -1;
            this.f6101a = -1;
        }
    }

    static final class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<DialogInterface> f6102a;

        public a(DialogInterface dialogInterface) {
            this.f6102a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -1 || i == -2 || i == -3) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f6102a.get(), message.what);
            } else if (i == 1) {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    static class b extends ArrayAdapter<CharSequence> {
        public b(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    static class c implements AbsListView.OnScrollListener {

        /* renamed from: a, reason: collision with root package name */
        private final View f6103a;
        private final View b;

        public c(View view, View view2) {
            this.f6103a = view;
            this.b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            HwAlertController.a(absListView, this.f6103a, this.b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    public HwAlertController(Context context, Dialog dialog, Window window) {
        this.b = context;
        this.c = dialog;
        this.d = window;
        this.f6092a = new a(dialog);
        dialog.requestWindowFeature(1);
    }

    private static boolean a(Context context) {
        return true;
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        View findViewById;
        ListAdapter listAdapter;
        View findViewById2;
        Window window = this.d;
        if (window == null) {
            HMSLog.w("HwAlertController", "mWin is null");
            return;
        }
        View findViewById3 = window.findViewById(ResourceLoaderUtil.getIdId("parentPanel"));
        if (findViewById3 == null) {
            HMSLog.w("HwAlertController", "parentPanel is null");
            return;
        }
        View findViewById4 = findViewById3.findViewById(ResourceLoaderUtil.getIdId("topPanel"));
        View findViewById5 = findViewById3.findViewById(ResourceLoaderUtil.getIdId("contentPanel"));
        View findViewById6 = findViewById3.findViewById(ResourceLoaderUtil.getIdId("buttonPanel"));
        ViewGroup viewGroup = (ViewGroup) findViewById3.findViewById(ResourceLoaderUtil.getIdId("customPanel"));
        if (viewGroup == null) {
            return;
        }
        c(viewGroup);
        View findViewById7 = viewGroup.findViewById(ResourceLoaderUtil.getIdId("topPanel"));
        View findViewById8 = viewGroup.findViewById(ResourceLoaderUtil.getIdId("contentPanel"));
        View findViewById9 = viewGroup.findViewById(ResourceLoaderUtil.getIdId("buttonPanel"));
        ViewGroup a2 = a(findViewById7, findViewById4);
        ViewGroup a3 = a(findViewById8, findViewById5);
        ViewGroup a4 = a(findViewById9, findViewById6);
        if (a3 != null) {
            b(a3);
        }
        if (a4 != null) {
            a(a4);
        }
        if (a2 != null) {
            d(a2);
        }
        boolean z = viewGroup.getVisibility() != 8;
        boolean z2 = (a2 == null || a2.getVisibility() == 8) ? 0 : 1;
        boolean z3 = (a4 == null || a4.getVisibility() == 8) ? false : true;
        if (!z3 && a3 != null && (findViewById2 = a3.findViewById(ResourceLoaderUtil.getIdId("textSpacerNoButtons"))) != null) {
            findViewById2.setVisibility(0);
        }
        if (z2 != 0) {
            ScrollView scrollView = this.D;
            if (scrollView != null) {
                scrollView.setClipToPadding(true);
            }
        } else {
            if (a3 != null && (findViewById = a3.findViewById(ResourceLoaderUtil.getIdId("textSpacerNoTitle"))) != null) {
                findViewById.setVisibility(0);
            }
            this.J.setGravity(17);
        }
        ListView listView = this.j;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).setHasDecor(z2, z3);
        }
        if (!z) {
            View view = this.j;
            if (view == null) {
                view = this.D;
            }
            if (view != null) {
                a(a3, view, z2 | (z3 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.j;
        if (listView2 == null || (listAdapter = this.L) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i = this.M;
        if (i > -1) {
            listView2.setItemChecked(i, true);
            listView2.setSelection(i);
        }
    }

    private void c(ViewGroup viewGroup) {
        View view = this.i;
        if (view == null) {
            view = this.k != 0 ? LayoutInflater.from(this.b).inflate(this.k, viewGroup, false) : null;
        }
        boolean z = view != null;
        if (!z || !a(view)) {
            this.d.setFlags(131072, 131072);
        }
        if (!z) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.d.findViewById(ResourceLoaderUtil.getIdId("custom"));
        frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
        if (this.l) {
            frameLayout.setPadding(this.m, this.n, this.o, this.p);
        }
    }

    private void d(ViewGroup viewGroup) {
        int i = 0;
        if (this.K != null) {
            viewGroup.addView(this.K, 0, new ViewGroup.LayoutParams(-1, -2));
            this.d.findViewById(ResourceLoaderUtil.getIdId("title_template")).setVisibility(8);
            return;
        }
        this.G = (ImageView) this.d.findViewById(R.id.icon);
        if (!(!TextUtils.isEmpty(this.f)) || !this.N) {
            this.d.findViewById(ResourceLoaderUtil.getIdId("title_template")).setVisibility(8);
            this.G.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.d.findViewById(ResourceLoaderUtil.getIdId("alertTitle"));
        this.H = textView;
        textView.setText(this.f);
        int i2 = this.E;
        if (i2 != 0) {
            this.G.setImageResource(i2);
        } else {
            Drawable drawable = this.F;
            if (drawable != null) {
                this.G.setImageDrawable(drawable);
            } else {
                this.H.setPadding(this.G.getPaddingLeft(), this.G.getPaddingTop(), this.G.getPaddingRight(), this.G.getPaddingBottom());
                this.G.setVisibility(8);
            }
        }
        this.I = (TextView) this.d.findViewById(ResourceLoaderUtil.getIdId("subTitle"));
        Context context = this.b;
        int dimensionPixelSize = context == null ? 0 : context.getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_title_layout_min_height"));
        if (this.I == null || TextUtils.isEmpty(this.g)) {
            i = dimensionPixelSize;
        } else {
            this.I.setVisibility(0);
            this.I.setText(this.g);
            Context context2 = this.b;
            if (context2 != null) {
                i = context2.getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_title_layout_max_height"));
            }
        }
        this.d.findViewById(ResourceLoaderUtil.getIdId("title_template")).setMinimumHeight(i);
    }

    public Button getButton(int i) {
        if (i == -1) {
            return this.r;
        }
        if (i == -2) {
            return this.z;
        }
        if (i == -3) {
            return this.v;
        }
        return null;
    }

    public int getIconAttributeResId(int i) {
        TypedValue typedValue = new TypedValue();
        Context context = this.b;
        if (context == null) {
            HMSLog.w("HwAlertController", "mCtx is null");
        } else {
            context.getTheme().resolveAttribute(i, typedValue, true);
        }
        return typedValue.resourceId;
    }

    public ListView getListView() {
        return this.j;
    }

    public void installContent() {
        if (this.c != null) {
            this.c.setContentView(a());
        }
        b();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        ScrollView scrollView = this.D;
        return scrollView != null && scrollView.executeKeyEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        ScrollView scrollView = this.D;
        return scrollView != null && scrollView.executeKeyEvent(keyEvent);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.f6092a.obtainMessage(i, onClickListener);
        }
        if (i == -1) {
            this.s = charSequence;
            this.t = message;
            this.u = drawable;
        } else if (i == -2) {
            this.A = charSequence;
            this.B = message;
            this.C = drawable;
        } else {
            if (i != -3) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.w = charSequence;
            this.x = message;
            this.y = drawable;
        }
    }

    public void setButtonPanelLayoutHint(int i) {
        this.S = i;
    }

    public void setCustomTitle(View view) {
        this.K = view;
    }

    public void setIcon(int i) {
        this.F = null;
        this.E = i;
        ImageView imageView = this.G;
        if (imageView != null) {
            if (i == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.G.setImageResource(this.E);
            }
        }
    }

    public void setMessage(CharSequence charSequence) {
        this.h = charSequence;
        TextView textView = this.J;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setSubTitle(CharSequence charSequence) {
        this.g = charSequence;
        TextView textView = this.I;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.f = charSequence;
        TextView textView = this.H;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setView(int i) {
        this.i = null;
        this.k = i;
        this.l = false;
    }

    public void setView(View view) {
        this.i = view;
        this.k = 0;
        this.l = false;
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.i = view;
        this.k = 0;
        this.l = true;
        this.m = i;
        this.n = i2;
        this.o = i3;
        this.p = i4;
    }

    public void setIcon(Drawable drawable) {
        this.F = drawable;
        this.E = 0;
        ImageView imageView = this.G;
        if (imageView != null) {
            if (drawable != null) {
                imageView.setVisibility(0);
                this.G.setImageDrawable(drawable);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    private int a() {
        int i = this.mBtnPanelSideLayout;
        if (i == 0) {
            return this.mAlertDialogLayout;
        }
        return this.S == 1 ? i : this.mAlertDialogLayout;
    }

    private ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private void a(ViewGroup viewGroup, View view, int i, int i2) {
        View findViewById = this.d.findViewById(ResourceLoaderUtil.getIdId("scrollIndicatorUp"));
        View findViewById2 = this.d.findViewById(ResourceLoaderUtil.getIdId("scrollIndicatorDown"));
        view.setScrollIndicators(i, i2);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        if (findViewById2 != null) {
            viewGroup.removeView(findViewById2);
        }
    }

    private void b(ViewGroup viewGroup) {
        View findViewById = this.d.findViewById(ResourceLoaderUtil.getIdId("scrollView"));
        if (findViewById instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) findViewById;
            this.D = scrollView;
            scrollView.setFocusable(false);
            this.D.setNestedScrollingEnabled(false);
            TextView textView = (TextView) viewGroup.findViewById(R.id.message);
            this.J = textView;
            if (textView == null) {
                return;
            }
            CharSequence charSequence = this.h;
            if (charSequence != null) {
                textView.setText(charSequence);
                return;
            }
            textView.setVisibility(8);
            this.D.removeView(this.J);
            if (this.j != null) {
                ViewGroup viewGroup2 = (ViewGroup) this.D.getParent();
                int indexOfChild = viewGroup2.indexOfChild(this.D);
                viewGroup2.removeViewAt(indexOfChild);
                viewGroup2.addView(this.j, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            viewGroup.setVisibility(8);
        }
    }

    static void a(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    private void a(ViewGroup viewGroup) {
        int i;
        Context context = this.b;
        int color = context == null ? 0 : context.getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_text_color"));
        Context context2 = this.b;
        int dimensionPixelSize = context2 == null ? 0 : context2.getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_text_size"));
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.r = button;
        button.setOnClickListener(this.T);
        if (TextUtils.isEmpty(this.s) && this.u == null) {
            this.r.setVisibility(8);
            i = 0;
        } else {
            this.r.setText(this.s);
            Drawable drawable = this.u;
            if (drawable != null) {
                int i2 = this.e;
                drawable.setBounds(0, 0, i2, i2);
                this.r.setCompoundDrawables(this.u, null, null, null);
            }
            a(this.r, this.q.get(-1), color, dimensionPixelSize);
            this.r.setVisibility(0);
            i = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.z = button2;
        button2.setOnClickListener(this.T);
        if (TextUtils.isEmpty(this.A) && this.C == null) {
            this.z.setVisibility(8);
        } else {
            this.z.setText(this.A);
            Drawable drawable2 = this.C;
            if (drawable2 != null) {
                int i3 = this.e;
                drawable2.setBounds(0, 0, i3, i3);
                this.z.setCompoundDrawables(this.C, null, null, null);
            }
            a(this.z, this.q.get(-2), color, dimensionPixelSize);
            this.z.setVisibility(0);
            i |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.v = button3;
        button3.setOnClickListener(this.T);
        if (TextUtils.isEmpty(this.w) && this.y == null) {
            this.v.setVisibility(8);
        } else {
            this.v.setText(this.w);
            Drawable drawable3 = this.y;
            if (drawable3 != null) {
                int i4 = this.e;
                drawable3.setBounds(0, 0, i4, i4);
                this.v.setCompoundDrawables(this.y, null, null, null);
            }
            a(this.v, this.q.get(-3), color, dimensionPixelSize);
            this.v.setVisibility(0);
            i |= 4;
        }
        if (a(this.b)) {
            if (i == 1) {
                a(this.r);
            } else if (i == 2) {
                a(this.z);
            } else if (i == 4) {
                a(this.v);
            }
        }
        if (i != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void a(Button button, ButtonConfig buttonConfig, int i, int i2) {
        if (button == null) {
            return;
        }
        String str = Build.MANUFACTURER;
        ButtonConfig.Level level = buttonConfig.f;
        ButtonConfig.Level level2 = ButtonConfig.Level.NORMAL;
        if (level == level2 && (i > 0 || (str != null && str.equals("HUAWEI")))) {
            buttonConfig.c = i;
            buttonConfig.d = i;
        }
        if (i2 > 0 && buttonConfig.f == level2) {
            buttonConfig.e = i2;
        }
        HwDialogUtil.a(button, buttonConfig.f6083a, buttonConfig.b);
        HwDialogUtil.b(button, buttonConfig.c, buttonConfig.d);
        button.setTextSize(0, buttonConfig.e);
    }

    private void a(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }
}
