package hbacak07.example.com.recyclerviewandcardviewexample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> urun_listesi;
    RecyclerView recyclerView;
    StoreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urun_listesi=new ArrayList<Item>();
        recyclerView=findViewById(R.id.recycler_view);
        urun_listesi=new ArrayList<>();

        urun_listesi.add(new Item("Ürün 1","100",R.drawable.urun_1));
        urun_listesi.add(new Item("Ürün 2","300",R.drawable.urun_2));
        urun_listesi.add(new Item("Ürün 3","200",R.drawable.urun_3));
        urun_listesi.add(new Item("Ürün 4","700",R.drawable.urun_4));
        urun_listesi.add(new Item("Ürün 5","800",R.drawable.urun_5));
        urun_listesi.add(new Item("Ürün 6","200",R.drawable.urun_6));
        urun_listesi.add(new Item("Ürün 7","100",R.drawable.urun_1));
        urun_listesi.add(new Item("Ürün 8","300",R.drawable.urun_2));
        urun_listesi.add(new Item("Ürün 9","200",R.drawable.urun_3));
        urun_listesi.add(new Item("Ürün 10","700",R.drawable.urun_4));
        urun_listesi.add(new Item("Ürün 11","800",R.drawable.urun_5));
        urun_listesi.add(new Item("Ürün 12","200",R.drawable.urun_6));

        mAdapter=new StoreAdapter(this,urun_listesi);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,3);
        Toast.makeText(this, "layout..:"+layoutManager.toString(), Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(8),true) );
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);


    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
        private Context context;
        private List<Item> item_list;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView isim, fiyat;
            public ImageView resim;

            public MyViewHolder(View view) {
                super(view);
                isim = view.findViewById(R.id.title);
                fiyat = view.findViewById(R.id.price);
                resim = view.findViewById(R.id.thumbnail);
            }
        }


        public StoreAdapter(Context context, List<Item> list) {
            this.context = context;
            this.item_list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final Item item = item_list.get(position);
            holder.isim.setText(item.getBaslik());
            holder.fiyat.setText(item.getFiyat());

            Glide.with(context)
                    .load(item.getResim())
                    .into(holder.resim);
        }

        @Override
        public int getItemCount() {
            return item_list.size();
        }
    }
}
