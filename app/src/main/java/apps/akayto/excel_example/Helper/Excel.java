package apps.akayto.excel_example.Helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import apps.akayto.excel_example.Molder.Funcionario;

import static android.support.constraint.Constraints.TAG;

public class Excel {

    public static boolean saveExcelFile(Context context, String fileName, Calendar calendar, String[][] diasFuncionarios, List<Funcionario> funcionarios) {

        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        int quantDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int quantWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e(TAG, "Storage not available or read only");
            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style para o Título
        CellStyle csTitle = wb.createCellStyle();
        csTitle.setFillForegroundColor(HSSFColor.LIME.index);
        csTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        csTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        csTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFFont fontTitle = (HSSFFont) wb.createFont();
        fontTitle.setFontName("Arial");
        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontTitle.setFontHeight((short)(16*20));
        csTitle.setFont(fontTitle);

        short borderTitle = CellStyle.BORDER_THICK;
        csTitle.setBorderBottom(borderTitle);
        csTitle.setBorderLeft(borderTitle);
        csTitle.setBorderRight(borderTitle);
        csTitle.setBorderTop(borderTitle);

        //Cell style para o cabeçalho
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFFont font = (HSSFFont) wb.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short)(12*20));
        cs.setFont(font);

        short border = CellStyle.BORDER_MEDIUM;
        cs.setBorderBottom(border);
        cs.setBorderLeft(border);
        cs.setBorderRight(border);
        cs.setBorderTop(border);


        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("myOrder");

        // Generate column headings
        Row rowFirst = sheet1.createRow(0);
        c = rowFirst.createCell(0);

        Row rowTitle = sheet1.createRow(1);
        rowTitle.setHeight((short)(7 * 100));

        CellRangeAddress titulo = new CellRangeAddress(1,1,1, quantDay+4);
        sheet1.addMergedRegion(titulo);

        for(int cell = 1; cell <= titulo.getLastColumn(); cell++) {
            c = rowTitle.createCell(cell);
            if (cell == 1)
                c.setCellValue("Tabela de Horários do Mês " + Calendario.toMes(calendar.get(Calendar.MONTH)));
            c = rowTitle.getCell(cell);
            c.setCellStyle(csTitle);
        }


        Row row = sheet1.createRow(2);
        row.setHeight((short)(5 * 100));

        c = row.createCell(0);


        c = row.createCell(row.getLastCellNum());
        c.setCellValue("Funcionário");
        c.setCellStyle(cs);

        c = row.createCell(row.getLastCellNum());
        c.setCellValue("Função");
        c.setCellStyle(cs);

        c = row.createCell(row.getLastCellNum());
        c.setCellValue("Coren");
        c.setCellStyle(cs);

        c = row.createCell(row.getLastCellNum());
        c.setCellValue("Matric.");
        c.setCellStyle(cs);

        for(int day=1;day<=quantDay;day++){
            int id = row.getLastCellNum();
            c = row.createCell(id);
            c.setCellValue(day);
            c.setCellStyle(cs);
            sheet1.setColumnWidth(id, 2 * 500);
        }

        //Subtitulo 2
        Row row2 = sheet1.createRow(3);
        row2.setHeight((short)(5 * 100));

        c = row2.createCell(1);
        c.setCellValue("Centro Cirúrgico");
        c.setCellStyle(cs);

        for(int i=2; i<=4;i++){
            c = row2.createCell(i);
            c.setCellValue("");
            c.setCellStyle(cs);
        }

        for(int day=1;day<=quantDay;day++){
            int id = row2.getLastCellNum();
            c = row2.createCell(id);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            c.setCellValue(Calendario.toDayWeek(calendar.get(Calendar.DAY_OF_WEEK)));
            c.setCellStyle(cs);
            sheet1.setColumnWidth(id, 2 * 500);
        }

        //Funcionários
        for(int id=0;id<funcionarios.size();id++){
            Funcionario funcionario = funcionarios.get(id);

            Row rowFuncionario = sheet1.createRow(sheet1.getLastRowNum()+id);
            c = rowFuncionario.createCell(1);
            c.setCellValue(funcionario.getNome());

            for(int i=2; i<=4;i++){
                c = rowFuncionario.createCell(i);
                c.setCellValue("");
            }

            for(int day=1;day<=quantDay;day++) {
                c = rowFuncionario.createCell(rowFuncionario.getLastCellNum());
                c.setCellValue(diasFuncionarios[day-1][id]);
            }
        }

        sheet1.setColumnWidth(0, (2 * 500));
        sheet1.setColumnWidth(1, (20 * 500));
        sheet1.setColumnWidth(2, (7 * 500));
        sheet1.setColumnWidth(3, (7 * 500));
        sheet1.setColumnWidth(4, (7 * 500));

        // Create a path where we will place our List of objects on external storage
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        return success;
    }

    public static void readExcelFile(Context context, String filename) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            Log.e(TAG, "Storage not available or read only");
            return;
        }

        try{
            // Creating Input Stream
            File file = new File(context.getExternalFilesDir(null), filename);
            FileInputStream myInput = new FileInputStream(file);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            /** We now need something to iterate through the cells.**/
            Iterator rowIter = mySheet.rowIterator();

            while(rowIter.hasNext()){
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    Log.d(TAG, "Cell Value: " +  myCell.toString());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){e.printStackTrace(); }

        return;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
