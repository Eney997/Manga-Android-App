package com.example.manga;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPageAdapted extends PagerAdapter {

    Context context;

    public SlideViewPageAdapted(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen,container,false);

        ImageView logo = view.findViewById(R.id.slideImage);
        ImageView ind1 = view.findViewById(R.id.ind1);
        ImageView ind2 = view.findViewById(R.id.ind2);
        ImageView ind3 = view.findViewById(R.id.ind3);
        ImageView arrowLeft = view.findViewById(R.id.leftArrow);
        ImageView arrowRight = view.findViewById(R.id.rightArrow);

        TextView title = view.findViewById(R.id.slideTextOne);
        TextView titleTwo = view.findViewById(R.id.slideTextTwo);

        Button btnGetStarted = view.findViewById(R.id.slideBtn);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideActivity.viewPager.setCurrentItem(position+1);
            }
        });

        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideActivity.viewPager.setCurrentItem(position-1);
            }
        });


        switch(position)
        {
            case 0:
                logo.setImageResource(R.drawable.luffy);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText("About App");
                titleTwo.setText("Discover and read popular mangas in one place! Stay updated, explore genres,and enjoy a seamless reading experience. Your manga journey starts here!");
                arrowLeft.setVisibility(View.GONE);
            break;

            case 1:
                logo.setImageResource(R.drawable.ichigo);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText("Manga");
                titleTwo.setText("Manga is said to have been born in 1814. Back then, Hokusai Katsuyoshi sketched a series of scenes from daily life… whimsical stories: manga in Japanese. In 1902, Kittiwakes Rakuten published the first modern manga with four boxes per page and typed texts.");
                break;

            case 2:
                logo.setImageResource(R.drawable.naruto);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                title.setText("Don't miss it");
                titleTwo.setText("To access the content in our app, users must create an account. Registration is quick and easy, allowing you to enjoy your favorite manga seamlessly. Sign up now to get started!");
                arrowRight.setVisibility(View.GONE);
                break;
        }


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
