package org.alee.util.douyin.widget;

import android.content.Context;
import android.content.DialogInterface;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/9/28
 * @description: xxxx
 *
 *********************************************************/
public class TipsToast {

    private QMUITipDialog mTipDialog;

    private TipsToast() {

    }

    /**
     * 获取单例对象
     *
     * @return {@link TipsToast}
     */
    public static TipsToast getInstance() {
        return TipsToastHolder.INSTANCE;
    }

    public void showTips(Context context, String content) {
        if (null != mTipDialog) {
            mTipDialog.dismiss();
        }
        mTipDialog = new QMUITipDialog.Builder(context)
                .setTipWord(content)
                .create();
        mTipDialog.setCancelable(true);
        mTipDialog.setOnDismissListener(mTipsDismissListener);
        mTipDialog.setOnShowListener(mTipsShowListener);
        mTipDialog.show();
    }

    private final DialogInterface.OnDismissListener  mTipsDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {

        }
    };

    private final DialogInterface.OnShowListener mTipsShowListener = dialog -> Observable.just(dialog)
            .subscribeOn(Schedulers.io())
            .delay(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .filter(dialogInterface -> null!=dialogInterface)
            .subscribe(dialogInterface -> dialogInterface.dismiss());
    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class TipsToastHolder {
        private static TipsToast INSTANCE = new TipsToast();
    }
}
