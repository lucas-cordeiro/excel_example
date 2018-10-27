package apps.akayto.excel_example;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import apps.akayto.excel_example.Helper.Calendario;
import apps.akayto.excel_example.Helper.Excel;
import apps.akayto.excel_example.Molder.Cargo;
import apps.akayto.excel_example.Molder.Funcionario;
import apps.akayto.excel_example.Molder.Jornada;
import apps.akayto.excel_example.Molder.Setor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int iYear = 2018;
        int iMonth = Calendar.JUNE; // 1 (months begin with 0)
        int iDay = 1;


        Jornada jornada = new Jornada();
        jornada.setNome("5x2");
        jornada.setDiarista(true);
        jornada.setEscala("5x2");
        jornada.setHoras(30);
        jornada.setFolgaFeriado(true);

        Setor ortopedia = new Setor();
        ortopedia.setNome("Ortopedia");
        ortopedia.setDescricao("Ossos");

        Cargo medico = new Cargo();
        medico.setNome("Médico");
        medico.setSetor(ortopedia);
        medico.setDescricao("Ajuda paciêntes");
        medico.setJornada(jornada);
        medico.setQuantiFuncionario(2);

        Funcionario jose = new Funcionario();
        jose.setNome("José");
        jose.setCargo(medico);

        Funcionario maria = new Funcionario();
        maria.setNome("Maria");
        maria.setCargo(medico);

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(jose);
        funcionarios.add(maria);

        Calendar Calendar = new GregorianCalendar(iYear, iMonth, iDay);

        int[] feriados = new int[1];
        feriados[0] = 22;

        criarTabela(funcionarios, Calendar, feriados);

        criarWord();

        //Excel.saveExcelFile(this,"myExcel.xls", mycal);


    }

    private void criarTabela(List<Funcionario> funcionarios, Calendar calendar, int[] feriados){
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        int firstDayMonth=0;
        int quantDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int quantWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        int quantDomingos=0;

        List<Integer> domingos = new ArrayList<>();

        for (int day=0;day<=quantDay;day+=7) {
            if(day==0) {
                switch (firstDay) {
                    case 1:
                        day = 1;
                        break;
                    case 2:
                        day = 7;
                        break;
                    case 3:
                        day = 6;
                        break;
                    case 4:
                        day = 5;
                        break;
                    case 5:
                        day = 4;
                        break;
                    case 6:
                        day = 3;
                        break;
                    case 7:
                        day = 2;
                        break;
                }
                firstDayMonth = day;
            }
            domingos.add(day);
            quantDomingos++;

            //Toast.makeText(this, "Domingo: "+domingos.get(quantDomingos-1), Toast.LENGTH_SHORT).show();

        }

        //Toast.makeText(this, "Quantidade de Domingos: "+quantDomingos, Toast.LENGTH_SHORT).show();

        String[][] diasFuncionarios = new String[quantDay][funcionarios.size()];

        int quantFuncionarios=0;

        List<Funcionario> funcionariosExcel = new ArrayList<>();

        while (funcionarios.size()>0){
            int id = new Random().nextInt(funcionarios.size());
            Funcionario funcionario = funcionarios.get(id);

            if(funcionario.getCargo().getJornada().isDiarista()) {

                int diasTrabalho = Integer.parseInt(funcionario.getCargo().getJornada().getEscala().toLowerCase().split("x")[0]), diasFolga = Integer.parseInt(funcionario.getCargo().getJornada().getEscala().toLowerCase().split("x")[1]);
                //Toast.makeText(this, "Dias trabalho: "+diasTrabalho+"\nDias folga: "+diasFolga, Toast.LENGTH_SHORT).show();

                Toast.makeText(this, "quantFuncionarios: "+quantFuncionarios+"\nId: "+id, Toast.LENGTH_SHORT).show();

                for(int day=1;day<=quantDay;day++){
                    diasFuncionarios[day-1][quantFuncionarios]="T";
                }

                List<Integer> diasdeFolga = new ArrayList<>();

                if(quantFuncionarios==0){

                }else {
                   /* for (int folga = 0;folga<= diasFolga; folga++) {
                        int diaDeFalga;
                    }*/
                }
                quantFuncionarios++;

            }else{



            }

            funcionariosExcel.add(funcionario);
            funcionarios.remove(id);
        }

        Toast.makeText(this,"Saiu do While", Toast.LENGTH_SHORT).show();

        Excel.saveExcelFile(this,"myExcel.xls", calendar, diasFuncionarios, funcionariosExcel);

    }

    private void criarWord(){
        XWPFDocument document = new XWPFDocument();
    }
}
