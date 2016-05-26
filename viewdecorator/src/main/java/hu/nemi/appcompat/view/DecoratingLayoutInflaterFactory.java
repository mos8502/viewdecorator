package hu.nemi.appcompat.view;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

class DecoratingLayoutInflaterFactory implements LayoutInflaterFactory {
    private ViewDecorator viewDecorator;
    private ViewFactory viewFactory;

    public DecoratingLayoutInflaterFactory(LayoutInflater layoutInflater, ViewDecorator viewDecorator) {
        this.viewDecorator = viewDecorator;
        this.viewFactory = newViewFactory(layoutInflater);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = viewFactory.createView(parent, name, attrs);
        if(view != null) {
            viewDecorator.decorate(parent, view, context, attrs);
        }
        return view;
    }

    private static ViewFactory newViewFactory(LayoutInflater layoutInflater) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return new ViewFactoryBase(layoutInflater);
        }

        return new ViewFactoryV11(layoutInflater);
    }
}
