package com.rijndael.kraloyun;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<FlowerViewHolder> {

    private Context mContext;
    private List<FlowerData> mFlowerList;

    MyAdapter(Context mContext, List<FlowerData> mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new FlowerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, final int position) {

        Glide.with(mContext)
                .load(mFlowerList.get(position).getFlowerDescription())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
               // .centerCrop()
                .override(450, 650)
                .skipMemoryCache(true)
                .into(holder.mImage);

       // holder.mImage.setImageDrawable(mFlowerList.get(position).getFlowerImage());
        holder.mTitle.setText(mFlowerList.get(position).getFlowerName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext,mContext.getString(R.string.loadinggame),Toast.LENGTH_LONG).show();



                Intent intent = new Intent(holder.itemView.getContext(), GameView.class);
               intent.putExtra("gameURL",FirebaseDatabaseLoad.linkler[position]);
                intent.putExtra("gameMod",FirebaseDatabaseLoad.oyunmod[position]);
               holder.itemView.getContext().startActivity(intent);

               // Intent mIntent = new Intent(mContext, DetailActivity.class);
               // mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getFlowerName());
               // mIntent.putExtra("Description", mFlowerList.get(holder.getAdapterPosition()).getFlowerDescription());
               // mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getFlowerImage());
               // mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class FlowerViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    FlowerViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}