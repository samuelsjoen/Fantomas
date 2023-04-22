package no.uib.inf101.sem2.fantomas;

import javax.swing.JFrame;
import no.uib.inf101.sem2.fantomas.controller.FantomasController;
import no.uib.inf101.sem2.fantomas.model.FantomasBoard;
import no.uib.inf101.sem2.fantomas.model.FantomasModel;
import no.uib.inf101.sem2.fantomas.view.FantomasView;

public class FantomasMain 
{
    public static final String WINDOW_TITLE = "Fantomas";

    public static void main(String[] args) {
      FantomasBoard board = new FantomasBoard(50, 50);
      FantomasModel model = new FantomasModel(board);
      FantomasView view = new FantomasView(model);
      FantomasController controller = new FantomasController(model, view);
  
      // The JFrame is the "root" application window.
      // We here set som properties of the main window, 
      // and tell it to display our fantomasView
      JFrame frame = new JFrame(WINDOW_TITLE);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Here we set which component to view in our window
      frame.setContentPane(view);
      
      // Call these methods to actually display the window
      frame.pack();
      frame.setVisible(true);
    }
}
