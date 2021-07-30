package com.example.emplymentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,sales;
    Button ok;
    TextView salary,total,years;
    RadioButton single,married,diploma,bachelor,master;
    SeekBar noYears;
    CheckBox french,spanish,mandarin;
    static double basicSalary=3240;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.extName);
        sales=findViewById(R.id.extSalesAmount);
        ok=findViewById(R.id.btnOK);
        salary=findViewById(R.id.txvBasic);
        total=findViewById(R.id.txvTotal);
        single=findViewById(R.id.rdbSingle);
        married=findViewById(R.id.rdbMarried);
        diploma=findViewById(R.id.rdbDiploma);
        bachelor=findViewById(R.id.rdbBachelor);
        master=findViewById(R.id.rdbMaster);
        years=findViewById(R.id.txvYears);
        noYears=findViewById(R.id.sbYears);
        french=findViewById(R.id.chkFrench);
        spanish=findViewById(R.id.chkSpanish);
        mandarin=findViewById(R.id.chkMandarin);
        //setup the seekBar event
        noYears.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                years.setText(String.valueOf(progress));
                double currentSalary=basicSalary;
                double extra=0;
                if(progress<3)
                    extra = progress*100;
                else if(progress<5)
                    extra=progress*150;
                else
                    extra=progress*200;

                currentSalary=basicSalary+extra;
                salary.setText(String.valueOf(currentSalary));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        diploma.setOnClickListener(new ButtonEvents());
        bachelor.setOnClickListener(new ButtonEvents());
        master.setOnClickListener(new ButtonEvents());
        ok.setOnClickListener(new ButtonEvents());

        french.setOnCheckedChangeListener(new CheckBoxEvent());
        spanish.setOnCheckedChangeListener(new CheckBoxEvent());
        mandarin.setOnCheckedChangeListener(new CheckBoxEvent());
    }
    private class CheckBoxEvent implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            double currentSalary=Double.parseDouble(salary.getText().toString());
            if (buttonView.getId()==R.id.chkFrench)
                if(buttonView.isChecked())
                    currentSalary+=50;
                else
                    currentSalary-=50;
            if (buttonView.getId()==R.id.chkSpanish)
                if(buttonView.isChecked())
                    currentSalary+=75;
                else
                    currentSalary-=75;
            if (buttonView.getId()==R.id.chkMandarin)
                if(buttonView.isChecked())
                    currentSalary+=90;
                else
                    currentSalary-=90;
            salary.setText(String.valueOf(currentSalary));
        }
    }

    private class ButtonEvents implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.rdbDiploma:noYears.setProgress(0);
                basicSalary=3240;
                    salary.setText(String.valueOf(basicSalary));break;
                case R.id.rdbBachelor:noYears.setProgress(0);
                    basicSalary=4000;
                    salary.setText(String.valueOf(basicSalary));break;
                case R.id.rdbMaster:noYears.setProgress(0);
                    basicSalary=4300;
                    salary.setText(String.valueOf(basicSalary));break;
                case R.id.btnOK:
                    //get the sales amount
                    if(!sales.getText().toString().isEmpty())
                    {
                        double salesAmount=Double.parseDouble(sales.getText().toString());
                        double comm=0.05*salesAmount;
                        double totalAmount=Double.parseDouble(salary.getText().toString())+comm;
                        if(married.isChecked())
                            totalAmount*=1.03;//add 3% of the total to the total
                        total.setText(String.format("%.2f",totalAmount));
                    }
                    else
                        Toast.makeText(getBaseContext(),"Please enter the sales amount",Toast.LENGTH_LONG).show();
            }
        }
    }
}