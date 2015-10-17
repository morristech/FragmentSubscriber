package com.meschke.fragmentsubscriber;

import android.app.Fragment;
import android.util.Log;

import rx.Subscriber;

/**
 * Created by scott.meschke on 8/28/2015.
 * An extension of {@link Subscriber} which has callbacks dependant on the fragment
 * state.
 */
public abstract class FragmentSubscriber<T> extends Subscriber<T> {

    private static final String TAG = "FragmentSubscriber";
    protected Fragment mFragment;

    protected FragmentSubscriber() {
        super();
        mFragment = getFragment();
    }

    @Override
    public final void onCompleted() {
        if (mFragment != null && mFragment.isVisible()) {
            onCompletedFragmentVisible();
        } else {
            onCompletedFragmentDetached();
        }
    }

    protected void onCompletedFragmentDetached() {

    }

    protected void onCompletedFragmentVisible() {

    }

    @Override
    public final void onError(Throwable e) {
        if (mFragment != null && mFragment.isVisible()) {
            onErrorFragmentVisible(e);
        } else {
            onErrorFragmentDetached(e);
        }
    }

    protected void onErrorFragmentVisible(Throwable e) {
        Log.e(TAG, "onErrorFragmentVisible: " + e);
    }

    protected void onErrorFragmentDetached(Throwable e) {
        Log.e(TAG, "onErrorFragmentDetached: " + e);
    }

    @Override
    public final void onNext(T next) {
        if (mFragment != null && mFragment.isVisible()) {
            onNextFragmentVisible(next);
        } else {
            onNextFragmentDetached(next);
        }
    }

    protected void onNextFragmentVisible(T next) {

    }

    protected void onNextFragmentDetached(T next) {

    }

    protected abstract Fragment getFragment();
}
