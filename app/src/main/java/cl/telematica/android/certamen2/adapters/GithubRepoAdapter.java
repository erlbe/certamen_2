package cl.telematica.android.certamen2.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.models.GithubRepo;

/**
 * Created by Erlend on 30.09.2016.
 */

public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.ViewHolder>{

    private List<GithubRepo> mRepoList;
    private String username;

    public GithubRepoAdapter(List<GithubRepo> repoList, String username){
        this.mRepoList = repoList;
        this.username = username;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_row, parent, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/"+username+"/"));
                v.getContext().startActivity(browserIntent);
            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubRepo githubRepo = mRepoList.get(position);

        holder.mTitleView.setText(githubRepo.getTitle());
        holder.mDescriptionView.setText(githubRepo.getDescription());
        holder.mUpdatedAtView.setText(githubRepo.getUpdatedAt());
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitleView;
        public TextView mDescriptionView;
        public TextView mUpdatedAtView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleView = (TextView) itemView.findViewById(R.id.title);
            mDescriptionView = (TextView) itemView.findViewById(R.id.description);
            mUpdatedAtView = (TextView) itemView.findViewById(R.id.updatedAt);
        }
    }
}
