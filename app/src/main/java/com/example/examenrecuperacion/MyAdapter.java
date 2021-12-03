package com.example.examenrecuperacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    private Context context;
    ArrayList<getter> list;
    ArrayList<getter> listaOriginal;





    public MyAdapter(Context context, ArrayList<getter> list ) {
        this.context = context;
        this.list = list;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(list);

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.usuarios,parent, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        final getter item = list.get(position);
        getter user = list.get(position);

        holder.txtnombre.setText(user.getNombre());
        holder.txttelefono.setText(user.getTelefono());
        holder.txtlatitud.setText(user.getLatitud());
        holder.txtlongitud.setText(user.getLongitud());
        Glide.with(holder.img1.getContext()).load(user.getUrl()).into(holder.img1);

        final getter infoData = list.get(position);

        holder.btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.itemView.getContext(),cambiar.class);
                intent.putExtra("itemDetalle",item);
                holder.itemView.getContext().startActivity(intent);


            }
        });


        holder.eliminarlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txttelefono.getContext());
                builder.setTitle("Estas Seguro(a)");
                builder.setMessage("Los datos no se podran recuperar");
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String key1 = list.get(position).getKey();
                        FirebaseDatabase.getInstance().getReference().child("Usuarios")
                                .child(key1).removeValue();


                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.txttelefono.getContext(), "Cancelar", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });



    }


    public void filtrar(ArrayList<getter> filtroUsuarios) {
        this.list = filtroUsuarios;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView txtnombre,txttelefono,txtlongitud,txtlatitud;
        Button btnModificar,eliminarlist;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eliminarlist = itemView.findViewById(R.id.eliminarlist);
            btnModificar= itemView.findViewById(R.id.btnModificarlit);
            img1 = itemView.findViewById(R.id.img1);
            txtnombre = itemView.findViewById(R.id.txtnombrelist);
            txttelefono = itemView.findViewById(R.id.txttelefonolist);
            txtlongitud = itemView.findViewById(R.id.txtlongitudlist);
            txtlatitud = itemView.findViewById(R.id.txtlatitudlist);


        }
    }
}
