package hu.nemi.view.demo;

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by nemi on 17/02/2016.
 */
public enum Font {
    ANONYMOUS_PRO_REGULAR("fonts/AnonymousPro-Regular.ttf"),
    AVRO_REGULAR("fonts/Arvo-Regular.ttf"),
    CHIVO_REGULAR("fonts/Chivo-Regular.ttf"),
    VARELA_ROUND_REGULAR("fonts/VarelaRound-Regular.ttf");
    private final String path;

    Font(String path) {
        this.path = path;
    }

    public Typeface getTypeFace(AssetManager assetManager) {
        return Typeface.createFromAsset(assetManager, path);
    }
}
