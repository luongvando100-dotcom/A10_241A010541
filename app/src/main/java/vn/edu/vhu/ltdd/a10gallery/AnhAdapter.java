package vn.edu.vhu.ltdd.a10gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnhAdapter extends RecyclerView.Adapter<AnhAdapter.AnhViewHolder> {

    public interface OnAnhClickListener {
        void onAnhClick(int viTri);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int viTri);
    }

    static class AnhViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgAnh;
        final TextView tvChuThich;
        final Button btnXoa;

        AnhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.imgAnh);
            tvChuThich = itemView.findViewById(R.id.tvChuThich);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }

    private final List<Integer> anhList;
    private final List<String> chuThich;
    private final OnAnhClickListener anhClickListener;
    private final OnDeleteClickListener deleteClickListener;

    public AnhAdapter(List<Integer> anhList, List<String> chuThich,
                      OnAnhClickListener anhClickListener,
                      OnDeleteClickListener deleteClickListener) {
        this.anhList = anhList;
        this.chuThich = chuThich;
        this.anhClickListener = anhClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public AnhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anh, parent, false);
        return new AnhViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnhViewHolder holder, int position) {
        holder.imgAnh.setImageResource(anhList.get(position));
        holder.tvChuThich.setText(chuThich.get(position));

        // NC2: Staggered effect - thay đổi chiều cao khác nhau cho mỗi ô so le
        ViewGroup.LayoutParams params = holder.imgAnh.getLayoutParams();
        params.height = (position % 2 == 0) ? 190 : 120;
        holder.imgAnh.setLayoutParams(params);

        holder.itemView.setOnClickListener(v -> {
            int p = holder.getBindingAdapterPosition();
            if (p != RecyclerView.NO_POSITION) anhClickListener.onAnhClick(p);
        });

        holder.btnXoa.setOnClickListener(v -> {
            int p = holder.getBindingAdapterPosition();
            if (p != RecyclerView.NO_POSITION) deleteClickListener.onDeleteClick(p);
        });
    }

    @Override
    public int getItemCount() {
        return anhList.size();
    }
}
