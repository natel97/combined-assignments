package com.cooksys.ftd.assignments.socket;

import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;

//import com.cooksys.ftd.assignments.socket.model.Config;
//import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     * @throws JAXBException 
     * @throws IOException 
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws JAXBException, UnknownHostException, IOException {
    	
    	RemoteConfig cfg = Utils.loadConfig("config/config.xml",Utils.createJAXBContext()).getRemote();
        Socket topSecretData = new Socket(cfg.getHost(), cfg.getPort());
        
        DataInputStream in = new DataInputStream(topSecretData.getInputStream());
        
        Student incomingStud = (Student) JAXBContext.newInstance(Student.class).createUnmarshaller().unmarshal(in);

        
        System.out.println(incomingStud.toString());

    	topSecretData.close();
    	
    	
    	//init stuffs
    	//JAXBContext context = JAXBContext.newInstance(Config.class);
    	//JAXBContext studentContext = JAXBContext.newInstance(Student.class);
    	
    	
    	//create config file
//    	
//    	String host = "localhost";
//    	int port = 3000;
    	
//    	Marshaller mar = context.createMarshaller();
//    	
//    	Config cfg = new Config();
//    	LocalConfig lcfg = new LocalConfig();
//    	RemoteConfig rcfg = new RemoteConfig();
//    	    	
//    	
//    	lcfg.setPort(port);
//    	rcfg.setHost(host);
//    	rcfg.setPort(port);
//    	
//    	
//    	cfg.setLocal(lcfg);
//    	cfg.setRemote(rcfg);
//    	cfg.setStudentFilePath("student.xml");
//    	
//    	FileOutputStream fos = new FileOutputStream(new File("config/config.xml"));
//    	
//    	
//    	mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//    	
//        mar.marshal(cfg, fos);
    	
    	
    	//Create student
//    	Student std = new Student();
//    	std.setFavoriteIDE("Sublime Text");
//    	std.setFavoriteLanguage("C#");
//    	std.setFirstName("Nathanial");
//    	std.setLastName("Lubitz");
//    	std.setFavoriteParadigm("Whats a Paradigm? Still don't quite understand after a quick Google search..");
//    	
//    	FileOutputStream sfos = new FileOutputStream(new File("config/student.xml"));
//    	
//    	studentContext.createMarshaller().marshal(std,sfos);
    	
//    	
    	//load config file
    	
    	
    	
    	
    }
}
