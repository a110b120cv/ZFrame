/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-21 下午12:01
 * *********************************************************
 */

package com.fosung.frame.http.response;

import android.app.ProgressDialog;

import com.fosung.frame.app.BaseFrameActivity;
import com.fosung.frame.http.okhttp.callback.FileCallBack;

import okhttp3.Request;


/**
 * 返回File对象
 */
public abstract class FileResponse extends FileCallBack {

    private ProgressDialog proBar;        //请求过程中的进度条
    private String         barMsg;        //进度条上的文字

    public FileResponse(String destPath) {
        this(destPath, null);
    }

    /**
     * @param barActy 进度条Atvicity实体
     */
    public FileResponse(String destPath, BaseFrameActivity barActy) {
        this(destPath, barActy, null);
    }

    /**
     * @param barActy 进度条Atvicity实体
     * @param barMsg  进度条上 显示的信息
     */
    public FileResponse(String destPath, BaseFrameActivity barActy, String barMsg) {
        super(destPath);
        if (barActy != null) {
            if (barActy.getProgressDialog() != null) {
                proBar = new ProgressDialog(barActy);
            } else {
                proBar = new ProgressDialog(barActy);
            }
            this.barMsg = barMsg;
        }
    }

    @Override
    public void onStart(Request request) {
        if (proBar != null) {
            proBar.show();
            proBar.setMessage(barMsg);
        }
    }

    @Override
    public void onFinished() {
        if (proBar != null) {
            proBar.dismiss();
            barMsg = null;
        }
    }

    public void setBarMsg(String barMsg) {
        if (proBar != null) {
            if (proBar.isShowing()) {
                proBar.setMessage(barMsg);
            } else {
                this.barMsg = barMsg;
            }
        }
    }
}
