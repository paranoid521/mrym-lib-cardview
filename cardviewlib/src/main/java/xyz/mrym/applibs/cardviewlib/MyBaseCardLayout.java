package xyz.mrym.applibs.cardviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class MyBaseCardLayout extends LinearLayout {

    public View view;
    public CardView daoyin_card_view;
    public ImageView daoyin_cover_view;
    public TextView daoyin_name_view;
    public TextView daoyin_level_view;
    public TextView daoyin_time_view;
    public TextView daoyin_hot_view;
    public TextView daoyin_level_title_view;
    public TextView daoyin_time_title_view;
    public TextView daoyin_hot_title_view;

    public DaoyinCardClickListener daoyinCardClickListener;

    public void setDaoyinCardClickListener(DaoyinCardClickListener daoyinCardClickListener) {
        this.daoyinCardClickListener = daoyinCardClickListener;
    }

    //引用控件时传入的属性值，例如,app:daoyin_name="daoyin"
    private int background_color;
    private String text;
    private int daoyin_cover;
    private String daoyin_name;
    private String daoyin_level;
    private String daoyin_time;
    private String daoyin_hot;
    private String pager_daoyin;
    private String taskName;

    @SuppressWarnings("ResourceType")
    @androidx.annotation.IdRes
    private int daoyin_id;

    public MyBaseCardLayout(Context context) {
        super(context);
    }

    public MyBaseCardLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyBaseCardLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyBaseCardLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, AttributeSet attrs) {
        //从attrs.xml那传来的一组值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.my_base_card_view);
        //得到attrs.xml中styleable.my_base_card_view对应属性的background_color值
        background_color = typedArray.getColor(R.styleable.my_base_card_view_background_color,
                getResources().getColor(R.color.transparent));
        text = typedArray.getText(R.styleable.my_base_card_view_text).toString();

        daoyin_cover = typedArray.getResourceId(R.styleable.my_base_card_view_daoyin_image, 0);
        daoyin_id = typedArray.getResourceId(R.styleable.my_base_card_view_daoyin_id, 12138);
        daoyin_name = typedArray.getText(R.styleable.my_base_card_view_daoyin_name).toString();
        daoyin_level = typedArray.getText(R.styleable.my_base_card_view_daoyin_level).toString();
        daoyin_time = typedArray.getText(R.styleable.my_base_card_view_daoyin_time).toString();
        daoyin_hot = typedArray.getText(R.styleable.my_base_card_view_daoyin_hot).toString();
        pager_daoyin = typedArray.getText(R.styleable.my_base_card_view_pager_daoyin).toString();
        taskName = typedArray.getText(R.styleable.my_base_card_view_taskName).toString();

        view = LayoutInflater.from(getContext()).inflate(R.layout.item_daoyin_base, this);
        daoyin_card_view = (CardView) view.findViewById(R.id.daoyin_card);
        daoyin_cover_view = (ImageView) view.findViewById(R.id.daoyin_cover);
        daoyin_name_view = (TextView) view.findViewById(R.id.daoyin_name);
        daoyin_level_view = (TextView) view.findViewById(R.id.daoyin_level);
        daoyin_time_view = (TextView) view.findViewById(R.id.daoyin_time);
        daoyin_hot_view = (TextView) view.findViewById(R.id.daoyin_hot);
        //title
        daoyin_level_title_view = (TextView) view.findViewById(R.id.daoyin_level_title);
        daoyin_time_title_view = (TextView) view.findViewById(R.id.daoyin_time_title);
        daoyin_hot_title_view = (TextView) view.findViewById(R.id.daoyin_hot_title);
        //生成控件ID
//        view.setId(daoyin_id);

        //Uri coverUri = Uri.parse("file:///android_asset/" + "qq.png");
        initData(daoyin_cover, daoyin_name, daoyin_level, daoyin_time, daoyin_hot);
        initEvent(pager_daoyin, taskName, daoyin_name);
        if(text.equals("series")) {
            daoyin_level_title_view.setVisibility(View.GONE);
            daoyin_time_title_view.setVisibility(View.GONE);
            daoyin_hot_title_view.setVisibility(View.GONE);
        }
    }

    public void initData(int daoyin_cover, String daoyin_name, String daoyin_level, String daoyin_time, String daoyin_hot){
        this.daoyin_cover_view.setBackgroundResource(daoyin_cover);
        this.daoyin_name_view.setText(daoyin_name);
        this.daoyin_level_view.setText(daoyin_level);
        this.daoyin_time_view.setText(daoyin_time);
        this.daoyin_hot_view.setText(daoyin_hot);
    }

    //如何设置自定义控件的点击监听器
    public void initEvent(final String pager_daoyin, final String taskName, final String daoyinName){
        daoyin_card_view.setOnClickListener(v -> {
            if(daoyinCardClickListener != null) {
                daoyinCardClickListener.cardOnClick(pager_daoyin, taskName, daoyin_name);
            } else {
                System.out.println("==============daoyinCardClickListener为空=============");
            }
        });
    }

    public interface DaoyinCardClickListener {
        void cardOnClick(String pager_daoyin, String taskName, String daoyin_name);
    }

}
