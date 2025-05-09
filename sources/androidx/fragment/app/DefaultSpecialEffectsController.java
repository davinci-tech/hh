package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {
    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    void executeOperations(List<SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation operation3 : list) {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(operation3.getFragment().mView);
            int i = AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[operation3.getFinalState().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                if (from == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                    operation = operation3;
                }
            } else if (i == 4 && from != SpecialEffectsController.Operation.State.VISIBLE) {
                operation2 = operation3;
            }
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Executing operations from " + operation + " to " + operation2);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList(list);
        syncAnimations(list);
        Iterator<SpecialEffectsController.Operation> it = list.iterator();
        while (it.hasNext()) {
            final SpecialEffectsController.Operation next = it.next();
            CancellationSignal cancellationSignal = new CancellationSignal();
            next.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(next, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            next.markStartedSpecialEffect(cancellationSignal2);
            arrayList2.add(new TransitionInfo(next, cancellationSignal2, z, !z ? next != operation2 : next != operation));
            next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                @Override // java.lang.Runnable
                public void run() {
                    if (arrayList3.contains(next)) {
                        arrayList3.remove(next);
                        DefaultSpecialEffectsController.this.applyContainerChanges(next);
                    }
                }
            });
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, arrayList3, z, operation, operation2);
        startAnimations(arrayList, arrayList3, startTransitions.containsValue(true), startTransitions);
        Iterator<SpecialEffectsController.Operation> it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            applyContainerChanges(it2.next());
        }
        arrayList3.clear();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Completed executing operations from " + operation + " to " + operation2);
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void syncAnimations(List<SpecialEffectsController.Operation> list) {
        Fragment fragment = list.get(list.size() - 1).getFragment();
        for (SpecialEffectsController.Operation operation : list) {
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private void startAnimations(List<AnimationInfo> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        int i;
        boolean z2;
        Context context;
        View view;
        int i2;
        final SpecialEffectsController.Operation operation;
        final ViewGroup container = getContainer();
        Context context2 = container.getContext();
        ArrayList arrayList = new ArrayList();
        Iterator<AnimationInfo> it = list.iterator();
        boolean z3 = false;
        while (true) {
            i = 2;
            if (!it.hasNext()) {
                break;
            }
            final AnimationInfo next = it.next();
            if (next.isVisibilityUnchanged()) {
                next.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = next.getAnimation(context2);
                if (animation == null) {
                    next.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(next);
                    } else {
                        final SpecialEffectsController.Operation operation2 = next.getOperation();
                        Fragment fragment = operation2.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation2))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            next.completeSpecialEffect();
                        } else {
                            boolean z4 = operation2.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z4) {
                                list2.remove(operation2);
                            }
                            final View view2 = fragment.mView;
                            container.startViewTransition(view2);
                            final boolean z5 = z4;
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    container.endViewTransition(view2);
                                    if (z5) {
                                        operation2.getFinalState().applyState(view2);
                                    }
                                    next.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v(FragmentManager.TAG, "Animator from operation " + operation2 + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view2);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                StringBuilder sb = new StringBuilder("Animator from operation ");
                                operation = operation2;
                                sb.append(operation);
                                sb.append(" has started.");
                                Log.v(FragmentManager.TAG, sb.toString());
                            } else {
                                operation = operation2;
                            }
                            next.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has been canceled.");
                                    }
                                }
                            });
                            z3 = true;
                        }
                    }
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            final AnimationInfo animationInfo = (AnimationInfo) it2.next();
            final SpecialEffectsController.Operation operation3 = animationInfo.getOperation();
            Fragment fragment2 = operation3.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo.completeSpecialEffect();
            } else if (z3) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo.completeSpecialEffect();
            } else {
                final View view3 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo.getAnimation(context2))).animation);
                if (operation3.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view3.startAnimation(animation2);
                    animationInfo.completeSpecialEffect();
                    z2 = z3;
                    context = context2;
                    i2 = i;
                    view = view3;
                } else {
                    container.startViewTransition(view3);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view3);
                    z2 = z3;
                    context = context2;
                    view = view3;
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation3) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animation from operation " + operation3 + " has reached onAnimationStart.");
                            }
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation3) {
                            container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    container.endViewTransition(view3);
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animation from operation " + operation3 + " has ended.");
                            }
                        }
                    });
                    view.startAnimation(endViewTransitionAnimation);
                    i2 = 2;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Animation from operation " + operation3 + " has started.");
                    }
                }
                final View view4 = view;
                animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view4.clearAnimation();
                        container.endViewTransition(view4);
                        animationInfo.completeSpecialEffect();
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Animation from operation " + operation3 + " has been cancelled.");
                        }
                    }
                });
                i = i2;
                z3 = z2;
                context2 = context;
            }
        }
    }

    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> list, List<SpecialEffectsController.Operation> list2, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        String str;
        String str2;
        View view;
        String str3;
        Object obj;
        Object obj2;
        ArrayList<View> arrayList;
        ArrayList<View> arrayList2;
        SpecialEffectsController.Operation operation3;
        View view2;
        boolean z2;
        ArrayMap arrayMap;
        ArrayList<View> arrayList3;
        SpecialEffectsController.Operation operation4;
        HashMap hashMap;
        Rect rect;
        FragmentTransitionImpl fragmentTransitionImpl;
        int i;
        ArrayList<View> arrayList4;
        View view3;
        SharedElementCallback enterTransitionCallback;
        SharedElementCallback exitTransitionCallback;
        ArrayList<String> arrayList5;
        final Rect rect2;
        View view4;
        final View view5;
        String findKeyForValue;
        ArrayList<String> arrayList6;
        boolean z3 = z;
        SpecialEffectsController.Operation operation5 = operation;
        SpecialEffectsController.Operation operation6 = operation2;
        HashMap hashMap2 = new HashMap();
        final FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (TransitionInfo transitionInfo : list) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = handlingImpl;
                } else if (handlingImpl != null && fragmentTransitionImpl2 != handlingImpl) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            for (TransitionInfo transitionInfo2 : list) {
                hashMap2.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return hashMap2;
        }
        View view6 = new View(getContainer().getContext());
        Rect rect3 = new Rect();
        ArrayList<View> arrayList7 = new ArrayList<>();
        ArrayList<View> arrayList8 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        Iterator<TransitionInfo> it = list.iterator();
        boolean z4 = false;
        Object obj3 = null;
        View view7 = null;
        while (true) {
            boolean hasNext = it.hasNext();
            str = FragmentManager.TAG;
            if (!hasNext) {
                break;
            }
            TransitionInfo next = it.next();
            if (!next.hasSharedElementTransition() || operation5 == null || operation6 == null) {
                arrayMap = arrayMap2;
                arrayList3 = arrayList8;
                operation4 = operation5;
                hashMap = hashMap2;
                rect = rect3;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                i = 0;
                arrayList4 = arrayList7;
                view3 = view6;
                view7 = view7;
            } else {
                Object wrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(next.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                View view8 = view7;
                HashMap hashMap3 = hashMap2;
                int i2 = 0;
                while (i2 < sharedElementTargetNames.size()) {
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i2));
                    ArrayList<String> arrayList9 = sharedElementTargetNames;
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i2));
                    }
                    i2++;
                    sharedElementTargetNames = arrayList9;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                if (!z3) {
                    enterTransitionCallback = operation.getFragment().getExitTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getEnterTransitionCallback();
                } else {
                    enterTransitionCallback = operation.getFragment().getEnterTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getExitTransitionCallback();
                }
                int size = sharedElementSourceNames.size();
                View view9 = view6;
                int i3 = 0;
                while (i3 < size) {
                    arrayMap2.put(sharedElementSourceNames.get(i3), sharedElementTargetNames2.get(i3));
                    i3++;
                    size = size;
                    rect3 = rect3;
                }
                Rect rect4 = rect3;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, ">>> entering view names <<<");
                    for (Iterator<String> it2 = sharedElementTargetNames2.iterator(); it2.hasNext(); it2 = it2) {
                        Log.v(FragmentManager.TAG, "Name: " + it2.next());
                    }
                    Log.v(FragmentManager.TAG, ">>> exiting view names <<<");
                    for (Iterator<String> it3 = sharedElementSourceNames.iterator(); it3.hasNext(); it3 = it3) {
                        Log.v(FragmentManager.TAG, "Name: " + it3.next());
                    }
                }
                ArrayMap<String, View> arrayMap3 = new ArrayMap<>();
                findNamedViews(arrayMap3, operation.getFragment().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Executing exit callback for operation " + operation5);
                    }
                    enterTransitionCallback.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str4 = sharedElementSourceNames.get(size2);
                        View view10 = arrayMap3.get(str4);
                        if (view10 == null) {
                            arrayMap2.remove(str4);
                            arrayList6 = sharedElementSourceNames;
                        } else {
                            arrayList6 = sharedElementSourceNames;
                            if (!str4.equals(ViewCompat.getTransitionName(view10))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view10), (String) arrayMap2.remove(str4));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList6;
                    }
                    arrayList5 = sharedElementSourceNames;
                } else {
                    arrayList5 = sharedElementSourceNames;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                final ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                findNamedViews(arrayMap4, operation2.getFragment().mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (exitTransitionCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Executing enter callback for operation " + operation6);
                    }
                    exitTransitionCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap4);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str5 = sharedElementTargetNames2.get(size3);
                        View view11 = arrayMap4.get(str5);
                        if (view11 == null) {
                            String findKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap2, str5);
                            if (findKeyForValue2 != null) {
                                arrayMap2.remove(findKeyForValue2);
                            }
                        } else if (!str5.equals(ViewCompat.getTransitionName(view11)) && (findKeyForValue = FragmentTransition.findKeyForValue(arrayMap2, str5)) != null) {
                            arrayMap2.put(findKeyForValue, ViewCompat.getTransitionName(view11));
                        }
                    }
                } else {
                    FragmentTransition.retainValues(arrayMap2, arrayMap4);
                }
                retainMatchingViews(arrayMap3, arrayMap2.keySet());
                retainMatchingViews(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList7.clear();
                    arrayList8.clear();
                    arrayMap = arrayMap2;
                    arrayList3 = arrayList8;
                    operation4 = operation5;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view7 = view8;
                    hashMap = hashMap3;
                    rect = rect4;
                    obj3 = null;
                    i = 0;
                    arrayList4 = arrayList7;
                    view3 = view9;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z3, arrayMap3, true);
                    arrayMap = arrayMap2;
                    ArrayList<View> arrayList10 = arrayList8;
                    ArrayList<View> arrayList11 = arrayList7;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, arrayMap4, false);
                        }
                    });
                    arrayList11.addAll(arrayMap3.values());
                    if (arrayList5.isEmpty()) {
                        i = 0;
                        view7 = view8;
                    } else {
                        i = 0;
                        View view12 = arrayMap3.get(arrayList5.get(0));
                        fragmentTransitionImpl2.setEpicenter(wrapTransitionInSet, view12);
                        view7 = view12;
                    }
                    arrayList10.addAll(arrayMap4.values());
                    if (sharedElementTargetNames2.isEmpty() || (view5 = arrayMap4.get(sharedElementTargetNames2.get(i))) == null) {
                        rect2 = rect4;
                        view4 = view9;
                    } else {
                        rect2 = rect4;
                        OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                            @Override // java.lang.Runnable
                            public void run() {
                                fragmentTransitionImpl2.getBoundsOnScreen(view5, rect2);
                            }
                        });
                        view4 = view9;
                        z4 = true;
                    }
                    fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view4, arrayList11);
                    rect = rect2;
                    view3 = view4;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList10);
                    operation4 = operation;
                    arrayList4 = arrayList11;
                    hashMap = hashMap3;
                    hashMap.put(operation4, true);
                    arrayList3 = arrayList10;
                    operation6 = operation2;
                    hashMap.put(operation6, true);
                    obj3 = wrapTransitionInSet;
                }
            }
            z3 = z;
            rect3 = rect;
            view6 = view3;
            arrayList8 = arrayList3;
            arrayList7 = arrayList4;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
            hashMap2 = hashMap;
            operation5 = operation4;
            arrayMap2 = arrayMap;
        }
        View view13 = view7;
        ArrayMap arrayMap5 = arrayMap2;
        ArrayList<View> arrayList12 = arrayList8;
        SpecialEffectsController.Operation operation7 = operation5;
        HashMap hashMap4 = hashMap2;
        Rect rect5 = rect3;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        boolean z5 = false;
        boolean z6 = true;
        ArrayList<View> arrayList13 = arrayList7;
        View view14 = view6;
        ArrayList arrayList14 = new ArrayList();
        Object obj4 = null;
        Object obj5 = null;
        for (TransitionInfo transitionInfo3 : list) {
            if (transitionInfo3.isVisibilityUnchanged()) {
                hashMap4.put(transitionInfo3.getOperation(), Boolean.valueOf(z5));
                transitionInfo3.completeSpecialEffect();
                obj4 = obj4;
                z6 = true;
            } else {
                Object obj6 = obj4;
                Object cloneTransition = fragmentTransitionImpl3.cloneTransition(transitionInfo3.getTransition());
                SpecialEffectsController.Operation operation8 = transitionInfo3.getOperation();
                boolean z7 = (obj3 == null || !(operation8 == operation7 || operation8 == operation6)) ? z5 : true;
                if (cloneTransition == null) {
                    if (!z7) {
                        hashMap4.put(operation8, Boolean.valueOf(z5));
                        transitionInfo3.completeSpecialEffect();
                    }
                    view = view14;
                    str3 = str;
                    obj2 = obj5;
                    arrayList = arrayList12;
                    arrayList2 = arrayList13;
                    view2 = view13;
                    z2 = true;
                    obj = obj6;
                } else {
                    final ArrayList<View> arrayList15 = new ArrayList<>();
                    Object obj7 = obj5;
                    captureTransitioningViews(arrayList15, operation8.getFragment().mView);
                    if (z7) {
                        if (operation8 == operation7) {
                            arrayList15.removeAll(arrayList13);
                        } else {
                            arrayList15.removeAll(arrayList12);
                        }
                    }
                    if (arrayList15.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(cloneTransition, view14);
                        view = view14;
                        str3 = str;
                        operation3 = operation8;
                        arrayList = arrayList12;
                        arrayList2 = arrayList13;
                        obj2 = obj7;
                        obj = obj6;
                    } else {
                        fragmentTransitionImpl3.addTargets(cloneTransition, arrayList15);
                        view = view14;
                        str3 = str;
                        obj = obj6;
                        obj2 = obj7;
                        arrayList = arrayList12;
                        arrayList2 = arrayList13;
                        fragmentTransitionImpl3.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList15, null, null, null, null);
                        if (operation8.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation3 = operation8;
                            list2.remove(operation3);
                            ArrayList<View> arrayList16 = new ArrayList<>(arrayList15);
                            arrayList16.remove(operation3.getFragment().mView);
                            fragmentTransitionImpl3.scheduleHideFragmentView(cloneTransition, operation3.getFragment().mView, arrayList16);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList15, 4);
                                }
                            });
                        } else {
                            operation3 = operation8;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList14.addAll(arrayList15);
                        if (z4) {
                            fragmentTransitionImpl3.setEpicenter(cloneTransition, rect5);
                        }
                        view2 = view13;
                    } else {
                        view2 = view13;
                        fragmentTransitionImpl3.setEpicenter(cloneTransition, view2);
                    }
                    z2 = true;
                    hashMap4.put(operation3, true);
                    if (transitionInfo3.isOverlapAllowed()) {
                        obj2 = fragmentTransitionImpl3.mergeTransitionsTogether(obj2, cloneTransition, null);
                    } else {
                        obj = fragmentTransitionImpl3.mergeTransitionsTogether(obj, cloneTransition, null);
                    }
                }
                view13 = view2;
                obj5 = obj2;
                obj4 = obj;
                z6 = z2;
                view14 = view;
                str = str3;
                arrayList12 = arrayList;
                arrayList13 = arrayList2;
                z5 = false;
            }
        }
        String str6 = str;
        ArrayList<View> arrayList17 = arrayList12;
        ArrayList<View> arrayList18 = arrayList13;
        boolean z8 = z6;
        Object mergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj5, obj4, obj3);
        if (mergeTransitionsInSequence == null) {
            return hashMap4;
        }
        for (final TransitionInfo transitionInfo4 : list) {
            if (!transitionInfo4.isVisibilityUnchanged()) {
                Object transition = transitionInfo4.getTransition();
                final SpecialEffectsController.Operation operation9 = transitionInfo4.getOperation();
                boolean z9 = (obj3 == null || !(operation9 == operation7 || operation9 == operation6)) ? false : z8;
                if (transition == null && !z9) {
                    str2 = str6;
                } else if (!ViewCompat.isLaidOut(getContainer())) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        str2 = str6;
                        Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation9);
                    } else {
                        str2 = str6;
                    }
                    transitionInfo4.completeSpecialEffect();
                } else {
                    str2 = str6;
                    fragmentTransitionImpl3.setListenerForTransitionEnd(transitionInfo4.getOperation().getFragment(), mergeTransitionsInSequence, transitionInfo4.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                        @Override // java.lang.Runnable
                        public void run() {
                            transitionInfo4.completeSpecialEffect();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Transition for operation " + operation9 + "has completed");
                            }
                        }
                    });
                }
                str6 = str2;
            }
        }
        String str7 = str6;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return hashMap4;
        }
        FragmentTransition.setViewVisibility(arrayList14, 4);
        ArrayList<String> prepareSetNameOverridesReordered = fragmentTransitionImpl3.prepareSetNameOverridesReordered(arrayList17);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str7, ">>>>> Beginning transition <<<<<");
            Log.v(str7, ">>>>> SharedElementFirstOutViews <<<<<");
            Iterator<View> it4 = arrayList18.iterator();
            while (it4.hasNext()) {
                View next2 = it4.next();
                Log.v(str7, "View: " + next2 + " Name: " + ViewCompat.getTransitionName(next2));
            }
            Log.v(str7, ">>>>> SharedElementLastInViews <<<<<");
            Iterator<View> it5 = arrayList17.iterator();
            while (it5.hasNext()) {
                View next3 = it5.next();
                Log.v(str7, "View: " + next3 + " Name: " + ViewCompat.getTransitionName(next3));
            }
        }
        fragmentTransitionImpl3.beginDelayedTransition(getContainer(), mergeTransitionsInSequence);
        fragmentTransitionImpl3.setNameOverridesReordered(getContainer(), arrayList18, arrayList17, prepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList14, 0);
        fragmentTransitionImpl3.swapSharedElementTargets(obj3, arrayList18, arrayList17);
        return hashMap4;
    }

    void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                if (arrayList.contains(view)) {
                    return;
                }
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
            return;
        }
        if (arrayList.contains(view)) {
            return;
        }
        arrayList.add(view);
    }

    void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return from == finalState || !(from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mLoadedAnim = false;
            this.mIsPop = z;
        }

        FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object exitTransition;
            Object enterTransition;
            boolean allowEnterTransitionOverlap;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    enterTransition = operation.getFragment().getReenterTransition();
                } else {
                    enterTransition = operation.getFragment().getEnterTransition();
                }
                this.mTransition = enterTransition;
                if (z) {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = allowEnterTransitionOverlap;
            } else {
                if (z) {
                    exitTransition = operation.getFragment().getReturnTransition();
                } else {
                    exitTransition = operation.getFragment().getExitTransition();
                }
                this.mTransition = exitTransition;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        Object getTransition() {
            return this.mTransition;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
