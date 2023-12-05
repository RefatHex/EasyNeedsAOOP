package com.example.easyneedsaoop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RetrieveMessages {
    private static final String SELECT_MESSAGES_QUERY = "SELECT senderUsername, message FROM messages WHERE receiverUsername = ?";

    protected Map<String, StringBuilder> getMessagesByReceiver(String receiverUsername) {
        Map<String, StringBuilder> messagesMap = new HashMap<>();

        try (Connection connection = database.connectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGES_QUERY)) {

            preparedStatement.setString(1, receiverUsername);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String senderUsername = resultSet.getString("senderUsername");
                    String message = resultSet.getString("message");

                    // Add the message to the sender's messages in the map
                    messagesMap.computeIfAbsent(senderUsername, k -> new StringBuilder()).append(message).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messagesMap;
    }


}
