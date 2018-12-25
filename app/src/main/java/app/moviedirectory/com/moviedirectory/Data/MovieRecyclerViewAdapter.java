package app.moviedirectory.com.moviedirectory.Data;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.moviedirectory.com.moviedirectory.Activities.MovieDetailActivity;
import app.moviedirectory.com.moviedirectory.Model.Movie;
import app.moviedirectory.com.moviedirectory.R;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);


        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        String posterLink = movie.getPoster();

        holder.movieName.setText(movie.getTitle());
        holder.movieCategory.setText(movie.getMovieType());

        Picasso.with(context).
                load(posterLink).
                placeholder(android.R.drawable.ic_btn_speak_now).
                into(holder.poster);

        holder.movieReleaseDate.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView poster;
        TextView movieName;
        TextView movieReleaseDate;
        TextView movieCategory;

        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            context = ctx;

            poster = (ImageView) itemView.findViewById(R.id.imageViewM);
            movieName = (TextView) itemView.findViewById(R.id.textViewName);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.textViewRelease);
            movieCategory = (TextView) itemView.findViewById(R.id.textViewCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Movie movie = movieList.get(getAdapterPosition());

                    Intent intent = new Intent(context, MovieDetailActivity.class);

                    intent.putExtra("movie", movie);

                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}
