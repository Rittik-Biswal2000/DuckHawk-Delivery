package com.duckhawk.duckhawk_delivery.ui.slideshow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.duckhawk.duckhawk_delivery.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    String Buyer,Address;
    View v;
    private RecyclerView mrecyclerView;
    public List<Slide> lstSlide;
    ProgressDialog progressDialog;
    private DatabaseReference mdatabaseref = FirebaseDatabase.getInstance().getReference().child(("Orders"));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_slideshow, container, false);

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        mdatabaseref.keepSynced(true);

        mrecyclerView = (RecyclerView) v.findViewById(R.id.order_recycle);
        mrecyclerView.setHasFixedSize(true);
        //RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstSlide);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mrecyclerView.setAdapter(recyclerViewAdapter);

        init();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Syncing");
        progressDialog.setCancelable(true);
        progressDialog.show();

        loaddata();
    }
    private void init()
    {
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    public void loaddata()
    {
        FirebaseRecyclerAdapter<Slide, SlideHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Slide, SlideHolder>
                (Slide.class, R.layout.item_order, SlideHolder.class, mdatabaseref) {
            @Override
            protected void populateViewHolder(SlideHolder slideHolder, Slide slide, int i) {
                slideHolder.setBuyer("Buyer: " + slide.getbuyer());
                slideHolder.setAddress("Address: "+slide.getAddress());
                slideHolder.setProdcat("Prod Category: " + slide.getProdcat());
                slideHolder.setProdid("Prod id: "+slide.getProductid());
            }
        };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<Slide, SlideHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Slide, SlideHolder>
//                (Slide.class, R.layout.item_order, SlideHolder.class, mdatabaseref) {
//            @Override
//            protected void populateViewHolder(SlideHolder slideHolder, Slide slide, int i) {
//                slideHolder.setBuyer(slide.getBuyer());
//                slideHolder.setAddress(slide.getAddress());
//            }
//        };
//        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
//    }

    public static class SlideHolder extends RecyclerView.ViewHolder
    {

        View mView;
        public SlideHolder(View itemView)
        {

            super(itemView);
            mView = itemView;

        }

        public void setBuyer(String Buyer)
        {
            TextView tv_buyer = (TextView) itemView.findViewById(R.id.order_buyer);
            tv_buyer.setText(Buyer);
        }

        public void setAddress(String Address)
        {
            TextView tv_address = (TextView) itemView.findViewById(R.id.order_location);
            tv_address.setText(Address);
        }
        public void setProdcat(String prodcat)
        {
            TextView tv_prodcat = (TextView) itemView.findViewById(R.id.order_prodcat);
            tv_prodcat.setText(prodcat);
        }


        public void setProdid(String prodcat)
        {
            TextView tv_prodid = (TextView) itemView.findViewById(R.id.order_prodid);
            tv_prodid.setText(prodcat);
        }
    }

//    @Override
//    public void onCreate( Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        adddata();
//
//    }

//    private void adddata() {
//        lstSlide = new ArrayList<>();
//        slideref.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots)
//                        {
//                            Note note = documentSnapshot.toObject(Note.class);
////                             Buyer = note.getBuyer();
////                             Address = note.getAddress();
//                            System.out.println(Buyer);
//                            System.out.println(Address);
//
//                            lstSlide.add(new Slide(Buyer,Address));
//                        }
//                    }
//                });
//    }
}