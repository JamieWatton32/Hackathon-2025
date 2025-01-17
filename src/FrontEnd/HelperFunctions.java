package FrontEnd;

import BackEnd.DataApi;

import java.time.LocalDate;
import java.util.*;

public class HelperFunctions {
    private static ArrayList<String[]> rooms = new ArrayList<>();

    public static void findOutstanding(String[][] roomList, ArrayList<String> outstandingRooms, LocalDate currentDate, int currentQuarter) {

        for (String[] room : roomList){

            switch (room[1].toUpperCase()) {
                case "OFFICE" -> {
                    rooms = DataApi.getRoomDates("Offices");
                    quarterlyCheck(room[0], rooms, outstandingRooms, currentDate, currentQuarter);
                }
                case "CLASSROOM" -> {
                    rooms = DataApi.getRoomDates("Classrooms");
                    quarterlyCheck(room[0], rooms, outstandingRooms, currentDate, currentQuarter);
                }
                case "SHOP" -> {
                    rooms = DataApi.getRoomDates("Shops");
                    monthlyCheck(room[0], rooms, outstandingRooms, currentDate);
                }
                case "LABORATORY" -> {
                    rooms = DataApi.getRoomDates("Laboratory");
                    monthlyCheck(room[0], rooms, outstandingRooms, currentDate);
                }
                case "MAINTENANCE" -> {
                    rooms = DataApi.getRoomDates("Maintenance");
                    quarterlyCheck(room[0], rooms, outstandingRooms, currentDate, currentQuarter);
                }
                case "CULINARY" -> {
                    rooms = DataApi.getRoomDates("Culinary");
                    monthlyCheck(room[0], rooms, outstandingRooms, currentDate);
                }
                case null, default -> {
                    //placeholder for error handling
                    continue;
                }
            }
        }
    }

    public static void findOutstanding(ArrayList<String[]> roomList, ArrayList<String> outstandingRooms, int month, int year) {

        for (String[] room : roomList){

            switch (room[1].toUpperCase()) {
                case "OFFICE" -> {
                    rooms = DataApi.getRoomDates("Offices");
                    quarterlyCheck(room[0], rooms, outstandingRooms, month, year);
                }
                case "CLASSROOM" -> {
                    rooms = DataApi.getRoomDates("Classrooms");
                    quarterlyCheck(room[0], rooms, outstandingRooms, month, year);
                }
                case "SHOP" -> {
                    rooms = DataApi.getRoomDates("Shops");
                    monthlyCheck(room[0], rooms, outstandingRooms, month, year);
                }
                case "LABORATORY" -> {
                    rooms = DataApi.getRoomDates("Laboratory");
                    monthlyCheck(room[0], rooms, outstandingRooms, month, year);
                }
                case "MAINTENANCE" -> {
                    rooms = DataApi.getRoomDates("Maintenance");
                    quarterlyCheck(room[0], rooms, outstandingRooms, month, year);
                }
                case "CULINARY" -> {
                    rooms = DataApi.getRoomDates("Culinary");
                    monthlyCheck(room[0], rooms, outstandingRooms, month, year);
                }
                case null, default -> {
                    //placeholder for error handling
                    continue;
                }
            }
        }
    }

    public static int findQuarter(LocalDate currentDate) {
        int quarter = 0;
        int month = currentDate.getMonthValue();

        if (month < 4) {
            quarter = 1;
        } else if (month < 7) {
            quarter = 2;
        } else if (month < 10) {
            quarter = 3;
        } else {
            quarter = 4;
        }

        return quarter;
    }

    public static int findQuarter(int month) {
        int quarter = 0;

        if (month < 4) {
            quarter = 1;
        } else if (month < 7) {
            quarter = 2;
        } else if (month < 10) {
            quarter = 3;
        } else {
            quarter = 4;
        }

        return quarter;
    }

    public static void monthlyCheck (String roomNumber, ArrayList<String[]> reports, ArrayList<String> outstandingRooms, LocalDate currentDate) {
        boolean flag = false;

        for (String[] report : reports) {
            String reportRoomNumber = report[0];
            int[] reportDate = Arrays.stream(report[1].replaceFirst("^(\\d{1,2})/(\\d{1,2})/(\\d{4}).*", "$1/$3").split("/"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if ((Objects.equals(roomNumber, reportRoomNumber)) && // if room numbers match
                    (reportDate[0] == currentDate.getMonthValue()) && // & months match
                    (reportDate[1] == currentDate.getYear())) { // & years match
                flag = true;
                break;
            }
        }

        if (flag == false) {
            outstandingRooms.add(roomNumber);
        }
    }

    public static void monthlyCheck (String roomNumber, ArrayList<String[]> reports, ArrayList<String> outstandingRooms, int month, int year) {
        boolean flag = false;

        for (String[] report : reports) {
//            System.out.println("Checking: " + report.toString());

            String reportRoomNumber = report[0];
            int[] reportDate = Arrays.stream(report[1].replaceFirst("^(\\d{1,2})/(\\d{1,2})/(\\d{4}).*", "$1/$3").split("/"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if ((Objects.equals(roomNumber.toUpperCase(), reportRoomNumber.toUpperCase())) && // if room numbers match
                    (reportDate[0] == month) && // & months match
                    (reportDate[1] == year)) { // & years match
                flag = true;
//                System.out.println("Match found: " + report + roomNumber);
                break;
            }
        }

        if (flag == false) {
            outstandingRooms.add(roomNumber);
        }
    }

    public static void quarterlyCheck (String roomNumber, ArrayList<String[]> reports, ArrayList<String> outstandingRooms, LocalDate currentDate, int currentQuarter){
        boolean flag = false;

        for (String[] report : reports) {
            String reportRoomNumber = report[0];
            int[] reportDate = Arrays.stream(report[1].replaceFirst("^(\\d{1,2})/(\\d{1,2})/(\\d{4}).*", "$1/$3").split("/"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int reportQuarter = findQuarter(reportDate[0]);

            if ((roomNumber.equalsIgnoreCase(reportRoomNumber)) && // if room numbers match
                    (reportQuarter == currentQuarter) && // & quarters match
                    (reportDate[1] == currentDate.getYear())) { // & years match
                flag = true;
                break;
            }
        }

        if (flag == false) {
            outstandingRooms.add(roomNumber);
        }
    }

    public static void quarterlyCheck (String roomNumber, ArrayList<String[]> reports, ArrayList<String> outstandingRooms, int month, int year){
        boolean flag = false;

        int currentQuarter = findQuarter(month);

        for (String[] report : reports) {
            String reportRoomNumber = report[0];
            int[] reportDate = Arrays.stream(report[1].replaceFirst("^(\\d{1,2})/(\\d{1,2})/(\\d{4}).*", "$1/$3").split("/"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int reportQuarter = findQuarter(reportDate[0]);

            if ((roomNumber.equalsIgnoreCase(reportRoomNumber)) && // if room numbers match
                    (reportQuarter == currentQuarter) && // & quarters match
                    (reportDate[1] == year)) { // & years match
                flag = true;
                break;
            }
        }

        if (flag == false) {
            outstandingRooms.add(roomNumber);
        }
    }

    public static int[] findAcademicYear(LocalDate currentDate) {
        int[] year;

        if (currentDate.getMonthValue() < 9) {
            year = new int[] {currentDate.getYear()-1,currentDate.getYear()};
        } else {
            year = new int[] {currentDate.getYear(),currentDate.getYear()+1};
        }

        return year;
    }
}
