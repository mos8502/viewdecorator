package hu.nemi.appcompat.view;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;

/**
 * Utility class for installing a ViewDecorator with an activity
 * */
public class ViewDecoratorInstaller {
    private static final String TAG = "ViewDecoratorInstaller";

    private ViewDecorator viewDecorator;

    /**
     * Constructor
     *
     * @param viewDecorator view decorator to be used by the helper. Can't be null
     * */
    public ViewDecoratorInstaller(@NonNull ViewDecorator viewDecorator) {
        this.viewDecorator = viewDecorator;
    }

    /**
     * Installs the helper to an activity. Once the helper is installed to an activity it can't be installed to another activty
     *
     * @param activity activity to install the helper to
     * @return true if installation was successful, false otherwise
     * @throws IllegalStateException if helper is already install with the activity
     * */
    @UiThread
    public boolean install(@NonNull AppCompatActivity activity) {
        AppCompatDelegate delegate = activity.getDelegate();
        if(delegate == null) {
            throw new IllegalStateException("activity has no delegate");
        }

        LayoutInflater layoutInflater = activity.getLayoutInflater();

        if(layoutInflater.getFactory() == null) {
            LayoutInflaterFactory layoutInflaterFactory = new DecoratingLayoutInflaterFactory(delegate, viewDecorator);
            LayoutInflaterCompat.setFactory(layoutInflater, layoutInflaterFactory);
            return true;
        }

        Log.w(TAG, "view decorator can't be installed, layout infalter already has view factory installed");

        return false;
    }
}
