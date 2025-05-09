package androidx.core.transition;

import android.transition.Transition;
import defpackage.ueu;
import defpackage.uhy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u001aÉ\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\bø\u0001\u0000\u001a5\u0010\r\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\bø\u0001\u0000\u001a5\u0010\u000f\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\bø\u0001\u0000\u001a5\u0010\u0010\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\bø\u0001\u0000\u001a5\u0010\u0011\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\bø\u0001\u0000\u001a5\u0010\u0012\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"addListener", "Landroid/transition/Transition$TransitionListener;", "Landroid/transition/Transition;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "transition", "", "onStart", "onCancel", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnResume", "doOnStart", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class TransitionKt {
    public static /* synthetic */ Transition.TransitionListener addListener$default(Transition transition, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Transition, ueu>() { // from class: androidx.core.transition.TransitionKt$addListener$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ ueu invoke(Transition transition2) {
                    invoke2(transition2);
                    return ueu.d;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Transition transition2) {
                    uhy.e((Object) transition2, "");
                }
            };
        }
        if ((i & 2) != 0) {
            function12 = new Function1<Transition, ueu>() { // from class: androidx.core.transition.TransitionKt$addListener$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ ueu invoke(Transition transition2) {
                    invoke2(transition2);
                    return ueu.d;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Transition transition2) {
                    uhy.e((Object) transition2, "");
                }
            };
        }
        Function1 function16 = function12;
        if ((i & 4) != 0) {
            function13 = new Function1<Transition, ueu>() { // from class: androidx.core.transition.TransitionKt$addListener$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ ueu invoke(Transition transition2) {
                    invoke2(transition2);
                    return ueu.d;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Transition transition2) {
                    uhy.e((Object) transition2, "");
                }
            };
        }
        Function1 function17 = function13;
        if ((i & 8) != 0) {
            function14 = new Function1<Transition, ueu>() { // from class: androidx.core.transition.TransitionKt$addListener$4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ ueu invoke(Transition transition2) {
                    invoke2(transition2);
                    return ueu.d;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Transition transition2) {
                    uhy.e((Object) transition2, "");
                }
            };
        }
        if ((i & 16) != 0) {
            function15 = new Function1<Transition, ueu>() { // from class: androidx.core.transition.TransitionKt$addListener$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ ueu invoke(Transition transition2) {
                    invoke2(transition2);
                    return ueu.d;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Transition transition2) {
                    uhy.e((Object) transition2, "");
                }
            };
        }
        uhy.e((Object) transition, "");
        uhy.e((Object) function1, "");
        uhy.e((Object) function16, "");
        uhy.e((Object) function17, "");
        uhy.e((Object) function14, "");
        uhy.e((Object) function15, "");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function17, function16);
        transition.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    public static final Transition.TransitionListener addListener(Transition transition, Function1<? super Transition, ueu> function1, Function1<? super Transition, ueu> function12, Function1<? super Transition, ueu> function13, Function1<? super Transition, ueu> function14, Function1<? super Transition, ueu> function15) {
        uhy.e((Object) transition, "");
        uhy.e((Object) function1, "");
        uhy.e((Object) function12, "");
        uhy.e((Object) function13, "");
        uhy.e((Object) function14, "");
        uhy.e((Object) function15, "");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function13, function12);
        transition.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    public static final Transition.TransitionListener doOnEnd(Transition transition, final Function1<? super Transition, ueu> function1) {
        uhy.e((Object) transition, "");
        uhy.e((Object) function1, "");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnEnd$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
                uhy.e((Object) transition2, "");
                Function1.this.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
                uhy.e((Object) transition2, "");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnStart(Transition transition, final Function1<? super Transition, ueu> function1) {
        uhy.e((Object) transition, "");
        uhy.e((Object) function1, "");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnStart$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
                uhy.e((Object) transition2, "");
                Function1.this.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
                uhy.e((Object) transition2, "");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnCancel(Transition transition, final Function1<? super Transition, ueu> function1) {
        uhy.e((Object) transition, "");
        uhy.e((Object) function1, "");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnCancel$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
                uhy.e((Object) transition2, "");
                Function1.this.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
                uhy.e((Object) transition2, "");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnResume(Transition transition, final Function1<? super Transition, ueu> function1) {
        uhy.e((Object) transition, "");
        uhy.e((Object) function1, "");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnResume$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
                uhy.e((Object) transition2, "");
                Function1.this.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
                uhy.e((Object) transition2, "");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnPause(Transition transition, final Function1<? super Transition, ueu> function1) {
        uhy.e((Object) transition, "");
        uhy.e((Object) function1, "");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnPause$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
                uhy.e((Object) transition2, "");
                Function1.this.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
                uhy.e((Object) transition2, "");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
                uhy.e((Object) transition2, "");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }
}
