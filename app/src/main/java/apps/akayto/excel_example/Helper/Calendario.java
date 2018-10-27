package apps.akayto.excel_example.Helper;

public class Calendario {
    public static String toMes(int month){
        String mes="";
        switch (month){
            case 0:
                mes = "Janeiro";
                break;
            case 1:
                mes = "Fevereiro";
                break;
            case 2:
                mes = "Março";
                break;
            case 3:
                mes = "Abril";
                break;
            case 4:
                mes = "Maio";
                break;
            case 5:
                mes = "Junho";
                break;
            case 6:
                mes = "Julho";
                break;
            case 7:
                mes = "Agosto";
                break;
            case 8:
                mes = "Setembro";
                break;
            case 9:
                mes = "Outubro";
                break;
            case 10:
                mes = "Novembro";
                break;
            case 11:
                mes = "Dezembro";
                break;
        }

        return mes;
    }

    public static String toDayWeek(int dayOfWeek){
        String dayWeek = "";

        switch (dayOfWeek) {
            case 1:
                dayWeek = "D";
                break;
            case 2:
                dayWeek = "S";
                break;
            case 3:
                dayWeek = "T";
                break;
            case 4:
            case 5:
                dayWeek = "Q";
                break;
            case 6:
            case 7:
                dayWeek = "S";
                break;
        }
        return dayWeek;
    }
}
