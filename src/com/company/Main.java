package com.company;

import jpos.FiscalPrinter;
import jpos.FiscalPrinterConst;
import jpos.JposConst;
import jpos.config.JposEntry;
import jpos.config.JposRegPopulator;
import jpos.config.simple.SimpleEntry;
import jpos.config.simple.xml.SimpleXmlRegPopulator;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;









public class Main {
    public static JposEntry findEntry(JposRegPopulator populator, String logicalName) {
        JposEntry entry = null;
        Enumeration<?> entries = populator.getEntries();
        while (entries.hasMoreElements()) {
            try {
                entry = (JposEntry) entries.nextElement();
                if (entry.getLogicalName().equalsIgnoreCase(logicalName)) {
                    break;
                }
            } catch (Exception e) {
            }
        }

        if (entry == null) {
            entry = new SimpleEntry(logicalName, populator);
        }
        return entry;
    }

    public static void main(String[] args) throws Exception {
        JposRegPopulator populator = new SimpleXmlRegPopulator();
        JposEntry fptrEntry = findEntry(populator, "FPrint");
        populator.load("jpos.xml");

        FiscalPrinter fptr = new FiscalPrinter();
        fptr.open("FiscalPrinter");
        fptr.setDeviceEnabled(true);
        fptr.setPowerNotify(JposConst.JPOS_PN_ENABLED);
        //fptr.directIO(1043, null, "C:\\Users\\i.purikov\\AppData\\Roaming\\ATOL\\Emu\\АТОЛ 77Ф\\Atol-77F.con");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {

                String i = scanner.nextLine();

                String[] input = i.split(" ");

                if(input[0].equals("1075")) {
                    int id = 10;
                    String value = "Пользовательский параметр";
                    fptr.directIO(1075, new int[]{0, id}, value);
                }

                if(input[0].equals("1065")) {


                    fptr.directIO(1054, null, null);



//                    int[] result = new int[6];
//
//                    fptr.directIO(1065, result, null);
//
//                    int markingModeCheckingStatus = result[0];
//                    int markCheckingCount = result[1];
//                    int markSoldCount = result[2];
//                    boolean noticeIsBegin = (result[3] == 1);
//                    int noticeFreeMemory = result[4];
//                    int noticeCount = result[5];

//                    fptr.pingMarkingServer();
//
//// Ожидание результатов проверки связи с сервером ИСМ
//                    while (true) {
//                        fptr.getMarkingServerStatus();
//                        if (fptr.getParamBool(IFptr.LIBFPTR_PARAM_CHECK_MARKING_SERVER_READY))
//                            break;
//                    }
//
//                    long errorCode = fptr.getParamInt(IFptr.LIBFPTR_PARAM_MARKING_SERVER_ERROR_CODE);
//                    String errorDescription = fptr.getParamString(IFptr.LIBFPTR_PARAM_MARKING_SERVER_ERROR_DESCRIPTION);
//                    long responseTime = fptr.getParamInt(IFptr.LIBFPTR_PARAM_MARKING_SERVER_RESPONSE_TIME);





                    }





                      if(input[0].equals("test"))

                    {

                        fptr.directIO(1063, null, null);
                        fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                        fptr.beginFiscalReceipt(true);

                        fptr.setPOSID("111", "88888");

//                        fptr.directIO(1009, null, null);
                        int id = 10;
                        String value = "Проверка Документов";
                        fptr.directIO(1075, new int[]{0, id}, value);

                        int operationId = 7;
                        String operationData = "abcdefghijklmnopqrstuvwxyz";
                        String operationDate = "31122020173621";
                        fptr.directIO(1057, new int[]{operationId}, new String[]{operationData, operationDate});

                        String foiv = "010";
                        String date = "31122018";
                        String num = "12345678901234567890";
                        String industryAttr = "Ид1=Знач1&Ид2=Знач2&Ид3=Знач3";
                        fptr.directIO(1056, null, new String[]{foiv, date, num, industryAttr});

                        // Дополнительные реквизиты чека - СНО, телефон получателя электронного чека
                        fptr.directIO(1010, new int[]{5, 0}, String.valueOf(0x01) /*ОСН*/);
                        fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");

                        fptr.directIO(1060, null, null);
                        fptr.directIO(1063, null, null);



                        // byte[] markingCode = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x32, 0x34, 0x37, 0x33, 0x32, 0x31, 0x32, 0x31, 0x79, 0x43, 0x4a, 0x75, 0x50, 0x67, 0x43, 0x26, 0x49, 0x61, 0x64, 0x55, 0x62, 0x1d, 0x39, 0x31, 0x30, 0x30, 0x33, 0x41, 0x1d, 0x39, 0x32, 0x75, 0x6f, 0x4d, 0x72, 0x4b, 0x69, 0x6f, 0x39, 0x45, 0x46, 0x56, 0x46, 0x75, 0x74, 0x4b, 0x4d, 0x68, 0x51, 0x6a, 0x4b, 0x56, 0x64, 0x35, 0x4f, 0x77, 0x49, 0x74, 0x54, 0x46, 0x77, 0x78, 0x67, 0x31, 0x32, 0x43, 0x56, 0x6b, 0x38, 0x31, 0x66, 0x37, 0x62, 0x73, 0x43, 0x61, 0x54, 0x4f, 0x59, 0x34, 0x4b, 0x69, 0x39, 0x33, 0x45, 0x48, 0x6f, 0x72, 0x36, 0x4a, 0x68, 0x38, 0x57, 0x37, 0x61, 0x78, 0x31, 0x51, 0x37, 0x78, 0x76, 0x4f, 0x39, 0x75, 0x46, 0x5a, 0x77, 0x70, 0x43, 0x6b, 0x53, 0x6d, 0x6f, 0x64, 0x66, 0x6a, 0x41, 0x3d, 0x3d};
                        //byte[] markingCode = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4C, 0x47, 0x4C, 0x4D, 0x59, 0x51, 0x51, 0x1D, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1D, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6D, 0x4B, 0x36, 0x53, 0x4E, 0x38, 0x76, 0x76, 0x77, 0x6F, 0x78, 0x5A, 0x46, 0x6B, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6F, 0x4A, 0x4E, 0x4D, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6A, 0x61, 0x30, 0x34, 0x63, 0x3D};

                       //byte[] markingCode = {0x30,0x31,0x30,0x34,0x36,0x36,0x30,0x30,0x35,0x33,0x37,0x38,0x33,0x34,0x37,0x31,0x32,0x31,0x31,0x52,0x4c,0x4f,0x52,0x41,0x6c,0x4a,0x67,0x57,0x4a,0x70,0x4e,0x1d,0x39,0x31,0x30,0x30,0x33,0x41,0x1d,0x39,0x32,0x36,0x63,0x43,0x55,0x6f,0x50,0x75,0x73,0x4e,0x76,0x64,0x42,0x55,0x66,0x33,0x32,0x64,0x49,0x51,0x4e,0x49,0x73,0x73,0x73,0x73,0x6c,0x33,0x4f,0x55,0x76,0x48,0x58,0x39,0x53,0x4b,0x79,0x58,0x76,0x46,0x36,0x4b,0x4f,0x73,0x56,0x76,0x4f,0x74,0x77,0x55,0x4a,0x33,0x6e,0x59,0x4e,0x5a,0x71,0x2b,0x68,0x66,0x55,0x7a,0x62,0x38,0x43,0x44,0x59,0x42,0x38,0x4f,0x70,0x44,0x34,0x79,0x79,0x57,0x55,0x6c,0x55,0x34,0x68,0x2f,0x34,0x77,0x53,0x36,0x67,0x3d,0x3d};
                        byte[] markingCode = {0x30,0x31,0x30,0x33,0x30,0x34,0x31,0x30,0x39,0x34,0x37,0x38,0x37,0x34,0x34,0x33,0x32,0x31,0x35,0x51,0x62,0x61,0x67,0x21,0x1d,0x39,0x33,0x5a,0x6a,0x71,0x77,0x1d,0x33,0x31,0x30,0x33,0x30,0x30,0x30,0x33,0x35,0x33};
                        int markingType = 256; // тип марки (тег 2100)
                        int markingStatus = 2; // Планируемый статус марки (тег 2103)
                        int processingMode = 0; // Режим обработки КМ (тег 2102)
                        int quantity = 1000; // Кол-во
                        String fractionalQuantity = "1/2"; // Дробное кол-во товара
                        int measurementUnit = 0; // Мера количества предмета расчета (тег 2108)
                        int waitForResult = 1; // дождаться результата проверки (0 - не дожидаться; 1 - дожидаться)
                        long validationResult0 = 0;




                        int[] result1059 = new int[2];
                        fptr.directIO(1058, result1059, new Object[]{markingCode, fractionalQuantity, markingType, markingStatus, processingMode, quantity, measurementUnit, waitForResult});
                        System.out.printf("Ошибка локальной проверки = %s; Результат локальной проверки = %s \n", result1059[0], result1059[1]);

                        int[] ready = new int[1];
                        Object[] result = new Object[8];

                        int countSleep = 0;
                        while (countSleep != 1) {
                            System.out.printf("Ждем проверки = %d\n", countSleep);
                            countSleep --;

                            fptr.directIO(1059, ready, result);


                            if (ready[0] == 0) { //проверка не завершена
                                try {
                                    Thread.sleep(100);
                                }
                                catch(InterruptedException e) {
                                    Thread.sleep(100);
                                }
                                continue;
                            }

                            if (result[0] != null) {
                                validationResult0 = (long) ((Object[]) result)[0]; // 2106
                                System.out.print("2106:");
                                System.out.print(validationResult0);
                            }
                            if (result[1] != null) {
                                long validationResult1 = (long) ((Object[]) result)[1]; // error
                                System.out.print("\nОшибка онлайн проверки:");
                                System.out.print(validationResult1);
                            }
                            if (result[2] != null) {
                                long validationResult2 = (long) ((Object[]) result)[2]; // 2109
                                System.out.print("\n2109:");
                                System.out.print(validationResult2);
                            }
                            if (result[3] != null) {
                                long validationResult3 = (long) ((Object[]) result)[3]; // 2005
                                System.out.print("\n2005:");
                                System.out.print(validationResult3);
                            }
                            if (result[4] != null) {
                                long validationResult4 = (long) ((Object[]) result)[4]; // 2105
                                System.out.print("\n2105:");
                                System.out.print(validationResult4);
                            }
                            if (result[5] != null) {
                                long validationResult5 = (long) ((Object[]) result)[5]; // 2100
                                System.out.print("\n2100:");
                                System.out.print(validationResult5);
                            }
                            if (result[6] != null) {
                                long validationResult6 = (long) ((Object[]) result)[6]; // 2102
                                System.out.print("\n2102:");
                                System.out.print(validationResult6);
                            }
                            if (result[7] != null) {
                                String validationResult7 = (String) ((Object[]) result)[7]; // 2101
                                System.out.print("\n2101:");
                                System.out.print(validationResult7);
                            }
                            break;
                        }

                        int[] resultMark = new int[1];
                        fptr.directIO(1061, resultMark, null);

                        // Регистрация простейшей позиции
                        fptr.directIO(1049, new int[]{markingType, markingStatus,processingMode, (int)validationResult0 }, new Object[]{markingCode, fractionalQuantity});
                        int attrNumber = 3;
                        int attrType = 0;
                        String attrValue = "30";

                        String foivb = "007";
                        String dateb = "20210317";
                        String numb = "12";
                        String industryAttrb = "Ид1";
                        fptr.directIO(1055, null, new String[]{foivb, dateb, numb, industryAttrb});
                        fptr.directIO(1011, new int[]{2, 1}, "0");
                        fptr.directIO(1011, new int[]{attrNumber, attrType}, attrValue);
                        fptr.directIO(1011, new int[]{7, 0}, "10");
                        fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567");
                        fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик");
                        fptr.directIO(1011, new int[]{11, 1}, "г.Москва, ул.Азовская, д.15А");
                        fptr.directIO(1011, new int[]{12, 1}, "7724389850");
                        fptr.directIO(1011, new int[]{5, 2}, "1.11");
                        fptr.directIO(1011, new int[]{13, 1}, "QIWI");
                        fptr.directIO(1011, new int[]{9, 1}, "Перевод денежных средств");
                        fptr.directIO(1011, new int[]{17, 1}, "7707329152");
                        fptr.directIO(1011, new int[]{10, 1}, "+7(495)9262555");
                        fptr.directIO(1011, new int[]{14, 1}, "+7(495)8888888");
                        fptr.directIO(1011, new int[]{15, 1}, "+7(495)5555555");
                        fptr.directIO(1010, new int[]{18, 1}, "1085"); // 1085
                        fptr.directIO(1010, new int[]{19, 1}, "1086"); // 1086



                        fptr.printRecItem("good", 3990, 1000, 1, 3990, "10");
                        //fptr.printRecItem("good", 4000, 1000, 1, 4000, "00");

                        // Скидка-округление на чек
                        fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 90);
                        // Оплата
                        fptr.printRecTotal(7900, 7900, "00");


                        int strAlignment = 0;
                        String text = "Строка %d";

                        for (int j = 0; j < 8; ++j) {
                            fptr.directIO(1041, new int[]{strAlignment}, String.format(text, j+1));
                        }

                        int barcodeType = 11;
                        int barcodeScale = 4;
                        int barcodeHeight = 0;
                        int barcodePrintText = 0;
                        int barcodeAlignment = 2;
                        fptr.directIO(1002, new int[]{barcodeType, barcodeScale, barcodeHeight, barcodePrintText, barcodeAlignment}, "ДАННЫЕ ШК");


                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "https://check1.fsrar.ru?id=820ea49");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "6-fc85-44e9-b911-8bec38edf8ff&dt=2");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "003141459&cn=010000006284A3C2DDBDC");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "A0CCFF59104181AABEEA564B01879D7B13");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "2962128F586E5FF5D7A2F4BACB9A08E0A9");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "42ADB46A78B8587E957C5836E9E076D90A");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "998AB1F118FA7BA35");
                    // Печать строк и штрихкодов
                    fptr.directIO(1002, new int[]{11, 3, 0, 1, 2, 1}, "https://check1.fsrar.ru?id=820ea496-fc85-44e9-b911-8bec38edf8ff&dt=2003141459&cn=010000006284A3C2DDBDCA0CCFF59104181AABEEA564B01879D7B132962128F586E5FF5D7A2F4BACB9A08E0A942ADB46A78B8587E957C5836E9E076D90A998AB1F118FA7BA35");


                        // Закрытие чека
                        fptr.endFiscalReceipt(false);
                    }

                if (input[0].equals("obj")) {

//                После правок в рамках задачи ACP-2224, с помощью DIO 1058, проверка КМ, в которой 2103 = 1 или 3 проходит корректно.
//                Однако, при попытке добавить данный КМ в позицию чека с помощью DIO 1049, в котором передаю реквизит 2110 = 1 или 3, получаю исключение JPOS, чек не оформляется. Логи прикладываю, использую следующий пример:
//                {code}
                fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                fptr.beginFiscalReceipt(true);
                fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                fptr.directIO(1011, new int[]{4, 0}, "1"); // Призанк способа расчета

                    int id = 10;
                    String value = "Проверка Документов";
                    fptr.directIO(1075, new int[]{0, id}, value);

// Запись КМ, целое
//                int markingType = 256; // тип марки (тег 2100)
//                int markingStatus = 1; // Присвоенный  статус марки (тег 2110)
//                int processingMode = 0; // Режим обработки КМ (тег 2102)
//                int validationResult  = 15; // Значение реквизита 2106
//                byte[] markingCode = {0x32, 0x32, 0x4E, 0x30, 0x30, 0x30, 0x30, 0x32, 0x4E, 0x55, 0x35, 0x44, 0x42, 0x4B, 0x59, 0x44, 0x4F, 0x54, 0x31, 0x37, 0x49, 0x44, 0x39, 0x38, 0x30, 0x37, 0x32, 0x36, 0x30, 0x31, 0x39, 0x30, 0x31, 0x39, 0x36, 0x30, 0x38, 0x43, 0x57, 0x31, 0x41, 0x34, 0x58, 0x52, 0x35, 0x45, 0x4A, 0x37, 0x4A, 0x4B, 0x46, 0x58, 0x35, 0x30, 0x46, 0x48, 0x48, 0x47, 0x56, 0x39, 0x32, 0x5A, 0x52, 0x32, 0x47, 0x5A, 0x52, 0x5A};
//                fptr.directIO(1049, new int[]{markingType, markingStatus, processingMode, validationResult}, new Object[] {markingCode, null});

                fptr.printRecItem("Кроссовки Adidas Crazy 8 \"All-Star\"", 1250000, 1000, 1, 1250000, "");
// Оплата
                fptr.printRecTotal(1250000, 1250000, "00");
                    // Печать нефискальных строк, только для П5.0.
                    fptr.setTrailerLine(1, "Печать строки №1 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines
//                    fptr.setTrailerLine(2, "Печать строки №2 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines
//                    fptr.setTrailerLine(3, "Печать строки №3 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines
//                    fptr.setTrailerLine(4, "Печать строки №4 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines

// Закрытие чека
                fptr.endFiscalReceipt(false);
                }


                if (input[0].equals("Rosneft")) {
                    // Начать проверку КМ, мерный товар
                    byte[] markingCode = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4C, 0x47, 0x4C, 0x4D, 0x59, 0x51, 0x51, 0x1D, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1D, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6D, 0x4B, 0x36, 0x53, 0x4E, 0x38, 0x76, 0x76, 0x77, 0x6F, 0x78, 0x5A, 0x46, 0x6B, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6F, 0x4A, 0x4E, 0x4D, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6A, 0x61, 0x30, 0x34, 0x63, 0x3D};
                    String fractionalQuantity = "1/2"; // Дробное кол-во товара
                    int markingType = 256; // тип марки (тег 2100)
                    int markingStatus = 2; // Планируемый статус марки (тег 2003)
                    int processingMode = 0; // Режим обработки КМ (тег 2102)
                    int quantity = 1000; // Кол-во
                    int measurementUnit = 0; // Мера количества предмета расчета (тег 2108)
                    int waitForResult = 1; // дождаться результата проверки (0 - не дожидаться; 1 - дожидаться)
                    int[] result = new int[2];
                    fptr.directIO(1058, result, new Object[] {markingCode, fractionalQuantity, markingType, markingStatus, processingMode, quantity, measurementUnit, waitForResult});
                    System.out.printf("Ошибка локальной проверки = %s; Результат локальной проверки = %s \n", result[0],result[1]);

                    // Запрашиваем результат проверки на ИСМ
                    int[] ready = new int[1];
                    Object[] result2 = new Object[8];

                    while (true) {
                        fptr.directIO(1059, ready, result2);
                        if (ready[0] == 1)
                            break;
                    }
                    if (result2[0] != null) {
                        long validationResult0 = (long)((Object[]) result2)[0]; // 2106
                        System.out.print("2106:");
                        System.out.print(validationResult0);
                    }
                    if (result2[1] != null) {
                        long validationResult1 = (long)((Object[]) result2)[1]; // error
                        System.out.print("\nОшибка онлайн проверки:");
                        System.out.print(validationResult1);
                    }
                    if (result2[2] != null) {
                        long validationResult2 = (long)((Object[]) result2)[2]; // 2109
                        System.out.print("\n2109:");
                        System.out.print(validationResult2);
                    }
                    if (result2[3] != null) {
                        long validationResult3 = (long)((Object[]) result2)[3]; // 2005
                        System.out.print("\n2005:");
                        System.out.print(validationResult3);
                    }
                    if (result2[4] != null) {
                        long validationResult4 = (long)((Object[]) result2)[4]; // 2105
                        System.out.print("\n2105:");
                        System.out.print(validationResult4);
                    }
                    if (result2[5] != null) {
                        long validationResult5 = (long)((Object[]) result2)[5]; // 2100
                        System.out.print("\n2100:");
                        System.out.print(validationResult5);
                    }
                    if (result2[6] != null) {
                        long validationResult6 = (long)((Object[]) result2)[6]; // 2102
                        System.out.print("\n2102:");
                        System.out.print(validationResult6);
                    }
                    if (result2[7] != null) {
                        String validationResult7 = (String)((Object[]) result2)[7]; // 2101
                        System.out.print("\n2101:");
                        System.out.print(validationResult7);
                    }
                    // Подтвердить реализации маркированного товара
                    int[] result3 = new int[1];
                    fptr.directIO(1061, result3, null);
                    System.out.println("2106 = " + result3[0]);

                    // Начать проверку КМ, штучный товар
                    byte[] markingCode4 = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x37, 0x35, 0x38, 0x33, 0x30, 0x32, 0x31, 0x4D, 0x64, 0x45, 0x66, 0x78, 0x3A, 0x58, 0x70, 0x36, 0x59, 0x46, 0x64, 0x37, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x32, 0x39, 0x1D, 0x39, 0x32, 0x61, 0x51, 0x49, 0x51, 0x6B, 0x49, 0x37, 0x6F, 0x48, 0x58, 0x6D, 0x7A, 0x47, 0x2F, 0x6D, 0x64, 0x4B, 0x78, 0x7A, 0x43, 0x55, 0x43, 0x4B, 0x54, 0x4A, 0x48, 0x58, 0x6F, 0x42, 0x4F, 0x44, 0x64, 0x6D, 0x43, 0x64, 0x4D, 0x35, 0x6B, 0x38, 0x51, 0x6A, 0x37, 0x67, 0x61, 0x5A, 0x56, 0x32, 0x78, 0x62, 0x6E, 0x36, 0x36, 0x78, 0x42, 0x58, 0x47, 0x49, 0x4B, 0x72, 0x74, 0x66, 0x76, 0x71, 0x50, 0x49, 0x4E, 0x41, 0x32, 0x6A, 0x6B, 0x62, 0x6A, 0x79, 0x6A, 0x33, 0x2F, 0x4F, 0x2B, 0x6B, 0x79, 0x36, 0x6F, 0x75, 0x31, 0x4E, 0x41, 0x3D, 0x3D};
                    int markingType4 = 256; // тип марки (тег 2100)
                    int markingStatus4 = 1; // Планируемый статус марки (тег 2003)
                    int processingMode4 = 0; // Режим обработки КМ (тег 2102)
                    int waitForResult4 = 1; // дождаться результата проверки (0 - не дожидаться; 1 - дожидаться)

                    int[] result4 = new int[2];
                    fptr.directIO(1058, result4, new Object[] {markingCode4, null, markingType4, markingStatus4, processingMode4, null, null, waitForResult4});
                    System.out.printf("Ошибка локальной проверки = %s; Результат локальной проверки = %s \n", result[0],result[1]);

                    // Запрашиваем результат проверки на ИСМ
                    int[] ready5 = new int[1];
                    Object[] result5 = new Object[8];

                    while (true) {
                        fptr.directIO(1059, ready5, result5);
                        if (ready5[0] == 1)
                            break;
                    }
                    if (result5[0] != null) {
                        long validationResult0 = (long)((Object[]) result5)[0]; // 2106
                        System.out.print("2106:");
                        System.out.print(validationResult0);
                    }
                    if (result5[1] != null) {
                        long validationResult1 = (long)((Object[]) result5)[1]; // error
                        System.out.print("\nОшибка онлайн проверки:");
                        System.out.print(validationResult1);
                    }
                    if (result5[2] != null) {
                        long validationResult2 = (long)((Object[]) result5)[2]; // 2109
                        System.out.print("\n2109:");
                        System.out.print(validationResult2);
                    }
                    if (result5[3] != null) {
                        long validationResult3 = (long)((Object[]) result5)[3]; // 2005
                        System.out.print("\n2005:");
                        System.out.print(validationResult3);
                    }
                    if (result5[4] != null) {
                        long validationResult4 = (long)((Object[]) result5)[4]; // 2105
                        System.out.print("\n2105:");
                        System.out.print(validationResult4);
                    }
                    if (result5[5] != null) {
                        long validationResult5 = (long)((Object[]) result5)[5]; // 2100
                        System.out.print("\n2100:");
                        System.out.print(validationResult5);
                    }
                    if (result5[6] != null) {
                        long validationResult6 = (long)((Object[]) result5)[6]; // 2102
                        System.out.print("\n2102:");
                        System.out.print(validationResult6);
                    }
                    if (result5[7] != null) {
                        String validationResult7 = (String)((Object[]) result5)[7]; // 2101
                        System.out.print("\n2101:");
                        System.out.print(validationResult7);
                    }
                    // Подтвердить реализации маркированного товара
                    int[] result6 = new int[1];
                    fptr.directIO(1061, result6, null);
                    System.out.println("2106 = " + result6[0]);
                }
                if (input[0].equals("sell_Perekrestok_full")) {
                    fptr.setPOSID("122" , "Эркки Борг");
                    // Открытие чека
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);

                    // Дополнительные реквизиты чека - признак агента (пл.субагент, банковский пл.субагент), телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{1, 1}, "TESTMAIL@MAIL.ru");

                    // Регистрация перовой позиции, мерный товар
                    fptr.directIO(1011, new int[]{2, 1}, "0"); // Мера количества предмета расчета (тег 2108)
                    fptr.directIO(1011, new int[]{3, 0}, "33"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Призанк способа расчета
                    // Запись КМ, мерный товар
                    int markingType = 256; // тип марки (тег 2100)
                    int markingStatus = 2; // Присвоенный  статус марки (тег 2110)
                    int processingMode = 0; // Режим обработки КМ (тег 2102)
                    int validationResult = 15; // Значение реквизита 2106
                    String fractionalQuantity = "1/2"; // Дробное кол-во товара
                    byte[] markingCode = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4C, 0x47, 0x4C, 0x4D, 0x59, 0x51, 0x51, 0x1D, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1D, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6D, 0x4B, 0x36, 0x53, 0x4E, 0x38, 0x76, 0x76, 0x77, 0x6F, 0x78, 0x5A, 0x46, 0x6B, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6F, 0x4A, 0x4E, 0x4D, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6A, 0x61, 0x30, 0x34, 0x63, 0x3D};
                    fptr.directIO(1049, new int[]{markingType, markingStatus, processingMode, validationResult}, new Object[] {markingCode, fractionalQuantity});
                    fptr.printRecItem("КМ, дробный товар 1", 1250000, 1000, 1, 1250000, "");

                    // Регистрация второй позиции, штучный товар
                    fptr.directIO(1011, new int[]{2, 1}, "0"); // Мера количества предмета расчета (тег 2108)
                    fptr.directIO(1011, new int[]{3, 0}, "32"); // Признак предмета расчета, реквизит 1212
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Призанк способа расчета, реквизит 1214
                    // Запись КМ, штучный товар
                    int markingType2 = 256; // тип марки (тег 2100)
                    int markingStatus2 = 1; // Присвоенный  статус марки (тег 2110)
                    int processingMode2 = 0; // Планируемый статус марки (тег 2103)
                    int validationResult2 = 5; // Значение реквизита 2106
                    byte[] markingCode2 = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x37, 0x35, 0x38, 0x33, 0x30, 0x32, 0x31, 0x4D, 0x64, 0x45, 0x66, 0x78, 0x3A, 0x58, 0x70, 0x36, 0x59, 0x46, 0x64, 0x37, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x32, 0x39, 0x1D, 0x39, 0x32, 0x61, 0x51, 0x49, 0x51, 0x6B, 0x49, 0x37, 0x6F, 0x48, 0x58, 0x6D, 0x7A, 0x47, 0x2F, 0x6D, 0x64, 0x4B, 0x78, 0x7A, 0x43, 0x55, 0x43, 0x4B, 0x54, 0x4A, 0x48, 0x58, 0x6F, 0x42, 0x4F, 0x44, 0x64, 0x6D, 0x43, 0x64, 0x4D, 0x35, 0x6B, 0x38, 0x51, 0x6A, 0x37, 0x67, 0x61, 0x5A, 0x56, 0x32, 0x78, 0x62, 0x6E, 0x36, 0x36, 0x78, 0x42, 0x58, 0x47, 0x49, 0x4B, 0x72, 0x74, 0x66, 0x76, 0x71, 0x50, 0x49, 0x4E, 0x41, 0x32, 0x6A, 0x6B, 0x62, 0x6A, 0x79, 0x6A, 0x33, 0x2F, 0x4F, 0x2B, 0x6B, 0x79, 0x36, 0x6F, 0x75, 0x31, 0x4E, 0x41, 0x3D, 0x3D};
                    fptr.directIO(1049, new int[]{markingType2, markingStatus2, processingMode2, validationResult2}, new Object[] {markingCode2, null});
                    fptr.printRecItem("КМ, штучный товар 2", 1900000, 1000, 2, 1900000, "");
