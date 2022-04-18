package client;

import common.console.ConsoleReader;
import common.interaction.Request;
import common.interaction.Response;
import common.interaction.Status;
import common.json.JsonConverter;
import common.utils.*;
import lombok.*;
//import lombok.experimental.var;
import server.commands.Add;
import server.commands.Help;
import server.dao.RouteDAO;
import server.file.FileChecker;
import server.file.FileSaver;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        var array = listOfCommand.toArray(new String[listOfCommand.size()]);
    }

    //var array = listOfCommand.toArray(new String[listOfCommand.size()]);
    private Route createObject(){
        boolean fUserInputAllValueUpdate = false;
        var newBuilderRouteUpdate = Route.builder();
        Route newObjectUpdate = null;
        newBuilderRouteUpdate.id(IdGenerator.nextId());
        newBuilderRouteUpdate.creationDate(ZonedDateTime.now());
        while (!fUserInputAllValueUpdate){
            newBuilderRouteUpdate.name(Input.getString("Введите имя маршрута"));
            newBuilderRouteUpdate.coordinates(new Coordinates(Input.getInt("Введите координату маршрута X(int) :"),Double.parseDouble(String.valueOf( Input.getInt("Введите координату маршрута Y(int) :")))));
            newBuilderRouteUpdate.from(new Location(Double.valueOf(Double.parseDouble(String.valueOf(Input.getInt("Введите координату отправления FromX(double)")))),Long.parseLong(String.valueOf(Input.getInt("Введите координату отправления FromY(Long)"))), Input.getString("Введите название маршрута (String)")));
            newBuilderRouteUpdate.to(new common.utils.loc.Location(Input.getInt("Введите координату прибытия ToX(int)"),Float.valueOf(Float.parseFloat(String.valueOf(Input.getInt("Введите координату прибытия ToY(float)")))),Input.getString("Введите название маршрута (String)")));
            newBuilderRouteUpdate.distance(Integer.parseInt(String.valueOf(Input.getInt("Введите длину дистанции (Integer)"))));

            fUserInputAllValueUpdate = true;
        }
        return newObjectUpdate;
   }
   public void executeStart (List<String> input,  InetSocketAddress inetSocketAddress, SocketChannel socketChannel, DataOutputStream dataOutputStream) {
        listOfCommand.forEach(x-> {
            try {
                this.executeScriptStart(input, inetSocketAddress,socketChannel,dataOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileSaver.getListOfFileNames().clear();
   }

   public boolean checkFileInList(ArrayList<String> list,String file){
        for (String nameOfFile: list ){
            if (Objects.equals(nameOfFile,file)){
                return false;
            }
        }
        return true;
   }
   RouteDAO dao = new RouteDAO();
   private void executeScriptStart(List<String> input, InetSocketAddress inetSocketAddress, SocketChannel socketChannel,
                                   DataOutputStream dataOutputStream) throws IOException {
        if (input.size() == 1) {
            // клиент месседж=реквест
            // клиент ресивера нет
            // клиент сендер
            Request request ;
            Response response = new Response();
            //var sendingArgs = args[1].split("-");

            switch (input.get(0)){
                case "help":
                    Help help = new Help();
                    response = help.execute(dao);
                    dataOutputStream.writeUTF(JsonConverter.serResponse(response));

            }
        }

//        else{
//            //что то
//        }

   }
}
