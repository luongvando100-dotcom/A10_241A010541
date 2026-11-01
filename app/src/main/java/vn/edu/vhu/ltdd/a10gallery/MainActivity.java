package vn.edu.vhu.ltdd.a10gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "A10_241A010541";

    public static final String EXTRA_VI_TRI = "extra_vi_tri";

    /** Danh sách 15 ảnh trong album. */
    public static final List<Integer> ANH = new ArrayList<>(Arrays.asList(
            R.drawable.anh1, R.drawable.anh2, R.drawable.anh3, R.drawable.anh4,
            R.drawable.anh5, R.drawable.anh6, R.drawable.anh7, R.drawable.anh8,
            R.drawable.anh9, R.drawable.anh10, R.drawable.anh11, R.drawable.anh12,
            R.drawable.anh13, R.drawable.anh14, R.drawable.anh15
    ));

    private final List<Integer> anhList = new ArrayList<>(ANH);
    private final List<String> chuThich = new ArrayList<>();
    private AnhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        refreshChuThich();

        RecyclerView rv = findViewById(R.id.rvAnh);

        // ---- StaggeredGridLayoutManager cho lưới so le hoặc GridLayoutManager ----
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv.setHasFixedSize(true);

        adapter = new AnhAdapter(anhList, chuThich,
                viTri -> {
                    Log.d(TAG, "Mở ảnh lớn tại vị trí " + viTri);
                    Intent i = new Intent(this, ViewerActivity.class);
                    i.putExtra(EXTRA_VI_TRI, viTri);
                    startActivity(i);
                },
                viTri -> {
                    // ---- Xóa ảnh dùng notifyItemRemoved ----
                    anhList.remove(viTri);
                    chuThich.remove(viTri);
                    adapter.notifyItemRemoved(viTri);
                    adapter.notifyItemRangeChanged(viTri, anhList.size());
                    Toast.makeText(this, "Đã xóa ảnh tại vị trí " + (viTri + 1), Toast.LENGTH_SHORT).show();
                }
        );
        rv.setAdapter(adapter);

        findViewById(R.id.btnDoiCot).setOnClickListener(v -> doiLayoutManager(rv));
    }

    private void refreshChuThich() {
        chuThich.clear();
        for (int i = 0; i < anhList.size(); i++) {
            chuThich.add(getString(R.string.anh_so, i + 1));
        }
    }

    private void doiLayoutManager(RecyclerView rv) {
        if (rv.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            rv.setLayoutManager(new GridLayoutManager(this, 3));
            Toast.makeText(this, "Đã chuyển sang GridLayout 3 cột", Toast.LENGTH_SHORT).show();
        } else {
            rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            Toast.makeText(this, "Đã chuyển sang StaggeredGridLayout", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }
}
