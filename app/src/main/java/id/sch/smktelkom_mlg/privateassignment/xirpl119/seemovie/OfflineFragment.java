package id.sch.smktelkom_mlg.privateassignment.xirpl119.seemovie;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class OfflineFragment extends Fragment {
    OfflineAdapter adapter;
    ArrayList<OfflineListItem> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewMovie;
    public  static DatabaseHandler mDb;
    public OfflineFragment() {
        // Required empty public constructor
    }


    public static void initDB(Context context) {
                int dbVersion = 1;
                String dbName = "movies.db";
                mDb = new DatabaseHandler(context, dbName, dbVersion) {


                    @Override
                        protected void dropTables(SQLiteDatabase db) {
                                db.execSQL(PlaceTable.getSQLDrop());
                            }

                                @Override
                        protected void createTables(SQLiteDatabase db) {
                                db.execSQL(PlaceTable.getSQLCreate());
                            }
                    };
           }

                @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_offline, container, false);

                    LinearLayoutManager layoutManagera = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mList = new ArrayList<>();
                recyclerViewMovie = (RecyclerView) view.findViewById(R.id.recyclerViewMovie);
                adapter = new OfflineAdapter(mList, getActivity().getApplicationContext());
                recyclerViewMovie.setAdapter(adapter);
                recyclerViewMovie.setLayoutManager(layoutManagera);
                recyclerViewMovie.setHasFixedSize(true);


                                // loadRecyclerViewData();
                                        initDB(getActivity());
                if (PlaceTable.isEmpty(mDb))
                        fillDataToDB();

                        refreshData(null);

                        return view;
            }



                private void fillDataToDB() {
                Resources resources = getResources();//error: The method getResources() is undefined for the type DbAdapter
                String[] arTitle = resources.getStringArray(R.array.tittle);
                TypedArray a = resources.obtainTypedArray(R.array.places_picture);
                String[] arFoto = new String[a.length()];
                for (int i = 0; i < arFoto.length; i++) {
                        int id = a.getResourceId(i, 0);
                        arFoto[i] = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                                        + resources.getResourcePackageName(id) + '/'
                                        + resources.getResourceTypeName(id) + '/'
                                        + resources.getResourceEntryName(id);
                    }
                a.recycle();
                for (int i = 0; i < arTitle.length; i++) {
                        PlaceTable.add(mDb, new OfflineListItem(arTitle[i], arFoto[i]));
                    }


                            }

                private void refreshData(String query) {
                mList.clear();

                        if (query == null || query.isEmpty())
                        PlaceTable.getAll(mDb);
                else
                    PlaceTable.getPlaceLike(mDb, query);

                        mList.addAll(PlaceTable.ITEMS);
                adapter.notifyDataSetChanged();
            }


            }