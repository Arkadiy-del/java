package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Server {
    private static final int PORT = 5000;
    private static final String FILE_NAME = "poems.txt";
    private static final String POEM_SEPARATOR = "###";

    public static void main(String[] args) {
        try {
            List<String> poems = readPoemsFromFile(FILE_NAME);

            if (poems.isEmpty()) {
                System.out.println("Файл со стихотворениями пуст.");
                return;
            }

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен на порту " + PORT);
            System.out.println("Ожидание клиентов...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getInetAddress());

                sendRandomPoem(clientSocket, poems);

                clientSocket.close();
                System.out.println("Клиент отключился.");
            }

        } catch (IOException e) {
            System.out.println("Ошибка сервера: " + e.getMessage());
        }
    }

    private static List<String> readPoemsFromFile(String fileName) throws IOException {
        Path path = Path.of(fileName);

        String text = Files.readString(path, StandardCharsets.UTF_8);

        return List.of(text.split(POEM_SEPARATOR))
                .stream()
                .map(String::trim)
                .filter(poem -> !poem.isEmpty())
                .toList();
    }

    private static void sendRandomPoem(Socket clientSocket, List<String> poems) throws IOException {
        Random random = new Random();
        String randomPoem = poems.get(random.nextInt(poems.size()));

        PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8),
                true
        );

        writer.println(randomPoem);
    }
}
