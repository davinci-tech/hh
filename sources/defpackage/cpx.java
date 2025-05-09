package defpackage;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cpx {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11408a;
    private EditText b;
    private Keyboard d;
    private Keyboard e;
    private LinearLayout f;
    private Keyboard h;
    private KeyboardView i;
    private Keyboard j;
    private boolean c = false;
    private KeyboardView.OnKeyboardActionListener g = new KeyboardView.OnKeyboardActionListener() { // from class: cpx.2
        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onPress(int i) {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onRelease(int i) {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onText(CharSequence charSequence) {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeDown() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeLeft() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeRight() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeUp() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onKey(int i, int[] iArr) {
            cpx.this.a(i);
        }
    };

    public cpx(Context context, EditText editText, KeyboardView keyboardView, LinearLayout linearLayout, boolean z) {
        this.f11408a = false;
        this.b = editText;
        this.d = new Keyboard(context, R.xml._2132082702_res_0x7f15000e);
        this.h = new Keyboard(context, R.xml._2132082690_res_0x7f150002);
        this.j = new Keyboard(context, R.xml._2132082707_res_0x7f150013);
        this.e = new Keyboard(context, R.xml._2132082706_res_0x7f150012);
        this.i = keyboardView;
        this.f = linearLayout;
        if (z) {
            e();
        } else {
            this.f11408a = true;
            d();
        }
        KeyboardView keyboardView2 = this.i;
        if (keyboardView2 != null) {
            keyboardView2.setEnabled(true);
            this.i.setPreviewEnabled(false);
            this.i.setOnKeyboardActionListener(this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        EditText editText = this.b;
        if (editText == null || this.i == null) {
            LogUtil.h("KeyboardUtil", "editText or keyboardView is null");
            return;
        }
        Editable text = editText.getText();
        int selectionStart = this.b.getSelectionStart();
        if (i == -5) {
            if (TextUtils.isEmpty(text) || selectionStart <= 0) {
                return;
            }
            text.delete(selectionStart - 1, selectionStart);
            return;
        }
        if (i == 210) {
            this.i.setKeyboard(this.j);
            return;
        }
        if (i == 220) {
            this.i.setKeyboard(this.e);
            return;
        }
        if (i == -3) {
            c();
            return;
        }
        if (i != -2) {
            if (i == -1) {
                a();
                this.i.setKeyboard(this.d);
                return;
            } else {
                text.insert(selectionStart, Character.toString((char) i));
                return;
            }
        }
        if (this.f11408a) {
            this.f11408a = false;
            e();
        } else {
            this.f11408a = true;
            d();
        }
    }

    private void a() {
        List<Keyboard.Key> keys = this.d.getKeys();
        if (this.c) {
            this.c = false;
            LogUtil.a("KeyboardUtil", "changeKey Lower");
            for (Keyboard.Key key : keys) {
                if (key.label != null && d(key.label.toString())) {
                    key.label = key.label.toString().toLowerCase(Locale.ENGLISH);
                    key.codes[0] = key.label.charAt(0);
                }
            }
            return;
        }
        this.c = true;
        LogUtil.a("KeyboardUtil", "changeKey Upper");
        for (Keyboard.Key key2 : keys) {
            if (key2.label != null && d(key2.label.toString())) {
                key2.label = key2.label.toString().toUpperCase(Locale.ENGLISH);
                key2.codes[0] = key2.label.charAt(0);
            }
        }
    }

    public void b() {
        KeyboardView keyboardView = this.i;
        if (keyboardView != null) {
            keyboardView.setVisibility(0);
        }
        LinearLayout linearLayout = this.f;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
    }

    public void c() {
        KeyboardView keyboardView = this.i;
        if (keyboardView == null) {
            LogUtil.h("KeyboardUtil", "keyboardView si null");
            return;
        }
        if (keyboardView.getVisibility() == 0) {
            this.i.setVisibility(8);
            LinearLayout linearLayout = this.f;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
        }
    }

    private boolean a(String str) {
        return "0123456789".indexOf(str) > -1;
    }

    private boolean d(String str) {
        return "abcdefghijklmnopqrstuvwxyz".indexOf(str.toLowerCase(Locale.ENGLISH)) > -1;
    }

    private void d() {
        List<Keyboard.Key> keys = this.h.getKeys();
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).label != null && a(keys.get(i).label.toString())) {
                arrayList.add(keys.get(i));
            }
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(10);
        LinkedList linkedList = new LinkedList();
        for (int i2 = 0; i2 < size; i2++) {
            linkedList.add(new cpv(i2 + 48, i2 + ""));
        }
        SecureRandom b = nsg.b();
        for (int i3 = 0; i3 < size; i3++) {
            int nextInt = b.nextInt(size - i3);
            arrayList2.add(new cpv(((cpv) linkedList.get(nextInt)).b(), ((cpv) linkedList.get(nextInt)).a()));
            linkedList.remove(nextInt);
        }
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            ((Keyboard.Key) arrayList.get(i4)).label = ((cpv) arrayList2.get(i4)).a();
            ((Keyboard.Key) arrayList.get(i4)).codes[0] = ((cpv) arrayList2.get(i4)).b();
        }
        KeyboardView keyboardView = this.i;
        if (keyboardView != null) {
            keyboardView.setKeyboard(this.h);
        }
    }

    private void e() {
        this.c = false;
        List<Keyboard.Key> keys = this.d.getKeys();
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).label != null && d(keys.get(i).label.toString())) {
                arrayList.add(keys.get(i));
            }
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(10);
        LinkedList linkedList = new LinkedList();
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = i2 + 97;
            linkedList.add(new cpv(i3, "" + ((char) i3)));
        }
        SecureRandom b = nsg.b();
        for (int i4 = 0; i4 < size; i4++) {
            int nextInt = b.nextInt(size - i4);
            arrayList2.add(new cpv(((cpv) linkedList.get(nextInt)).b(), ((cpv) linkedList.get(nextInt)).a()));
            linkedList.remove(nextInt);
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            ((Keyboard.Key) arrayList.get(i5)).label = ((cpv) arrayList2.get(i5)).a();
            ((Keyboard.Key) arrayList.get(i5)).codes[0] = ((cpv) arrayList2.get(i5)).b();
        }
        KeyboardView keyboardView = this.i;
        if (keyboardView != null) {
            keyboardView.setKeyboard(this.d);
        }
    }
}
