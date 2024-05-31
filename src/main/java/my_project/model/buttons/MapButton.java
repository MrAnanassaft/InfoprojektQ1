package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.Background;

import static my_project.control.ProgramController.viewController;

public class MapButton extends Buttons{

    private String[] pathToImage = new String[3];
    public MapButton(ProgramController p){
        super(p);
        setPicture("src/main/resources/graphic/buttons/Mapselectbutton.png");
        pathToImage[0] = "src/main/resources/graphic/backgrounds/Background Cave.png";
        pathToImage[1] = "src/main/resources/graphic/backgrounds/Background Plains.png";
        pathToImage[2] = "src/main/resources/graphic/backgrounds/Background Vulcan.png";
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed){
                    wasPressed = true;
                    getViewController().showScene(5);
                    create(3,5,200,400,300,pathToImage,0.1);
                }
            }

            @Override
            public int getSceneIndex() {
                return 0;
            }

            @Override
            public ViewController getViewController() {
                return ProgramController.viewController;
            }
        };

        button = new Button(buttonHandler, 0, 1200, 650,image,false);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,0);
    }

    public static void useButtons(String image){
        if(image == "src/main/resources/graphic/backgrounds/Background Cave.png"){
            Background.setBackground(1);
        }else if(image == "src/main/resources/graphic/backgrounds/Background Plains.png"){
            Background.setBackground(2);
        }else if(image == "src/main/resources/graphic/backgrounds/Background Vulcan.png"){
            Background.setBackground(3);
        }
    }
}
