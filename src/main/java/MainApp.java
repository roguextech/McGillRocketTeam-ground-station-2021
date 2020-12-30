import java.io.FileInputStream; 
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;  
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.transform.Scale;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import java.util.*;

import java.io.IOException;

public class MainApp extends Application {
	
	/* -=-=-=-=-=-=-=-=-=-=-=-INFO-=-=-=-=-=-=-=-=-=-=-=-
	 * 
	 * IF YOU CHOOSE A NEW MAP IMAGE, MAKE SURE TO UPDATE THE SIZE AND CORNERS
	 * 
	 * ALL NEW MAP IMAGES SHOULD BE ORIENTED WITH NORTH == Y-AXIS
	 * OTHERWISE DIRECTIONS WILL BE OFF
	 * 
	 * 
	*/
	
	// THE SIZE OF THE IMAGE
	private int image_width  = 2878	/8; // divided by two to fit computer size
	private int image_height = 1634	/8;
	
	// THE CORNER LATITUDES AND LONGITUDES 
	final double lower_lat = decimal_converter(33,10,17); //top of the screen
	final double upper_lat = decimal_converter(32,53,01); //bottom of the screen
	
	final double lower_lon = decimal_converter(107,06,20); //left of the screen
	final double upper_lon = decimal_converter(106,48,50); //right of the screen
	
	//	DON'T TOUCH THESE
	private String buttonname = "Resize";
	private boolean fullscreen = false;
	private int count = 0;
	
	//	CONVERTING LATS AND LONS TO DECIMALS
    public static double decimal_converter(double degrees, double minutes, double seconds) {
    	return degrees + minutes/60 + seconds/3600;
    }
    
    //	CONVERTING LON TO X VALUE
    public int find_x (double lon) {
    	int x = Math.abs((int)(((lon - lower_lon)/(upper_lon-lower_lon))*image_width));
    	//System.out.println(x);
    	return x;
    }
    
    //	CONVERTING LAT TO Y VALUE
    public int find_y (double lat) {
    	int y = Math.abs((int)(((lat - lower_lat)/(upper_lat-lower_lat))*image_height));
    	System.out.println(y);
    	return y;
    }
	
    @Override
    public void start(final Stage stage) throws IOException {
    	
    	//	CALCULATING FROM LAT & LON TO DECIMAL
    	double lat = decimal_converter(33,05,03);
    	double lon = decimal_converter(107,03,30);
        
    	//	CREATING CIRCLE
        Circle circle = new Circle();
        
        //	LOCATION OF CIRCLE
        circle.setCenterX(find_x(lon));
        circle.setCenterY(find_y(lat));
        
        //	SIZE & COLOR OF CIRCLE
        circle.setRadius(5); 
        circle.setFill(Color.RED);
        
    	//	OBTAINING IMAGE
    	InputStream stream = new FileInputStream("C:\\Users\\1234\\git\\Ground-station-2021\\GoogleEarthMap.jpg");
        
    	//	RESIZING IMAGE
    	Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitWidth(image_width);

        
        int heightpluswidth = image_height + image_width;
        int heightaddition = 1000*(image_height/heightpluswidth);
        int widthaddition = 1000*(image_width/heightpluswidth);
        
        //	CREATING BUTTON
        Button button = new Button(buttonname);
        
        //	PRESSING THE BUTTON
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) {
            	
            	if (!fullscreen) {            			//	-=-=-=-=-=-=-=-=-	MAXIMIZING	-=-=-=-=-=-=-=-=-=-

	                System.out.println("maximizing");
	                
	                // ALTERNATE BOOLEAN FOR NEXT BUTTON USE
	                if (count == 4) fullscreen = true;
	                
	                //	RESIZE THE IMAGE
	                image_height += 1634	/10;
	                image_width  += 2878	/10;
	                imageView.setFitWidth(image_width);
	                count++;
	                
	                //======SIMPLY A REPEAT OF INITIAL SETUP TO ENSURE THE CORRECT LOCATION OF THE CIRCLE=======
	                //	CALCULATES NEW DECIMAL LAT AND LON BASED ON NEW WINDOW HEIGHT AND WIDTH
	            	double lat = decimal_converter(33,05,03);
	            	double lon = decimal_converter(107,03,30);
//	            	CREATING CIRCLE
	                Circle circle = new Circle();
	                //	LOCATION OF CIRCLE
	                circle.setCenterX(find_x(lon));
	                circle.setCenterY(find_y(lat));
	                //	SIZE & COLOR OF CIRCLE
	                circle.setRadius(5); 
	                circle.setFill(Color.RED);
//	                imageView.setPreserveRatio(true);
	                Group root = new Group(imageView,circle,button);
	                Scene scene = new Scene(root, image_width, image_height);
//	                final double newWidth  = scene.getWidth();
//	                final double newHeight = scene.getHeight();
	                stage.setTitle("Displaying Image");
	                stage.setScene(scene);
	                stage.show();
            	}
            	else if (fullscreen) {	            		//	-=-=-=-=-=-=-=-=-	MINIMIZING	-=-=-=-=-=-=-=-=-=-

            		System.out.println("minimizing");
            		
	                // ALTERNATE BOOLEAN FOR NEXT BUTTON USE
	                fullscreen = false;
            		count = 0;
            		//stage.setFullScreen(false);
	                image_height -= 1634	/2;
	                image_width  -= 2878	/2;
	                imageView.setFitWidth(image_width);

	                //======SIMPLY A REPEAT OF INITIAL SETUP TO ENSURE THE CORRECT LOCATION OF THE CIRCLE======
	                //	CALCULATES NEW DECIMAL LAT AND LON BASED ON NEW WINDOW HEIGHT AND WIDTH
	            	double lat = decimal_converter(33,05,03);
	            	double lon = decimal_converter(107,03,30);
	            	//	CREATING CIRCLE
	                Circle circle = new Circle();
	                //	LOCATION OF CIRCLE
	                circle.setCenterX(find_x(lon));
	                circle.setCenterY(find_y(lat));
	                //	SIZE & COLOR OF CIRCLE
	                circle.setRadius(5); 
	                circle.setFill(Color.RED);
	                Group root = new Group(imageView,circle,button);
	                Scene scene = new Scene(root, image_width, image_height);
	                stage.setTitle("Displaying Image");
	                stage.setScene(scene);
	                stage.show();
            	}
            } 
        }; 
        button.setOnAction(event);

        //	CREATING FINAL PRODUCT
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView,circle,button);
        Scene scene = new Scene(root, image_width, image_height);
        
        final double newWidth  = scene.getWidth();
        final double newHeight = scene.getHeight();

        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.show();
    }
    

    
    public static void main(String[] args) {
        launch(args);
    }
}