//                    fptr.printRecItem("12345678901234567890123456789", 1900000, 1000, 2, 1900000, "");

                    // Регистрация третьей позиции
                    fptr.directIO(1011, new int[]{2, 1}, "0"); // Мера количества предмета расчета (тег 2108)
                    fptr.directIO(1011, new int[]{3, 0}, "2"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Без маркировки 3", 3299997, 3000, 4, 1099999, "");
                    // Регистрация четвертой позиции
                    fptr.directIO(1011, new int[]{2, 1}, "11"); // Мера количества предмета расчета (тег 2108)
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Без маркировки 4", 1799760, 11537, 3, 155999, "");
                    // Регистрация пятой позиции
                    fptr.directIO(1011, new int[]{2, 1}, "41"); // Мера количества предмета расчета (тег 2108)
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Без маркировки 5", 9113354, 45567, 3, 199999, "");
                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 11);

                    // Оплата
                    fptr.printRecTotal(100000, 100000, "20");
                    fptr.printRecTotal(100000, 100000, "10");
                    fptr.printRecTotal(17163100, 17500000, "00");
//                    // Указываем пользовательские реквизиты
//                    String[] userProperty0 = new String[]{"ID10", "БАЛЛАМИ ВЫРУЧАЙКИ:       0.00"};
//                    fptr.directIO(1025, null, userProperty0);
//                    String[] userProperty = new String[]{"ID14", "КОД: 2021"};
//                    fptr.directIO(1025, null, userProperty);
//                    String[] userProperty1 = new String[]{"ID15", "ПРИНЯТО:            175000.00"};
//                    fptr.directIO(1025, null, userProperty1);

                    // Указываем пользовательские реквизиты
                    String[] userProperty0 = new String[]{"ID11", "КАРТА КЛУБА:  7789********9799"};
                    fptr.directIO(1025, null, userProperty0);
                    String[] userProperty = new String[]{"ID10", "БАЛАНС БАЛЛОВ:99999"};
                    fptr.directIO(1025, null, userProperty);
