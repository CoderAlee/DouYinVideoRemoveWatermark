package androidx.appcompat.widget;

import android.widget.ImageButton;

import org.alee.reflex.ReflexClass;
import org.alee.reflex.ReflexObject;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/22
 * @description: xxxx
 *
 *********************************************************/
public final class ToolbarMapping {
    public static ReflexObject<ImageButton> mNavButtonView;

    static {
        ReflexClass.load(ToolbarMapping.class, Toolbar.class);
    }
}
