package com.example.searchandrescue;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.example.searchandrescue.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class Task extends Fragment {

    private TextView mNameTask;
    private TextView mDescrbingOfTask;
    private TextView mCoordiinate1;
    private TextView mCoordinate2;
    private TextView mEquipment;
    private TextView mNaturalConditions;
    private TextView mTime;
    private TextView mDate;
    public String conterOfFragment = "0";

    private DatabaseReference mRef;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        mNameTask = (TextView) rootView.findViewById(R.id.nameFromDatabase);
        mDescrbingOfTask = (TextView) rootView.findViewById(R.id.descridingFromDatabase);
        mCoordiinate1 = (TextView) rootView.findViewById(R.id.coordinate1From);
        mCoordinate2 = (TextView) rootView.findViewById(R.id.coordinate2From);
        mEquipment = (TextView) rootView.findViewById(R.id.equipmentFromDatabase);
        mNaturalConditions = (TextView) rootView.findViewById(R.id.naturalConditionsFrom);
        mTime = (TextView) rootView.findViewById(R.id.timeFrom);
        mDate = (TextView) rootView.findViewById(R.id.dateFrom);

        Bundle bundle = getArguments();
        conterOfFragment = "0";
        if(bundle != null){
            conterOfFragment = bundle.getString("Value", "0");
        }
        Toast.makeText(getActivity(), conterOfFragment, Toast.LENGTH_SHORT).show();
        changeText();

        return rootView;
    }

    public void changeText() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //dbCounter = dataSnapshot.child("counter").getValue(String.class);
                    //Toast.makeText(getActivity(), dbCounter, Toast.LENGTH_SHORT).show();
                    mNameTask.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("nameOfTask").getValue(String.class));
                    mDescrbingOfTask.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("describing").getValue(String.class));
                    mCoordiinate1.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("Coordinate1").getValue(String.class));
                    mCoordinate2.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("Coordinate2").getValue(String.class));
                    mEquipment.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("Equipment").getValue(String.class));
                    mNaturalConditions.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("NaturalConditions").getValue(String.class));
                    mTime.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("time").getValue(String.class));
                    mDate.setText(dataSnapshot.child("tasks").child(conterOfFragment).child("Date").getValue(String.class));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error" + databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
