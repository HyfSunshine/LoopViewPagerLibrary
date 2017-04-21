package com.hyf.loopvplibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by HeYongFeng on 2017/4/20.
 */

public class LoopBanner {
    public static void showToast(Context context,String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
