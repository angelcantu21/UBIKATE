package com.software.angelcantu.ubicateqr;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView escaner;
    String empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        EscanearQR();
    }

    public void EscanearQR()
    {
        escaner=new ZXingScannerView(this);
        setContentView(escaner);
        escaner.setResultHandler(this);
        escaner.startCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        escaner.stopCamera();
        onBackPressed();
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado de QR");
        builder.setMessage("Cargando....");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //escaner.resumeCameraPreview(this);

        switch (result.getText())
        {
            case "Universidad Tecnológica General Mariano Escobedo":
                empresa="UTE";
                Intent ute = new Intent(getApplicationContext(), TabbedActivity.class);
                ute.putExtra("Info", empresa);
                ute.putExtra("id", "1");
                startActivity(ute);
                finish();
                break;
            case "Universidad Autónoma de Nuevo León":
                empresa="UANL";
                Intent d3 = new Intent(getApplicationContext(), TabbedActivity.class);
                d3.putExtra("Info", empresa);
                d3.putExtra("id", "2");
                startActivity(d3);
                finish();
                break;
            default:
                Toast.makeText(getApplicationContext(), "No existe ese codigo QR", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}