//                    String[] userProperty1 = new String[]{"ID13", "ПРИНЯТО:            175000.00"};
//                    fptr.directIO(1025, null, userProperty1);

                    // QR-код + оверлей
                    // Печать строк
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 1}, "");
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 1}, "*На артикул не предоставляется скидка");
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 1}, "................................................................");
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 1}, "Дата: 06.08.2021 10:00                             КПП:773345002");
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 1}, "");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "https://check1.fsrar.ru?id=820ea49");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "6-fc85-44e9-b911-8bec38edf8ff&dt=2");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "003141459&cn=010000006284A3C2DDBDC");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "A0CCFF59104181AABEEA564B01879D7B13");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "2962128F586E5FF5D7A2F4BACB9A08E0A9");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "42ADB46A78B8587E957C5836E9E076D90A");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "998AB1F118FA7BA35");
                    // Печать ШК
                    fptr.directIO(1002, new int[]{11, 3, 0, 1, 2}, "https://check1.fsrar.ru?id=820ea496-fc85-44e9-b911-8bec38edf8ff&dt=2003141459&cn=010000006284A3C2DDBDCA0CCFF59104181AABEEA564B01879D7B132962128F586E5FF5D7A2F4BACB9A08E0A942ADB46A78B8587E957C5836E9E076D90A998AB1F118FA7BA35");

                    // QR-код + оверлей в preItems, в шапке чека
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0, 3}, "Скачай мобильное приложение Пятёрочки в App Store или");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0, 3}, "Google Play");
                    fptr.directIO(1002, new int[]{11, 2, 0, 0, 2, 3}, "https://5ka.ru/app");

                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }







                if (input[0].equals("half")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    fptr.directIO(1009, null, null);


// Дополнительные реквизиты чека - СНО, телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{5, 0}, String.valueOf(0x01) /*ОСН*/);
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                   // fptr.directIO(1009, null, null);
                    fptr.directIO(1010, new int[]{7, 0}, "10");


                    fptr.directIO(1060, null, null);




                   byte[] markingCode1 = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4c, 0x47, 0x4c, 0x4d, 0x59, 0x51, 0x51, 0x1d, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1d, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6d, 0x4b, 0x36, 0x53, 0x4e, 0x38, 0x76, 0x76, 0x77, 0x6f, 0x78, 0x5a, 0x46, 0x6b, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6f, 0x4a, 0x4e, 0x4d, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6a, 0x61, 0x30, 0x34, 0x63, 0x3d};
                    String fractionalQuantity = "1/2"; // Дробное кол-во товара
                    int markingType = 256; // тип марки (тег 2100)
                    int markingStatus = 2; // Планируемый статус марки (тег 2103)
                    int processingMode = 0; // Режим обработки КМ (тег 2102)
                    int quantity = 1000; // Кол-во
                    int measurementUnit = 0; // Мера количества предмета расчета (тег 2108)
                    int waitForResult = 1; // дождаться результата проверки (0 - не дожидаться; 1 - дожидаться)
                    long validationResult0 = 0;




                    for(int a=0; a < 1; a++) {
                         byte[] markingCode = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4c, 0x47, 0x4c, 0x4d, 0x59, 0x51, 0x51, 0x1d, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1d, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6d, 0x4b, 0x36, 0x53, 0x4e, 0x38, 0x76, 0x76, 0x77, 0x6f, 0x78, 0x5a, 0x46, 0x6b, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6f, 0x4a, 0x4e, 0x4d, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6a, 0x61, 0x30, 0x34, 0x63, 0x3d};
                         fractionalQuantity = "1/2"; // Дробное кол-во товара
                         markingType = 256; // тип марки (тег 2100)
                         markingStatus = 2; // Планируемый статус марки (тег 2103)
                         processingMode = 0; // Режим обработки КМ (тег 2102)
                         quantity = 1000; // Кол-во
                         measurementUnit = 0; // Мера количества предмета расчета (тег 2108)
                         waitForResult = 1; // дождаться результата проверки (0 - не дожидаться; 1 - дожидаться)


                        int[] result1059 = new int[2];
                        fptr.directIO(1058, result1059, new Object[]{markingCode, fractionalQuantity, markingType, markingStatus, processingMode, quantity, measurementUnit, waitForResult});
                        System.out.printf("Ошибка локальной проверки = %s; Результат локальной проверки = %s \n", result1059[0], result1059[1]);


                        //Начать проверку КМ, целое


//
//                    int markingType = 256;
//                    String fractionalQuantity = "1/2";
//                    byte[] markingCode = {0x32, 0x32, 0x4E, 0x30, 0x30, 0x30, 0x30, 0x32, 0x4E, 0x55, 0x35, 0x44, 0x42, 0x4B, 0x59, 0x44, 0x4F, 0x54, 0x31, 0x37, 0x49, 0x44, 0x39, 0x38, 0x30, 0x37, 0x32, 0x36, 0x30, 0x31, 0x39, 0x30, 0x31, 0x39, 0x36, 0x30, 0x38, 0x43, 0x57, 0x31, 0x41, 0x34, 0x58, 0x52, 0x35, 0x45, 0x4A, 0x37, 0x4A, 0x4B, 0x46, 0x58, 0x35, 0x30, 0x46, 0x48, 0x48, 0x47, 0x56, 0x39, 0x32, 0x5A, 0x52, 0x32, 0x47, 0x5A, 0x52, 0x5A};
//                    int markingStatus = 2;
//                    int processingMode = 0;
//                    int quantity = 1000;
//                    int measurementUnit = 0;
//                    int waitForResult = 1;
//
//                    int[] result1059 = new int[2];
//                    fptr.directIO(1058, result1059, new Object[]{markingCode, fractionalQuantity, markingType, markingStatus,  processingMode, quantity, measurementUnit, waitForResult});




                        int[] ready = new int[1];
                        Object[] result = new Object[8];




                        Thread.sleep(2100);

                        fptr.directIO(1059, ready, result);




                        if (ready[0] == 1) {

                            //fptr.directIO(1060, null, null);
                            break;
                        }
                        else {
                            validationResult0 = (long) ((Object[]) result)[0];

                            //long validationResult0 = 0;
                            int j = (int) validationResult0;


                            if (result[0] != null) {
                                validationResult0 = (long) ((Object[]) result)[0]; // 2106
                                System.out.print("2106:");
                                System.out.print(validationResult0);
                            }

                            if (result[1] != null) {
                                long validationResult1 = (long) ((Object[]) result)[1]; // error
                                System.out.print("\nОшибка онлайн проверки:");
                                System.out.print(validationResult1);
                            }
                            if (result[2] != null) {
                                long validationResult2 = (long) ((Object[]) result)[2]; // 2109
                                System.out.print("\n2109:");
                                System.out.print(validationResult2);
                            }
                            if (result[3] != null) {
                                long validationResult3 = (long) ((Object[]) result)[3]; // 2005
                                System.out.print("\n2005:");
                                System.out.print(validationResult3);
                            }
                            if (result[4] != null) {
                                long validationResult4 = (long) ((Object[]) result)[4]; // 2105
                                System.out.print("\n2105:");
                                System.out.print(validationResult4);
                            }
                            if (result[5] != null) {
                                long validationResult5 = (long) ((Object[]) result)[5]; // 2100
                                System.out.print("\n2100:");
                                System.out.print(validationResult5);
                            }
                            if (result[6] != null) {
                                long validationResult6 = (long) ((Object[]) result)[6]; // 2102
                                System.out.print("\n2102:");
                                System.out.print(validationResult6);
                            }
                            if (result[7] != null) {
                                String validationResult7 = (String) ((Object[]) result)[7]; // 2101
                                System.out.print("\n2101:");
                                System.out.print(validationResult7);
                            }

                            int[] resultMark = new int[1];
                            fptr.directIO(1061, resultMark, null);

                        }


                        //while (true) {

                        //}


                        //fptr.directIO(1060, null, null);
                    }


                    int[] resultMark = new int[1];


                    fptr.directIO(1061, resultMark, null);

                    // Регистрация простейшей позиции
                    fptr.directIO(1049, new int[]{markingType, markingStatus,processingMode, (int)validationResult0 }, new Object[]{markingCode1, null});
                    int attrNumber = 3;
                    int attrType = 0;
                    String attrValue = "33";

                    String foivb = "007";
                    String dateb = "20210317";
                    String numb = "12";
                    String industryAttrb = "Ид1";
                    fptr.directIO(1055, null, new String[]{foivb, dateb, numb, industryAttrb});

                    fptr.directIO(1011, new int[]{7, 0}, "10");
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567");
                    fptr.directIO(1011, new int[]{9, 1}, "Перевод денежных средств");
                    fptr.directIO(1011, new int[]{10, 1}, "+7(495)9262555");
                    fptr.directIO(1011, new int[]{11, 1}, "г.Москва, ул.Азовская, д.15А");
                    fptr.directIO(1011, new int[]{12, 1}, "7724389850");
                    fptr.directIO(1011, new int[]{13, 1}, "QIWI");
                    fptr.directIO(1011, new int[]{14, 1}, "+7(495)8888888");
                    fptr.directIO(1011, new int[]{15, 1}, "+7(495)5555555");
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик");
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152");
                    fptr.directIO(1011, new int[]{2, 1}, "0");

                    fptr.directIO(1011, new int[]{attrNumber, attrType}, attrValue);
                    fptr.printRecItem("good", 3990, 1000, 1, 3990, "10");

// Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 90);

// Оплата
                    fptr.printRecTotal(7900, 7900, "00");
                    // Печать нефискальных строк, только для П5.0.
                    fptr.setTrailerLine(1, "Печать строки №1 в подвале", true); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(2, "Печать строки №2 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(3, "Печать строки №3 в подвале", true); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(4, "Печать строки №4 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines


// Закрытие чека
                    fptr.endFiscalReceipt(false);



                }

                if (input[0].equals("whole")) {


                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);


// Дополнительные реквизиты чека - СНО, телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{5, 0}, String.valueOf(0x01) /*ОСН*/);
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");

                    // Начать проверку КМ, дробное
//                    byte[] markingCode = {0x32, 0x32, 0x4E, 0x30, 0x30, 0x30, 0x30, 0x32, 0x4E, 0x55, 0x35, 0x44, 0x42, 0x4B, 0x59, 0x44, 0x4F, 0x54, 0x31, 0x37, 0x49, 0x44, 0x39, 0x38, 0x30, 0x37, 0x32, 0x36, 0x30, 0x31, 0x39, 0x30, 0x31, 0x39, 0x36, 0x30, 0x38, 0x43, 0x57, 0x31, 0x41, 0x34, 0x58, 0x52, 0x35, 0x45, 0x4A, 0x37, 0x4A, 0x4B, 0x46, 0x58, 0x35, 0x30, 0x46, 0x48, 0x48, 0x47, 0x56, 0x39, 0x32, 0x5A, 0x52, 0x32, 0x47, 0x5A, 0x52, 0x5A};
//                    String fractionalQuantity = "1/2"; // Дробное кол-во товара
//                    int markingType = 256; // тип марки (тег 2100)
//                    int markingStatus = 2; // Планируемый статус марки (тег 2103)
//                    int processingMode = 0; // Режим обработки КМ (тег 2102)
//                    int quantity = 1000; // Кол-во
//                    int measurementUnit = 0; // Мера количества предмета расчета (тег 2108)
//                    int waitForResult = 1; // дождаться результата проверки (0 - не дожидаться; 1 - дожидаться)
//
//                    int[] result = new int[2];
//                    fptr.directIO(1058, result, new Object[] {markingCode, fractionalQuantity, markingType, markingStatus, processingMode, quantity, measurementUnit, waitForResult});
//                    System.out.printf("Ошибка локальной проверки = %s; Результат локальной проверки = %s \n", result[0],result[1]);

                     //Начать проверку КМ, целое
                    byte[] markingCode = {0x30,  0x30,  0x30,  0x30,  0x30,  0x30,  0x34,  0x36,  0x32,  0x30,  0x39,  0x35,  0x39,  0x37,  0x66,  0x62,  0x55,  0x47,  0x33,  0x7a,  0x5a,  0x41,  0x43,  0x6c,  0x6f,  0x6a,  0x42,  0x39,  0x4c};
                    int markingType = 256; // тип марки (тег 2100)
                    int markingStatus = 1; // Планируемый статус марки (тег 2103)
                    int processingMode = 0; // Режим обработки КМ (тег 2102)
                    int waitForResult = 1; // дождаться результата проверки (0 - не дожидаться; 1 - дожидаться)

                    int[] result1059 = new int[2];
                    fptr.directIO(1058, result1059, new Object[] {markingCode, null, markingType, markingStatus, processingMode, null, null, waitForResult});
                    System.out.printf("Ошибка локальной проверки = %s; Результат локальной проверки = %s \n", result1059[0],result1059[1]);


//                    fptr.directIO(1060, null, null);
//                    int markingType = 256;
//                    String fractionalQuantity = "1/2";
//                    byte[] markingCode = {0x32, 0x32, 0x4E, 0x30, 0x30, 0x30, 0x30, 0x32, 0x4E, 0x55, 0x35, 0x44, 0x42, 0x4B, 0x59, 0x44, 0x4F, 0x54, 0x31, 0x37, 0x49, 0x44, 0x39, 0x38, 0x30, 0x37, 0x32, 0x36, 0x30, 0x31, 0x39, 0x30, 0x31, 0x39, 0x36, 0x30, 0x38, 0x43, 0x57, 0x31, 0x41, 0x34, 0x58, 0x52, 0x35, 0x45, 0x4A, 0x37, 0x4A, 0x4B, 0x46, 0x58, 0x35, 0x30, 0x46, 0x48, 0x48, 0x47, 0x56, 0x39, 0x32, 0x5A, 0x52, 0x32, 0x47, 0x5A, 0x52, 0x5A};
//                    int markingStatus = 2;
//                    int processingMode = 0;
//                    int quantity = 1000;
//                    int measurementUnit = 0;
//                    int waitForResult = 1;
//
//                    int[] result1059 = new int[2];
//                    fptr.directIO(1058, result1059, new Object[]{markingCode, fractionalQuantity, markingType, markingStatus,  processingMode, quantity, measurementUnit, waitForResult});


                    int[] ready = new int[1];
                    Object[] result = new Object[8];


                    Thread.sleep(1100);



                    while (true) {
                        fptr.directIO(1059, ready, result);
                        if (ready[0] == 1)
                            break;
                    }
                    long validationResult0 = (long)((Object[]) result)[0];

                    //long validationResult0 = 0;
                    int j = (int) validationResult0;


                    if (result[0] != null) {
                        validationResult0 = (long) ((Object[]) result)[0]; // 2106
                        System.out.print("2106:");
                        System.out.print(validationResult0);
                    }

                    if (result[1] != null) {
                        long validationResult1 = (long) ((Object[]) result)[1]; // error
                        System.out.print("\nОшибка онлайн проверки:");
                        System.out.print(validationResult1);
                    }
                    if (result[2] != null) {
                        long validationResult2 = (long) ((Object[]) result)[2]; // 2109
                        System.out.print("\n2109:");
                        System.out.print(validationResult2);
                    }
                    if (result[3] != null) {
                        long validationResult3 = (long) ((Object[]) result)[3]; // 2005
                        System.out.print("\n2005:");
                        System.out.print(validationResult3);
                    }
                    if (result[4] != null) {
                        long validationResult4 = (long) ((Object[]) result)[4]; // 2105
                        System.out.print("\n2105:");
                        System.out.print(validationResult4);
                    }
                    if (result[5] != null) {
                        long validationResult5 = (long) ((Object[]) result)[5]; // 2100
                        System.out.print("\n2100:");
                        System.out.print(validationResult5);
                    }
                    if (result[6] != null) {
                        long validationResult6 = (long) ((Object[]) result)[6]; // 2102
                        System.out.print("\n2102:");
                        System.out.print(validationResult6);
                    }
                    if (result[7] != null) {
                        String validationResult7 = (String) ((Object[]) result)[7]; // 2101
                        System.out.print("\n2101:");
                        System.out.print(validationResult7);
                    }

                    int[] resultMark = new int[1];
                    fptr.directIO(1061, resultMark, null);

                   // fptr.directIO(1062, null, null);

                    // Регистрация простейшей позиции
                    fptr.directIO(1049, new int[]{markingType, markingStatus,processingMode, (int)validationResult0 }, new Object[]{markingCode, null});
                    int attrNumber = 3;
                    int attrType = 0;
                    String attrValue = "33";

                    String foivb = "007";
                    String dateb = "20210317";
                    String numb = "12";
                    String industryAttrb = "Ид1";
                    fptr.directIO(1055, null, new String[]{foivb, dateb, numb, industryAttrb});
                    //fptr.directIO(1011, new int[]{2, 1}, "0");

                    fptr.directIO(1011, new int[]{attrNumber, attrType}, attrValue);
                    fptr.printRecItem("good", 3990, 1000, 1, 3990, "1");

// Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 90);

// Оплата
                    fptr.printRecTotal(7900, 7900, "00");

