package server.commands;

import common.interaction.Response;
import server.dao.RouteDAO;

/**
 * Класс команды EXECUTE SCRIPT, предназначенный для чтения и исполнения скрипта из файла
 */

public class ExecuteScript extends ACommands{
    private RouteDAO routeDAO = new RouteDAO();
    public ExecuteScript(RouteDAO routeDAO){
        this.routeDAO = routeDAO;

    }

    @Override
    public String toString() {
        return "ExecuteScript";
    }


    @Override
    public Response execute(RouteDAO routeDAO){
        RouteDAO.executeScript(routeDAO[0]);
    }
}

/*public class ExecuteScript {
    FileManager manager = new FileManager();
    RouteDAO dao = manager.read();
    Response response = new Response();
    Request request = new Request();
    private final ArrayList<String> listOfCommand = new ArrayList<>();
    private final ArrayList<String> fileNameList = new ArrayList<>();
    FileSaver fileSaver = new FileSaver();
    FileChecker fileChecker = new FileChecker(fileSaver);
    public void read(String fileName){
        System.out.println(fileName);
        File file = new File(fileName);
        try {
            FileInputStream f = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(f));
            String a;
            while ((a = reader.readLine())!=null){
                System.out.println(a);
                listOfCommand.add(a);
            }
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Invalid name of file.");
        }
        var argss = listOfCommand.toArray(new String[listOfCommand.size()]);

    }

    /*private Route createObject() {
        boolean fUserInputAllValueUpdate = false;
        //прописать буилдер
        void newBuilderRouteUpdate = Route.builder();
        Route newObjectUpdate = null;
        //newBuilderRouteUpdate.id((long) ((int) (Math.random() * 1000)));
        newBuilderRouteUpdate.creationDate(LocalDateTime.now());
        while (!fUserInputAllValueUpdate) {
            newBuilderRouteUpdate.name(userInput.getString("Введите имя объекта"));
            newBuilderRouteUpdate.coordinates(new Coordinates(userInput.getInt("Введите координату X (int) :"), userInput.getInt("Введите координату Y (int) :")));
            newBuilderRouteUpdate.enginePower(userInput.getInt("Введите engine power(int) :"));
            newBuilderRouteUpdate.numberOfWheels(userInput.getInt("Введите количество колес (не наркотики) (int) :"));
            newBuilderRouteUpdate.type(userInput.getVehicleEnum("Введите тип транспортного средства"));
            newBuilderRouteUpdate.fuelType(userInput.getFuelType("Введите тип топлива"));
            newObjectUpdate = newBuilderVehicleUpdate.build();

            fUserInputAllValueUpdate = true;
        }
        return newObjectUpdate;*/
   // }
    /*public void executeStart (InetSocketAddress inetSocketAddress, DatagramSocket clientSocket){
        listOfCommand.forEach(x->this.executeScriptStart(x.split("-"), inetSocketAddress, clientSocket));
        fileSaver.getFileNameList().clear();
    }*/
    /*public boolean checkFileInList(ArrayList<String> list, String file){
        for (String nameOfFile: list
        ) {

            if (Objects.equals(nameOfFile, file)){
                return false;
            }
        }
        return true;
    }

    private void executeScript(String[] args , InetSocketAddress inetSocketAddress, DatagramSocket clientSocket) {
        if (args.length > 1) {
            ClientMessage clientMessage;
            ClientReceiver clientReceiver;
            ClientSender createClientMessageAndSend;

            var sendingArgs = args[1].split("-");
            switch (args[0]) {
                case "add":

                    RouteDAO newObject = create(routeDAO);
                    InsertCommand insertCommand = new InsertCommand(null);
                    clientMessage = new ClientMessage(insertCommand, sendingArgs, newObject);
                    createClientMessageAndSend = new ClientSender(clientMessage);
                    createClientMessageAndSend.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
                case "execute_script":
                    System.out.println("1");
                    System.out.println(fileNameList);
                    if (checkFileInList(fileNameList,sendingArgs[0])) {
                        System.out.println(fileSaver.getFileNameList());
                        fileNameList.add(sendingArgs[0]);
                        System.out.println("2");
                        System.out.println(fileNameList);
                        ExecuteScriptStarter executeScriptStarter = new ExecuteScriptStarter();
                        executeScriptStarter.executeStart(inetSocketAddress, clientSocket);
                    }else{
                        System.out.println("3");
                        System.out.println("рекурсия");
                    }
                    break;
                case "remove_by_id":
                    RemoveById removeCommand = new RemoveById(null);
                    clientMessage = new ClientMessage(removeCommand, sendingArgs, null);
                    createClientMessageAndSend = new ClientSender(clientMessage);
                    createClientMessageAndSend.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
                case "update_by":
                    RouteDAO newObjectUpdate = createObject();
                    UpdateById updateCommand = new UpdateCommand(null);
                    ClientMessage clientMessageUp = new ClientMessage(updateCommand, sendingArgs, newObjectUpdate);

                    ClientSender createClientMessageAndSend1 = new ClientSender(clientMessageUp);
                    createClientMessageAndSend1.sendMessage(inetSocketAddress, clientSocket);
                    break;

                case "remove_first":
//                    printInformation.printInStream("Идет создание объекта в remove_greater! ");
                    Vehicle newObjectRG = createObject();
                    RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand(null);
                    ClientMessage clientMessageRG = new ClientMessage(removeGreaterCommand, sendingArgs, newObjectRG);

                    ClientSender createClientMessageAndSendRG = new ClientSender(clientMessageRG);
                    createClientMessageAndSendRG.sendMessage(inetSocketAddress, clientSocket);
                    break;
                case "head":
//                    printInformation.printInStream("Идет создание объекта в remove_lower! ");
                    Vehicle newObjectRL = createObject();
                    RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(null);
                    ClientMessage clientMessageRL = new ClientMessage(removeLowerCommand, sendingArgs, newObjectRL);

                    ClientSender createClientMessageAndSendRL = new ClientSender(clientMessageRL);
                    createClientMessageAndSendRL.sendMessage(inetSocketAddress, clientSocket);
                    break;
                case "add_if_min":
                    FilterContainsNameCommand filterContainsNameCommand = new FilterContainsNameCommand(null);
                    clientMessage = new ClientMessage(filterContainsNameCommand, sendingArgs, null);
                    createClientMessageAndSend = new ClientSender(clientMessage);
                    createClientMessageAndSend.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
            }
        } else {
            ClientMessage clientMessage;
            ClientReceiver clientReceiver;
            ClientSender clientSender;
            switch (args[0]) {
                case ("help"):
                    HelpCommand helpCommand = new HelpCommand(null);
                    clientMessage = new ClientMessage(helpCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
                case ("info"):
                    InfoCommand infoCommand = new InfoCommand(null);
                    clientMessage = new ClientMessage(infoCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
                case ("show"):
                    ShowCommand showCommand = new ShowCommand(null);
                    clientMessage = new ClientMessage(showCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;

                case ("clear"):
                    break;

                case ("print_unique_distance"):
                    SumOfEnginePowerCommand sumOfEnginePowerCommand = new SumOfEnginePowerCommand(null);
                    clientMessage = new ClientMessage(sumOfEnginePowerCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
                case ("print_field_descending_distance"):
                    PrintFieldDescendingFuelTypeCommand printFieldDescendingFuelTypeCommand = new PrintFieldDescendingFuelTypeCommand(null);
                    clientMessage = new ClientMessage(printFieldDescendingFuelTypeCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;


                    /*case ("print_field_ascending_distance"):
                    PrintFieldDescendingFuelTypeCommand printFieldDescendingFuelTypeCommand = new PrintFieldDescendingFuelTypeCommand(null);
                    clientMessage = new ClientMessage(printFieldDescendingFuelTypeCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;*/

                /*case ("exit"):
                    SaveCommand saveCommand = new SaveCommand(null);
                    clientMessage = new ClientMessage(saveCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    break;
            }
        }
    }
}




/*public class ExecuteScript extends ACommands {
    //TODO эта параша нихуя не работает и я боюсь сюда лезть. @ника чини. /беззлобно
    FileManager manager = new FileManager();
    RouteDAO dao = manager.read();

    public Response execute(RouteDAO routeDAO) {

        String nameOfScript = args.get(1);
        if (ExecuteReader.checkNameOfFileInList(nameOfScript, sendingArgs[0])) {
            ExecuteReader.listOfNamesOfScripts.add(nameOfScript);
            try {
                List<String> listOfCommands = Files.readAllLines(Paths.get(nameOfScript + ".txt").toAbsolutePath());
                for (String lineOfFile : listOfCommands
                ) {
                    ACommands commands;
                    String command = lineOfFile.trim();

                    if (command.isEmpty()) {
                        throw new EmptyInputException();
                    }
                    List<String> args = new ArrayList<>(Arrays.asList(command.split(" ")));
                    try {
                        commands = CommandSaver.getCommand(args);
                        commands.execute(dao);
                    } catch (RuntimeException e) {
                        response.msg("ты норм? в скрипте параша написана, переделывай").
                                status(Status.USER_EBLAN_ERROR);
                    }
                }
            } catch (NoSuchFileException e) {
                response.msg("файл не найден").status(Status.FILE_ERROR);
            } catch (IOException e) {
                response.msg("Все пошло по пизде, чекай мать: " + e.getMessage()).
                        status(Status.UNKNOWN_ERROR);

            }
            ExecuteReader.listOfNamesOfScripts.clear();
        } else {
            response.msg("пу пу пу.... обнаружена рекурсия").status(Status.USER_EBLAN_ERROR);
        }
        response.msg("что-то не так произошло....").status(Status.UNKNOWN_ERROR);

        return response;
    }

    private void executeScriptStart(String[] args,,) {
        if (args.length > 1) {
        }
        ConsoleReader consoleReader = new ConsoleReader();
        ConsoleOutputer output = new ConsoleOutputer();
        String serverResponse;
        Request request;
        String nameOfScript = args.get(1);
        FileSaver fileSaver = new FileSaver();

        var sendingArgs = args[1].split("-");
        switch (args[0]) {
//                case "add":
//                    RouteDAO routeDAO = new RouteDAO();
//                    Add addCommand = new Add();
//
//                    break;
            case "execute_script":
                System.out.println("1");
                System.out.println(nameOfScript);
                if (ExecuteReader.checkNameOfFileInList(nameOfScript, sendingArgs[0])) ;
            {
                System.out.println(fileSaver.getListOfFileNames());
                nameOfScript.add(sendingArgs[0]);
                System.out.println("2");
                System.out.println(nameOfScript);
                //ЧТЕНИЕ СКРИПТА КАК ПРОПИСАТЬ

                //executeScriptStarter.executeStart(inetSocketAddress, clientSocket);
            } else{
                System.out.println("3");
                System.out.println("рекурсия");
            }
            break;
                /*case "remove":
                    RemoveCommand removeCommand = new RemoveCommand(null);
                    clientMessage = new ClientMessage(removeCommand, sendingArgs, null);
                    createClientMessageAndSend = new ClientSender(clientMessage);
                    createClientMessageAndSend.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
                case "update":
//                    printInformation.printInStream("Идет создание объекта в замену старого! ");
                    Vehicle newObjectUpdate = createObject();
                    UpdateCommand updateCommand = new UpdateCommand(null);
                    ClientMessage clientMessageUp = new ClientMessage(updateCommand, sendingArgs, newObjectUpdate);

                    ClientSender createClientMessageAndSend1 = new ClientSender(clientMessageUp);
                    createClientMessageAndSend1.sendMessage(inetSocketAddress, clientSocket);
                    break;

                case "remove_greater":
//                    printInformation.printInStream("Идет создание объекта в remove_greater! ");
                    Vehicle newObjectRG = createObject();
                    RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand(null);
                    ClientMessage clientMessageRG = new ClientMessage(removeGreaterCommand, sendingArgs, newObjectRG);

                    ClientSender createClientMessageAndSendRG = new ClientSender(clientMessageRG);
                    createClientMessageAndSendRG.sendMessage(inetSocketAddress, clientSocket);
                    break;
                case "remove_lower":
//                    printInformation.printInStream("Идет создание объекта в remove_lower! ");
                    Vehicle newObjectRL = createObject();
                    RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(null);
                    ClientMessage clientMessageRL = new ClientMessage(removeLowerCommand, sendingArgs, newObjectRL);

                    ClientSender createClientMessageAndSendRL = new ClientSender(clientMessageRL);
                    createClientMessageAndSendRL.sendMessage(inetSocketAddress, clientSocket);
                    break;
                case "filter_contains_name":
                    FilterContainsNameCommand filterContainsNameCommand = new FilterContainsNameCommand(null);
                    clientMessage = new ClientMessage(filterContainsNameCommand, sendingArgs, null);
                    createClientMessageAndSend = new ClientSender(clientMessage);
                    createClientMessageAndSend.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;
            }*/
            /*default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
        } else{
            switch (args[0]) {
                case ("help"):
                    Help helpCommand = new Help();
                    clientMessage = new ClientMessage(helpCommand, new String[0], null);
                    clientSender = new ClientSender(clientMessage);
                    clientSender.sendMessage(inetSocketAddress, clientSocket);
                    clientReceiver = new ClientReceiver();
                    clientReceiver.getMessage(clientSocket);
                    break;

            }
        }
    }
}*/


