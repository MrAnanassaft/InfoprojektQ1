package KAGO_framework.view.simple_gui;

import KAGO_framework.control.Interactable;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Variable_Container;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static my_project.control.ProgramController.viewController;
/**
 * Repräsentiert ein anklickbares Label
 */
public class Button extends Label implements Interactable {

    //Referenzen
    private ButtonHandler buttonHandler;
    //Attribute
    private int actionCode;
    protected boolean isHovered;
    private boolean isTouched = false;
    /**
     * Erzeugt einen neuen, simplen und nicht sonderlich schönen, aber funktionalen Button
     * Entspricht einem anklickbaren Label
     * @param bH der zugewiesene ButtonHandler
     * @param actionCode der Action-Code, den dieser Button benutzen soll
     * @param x x obere linke Ecke des Buttons
     * @param y y obere linke Ecke des Buttons
     * @param text Beschriftung des Buttons
     * @param textsize Textgröße für die Beschriftung
     */
    public Button(ButtonHandler bH, int actionCode, double x, double y, String text, int textsize){
        super(x,y,text,textsize,true);
        buttonHandler = bH;
        buttonHandler.getViewController().draw(this,buttonHandler.getSceneIndex());
        buttonHandler.getViewController().register(this,buttonHandler.getSceneIndex());
        this.actionCode = actionCode;
    }

    /**
     * Erzeugt einen neuen, simplen und nicht sonderlich schönen, aber funktionalen Button
     * Entspricht einem anklickbaren Label
     * @param bH der zugewiesene ButtonHandler
     * @param actionCode der Action-Code, den dieser Button benutzen soll
     * @param x x obere linke Ecke des Buttons
     * @param y y obere linke Ecke des Buttons
     * @param image das Bild, das der Button benutzen soll
     * @param hasBorder true, falls ein Rahmen gezeichnet werden soll
     */
    public Button(ButtonHandler bH, int actionCode, double x, double y, BufferedImage image, boolean hasBorder){
        super(x,y,image,hasBorder);
        buttonHandler = bH;
        buttonHandler.getViewController().draw(this,buttonHandler.getSceneIndex());
        buttonHandler.getViewController().register(this,buttonHandler.getSceneIndex());
        this.actionCode = actionCode;
    }

    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        if(isTouched){
            drawTool.setLineWidth(4);
            drawTool.setCurrentColor(Color.RED);
            drawTool.drawRectangle(this.x,this.y,this.getWidth(),this.getHeight());
            drawTool.setLineWidth(1);
        }
    }

    @Override
    public void update(double dt) {

    }

    /**
     * Sendet bei Mausklick auf den Button den entsprechenden ActionCode an den zugehörigen
     * ButtonHandler
     * @param e Das übergebene Objekt der Klasse MouseEvent enthält alle Information über das Ereignis.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        if(mouseX > x && mouseX < x+width && mouseY > y && mouseY < y+height){
            if(viewController.getSceneIndex() == 3){
                buttonHandler.processButtonClick(1);
            }else if(viewController.getSceneIndex() == 4){
                buttonHandler.processButtonClick(2);
            }else if(viewController.getSceneIndex() == 5){
                buttonHandler.processButtonClick(3);
            }else {
                buttonHandler.processButtonClick(actionCode);
            }
        }
    }

    @Override
    public void keyPressed(int key) {

    }
    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    public boolean mouseHovered(MouseEvent e){
        double mouseX = e.getX();
        double mouseY = e.getY();
        if(mouseX > x && mouseX < x+width && mouseY > y && mouseY < y+height){
            return true;
        }else{
            return false;
        }
    }
    public void isTouched(){
        isTouched =  true;
    }
    public void isNotTouched(){
        isTouched = false;
    }
    public ButtonHandler getButtonHandler(){
        return buttonHandler;
    }
}
