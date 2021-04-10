/**
 * created by ray.  
 * on 21-4-10 上午9:06.
 */


package com.awinic.atouchdect.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class AutoSuitUtil {
    private final int mWidthDp;
    private final Context mContext;
    private final float mAspectRatio;
    public AutoSuitUtil(Context context, int widthDp, int heightDp, float bg_ratio) {
        mWidthDp = widthDp;
        mAspectRatio = bg_ratio;
        mContext = context;
    }
    public void ModifySizeDynamic(View v){
        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.width = DensityUtil.dip2px(mContext, mWidthDp);
        params.height = DensityUtil.dip2px(mContext, mWidthDp / mAspectRatio);
    }
 /**
  *
  * @brief 自动调整控件大小
  * @param ratio: 图片的原始宽高比
  *        duty： 图片占一行的多少，若一行显示两张图片就是0.5
  *        empty：一行中有多少空白，也就是margin的总宽度
  * @return
  */
    public static void AutoModifySize(Context c, float ratio, float duty, int empty, DisplayMetrics dm, View... v){
        AutoSuitUtil ast = new AutoSuitUtil(c, (int)(duty * (DensityUtil.px2dip(c, dm.widthPixels) - empty)),
                                            DensityUtil.px2dip(c, dm.heightPixels), ratio);
        for (View view : v) {
            ast.ModifySizeDynamic(view);
        }
    }
}
