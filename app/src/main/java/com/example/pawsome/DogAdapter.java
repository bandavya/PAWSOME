package com.example.pawsome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> implements Filterable {
    private Context dContext;
    private List<DogItem> dList;
    private List<DogItem> fList;
    private OnItemClickListener dListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        dListener = listener;
    }


    public DogAdapter(Context context, List<DogItem> DogList) {
        dContext = context;
        dList = DogList;
        fList = new ArrayList<>(DogList);

    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(dContext).inflate(R.layout.dog_item, parent, false);
    return new DogViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        DogItem currentItem = dList.get(position);
        String imageUrl = currentItem.getImageUrl();
        String BreedName = currentItem.getBreedName();

        holder.dTextViewBreed.setText(BreedName);
        Picasso.with(dContext).load(imageUrl).fit().centerInside().into(holder.dImageView);
    }

    @Override
    public int getItemCount() {
        return dList.size();
    }

    @Override
    public Filter getFilter() {
        return dfilter;
    }

    Filter dfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DogItem> filteredList = new ArrayList<>();
            if (constraint == null|| constraint.length() == 0){
                filteredList.addAll(fList);
            }else{
                for(DogItem dog: fList){
                    if(dog.getBreedName().toLowerCase().contains(constraint.toString().toLowerCase().trim())){
                        filteredList.add(dog);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;


                return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dList.clear();
            dList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class DogViewHolder extends RecyclerView.ViewHolder{

        public ImageView dImageView;
        public TextView dTextViewBreed;
        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            dImageView = itemView.findViewById(R.id.image_view);
            dTextViewBreed = itemView.findViewById(R.id.text_view_breed);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dListener!= null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            dListener.onItemClick(position);

                        }
                    }
                }
            });

        }
    }
}
