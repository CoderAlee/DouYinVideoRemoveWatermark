package org.alee.util.douyin.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import org.alee.dokodemo.door.annotation.Node;
import org.alee.remove.weatermark.UrlHelper;
import org.alee.util.douyin.R;
import org.alee.util.douyin.base.BaseFragment;
import org.alee.util.douyin.widget.ProgressDialogBuilder;
import org.alee.util.douyin.widget.TipsToast;

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
@Node
public final class MainFragment extends BaseFragment {
    private static final String BUTTON_PARSE_VIDEO = "解析视频";
    private String mShearBoardContent;
    private EditText mContentView;
    private QMUIAlphaButton mPreviewView;
    private VideoView mVideoPlayer;
    private QMUIDialog mProgressDialog;
    private final QMUIProgressBar.OnProgressChangeListener mProgressChangeListener = new QMUIProgressBar.OnProgressChangeListener() {
        @Override
        public void onProgressChange(QMUIProgressBar progressBar, int currentValue, int maxValue) {
            if (currentValue == maxValue) {
                mProgressDialog.dismiss();
            }
        }
    };
    private String mVideoUrl;
    private final DialogInterface.OnDismissListener mDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            if (!TextUtils.isEmpty(mVideoUrl)) {
                mPreviewView.setText("保存视频");
                mVideoPlayer.setUrl(mVideoUrl);
                mVideoPlayer.start();
                return;
            }
            mPreviewView.setText(BUTTON_PARSE_VIDEO);
            TipsToast.getInstance().showTips(mContext, "去除水印失败!");
        }
    };
    private final DialogInterface.OnShowListener mShowListener = new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialog) {
            mVideoUrl = "";
            String inputContent = mContentView.getText().toString().trim();
            addVideoTitle(inputContent);
            addVideoUrl(inputContent);
        }
    };


    private void addVideoTitle(String inputContent) {
        Observable.just(inputContent)
                .subscribeOn(Schedulers.io())
                .map(s -> UrlHelper.getInstance().resolveTheTitle(s)).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    StandardVideoController controller = new StandardVideoController(mContext);
                    controller.addDefaultControlComponent(s, false);
                    mVideoPlayer.setVideoController(controller);
                });
    }

    private void addVideoUrl(String inputContent) {
        Observable.just(inputContent)
                .subscribeOn(Schedulers.io())
                .map(s -> {
                    String url = "";
                    try {
                        url = UrlHelper.getInstance().resolveTheUrl(s);
                    } catch (Throwable ignored) {
                    }
                    return url;
                }).filter(s -> !TextUtils.isEmpty(s)).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> mVideoUrl = s);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void getViews() {
        mContentView = findView(R.id.input_content);
        mPreviewView = findView(R.id.btn_preview);
        mVideoPlayer = findView(R.id.vv_player);
    }

    @Override
    protected void setListeners() {
        setClick(mPreviewView);
    }

    @Override
    protected void setViewsValue() {
        mContentView.setHint("请输入要去除水印的抖音视频连接");
        if (!TextUtils.isEmpty(mShearBoardContent)) {
            mContentView.setText(mShearBoardContent);
        }
        mPreviewView.setText(BUTTON_PARSE_VIDEO);
    }

    @Override
    protected void initData() {
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = cm.getPrimaryClip();
        ClipData.Item item = data.getItemAt(0);
        mShearBoardContent = item.getText().toString();
    }

    @Override
    protected void processClick(View v) {
        switch (mPreviewView.getText().toString()) {
            case BUTTON_PARSE_VIDEO:
                parseTheVideo();
                break;
            default:
                break;
        }
    }

    private void parseTheVideo() {
        String inputContent = mContentView.getText().toString().trim();
        if (TextUtils.isEmpty(inputContent)) {
            TipsToast.getInstance().showTips(mContext, "请输入要去除水印的抖音链接!");
            return;
        }
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialogBuilder(mContext)
                    .setProgressBarListener(mProgressChangeListener)
                    .setTitle("视频解析中")
                    .setCanceledOnTouchOutside(false)
                    .addAction("取消", (dialog, index) -> mProgressDialog.dismiss()).create();
            mProgressDialog.setOnDismissListener(mDismissListener);
            mProgressDialog.setOnShowListener(mShowListener);
        }
        mProgressDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mVideoPlayer.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mVideoPlayer.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVideoPlayer.release();
    }

}
