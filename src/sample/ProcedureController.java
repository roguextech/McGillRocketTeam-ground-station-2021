package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

public class ProcedureController {

    @FXML
    Button loadProcedureButton;

    @FXML
    ListView procedures;

    public void loadProcedure(javafx.event.ActionEvent actionEvent) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        //Runtime.getRuntime().exec("explorer.exe /open, C://");
        //Desktop.getDesktop().open(new File("C:\\"));
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Spreadsheets", "xlsx", "xls", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(new JPanel());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            if (getFileExtension(selectedFile).equals("xlsx")) {
                System.out.println("file path: " + selectedFile.getAbsolutePath());
                String filepath = selectedFile.getAbsolutePath();
                FileInputStream inputStream = new FileInputStream(new File(filepath));

                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet firstSheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = firstSheet.iterator();

                while (rowIterator.hasNext()) {
                    Row nextRow = rowIterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    //add procedure message
                    Cell cell = cellIterator.next();
                    procedures.getItems().add()


                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        System.out.print(cell.getStringCellValue());
                    }
                }
            }
        }

    }

    private static String getFileExtension(File file) {
        System.out.print("file name: " + file.getName());
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            System.out.println(fileName.substring(fileName.lastIndexOf(".") + 1));
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        else return "";
    }
}