// Закрытие чека
                    fptr.endFiscalReceipt(false);


                }


                if (input[0].equals("1017")){
                    String[] result = new String[1];
                    int attrNumber = 1046;
                    int attrType = 1;

                    fptr.directIO(1017, new int[]{attrNumber, attrType}, result);
                    String attrValue = result[0];
                    System.out.println(attrValue);

                }







                if (input[0].equals("1.2")) {
                     fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                     fptr.beginFiscalReceipt(true);


// Дополнительные реквизиты чека - СНО, телефон получателя электронного чека
                     fptr.directIO(1010, new int[]{5, 0}, String.valueOf(0x01) /*ОСН*/);
                     fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");

// Нефискальный текст - разделитель позиций
                     fptr.printRecMessage("----------------------------------------------------------------");
                     int operationId = 7;
                     String operationData = "abcdefghijklmnopqrstuvwxyz";
                     String operationDate = "31122020173621";
                     fptr.directIO(1057, new int[]{operationId}, new String[]{operationData, operationDate});

                     String foiv = "010";
                     String date = "31122018";
                     String num = "12345678901234567890";
                     String industryAttr = "Ид1=Знач1&Ид2=Знач2&Ид3=Знач3";
                     fptr.directIO(1056, null, new String[]{foiv, date, num, industryAttr});

                     //fptr.directIO(1060, null, null);

                     int markingType = 256;
                     String fractionalQuantity = "1/2";
//                     byte[] markingCode = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4c, 0x47, 0x4c, 0x4d, 0x59, 0x51, 0x51, 0x1d, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1d, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6d, 0x4b, 0x36, 0x53, 0x4e, 0x38, 0x76, 0x76, 0x77, 0x6f, 0x78, 0x5a, 0x46, 0x6b, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6f, 0x4a, 0x4e, 0x4d, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6a, 0x61, 0x30, 0x34, 0x63, 0x3d};
                        byte[] markingCode = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x32, 0x30, 0x34, 0x39, 0x35, 0x39, 0x32, 0x31, 0x63, 0x57, 0x7a, 0x6c, 0x4a, 0x43, 0x30, 0x47, 0x28, 0x6e, 0x32, 0x4d, 0x2c};
                        int markingStatus = 2;
                     int processingMode = 0;
                     int quantity = 1000;
                     int measurementUnit = 0;
                     int waitForResult = 0;

                     int[] result1059 = new int[2];
                     fptr.directIO(1058, result1059, new Object[]{markingCode, fractionalQuantity, markingType, markingStatus,  processingMode, quantity, measurementUnit, waitForResult});


                     //fptr.directIO(1050, null, null);
//
//                     int[] timeout = {90000};
//                     fptr.directIO(1052, timeout, null);

                     int[] ready = new int[2];
                     Object[] result = new Object[8];

                     while (true) {
                         fptr.directIO(1059, ready, result);
                         if (ready[0] == 1)
                             break;
                     }
                     long validationResult0 = (long)((Object[]) result)[0];

                     //long validationResult0 = 0;
                     int j = (int) validationResult0;


                     if (result[0] != null) {
                        validationResult0 = (long) ((Object[]) result)[0]; // 2106
                         System.out.print("2106:");
                         System.out.print(validationResult0);
                     }

                     if (result[1] != null) {
                         long validationResult1 = (long) ((Object[]) result)[1]; // error
                         System.out.print("\nОшибка онлайн проверки:");
                         System.out.println(validationResult1);
                     }
                     if (result[2] != null) {
                         long validationResult2 = (long) ((Object[]) result)[2]; // 2109
                         System.out.print("\n2109:");
                         System.out.print(validationResult2);
                     }
                     if (result[3] != null) {
                         long validationResult3 = (long) ((Object[]) result)[3]; // 2005
                         System.out.print("\n2005:");
                         System.out.print(validationResult3);
                     }
                     if (result[4] != null) {
                         long validationResult4 = (long) ((Object[]) result)[4]; // 2105
                         System.out.print("\n2105:");
                         System.out.print(validationResult4);
                     }
                     if (result[5] != null) {
                         long validationResult5 = (long) ((Object[]) result)[5]; // 2100
                         System.out.print("\n2100:");
                         System.out.print(validationResult5);
                     }
                     if (result[6] != null) {
                         long validationResult6 = (long) ((Object[]) result)[6]; // 2102
                         System.out.print("\n2102:");
                         System.out.print(validationResult6);
                     }
                     if (result[7] != null) {
                         String validationResult7 = (String) ((Object[]) result)[7]; // 2101
                         System.out.print("\n2101:");
                         System.out.print(validationResult7);
                     }

                     int[] resultMark = new int[1];

                     fptr.directIO(1061, resultMark, null);



// Регистрация простейшей позиции
                     fptr.directIO(1049, new int[]{markingType, markingStatus, processingMode, (int)validationResult0 }, new Object[]{markingCode, fractionalQuantity});


                    int typeCode = 0;
                    String[] productCodes = new String[]{
                            "46198488",
                            "4606203090785",
                            "14601234567890",
                            "010842430916235121tafGFwyE=DZdL"

                    };

                    for (String productCode : productCodes) {
                        fptr.directIO(1064, new int[]{typeCode}, productCode);
                    }


                     String foivb = "007";
                     String dateb = "20210317";
                     String numb = "12";
                     String industryAttrb = "Ид1";
                     fptr.directIO(1055, null, new String[]{foivb, dateb, numb, industryAttrb});

                 //   fptr.directIO(1011, new int[]{3, 0}, "33"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
//
                     int attrNumber = 3;
                     int attrType = 0;
                     String attrValue = "33";
                     fptr.directIO(1011, new int[]{attrNumber, attrType}, attrValue);
                     fptr.directIO(1011, new int[]{2, 1}, "0");
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1010, new int[]{2, 1}, "Петрова П.П."); // Получатель, 1227
                    fptr.directIO(1010, new int[]{3, 1}, "526317984689"); // ИНН Получателя, 1228
                    fptr.directIO(1010, new int[]{4, 1}, "123123321321@mail.ru");// 1117 flhtc jnghfdbntkz

                    fptr.directIO(1010, new int[]{18, 1}, "1085"); // 1085
                    fptr.directIO(1010, new int[]{19, 1}, "1086"); // 1086
                    fptr.directIO(1010, new int[]{51, 1}, "30.01.1977"); // 1243
                    fptr.directIO(1010, new int[]{52, 0}, "067"); // 1244
                    fptr.directIO(1010, new int[]{53, 0}, "99"); // 1245
                    fptr.directIO(1010, new int[]{54, 1}, "Passport"); // 1246
                    fptr.directIO(1010, new int[]{55, 1}, "Москва"); // 1254


                    fptr.printRecItem("good", 3990, 1000, 1, 3990, "1");

// Скидка-округление на чек
                     fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 90);

// Оплата
                     fptr.printRecTotal(7900, 7900, "00");

                    fptr.setHeaderLine(1, "⤊01⤊⇌АО \"РН-Ростовнефтепродукт АТОЛ\"", false);
                    fptr.setHeaderLine(2, "", false);
                    fptr.setHeaderLine(3, "", false);
                    fptr.setHeaderLine(4, "", false);


// Закрытие чека
                     fptr.endFiscalReceipt(false);
                 }

                     if (input[0].equals("1058")) {

                         String[] result = new String[2];
                         int documentNumber = 12;

                         fptr.directIO(1008, new int[]{documentNumber}, result);
                         String ofdSign = result[0];
                         String ticketDateTime = result[1];
                     }

                if (input[0].equals("1070")) {

                    int[] result = new int[2];

                    fptr.directIO(1070, result, null);

                    boolean responseToRequestUnreliableMarkingCodes = (result[0] == 1);
                    boolean responseToNoticeUnreliableMarkingCodes = (result[1] == 1);
                }

                if (input[0].equals("1074")) {

                    String[] result = new String[5];
                    result[4] = "ABC";

                    fptr.directIO(1074, null, result);

                    String receiptNumber = result[0];
                    String receiptSign = result[1];
                    String receiptDateTime = result[2];
                    String receiptSum = result[3];

                }


                if (input[0].equals("1073")) {

                    int docNumber = 1189;
                    String[] result = new String[1];

                    fptr.directIO(1073, new int[]{docNumber}, result);
                    String jsonDocument = result[0];
                }

                     if (input[0].equals("1064")) {

// Открытие чека
                         fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                         fptr.beginFiscalReceipt(true);

// Дополнительные реквизиты чека - СНО, телефон получателя электронного чека
                         fptr.directIO(1010, new int[]{5, 0}, String.valueOf(0x01) /*ОСН*/);
                         fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");

// Нефискальный текст - разделитель позиций
                         fptr.printRecMessage("----------------------------------------------------------------");
                         fptr.directIO(1011, new int[]{3, 0}, String.valueOf(1)); // Признак предмета расчета
// Регистрация простейшей позиции
                         int typeCode = 0;
                         String[] productCodes = new String[]{
                                 "46198488",
                                 "4606203090785",
                                 "14601234567890",
                                 "0104660053783471211RLORAlJgWJpN"

                         };

                         for (String productCode : productCodes) {
                             fptr.directIO(1064, new int[]{typeCode}, productCode);
                         }


                         fptr.printRecItem("Товар 2", 3990, 1000, 1, 3990, "0");

// Оплата
                         fptr.printRecTotal(3990, 3990, "00");

// Закрытие чека
                         fptr.endFiscalReceipt(false);
                         //fptr.close();

                         String[] userProperty0 = new String[]{"ID11", "БАЛЛАМИ ВЫРУЧАЙКИ:   11125.00"};
                         String[] userProperty1 = new String[]{"ID12", "Карта Клуба : 1231412341234123"};
                         String[] userProperty2 = new String[]{"ID13", "Касса 999"};
                         fptr.directIO(1025, null, userProperty0);
                         fptr.directIO(1025, null, userProperty1);
                         fptr.directIO(1025, null, userProperty2);


                         fptr.setHeaderLine(1, "⤊03⤊⇌АО \"РН-Ростовнефтепродукт\"", false);
                         fptr.setHeaderLine(2, "⤊03⤊⇌АЗС 37", false);
                         fptr.setHeaderLine(3, "⤊03⤊⇌РО, г. Батайск, Ленина, 3", false);
                     }


                 if (input[0].equals("1050")){
                     fptr.directIO(1050, null, null);
                 }


                if (input[0].equals("1051")) {
                    // Добавить первый КМ на проверку в буфер драйвера, дробный товар
                    int markingType = 256;
                    byte[] markingCode = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4C, 0x47, 0x4C, 0x4D, 0x59, 0x51, 0x51, 0x1D, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1D, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6D, 0x4B, 0x36, 0x53, 0x4E, 0x38, 0x76, 0x76, 0x77, 0x6F, 0x78, 0x5A, 0x46, 0x6B, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6F, 0x4A, 0x4E, 0x4D, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6A, 0x61, 0x30, 0x34, 0x63, 0x3D};
                    int markingStatus = 2;
                    int processingMode = 0;
                    int quantity = 1000;
                    int measurementUnit = 0;
                    int waitForResult = 1;
                    String fractionalQuantity = "1/2";
                    fptr.directIO(1051, null, new Object[] {markingCode, fractionalQuantity, markingType, markingStatus, processingMode, quantity, measurementUnit, waitForResult});

                    // Добавить второй КМ на проверку в буфер драйвера, штучный товар
                    int markingType2 = 256;
                    byte[] markingCode2 = {0x30, 0x31, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x30, 0x31, 0x32, 0x33, 0x32, 0x31, 0x58, 0x48, 0x65, 0x22, 0x49, 0x6D, 0x51, 0x3E, 0x2A, 0x41, 0x26, 0x6A, 0x4F, 0x4C, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x38, 0x42, 0x1D, 0x39, 0x32, 0x42, 0x43, 0x42, 0x72, 0x33, 0x59, 0x52, 0x44, 0x70, 0x72, 0x4D, 0x31, 0x41, 0x41, 0x57, 0x50, 0x6B, 0x6A, 0x45, 0x2F, 0x52, 0x61, 0x74, 0x50, 0x4D, 0x37, 0x58, 0x79, 0x6C, 0x74, 0x45, 0x74, 0x71, 0x4F, 0x54, 0x56, 0x34, 0x59, 0x39, 0x62, 0x4F, 0x74, 0x6E, 0x65, 0x67, 0x51, 0x4C, 0x7A, 0x65, 0x68, 0x31, 0x4F, 0x56, 0x75, 0x4F, 0x5A, 0x48, 0x4D, 0x66, 0x51, 0x44, 0x53, 0x4D, 0x71, 0x54, 0x6E, 0x58, 0x6A, 0x49, 0x63, 0x4D, 0x38, 0x59, 0x62, 0x32, 0x30, 0x71, 0x4C, 0x72, 0x34, 0x64, 0x2B, 0x59, 0x6B, 0x66, 0x67, 0x3D, 0x3D};
                    int markingStatus2 = 1;
                    int processingMode2 = 0;
                    int waitForResult2 = 1;
                    fptr.directIO(1051, null, new Object[] {markingCode2, null, markingType2, markingStatus2, processingMode2, null, null, waitForResult2});


