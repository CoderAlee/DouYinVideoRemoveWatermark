package org.alee.util.douyin.widget.player;

import android.content.Context;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.alee.util.douyin.R;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/23
 * @description: xxxx
 *
 *********************************************************/
public final class PavedPlayer extends StandardGSYVideoPlayer {
    public PavedPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public PavedPlayer(Context context) {
        super(context);
    }

    public PavedPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_paved_player;
    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        mChangePosition = false;
    }

    @Override
    protected void touchDoubleUp() {
        // ignored
    }

}
