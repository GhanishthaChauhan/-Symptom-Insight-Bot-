import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SimpleChatBotSwing extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;

    private static Map<String, DiseaseInfo> diseaseInfoMap;

    public SimpleChatBotSwing() {
        setTitle("Simple ChatBot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        JLabel titleLabel = new JLabel("Welcome to the ChatBot");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userMessage = inputField.getText();
                chatArea.append("You: " + userMessage + "\n\n");
                String botResponse = getBotResponse(userMessage);
                chatArea.append("ChatBot: " + botResponse + "\n\n");
                inputField.setText("");
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.SOUTH);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Set the background color of the main panel
        mainPanel.setBackground(new Color(77, 147, 103)); // Light purple background color

        add(mainPanel);
    }

    public static String getBotResponse(String userMessage) {
        String response = "I'm just a simple chatbot, and I don't have answers to everything.";
    
        if (diseaseInfoMap.containsKey(userMessage.toLowerCase())) {
            DiseaseInfo info = diseaseInfoMap.get(userMessage.toLowerCase());
            response = info.toString();
        } else if (userMessage.contains("hello") || userMessage.contains("hi")) {
            response = "Hello! How can I help you today?";
        } else if (userMessage.contains("your name")) {
            response = "I'm a ChatBot.";
        } else if (userMessage.contains("thank you")) {
            response = "You're welcome!";
        } else if (userMessage.contains("bye")) {
            response = "Goodbye! Have a great day!";
        }
    
        return response;
    }

    public static void main(String[] args) {
        // Initialize disease information
        diseaseInfoMap = new HashMap<>();
        diseaseInfoMap.put("fever", new DiseaseInfo("Fever", "High body temperature", "Infections, heat exhaustion", "Rest, hydration, fever-reducing medications"));
        diseaseInfoMap.put("cold", new DiseaseInfo("Common Cold", "Runny or stuffy nose, cough", "Viral infection", "Rest, hydration, over-the-counter cold remedies"));
        diseaseInfoMap.put("flu", new DiseaseInfo("Influenza", "Fever, body aches, cough", "Viral infection", "Rest, hydration, antiviral medications"));
        diseaseInfoMap.put("diabetes", new DiseaseInfo("Diabetes", "Increased thirst, frequent urination", "Genetic and lifestyle factors", "Medications, insulin, dietary management"));
        diseaseInfoMap.put("hypertension", new DiseaseInfo("Hypertension", "High blood pressure", "Genetic and lifestyle factors", "Medications, lifestyle changes"));
        diseaseInfoMap.put("asthma", new DiseaseInfo("Asthma", "Shortness of breath, wheezing", "Allergens and irritants", "Inhalers, corticosteroids"));
        diseaseInfoMap.put("cancer", new DiseaseInfo("Cancer", "Varies by type", "Varies by type", "Surgery, chemotherapy, radiation therapy, immunotherapy"));
        diseaseInfoMap.put("thyroid", new DiseaseInfo("Thyroid Disorders", "Weight changes, fatigue", "Hormonal imbalances", "Medications, radioactive iodine"));
        diseaseInfoMap.put("cardiovascular disease", new DiseaseInfo("Cardiovascular Disease", "Chest pain, shortness of breath", "Diet, exercise, smoking", "Medications, surgery"));
        diseaseInfoMap.put("alzheimer", new DiseaseInfo("Alzheimer's Disease", "Memory loss, cognitive decline", "Neurodegenerative", "Supportive care, medications"));
        diseaseInfoMap.put("sleep disorder", new DiseaseInfo("Sleep Disorders", "Insomnia, sleep apnea", "Various causes", "Lifestyle changes, CPAP, therapy"));

        SwingUtilities.invokeLater(() -> {
            SimpleChatBotSwing chatBot = new SimpleChatBotSwing();
            chatBot.setVisible(true);
        });
    }
}

class DiseaseInfo {
    private String name;
    private String symptoms;
    private String causes;
    private String cures;

    public DiseaseInfo(String name, String symptoms, String causes, String cures) {
        this.name = name;
        this.symptoms = symptoms;
        this.causes = causes;
        this.cures = cures;
    }

    @Override
    public String toString() {
        return "Disease: " + name + "\n" +
                "Symptoms: " + symptoms + "\n" +
                "Causes: " + causes + "\n" +
                "Cures: " + cures;
    }
}
