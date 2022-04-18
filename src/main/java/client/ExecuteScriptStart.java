package client;

import common.console.ConsoleReader;
import common.utils.IdGenerator;
import common.utils.Route;
import lombok.experimental.var;
import server.file.FileChecker;
import server.file.FileSaver;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class ExecuteScriptStart {
    ConsoleReader reader = new ConsoleReader();
    FileSaver fileSaver = new FileSaver();
    FileChecker fileChecker = new FileChecker(fileSaver);
    private final ArrayList<String> listOfCommand = new ArrayList<>();
    private final ArrayList<String> fileNameList = new ArrayList<>();
    public void read(String nameOfFile){
        System.out.println(nameOfFile);
        File file = new File(nameOfFile);
        try{
            FileInputStream f = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(f));
            String a;
            while ((a=reader.readLine())!=null){
                System.out.println(a);
                listOfCommand.add(a);
            }
            f.close();

            //} catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Неправильное имя файла");
        }
    }

    //var argss = listOfCommand.toArray(new String[listOfCommand.size()]);
    private Route createObject(){
        boolean fUserInputAllValueUpdate = false;
        var newBuilderRouteUpdate = Route.builder();
        Route newObjectUpdate = null;
        newBuilderRouteUpdate.id(IdGenerator.nextId());
        newBuilderRouteUpdate.creationDate(ZonedDateTime.now());
        while (!fUserInputAllValueUpdate){
            newBuilderRouteUpdate.name(Input.getString("Введите имя маршрута"));
            newBuilderRouteUpdate.coordinates(new Coordinates(Input.getInt("Введите координату маршрута X(int) :", Input.getInt("Введите координату маршрута Y(int) :"))));
        }

   }
}
