package com.carros.carroscrudv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.carros.carroscrudv2.R;
import com.carros.carroscrudv2.model.Carros;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CarroAdapter extends ArrayAdapter<Carros> {


    private final Context context;
    private final ArrayList<Carros> elementos;

    public CarroAdapter(Context context, ArrayList<Carros> elementos){
        super(context, R.layout.listacarros, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listacarros, parent, false);

        TextView modelo = rowView.findViewById(R.id.txtListaModelo);
        TextView placa = rowView.findViewById(R.id.txtListaPlaca);
        TextView Tmarca = rowView.findViewById(R.id.txtListaMarca);

        //CHAMANDO A LOGO
        ImageView imgLogo = rowView.findViewById(R.id.imgListaLogo);

        //SETANDO IMAGENS NA LISTA
        String marca = elementos.get(position).getMarca();
        if (marca.equals("Audi")) {
            Picasso.get().load("https://i.ibb.co/FqLDFgR/audi.png").into(imgLogo);
        } else if (marca.equals("Ford")) {
            Picasso.get().load("https://i.ibb.co/Kh0WNW3/ford.png").into(imgLogo);
        } else if (marca.equals("GM") || marca.equals("Chevrolet")) {
            Picasso.get().load("https://i.ibb.co/7jSKYg5/chevrolet.png").into(imgLogo);
        } else if (marca.equals("Volkswagem") || marca.equals("VW")) {
            Picasso.get().load("https://i.ibb.co/t3W8mZr/volkswagen.png").into(imgLogo);
        } else if (marca.equals("Toyota")) {
            Picasso.get().load("https://i.ibb.co/8cHcNfb/toyota.png").into(imgLogo);
        } else if (marca.equals("Mitsubish")) {
            Picasso.get().load("https://i.ibb.co/RCGxWTz/mitsubish.png").into(imgLogo);
        } else if (marca.equals("Jeep")) {
            Picasso.get().load("https://i.ibb.co/CbWCDmm/jeep.png").into(imgLogo);
        } else if (marca.equals("Hyundai")) {
            Picasso.get().load("https://i.ibb.co/tpLGfm5/hyundai.png").into(imgLogo);
        } else if (marca.equals("Dodge")) {
            Picasso.get().load("https://i.ibb.co/kxVRDkT/dodge.png").into(imgLogo);
        } else if (marca.equals("Fiat")) {
            Picasso.get().load("https://i.ibb.co/F5g5sjG/fiat.png").into(imgLogo);
        } else if (marca.equals("BMW") || marca.equals("bmw")) {
            Picasso.get().load("https://i.ibb.co/HVfFZvG/bmw.png").into(imgLogo);
        } else if (marca.equals("Cherry")) {
            Picasso.get().load("https://i.ibb.co/5v6JGSM/chery.png").into(imgLogo);
        } else if (marca.equals("Citroen")) {
            Picasso.get().load("https://i.ibb.co/PjDBdJt/citroen.png").into(imgLogo);
        } else if (marca.equals("Ferrari")) {
            Picasso.get().load("https://i.ibb.co/p4WPSzq/ferrari.png").into(imgLogo);
        } else if (marca.equals("Jac")) {
            Picasso.get().load("https://i.ibb.co/5YmPG4m/jac.png").into(imgLogo);
        } else if (marca.equals("Peugeot")) {
            Picasso.get().load("https://i.ibb.co/ZxPW4Bj/peugeot.png").into(imgLogo);
        } else if (marca.equals("Suzuki")) {
            Picasso.get().load("https://i.ibb.co/WztZybF/suzuki.png").into(imgLogo);
        }else if (marca.equals("Renault")) {
            Picasso.get().load("https://i.ibb.co/VQ08VtN/renault.png").into(imgLogo);
        } else {
            Picasso.get().load("https://i.ibb.co/6yXNXYn/desconhecido.png").into(imgLogo);
        }

        Tmarca.setText("Marca: " + elementos.get(position).getMarca());
        modelo.setText("Modelo: " + elementos.get(position).getModelo());
        placa.setText("Placa: " + elementos.get(position).getPlaca());

        return rowView;
    }
}