// Добавление марок в таблицу проверенных КМ
                    fptr.directIO(1071, null, null);



                }




                if (input[0].equals("1052")) {
                    int[] timeout = {90000};
                    fptr.directIO(1052, timeout, null);
                }

                if (input[0].equals("1053")) {
                    int[] iArg = new int[10];
                    String[] sArg = new String[2];

                    iArg[0] = 0;
                    fptr.directIO(1053, iArg, sArg);

                    int driverError = iArg[0];
                    int localValidationError = iArg[1];
                    int localValidationResult = iArg[2];
                    int onlineValidationError = iArg[3];
                    int onlineValidationResult = iArg[4];
                    int requestProcessingCode = iArg[5];
                    int requestProcessingResult = iArg[6];
                    int statusInfo = iArg[7];
                    int markingCodeType = iArg[8];
                    int processingMode = iArg[9];

                    String productID = sArg[0];
                    String driverErrorDescription = sArg[1];
                }

                if (input[0].equals("1049")) {
                    int markingType = 256;
                    byte[] markingCode = {0x32, 0x32, 0x4E, 0x30, 0x30, 0x30, 0x30, 0x32, 0x4E, 0x55, 0x35, 0x44, 0x42, 0x4B, 0x59, 0x44, 0x4F, 0x54, 0x31, 0x37, 0x49, 0x44, 0x39, 0x38, 0x30, 0x37, 0x32, 0x36, 0x30, 0x31, 0x39, 0x30, 0x31, 0x39, 0x36, 0x30, 0x38, 0x43, 0x57, 0x31, 0x41, 0x34, 0x58, 0x52, 0x35, 0x45, 0x4A, 0x37, 0x4A, 0x4B, 0x46, 0x58, 0x35, 0x30, 0x46, 0x48, 0x48, 0x47, 0x56, 0x39, 0x32, 0x5A, 0x52, 0x32, 0x47, 0x5A, 0x52, 0x5A};
                    int markingStatus = 2;
                    int processingMode = 0;
                    int validationResult = 15;
                    String fractionalQuantity = "1/2";

                    fptr.directIO(1049, new int[]{markingType, markingStatus, processingMode, validationResult}, new Object[] {markingCode, fractionalQuantity});
                }


                if (input[0].equals("1058")) {
                    int markingType = 256;
                    String fractionalQuantity = "1/2";
                    byte[] markingCode = {0x32, 0x32, 0x4E, 0x30, 0x30, 0x30, 0x30, 0x32, 0x4E, 0x55, 0x35, 0x44, 0x42, 0x4B, 0x59, 0x44, 0x4F, 0x54, 0x31, 0x37, 0x49, 0x44, 0x39, 0x38, 0x30, 0x37, 0x32, 0x36, 0x30, 0x31, 0x39, 0x30, 0x31, 0x39, 0x36, 0x30, 0x38, 0x43, 0x57, 0x31, 0x41, 0x34, 0x58, 0x52, 0x35, 0x45, 0x4A, 0x37, 0x4A, 0x4B, 0x46, 0x58, 0x35, 0x30, 0x46, 0x48, 0x48, 0x47, 0x56, 0x39, 0x32, 0x5A, 0x52, 0x32, 0x47, 0x5A, 0x52, 0x5A};
                    int markingStatus = 2;
                    int processingMode = 0;
                    int quantity = 1000;
                    int measurementUnit = 0;
                    int waitForResult = 1;

                    int[] result = new int[2];
                    fptr.directIO(1058, result, new Object[]{markingCode, fractionalQuantity, markingType, markingStatus, processingMode, quantity, measurementUnit, waitForResult});
                }

              //  fptr.close();


                if (input[0].equals("1058")) {
                    int markingType = 256;
                    String fractionalQuantity = "1/2";
                    byte[] markingCode = {0x32, 0x32, 0x4E, 0x30, 0x30, 0x30, 0x30, 0x32, 0x4E, 0x55, 0x35, 0x44, 0x42, 0x4B, 0x59, 0x44, 0x4F, 0x54, 0x31, 0x37, 0x49, 0x44, 0x39, 0x38, 0x30, 0x37, 0x32, 0x36, 0x30, 0x31, 0x39, 0x30, 0x31, 0x39, 0x36, 0x30, 0x38, 0x43, 0x57, 0x31, 0x41, 0x34, 0x58, 0x52, 0x35, 0x45, 0x4A, 0x37, 0x4A, 0x4B, 0x46, 0x58, 0x35, 0x30, 0x46, 0x48, 0x48, 0x47, 0x56, 0x39, 0x32, 0x5A, 0x52, 0x32, 0x47, 0x5A, 0x52, 0x5A};
                    int markingStatus = 2;
                    int processingMode = 0;
                    int quantity = 2000;
                    int measurementUnit = 0;
                    int waitForResult = 1;

                    int[] result = new int[2];
                    fptr.directIO(1058, result, new Object[] {markingCode, fractionalQuantity, markingType, markingStatus, processingMode, quantity, measurementUnit, waitForResult});

                }

                if (input[0].equals("x")) {
                    fptr.printXReport();
                }
                if (input[0].equals("version_firmware")) {
                    String[] result = new String[1];
                    fptr.getData(FiscalPrinterConst.FPTR_GD_FIRMWARE, null, result);
                }
                if (input[0].equals("z")) {
                    fptr.directIO(51, new int[]{1021, 5, 0}, new String[]{"IVANOV"});
                    fptr.directIO(51, new int[]{1203, 5, 0}, new String[]{"7899156648"});
                    int documentType = 2;

                    fptr.directIO(1009, new int[]{documentType}, null);
//                    fptr.directIO(61, null, new FiscalProperty(1203, 5, "502805064090", false));
                    fptr.printZReport();
                }
                if (input[0].equals("tekras")) {
                    // отчет о состоянии расчетов
                    fptr.printReport(100, null, null);
                }
                if (input[0].equals("cashin")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_CASH_IN);
                    fptr.beginFiscalReceipt(true);
                    // Внесение сумм
                    fptr.printRecCash(10000);
                    // Закрытие внесения
                    fptr.endFiscalReceipt(false);
                }


                if (input[0].equals("cashout")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_CASH_OUT);
                    fptr.beginFiscalReceipt(true);
                    // Внесение сумм
                    fptr.printRecCash(1000);
                    // Закрытие внесения
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("otmena_cashin")) {
                    fptr.printRecVoid("");
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("sost_smena")) {
                    fptr.getPhysicalDeviceDescription();
                    // Состояние крышки
                    fptr.getCoverOpen();
                    // Состояние бумаги
                    fptr.getRecEmpty();
                    // Состояние смены
                    fptr.getDayOpened();
                    String[] str = new String[1];
                    fptr.setDateType(FiscalPrinterConst.FPTR_DT_RTC);
                    fptr.getDate(str);
                    System.out.println(str[0]);
                    String[] str1 = new String[1];
                    fptr.setDateType(FiscalPrinterConst.FPTR_DT_RTC);
                    fptr.getDate(str1);
                    System.out.println(str1[0]);
                    String data[] = new String[1];
                    fptr.getData(3, null, data);
                    System.out.println(data[0]);
                }
                if (input[0].equals("lastdoc")) {
                    String[] result = new String[3];
                    fptr.directIO(1007, null, result);
                    String documentNumber = result[0];
                    String documentSign = result[1];
                    String documentDateTime = result[2];
                }
                if (input[0].equals("sn")) {
                    String[] data = new String[1];
                    fptr.getData(9, null, data);
                    System.out.println("serial = " + data[0]);
                }


                if (input[0].equals("1920")) {
                    fptr.setPOSID("55", "88888");

                    fptr.directIO(1009, null, null);

                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);

                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");


                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1010, new int[]{2, 1}, "Петрова П.П."); // Получатель, 1227
                    fptr.directIO(1010, new int[]{3, 1}, "526317984689"); // ИНН Получателя, 1228
                    fptr.directIO(1010, new int[]{4, 1}, "123123321321@mail.ru");// 1117 flhtc jnghfdbntkz

                    fptr.directIO(1010, new int[]{18, 1}, "1085"); // 1085
                    fptr.directIO(1010, new int[]{19, 1}, "1086"); // 1086
                    fptr.directIO(1010, new int[]{51, 1}, "30.01.1977"); // 1243
                    fptr.directIO(1010, new int[]{52, 0}, "067"); // 1244
                    fptr.directIO(1010, new int[]{53, 0}, "99"); // 1245
                    fptr.directIO(1010, new int[]{54, 1}, "Passport"); // 1246
                    fptr.directIO(1010, new int[]{55, 1}, "Москва"); // 1254


                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика

                    // Регистрация 1-ой позиции.
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
//                    fptr.directIO(1011, new int[]{4, 0}, "3"); // Признак способа расчета
                    fptr.printRecItem("230069 Конфеты Метель-раз 1кг", 206742, 15582, 2, 13268, "St");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 32, 2);
                    // Регистрация 2-ой позиции.
                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("1863 Напиток COCA-COLA газ.ж/б 0.33л", 837600, 200000, 1, 4188, "St");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 4188, 2);
                    // Регистрация 3-ой позиции.
                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("3416193 Вин.САН.ПУЛ.СОЛ.к.сух.12% 0.75л", 37999, 1000, 1, 37999, "St");
//                    // Регистрация 4-ой позиции.

                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "2"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
