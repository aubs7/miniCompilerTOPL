import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class GUI extends JPanel implements ActionListener{
    private JButton openFile;
    private JButton lexicalButton;
    private JButton syntaxButton;
    private JButton semanticButton;
    private JButton clear;
    private JTextField resultArea;
    private JTextArea codeArea;
    private JLabel resultTitle;

    //LexicalAnalysis lexicalAnalysis;

    String fileContent;

    String [] output;

    public GUI() {
        //construct components
        openFile = new JButton("Open File");
        lexicalButton = new JButton("Lexical Analysis");
        syntaxButton = new JButton("Syntax Analysis");
        semanticButton = new JButton("Semantic Analysis");
        clear = new JButton("Clear");
        resultArea = new JTextField();
        codeArea = new JTextArea(5, 5);
        resultTitle = new JLabel("RESULT:");

        //adjust size and set layout
        setPreferredSize(new Dimension(840, 520));
        setBackground(new Color(168, 225, 227));
        setLayout(null);

        //add components
        add(openFile);
        add(lexicalButton);
        add(syntaxButton);
        add(semanticButton);
        add(clear);
        add(resultArea);
        add(codeArea);
        add(resultTitle);

        //set component bounds & attributes
        openFile.setBounds(30, 70, 180, 55);
            openFile.setFont(new Font("Century Gothic", Font.BOLD, 15));
            openFile.setBackground(new Color(80, 198, 204));
            openFile.addActionListener(this);

        lexicalButton.setBounds(30, 140, 180, 55);
            lexicalButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
            lexicalButton.setEnabled(false);
            lexicalButton.addActionListener(this);

        syntaxButton.setBounds(30, 210, 180, 55);
            syntaxButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
            syntaxButton.setEnabled(false);
            syntaxButton.addActionListener(this);

        semanticButton.setBounds(30, 285, 180, 55);
            semanticButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
            semanticButton.setEnabled(false);
            semanticButton.addActionListener(this);

        clear.setBounds(30, 355, 180, 55);
            clear.setFont(new Font("Century Gothic", Font.BOLD, 15));
            clear.setBackground(new Color(80, 198, 204));
            clear.addActionListener(this);

        resultArea.setBounds(310, 25, 500, 35);
            resultArea.setFont(new Font("Century Gothic", Font.BOLD, 13));
            resultArea.setBackground(new Color(247, 247, 247));
            resultArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
            resultArea.setHorizontalAlignment(JTextField.CENTER);;
            resultArea.setEditable(false);

        codeArea.setBounds(220, 70, 590, 340);
            codeArea.setFont(new Font("Century Gothic", Font.BOLD, 15));
            codeArea.setBackground(new Color(247, 247, 247));
            codeArea.setEditable(false);

        resultTitle.setBounds(220, 25, 85, 35);
            resultTitle.setFont(new Font("Century Gothic", Font.BOLD, 23));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File("C:\\Users\\USER"));

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    codeArea.setText(content.toString());
                    br.close();

                    fileContent = content.toString();
                    codeArea.setText(fileContent);

                    lexicalButton.setBackground(new Color(80, 198, 204));
                    lexicalButton.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } else if (e.getSource() == lexicalButton) {

            output = Tokenizer.tokenize(fileContent);
            resultArea.setText(Arrays.toString(output));
            syntaxButton.setBackground(new Color(80, 198, 204));
            syntaxButton.setEnabled(true);

        } else if (e.getSource() == syntaxButton) {

            if(Parser.parse(output)==false){
                resultArea.setText("Syntax is wrong! Error in compiling, please try again.");
                setClear();
            } else if (Parser.parse(output)==true) {
                resultArea.setText("Syntax is correct!");
                semanticButton.setBackground(new Color(80, 198, 204));
                semanticButton.setEnabled(true);
            }

        } else if (e.getSource() == semanticButton){

            if(SemanticAnalysis.analyzer(fileContent)==false){
                resultArea.setText("Semantically wrong! Error in compiling, please try again.");
                setClear();
            } else if (SemanticAnalysis.analyzer(fileContent)==true) {
                resultArea.setText("Semantically correct!");
            }

        } else if (e.getSource()==clear){
            resultArea.setText("");
            codeArea.setText("");
            setClear();
        }
    }

    public void setClear(){
            fileContent="";
            lexicalButton.setEnabled(false);
            syntaxButton.setEnabled(false);
            semanticButton.setEnabled(false);
            lexicalButton.setBackground(new Color(247, 247, 247));
            syntaxButton.setBackground(new Color(247, 247, 247));
            semanticButton.setBackground(new Color(247, 247, 247));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setTitle("Welcome to my Mini Compiler!");
    }
}