package hu.nemi.view.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import hu.nemi.appcompat.view.ViewDecorator;
import hu.nemi.appcompat.view.ViewDecoratorInstaller;

public class DemoActivity extends AppCompatActivity implements ViewDecorator {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        installViewDecorator();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    void installViewDecorator() {
        ViewDecoratorInstaller installer = new ViewDecoratorInstaller(this);
        installer.install(this);
    }

    @Override
    public void decorate(View parent, View view, Context context, AttributeSet attrs) {
        if(view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTypeface(getTypeFace(context, attrs));
        }
    }

    Typeface getTypeFace(Context context, AttributeSet attrs) {
        Typeface typeFace = null;
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Font, 0, 0);
        int ordinal = ta.getInt(R.styleable.Font_font, -1);
        if(ordinal != -1) {
            Font font = Font.values()[ordinal];
            typeFace = font.getTypeFace(getAssets());
        }
        ta.recycle();
        return typeFace;
    }

}