//                    int[] markingType = {1};
//                    byte[] markingCode = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x37, 0x35, 0x38, 0x33, 0x30, 0x32, 0x31, 0x4D, 0x64, 0x45, 0x66, 0x78, 0x3A, 0x58, 0x70, 0x36, 0x59, 0x46, 0x64, 0x37, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x32, 0x39, 0x1D, 0x39, 0x32, 0x61, 0x51, 0x49, 0x51, 0x6B, 0x49, 0x37, 0x6F, 0x48, 0x58, 0x6D, 0x7A, 0x47, 0x2F, 0x6D, 0x64, 0x4B, 0x78, 0x7A, 0x43, 0x55, 0x43, 0x4B, 0x54, 0x4A, 0x48, 0x58, 0x6F, 0x42, 0x4F, 0x44, 0x64, 0x6D, 0x43, 0x64, 0x4D, 0x35, 0x6B, 0x38, 0x51, 0x6A, 0x37, 0x67, 0x61, 0x5A, 0x56, 0x32, 0x78, 0x62, 0x6E, 0x36, 0x36, 0x78, 0x42, 0x58, 0x47, 0x49, 0x4B, 0x72, 0x74, 0x66, 0x76, 0x71, 0x50, 0x49, 0x4E, 0x41, 0x32, 0x6A, 0x6B, 0x62, 0x6A, 0x79, 0x6A, 0x33, 0x2F, 0x4F, 0x2B, 0x6B, 0x79, 0x36, 0x6F, 0x75, 0x31, 0x4E, 0x41, 0x3D, 0x3D};
//                    fptr.directIO(1040, markingType, markingCode);
                    fptr.printRecItem("*3276878 СИГАРЕТЫ PARLIAM.NIGHT BLUE 1П.", 136000, 8000, 1, 17000, "ST");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 17000, 2);
                    fptr.printRecMessage(" СКИДКА:                  0.00    ПОДЫТОГ:               424.97");





                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 41);

                    fptr.printRecSubtotal(200000);
                    //fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "СЭКОНОМИЛИ: RUB -69.90");
                    //fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    //fptr.printRecMessage("ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    // Оплата
                    fptr.printRecTotal(2000000, 2000000, "00");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 2}, "СЭКОНОМИЛИ: RUB -69.90");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "2020 DON'T CRY to NIGHT");

                    //fptr.printRecMessage("ПОЛУЧИТЕ 1 ФИШЕК");
                    //fptr.printRecMessage("ЧЕК-N 1005      ПОЗ 9       КАС 1       КСР 002");

                    // Указываем пользовательские реквизиты 15000 и 15001 (x5)
                    // String[] userProperty0 = new String[]{"ID10", "БАЛЛАМИ ВЫРУЧАЙКИ:      25.00"};
                    //fptr.directIO(1025, null, userProperty0);
                    int strAlignment = 0;
                    String text = "Строка %d";

                    for (int j = 0; j < 8; ++j) {
                        fptr.directIO(1041, new int[]{strAlignment}, String.format(text, j+1));
                    }

                    int barcodeType = 11;
                    int barcodeScale = 4;
                    int barcodeHeight = 0;
                    int barcodePrintText = 0;
                    int barcodeAlignment = 0;
                    fptr.directIO(1002, new int[]{barcodeType, barcodeScale, barcodeHeight, barcodePrintText, barcodeAlignment}, "ДАННЫЕ ШК");


                    fptr.directIO(1041, new int[]{2, 0, 0, 0, 0}, "https://check1.fsrar.ru?id=820ea49");
                    fptr.directIO(1041, new int[]{2, 0, 0, 0, 0}, "6-fc85-44e9-b911-8bec38edf8ff&dt=2");
                    fptr.directIO(1041, new int[]{2, 0, 0, 0, 0}, "003141459&cn=010000006284A3C2DDBDC");
                    fptr.directIO(1041, new int[]{2, 0, 0, 0, 0}, "A0CCFF59104181AABEEA564B01879D7B13");
                    fptr.directIO(1041, new int[]{2, 0, 0, 0, 0}, "2962128F586E5FF5D7A2F4BACB9A08E0A9");
                    fptr.directIO(1041, new int[]{2, 0, 0, 0, 0}, "42ADB46A78B8587E957C5836E9E076D90A");
                    fptr.directIO(1041, new int[]{2, 0, 0, 0, 0}, "998AB1F118FA7BA35");
                    // Печать строк и штрихкодов
                    fptr.directIO(1002, new int[]{11, 3, 0, 1, 0, 1}, "https://check1.fsrar.ru?id=820ea496-fc85-44e9-b911-8bec38edf8ff&dt=2003141459&cn=010000006284A3C2DDBDCA0CCFF59104181AABEEA564B01879D7B132962128F586E5FF5D7A2F4BACB9A08E0A942ADB46A78B8587E957C5836E9E076D90A998AB1F118FA7BA35");

                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");


                    fptr.setHeaderLine(1, "⤊01⤊⇌АО \"РН-Ростовнефтепродукт АТОЛ\"", false);
                    fptr.setHeaderLine(2, "", false);
                    fptr.setHeaderLine(3, "", false);
                    fptr.setHeaderLine(4, "", false);




                    fptr.endFiscalReceipt(false);

                }

                if (input[0].equals("openshift")) {
                    //fptr.directIO(1038, null, new String[]{"Эркки Борг"});
                    //fptr.directIO(1027, null, new String[]{"7724389850"});
                    fptr.directIO(51, new int[]{1021, 5, 0}, new String[]{"Эррки Борг"});
                    fptr.directIO(51, new int[]{1203, 5, 0}, new String[]{"7724389850"});
//                    fptr.directIO(61, (int[]) null, new FiscalProperty(1021, 5, "Эррки Борг", true));
//                    fptr.directIO(61, (int[]) null, new FiscalProperty(1203, 5, "7724389850", true));
//                    fptr.setPOSID("55" , "Эркки Борг");
                    fptr.directIO(1003, null, null);
                }
                if (input[0].equals("sell")) {
//                    int settingsID = 244;
//                    String settingValue = "Подарочной картой";
//                    fptr.directIO(1005, new int[]{settingsID}, settingValue);
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
//                    fptr.printRecMessage("Печать строки (Метод №1)");
//                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "Печать строки (Метод №2)");
                    // Дополнительные реквизиты чека - признак агента (пл.субагент, банковский пл.субагент), телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{1, 1}, "client@client.ru");
                    fptr.directIO(1010, new int[]{2, 1}, "Оливер Хьюз");
                    fptr.directIO(1010, new int[]{3, 1}, "526317984689");
//                    fptr.directIO(1010, new int[]{5, 0}, "1"); // система налогообложения (тег ФН 1055)
//                    fptr.directIO(1010, new int[]{7, 0}, "64");
//                    fptr.directIO(1010, new int[]{8, 1}, "+7(495)1234567");
//                    fptr.directIO(1010, new int[]{9, 1}, "Перевод денежных средств");
//                    fptr.directIO(1010, new int[]{10, 1}, "+7(495)9262555");
//                   fptr.directIO(1010, new int[]{11, 1}, "г.Москва, ул.Азовская, д.15А");
//                    fptr.directIO(1010, new int[]{12, 1}, "7724389850");
//                    fptr.directIO(1010, new int[]{13, 1}, "QIWI");
//                    fptr.directIO(1010, new int[]{14, 1}, "+7(495)8888888");
//                    fptr.directIO(1010, new int[]{15, 1}, "+7(495)5555555");
                    // Регистрация перовй позиции с дополнительными реквизитами
                    fptr.directIO(1011, new int[]{7, 0}, "64");
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567");
                    fptr.directIO(1011, new int[]{9, 1}, "Перевод денежных средств");
                    fptr.directIO(1011, new int[]{10, 1}, "+7(495)9262555");
                    fptr.directIO(1011, new int[]{11, 1}, "г.Москва, ул.Азовская, д.15А");
                    fptr.directIO(1011, new int[]{12, 1}, "7724389850");
                    fptr.directIO(1011, new int[]{13, 1}, "QIWI");
                    fptr.directIO(1011, new int[]{14, 1}, "+7(495)8888888");
                    fptr.directIO(1011, new int[]{15, 1}, "+7(495)5555555");
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик");
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152");
                    // Добавляем дополнительный реквизит БСО, тег 1192
                    fptr.directIO(1010, new int[]{24,1}, "12212121");
                    fptr.directIO(1011, new int[]{3, 0}, "4"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Оплата услуг связи", 30066, 2000, 1, 15033, "шт");
                    // Регистрация скидки на позицию, только печать на ЧЛ
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 66, 1);
                    // Нефискальный текст - разделитель позиций
                    fptr.printRecMessage("----------------------------------------------------------------");
                    fptr.directIO(1011, new int[]{3, 0}, "7"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    // Регистрация второй позиции
                    fptr.printRecItem("Покупка лотерейного билета", 10000, 1000, 2, 10000, "шт");
                    // Нефискальный текст - разделитель позиций
                    fptr.printRecMessage("----------------------------------------------------------------");
                    fptr.directIO(1011, new int[]{3, 0}, "15"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    // Регистрация третьей позиции
                    fptr.printRecItem("15", 16000, 2000, 4, 8000, "шт");
                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 33);
                    // Оплата
                    fptr.printRecTotal(20066, 20066, "10");
                    // Опалата подарочной картой
                    fptr.printRecTotal(16000, 16000, "50");
                    fptr.printRecTotal(20000, 20000, "00");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 2}, "СЭКОНОМИЛИ: RUB -69.90");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "2022 DON'T CRY to NIGHT");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("sell_noprint")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Отключение печати чека
                    fptr.directIO(1009, null, null);
                    // Дополнительные реквизиты чека - СНО, телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{5, 0}, String.valueOf(0x01) /*ОСН*/);
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    // Регистрация позиции с дополнительными реквизитами и скидкой
                    fptr.printRecItem("Товар 1", 4000, 2000, 1, 2000, "шт");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 90, 1);
                    // Нефискальный текст - разделитель позиций
                    fptr.printRecMessage("----------------------------------------------------------------");
                    // Регистрация простейшей позиции
                    fptr.printRecItem("Товар 2", 3990, 1000, 1, 3990, "");
                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 90);
                    // Оплата
                    fptr.printRecTotal(7900, 7900, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("sell_kt")) {
                    // Открытие чека
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Регистрация старой маркировки
                    fptr.directIO(1029, null, new String[]{"98765432101234", "ABC1234"});
                    fptr.printRecItem("Табак", 24000, 2000, 1, 12000, "шт");
                    fptr.directIO(1030, null, new String[]{"98765432101234", "ABC1234567890"});
                    fptr.printRecItem("Лекарства", 250000, 5000, 1, 50000, "шт");
                    fptr.directIO(1031, null, new String[]{"98765432101234", "RU-430302-ABC1234567"});
                    fptr.printRecItem("Мех", 10000000, 1000, 1, 10000000, "шт");
                    fptr.directIO(1037, null, new String[]{"98765432101234", "sgEKKPPcS25y5"});
                    fptr.printRecItem("Обувь", 1000000, 1000, 1, 1000000, "шт");

//                  Запись “сырого” кода маркировки, тег 1162
                    byte[] markingCode = new byte[]{0x44, 0x4D, 0x59, (byte) 0xD3, (byte) 0x9E, 0x7F, 0x19, 0x72, 0x41, 0x42, 0x43, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x30};
                    fptr.directIO(1039, null, markingCode);
                    fptr.printRecItem("Nike Air More Uptempo '96 'Tiffany' [M]", 1250000, 1000, 1, 1250000, "ST");


//                    // Запись неразобранной марки, значение считанной сканером марки, которое будет средствами ККТ преобразовано в реквизит 1162
//                    int[] markingType = {1};
//                    byte[] markingCode = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x37, 0x35, 0x38, 0x33, 0x30, 0x32, 0x31, 0x4D, 0x64, 0x45, 0x66, 0x78, 0x3A, 0x58, 0x70, 0x36, 0x59, 0x46, 0x64, 0x37, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x32, 0x39, 0x1D, 0x39, 0x32, 0x61, 0x51, 0x49, 0x51, 0x6B, 0x49, 0x37, 0x6F, 0x48, 0x58, 0x6D, 0x7A, 0x47, 0x2F, 0x6D, 0x64, 0x4B, 0x78, 0x7A, 0x43, 0x55, 0x43, 0x4B, 0x54, 0x4A, 0x48, 0x58, 0x6F, 0x42, 0x4F, 0x44, 0x64, 0x6D, 0x43, 0x64, 0x4D, 0x35, 0x6B, 0x38, 0x51, 0x6A, 0x37, 0x67, 0x61, 0x5A, 0x56, 0x32, 0x78, 0x62, 0x6E, 0x36, 0x36, 0x78, 0x42, 0x58, 0x47, 0x49, 0x4B, 0x72, 0x74, 0x66, 0x76, 0x71, 0x50, 0x49, 0x4E, 0x41, 0x32, 0x6A, 0x6B, 0x62, 0x6A, 0x79, 0x6A, 0x33, 0x2F, 0x4F, 0x2B, 0x6B, 0x79, 0x36, 0x6F, 0x75, 0x31, 0x4E, 0x41, 0x3D, 0x3D};
//                    fptr.directIO(1040, markingType, markingCode);
//                    fptr.printRecItem("Кроссовки Adidas Crazy 8 \"All-Star\"", 1250000, 1000, 1, 1250000, "ST");

                    // Оплата
                    fptr.printRecTotal(12524000, 12524000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }

                if (input[0].equals("buy")) {
                    fptr.setFiscalReceiptType(102);
                    fptr.beginFiscalReceipt(true);
                    // Дополнительные реквизиты чека, телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    // Регистрация перовй позиции с дополнительными реквизитами
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    //fptr.directIO(121, null, new String[]{"98765432101234", "ABC1234567890"});
                    fptr.printRecItem("Товар №1", 100000, 1000, 1, 100000, "");
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Товар №2", 20000, 2000, 2, 10000, "");
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Товар №3", 5000, 5000, 4, 1000, "");
                    // Оплата
                    fptr.printRecTotal(100000, 100000, "10");
                    fptr.printRecTotal(125000, 25000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("refund")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_REFUND);
                    fptr.beginFiscalReceipt(true);
                    // Дополнительные реквизиты чека, телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{24, 1}, "ФП 1297994844"); // дополнительный реквизит чека (БСО) (тег ФН 1192), здесь можно передать ФП № некорректного чека прихода
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    // Регистрация перовй позиции с дополнительными реквизитами
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    //fptr.directIO(121, null, new String[]{"98765432101234", "ABC1234567890"});
                    fptr.printRecItemRefund("Товар №1", 100000, 1000, 1, 100000, "");
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItemRefund("Товар №2", 20000, 2000, 2, 10000, "");
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItemRefund("Товар №3", 5000, 5000, 4, 1000, "");
                    // Оплата
                    fptr.printRecTotal(100000, 100000, "10");
                    fptr.printRecTotal(125000, 25000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("refundbuy")) {
                    fptr.setFiscalReceiptType(103);
                    fptr.beginFiscalReceipt(true);
                    // Дополнительные реквизиты чека, телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    // Регистрация перовй позиции с дополнительными реквизитами
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    //fptr.directIO(121, null, new String[]{"98765432101234", "ABC1234567890"});
                    fptr.printRecItemRefund("Товар №1", 100000, 1000, 1, 100000, "");
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItemRefund("Товар №2", 20000, 2000, 2, 10000, "");
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItemRefund("Товар №3", 5000, 5000, 4, 1000, "");
                    // Оплата
                    fptr.printRecTotal(100000, 100000, "10");
                    fptr.printRecTotal(125000, 25000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("correct")) {
                    // Открытие чека
                    fptr.setFiscalReceiptType(100);
                    fptr.beginFiscalReceipt(true);
                    // Основание для коррекции
                    fptr.directIO(1010, new int[]{20, 0}, "0");
                    fptr.directIO(1010, new int[]{21, 1}, "Документ коррекции");
                    fptr.directIO(1010, new int[]{22, 4}, "01012018000000");
                    fptr.directIO(1010, new int[]{23, 1}, "1234");
                    // Налоги, НДС 20%, НДС 10%, НДС 0%, без НДС, НДС 10/110%, НДС 20/120%
                    fptr.directIO(1026, new int[]{1}, "1000");
                    fptr.directIO(1026, new int[]{2}, "2000");
                    fptr.directIO(1026, new int[]{3}, "3000");
                    fptr.directIO(1026, new int[]{4}, "4000");
                    fptr.directIO(1026, new int[]{5}, "5000");
                    fptr.directIO(1026, new int[]{6}, "6000");
                    // Оплаты
                    fptr.printRecTotal(30000, 30000, "10");
                    fptr.printRecTotal(500000, 500000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                    String[] taxes = new String[6];
                    fptr.directIO(1032, null, taxes);
                    for (int t = 0; t < taxes.length; ++t) {
                        System.out.println(taxes[t]);
                    }
                }
                if (input[0].equals("correctbuy")) {
                    // Открытие чека
                    fptr.setFiscalReceiptType(104);
                    fptr.beginFiscalReceipt(true);
                    // Основание для коррекции
                    fptr.directIO(1010, new int[]{20, 0}, "0");
                    fptr.directIO(1010, new int[]{21, 1}, "Документ коррекции");
                    fptr.directIO(1010, new int[]{22, 4}, "01012018000000");
                    fptr.directIO(1010, new int[]{23, 1}, "1234");
                    // Налоги, НДС 20%, НДС 10%, НДС 0%, без НДС, НДС 10/110%, НДС 20/120%
                    fptr.directIO(1026, new int[]{1}, "1000");
                    fptr.directIO(1026, new int[]{2}, "2000");
                    fptr.directIO(1026, new int[]{3}, "3000");
                    fptr.directIO(1026, new int[]{4}, "4000");
                    fptr.directIO(1026, new int[]{5}, "5000");
                    fptr.directIO(1026, new int[]{6}, "6000");
                    // Оплаты
                    fptr.printRecTotal(3000, 3000, "10");
                    fptr.printRecTotal(50000, 50000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("correctrefund")) {
                    // Открытие чека
                    fptr.setFiscalReceiptType(101);
                    fptr.beginFiscalReceipt(true);
                    // Основание для коррекции
                    fptr.directIO(1010, new int[]{20, 0}, "0");
                    fptr.directIO(1010, new int[]{21, 1}, "№ 1234");
                    fptr.directIO(1010, new int[]{22, 4}, "01012018000000");
                    fptr.directIO(1010, new int[]{23, 1}, "Документ коррекции");
                    // Налоги, НДС 20%, НДС 10%, НДС 0%, без НДС, НДС 10/110%, НДС 20/120%
                    fptr.directIO(1026, new int[]{1}, "1000");
                    fptr.directIO(1026, new int[]{2}, "2000");
                    fptr.directIO(1026, new int[]{3}, "3000");
                    fptr.directIO(1026, new int[]{4}, "4000");
                    fptr.directIO(1026, new int[]{5}, "5000");
                    fptr.directIO(1026, new int[]{6}, "6000");
                    // Оплаты
                    fptr.printRecTotal(3000, 3000, "10");
                    fptr.printRecTotal(50000, 50000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("cancel")) {
                    fptr.printRecVoid("");
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("no_fisc")) {
                    // Открытие нефискального документа
                    fptr.beginNonFiscal();
                    // Печать строки
                    fptr.printRecMessage("Печать строки (Метод №1)");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "Печать строки (Метод №2)");
                    int alignment = 1;
                    int doubleWidth = 1;
                    int doubleHeight = 0;
                    int brightness = 7;
                    int font = 2;
                    String text = "Печать строки с форматированием";
                    fptr.directIO(1001, new int[]{alignment, doubleWidth, doubleHeight, brightness, font}, text);
                    // Закрытие нефискального документа
                    fptr.endNonFiscal();
                }
                if (input[0].equals("qr")) {
                    // Открытие нефискального документа
                    fptr.beginNonFiscal();
                    // Печать строк и штрихкодов
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ИНН: 111111111111 КПП: 222222222");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "КАССА: 1               СМЕНА: 11");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ЧЕК: 314  ДАТА: 20.11.2017 15:39");
                    fptr.directIO(1002, new int[]{11, 7, 0, 0}, "https://check.egais.ru?id=cf1b1096-3cbc-11e7-b3c1-9b018b2ba3f7");
                    fptr.directIO(1002, new int[]{6, 1, 50, 1}, "AAD1a2pEasx145");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "https://check.egais.ru?id=cf1b1096-3cbc-11e7-b3c1-9b018b2ba3f7");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "10 58 1c 85 bb 80 99 84 40 b1 4f 35 8a 35 3f 7c " +
                            "78 b0 0a ff cd 37 c1 8e ca 04 1c 7e e7 5d b4 85 " +
                            "ff d2 d6 b2 8d 7f df 48 d2 5d 81 10 de 6a 05 c9 " +
                            "81 74");
                    // Закрытие нефискального документа
                    fptr.endNonFiscal();
                }
                if (input[0].equals("test")) {
//                    int settingsID = 244;
//                    String settingValue = "Подарочной картой";
//                    fptr.directIO(1005, new int[]{settingsID}, settingValue);
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    fptr.printRecMessage("Печать строки (Метод №1)");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "Печать строки (Метод №2)");
                    // Дополнительные реквизиты чека - признак агента (пл.субагент, банковский пл.субагент), телефон получателя электронного чека
                    fptr.directIO(1010, new int[]{5, 0}, "1");
                    fptr.directIO(1010, new int[]{7, 0}, "10");
                    fptr.directIO(1010, new int[]{8, 1}, "+7(495)1234567");
                    fptr.directIO(1010, new int[]{9, 1}, "Перевод денежных средств");
                    fptr.directIO(1010, new int[]{10, 1}, "+7(495)9262555");
                    fptr.directIO(1010, new int[]{11, 1}, "г.Москва, ул.Азовская, д.15А");
                    fptr.directIO(1010, new int[]{12, 1}, "7724389850");
                    fptr.directIO(1010, new int[]{13, 1}, "QIWI");
                    fptr.directIO(1010, new int[]{14, 1}, "+7(495)8888888");
                    fptr.directIO(1010, new int[]{15, 1}, "+7(495)5555555");
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    // Регистрация перовй позиции с дополнительными реквизитами
                    fptr.directIO(1011, new int[]{7, 0}, "10");
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567");
                    fptr.directIO(1011, new int[]{9, 1}, "Перевод денежных средств");
                    fptr.directIO(1011, new int[]{10, 1}, "+7(495)9262555");
                    fptr.directIO(1011, new int[]{11, 1}, "г.Москва, ул.Азовская, д.15А");
                    fptr.directIO(1011, new int[]{12, 1}, "7724389850");
                    fptr.directIO(1011, new int[]{13, 1}, "QIWI");
                    fptr.directIO(1011, new int[]{14, 1}, "+7(495)8888888");
                    fptr.directIO(1011, new int[]{15, 1}, "+7(495)5555555");
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик");
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152");
                    // Добавляем дополнительный реквизит БСО, тег 1192
                    fptr.directIO(1010, new int[]{24, 1}, "12212121");
                    fptr.directIO(1011, new int[]{3, 0}, "4"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Оплата услуг связи", 22000, 1000, 1, 22000, "шт");
                    // Регистрация скидки на позицию, только печать на ЧЛ
//                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 1000, 1);
                    // Нефискальный текст - разделитель позиций
                    fptr.printRecMessage("----------------------------------------------------------------");
                    fptr.directIO(1011, new int[]{3, 0}, "7"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    // Регистрация второй позиции
                    fptr.printRecItem("Покупка лотерейного билета", 10090, 1000, 1, 10090, "шт");
                    // Нефискальный текст - разделитель позиций
                    fptr.printRecMessage("----------------------------------------------------------------");
                    fptr.directIO(1011, new int[]{3, 0}, "15"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    // Регистрация третьей позиции
                    fptr.printRecItem("15", 16000, 2000, 2, 8000, "шт");
                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидка", 90);
                    // Оплата
                    fptr.printRecTotal(8000, 8000, "10");
                    // Опалата подарочной картой
//                    fptr.printRecTotal(18000, 10000, "50");
                    fptr.printRecTotal(48000, 40000, "00");
                    // Запрос текущей суммы чека
                    String[] result = new String[1];
                    fptr.getData(FiscalPrinterConst.FPTR_GD_CURRENT_TOTAL, null, result);
                    // Закрытие чека
                    // Печать нефискальных строк, только для П5.0.
                    fptr.setTrailerLine(1, "Печать строки №1 в подвале", true); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(2, "Печать строки №2 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(3, "Печать строки №3 в подвале", true); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(4, "Печать строки №4 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines

                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("test2")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    fptr.directIO(1010, new int[]{5, 0}, "1");
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
//                    fptr.directIO(1011, new int[]{3, 0}, "4"); // Признак предмета расчета
//                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Конструктор LEGO", 311000, 2000, 1, 155500, "st");
//                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидка", 32);
                    fptr.printRecTotal(211000, 211000, "10");
                    fptr.printRecTotal(100000, 100000, "00");
                    // Запрос текущей суммы чека
                    String[] result = new String[1];
                    fptr.getData(FiscalPrinterConst.FPTR_GD_CURRENT_TOTAL, null, result);
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("otmena_cashin")) {
//                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_CASH_OUT);
//                    fptr.beginFiscalReceipt(true);
                    fptr.printRecVoid("");
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("close_check")) {
//                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_CASH_OUT);
//                    fptr.beginFiscalReceipt(true);
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("sell_predoplata")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Дополнительные реквизиты чека - телефон получателя электронного чека и место расчетов
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1010, new int[]{6, 1}, "г. Москва, центральный офис");
                    // Дополнительные реквизиты позиции: признак предмета расчета и способа расчета
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Конструктор LEGO", 170000, 1000, 1, 170000, "шт"); // В первом чеке указываем используемую ставку НДС, например «НДС 20%»
                    // Тип оплаты - безналичными
                    fptr.printRecTotal(170000, 70000, "00");
                    fptr.printRecTotal(170000, 100000, "10");

                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("sell_zakritie_predoplati")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Дополнительные реквизиты чека - телефон получателя электронного чека и место расчетов
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1010, new int[]{6, 1}, "г. Казань, магазин №1");
                    // Дополнительные реквизиты позиции: признак предмета расчета и способа расчета
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Конструктор LEGO", 170000, 1000, 4, 170000, "шт"); // Во втором чеке ставку НДС указываем «НДС не облагается»
                    // Тип оплаты - зачет аванса и (или) предыдущих платежей
                    fptr.printRecTotal(170000, 170000, "20");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                // Частичная предоплата, 1 и 2 чек
                if (input[0].equals("part_predoplata")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Дополнительные реквизиты чека - телефон получателя электронного чека и место расчетов
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1010, new int[]{6, 1}, "г. Москва, центральный офис");
                    // Дополнительные реквизиты позиции: признак предмета расчета и способа расчета
                    fptr.directIO(1011, new int[]{3, 0}, "10"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "2"); // Признак способа расчета
                    fptr.printRecItem("Товар №1", 8000, 1000, 6, 8000, "шт");
                    fptr.directIO(1011, new int[]{3, 0}, "10"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "2"); // Признак способа расчета
                    fptr.printRecItem("Товар №2", 18000, 1000, 6, 18000, "шт");
                    // Тип оплаты - безналичными
                    fptr.printRecTotal(26000, 26000, "10");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("end_predoplata")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Дополнительные реквизиты чека - телефон получателя электронного чека и место расчетов
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1010, new int[]{6, 1}, "г. Казань, магазин №1");
                    // Дополнительные реквизиты позиции: признак предмета расчета и способа расчета
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Товар №1", 20000, 2000, 1, 10000, "шт");
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Товар №2", 45000, 3000, 1, 15000, "шт");
                    // Тип оплаты - зачет аванса и (или) предыдущих платежей
                    fptr.printRecTotal(0, 26000, "20");
                    fptr.printRecTotal(0, 39000, "00");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("1020")) {


                    List<String> result = new LinkedList<>();
                    fptr.directIO(1020, null, result);

                    int shiftNumber = Integer.parseInt(result.get(0));

//                    List<String> result = new LinkedList<>();
//                    fptr.directIO(1020, null, result);
                    //int shiftNumber = Integer.parseInt(result.get(0));

//                    String[] result = new String[1];
//                    fptr.getData(FiscalPrinterConst.FPTR_GD_CURRENT_TOTAL, null, result);
                }


                if (input[0].equals("1021")) {
                   // List<String> result = new LinkedList<>();
                    //fptr.directIO(1021, null, result);
                    //double shiftNumber = Double.parseDouble(result.get(0));
                    //System.out.println(shiftNumber);
                    List<String> result = new LinkedList<>();
                    fptr.directIO(1021, null, result);

                    double shiftNumber = Double.parseDouble(result.get(0));
                }
                if (input[0].equals("1025")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    fptr.directIO(1010, new int[]{5, 0}, "1");
                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1011, new int[]{3, 0}, "4"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("бананы", 11132, 2000, 1, 5566, "шт");
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидка", 32);
                    fptr.printRecTotal(10000, 10000, "10");
                    fptr.printRecTotal(11100, 1100, "00");
                    // Указываем пользовательские реквизиты 15000 и 15001 (x5)
                    String[] userProperty = new String[]{"tag15000", "КОД: 77777"};
                    fptr.directIO(1025, null, userProperty);
                    String[] userProperty1 = new String[]{"tag15001", "ПРИНЯТО:                 0.00"};
                    fptr.directIO(1025, null, userProperty1);
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("recording_settings")) {
                    fptr.directIO(1005, new int[]{276}, "5");
                    fptr.directIO(1006, null, null);
                }
                if (input[0].equals("qr_obtekanie_text")) {
                    // При необходимости, отключить печать подвала в нефиск док-тах, printNonFiscalFooter = 0
                    // Пока реализованно только для платформы 2.5
                    // Открытие нефискального документа
                    fptr.beginNonFiscal();
                    // Добавление текста для печати рядом со штрихкодом
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "https://check1.fsrar.ru?id=820ea49");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "6-fc85-44e9-b911-8bec38edf8ff&dt=2");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "003141459&cn=010000006284A3C2DDBDC");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "A0CCFF59104181AABEEA564B01879D7B13");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "2962128F586E5FF5D7A2F4BACB9A08E0A9");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "42ADB46A78B8587E957C5836E9E076D90A");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "998AB1F118FA7BA35");
                    // Печать строк и штрихкодов
                    fptr.directIO(1002, new int[]{11, 3, 0, 1, 2}, "https://check1.fsrar.ru?id=820ea496-fc85-44e9-b911-8bec38edf8ff&dt=2003141459&cn=010000006284A3C2DDBDCA0CCFF59104181AABEEA564B01879D7B132962128F586E5FF5D7A2F4BACB9A08E0A942ADB46A78B8587E957C5836E9E076D90A998AB1F118FA7BA35");
                    // Закрытие нефискального документа
                    fptr.endNonFiscal();
                }
                if (input[0].equals("stroki_v_podvale")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    fptr.directIO(1010, new int[]{1, 1}, "client@client.ru");

                    int[] markingType = {1};
                    byte[] markingCode = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x37, 0x35, 0x38, 0x33, 0x30, 0x32, 0x31, 0x4D, 0x64, 0x45, 0x66, 0x78, 0x3A, 0x58, 0x70, 0x36, 0x59, 0x46, 0x64, 0x37, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x32, 0x39, 0x1D, 0x39, 0x32, 0x61, 0x51, 0x49, 0x51, 0x6B, 0x49, 0x37, 0x6F, 0x48, 0x58, 0x6D, 0x7A, 0x47, 0x2F, 0x6D, 0x64, 0x4B, 0x78, 0x7A, 0x43, 0x55, 0x43, 0x4B, 0x54, 0x4A, 0x48, 0x58, 0x6F, 0x42, 0x4F, 0x44, 0x64, 0x6D, 0x43, 0x64, 0x4D, 0x35, 0x6B, 0x38, 0x51, 0x6A, 0x37, 0x67, 0x61, 0x5A, 0x56, 0x32, 0x78, 0x62, 0x6E, 0x36, 0x36, 0x78, 0x42, 0x58, 0x47, 0x49, 0x4B, 0x72, 0x74, 0x66, 0x76, 0x71, 0x50, 0x49, 0x4E, 0x41, 0x32, 0x6A, 0x6B, 0x62, 0x6A, 0x79, 0x6A, 0x33, 0x2F, 0x4F, 0x2B, 0x6B, 0x79, 0x36, 0x6F, 0x75, 0x31, 0x4E, 0x41, 0x3D, 0x3D};
                    fptr.directIO(1040, markingType, markingCode);

