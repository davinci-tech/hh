package com.huawei.uikit.hwalphaindexerlistview.widget;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import defpackage.sko;
import defpackage.skq;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class HwSortedTextListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10595a = "HwSortedTextListAdapter";
    private static final int b = 16;
    private static final String c = "";
    private static final int d = -1;
    private HwSectionIndexer e;
    private int f;
    private int g;
    private final Object h;
    private boolean i;
    private Map<String, String> j;
    private Map<String, CollationKey> k;
    private Context l;
    private String m;
    private LayoutInflater n;
    private List<? extends Map<String, ?>> o;

    class a implements Comparator<Map<String, ?>> {
        final /* synthetic */ Comparator d;

        a(Comparator comparator) {
            this.d = comparator;
        }

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(Map<String, ?> map, Map<String, ?> map2) {
            int a2;
            String obj = map.get(HwSortedTextListAdapter.this.m).toString();
            String obj2 = map2.get(HwSortedTextListAdapter.this.m).toString();
            String str = (String) HwSortedTextListAdapter.this.j.get(obj);
            String str2 = (String) HwSortedTextListAdapter.this.j.get(obj2);
            if (this.d == null) {
                return 0;
            }
            if ("".equals(str) && "".equals(str2)) {
                return this.d.compare(obj, obj2);
            }
            if ("".equals(str) && !"".equals(str2)) {
                return 1;
            }
            if ("".equals(str2) && !"".equals(str)) {
                return -1;
            }
            if (HwSortedTextListAdapter.this.i && !str.equals(str2)) {
                if ("#".equals(str)) {
                    return 1;
                }
                if ("#".equals(str2)) {
                    return -1;
                }
            }
            if (MLAsrConstants.LAN_ZH.equals(Locale.getDefault().getLanguage()) && (a2 = HwSortedTextListAdapter.this.a(str, str2)) != 0) {
                return a2;
            }
            int compare = this.d.compare(str, str2);
            return compare == 0 ? this.d.compare(obj, obj2) : compare;
        }
    }

    class c implements Comparator<String> {
        final /* synthetic */ Collator c;

        c(Collator collator) {
            this.c = collator;
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            int a2;
            if ("".equals(str)) {
                return 1;
            }
            if ("".equals(str2)) {
                return -1;
            }
            if (HwSortedTextListAdapter.this.i) {
                if ("#".equals(str)) {
                    return 1;
                }
                if ("#".equals(str2)) {
                    return -1;
                }
            }
            return (!MLAsrConstants.LAN_ZH.equals(Locale.getDefault().getLanguage()) || (a2 = HwSortedTextListAdapter.this.a(str, str2)) == 0) ? this.c.compare(str, str2) : a2;
        }
    }

    public HwSortedTextListAdapter(Context context, int i, List<? extends Map<String, ?>> list, String str) {
        this(context, i, 0, list, str);
    }

    public Context getContext() {
        return this.l;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.o.size();
    }

    public LayoutInflater getInflater() {
        return this.n;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return (this.o != null && i >= 0 && i < getCount()) ? this.o.get(i).get(this.m) : "";
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public int getPosition(Map<String, ?> map) {
        return this.o.indexOf(map);
    }

    public int getPositionForSection(int i) {
        return this.e.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return this.e.getSectionForPosition(i);
    }

    public Object getSectionNameForPosition(int i) {
        return this.e.getSections()[getSectionForPosition(i)];
    }

    public Object[] getSections() {
        return this.e.getSections();
    }

    public String getSortKeyName() {
        return this.m;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return a(i, view, viewGroup, this.f);
    }

    public boolean isDigitLast() {
        return this.i;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setSortKeyName(String str) {
        this.m = str;
    }

    public void setViewImage(ImageView imageView, String str) {
        if (imageView == null) {
            Log.w(f10595a, "setViewImage: view is null!");
            return;
        }
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    public void setViewText(TextView textView, String str) {
        if (textView == null) {
            Log.w(f10595a, "setViewText: view is null!");
        } else {
            textView.setText(str);
        }
    }

    public void sort(Comparator<Object> comparator) {
        synchronized (this.h) {
            Collections.sort(this.o, new a(comparator));
        }
        notifyDataSetChanged();
    }

    public HwSortedTextListAdapter(Context context, int i, int i2, List<? extends Map<String, ?>> list, String str) {
        this(context, i, i2, list, str, false);
    }

    public HwSortedTextListAdapter(Context context, int i, int i2, List<? extends Map<String, ?>> list, String str, boolean z) {
        this.g = 0;
        this.h = new Object();
        this.i = false;
        this.l = context;
        this.n = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f = i;
        this.g = i2;
        this.i = z;
        this.o = list;
        this.m = str;
        a();
    }

    private View a(int i, View view, ViewGroup viewGroup, int i2) {
        TextView textView;
        if (view == null) {
            view = this.n.inflate(i2, viewGroup, false);
        }
        try {
            int i3 = this.g;
            if (i3 == 0) {
                textView = view instanceof TextView ? (TextView) view : null;
            } else {
                textView = (TextView) view.findViewById(i3);
            }
            Object item = getItem(i);
            if (textView != null) {
                if (item instanceof CharSequence) {
                    textView.setText((CharSequence) item);
                } else {
                    textView.setText(String.valueOf(item));
                }
            }
            return view;
        } catch (ClassCastException unused) {
            throw new IllegalStateException("HwSortedTextListAdapter requires the resource ID to be a TextView");
        }
    }

    public void setViewImage(ImageView imageView, int i) {
        if (imageView == null) {
            Log.w(f10595a, "setViewImage: view is null!");
        } else {
            imageView.setImageResource(i);
        }
    }

    private void a() {
        sko e = sko.e();
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        this.j = new HashMap(16);
        this.k = new HashMap(16);
        Collator collator = Collator.getInstance();
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            String obj = this.o.get(i).get(this.m).toString();
            this.k.put(obj, collator.getCollationKey(obj));
            String e2 = TextUtils.isEmpty(obj) ? "" : e.e(obj);
            this.j.put(obj, e2);
            if (linkedHashMap.containsKey(e2)) {
                linkedHashMap.put(e2, Integer.valueOf(((Integer) linkedHashMap.get(e2)).intValue() + 1));
            } else {
                linkedHashMap.put(e2, 1);
            }
        }
        String[] strArr = (String[]) linkedHashMap.keySet().toArray(new String[0]);
        int length = strArr.length;
        a(strArr, collator);
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = ((Integer) linkedHashMap.get(strArr[i2])).intValue();
        }
        this.e = new HwSectionIndexer(strArr, iArr);
        sort(collator);
    }

    private void a(String[] strArr, Collator collator) {
        Arrays.sort(strArr, new c(collator));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(String str, String str2) {
        if (str != null && str2 != null) {
            int length = str.length();
            int length2 = str2.length();
            if (length > length2) {
                return 1;
            }
            if (length < length2) {
                return -1;
            }
        }
        return 0;
    }

    private String a(String str) {
        String e = skq.b().e(str);
        return (!TextUtils.isEmpty(e) && e.length() >= 1) ? e.substring(0, 1).toUpperCase(Locale.ENGLISH) : "";
    }
}
