
import javax.swing.*;




public class App {
    private static void createAndShowGUI() {
        JFrame window = new JFrame("HelloWorldSwing");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        window.add(screen);
        window.addKeyListener(screen);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    
    
    
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                System.out.println("Space changes turns, it has to be player 1's turn to move white's pieces" +
                        " and player 2's turn to move black's pieces") ;
            }
        });
    }
}