//                    fptr.directIO(1040, 0, "3031303239303030303034373538333032314D644566783A587036594664371D3931383032391D3932615149516B49376F48586D7A472F6D644B787A4355434B544A48586F424F44646D43644D356B38516A3767615A563278626E363678425847494B727466767150494E41326A6B626A796A332F4F2B6B79366F75314E413D3D"); // запись неразобранной марки
                    fptr.directIO(1011, new int[]{3, 0}, "4"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("Кроссовки Adidas Crazy 8 \"All-Star\"", 1250000, 1000, 1, 1250000, "шт");
                    fptr.printRecTotal(750000, 750000, "10");
                    fptr.printRecTotal(500000, 500000, "00");

                    // Печать нефискальных строк, после фискальной части в чеке, справедливо при настройке cacheRecMessages = 1
                    fptr.printRecMessage("Печать строки");
                    fptr.directIO(1001, new int[]{1, 1, 1, 0, 0}, "COVID-19");
                    fptr.directIO(1002, new int[]{11, 4, 0, 0}, "Апокалипсис сегодня, а может быть завтра, а может быть и не в этой жизни, ведь как завещал великий полковник Курт...");

//                    // Печать нефискальных строк, только для П5.0.
                    fptr.setTrailerLine(1, "Печать строки №1 в подвале", true); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(2, "Печать строки №2 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(3, "Печать строки №3 в подвале", true); // Кол-во строк, смотри настройку numTrailerLines
                    fptr.setTrailerLine(4, "Печать строки №4 в подвале", false); // Кол-во строк, смотри настройку numTrailerLines

                    // Закрытие чека
                    fptr.endFiscalReceipt(false);

                }





                if (input[0].equals("getShiftCounters")){

                    List<String> result = new LinkedList<>();
                    fptr.directIO(1020, null, result);

                    int shiftNumber = Integer.parseInt(result.get(8));


                }


                if (input[0].equals("1021")) {
                    List<String> result = new LinkedList<>();
                    fptr.directIO(1021, null, result);

                    double shiftNumber = Double.parseDouble(result.get(0));
                }


                if (input[0].equals("check")) {
                    String[] result = new String[1];
                    fptr.getData(FiscalPrinterConst.FPTR_GD_RECEIPT_NUMBER, null, result);
                }





                if (input[0].equals("skidka")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Регистрация первой позиции
                    fptr.directIO(1011, new int[]{3, 0}, "4"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("*1860 Напиток COCA-COLA газ.ПЭТ 2.0л", 4589, 1000, 1, 4589, "шт");
                    // Регистрация скидки на позицию, только печать на ЧЛ
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 510, 1);
                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 89);
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    // Оплата
                    fptr.printRecTotal(4500, 4500, "00");



                    //fptr.printRecMessage("ВЫ СЭКОНОМИЛИ: RUB 69.90");

                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("sell_ST")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Регистрация первой позиции
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("*1860 Напиток COCA-COLA газ.ПЭТ 2.0л", 3495, 1000, 1, 3495, "st");
//                    fptr.printRecSubtotal
                    // Регистрация скидки на позицию, только печать на ЧЛ
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 510, 1);
                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 95);
                    // Оплата
                    fptr.printRecTotal(3400, 3400, "10");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("lic")) {

                    String filePath = "C:/Users/i.purikov/Downloads/zn_00105720461538.json";
                    int[] result = new int[1];
                    fptr.directIO(1046, result, filePath);
                }
                if (input[0].equals("licread")) {
                    String[] licenses = new String[1];
                    fptr.directIO(1048, null, licenses);

                }

                if (input[0].equals("sell_markirovka")) {
                    // Открытие чека
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS");

////                  Запись “сырого” кода маркировки, тег 1162
//                    byte[] markingCode = new byte[]{0x44, 0x4D, 0x59, (byte) 0xD3, (byte) 0x9E, 0x7F, 0x19, 0x72, 0x41, 0x42, 0x43, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x30};
//                    fptr.directIO(1039, null, markingCode);
//                    fptr.printRecItem("Nike Air More Uptempo '96 'Tiffany'", 1250000, 1000, 1, 1250000, "ST");

                    // Запись неразобранной марки, значение считанной сканером марки, которое будет средствами ККТ преобразовано в реквизит 1162
                    int[] markingType = {1};
                    byte[] markingCode = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x37, 0x35, 0x38, 0x33, 0x30, 0x32, 0x31, 0x4D, 0x64, 0x45, 0x66, 0x78, 0x3A, 0x58, 0x70, 0x36, 0x59, 0x46, 0x64, 0x37, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x32, 0x39, 0x1D, 0x39, 0x32, 0x61, 0x51, 0x49, 0x51, 0x6B, 0x49, 0x37, 0x6F, 0x48, 0x58, 0x6D, 0x7A, 0x47, 0x2F, 0x6D, 0x64, 0x4B, 0x78, 0x7A, 0x43, 0x55, 0x43, 0x4B, 0x54, 0x4A, 0x48, 0x58, 0x6F, 0x42, 0x4F, 0x44, 0x64, 0x6D, 0x43, 0x64, 0x4D, 0x35, 0x6B, 0x38, 0x51, 0x6A, 0x37, 0x67, 0x61, 0x5A, 0x56, 0x32, 0x78, 0x62, 0x6E, 0x36, 0x36, 0x78, 0x42, 0x58, 0x47, 0x49, 0x4B, 0x72, 0x74, 0x66, 0x76, 0x71, 0x50, 0x49, 0x4E, 0x41, 0x32, 0x6A, 0x6B, 0x62, 0x6A, 0x79, 0x6A, 0x33, 0x2F, 0x4F, 0x2B, 0x6B, 0x79, 0x36, 0x6F, 0x75, 0x31, 0x4E, 0x41, 0x3D, 0x3D};
                    fptr.directIO(1040, markingType, markingCode);
                    fptr.printRecItem("Кроссовки Adidas Crazy 8 \"All-Star\" [M]", 1250000, 1000, 1, 1250000, "ST");


                    // Оплата
                    fptr.printRecTotal(1250000, 1250000, "10");
                    fptr.directIO(1001, new int[]{1, 1, 0, 0, 0, 1}, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 2}, "СЭКОНОМИЛИ: RUB -69.90");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "2020 DON'T CRY to NIGHT");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("test_BILLA_DISCOUNT")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);
                    // Регистрация первой позиции
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("СУМКА ИЗ ПОЛИПРОПИ", 55920, 8000, 1, 6990, "ST");
                    // Регистрация скидки на позицию, только печать на ЧЛ
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 6990, 0);
                    fptr.printRecMessage("Включая скидку GESCHENKAKTION TEST EG   =  69.90");
                    // Подитог чека
                    fptr.printRecSubtotal(48930);
                    // Скидка-округление на чек, отбрасываем копейки
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 30);
                    // Подитог чека
                    fptr.printRecSubtotal(48900);
                    // Оплата
                    fptr.printRecTotal(48900, 50000, "00");
                    // Печать нефискальных строк, после фискальной части в чеке, справедливо при настройке cacheRecMessages = 1
                    fptr.printRecMessage("ПОЛУЧИТЕ 1 ФИШЕК");
                    fptr.printRecMessage("ЧЕК-N 1005      ПОЗ 9       КАС 1       КСР 002");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("cashin_BILLA")) {
                    fptr.directIO(1005, new int[]{66}, "0");
                    fptr.directIO(1006, null, null);

                    fptr.beginNonFiscal();
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 0}, "190(RU06/0000001976661)");
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 0}, "190(RU06/0000001976661)");
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 0}, "");
                    fptr.directIO(1001, new int[]{0, 0, 0, 0, 0}, "190(RU06/0000001976661)");
                    fptr.endNonFiscal();

                    fptr.directIO(1005, new int[]{66}, "1");
                    fptr.directIO(1006, null, null);

                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_CASH_IN);
                    fptr.beginFiscalReceipt(true);
                    fptr.printRecCash(10000);
                    fptr.endFiscalReceipt(false);
                }




                if (input[0].equals("FW")) {

                    String[] templatesInfo = new String[2];
                    //List<String> result = new LinkedList<>();
                    fptr.directIO(1042, null, templatesInfo);
                    System.out.printf("Версия шаблона = %s\nХЭШ шаблона = %s", templatesInfo[0],templatesInfo[1]);





                }

                if (input[0].equals("1920")) {
                    fptr.setPOSID("55", "88888");

                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);

                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS1");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS2");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "PREITEMS3");


                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
                    fptr.directIO(1010, new int[]{2, 1}, "Петрова П.П."); // Получатель, 1227
                    fptr.directIO(1010, new int[]{3, 1}, "526317984689"); // ИНН Получателя, 1228
                    fptr.directIO(1010, new int[]{4, 1}, "123123321321@mail.ru");// 1117 flhtc jnghfdbntkz

                    fptr.directIO(1010, new int[]{18, 1}, "1085"); // 1085
                    fptr.directIO(1010, new int[]{19, 1}, "1086"); // 1086

                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика

                    // Регистрация 1-ой позиции.
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
//                    fptr.directIO(1011, new int[]{4, 0}, "3"); // Признак способа расчета
                    fptr.printRecItem("230069 Конфеты Метель-раз 1кг", 206742, 15582, 2, 13268, "St");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 32, 2);
                    // Регистрация 2-ой позиции.
                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("1863 Напиток COCA-COLA газ.ж/б 0.33л", 837600, 200000, 1, 4188, "St");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 4188, 2);
                    // Регистрация 3-ой позиции.
                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("3416193 Вин.САН.ПУЛ.СОЛ.к.сух.12% 0.75л", 37999, 1000, 1, 37999, "St");
