package com.example.nearbuy;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String productId = horizontalProductScrollModelList.get(position).getProductID();
        String resource = horizontalProductScrollModelList.get(position).getProductImage();
        String title = horizontalProductScrollModelList.get(position).getProductTitle();
        String description = horizontalProductScrollModelList.get(position).getProductDescription();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();
        String url = horizontalProductScrollModelList.get(position).getProductUrl();


        viewHolder.setData(productId,resource,title,description,price,url);


    }

    @Override
    public int getItemCount() {
        if (horizontalProductScrollModelList.size() > 8){
            return 8;
        }else{
            return horizontalProductScrollModelList.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;
        private TextView productUrl;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);
            productUrl = itemView.findViewById(R.id.h_s_product_url);


        }

        private void setData(final String productId, String resource, String title, String description, String price, final String url) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(productImage);
            productUrl.setText(url);
            productPrice.setText("Rs. "+price+"/-");
            productDescription.setText(description);
            productTitle.setText(title);

            if (!title.equals("")){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        productDetailsIntent.putExtra("PRODUCT_ID",productId );
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }
        }
    }
}
