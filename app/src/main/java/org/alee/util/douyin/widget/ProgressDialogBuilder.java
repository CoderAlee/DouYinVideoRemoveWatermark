package org.alee.util.douyin.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogBuilder;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogView;

import org.alee.util.douyin.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.qmuiteam.qmui.widget.QMUIProgressBar.TYPE_CIRCLE;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public final class ProgressDialogBuilder extends QMUIDialogBuilder {
    private QMUIProgressBar mProgressBar;
    private QMUIProgressBar.OnProgressChangeListener mProgressChangeListener;

    public ProgressDialogBuilder(Context context) {
        super(context);
    }

    @Nullable
    @Override
    protected View onCreateContent(@NonNull QMUIDialog dialog, @NonNull QMUIDialogView parent, @NonNull Context context) {
        View content = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog_content, parent, false);
        mProgressBar = content.findViewById(R.id.progress_bar);
        mProgressBar.setType(TYPE_CIRCLE);
        mProgressBar.setMaxValue(100);
        mProgressBar.setOnProgressChangeListener(mProgressChangeListener);
        Observable.intervalRange(1, 100, 500L, 200L, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mProgressBar.setProgress(aLong.intValue()));
        return content;
    }

    public ProgressDialogBuilder setProgressBarListener(QMUIProgressBar.OnProgressChangeListener listener) {
        mProgressChangeListener = listener;
        return this;
    }

}
