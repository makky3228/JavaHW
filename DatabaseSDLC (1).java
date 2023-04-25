import javax.swing.;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GUISDLC {
    public static void main(String[] args) throws FileNotFoundException {
// Start the GUI frame and panel
        JFrame frame = new JFrame("Word Occurrence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Select a file to analyze:"), c);

        JButton chooseFileButton = new JButton("Choose File");
        c.gridx = 1;
        panel.add(chooseFileButton, c);

        JTextArea outputArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(scrollPane, c);

        chooseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                Map<String, Integer> wordCounts = countWords(file);

                // Connect to the database
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/wordOccurrences", "user", "Password1");
                    for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                        String word = entry.getKey();
                        Integer count = entry.getValue();
                        String query = "INSERT IGNORE INTO word (word) VALUES (?)";
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setString(1, word);
                        ps.executeUpdate();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

                outputArea.setText("");
                for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                    outputArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static Map<String, Integer> countWords(File file) throws FileNotFoundException {
        Map<String, Integer> hashMap = new HashMap<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String word = sc.next().toLowerCase();
            Integer count = hashMap.get(word);
            if (count == null) {
                hashMap.put(word, 1);
            } else {
                hashMap.put(word, count + 1);
            }
        }
        sc.close();
        return hashMap;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }