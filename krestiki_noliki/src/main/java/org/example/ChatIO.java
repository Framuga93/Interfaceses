package org.example;

import java.io.*;

public class ChatIO {
    private static final String FILE_NAME = "chat.txt";

    public static void writeChatToFile(String chatMsg) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.append(chatMsg);
        writer.close();
    }

    public static String readerChat() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        reader.close();
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

}
