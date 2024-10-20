/*
 *@author:Leudvan Guedes
 */
package br.edu.fateczl.calcularsalario;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.calcularsalario.model.ProfessorHorista;
import br.edu.fateczl.calcularsalario.model.ProfessorTitular;

public class MainActivity extends AppCompatActivity {

    private Spinner spTipoProfessor;
    private TextView tvResultado;
    private ConstraintLayout layoutProfessorTitular;
    private ConstraintLayout layoutProfessorHorista;
    private final String[] tiposProfessores = {"Professor Titular","Professor Horista"};
    private static final int PROFESSOR_TITULAR = 0;
    private static final int PROFESSOR_HORISTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spTipoProfessor = findViewById(R.id.spTipoProfessor);
        Button btnCalcular = findViewById(R.id.btnCalcular);
        tvResultado = findViewById(R.id.tvResultado);
        layoutProfessorTitular = findViewById(R.id.layoutProfessorTitular);
        layoutProfessorHorista = findViewById(R.id.layoutProfessorHorista);
        preencherSpinnerProfessores();

        spTipoProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                onNothingSelected(adapterView);
                btnCalcular.setVisibility(View.VISIBLE);
                if(pos==PROFESSOR_TITULAR){
                    layoutProfessorTitular.setVisibility(View.VISIBLE);
                }else {
                    layoutProfessorHorista.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                layoutProfessorHorista.setVisibility(View.INVISIBLE);
                layoutProfessorTitular.setVisibility(View.INVISIBLE);
                btnCalcular.setVisibility(View.INVISIBLE);
            }
        });

        btnCalcular.setOnClickListener(op -> {
            if(spTipoProfessor.getSelectedItemPosition() == PROFESSOR_TITULAR)
                calcularSalarioTitular();
            else
                calcularSalarioHorista();
        });

    }

    private void preencherSpinnerProfessores() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,tiposProfessores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoProfessor.setAdapter(adapter);
    }

    private void calcularSalarioHorista() {
        EditText etValorHoraAula = findViewById(R.id.etValorHoraAula);
        EditText etQtdHoras = findViewById(R.id.etQtdHoras);

        ProfessorHorista prof = new ProfessorHorista();
        prof.setValorHoraAula(Double.parseDouble(etValorHoraAula.getText().toString()));
        prof.setHorasAula(Integer.parseInt(etQtdHoras.getText().toString()));
        double salario = prof.calcSalario();

        tvResultado.setText(String.valueOf(salario));
    }

    private void calcularSalarioTitular(){
        EditText etTempoInstituicao = findViewById(R.id.etTempoInstituicao);
        EditText etSalario = findViewById(R.id.etSalario);

        ProfessorTitular prof = new ProfessorTitular();
        prof.setSalario(Double.parseDouble(etSalario.getText().toString()));
        prof.setAnosInstituicao(Integer.parseInt(etTempoInstituicao.getText().toString()));
        double salario = prof.calcSalario();

        tvResultado.setText(String.valueOf(salario));
    }
}