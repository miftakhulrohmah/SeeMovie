package id.sch.smktelkom_mlg.privateassignment.xirpl119.seemovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by A455L on 14/05/2017.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<HomeListItem> homeListItems;
    private Context context;

    public HomeAdapter(List<HomeListItem> homeListItems, Context context) {
        this.homeListItems = homeListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, final int position) {
        final HomeListItem homeListItem = homeListItems.get(position);

        holder.textViewTitle.setText(homeListItem.getTitle());
        holder.textViewContent.setText(homeListItem.getContent());

        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w500"+homeListItem.getImageUrl())
                .into(holder.imageViewOtOf);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, homeListItem.getTitle() + " is opened", Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, DetailActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
       });
    }

    @Override
    public int getItemCount() {

        return homeListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewContent;
        public ImageView imageViewOtOf;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView){
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
            imageViewOtOf = (ImageView) itemView.findViewById(R.id.imageViewOtOf);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }
}

