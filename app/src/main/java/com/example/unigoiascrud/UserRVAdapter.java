package com.example.unigoiascrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class UserRVAdapter extends ListAdapter<UserModal,
        UserRVAdapter.ViewHolder> {
    private OnItemClickListener listener;
    UserRVAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<UserModal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserModal>() {
                @Override
                public boolean areItemsTheSame(UserModal oldItem, UserModal
                        newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(UserModal oldItem, UserModal
                        newItem) {
                    return oldItem.getUserName().equals(newItem.getUserName()) &&
                            oldItem.getCourseDescription().equals(newItem.getCourseDescription()) &&
                            oldItem.getEstiloCurso().equals(newItem.getEstiloCurso());
                }
            };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_rv_item, parent, false);
        return new ViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModal model = getUserAt(position);
        holder.userNameTV.setText(model.getUserName());
        holder.userDescTV.setText(model.getCourseDescription());
        holder.estiloCursoTV.setText(model.getEstiloCurso());
    }
    public UserModal getUserAt(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTV, userDescTV, estiloCursoTV;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTV = itemView.findViewById(R.id.idTVNomeUsuario);
            userDescTV = itemView.findViewById(R.id.idTVDescricaoCurso);
            estiloCursoTV = itemView.findViewById(R.id.idTVEstiloCurso);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position !=
                            RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(UserModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}