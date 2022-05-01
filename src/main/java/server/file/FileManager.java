package server.file;

import server.dao.RouteDAO;
import common.utils.Route;
import common.utils.RouteInfo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс, который позволяет осуществлять корректную запись данных в файл
 */
public class FileManager {


    private static String directory = System.getenv().get("collection.csv");
    private static String nameOfFile = System.getenv().get("collection.csv");
    private static final String TEMP_FILE = "D:/collection_temp.csv";
    File file = new File(directory);

    /**
     * Метод записи данных о коллекции в файл
     *
     * @param routeDAO
     */

    public void write(RouteDAO routeDAO) {

        try {
            if (!file.exists()) {
                if (!file.createNewFile())
                    System.out.println("Файл не создан");
            }
            FileOutputStream fos = new FileOutputStream(file);

            String toBeWritten = ("id,name,x,y,date,fromX,fromX,fromName,toX,toY,toName,distance"
                    + System.lineSeparator() + routeDAO.getDescription());
//            String toBeWritten = ("id,name,coordinates={x,y,},date,location={fromX,fromX,fromName},location={toX,toY,toName},distance"
//                    + System.lineSeparator() + routeDAO.getDescription());

            fos.write(toBeWritten.getBytes(StandardCharsets.UTF_8));

            fos.flush();
            fos.close();
            System.out.println("элемент успешно сохранен");

        } catch (IOException e) {
            //saveToTmp(routeDAO);
            System.out.println("не удалось сохранить: " + e.getMessage());
        }

    }



    public RouteDAO read() {
        File file = new File(nameOfFile);
        String input = " ";
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("Чтение временного файла");
                    nameOfFile = TEMP_FILE;
                    file = new File(nameOfFile);
                }
//                System.out.println("Файл не создан");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            int nextByte;
            while ((nextByte = inputStream.read()) != -1)
                bos.write((char) nextByte);

            input = bos.toString();

        } catch (IOException e) {
//            FileManager.createTmpFile();
            return new RouteDAO();
        }

        List<String> lines = new ArrayList<>(Arrays.asList(input.split(System.lineSeparator())));
        RouteDAO dao = new RouteDAO();
        try {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                List<String> lineSplit = Arrays.asList(line.split(","));
                if (i == 0)
                    dao.initDate = lineSplit.get(0);

                else {
                    RouteInfo info = new RouteInfo(lineSplit);
                    dao.create(new Route(info));
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new RouteDAO();
        }
        return dao;
    }

    public void save(RouteDAO dao) throws IOException {

        String filepath = directory;

        try {
            FileOutputStream stream = new FileOutputStream(filepath);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            String description = dao.getDescription();
            writer.write(description);
            write(dao);
        } catch (IOException exception) {
            directory = TEMP_FILE;
            file = new File(directory);
            save(dao);
        }
    }
}