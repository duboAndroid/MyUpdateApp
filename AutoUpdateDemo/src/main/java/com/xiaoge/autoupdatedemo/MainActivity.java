package com.xiaoge.autoupdatedemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 检查更新
        checkUpdate();
    }

    /**
     * 检查更新方法
     * // 1.在子线程中联网请求服务器
     * // 2.对比本地的版本号和服务器的版本号，如果服务器有新版本，弹出对话框询问用户是否下载更新
     * // 3.如果用户同意下载更新文件，开始下载更新，并弹出下载更新的进度对话框
     * // 4.下载完成之后，打开安装文件，安装应用
     */
    private void checkUpdate() {

        // 1.在子线程中联网请求服务器
        // 2.对比本地的版本号和服务器的版本号，如果服务器有新版本，弹出对话框询问用户是否下载更新
        // 3.如果用户同意下载更新文件，开始下载更新，并弹出下载更新的进度对话框
        // 4.下载完成之后，打开安装文件，安装应用
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = "";
                try {
                    JSONObject object = new JSONObject(json);
                    // 解析服务器返回的json字符串 比较版本大小  获取相关的信息
                    // TODO
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(true){//假设服务器的版本大于当前版本
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 创建弹窗
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("发现新版本");
                            builder.setMessage("更新介绍：\r\n1:xxxxxx\r\n2:xxxxxx\r\n3:xxxxxx");

                            // 点击更新按钮的回调
                            builder.setPositiveButton("暂不更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            // 点击取消更新的回调
                            builder.setNegativeButton("立即更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    Toast.makeText(MainActivity.this,"更新中……",Toast.LENGTH_SHORT).show();
                                    // 通知栏显示下载进度
                                    Intent updateIntent =new Intent(MainActivity.this, UpdateService.class);
                                    updateIntent.putExtra("titleId",R.string.app_name);
                                    startService(updateIntent);

                                    // 下载完成之后 自动打开下载的apk文件 开始安装
                                }
                            });

                            // 显示对话框
                            builder.create().show();
                        }
                    });
                }
            }
        }).start();

    }
}
