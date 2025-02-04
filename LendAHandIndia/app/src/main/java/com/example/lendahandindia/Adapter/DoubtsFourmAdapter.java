package com.example.lendahandindia.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendahandindia.DoubtsFourmActivity;
import com.example.lendahandindia.Modal.DoubtsFourmModal;
import com.example.lendahandindia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Comment;

import java.util.List;

public class DoubtsFourmAdapter extends RecyclerView.Adapter<DoubtsFourmAdapter.ViewHolder> {

    Context mContext;
    List<DoubtsFourmModal> mDoubts;

    FirebaseUser fUser;
    public DoubtsFourmAdapter(Context mContext, List<DoubtsFourmModal> mDoubts) {
        this.mContext = mContext;
        this.mDoubts = mDoubts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.doubt_item,parent,false);
        return new DoubtsFourmAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        fUser= FirebaseAuth.getInstance().getCurrentUser();

        final DoubtsFourmModal doubt=mDoubts.get(position);

        String email=fUser.getEmail();

        holder.name.setText(doubt.getName());

        holder.doubts.setText(doubt.getDoubts());

        if(doubt.getAnswer()!=null)
        {
            holder.answer.setText(doubt.getAnswer());
            holder.answer.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return mDoubts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView doubts;
        public TextView answer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            doubts=itemView.findViewById(R.id.doubt);
            answer=itemView.findViewById(R.id.answer);
        }
    }


}