//                    // Регистрация 4-ой позиции.

                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "2"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета

                    int markingStatus = 2;
                    int processingMode = 0;
                    int quantity = 2000;
                    int measurementUnit = 0;
                    int waitForResult = 1;
                    int[] markingType = {1};
                    byte[] markingCode = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4c, 0x47, 0x4c, 0x4d, 0x59, 0x51, 0x51, 0x1d, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1d, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6d, 0x4b, 0x36, 0x53, 0x4e, 0x38, 0x76, 0x76, 0x77, 0x6f, 0x78, 0x5a, 0x46, 0x6b, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6f, 0x4a, 0x4e, 0x4d, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6a, 0x61, 0x30, 0x34, 0x63, 0x3d};
                    fptr.directIO(1040, markingType, markingCode);
                    fptr.printRecItem("*3276878 СИГАРЕТЫ PARLIAM.NIGHT BLUE 1П.", 136000, 8000, 1, 17000, "ST");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 17000, 2);
                    fptr.printRecMessage(" СКИДКА:                  0.00    ПОДЫТОГ:               424.97");





                    // Скидка-округление на чек
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 41);

                    fptr.printRecSubtotal(200000);
                    //fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "СЭКОНОМИЛИ: RUB -69.90");
                    //fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    //fptr.printRecMessage("ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    // Оплата
                    fptr.printRecTotal(2000000, 2000000, "00");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 2}, "СЭКОНОМИЛИ: RUB -69.90");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "2020 DON'T CRY to NIGHT");

                    //fptr.printRecMessage("ПОЛУЧИТЕ 1 ФИШЕК");
                    //fptr.printRecMessage("ЧЕК-N 1005      ПОЗ 9       КАС 1       КСР 002");

                    // Указываем пользовательские реквизиты 15000 и 15001 (x5)
                    // String[] userProperty0 = new String[]{"ID10", "БАЛЛАМИ ВЫРУЧАЙКИ:      25.00"};
                    //fptr.directIO(1025, null, userProperty0);
                    int strAlignment = 0;
                    String text = "Строка %d";

                    for (int j = 0; j < 8; ++j) {
                        fptr.directIO(1041, new int[]{strAlignment}, String.format(text, j+1));
                    }

                    int barcodeType = 11;
                    int barcodeScale = 4;
                    int barcodeHeight = 0;
                    int barcodePrintText = 0;
                    int barcodeAlignment = 0;
                    fptr.directIO(1002, new int[]{barcodeType, barcodeScale, barcodeHeight, barcodePrintText, barcodeAlignment}, "ДАННЫЕ ШК");

                    /*
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "https://check1.fsrar.ru?id=820ea49");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "6-fc85-44e9-b911-8bec38edf8ff&dt=2");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "003141459&cn=010000006284A3C2DDBDC");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "A0CCFF59104181AABEEA564B01879D7B13");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "2962128F586E5FF5D7A2F4BACB9A08E0A9");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "42ADB46A78B8587E957C5836E9E076D90A");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "998AB1F118FA7BA35");
                    // Печать строк и штрихкодов
                    fptr.directIO(1002, new int[]{11, 3, 0, 1, 2, 1}, "https://check1.fsrar.ru?id=820ea496-fc85-44e9-b911-8bec38edf8ff&dt=2003141459&cn=010000006284A3C2DDBDCA0CCFF59104181AABEEA564B01879D7B132962128F586E5FF5D7A2F4BACB9A08E0A942ADB46A78B8587E957C5836E9E076D90A998AB1F118FA7BA35");
                    */
                    fptr.endFiscalReceipt(false);

                }


                if (input[0].equals("BILLA")) {
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);



                    // Регистрация первой позиции
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("СУМКА ИЗ ПОЛИПРОПИ", 48936, 8000, 1, 6117, "шт");


                    int id = 10;
                    String value = "Проверка Документов";
                    fptr.directIO(1075, new int[]{0, id}, value);

                    // Регистрация скидки на позицию, только печать на ЧЛ
                    //fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 6990, 0);
                    fptr.printRecMessage("Включая скидку GESCHENKAKTION TEST EG   =  69.90");
                    // Подитог чека
                    fptr.printRecSubtotal(48936);
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0, 0}, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
//                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
//                    fptr.printRecMessage("ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    // Скидка-округление на чек, отбрасываем копейки
                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 36);
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0, 0}, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
//                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
//                    fptr.printRecMessage("ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    // Подитог чека
                    fptr.printRecSubtotal(48900);
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0, 0}, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
//                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
//                    fptr.printRecMessage("ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    // Оплата
                    fptr.printRecTotal(48900, 50000, "00");
                    // Печать нефискальных строк с форматированием
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 2}, "СЭКОНОМИЛИ: RUB -69.90");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "2020 DON'T CRY to NIGHT");




                    //fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "2020 DON'T CRY to NIGHT");
                    //fptr.printRecMessage("ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    fptr.printNormal(FiscalPrinterConst.FPTR_S_RECEIPT, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    //fptr.directIO(1001, new int[]{1, 1, 0, 0, 0}, "ВЫ СЭКОНОМИЛИ: RUB 69.90");
                    // Печать нефискальных строк, после фискальной части в чеке, справедливо при настройке cacheRecMessages = 1
                    fptr.printRecMessage("ПОЛУЧИТЕ 1 ФИШЕК");
                    fptr.printRecMessage("ЧЕК-N 1005      ПОЗ 9       КАС 1       КСР 002");
                    // QR-код + оверлей
                    // Печать строк
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "https://check1.fsrar.ru?id=820ea49");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "6-fc85-44e9-b911-8bec38edf8ff&dt=2");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "003141459&cn=010000006284A3C2DDBDC");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "A0CCFF59104181AABEEA564B01879D7B13");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "2962128F586E5FF5D7A2F4BACB9A08E0A9");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "42ADB46A78B8587E957C5836E9E076D90A");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "998AB1F118FA7BA35");
                    // Печать штрихкода
                    //fptr.directIO(1002, new int[]{11, 3, 0, 1, 2}, "https://check1.fsrar.ru?id=820ea496-fc85-44e9-b911-8bec38edf8ff&dt=2003141459&cn=010000006284A3C2DDBDCA0CCFF59104181AABEEA564B01879D7B132962128F586E5FF5D7A2F4BACB9A08E0A942ADB46A78B8587E957C5836E9E076D90A998AB1F118FA7BA35");
                    // Закрытие чека
                    fptr.endFiscalReceipt(false);
                }
                if (input[0].equals("Billa")) {
                    fptr.setPOSID("55" , "44564564");
                    fptr.setFiscalReceiptType(FiscalPrinterConst.FPTR_RT_SALES);
                    fptr.beginFiscalReceipt(true);



                    fptr.directIO(1010, new int[]{1, 1}, "client@mail.ru");
//                    fptr.directIO(1010, new int[]{2, 1}, "Петрова П.П."); // Получатель, 1227
//                    fptr.directIO(1010, new int[]{3, 1}, "526317984689"); // ИНН Получателя, 1228
                    fptr.directIO(1010, new int[]{4, 1}, "123123321321@mail.ru");// 1117 flhtc jnghfdbntkz

                    fptr.directIO(1010, new int[]{18, 1}, "1085"); // 1085
                    fptr.directIO(1010, new int[]{19, 1}, "1086"); // 1086



                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика

                    // Регистрация 1-ой позиции.
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
//                    fptr.directIO(1011, new int[]{4, 0}, "3"); // Признак способа расчета
                    fptr.printRecItem("230069 Конфеты Метель-раз 1кг", 206742, 15582, 2, 13268, "St");
                    //fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 32, 2);
                    // Регистрация 2-ой позиции.
                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("1863 Напиток COCA-COLA газ.ж/б 0.33л", 837600, 200000, 1, 4188, "St");
                    fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 4188, 2);
                    // Регистрация 3-ой позиции.
                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "1"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    fptr.printRecItem("3416193 Вин.САН.ПУЛ.СОЛ.к.сух.12% 0.75л", 37999, 1000, 1, 37999, "St");
//                    // Регистрация 4-ой позиции.

                    fptr.directIO(1011, new int[]{7, 0}, "64"); // Агенты, ревизит позиции 1222
                    fptr.directIO(1011, new int[]{8, 1}, "+7(495)1234567"); // Тел. поставщика
                    fptr.directIO(1011, new int[]{16, 1}, "ООО Поставщик"); // Поставщик
                    fptr.directIO(1011, new int[]{17, 1}, "7707329152"); // ИНН поставщика
                    fptr.directIO(1011, new int[]{3, 0}, "2"); // Признак предмета расчета
                    fptr.directIO(1011, new int[]{4, 0}, "4"); // Признак способа расчета
                    int[] markingType = {1};
                    //byte[] markingCode = {0x30, 0x31, 0x30, 0x32, 0x39, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x37, 0x35, 0x38, 0x33, 0x30, 0x32, 0x31, 0x4D, 0x64, 0x45, 0x66, 0x78, 0x3A, 0x58, 0x70, 0x36, 0x59, 0x46, 0x64, 0x37, 0x1D, 0x39, 0x31, 0x38, 0x30, 0x32, 0x39, 0x1D, 0x39, 0x32, 0x61, 0x51, 0x49, 0x51, 0x6B, 0x49, 0x37, 0x6F, 0x48, 0x58, 0x6D, 0x7A, 0x47, 0x2F, 0x6D, 0x64, 0x4B, 0x78, 0x7A, 0x43, 0x55, 0x43, 0x4B, 0x54, 0x4A, 0x48, 0x58, 0x6F, 0x42, 0x4F, 0x44, 0x64, 0x6D, 0x43, 0x64, 0x4D, 0x35, 0x6B, 0x38, 0x51, 0x6A, 0x37, 0x67, 0x61, 0x5A, 0x56, 0x32, 0x78, 0x62, 0x6E, 0x36, 0x36, 0x78, 0x42, 0x58, 0x47, 0x49, 0x4B, 0x72, 0x74, 0x66, 0x76, 0x71, 0x50, 0x49, 0x4E, 0x41, 0x32, 0x6A, 0x6B, 0x62, 0x6A, 0x79, 0x6A, 0x33, 0x2F, 0x4F, 0x2B, 0x6B, 0x79, 0x36, 0x6F, 0x75, 0x31, 0x4E, 0x41, 0x3D, 0x3D};
                    //fptr.directIO(1040, markingType, markingCode);
                    fptr.printRecItem("*3276878 СИГАРЕТЫ PARLIAM.NIGHT BLUE 1П.", 14875, 1000, 1, 14875, "ST");
                    //fptr.printRecItemAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "Скидочка", 17000, 2);
                    fptr.printRecMessage(" СКИДКА:                  0.00    ПОДЫТОГ:               424.97");


                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "https://check1.fsrar.ru?id=820ea49");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "6-fc85-44e9-b911-8bec38edf8ff&dt=2");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "003141459&cn=010000006284A3C2DDBDC");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "A0CCFF59104181AABEEA564B01879D7B13");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "2962128F586E5FF5D7A2F4BACB9A08E0A9");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "42ADB46A78B8587E957C5836E9E076D90A");
                    fptr.directIO(1041, new int[]{0, 0, 0, 0, 0}, "998AB1F118FA7BA35");
                    // Печать строк и штрихкодов
                    fptr.directIO(1002, new int[]{11, 3, 0, 1, 2}, "https://check1.fsrar.ru?id=820ea496-fc85-44e9-b911-8bec38edf8ff&dt=2003141459&cn=010000006284A3C2DDBDCA0CCFF59104181AABEEA564B01879D7B132962128F586E5FF5D7A2F4BACB9A08E0A942ADB46A78B8587E957C5836E9E076D90A998AB1F118FA7BA35");
                    // Закрытие нефискального документа



                    // Скидка-округление на чек
//                    fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 00);

                    fptr.printRecSubtotal(1097200);
                    // Оплата
                    fptr.printRecTotal(1097200, 1097200, "00");
                    // Указываем пользовательские реквизиты 15000 и 15001 (x5)
                    String[] userProperty0 = new String[]{"ID11", "БАЛЛАМИ ВЫРУЧАЙКИ:   11125.00"};
                    String[] userProperty1 = new String[]{"ID12", "Карта Клуба : 1231412341234123"};
                    String[] userProperty2 = new String[]{"ID13", "Касса 999"};
                    fptr.directIO(1025, null, userProperty0);
                    fptr.directIO(1025, null, userProperty1);
                    fptr.directIO(1025, null, userProperty2);
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 2}, "СЭКОНОМИЛИ: RUB -69.90");
                    fptr.directIO(1001, new int[]{0, 1, 0, 0, 0, 1}, "2020 DON'T CRY to NIGHT");


//                    String[] userProperty = new String[]{"ID14", "КОД:8881"};
//                    fptr.directIO(1025, null, userProperty);
//                    String[] userProperty1 = new String[]{"ID15", "ПРИНЯТО:             20000.00"};
//                    fptr.directIO(1025, null, userProperty1);


                    // Печать нефискальных строк, после фискальной части в чеке, справедливо при настройке cacheRecMessages = 1
//                    fptr.printRecMessage("Печать строки");
//                    fptr.directIO(1001, new int[]{0, 0, 0, 1, 0}, "КВИТАНЦИЯ");
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0}, "к приходному кассовому\")");
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0}, "ОРДЕРУ № 41000036 от 20.05.20");
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0}, "к приходному кассовому\")");
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0}, "к приходному кассовому\")");
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0}, "к приходному кассовому\")");
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0}, "к приходному кассовому\")");
//                    fptr.directIO(1001, new int[]{1, 1, 0, 0}, "к приходному кассовому\")");

                    int strAlignment = 0;
                    String text = "Строка %d";

                    for (int j = 0; j < 8; ++j) {
                        fptr.directIO(1041, new int[]{strAlignment}, String.format(text, j+1));
                    }

                    int barcodeType = 11;
                    int barcodeScale = 4;
                    int barcodeHeight = 0;
                    int barcodePrintText = 0;
                    int barcodeAlignment = 2;
                    fptr.directIO(1002, new int[]{barcodeType, barcodeScale, barcodeHeight, barcodePrintText, barcodeAlignment}, "ДАННЫЕ ШК");

                    // Закрытие чека
                    fptr.endFiscalReceipt(false);


                }



                if (input[0].equals("1016")) {
                    String dateTime = "19022021122048";


                    fptr.directIO(1016, null, dateTime);
                }



                //fptr.printZReport();

                if (input[0].equals("ZReport")) {
                    String[] result = new String[1];
                    fptr.getData(FiscalPrinterConst.FPTR_GD_Z_REPORT, null, result);
                }

                try {

                    int[] ready = new int[1];
                    Object[] result = new Object[8];

                    while (true) {
                        fptr.directIO(1059, ready, result);
                        if (ready[0] == 1)



                            break;
                    }

                } catch (jpos.JposException e) {






                    if (e.getOrigException() instanceof ru.atol.drivers10.jpos.fptr.errors.DriverException ) {
                        System.out.println(String.format("Driver error = %d [%s]", ((ru.atol.drivers10.jpos.fptr.errors.DriverException)e.getOrigException()).getErrorCode(), e.getMessage()));
                        System.out.println("Point 1223");



                    } else {

                        System.out.println(String.format("JposException = %s", e.getMessage()));


                    }
                } finally {

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());

//                int[] resultMark = new int[1];
//                fptr.directIO(1061, resultMark, null);
//
//                byte[] markingCode1 = {0x30, 0x31, 0x34, 0x34, 0x39, 0x34, 0x35, 0x35, 0x30, 0x34, 0x33, 0x35, 0x33, 0x30, 0x36, 0x38, 0x32, 0x31, 0x51, 0x58, 0x59, 0x58, 0x53, 0x41, 0x4C, 0x47, 0x4C, 0x4D, 0x59, 0x51, 0x51, 0x1D, 0x39, 0x31, 0x45, 0x45, 0x30, 0x36, 0x1D, 0x39, 0x32, 0x59, 0x57, 0x43, 0x58, 0x62, 0x6D, 0x4B, 0x36, 0x53, 0x4E, 0x38, 0x76, 0x76, 0x77, 0x6F, 0x78, 0x5A, 0x46, 0x6B, 0x37, 0x57, 0x41, 0x59, 0x38, 0x57, 0x6F, 0x4A, 0x4E, 0x4D, 0x47, 0x47, 0x72, 0x36, 0x43, 0x67, 0x74, 0x69, 0x75, 0x6A, 0x61, 0x30, 0x34, 0x63, 0x3D};
//
//
//                int markingType = 256; // тип марки (тег 2100)
//                int markingStatus = 2; // Планируемый статус марки (тег 2103)
//                int processingMode = 0; // Режим обработки КМ (тег 2102)
//                long validationResult0 = 0;
//
//                fptr.directIO(1049, new int[]{markingType, markingStatus,processingMode, (int)validationResult0 }, new Object[]{markingCode1, null});
//                int attrNumber = 3;
//                int attrType = 0;
//                String attrValue = "32";
//
//                String foivb = "007";
//                String dateb = "20210317";
//                String numb = "12";
//                String industryAttrb = "Ид1";
//                fptr.directIO(1055, null, new String[]{foivb, dateb, numb, industryAttrb});
//                fptr.directIO(1011, new int[]{2, 1}, "0");
//
//                fptr.directIO(1011, new int[]{attrNumber, attrType}, attrValue);
//                fptr.printRecItem("good", 3990, 1000, 1, 3990, "10");
//
//// Скидка-округление на чек
//                fptr.printRecSubtotalAdjustment(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "", 90);
//
//// Оплата
//                fptr.printRecTotal(7900, 7900, "00");
//
//// Закрытие чека
//                fptr.endFiscalReceipt(false);

            } finally {
                try {

//                    int[] ready = new int[1];
//                    Object[] result = new Object[8];
//                    while (true) {
//                        fptr.directIO(1059, ready, result);
//                        if (ready[0] == 0)
//                            break;
//                    }
                     //fptr.close();
                     //System.gc();
                } catch (Exception ignored)
                {

                }
                   }



        }
    }
}
